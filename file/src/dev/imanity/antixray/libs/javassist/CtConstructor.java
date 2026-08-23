// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist;

import dev.imanity.antixray.libs.javassist.compiler.CompileError;
import dev.imanity.antixray.libs.javassist.compiler.Javac;
import dev.imanity.antixray.libs.javassist.bytecode.Bytecode;
import dev.imanity.antixray.libs.javassist.bytecode.CodeIterator;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;
import dev.imanity.antixray.libs.javassist.bytecode.CodeAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.BadBytecode;
import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;

public final class CtConstructor extends CtBehavior
{
    protected CtConstructor(final MethodInfo methodInfo, final CtClass ctClass) {
        super(ctClass, methodInfo);
    }
    
    public CtConstructor(final CtClass[] array, final CtClass ctClass) {
        this((MethodInfo)null, ctClass);
        this.methodInfo = new MethodInfo(ctClass.getClassFile2().getConstPool(), "<init>", Descriptor.ofConstructor(array));
        this.setModifiers(1);
    }
    
    public CtConstructor(final CtConstructor ctConstructor, final CtClass ctClass, final ClassMap classMap) {
        this((MethodInfo)null, ctClass);
        this.copy(ctConstructor, true, classMap);
    }
    
    public boolean isConstructor() {
        return this.methodInfo.isConstructor();
    }
    
    public boolean isClassInitializer() {
        return this.methodInfo.isStaticInitializer();
    }
    
    @Override
    public String getLongName() {
        return this.getDeclaringClass().getName() + (this.isConstructor() ? Descriptor.toString(this.getSignature()) : ".<clinit>()");
    }
    
    @Override
    public String getName() {
        if (this.methodInfo.isStaticInitializer()) {
            return "<clinit>";
        }
        return this.declaringClass.getSimpleName();
    }
    
    @Override
    public boolean isEmpty() {
        final CodeAttribute codeAttribute = this.getMethodInfo2().getCodeAttribute();
        if (codeAttribute == null) {
            return false;
        }
        final ConstPool constPool = codeAttribute.getConstPool();
        final CodeIterator iterator = codeAttribute.iterator();
        try {
            final int byte1 = iterator.byteAt(iterator.next());
            final int next;
            final int constructor;
            return byte1 == 177 || (byte1 == 42 && iterator.byteAt(next = iterator.next()) == 183 && (constructor = constPool.isConstructor(this.getSuperclassName(), iterator.u16bitAt(next + 1))) != 0 && "()V".equals(constPool.getUtf8Info(constructor)) && iterator.byteAt(iterator.next()) == 177 && !iterator.hasNext());
        }
        catch (final BadBytecode badBytecode) {
            return false;
        }
    }
    
    private String getSuperclassName() {
        return this.declaringClass.getClassFile2().getSuperclass();
    }
    
    public boolean callsSuper() {
        final CodeAttribute codeAttribute = this.methodInfo.getCodeAttribute();
        if (codeAttribute != null) {
            final CodeIterator iterator = codeAttribute.iterator();
            try {
                return iterator.skipSuperConstructor() >= 0;
            }
            catch (final BadBytecode badBytecode) {
                throw new CannotCompileException(badBytecode);
            }
        }
        return false;
    }
    
    @Override
    public void setBody(String body) {
        if (body == null) {
            if (this.isClassInitializer()) {
                body = ";";
            }
            else {
                body = "super();";
            }
        }
        super.setBody(body);
    }
    
    public void setBody(final CtConstructor ctConstructor, final ClassMap classMap) {
        CtBehavior.setBody0(ctConstructor.declaringClass, ctConstructor.methodInfo, this.declaringClass, this.methodInfo, classMap);
    }
    
    public void insertBeforeBody(final String s) {
        final CtClass declaringClass = this.declaringClass;
        declaringClass.checkModify();
        if (this.isClassInitializer()) {
            throw new CannotCompileException("class initializer");
        }
        final CodeAttribute codeAttribute = this.methodInfo.getCodeAttribute();
        final CodeIterator iterator = codeAttribute.iterator();
        final Bytecode bytecode = new Bytecode(this.methodInfo.getConstPool(), codeAttribute.getMaxStack(), codeAttribute.getMaxLocals());
        bytecode.setStackDepth(codeAttribute.getMaxStack());
        final Javac javac = new Javac(bytecode, declaringClass);
        try {
            javac.recordParams(this.getParameterTypes(), false);
            javac.compileStmnt(s);
            codeAttribute.setMaxStack(bytecode.getMaxStack());
            codeAttribute.setMaxLocals(bytecode.getMaxLocals());
            iterator.skipConstructor();
            iterator.insert(bytecode.getExceptionTable(), iterator.insertEx(bytecode.get()));
            this.methodInfo.rebuildStackMapIf6(declaringClass.getClassPool(), declaringClass.getClassFile2());
        }
        catch (final NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
        catch (final CompileError compileError) {
            throw new CannotCompileException(compileError);
        }
        catch (final BadBytecode badBytecode) {
            throw new CannotCompileException(badBytecode);
        }
    }
    
    @Override
    int getStartPosOfBody(final CodeAttribute codeAttribute) {
        final CodeIterator iterator = codeAttribute.iterator();
        try {
            iterator.skipConstructor();
            return iterator.next();
        }
        catch (final BadBytecode badBytecode) {
            throw new CannotCompileException(badBytecode);
        }
    }
    
    public CtMethod toMethod(final String s, final CtClass ctClass) {
        return this.toMethod(s, ctClass, null);
    }
    
    public CtMethod toMethod(final String name, final CtClass ctClass, final ClassMap classMap) {
        final CtMethod ctMethod = new CtMethod(null, ctClass);
        ctMethod.copy(this, false, classMap);
        if (this.isConstructor()) {
            final CodeAttribute codeAttribute = ctMethod.getMethodInfo2().getCodeAttribute();
            if (codeAttribute != null) {
                removeConsCall(codeAttribute);
                try {
                    this.methodInfo.rebuildStackMapIf6(ctClass.getClassPool(), ctClass.getClassFile2());
                }
                catch (final BadBytecode badBytecode) {
                    throw new CannotCompileException(badBytecode);
                }
            }
        }
        ctMethod.setName(name);
        return ctMethod;
    }
    
    private static void removeConsCall(final CodeAttribute codeAttribute) {
        final CodeIterator iterator = codeAttribute.iterator();
        try {
            int n = iterator.skipConstructor();
            if (n >= 0) {
                final String methodrefType = codeAttribute.getConstPool().getMethodrefType(iterator.u16bitAt(n + 1));
                final int n2 = Descriptor.numOfParameters(methodrefType) + 1;
                if (n2 > 3) {
                    n = iterator.insertGapAt(n, n2 - 3, false).position;
                }
                iterator.writeByte(87, n++);
                iterator.writeByte(0, n);
                iterator.writeByte(0, n + 1);
                final Descriptor.Iterator iterator2 = new Descriptor.Iterator(methodrefType);
                while (true) {
                    iterator2.next();
                    if (!iterator2.isParameter()) {
                        break;
                    }
                    iterator.writeByte(iterator2.is2byte() ? 88 : 87, n++);
                }
            }
        }
        catch (final BadBytecode badBytecode) {
            throw new CannotCompileException(badBytecode);
        }
    }
}
