// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.resolver;

import java.lang.reflect.Method;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.lang.reflect.Field;
import java.util.Map;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ClassDeclaration;

public final class NoOpResolver implements ClassDeclarationResolver, ClassPathResolver, CompiledFieldNameResolver, CompiledMethodNameResolver, FieldAliasResolver, FieldNameResolver, MethodAliasResolver, MethodNameResolver
{
    public static final NoOpResolver INSTANCE;
    
    private NoOpResolver() {
    }
    
    @Override
    public String resolveCompiledMethodName(final Class<?> clazz, final String s, final Class<?>[] array) {
        return s;
    }
    
    @Override
    public String resolveCompiledFieldName(final Class<?> clazz, final String s) {
        return s;
    }
    
    @Override
    public String resolveMethodName(final Class<?> clazz, final String s, final Class<?>[] array) {
        return s;
    }
    
    @Override
    public String resolveFieldName(final Class<?> clazz, final String s) {
        return s;
    }
    
    @Override
    public String resolveClassPath(final String s) {
        return s;
    }
    
    @Override
    public ClassDeclaration resolveClassDeclaration(final String s, final Class<?> clazz) {
        return null;
    }
    
    @Override
    public void resolveClassVariables(final String s, final Class<?> clazz, final Map<String, String> map) {
    }
    
    @Override
    public String resolveFieldAlias(final Field field, final String str) {
        try {
            return field.getName();
        }
        catch (final Throwable thrown) {
            MountiplexUtil.LOGGER.log(Level.WARNING, "Failed to retrieve field name alias for " + MPLType.getName(field.getDeclaringClass()) + ":" + str + ":", thrown);
            return null;
        }
    }
    
    @Override
    public String resolveMethodAlias(final Method method, final String str) {
        try {
            return method.getName();
        }
        catch (final Throwable thrown) {
            MountiplexUtil.LOGGER.log(Level.WARNING, "Failed to retrieve method name alias for " + MPLType.getName(method.getDeclaringClass()) + ":" + str + ":", thrown);
            return null;
        }
    }
    
    static {
        INSTANCE = new NoOpResolver();
    }
}
