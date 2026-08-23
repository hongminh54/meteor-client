// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

final class FieldWriter extends FieldVisitor
{
    private final SymbolTable symbolTable;
    private final int accessFlags;
    private final int nameIndex;
    private final int descriptorIndex;
    private int signatureIndex;
    private int constantValueIndex;
    private AnnotationWriter lastRuntimeVisibleAnnotation;
    private AnnotationWriter lastRuntimeInvisibleAnnotation;
    private AnnotationWriter lastRuntimeVisibleTypeAnnotation;
    private AnnotationWriter lastRuntimeInvisibleTypeAnnotation;
    private Attribute firstAttribute;
    
    FieldWriter(final SymbolTable symbolTable, final int accessFlags, final String s, final String s2, final String s3, final Object o) {
        super(589824);
        this.symbolTable = symbolTable;
        this.accessFlags = accessFlags;
        this.nameIndex = symbolTable.addConstantUtf8(s);
        this.descriptorIndex = symbolTable.addConstantUtf8(s2);
        if (s3 != null) {
            this.signatureIndex = symbolTable.addConstantUtf8(s3);
        }
        if (o != null) {
            this.constantValueIndex = symbolTable.addConstant(o).index;
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
    
    int computeFieldInfoSize() {
        int n = 8;
        if (this.constantValueIndex != 0) {
            this.symbolTable.addConstantUtf8("ConstantValue");
            n += 8;
        }
        int n2 = n + Attribute.computeAttributesSize(this.symbolTable, this.accessFlags, this.signatureIndex) + AnnotationWriter.computeAnnotationsSize(this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation);
        if (this.firstAttribute != null) {
            n2 += this.firstAttribute.computeAttributesSize(this.symbolTable);
        }
        return n2;
    }
    
    void putFieldInfo(final ByteVector byteVector) {
        final boolean b = this.symbolTable.getMajorVersion() < 49;
        byteVector.putShort(this.accessFlags & ~(b ? 4096 : 0)).putShort(this.nameIndex).putShort(this.descriptorIndex);
        int n = 0;
        if (this.constantValueIndex != 0) {
            ++n;
        }
        if ((this.accessFlags & 0x1000) != 0x0 && b) {
            ++n;
        }
        if (this.signatureIndex != 0) {
            ++n;
        }
        if ((this.accessFlags & 0x20000) != 0x0) {
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
        if (this.constantValueIndex != 0) {
            byteVector.putShort(this.symbolTable.addConstantUtf8("ConstantValue")).putInt(2).putShort(this.constantValueIndex);
        }
        Attribute.putAttributes(this.symbolTable, this.accessFlags, this.signatureIndex, byteVector);
        AnnotationWriter.putAnnotations(this.symbolTable, this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation, byteVector);
        if (this.firstAttribute != null) {
            this.firstAttribute.putAttributes(this.symbolTable, byteVector);
        }
    }
    
    final void collectAttributePrototypes(final Attribute.Set set) {
        set.addAttributes(this.firstAttribute);
    }
}
