// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.convert;

import dev.imanity.antixray.libs.javassist.bytecode.CodeIterator;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;
import dev.imanity.antixray.libs.javassist.CtClass;
import dev.imanity.antixray.libs.javassist.bytecode.CodeAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;
import dev.imanity.antixray.libs.javassist.bytecode.Opcode;

public abstract class Transformer implements Opcode
{
    private Transformer next;
    
    public Transformer(final Transformer next) {
        this.next = next;
    }
    
    public Transformer getNext() {
        return this.next;
    }
    
    public void initialize(final ConstPool constPool, final CodeAttribute codeAttribute) {
    }
    
    public void initialize(final ConstPool constPool, final CtClass ctClass, final MethodInfo methodInfo) {
        this.initialize(constPool, methodInfo.getCodeAttribute());
    }
    
    public void clean() {
    }
    
    public abstract int transform(final CtClass p0, final int p1, final CodeIterator p2, final ConstPool p3);
    
    public int extraLocals() {
        return 0;
    }
    
    public int extraStack() {
        return 0;
    }
}
