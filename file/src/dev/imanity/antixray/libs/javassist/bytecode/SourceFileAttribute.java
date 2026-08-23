// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.util.Map;
import java.io.DataInputStream;

public class SourceFileAttribute extends AttributeInfo
{
    public static final String tag = "SourceFile";
    
    SourceFileAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) {
        super(constPool, n, dataInputStream);
    }
    
    public SourceFileAttribute(final ConstPool constPool, final String s) {
        super(constPool, "SourceFile");
        final int addUtf8Info = constPool.addUtf8Info(s);
        this.set(new byte[] { (byte)(addUtf8Info >>> 8), (byte)addUtf8Info });
    }
    
    public String getFileName() {
        return this.getConstPool().getUtf8Info(ByteArray.readU16bit(this.get(), 0));
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map<String, String> map) {
        return new SourceFileAttribute(constPool, this.getFileName());
    }
}
