// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.util;

import org.jetbrains.annotations.Nullable;
import org.bukkit.util.NumberConversions;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0018\b\u0086\b\u0018\u00002\u00020\u0001B\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003B\u001f\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004H\u00c7\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0006H\u00c6\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0006H\u00c6\u0003¢\u0006\u0004\b\u000e\u0010\rJ.\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006H\u00c7\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0015\u001a\u00020\u00142\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u0017H\u00d6\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001b\u001a\u00020\u001aH\u00d7\u0001¢\u0006\u0004\b\u001b\u0010\u001cR\"\u0010\u001d\u001a\u00020\u00068\u0007@\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010\r\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u00048\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010\u000bR$\u0010)\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00118G@GX\u0086\u000e¢\u0006\f\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R$\u0010,\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00118G@GX\u0086\u000e¢\u0006\f\u001a\u0004\b*\u0010&\"\u0004\b+\u0010(R\"\u0010-\u001a\u00020\u00068\u0007@\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\b-\u0010\u001e\u001a\u0004\b.\u0010\r\"\u0004\b/\u0010!R$\u00102\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00118G@GX\u0086\u000e¢\u0006\f\u001a\u0004\b0\u0010&\"\u0004\b1\u0010(" }, d2 = { "Ldev/imanity/antixray/api/util/Position;", "", "<init>", "()V", "Ldev/imanity/antixray/api/util/Vec3d;", "p0", "", "p1", "p2", "(Ldev/imanity/antixray/api/util/Vec3d;FF)V", "component1", "()Ldev/imanity/antixray/api/util/Vec3d;", "component2", "()F", "component3", "copy", "(Ldev/imanity/antixray/api/util/Vec3d;FF)Ldev/imanity/antixray/api/util/Position;", "", "distanceSquared", "(Ldev/imanity/antixray/api/util/Position;)D", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "pitch", "F", "getPitch", "setPitch", "(F)V", "vec3d", "Ldev/imanity/antixray/api/util/Vec3d;", "getVec3d", "getX", "()D", "setX", "(D)V", "x", "getY", "setY", "y", "yaw", "getYaw", "setYaw", "getZ", "setZ", "z" })
public final class Position
{
    @NotNull
    private final Vec3d vec3d;
    private float yaw;
    private float pitch;
    
    public Position(@NotNull final Vec3d vec3d, final float yaw, final float pitch) {
        Intrinsics.checkNotNullParameter((Object)vec3d, "");
        this.vec3d = vec3d;
        this.yaw = yaw;
        this.pitch = pitch;
    }
    
    @NotNull
    @JvmName(name = "getVec3d")
    public final Vec3d getVec3d() {
        return this.vec3d;
    }
    
    @JvmName(name = "getYaw")
    public final float getYaw() {
        return this.yaw;
    }
    
    @JvmName(name = "setYaw")
    public final void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    @JvmName(name = "getPitch")
    public final float getPitch() {
        return this.pitch;
    }
    
    @JvmName(name = "setPitch")
    public final void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    @JvmName(name = "getX")
    public final double getX() {
        return this.vec3d.x;
    }
    
    @JvmName(name = "setX")
    public final void setX(final double x) {
        this.vec3d.x = x;
    }
    
    @JvmName(name = "getY")
    public final double getY() {
        return this.vec3d.y;
    }
    
    @JvmName(name = "setY")
    public final void setY(final double y) {
        this.vec3d.y = y;
    }
    
    @JvmName(name = "getZ")
    public final double getZ() {
        return this.vec3d.z;
    }
    
    @JvmName(name = "setZ")
    public final void setZ(final double z) {
        this.vec3d.z = z;
    }
    
    public Position() {
        this(new Vec3d(0.0, 0.0, 0.0), 0.0f, 0.0f);
    }
    
    public final double distanceSquared(@NotNull final Position position) {
        Intrinsics.checkNotNullParameter((Object)position, "");
        return NumberConversions.square(this.vec3d.x - position.vec3d.x) + NumberConversions.square(this.vec3d.y - position.vec3d.y) + NumberConversions.square(this.vec3d.z - position.vec3d.z);
    }
    
    @NotNull
    public final Vec3d component1() {
        return this.vec3d;
    }
    
    public final float component2() {
        return this.yaw;
    }
    
    public final float component3() {
        return this.pitch;
    }
    
    @NotNull
    public final Position copy(@NotNull final Vec3d vec3d, final float n, final float n2) {
        Intrinsics.checkNotNullParameter((Object)vec3d, "");
        return new Position(vec3d, n, n2);
    }
    
    @NotNull
    @Override
    public String toString() {
        return "Position(vec3d=" + this.vec3d + ", yaw=" + this.yaw + ", pitch=" + this.pitch + ')';
    }
    
    @Override
    public int hashCode() {
        return (this.vec3d.hashCode() * 31 + Float.hashCode(this.yaw)) * 31 + Float.hashCode(this.pitch);
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        final Position position = (Position)o;
        return Intrinsics.areEqual((Object)this.vec3d, (Object)position.vec3d) && Float.compare(this.yaw, position.yaw) == 0 && Float.compare(this.pitch, position.pitch) == 0;
    }
}
