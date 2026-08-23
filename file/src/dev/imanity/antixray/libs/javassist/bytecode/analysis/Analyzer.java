// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode.analysis;

import java.util.Iterator;
import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import dev.imanity.antixray.libs.javassist.bytecode.ExceptionTable;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;
import dev.imanity.antixray.libs.javassist.NotFoundException;
import dev.imanity.antixray.libs.javassist.bytecode.BadBytecode;
import dev.imanity.antixray.libs.javassist.CtMethod;
import dev.imanity.antixray.libs.javassist.bytecode.CodeIterator;
import dev.imanity.antixray.libs.javassist.bytecode.CodeAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;
import dev.imanity.antixray.libs.javassist.CtClass;
import dev.imanity.antixray.libs.javassist.bytecode.Opcode;

public class Analyzer implements Opcode
{
    private final SubroutineScanner scanner;
    private CtClass clazz;
    private ExceptionInfo[] exceptions;
    private Frame[] frames;
    private Subroutine[] subroutines;
    
    public Analyzer() {
        this.scanner = new SubroutineScanner();
    }
    
    public Frame[] analyze(final CtClass clazz, final MethodInfo methodInfo) {
        this.clazz = clazz;
        final CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        if (codeAttribute == null) {
            return null;
        }
        final int maxLocals = codeAttribute.getMaxLocals();
        final int maxStack = codeAttribute.getMaxStack();
        final int codeLength = codeAttribute.getCodeLength();
        final CodeIterator iterator = codeAttribute.iterator();
        final IntQueue intQueue = new IntQueue();
        this.exceptions = this.buildExceptionInfo(methodInfo);
        this.subroutines = this.scanner.scan(methodInfo);
        final Executor executor = new Executor(clazz.getClassPool(), methodInfo.getConstPool());
        (this.frames = new Frame[codeLength])[iterator.lookAhead()] = this.firstFrame(methodInfo, maxLocals, maxStack);
        intQueue.add(iterator.next());
        while (!intQueue.isEmpty()) {
            this.analyzeNextEntry(methodInfo, iterator, intQueue, executor);
        }
        return this.frames;
    }
    
    public Frame[] analyze(final CtMethod ctMethod) {
        return this.analyze(ctMethod.getDeclaringClass(), ctMethod.getMethodInfo2());
    }
    
    private void analyzeNextEntry(final MethodInfo methodInfo, final CodeIterator codeIterator, final IntQueue intQueue, final Executor executor) {
        final int take = intQueue.take();
        codeIterator.move(take);
        codeIterator.next();
        final Frame copy = this.frames[take].copy();
        final Subroutine subroutine = this.subroutines[take];
        try {
            executor.execute(methodInfo, take, codeIterator, copy, subroutine);
        }
        catch (final RuntimeException ex) {
            throw new BadBytecode(ex.getMessage() + "[pos = " + take + "]", ex);
        }
        final int byte1 = codeIterator.byteAt(take);
        if (byte1 == 170) {
            this.mergeTableSwitch(intQueue, take, codeIterator, copy);
        }
        else if (byte1 == 171) {
            this.mergeLookupSwitch(intQueue, take, codeIterator, copy);
        }
        else if (byte1 == 169) {
            this.mergeRet(intQueue, codeIterator, take, copy, subroutine);
        }
        else if (Util.isJumpInstruction(byte1)) {
            final int jumpTarget = Util.getJumpTarget(take, codeIterator);
            if (Util.isJsr(byte1)) {
                this.mergeJsr(intQueue, this.frames[take], this.subroutines[jumpTarget], take, this.lookAhead(codeIterator, take));
            }
            else if (!Util.isGoto(byte1)) {
                this.merge(intQueue, copy, this.lookAhead(codeIterator, take));
            }
            this.merge(intQueue, copy, jumpTarget);
        }
        else if (byte1 != 191 && !Util.isReturn(byte1)) {
            this.merge(intQueue, copy, this.lookAhead(codeIterator, take));
        }
        this.mergeExceptionHandlers(intQueue, methodInfo, take, copy);
    }
    
    private ExceptionInfo[] buildExceptionInfo(final MethodInfo methodInfo) {
        final ConstPool constPool = methodInfo.getConstPool();
        final ClassPool classPool = this.clazz.getClassPool();
        final ExceptionTable exceptionTable = methodInfo.getCodeAttribute().getExceptionTable();
        final ExceptionInfo[] array = new ExceptionInfo[exceptionTable.size()];
        for (int i = 0; i < exceptionTable.size(); ++i) {
            final int catchType = exceptionTable.catchType(i);
            Type type;
            try {
                type = ((catchType == 0) ? Type.THROWABLE : Type.get(classPool.get(constPool.getClassInfo(catchType))));
            }
            catch (final NotFoundException ex) {
                throw new IllegalStateException(ex.getMessage());
            }
            array[i] = new ExceptionInfo(exceptionTable.startPc(i), exceptionTable.endPc(i), exceptionTable.handlerPc(i), type);
        }
        return array;
    }
    
    private Frame firstFrame(final MethodInfo methodInfo, final int n, final int n2) {
        int n3 = 0;
        final Frame frame = new Frame(n, n2);
        if ((methodInfo.getAccessFlags() & 0x8) == 0x0) {
            frame.setLocal(n3++, Type.get(this.clazz));
        }
        CtClass[] parameterTypes;
        try {
            parameterTypes = Descriptor.getParameterTypes(methodInfo.getDescriptor(), this.clazz.getClassPool());
        }
        catch (final NotFoundException cause) {
            throw new RuntimeException(cause);
        }
        for (int i = 0; i < parameterTypes.length; ++i) {
            final Type zeroExtend = this.zeroExtend(Type.get(parameterTypes[i]));
            frame.setLocal(n3++, zeroExtend);
            if (zeroExtend.getSize() == 2) {
                frame.setLocal(n3++, Type.TOP);
            }
        }
        return frame;
    }
    
    private int getNext(final CodeIterator codeIterator, final int n, final int n2) {
        codeIterator.move(n);
        codeIterator.next();
        final int lookAhead = codeIterator.lookAhead();
        codeIterator.move(n2);
        codeIterator.next();
        return lookAhead;
    }
    
    private int lookAhead(final CodeIterator codeIterator, final int i) {
        if (!codeIterator.hasNext()) {
            throw new BadBytecode("Execution falls off end! [pos = " + i + "]");
        }
        return codeIterator.lookAhead();
    }
    
    private void merge(final IntQueue intQueue, final Frame frame, final int n) {
        final Frame frame2 = this.frames[n];
        boolean merge;
        if (frame2 == null) {
            this.frames[n] = frame.copy();
            merge = true;
        }
        else {
            merge = frame2.merge(frame);
        }
        if (merge) {
            intQueue.add(n);
        }
    }
    
    private void mergeExceptionHandlers(final IntQueue intQueue, final MethodInfo methodInfo, final int n, final Frame frame) {
        for (int i = 0; i < this.exceptions.length; ++i) {
            final ExceptionInfo exceptionInfo = this.exceptions[i];
            if (n >= exceptionInfo.start && n < exceptionInfo.end) {
                final Frame copy = frame.copy();
                copy.clearStack();
                copy.push(exceptionInfo.type);
                this.merge(intQueue, copy, exceptionInfo.handler);
            }
        }
    }
    
    private void mergeJsr(final IntQueue intQueue, final Frame frame, final Subroutine subroutine, final int i, final int n) {
        if (subroutine == null) {
            throw new BadBytecode("No subroutine at jsr target! [pos = " + i + "]");
        }
        Frame frame2 = this.frames[n];
        boolean b = false;
        if (frame2 == null) {
            final Frame[] frames = this.frames;
            final Frame copy = frame.copy();
            frames[n] = copy;
            frame2 = copy;
            b = true;
        }
        else {
            for (int j = 0; j < frame.localsLength(); ++j) {
                if (!subroutine.isAccessed(j)) {
                    final Type local = frame2.getLocal(j);
                    final Type local2 = frame.getLocal(j);
                    if (local == null) {
                        frame2.setLocal(j, local2);
                        b = true;
                    }
                    else {
                        final Type merge = local.merge(local2);
                        frame2.setLocal(j, merge);
                        if (!merge.equals(local) || merge.popChanged()) {
                            b = true;
                        }
                    }
                }
            }
        }
        if (!frame2.isJsrMerged()) {
            frame2.setJsrMerged(true);
            b = true;
        }
        if (b && frame2.isRetMerged()) {
            intQueue.add(n);
        }
    }
    
    private void mergeLookupSwitch(final IntQueue intQueue, final int n, final CodeIterator codeIterator, final Frame frame) {
        int i = (n & 0xFFFFFFFC) + 4;
        this.merge(intQueue, frame, n + codeIterator.s32bitAt(i));
        i += 4;
        final int n2 = codeIterator.s32bitAt(i) * 8;
        for (i += 4, final int n3 = n2 + i, i += 4; i < n3; i += 8) {
            this.merge(intQueue, frame, codeIterator.s32bitAt(i) + n);
        }
    }
    
    private void mergeRet(final IntQueue intQueue, final CodeIterator codeIterator, final int i, final Frame frame, final Subroutine subroutine) {
        if (subroutine == null) {
            throw new BadBytecode("Ret on no subroutine! [pos = " + i + "]");
        }
        final Iterator<Integer> iterator = subroutine.callers().iterator();
        while (iterator.hasNext()) {
            final int next = this.getNext(codeIterator, iterator.next(), i);
            Frame frame2 = this.frames[next];
            boolean mergeStack;
            if (frame2 == null) {
                final Frame[] frames = this.frames;
                final int n = next;
                final Frame copyStack = frame.copyStack();
                frames[n] = copyStack;
                frame2 = copyStack;
                mergeStack = true;
            }
            else {
                mergeStack = frame2.mergeStack(frame);
            }
            for (final int intValue : subroutine.accessed()) {
                final Type local = frame2.getLocal(intValue);
                final Type local2 = frame.getLocal(intValue);
                if (local != local2) {
                    frame2.setLocal(intValue, local2);
                    mergeStack = true;
                }
            }
            if (!frame2.isRetMerged()) {
                frame2.setRetMerged(true);
                mergeStack = true;
            }
            if (mergeStack && frame2.isJsrMerged()) {
                intQueue.add(next);
            }
        }
    }
    
    private void mergeTableSwitch(final IntQueue intQueue, final int n, final CodeIterator codeIterator, final Frame frame) {
        int i = (n & 0xFFFFFFFC) + 4;
        this.merge(intQueue, frame, n + codeIterator.s32bitAt(i));
        i += 4;
        final int s32bit = codeIterator.s32bitAt(i);
        i += 4;
        final int n2 = (codeIterator.s32bitAt(i) - s32bit + 1) * 4;
        for (i += 4; i < n2 + i; i += 4) {
            this.merge(intQueue, frame, codeIterator.s32bitAt(i) + n);
        }
    }
    
    private Type zeroExtend(final Type type) {
        if (type == Type.SHORT || type == Type.BYTE || type == Type.CHAR || type == Type.BOOLEAN) {
            return Type.INTEGER;
        }
        return type;
    }
    
    private static class ExceptionInfo
    {
        private int end;
        private int handler;
        private int start;
        private Type type;
        
        private ExceptionInfo(final int start, final int end, final int handler, final Type type) {
            this.start = start;
            this.end = end;
            this.handler = handler;
            this.type = type;
        }
    }
}
