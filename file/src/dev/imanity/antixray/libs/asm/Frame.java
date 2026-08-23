// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

class Frame
{
    static final int SAME_FRAME = 0;
    static final int SAME_LOCALS_1_STACK_ITEM_FRAME = 64;
    static final int RESERVED = 128;
    static final int SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED = 247;
    static final int CHOP_FRAME = 248;
    static final int SAME_FRAME_EXTENDED = 251;
    static final int APPEND_FRAME = 252;
    static final int FULL_FRAME = 255;
    static final int ITEM_TOP = 0;
    static final int ITEM_INTEGER = 1;
    static final int ITEM_FLOAT = 2;
    static final int ITEM_DOUBLE = 3;
    static final int ITEM_LONG = 4;
    static final int ITEM_NULL = 5;
    static final int ITEM_UNINITIALIZED_THIS = 6;
    static final int ITEM_OBJECT = 7;
    static final int ITEM_UNINITIALIZED = 8;
    private static final int ITEM_ASM_BOOLEAN = 9;
    private static final int ITEM_ASM_BYTE = 10;
    private static final int ITEM_ASM_CHAR = 11;
    private static final int ITEM_ASM_SHORT = 12;
    private static final int DIM_SIZE = 6;
    private static final int KIND_SIZE = 4;
    private static final int FLAGS_SIZE = 2;
    private static final int VALUE_SIZE = 20;
    private static final int DIM_SHIFT = 26;
    private static final int KIND_SHIFT = 22;
    private static final int FLAGS_SHIFT = 20;
    private static final int DIM_MASK = -67108864;
    private static final int KIND_MASK = 62914560;
    private static final int VALUE_MASK = 1048575;
    private static final int ARRAY_OF = 67108864;
    private static final int ELEMENT_OF = -67108864;
    private static final int CONSTANT_KIND = 4194304;
    private static final int REFERENCE_KIND = 8388608;
    private static final int UNINITIALIZED_KIND = 12582912;
    private static final int FORWARD_UNINITIALIZED_KIND = 16777216;
    private static final int LOCAL_KIND = 20971520;
    private static final int STACK_KIND = 25165824;
    private static final int TOP_IF_LONG_OR_DOUBLE_FLAG = 1048576;
    private static final int TOP = 4194304;
    private static final int BOOLEAN = 4194313;
    private static final int BYTE = 4194314;
    private static final int CHAR = 4194315;
    private static final int SHORT = 4194316;
    private static final int INTEGER = 4194305;
    private static final int FLOAT = 4194306;
    private static final int LONG = 4194308;
    private static final int DOUBLE = 4194307;
    private static final int NULL = 4194309;
    private static final int UNINITIALIZED_THIS = 4194310;
    Label owner;
    private int[] inputLocals;
    private int[] inputStack;
    private int[] outputLocals;
    private int[] outputStack;
    private short outputStackStart;
    private short outputStackTop;
    private int initializationCount;
    private int[] initializations;
    
    Frame(final Label owner) {
        this.owner = owner;
    }
    
    final void copyFrom(final Frame frame) {
        this.inputLocals = frame.inputLocals;
        this.inputStack = frame.inputStack;
        this.outputStackStart = 0;
        this.outputLocals = frame.outputLocals;
        this.outputStack = frame.outputStack;
        this.outputStackTop = frame.outputStackTop;
        this.initializationCount = frame.initializationCount;
        this.initializations = frame.initializations;
    }
    
    static int getAbstractTypeFromApiFormat(final SymbolTable symbolTable, final Object o) {
        if (o instanceof Integer) {
            return 0x400000 | (int)o;
        }
        if (o instanceof String) {
            return getAbstractTypeFromDescriptor(symbolTable, Type.getObjectType((String)o).getDescriptor(), 0);
        }
        final Label label = (Label)o;
        if ((label.flags & 0x4) != 0x0) {
            return 0xC00000 | symbolTable.addUninitializedType("", label.bytecodeOffset);
        }
        return 0x1000000 | symbolTable.addForwardUninitializedType("", label);
    }
    
    static int getAbstractTypeFromInternalName(final SymbolTable symbolTable, final String s) {
        return 0x800000 | symbolTable.addType(s);
    }
    
    private static int getAbstractTypeFromDescriptor(final SymbolTable symbolTable, final String s, final int n) {
        switch (s.charAt(n)) {
            case 'V': {
                return 0;
            }
            case 'B':
            case 'C':
            case 'I':
            case 'S':
            case 'Z': {
                return 4194305;
            }
            case 'F': {
                return 4194306;
            }
            case 'J': {
                return 4194308;
            }
            case 'D': {
                return 4194307;
            }
            case 'L': {
                return 0x800000 | symbolTable.addType(s.substring(n + 1, s.length() - 1));
            }
            case '[': {
                int beginIndex;
                for (beginIndex = n + 1; s.charAt(beginIndex) == '['; ++beginIndex) {}
                int n2 = 0;
                switch (s.charAt(beginIndex)) {
                    case 'Z': {
                        n2 = 4194313;
                        break;
                    }
                    case 'C': {
                        n2 = 4194315;
                        break;
                    }
                    case 'B': {
                        n2 = 4194314;
                        break;
                    }
                    case 'S': {
                        n2 = 4194316;
                        break;
                    }
                    case 'I': {
                        n2 = 4194305;
                        break;
                    }
                    case 'F': {
                        n2 = 4194306;
                        break;
                    }
                    case 'J': {
                        n2 = 4194308;
                        break;
                    }
                    case 'D': {
                        n2 = 4194307;
                        break;
                    }
                    case 'L': {
                        n2 = (0x800000 | symbolTable.addType(s.substring(beginIndex + 1, s.length() - 1)));
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException(stringConcat$0(s.substring(beginIndex)));
                    }
                }
                return beginIndex - n << 26 | n2;
            }
            default: {
                throw new IllegalArgumentException(stringConcat$1(s.substring(n)));
            }
        }
    }
    
    private static /* synthetic */ String stringConcat$0(final String str) {
        return "Invalid descriptor fragment: " + str;
    }
    
    private static /* synthetic */ String stringConcat$1(final String str) {
        return "Invalid descriptor: " + str;
    }
    
    final void setInputFrameFromDescriptor(final SymbolTable symbolTable, final int n, final String s, final int n2) {
        this.inputLocals = new int[n2];
        this.inputStack = new int[0];
        int i = 0;
        if ((n & 0x8) == 0x0) {
            if ((n & 0x40000) == 0x0) {
                this.inputLocals[i++] = (0x800000 | symbolTable.addType(symbolTable.getClassName()));
            }
            else {
                this.inputLocals[i++] = 4194310;
            }
        }
        final Type[] argumentTypes = Type.getArgumentTypes(s);
        for (int length = argumentTypes.length, j = 0; j < length; ++j) {
            final int abstractTypeFromDescriptor = getAbstractTypeFromDescriptor(symbolTable, argumentTypes[j].getDescriptor(), 0);
            this.inputLocals[i++] = abstractTypeFromDescriptor;
            if (abstractTypeFromDescriptor == 4194308 || abstractTypeFromDescriptor == 4194307) {
                this.inputLocals[i++] = 4194304;
            }
        }
        while (i < n2) {
            this.inputLocals[i++] = 4194304;
        }
    }
    
    final void setInputFrameFromApiFormat(final SymbolTable symbolTable, final int n, final Object[] array, final int n2, final Object[] array2) {
        int i = 0;
        for (int j = 0; j < n; ++j) {
            this.inputLocals[i++] = getAbstractTypeFromApiFormat(symbolTable, array[j]);
            if (array[j] == Opcodes.LONG || array[j] == Opcodes.DOUBLE) {
                this.inputLocals[i++] = 4194304;
            }
        }
        while (i < this.inputLocals.length) {
            this.inputLocals[i++] = 4194304;
        }
        int n3 = 0;
        for (int k = 0; k < n2; ++k) {
            if (array2[k] == Opcodes.LONG || array2[k] == Opcodes.DOUBLE) {
                ++n3;
            }
        }
        this.inputStack = new int[n2 + n3];
        int n4 = 0;
        for (int l = 0; l < n2; ++l) {
            this.inputStack[n4++] = getAbstractTypeFromApiFormat(symbolTable, array2[l]);
            if (array2[l] == Opcodes.LONG || array2[l] == Opcodes.DOUBLE) {
                this.inputStack[n4++] = 4194304;
            }
        }
        this.outputStackTop = 0;
        this.initializationCount = 0;
    }
    
    final int getInputStackSize() {
        return this.inputStack.length;
    }
    
    private int getLocal(final int n) {
        if (this.outputLocals == null || n >= this.outputLocals.length) {
            return 0x1400000 | n;
        }
        int n2 = this.outputLocals[n];
        if (n2 == 0) {
            final int[] outputLocals = this.outputLocals;
            final int n3 = 0x1400000 | n;
            outputLocals[n] = n3;
            n2 = n3;
        }
        return n2;
    }
    
    private void setLocal(final int n, final int n2) {
        if (this.outputLocals == null) {
            this.outputLocals = new int[10];
        }
        final int length = this.outputLocals.length;
        if (n >= length) {
            final int[] outputLocals = new int[Math.max(n + 1, 2 * length)];
            System.arraycopy(this.outputLocals, 0, outputLocals, 0, length);
            this.outputLocals = outputLocals;
        }
        this.outputLocals[n] = n2;
    }
    
    private void push(final int n) {
        if (this.outputStack == null) {
            this.outputStack = new int[10];
        }
        final int length = this.outputStack.length;
        if (this.outputStackTop >= length) {
            final int[] outputStack = new int[Math.max(this.outputStackTop + 1, 2 * length)];
            System.arraycopy(this.outputStack, 0, outputStack, 0, length);
            this.outputStack = outputStack;
        }
        final int[] outputStack2 = this.outputStack;
        final short outputStackTop = this.outputStackTop;
        this.outputStackTop = (short)(outputStackTop + 1);
        outputStack2[outputStackTop] = n;
        final short outputStackMax = (short)(this.outputStackStart + this.outputStackTop);
        if (outputStackMax > this.owner.outputStackMax) {
            this.owner.outputStackMax = outputStackMax;
        }
    }
    
    private void push(final SymbolTable symbolTable, final String s) {
        final int abstractTypeFromDescriptor = getAbstractTypeFromDescriptor(symbolTable, s, (s.charAt(0) == '(') ? Type.getReturnTypeOffset(s) : 0);
        if (abstractTypeFromDescriptor != 0) {
            this.push(abstractTypeFromDescriptor);
            if (abstractTypeFromDescriptor == 4194308 || abstractTypeFromDescriptor == 4194307) {
                this.push(4194304);
            }
        }
    }
    
    private int pop() {
        if (this.outputStackTop > 0) {
            final int[] outputStack = this.outputStack;
            final short outputStackTop = (short)(this.outputStackTop - 1);
            this.outputStackTop = outputStackTop;
            return outputStack[outputStackTop];
        }
        return 0x1800000 | -(--this.outputStackStart);
    }
    
    private void pop(final int n) {
        if (this.outputStackTop >= n) {
            this.outputStackTop -= (short)n;
        }
        else {
            this.outputStackStart -= (short)(n - this.outputStackTop);
            this.outputStackTop = 0;
        }
    }
    
    private void pop(final String s) {
        final char char1 = s.charAt(0);
        if (char1 == '(') {
            this.pop((Type.getArgumentsAndReturnSizes(s) >> 2) - 1);
        }
        else if (char1 == 'J' || char1 == 'D') {
            this.pop(2);
        }
        else {
            this.pop(1);
        }
    }
    
    private void addInitializedType(final int n) {
        if (this.initializations == null) {
            this.initializations = new int[2];
        }
        final int length = this.initializations.length;
        if (this.initializationCount >= length) {
            final int[] initializations = new int[Math.max(this.initializationCount + 1, 2 * length)];
            System.arraycopy(this.initializations, 0, initializations, 0, length);
            this.initializations = initializations;
        }
        this.initializations[this.initializationCount++] = n;
    }
    
    private int getInitializedType(final SymbolTable symbolTable, final int n) {
        if (n == 4194310 || (n & 0xFFC00000) == 0xC00000 || (n & 0xFFC00000) == 0x1000000) {
            int i = 0;
            while (i < this.initializationCount) {
                int n2 = this.initializations[i];
                final int n3 = n2 & 0xFC000000;
                final int n4 = n2 & 0x3C00000;
                final int n5 = n2 & 0xFFFFF;
                if (n4 == 20971520) {
                    n2 = n3 + this.inputLocals[n5];
                }
                else if (n4 == 25165824) {
                    n2 = n3 + this.inputStack[this.inputStack.length - n5];
                }
                if (n == n2) {
                    if (n == 4194310) {
                        return 0x800000 | symbolTable.addType(symbolTable.getClassName());
                    }
                    return 0x800000 | symbolTable.addType(symbolTable.getType(n & 0xFFFFF).value);
                }
                else {
                    ++i;
                }
            }
        }
        return n;
    }
    
    void execute(final int n, final int n2, final Symbol symbol, final SymbolTable symbolTable) {
        Label_2302: {
            switch (n) {
                case 0:
                case 116:
                case 117:
                case 118:
                case 119:
                case 145:
                case 146:
                case 147:
                case 167:
                case 177: {
                    break;
                }
                case 1: {
                    this.push(4194309);
                    break;
                }
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 16:
                case 17:
                case 21: {
                    this.push(4194305);
                    break;
                }
                case 9:
                case 10:
                case 22: {
                    this.push(4194308);
                    this.push(4194304);
                    break;
                }
                case 11:
                case 12:
                case 13:
                case 23: {
                    this.push(4194306);
                    break;
                }
                case 14:
                case 15:
                case 24: {
                    this.push(4194307);
                    this.push(4194304);
                    break;
                }
                case 18: {
                    switch (symbol.tag) {
                        case 3: {
                            this.push(4194305);
                            break Label_2302;
                        }
                        case 5: {
                            this.push(4194308);
                            this.push(4194304);
                            break Label_2302;
                        }
                        case 4: {
                            this.push(4194306);
                            break Label_2302;
                        }
                        case 6: {
                            this.push(4194307);
                            this.push(4194304);
                            break Label_2302;
                        }
                        case 7: {
                            this.push(0x800000 | symbolTable.addType("java/lang/Class"));
                            break Label_2302;
                        }
                        case 8: {
                            this.push(0x800000 | symbolTable.addType("java/lang/String"));
                            break Label_2302;
                        }
                        case 16: {
                            this.push(0x800000 | symbolTable.addType("java/lang/invoke/MethodType"));
                            break Label_2302;
                        }
                        case 15: {
                            this.push(0x800000 | symbolTable.addType("java/lang/invoke/MethodHandle"));
                            break Label_2302;
                        }
                        case 17: {
                            this.push(symbolTable, symbol.value);
                            break Label_2302;
                        }
                        default: {
                            throw new AssertionError();
                        }
                    }
                    break;
                }
                case 25: {
                    this.push(this.getLocal(n2));
                    break;
                }
                case 47:
                case 143: {
                    this.pop(2);
                    this.push(4194308);
                    this.push(4194304);
                    break;
                }
                case 49:
                case 138: {
                    this.pop(2);
                    this.push(4194307);
                    this.push(4194304);
                    break;
                }
                case 50: {
                    this.pop(1);
                    final int pop = this.pop();
                    this.push((pop == 4194309) ? pop : (-67108864 + pop));
                    break;
                }
                case 54:
                case 56:
                case 58: {
                    this.setLocal(n2, this.pop());
                    if (n2 > 0) {
                        final int local = this.getLocal(n2 - 1);
                        if (local == 4194308 || local == 4194307) {
                            this.setLocal(n2 - 1, 4194304);
                        }
                        else if ((local & 0x3C00000) == 0x1400000 || (local & 0x3C00000) == 0x1800000) {
                            this.setLocal(n2 - 1, local | 0x100000);
                        }
                        break;
                    }
                    break;
                }
                case 55:
                case 57: {
                    this.pop(1);
                    this.setLocal(n2, this.pop());
                    this.setLocal(n2 + 1, 4194304);
                    if (n2 > 0) {
                        final int local2 = this.getLocal(n2 - 1);
                        if (local2 == 4194308 || local2 == 4194307) {
                            this.setLocal(n2 - 1, 4194304);
                        }
                        else if ((local2 & 0x3C00000) == 0x1400000 || (local2 & 0x3C00000) == 0x1800000) {
                            this.setLocal(n2 - 1, local2 | 0x100000);
                        }
                        break;
                    }
                    break;
                }
                case 79:
                case 81:
                case 83:
                case 84:
                case 85:
                case 86: {
                    this.pop(3);
                    break;
                }
                case 80:
                case 82: {
                    this.pop(4);
                    break;
                }
                case 87:
                case 153:
                case 154:
                case 155:
                case 156:
                case 157:
                case 158:
                case 170:
                case 171:
                case 172:
                case 174:
                case 176:
                case 191:
                case 194:
                case 195:
                case 198:
                case 199: {
                    this.pop(1);
                    break;
                }
                case 88:
                case 159:
                case 160:
                case 161:
                case 162:
                case 163:
                case 164:
                case 165:
                case 166:
                case 173:
                case 175: {
                    this.pop(2);
                    break;
                }
                case 89: {
                    final int pop2 = this.pop();
                    this.push(pop2);
                    this.push(pop2);
                    break;
                }
                case 90: {
                    final int pop3 = this.pop();
                    final int pop4 = this.pop();
                    this.push(pop3);
                    this.push(pop4);
                    this.push(pop3);
                    break;
                }
                case 91: {
                    final int pop5 = this.pop();
                    final int pop6 = this.pop();
                    final int pop7 = this.pop();
                    this.push(pop5);
                    this.push(pop7);
                    this.push(pop6);
                    this.push(pop5);
                    break;
                }
                case 92: {
                    final int pop8 = this.pop();
                    final int pop9 = this.pop();
                    this.push(pop9);
                    this.push(pop8);
                    this.push(pop9);
                    this.push(pop8);
                    break;
                }
                case 93: {
                    final int pop10 = this.pop();
                    final int pop11 = this.pop();
                    final int pop12 = this.pop();
                    this.push(pop11);
                    this.push(pop10);
                    this.push(pop12);
                    this.push(pop11);
                    this.push(pop10);
                    break;
                }
                case 94: {
                    final int pop13 = this.pop();
                    final int pop14 = this.pop();
                    final int pop15 = this.pop();
                    final int pop16 = this.pop();
                    this.push(pop14);
                    this.push(pop13);
                    this.push(pop16);
                    this.push(pop15);
                    this.push(pop14);
                    this.push(pop13);
                    break;
                }
                case 95: {
                    final int pop17 = this.pop();
                    final int pop18 = this.pop();
                    this.push(pop17);
                    this.push(pop18);
                    break;
                }
                case 46:
                case 51:
                case 52:
                case 53:
                case 96:
                case 100:
                case 104:
                case 108:
                case 112:
                case 120:
                case 122:
                case 124:
                case 126:
                case 128:
                case 130:
                case 136:
                case 142:
                case 149:
                case 150: {
                    this.pop(2);
                    this.push(4194305);
                    break;
                }
                case 97:
                case 101:
                case 105:
                case 109:
                case 113:
                case 127:
                case 129:
                case 131: {
                    this.pop(4);
                    this.push(4194308);
                    this.push(4194304);
                    break;
                }
                case 48:
                case 98:
                case 102:
                case 106:
                case 110:
                case 114:
                case 137:
                case 144: {
                    this.pop(2);
                    this.push(4194306);
                    break;
                }
                case 99:
                case 103:
                case 107:
                case 111:
                case 115: {
                    this.pop(4);
                    this.push(4194307);
                    this.push(4194304);
                    break;
                }
                case 121:
                case 123:
                case 125: {
                    this.pop(3);
                    this.push(4194308);
                    this.push(4194304);
                    break;
                }
                case 132: {
                    this.setLocal(n2, 4194305);
                    break;
                }
                case 133:
                case 140: {
                    this.pop(1);
                    this.push(4194308);
                    this.push(4194304);
                    break;
                }
                case 134: {
                    this.pop(1);
                    this.push(4194306);
                    break;
                }
                case 135:
                case 141: {
                    this.pop(1);
                    this.push(4194307);
                    this.push(4194304);
                    break;
                }
                case 139:
                case 190:
                case 193: {
                    this.pop(1);
                    this.push(4194305);
                    break;
                }
                case 148:
                case 151:
                case 152: {
                    this.pop(4);
                    this.push(4194305);
                    break;
                }
                case 168:
                case 169: {
                    throw new IllegalArgumentException("JSR/RET are not supported with computeFrames option");
                }
                case 178: {
                    this.push(symbolTable, symbol.value);
                    break;
                }
                case 179: {
                    this.pop(symbol.value);
                    break;
                }
                case 180: {
                    this.pop(1);
                    this.push(symbolTable, symbol.value);
                    break;
                }
                case 181: {
                    this.pop(symbol.value);
                    this.pop();
                    break;
                }
                case 182:
                case 183:
                case 184:
                case 185: {
                    this.pop(symbol.value);
                    if (n != 184) {
                        final int pop19 = this.pop();
                        if (n == 183 && symbol.name.charAt(0) == '<') {
                            this.addInitializedType(pop19);
                        }
                    }
                    this.push(symbolTable, symbol.value);
                    break;
                }
                case 186: {
                    this.pop(symbol.value);
                    this.push(symbolTable, symbol.value);
                    break;
                }
                case 187: {
                    this.push(0xC00000 | symbolTable.addUninitializedType(symbol.value, n2));
                    break;
                }
                case 188: {
                    this.pop();
                    switch (n2) {
                        case 4: {
                            this.push(71303177);
                            break Label_2302;
                        }
                        case 5: {
                            this.push(71303179);
                            break Label_2302;
                        }
                        case 8: {
                            this.push(71303178);
                            break Label_2302;
                        }
                        case 9: {
                            this.push(71303180);
                            break Label_2302;
                        }
                        case 10: {
                            this.push(71303169);
                            break Label_2302;
                        }
                        case 6: {
                            this.push(71303170);
                            break Label_2302;
                        }
                        case 7: {
                            this.push(71303171);
                            break Label_2302;
                        }
                        case 11: {
                            this.push(71303172);
                            break Label_2302;
                        }
                        default: {
                            throw new IllegalArgumentException();
                        }
                    }
                    break;
                }
                case 189: {
                    final String value = symbol.value;
                    this.pop();
                    if (value.charAt(0) == '[') {
                        this.push(symbolTable, stringConcat$2(value));
                        break;
                    }
                    this.push(0x4800000 | symbolTable.addType(value));
                    break;
                }
                case 192: {
                    final String value2 = symbol.value;
                    this.pop();
                    if (value2.charAt(0) == '[') {
                        this.push(symbolTable, value2);
                        break;
                    }
                    this.push(0x800000 | symbolTable.addType(value2));
                    break;
                }
                case 197: {
                    this.pop(n2);
                    this.push(symbolTable, symbol.value);
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }
    }
    
    private static /* synthetic */ String stringConcat$2(final String str) {
        return "[" + str;
    }
    
    private int getConcreteOutputType(final int n, final int n2) {
        final int n3 = n & 0xFC000000;
        final int n4 = n & 0x3C00000;
        if (n4 == 20971520) {
            int n5 = n3 + this.inputLocals[n & 0xFFFFF];
            if ((n & 0x100000) != 0x0 && (n5 == 4194308 || n5 == 4194307)) {
                n5 = 4194304;
            }
            return n5;
        }
        if (n4 == 25165824) {
            int n6 = n3 + this.inputStack[n2 - (n & 0xFFFFF)];
            if ((n & 0x100000) != 0x0 && (n6 == 4194308 || n6 == 4194307)) {
                n6 = 4194304;
            }
            return n6;
        }
        return n;
    }
    
    final boolean merge(final SymbolTable symbolTable, final Frame frame, final int n) {
        boolean b = false;
        final int length = this.inputLocals.length;
        final int length2 = this.inputStack.length;
        if (frame.inputLocals == null) {
            frame.inputLocals = new int[length];
            b = true;
        }
        for (int i = 0; i < length; ++i) {
            int n3;
            if (this.outputLocals != null && i < this.outputLocals.length) {
                final int n2 = this.outputLocals[i];
                if (n2 == 0) {
                    n3 = this.inputLocals[i];
                }
                else {
                    n3 = this.getConcreteOutputType(n2, length2);
                }
            }
            else {
                n3 = this.inputLocals[i];
            }
            if (this.initializations != null) {
                n3 = this.getInitializedType(symbolTable, n3);
            }
            b |= merge(symbolTable, n3, frame.inputLocals, i);
        }
        if (n > 0) {
            for (int j = 0; j < length; ++j) {
                b |= merge(symbolTable, this.inputLocals[j], frame.inputLocals, j);
            }
            if (frame.inputStack == null) {
                frame.inputStack = new int[1];
                b = true;
            }
            return b | merge(symbolTable, n, frame.inputStack, 0);
        }
        final int n4 = this.inputStack.length + this.outputStackStart;
        if (frame.inputStack == null) {
            frame.inputStack = new int[n4 + this.outputStackTop];
            b = true;
        }
        for (short n5 = 0; n5 < n4; ++n5) {
            int initializedType = this.inputStack[n5];
            if (this.initializations != null) {
                initializedType = this.getInitializedType(symbolTable, initializedType);
            }
            b |= merge(symbolTable, initializedType, frame.inputStack, n5);
        }
        for (short n6 = 0; n6 < this.outputStackTop; ++n6) {
            int n7 = this.getConcreteOutputType(this.outputStack[n6], length2);
            if (this.initializations != null) {
                n7 = this.getInitializedType(symbolTable, n7);
            }
            b |= merge(symbolTable, n7, frame.inputStack, n4 + n6);
        }
        return b;
    }
    
    private static boolean merge(final SymbolTable symbolTable, final int n, final int[] array, final int n2) {
        final int n3 = array[n2];
        if (n3 == n) {
            return false;
        }
        int n4 = n;
        if ((n & 0x3FFFFFF) == 0x400005) {
            if (n3 == 4194309) {
                return false;
            }
            n4 = 4194309;
        }
        if (n3 == 0) {
            array[n2] = n4;
            return true;
        }
        int n5;
        if ((n3 & 0xFC000000) != 0x0 || (n3 & 0x3C00000) == 0x800000) {
            if (n4 == 4194309) {
                return false;
            }
            if ((n4 & 0xFFC00000) == (n3 & 0xFFC00000)) {
                if ((n3 & 0x3C00000) == 0x800000) {
                    n5 = ((n4 & 0xFC000000) | 0x800000 | symbolTable.addMergedType(n4 & 0xFFFFF, n3 & 0xFFFFF));
                }
                else {
                    n5 = (-67108864 + (n4 & 0xFC000000) | 0x800000 | symbolTable.addType("java/lang/Object"));
                }
            }
            else if ((n4 & 0xFC000000) != 0x0 || (n4 & 0x3C00000) == 0x800000) {
                int a = n4 & 0xFC000000;
                if (a != 0 && (n4 & 0x3C00000) != 0x800000) {
                    a -= 67108864;
                }
                int b = n3 & 0xFC000000;
                if (b != 0 && (n3 & 0x3C00000) != 0x800000) {
                    b -= 67108864;
                }
                n5 = (Math.min(a, b) | 0x800000 | symbolTable.addType("java/lang/Object"));
            }
            else {
                n5 = 4194304;
            }
        }
        else if (n3 == 4194309) {
            n5 = (((n4 & 0xFC000000) != 0x0 || (n4 & 0x3C00000) == 0x800000) ? n4 : 4194304);
        }
        else {
            n5 = 4194304;
        }
        if (n5 != n3) {
            array[n2] = n5;
            return true;
        }
        return false;
    }
    
    final void accept(final MethodWriter methodWriter) {
        final int[] inputLocals = this.inputLocals;
        int n = 0;
        int n2 = 0;
        int i = 0;
        while (i < inputLocals.length) {
            final int n3 = inputLocals[i];
            i += ((n3 == 4194308 || n3 == 4194307) ? 2 : 1);
            if (n3 == 4194304) {
                ++n2;
            }
            else {
                n += n2 + 1;
                n2 = 0;
            }
        }
        final int[] inputStack = this.inputStack;
        int n4 = 0;
        int n5;
        for (int j = 0; j < inputStack.length; j += ((n5 == 4194308 || n5 == 4194307) ? 2 : 1), ++n4) {
            n5 = inputStack[j];
        }
        int visitFrameStart = methodWriter.visitFrameStart(this.owner.bytecodeOffset, n, n4);
        int n6 = 0;
        while (n-- > 0) {
            final int n7 = inputLocals[n6];
            n6 += ((n7 == 4194308 || n7 == 4194307) ? 2 : 1);
            methodWriter.visitAbstractType(visitFrameStart++, n7);
        }
        int n8 = 0;
        while (n4-- > 0) {
            final int n9 = inputStack[n8];
            n8 += ((n9 == 4194308 || n9 == 4194307) ? 2 : 1);
            methodWriter.visitAbstractType(visitFrameStart++, n9);
        }
        methodWriter.visitFrameEnd();
    }
    
    static void putAbstractType(final SymbolTable symbolTable, final int n, final ByteVector byteVector) {
        int n2 = (n & 0xFC000000) >> 26;
        if (n2 == 0) {
            final int n3 = n & 0xFFFFF;
            switch (n & 0x3C00000) {
                case 4194304: {
                    byteVector.putByte(n3);
                    break;
                }
                case 8388608: {
                    byteVector.putByte(7).putShort(symbolTable.addConstantClass(symbolTable.getType(n3).value).index);
                    break;
                }
                case 12582912: {
                    byteVector.putByte(8).putShort((int)symbolTable.getType(n3).data);
                    break;
                }
                case 16777216: {
                    byteVector.putByte(8);
                    symbolTable.getForwardUninitializedLabel(n3).put(byteVector);
                    break;
                }
                default: {
                    throw new AssertionError();
                }
            }
        }
        else {
            final StringBuilder sb = new StringBuilder();
            while (n2-- > 0) {
                sb.append('[');
            }
            if ((n & 0x3C00000) == 0x800000) {
                sb.append('L').append(symbolTable.getType(n & 0xFFFFF).value).append(';');
            }
            else {
                switch (n & 0xFFFFF) {
                    case 9: {
                        sb.append('Z');
                        break;
                    }
                    case 10: {
                        sb.append('B');
                        break;
                    }
                    case 11: {
                        sb.append('C');
                        break;
                    }
                    case 12: {
                        sb.append('S');
                        break;
                    }
                    case 1: {
                        sb.append('I');
                        break;
                    }
                    case 2: {
                        sb.append('F');
                        break;
                    }
                    case 4: {
                        sb.append('J');
                        break;
                    }
                    case 3: {
                        sb.append('D');
                        break;
                    }
                    default: {
                        throw new AssertionError();
                    }
                }
            }
            byteVector.putByte(7).putShort(symbolTable.addConstantClass(sb.toString()).index);
        }
    }
}
