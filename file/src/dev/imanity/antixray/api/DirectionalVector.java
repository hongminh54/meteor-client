// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api;

import org.jetbrains.annotations.Nullable;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.bukkit.util.Vector;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u0002H\u00c7\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002H\u00c7\u0003¢\u0006\u0004\b\t\u0010\bJ$\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0002H\u00c7\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\r\u001a\u00020\f2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fH\u00d6\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0012H\u00d7\u0001¢\u0006\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u00028\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u00028\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0016\u001a\u0004\b\u0019\u0010\b" }, d2 = { "Ldev/imanity/antixray/api/DirectionalVector;", "", "Lorg/bukkit/util/Vector;", "p0", "p1", "<init>", "(Lorg/bukkit/util/Vector;Lorg/bukkit/util/Vector;)V", "component1", "()Lorg/bukkit/util/Vector;", "component2", "copy", "(Lorg/bukkit/util/Vector;Lorg/bukkit/util/Vector;)Ldev/imanity/antixray/api/DirectionalVector;", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "direction", "Lorg/bukkit/util/Vector;", "getDirection", "position", "getPosition" })
public final class DirectionalVector
{
    @NotNull
    private final Vector position;
    @NotNull
    private final Vector direction;
    
    public DirectionalVector(@NotNull final Vector position, @NotNull final Vector direction) {
        Intrinsics.checkNotNullParameter((Object)position, "");
        Intrinsics.checkNotNullParameter((Object)direction, "");
        this.position = position;
        this.direction = direction;
    }
    
    @NotNull
    @JvmName(name = "getPosition")
    public final Vector getPosition() {
        return this.position;
    }
    
    @NotNull
    @JvmName(name = "getDirection")
    public final Vector getDirection() {
        return this.direction;
    }
    
    @NotNull
    public final Vector component1() {
        return this.position;
    }
    
    @NotNull
    public final Vector component2() {
        return this.direction;
    }
    
    @NotNull
    public final DirectionalVector copy(@NotNull final Vector vector, @NotNull final Vector vector2) {
        Intrinsics.checkNotNullParameter((Object)vector, "");
        Intrinsics.checkNotNullParameter((Object)vector2, "");
        return new DirectionalVector(vector, vector2);
    }
    
    @NotNull
    @Override
    public String toString() {
        return "DirectionalVector(position=" + this.position + ", direction=" + this.direction + ')';
    }
    
    @Override
    public int hashCode() {
        return this.position.hashCode() * 31 + this.direction.hashCode();
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DirectionalVector)) {
            return false;
        }
        final DirectionalVector directionalVector = (DirectionalVector)o;
        return Intrinsics.areEqual((Object)this.position, (Object)directionalVector.position) && Intrinsics.areEqual((Object)this.direction, (Object)directionalVector.direction);
    }
}
