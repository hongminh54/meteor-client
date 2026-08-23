// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

final class MethodWriter extends MethodVisitor
{
    static final int COMPUTE_NOTHING = 0;
    static final int COMPUTE_MAX_STACK_AND_LOCAL = 1;
    static final int COMPUTE_MAX_STACK_AND_LOCAL_FROM_FRAMES = 2;
    static final int COMPUTE_INSERTED_FRAMES = 3;
    static final int COMPUTE_ALL_FRAMES = 4;
    private static final int NA = 0;
    private static final int[] STACK_SIZE_DELTA;
    private final SymbolTable symbolTable;
    private final int accessFlags;
    private final int nameIndex;
    private final String name;
    private final int descriptorIndex;
    private final String descriptor;
    private int maxStack;
    private int maxLocals;
    private final ByteVector code;
    private Handler firstHandler;
    private Handler lastHandler;
    private int lineNumberTableLength;
    private ByteVector lineNumberTable;
    private int localVariableTableLength;
    private ByteVector localVariableTable;
    private int localVariableTypeTableLength;
    private ByteVector localVariableTypeTable;
    private int stackMapTableNumberOfEntries;
    private ByteVector stackMapTableEntries;
    private AnnotationWriter lastCodeRuntimeVisibleTypeAnnotation;
    private AnnotationWriter lastCodeRuntimeInvisibleTypeAnnotation;
    private Attribute firstCodeAttribute;
    private final int numberOfExceptions;
    private final int[] exceptionIndexTable;
    private final int signatureIndex;
    private AnnotationWriter lastRuntimeVisibleAnnotation;
    private AnnotationWriter lastRuntimeInvisibleAnnotation;
    private int visibleAnnotableParameterCount;
    private AnnotationWriter[] lastRuntimeVisibleParameterAnnotations;
    private int invisibleAnnotableParameterCount;
    private AnnotationWriter[] lastRuntimeInvisibleParameterAnnotations;
    private AnnotationWriter lastRuntimeVisibleTypeAnnotation;
    private AnnotationWriter lastRuntimeInvisibleTypeAnnotation;
    private ByteVector defaultValue;
    private int parametersCount;
    private ByteVector parameters;
    private Attribute firstAttribute;
    private final int compute;
    private Label firstBasicBlock;
    private Label lastBasicBlock;
    private Label currentBasicBlock;
    private int relativeStackSize;
    private int maxRelativeStackSize;
    private int currentLocals;
    private int previousFrameOffset;
    private int[] previousFrame;
    private int[] currentFrame;
    private boolean hasSubroutines;
    private boolean hasAsmInstructions;
    private int lastBytecodeOffset;
    private int sourceOffset;
    private int sourceLength;
    
    MethodWriter(final SymbolTable symbolTable, final int n, final String s, final String descriptor, final String s2, final String[] array, final int compute) {
        super(589824);
        this.code = new ByteVector();
        this.symbolTable = symbolTable;
        this.accessFlags = ("<init>".equals(s) ? (n | 0x40000) : n);
        this.nameIndex = symbolTable.addConstantUtf8(s);
        this.name = s;
        this.descriptorIndex = symbolTable.addConstantUtf8(descriptor);
        this.descriptor = descriptor;
        this.signatureIndex = ((s2 == null) ? 0 : symbolTable.addConstantUtf8(s2));
        if (array != null && array.length > 0) {
            this.numberOfExceptions = array.length;
            this.exceptionIndexTable = new int[this.numberOfExceptions];
            for (int i = 0; i < this.numberOfExceptions; ++i) {
                this.exceptionIndexTable[i] = symbolTable.addConstantClass(array[i]).index;
            }
        }
        else {
            this.numberOfExceptions = 0;
            this.exceptionIndexTable = null;
        }
        this.compute = compute;
        if (compute != 0) {
            int n2 = Type.getArgumentsAndReturnSizes(descriptor) >> 2;
            if ((n & 0x8) != 0x0) {
                --n2;
            }
            this.maxLocals = n2;
            this.currentLocals = n2;
            this.visitLabel(this.firstBasicBlock = new Label());
        }
    }
    
    boolean hasFrames() {
        return this.stackMapTableNumberOfEntries > 0;
    }
    
    boolean hasAsmInstructions() {
        return this.hasAsmInstructions;
    }
    
    @Override
    public void visitParameter(final String s, final int n) {
        if (this.parameters == null) {
            this.parameters = new ByteVector();
        }
        ++this.parametersCount;
        this.parameters.putShort((s == null) ? 0 : this.symbolTable.addConstantUtf8(s)).putShort(n);
    }
    
    @Override
    public AnnotationVisitor visitAnnotationDefault() {
        this.defaultValue = new ByteVector();
        return new AnnotationWriter(this.symbolTable, false, this.defaultValue, null);
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
    public void visitAnnotableParameterCount(final int n, final boolean b) {
        if (b) {
            this.visibleAnnotableParameterCount = n;
        }
        else {
            this.invisibleAnnotableParameterCount = n;
        }
    }
    
    @Override
    public AnnotationVisitor visitParameterAnnotation(final int n, final String s, final boolean b) {
        if (b) {
            if (this.lastRuntimeVisibleParameterAnnotations == null) {
                this.lastRuntimeVisibleParameterAnnotations = new AnnotationWriter[Type.getArgumentCount(this.descriptor)];
            }
            return this.lastRuntimeVisibleParameterAnnotations[n] = AnnotationWriter.create(this.symbolTable, s, this.lastRuntimeVisibleParameterAnnotations[n]);
        }
        if (this.lastRuntimeInvisibleParameterAnnotations == null) {
            this.lastRuntimeInvisibleParameterAnnotations = new AnnotationWriter[Type.getArgumentCount(this.descriptor)];
        }
        return this.lastRuntimeInvisibleParameterAnnotations[n] = AnnotationWriter.create(this.symbolTable, s, this.lastRuntimeInvisibleParameterAnnotations[n]);
    }
    
    @Override
    public void visitAttribute(final Attribute attribute) {
        if (attribute.isCodeAttribute()) {
            attribute.nextAttribute = this.firstCodeAttribute;
            this.firstCodeAttribute = attribute;
        }
        else {
            attribute.nextAttribute = this.firstAttribute;
            this.firstAttribute = attribute;
        }
    }
    
    @Override
    public void visitCode() {
    }
    
    @Override
    public void visitFrame(final int n, final int n2, final Object[] array, final int n3, final Object[] array2) {
        if (this.compute == 4) {
            return;
        }
        if (this.compute == 3) {
            if (this.currentBasicBlock.frame == null) {
                (this.currentBasicBlock.frame = new CurrentFrame(this.currentBasicBlock)).setInputFrameFromDescriptor(this.symbolTable, this.accessFlags, this.descriptor, n2);
                this.currentBasicBlock.frame.accept(this);
            }
            else {
                if (n == -1) {
                    this.currentBasicBlock.frame.setInputFrameFromApiFormat(this.symbolTable, n2, array, n3, array2);
                }
                this.currentBasicBlock.frame.accept(this);
            }
        }
        else if (n == -1) {
            if (this.previousFrame == null) {
                final int n4 = Type.getArgumentsAndReturnSizes(this.descriptor) >> 2;
                final Frame frame = new Frame(new Label());
                frame.setInputFrameFromDescriptor(this.symbolTable, this.accessFlags, this.descriptor, n4);
                frame.accept(this);
            }
            this.currentLocals = n2;
            int visitFrameStart = this.visitFrameStart(this.code.length, n2, n3);
            for (int i = 0; i < n2; ++i) {
                this.currentFrame[visitFrameStart++] = Frame.getAbstractTypeFromApiFormat(this.symbolTable, array[i]);
            }
            for (int j = 0; j < n3; ++j) {
                this.currentFrame[visitFrameStart++] = Frame.getAbstractTypeFromApiFormat(this.symbolTable, array2[j]);
            }
            this.visitFrameEnd();
        }
        else {
            if (this.symbolTable.getMajorVersion() < 50) {
                throw new IllegalArgumentException("Class versions V1_5 or less must use F_NEW frames.");
            }
            int length;
            if (this.stackMapTableEntries == null) {
                this.stackMapTableEntries = new ByteVector();
                length = this.code.length;
            }
            else {
                length = this.code.length - this.previousFrameOffset - 1;
                if (length < 0) {
                    if (n == 3) {
                        return;
                    }
                    throw new IllegalStateException();
                }
            }
            switch (n) {
                case 0: {
                    this.currentLocals = n2;
                    this.stackMapTableEntries.putByte(255).putShort(length).putShort(n2);
                    for (int k = 0; k < n2; ++k) {
                        this.putFrameType(array[k]);
                    }
                    this.stackMapTableEntries.putShort(n3);
                    for (int l = 0; l < n3; ++l) {
                        this.putFrameType(array2[l]);
                    }
                    break;
                }
                case 1: {
                    this.currentLocals += n2;
                    this.stackMapTableEntries.putByte(251 + n2).putShort(length);
                    for (int n5 = 0; n5 < n2; ++n5) {
                        this.putFrameType(array[n5]);
                    }
                    break;
                }
                case 2: {
                    this.currentLocals -= n2;
                    this.stackMapTableEntries.putByte(251 - n2).putShort(length);
                    break;
                }
                case 3: {
                    if (length < 64) {
                        this.stackMapTableEntries.putByte(length);
                        break;
                    }
                    this.stackMapTableEntries.putByte(251).putShort(length);
                    break;
                }
                case 4: {
                    if (length < 64) {
                        this.stackMapTableEntries.putByte(64 + length);
                    }
                    else {
                        this.stackMapTableEntries.putByte(247).putShort(length);
                    }
                    this.putFrameType(array2[0]);
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
            this.previousFrameOffset = this.code.length;
            ++this.stackMapTableNumberOfEntries;
        }
        if (this.compute == 2) {
            this.relativeStackSize = n3;
            for (int n6 = 0; n6 < n3; ++n6) {
                if (array2[n6] == Opcodes.LONG || array2[n6] == Opcodes.DOUBLE) {
                    ++this.relativeStackSize;
                }
            }
            if (this.relativeStackSize > this.maxRelativeStackSize) {
                this.maxRelativeStackSize = this.relativeStackSize;
            }
        }
        this.maxStack = Math.max(this.maxStack, n3);
        this.maxLocals = Math.max(this.maxLocals, this.currentLocals);
    }
    
    @Override
    public void visitInsn(final int n) {
        this.lastBytecodeOffset = this.code.length;
        this.code.putByte(n);
        if (this.currentBasicBlock != null) {
            if (this.compute == 4 || this.compute == 3) {
                this.currentBasicBlock.frame.execute(n, 0, null, null);
            }
            else {
                final int n2 = this.relativeStackSize + MethodWriter.STACK_SIZE_DELTA[n];
                if (n2 > this.maxRelativeStackSize) {
                    this.maxRelativeStackSize = n2;
                }
                this.relativeStackSize = n2;
            }
            if ((n >= 172 && n <= 177) || n == 191) {
                this.endCurrentBasicBlockWithNoSuccessor();
            }
        }
    }
    
    @Override
    public void visitIntInsn(final int n, final int n2) {
        this.lastBytecodeOffset = this.code.length;
        if (n == 17) {
            this.code.put12(n, n2);
        }
        else {
            this.code.put11(n, n2);
        }
        if (this.currentBasicBlock != null) {
            if (this.compute == 4 || this.compute == 3) {
                this.currentBasicBlock.frame.execute(n, n2, null, null);
            }
            else if (n != 188) {
                final int n3 = this.relativeStackSize + 1;
                if (n3 > this.maxRelativeStackSize) {
                    this.maxRelativeStackSize = n3;
                }
                this.relativeStackSize = n3;
            }
        }
    }
    
    @Override
    public void visitVarInsn(final int n, final int n2) {
        this.lastBytecodeOffset = this.code.length;
        if (n2 < 4 && n != 169) {
            int n3;
            if (n < 54) {
                n3 = 26 + (n - 21 << 2) + n2;
            }
            else {
                n3 = 59 + (n - 54 << 2) + n2;
            }
            this.code.putByte(n3);
        }
        else if (n2 >= 256) {
            this.code.putByte(196).put12(n, n2);
        }
        else {
            this.code.put11(n, n2);
        }
        if (this.currentBasicBlock != null) {
            if (this.compute == 4 || this.compute == 3) {
                this.currentBasicBlock.frame.execute(n, n2, null, null);
            }
            else if (n == 169) {
                final Label currentBasicBlock = this.currentBasicBlock;
                currentBasicBlock.flags |= 0x40;
                this.currentBasicBlock.outputStackSize = (short)this.relativeStackSize;
                this.endCurrentBasicBlockWithNoSuccessor();
            }
            else {
                final int n4 = this.relativeStackSize + MethodWriter.STACK_SIZE_DELTA[n];
                if (n4 > this.maxRelativeStackSize) {
                    this.maxRelativeStackSize = n4;
                }
                this.relativeStackSize = n4;
            }
        }
        if (this.compute != 0) {
            int maxLocals;
            if (n == 22 || n == 24 || n == 55 || n == 57) {
                maxLocals = n2 + 2;
            }
            else {
                maxLocals = n2 + 1;
            }
            if (maxLocals > this.maxLocals) {
                this.maxLocals = maxLocals;
            }
        }
        if (n >= 54 && this.compute == 4 && this.firstHandler != null) {
            this.visitLabel(new Label());
        }
    }
    
    @Override
    public void visitTypeInsn(final int n, final String s) {
        this.lastBytecodeOffset = this.code.length;
        final Symbol addConstantClass = this.symbolTable.addConstantClass(s);
        this.code.put12(n, addConstantClass.index);
        if (this.currentBasicBlock != null) {
            if (this.compute == 4 || this.compute == 3) {
                this.currentBasicBlock.frame.execute(n, this.lastBytecodeOffset, addConstantClass, this.symbolTable);
            }
            else if (n == 187) {
                final int n2 = this.relativeStackSize + 1;
                if (n2 > this.maxRelativeStackSize) {
                    this.maxRelativeStackSize = n2;
                }
                this.relativeStackSize = n2;
            }
        }
    }
    
    @Override
    public void visitFieldInsn(final int n, final String s, final String s2, final String s3) {
        this.lastBytecodeOffset = this.code.length;
        final Symbol addConstantFieldref = this.symbolTable.addConstantFieldref(s, s2, s3);
        this.code.put12(n, addConstantFieldref.index);
        if (this.currentBasicBlock != null) {
            if (this.compute == 4 || this.compute == 3) {
                this.currentBasicBlock.frame.execute(n, 0, addConstantFieldref, this.symbolTable);
            }
            else {
                final char char1 = s3.charAt(0);
                int n2 = 0;
                switch (n) {
                    case 178: {
                        n2 = this.relativeStackSize + ((char1 == 'D' || char1 == 'J') ? 2 : 1);
                        break;
                    }
                    case 179: {
                        n2 = this.relativeStackSize + ((char1 == 'D' || char1 == 'J') ? -2 : -1);
                        break;
                    }
                    case 180: {
                        n2 = this.relativeStackSize + ((char1 == 'D' || char1 == 'J') ? 1 : 0);
                        break;
                    }
                    default: {
                        n2 = this.relativeStackSize + ((char1 == 'D' || char1 == 'J') ? -3 : -2);
                        break;
                    }
                }
                if (n2 > this.maxRelativeStackSize) {
                    this.maxRelativeStackSize = n2;
                }
                this.relativeStackSize = n2;
            }
        }
    }
    
    @Override
    public void visitMethodInsn(final int n, final String s, final String s2, final String s3, final boolean b) {
        this.lastBytecodeOffset = this.code.length;
        final Symbol addConstantMethodref = this.symbolTable.addConstantMethodref(s, s2, s3, b);
        if (n == 185) {
            this.code.put12(185, addConstantMethodref.index).put11(addConstantMethodref.getArgumentsAndReturnSizes() >> 2, 0);
        }
        else {
            this.code.put12(n, addConstantMethodref.index);
        }
        if (this.currentBasicBlock != null) {
            if (this.compute == 4 || this.compute == 3) {
                this.currentBasicBlock.frame.execute(n, 0, addConstantMethodref, this.symbolTable);
            }
            else {
                final int argumentsAndReturnSizes = addConstantMethodref.getArgumentsAndReturnSizes();
                final int n2 = (argumentsAndReturnSizes & 0x3) - (argumentsAndReturnSizes >> 2);
                int n3;
                if (n == 184) {
                    n3 = this.relativeStackSize + n2 + 1;
                }
                else {
                    n3 = this.relativeStackSize + n2;
                }
                if (n3 > this.maxRelativeStackSize) {
                    this.maxRelativeStackSize = n3;
                }
                this.relativeStackSize = n3;
            }
        }
    }
    
    @Override
    public void visitInvokeDynamicInsn(final String s, final String s2, final Handle handle, final Object... array) {
        this.lastBytecodeOffset = this.code.length;
        final Symbol addConstantInvokeDynamic = this.symbolTable.addConstantInvokeDynamic(s, s2, handle, array);
        this.code.put12(186, addConstantInvokeDynamic.index);
        this.code.putShort(0);
        if (this.currentBasicBlock != null) {
            if (this.compute == 4 || this.compute == 3) {
                this.currentBasicBlock.frame.execute(186, 0, addConstantInvokeDynamic, this.symbolTable);
            }
            else {
                final int argumentsAndReturnSizes = addConstantInvokeDynamic.getArgumentsAndReturnSizes();
                final int n = this.relativeStackSize + ((argumentsAndReturnSizes & 0x3) - (argumentsAndReturnSizes >> 2) + 1);
                if (n > this.maxRelativeStackSize) {
                    this.maxRelativeStackSize = n;
                }
                this.relativeStackSize = n;
            }
        }
    }
    
    @Override
    public void visitJumpInsn(final int n, final Label label) {
        this.lastBytecodeOffset = this.code.length;
        final int n2 = (n >= 200) ? (n - 33) : n;
        boolean b = false;
        if ((label.flags & 0x4) != 0x0 && label.bytecodeOffset - this.code.length < -32768) {
            if (n2 == 167) {
                this.code.putByte(200);
            }
            else if (n2 == 168) {
                this.code.putByte(201);
            }
            else {
                this.code.putByte((n2 >= 198) ? (n2 ^ 0x1) : ((n2 + 1 ^ 0x1) - 1));
                this.code.putShort(8);
                this.code.putByte(220);
                this.hasAsmInstructions = true;
                b = true;
            }
            label.put(this.code, this.code.length - 1, true);
        }
        else if (n2 != n) {
            this.code.putByte(n);
            label.put(this.code, this.code.length - 1, true);
        }
        else {
            this.code.putByte(n2);
            label.put(this.code, this.code.length - 1, false);
        }
        if (this.currentBasicBlock != null) {
            Label label2 = null;
            if (this.compute == 4) {
                this.currentBasicBlock.frame.execute(n2, 0, null, null);
                final Label canonicalInstance = label.getCanonicalInstance();
                canonicalInstance.flags |= 0x2;
                this.addSuccessorToCurrentBasicBlock(0, label);
                if (n2 != 167) {
                    label2 = new Label();
                }
            }
            else if (this.compute == 3) {
                this.currentBasicBlock.frame.execute(n2, 0, null, null);
            }
            else if (this.compute == 2) {
                this.relativeStackSize += MethodWriter.STACK_SIZE_DELTA[n2];
            }
            else if (n2 == 168) {
                if ((label.flags & 0x20) == 0x0) {
                    label.flags |= 0x20;
                    this.hasSubroutines = true;
                }
                final Label currentBasicBlock = this.currentBasicBlock;
                currentBasicBlock.flags |= 0x10;
                this.addSuccessorToCurrentBasicBlock(this.relativeStackSize + 1, label);
                label2 = new Label();
            }
            else {
                this.addSuccessorToCurrentBasicBlock(this.relativeStackSize += MethodWriter.STACK_SIZE_DELTA[n2], label);
            }
            if (label2 != null) {
                if (b) {
                    final Label label3 = label2;
                    label3.flags |= 0x2;
                }
                this.visitLabel(label2);
            }
            if (n2 == 167) {
                this.endCurrentBasicBlockWithNoSuccessor();
            }
        }
    }
    
    @Override
    public void visitLabel(final Label currentBasicBlock) {
        this.hasAsmInstructions |= currentBasicBlock.resolve(this.code.data, this.stackMapTableEntries, this.code.length);
        if ((currentBasicBlock.flags & 0x1) != 0x0) {
            return;
        }
        if (this.compute == 4) {
            if (this.currentBasicBlock != null) {
                if (currentBasicBlock.bytecodeOffset == this.currentBasicBlock.bytecodeOffset) {
                    final Label currentBasicBlock2 = this.currentBasicBlock;
                    currentBasicBlock2.flags |= (short)(currentBasicBlock.flags & 0x2);
                    currentBasicBlock.frame = this.currentBasicBlock.frame;
                    return;
                }
                this.addSuccessorToCurrentBasicBlock(0, currentBasicBlock);
            }
            if (this.lastBasicBlock != null) {
                if (currentBasicBlock.bytecodeOffset == this.lastBasicBlock.bytecodeOffset) {
                    final Label lastBasicBlock = this.lastBasicBlock;
                    lastBasicBlock.flags |= (short)(currentBasicBlock.flags & 0x2);
                    currentBasicBlock.frame = this.lastBasicBlock.frame;
                    this.currentBasicBlock = this.lastBasicBlock;
                    return;
                }
                this.lastBasicBlock.nextBasicBlock = currentBasicBlock;
            }
            this.lastBasicBlock = currentBasicBlock;
            this.currentBasicBlock = currentBasicBlock;
            currentBasicBlock.frame = new Frame(currentBasicBlock);
        }
        else if (this.compute == 3) {
            if (this.currentBasicBlock == null) {
                this.currentBasicBlock = currentBasicBlock;
            }
            else {
                this.currentBasicBlock.frame.owner = currentBasicBlock;
            }
        }
        else if (this.compute == 1) {
            if (this.currentBasicBlock != null) {
                this.currentBasicBlock.outputStackMax = (short)this.maxRelativeStackSize;
                this.addSuccessorToCurrentBasicBlock(this.relativeStackSize, currentBasicBlock);
            }
            this.currentBasicBlock = currentBasicBlock;
            this.relativeStackSize = 0;
            this.maxRelativeStackSize = 0;
            if (this.lastBasicBlock != null) {
                this.lastBasicBlock.nextBasicBlock = currentBasicBlock;
            }
            this.lastBasicBlock = currentBasicBlock;
        }
        else if (this.compute == 2 && this.currentBasicBlock == null) {
            this.currentBasicBlock = currentBasicBlock;
        }
    }
    
    @Override
    public void visitLdcInsn(final Object o) {
        this.lastBytecodeOffset = this.code.length;
        final Symbol addConstant = this.symbolTable.addConstant(o);
        final int index = addConstant.index;
        final char char1;
        final boolean b = addConstant.tag == 5 || addConstant.tag == 6 || (addConstant.tag == 17 && ((char1 = addConstant.value.charAt(0)) == 'J' || char1 == 'D'));
        if (b) {
            this.code.put12(20, index);
        }
        else if (index >= 256) {
            this.code.put12(19, index);
        }
        else {
            this.code.put11(18, index);
        }
        if (this.currentBasicBlock != null) {
            if (this.compute == 4 || this.compute == 3) {
                this.currentBasicBlock.frame.execute(18, 0, addConstant, this.symbolTable);
            }
            else {
                final int n = this.relativeStackSize + (b ? 2 : 1);
                if (n > this.maxRelativeStackSize) {
                    this.maxRelativeStackSize = n;
                }
                this.relativeStackSize = n;
            }
        }
    }
    
    @Override
    public void visitIincInsn(final int n, final int n2) {
        this.lastBytecodeOffset = this.code.length;
        if (n > 255 || n2 > 127 || n2 < -128) {
            this.code.putByte(196).put12(132, n).putShort(n2);
        }
        else {
            this.code.putByte(132).put11(n, n2);
        }
        if (this.currentBasicBlock != null && (this.compute == 4 || this.compute == 3)) {
            this.currentBasicBlock.frame.execute(132, n, null, null);
        }
        if (this.compute != 0) {
            final int maxLocals = n + 1;
            if (maxLocals > this.maxLocals) {
                this.maxLocals = maxLocals;
            }
        }
    }
    
    @Override
    public void visitTableSwitchInsn(final int n, final int n2, final Label label, final Label... array) {
        this.lastBytecodeOffset = this.code.length;
        this.code.putByte(170).putByteArray(null, 0, (4 - this.code.length % 4) % 4);
        label.put(this.code, this.lastBytecodeOffset, true);
        this.code.putInt(n).putInt(n2);
        for (int length = array.length, i = 0; i < length; ++i) {
            array[i].put(this.code, this.lastBytecodeOffset, true);
        }
        this.visitSwitchInsn(label, array);
    }
    
    @Override
    public void visitLookupSwitchInsn(final Label label, final int[] array, final Label[] array2) {
        this.lastBytecodeOffset = this.code.length;
        this.code.putByte(171).putByteArray(null, 0, (4 - this.code.length % 4) % 4);
        label.put(this.code, this.lastBytecodeOffset, true);
        this.code.putInt(array2.length);
        for (int i = 0; i < array2.length; ++i) {
            this.code.putInt(array[i]);
            array2[i].put(this.code, this.lastBytecodeOffset, true);
        }
        this.visitSwitchInsn(label, array2);
    }
    
    private void visitSwitchInsn(final Label label, final Label[] array) {
        if (this.currentBasicBlock != null) {
            if (this.compute == 4) {
                this.currentBasicBlock.frame.execute(171, 0, null, null);
                this.addSuccessorToCurrentBasicBlock(0, label);
                final Label canonicalInstance = label.getCanonicalInstance();
                canonicalInstance.flags |= 0x2;
                for (final Label label2 : array) {
                    this.addSuccessorToCurrentBasicBlock(0, label2);
                    final Label canonicalInstance2 = label2.getCanonicalInstance();
                    canonicalInstance2.flags |= 0x2;
                }
            }
            else if (this.compute == 1) {
                this.addSuccessorToCurrentBasicBlock(--this.relativeStackSize, label);
                for (int length2 = array.length, j = 0; j < length2; ++j) {
                    this.addSuccessorToCurrentBasicBlock(this.relativeStackSize, array[j]);
                }
            }
            this.endCurrentBasicBlockWithNoSuccessor();
        }
    }
    
    @Override
    public void visitMultiANewArrayInsn(final String s, final int n) {
        this.lastBytecodeOffset = this.code.length;
        final Symbol addConstantClass = this.symbolTable.addConstantClass(s);
        this.code.put12(197, addConstantClass.index).putByte(n);
        if (this.currentBasicBlock != null) {
            if (this.compute == 4 || this.compute == 3) {
                this.currentBasicBlock.frame.execute(197, n, addConstantClass, this.symbolTable);
            }
            else {
                this.relativeStackSize += 1 - n;
            }
        }
    }
    
    @Override
    public AnnotationVisitor visitInsnAnnotation(final int n, final TypePath typePath, final String s, final boolean b) {
        if (b) {
            return this.lastCodeRuntimeVisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, (n & 0xFF0000FF) | this.lastBytecodeOffset << 8, typePath, s, this.lastCodeRuntimeVisibleTypeAnnotation);
        }
        return this.lastCodeRuntimeInvisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, (n & 0xFF0000FF) | this.lastBytecodeOffset << 8, typePath, s, this.lastCodeRuntimeInvisibleTypeAnnotation);
    }
    
    @Override
    public void visitTryCatchBlock(final Label label, final Label label2, final Label label3, final String s) {
        final Handler lastHandler = new Handler(label, label2, label3, (s != null) ? this.symbolTable.addConstantClass(s).index : 0, s);
        if (this.firstHandler == null) {
            this.firstHandler = lastHandler;
        }
        else {
            this.lastHandler.nextHandler = lastHandler;
        }
        this.lastHandler = lastHandler;
    }
    
    @Override
    public AnnotationVisitor visitTryCatchAnnotation(final int n, final TypePath typePath, final String s, final boolean b) {
        if (b) {
            return this.lastCodeRuntimeVisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, n, typePath, s, this.lastCodeRuntimeVisibleTypeAnnotation);
        }
        return this.lastCodeRuntimeInvisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, n, typePath, s, this.lastCodeRuntimeInvisibleTypeAnnotation);
    }
    
    @Override
    public void visitLocalVariable(final String s, final String s2, final String s3, final Label label, final Label label2, final int n) {
        if (s3 != null) {
            if (this.localVariableTypeTable == null) {
                this.localVariableTypeTable = new ByteVector();
            }
            ++this.localVariableTypeTableLength;
            this.localVariableTypeTable.putShort(label.bytecodeOffset).putShort(label2.bytecodeOffset - label.bytecodeOffset).putShort(this.symbolTable.addConstantUtf8(s)).putShort(this.symbolTable.addConstantUtf8(s3)).putShort(n);
        }
        if (this.localVariableTable == null) {
            this.localVariableTable = new ByteVector();
        }
        ++this.localVariableTableLength;
        this.localVariableTable.putShort(label.bytecodeOffset).putShort(label2.bytecodeOffset - label.bytecodeOffset).putShort(this.symbolTable.addConstantUtf8(s)).putShort(this.symbolTable.addConstantUtf8(s2)).putShort(n);
        if (this.compute != 0) {
            final char char1 = s2.charAt(0);
            final int maxLocals = n + ((char1 == 'J' || char1 == 'D') ? 2 : 1);
            if (maxLocals > this.maxLocals) {
                this.maxLocals = maxLocals;
            }
        }
    }
    
    @Override
    public AnnotationVisitor visitLocalVariableAnnotation(final int n, final TypePath typePath, final Label[] array, final Label[] array2, final int[] array3, final String s, final boolean b) {
        final ByteVector byteVector = new ByteVector();
        byteVector.putByte(n >>> 24).putShort(array.length);
        for (int i = 0; i < array.length; ++i) {
            byteVector.putShort(array[i].bytecodeOffset).putShort(array2[i].bytecodeOffset - array[i].bytecodeOffset).putShort(array3[i]);
        }
        TypePath.put(typePath, byteVector);
        byteVector.putShort(this.symbolTable.addConstantUtf8(s)).putShort(0);
        if (b) {
            return this.lastCodeRuntimeVisibleTypeAnnotation = new AnnotationWriter(this.symbolTable, true, byteVector, this.lastCodeRuntimeVisibleTypeAnnotation);
        }
        return this.lastCodeRuntimeInvisibleTypeAnnotation = new AnnotationWriter(this.symbolTable, true, byteVector, this.lastCodeRuntimeInvisibleTypeAnnotation);
    }
    
    @Override
    public void visitLineNumber(final int n, final Label label) {
        if (this.lineNumberTable == null) {
            this.lineNumberTable = new ByteVector();
        }
        ++this.lineNumberTableLength;
        this.lineNumberTable.putShort(label.bytecodeOffset);
        this.lineNumberTable.putShort(n);
    }
    
    @Override
    public void visitMaxs(final int maxStack, final int maxLocals) {
        if (this.compute == 4) {
            this.computeAllFrames();
        }
        else if (this.compute == 1) {
            this.computeMaxStackAndLocal();
        }
        else if (this.compute == 2) {
            this.maxStack = this.maxRelativeStackSize;
        }
        else {
            this.maxStack = maxStack;
            this.maxLocals = maxLocals;
        }
    }
    
    private void computeAllFrames() {
        for (Handler handler = this.firstHandler; handler != null; handler = handler.nextHandler) {
            final int abstractTypeFromInternalName = Frame.getAbstractTypeFromInternalName(this.symbolTable, (handler.catchTypeDescriptor == null) ? "java/lang/Throwable" : handler.catchTypeDescriptor);
            final Label canonicalInstance;
            final Label label = canonicalInstance = handler.handlerPc.getCanonicalInstance();
            canonicalInstance.flags |= 0x2;
            for (Label label2 = handler.startPc.getCanonicalInstance(); label2 != handler.endPc.getCanonicalInstance(); label2 = label2.nextBasicBlock) {
                label2.outgoingEdges = new Edge(abstractTypeFromInternalName, label, label2.outgoingEdges);
            }
        }
        final Frame frame = this.firstBasicBlock.frame;
        frame.setInputFrameFromDescriptor(this.symbolTable, this.accessFlags, this.descriptor, this.maxLocals);
        frame.accept(this);
        Label nextListElement = this.firstBasicBlock;
        nextListElement.nextListElement = Label.EMPTY_LIST;
        int max = 0;
        while (nextListElement != Label.EMPTY_LIST) {
            final Label label3 = nextListElement;
            nextListElement = nextListElement.nextListElement;
            label3.nextListElement = null;
            final Label label4 = label3;
            label4.flags |= 0x8;
            final int n = label3.frame.getInputStackSize() + label3.outputStackMax;
            if (n > max) {
                max = n;
            }
            for (Edge edge = label3.outgoingEdges; edge != null; edge = edge.nextEdge) {
                final Label canonicalInstance2 = edge.successor.getCanonicalInstance();
                if (label3.frame.merge(this.symbolTable, canonicalInstance2.frame, edge.info) && canonicalInstance2.nextListElement == null) {
                    canonicalInstance2.nextListElement = nextListElement;
                    nextListElement = canonicalInstance2;
                }
            }
        }
        for (Label label5 = this.firstBasicBlock; label5 != null; label5 = label5.nextBasicBlock) {
            if ((label5.flags & 0xA) == 0xA) {
                label5.frame.accept(this);
            }
            if ((label5.flags & 0x8) == 0x0) {
                final Label nextBasicBlock = label5.nextBasicBlock;
                final int bytecodeOffset = label5.bytecodeOffset;
                final int n2 = ((nextBasicBlock == null) ? this.code.length : nextBasicBlock.bytecodeOffset) - 1;
                if (n2 >= bytecodeOffset) {
                    for (int i = bytecodeOffset; i < n2; ++i) {
                        this.code.data[i] = 0;
                    }
                    this.code.data[n2] = -65;
                    this.currentFrame[this.visitFrameStart(bytecodeOffset, 0, 1)] = Frame.getAbstractTypeFromInternalName(this.symbolTable, "java/lang/Throwable");
                    this.visitFrameEnd();
                    this.firstHandler = Handler.removeRange(this.firstHandler, label5, nextBasicBlock);
                    max = Math.max(max, 1);
                }
            }
        }
        this.maxStack = max;
    }
    
    private void computeMaxStackAndLocal() {
        for (Handler handler = this.firstHandler; handler != null; handler = handler.nextHandler) {
            final Label handlerPc = handler.handlerPc;
            for (Label label = handler.startPc; label != handler.endPc; label = label.nextBasicBlock) {
                if ((label.flags & 0x10) == 0x0) {
                    label.outgoingEdges = new Edge(Integer.MAX_VALUE, handlerPc, label.outgoingEdges);
                }
                else {
                    label.outgoingEdges.nextEdge.nextEdge = new Edge(Integer.MAX_VALUE, handlerPc, label.outgoingEdges.nextEdge.nextEdge);
                }
            }
        }
        if (this.hasSubroutines) {
            short n = 1;
            this.firstBasicBlock.markSubroutine(n);
            for (short n2 = 1; n2 <= n; ++n2) {
                for (Label label2 = this.firstBasicBlock; label2 != null; label2 = label2.nextBasicBlock) {
                    if ((label2.flags & 0x10) != 0x0 && label2.subroutineId == n2) {
                        final Label successor = label2.outgoingEdges.nextEdge.successor;
                        if (successor.subroutineId == 0) {
                            final Label label3 = successor;
                            ++n;
                            label3.markSubroutine(n);
                        }
                    }
                }
            }
            for (Label label4 = this.firstBasicBlock; label4 != null; label4 = label4.nextBasicBlock) {
                if ((label4.flags & 0x10) != 0x0) {
                    label4.outgoingEdges.nextEdge.successor.addSubroutineRetSuccessors(label4);
                }
            }
        }
        Label nextListElement = this.firstBasicBlock;
        nextListElement.nextListElement = Label.EMPTY_LIST;
        int maxStack = this.maxStack;
        while (nextListElement != Label.EMPTY_LIST) {
            final Label label5 = nextListElement;
            nextListElement = nextListElement.nextListElement;
            final short inputStackSize = label5.inputStackSize;
            final int n3 = inputStackSize + label5.outputStackMax;
            if (n3 > maxStack) {
                maxStack = n3;
            }
            Edge edge = label5.outgoingEdges;
            if ((label5.flags & 0x10) != 0x0) {
                edge = edge.nextEdge;
            }
            while (edge != null) {
                final Label successor2 = edge.successor;
                if (successor2.nextListElement == null) {
                    successor2.inputStackSize = (short)((edge.info == Integer.MAX_VALUE) ? 1 : (inputStackSize + edge.info));
                    successor2.nextListElement = nextListElement;
                    nextListElement = successor2;
                }
                edge = edge.nextEdge;
            }
        }
        this.maxStack = maxStack;
    }
    
    @Override
    public void visitEnd() {
    }
    
    private void addSuccessorToCurrentBasicBlock(final int n, final Label label) {
        this.currentBasicBlock.outgoingEdges = new Edge(n, label, this.currentBasicBlock.outgoingEdges);
    }
    
    private void endCurrentBasicBlockWithNoSuccessor() {
        if (this.compute == 4) {
            final Label label = new Label();
            label.frame = new Frame(label);
            label.resolve(this.code.data, this.stackMapTableEntries, this.code.length);
            this.lastBasicBlock.nextBasicBlock = label;
            this.lastBasicBlock = label;
            this.currentBasicBlock = null;
        }
        else if (this.compute == 1) {
            this.currentBasicBlock.outputStackMax = (short)this.maxRelativeStackSize;
            this.currentBasicBlock = null;
        }
    }
    
    int visitFrameStart(final int n, final int n2, final int n3) {
        final int n4 = 3 + n2 + n3;
        if (this.currentFrame == null || this.currentFrame.length < n4) {
            this.currentFrame = new int[n4];
        }
        this.currentFrame[0] = n;
        this.currentFrame[1] = n2;
        this.currentFrame[2] = n3;
        return 3;
    }
    
    void visitAbstractType(final int n, final int n2) {
        this.currentFrame[n] = n2;
    }
    
    void visitFrameEnd() {
        if (this.previousFrame != null) {
            if (this.stackMapTableEntries == null) {
                this.stackMapTableEntries = new ByteVector();
            }
            this.putFrame();
            ++this.stackMapTableNumberOfEntries;
        }
        this.previousFrame = this.currentFrame;
        this.currentFrame = null;
    }
    
    private void putFrame() {
        final int n = this.currentFrame[1];
        final int n2 = this.currentFrame[2];
        if (this.symbolTable.getMajorVersion() < 50) {
            this.stackMapTableEntries.putShort(this.currentFrame[0]).putShort(n);
            this.putAbstractTypes(3, 3 + n);
            this.stackMapTableEntries.putShort(n2);
            this.putAbstractTypes(3 + n, 3 + n + n2);
            return;
        }
        final int n3 = (this.stackMapTableNumberOfEntries == 0) ? this.currentFrame[0] : (this.currentFrame[0] - this.previousFrame[0] - 1);
        final int n4 = this.previousFrame[1];
        final int n5 = n - n4;
        int n6 = 255;
        if (n2 == 0) {
            switch (n5) {
                case -3:
                case -2:
                case -1: {
                    n6 = 248;
                    break;
                }
                case 0: {
                    n6 = ((n3 < 64) ? 0 : 251);
                    break;
                }
                case 1:
                case 2:
                case 3: {
                    n6 = 252;
                    break;
                }
            }
        }
        else if (n5 == 0 && n2 == 1) {
            n6 = ((n3 < 63) ? 64 : 247);
        }
        if (n6 != 255) {
            int n7 = 3;
            for (int n8 = 0; n8 < n4 && n8 < n; ++n8) {
                if (this.currentFrame[n7] != this.previousFrame[n7]) {
                    n6 = 255;
                    break;
                }
                ++n7;
            }
        }
        switch (n6) {
            case 0: {
                this.stackMapTableEntries.putByte(n3);
                break;
            }
            case 64: {
                this.stackMapTableEntries.putByte(64 + n3);
                this.putAbstractTypes(3 + n, 4 + n);
                break;
            }
            case 247: {
                this.stackMapTableEntries.putByte(247).putShort(n3);
                this.putAbstractTypes(3 + n, 4 + n);
                break;
            }
            case 251: {
                this.stackMapTableEntries.putByte(251).putShort(n3);
                break;
            }
            case 248: {
                this.stackMapTableEntries.putByte(251 + n5).putShort(n3);
                break;
            }
            case 252: {
                this.stackMapTableEntries.putByte(251 + n5).putShort(n3);
                this.putAbstractTypes(3 + n4, 3 + n);
                break;
            }
            default: {
                this.stackMapTableEntries.putByte(255).putShort(n3).putShort(n);
                this.putAbstractTypes(3, 3 + n);
                this.stackMapTableEntries.putShort(n2);
                this.putAbstractTypes(3 + n, 3 + n + n2);
                break;
            }
        }
    }
    
    private void putAbstractTypes(final int n, final int n2) {
        for (int i = n; i < n2; ++i) {
            Frame.putAbstractType(this.symbolTable, this.currentFrame[i], this.stackMapTableEntries);
        }
    }
    
    private void putFrameType(final Object o) {
        if (o instanceof Integer) {
            this.stackMapTableEntries.putByte((int)o);
        }
        else if (o instanceof String) {
            this.stackMapTableEntries.putByte(7).putShort(this.symbolTable.addConstantClass((String)o).index);
        }
        else {
            this.stackMapTableEntries.putByte(8);
            ((Label)o).put(this.stackMapTableEntries);
        }
    }
    
    boolean canCopyMethodAttributes(final ClassReader classReader, final boolean b, final boolean b2, final int n, final int n2, final int n3) {
        if (classReader != this.symbolTable.getSource() || n != this.descriptorIndex || n2 != this.signatureIndex || b2 != ((this.accessFlags & 0x20000) != 0x0)) {
            return false;
        }
        if (b != (this.symbolTable.getMajorVersion() < 49 && (this.accessFlags & 0x1000) != 0x0)) {
            return false;
        }
        if (n3 == 0) {
            if (this.numberOfExceptions != 0) {
                return false;
            }
        }
        else if (classReader.readUnsignedShort(n3) == this.numberOfExceptions) {
            int n4 = n3 + 2;
            for (int i = 0; i < this.numberOfExceptions; ++i) {
                if (classReader.readUnsignedShort(n4) != this.exceptionIndexTable[i]) {
                    return false;
                }
                n4 += 2;
            }
        }
        return true;
    }
    
    void setMethodAttributesSource(final int n, final int n2) {
        this.sourceOffset = n + 6;
        this.sourceLength = n2 - 6;
    }
    
    int computeMethodInfoSize() {
        if (this.sourceOffset != 0) {
            return 6 + this.sourceLength;
        }
        int n = 8;
        if (this.code.length > 0) {
            if (this.code.length > 65535) {
                throw new MethodTooLargeException(this.symbolTable.getClassName(), this.name, this.descriptor, this.code.length);
            }
            this.symbolTable.addConstantUtf8("Code");
            n += 16 + this.code.length + Handler.getExceptionTableSize(this.firstHandler);
            if (this.stackMapTableEntries != null) {
                this.symbolTable.addConstantUtf8((this.symbolTable.getMajorVersion() >= 50) ? "StackMapTable" : "StackMap");
                n += 8 + this.stackMapTableEntries.length;
            }
            if (this.lineNumberTable != null) {
                this.symbolTable.addConstantUtf8("LineNumberTable");
                n += 8 + this.lineNumberTable.length;
            }
            if (this.localVariableTable != null) {
                this.symbolTable.addConstantUtf8("LocalVariableTable");
                n += 8 + this.localVariableTable.length;
            }
            if (this.localVariableTypeTable != null) {
                this.symbolTable.addConstantUtf8("LocalVariableTypeTable");
                n += 8 + this.localVariableTypeTable.length;
            }
            if (this.lastCodeRuntimeVisibleTypeAnnotation != null) {
                n += this.lastCodeRuntimeVisibleTypeAnnotation.computeAnnotationsSize("RuntimeVisibleTypeAnnotations");
            }
            if (this.lastCodeRuntimeInvisibleTypeAnnotation != null) {
                n += this.lastCodeRuntimeInvisibleTypeAnnotation.computeAnnotationsSize("RuntimeInvisibleTypeAnnotations");
            }
            if (this.firstCodeAttribute != null) {
                n += this.firstCodeAttribute.computeAttributesSize(this.symbolTable, this.code.data, this.code.length, this.maxStack, this.maxLocals);
            }
        }
        if (this.numberOfExceptions > 0) {
            this.symbolTable.addConstantUtf8("Exceptions");
            n += 8 + 2 * this.numberOfExceptions;
        }
        int n2 = n + Attribute.computeAttributesSize(this.symbolTable, this.accessFlags, this.signatureIndex) + AnnotationWriter.computeAnnotationsSize(this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation);
        if (this.lastRuntimeVisibleParameterAnnotations != null) {
            n2 += AnnotationWriter.computeParameterAnnotationsSize("RuntimeVisibleParameterAnnotations", this.lastRuntimeVisibleParameterAnnotations, (this.visibleAnnotableParameterCount == 0) ? this.lastRuntimeVisibleParameterAnnotations.length : this.visibleAnnotableParameterCount);
        }
        if (this.lastRuntimeInvisibleParameterAnnotations != null) {
            n2 += AnnotationWriter.computeParameterAnnotationsSize("RuntimeInvisibleParameterAnnotations", this.lastRuntimeInvisibleParameterAnnotations, (this.invisibleAnnotableParameterCount == 0) ? this.lastRuntimeInvisibleParameterAnnotations.length : this.invisibleAnnotableParameterCount);
        }
        if (this.defaultValue != null) {
            this.symbolTable.addConstantUtf8("AnnotationDefault");
            n2 += 6 + this.defaultValue.length;
        }
        if (this.parameters != null) {
            this.symbolTable.addConstantUtf8("MethodParameters");
            n2 += 7 + this.parameters.length;
        }
        if (this.firstAttribute != null) {
            n2 += this.firstAttribute.computeAttributesSize(this.symbolTable);
        }
        return n2;
    }
    
    void putMethodInfo(final ByteVector byteVector) {
        final boolean b = this.symbolTable.getMajorVersion() < 49;
        byteVector.putShort(this.accessFlags & ~(b ? 4096 : 0)).putShort(this.nameIndex).putShort(this.descriptorIndex);
        if (this.sourceOffset != 0) {
            byteVector.putByteArray(this.symbolTable.getSource().classFileBuffer, this.sourceOffset, this.sourceLength);
            return;
        }
        int n = 0;
        if (this.code.length > 0) {
            ++n;
        }
        if (this.numberOfExceptions > 0) {
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
        if (this.lastRuntimeVisibleParameterAnnotations != null) {
            ++n;
        }
        if (this.lastRuntimeInvisibleParameterAnnotations != null) {
            ++n;
        }
        if (this.lastRuntimeVisibleTypeAnnotation != null) {
            ++n;
        }
        if (this.lastRuntimeInvisibleTypeAnnotation != null) {
            ++n;
        }
        if (this.defaultValue != null) {
            ++n;
        }
        if (this.parameters != null) {
            ++n;
        }
        if (this.firstAttribute != null) {
            n += this.firstAttribute.getAttributeCount();
        }
        byteVector.putShort(n);
        if (this.code.length > 0) {
            int n2 = 10 + this.code.length + Handler.getExceptionTableSize(this.firstHandler);
            int n3 = 0;
            if (this.stackMapTableEntries != null) {
                n2 += 8 + this.stackMapTableEntries.length;
                ++n3;
            }
            if (this.lineNumberTable != null) {
                n2 += 8 + this.lineNumberTable.length;
                ++n3;
            }
            if (this.localVariableTable != null) {
                n2 += 8 + this.localVariableTable.length;
                ++n3;
            }
            if (this.localVariableTypeTable != null) {
                n2 += 8 + this.localVariableTypeTable.length;
                ++n3;
            }
            if (this.lastCodeRuntimeVisibleTypeAnnotation != null) {
                n2 += this.lastCodeRuntimeVisibleTypeAnnotation.computeAnnotationsSize("RuntimeVisibleTypeAnnotations");
                ++n3;
            }
            if (this.lastCodeRuntimeInvisibleTypeAnnotation != null) {
                n2 += this.lastCodeRuntimeInvisibleTypeAnnotation.computeAnnotationsSize("RuntimeInvisibleTypeAnnotations");
                ++n3;
            }
            if (this.firstCodeAttribute != null) {
                n2 += this.firstCodeAttribute.computeAttributesSize(this.symbolTable, this.code.data, this.code.length, this.maxStack, this.maxLocals);
                n3 += this.firstCodeAttribute.getAttributeCount();
            }
            byteVector.putShort(this.symbolTable.addConstantUtf8("Code")).putInt(n2).putShort(this.maxStack).putShort(this.maxLocals).putInt(this.code.length).putByteArray(this.code.data, 0, this.code.length);
            Handler.putExceptionTable(this.firstHandler, byteVector);
            byteVector.putShort(n3);
            if (this.stackMapTableEntries != null) {
                byteVector.putShort(this.symbolTable.addConstantUtf8((this.symbolTable.getMajorVersion() >= 50) ? "StackMapTable" : "StackMap")).putInt(2 + this.stackMapTableEntries.length).putShort(this.stackMapTableNumberOfEntries).putByteArray(this.stackMapTableEntries.data, 0, this.stackMapTableEntries.length);
            }
            if (this.lineNumberTable != null) {
                byteVector.putShort(this.symbolTable.addConstantUtf8("LineNumberTable")).putInt(2 + this.lineNumberTable.length).putShort(this.lineNumberTableLength).putByteArray(this.lineNumberTable.data, 0, this.lineNumberTable.length);
            }
            if (this.localVariableTable != null) {
                byteVector.putShort(this.symbolTable.addConstantUtf8("LocalVariableTable")).putInt(2 + this.localVariableTable.length).putShort(this.localVariableTableLength).putByteArray(this.localVariableTable.data, 0, this.localVariableTable.length);
            }
            if (this.localVariableTypeTable != null) {
                byteVector.putShort(this.symbolTable.addConstantUtf8("LocalVariableTypeTable")).putInt(2 + this.localVariableTypeTable.length).putShort(this.localVariableTypeTableLength).putByteArray(this.localVariableTypeTable.data, 0, this.localVariableTypeTable.length);
            }
            if (this.lastCodeRuntimeVisibleTypeAnnotation != null) {
                this.lastCodeRuntimeVisibleTypeAnnotation.putAnnotations(this.symbolTable.addConstantUtf8("RuntimeVisibleTypeAnnotations"), byteVector);
            }
            if (this.lastCodeRuntimeInvisibleTypeAnnotation != null) {
                this.lastCodeRuntimeInvisibleTypeAnnotation.putAnnotations(this.symbolTable.addConstantUtf8("RuntimeInvisibleTypeAnnotations"), byteVector);
            }
            if (this.firstCodeAttribute != null) {
                this.firstCodeAttribute.putAttributes(this.symbolTable, this.code.data, this.code.length, this.maxStack, this.maxLocals, byteVector);
            }
        }
        if (this.numberOfExceptions > 0) {
            byteVector.putShort(this.symbolTable.addConstantUtf8("Exceptions")).putInt(2 + 2 * this.numberOfExceptions).putShort(this.numberOfExceptions);
            final int[] exceptionIndexTable = this.exceptionIndexTable;
            for (int length = exceptionIndexTable.length, i = 0; i < length; ++i) {
                byteVector.putShort(exceptionIndexTable[i]);
            }
        }
        Attribute.putAttributes(this.symbolTable, this.accessFlags, this.signatureIndex, byteVector);
        AnnotationWriter.putAnnotations(this.symbolTable, this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation, byteVector);
        if (this.lastRuntimeVisibleParameterAnnotations != null) {
            AnnotationWriter.putParameterAnnotations(this.symbolTable.addConstantUtf8("RuntimeVisibleParameterAnnotations"), this.lastRuntimeVisibleParameterAnnotations, (this.visibleAnnotableParameterCount == 0) ? this.lastRuntimeVisibleParameterAnnotations.length : this.visibleAnnotableParameterCount, byteVector);
        }
        if (this.lastRuntimeInvisibleParameterAnnotations != null) {
            AnnotationWriter.putParameterAnnotations(this.symbolTable.addConstantUtf8("RuntimeInvisibleParameterAnnotations"), this.lastRuntimeInvisibleParameterAnnotations, (this.invisibleAnnotableParameterCount == 0) ? this.lastRuntimeInvisibleParameterAnnotations.length : this.invisibleAnnotableParameterCount, byteVector);
        }
        if (this.defaultValue != null) {
            byteVector.putShort(this.symbolTable.addConstantUtf8("AnnotationDefault")).putInt(this.defaultValue.length).putByteArray(this.defaultValue.data, 0, this.defaultValue.length);
        }
        if (this.parameters != null) {
            byteVector.putShort(this.symbolTable.addConstantUtf8("MethodParameters")).putInt(1 + this.parameters.length).putByte(this.parametersCount).putByteArray(this.parameters.data, 0, this.parameters.length);
        }
        if (this.firstAttribute != null) {
            this.firstAttribute.putAttributes(this.symbolTable, byteVector);
        }
    }
    
    final void collectAttributePrototypes(final Attribute.Set set) {
        set.addAttributes(this.firstAttribute);
        set.addAttributes(this.firstCodeAttribute);
    }
    
    static {
        STACK_SIZE_DELTA = new int[] { 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 0, 0, 1, 2, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, -1, 0, -1, -1, -1, -1, -1, -2, -1, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, -4, -3, -4, -3, -3, -3, -3, -1, -2, 1, 1, 1, 2, 2, 2, 0, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -2, -1, -2, -1, -2, 0, 1, 0, 1, -1, -1, 0, 0, 1, 1, -1, 0, -1, 0, 0, 0, -3, -1, -1, -3, -3, -1, -1, -1, -1, -1, -1, -2, -2, -2, -2, -2, -2, -2, -2, 0, 1, 0, -1, -1, -1, -2, -1, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1, -1, 0, 0 };
    }
}
