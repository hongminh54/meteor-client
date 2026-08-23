// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.asm.javassist;

import dev.imanity.antixray.libs.javassist.compiler.NoFieldException;
import dev.imanity.antixray.libs.mountiplex.reflection.SafeField;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.GeneratedCodeInvoker;
import dev.imanity.antixray.libs.mountiplex.reflection.util.signature.MethodSignature;
import dev.imanity.antixray.libs.mountiplex.reflection.util.ArrayHelper;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Remapping;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import dev.imanity.antixray.libs.mountiplex.reflection.util.signature.FieldSignature;
import dev.imanity.antixray.libs.mountiplex.reflection.util.IgnoresRemapping;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import java.util.Map;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.javassist.compiler.ast.ASTree;
import dev.imanity.antixray.libs.javassist.NotFoundException;
import dev.imanity.antixray.libs.javassist.compiler.CompileError;
import dev.imanity.antixray.libs.javassist.compiler.ast.Member;
import dev.imanity.antixray.libs.javassist.CtField;
import dev.imanity.antixray.libs.javassist.compiler.ast.Symbol;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;
import dev.imanity.antixray.libs.javassist.CtClass;
import dev.imanity.antixray.libs.javassist.ClassPool;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.ResolvedClassPool;
import dev.imanity.antixray.libs.javassist.compiler.MemberResolver;

public final class MPLMemberResolver extends MemberResolver
{
    public static final String IGNORE_PREFIX = "MPL_NOREMAP$";
    private static final String GENERATED_CODE_INVOKER_NAME;
    private final ResolvedClassPool resolvedClassPool;
    private FailedLocalFieldLookup lastFailedLocalFieldLookup;
    private static final String INVALID;
    private static final java.lang.reflect.Method getInvalidNamesMethod;
    
    public MPLMemberResolver(final ResolvedClassPool resolvedClassPool) {
        super(resolvedClassPool);
        this.lastFailedLocalFieldLookup = null;
        this.resolvedClassPool = resolvedClassPool;
    }
    
    @Override
    public ResolvedClassPool getClassPool() {
        return this.resolvedClassPool;
    }
    
    @Override
    public Method lookupMethod(final CtClass ctClass, final CtClass ctClass2, final MethodInfo methodInfo, final String s, final int[] array, final int[] array2, final String[] array3) {
        final String preprocessMethodName = this.preprocessMethodName(ctClass, s, array, array2, array3);
        try (final ResolvedClassPool.IgnoreToken ignoreResolver = this.resolvedClassPool.ignoreResolver()) {
            return super.lookupMethod(ctClass, ctClass2, methodInfo, preprocessMethodName, array, array2, array3);
        }
    }
    
    @Override
    public CtField lookupField(final String s, final Symbol symbol) {
        CtClass lookupClass;
        try (final ResolvedClassPool.IgnoreToken ignoreResolver = this.resolvedClassPool.ignoreResolver()) {
            lookupClass = this.lookupClass(s, false);
        }
        String s2 = symbol.get();
        if (symbol instanceof Member) {
            s2 = this.preprocessFieldName(lookupClass, s2);
        }
        try (final ResolvedClassPool.IgnoreToken ignoreResolver2 = this.resolvedClassPool.ignoreResolver()) {
            return lookupClass.getField(s2);
        }
        catch (final NotFoundException ex) {
            this.lastFailedLocalFieldLookup = new FailedLocalFieldLookup(lookupClass.getName(), symbol.get());
            throw new CompileError("no such field: " + lookupClass.getName() + " -> " + symbol.get());
        }
    }
    
    @Override
    public CtField lookupFieldByJvmName2(String javaToJvmName, final Symbol symbol, final ASTree asTree) {
        String str = symbol.get();
        final String jvmToJavaName = MemberResolver.jvmToJavaName(javaToJvmName);
        CtClass lookupClass;
        try {
            lookupClass = this.lookupClass(jvmToJavaName, true);
        }
        catch (final CompileError compileError) {
            if (str.startsWith("MPL_NOREMAP$")) {
                str = str.substring("MPL_NOREMAP$".length());
            }
            if (this.lastFailedLocalFieldLookup != null && this.lastFailedLocalFieldLookup.fieldName.equals(str)) {
                MountiplexUtil.LOGGER.severe("Local field lookup also failed: " + this.lastFailedLocalFieldLookup.className + " -> " + this.lastFailedLocalFieldLookup.fieldName);
                this.lastFailedLocalFieldLookup = null;
            }
            throw new NoFieldException(javaToJvmName + "/" + str, "??" + jvmToJavaName + "??." + str, asTree);
        }
        final String preprocessFieldName = this.preprocessFieldName(lookupClass, str);
        try {
            return lookupClass.getField(preprocessFieldName);
        }
        catch (final NotFoundException ex) {
            javaToJvmName = MemberResolver.javaToJvmName(lookupClass.getName());
            throw new NoFieldException(javaToJvmName + "$" + preprocessFieldName, lookupClass.getName() + "." + preprocessFieldName, asTree);
        }
    }
    
    @Override
    public CtClass lookupClass(final String str, final boolean b) {
        Map map;
        try {
            map = (Map)MPLMemberResolver.getInvalidNamesMethod.invoke(this, new Object[0]);
        }
        catch (final Throwable t) {
            throw MountiplexUtil.uncheckedRethrow(t);
        }
        final String s = map.get(str);
        if (s == MPLMemberResolver.INVALID) {
            throw new CompileError("no such class: " + str);
        }
        if (s != null) {
            try {
                return this.resolvedClassPool.getWithoutResolving(s);
            }
            catch (final NotFoundException ex) {
                map.remove(str);
            }
        }
        return super.lookupClass(str, b);
    }
    
    public CtClass lookupClassByJvmNameIgnoreResolver(final String s) {
        String s2 = MemberResolver.jvmToJavaName(s);
        if (!s2.startsWith("MPL_NOREMAP$") && !Resolver.resolveClassPath(s2).equals(s2)) {
            s2 = "MPL_NOREMAP$" + s2;
        }
        return this.lookupClass(s2, false);
    }
    
    @Override
    public CtClass lookupClassByJvmName(final String s) {
        return this.lookupClass(MemberResolver.jvmToJavaName(s), false);
    }
    
    @Override
    public CtClass lookupClass(final int n, int n2, final String s) {
        if (n == 307 && n2 > 0) {
            String s2 = this.lookupClassByJvmName(s).getName();
            while (n2-- > 0) {
                s2 += "[]";
            }
            return this.lookupClass("MPL_NOREMAP$" + s2, false);
        }
        return super.lookupClass(n, n2, s);
    }
    
    private String preprocessFieldName(final CtClass ctClass, final String s) {
        if (s.startsWith("MPL_NOREMAP$")) {
            return s.substring("MPL_NOREMAP$".length());
        }
        try {
            CtClass superclass;
            try (final ResolvedClassPool.IgnoreToken ignoreResolver = this.resolvedClassPool.ignoreResolver()) {
                superclass = ctClass.getSuperclass();
            }
            if (superclass != null && superclass.getName().equals(MPLMemberResolver.GENERATED_CODE_INVOKER_NAME)) {
                return s;
            }
        }
        catch (final NotFoundException ex) {}
        Class<?> classByExactName;
        try {
            classByExactName = Resolver.getClassByExactName(ctClass.getName());
        }
        catch (final ClassNotFoundException ex2) {
            return s;
        }
        if (IgnoresRemapping.class.isAssignableFrom(classByExactName)) {
            return s;
        }
        final Remapping.FieldRemapping find = this.getClassPool().getRemappings().find(classByExactName, new FieldSignature(s));
        if (find != null) {
            return MPLType.getName(find.field);
        }
        final String resolveFieldName = Resolver.resolveFieldName(classByExactName, s);
        return (resolveFieldName != null) ? resolveFieldName : s;
    }
    
    private String preprocessMethodName(final CtClass ctClass, final String s, final int[] array, final int[] array2, final String[] array3) {
        if (s.startsWith("MPL_NOREMAP$")) {
            return s.substring("MPL_NOREMAP$".length());
        }
        if (s.equals("<init>")) {
            return s;
        }
        if (ctClass.getName().startsWith("java.")) {
            return s;
        }
        try {
            final CtClass superclass = ctClass.getSuperclass();
            if (superclass != null && superclass.getName().equals(MPLMemberResolver.GENERATED_CODE_INVOKER_NAME)) {
                return s;
            }
        }
        catch (final NotFoundException ex) {}
        Class<?> classByExactName;
        try {
            classByExactName = Resolver.getClassByExactName(ctClass.getName());
        }
        catch (final ClassNotFoundException ex2) {
            return s;
        }
        if (IgnoresRemapping.class.isAssignableFrom(classByExactName)) {
            return s;
        }
        final Class[] array4 = new Class[array.length];
        for (int i = 0; i < array4.length; ++i) {
            array4[i] = translateType(array[i], array3[i]);
            if (array4[i] == null) {
                return s;
            }
            array4[i] = ArrayHelper.getArrayType(array4[i], array2[i]);
        }
        final Remapping.MethodRemapping find = this.getClassPool().getRemappings().find(classByExactName, new MethodSignature(s, array4));
        if (find != null) {
            return MPLType.getName(find.method);
        }
        return Resolver.resolveMethodName(classByExactName, s, array4);
    }
    
    private static Class<?> translateType(final int n, final String s) {
        switch (n) {
            case 301: {
                return Boolean.TYPE;
            }
            case 306: {
                return Character.TYPE;
            }
            case 303: {
                return Byte.TYPE;
            }
            case 334: {
                return Short.TYPE;
            }
            case 324: {
                return Integer.TYPE;
            }
            case 326: {
                return Long.TYPE;
            }
            case 317: {
                return Float.TYPE;
            }
            case 312: {
                return Double.TYPE;
            }
            case 307: {
                try {
                    return Resolver.getClassByExactName(s.replace('/', '.'));
                }
                catch (final ClassNotFoundException ex) {
                    return null;
                }
                break;
            }
        }
        return null;
    }
    
    static {
        GENERATED_CODE_INVOKER_NAME = GeneratedCodeInvoker.class.getName();
        try {
            INVALID = SafeField.get(MemberResolver.class, "INVALID", String.class);
            (getInvalidNamesMethod = MemberResolver.class.getDeclaredMethod("getInvalidNames", (Class<?>[])new Class[0])).setAccessible(true);
        }
        catch (final Throwable t) {
            throw MountiplexUtil.uncheckedRethrow(t);
        }
    }
    
    public static class NoFieldException extends dev.imanity.antixray.libs.javassist.compiler.NoFieldException
    {
        private static final long serialVersionUID = -3755584273114392309L;
        private final String replFieldName;
        
        public NoFieldException(final String replFieldName, final String s, final ASTree asTree) {
            super(s, asTree);
            this.replFieldName = replFieldName;
        }
        
        @Override
        public String getField() {
            return this.replFieldName;
        }
    }
    
    private static class FailedLocalFieldLookup
    {
        public final String className;
        public final String fieldName;
        
        public FailedLocalFieldLookup(final String className, final String fieldName) {
            this.className = className;
            this.fieldName = fieldName;
        }
    }
}
