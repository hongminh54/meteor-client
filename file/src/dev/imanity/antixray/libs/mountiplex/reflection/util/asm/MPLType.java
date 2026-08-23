// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.asm;

import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.security.ProtectionDomain;
import dev.imanity.antixray.libs.asm.ClassWriter;
import dev.imanity.antixray.libs.mountiplex.reflection.util.GeneratorClassLoader;
import java.net.URLClassLoader;
import java.lang.reflect.Field;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ParameterDeclaration;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ParameterListDeclaration;
import dev.imanity.antixray.libs.asm.Type;
import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;
import dev.imanity.antixray.libs.asm.MethodVisitor;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.MethodDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

public class MPLType
{
    private static final MPLTypeHelper REMAPPING_DISABLED_HELPER;
    private static final MPLTypeHelper REMAPPING_ENABLED_HELPER;
    private static MPLTypeHelper helper;
    
    public static void setRemappingEnabled(final boolean b) {
        MPLType.helper = (b ? MPLType.REMAPPING_ENABLED_HELPER : MPLType.REMAPPING_DISABLED_HELPER);
    }
    
    public static String getInternalName(final Class<?> clazz) {
        return MPLType.helper.getClassName(clazz).replace('.', '/');
    }
    
    public static String[] getInternalNames(final Class<?>[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = getInternalName(array[i]);
        }
        return array2;
    }
    
    public static String getDescriptor(final Class<?> clazz) {
        final StringBuilder sb = new StringBuilder();
        appendDescriptor(clazz, sb);
        return sb.toString();
    }
    
    public static String getConstructorDescriptor(final Constructor<?> constructor) {
        final StringBuilder sb = new StringBuilder();
        sb.append('(');
        final Class[] parameterTypes = constructor.getParameterTypes();
        for (int length = parameterTypes.length, i = 0; i < length; ++i) {
            appendDescriptor(parameterTypes[i], sb);
        }
        return sb.append(")V").toString();
    }
    
    public static String getMethodDescriptor(final Method method) {
        return getMethodDescriptor(method.getReturnType(), method.getParameterTypes());
    }
    
    public static String getInternalMethodDescriptor(final MethodDeclaration methodDeclaration) {
        final Class<?> type = methodDeclaration.returnType.type;
        final Class[] array = new Class[methodDeclaration.parameters.parameters.length];
        for (int i = 0; i < array.length; ++i) {
            array[i] = methodDeclaration.parameters.parameters[i].type.type;
        }
        return getMethodDescriptor(type, array);
    }
    
    public static String getMethodDescriptor(final Class<?> clazz, final Class<?>[] array) {
        final StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (int length = array.length, i = 0; i < length; ++i) {
            appendDescriptor(array[i], sb);
        }
        sb.append(')');
        appendDescriptor(clazz, sb);
        return sb.toString();
    }
    
    private static void appendDescriptor(final Class<?> clazz, final StringBuilder sb) {
        Class<Integer> componentType;
        for (componentType = (Class<Integer>)clazz; componentType.isArray(); componentType = componentType.getComponentType()) {
            sb.append('[');
        }
        if (componentType.isPrimitive()) {
            char c;
            if (componentType == Integer.TYPE) {
                c = 'I';
            }
            else if (componentType == Void.TYPE) {
                c = 'V';
            }
            else if (componentType == Boolean.TYPE) {
                c = 'Z';
            }
            else if (componentType == Byte.TYPE) {
                c = 'B';
            }
            else if (componentType == Character.TYPE) {
                c = 'C';
            }
            else if (componentType == Short.TYPE) {
                c = 'S';
            }
            else if (componentType == Double.TYPE) {
                c = 'D';
            }
            else if (componentType == Float.TYPE) {
                c = 'F';
            }
            else {
                if (componentType != Long.TYPE) {
                    throw new AssertionError();
                }
                c = 'J';
            }
            sb.append(c);
        }
        else {
            sb.append('L');
            final String className = MPLType.helper.getClassName(componentType);
            for (int length = className.length(), i = 0; i < length; ++i) {
                final char char1 = className.charAt(i);
                sb.append((char1 == '.') ? '/' : char1);
            }
            sb.append(';');
        }
    }
    
    public static int getOpcode(final Class<?> clazz, final int n) {
        return getType(clazz).getOpcode(n);
    }
    
    public static void visitBoxVariable(final MethodVisitor methodVisitor, final Class<?> clazz) {
        if (clazz == Void.TYPE) {
            methodVisitor.visitInsn(1);
        }
        else if (clazz.isPrimitive()) {
            final Class<?> boxedType = BoxedType.getBoxedType(clazz);
            if (boxedType != null) {
                methodVisitor.visitMethodInsn(184, getInternalName(boxedType), "valueOf", "(" + getDescriptor(clazz) + ")" + getDescriptor(boxedType), false);
            }
        }
    }
    
    public static void visitUnboxVariable(final MethodVisitor methodVisitor, final Class<?> clazz) {
        if (clazz == Void.TYPE) {
            methodVisitor.visitInsn(1);
        }
        else if (clazz.isPrimitive()) {
            methodVisitor.visitMethodInsn(182, getInternalName(BoxedType.getBoxedType(clazz)), clazz.getSimpleName() + "Value", "()" + getDescriptor(clazz), false);
        }
    }
    
    public static int visitVarILoad(final MethodVisitor methodVisitor, final int n, final Class<?> clazz) {
        final Type type = Type.getType(clazz);
        methodVisitor.visitVarInsn(type.getOpcode(21), n);
        return n + type.getSize();
    }
    
    public static int visitVarILoad(final MethodVisitor methodVisitor, final int n, final Class<?>... array) {
        int visitVarILoad = n;
        for (int length = array.length, i = 0; i < length; ++i) {
            visitVarILoad = visitVarILoad(methodVisitor, visitVarILoad, array[i]);
        }
        return visitVarILoad;
    }
    
    public static int visitVarILoad(final MethodVisitor methodVisitor, final int n, final ParameterListDeclaration parameterListDeclaration) {
        int visitVarILoad = n;
        final ParameterDeclaration[] parameters = parameterListDeclaration.parameters;
        for (int length = parameters.length, i = 0; i < length; ++i) {
            visitVarILoad = visitVarILoad(methodVisitor, visitVarILoad, parameters[i].type.type);
        }
        return visitVarILoad;
    }
    
    public static int visitVarILoadAndBox(final MethodVisitor methodVisitor, final int n, final Class<?> clazz) {
        final int visitVarILoad = visitVarILoad(methodVisitor, n, clazz);
        visitBoxVariable(methodVisitor, clazz);
        return visitVarILoad;
    }
    
    public static int visitVarIStore(final MethodVisitor methodVisitor, final int n, final Class<?> clazz) {
        final Type type = Type.getType(clazz);
        methodVisitor.visitVarInsn(type.getOpcode(54), n);
        return n + type.getSize();
    }
    
    public static int visitVarIStore(final MethodVisitor methodVisitor, final int n, final Class<?>... array) {
        int visitVarIStore = n;
        for (int length = array.length, i = 0; i < length; ++i) {
            visitVarIStore = visitVarIStore(methodVisitor, visitVarIStore, array[i]);
        }
        return visitVarIStore;
    }
    
    public static Type getReturnType(final Method method) {
        return getType(method.getReturnType());
    }
    
    public static Type[] getArgumentTypes(final Method method) {
        final Class<?>[] parameterTypes = method.getParameterTypes();
        final Type[] array = new Type[parameterTypes.length];
        for (int i = parameterTypes.length - 1; i >= 0; --i) {
            array[i] = getType(parameterTypes[i]);
        }
        return array;
    }
    
    public static Type[] getTypes(final Class<?>... array) {
        final Type[] array2 = new Type[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = getType(array[i]);
        }
        return array2;
    }
    
    public static Type getType(final Class<?> clazz) {
        if (!clazz.isPrimitive()) {
            return Type.getType(getDescriptor(clazz));
        }
        if (clazz == Integer.TYPE) {
            return Type.INT_TYPE;
        }
        if (clazz == Void.TYPE) {
            return Type.VOID_TYPE;
        }
        if (clazz == Boolean.TYPE) {
            return Type.BOOLEAN_TYPE;
        }
        if (clazz == Byte.TYPE) {
            return Type.BYTE_TYPE;
        }
        if (clazz == Character.TYPE) {
            return Type.CHAR_TYPE;
        }
        if (clazz == Short.TYPE) {
            return Type.SHORT_TYPE;
        }
        if (clazz == Double.TYPE) {
            return Type.DOUBLE_TYPE;
        }
        if (clazz == Float.TYPE) {
            return Type.FLOAT_TYPE;
        }
        if (clazz == Long.TYPE) {
            return Type.LONG_TYPE;
        }
        throw new AssertionError();
    }
    
    public static String getName(final Class<?> clazz) {
        return MPLType.helper.getClassName(clazz);
    }
    
    public static String getName(final Method method) {
        return Resolver.resolveCompiledMethodName(method.getDeclaringClass(), MPLType.helper.getMethodName(method), method.getParameterTypes());
    }
    
    public static String getName(final Field field) {
        return Resolver.resolveCompiledFieldName(field.getDeclaringClass(), MPLType.helper.getFieldName(field));
    }
    
    public static Method getDeclaredMethod(final Class<?> clazz, final String s, final Class<?>... array) {
        return MPLType.helper.getDeclaredMethod(clazz, s, array);
    }
    
    public static Field getDeclaredField(final Class<?> clazz, final String s) {
        return MPLType.helper.getDeclaredField(clazz, s);
    }
    
    public static Class<?> getClassByName(final String str, final boolean b, final ClassLoader classLoader) {
        try {
            try {
                return MPLType.helper.getClassByName(str, b, classLoader);
            }
            catch (final IllegalStateException ex) {
                if ("zip file closed".equals(ex.getMessage()) && classLoader instanceof URLClassLoader) {
                    throw new LoaderClosedException();
                }
                throw new ClassNotFoundException("Failed to load class " + str, ex);
            }
        }
        catch (final ClassNotFoundException ex2) {
            final Class<?> generatedClass = GeneratorClassLoader.findGeneratedClass(str);
            if (generatedClass != null) {
                return generatedClass;
            }
            throw ex2;
        }
    }
    
    public static Class<?> getClassByName(final String str) {
        try {
            try {
                return MPLType.helper.getClassByName(str, false, MPLType.class.getClassLoader());
            }
            catch (final IllegalStateException ex) {
                if ("zip file closed".equals(ex.getMessage())) {
                    throw new LoaderClosedException();
                }
                throw new ClassNotFoundException("Failed to load class " + str, ex);
            }
        }
        catch (final ClassNotFoundException ex2) {
            final Class<?> generatedClass = GeneratorClassLoader.findGeneratedClass(str);
            if (generatedClass != null) {
                return generatedClass;
            }
            throw ex2;
        }
    }
    
    private static MPLTypeHelper generateNoRemappingHelper() {
        final String replace = MPLTypeHelper.class.getName().replace('.', '/');
        final String string = MPLType.class.getName().replace('.', '/') + "$HelperImpl";
        final String string2 = "Ljava/lang/Object;L" + replace + ";";
        final ClassWriter classWriter = new ClassWriter(0);
        classWriter.visit(52, 9, string, string2, "java/lang/Object", new String[] { replace });
        final MethodVisitor visitMethod = classWriter.visitMethod(1, "<init>", "()V", null, null);
        visitMethod.visitCode();
        visitMethod.visitVarInsn(25, 0);
        visitMethod.visitMethodInsn(183, "java/lang/Object", "<init>", "()V", false);
        visitMethod.visitInsn(177);
        visitMethod.visitMaxs(1, 1);
        visitMethod.visitEnd();
        final MethodVisitor visitMethod2 = classWriter.visitMethod(1, "getClassName", "(Ljava/lang/Class;)Ljava/lang/String;", "(Ljava/lang/Class<*>;)Ljava/lang/String;", null);
        visitMethod2.visitCode();
        visitMethod2.visitVarInsn(25, 1);
        visitMethod2.visitMethodInsn(182, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
        visitMethod2.visitInsn(176);
        visitMethod2.visitMaxs(1, 2);
        visitMethod2.visitEnd();
        final MethodVisitor visitMethod3 = classWriter.visitMethod(1, "getMethodName", "(Ljava/lang/reflect/Method;)Ljava/lang/String;", null, null);
        visitMethod3.visitCode();
        visitMethod3.visitVarInsn(25, 1);
        visitMethod3.visitMethodInsn(182, "java/lang/reflect/Method", "getName", "()Ljava/lang/String;", false);
        visitMethod3.visitInsn(176);
        visitMethod3.visitMaxs(1, 2);
        visitMethod3.visitEnd();
        final MethodVisitor visitMethod4 = classWriter.visitMethod(1, "getFieldName", "(Ljava/lang/reflect/Field;)Ljava/lang/String;", null, null);
        visitMethod4.visitCode();
        visitMethod4.visitVarInsn(25, 1);
        visitMethod4.visitMethodInsn(182, "java/lang/reflect/Field", "getName", "()Ljava/lang/String;", false);
        visitMethod4.visitInsn(176);
        visitMethod4.visitMaxs(1, 2);
        visitMethod4.visitEnd();
        final MethodVisitor visitMethod5 = classWriter.visitMethod(129, "getDeclaredMethod", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", "(Ljava/lang/Class<*>;Ljava/lang/String;[Ljava/lang/Class<*>;)Ljava/lang/reflect/Method;", new String[] { "java/lang/NoSuchMethodException", "java/lang/SecurityException" });
        visitMethod5.visitCode();
        visitMethod5.visitVarInsn(25, 1);
        visitMethod5.visitVarInsn(25, 2);
        visitMethod5.visitVarInsn(25, 3);
        visitMethod5.visitMethodInsn(182, "java/lang/Class", "getDeclaredMethod", "(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", false);
        visitMethod5.visitInsn(176);
        visitMethod5.visitMaxs(3, 4);
        visitMethod5.visitEnd();
        final MethodVisitor visitMethod6 = classWriter.visitMethod(1, "getDeclaredField", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;", "(Ljava/lang/Class<*>;Ljava/lang/String;)Ljava/lang/reflect/Field;", new String[] { "java/lang/NoSuchFieldException", "java/lang/SecurityException" });
        visitMethod6.visitCode();
        visitMethod6.visitVarInsn(25, 1);
        visitMethod6.visitVarInsn(25, 2);
        visitMethod6.visitMethodInsn(182, "java/lang/Class", "getDeclaredField", "(Ljava/lang/String;)Ljava/lang/reflect/Field;", false);
        visitMethod6.visitInsn(176);
        visitMethod6.visitMaxs(2, 3);
        visitMethod6.visitEnd();
        final MethodVisitor visitMethod7 = classWriter.visitMethod(1, "getClassByName", "(Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class;", "(Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class<*>;", new String[] { "java/lang/ClassNotFoundException" });
        visitMethod7.visitCode();
        visitMethod7.visitVarInsn(25, 1);
        visitMethod7.visitVarInsn(21, 2);
        visitMethod7.visitVarInsn(25, 3);
        visitMethod7.visitMethodInsn(184, "java/lang/Class", "forName", "(Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class;", false);
        visitMethod7.visitInsn(176);
        visitMethod7.visitMaxs(3, 4);
        visitMethod7.visitEnd();
        classWriter.visitEnd();
        final Class<?> classFromBytecode = GeneratorClassLoader.get(MPLType.class.getClassLoader()).createClassFromBytecode(MPLType.class.getName() + "$HelperImpl", classWriter.toByteArray(), null, false);
        try {
            return (MPLTypeHelper)classFromBytecode.newInstance();
        }
        catch (final Throwable t) {
            throw MountiplexUtil.uncheckedRethrow(t);
        }
    }
    
    static {
        REMAPPING_DISABLED_HELPER = generateNoRemappingHelper();
        REMAPPING_ENABLED_HELPER = new DefaultMPLTypeHelper();
        MPLType.helper = MPLType.REMAPPING_DISABLED_HELPER;
    }
    
    private static final class DefaultMPLTypeHelper implements MPLTypeHelper
    {
        @Override
        public String getClassName(final Class<?> clazz) {
            return clazz.getName();
        }
        
        @Override
        public String getMethodName(final Method method) {
            return method.getName();
        }
        
        @Override
        public String getFieldName(final Field field) {
            return field.getName();
        }
        
        @Override
        public Method getDeclaredMethod(final Class<?> clazz, final String name, final Class<?>... parameterTypes) {
            return clazz.getDeclaredMethod(name, (Class[])parameterTypes);
        }
        
        @Override
        public Field getDeclaredField(final Class<?> clazz, final String name) {
            return clazz.getDeclaredField(name);
        }
        
        @Override
        public Class<?> getClassByName(final String name, final boolean initialize, final ClassLoader loader) {
            return Class.forName(name, initialize, loader);
        }
    }
    
    public static final class LoaderClosedException extends ClassNotFoundException
    {
        private static final long serialVersionUID = -2465209759941212720L;
        
        public LoaderClosedException() {
            super("This ClassLoader is closed");
        }
    }
}
