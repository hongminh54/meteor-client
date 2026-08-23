// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.expr;

import dev.imanity.antixray.libs.javassist.bytecode.CodeAttribute;
import dev.imanity.antixray.libs.javassist.CtPrimitiveType;
import dev.imanity.antixray.libs.javassist.bytecode.Bytecode;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import dev.imanity.antixray.libs.javassist.bytecode.ClassFile;
import dev.imanity.antixray.libs.javassist.bytecode.ExceptionsAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.ExceptionTable;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.javassist.NotFoundException;
import java.util.List;
import java.util.LinkedList;
import dev.imanity.antixray.libs.javassist.CtConstructor;
import dev.imanity.antixray.libs.javassist.CtBehavior;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;
import dev.imanity.antixray.libs.javassist.CtClass;
import dev.imanity.antixray.libs.javassist.bytecode.CodeIterator;
import dev.imanity.antixray.libs.javassist.bytecode.Opcode;

public abstract class Expr implements Opcode
{
    int currentPos;
    CodeIterator iterator;
    CtClass thisClass;
    MethodInfo thisMethod;
    boolean edited;
    int maxLocals;
    int maxStack;
    static final String javaLangObject = "java.lang.Object";
    
    protected Expr(final int currentPos, final CodeIterator iterator, final CtClass thisClass, final MethodInfo thisMethod) {
        this.currentPos = currentPos;
        this.iterator = iterator;
        this.thisClass = thisClass;
        this.thisMethod = thisMethod;
    }
    
    public CtClass getEnclosingClass() {
        return this.thisClass;
    }
    
    protected final ConstPool getConstPool() {
        return this.thisMethod.getConstPool();
    }
    
    protected final boolean edited() {
        return this.edited;
    }
    
    protected final int locals() {
        return this.maxLocals;
    }
    
    protected final int stack() {
        return this.maxStack;
    }
    
    protected final boolean withinStatic() {
        return (this.thisMethod.getAccessFlags() & 0x8) != 0x0;
    }
    
    public CtBehavior where() {
        final MethodInfo thisMethod = this.thisMethod;
        final CtBehavior[] declaredBehaviors = this.thisClass.getDeclaredBehaviors();
        for (int i = declaredBehaviors.length - 1; i >= 0; --i) {
            if (declaredBehaviors[i].getMethodInfo2() == thisMethod) {
                return declaredBehaviors[i];
            }
        }
        final CtConstructor classInitializer = this.thisClass.getClassInitializer();
        if (classInitializer != null && classInitializer.getMethodInfo2() == thisMethod) {
            return classInitializer;
        }
        for (int j = declaredBehaviors.length - 1; j >= 0; --j) {
            if (this.thisMethod.getName().equals(declaredBehaviors[j].getMethodInfo2().getName()) && this.thisMethod.getDescriptor().equals(declaredBehaviors[j].getMethodInfo2().getDescriptor())) {
                return declaredBehaviors[j];
            }
        }
        throw new RuntimeException("fatal: not found");
    }
    
    public CtClass[] mayThrow() {
        final ClassPool classPool = this.thisClass.getClassPool();
        final ConstPool constPool = this.thisMethod.getConstPool();
        final LinkedList list = new LinkedList();
        try {
            final ExceptionTable exceptionTable = this.thisMethod.getCodeAttribute().getExceptionTable();
            final int currentPos = this.currentPos;
            for (int size = exceptionTable.size(), i = 0; i < size; ++i) {
                if (exceptionTable.startPc(i) <= currentPos && currentPos < exceptionTable.endPc(i)) {
                    final int catchType = exceptionTable.catchType(i);
                    if (catchType > 0) {
                        try {
                            addClass(list, classPool.get(constPool.getClassInfo(catchType)));
                        }
                        catch (final NotFoundException ex) {}
                    }
                }
            }
        }
        catch (final NullPointerException ex2) {}
        final ExceptionsAttribute exceptionsAttribute = this.thisMethod.getExceptionsAttribute();
        if (exceptionsAttribute != null) {
            final String[] exceptions = exceptionsAttribute.getExceptions();
            if (exceptions != null) {
                for (int length = exceptions.length, j = 0; j < length; ++j) {
                    try {
                        addClass(list, classPool.get(exceptions[j]));
                    }
                    catch (final NotFoundException ex3) {}
                }
            }
        }
        return (CtClass[])list.toArray(new CtClass[list.size()]);
    }
    
    private static void addClass(final List<CtClass> list, final CtClass ctClass) {
        if (list.contains(ctClass)) {
            return;
        }
        list.add(ctClass);
    }
    
    public int indexOfBytecode() {
        return this.currentPos;
    }
    
    public int getLineNumber() {
        return this.thisMethod.getLineNumber(this.currentPos);
    }
    
    public String getFileName() {
        final ClassFile classFile2 = this.thisClass.getClassFile2();
        if (classFile2 == null) {
            return null;
        }
        return classFile2.getSourceFile();
    }
    
    static final boolean checkResultValue(final CtClass ctClass, final String s) {
        final boolean b = s.indexOf("$_") >= 0;
        if (!b && ctClass != CtClass.voidType) {
            throw new CannotCompileException("the resulting value is not stored in $_");
        }
        return b;
    }
    
    static final void storeStack(final CtClass[] array, final boolean b, final int n, final Bytecode bytecode) {
        storeStack0(0, array.length, array, n + 1, bytecode);
        if (b) {
            bytecode.addOpcode(1);
        }
        bytecode.addAstore(n);
    }
    
    private static void storeStack0(final int n, final int n2, final CtClass[] array, final int n3, final Bytecode bytecode) {
        if (n >= n2) {
            return;
        }
        final CtClass ctClass = array[n];
        int dataSize;
        if (ctClass instanceof CtPrimitiveType) {
            dataSize = ((CtPrimitiveType)ctClass).getDataSize();
        }
        else {
            dataSize = 1;
        }
        storeStack0(n + 1, n2, array, n3 + dataSize, bytecode);
        bytecode.addStore(n3, ctClass);
    }
    
    public abstract void replace(final String p0);
    
    public void replace(final String s, final ExprEditor exprEditor) {
        this.replace(s);
        if (exprEditor != null) {
            this.runEditor(exprEditor, this.iterator);
        }
    }
    
    protected void replace0(int position, final Bytecode bytecode, final int n) {
        final byte[] value = bytecode.get();
        this.edited = true;
        final int n2 = value.length - n;
        for (int i = 0; i < n; ++i) {
            this.iterator.writeByte(0, position + i);
        }
        if (n2 > 0) {
            position = this.iterator.insertGapAt(position, n2, false).position;
        }
        this.iterator.write(value, position);
        this.iterator.insert(bytecode.getExceptionTable(), position);
        this.maxLocals = bytecode.getMaxLocals();
        this.maxStack = bytecode.getMaxStack();
    }
    
    protected void runEditor(final ExprEditor exprEditor, final CodeIterator codeIterator) {
        final CodeAttribute value = codeIterator.get();
        final int maxLocals = value.getMaxLocals();
        final int maxStack = value.getMaxStack();
        final int locals = this.locals();
        value.setMaxStack(this.stack());
        value.setMaxLocals(locals);
        final ExprEditor.LoopContext loopContext = new ExprEditor.LoopContext(locals);
        final int codeLength = codeIterator.getCodeLength();
        final int lookAhead = codeIterator.lookAhead();
        codeIterator.move(this.currentPos);
        if (exprEditor.doit(this.thisClass, this.thisMethod, loopContext, codeIterator, lookAhead)) {
            this.edited = true;
        }
        codeIterator.move(lookAhead + codeIterator.getCodeLength() - codeLength);
        value.setMaxLocals(maxLocals);
        value.setMaxStack(maxStack);
        this.maxLocals = loopContext.maxLocals;
        this.maxStack += loopContext.maxStack;
    }
}
