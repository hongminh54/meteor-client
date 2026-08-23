// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

public final class Handle
{
    private final int tag;
    private final String owner;
    private final String name;
    private final String descriptor;
    private final boolean isInterface;
    
    @Deprecated
    public Handle(final int n, final String s, final String s2, final String s3) {
        this(n, s, s2, s3, n == 9);
    }
    
    public Handle(final int tag, final String owner, final String name, final String descriptor, final boolean isInterface) {
        this.tag = tag;
        this.owner = owner;
        this.name = name;
        this.descriptor = descriptor;
        this.isInterface = isInterface;
    }
    
    public int getTag() {
        return this.tag;
    }
    
    public String getOwner() {
        return this.owner;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDesc() {
        return this.descriptor;
    }
    
    public boolean isInterface() {
        return this.isInterface;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Handle)) {
            return false;
        }
        final Handle handle = (Handle)o;
        return this.tag == handle.tag && this.isInterface == handle.isInterface && this.owner.equals(handle.owner) && this.name.equals(handle.name) && this.descriptor.equals(handle.descriptor);
    }
    
    @Override
    public int hashCode() {
        return this.tag + (this.isInterface ? 64 : 0) + this.owner.hashCode() * this.name.hashCode() * this.descriptor.hashCode();
    }
    
    @Override
    public String toString() {
        return stringConcat$0(this.owner, this.name, this.descriptor, this.tag, this.isInterface ? " itf" : "");
    }
    
    private static /* synthetic */ String stringConcat$0(final String str, final String str2, final String str3, final int i, final String str4) {
        return str + "." + str2 + str3 + " (" + i + str4 + ")";
    }
}
