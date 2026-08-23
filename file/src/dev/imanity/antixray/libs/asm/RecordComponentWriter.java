// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

final class RecordComponentWriter extends RecordComponentVisitor
{
    private final SymbolTable symbolTable;
    private final int nameIndex;
    private final int descriptorIndex;
    private int signatureIndex;
    private AnnotationWriter lastRuntimeVisibleAnnotation;
    private AnnotationWriter lastRuntimeInvisibleAnnotation;
    private AnnotationWriter lastRuntimeVisibleTypeAnnotation;
    private AnnotationWriter lastRuntimeInvisibleTypeAnnotation;
    private Attribute firstAttribute;
    
    RecordComponentWriter(final SymbolTable symbolTable, final String s, final String s2, final String s3) {
        super(589824);
        this.symbolTable = symbolTable;
        this.nameIndex = symbolTable.addConstantUtf8(s);
        this.descriptorIndex = symbolTable.addConstantUtf8(s2);
        if (s3 != null) {
            this.signatureIndex = symbolTable.addConstantUtf8(s3);
        }
    }
    
    @Override
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        if (b) {
            return this.lastRuntimeVisibleAnnotation = AnnotationWriter.create(this.symbolTable, s, this.lastRuntimeVisibleAnnotation);
        }
        return this.lastRuntimeInvisibleAnnotation = AnnotationWriter.create(this.symbolTable, s, this.lastRuntimeInvisibleAnnotation);
    }
    
    @Override
    public AnnotationVisitor visitTypeAnnotation(final int n, final TypePath typePath, final String s, final boolean b) {
        if (b) {
            return this.lastRuntimeVisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, n, typePath, s, this.lastRuntimeVisibleTypeAnnotation);
        }
        return this.lastRuntimeInvisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, n, typePath, s, this.lastRuntimeInvisibleTypeAnnotation);
    }
    
    @Override
    public void visitAttribute(final Attribute firstAttribute) {
        firstAttribute.nextAttribute = this.firstAttribute;
        this.firstAttribute = firstAttribute;
    }
    
    @Override
    public void visitEnd() {
    }
    
    int computeRecordComponentInfoSize() {
        int n = 6 + Attribute.computeAttributesSize(this.symbolTable, 0, this.signatureIndex) + AnnotationWriter.computeAnnotationsSize(this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation);
        if (this.firstAttribute != null) {
            n += this.firstAttribute.computeAttributesSize(this.symbolTable);
        }
        return n;
    }
    
    void putRecordComponentInfo(final ByteVector byteVector) {
        byteVector.putShort(this.nameIndex).putShort(this.descriptorIndex);
        int n = 0;
        if (this.signatureIndex != 0) {
            ++n;
        }
        if (this.lastRuntimeVisibleAnnotation != null) {
            ++n;
        }
        if (this.lastRuntimeInvisibleAnnotation != null) {
            ++n;
        }
        if (this.lastRuntimeVisibleTypeAnnotation != null) {
            ++n;
        }
        if (this.lastRuntimeInvisibleTypeAnnotation != null) {
            ++n;
        }
        if (this.firstAttribute != null) {
            n += this.firstAttribute.getAttributeCount();
        }
        byteVector.putShort(n);
        Attribute.putAttributes(this.symbolTable, 0, this.signatureIndex, byteVector);
        AnnotationWriter.putAnnotations(this.symbolTable, this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation, byteVector);
        if (this.firstAttribute != null) {
            this.firstAttribute.putAttributes(this.symbolTable, byteVector);
        }
    }
    
    final void collectAttributePrototypes(final Attribute.Set set) {
        set.addAttributes(this.firstAttribute);
    }
}
