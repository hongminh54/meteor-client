// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.resolver;

public interface ClassPathResolver
{
    String resolveClassPath(final String p0);
    
    default boolean canLoadClassPath(final String s) {
        return true;
    }
}
