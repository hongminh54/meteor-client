// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode.annotation;

import dev.imanity.antixray.libs.javassist.bytecode.BadBytecode;
import dev.imanity.antixray.libs.javassist.bytecode.SignatureAttribute;
import java.util.Map;
import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;

public class ClassMemberValue extends MemberValue
{
    int valueIndex;
    
    public ClassMemberValue(final int valueIndex, final ConstPool constPool) {
        super('c', constPool);
        this.valueIndex = valueIndex;
    }
    
    public ClassMemberValue(final String value, final ConstPool constPool) {
        super('c', constPool);
        this.setValue(value);
    }
    
    public ClassMemberValue(final ConstPool constPool) {
        super('c', constPool);
        this.setValue("java.lang.Class");
    }
    
    @Override
    Object getValue(final ClassLoader classLoader, final ClassPool classPool, final Method method) {
        final String value = this.getValue();
        if (value.equals("void")) {
            return Void.TYPE;
        }
        if (value.equals("int")) {
            return Integer.TYPE;
        }
        if (value.equals("byte")) {
            return Byte.TYPE;
        }
        if (value.equals("long")) {
            return Long.TYPE;
        }
        if (value.equals("double")) {
            return Double.TYPE;
        }
        if (value.equals("float")) {
            return Float.TYPE;
        }
        if (value.equals("char")) {
            return Character.TYPE;
        }
        if (value.equals("short")) {
            return Short.TYPE;
        }
        if (value.equals("boolean")) {
            return Boolean.TYPE;
        }
        return MemberValue.loadClass(classLoader, value);
    }
    
    @Override
    Class<?> getType(final ClassLoader classLoader) {
        return MemberValue.loadClass(classLoader, "java.lang.Class");
    }
    
    @Override
    public void renameClass(final String s, final String s2) {
        this.setValue(Descriptor.toClassName(Descriptor.rename(this.cp.getUtf8Info(this.valueIndex), s, s2)));
    }
    
    @Override
    public void renameClass(final Map<String, String> map) {
        this.setValue(Descriptor.toClassName(Descriptor.rename(this.cp.getUtf8Info(this.valueIndex), map)));
    }
    
    public String getValue() {
        final String utf8Info = this.cp.getUtf8Info(this.valueIndex);
        try {
            return SignatureAttribute.toTypeSignature(utf8Info).jvmTypeName();
        }
        catch (final BadBytecode cause) {
            throw new RuntimeException(cause);
        }
    }
    
    public void setValue(final String s) {
        this.valueIndex = this.cp.addUtf8Info(Descriptor.of(s));
    }
    
    @Override
    public String toString() {
        return this.getValue().replace('$', '.') + ".class";
    }
    
    @Override
    public void write(final AnnotationsWriter annotationsWriter) {
        annotationsWriter.classInfoIndex(this.cp.getUtf8Info(this.valueIndex));
    }
    
    @Override
    public void accept(final MemberValueVisitor memberValueVisitor) {
        memberValueVisitor.visitClassMemberValue(this);
    }
}
