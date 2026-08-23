// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.expr;

import dev.imanity.antixray.libs.javassist.compiler.JvstTypeChecker;
import dev.imanity.antixray.libs.javassist.compiler.ast.ASTList;
import dev.imanity.antixray.libs.javassist.compiler.JvstCodeGen;
import dev.imanity.antixray.libs.javassist.bytecode.Bytecode;
import dev.imanity.antixray.libs.javassist.bytecode.CodeAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;
import dev.imanity.antixray.libs.javassist.compiler.ProceedHandler;
import dev.imanity.antixray.libs.javassist.compiler.Javac;
import dev.imanity.antixray.libs.javassist.CtPrimitiveType;
import dev.imanity.antixray.libs.javassist.bytecode.BadBytecode;
import dev.imanity.antixray.libs.javassist.NotFoundException;
import dev.imanity.antixray.libs.javassist.compiler.CompileError;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import dev.imanity.antixray.libs.javassist.CtBehavior;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;
import dev.imanity.antixray.libs.javassist.CtClass;
import dev.imanity.antixray.libs.javassist.bytecode.CodeIterator;

public class NewArray extends Expr
{
    int opcode;
    
    protected NewArray(final int n, final CodeIterator codeIterator, final CtClass ctClass, final MethodInfo methodInfo, final int opcode) {
        super(n, codeIterator, ctClass, methodInfo);
        this.opcode = opcode;
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
    
    public CtClass getComponentType() {
        if (this.opcode == 188) {
            return this.getPrimitiveType(this.iterator.byteAt(this.currentPos + 1));
        }
        if (this.opcode == 189 || this.opcode == 197) {
            final String classInfo = this.getConstPool().getClassInfo(this.iterator.u16bitAt(this.currentPos + 1));
            return Descriptor.toCtClass(Descriptor.toArrayComponent(classInfo, Descriptor.arrayDimension(classInfo)), this.thisClass.getClassPool());
        }
        throw new RuntimeException("bad opcode: " + this.opcode);
    }
    
    CtClass getPrimitiveType(final int i) {
        switch (i) {
            case 4: {
                return CtClass.booleanType;
            }
            case 5: {
                return CtClass.charType;
            }
            case 6: {
                return CtClass.floatType;
            }
            case 7: {
                return CtClass.doubleType;
            }
            case 8: {
                return CtClass.byteType;
            }
            case 9: {
                return CtClass.shortType;
            }
            case 10: {
                return CtClass.intType;
            }
            case 11: {
                return CtClass.longType;
            }
            default: {
                throw new RuntimeException("bad atype: " + i);
            }
        }
    }
    
    public int getDimension() {
        if (this.opcode == 188) {
            return 1;
        }
        if (this.opcode == 189 || this.opcode == 197) {
            return Descriptor.arrayDimension(this.getConstPool().getClassInfo(this.iterator.u16bitAt(this.currentPos + 1))) + ((this.opcode == 189) ? 1 : 0);
        }
        throw new RuntimeException("bad opcode: " + this.opcode);
    }
    
    public int getCreatedDimensions() {
        if (this.opcode == 197) {
            return this.iterator.byteAt(this.currentPos + 3);
        }
        return 1;
    }
    
    @Override
    public void replace(final String s) {
        try {
            this.replace2(s);
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
    
    private void replace2(final String s) {
        this.thisClass.getClassFile();
        final ConstPool constPool = this.getConstPool();
        final int currentPos = this.currentPos;
        int byte1 = 1;
        int n;
        String s2;
        int n2;
        if (this.opcode == 188) {
            n = this.iterator.byteAt(this.currentPos + 1);
            s2 = "[" + ((CtPrimitiveType)this.getPrimitiveType(n)).getDescriptor();
            n2 = 2;
        }
        else if (this.opcode == 189) {
            n = this.iterator.u16bitAt(currentPos + 1);
            final String classInfo = constPool.getClassInfo(n);
            if (classInfo.startsWith("[")) {
                s2 = "[" + classInfo;
            }
            else {
                s2 = "[L" + classInfo + ";";
            }
            n2 = 3;
        }
        else {
            if (this.opcode != 197) {
                throw new RuntimeException("bad opcode: " + this.opcode);
            }
            n = this.iterator.u16bitAt(this.currentPos + 1);
            s2 = constPool.getClassInfo(n);
            byte1 = this.iterator.byteAt(this.currentPos + 3);
            n2 = 4;
        }
        final CtClass ctClass = Descriptor.toCtClass(s2, this.thisClass.getClassPool());
        final Javac javac = new Javac(this.thisClass);
        final CodeAttribute value = this.iterator.get();
        final CtClass[] array = new CtClass[byte1];
        for (int i = 0; i < byte1; ++i) {
            array[i] = CtClass.intType;
        }
        final int maxLocals = value.getMaxLocals();
        javac.recordParams("java.lang.Object", array, true, maxLocals, this.withinStatic());
        Expr.checkResultValue(ctClass, s);
        final int recordReturnType = javac.recordReturnType(ctClass, true);
        javac.recordProceed(new ProceedForArray(ctClass, this.opcode, n, byte1));
        final Bytecode bytecode = javac.getBytecode();
        Expr.storeStack(array, true, maxLocals, bytecode);
        javac.recordLocalVariables(value, currentPos);
        bytecode.addOpcode(1);
        bytecode.addAstore(recordReturnType);
        javac.compileStmnt(s);
        bytecode.addAload(recordReturnType);
        this.replace0(currentPos, bytecode, n2);
    }
    
    static class ProceedForArray implements ProceedHandler
    {
        CtClass arrayType;
        int opcode;
        int index;
        int dimension;
        
        ProceedForArray(final CtClass arrayType, final int opcode, final int index, final int dimension) {
            this.arrayType = arrayType;
            this.opcode = opcode;
            this.index = index;
            this.dimension = dimension;
        }
        
        @Override
        public void doit(final JvstCodeGen jvstCodeGen, final Bytecode bytecode, final ASTList list) {
            final int methodArgsLength = jvstCodeGen.getMethodArgsLength(list);
            if (methodArgsLength != this.dimension) {
                throw new CompileError("$proceed() with a wrong number of parameters");
            }
            jvstCodeGen.atMethodArgs(list, new int[methodArgsLength], new int[methodArgsLength], new String[methodArgsLength]);
            bytecode.addOpcode(this.opcode);
            if (this.opcode == 189) {
                bytecode.addIndex(this.index);
            }
            else if (this.opcode == 188) {
                bytecode.add(this.index);
            }
            else {
                bytecode.addIndex(this.index);
                bytecode.add(this.dimension);
                bytecode.growStack(1 - this.dimension);
            }
            jvstCodeGen.setType(this.arrayType);
        }
        
        @Override
        public void setReturnType(final JvstTypeChecker jvstTypeChecker, final ASTList list) {
            jvstTypeChecker.setType(this.arrayType);
        }
    }
}
