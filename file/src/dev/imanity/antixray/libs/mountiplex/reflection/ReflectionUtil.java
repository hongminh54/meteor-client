// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection;

import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.ASMUtil;
import java.util.ListIterator;
import java.util.Collection;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import dev.imanity.antixray.libs.mountiplex.reflection.util.signature.MethodSignature;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.Arrays;
import java.lang.reflect.Method;
import java.util.function.Function;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.util.stream.Stream;
import java.lang.reflect.Modifier;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;

public class ReflectionUtil
{
    public static String filterGenerics(final String s) {
        final int index = s.indexOf(62);
        if (index == -1) {
            return s;
        }
        final int lastIndex = s.lastIndexOf(60, index);
        if (lastIndex == -1) {
            return s;
        }
        return filterGenerics(s.substring(0, lastIndex) + s.substring(index + 1));
    }
    
    public static String getTypeName(Class<?> componentType) {
        int n;
        for (n = 0; componentType.isArray(); componentType = componentType.getComponentType(), ++n) {}
        String str;
        if (componentType.isPrimitive() || BoxedType.getUnboxedType(componentType) != null) {
            str = componentType.getSimpleName();
        }
        else {
            str = MPLType.getName(componentType);
        }
        for (int i = 0; i < n; ++i) {
            str += "[]";
        }
        return str;
    }
    
    public static String getAccessibleTypeName(final Class<?> clazz) {
        if (Resolver.isPublic(clazz)) {
            String s = getTypeName(clazz);
            if (!Resolver.resolveClassPath(s).equals(s)) {
                s = "MPL_NOREMAP$" + s;
            }
            return s;
        }
        return "Object";
    }
    
    public static String getAccessibleTypeCast(final Class<?> clazz) {
        if (clazz == Object.class || !Resolver.isPublic(clazz)) {
            return "";
        }
        final String typeName = getTypeName(clazz);
        if (Resolver.resolveClassPath(typeName).equals(typeName)) {
            return '(' + typeName + ')';
        }
        return "(MPL_NOREMAP$" + typeName + ')';
    }
    
    public static String getCastString(final Class<?> clazz) {
        final StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(getTypeName(clazz));
        sb.append(')');
        return sb.toString();
    }
    
    public static int parseModifiers(final String[] array, final int n) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            if (array[i].equals("public")) {
                n2 |= 0x1;
            }
            else if (array[i].equals("private")) {
                n2 |= 0x2;
            }
            else if (array[i].equals("protected")) {
                n2 |= 0x4;
            }
            else if (array[i].equals("final")) {
                n2 |= 0x10;
            }
            else if (array[i].equals("static")) {
                n2 |= 0x8;
            }
            else if (array[i].equals("volatile")) {
                n2 |= 0x40;
            }
            else if (array[i].equals("abstract")) {
                n2 |= 0x400;
            }
        }
        return n2;
    }
    
    public static boolean compareModifiers(final int mod, final int mod2) {
        return Modifier.isPrivate(mod) == Modifier.isPrivate(mod2) && Modifier.isPublic(mod) == Modifier.isPublic(mod2) && Modifier.isProtected(mod) == Modifier.isProtected(mod2) && Modifier.isStatic(mod) == Modifier.isStatic(mod2) && Modifier.isFinal(mod) == Modifier.isFinal(mod2);
    }
    
    public static Stream<Class<?>> getAllDeclaringClasses(final Class<?> clazz) {
        return MountiplexUtil.iterateNullTerminated(clazz, Class::getDeclaringClass);
    }
    
    public static Stream<Class<?>> getAllClasses(final Class<?> clazz) {
        return MountiplexUtil.iterateNullTerminated(clazz, Class::getSuperclass);
    }
    
    public static Stream<Class<?>> getAllClassesAndInterfaces(final Class<?> clazz) {
        return Stream.concat((Stream<? extends Class<?>>)getAllClasses(clazz), (Stream<? extends Class<?>>)getAllClasses(clazz).flatMap((Function<? super Class<?>, ? extends Stream<?>>)ReflectionUtil::discoverAllInterfaces).distinct());
    }
    
    private static Stream<Class<?>> discoverAllInterfaces(final Class<?> clazz) {
        final Class[] interfaces = clazz.getInterfaces();
        return (Stream<Class<?>>)Stream.concat((Stream<?>)Stream.of((T[])interfaces), (Stream<?>)Stream.of((T[])interfaces).flatMap((Function<? super T, ? extends Stream<?>>)ReflectionUtil::discoverAllInterfaces));
    }
    
    public static Stream<Method> getDeclaredMethods(final Class<?> clazz) {
        return Stream.of(sortSyntheticMethods(clazz.getDeclaredMethods()));
    }
    
    public static Stream<Method> getMethods(final Class<?> clazz) {
        return Stream.of(sortSyntheticMethods(clazz.getMethods()));
    }
    
    private static Method[] sortSyntheticMethods(final Method[] a) {
        Arrays.sort(a, 0, a.length, (method, method2) -> {
            final int n = method.getModifiers() | method2.getModifiers();
            if (Modifier.isPrivate(n) || Modifier.isStatic(n) || !MPLType.getName(method).equals(MPLType.getName(method2)) || !Arrays.equals(method.getParameterTypes(), method2.getParameterTypes()) || method.getReturnType().equals(method2.getReturnType())) {
                return 0;
            }
            else if (method.getReturnType().isAssignableFrom(method2.getReturnType())) {
                return 1;
            }
            else {
                return -1;
            }
        });
        return a;
    }
    
    public static Stream<Method> getAllMethods(final Class<?> clazz) {
        return getAllClassesAndInterfaces(clazz).flatMap((Function<? super Class<?>, ? extends Stream<? extends Method>>)ReflectionUtil::getDeclaredMethods).filter(createDuplicateMethodFilter());
    }
    
    public static Predicate<Method> createDuplicateMethodFilter() {
        return method -> {
            final Object o = new HashSet();
            final boolean b;
            if (!Modifier.isStatic(method.getModifiers())) {
                if (!set.add(new MethodSignature(method))) {
                    return b;
                }
            }
            return b;
        };
    }
    
    public static Stream<Field> getAllFields(final Class<?> clazz) {
        return MountiplexUtil.iterateNullTerminated(clazz, Class::getSuperclass).flatMap(clazz2 -> Stream.of(clazz2.getDeclaredFields()));
    }
    
    public static Stream<Field> getAllNonStaticFields(final Class<?> clazz) {
        return getAllFields(clazz).filter(field -> !Modifier.isStatic(field.getModifiers()));
    }
    
    public static Stream<Field> getAllStaticFields(final Class<?> clazz) {
        return getAllFields(clazz).filter(field -> Modifier.isStatic(field.getModifiers()));
    }
    
    public static String stringifyType(final Class<?> clazz) {
        return (clazz == null) ? "[null]" : clazz.getSimpleName();
    }
    
    public static String stringifyMethodSignature(final Method method) {
        String str = Modifier.toString(method.getModifiers() & 0xFFFFFFBF) + " " + stringifyType(method.getReturnType()) + " " + MPLType.getName(method) + "(";
        int n = 1;
        for (final Class<?> clazz : method.getParameterTypes()) {
            if (n != 0) {
                n = 0;
            }
            else {
                str += ", ";
            }
            str += stringifyType(clazz);
        }
        return str + ")";
    }
    
    private static boolean hasMethod(final Class<?> clazz, final Method method) {
        try {
            return MPLType.getDeclaredMethod(clazz, MPLType.getName(method), method.getParameterTypes()) != null;
        }
        catch (final Throwable t) {
            return false;
        }
    }
    
    public static Class<?> findMethodClass(final Method method) {
        Class<?> clazz = method.getDeclaringClass();
        for (final Class clazz2 : clazz.getInterfaces()) {
            if (hasMethod(clazz2, method)) {
                return clazz2;
            }
        }
        Class clazz3 = clazz;
        while ((clazz = clazz.getSuperclass()) != null) {
            if (hasMethod(clazz, method)) {
                clazz3 = clazz;
            }
        }
        return clazz3;
    }
    
    public static <A extends Annotation, V> V recurseFindAnnotationValue(final Class<?> clazz, final Class<A> clazz2, final Function<A, V> function, final V other) {
        return getAllDeclaringClasses(clazz).map(clazz3 -> clazz3.getAnnotation(annotationClass)).filter(Objects::nonNull).map((Function<? super Object, ? extends V>)function).findFirst().orElse(other);
    }
    
    public static RuntimeException fixMethodInvokeException(final Method method, final Object o, final Object[] array, Throwable cleanCause) {
        Throwable t = null;
        if (cleanCause instanceof InvocationTargetException) {
            cleanCause = getCleanCause(cleanCause);
        }
        else {
            if (cleanCause instanceof IllegalAccessException) {
                filterInvokeTraceElements(cleanCause, 0);
                final RuntimeException ex = new RuntimeException("Failed to invoke method " + stringifyMethodSignature(method), cleanCause);
                ex.setStackTrace(new StackTraceElement[0]);
                return ex;
            }
            if (Modifier.isStatic(method.getModifiers())) {
                if (o != null) {
                    t = new IllegalArgumentException("Method " + stringifyMethodSignature(method) + " is static and can not be invoked on instance of type " + stringifyType(o.getClass()));
                }
            }
            else if (o == null) {
                t = new IllegalArgumentException("Method " + stringifyMethodSignature(method) + " is not static and requires an instance passed in (instance is null)");
            }
            else {
                final Class<?> methodClass = findMethodClass(method);
                if (!methodClass.isInstance(o)) {
                    t = new IllegalArgumentException("Method " + stringifyMethodSignature(method) + " is declared in class " + stringifyType(methodClass) + " and can not be invoked on object of type " + stringifyType(o.getClass()));
                }
            }
            if (t == null) {
                final Class<?>[] parameterTypes = method.getParameterTypes();
                if (array.length != parameterTypes.length) {
                    t = new IllegalArgumentException("Method " + stringifyMethodSignature(method) + " Illegal number of arguments provided. Expected " + parameterTypes.length + ", but got " + array.length);
                }
                else {
                    for (int i = 0; i < parameterTypes.length; ++i) {
                        final Object o2 = array[i];
                        if (parameterTypes[i].isPrimitive() && o2 == null) {
                            t = new IllegalArgumentException("Method " + stringifyMethodSignature(method) + " Passed in null for primitive type parameter #" + (i + 1));
                            break;
                        }
                        if (o2 != null && !BoxedType.tryBoxType(parameterTypes[i]).isInstance(o2)) {
                            t = new IllegalArgumentException("Method " + stringifyMethodSignature(method) + " Passed in wrong type for parameter #" + (i + 1) + " (" + stringifyType(parameterTypes[i]) + " expected, but was " + stringifyType(o2.getClass()) + ")");
                            break;
                        }
                    }
                }
            }
            if (t == null && cleanCause instanceof IllegalArgumentException) {
                t = cleanCause;
            }
            if (t != null) {
                t.setStackTrace(cleanCause.getStackTrace());
                filterInvokeTraceElements(t, 0);
                addMethodTraceElement(t, method);
                return (RuntimeException)t;
            }
        }
        if (cleanCause instanceof ExceptionInInitializerError) {
            final ExceptionInInitializerError exceptionInInitializerError = (ExceptionInInitializerError)cleanCause;
            filterCause(exceptionInInitializerError);
            filterInvokeTraceElements(exceptionInInitializerError, 0);
        }
        if (cleanCause instanceof Error) {
            throw (Error)cleanCause;
        }
        Throwable t2;
        if (cleanCause instanceof RuntimeException) {
            t2 = cleanCause;
        }
        else {
            t2 = new RuntimeException("Failed to invoke method " + stringifyMethodSignature(method), cleanCause);
            t2.setStackTrace(new StackTraceElement[0]);
        }
        return (RuntimeException)t2;
    }
    
    private static Throwable getCleanCause(final Throwable t) {
        final Throwable cause = t.getCause();
        filterInvokeTraceElements(cause, cause.getStackTrace().length - t.getStackTrace().length);
        return cause;
    }
    
    private static void filterCause(final Throwable t) {
        if (t.getCause() != null) {
            filterCause(t.getCause());
            final ArrayList list = new ArrayList((Collection<? extends E>)Arrays.asList(t.getCause().getStackTrace()));
            final StackTraceElement[] stackTrace = t.getStackTrace();
            for (int n = stackTrace.length - 1; n >= 0 && ((StackTraceElement)list.get(list.size() - 1)).equals(stackTrace[n]); --n) {
                list.remove(list.size() - 1);
            }
            t.getCause().setStackTrace((StackTraceElement[])list.toArray(new StackTraceElement[list.size()]));
        }
    }
    
    private static void filterInvokeTraceElements(final Throwable t, final int n) {
        final ArrayList list = new ArrayList((Collection<? extends E>)Arrays.asList(t.getStackTrace()));
        if (n >= 0 && n < list.size()) {
            final ListIterator listIterator = list.listIterator(n);
            while (listIterator.hasNext()) {
                final StackTraceElement stackTraceElement = (StackTraceElement)listIterator.next();
                final String className = stackTraceElement.getClassName();
                if (className.equals("java.lang.reflect.Method") && stackTraceElement.getMethodName().equals("invoke")) {
                    break;
                }
                if (!className.startsWith("java.lang.reflect.") && !className.startsWith("sun.reflect.")) {
                    break;
                }
                listIterator.remove();
            }
            t.setStackTrace((StackTraceElement[])list.toArray(new StackTraceElement[list.size()]));
        }
    }
    
    private static void addMethodTraceElement(final Throwable t, final Method method) {
        final StackTraceElement[] stackTrace = t.getStackTrace();
        final StackTraceElement[] stackTrace2 = new StackTraceElement[stackTrace.length + 1];
        System.arraycopy(stackTrace, 0, stackTrace2, 1, stackTrace.length);
        stackTrace2[0] = ASMUtil.findMethodDetails(method);
        t.setStackTrace(stackTrace2);
    }
}
