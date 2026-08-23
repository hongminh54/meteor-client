// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode.analysis;

import dev.imanity.antixray.libs.javassist.bytecode.CodeIterator;
import dev.imanity.antixray.libs.javassist.bytecode.CodeAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;
import dev.imanity.antixray.libs.javassist.bytecode.InstructionPrinter;
import dev.imanity.antixray.libs.javassist.bytecode.BadBytecode;
import dev.imanity.antixray.libs.javassist.NotFoundException;
import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import dev.imanity.antixray.libs.javassist.Modifier;
import dev.imanity.antixray.libs.javassist.CtMethod;
import dev.imanity.antixray.libs.javassist.CtClass;
import java.io.PrintStream;

public final class FramePrinter
{
    private final PrintStream stream;
    
    public FramePrinter(final PrintStream stream) {
        this.stream = stream;
    }
    
    public static void print(final CtClass ctClass, final PrintStream printStream) {
        new FramePrinter(printStream).print(ctClass);
    }
    
    public void print(final CtClass ctClass) {
        final CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; ++i) {
            this.print(declaredMethods[i]);
        }
    }
    
    private String getMethodString(final CtMethod ctMethod) {
        try {
            return Modifier.toString(ctMethod.getModifiers()) + " " + ctMethod.getReturnType().getName() + " " + ctMethod.getName() + Descriptor.toString(ctMethod.getSignature()) + ";";
        }
        catch (final NotFoundException cause) {
            throw new RuntimeException(cause);
        }
    }
    
    public void print(final CtMethod ctMethod) {
        this.stream.println("\n" + this.getMethodString(ctMethod));
        final MethodInfo methodInfo2 = ctMethod.getMethodInfo2();
        final ConstPool constPool = methodInfo2.getConstPool();
        final CodeAttribute codeAttribute = methodInfo2.getCodeAttribute();
        if (codeAttribute == null) {
            return;
        }
        Frame[] analyze;
        try {
            analyze = new Analyzer().analyze(ctMethod.getDeclaringClass(), methodInfo2);
        }
        catch (final BadBytecode cause) {
            throw new RuntimeException(cause);
        }
        final int length = String.valueOf(codeAttribute.getCodeLength()).length();
        final CodeIterator iterator = codeAttribute.iterator();
        while (iterator.hasNext()) {
            int next;
            try {
                next = iterator.next();
            }
            catch (final BadBytecode cause2) {
                throw new RuntimeException(cause2);
            }
            this.stream.println(next + ": " + InstructionPrinter.instructionString(iterator, next, constPool));
            this.addSpacing(length + 3);
            final Frame frame = analyze[next];
            if (frame == null) {
                this.stream.println("--DEAD CODE--");
            }
            else {
                this.printStack(frame);
                this.addSpacing(length + 3);
                this.printLocals(frame);
            }
        }
    }
    
    private void printStack(final Frame frame) {
        this.stream.print("stack [");
        for (int topIndex = frame.getTopIndex(), i = 0; i <= topIndex; ++i) {
            if (i > 0) {
                this.stream.print(", ");
            }
            this.stream.print(frame.getStack(i));
        }
        this.stream.println("]");
    }
    
    private void printLocals(final Frame frame) {
        this.stream.print("locals [");
        for (int localsLength = frame.localsLength(), i = 0; i < localsLength; ++i) {
            if (i > 0) {
                this.stream.print(", ");
            }
            final Type local = frame.getLocal(i);
            this.stream.print((local == null) ? "empty" : local.toString());
        }
        this.stream.println("]");
    }
    
    private void addSpacing(int n) {
        while (n-- > 0) {
            this.stream.print(' ');
        }
    }
}
