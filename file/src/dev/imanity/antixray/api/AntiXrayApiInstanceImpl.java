// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api;

import dev.imanity.antixray.api.chunk.IDirectChunkManager;
import dev.imanity.antixray.a.IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll;
import org.bukkit.World;
import dev.imanity.antixray.a.IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl;
import org.bukkit.block.Block;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import dev.imanity.antixray.api.chunk.DirectChunk;
import org.bukkit.Location;
import dev.imanity.antixray.a.IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl;
import dev.imanity.antixray.api.event.PlayerBypassChangedEvent;
import io.fairyproject.data.MetaKey;
import dev.imanity.antixray.AntiXrayKeys;
import org.jetbrains.annotations.Nullable;
import dev.imanity.antixray.a.IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll;
import kotlin.jvm.internal.Intrinsics;
import org.bukkit.entity.Player;
import dev.imanity.antixray.a.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll;
import dev.imanity.antixray.a.IllIlllIIllIIlIIlIlIIIllIIllIIlllIIIlIIlIIlllIIlIlIIIlIlIIIlIIllIlllIIIlllIIllIllIllIIlllIlIllIIlIlIIIlIlIlllIIIlIlllIIlIIllIIlIIllIllIIllIlllIIIllIllIlIIIllIIllIIllIIlIIIlIIlIlIIIllIIllIllIlllIIIllIIIllIIIlllIIlIIIlIlll;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import kotlin.Lazy;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0006\u001a\u00020\u0017H\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020#H\u0016J\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020#H\u0016J\u0018\u0010$\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u001dH\u0016R#\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R#\u0010\n\u001a\n \u0005*\u0004\u0018\u00010\u000b0\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\r¨\u0006&" }, d2 = { "Ldev/imanity/antixray/api/AntiXrayApiInstanceImpl;", "Ldev/imanity/antixray/api/AntiXrayApiInstance;", "()V", "directChunkManager", "Ldev/imanity/antixray/core/chunk/DirectChunkManager;", "kotlin.jvm.PlatformType", "getDirectChunkManager", "()Ldev/imanity/antixray/core/chunk/DirectChunkManager;", "directChunkManager$delegate", "Lkotlin/Lazy;", "raytraceServer", "Ldev/imanity/antixray/core/RaytraceServer;", "getRaytraceServer", "()Ldev/imanity/antixray/core/RaytraceServer;", "raytraceServer$delegate", "addRaytraceBlock", "", "location", "Lorg/bukkit/Location;", "directChunk", "Ldev/imanity/antixray/api/chunk/DirectChunk;", "replacement", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "Ldev/imanity/antixray/api/chunk/IDirectChunkManager;", "getRaytracePlayer", "Ldev/imanity/antixray/api/IRaytracePlayer;", "player", "Lorg/bukkit/entity/Player;", "isBypassed", "", "queueBlockChange", "block", "Lorg/bukkit/block/Block;", "scheduleTask", "runnable", "Ljava/lang/Runnable;", "setBypassed", "value", "plugin" })
public final class AntiXrayApiInstanceImpl implements AntiXrayApiInstance
{
    @NotNull
    private final Lazy directChunkManager$delegate;
    @NotNull
    private final Lazy raytraceServer$delegate;
    
    public AntiXrayApiInstanceImpl() {
        this.directChunkManager$delegate = LazyKt.lazy((Function0)AntiXrayApiInstanceImpl$directChunkManager.AntiXrayApiInstanceImpl$directChunkManager$2.INSTANCE);
        this.raytraceServer$delegate = LazyKt.lazy((Function0)AntiXrayApiInstanceImpl$raytraceServer.AntiXrayApiInstanceImpl$raytraceServer$2.INSTANCE);
    }
    
    private final IllIlllIIllIIlIIlIlIIIllIIllIIlllIIIlIIlIIlllIIlIlIIIlIlIIIlIIllIlllIIIlllIIllIllIllIIlllIlIllIIlIlIIIlIlIlllIIIlIlllIIlIIllIIlIIllIllIIllIlllIIIllIllIlIIIllIIllIIllIIlIIIlIIlIlIIIllIIllIllIlllIIIllIIIllIIIlllIIlIIIlIlll getDirectChunkManager() {
        return (IllIlllIIllIIlIIlIlIIIllIIllIIlllIIIlIIlIIlllIIlIlIIIlIlIIIlIIllIlllIIIlllIIllIllIllIIlllIlIllIIlIlIIIlIlIlllIIIlIlllIIlIIllIIlIIllIllIIllIlllIIIllIllIlIIIllIIllIIllIIlIIIlIIlIlIIIllIIllIllIlllIIIllIIIllIIIlllIIlIIIlIlll)this.directChunkManager$delegate.getValue();
    }
    
    private final IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll getRaytraceServer() {
        return (IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll)this.raytraceServer$delegate.getValue();
    }
    
    @Nullable
    @Override
    public IRaytracePlayer getRaytracePlayer(@NotNull final Player player) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        return IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(player);
    }
    
    @Override
    public boolean isBypassed(@NotNull final Player player) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        final Object orDefault = IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll(player).getOrDefault((MetaKey)AntiXrayKeys.INSTANCE.getAntiXrayBypass(), (Object)false);
        Intrinsics.checkNotNullExpressionValue(orDefault, "");
        return (boolean)orDefault;
    }
    
    @Override
    public void setBypassed(@NotNull final Player player, final boolean b) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        if (this.isBypassed(player) == b) {
            return;
        }
        IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll(player).put((MetaKey)AntiXrayKeys.INSTANCE.getAntiXrayBypass(), (Object)b);
        final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl = IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(player);
        if (iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl != null) {
            iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl.clear();
        }
        IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IllIIllIlIlIllIIllIIIllIIIllIIIllIIIlIlIlIllIlIIllIIlllIlllIIlIIIllIIlIllIIlllIIllIllIIIlIIlIlIlllIIIlllIIlIIIllIIIllIIIllIlIIlllIIlIlIIIlllIIlIlIIIlllIIlllIIlIllIllIIlIIIllIIllIlllIIllIIllIllIIllIIllIIlIIllIllIIllIIlIIl(player);
        new PlayerBypassChangedEvent(player, b).call();
    }
    
    @Override
    public void addRaytraceBlock(@NotNull final Location location, @NotNull final DirectChunk directChunk, @Nullable final WrappedBlockState wrappedBlockState) {
        Intrinsics.checkNotNullParameter((Object)location, "");
        Intrinsics.checkNotNullParameter((Object)directChunk, "");
        final IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll raytraceServer = this.getRaytraceServer();
        Intrinsics.checkNotNullExpressionValue((Object)raytraceServer, "");
        IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(raytraceServer, location, directChunk, wrappedBlockState, false, false, 24, null);
    }
    
    @Override
    public void queueBlockChange(@NotNull final Block block) {
        Intrinsics.checkNotNullParameter((Object)block, "");
        final World world = block.getWorld();
        Intrinsics.checkNotNullExpressionValue((Object)world, "");
        final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll illIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll = IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl.IllIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll(world);
        if (illIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll == null) {
            return;
        }
        illIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(block);
    }
    
    @NotNull
    @Override
    public IDirectChunkManager getDirectChunkManager() {
        final IllIlllIIllIIlIIlIlIIIllIIllIIlllIIIlIIlIIlllIIlIlIIIlIlIIIlIIllIlllIIIlllIIllIllIllIIlllIlIllIIlIlIIIlIlIlllIIIlIlllIIlIIllIIlIIllIllIIllIlllIIIllIllIlIIIllIIllIIllIIlIIIlIIlIlIIIllIIllIllIlllIIIllIIIllIIIlllIIlIIIlIlll directChunkManager = this.getDirectChunkManager();
        Intrinsics.checkNotNullExpressionValue((Object)directChunkManager, "");
        return directChunkManager;
    }
    
    @Override
    public void scheduleTask(@NotNull final Runnable runnable) {
        Intrinsics.checkNotNullParameter((Object)runnable, "");
        this.getRaytraceServer().schedule(runnable);
    }
    
    @Override
    public void scheduleTask(@NotNull final Player player, @NotNull final Runnable runnable) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)runnable, "");
        this.getRaytraceServer().IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(player, runnable);
    }
    
    @Override
    public void addRaytraceBlock(@NotNull final Location location) {
        DefaultImpls.addRaytraceBlock(this, location);
    }
    
    @Override
    public void addRaytraceBlock(@NotNull final Location location, @Nullable final WrappedBlockState wrappedBlockState) {
        DefaultImpls.addRaytraceBlock(this, location, wrappedBlockState);
    }
}
