// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

public class ClassWriter extends ClassVisitor
{
    public static final int COMPUTE_MAXS = 1;
    public static final int COMPUTE_FRAMES = 2;
    private final int flags;
    private int version;
    private final SymbolTable symbolTable;
    private int accessFlags;
    private int thisClass;
    private int superClass;
    private int interfaceCount;
    private int[] interfaces;
    private FieldWriter firstField;
    private FieldWriter lastField;
    private MethodWriter firstMethod;
    private MethodWriter lastMethod;
    private int numberOfInnerClasses;
    private ByteVector innerClasses;
    private int enclosingClassIndex;
    private int enclosingMethodIndex;
    private int signatureIndex;
    private int sourceFileIndex;
    private ByteVector debugExtension;
    private AnnotationWriter lastRuntimeVisibleAnnotation;
    private AnnotationWriter lastRuntimeInvisibleAnnotation;
    private AnnotationWriter lastRuntimeVisibleTypeAnnotation;
    private AnnotationWriter lastRuntimeInvisibleTypeAnnotation;
    private ModuleWriter moduleWriter;
    private int nestHostClassIndex;
    private int numberOfNestMemberClasses;
    private ByteVector nestMemberClasses;
    private int numberOfPermittedSubclasses;
    private ByteVector permittedSubclasses;
    private RecordComponentWriter firstRecordComponent;
    private RecordComponentWriter lastRecordComponent;
    private Attribute firstAttribute;
    private int compute;
    
    public ClassWriter(final int n) {
        this(null, n);
    }
    
    public ClassWriter(final ClassReader classReader, final int flags) {
        super(589824);
        this.flags = flags;
        this.symbolTable = ((classReader == null) ? new SymbolTable(this) : new SymbolTable(this, classReader));
        if ((flags & 0x2) != 0x0) {
            this.compute = 4;
        }
        else if ((flags & 0x1) != 0x0) {
            this.compute = 1;
        }
        else {
            this.compute = 0;
        }
    }
    
    public boolean hasFlags(final int n) {
        return (this.flags & n) == n;
    }
    
    @Override
    public final void visit(final int version, final int accessFlags, final String s, final String s2, final String s3, final String[] array) {
        this.version = version;
        this.accessFlags = accessFlags;
        this.thisClass = this.symbolTable.setMajorVersionAndClassName(version & 0xFFFF, s);
        if (s2 != null) {
            this.signatureIndex = this.symbolTable.addConstantUtf8(s2);
        }
        this.superClass = ((s3 == null) ? 0 : this.symbolTable.addConstantClass(s3).index);
        if (array != null && array.length > 0) {
            this.interfaceCount = array.length;
            this.interfaces = new int[this.interfaceCount];
            for (int i = 0; i < this.interfaceCount; ++i) {
                this.interfaces[i] = this.symbolTable.addConstantClass(array[i]).index;
            }
        }
        if (this.compute == 1 && (version & 0xFFFF) >= 51) {
            this.compute = 2;
        }
    }
    
    @Override
    public final void visitSource(final String s, final String s2) {
        if (s != null) {
            this.sourceFileIndex = this.symbolTable.addConstantUtf8(s);
        }
        if (s2 != null) {
            this.debugExtension = new ByteVector().encodeUtf8(s2, 0, Integer.MAX_VALUE);
        }
    }
    
    @Override
    public final ModuleVisitor visitModule(final String s, final int n, final String s2) {
        return this.moduleWriter = new ModuleWriter(this.symbolTable, this.symbolTable.addConstantModule(s).index, n, (s2 == null) ? 0 : this.symbolTable.addConstantUtf8(s2));
    }
    
    @Override
    public final void visitNestHost(final String s) {
        this.nestHostClassIndex = this.symbolTable.addConstantClass(s).index;
    }
    
    @Override
    public final void visitOuterClass(final String s, final String s2, final String s3) {
        this.enclosingClassIndex = this.symbolTable.addConstantClass(s).index;
        if (s2 != null && s3 != null) {
            this.enclosingMethodIndex = this.symbolTable.addConstantNameAndType(s2, s3);
        }
    }
    
    @Override
    public final AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        if (b) {
            return this.lastRuntimeVisibleAnnotation = AnnotationWriter.create(this.symbolTable, s, this.lastRuntimeVisibleAnnotation);
        }
        return this.lastRuntimeInvisibleAnnotation = AnnotationWriter.create(this.symbolTable, s, this.lastRuntimeInvisibleAnnotation);
    }
    
    @Override
    public final AnnotationVisitor visitTypeAnnotation(final int n, final TypePath typePath, final String s, final boolean b) {
        if (b) {
            return this.lastRuntimeVisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, n, typePath, s, this.lastRuntimeVisibleTypeAnnotation);
        }
        return this.lastRuntimeInvisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, n, typePath, s, this.lastRuntimeInvisibleTypeAnnotation);
    }
    
    @Override
    public final void visitAttribute(final Attribute firstAttribute) {
        firstAttribute.nextAttribute = this.firstAttribute;
        this.firstAttribute = firstAttribute;
    }
    
    @Override
    public final void visitNestMember(final String s) {
        if (this.nestMemberClasses == null) {
            this.nestMemberClasses = new ByteVector();
        }
        ++this.numberOfNestMemberClasses;
        this.nestMemberClasses.putShort(this.symbolTable.addConstantClass(s).index);
    }
    
    @Override
    public final void visitPermittedSubclass(final String s) {
        if (this.permittedSubclasses == null) {
            this.permittedSubclasses = new ByteVector();
        }
        ++this.numberOfPermittedSubclasses;
        this.permittedSubclasses.putShort(this.symbolTable.addConstantClass(s).index);
    }
    
    @Override
    public final void visitInnerClass(final String s, final String s2, final String s3, final int n) {
        if (this.innerClasses == null) {
            this.innerClasses = new ByteVector();
        }
        final Symbol addConstantClass = this.symbolTable.addConstantClass(s);
        if (addConstantClass.info == 0) {
            ++this.numberOfInnerClasses;
            this.innerClasses.putShort(addConstantClass.index);
            this.innerClasses.putShort((s2 == null) ? 0 : this.symbolTable.addConstantClass(s2).index);
            this.innerClasses.putShort((s3 == null) ? 0 : this.symbolTable.addConstantUtf8(s3));
            this.innerClasses.putShort(n);
            addConstantClass.info = this.numberOfInnerClasses;
        }
    }
    
    @Override
    public final RecordComponentVisitor visitRecordComponent(final String s, final String s2, final String s3) {
        final RecordComponentWriter lastRecordComponent = new RecordComponentWriter(this.symbolTable, s, s2, s3);
        if (this.firstRecordComponent == null) {
            this.firstRecordComponent = lastRecordComponent;
        }
        else {
            this.lastRecordComponent.delegate = lastRecordComponent;
        }
        return this.lastRecordComponent = lastRecordComponent;
    }
    
    @Override
    public final FieldVisitor visitField(final int n, final String s, final String s2, final String s3, final Object o) {
        final FieldWriter lastField = new FieldWriter(this.symbolTable, n, s, s2, s3, o);
        if (this.firstField == null) {
            this.firstField = lastField;
        }
        else {
            this.lastField.fv = lastField;
        }
        return this.lastField = lastField;
    }
    
    @Override
    public final MethodVisitor visitMethod(final int n, final String s, final String s2, final String s3, final String[] array) {
        final MethodWriter lastMethod = new MethodWriter(this.symbolTable, n, s, s2, s3, array, this.compute);
        if (this.firstMethod == null) {
            this.firstMethod = lastMethod;
        }
        else {
            this.lastMethod.mv = lastMethod;
        }
        return this.lastMethod = lastMethod;
    }
    
    @Override
    public final void visitEnd() {
    }
    
    public byte[] toByteArray() {
        int n = 24 + 2 * this.interfaceCount;
        int n2 = 0;
        for (FieldWriter firstField = this.firstField; firstField != null; firstField = (FieldWriter)firstField.fv) {
            ++n2;
            n += firstField.computeFieldInfoSize();
        }
        int n3 = 0;
        for (MethodWriter firstMethod = this.firstMethod; firstMethod != null; firstMethod = (MethodWriter)firstMethod.mv) {
            ++n3;
            n += firstMethod.computeMethodInfoSize();
        }
        int n4 = 0;
        if (this.innerClasses != null) {
            ++n4;
            n += 8 + this.innerClasses.length;
            this.symbolTable.addConstantUtf8("InnerClasses");
        }
        if (this.enclosingClassIndex != 0) {
            ++n4;
            n += 10;
            this.symbolTable.addConstantUtf8("EnclosingMethod");
        }
        if ((this.accessFlags & 0x1000) != 0x0 && (this.version & 0xFFFF) < 49) {
            ++n4;
            n += 6;
            this.symbolTable.addConstantUtf8("Synthetic");
        }
        if (this.signatureIndex != 0) {
            ++n4;
            n += 8;
            this.symbolTable.addConstantUtf8("Signature");
        }
        if (this.sourceFileIndex != 0) {
            ++n4;
            n += 8;
            this.symbolTable.addConstantUtf8("SourceFile");
        }
        if (this.debugExtension != null) {
            ++n4;
            n += 6 + this.debugExtension.length;
            this.symbolTable.addConstantUtf8("SourceDebugExtension");
        }
        if ((this.accessFlags & 0x20000) != 0x0) {
            ++n4;
            n += 6;
            this.symbolTable.addConstantUtf8("Deprecated");
        }
        if (this.lastRuntimeVisibleAnnotation != null) {
            ++n4;
            n += this.lastRuntimeVisibleAnnotation.computeAnnotationsSize("RuntimeVisibleAnnotations");
        }
        if (this.lastRuntimeInvisibleAnnotation != null) {
            ++n4;
            n += this.lastRuntimeInvisibleAnnotation.computeAnnotationsSize("RuntimeInvisibleAnnotations");
        }
        if (this.lastRuntimeVisibleTypeAnnotation != null) {
            ++n4;
            n += this.lastRuntimeVisibleTypeAnnotation.computeAnnotationsSize("RuntimeVisibleTypeAnnotations");
        }
        if (this.lastRuntimeInvisibleTypeAnnotation != null) {
            ++n4;
            n += this.lastRuntimeInvisibleTypeAnnotation.computeAnnotationsSize("RuntimeInvisibleTypeAnnotations");
        }
        if (this.symbolTable.computeBootstrapMethodsSize() > 0) {
            ++n4;
            n += this.symbolTable.computeBootstrapMethodsSize();
        }
        if (this.moduleWriter != null) {
            n4 += this.moduleWriter.getAttributeCount();
            n += this.moduleWriter.computeAttributesSize();
        }
        if (this.nestHostClassIndex != 0) {
            ++n4;
            n += 8;
            this.symbolTable.addConstantUtf8("NestHost");
        }
        if (this.nestMemberClasses != null) {
            ++n4;
            n += 8 + this.nestMemberClasses.length;
            this.symbolTable.addConstantUtf8("NestMembers");
        }
        if (this.permittedSubclasses != null) {
            ++n4;
            n += 8 + this.permittedSubclasses.length;
            this.symbolTable.addConstantUtf8("PermittedSubclasses");
        }
        int n5 = 0;
        int n6 = 0;
        if ((this.accessFlags & 0x10000) != 0x0 || this.firstRecordComponent != null) {
            for (RecordComponentWriter firstRecordComponent = this.firstRecordComponent; firstRecordComponent != null; firstRecordComponent = (RecordComponentWriter)firstRecordComponent.delegate) {
                ++n5;
                n6 += firstRecordComponent.computeRecordComponentInfoSize();
            }
            ++n4;
            n += 8 + n6;
            this.symbolTable.addConstantUtf8("Record");
        }
        if (this.firstAttribute != null) {
            n4 += this.firstAttribute.getAttributeCount();
            n += this.firstAttribute.computeAttributesSize(this.symbolTable);
        }
        final int n7 = n + this.symbolTable.getConstantPoolLength();
        final int constantPoolCount = this.symbolTable.getConstantPoolCount();
        if (constantPoolCount > 65535) {
            throw new ClassTooLargeException(this.symbolTable.getClassName(), constantPoolCount);
        }
        final ByteVector byteVector = new ByteVector(n7);
        byteVector.putInt(-889275714).putInt(this.version);
        this.symbolTable.putConstantPool(byteVector);
        byteVector.putShort(this.accessFlags & ~(((this.version & 0xFFFF) < 49) ? 4096 : 0)).putShort(this.thisClass).putShort(this.superClass);
        byteVector.putShort(this.interfaceCount);
        for (int i = 0; i < this.interfaceCount; ++i) {
            byteVector.putShort(this.interfaces[i]);
        }
        byteVector.putShort(n2);
        for (FieldWriter firstField2 = this.firstField; firstField2 != null; firstField2 = (FieldWriter)firstField2.fv) {
            firstField2.putFieldInfo(byteVector);
        }
        byteVector.putShort(n3);
        boolean b = false;
        boolean b2 = false;
        for (MethodWriter firstMethod2 = this.firstMethod; firstMethod2 != null; firstMethod2 = (MethodWriter)firstMethod2.mv) {
            b |= firstMethod2.hasFrames();
            b2 |= firstMethod2.hasAsmInstructions();
            firstMethod2.putMethodInfo(byteVector);
        }
        byteVector.putShort(n4);
        if (this.innerClasses != null) {
            byteVector.putShort(this.symbolTable.addConstantUtf8("InnerClasses")).putInt(this.innerClasses.length + 2).putShort(this.numberOfInnerClasses).putByteArray(this.innerClasses.data, 0, this.innerClasses.length);
        }
        if (this.enclosingClassIndex != 0) {
            byteVector.putShort(this.symbolTable.addConstantUtf8("EnclosingMethod")).putInt(4).putShort(this.enclosingClassIndex).putShort(this.enclosingMethodIndex);
        }
        if ((this.accessFlags & 0x1000) != 0x0 && (this.version & 0xFFFF) < 49) {
            byteVector.putShort(this.symbolTable.addConstantUtf8("Synthetic")).putInt(0);
        }
        if (this.signatureIndex != 0) {
            byteVector.putShort(this.symbolTable.addConstantUtf8("Signature")).putInt(2).putShort(this.signatureIndex);
        }
        if (this.sourceFileIndex != 0) {
            byteVector.putShort(this.symbolTable.addConstantUtf8("SourceFile")).putInt(2).putShort(this.sourceFileIndex);
        }
        if (this.debugExtension != null) {
            final int length = this.debugExtension.length;
            byteVector.putShort(this.symbolTable.addConstantUtf8("SourceDebugExtension")).putInt(length).putByteArray(this.debugExtension.data, 0, length);
        }
        if ((this.accessFlags & 0x20000) != 0x0) {
            byteVector.putShort(this.symbolTable.addConstantUtf8("Deprecated")).putInt(0);
        }
        AnnotationWriter.putAnnotations(this.symbolTable, this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation, byteVector);
        this.symbolTable.putBootstrapMethods(byteVector);
        if (this.moduleWriter != null) {
            this.moduleWriter.putAttributes(byteVector);
        }
        if (this.nestHostClassIndex != 0) {
            byteVector.putShort(this.symbolTable.addConstantUtf8("NestHost")).putInt(2).putShort(this.nestHostClassIndex);
        }
        if (this.nestMemberClasses != null) {
            byteVector.putShort(this.symbolTable.addConstantUtf8("NestMembers")).putInt(this.nestMemberClasses.length + 2).putShort(this.numberOfNestMemberClasses).putByteArray(this.nestMemberClasses.data, 0, this.nestMemberClasses.length);
        }
        if (this.permittedSubclasses != null) {
            byteVector.putShort(this.symbolTable.addConstantUtf8("PermittedSubclasses")).putInt(this.permittedSubclasses.length + 2).putShort(this.numberOfPermittedSubclasses).putByteArray(this.permittedSubclasses.data, 0, this.permittedSubclasses.length);
        }
        if ((this.accessFlags & 0x10000) != 0x0 || this.firstRecordComponent != null) {
            byteVector.putShort(this.symbolTable.addConstantUtf8("Record")).putInt(n6 + 2).putShort(n5);
            for (RecordComponentWriter firstRecordComponent2 = this.firstRecordComponent; firstRecordComponent2 != null; firstRecordComponent2 = (RecordComponentWriter)firstRecordComponent2.delegate) {
                firstRecordComponent2.putRecordComponentInfo(byteVector);
            }
        }
        if (this.firstAttribute != null) {
            this.firstAttribute.putAttributes(this.symbolTable, byteVector);
        }
        if (b2) {
            return this.replaceAsmInstructions(byteVector.data, b);
        }
        return byteVector.data;
    }
    
    private byte[] replaceAsmInstructions(final byte[] array, final boolean b) {
        final Attribute[] attributePrototypes = this.getAttributePrototypes();
        this.firstField = null;
        this.lastField = null;
        this.firstMethod = null;
        this.lastMethod = null;
        this.lastRuntimeVisibleAnnotation = null;
        this.lastRuntimeInvisibleAnnotation = null;
        this.lastRuntimeVisibleTypeAnnotation = null;
        this.lastRuntimeInvisibleTypeAnnotation = null;
        this.moduleWriter = null;
        this.nestHostClassIndex = 0;
        this.numberOfNestMemberClasses = 0;
        this.nestMemberClasses = null;
        this.numberOfPermittedSubclasses = 0;
        this.permittedSubclasses = null;
        this.firstRecordComponent = null;
        this.lastRecordComponent = null;
        this.firstAttribute = null;
        this.compute = (b ? 3 : 0);
        new ClassReader(array, 0, false).accept(this, attributePrototypes, (b ? 8 : 0) | 0x100);
        return this.toByteArray();
    }
    
    private Attribute[] getAttributePrototypes() {
        final Attribute.Set set = new Attribute.Set();
        set.addAttributes(this.firstAttribute);
        for (FieldWriter firstField = this.firstField; firstField != null; firstField = (FieldWriter)firstField.fv) {
            firstField.collectAttributePrototypes(set);
        }
        for (MethodWriter firstMethod = this.firstMethod; firstMethod != null; firstMethod = (MethodWriter)firstMethod.mv) {
            firstMethod.collectAttributePrototypes(set);
        }
        for (RecordComponentWriter firstRecordComponent = this.firstRecordComponent; firstRecordComponent != null; firstRecordComponent = (RecordComponentWriter)firstRecordComponent.delegate) {
            firstRecordComponent.collectAttributePrototypes(set);
        }
        return set.toArray();
    }
    
    public int newConst(final Object o) {
        return this.symbolTable.addConstant(o).index;
    }
    
    public int newUTF8(final String s) {
        return this.symbolTable.addConstantUtf8(s);
    }
    
    public int newClass(final String s) {
        return this.symbolTable.addConstantClass(s).index;
    }
    
    public int newMethodType(final String s) {
        return this.symbolTable.addConstantMethodType(s).index;
    }
    
    public int newModule(final String s) {
        return this.symbolTable.addConstantModule(s).index;
    }
    
    public int newPackage(final String s) {
        return this.symbolTable.addConstantPackage(s).index;
    }
    
    @Deprecated
    public int newHandle(final int n, final String s, final String s2, final String s3) {
        return this.newHandle(n, s, s2, s3, n == 9);
    }
    
    public int newHandle(final int n, final String s, final String s2, final String s3, final boolean b) {
        return this.symbolTable.addConstantMethodHandle(n, s, s2, s3, b).index;
    }
    
    public int newConstantDynamic(final String s, final String s2, final Handle handle, final Object... array) {
        return this.symbolTable.addConstantDynamic(s, s2, handle, array).index;
    }
    
    public int newInvokeDynamic(final String s, final String s2, final Handle handle, final Object... array) {
        return this.symbolTable.addConstantInvokeDynamic(s, s2, handle, array).index;
    }
    
    public int newField(final String s, final String s2, final String s3) {
        return this.symbolTable.addConstantFieldref(s, s2, s3).index;
    }
    
    public int newMethod(final String s, final String s2, final String s3, final boolean b) {
        return this.symbolTable.addConstantMethodref(s, s2, s3, b).index;
    }
    
    public int newNameType(final String s, final String s2) {
        return this.symbolTable.addConstantNameAndType(s, s2);
    }
    
    protected String getCommonSuperClass(final String typeName, final String typeName2) {
        final ClassLoader classLoader = this.getClassLoader();
        Class<?> clazz;
        try {
            clazz = Class.forName(typeName.replace('/', '.'), false, classLoader);
        }
        catch (final ClassNotFoundException cause) {
            throw new TypeNotPresentException(typeName, cause);
        }
        Class<?> forName;
        try {
            forName = Class.forName(typeName2.replace('/', '.'), false, classLoader);
        }
        catch (final ClassNotFoundException cause2) {
            throw new TypeNotPresentException(typeName2, cause2);
        }
        if (clazz.isAssignableFrom(forName)) {
            return typeName;
        }
        if (forName.isAssignableFrom(clazz)) {
            return typeName2;
        }
        if (clazz.isInterface() || forName.isInterface()) {
            return "java/lang/Object";
        }
        do {
            clazz = clazz.getSuperclass();
        } while (!clazz.isAssignableFrom(forName));
        return clazz.getName().replace('.', '/');
    }
    
    protected ClassLoader getClassLoader() {
        return this.getClass().getClassLoader();
    }
}
