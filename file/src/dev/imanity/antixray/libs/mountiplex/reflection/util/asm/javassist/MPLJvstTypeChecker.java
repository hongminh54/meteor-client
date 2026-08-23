// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.asm.javassist;

import dev.imanity.antixray.libs.javassist.compiler.CompileError;
import dev.imanity.antixray.libs.javassist.compiler.NoFieldException;
import dev.imanity.antixray.libs.javassist.compiler.MemberResolver;
import dev.imanity.antixray.libs.javassist.compiler.ast.Visitor;
import dev.imanity.antixray.libs.javassist.compiler.ast.ASTList;
import dev.imanity.antixray.libs.javassist.compiler.ast.ASTree;
import dev.imanity.antixray.libs.javassist.compiler.ast.Symbol;
import dev.imanity.antixray.libs.javassist.compiler.ast.Keyword;
import dev.imanity.antixray.libs.javassist.compiler.ast.Expr;
import dev.imanity.antixray.libs.javassist.compiler.ast.CallExpr;
import dev.imanity.antixray.libs.javassist.compiler.JvstCodeGen;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.javassist.CtClass;
import dev.imanity.antixray.libs.javassist.compiler.JvstTypeChecker;

public class MPLJvstTypeChecker extends JvstTypeChecker
{
    public MPLJvstTypeChecker(final CtClass ctClass, final ClassPool classPool, final JvstCodeGen jvstCodeGen) {
        super(ctClass, classPool, jvstCodeGen);
    }
    
    @Override
    public void atCallExpr(final CallExpr callExpr) {
        if (mustSuppressResolvingOfCallExpr(callExpr)) {
            this.atCallExprSuppressLookup(callExpr);
        }
        else {
            super.atCallExpr(callExpr);
        }
    }
    
    public static boolean mustSuppressResolvingOfCallExpr(final CallExpr callExpr) {
        final ASTree oprand1 = callExpr.oprand1();
        if (!(oprand1 instanceof Expr) || ((Expr)oprand1).getOperator() != 46) {
            return false;
        }
        final ASTree oprand2 = ((Expr)oprand1).oprand1();
        if (oprand2 instanceof Expr) {
            final Expr expr = (Expr)oprand2;
            if (expr.getOperator() == 46) {
                final ASTree oprand3 = expr.oprand2();
                if (oprand3 instanceof Keyword && ((Keyword)oprand3).get() == 336 && ((Symbol)expr.oprand1()).get() != null) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void atCallExprSuppressLookup(final CallExpr callExpr) {
        final Expr expr = (Expr)callExpr.oprand1();
        final ASTList list = (ASTList)callExpr.oprand2();
        final String value = ((Symbol)expr.oprand2()).get();
        final ASTree oprand1 = expr.oprand1();
        boolean b = false;
        try {
            oprand1.accept(this);
        }
        catch (final NoFieldException ex) {
            if (ex.getExpr() != oprand1) {
                throw ex;
            }
            b = true;
            this.exprType = 307;
            this.arrayDim = 0;
            this.className = ex.getField();
            expr.setOperator(35);
            expr.setOprand1(new Symbol(MemberResolver.jvmToJavaName(this.className)));
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
            lookupClass = (b ? mplMemberResolver.lookupClassByJvmName(this.className) : mplMemberResolver.lookupClassByJvmNameIgnoreResolver(this.className));
        }
        callExpr.setMethod(this.atMethodCallCore(lookupClass, value, list));
    }
}
