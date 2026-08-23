// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

final class ModuleWriter extends ModuleVisitor
{
    private final SymbolTable symbolTable;
    private final int moduleNameIndex;
    private final int moduleFlags;
    private final int moduleVersionIndex;
    private int requiresCount;
    private final ByteVector requires;
    private int exportsCount;
    private final ByteVector exports;
    private int opensCount;
    private final ByteVector opens;
    private int usesCount;
    private final ByteVector usesIndex;
    private int providesCount;
    private final ByteVector provides;
    private int packageCount;
    private final ByteVector packageIndex;
    private int mainClassIndex;
    
    ModuleWriter(final SymbolTable symbolTable, final int moduleNameIndex, final int moduleFlags, final int moduleVersionIndex) {
        super(589824);
        this.symbolTable = symbolTable;
        this.moduleNameIndex = moduleNameIndex;
        this.moduleFlags = moduleFlags;
        this.moduleVersionIndex = moduleVersionIndex;
        this.requires = new ByteVector();
        this.exports = new ByteVector();
        this.opens = new ByteVector();
        this.usesIndex = new ByteVector();
        this.provides = new ByteVector();
        this.packageIndex = new ByteVector();
    }
    
    @Override
    public void visitMainClass(final String s) {
        this.mainClassIndex = this.symbolTable.addConstantClass(s).index;
    }
    
    @Override
    public void visitPackage(final String s) {
        this.packageIndex.putShort(this.symbolTable.addConstantPackage(s).index);
        ++this.packageCount;
    }
    
    @Override
    public void visitRequire(final String s, final int n, final String s2) {
        this.requires.putShort(this.symbolTable.addConstantModule(s).index).putShort(n).putShort((s2 == null) ? 0 : this.symbolTable.addConstantUtf8(s2));
        ++this.requiresCount;
    }
    
    @Override
    public void visitExport(final String s, final int n, final String... array) {
        this.exports.putShort(this.symbolTable.addConstantPackage(s).index).putShort(n);
        if (array == null) {
            this.exports.putShort(0);
        }
        else {
            this.exports.putShort(array.length);
            for (int length = array.length, i = 0; i < length; ++i) {
                this.exports.putShort(this.symbolTable.addConstantModule(array[i]).index);
            }
        }
        ++this.exportsCount;
    }
    
    @Override
    public void visitOpen(final String s, final int n, final String... array) {
        this.opens.putShort(this.symbolTable.addConstantPackage(s).index).putShort(n);
        if (array == null) {
            this.opens.putShort(0);
        }
        else {
            this.opens.putShort(array.length);
            for (int length = array.length, i = 0; i < length; ++i) {
                this.opens.putShort(this.symbolTable.addConstantModule(array[i]).index);
            }
        }
        ++this.opensCount;
    }
    
    @Override
    public void visitUse(final String s) {
        this.usesIndex.putShort(this.symbolTable.addConstantClass(s).index);
        ++this.usesCount;
    }
    
    @Override
    public void visitProvide(final String s, final String... array) {
        this.provides.putShort(this.symbolTable.addConstantClass(s).index);
        this.provides.putShort(array.length);
        for (int length = array.length, i = 0; i < length; ++i) {
            this.provides.putShort(this.symbolTable.addConstantClass(array[i]).index);
        }
        ++this.providesCount;
    }
    
    @Override
    public void visitEnd() {
    }
    
    int getAttributeCount() {
        return 1 + ((this.packageCount > 0) ? 1 : 0) + ((this.mainClassIndex > 0) ? 1 : 0);
    }
    
    int computeAttributesSize() {
        this.symbolTable.addConstantUtf8("Module");
        int n = 22 + this.requires.length + this.exports.length + this.opens.length + this.usesIndex.length + this.provides.length;
        if (this.packageCount > 0) {
            this.symbolTable.addConstantUtf8("ModulePackages");
            n += 8 + this.packageIndex.length;
        }
        if (this.mainClassIndex > 0) {
            this.symbolTable.addConstantUtf8("ModuleMainClass");
            n += 8;
        }
        return n;
    }
    
    void putAttributes(final ByteVector byteVector) {
        byteVector.putShort(this.symbolTable.addConstantUtf8("Module")).putInt(16 + this.requires.length + this.exports.length + this.opens.length + this.usesIndex.length + this.provides.length).putShort(this.moduleNameIndex).putShort(this.moduleFlags).putShort(this.moduleVersionIndex).putShort(this.requiresCount).putByteArray(this.requires.data, 0, this.requires.length).putShort(this.exportsCount).putByteArray(this.exports.data, 0, this.exports.length).putShort(this.opensCount).putByteArray(this.opens.data, 0, this.opens.length).putShort(this.usesCount).putByteArray(this.usesIndex.data, 0, this.usesIndex.length).putShort(this.providesCount).putByteArray(this.provides.data, 0, this.provides.length);
        if (this.packageCount > 0) {
            byteVector.putShort(this.symbolTable.addConstantUtf8("ModulePackages")).putInt(2 + this.packageIndex.length).putShort(this.packageCount).putByteArray(this.packageIndex.data, 0, this.packageIndex.length);
        }
        if (this.mainClassIndex > 0) {
            byteVector.putShort(this.symbolTable.addConstantUtf8("ModuleMainClass")).putInt(2).putShort(this.mainClassIndex);
        }
    }
}
