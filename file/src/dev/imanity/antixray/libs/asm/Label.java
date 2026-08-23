// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

public class Label
{
    static final int FLAG_DEBUG_ONLY = 1;
    static final int FLAG_JUMP_TARGET = 2;
    static final int FLAG_RESOLVED = 4;
    static final int FLAG_REACHABLE = 8;
    static final int FLAG_SUBROUTINE_CALLER = 16;
    static final int FLAG_SUBROUTINE_START = 32;
    static final int FLAG_SUBROUTINE_END = 64;
    static final int FLAG_LINE_NUMBER = 128;
    static final int LINE_NUMBERS_CAPACITY_INCREMENT = 4;
    static final int FORWARD_REFERENCES_CAPACITY_INCREMENT = 6;
    static final int FORWARD_REFERENCE_TYPE_MASK = -268435456;
    static final int FORWARD_REFERENCE_TYPE_SHORT = 268435456;
    static final int FORWARD_REFERENCE_TYPE_WIDE = 536870912;
    static final int FORWARD_REFERENCE_TYPE_STACK_MAP = 805306368;
    static final int FORWARD_REFERENCE_HANDLE_MASK = 268435455;
    static final Label EMPTY_LIST;
    public Object info;
    short flags;
    private short lineNumber;
    private int[] otherLineNumbers;
    int bytecodeOffset;
    private int[] forwardReferences;
    short inputStackSize;
    short outputStackSize;
    short outputStackMax;
    short subroutineId;
    Frame frame;
    Label nextBasicBlock;
    Edge outgoingEdges;
    Label nextListElement;
    
    public int getOffset() {
        if ((this.flags & 0x4) == 0x0) {
            throw new IllegalStateException("Label offset position has not been resolved yet");
        }
        return this.bytecodeOffset;
    }
    
    final Label getCanonicalInstance() {
        return (this.frame == null) ? this : this.frame.owner;
    }
    
    final void addLineNumber(final int n) {
        if ((this.flags & 0x80) == 0x0) {
            this.flags |= 0x80;
            this.lineNumber = (short)n;
        }
        else {
            if (this.otherLineNumbers == null) {
                this.otherLineNumbers = new int[4];
            }
            final int n2 = ++this.otherLineNumbers[0];
            if (n2 >= this.otherLineNumbers.length) {
                final int[] otherLineNumbers = new int[this.otherLineNumbers.length + 4];
                System.arraycopy(this.otherLineNumbers, 0, otherLineNumbers, 0, this.otherLineNumbers.length);
                this.otherLineNumbers = otherLineNumbers;
            }
            this.otherLineNumbers[n2] = n;
        }
    }
    
    final void accept(final MethodVisitor methodVisitor, final boolean b) {
        methodVisitor.visitLabel(this);
        if (b && (this.flags & 0x80) != 0x0) {
            methodVisitor.visitLineNumber(this.lineNumber & 0xFFFF, this);
            if (this.otherLineNumbers != null) {
                for (int i = 1; i <= this.otherLineNumbers[0]; ++i) {
                    methodVisitor.visitLineNumber(this.otherLineNumbers[i], this);
                }
            }
        }
    }
    
    final void put(final ByteVector byteVector, final int n, final boolean b) {
        if ((this.flags & 0x4) == 0x0) {
            if (b) {
                this.addForwardReference(n, 536870912, byteVector.length);
                byteVector.putInt(-1);
            }
            else {
                this.addForwardReference(n, 268435456, byteVector.length);
                byteVector.putShort(-1);
            }
        }
        else if (b) {
            byteVector.putInt(this.bytecodeOffset - n);
        }
        else {
            byteVector.putShort(this.bytecodeOffset - n);
        }
    }
    
    final void put(final ByteVector byteVector) {
        if ((this.flags & 0x4) == 0x0) {
            this.addForwardReference(0, 805306368, byteVector.length);
        }
        byteVector.putShort(this.bytecodeOffset);
    }
    
    private void addForwardReference(final int n, final int n2, final int n3) {
        if (this.forwardReferences == null) {
            this.forwardReferences = new int[6];
        }
        int n4 = this.forwardReferences[0];
        if (n4 + 2 >= this.forwardReferences.length) {
            final int[] forwardReferences = new int[this.forwardReferences.length + 6];
            System.arraycopy(this.forwardReferences, 0, forwardReferences, 0, this.forwardReferences.length);
            this.forwardReferences = forwardReferences;
        }
        this.forwardReferences[++n4] = n;
        this.forwardReferences[++n4] = (n2 | n3);
        this.forwardReferences[0] = n4;
    }
    
    final boolean resolve(final byte[] array, final ByteVector byteVector, final int bytecodeOffset) {
        this.flags |= 0x4;
        this.bytecodeOffset = bytecodeOffset;
        if (this.forwardReferences == null) {
            return false;
        }
        boolean b = false;
        for (int i = this.forwardReferences[0]; i > 0; i -= 2) {
            final int n = this.forwardReferences[i - 1];
            final int n2 = this.forwardReferences[i];
            final int n3 = bytecodeOffset - n;
            int n4 = n2 & 0xFFFFFFF;
            if ((n2 & 0xF0000000) == 0x10000000) {
                if (n3 < -32768 || n3 > 32767) {
                    final int n5 = array[n] & 0xFF;
                    if (n5 < 198) {
                        array[n] = (byte)(n5 + 49);
                    }
                    else {
                        array[n] = (byte)(n5 + 20);
                    }
                    b = true;
                }
                array[n4++] = (byte)(n3 >>> 8);
                array[n4] = (byte)n3;
            }
            else if ((n2 & 0xF0000000) == 0x20000000) {
                array[n4++] = (byte)(n3 >>> 24);
                array[n4++] = (byte)(n3 >>> 16);
                array[n4++] = (byte)(n3 >>> 8);
                array[n4] = (byte)n3;
            }
            else {
                byteVector.data[n4++] = (byte)(bytecodeOffset >>> 8);
                byteVector.data[n4] = (byte)bytecodeOffset;
            }
        }
        return b;
    }
    
    final void markSubroutine(final short subroutineId) {
        Label label = this;
        label.nextListElement = Label.EMPTY_LIST;
        while (label != Label.EMPTY_LIST) {
            final Label label2 = label;
            label = label.nextListElement;
            label2.nextListElement = null;
            if (label2.subroutineId == 0) {
                label2.subroutineId = subroutineId;
                label = label2.pushSuccessors(label);
            }
        }
    }
    
    final void addSubroutineRetSuccessors(final Label label) {
        Label empty_LIST = Label.EMPTY_LIST;
        Label pushSuccessors = this;
        pushSuccessors.nextListElement = Label.EMPTY_LIST;
        while (pushSuccessors != Label.EMPTY_LIST) {
            final Label label2 = pushSuccessors;
            final Label nextListElement = label2.nextListElement;
            label2.nextListElement = empty_LIST;
            empty_LIST = label2;
            if ((label2.flags & 0x40) != 0x0 && label2.subroutineId != label.subroutineId) {
                label2.outgoingEdges = new Edge(label2.outputStackSize, label.outgoingEdges.successor, label2.outgoingEdges);
            }
            pushSuccessors = label2.pushSuccessors(nextListElement);
        }
        while (empty_LIST != Label.EMPTY_LIST) {
            final Label nextListElement2 = empty_LIST.nextListElement;
            empty_LIST.nextListElement = null;
            empty_LIST = nextListElement2;
        }
    }
    
    private Label pushSuccessors(final Label label) {
        Label successor = label;
        for (Edge edge = this.outgoingEdges; edge != null; edge = edge.nextEdge) {
            if (((this.flags & 0x10) == 0x0 || edge != this.outgoingEdges.nextEdge) && edge.successor.nextListElement == null) {
                edge.successor.nextListElement = successor;
                successor = edge.successor;
            }
        }
        return successor;
    }
    
    @Override
    public String toString() {
        return stringConcat$0(System.identityHashCode(this));
    }
    
    private static /* synthetic */ String stringConcat$0(final int i) {
        return "L" + i;
    }
    
    static {
        EMPTY_LIST = new Label();
    }
}
