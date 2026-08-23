// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.util.Map;
import java.io.DataInputStream;

public class ConstantAttribute extends AttributeInfo
{
    public static final String tag = "ConstantValue";
    
    ConstantAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) {
        super(constPool, n, dataInputStream);
    }
    
    public ConstantAttribute(final ConstPool constPool, final int n) {
        super(constPool, "ConstantValue");
        this.set(new byte[] { (byte)(n >>> 8), (byte)n });
    }
    
    public int getConstantValue() {
        return ByteArray.readU16bit(this.get(), 0);
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map<String, String> map) {
        return new ConstantAttribute(constPool, this.getConstPool().copy(this.getConstantValue(), constPool, map));
    }
}
