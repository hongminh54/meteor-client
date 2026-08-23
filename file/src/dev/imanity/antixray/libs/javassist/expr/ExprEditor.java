// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.expr;

import dev.imanity.antixray.libs.javassist.bytecode.ExceptionTable;
import dev.imanity.antixray.libs.javassist.bytecode.CodeIterator;
import dev.imanity.antixray.libs.javassist.bytecode.CodeAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.BadBytecode;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;
import dev.imanity.antixray.libs.javassist.CtClass;

public class ExprEditor
{
    public boolean doit(final CtClass ctClass, final MethodInfo methodInfo) {
        final CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        if (codeAttribute == null) {
            return false;
        }
        final CodeIterator iterator = codeAttribute.iterator();
        boolean b = false;
        final LoopContext loopContext = new LoopContext(codeAttribute.getMaxLocals());
        while (iterator.hasNext()) {
            if (this.loopBody(iterator, ctClass, methodInfo, loopContext)) {
                b = true;
            }
        }
        final ExceptionTable exceptionTable = codeAttribute.getExceptionTable();
        for (int size = exceptionTable.size(), i = 0; i < size; ++i) {
            final Handler handler = new Handler(exceptionTable, i, iterator, ctClass, methodInfo);
            this.edit(handler);
            if (handler.edited()) {
                b = true;
                loopContext.updateMax(handler.locals(), handler.stack());
            }
        }
        if (codeAttribute.getMaxLocals() < loopContext.maxLocals) {
            codeAttribute.setMaxLocals(loopContext.maxLocals);
        }
        codeAttribute.setMaxStack(codeAttribute.getMaxStack() + loopContext.maxStack);
        try {
            if (b) {
                methodInfo.rebuildStackMapIf6(ctClass.getClassPool(), ctClass.getClassFile2());
            }
        }
        catch (final BadBytecode badBytecode) {
            throw new CannotCompileException(badBytecode.getMessage(), badBytecode);
        }
        return b;
    }
    
    boolean doit(final CtClass ctClass, final MethodInfo methodInfo, final LoopContext loopContext, final CodeIterator codeIterator, int n) {
        boolean b = false;
        while (codeIterator.hasNext() && codeIterator.lookAhead() < n) {
            final int codeLength = codeIterator.getCodeLength();
            if (this.loopBody(codeIterator, ctClass, methodInfo, loopContext)) {
                b = true;
                final int codeLength2 = codeIterator.getCodeLength();
                if (codeLength == codeLength2) {
                    continue;
                }
                n += codeLength2 - codeLength;
            }
        }
        return b;
    }
    
    final boolean loopBody(final CodeIterator codeIterator, final CtClass ctClass, final MethodInfo methodInfo, final LoopContext loopContext) {
        try {
            Expr expr = null;
            final int next = codeIterator.next();
            final int byte1 = codeIterator.byteAt(next);
            if (byte1 >= 178) {
                if (byte1 < 188) {
                    if (byte1 == 184 || byte1 == 185 || byte1 == 182) {
                        expr = new MethodCall(next, codeIterator, ctClass, methodInfo);
                        this.edit((MethodCall)expr);
                    }
                    else if (byte1 == 180 || byte1 == 178 || byte1 == 181 || byte1 == 179) {
                        expr = new FieldAccess(next, codeIterator, ctClass, methodInfo, byte1);
                        this.edit((FieldAccess)expr);
                    }
                    else if (byte1 == 187) {
                        loopContext.newList = new NewOp(loopContext.newList, next, methodInfo.getConstPool().getClassInfo(codeIterator.u16bitAt(next + 1)));
                    }
                    else if (byte1 == 183) {
                        final NewOp newList = loopContext.newList;
                        if (newList != null && methodInfo.getConstPool().isConstructor(newList.type, codeIterator.u16bitAt(next + 1)) > 0) {
                            expr = new NewExpr(next, codeIterator, ctClass, methodInfo, newList.type, newList.pos);
                            this.edit((NewExpr)expr);
                            loopContext.newList = newList.next;
                        }
                        else {
                            final MethodCall methodCall = new MethodCall(next, codeIterator, ctClass, methodInfo);
                            if (methodCall.getMethodName().equals("<init>")) {
                                this.edit((ConstructorCall)(expr = new ConstructorCall(next, codeIterator, ctClass, methodInfo)));
                            }
                            else {
                                expr = methodCall;
                                this.edit(methodCall);
                            }
                        }
                    }
                }
                else if (byte1 == 188 || byte1 == 189 || byte1 == 197) {
                    expr = new NewArray(next, codeIterator, ctClass, methodInfo, byte1);
                    this.edit((NewArray)expr);
                }
                else if (byte1 == 193) {
                    expr = new Instanceof(next, codeIterator, ctClass, methodInfo);
                    this.edit((Instanceof)expr);
                }
                else if (byte1 == 192) {
                    expr = new Cast(next, codeIterator, ctClass, methodInfo);
                    this.edit((Cast)expr);
                }
            }
            if (expr != null && expr.edited()) {
                loopContext.updateMax(expr.locals(), expr.stack());
                return true;
            }
            return false;
        }
        catch (final BadBytecode badBytecode) {
            throw new CannotCompileException(badBytecode);
        }
    }
    
    public void edit(final NewExpr newExpr) {
    }
    
    public void edit(final NewArray newArray) {
    }
    
    public void edit(final MethodCall methodCall) {
    }
    
    public void edit(final ConstructorCall constructorCall) {
    }
    
    public void edit(final FieldAccess fieldAccess) {
    }
    
    public void edit(final Instanceof instanceof1) {
    }
    
    public void edit(final Cast cast) {
    }
    
    public void edit(final Handler handler) {
    }
    
    static final class NewOp
    {
        NewOp next;
        int pos;
        String type;
        
        NewOp(final NewOp next, final int pos, final String type) {
            this.next = next;
            this.pos = pos;
            this.type = type;
        }
    }
    
    static final class LoopContext
    {
        NewOp newList;
        int maxLocals;
        int maxStack;
        
        LoopContext(final int maxLocals) {
            this.maxLocals = maxLocals;
            this.maxStack = 0;
            this.newList = null;
        }
        
        void updateMax(final int maxLocals, final int maxStack) {
            if (this.maxLocals < maxLocals) {
                this.maxLocals = maxLocals;
            }
            if (this.maxStack < maxStack) {
                this.maxStack = maxStack;
            }
        }
    }
}
