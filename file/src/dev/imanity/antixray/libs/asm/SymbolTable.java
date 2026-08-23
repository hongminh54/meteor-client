// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

final class SymbolTable
{
    final ClassWriter classWriter;
    private final ClassReader sourceClassReader;
    private int majorVersion;
    private String className;
    private int entryCount;
    private Entry[] entries;
    private int constantPoolCount;
    private ByteVector constantPool;
    private int bootstrapMethodCount;
    private ByteVector bootstrapMethods;
    private int typeCount;
    private Entry[] typeTable;
    private int labelCount;
    private LabelEntry[] labelTable;
    private LabelEntry[] labelEntries;
    
    SymbolTable(final ClassWriter classWriter) {
        this.classWriter = classWriter;
        this.sourceClassReader = null;
        this.entries = new Entry[256];
        this.constantPoolCount = 1;
        this.constantPool = new ByteVector();
    }
    
    SymbolTable(final ClassWriter classWriter, final ClassReader sourceClassReader) {
        this.classWriter = classWriter;
        this.sourceClassReader = sourceClassReader;
        final byte[] classFileBuffer = sourceClassReader.classFileBuffer;
        final int n = sourceClassReader.getItem(1) - 1;
        final int n2 = sourceClassReader.header - n;
        this.constantPoolCount = sourceClassReader.getItemCount();
        (this.constantPool = new ByteVector(n2)).putByteArray(classFileBuffer, n, n2);
        this.entries = new Entry[this.constantPoolCount * 2];
        final char[] array = new char[sourceClassReader.getMaxStringLength()];
        boolean b = false;
        byte b2;
        for (int i = 1; i < this.constantPoolCount; i += ((b2 == 5 || b2 == 6) ? 2 : 1)) {
            final int item = sourceClassReader.getItem(i);
            b2 = classFileBuffer[item - 1];
            switch (b2) {
                case 9:
                case 10:
                case 11: {
                    final int item2 = sourceClassReader.getItem(sourceClassReader.readUnsignedShort(item + 2));
                    this.addConstantMemberReference(i, b2, sourceClassReader.readClass(item, array), sourceClassReader.readUTF8(item2, array), sourceClassReader.readUTF8(item2 + 2, array));
                    break;
                }
                case 3:
                case 4: {
                    this.addConstantIntegerOrFloat(i, b2, sourceClassReader.readInt(item));
                    break;
                }
                case 12: {
                    this.addConstantNameAndType(i, sourceClassReader.readUTF8(item, array), sourceClassReader.readUTF8(item + 2, array));
                    break;
                }
                case 5:
                case 6: {
                    this.addConstantLongOrDouble(i, b2, sourceClassReader.readLong(item));
                    break;
                }
                case 1: {
                    this.addConstantUtf8(i, sourceClassReader.readUtf(i, array));
                    break;
                }
                case 15: {
                    final int item3 = sourceClassReader.getItem(sourceClassReader.readUnsignedShort(item + 1));
                    final int item4 = sourceClassReader.getItem(sourceClassReader.readUnsignedShort(item3 + 2));
                    this.addConstantMethodHandle(i, sourceClassReader.readByte(item), sourceClassReader.readClass(item3, array), sourceClassReader.readUTF8(item4, array), sourceClassReader.readUTF8(item4 + 2, array));
                    break;
                }
                case 17:
                case 18: {
                    b = true;
                    final int item5 = sourceClassReader.getItem(sourceClassReader.readUnsignedShort(item + 2));
                    this.addConstantDynamicOrInvokeDynamicReference(b2, i, sourceClassReader.readUTF8(item5, array), sourceClassReader.readUTF8(item5 + 2, array), sourceClassReader.readUnsignedShort(item));
                    break;
                }
                case 7:
                case 8:
                case 16:
                case 19:
                case 20: {
                    this.addConstantUtf8Reference(i, b2, sourceClassReader.readUTF8(item, array));
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }
        if (b) {
            this.copyBootstrapMethods(sourceClassReader, array);
        }
    }
    
    private void copyBootstrapMethods(final ClassReader classReader, final char[] array) {
        final byte[] classFileBuffer = classReader.classFileBuffer;
        int firstAttributeOffset = classReader.getFirstAttributeOffset();
        for (int i = classReader.readUnsignedShort(firstAttributeOffset - 2); i > 0; --i) {
            if ("BootstrapMethods".equals(classReader.readUTF8(firstAttributeOffset, array))) {
                this.bootstrapMethodCount = classReader.readUnsignedShort(firstAttributeOffset + 6);
                break;
            }
            firstAttributeOffset += 6 + classReader.readInt(firstAttributeOffset + 2);
        }
        if (this.bootstrapMethodCount > 0) {
            final int n = firstAttributeOffset + 8;
            final int n2 = classReader.readInt(firstAttributeOffset + 2) - 2;
            (this.bootstrapMethods = new ByteVector(n2)).putByteArray(classFileBuffer, n, n2);
            int n3 = n;
            for (int j = 0; j < this.bootstrapMethodCount; ++j) {
                final int n4 = n3 - n;
                final int unsignedShort = classReader.readUnsignedShort(n3);
                n3 += 2;
                int unsignedShort2 = classReader.readUnsignedShort(n3);
                n3 += 2;
                int hashCode = classReader.readConst(unsignedShort, array).hashCode();
                while (unsignedShort2-- > 0) {
                    final int unsignedShort3 = classReader.readUnsignedShort(n3);
                    n3 += 2;
                    hashCode ^= classReader.readConst(unsignedShort3, array).hashCode();
                }
                this.add(new Entry(j, 64, n4, hashCode & Integer.MAX_VALUE));
            }
        }
    }
    
    ClassReader getSource() {
        return this.sourceClassReader;
    }
    
    int getMajorVersion() {
        return this.majorVersion;
    }
    
    String getClassName() {
        return this.className;
    }
    
    int setMajorVersionAndClassName(final int majorVersion, final String className) {
        this.majorVersion = majorVersion;
        this.className = className;
        return this.addConstantClass(className).index;
    }
    
    int getConstantPoolCount() {
        return this.constantPoolCount;
    }
    
    int getConstantPoolLength() {
        return this.constantPool.length;
    }
    
    void putConstantPool(final ByteVector byteVector) {
        byteVector.putShort(this.constantPoolCount).putByteArray(this.constantPool.data, 0, this.constantPool.length);
    }
    
    int computeBootstrapMethodsSize() {
        if (this.bootstrapMethods != null) {
            this.addConstantUtf8("BootstrapMethods");
            return 8 + this.bootstrapMethods.length;
        }
        return 0;
    }
    
    void putBootstrapMethods(final ByteVector byteVector) {
        if (this.bootstrapMethods != null) {
            byteVector.putShort(this.addConstantUtf8("BootstrapMethods")).putInt(this.bootstrapMethods.length + 2).putShort(this.bootstrapMethodCount).putByteArray(this.bootstrapMethods.data, 0, this.bootstrapMethods.length);
        }
    }
    
    private Entry get(final int n) {
        return this.entries[n % this.entries.length];
    }
    
    private Entry put(final Entry entry) {
        if (this.entryCount > this.entries.length * 3 / 4) {
            final int length = this.entries.length;
            final int n = length * 2 + 1;
            final Entry[] entries = new Entry[n];
            for (int i = length - 1; i >= 0; --i) {
                Entry next;
                for (Entry entry2 = this.entries[i]; entry2 != null; entry2 = next) {
                    final int n2 = entry2.hashCode % n;
                    next = entry2.next;
                    entry2.next = entries[n2];
                    entries[n2] = entry2;
                }
            }
            this.entries = entries;
        }
        ++this.entryCount;
        final int n3 = entry.hashCode % this.entries.length;
        entry.next = this.entries[n3];
        return this.entries[n3] = entry;
    }
    
    private void add(final Entry entry) {
        ++this.entryCount;
        final int n = entry.hashCode % this.entries.length;
        entry.next = this.entries[n];
        this.entries[n] = entry;
    }
    
    Symbol addConstant(final Object o) {
        if (o instanceof Integer) {
            return this.addConstantInteger((int)o);
        }
        if (o instanceof Byte) {
            return this.addConstantInteger((int)o);
        }
        if (o instanceof Character) {
            return this.addConstantInteger((char)o);
        }
        if (o instanceof Short) {
            return this.addConstantInteger((int)o);
        }
        if (o instanceof Boolean) {
            return this.addConstantInteger(((boolean)o) ? 1 : 0);
        }
        if (o instanceof Float) {
            return this.addConstantFloat((float)o);
        }
        if (o instanceof Long) {
            return this.addConstantLong((long)o);
        }
        if (o instanceof Double) {
            return this.addConstantDouble((double)o);
        }
        if (o instanceof String) {
            return this.addConstantString((String)o);
        }
        if (o instanceof Type) {
            final Type type = (Type)o;
            final int sort = type.getSort();
            if (sort == 10) {
                return this.addConstantClass(type.getInternalName());
            }
            if (sort == 11) {
                return this.addConstantMethodType(type.getDescriptor());
            }
            return this.addConstantClass(type.getDescriptor());
        }
        else {
            if (o instanceof Handle) {
                final Handle handle = (Handle)o;
                return this.addConstantMethodHandle(handle.getTag(), handle.getOwner(), handle.getName(), handle.getDesc(), handle.isInterface());
            }
            if (o instanceof ConstantDynamic) {
                final ConstantDynamic constantDynamic = (ConstantDynamic)o;
                return this.addConstantDynamic(constantDynamic.getName(), constantDynamic.getDescriptor(), constantDynamic.getBootstrapMethod(), constantDynamic.getBootstrapMethodArgumentsUnsafe());
            }
            throw new IllegalArgumentException(stringConcat$0(o));
        }
    }
    
    private static /* synthetic */ String stringConcat$0(final Object obj) {
        return "value " + obj;
    }
    
    Symbol addConstantClass(final String s) {
        return this.addConstantUtf8Reference(7, s);
    }
    
    Symbol addConstantFieldref(final String s, final String s2, final String s3) {
        return this.addConstantMemberReference(9, s, s2, s3);
    }
    
    Symbol addConstantMethodref(final String s, final String s2, final String s3, final boolean b) {
        return this.addConstantMemberReference(b ? 11 : 10, s, s2, s3);
    }
    
    private Entry addConstantMemberReference(final int n, final String anObject, final String anObject2, final String anObject3) {
        final int hash = hash(n, anObject, anObject2, anObject3);
        for (Entry entry = this.get(hash); entry != null; entry = entry.next) {
            if (entry.tag == n && entry.hashCode == hash && entry.owner.equals(anObject) && entry.name.equals(anObject2) && entry.value.equals(anObject3)) {
                return entry;
            }
        }
        this.constantPool.put122(n, this.addConstantClass(anObject).index, this.addConstantNameAndType(anObject2, anObject3));
        return this.put(new Entry(this.constantPoolCount++, n, anObject, anObject2, anObject3, 0L, hash));
    }
    
    private void addConstantMemberReference(final int n, final int n2, final String s, final String s2, final String s3) {
        this.add(new Entry(n, n2, s, s2, s3, 0L, hash(n2, s, s2, s3)));
    }
    
    Symbol addConstantString(final String s) {
        return this.addConstantUtf8Reference(8, s);
    }
    
    Symbol addConstantInteger(final int n) {
        return this.addConstantIntegerOrFloat(3, n);
    }
    
    Symbol addConstantFloat(final float n) {
        return this.addConstantIntegerOrFloat(4, Float.floatToRawIntBits(n));
    }
    
    private Symbol addConstantIntegerOrFloat(final int n, final int n2) {
        final int hash = hash(n, n2);
        for (Entry entry = this.get(hash); entry != null; entry = entry.next) {
            if (entry.tag == n && entry.hashCode == hash && entry.data == n2) {
                return entry;
            }
        }
        this.constantPool.putByte(n).putInt(n2);
        return this.put(new Entry(this.constantPoolCount++, n, n2, hash));
    }
    
    private void addConstantIntegerOrFloat(final int n, final int n2, final int n3) {
        this.add(new Entry(n, n2, n3, hash(n2, n3)));
    }
    
    Symbol addConstantLong(final long n) {
        return this.addConstantLongOrDouble(5, n);
    }
    
    Symbol addConstantDouble(final double n) {
        return this.addConstantLongOrDouble(6, Double.doubleToRawLongBits(n));
    }
    
    private Symbol addConstantLongOrDouble(final int n, final long n2) {
        final int hash = hash(n, n2);
        for (Entry entry = this.get(hash); entry != null; entry = entry.next) {
            if (entry.tag == n && entry.hashCode == hash && entry.data == n2) {
                return entry;
            }
        }
        final int constantPoolCount = this.constantPoolCount;
        this.constantPool.putByte(n).putLong(n2);
        this.constantPoolCount += 2;
        return this.put(new Entry(constantPoolCount, n, n2, hash));
    }
    
    private void addConstantLongOrDouble(final int n, final int n2, final long n3) {
        this.add(new Entry(n, n2, n3, hash(n2, n3)));
    }
    
    int addConstantNameAndType(final String anObject, final String anObject2) {
        final int hash = hash(12, anObject, anObject2);
        for (Entry entry = this.get(hash); entry != null; entry = entry.next) {
            if (entry.tag == 12 && entry.hashCode == hash && entry.name.equals(anObject) && entry.value.equals(anObject2)) {
                return entry.index;
            }
        }
        this.constantPool.put122(12, this.addConstantUtf8(anObject), this.addConstantUtf8(anObject2));
        return this.put(new Entry(this.constantPoolCount++, 12, anObject, anObject2, hash)).index;
    }
    
    private void addConstantNameAndType(final int n, final String s, final String s2) {
        this.add(new Entry(n, 12, s, s2, hash(12, s, s2)));
    }
    
    int addConstantUtf8(final String anObject) {
        final int hash = hash(1, anObject);
        for (Entry entry = this.get(hash); entry != null; entry = entry.next) {
            if (entry.tag == 1 && entry.hashCode == hash && entry.value.equals(anObject)) {
                return entry.index;
            }
        }
        this.constantPool.putByte(1).putUTF8(anObject);
        return this.put(new Entry(this.constantPoolCount++, 1, anObject, hash)).index;
    }
    
    private void addConstantUtf8(final int n, final String s) {
        this.add(new Entry(n, 1, s, hash(1, s)));
    }
    
    Symbol addConstantMethodHandle(final int n, final String anObject, final String anObject2, final String anObject3, final boolean b) {
        final int hash = hash(15, anObject, anObject2, anObject3, n);
        for (Entry entry = this.get(hash); entry != null; entry = entry.next) {
            if (entry.tag == 15 && entry.hashCode == hash && entry.data == n && entry.owner.equals(anObject) && entry.name.equals(anObject2) && entry.value.equals(anObject3)) {
                return entry;
            }
        }
        if (n <= 4) {
            this.constantPool.put112(15, n, this.addConstantFieldref(anObject, anObject2, anObject3).index);
        }
        else {
            this.constantPool.put112(15, n, this.addConstantMethodref(anObject, anObject2, anObject3, b).index);
        }
        return this.put(new Entry(this.constantPoolCount++, 15, anObject, anObject2, anObject3, n, hash));
    }
    
    private void addConstantMethodHandle(final int n, final int n2, final String s, final String s2, final String s3) {
        this.add(new Entry(n, 15, s, s2, s3, n2, hash(15, s, s2, s3, n2)));
    }
    
    Symbol addConstantMethodType(final String s) {
        return this.addConstantUtf8Reference(16, s);
    }
    
    Symbol addConstantDynamic(final String s, final String s2, final Handle handle, final Object... array) {
        return this.addConstantDynamicOrInvokeDynamicReference(17, s, s2, this.addBootstrapMethod(handle, array).index);
    }
    
    Symbol addConstantInvokeDynamic(final String s, final String s2, final Handle handle, final Object... array) {
        return this.addConstantDynamicOrInvokeDynamicReference(18, s, s2, this.addBootstrapMethod(handle, array).index);
    }
    
    private Symbol addConstantDynamicOrInvokeDynamicReference(final int n, final String anObject, final String anObject2, final int n2) {
        final int hash = hash(n, anObject, anObject2, n2);
        for (Entry entry = this.get(hash); entry != null; entry = entry.next) {
            if (entry.tag == n && entry.hashCode == hash && entry.data == n2 && entry.name.equals(anObject) && entry.value.equals(anObject2)) {
                return entry;
            }
        }
        this.constantPool.put122(n, n2, this.addConstantNameAndType(anObject, anObject2));
        return this.put(new Entry(this.constantPoolCount++, n, null, anObject, anObject2, n2, hash));
    }
    
    private void addConstantDynamicOrInvokeDynamicReference(final int n, final int n2, final String s, final String s2, final int n3) {
        this.add(new Entry(n2, n, null, s, s2, n3, hash(n, s, s2, n3)));
    }
    
    Symbol addConstantModule(final String s) {
        return this.addConstantUtf8Reference(19, s);
    }
    
    Symbol addConstantPackage(final String s) {
        return this.addConstantUtf8Reference(20, s);
    }
    
    private Symbol addConstantUtf8Reference(final int n, final String anObject) {
        final int hash = hash(n, anObject);
        for (Entry entry = this.get(hash); entry != null; entry = entry.next) {
            if (entry.tag == n && entry.hashCode == hash && entry.value.equals(anObject)) {
                return entry;
            }
        }
        this.constantPool.put12(n, this.addConstantUtf8(anObject));
        return this.put(new Entry(this.constantPoolCount++, n, anObject, hash));
    }
    
    private void addConstantUtf8Reference(final int n, final int n2, final String s) {
        this.add(new Entry(n, n2, s, hash(n2, s)));
    }
    
    Symbol addBootstrapMethod(final Handle handle, final Object... array) {
        ByteVector bootstrapMethods = this.bootstrapMethods;
        if (bootstrapMethods == null) {
            final ByteVector bootstrapMethods2 = new ByteVector();
            this.bootstrapMethods = bootstrapMethods2;
            bootstrapMethods = bootstrapMethods2;
        }
        final int length = array.length;
        final int[] array2 = new int[length];
        for (int i = 0; i < length; ++i) {
            array2[i] = this.addConstant(array[i]).index;
        }
        final int length2 = bootstrapMethods.length;
        bootstrapMethods.putShort(this.addConstantMethodHandle(handle.getTag(), handle.getOwner(), handle.getName(), handle.getDesc(), handle.isInterface()).index);
        bootstrapMethods.putShort(length);
        for (int j = 0; j < length; ++j) {
            bootstrapMethods.putShort(array2[j]);
        }
        final int n = bootstrapMethods.length - length2;
        int hashCode = handle.hashCode();
        for (int length3 = array.length, k = 0; k < length3; ++k) {
            hashCode ^= array[k].hashCode();
        }
        return this.addBootstrapMethod(length2, n, hashCode & Integer.MAX_VALUE);
    }
    
    private Symbol addBootstrapMethod(final int length, final int n, final int n2) {
        final byte[] data = this.bootstrapMethods.data;
        for (Entry entry = this.get(n2); entry != null; entry = entry.next) {
            if (entry.tag == 64 && entry.hashCode == n2) {
                final int n3 = (int)entry.data;
                boolean b = true;
                for (int i = 0; i < n; ++i) {
                    if (data[length + i] != data[n3 + i]) {
                        b = false;
                        break;
                    }
                }
                if (b) {
                    this.bootstrapMethods.length = length;
                    return entry;
                }
            }
        }
        return this.put(new Entry(this.bootstrapMethodCount++, 64, length, n2));
    }
    
    Symbol getType(final int n) {
        return this.typeTable[n];
    }
    
    Label getForwardUninitializedLabel(final int n) {
        return this.labelTable[(int)this.typeTable[n].data].label;
    }
    
    int addType(final String anObject) {
        final int hash = hash(128, anObject);
        for (Entry entry = this.get(hash); entry != null; entry = entry.next) {
            if (entry.tag == 128 && entry.hashCode == hash && entry.value.equals(anObject)) {
                return entry.index;
            }
        }
        return this.addTypeInternal(new Entry(this.typeCount, 128, anObject, hash));
    }
    
    int addUninitializedType(final String anObject, final int n) {
        final int hash = hash(129, anObject, n);
        for (Entry entry = this.get(hash); entry != null; entry = entry.next) {
            if (entry.tag == 129 && entry.hashCode == hash && entry.data == n && entry.value.equals(anObject)) {
                return entry.index;
            }
        }
        return this.addTypeInternal(new Entry(this.typeCount, 129, anObject, n, hash));
    }
    
    int addForwardUninitializedType(final String anObject, final Label label) {
        final int index = this.getOrAddLabelEntry(label).index;
        final int hash = hash(130, anObject, index);
        for (Entry entry = this.get(hash); entry != null; entry = entry.next) {
            if (entry.tag == 130 && entry.hashCode == hash && entry.data == index && entry.value.equals(anObject)) {
                return entry.index;
            }
        }
        return this.addTypeInternal(new Entry(this.typeCount, 130, anObject, index, hash));
    }
    
    int addMergedType(final int n, final int n2) {
        final long n3 = (n < n2) ? ((long)n | (long)n2 << 32) : ((long)n2 | (long)n << 32);
        final int hash = hash(131, n + n2);
        for (Entry entry = this.get(hash); entry != null; entry = entry.next) {
            if (entry.tag == 131 && entry.hashCode == hash && entry.data == n3) {
                return entry.info;
            }
        }
        return this.put(new Entry(this.typeCount, 131, n3, hash)).info = this.addType(this.classWriter.getCommonSuperClass(this.typeTable[n].value, this.typeTable[n2].value));
    }
    
    private int addTypeInternal(final Entry entry) {
        if (this.typeTable == null) {
            this.typeTable = new Entry[16];
        }
        if (this.typeCount == this.typeTable.length) {
            final Entry[] typeTable = new Entry[2 * this.typeTable.length];
            System.arraycopy(this.typeTable, 0, typeTable, 0, this.typeTable.length);
            this.typeTable = typeTable;
        }
        this.typeTable[this.typeCount++] = entry;
        return this.put(entry).index;
    }
    
    private LabelEntry getOrAddLabelEntry(final Label label) {
        if (this.labelEntries == null) {
            this.labelEntries = new LabelEntry[16];
            this.labelTable = new LabelEntry[16];
        }
        final int identityHashCode = System.identityHashCode(label);
        LabelEntry next;
        for (next = this.labelEntries[identityHashCode % this.labelEntries.length]; next != null && next.label != label; next = next.next) {}
        if (next != null) {
            return next;
        }
        if (this.labelCount > this.labelEntries.length * 3 / 4) {
            final int length = this.labelEntries.length;
            final int n = length * 2 + 1;
            final LabelEntry[] labelEntries = new LabelEntry[n];
            for (int i = length - 1; i >= 0; --i) {
                LabelEntry next2;
                for (LabelEntry labelEntry = this.labelEntries[i]; labelEntry != null; labelEntry = next2) {
                    final int n2 = System.identityHashCode(labelEntry.label) % n;
                    next2 = labelEntry.next;
                    labelEntry.next = labelEntries[n2];
                    labelEntries[n2] = labelEntry;
                }
            }
            this.labelEntries = labelEntries;
        }
        if (this.labelCount == this.labelTable.length) {
            final LabelEntry[] labelTable = new LabelEntry[2 * this.labelTable.length];
            System.arraycopy(this.labelTable, 0, labelTable, 0, this.labelTable.length);
            this.labelTable = labelTable;
        }
        final LabelEntry labelEntry2 = new LabelEntry(this.labelCount, label);
        final int n3 = identityHashCode % this.labelEntries.length;
        labelEntry2.next = this.labelEntries[n3];
        this.labelEntries[n3] = labelEntry2;
        return this.labelTable[this.labelCount++] = labelEntry2;
    }
    
    private static int hash(final int n, final int n2) {
        return Integer.MAX_VALUE & n + n2;
    }
    
    private static int hash(final int n, final long n2) {
        return Integer.MAX_VALUE & n + (int)n2 + (int)(n2 >>> 32);
    }
    
    private static int hash(final int n, final String s) {
        return Integer.MAX_VALUE & n + s.hashCode();
    }
    
    private static int hash(final int n, final String s, final int n2) {
        return Integer.MAX_VALUE & n + s.hashCode() + n2;
    }
    
    private static int hash(final int n, final String s, final String s2) {
        return Integer.MAX_VALUE & n + s.hashCode() * s2.hashCode();
    }
    
    private static int hash(final int n, final String s, final String s2, final int n2) {
        return Integer.MAX_VALUE & n + s.hashCode() * s2.hashCode() * (n2 + 1);
    }
    
    private static int hash(final int n, final String s, final String s2, final String s3) {
        return Integer.MAX_VALUE & n + s.hashCode() * s2.hashCode() * s3.hashCode();
    }
    
    private static int hash(final int n, final String s, final String s2, final String s3, final int n2) {
        return Integer.MAX_VALUE & n + s.hashCode() * s2.hashCode() * s3.hashCode() * n2;
    }
    
    private static final class Entry extends Symbol
    {
        final int hashCode;
        Entry next;
        
        Entry(final int n, final int n2, final String s, final String s2, final String s3, final long n3, final int hashCode) {
            super(n, n2, s, s2, s3, n3);
            this.hashCode = hashCode;
        }
        
        Entry(final int n, final int n2, final String s, final int hashCode) {
            super(n, n2, null, null, s, 0L);
            this.hashCode = hashCode;
        }
        
        Entry(final int n, final int n2, final String s, final long n3, final int hashCode) {
            super(n, n2, null, null, s, n3);
            this.hashCode = hashCode;
        }
        
        Entry(final int n, final int n2, final String s, final String s2, final int hashCode) {
            super(n, n2, null, s, s2, 0L);
            this.hashCode = hashCode;
        }
        
        Entry(final int n, final int n2, final long n3, final int hashCode) {
            super(n, n2, null, null, null, n3);
            this.hashCode = hashCode;
        }
    }
    
    private static final class LabelEntry
    {
        final int index;
        final Label label;
        LabelEntry next;
        
        LabelEntry(final int index, final Label label) {
            this.index = index;
            this.label = label;
        }
    }
}
