// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode.annotation;

import java.lang.reflect.Method;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;

public class BooleanMemberValue extends MemberValue
{
    int valueIndex;
    
    public BooleanMemberValue(final int valueIndex, final ConstPool constPool) {
        super('Z', constPool);
        this.valueIndex = valueIndex;
    }
    
    public BooleanMemberValue(final boolean value, final ConstPool constPool) {
        super('Z', constPool);
        this.setValue(value);
    }
    
    public BooleanMemberValue(final ConstPool constPool) {
        super('Z', constPool);
        this.setValue(false);
    }
    
    @Override
    Object getValue(final ClassLoader classLoader, final ClassPool classPool, final Method method) {
        return this.getValue();
    }
    
    @Override
    Class<?> getType(final ClassLoader classLoader) {
        return Boolean.TYPE;
    }
    
    public boolean getValue() {
        return this.cp.getIntegerInfo(this.valueIndex) != 0;
    }
    
    public void setValue(final boolean b) {
        this.valueIndex = this.cp.addIntegerInfo(b ? 1 : 0);
    }
    
    @Override
    public String toString() {
        return this.getValue() ? "true" : "false";
    }
    
    @Override
    public void write(final AnnotationsWriter annotationsWriter) {
        annotationsWriter.constValueIndex(this.getValue());
    }
    
    @Override
    public void accept(final MemberValueVisitor memberValueVisitor) {
        memberValueVisitor.visitBooleanMemberValue(this);
    }
}
