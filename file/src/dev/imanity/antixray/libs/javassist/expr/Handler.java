// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.expr;

import dev.imanity.antixray.libs.javassist.bytecode.Bytecode;
import dev.imanity.antixray.libs.javassist.bytecode.CodeAttribute;
import dev.imanity.antixray.libs.javassist.compiler.CompileError;
import dev.imanity.antixray.libs.javassist.NotFoundException;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import dev.imanity.antixray.libs.javassist.compiler.Javac;
import dev.imanity.antixray.libs.javassist.CtBehavior;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;
import dev.imanity.antixray.libs.javassist.CtClass;
import dev.imanity.antixray.libs.javassist.bytecode.CodeIterator;
import dev.imanity.antixray.libs.javassist.bytecode.ExceptionTable;

public class Handler extends Expr
{
    private static String EXCEPTION_NAME;
    private ExceptionTable etable;
    private int index;
    
    protected Handler(final ExceptionTable etable, final int index, final CodeIterator codeIterator, final CtClass ctClass, final MethodInfo methodInfo) {
        super(etable.handlerPc(index), codeIterator, ctClass, methodInfo);
        this.etable = etable;
        this.index = index;
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
    
    @Override
    public CtClass[] mayThrow() {
        return super.mayThrow();
    }
    
    public CtClass getType() {
        final int catchType = this.etable.catchType(this.index);
        if (catchType == 0) {
            return null;
        }
        return this.thisClass.getClassPool().getCtClass(this.getConstPool().getClassInfo(catchType));
    }
    
    public boolean isFinally() {
        return this.etable.catchType(this.index) == 0;
    }
    
    @Override
    public void replace(final String s) {
        throw new RuntimeException("not implemented yet");
    }
    
    public void insertBefore(final String s) {
        this.edited = true;
        this.getConstPool();
        final CodeAttribute value = this.iterator.get();
        final Javac javac = new Javac(this.thisClass);
        final Bytecode bytecode = javac.getBytecode();
        bytecode.setStackDepth(1);
        bytecode.setMaxLocals(value.getMaxLocals());
        try {
            final CtClass type = this.getType();
            final int recordVariable = javac.recordVariable(type, Handler.EXCEPTION_NAME);
            javac.recordReturnType(type, false);
            bytecode.addAstore(recordVariable);
            javac.compileStmnt(s);
            bytecode.addAload(recordVariable);
            final int handlerPc = this.etable.handlerPc(this.index);
            bytecode.addOpcode(167);
            bytecode.addIndex(handlerPc - this.iterator.getCodeLength() - bytecode.currentPc() + 1);
            this.maxStack = bytecode.getMaxStack();
            this.maxLocals = bytecode.getMaxLocals();
            final int append = this.iterator.append(bytecode.get());
            this.iterator.append(bytecode.getExceptionTable(), append);
            this.etable.setHandlerPc(this.index, append);
        }
        catch (final NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
        catch (final CompileError compileError) {
            throw new CannotCompileException(compileError);
        }
    }
    
    static {
        Handler.EXCEPTION_NAME = "$1";
    }
}
