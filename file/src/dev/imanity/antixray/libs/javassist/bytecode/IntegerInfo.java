// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.io.PrintWriter;
import java.io.DataOutputStream;
import java.util.Map;
import java.io.DataInputStream;

class IntegerInfo extends ConstInfo
{
    static final int tag = 3;
    int value;
    
    public IntegerInfo(final int value, final int n) {
        super(n);
        this.value = value;
    }
    
    public IntegerInfo(final DataInputStream dataInputStream, final int n) {
        super(n);
        this.value = dataInputStream.readInt();
    }
    
    @Override
    public int hashCode() {
        return this.value;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof IntegerInfo && ((IntegerInfo)o).value == this.value;
    }
    
    @Override
    public int getTag() {
        return 3;
    }
    
    @Override
    public int copy(final ConstPool constPool, final ConstPool constPool2, final Map<String, String> map) {
        return constPool2.addIntegerInfo(this.value);
    }
    
    @Override
    public void write(final DataOutputStream dataOutputStream) {
        dataOutputStream.writeByte(3);
        dataOutputStream.writeInt(this.value);
    }
    
    @Override
    public void print(final PrintWriter printWriter) {
        printWriter.print("Integer ");
        printWriter.println(this.value);
    }
}
