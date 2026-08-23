// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.io.ByteArrayOutputStream;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.DataOutputStream;
import java.util.Map;
import java.io.DataInputStream;

public class StackMapTable extends AttributeInfo
{
    public static final String tag = "StackMapTable";
    public static final int TOP = 0;
    public static final int INTEGER = 1;
    public static final int FLOAT = 2;
    public static final int DOUBLE = 3;
    public static final int LONG = 4;
    public static final int NULL = 5;
    public static final int THIS = 6;
    public static final int OBJECT = 7;
    public static final int UNINIT = 8;
    
    StackMapTable(final ConstPool constPool, final byte[] array) {
        super(constPool, "StackMapTable", array);
    }
    
    StackMapTable(final ConstPool constPool, final int n, final DataInputStream dataInputStream) {
        super(constPool, n, dataInputStream);
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map<String, String> map) {
        try {
            return new StackMapTable(constPool, new Copier(this.constPool, this.info, constPool, map).doit());
        }
        catch (final BadBytecode badBytecode) {
            throw new RuntimeCopyException("bad bytecode. fatal?");
        }
    }
    
    @Override
    void write(final DataOutputStream dataOutputStream) {
        super.write(dataOutputStream);
    }
    
    public void insertLocal(final int n, final int n2, final int n3) {
        this.set(new InsertLocal(this.get(), n, n2, n3).doit());
    }
    
    public static int typeTagOf(final char c) {
        switch (c) {
            case 'D': {
                return 3;
            }
            case 'F': {
                return 2;
            }
            case 'J': {
                return 4;
            }
            case 'L':
            case '[': {
                return 7;
            }
            default: {
                return 1;
            }
        }
    }
    
    public void println(final PrintWriter printWriter) {
        Printer.print(this, printWriter);
    }
    
    public void println(final PrintStream out) {
        Printer.print(this, new PrintWriter(out, true));
    }
    
    void shiftPc(final int n, final int n2, final boolean b) {
        new OffsetShifter(this, n, n2).parse();
        new Shifter(this, n, n2, b).doit();
    }
    
    void shiftForSwitch(final int n, final int n2) {
        new SwitchShifter(this, n, n2).doit();
    }
    
    public void removeNew(final int n) {
        try {
            this.set(new NewRemover(this.get(), n).doit());
        }
        catch (final BadBytecode badBytecode) {
            throw new CannotCompileException("bad stack map table", badBytecode);
        }
    }
    
    public static class RuntimeCopyException extends RuntimeException
    {
        private static final long serialVersionUID = 1L;
        
        public RuntimeCopyException(final String message) {
            super(message);
        }
    }
    
    public static class Walker
    {
        byte[] info;
        int numOfEntries;
        
        public Walker(final StackMapTable stackMapTable) {
            this(stackMapTable.get());
        }
        
        public Walker(final byte[] info) {
            this.info = info;
            this.numOfEntries = ByteArray.readU16bit(info, 0);
        }
        
        public final int size() {
            return this.numOfEntries;
        }
        
        public void parse() {
            final int numOfEntries = this.numOfEntries;
            int stackMapFrames = 2;
            for (int i = 0; i < numOfEntries; ++i) {
                stackMapFrames = this.stackMapFrames(stackMapFrames, i);
            }
        }
        
        int stackMapFrames(int i, final int j) {
            final int k = this.info[i] & 0xFF;
            if (k < 64) {
                this.sameFrame(i, k);
                ++i;
            }
            else if (k < 128) {
                i = this.sameLocals(i, k);
            }
            else {
                if (k < 247) {
                    throw new BadBytecode("bad frame_type " + k + " in StackMapTable (pos: " + i + ", frame no.:" + j + ")");
                }
                if (k == 247) {
                    i = this.sameLocals(i, k);
                }
                else if (k < 251) {
                    this.chopFrame(i, ByteArray.readU16bit(this.info, i + 1), 251 - k);
                    i += 3;
                }
                else if (k == 251) {
                    this.sameFrame(i, ByteArray.readU16bit(this.info, i + 1));
                    i += 3;
                }
                else if (k < 255) {
                    i = this.appendFrame(i, k);
                }
                else {
                    i = this.fullFrame(i);
                }
            }
            return i;
        }
        
        public void sameFrame(final int n, final int n2) {
        }
        
        private int sameLocals(int n, final int n2) {
            final int n3 = n;
            int u16bit;
            if (n2 < 128) {
                u16bit = n2 - 64;
            }
            else {
                u16bit = ByteArray.readU16bit(this.info, n + 1);
                n += 2;
            }
            final int n4 = this.info[n + 1] & 0xFF;
            int u16bit2 = 0;
            if (n4 == 7 || n4 == 8) {
                u16bit2 = ByteArray.readU16bit(this.info, n + 2);
                this.objectOrUninitialized(n4, u16bit2, n + 2);
                n += 2;
            }
            this.sameLocals(n3, u16bit, n4, u16bit2);
            return n + 2;
        }
        
        public void sameLocals(final int n, final int n2, final int n3, final int n4) {
        }
        
        public void chopFrame(final int n, final int n2, final int n3) {
        }
        
        private int appendFrame(final int n, final int n2) {
            final int n3 = n2 - 251;
            final int u16bit = ByteArray.readU16bit(this.info, n + 1);
            final int[] array = new int[n3];
            final int[] array2 = new int[n3];
            int n4 = n + 3;
            for (int i = 0; i < n3; ++i) {
                final int n5 = this.info[n4] & 0xFF;
                array[i] = n5;
                if (n5 == 7 || n5 == 8) {
                    this.objectOrUninitialized(n5, array2[i] = ByteArray.readU16bit(this.info, n4 + 1), n4 + 1);
                    n4 += 3;
                }
                else {
                    array2[i] = 0;
                    ++n4;
                }
            }
            this.appendFrame(n, u16bit, array, array2);
            return n4;
        }
        
        public void appendFrame(final int n, final int n2, final int[] array, final int[] array2) {
        }
        
        private int fullFrame(final int n) {
            final int u16bit = ByteArray.readU16bit(this.info, n + 1);
            final int u16bit2 = ByteArray.readU16bit(this.info, n + 3);
            final int[] array = new int[u16bit2];
            final int[] array2 = new int[u16bit2];
            final int verifyTypeInfo = this.verifyTypeInfo(n + 5, u16bit2, array, array2);
            final int u16bit3 = ByteArray.readU16bit(this.info, verifyTypeInfo);
            final int[] array3 = new int[u16bit3];
            final int[] array4 = new int[u16bit3];
            final int verifyTypeInfo2 = this.verifyTypeInfo(verifyTypeInfo + 2, u16bit3, array3, array4);
            this.fullFrame(n, u16bit, array, array2, array3, array4);
            return verifyTypeInfo2;
        }
        
        public void fullFrame(final int n, final int n2, final int[] array, final int[] array2, final int[] array3, final int[] array4) {
        }
        
        private int verifyTypeInfo(int n, final int n2, final int[] array, final int[] array2) {
            for (int i = 0; i < n2; ++i) {
                final int n3 = this.info[n++] & 0xFF;
                array[i] = n3;
                if (n3 == 7 || n3 == 8) {
                    this.objectOrUninitialized(n3, array2[i] = ByteArray.readU16bit(this.info, n), n);
                    n += 2;
                }
            }
            return n;
        }
        
        public void objectOrUninitialized(final int n, final int n2, final int n3) {
        }
    }
    
    static class SimpleCopy extends Walker
    {
        private Writer writer;
        
        public SimpleCopy(final byte[] array) {
            super(array);
            this.writer = new Writer(array.length);
        }
        
        public byte[] doit() {
            this.parse();
            return this.writer.toByteArray();
        }
        
        @Override
        public void sameFrame(final int n, final int n2) {
            this.writer.sameFrame(n2);
        }
        
        @Override
        public void sameLocals(final int n, final int n2, final int n3, final int n4) {
            this.writer.sameLocals(n2, n3, this.copyData(n3, n4));
        }
        
        @Override
        public void chopFrame(final int n, final int n2, final int n3) {
            this.writer.chopFrame(n2, n3);
        }
        
        @Override
        public void appendFrame(final int n, final int n2, final int[] array, final int[] array2) {
            this.writer.appendFrame(n2, array, this.copyData(array, array2));
        }
        
        @Override
        public void fullFrame(final int n, final int n2, final int[] array, final int[] array2, final int[] array3, final int[] array4) {
            this.writer.fullFrame(n2, array, this.copyData(array, array2), array3, this.copyData(array3, array4));
        }
        
        protected int copyData(final int n, final int n2) {
            return n2;
        }
        
        protected int[] copyData(final int[] array, final int[] array2) {
            return array2;
        }
    }
    
    static class Copier extends SimpleCopy
    {
        private ConstPool srcPool;
        private ConstPool destPool;
        private Map<String, String> classnames;
        
        public Copier(final ConstPool srcPool, final byte[] array, final ConstPool destPool, final Map<String, String> classnames) {
            super(array);
            this.srcPool = srcPool;
            this.destPool = destPool;
            this.classnames = classnames;
        }
        
        @Override
        protected int copyData(final int n, final int n2) {
            if (n == 7) {
                return this.srcPool.copy(n2, this.destPool, this.classnames);
            }
            return n2;
        }
        
        @Override
        protected int[] copyData(final int[] array, final int[] array2) {
            final int[] array3 = new int[array2.length];
            for (int i = 0; i < array2.length; ++i) {
                if (array[i] == 7) {
                    array3[i] = this.srcPool.copy(array2[i], this.destPool, this.classnames);
                }
                else {
                    array3[i] = array2[i];
                }
            }
            return array3;
        }
    }
    
    static class InsertLocal extends SimpleCopy
    {
        private int varIndex;
        private int varTag;
        private int varData;
        
        public InsertLocal(final byte[] array, final int varIndex, final int varTag, final int varData) {
            super(array);
            this.varIndex = varIndex;
            this.varTag = varTag;
            this.varData = varData;
        }
        
        @Override
        public void fullFrame(final int n, final int n2, final int[] array, final int[] array2, final int[] array3, final int[] array4) {
            final int length = array.length;
            if (length < this.varIndex) {
                super.fullFrame(n, n2, array, array2, array3, array4);
                return;
            }
            final int n3 = (this.varTag == 4 || this.varTag == 3) ? 2 : 1;
            final int[] array5 = new int[length + n3];
            final int[] array6 = new int[length + n3];
            final int varIndex = this.varIndex;
            int n4 = 0;
            for (int i = 0; i < length; ++i) {
                if (n4 == varIndex) {
                    n4 += n3;
                }
                array5[n4] = array[i];
                array6[n4++] = array2[i];
            }
            array5[varIndex] = this.varTag;
            array6[varIndex] = this.varData;
            if (n3 > 1) {
                array6[varIndex + 1] = (array5[varIndex + 1] = 0);
            }
            super.fullFrame(n, n2, array5, array6, array3, array4);
        }
    }
    
    public static class Writer
    {
        ByteArrayOutputStream output;
        int numOfEntries;
        
        public Writer(final int size) {
            this.output = new ByteArrayOutputStream(size);
            this.numOfEntries = 0;
            this.output.write(0);
            this.output.write(0);
        }
        
        public byte[] toByteArray() {
            final byte[] byteArray = this.output.toByteArray();
            ByteArray.write16bit(this.numOfEntries, byteArray, 0);
            return byteArray;
        }
        
        public StackMapTable toStackMapTable(final ConstPool constPool) {
            return new StackMapTable(constPool, this.toByteArray());
        }
        
        public void sameFrame(final int b) {
            ++this.numOfEntries;
            if (b < 64) {
                this.output.write(b);
            }
            else {
                this.output.write(251);
                this.write16(b);
            }
        }
        
        public void sameLocals(final int n, final int n2, final int n3) {
            ++this.numOfEntries;
            if (n < 64) {
                this.output.write(n + 64);
            }
            else {
                this.output.write(247);
                this.write16(n);
            }
            this.writeTypeInfo(n2, n3);
        }
        
        public void chopFrame(final int n, final int n2) {
            ++this.numOfEntries;
            this.output.write(251 - n2);
            this.write16(n);
        }
        
        public void appendFrame(final int n, final int[] array, final int[] array2) {
            ++this.numOfEntries;
            final int length = array.length;
            this.output.write(length + 251);
            this.write16(n);
            for (int i = 0; i < length; ++i) {
                this.writeTypeInfo(array[i], array2[i]);
            }
        }
        
        public void fullFrame(final int n, final int[] array, final int[] array2, final int[] array3, final int[] array4) {
            ++this.numOfEntries;
            this.output.write(255);
            this.write16(n);
            final int length = array.length;
            this.write16(length);
            for (int i = 0; i < length; ++i) {
                this.writeTypeInfo(array[i], array2[i]);
            }
            final int length2 = array3.length;
            this.write16(length2);
            for (int j = 0; j < length2; ++j) {
                this.writeTypeInfo(array3[j], array4[j]);
            }
        }
        
        private void writeTypeInfo(final int b, final int n) {
            this.output.write(b);
            if (b == 7 || b == 8) {
                this.write16(n);
            }
        }
        
        private void write16(final int n) {
            this.output.write(n >>> 8 & 0xFF);
            this.output.write(n & 0xFF);
        }
    }
    
    static class Printer extends Walker
    {
        private PrintWriter writer;
        private int offset;
        
        public static void print(final StackMapTable stackMapTable, final PrintWriter printWriter) {
            try {
                new Printer(stackMapTable.get(), printWriter).parse();
            }
            catch (final BadBytecode badBytecode) {
                printWriter.println(badBytecode.getMessage());
            }
        }
        
        Printer(final byte[] array, final PrintWriter writer) {
            super(array);
            this.writer = writer;
            this.offset = -1;
        }
        
        @Override
        public void sameFrame(final int n, final int i) {
            this.offset += i + 1;
            this.writer.println(this.offset + " same frame: " + i);
        }
        
        @Override
        public void sameLocals(final int n, final int i, final int n2, final int n3) {
            this.offset += i + 1;
            this.writer.println(this.offset + " same locals: " + i);
            this.printTypeInfo(n2, n3);
        }
        
        @Override
        public void chopFrame(final int n, final int i, final int j) {
            this.offset += i + 1;
            this.writer.println(this.offset + " chop frame: " + i + ",    " + j + " last locals");
        }
        
        @Override
        public void appendFrame(final int n, final int i, final int[] array, final int[] array2) {
            this.offset += i + 1;
            this.writer.println(this.offset + " append frame: " + i);
            for (int j = 0; j < array.length; ++j) {
                this.printTypeInfo(array[j], array2[j]);
            }
        }
        
        @Override
        public void fullFrame(final int n, final int i, final int[] array, final int[] array2, final int[] array3, final int[] array4) {
            this.offset += i + 1;
            this.writer.println(this.offset + " full frame: " + i);
            this.writer.println("[locals]");
            for (int j = 0; j < array.length; ++j) {
                this.printTypeInfo(array[j], array2[j]);
            }
            this.writer.println("[stack]");
            for (int k = 0; k < array3.length; ++k) {
                this.printTypeInfo(array3[k], array4[k]);
            }
        }
        
        private void printTypeInfo(final int n, final int n2) {
            String x = null;
            switch (n) {
                case 0: {
                    x = "top";
                    break;
                }
                case 1: {
                    x = "integer";
                    break;
                }
                case 2: {
                    x = "float";
                    break;
                }
                case 3: {
                    x = "double";
                    break;
                }
                case 4: {
                    x = "long";
                    break;
                }
                case 5: {
                    x = "null";
                    break;
                }
                case 6: {
                    x = "this";
                    break;
                }
                case 7: {
                    x = "object (cpool_index " + n2 + ")";
                    break;
                }
                case 8: {
                    x = "uninitialized (offset " + n2 + ")";
                    break;
                }
            }
            this.writer.print("    ");
            this.writer.println(x);
        }
    }
    
    static class OffsetShifter extends Walker
    {
        int where;
        int gap;
        
        public OffsetShifter(final StackMapTable stackMapTable, final int where, final int gap) {
            super(stackMapTable);
            this.where = where;
            this.gap = gap;
        }
        
        @Override
        public void objectOrUninitialized(final int n, final int n2, final int n3) {
            if (n == 8 && this.where <= n2) {
                ByteArray.write16bit(n2 + this.gap, this.info, n3);
            }
        }
    }
    
    static class Shifter extends Walker
    {
        private StackMapTable stackMap;
        int where;
        int gap;
        int position;
        byte[] updatedInfo;
        boolean exclusive;
        
        public Shifter(final StackMapTable stackMap, final int where, final int gap, final boolean exclusive) {
            super(stackMap);
            this.stackMap = stackMap;
            this.where = where;
            this.gap = gap;
            this.position = 0;
            this.updatedInfo = null;
            this.exclusive = exclusive;
        }
        
        public void doit() {
            this.parse();
            if (this.updatedInfo != null) {
                this.stackMap.set(this.updatedInfo);
            }
        }
        
        @Override
        public void sameFrame(final int n, final int n2) {
            this.update(n, n2, 0, 251);
        }
        
        @Override
        public void sameLocals(final int n, final int n2, final int n3, final int n4) {
            this.update(n, n2, 64, 247);
        }
        
        void update(final int n, final int n2, final int n3, final int n4) {
            final int position = this.position;
            this.position = position + n2 + ((position != 0) ? 1 : 0);
            boolean b;
            if (this.exclusive) {
                b = ((position == 0 && this.where == 0) || (position < this.where && this.where <= this.position));
            }
            else {
                b = (position <= this.where && this.where < this.position);
            }
            if (b) {
                final int n5 = this.info[n] & 0xFF;
                final int n6 = n2 + this.gap;
                this.position += this.gap;
                if (n6 < 64) {
                    this.info[n] = (byte)(n6 + n3);
                }
                else if (n2 < 64 && n5 != n4) {
                    final byte[] insertGap = insertGap(this.info, n, 2);
                    insertGap[n] = (byte)n4;
                    ByteArray.write16bit(n6, insertGap, n + 1);
                    this.updatedInfo = insertGap;
                }
                else {
                    ByteArray.write16bit(n6, this.info, n + 1);
                }
            }
        }
        
        static byte[] insertGap(final byte[] array, final int n, final int n2) {
            final int length = array.length;
            final byte[] array2 = new byte[length + n2];
            if (n <= 0) {
                System.arraycopy(array, 0, array2, n2, length);
            }
            else if (n >= length) {
                System.arraycopy(array, 0, array2, 0, length);
            }
            else {
                assert n > 0 && n < length;
                System.arraycopy(array, 0, array2, 0, n);
                System.arraycopy(array, n, array2, n + n2, length - n);
            }
            return array2;
        }
        
        @Override
        public void chopFrame(final int n, final int n2, final int n3) {
            this.update(n, n2);
        }
        
        @Override
        public void appendFrame(final int n, final int n2, final int[] array, final int[] array2) {
            this.update(n, n2);
        }
        
        @Override
        public void fullFrame(final int n, final int n2, final int[] array, final int[] array2, final int[] array3, final int[] array4) {
            this.update(n, n2);
        }
        
        void update(final int n, final int n2) {
            final int position = this.position;
            this.position = position + n2 + ((position != 0) ? 1 : 0);
            boolean b;
            if (this.exclusive) {
                b = ((position == 0 && this.where == 0) || (position < this.where && this.where <= this.position));
            }
            else {
                b = (position <= this.where && this.where < this.position);
            }
            if (b) {
                ByteArray.write16bit(n2 + this.gap, this.info, n + 1);
                this.position += this.gap;
            }
        }
    }
    
    static class SwitchShifter extends Shifter
    {
        SwitchShifter(final StackMapTable stackMapTable, final int n, final int n2) {
            super(stackMapTable, n, n2, false);
        }
        
        @Override
        void update(final int n, final int n2, final int n3, final int n4) {
            final int position = this.position;
            this.position = position + n2 + ((position != 0) ? 1 : 0);
            int n5;
            if (this.where == this.position) {
                n5 = n2 - this.gap;
            }
            else {
                if (this.where != position) {
                    return;
                }
                n5 = n2 + this.gap;
            }
            if (n2 < 64) {
                if (n5 < 64) {
                    this.info[n] = (byte)(n5 + n3);
                }
                else {
                    final byte[] insertGap = Shifter.insertGap(this.info, n, 2);
                    insertGap[n] = (byte)n4;
                    ByteArray.write16bit(n5, insertGap, n + 1);
                    this.updatedInfo = insertGap;
                }
            }
            else if (n5 < 64) {
                final byte[] deleteGap = deleteGap(this.info, n, 2);
                deleteGap[n] = (byte)(n5 + n3);
                this.updatedInfo = deleteGap;
            }
            else {
                ByteArray.write16bit(n5, this.info, n + 1);
            }
        }
        
        static byte[] deleteGap(final byte[] array, int n, final int n2) {
            n += n2;
            final int length = array.length;
            final byte[] array2 = new byte[length - n2];
            for (int i = 0; i < length; ++i) {
                array2[i - ((i < n) ? 0 : n2)] = array[i];
            }
            return array2;
        }
        
        @Override
        void update(final int n, final int n2) {
            final int position = this.position;
            this.position = position + n2 + ((position != 0) ? 1 : 0);
            int n3;
            if (this.where == this.position) {
                n3 = n2 - this.gap;
            }
            else {
                if (this.where != position) {
                    return;
                }
                n3 = n2 + this.gap;
            }
            ByteArray.write16bit(n3, this.info, n + 1);
        }
    }
    
    static class NewRemover extends SimpleCopy
    {
        int posOfNew;
        
        public NewRemover(final byte[] array, final int posOfNew) {
            super(array);
            this.posOfNew = posOfNew;
        }
        
        @Override
        public void sameLocals(final int n, final int n2, final int n3, final int n4) {
            if (n3 == 8 && n4 == this.posOfNew) {
                super.sameFrame(n, n2);
            }
            else {
                super.sameLocals(n, n2, n3, n4);
            }
        }
        
        @Override
        public void fullFrame(final int n, final int n2, final int[] array, final int[] array2, int[] array3, int[] array4) {
            for (int n3 = array3.length - 1, i = 0; i < n3; ++i) {
                if (array3[i] == 8 && array4[i] == this.posOfNew && array3[i + 1] == 8 && array4[i + 1] == this.posOfNew) {
                    final int[] array5 = new int[++n3 - 2];
                    final int[] array6 = new int[n3 - 2];
                    int n4 = 0;
                    for (int j = 0; j < n3; ++j) {
                        if (j == i) {
                            ++j;
                        }
                        else {
                            array5[n4] = array3[j];
                            array6[n4++] = array4[j];
                        }
                    }
                    array3 = array5;
                    array4 = array6;
                    break;
                }
            }
            super.fullFrame(n, n2, array, array2, array3, array4);
        }
    }
}
