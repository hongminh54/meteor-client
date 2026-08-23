// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;

public class ArrayHelper
{
    private static int[][] dimensions_cache;
    
    public static Class<?> getArrayType(Class<?> componentType, int n) {
        if (n == 0) {
            return componentType;
        }
        while (componentType.isArray()) {
            componentType = componentType.getComponentType();
            ++n;
        }
        if (n == 1 && !componentType.isPrimitive()) {
            try {
                return MPLType.getClassByName("[L" + MPLType.getName(componentType) + ";", false, componentType.getClassLoader());
            }
            catch (final ClassNotFoundException ex) {}
        }
        final int[][] dimensions_cache = ArrayHelper.dimensions_cache;
        final int length = dimensions_cache.length;
        int[] dimensions;
        if (n >= length) {
            final int[][] dimensions_cache2 = Arrays.copyOf(dimensions_cache, n + 1);
            for (int i = length; i <= n; ++i) {
                dimensions_cache2[i] = new int[i];
            }
            ArrayHelper.dimensions_cache = dimensions_cache2;
            dimensions = dimensions_cache2[n];
        }
        else {
            dimensions = dimensions_cache[n];
        }
        return Array.newInstance(componentType, dimensions).getClass();
    }
    
    static {
        ArrayHelper.dimensions_cache = new int[][] { { 0 } };
    }
}
