// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.io.PrintWriter;
import java.io.DataOutputStream;
import java.util.Map;
import java.io.DataInputStream;

class LongInfo extends ConstInfo
{
    static final int tag = 5;
    long value;
    
    public LongInfo(final long value, final int n) {
        super(n);
        this.value = value;
    }
    
    public LongInfo(final DataInputStream dataInputStream, final int n) {
        super(n);
        this.value = dataInputStream.readLong();
    }
    
    @Override
    public int hashCode() {
        return (int)(this.value ^ this.value >>> 32);
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof LongInfo && ((LongInfo)o).value == this.value;
    }
    
    @Override
    public int getTag() {
        return 5;
    }
    
    @Override
    public int copy(final ConstPool constPool, final ConstPool constPool2, final Map<String, String> map) {
        return constPool2.addLongInfo(this.value);
    }
    
    @Override
    public void write(final DataOutputStream dataOutputStream) {
        dataOutputStream.writeByte(5);
        dataOutputStream.writeLong(this.value);
    }
    
    @Override
    public void print(final PrintWriter printWriter) {
        printWriter.print("Long ");
        printWriter.println(this.value);
    }
}
