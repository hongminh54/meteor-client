// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.util.Map;
import java.io.DataInputStream;

public class NestHostAttribute extends AttributeInfo
{
    public static final String tag = "NestHost";
    
    NestHostAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) {
        super(constPool, n, dataInputStream);
    }
    
    private NestHostAttribute(final ConstPool constPool, final int n) {
        super(constPool, "NestHost", new byte[2]);
        ByteArray.write16bit(n, this.get(), 0);
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map<String, String> map) {
        return new NestHostAttribute(constPool, this.getConstPool().copy(ByteArray.readU16bit(this.get(), 0), constPool, map));
    }
    
    public int hostClassIndex() {
        return ByteArray.readU16bit(this.info, 0);
    }
}
