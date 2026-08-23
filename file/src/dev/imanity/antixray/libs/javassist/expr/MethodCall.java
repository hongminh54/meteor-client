// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.expr;

import dev.imanity.antixray.libs.javassist.bytecode.Bytecode;
import dev.imanity.antixray.libs.javassist.bytecode.CodeAttribute;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.javassist.bytecode.BadBytecode;
import dev.imanity.antixray.libs.javassist.NotFoundException;
import dev.imanity.antixray.libs.javassist.compiler.CompileError;
import dev.imanity.antixray.libs.javassist.compiler.Javac;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import dev.imanity.antixray.libs.javassist.CtMethod;
import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import dev.imanity.antixray.libs.javassist.CtBehavior;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;
import dev.imanity.antixray.libs.javassist.CtClass;
import dev.imanity.antixray.libs.javassist.bytecode.CodeIterator;

public class MethodCall extends Expr
{
    protected MethodCall(final int n, final CodeIterator codeIterator, final CtClass ctClass, final MethodInfo methodInfo) {
        super(n, codeIterator, ctClass, methodInfo);
    }
    
    private int getNameAndType(final ConstPool constPool) {
        final int currentPos = this.currentPos;
        final int byte1 = this.iterator.byteAt(currentPos);
        final int u16bit = this.iterator.u16bitAt(currentPos + 1);
        if (byte1 == 185) {
            return constPool.getInterfaceMethodrefNameAndType(u16bit);
        }
        return constPool.getMethodrefNameAndType(u16bit);
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
    
    protected CtClass getCtClass() {
        return this.thisClass.getClassPool().get(this.getClassName());
    }
    
    public String getClassName() {
        final ConstPool constPool = this.getConstPool();
        final int currentPos = this.currentPos;
        final int byte1 = this.iterator.byteAt(currentPos);
        final int u16bit = this.iterator.u16bitAt(currentPos + 1);
        String s;
        if (byte1 == 185) {
            s = constPool.getInterfaceMethodrefClassName(u16bit);
        }
        else {
            s = constPool.getMethodrefClassName(u16bit);
        }
        if (s.charAt(0) == '[') {
            s = Descriptor.toClassName(s);
        }
        return s;
    }
    
    public String getMethodName() {
        final ConstPool constPool = this.getConstPool();
        return constPool.getUtf8Info(constPool.getNameAndTypeName(this.getNameAndType(constPool)));
    }
    
    public CtMethod getMethod() {
        return this.getCtClass().getMethod(this.getMethodName(), this.getSignature());
    }
    
    public String getSignature() {
        final ConstPool constPool = this.getConstPool();
        return constPool.getUtf8Info(constPool.getNameAndTypeDescriptor(this.getNameAndType(constPool)));
    }
    
    @Override
    public CtClass[] mayThrow() {
        return super.mayThrow();
    }
    
    public boolean isSuper() {
        return this.iterator.byteAt(this.currentPos) == 183 && !this.where().getDeclaringClass().getName().equals(this.getClassName());
    }
    
    @Override
    public void replace(final String s) {
        this.thisClass.getClassFile();
        final ConstPool constPool = this.getConstPool();
        final int currentPos = this.currentPos;
        final int u16bit = this.iterator.u16bitAt(currentPos + 1);
        final int byte1 = this.iterator.byteAt(currentPos);
        int n;
        String s2;
        String s3;
        String s4;
        if (byte1 == 185) {
            n = 5;
            s2 = constPool.getInterfaceMethodrefClassName(u16bit);
            s3 = constPool.getInterfaceMethodrefName(u16bit);
            s4 = constPool.getInterfaceMethodrefType(u16bit);
        }
        else {
            if (byte1 != 184 && byte1 != 183 && byte1 != 182) {
                throw new CannotCompileException("not method invocation");
            }
            n = 3;
            s2 = constPool.getMethodrefClassName(u16bit);
            s3 = constPool.getMethodrefName(u16bit);
            s4 = constPool.getMethodrefType(u16bit);
        }
        final Javac javac = new Javac(this.thisClass);
        final ClassPool classPool = this.thisClass.getClassPool();
        final CodeAttribute value = this.iterator.get();
        try {
            final CtClass[] parameterTypes = Descriptor.getParameterTypes(s4, classPool);
            final CtClass returnType = Descriptor.getReturnType(s4, classPool);
            final int maxLocals = value.getMaxLocals();
            javac.recordParams(s2, parameterTypes, true, maxLocals, this.withinStatic());
            final int recordReturnType = javac.recordReturnType(returnType, true);
            if (byte1 == 184) {
                javac.recordStaticProceed(s2, s3);
            }
            else if (byte1 == 183) {
                javac.recordSpecialProceed("$0", s2, s3, s4, u16bit);
            }
            else {
                javac.recordProceed("$0", s3);
            }
            Expr.checkResultValue(returnType, s);
            final Bytecode bytecode = javac.getBytecode();
            Expr.storeStack(parameterTypes, byte1 == 184, maxLocals, bytecode);
            javac.recordLocalVariables(value, currentPos);
            if (returnType != CtClass.voidType) {
                bytecode.addConstZero(returnType);
                bytecode.addStore(recordReturnType, returnType);
            }
            javac.compileStmnt(s);
            if (returnType != CtClass.voidType) {
                bytecode.addLoad(recordReturnType, returnType);
            }
            this.replace0(currentPos, bytecode, n);
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
}
