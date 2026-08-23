// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.io.DataInputStream;

class MethodrefInfo extends MemberrefInfo
{
    static final int tag = 10;
    
    public MethodrefInfo(final int n, final int n2, final int n3) {
        super(n, n2, n3);
    }
    
    public MethodrefInfo(final DataInputStream dataInputStream, final int n) {
        super(dataInputStream, n);
    }
    
    @Override
    public int getTag() {
        return 10;
    }
    
    @Override
    public String getTagName() {
        return "Method";
    }
    
    @Override
    protected int copy2(final ConstPool constPool, final int n, final int n2) {
        return constPool.addMethodrefInfo(n, n2);
    }
}
