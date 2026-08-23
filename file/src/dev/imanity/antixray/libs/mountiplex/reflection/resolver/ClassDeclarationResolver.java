// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.resolver;

import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ClassResolver;
import java.util.Map;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ClassDeclaration;

public interface ClassDeclarationResolver
{
    ClassDeclaration resolveClassDeclaration(final String p0, final Class<?> p1);
    
    void resolveClassVariables(final String p0, final Class<?> p1, final Map<String, String> p2);
    
    default ClassResolver getRootClassResolver(final String s, final Class<?> clazz) {
        return ClassResolver.DEFAULT;
    }
}
