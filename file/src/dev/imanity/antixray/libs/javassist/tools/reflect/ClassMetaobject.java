// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.tools.reflect;

import java.util.Arrays;
import java.lang.reflect.InvocationTargetException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.io.Serializable;

public class ClassMetaobject implements Serializable
{
    private static final long serialVersionUID = 1L;
    static final String methodPrefix = "_m_";
    static final int methodPrefixLen = 3;
    private Class<?> javaClass;
    private Constructor<?>[] constructors;
    private Method[] methods;
    public static boolean useContextClassLoader;
    
    public ClassMetaobject(final String[] array) {
        try {
            this.javaClass = this.getClassObject(array[0]);
        }
        catch (final ClassNotFoundException cause) {
            throw new RuntimeException("not found: " + array[0] + ", useContextClassLoader: " + Boolean.toString(ClassMetaobject.useContextClassLoader), cause);
        }
        this.constructors = this.javaClass.getConstructors();
        this.methods = null;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeUTF(this.javaClass.getName());
    }
    
    private void readObject(final ObjectInputStream objectInputStream) {
        this.javaClass = this.getClassObject(objectInputStream.readUTF());
        this.constructors = this.javaClass.getConstructors();
        this.methods = null;
    }
    
    private Class<?> getClassObject(final String s) {
        if (ClassMetaobject.useContextClassLoader) {
            return Thread.currentThread().getContextClassLoader().loadClass(s);
        }
        return Class.forName(s);
    }
    
    public final Class<?> getJavaClass() {
        return this.javaClass;
    }
    
    public final String getName() {
        return this.javaClass.getName();
    }
    
    public final boolean isInstance(final Object o) {
        return this.javaClass.isInstance(o);
    }
    
    public final Object newInstance(final Object[] initargs) {
        for (int length = this.constructors.length, i = 0; i < length; ++i) {
            try {
                return this.constructors[i].newInstance(initargs);
            }
            catch (final IllegalArgumentException ex) {}
            catch (final InstantiationException ex2) {
                throw new CannotCreateException(ex2);
            }
            catch (final IllegalAccessException ex3) {
                throw new CannotCreateException(ex3);
            }
            catch (final InvocationTargetException ex4) {
                throw new CannotCreateException(ex4);
            }
        }
        throw new CannotCreateException("no constructor matches");
    }
    
    public Object trapFieldRead(final String name) {
        final Class<?> javaClass = this.getJavaClass();
        try {
            return javaClass.getField(name).get(null);
        }
        catch (final NoSuchFieldException ex) {
            throw new RuntimeException(ex.toString());
        }
        catch (final IllegalAccessException ex2) {
            throw new RuntimeException(ex2.toString());
        }
    }
    
    public void trapFieldWrite(final String name, final Object value) {
        final Class<?> javaClass = this.getJavaClass();
        try {
            javaClass.getField(name).set(null, value);
        }
        catch (final NoSuchFieldException ex) {
            throw new RuntimeException(ex.toString());
        }
        catch (final IllegalAccessException ex2) {
            throw new RuntimeException(ex2.toString());
        }
    }
    
    public static Object invoke(final Object obj, final int i, final Object[] args) {
        final Method[] methods = obj.getClass().getMethods();
        final int length = methods.length;
        final String string = "_m_" + i;
        for (int j = 0; j < length; ++j) {
            if (methods[j].getName().startsWith(string)) {
                try {
                    return methods[j].invoke(obj, args);
                }
                catch (final InvocationTargetException ex) {
                    throw ex.getTargetException();
                }
                catch (final IllegalAccessException ex2) {
                    throw new CannotInvokeException(ex2);
                }
            }
        }
        throw new CannotInvokeException("cannot find a method");
    }
    
    public Object trapMethodcall(final int n, final Object[] args) {
        try {
            return this.getReflectiveMethods()[n].invoke(null, args);
        }
        catch (final InvocationTargetException ex) {
            throw ex.getTargetException();
        }
        catch (final IllegalAccessException ex2) {
            throw new CannotInvokeException(ex2);
        }
    }
    
    public final Method[] getReflectiveMethods() {
        if (this.methods != null) {
            return this.methods;
        }
        final Method[] declaredMethods = this.getJavaClass().getDeclaredMethods();
        final int length = declaredMethods.length;
        final int[] array = new int[length];
        int n = 0;
        for (int i = 0; i < length; ++i) {
            final String name = declaredMethods[i].getName();
            if (name.startsWith("_m_")) {
                int n2 = 0;
                int index = 3;
                while (true) {
                    final char char1 = name.charAt(index);
                    if ('0' > char1 || char1 > '9') {
                        break;
                    }
                    n2 = n2 * 10 + char1 - 48;
                    ++index;
                }
                array[i] = ++n2;
                if (n2 > n) {
                    n = n2;
                }
            }
        }
        this.methods = new Method[n];
        for (int j = 0; j < length; ++j) {
            if (array[j] > 0) {
                this.methods[array[j] - 1] = declaredMethods[j];
            }
        }
        return this.methods;
    }
    
    public final Method getMethod(final int n) {
        return this.getReflectiveMethods()[n];
    }
    
    public final String getMethodName(final int n) {
        final String name = this.getReflectiveMethods()[n].getName();
        int beginIndex = 3;
        char char1;
        do {
            char1 = name.charAt(beginIndex++);
        } while (char1 >= '0' && '9' >= char1);
        return name.substring(beginIndex);
    }
    
    public final Class<?>[] getParameterTypes(final int n) {
        return this.getReflectiveMethods()[n].getParameterTypes();
    }
    
    public final Class<?> getReturnType(final int n) {
        return this.getReflectiveMethods()[n].getReturnType();
    }
    
    public final int getMethodIndex(final String s, final Class<?>[] a) {
        final Method[] reflectiveMethods = this.getReflectiveMethods();
        for (int i = 0; i < reflectiveMethods.length; ++i) {
            if (reflectiveMethods[i] != null) {
                if (this.getMethodName(i).equals(s) && Arrays.equals(a, reflectiveMethods[i].getParameterTypes())) {
                    return i;
                }
            }
        }
        throw new NoSuchMethodException("Method " + s + " not found");
    }
    
    static {
        ClassMetaobject.useContextClassLoader = false;
    }
}
