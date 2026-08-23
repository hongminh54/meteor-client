// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.io.DataOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Collection;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExceptionTable implements Cloneable
{
    private ConstPool constPool;
    private List<ExceptionTableEntry> entries;
    
    public ExceptionTable(final ConstPool constPool) {
        this.constPool = constPool;
        this.entries = new ArrayList<ExceptionTableEntry>();
    }
    
    ExceptionTable(final ConstPool constPool, final DataInputStream dataInputStream) {
        this.constPool = constPool;
        final int unsignedShort = dataInputStream.readUnsignedShort();
        final ArrayList entries = new ArrayList(unsignedShort);
        for (int i = 0; i < unsignedShort; ++i) {
            entries.add((Object)new ExceptionTableEntry(dataInputStream.readUnsignedShort(), dataInputStream.readUnsignedShort(), dataInputStream.readUnsignedShort(), dataInputStream.readUnsignedShort()));
        }
        this.entries = (List<ExceptionTableEntry>)entries;
    }
    
    public Object clone() {
        final ExceptionTable exceptionTable = (ExceptionTable)super.clone();
        exceptionTable.entries = new ArrayList<ExceptionTableEntry>(this.entries);
        return exceptionTable;
    }
    
    public int size() {
        return this.entries.size();
    }
    
    public int startPc(final int n) {
        return this.entries.get(n).startPc;
    }
    
    public void setStartPc(final int n, final int startPc) {
        this.entries.get(n).startPc = startPc;
    }
    
    public int endPc(final int n) {
        return this.entries.get(n).endPc;
    }
    
    public void setEndPc(final int n, final int endPc) {
        this.entries.get(n).endPc = endPc;
    }
    
    public int handlerPc(final int n) {
        return this.entries.get(n).handlerPc;
    }
    
    public void setHandlerPc(final int n, final int handlerPc) {
        this.entries.get(n).handlerPc = handlerPc;
    }
    
    public int catchType(final int n) {
        return this.entries.get(n).catchType;
    }
    
    public void setCatchType(final int n, final int catchType) {
        this.entries.get(n).catchType = catchType;
    }
    
    public void add(final int n, final ExceptionTable exceptionTable, final int n2) {
        int size = exceptionTable.size();
        while (--size >= 0) {
            final ExceptionTableEntry exceptionTableEntry = exceptionTable.entries.get(size);
            this.add(n, exceptionTableEntry.startPc + n2, exceptionTableEntry.endPc + n2, exceptionTableEntry.handlerPc + n2, exceptionTableEntry.catchType);
        }
    }
    
    public void add(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n2 < n3) {
            this.entries.add(n, new ExceptionTableEntry(n2, n3, n4, n5));
        }
    }
    
    public void add(final int n, final int n2, final int n3, final int n4) {
        if (n < n2) {
            this.entries.add(new ExceptionTableEntry(n, n2, n3, n4));
        }
    }
    
    public void remove(final int n) {
        this.entries.remove(n);
    }
    
    public ExceptionTable copy(final ConstPool constPool, final Map<String, String> map) {
        final ExceptionTable exceptionTable = new ExceptionTable(constPool);
        final ConstPool constPool2 = this.constPool;
        for (final ExceptionTableEntry exceptionTableEntry : this.entries) {
            exceptionTable.add(exceptionTableEntry.startPc, exceptionTableEntry.endPc, exceptionTableEntry.handlerPc, constPool2.copy(exceptionTableEntry.catchType, constPool, map));
        }
        return exceptionTable;
    }
    
    void shiftPc(final int n, final int n2, final boolean b) {
        for (final ExceptionTableEntry exceptionTableEntry : this.entries) {
            exceptionTableEntry.startPc = shiftPc(exceptionTableEntry.startPc, n, n2, b);
            exceptionTableEntry.endPc = shiftPc(exceptionTableEntry.endPc, n, n2, b);
            exceptionTableEntry.handlerPc = shiftPc(exceptionTableEntry.handlerPc, n, n2, b);
        }
    }
    
    private static int shiftPc(int n, final int n2, final int n3, final boolean b) {
        if (n > n2 || (b && n == n2)) {
            n += n3;
        }
        return n;
    }
    
    void write(final DataOutputStream dataOutputStream) {
        dataOutputStream.writeShort(this.size());
        for (final ExceptionTableEntry exceptionTableEntry : this.entries) {
            dataOutputStream.writeShort(exceptionTableEntry.startPc);
            dataOutputStream.writeShort(exceptionTableEntry.endPc);
            dataOutputStream.writeShort(exceptionTableEntry.handlerPc);
            dataOutputStream.writeShort(exceptionTableEntry.catchType);
        }
    }
}
