// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.compiler;

import dev.imanity.antixray.libs.javassist.NotFoundException;
import dev.imanity.antixray.libs.javassist.compiler.ast.CallExpr;
import dev.imanity.antixray.libs.javassist.CtPrimitiveType;
import dev.imanity.antixray.libs.javassist.compiler.ast.ASTList;
import dev.imanity.antixray.libs.javassist.compiler.ast.Symbol;
import dev.imanity.antixray.libs.javassist.compiler.ast.CastExpr;
import dev.imanity.antixray.libs.javassist.compiler.ast.Visitor;
import dev.imanity.antixray.libs.javassist.compiler.ast.ASTree;
import dev.imanity.antixray.libs.javassist.compiler.ast.Expr;
import dev.imanity.antixray.libs.javassist.compiler.ast.Member;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.javassist.CtClass;

public class JvstTypeChecker extends TypeChecker
{
    private JvstCodeGen codeGen;
    
    public JvstTypeChecker(final CtClass ctClass, final ClassPool classPool, final JvstCodeGen codeGen) {
        super(ctClass, classPool);
        this.codeGen = codeGen;
    }
    
    public void addNullIfVoid() {
        if (this.exprType == 344) {
            this.exprType = 307;
            this.arrayDim = 0;
            this.className = "java/lang/Object";
        }
    }
    
    @Override
    public void atMember(final Member member) {
        final String value = member.get();
        if (value.equals(this.codeGen.paramArrayName)) {
            this.exprType = 307;
            this.arrayDim = 1;
            this.className = "java/lang/Object";
        }
        else if (value.equals("$sig")) {
            this.exprType = 307;
            this.arrayDim = 1;
            this.className = "java/lang/Class";
        }
        else if (value.equals("$type") || value.equals("$class")) {
            this.exprType = 307;
            this.arrayDim = 0;
            this.className = "java/lang/Class";
        }
        else {
            super.atMember(member);
        }
    }
    
    @Override
    protected void atFieldAssign(final Expr expr, final int n, final ASTree asTree, final ASTree asTree2) {
        if (asTree instanceof Member && ((Member)asTree).get().equals(this.codeGen.paramArrayName)) {
            asTree2.accept(this);
            final CtClass[] paramTypeList = this.codeGen.paramTypeList;
            if (paramTypeList == null) {
                return;
            }
            for (int length = paramTypeList.length, i = 0; i < length; ++i) {
                this.compileUnwrapValue(paramTypeList[i]);
            }
        }
        else {
            super.atFieldAssign(expr, n, asTree, asTree2);
        }
    }
    
    @Override
    public void atCastExpr(final CastExpr castExpr) {
        final ASTList className = castExpr.getClassName();
        if (className != null && castExpr.getArrayDim() == 0) {
            final ASTree head = className.head();
            if (head instanceof Symbol && className.tail() == null) {
                final String value = ((Symbol)head).get();
                if (value.equals(this.codeGen.returnCastName)) {
                    this.atCastToRtype(castExpr);
                    return;
                }
                if (value.equals("$w")) {
                    this.atCastToWrapper(castExpr);
                    return;
                }
            }
        }
        super.atCastExpr(castExpr);
    }
    
    protected void atCastToRtype(final CastExpr castExpr) {
        final CtClass returnType = this.codeGen.returnType;
        castExpr.getOprand().accept(this);
        if (this.exprType == 344 || CodeGen.isRefType(this.exprType) || this.arrayDim > 0) {
            this.compileUnwrapValue(returnType);
        }
        else if (returnType instanceof CtPrimitiveType) {
            this.exprType = MemberResolver.descToType(((CtPrimitiveType)returnType).getDescriptor());
            this.arrayDim = 0;
            this.className = null;
        }
    }
    
    protected void atCastToWrapper(final CastExpr castExpr) {
        castExpr.getOprand().accept(this);
        if (CodeGen.isRefType(this.exprType) || this.arrayDim > 0) {
            return;
        }
        if (this.resolver.lookupClass(this.exprType, this.arrayDim, this.className) instanceof CtPrimitiveType) {
            this.exprType = 307;
            this.arrayDim = 0;
            this.className = "java/lang/Object";
        }
    }
    
    @Override
    public void atCallExpr(final CallExpr callExpr) {
        final ASTree oprand1 = callExpr.oprand1();
        if (oprand1 instanceof Member) {
            final String value = ((Member)oprand1).get();
            if (this.codeGen.procHandler != null && value.equals(this.codeGen.proceedName)) {
                this.codeGen.procHandler.setReturnType(this, (ASTList)callExpr.oprand2());
                return;
            }
            if (value.equals("$cflow")) {
                this.atCflow((ASTList)callExpr.oprand2());
                return;
            }
        }
        super.atCallExpr(callExpr);
    }
    
    protected void atCflow(final ASTList list) {
        this.exprType = 324;
        this.arrayDim = 0;
        this.className = null;
    }
    
    public boolean isParamListName(final ASTList list) {
        if (this.codeGen.paramTypeList != null && list != null && list.tail() == null) {
            final ASTree head = list.head();
            return head instanceof Member && ((Member)head).get().equals(this.codeGen.paramListName);
        }
        return false;
    }
    
    @Override
    public int getMethodArgsLength(ASTList tail) {
        final String paramListName = this.codeGen.paramListName;
        int n = 0;
        while (tail != null) {
            final ASTree head = tail.head();
            if (head instanceof Member && ((Member)head).get().equals(paramListName)) {
                if (this.codeGen.paramTypeList != null) {
                    n += this.codeGen.paramTypeList.length;
                }
            }
            else {
                ++n;
            }
            tail = tail.tail();
        }
        return n;
    }
    
    @Override
    public void atMethodArgs(ASTList tail, final int[] array, final int[] array2, final String[] array3) {
        final CtClass[] paramTypeList = this.codeGen.paramTypeList;
        final String paramListName = this.codeGen.paramListName;
        int n = 0;
        while (tail != null) {
            final ASTree head = tail.head();
            if (head instanceof Member && ((Member)head).get().equals(paramListName)) {
                if (paramTypeList != null) {
                    for (int length = paramTypeList.length, i = 0; i < length; ++i) {
                        this.setType(paramTypeList[i]);
                        array[n] = this.exprType;
                        array2[n] = this.arrayDim;
                        array3[n] = this.className;
                        ++n;
                    }
                }
            }
            else {
                head.accept(this);
                array[n] = this.exprType;
                array2[n] = this.arrayDim;
                array3[n] = this.className;
                ++n;
            }
            tail = tail.tail();
        }
    }
    
    void compileInvokeSpecial(final ASTree asTree, final String s, final String s2, final String returnType, final ASTList list) {
        asTree.accept(this);
        final int methodArgsLength = this.getMethodArgsLength(list);
        this.atMethodArgs(list, new int[methodArgsLength], new int[methodArgsLength], new String[methodArgsLength]);
        this.setReturnType(returnType);
        this.addNullIfVoid();
    }
    
    protected void compileUnwrapValue(final CtClass type) {
        if (type == CtClass.voidType) {
            this.addNullIfVoid();
        }
        else {
            this.setType(type);
        }
    }
    
    public void setType(final CtClass ctClass) {
        this.setType(ctClass, 0);
    }
    
    private void setType(final CtClass ctClass, final int n) {
        if (ctClass.isPrimitive()) {
            this.exprType = MemberResolver.descToType(((CtPrimitiveType)ctClass).getDescriptor());
            this.arrayDim = n;
            this.className = null;
        }
        else {
            if (ctClass.isArray()) {
                try {
                    this.setType(ctClass.getComponentType(), n + 1);
                    return;
                }
                catch (final NotFoundException ex) {
                    throw new CompileError("undefined type: " + ctClass.getName());
                }
            }
            this.exprType = 307;
            this.arrayDim = n;
            this.className = MemberResolver.javaToJvmName(ctClass.getName());
        }
    }
}
