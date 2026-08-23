// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.util;

import org.jetbrains.annotations.Nullable;
import org.bukkit.Location;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.bukkit.util.Vector;
import kotlin.jvm.JvmField;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002H\u00c6\u0003¢\u0006\u0004\b\f\u0010\u000bJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003¢\u0006\u0004\b\r\u0010\u000bJ.\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0002H\u00c7\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0000¢\u0006\u0004\b\u0014\u0010\u0011J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0019\u001a\u00020\u0018H\u00d6\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u001b\u0010\tJ%\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u001d\u0010\u0007J\u0017\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u001eH\u0007¢\u0006\u0004\b\u001d\u0010\u001fJ\u0017\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020 H\u0007¢\u0006\u0004\b\u001d\u0010!J-\u0010#\u001a\u00020\u001c2\u0006\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u0002¢\u0006\u0004\b#\u0010$J\u0017\u0010%\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0007¢\u0006\u0004\b%\u0010\u0013J\u000f\u0010'\u001a\u00020&H\u0017¢\u0006\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010+\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b+\u0010*R\u0016\u0010,\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b,\u0010*" }, d2 = { "Ldev/imanity/antixray/api/util/Vec3d;", "", "", "p0", "p1", "p2", "<init>", "(DDD)V", "clone", "()Ldev/imanity/antixray/api/util/Vec3d;", "component1", "()D", "component2", "component3", "copy", "(DDD)Ldev/imanity/antixray/api/util/Vec3d;", "distanceSquared", "(Ldev/imanity/antixray/api/util/Vec3d;)D", "div", "(Ldev/imanity/antixray/api/util/Vec3d;)Ldev/imanity/antixray/api/util/Vec3d;", "dot", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "normalize", "", "set", "Lorg/bukkit/Location;", "(Lorg/bukkit/Location;)Ldev/imanity/antixray/api/util/Vec3d;", "Lorg/bukkit/util/Vector;", "(Lorg/bukkit/util/Vector;)Ldev/imanity/antixray/api/util/Vec3d;", "p3", "setAdd", "(Ldev/imanity/antixray/api/util/Vec3d;DDD)V", "subtract", "", "toString", "()Ljava/lang/String;", "x", "D", "y", "z" })
public final class Vec3d
{
    @JvmField
    public double x;
    @JvmField
    public double y;
    @JvmField
    public double z;
    
    public Vec3d(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public final void set(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    @NotNull
    public final Vec3d set(@NotNull final Vector vector) {
        Intrinsics.checkNotNullParameter((Object)vector, "");
        this.x = vector.getX();
        this.y = vector.getY();
        this.z = vector.getZ();
        return this;
    }
    
    @NotNull
    public final Vec3d set(@NotNull final Location location) {
        Intrinsics.checkNotNullParameter((Object)location, "");
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        return this;
    }
    
    @NotNull
    public final Vec3d subtract(@NotNull final Vec3d vec3d) {
        Intrinsics.checkNotNullParameter((Object)vec3d, "");
        this.x -= vec3d.x;
        this.y -= vec3d.y;
        this.z -= vec3d.z;
        return this;
    }
    
    public final void setAdd(@NotNull final Vec3d vec3d, final double n, final double n2, final double n3) {
        Intrinsics.checkNotNullParameter((Object)vec3d, "");
        this.x = vec3d.x + n;
        this.y = vec3d.y + n2;
        this.z = vec3d.z + n3;
    }
    
    @NotNull
    public final Vec3d div(@NotNull final Vec3d vec3d) {
        Intrinsics.checkNotNullParameter((Object)vec3d, "");
        this.x /= vec3d.x;
        this.z /= vec3d.z;
        this.y /= vec3d.y;
        return this;
    }
    
    @NotNull
    public final Vec3d normalize() {
        final double sqrt = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        this.x /= sqrt;
        this.y /= sqrt;
        this.z /= sqrt;
        return this;
    }
    
    public final double distanceSquared(@NotNull final Vec3d vec3d) {
        Intrinsics.checkNotNullParameter((Object)vec3d, "");
        final double n = this.x - vec3d.x;
        final double n2 = this.y - vec3d.y;
        final double n3 = this.z - vec3d.z;
        return n * n + n2 * n2 + n3 * n3;
    }
    
    @NotNull
    public final Vec3d clone() {
        return new Vec3d(this.x, this.y, this.z);
    }
    
    public final double dot(@NotNull final Vec3d vec3d) {
        Intrinsics.checkNotNullParameter((Object)vec3d, "");
        return this.x * vec3d.x + this.y * vec3d.y + this.z * vec3d.z;
    }
    
    @NotNull
    @Override
    public String toString() {
        return new StringBuilder().append('(').append(this.x).append(", ").append(this.y).append(", ").append(this.z).append(')').toString();
    }
    
    public final double component1() {
        return this.x;
    }
    
    public final double component2() {
        return this.y;
    }
    
    public final double component3() {
        return this.z;
    }
    
    @NotNull
    public final Vec3d copy(final double n, final double n2, final double n3) {
        return new Vec3d(n, n2, n3);
    }
    
    @Override
    public int hashCode() {
        return (Double.hashCode(this.x) * 31 + Double.hashCode(this.y)) * 31 + Double.hashCode(this.z);
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vec3d)) {
            return false;
        }
        final Vec3d vec3d = (Vec3d)o;
        return Double.compare(this.x, vec3d.x) == 0 && Double.compare(this.y, vec3d.y) == 0 && Double.compare(this.z, vec3d.z) == 0;
    }
}
