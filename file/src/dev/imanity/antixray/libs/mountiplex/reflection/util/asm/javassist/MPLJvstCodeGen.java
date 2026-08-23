// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.asm.javassist;

import dev.imanity.antixray.libs.javassist.compiler.CodeGen;
import dev.imanity.antixray.libs.javassist.compiler.MemberCodeGen;
import dev.imanity.antixray.libs.javassist.compiler.ast.ASTree;
import dev.imanity.antixray.libs.javassist.compiler.MemberResolver;
import dev.imanity.antixray.libs.javassist.compiler.CompileError;
import dev.imanity.antixray.libs.javassist.compiler.NoFieldException;
import dev.imanity.antixray.libs.javassist.compiler.ast.Visitor;
import dev.imanity.antixray.libs.javassist.compiler.ast.Keyword;
import dev.imanity.antixray.libs.javassist.compiler.ast.Symbol;
import dev.imanity.antixray.libs.javassist.compiler.ast.ASTList;
import dev.imanity.antixray.libs.javassist.compiler.ast.Expr;
import dev.imanity.antixray.libs.javassist.compiler.ast.CallExpr;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.javassist.compiler.TypeChecker;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.ResolvedClassPool;
import dev.imanity.antixray.libs.javassist.CtClass;
import dev.imanity.antixray.libs.javassist.bytecode.Bytecode;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.javassist.compiler.JvstCodeGen;

public final class MPLJvstCodeGen extends JvstCodeGen
{
    private static final Method membercodegen_atMethodCallCore2;
    private static final Field codegen_typeChecker;
    private static final Field typechecker_resolver;
    private static final Method argTypesToString;
    
    public MPLJvstCodeGen(final Bytecode bytecode, final CtClass ctClass, final ResolvedClassPool resolvedClassPool) {
        super(bytecode, ctClass, resolvedClassPool);
        this.setTypeChecker(new MPLJvstTypeChecker(ctClass, resolvedClassPool, this));
        this.resolver = new MPLMemberResolver(resolvedClassPool);
        try {
            MPLJvstCodeGen.typechecker_resolver.set(MPLJvstCodeGen.codegen_typeChecker.get(this), new MPLMemberResolver(resolvedClassPool));
        }
        catch (final Throwable t) {
            throw MountiplexUtil.uncheckedRethrow(t);
        }
    }
    
    @Override
    public void atCallExpr(final CallExpr callExpr) {
        if (MPLJvstTypeChecker.mustSuppressResolvingOfCallExpr(callExpr)) {
            this.atCallExprSuppressLookup(callExpr);
        }
        else {
            super.atCallExpr(callExpr);
        }
    }
    
    private void atCallExprSuppressLookup(final CallExpr callExpr) {
        final Expr expr = (Expr)callExpr.oprand1();
        final ASTList list = (ASTList)callExpr.oprand2();
        boolean b = false;
        boolean b2 = false;
        final int n = -1;
        final MemberResolver.Method method = callExpr.getMethod();
        final String value = ((Symbol)expr.oprand2()).get();
        final ASTree oprand1 = expr.oprand1();
        if (oprand1 instanceof Keyword && ((Keyword)oprand1).get() == 336) {
            b2 = true;
        }
        boolean b3 = false;
        try {
            oprand1.accept(this);
        }
        catch (final NoFieldException ex) {
            if (ex.getExpr() != oprand1) {
                throw ex;
            }
            b3 = true;
            this.exprType = 307;
            this.arrayDim = 0;
            this.className = ex.getField();
            b = true;
        }
        final MPLMemberResolver mplMemberResolver = (MPLMemberResolver)this.resolver;
        CtClass lookupClass;
        if (this.arrayDim > 0) {
            lookupClass = mplMemberResolver.lookupClass("java.lang.Object", true);
        }
        else {
            if (this.exprType != 307) {
                throw new CompileError("bad method");
            }
            lookupClass = (b3 ? mplMemberResolver.lookupClassByJvmName(this.className) : mplMemberResolver.lookupClassByJvmNameIgnoreResolver(this.className));
        }
        this.atMethodCallCore(lookupClass, value, list, b, b2, n, method);
    }
    
    @Override
    public void atMethodCallCore(final CtClass ctClass, final String str, final ASTList list, boolean b, final boolean b2, final int i, MemberResolver.Method lookupMethod) {
        final int methodArgsLength = this.getMethodArgsLength(list);
        final int[] array = new int[methodArgsLength];
        final int[] array2 = new int[methodArgsLength];
        final String[] array3 = new String[methodArgsLength];
        if (!b && lookupMethod != null && lookupMethod.isStatic()) {
            this.bytecode.addOpcode(87);
            b = true;
        }
        this.bytecode.getStackDepth();
        this.atMethodArgs(list, array, array2, array3);
        if (lookupMethod == null) {
            lookupMethod = this.resolver.lookupMethod(ctClass, this.thisClass, this.thisMethod, str, array, array2, array3);
        }
        if (lookupMethod == null) {
            final String name = ctClass.getName();
            String s;
            try {
                s = (String)MPLJvstCodeGen.argTypesToString.invoke(null, array, array2, array3);
            }
            catch (final Throwable t) {
                throw MountiplexUtil.uncheckedRethrow(t);
            }
            String s2;
            if (str.equals("<init>")) {
                s2 = "cannot find constructor " + name + s;
            }
            else {
                s2 = str + s + " not found in " + name;
            }
            throw new CompileError(s2);
        }
        try {
            MPLJvstCodeGen.membercodegen_atMethodCallCore2.invoke(this, ctClass, lookupMethod.info.getName(), b, b2, i, lookupMethod);
        }
        catch (final Throwable t2) {
            throw MountiplexUtil.uncheckedRethrow(t2);
        }
    }
    
    static {
        try {
            (membercodegen_atMethodCallCore2 = MemberCodeGen.class.getDeclaredMethod("atMethodCallCore2", CtClass.class, String.class, Boolean.TYPE, Boolean.TYPE, Integer.TYPE, MemberResolver.Method.class)).setAccessible(true);
            (codegen_typeChecker = CodeGen.class.getDeclaredField("typeChecker")).setAccessible(true);
            (typechecker_resolver = TypeChecker.class.getDeclaredField("resolver")).setAccessible(true);
            (argTypesToString = TypeChecker.class.getDeclaredMethod("argTypesToString", int[].class, int[].class, String[].class)).setAccessible(true);
        }
        catch (final Throwable t) {
            throw MountiplexUtil.uncheckedRethrow(t);
        }
    }
}
