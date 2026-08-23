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

public class Instanceof extends Expr
{
    protected Instanceof(final int n, final CodeIterator codeIterator, final CtClass ctClass, final MethodInfo methodInfo) {
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
            final CtClass booleanType = CtClass.booleanType;
            final int maxLocals = value.getMaxLocals();
            javac.recordParams("java.lang.Object", array, true, maxLocals, this.withinStatic());
            final int recordReturnType = javac.recordReturnType(booleanType, true);
            javac.recordProceed(new ProceedForInstanceof(u16bit));
            javac.recordType(this.getType());
            Expr.checkResultValue(booleanType, s);
            final Bytecode bytecode = javac.getBytecode();
            Expr.storeStack(array, true, maxLocals, bytecode);
            javac.recordLocalVariables(value, currentPos);
            bytecode.addConstZero(booleanType);
            bytecode.addStore(recordReturnType, booleanType);
            javac.compileStmnt(s);
            bytecode.addLoad(recordReturnType, booleanType);
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
    
    static class ProceedForInstanceof implements ProceedHandler
    {
        int index;
        
        ProceedForInstanceof(final int index) {
            this.index = index;
        }
        
        @Override
        public void doit(final JvstCodeGen jvstCodeGen, final Bytecode bytecode, final ASTList list) {
            if (jvstCodeGen.getMethodArgsLength(list) != 1) {
                throw new CompileError("$proceed() cannot take more than one parameter for instanceof");
            }
            jvstCodeGen.atMethodArgs(list, new int[1], new int[1], new String[1]);
            bytecode.addOpcode(193);
            bytecode.addIndex(this.index);
            jvstCodeGen.setType(CtClass.booleanType);
        }
        
        @Override
        public void setReturnType(final JvstTypeChecker jvstTypeChecker, final ASTList list) {
            jvstTypeChecker.atMethodArgs(list, new int[1], new int[1], new String[1]);
            jvstTypeChecker.setType(CtClass.booleanType);
        }
    }
}
