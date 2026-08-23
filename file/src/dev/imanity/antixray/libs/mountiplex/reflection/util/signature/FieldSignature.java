// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.signature;

public final class FieldSignature
{
    private final String name;
    
    public FieldSignature(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof FieldSignature && this.name.equals(((FieldSignature)o).name);
    }
}
