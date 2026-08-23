// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode.annotation;

import java.util.Map;
import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;

public class EnumMemberValue extends MemberValue
{
    int typeIndex;
    int valueIndex;
    
    public EnumMemberValue(final int typeIndex, final int valueIndex, final ConstPool constPool) {
        super('e', constPool);
        this.typeIndex = typeIndex;
        this.valueIndex = valueIndex;
    }
    
    public EnumMemberValue(final ConstPool constPool) {
        super('e', constPool);
        final int n = 0;
        this.valueIndex = n;
        this.typeIndex = n;
    }
    
    @Override
    Object getValue(final ClassLoader classLoader, final ClassPool classPool, final Method method) {
        try {
            return this.getType(classLoader).getField(this.getValue()).get(null);
        }
        catch (final NoSuchFieldException ex) {
            throw new ClassNotFoundException(this.getType() + "." + this.getValue());
        }
        catch (final IllegalAccessException ex2) {
            throw new ClassNotFoundException(this.getType() + "." + this.getValue());
        }
    }
    
    @Override
    Class<?> getType(final ClassLoader classLoader) {
        return MemberValue.loadClass(classLoader, this.getType());
    }
    
    @Override
    public void renameClass(final String s, final String s2) {
        this.setType(Descriptor.toClassName(Descriptor.rename(this.cp.getUtf8Info(this.typeIndex), s, s2)));
    }
    
    @Override
    public void renameClass(final Map<String, String> map) {
        this.setType(Descriptor.toClassName(Descriptor.rename(this.cp.getUtf8Info(this.typeIndex), map)));
    }
    
    public String getType() {
        return Descriptor.toClassName(this.cp.getUtf8Info(this.typeIndex));
    }
    
    public void setType(final String s) {
        this.typeIndex = this.cp.addUtf8Info(Descriptor.of(s));
    }
    
    public String getValue() {
        return this.cp.getUtf8Info(this.valueIndex);
    }
    
    public void setValue(final String s) {
        this.valueIndex = this.cp.addUtf8Info(s);
    }
    
    @Override
    public String toString() {
        return this.getType() + "." + this.getValue();
    }
    
    @Override
    public void write(final AnnotationsWriter annotationsWriter) {
        annotationsWriter.enumConstValue(this.cp.getUtf8Info(this.typeIndex), this.getValue());
    }
    
    @Override
    public void accept(final MemberValueVisitor memberValueVisitor) {
        memberValueVisitor.visitEnumMemberValue(this);
    }
}
