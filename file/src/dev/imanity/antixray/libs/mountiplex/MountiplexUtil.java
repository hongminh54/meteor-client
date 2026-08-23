// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex;

import java.util.stream.StreamSupport;
import java.util.Spliterators;
import java.util.function.UnaryOperator;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.stream.Stream;
import java.util.Collection;
import java.lang.reflect.Array;
import dev.imanity.antixray.libs.mountiplex.reflection.util.ArrayHelper;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.logging.Logger;

public class MountiplexUtil
{
    public static Logger LOGGER;
    private static ArrayList<Runnable> unloaders;
    
    public static void unloadMountiplex() {
        synchronized (MountiplexUtil.unloaders) {
            final Iterator<Runnable> iterator = MountiplexUtil.unloaders.iterator();
            while (iterator.hasNext()) {
                iterator.next().run();
            }
            MountiplexUtil.unloaders.clear();
            MountiplexUtil.unloaders.trimToSize();
        }
    }
    
    public static void registerUnloader(final Runnable e) {
        synchronized (MountiplexUtil.unloaders) {
            MountiplexUtil.unloaders.add(e);
        }
    }
    
    public static boolean containsChar(final char c, final char... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (array[i] == c) {
                return true;
            }
        }
        return false;
    }
    
    public static Class<?>[] getTypes(final Object[] array) {
        final Class[] array2 = new Class[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = ((array[i] == null) ? null : array[i].getClass());
        }
        return array2;
    }
    
    public static Class<?> getArrayType(final Class<?> clazz) {
        return getArrayType(clazz, 1);
    }
    
    public static Class<?> getArrayType(final Class<?> clazz, final int n) {
        return ArrayHelper.getArrayType(clazz, n);
    }
    
    public static <T> T[] createArray(final Class<T> componentType, final int length) {
        return (T[])Array.newInstance(componentType, length);
    }
    
    public static boolean retainAll(final Collection<?> collection, final Collection<?> collection2) {
        final Iterator<?> iterator = collection.iterator();
        boolean b = false;
        while (iterator.hasNext()) {
            if (!collection2.contains(iterator.next())) {
                iterator.remove();
                b = true;
            }
        }
        return b;
    }
    
    public static <T> Stream<T> toStream(final T o) {
        return Collections.singleton(o).stream();
    }
    
    public static Object[] toArray(final Collection<?> collection) {
        final Object[] array = new Object[collection.size()];
        final Iterator iterator = collection.iterator();
        for (int i = 0; i < array.length; ++i) {
            array[i] = iterator.next();
        }
        return array;
    }
    
    public static <T> T[] toArray(final Collection<?> collection, T[] array) {
        final int size = collection.size();
        if (array.length < size) {
            array = createArray(array.getClass().getComponentType(), size);
        }
        final Iterator iterator = collection.iterator();
        for (int i = 0; i < array.length; ++i) {
            if (!iterator.hasNext()) {
                array[i] = null;
                break;
            }
            array[i] = (T)iterator.next();
        }
        return array;
    }
    
    public static String getLastBefore(final String s, final String str) {
        final int lastIndex = s.lastIndexOf(str);
        return (lastIndex >= 0) ? s.substring(0, lastIndex) : "";
    }
    
    public static <T> T[] toArray(final Collection<?> collection, final Class<T> clazz) {
        return collection.toArray((T[])createArray((Class<T>)clazz, collection.size()));
    }
    
    public static <T> T[] getClassConstants(final Class<T> clazz) {
        return getClassConstants(clazz, clazz);
    }
    
    public static <T> T[] getClassConstants(final Class<?> obj, final Class<T> clazz) {
        if (clazz.isEnum()) {
            return (T[])clazz.getEnumConstants();
        }
        try {
            final Field[] declaredFields = obj.getDeclaredFields();
            final ArrayList list = new ArrayList<Object>(declaredFields.length);
            for (final Field field : declaredFields) {
                if (Modifier.isStatic(field.getModifiers()) && clazz.isAssignableFrom(field.getType())) {
                    final Object value = field.get(null);
                    if (value != null) {
                        list.add(value);
                    }
                }
            }
            return toArray(list, clazz);
        }
        catch (final Throwable thrown) {
            MountiplexUtil.LOGGER.log(Level.WARNING, "Failed to find class constants of " + obj, thrown);
            return createArray(clazz, 0);
        }
    }
    
    public static <T> T parseArray(final T[] array, String replace, final T t) {
        if (replace == null || replace.isEmpty()) {
            return t;
        }
        replace = replace.toUpperCase(Locale.ENGLISH).replace("_", "").replace(" ", "");
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array2.length; ++i) {
            if (array[i] instanceof Enum) {
                array2[i] = ((Enum)array[i]).name();
            }
            else {
                array2[i] = array[i].toString();
            }
            array2[i] = array2[i].toUpperCase(Locale.ENGLISH).replace("_", "");
            if (array2[i].equals(replace)) {
                return array[i];
            }
        }
        for (int j = 0; j < array2.length; ++j) {
            if (array2[j].contains(replace)) {
                return array[j];
            }
        }
        for (int k = 0; k < array2.length; ++k) {
            if (replace.contains(array2[k])) {
                return array[k];
            }
        }
        return t;
    }
    
    public static <T> T parseEnum(final String s, final T t) {
        return parseEnum(t.getClass(), s, t);
    }
    
    public static <T> T parseEnum(final Class<T> clazz, final String s, final T t) {
        if (!clazz.isEnum()) {
            throw new IllegalArgumentException("Class '" + clazz.getSimpleName() + "' is not an Enumeration!");
        }
        return parseArray(clazz.getEnumConstants(), s, t);
    }
    
    public static RuntimeException uncheckedRethrow(Throwable cause) {
        if (cause instanceof InvocationTargetException) {
            cause = cause.getCause();
        }
        if (cause instanceof Error) {
            throw (Error)cause;
        }
        if (cause instanceof RuntimeException) {
            return (RuntimeException)cause;
        }
        final RuntimeException ex = new RuntimeException("An exception occurred", cause);
        ex.setStackTrace(new StackTraceElement[0]);
        return ex;
    }
    
    public static double similarity(final String s, final String s2) {
        String s3 = s;
        String s4 = s2;
        if (s.length() < s2.length()) {
            s3 = s2;
            s4 = s;
        }
        final int length = s3.length();
        if (length == 0) {
            return 1.0;
        }
        return (length - editDistance(s3, s4)) / (double)length;
    }
    
    public static int editDistance(String lowerCase, String lowerCase2) {
        lowerCase = lowerCase.toLowerCase(Locale.ENGLISH);
        lowerCase2 = lowerCase2.toLowerCase(Locale.ENGLISH);
        final int[] array = new int[lowerCase2.length() + 1];
        for (int i = 0; i <= lowerCase.length(); ++i) {
            int b = i;
            for (int j = 0; j <= lowerCase2.length(); ++j) {
                if (i == 0) {
                    array[j] = j;
                }
                else if (j > 0) {
                    int a = array[j - 1];
                    if (lowerCase.charAt(i - 1) != lowerCase2.charAt(j - 1)) {
                        a = Math.min(Math.min(a, b), array[j]) + 1;
                    }
                    array[j - 1] = b;
                    b = a;
                }
            }
            if (i > 0) {
                array[lowerCase2.length()] = b;
            }
        }
        return array[lowerCase2.length()];
    }
    
    public static <T> List<T> createUmodifiableList(final Collection<T> collection) {
        if (collection.isEmpty()) {
            return Collections.emptyList();
        }
        final Object[] a = new Object[collection.size()];
        int n = 0;
        final Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            a[n++] = iterator.next();
        }
        return Arrays.asList((T[])a);
    }
    
    public static String getPackagePathFromClassPath(final String s) {
        int endIndex = -100;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '.') {
                endIndex = i;
            }
            else if (i == endIndex + 1) {
                if (char1 == '$') {
                    break;
                }
                if (Character.isUpperCase(char1)) {
                    break;
                }
            }
        }
        return (endIndex == -100) ? "" : s.substring(0, endIndex);
    }
    
    public static <T> Stream<T> iterateNullTerminated(final T t, final UnaryOperator<T> unaryOperator) {
        if (t == null) {
            return Stream.empty();
        }
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize((Iterator<? extends T>)new Iterator<T>() {
            T current = t;
            
            @Override
            public boolean hasNext() {
                return this.current != null;
            }
            
            @Override
            public T next() {
                final T current = this.current;
                this.current = (T)unaryOperator.apply(this.current);
                return current;
            }
        }, 1040), false);
    }
    
    static {
        MountiplexUtil.LOGGER = Logger.getLogger("REFLECTION");
        MountiplexUtil.unloaders = new ArrayList<Runnable>();
    }
}
