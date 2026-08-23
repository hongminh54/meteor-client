// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.util.Map;
import java.io.DataInputStream;

public class SyntheticAttribute extends AttributeInfo
{
    public static final String tag = "Synthetic";
    
    SyntheticAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) {
        super(constPool, n, dataInputStream);
    }
    
    public SyntheticAttribute(final ConstPool constPool) {
        super(constPool, "Synthetic", new byte[0]);
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map<String, String> map) {
        return new SyntheticAttribute(constPool);
    }
}
