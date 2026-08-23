// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api;

import dev.imanity.antixray.api.chunk.IDirectChunkManager;
import org.bukkit.block.Block;
import dev.imanity.antixray.api.chunk.DirectChunk;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;
import org.bukkit.entity.Player;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0016\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eJ\u0016\u0010\u001f\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u0018R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006!" }, d2 = { "Ldev/imanity/antixray/api/AntiXrayAPI;", "", "()V", "instance", "Ldev/imanity/antixray/api/AntiXrayApiInstance;", "getInstance", "()Ldev/imanity/antixray/api/AntiXrayApiInstance;", "setInstance", "(Ldev/imanity/antixray/api/AntiXrayApiInstance;)V", "addRaytraceBlock", "", "location", "Lorg/bukkit/Location;", "replacement", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "directChunk", "Ldev/imanity/antixray/api/chunk/DirectChunk;", "getDirectChunkManager", "Ldev/imanity/antixray/api/chunk/IDirectChunkManager;", "getRaytracePlayer", "Ldev/imanity/antixray/api/IRaytracePlayer;", "player", "Lorg/bukkit/entity/Player;", "isBypassed", "", "queueBlockChange", "block", "Lorg/bukkit/block/Block;", "scheduleTask", "runnable", "Ljava/lang/Runnable;", "setBypassed", "bypassed", "api" })
public final class AntiXrayAPI
{
    @NotNull
    public static final AntiXrayAPI INSTANCE;
    public static AntiXrayApiInstance instance;
    
    private AntiXrayAPI() {
    }
    
    @NotNull
    public final AntiXrayApiInstance getInstance() {
        final AntiXrayApiInstance instance = AntiXrayAPI.instance;
        if (instance != null) {
            return instance;
        }
        Intrinsics.throwUninitializedPropertyAccessException("");
        return null;
    }
    
    public final void setInstance(@NotNull final AntiXrayApiInstance instance) {
        Intrinsics.checkNotNullParameter((Object)instance, "");
        AntiXrayAPI.instance = instance;
    }
    
    @Nullable
    public final IRaytracePlayer getRaytracePlayer(@NotNull final Player player) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        return this.getInstance().getRaytracePlayer(player);
    }
    
    public final boolean isBypassed(@NotNull final Player player) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        return this.getInstance().isBypassed(player);
    }
    
    public final void setBypassed(@NotNull final Player player, final boolean b) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        this.getInstance().setBypassed(player, b);
    }
    
    public final void addRaytraceBlock(@NotNull final Location location) {
        Intrinsics.checkNotNullParameter((Object)location, "");
        this.getInstance().addRaytraceBlock(location);
    }
    
    public final void addRaytraceBlock(@NotNull final Location location, @NotNull final WrappedBlockState wrappedBlockState) {
        Intrinsics.checkNotNullParameter((Object)location, "");
        Intrinsics.checkNotNullParameter((Object)wrappedBlockState, "");
        this.getInstance().addRaytraceBlock(location, wrappedBlockState);
    }
    
    public final void addRaytraceBlock(@NotNull final Location location, @NotNull final DirectChunk directChunk, @NotNull final WrappedBlockState wrappedBlockState) {
        Intrinsics.checkNotNullParameter((Object)location, "");
        Intrinsics.checkNotNullParameter((Object)directChunk, "");
        Intrinsics.checkNotNullParameter((Object)wrappedBlockState, "");
        this.getInstance().addRaytraceBlock(location, directChunk, wrappedBlockState);
    }
    
    public final void queueBlockChange(@NotNull final Block block) {
        Intrinsics.checkNotNullParameter((Object)block, "");
        this.getInstance().queueBlockChange(block);
    }
    
    @NotNull
    public final IDirectChunkManager getDirectChunkManager() {
        return this.getInstance().getDirectChunkManager();
    }
    
    public final void scheduleTask(@NotNull final Runnable runnable) {
        Intrinsics.checkNotNullParameter((Object)runnable, "");
        this.getInstance().scheduleTask(runnable);
    }
    
    public final void scheduleTask(@NotNull final Player player, @NotNull final Runnable runnable) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)runnable, "");
        this.getInstance().scheduleTask(player, runnable);
    }
    
    static {
        INSTANCE = new AntiXrayAPI();
    }
}
