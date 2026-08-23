// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

final class AnnotationWriter extends AnnotationVisitor
{
    private final SymbolTable symbolTable;
    private final boolean useNamedValues;
    private final ByteVector annotation;
    private final int numElementValuePairsOffset;
    private int numElementValuePairs;
    private final AnnotationWriter previousAnnotation;
    private AnnotationWriter nextAnnotation;
    
    AnnotationWriter(final SymbolTable symbolTable, final boolean useNamedValues, final ByteVector annotation, final AnnotationWriter previousAnnotation) {
        super(589824);
        this.symbolTable = symbolTable;
        this.useNamedValues = useNamedValues;
        this.annotation = annotation;
        this.numElementValuePairsOffset = ((annotation.length == 0) ? -1 : (annotation.length - 2));
        this.previousAnnotation = previousAnnotation;
        if (previousAnnotation != null) {
            previousAnnotation.nextAnnotation = this;
        }
    }
    
    static AnnotationWriter create(final SymbolTable symbolTable, final String s, final AnnotationWriter annotationWriter) {
        final ByteVector byteVector = new ByteVector();
        byteVector.putShort(symbolTable.addConstantUtf8(s)).putShort(0);
        return new AnnotationWriter(symbolTable, true, byteVector, annotationWriter);
    }
    
    static AnnotationWriter create(final SymbolTable symbolTable, final int n, final TypePath typePath, final String s, final AnnotationWriter annotationWriter) {
        final ByteVector byteVector = new ByteVector();
        TypeReference.putTarget(n, byteVector);
        TypePath.put(typePath, byteVector);
        byteVector.putShort(symbolTable.addConstantUtf8(s)).putShort(0);
        return new AnnotationWriter(symbolTable, true, byteVector, annotationWriter);
    }
    
    @Override
    public void visit(final String s, final Object o) {
        ++this.numElementValuePairs;
        if (this.useNamedValues) {
            this.annotation.putShort(this.symbolTable.addConstantUtf8(s));
        }
        if (o instanceof String) {
            this.annotation.put12(115, this.symbolTable.addConstantUtf8((String)o));
        }
        else if (o instanceof Byte) {
            this.annotation.put12(66, this.symbolTable.addConstantInteger((byte)o).index);
        }
        else if (o instanceof Boolean) {
            this.annotation.put12(90, this.symbolTable.addConstantInteger(((boolean)o) ? 1 : 0).index);
        }
        else if (o instanceof Character) {
            this.annotation.put12(67, this.symbolTable.addConstantInteger((char)o).index);
        }
        else if (o instanceof Short) {
            this.annotation.put12(83, this.symbolTable.addConstantInteger((short)o).index);
        }
        else if (o instanceof Type) {
            this.annotation.put12(99, this.symbolTable.addConstantUtf8(((Type)o).getDescriptor()));
        }
        else if (o instanceof byte[]) {
            final byte[] array = (byte[])o;
            this.annotation.put12(91, array.length);
            final byte[] array2 = array;
            for (int length = array2.length, i = 0; i < length; ++i) {
                this.annotation.put12(66, this.symbolTable.addConstantInteger(array2[i]).index);
            }
        }
        else if (o instanceof boolean[]) {
            final boolean[] array3 = (boolean[])o;
            this.annotation.put12(91, array3.length);
            final boolean[] array4 = array3;
            for (int length2 = array4.length, j = 0; j < length2; ++j) {
                this.annotation.put12(90, this.symbolTable.addConstantInteger(array4[j] ? 1 : 0).index);
            }
        }
        else if (o instanceof short[]) {
            final short[] array5 = (short[])o;
            this.annotation.put12(91, array5.length);
            final short[] array6 = array5;
            for (int length3 = array6.length, k = 0; k < length3; ++k) {
                this.annotation.put12(83, this.symbolTable.addConstantInteger(array6[k]).index);
            }
        }
        else if (o instanceof char[]) {
            final char[] array7 = (char[])o;
            this.annotation.put12(91, array7.length);
            final char[] array8 = array7;
            for (int length4 = array8.length, l = 0; l < length4; ++l) {
                this.annotation.put12(67, this.symbolTable.addConstantInteger(array8[l]).index);
            }
        }
        else if (o instanceof int[]) {
            final int[] array9 = (int[])o;
            this.annotation.put12(91, array9.length);
            final int[] array10 = array9;
            for (int length5 = array10.length, n = 0; n < length5; ++n) {
                this.annotation.put12(73, this.symbolTable.addConstantInteger(array10[n]).index);
            }
        }
        else if (o instanceof long[]) {
            final long[] array11 = (long[])o;
            this.annotation.put12(91, array11.length);
            final long[] array12 = array11;
            for (int length6 = array12.length, n2 = 0; n2 < length6; ++n2) {
                this.annotation.put12(74, this.symbolTable.addConstantLong(array12[n2]).index);
            }
        }
        else if (o instanceof float[]) {
            final float[] array13 = (float[])o;
            this.annotation.put12(91, array13.length);
            final float[] array14 = array13;
            for (int length7 = array14.length, n3 = 0; n3 < length7; ++n3) {
                this.annotation.put12(70, this.symbolTable.addConstantFloat(array14[n3]).index);
            }
        }
        else if (o instanceof double[]) {
            final double[] array15 = (double[])o;
            this.annotation.put12(91, array15.length);
            final double[] array16 = array15;
            for (int length8 = array16.length, n4 = 0; n4 < length8; ++n4) {
                this.annotation.put12(68, this.symbolTable.addConstantDouble(array16[n4]).index);
            }
        }
        else {
            final Symbol addConstant = this.symbolTable.addConstant(o);
            this.annotation.put12(".s.IFJDCS".charAt(addConstant.tag), addConstant.index);
        }
    }
    
    @Override
    public void visitEnum(final String s, final String s2, final String s3) {
        ++this.numElementValuePairs;
        if (this.useNamedValues) {
            this.annotation.putShort(this.symbolTable.addConstantUtf8(s));
        }
        this.annotation.put12(101, this.symbolTable.addConstantUtf8(s2)).putShort(this.symbolTable.addConstantUtf8(s3));
    }
    
    @Override
    public AnnotationVisitor visitAnnotation(final String s, final String s2) {
        ++this.numElementValuePairs;
        if (this.useNamedValues) {
            this.annotation.putShort(this.symbolTable.addConstantUtf8(s));
        }
        this.annotation.put12(64, this.symbolTable.addConstantUtf8(s2)).putShort(0);
        return new AnnotationWriter(this.symbolTable, true, this.annotation, null);
    }
    
    @Override
    public AnnotationVisitor visitArray(final String s) {
        ++this.numElementValuePairs;
        if (this.useNamedValues) {
            this.annotation.putShort(this.symbolTable.addConstantUtf8(s));
        }
        this.annotation.put12(91, 0);
        return new AnnotationWriter(this.symbolTable, false, this.annotation, null);
    }
    
    @Override
    public void visitEnd() {
        if (this.numElementValuePairsOffset != -1) {
            final byte[] data = this.annotation.data;
            data[this.numElementValuePairsOffset] = (byte)(this.numElementValuePairs >>> 8);
            data[this.numElementValuePairsOffset + 1] = (byte)this.numElementValuePairs;
        }
    }
    
    int computeAnnotationsSize(final String s) {
        if (s != null) {
            this.symbolTable.addConstantUtf8(s);
        }
        int n = 8;
        for (AnnotationWriter previousAnnotation = this; previousAnnotation != null; previousAnnotation = previousAnnotation.previousAnnotation) {
            n += previousAnnotation.annotation.length;
        }
        return n;
    }
    
    static int computeAnnotationsSize(final AnnotationWriter annotationWriter, final AnnotationWriter annotationWriter2, final AnnotationWriter annotationWriter3, final AnnotationWriter annotationWriter4) {
        int n = 0;
        if (annotationWriter != null) {
            n += annotationWriter.computeAnnotationsSize("RuntimeVisibleAnnotations");
        }
        if (annotationWriter2 != null) {
            n += annotationWriter2.computeAnnotationsSize("RuntimeInvisibleAnnotations");
        }
        if (annotationWriter3 != null) {
            n += annotationWriter3.computeAnnotationsSize("RuntimeVisibleTypeAnnotations");
        }
        if (annotationWriter4 != null) {
            n += annotationWriter4.computeAnnotationsSize("RuntimeInvisibleTypeAnnotations");
        }
        return n;
    }
    
    void putAnnotations(final int n, final ByteVector byteVector) {
        int n2 = 2;
        int n3 = 0;
        AnnotationWriter previousAnnotation = this;
        AnnotationWriter annotationWriter = null;
        while (previousAnnotation != null) {
            previousAnnotation.visitEnd();
            n2 += previousAnnotation.annotation.length;
            ++n3;
            annotationWriter = previousAnnotation;
            previousAnnotation = previousAnnotation.previousAnnotation;
        }
        byteVector.putShort(n);
        byteVector.putInt(n2);
        byteVector.putShort(n3);
        for (AnnotationWriter nextAnnotation = annotationWriter; nextAnnotation != null; nextAnnotation = nextAnnotation.nextAnnotation) {
            byteVector.putByteArray(nextAnnotation.annotation.data, 0, nextAnnotation.annotation.length);
        }
    }
    
    static void putAnnotations(final SymbolTable symbolTable, final AnnotationWriter annotationWriter, final AnnotationWriter annotationWriter2, final AnnotationWriter annotationWriter3, final AnnotationWriter annotationWriter4, final ByteVector byteVector) {
        if (annotationWriter != null) {
            annotationWriter.putAnnotations(symbolTable.addConstantUtf8("RuntimeVisibleAnnotations"), byteVector);
        }
        if (annotationWriter2 != null) {
            annotationWriter2.putAnnotations(symbolTable.addConstantUtf8("RuntimeInvisibleAnnotations"), byteVector);
        }
        if (annotationWriter3 != null) {
            annotationWriter3.putAnnotations(symbolTable.addConstantUtf8("RuntimeVisibleTypeAnnotations"), byteVector);
        }
        if (annotationWriter4 != null) {
            annotationWriter4.putAnnotations(symbolTable.addConstantUtf8("RuntimeInvisibleTypeAnnotations"), byteVector);
        }
    }
    
    static int computeParameterAnnotationsSize(final String s, final AnnotationWriter[] array, final int n) {
        int n2 = 7 + 2 * n;
        for (final AnnotationWriter annotationWriter : array) {
            n2 += ((annotationWriter == null) ? 0 : (annotationWriter.computeAnnotationsSize(s) - 8));
        }
        return n2;
    }
    
    static void putParameterAnnotations(final int n, final AnnotationWriter[] array, final int n2, final ByteVector byteVector) {
        int n3 = 1 + 2 * n2;
        for (final AnnotationWriter annotationWriter : array) {
            n3 += ((annotationWriter == null) ? 0 : (annotationWriter.computeAnnotationsSize(null) - 8));
        }
        byteVector.putShort(n);
        byteVector.putInt(n3);
        byteVector.putByte(n2);
        for (AnnotationWriter previousAnnotation : array) {
            AnnotationWriter annotationWriter2 = null;
            int n4 = 0;
            while (previousAnnotation != null) {
                previousAnnotation.visitEnd();
                ++n4;
                annotationWriter2 = previousAnnotation;
                previousAnnotation = previousAnnotation.previousAnnotation;
            }
            byteVector.putShort(n4);
            for (AnnotationWriter nextAnnotation = annotationWriter2; nextAnnotation != null; nextAnnotation = nextAnnotation.nextAnnotation) {
                byteVector.putByteArray(nextAnnotation.annotation.data, 0, nextAnnotation.annotation.length);
            }
        }
    }
}
