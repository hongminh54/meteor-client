// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api;

import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002H\u00c7\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004H\u00c7\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0006H\u00c7\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u00c7\u0003¢\u0006\u0004\b\u0012\u0010\u0011J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0006H\u00c7\u0003¢\u0006\u0004\b\u0013\u0010\u0011JF\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006H\u00c7\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0017\u001a\u00020\u00162\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u0019H\u00d6\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001d\u001a\u00020\u001cH\u00d7\u0001¢\u0006\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020\u00068\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\u0011R\u001a\u0010\"\u001a\u00020\u00028\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010\rR\u001c\u0010%\u001a\u0004\u0018\u00010\u00068\u0007X\u0087\u0004¢\u0006\f\n\u0004\b%\u0010 \u001a\u0004\b&\u0010\u0011R\u001c\u0010'\u001a\u0004\u0018\u00010\u00068\u0007X\u0087\u0004¢\u0006\f\n\u0004\b'\u0010 \u001a\u0004\b(\u0010\u0011R\u001a\u0010)\u001a\u00020\u00048\u0007X\u0087\u0004¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010\u000f" }, d2 = { "Ldev/imanity/antixray/api/Vision;", "", "Lorg/bukkit/entity/Player;", "p0", "Lorg/bukkit/World;", "p1", "Ldev/imanity/antixray/api/DirectionalVector;", "p2", "p3", "p4", "<init>", "(Lorg/bukkit/entity/Player;Lorg/bukkit/World;Ldev/imanity/antixray/api/DirectionalVector;Ldev/imanity/antixray/api/DirectionalVector;Ldev/imanity/antixray/api/DirectionalVector;)V", "component1", "()Lorg/bukkit/entity/Player;", "component2", "()Lorg/bukkit/World;", "component3", "()Ldev/imanity/antixray/api/DirectionalVector;", "component4", "component5", "copy", "(Lorg/bukkit/entity/Player;Lorg/bukkit/World;Ldev/imanity/antixray/api/DirectionalVector;Ldev/imanity/antixray/api/DirectionalVector;Ldev/imanity/antixray/api/DirectionalVector;)Ldev/imanity/antixray/api/Vision;", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "origin", "Ldev/imanity/antixray/api/DirectionalVector;", "getOrigin", "player", "Lorg/bukkit/entity/Player;", "getPlayer", "thirdPersonBack", "getThirdPersonBack", "thirdPersonFront", "getThirdPersonFront", "world", "Lorg/bukkit/World;", "getWorld" })
public final class Vision
{
    @NotNull
    private final Player player;
    @NotNull
    private final World world;
    @NotNull
    private final DirectionalVector origin;
    @Nullable
    private final DirectionalVector thirdPersonFront;
    @Nullable
    private final DirectionalVector thirdPersonBack;
    
    public Vision(@NotNull final Player player, @NotNull final World world, @NotNull final DirectionalVector origin, @Nullable final DirectionalVector thirdPersonFront, @Nullable final DirectionalVector thirdPersonBack) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)world, "");
        Intrinsics.checkNotNullParameter((Object)origin, "");
        this.player = player;
        this.world = world;
        this.origin = origin;
        this.thirdPersonFront = thirdPersonFront;
        this.thirdPersonBack = thirdPersonBack;
    }
    
    @NotNull
    @JvmName(name = "getPlayer")
    public final Player getPlayer() {
        return this.player;
    }
    
    @NotNull
    @JvmName(name = "getWorld")
    public final World getWorld() {
        return this.world;
    }
    
    @NotNull
    @JvmName(name = "getOrigin")
    public final DirectionalVector getOrigin() {
        return this.origin;
    }
    
    @Nullable
    @JvmName(name = "getThirdPersonFront")
    public final DirectionalVector getThirdPersonFront() {
        return this.thirdPersonFront;
    }
    
    @Nullable
    @JvmName(name = "getThirdPersonBack")
    public final DirectionalVector getThirdPersonBack() {
        return this.thirdPersonBack;
    }
    
    @NotNull
    public final Player component1() {
        return this.player;
    }
    
    @NotNull
    public final World component2() {
        return this.world;
    }
    
    @NotNull
    public final DirectionalVector component3() {
        return this.origin;
    }
    
    @Nullable
    public final DirectionalVector component4() {
        return this.thirdPersonFront;
    }
    
    @Nullable
    public final DirectionalVector component5() {
        return this.thirdPersonBack;
    }
    
    @NotNull
    public final Vision copy(@NotNull final Player player, @NotNull final World world, @NotNull final DirectionalVector directionalVector, @Nullable final DirectionalVector directionalVector2, @Nullable final DirectionalVector directionalVector3) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)world, "");
        Intrinsics.checkNotNullParameter((Object)directionalVector, "");
        return new Vision(player, world, directionalVector, directionalVector2, directionalVector3);
    }
    
    @NotNull
    @Override
    public String toString() {
        return "Vision(player=" + this.player + ", world=" + this.world + ", origin=" + this.origin + ", thirdPersonFront=" + this.thirdPersonFront + ", thirdPersonBack=" + this.thirdPersonBack + ')';
    }
    
    @Override
    public int hashCode() {
        return (((this.player.hashCode() * 31 + this.world.hashCode()) * 31 + this.origin.hashCode()) * 31 + ((this.thirdPersonFront == null) ? 0 : this.thirdPersonFront.hashCode())) * 31 + ((this.thirdPersonBack == null) ? 0 : this.thirdPersonBack.hashCode());
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vision)) {
            return false;
        }
        final Vision vision = (Vision)o;
        return Intrinsics.areEqual((Object)this.player, (Object)vision.player) && Intrinsics.areEqual((Object)this.world, (Object)vision.world) && Intrinsics.areEqual((Object)this.origin, (Object)vision.origin) && Intrinsics.areEqual((Object)this.thirdPersonFront, (Object)vision.thirdPersonFront) && Intrinsics.areEqual((Object)this.thirdPersonBack, (Object)vision.thirdPersonBack);
    }
}
