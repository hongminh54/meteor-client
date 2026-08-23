// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.convert;

import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;
import dev.imanity.antixray.libs.javassist.bytecode.CodeIterator;
import dev.imanity.antixray.libs.javassist.CtMethod;

public class TransformCallToStatic extends TransformCall
{
    public TransformCallToStatic(final Transformer transformer, final CtMethod ctMethod, final CtMethod ctMethod2) {
        super(transformer, ctMethod, ctMethod2);
        this.methodDescriptor = ctMethod.getMethodInfo2().getDescriptor();
    }
    
    @Override
    protected int match(final int n, final int n2, final CodeIterator codeIterator, final int n3, final ConstPool constPool) {
        if (this.newIndex == 0) {
            this.newIndex = constPool.addMethodrefInfo(constPool.addClassInfo(this.newClassname), constPool.addNameAndTypeInfo(this.newMethodname, Descriptor.insertParameter(this.classname, this.methodDescriptor)));
            this.constPool = constPool;
        }
        codeIterator.writeByte(184, n2);
        codeIterator.write16bit(this.newIndex, n2 + 1);
        return n2;
    }
}
