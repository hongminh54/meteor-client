// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.io.IOException;
import java.io.OutputStream;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.AnnotationsWriter;
import java.io.ByteArrayOutputStream;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.MemberValue;
import java.util.Map;
import java.io.DataInputStream;

public class AnnotationDefaultAttribute extends AttributeInfo
{
    public static final String tag = "AnnotationDefault";
    
    public AnnotationDefaultAttribute(final ConstPool constPool, final byte[] array) {
        super(constPool, "AnnotationDefault", array);
    }
    
    public AnnotationDefaultAttribute(final ConstPool constPool) {
        this(constPool, new byte[] { 0, 0 });
    }
    
    AnnotationDefaultAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) {
        super(constPool, n, dataInputStream);
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map<String, String> map) {
        final AnnotationsAttribute.Copier copier = new AnnotationsAttribute.Copier(this.info, this.constPool, constPool, map);
        try {
            copier.memberValue(0);
            return new AnnotationDefaultAttribute(constPool, copier.close());
        }
        catch (final Exception ex) {
            throw new RuntimeException(ex.toString());
        }
    }
    
    @Override
    void renameClass(final String s, final String s2) {
        try {
            final MemberValue defaultValue = this.getDefaultValue();
            defaultValue.renameClass(s, s2);
            this.setDefaultValue(defaultValue);
        }
        catch (final Exception ex) {}
    }
    
    @Override
    void renameClass(final Map<String, String> map) {
        try {
            final MemberValue defaultValue = this.getDefaultValue();
            defaultValue.renameClass(map);
            this.setDefaultValue(defaultValue);
        }
        catch (final Exception ex) {}
    }
    
    public MemberValue getDefaultValue() {
        try {
            return new AnnotationsAttribute.Parser(this.info, this.constPool).parseMemberValue();
        }
        catch (final Exception ex) {
            throw new RuntimeException(ex.toString());
        }
    }
    
    public void setDefaultValue(final MemberValue memberValue) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final AnnotationsWriter annotationsWriter = new AnnotationsWriter(byteArrayOutputStream, this.constPool);
        try {
            memberValue.write(annotationsWriter);
            annotationsWriter.close();
        }
        catch (final IOException cause) {
            throw new RuntimeException(cause);
        }
        this.set(byteArrayOutputStream.toByteArray());
    }
    
    @Override
    public String toString() {
        return this.getDefaultValue().toString();
    }
}
