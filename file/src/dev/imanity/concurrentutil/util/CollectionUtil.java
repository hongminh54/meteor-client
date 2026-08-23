// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.concurrentutil.util;

import java.util.Iterator;
import java.util.Collection;

public final class CollectionUtil
{
    public static String toString(final Collection<?> collection, final String s) {
        return toString(collection, s, new StringBuilder(s.length() + 128)).toString();
    }
    
    public static StringBuilder toString(final Collection<?> collection, final String str, final StringBuilder sb) {
        sb.append(str).append("{elements={");
        int n = 1;
        for (final Object next : collection) {
            if (n == 0) {
                sb.append(", ");
            }
            n = 0;
            sb.append('\"').append(next).append('\"');
        }
        return sb.append("}}");
    }
    
    private CollectionUtil() {
        throw new RuntimeException();
    }
}
