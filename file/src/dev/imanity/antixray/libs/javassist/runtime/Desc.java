// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.runtime;

import java.io.Serializable;

public class Desc
{
    public static boolean useContextClassLoader;
    private static final ThreadLocal<Boolean> USE_CONTEXT_CLASS_LOADER_LOCALLY;
    
    public static void setUseContextClassLoaderLocally() {
        Desc.USE_CONTEXT_CLASS_LOADER_LOCALLY.set(true);
    }
    
    public static void resetUseContextClassLoaderLocally() {
        Desc.USE_CONTEXT_CLASS_LOADER_LOCALLY.remove();
    }
    
    private static Class<?> getClassObject(final String s) {
        if (Desc.useContextClassLoader || Desc.USE_CONTEXT_CLASS_LOADER_LOCALLY.get()) {
            return Class.forName(s, true, Thread.currentThread().getContextClassLoader());
        }
        return Class.forName(s);
    }
    
    public static Class<?> getClazz(final String str) {
        try {
            return getClassObject(str);
        }
        catch (final ClassNotFoundException cause) {
            throw new RuntimeException("$class: internal error, could not find class '" + str + "' (Desc.useContextClassLoader: " + Boolean.toString(Desc.useContextClassLoader) + ")", cause);
        }
    }
    
    public static Class<?>[] getParams(final String s) {
        if (s.charAt(0) != '(') {
            throw new RuntimeException("$sig: internal error");
        }
        return getType(s, s.length(), 1, 0);
    }
    
    public static Class<?> getType(final String s) {
        final Class<?>[] type = getType(s, s.length(), 0, 0);
        if (type == null || type.length != 1) {
            throw new RuntimeException("$type: internal error");
        }
        return type[0];
    }
    
    private static Class<?>[] getType(final String s, final int n, final int index, final int n2) {
        if (index >= n) {
            return new Class[n2];
        }
        Serializable s2 = null;
        switch (s.charAt(index)) {
            case 'Z': {
                s2 = Boolean.TYPE;
                break;
            }
            case 'C': {
                s2 = Character.TYPE;
                break;
            }
            case 'B': {
                s2 = Byte.TYPE;
                break;
            }
            case 'S': {
                s2 = Short.TYPE;
                break;
            }
            case 'I': {
                s2 = Integer.TYPE;
                break;
            }
            case 'J': {
                s2 = Long.TYPE;
                break;
            }
            case 'F': {
                s2 = Float.TYPE;
                break;
            }
            case 'D': {
                s2 = Double.TYPE;
                break;
            }
            case 'V': {
                s2 = Void.TYPE;
                break;
            }
            case 'L':
            case '[': {
                return getClassType(s, n, index, n2);
            }
            default: {
                return new Class[n2];
            }
        }
        final Class<?>[] type = getType(s, n, index + 1, n2 + 1);
        type[n2] = (Class<?>)s2;
        return type;
    }
    
    private static Class<?>[] getClassType(final String s, final int n, final int n2, final int n3) {
        int index;
        for (index = n2; s.charAt(index) == '['; ++index) {}
        if (s.charAt(index) == 'L') {
            index = s.indexOf(59, index);
            if (index < 0) {
                throw new IndexOutOfBoundsException("bad descriptor");
            }
        }
        String s2;
        if (s.charAt(n2) == 'L') {
            s2 = s.substring(n2 + 1, index);
        }
        else {
            s2 = s.substring(n2, index + 1);
        }
        final Class<?>[] type = getType(s, n, index + 1, n3 + 1);
        try {
            type[n3] = getClassObject(s2.replace('/', '.'));
        }
        catch (final ClassNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return type;
    }
    
    static {
        Desc.useContextClassLoader = false;
        USE_CONTEXT_CLASS_LOADER_LOCALLY = new ThreadLocal<Boolean>() {
            @Override
            protected Boolean initialValue() {
                return false;
            }
        };
    }
}
