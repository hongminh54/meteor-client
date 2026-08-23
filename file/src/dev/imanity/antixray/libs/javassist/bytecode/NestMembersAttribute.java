// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.util.Map;
import java.io.DataInputStream;

public class NestMembersAttribute extends AttributeInfo
{
    public static final String tag = "NestMembers";
    
    NestMembersAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) {
        super(constPool, n, dataInputStream);
    }
    
    private NestMembersAttribute(final ConstPool constPool, final byte[] array) {
        super(constPool, "NestMembers", array);
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map<String, String> map) {
        final byte[] value = this.get();
        final byte[] array = new byte[value.length];
        final ConstPool constPool2 = this.getConstPool();
        final int u16bit = ByteArray.readU16bit(value, 0);
        ByteArray.write16bit(u16bit, array, 0);
        for (int i = 0, n = 2; i < u16bit; ++i, n += 2) {
            ByteArray.write16bit(constPool2.copy(ByteArray.readU16bit(value, n), constPool, map), array, n);
        }
        return new NestMembersAttribute(constPool, array);
    }
    
    public int numberOfClasses() {
        return ByteArray.readU16bit(this.info, 0);
    }
    
    public int memberClass(final int n) {
        return ByteArray.readU16bit(this.info, n * 2 + 2);
    }
}
