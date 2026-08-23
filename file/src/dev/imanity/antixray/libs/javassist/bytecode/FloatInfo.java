// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.io.PrintWriter;
import java.io.DataOutputStream;
import java.util.Map;
import java.io.DataInputStream;

class FloatInfo extends ConstInfo
{
    static final int tag = 4;
    float value;
    
    public FloatInfo(final float value, final int n) {
        super(n);
        this.value = value;
    }
    
    public FloatInfo(final DataInputStream dataInputStream, final int n) {
        super(n);
        this.value = dataInputStream.readFloat();
    }
    
    @Override
    public int hashCode() {
        return Float.floatToIntBits(this.value);
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof FloatInfo && ((FloatInfo)o).value == this.value;
    }
    
    @Override
    public int getTag() {
        return 4;
    }
    
    @Override
    public int copy(final ConstPool constPool, final ConstPool constPool2, final Map<String, String> map) {
        return constPool2.addFloatInfo(this.value);
    }
    
    @Override
    public void write(final DataOutputStream dataOutputStream) {
        dataOutputStream.writeByte(4);
        dataOutputStream.writeFloat(this.value);
    }
    
    @Override
    public void print(final PrintWriter printWriter) {
        printWriter.print("Float ");
        printWriter.println(this.value);
    }
}
