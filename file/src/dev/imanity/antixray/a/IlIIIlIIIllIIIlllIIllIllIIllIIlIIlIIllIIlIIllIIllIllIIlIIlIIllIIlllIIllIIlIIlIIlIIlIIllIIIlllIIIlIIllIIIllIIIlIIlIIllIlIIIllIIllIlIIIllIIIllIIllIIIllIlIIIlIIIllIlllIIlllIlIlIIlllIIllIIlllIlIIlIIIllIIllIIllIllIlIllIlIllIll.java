// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

public class IlIIIlIIIllIIIlllIIllIllIIllIIlIIlIIllIIlIIllIIllIllIIlIIlIIllIIlllIIllIIlIIlIIlIIlIIllIIIlllIIIlIIllIIIllIIIlIIlIIllIlIIIllIIllIlIIIllIIIllIIllIIIllIlIIIlIIIllIlllIIlllIlIlIIlllIIllIIlllIlIIlIIIllIIllIIllIllIlIllIlIllIll
{
    private int dH;
    
    @Override
    public String toString() {
        final byte[] bytes = new byte[3];
        this.dH = -233257106;
        bytes[0] = (byte)(this.dH >>> 10);
        this.dH = -2031106493;
        bytes[1] = (byte)(this.dH >>> 5);
        this.dH = 1492595599;
        bytes[2] = (byte)(this.dH >>> 8);
        return new String(bytes);
    }
}
