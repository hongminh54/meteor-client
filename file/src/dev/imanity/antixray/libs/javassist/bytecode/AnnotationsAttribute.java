// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import dev.imanity.antixray.libs.javassist.bytecode.annotation.ArrayMemberValue;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.AnnotationMemberValue;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.ClassMemberValue;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.EnumMemberValue;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.StringMemberValue;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.BooleanMemberValue;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.ShortMemberValue;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.LongMemberValue;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.IntegerMemberValue;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.FloatMemberValue;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.DoubleMemberValue;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.CharMemberValue;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.ByteMemberValue;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.MemberValue;
import java.util.HashMap;
import java.io.IOException;
import java.io.OutputStream;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.AnnotationsWriter;
import java.io.ByteArrayOutputStream;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.Annotation;
import java.util.Map;
import java.io.DataInputStream;

public class AnnotationsAttribute extends AttributeInfo
{
    public static final String visibleTag = "RuntimeVisibleAnnotations";
    public static final String invisibleTag = "RuntimeInvisibleAnnotations";
    
    public AnnotationsAttribute(final ConstPool constPool, final String s, final byte[] array) {
        super(constPool, s, array);
    }
    
    public AnnotationsAttribute(final ConstPool constPool, final String s) {
        this(constPool, s, new byte[] { 0, 0 });
    }
    
    AnnotationsAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) {
        super(constPool, n, dataInputStream);
    }
    
    public int numAnnotations() {
        return ByteArray.readU16bit(this.info, 0);
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map<String, String> map) {
        final Copier copier = new Copier(this.info, this.constPool, constPool, map);
        try {
            copier.annotationArray();
            return new AnnotationsAttribute(constPool, this.getName(), copier.close());
        }
        catch (final Exception cause) {
            throw new RuntimeException(cause);
        }
    }
    
    public Annotation getAnnotation(final String anObject) {
        final Annotation[] annotations = this.getAnnotations();
        for (int i = 0; i < annotations.length; ++i) {
            if (annotations[i].getTypeName().equals(anObject)) {
                return annotations[i];
            }
        }
        return null;
    }
    
    public void addAnnotation(final Annotation annotation) {
        final String typeName = annotation.getTypeName();
        final Annotation[] annotations = this.getAnnotations();
        for (int i = 0; i < annotations.length; ++i) {
            if (annotations[i].getTypeName().equals(typeName)) {
                annotations[i] = annotation;
                this.setAnnotations(annotations);
                return;
            }
        }
        final Annotation[] annotations2 = new Annotation[annotations.length + 1];
        System.arraycopy(annotations, 0, annotations2, 0, annotations.length);
        annotations2[annotations.length] = annotation;
        this.setAnnotations(annotations2);
    }
    
    public boolean removeAnnotation(final String anObject) {
        final Annotation[] annotations = this.getAnnotations();
        for (int i = 0; i < annotations.length; ++i) {
            if (annotations[i].getTypeName().equals(anObject)) {
                final Annotation[] annotations2 = new Annotation[annotations.length - 1];
                System.arraycopy(annotations, 0, annotations2, 0, i);
                if (i < annotations.length - 1) {
                    System.arraycopy(annotations, i + 1, annotations2, i, annotations.length - i - 1);
                }
                this.setAnnotations(annotations2);
                return true;
            }
        }
        return false;
    }
    
    public Annotation[] getAnnotations() {
        try {
            return new Parser(this.info, this.constPool).parseAnnotations();
        }
        catch (final Exception cause) {
            throw new RuntimeException(cause);
        }
    }
    
    public void setAnnotations(final Annotation[] array) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final AnnotationsWriter annotationsWriter = new AnnotationsWriter(byteArrayOutputStream, this.constPool);
        try {
            final int length = array.length;
            annotationsWriter.numAnnotations(length);
            for (int i = 0; i < length; ++i) {
                array[i].write(annotationsWriter);
            }
            annotationsWriter.close();
        }
        catch (final IOException cause) {
            throw new RuntimeException(cause);
        }
        this.set(byteArrayOutputStream.toByteArray());
    }
    
    public void setAnnotation(final Annotation annotation) {
        this.setAnnotations(new Annotation[] { annotation });
    }
    
    @Override
    void renameClass(final String s, final String s2) {
        final HashMap hashMap = new HashMap();
        hashMap.put(s, s2);
        this.renameClass(hashMap);
    }
    
    @Override
    void renameClass(final Map<String, String> map) {
        final Renamer renamer = new Renamer(this.info, this.getConstPool(), map);
        try {
            renamer.annotationArray();
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
        final Annotation[] annotations = this.getAnnotations();
        final StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < annotations.length) {
            sb.append(annotations[i++].toString());
            if (i != annotations.length) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
    
    static class Walker
    {
        byte[] info;
        
        Walker(final byte[] info) {
            this.info = info;
        }
        
        final void parameters() {
            this.parameters(this.info[0] & 0xFF, 1);
        }
        
        void parameters(final int n, int annotationArray) {
            for (int i = 0; i < n; ++i) {
                annotationArray = this.annotationArray(annotationArray);
            }
        }
        
        final void annotationArray() {
            this.annotationArray(0);
        }
        
        final int annotationArray(final int n) {
            return this.annotationArray(n + 2, ByteArray.readU16bit(this.info, n));
        }
        
        int annotationArray(int annotation, final int n) {
            for (int i = 0; i < n; ++i) {
                annotation = this.annotation(annotation);
            }
            return annotation;
        }
        
        final int annotation(final int n) {
            return this.annotation(n + 4, ByteArray.readU16bit(this.info, n), ByteArray.readU16bit(this.info, n + 2));
        }
        
        int annotation(int memberValuePair, final int n, final int n2) {
            for (int i = 0; i < n2; ++i) {
                memberValuePair = this.memberValuePair(memberValuePair);
            }
            return memberValuePair;
        }
        
        final int memberValuePair(final int n) {
            return this.memberValuePair(n + 2, ByteArray.readU16bit(this.info, n));
        }
        
        int memberValuePair(final int n, final int n2) {
            return this.memberValue(n);
        }
        
        final int memberValue(final int n) {
            final int n2 = this.info[n] & 0xFF;
            if (n2 == 101) {
                this.enumMemberValue(n, ByteArray.readU16bit(this.info, n + 1), ByteArray.readU16bit(this.info, n + 3));
                return n + 5;
            }
            if (n2 == 99) {
                this.classMemberValue(n, ByteArray.readU16bit(this.info, n + 1));
                return n + 3;
            }
            if (n2 == 64) {
                return this.annotationMemberValue(n + 1);
            }
            if (n2 == 91) {
                return this.arrayMemberValue(n + 3, ByteArray.readU16bit(this.info, n + 1));
            }
            this.constValueMember(n2, ByteArray.readU16bit(this.info, n + 1));
            return n + 3;
        }
        
        void constValueMember(final int n, final int n2) {
        }
        
        void enumMemberValue(final int n, final int n2, final int n3) {
        }
        
        void classMemberValue(final int n, final int n2) {
        }
        
        int annotationMemberValue(final int n) {
            return this.annotation(n);
        }
        
        int arrayMemberValue(int memberValue, final int n) {
            for (int i = 0; i < n; ++i) {
                memberValue = this.memberValue(memberValue);
            }
            return memberValue;
        }
    }
    
    static class Renamer extends Walker
    {
        ConstPool cpool;
        Map<String, String> classnames;
        
        Renamer(final byte[] array, final ConstPool cpool, final Map<String, String> classnames) {
            super(array);
            this.cpool = cpool;
            this.classnames = classnames;
        }
        
        @Override
        int annotation(final int n, final int n2, final int n3) {
            this.renameType(n - 4, n2);
            return super.annotation(n, n2, n3);
        }
        
        @Override
        void enumMemberValue(final int n, final int n2, final int n3) {
            this.renameType(n + 1, n2);
            super.enumMemberValue(n, n2, n3);
        }
        
        @Override
        void classMemberValue(final int n, final int n2) {
            this.renameType(n + 1, n2);
            super.classMemberValue(n, n2);
        }
        
        private void renameType(final int n, final int n2) {
            final String utf8Info = this.cpool.getUtf8Info(n2);
            final String rename = Descriptor.rename(utf8Info, this.classnames);
            if (!utf8Info.equals(rename)) {
                ByteArray.write16bit(this.cpool.addUtf8Info(rename), this.info, n);
            }
        }
    }
    
    static class Copier extends Walker
    {
        ByteArrayOutputStream output;
        AnnotationsWriter writer;
        ConstPool srcPool;
        ConstPool destPool;
        Map<String, String> classnames;
        
        Copier(final byte[] array, final ConstPool constPool, final ConstPool constPool2, final Map<String, String> map) {
            this(array, constPool, constPool2, map, true);
        }
        
        Copier(final byte[] array, final ConstPool srcPool, final ConstPool destPool, final Map<String, String> classnames, final boolean b) {
            super(array);
            this.output = new ByteArrayOutputStream();
            if (b) {
                this.writer = new AnnotationsWriter(this.output, destPool);
            }
            this.srcPool = srcPool;
            this.destPool = destPool;
            this.classnames = classnames;
        }
        
        byte[] close() {
            this.writer.close();
            return this.output.toByteArray();
        }
        
        @Override
        void parameters(final int n, final int n2) {
            this.writer.numParameters(n);
            super.parameters(n, n2);
        }
        
        @Override
        int annotationArray(final int n, final int n2) {
            this.writer.numAnnotations(n2);
            return super.annotationArray(n, n2);
        }
        
        @Override
        int annotation(final int n, final int n2, final int n3) {
            this.writer.annotation(this.copyType(n2), n3);
            return super.annotation(n, n2, n3);
        }
        
        @Override
        int memberValuePair(final int n, final int n2) {
            this.writer.memberValuePair(this.copy(n2));
            return super.memberValuePair(n, n2);
        }
        
        @Override
        void constValueMember(final int n, final int n2) {
            this.writer.constValueIndex(n, this.copy(n2));
            super.constValueMember(n, n2);
        }
        
        @Override
        void enumMemberValue(final int n, final int n2, final int n3) {
            this.writer.enumConstValue(this.copyType(n2), this.copy(n3));
            super.enumMemberValue(n, n2, n3);
        }
        
        @Override
        void classMemberValue(final int n, final int n2) {
            this.writer.classInfoIndex(this.copyType(n2));
            super.classMemberValue(n, n2);
        }
        
        @Override
        int annotationMemberValue(final int n) {
            this.writer.annotationValue();
            return super.annotationMemberValue(n);
        }
        
        @Override
        int arrayMemberValue(final int n, final int n2) {
            this.writer.arrayValue(n2);
            return super.arrayMemberValue(n, n2);
        }
        
        int copy(final int n) {
            return this.srcPool.copy(n, this.destPool, this.classnames);
        }
        
        int copyType(final int n) {
            return this.destPool.addUtf8Info(Descriptor.rename(this.srcPool.getUtf8Info(n), this.classnames));
        }
    }
    
    static class Parser extends Walker
    {
        ConstPool pool;
        Annotation[][] allParams;
        Annotation[] allAnno;
        Annotation currentAnno;
        MemberValue currentMember;
        
        Parser(final byte[] array, final ConstPool pool) {
            super(array);
            this.pool = pool;
        }
        
        Annotation[][] parseParameters() {
            this.parameters();
            return this.allParams;
        }
        
        Annotation[] parseAnnotations() {
            this.annotationArray();
            return this.allAnno;
        }
        
        MemberValue parseMemberValue() {
            this.memberValue(0);
            return this.currentMember;
        }
        
        @Override
        void parameters(final int n, int annotationArray) {
            final Annotation[][] allParams = new Annotation[n][];
            for (int i = 0; i < n; ++i) {
                annotationArray = this.annotationArray(annotationArray);
                allParams[i] = this.allAnno;
            }
            this.allParams = allParams;
        }
        
        @Override
        int annotationArray(int annotation, final int n) {
            final Annotation[] allAnno = new Annotation[n];
            for (int i = 0; i < n; ++i) {
                annotation = this.annotation(annotation);
                allAnno[i] = this.currentAnno;
            }
            this.allAnno = allAnno;
            return annotation;
        }
        
        @Override
        int annotation(final int n, final int n2, final int n3) {
            this.currentAnno = new Annotation(n2, this.pool);
            return super.annotation(n, n2, n3);
        }
        
        @Override
        int memberValuePair(int memberValuePair, final int n) {
            memberValuePair = super.memberValuePair(memberValuePair, n);
            this.currentAnno.addMemberValue(n, this.currentMember);
            return memberValuePair;
        }
        
        @Override
        void constValueMember(final int i, final int n) {
            final ConstPool pool = this.pool;
            MemberValue currentMember = null;
            switch (i) {
                case 66: {
                    currentMember = new ByteMemberValue(n, pool);
                    break;
                }
                case 67: {
                    currentMember = new CharMemberValue(n, pool);
                    break;
                }
                case 68: {
                    currentMember = new DoubleMemberValue(n, pool);
                    break;
                }
                case 70: {
                    currentMember = new FloatMemberValue(n, pool);
                    break;
                }
                case 73: {
                    currentMember = new IntegerMemberValue(n, pool);
                    break;
                }
                case 74: {
                    currentMember = new LongMemberValue(n, pool);
                    break;
                }
                case 83: {
                    currentMember = new ShortMemberValue(n, pool);
                    break;
                }
                case 90: {
                    currentMember = new BooleanMemberValue(n, pool);
                    break;
                }
                case 115: {
                    currentMember = new StringMemberValue(n, pool);
                    break;
                }
                default: {
                    throw new RuntimeException("unknown tag:" + i);
                }
            }
            this.currentMember = currentMember;
            super.constValueMember(i, n);
        }
        
        @Override
        void enumMemberValue(final int n, final int n2, final int n3) {
            this.currentMember = new EnumMemberValue(n2, n3, this.pool);
            super.enumMemberValue(n, n2, n3);
        }
        
        @Override
        void classMemberValue(final int n, final int n2) {
            this.currentMember = new ClassMemberValue(n2, this.pool);
            super.classMemberValue(n, n2);
        }
        
        @Override
        int annotationMemberValue(int annotationMemberValue) {
            final Annotation currentAnno = this.currentAnno;
            annotationMemberValue = super.annotationMemberValue(annotationMemberValue);
            this.currentMember = new AnnotationMemberValue(this.currentAnno, this.pool);
            this.currentAnno = currentAnno;
            return annotationMemberValue;
        }
        
        @Override
        int arrayMemberValue(int memberValue, final int n) {
            final ArrayMemberValue currentMember = new ArrayMemberValue(this.pool);
            final MemberValue[] value = new MemberValue[n];
            for (int i = 0; i < n; ++i) {
                memberValue = this.memberValue(memberValue);
                value[i] = this.currentMember;
            }
            currentMember.setValue(value);
            this.currentMember = currentMember;
            return memberValue;
        }
    }
}
