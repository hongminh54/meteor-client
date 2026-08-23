// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util;

import java.util.HashMap;
import java.util.Set;
import java.util.Map;

public class BoxedType
{
    private static final Map<String, Class<?>> unboxedByName;
    private static final Map<Class<?>, Class<?>> unboxedToBoxed;
    private static final Map<Class<?>, Class<?>> boxedToUnboxed;
    private static final Map<Class<?>, Object> unboxedDefaults;
    
    private static final <T> void register(final Class<?> clazz, final Class<T> clazz2, final T t) {
        BoxedType.unboxedToBoxed.put(clazz, clazz2);
        BoxedType.boxedToUnboxed.put(clazz2, clazz);
        BoxedType.unboxedByName.put(clazz.getSimpleName(), clazz);
        BoxedType.unboxedDefaults.put(clazz, t);
    }
    
    public static <T> T getDefaultValue(final Class<T> clazz) {
        return (T)BoxedType.unboxedDefaults.get(clazz);
    }
    
    public static Set<Class<?>> getUnboxedTypes() {
        return BoxedType.unboxedToBoxed.keySet();
    }
    
    public static Set<Class<?>> getBoxedTypes() {
        return BoxedType.boxedToUnboxed.keySet();
    }
    
    public static Class<?> getUnboxedType(final String s) {
        return BoxedType.unboxedByName.get(s);
    }
    
    public static Class<?> getUnboxedType(final Class<?> clazz) {
        return BoxedType.boxedToUnboxed.get(clazz);
    }
    
    public static Class<?> getBoxedType(final Class<?> clazz) {
        return BoxedType.unboxedToBoxed.get(clazz);
    }
    
    public static Class<?> tryBoxType(final Class<?> clazz) {
        final Class clazz2 = BoxedType.unboxedToBoxed.get(clazz);
        return (clazz2 == null) ? clazz : clazz2;
    }
    
    static {
        unboxedByName = new HashMap<String, Class<?>>();
        unboxedToBoxed = new HashMap<Class<?>, Class<?>>();
        boxedToUnboxed = new HashMap<Class<?>, Class<?>>();
        unboxedDefaults = new HashMap<Class<?>, Object>();
        register(Boolean.TYPE, Boolean.class, false);
        register(Character.TYPE, Character.class, '\0');
        register(Byte.TYPE, Byte.class, (Byte)0);
        register(Short.TYPE, Short.class, (Short)0);
        register(Integer.TYPE, Integer.class, 0);
        register(Long.TYPE, Long.class, 0L);
        register(Float.TYPE, Float.class, 0.0f);
        register(Double.TYPE, Double.class, 0.0);
        register(Void.TYPE, Void.class, null);
    }
}
