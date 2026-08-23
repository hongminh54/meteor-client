// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.io.DataInputStream;

class InterfaceMethodrefInfo extends MemberrefInfo
{
    static final int tag = 11;
    
    public InterfaceMethodrefInfo(final int n, final int n2, final int n3) {
        super(n, n2, n3);
    }
    
    public InterfaceMethodrefInfo(final DataInputStream dataInputStream, final int n) {
        super(dataInputStream, n);
    }
    
    @Override
    public int getTag() {
        return 11;
    }
    
    @Override
    public String getTagName() {
        return "Interface";
    }
    
    @Override
    protected int copy2(final ConstPool constPool, final int n, final int n2) {
        return constPool.addInterfaceMethodrefInfo(n, n2);
    }
}
