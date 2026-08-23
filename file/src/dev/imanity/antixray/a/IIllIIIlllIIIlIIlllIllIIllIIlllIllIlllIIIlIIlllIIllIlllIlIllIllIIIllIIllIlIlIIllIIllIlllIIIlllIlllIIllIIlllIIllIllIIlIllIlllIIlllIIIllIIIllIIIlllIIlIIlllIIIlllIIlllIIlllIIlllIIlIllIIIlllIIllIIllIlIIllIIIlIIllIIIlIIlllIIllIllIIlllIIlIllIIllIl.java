// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

public class IIllIIIlllIIIlIIlllIllIIllIIlllIllIlllIIIlIIlllIIllIlllIlIllIllIIIllIIllIlIlIIllIIllIlllIIIlllIlllIIllIIlllIIllIllIIlIllIlllIIlllIIIllIIIllIIIlllIIlIIlllIIIlllIIlllIIlllIIlllIIlIllIIIlllIIllIIllIlIIllIIIlIIllIIIlIIlllIIllIllIIlllIIlIllIIllIl
{
    private int dG;
    private int dH;
    
    public IIllIIIlllIIIlIIlllIllIIllIIlllIllIlllIIIlIIlllIIllIlllIlIllIllIIIllIIllIlIlIIllIIllIlllIIIlllIlllIIllIIlllIIllIllIIlIllIlllIIlllIIIllIIIllIIIlllIIlIIlllIIIlllIIlllIIlllIIlllIIlIllIIIlllIIllIIllIlIIllIIIlIIllIIIlIIlllIIllIllIIlllIIlIllIIllIl() {
        this.dG = 120;
    }
    
    @Override
    public String toString() {
        final byte[] bytes = new byte[6];
        this.dH = -954328920;
        bytes[0] = (byte)(this.dH >>> 7);
        this.dH = -94089180;
        bytes[1] = (byte)(this.dH >>> 17);
        this.dH = -1794559070;
        bytes[2] = (byte)(this.dH >>> 8);
        this.dH = 1872996763;
        bytes[3] = (byte)(this.dH >>> 3);
        this.dH = -828027717;
        bytes[4] = (byte)(this.dH >>> 6);
        this.dH = -1047884009;
        bytes[5] = (byte)(this.dH >>> 19);
        return new String(bytes);
    }
}
