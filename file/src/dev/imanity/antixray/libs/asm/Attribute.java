// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

public class Attribute
{
    public final String type;
    private byte[] content;
    Attribute nextAttribute;
    
    protected Attribute(final String type) {
        this.type = type;
    }
    
    public boolean isUnknown() {
        return true;
    }
    
    public boolean isCodeAttribute() {
        return false;
    }
    
    protected Label[] getLabels() {
        return new Label[0];
    }
    
    protected Attribute read(final ClassReader classReader, final int n, final int n2, final char[] array, final int n3, final Label[] array2) {
        final Attribute attribute = new Attribute(this.type);
        attribute.content = new byte[n2];
        System.arraycopy(classReader.classFileBuffer, n, attribute.content, 0, n2);
        return attribute;
    }
    
    protected ByteVector write(final ClassWriter classWriter, final byte[] array, final int n, final int n2, final int n3) {
        return new ByteVector(this.content);
    }
    
    final int getAttributeCount() {
        int n = 0;
        for (Attribute nextAttribute = this; nextAttribute != null; nextAttribute = nextAttribute.nextAttribute) {
            ++n;
        }
        return n;
    }
    
    final int computeAttributesSize(final SymbolTable symbolTable) {
        return this.computeAttributesSize(symbolTable, null, 0, -1, -1);
    }
    
    final int computeAttributesSize(final SymbolTable symbolTable, final byte[] array, final int n, final int n2, final int n3) {
        final ClassWriter classWriter = symbolTable.classWriter;
        int n4 = 0;
        for (Attribute nextAttribute = this; nextAttribute != null; nextAttribute = nextAttribute.nextAttribute) {
            symbolTable.addConstantUtf8(nextAttribute.type);
            n4 += 6 + nextAttribute.write(classWriter, array, n, n2, n3).length;
        }
        return n4;
    }
    
    static int computeAttributesSize(final SymbolTable symbolTable, final int n, final int n2) {
        int n3 = 0;
        if ((n & 0x1000) != 0x0 && symbolTable.getMajorVersion() < 49) {
            symbolTable.addConstantUtf8("Synthetic");
            n3 += 6;
        }
        if (n2 != 0) {
            symbolTable.addConstantUtf8("Signature");
            n3 += 8;
        }
        if ((n & 0x20000) != 0x0) {
            symbolTable.addConstantUtf8("Deprecated");
            n3 += 6;
        }
        return n3;
    }
    
    final void putAttributes(final SymbolTable symbolTable, final ByteVector byteVector) {
        this.putAttributes(symbolTable, null, 0, -1, -1, byteVector);
    }
    
    final void putAttributes(final SymbolTable symbolTable, final byte[] array, final int n, final int n2, final int n3, final ByteVector byteVector) {
        final ClassWriter classWriter = symbolTable.classWriter;
        for (Attribute nextAttribute = this; nextAttribute != null; nextAttribute = nextAttribute.nextAttribute) {
            final ByteVector write = nextAttribute.write(classWriter, array, n, n2, n3);
            byteVector.putShort(symbolTable.addConstantUtf8(nextAttribute.type)).putInt(write.length);
            byteVector.putByteArray(write.data, 0, write.length);
        }
    }
    
    static void putAttributes(final SymbolTable symbolTable, final int n, final int n2, final ByteVector byteVector) {
        if ((n & 0x1000) != 0x0 && symbolTable.getMajorVersion() < 49) {
            byteVector.putShort(symbolTable.addConstantUtf8("Synthetic")).putInt(0);
        }
        if (n2 != 0) {
            byteVector.putShort(symbolTable.addConstantUtf8("Signature")).putInt(2).putShort(n2);
        }
        if ((n & 0x20000) != 0x0) {
            byteVector.putShort(symbolTable.addConstantUtf8("Deprecated")).putInt(0);
        }
    }
    
    static final class Set
    {
        private static final int SIZE_INCREMENT = 6;
        private int size;
        private Attribute[] data;
        
        Set() {
            this.data = new Attribute[6];
        }
        
        void addAttributes(final Attribute attribute) {
            for (Attribute nextAttribute = attribute; nextAttribute != null; nextAttribute = nextAttribute.nextAttribute) {
                if (!this.contains(nextAttribute)) {
                    this.add(nextAttribute);
                }
            }
        }
        
        Attribute[] toArray() {
            final Attribute[] array = new Attribute[this.size];
            System.arraycopy(this.data, 0, array, 0, this.size);
            return array;
        }
        
        private boolean contains(final Attribute attribute) {
            for (int i = 0; i < this.size; ++i) {
                if (this.data[i].type.equals(attribute.type)) {
                    return true;
                }
            }
            return false;
        }
        
        private void add(final Attribute attribute) {
            if (this.size >= this.data.length) {
                final Attribute[] data = new Attribute[this.data.length + 6];
                System.arraycopy(this.data, 0, data, 0, this.size);
                this.data = data;
            }
            this.data[this.size++] = attribute;
        }
    }
}
