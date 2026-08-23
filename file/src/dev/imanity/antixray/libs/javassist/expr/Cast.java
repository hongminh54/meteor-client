// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.expr;

import dev.imanity.antixray.libs.javassist.compiler.JvstTypeChecker;
import dev.imanity.antixray.libs.javassist.compiler.ast.ASTList;
import dev.imanity.antixray.libs.javassist.compiler.JvstCodeGen;
import dev.imanity.antixray.libs.javassist.bytecode.Bytecode;
import dev.imanity.antixray.libs.javassist.bytecode.CodeAttribute;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.javassist.bytecode.BadBytecode;
import dev.imanity.antixray.libs.javassist.NotFoundException;
import dev.imanity.antixray.libs.javassist.compiler.CompileError;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import dev.imanity.antixray.libs.javassist.compiler.ProceedHandler;
import dev.imanity.antixray.libs.javassist.compiler.Javac;
import dev.imanity.antixray.libs.javassist.CtBehavior;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;
import dev.imanity.antixray.libs.javassist.CtClass;
import dev.imanity.antixray.libs.javassist.bytecode.CodeIterator;

public class Cast extends Expr
{
    protected Cast(final int n, final CodeIterator codeIterator, final CtClass ctClass, final MethodInfo methodInfo) {
        super(n, codeIterator, ctClass, methodInfo);
    }
    
    @Override
    public CtBehavior where() {
        return super.where();
    }
    
    @Override
    public int getLineNumber() {
        return super.getLineNumber();
    }
    
    @Override
    public String getFileName() {
        return super.getFileName();
    }
    
    public CtClass getType() {
        return this.thisClass.getClassPool().getCtClass(this.getConstPool().getClassInfo(this.iterator.u16bitAt(this.currentPos + 1)));
    }
    
    @Override
    public CtClass[] mayThrow() {
        return super.mayThrow();
    }
    
    @Override
    public void replace(final String s) {
        this.thisClass.getClassFile();
        this.getConstPool();
        final int currentPos = this.currentPos;
        final int u16bit = this.iterator.u16bitAt(currentPos + 1);
        final Javac javac = new Javac(this.thisClass);
        final ClassPool classPool = this.thisClass.getClassPool();
        final CodeAttribute value = this.iterator.get();
        try {
            final CtClass[] array = { classPool.get("java.lang.Object") };
            final CtClass type = this.getType();
            final int maxLocals = value.getMaxLocals();
            javac.recordParams("java.lang.Object", array, true, maxLocals, this.withinStatic());
            final int recordReturnType = javac.recordReturnType(type, true);
            javac.recordProceed(new ProceedForCast(u16bit, type));
            Expr.checkResultValue(type, s);
            final Bytecode bytecode = javac.getBytecode();
            Expr.storeStack(array, true, maxLocals, bytecode);
            javac.recordLocalVariables(value, currentPos);
            bytecode.addConstZero(type);
            bytecode.addStore(recordReturnType, type);
            javac.compileStmnt(s);
            bytecode.addLoad(recordReturnType, type);
            this.replace0(currentPos, bytecode, 3);
        }
        catch (final CompileError compileError) {
            throw new CannotCompileException(compileError);
        }
        catch (final NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
        catch (final BadBytecode badBytecode) {
            throw new CannotCompileException("broken method");
        }
    }
    
    static class ProceedForCast implements ProceedHandler
    {
        int index;
        CtClass retType;
        
        ProceedForCast(final int index, final CtClass retType) {
            this.index = index;
            this.retType = retType;
        }
        
        @Override
        public void doit(final JvstCodeGen jvstCodeGen, final Bytecode bytecode, final ASTList list) {
            if (jvstCodeGen.getMethodArgsLength(list) != 1) {
                throw new CompileError("$proceed() cannot take more than one parameter for cast");
            }
            jvstCodeGen.atMethodArgs(list, new int[1], new int[1], new String[1]);
            bytecode.addOpcode(192);
            bytecode.addIndex(this.index);
            jvstCodeGen.setType(this.retType);
        }
        
        @Override
        public void setReturnType(final JvstTypeChecker jvstTypeChecker, final ASTList list) {
            jvstTypeChecker.atMethodArgs(list, new int[1], new int[1], new String[1]);
            jvstTypeChecker.setType(this.retType);
        }
    }
}
