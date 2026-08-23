// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode.annotation;

import java.lang.reflect.Method;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;

public class IntegerMemberValue extends MemberValue
{
    int valueIndex;
    
    public IntegerMemberValue(final int valueIndex, final ConstPool constPool) {
        super('I', constPool);
        this.valueIndex = valueIndex;
    }
    
    public IntegerMemberValue(final ConstPool constPool, final int value) {
        super('I', constPool);
        this.setValue(value);
    }
    
    public IntegerMemberValue(final ConstPool constPool) {
        super('I', constPool);
        this.setValue(0);
    }
    
    @Override
    Object getValue(final ClassLoader classLoader, final ClassPool classPool, final Method method) {
        return this.getValue();
    }
    
    @Override
    Class<?> getType(final ClassLoader classLoader) {
        return Integer.TYPE;
    }
    
    public int getValue() {
        return this.cp.getIntegerInfo(this.valueIndex);
    }
    
    public void setValue(final int n) {
        this.valueIndex = this.cp.addIntegerInfo(n);
    }
    
    @Override
    public String toString() {
        return Integer.toString(this.getValue());
    }
    
    @Override
    public void write(final AnnotationsWriter annotationsWriter) {
        annotationsWriter.constValueIndex(this.getValue());
    }
    
    @Override
    public void accept(final MemberValueVisitor memberValueVisitor) {
        memberValueVisitor.visitIntegerMemberValue(this);
    }
}
