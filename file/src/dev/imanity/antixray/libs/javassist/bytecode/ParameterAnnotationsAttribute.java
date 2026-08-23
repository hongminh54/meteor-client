// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.util.HashMap;
import java.io.IOException;
import java.io.OutputStream;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.AnnotationsWriter;
import java.io.ByteArrayOutputStream;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.Annotation;
import java.util.Map;
import java.io.DataInputStream;

public class ParameterAnnotationsAttribute extends AttributeInfo
{
    public static final String visibleTag = "RuntimeVisibleParameterAnnotations";
    public static final String invisibleTag = "RuntimeInvisibleParameterAnnotations";
    
    public ParameterAnnotationsAttribute(final ConstPool constPool, final String s, final byte[] array) {
        super(constPool, s, array);
    }
    
    public ParameterAnnotationsAttribute(final ConstPool constPool, final String s) {
        this(constPool, s, new byte[] { 0 });
    }
    
    ParameterAnnotationsAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) {
        super(constPool, n, dataInputStream);
    }
    
    public int numParameters() {
        return this.info[0] & 0xFF;
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map<String, String> map) {
        final AnnotationsAttribute.Copier copier = new AnnotationsAttribute.Copier(this.info, this.constPool, constPool, map);
        try {
            copier.parameters();
            return new ParameterAnnotationsAttribute(constPool, this.getName(), copier.close());
        }
        catch (final Exception ex) {
            throw new RuntimeException(ex.toString());
        }
    }
    
    public Annotation[][] getAnnotations() {
        try {
            return new AnnotationsAttribute.Parser(this.info, this.constPool).parseParameters();
        }
        catch (final Exception ex) {
            throw new RuntimeException(ex.toString());
        }
    }
    
    public void setAnnotations(final Annotation[][] array) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final AnnotationsWriter annotationsWriter = new AnnotationsWriter(byteArrayOutputStream, this.constPool);
        try {
            annotationsWriter.numParameters(array.length);
            for (final Annotation[] array2 : array) {
                annotationsWriter.numAnnotations(array2.length);
                for (int j = 0; j < array2.length; ++j) {
                    array2[j].write(annotationsWriter);
                }
            }
            annotationsWriter.close();
        }
        catch (final IOException cause) {
            throw new RuntimeException(cause);
        }
        this.set(byteArrayOutputStream.toByteArray());
    }
    
    @Override
    void renameClass(final String s, final String s2) {
        final HashMap hashMap = new HashMap();
        hashMap.put(s, s2);
        this.renameClass(hashMap);
    }
    
    @Override
    void renameClass(final Map<String, String> map) {
        final AnnotationsAttribute.Renamer renamer = new AnnotationsAttribute.Renamer(this.info, this.getConstPool(), map);
        try {
            renamer.parameters();
        }
        catch (final Exception cause) {
            throw new RuntimeException(cause);
        }
    }
    
    @Override
    void getRefClasses(final Map<String, String> map) {
        this.renameClass(map);
    }
    
    @Override
    public String toString() {
        final Annotation[][] annotations = this.getAnnotations();
        final StringBuilder sb = new StringBuilder();
        for (final Annotation[] array2 : annotations) {
            for (int length2 = array2.length, j = 0; j < length2; ++j) {
                sb.append(array2[j].toString()).append(' ');
            }
            sb.append(", ");
        }
        return sb.toString().replaceAll(" (?=,)|, $", "");
    }
}
