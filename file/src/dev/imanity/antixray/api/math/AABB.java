// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.math;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.JvmName;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0016\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003¢\u0006\u0004\b\r\u0010\fJ\u0010\u0010\u000e\u001a\u00020\u0002H\u00c6\u0003¢\u0006\u0004\b\u000e\u0010\fJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003¢\u0006\u0004\b\u000f\u0010\fJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003¢\u0006\u0004\b\u0010\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003¢\u0006\u0004\b\u0011\u0010\fJ%\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0013\u0010\u0014JL\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0002H\u00c7\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u001a\u0010\u0017\u001a\u00020\u00122\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u0019H\u00d6\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001d\u001a\u00020\u001cH\u00d7\u0001¢\u0006\u0004\b\u001d\u0010\u001eR\"\u0010\u001f\u001a\u00020\u00028\u0007@\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\f\"\u0004\b\"\u0010#R\"\u0010$\u001a\u00020\u00028\u0007@\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\b$\u0010 \u001a\u0004\b%\u0010\f\"\u0004\b&\u0010#R\"\u0010'\u001a\u00020\u00028\u0007@\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\b'\u0010 \u001a\u0004\b(\u0010\f\"\u0004\b)\u0010#R\"\u0010*\u001a\u00020\u00028\u0007@\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\b*\u0010 \u001a\u0004\b+\u0010\f\"\u0004\b,\u0010#R\"\u0010-\u001a\u00020\u00028\u0007@\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\b-\u0010 \u001a\u0004\b.\u0010\f\"\u0004\b/\u0010#R\"\u00100\u001a\u00020\u00028\u0007@\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\b0\u0010 \u001a\u0004\b1\u0010\f\"\u0004\b2\u0010#" }, d2 = { "Ldev/imanity/antixray/api/math/AABB;", "", "", "p0", "p1", "p2", "p3", "p4", "p5", "<init>", "(DDDDDD)V", "component1", "()D", "component2", "component3", "component4", "component5", "component6", "", "contains", "(DDD)Z", "copy", "(DDDDDD)Ldev/imanity/antixray/api/math/AABB;", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "maxX", "D", "getMaxX", "setMaxX", "(D)V", "maxY", "getMaxY", "setMaxY", "maxZ", "getMaxZ", "setMaxZ", "minX", "getMinX", "setMinX", "minY", "getMinY", "setMinY", "minZ", "getMinZ", "setMinZ" })
public final class AABB
{
    private double minX;
    private double minY;
    private double minZ;
    private double maxX;
    private double maxY;
    private double maxZ;
    
    public AABB(final double minX, final double minY, final double minZ, final double maxX, final double maxY, final double maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }
    
    @JvmName(name = "getMinX")
    public final double getMinX() {
        return this.minX;
    }
    
    @JvmName(name = "setMinX")
    public final void setMinX(final double minX) {
        this.minX = minX;
    }
    
    @JvmName(name = "getMinY")
    public final double getMinY() {
        return this.minY;
    }
    
    @JvmName(name = "setMinY")
    public final void setMinY(final double minY) {
        this.minY = minY;
    }
    
    @JvmName(name = "getMinZ")
    public final double getMinZ() {
        return this.minZ;
    }
    
    @JvmName(name = "setMinZ")
    public final void setMinZ(final double minZ) {
        this.minZ = minZ;
    }
    
    @JvmName(name = "getMaxX")
    public final double getMaxX() {
        return this.maxX;
    }
    
    @JvmName(name = "setMaxX")
    public final void setMaxX(final double maxX) {
        this.maxX = maxX;
    }
    
    @JvmName(name = "getMaxY")
    public final double getMaxY() {
        return this.maxY;
    }
    
    @JvmName(name = "setMaxY")
    public final void setMaxY(final double maxY) {
        this.maxY = maxY;
    }
    
    @JvmName(name = "getMaxZ")
    public final double getMaxZ() {
        return this.maxZ;
    }
    
    @JvmName(name = "setMaxZ")
    public final void setMaxZ(final double maxZ) {
        this.maxZ = maxZ;
    }
    
    public final boolean contains(final double n, final double n2, final double n3) {
        final double minX = this.minX;
        if (n <= this.maxX && minX <= n) {
            final double minY = this.minY;
            if (n2 <= this.maxY && minY <= n2) {
                final double minZ = this.minZ;
                if (n3 <= this.maxZ && minZ <= n3) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public final double component1() {
        return this.minX;
    }
    
    public final double component2() {
        return this.minY;
    }
    
    public final double component3() {
        return this.minZ;
    }
    
    public final double component4() {
        return this.maxX;
    }
    
    public final double component5() {
        return this.maxY;
    }
    
    public final double component6() {
        return this.maxZ;
    }
    
    @NotNull
    public final AABB copy(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        return new AABB(n, n2, n3, n4, n5, n6);
    }
    
    @NotNull
    @Override
    public String toString() {
        return "AABB(minX=" + this.minX + ", minY=" + this.minY + ", minZ=" + this.minZ + ", maxX=" + this.maxX + ", maxY=" + this.maxY + ", maxZ=" + this.maxZ + ')';
    }
    
    @Override
    public int hashCode() {
        return ((((Double.hashCode(this.minX) * 31 + Double.hashCode(this.minY)) * 31 + Double.hashCode(this.minZ)) * 31 + Double.hashCode(this.maxX)) * 31 + Double.hashCode(this.maxY)) * 31 + Double.hashCode(this.maxZ);
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AABB)) {
            return false;
        }
        final AABB aabb = (AABB)o;
        return Double.compare(this.minX, aabb.minX) == 0 && Double.compare(this.minY, aabb.minY) == 0 && Double.compare(this.minZ, aabb.minZ) == 0 && Double.compare(this.maxX, aabb.maxX) == 0 && Double.compare(this.maxY, aabb.maxY) == 0 && Double.compare(this.maxZ, aabb.maxZ) == 0;
    }
}
