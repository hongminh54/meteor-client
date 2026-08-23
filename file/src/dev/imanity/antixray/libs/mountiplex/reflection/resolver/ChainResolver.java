// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.resolver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ClassDeclaration;

public class ChainResolver
{
    public static ClassDeclarationResolver chain(final ClassDeclarationResolver classDeclarationResolver, final ClassDeclarationResolver classDeclarationResolver2) {
        return (classDeclarationResolver == NoOpResolver.INSTANCE) ? classDeclarationResolver2 : new ClassDeclarationResolver() {
            @Override
            public ClassDeclaration resolveClassDeclaration(final String s, final Class<?> clazz) {
                ClassDeclaration classDeclaration = classDeclarationResolver.resolveClassDeclaration(s, clazz);
                if (classDeclaration == null) {
                    classDeclaration = classDeclarationResolver2.resolveClassDeclaration(s, clazz);
                }
                return classDeclaration;
            }
            
            @Override
            public void resolveClassVariables(final String s, final Class<?> clazz, final Map<String, String> map) {
                classDeclarationResolver.resolveClassVariables(s, clazz, map);
                classDeclarationResolver2.resolveClassVariables(s, clazz, map);
            }
        };
    }
    
    public static ClassPathResolver chain(final ClassPathResolver classPathResolver, final ClassPathResolver classPathResolver2) {
        return (classPathResolver == NoOpResolver.INSTANCE) ? classPathResolver2 : new ClassPathResolver() {
            @Override
            public String resolveClassPath(String s) {
                s = classPathResolver.resolveClassPath(s);
                s = classPathResolver2.resolveClassPath(s);
                return s;
            }
            
            @Override
            public boolean canLoadClassPath(final String s) {
                return classPathResolver.canLoadClassPath(s) && classPathResolver2.canLoadClassPath(s);
            }
        };
    }
    
    public static CompiledFieldNameResolver chain(final CompiledFieldNameResolver compiledFieldNameResolver, final CompiledFieldNameResolver compiledFieldNameResolver2) {
        return (compiledFieldNameResolver == NoOpResolver.INSTANCE) ? compiledFieldNameResolver2 : ((clazz2, s2) -> {
            s2 = compiledFieldNameResolver3.resolveCompiledFieldName(clazz2, s2);
            s2 = compiledFieldNameResolver4.resolveCompiledFieldName(clazz2, s2);
            return s2;
        });
    }
    
    public static CompiledMethodNameResolver chain(final CompiledMethodNameResolver compiledMethodNameResolver, final CompiledMethodNameResolver compiledMethodNameResolver2) {
        return (compiledMethodNameResolver == NoOpResolver.INSTANCE) ? compiledMethodNameResolver2 : ((clazz2, s2, array2) -> {
            s2 = compiledMethodNameResolver3.resolveCompiledMethodName(clazz2, s2, array2);
            s2 = compiledMethodNameResolver4.resolveCompiledMethodName(clazz2, s2, array2);
            return s2;
        });
    }
    
    public static FieldNameResolver chain(final FieldNameResolver fieldNameResolver, final FieldNameResolver fieldNameResolver2) {
        return (fieldNameResolver == NoOpResolver.INSTANCE) ? fieldNameResolver2 : ((clazz2, s2) -> {
            s2 = fieldNameResolver3.resolveFieldName(clazz2, s2);
            s2 = fieldNameResolver4.resolveFieldName(clazz2, s2);
            return s2;
        });
    }
    
    public static FieldAliasResolver chain(final FieldAliasResolver fieldAliasResolver, final FieldAliasResolver fieldAliasResolver2) {
        return (field2, s2) -> {
            fieldAliasResolver3.resolveFieldAlias(field2, s2);
            final String s3;
            return (s3 != null) ? s3 : fieldAliasResolver4.resolveFieldAlias(field2, s2);
        };
    }
    
    public static MethodNameResolver chain(final MethodNameResolver methodNameResolver, final MethodNameResolver methodNameResolver2) {
        return (methodNameResolver == NoOpResolver.INSTANCE) ? methodNameResolver2 : ((clazz2, s2, array2) -> {
            s2 = methodNameResolver3.resolveMethodName(clazz2, s2, array2);
            s2 = methodNameResolver4.resolveMethodName(clazz2, s2, array2);
            return s2;
        });
    }
    
    public static MethodAliasResolver chain(final MethodAliasResolver methodAliasResolver, final MethodAliasResolver methodAliasResolver2) {
        return (method2, s2) -> {
            methodAliasResolver3.resolveMethodAlias(method2, s2);
            final String s3;
            return (s3 != null) ? s3 : methodAliasResolver4.resolveMethodAlias(method2, s2);
        };
    }
}
