// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.legacy;

import org.bukkit.Chunk;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.Collection;
import org.bukkit.entity.Entity;
import java.util.Set;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import org.bukkit.block.Block;
import dev.imanity.antixray.api.util.LongHash;
import kotlin.jvm.internal.Intrinsics;
import dev.imanity.antixray.api.chunk.DirectChunk;
import org.bukkit.World;
import dev.imanity.antixray.bootstrap.bukkit.BukkitPlugin;
import org.bukkit.plugin.Plugin;
import java.util.function.Consumer;
import io.fairyproject.bukkit.listener.events.Events;
import org.bukkit.event.world.ChunkUnloadEvent;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import org.jetbrains.annotations.NotNull;
import it.unimi.dsi.fastutil.longs.LongSet;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import dev.imanity.antixray.nms.NativeProvider;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J+\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010H\u0016¢\u0006\u0002\u0010\u0011J+\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010H\u0016¢\u0006\u0002\u0010\u0011J\b\u0010\u0013\u001a\u00020\nH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014" }, d2 = { "Ldev/imanity/antixray/nms/legacy/AbstractLegacyNativeProvider;", "Ldev/imanity/antixray/nms/NativeProvider;", "()V", "cancelUnloadChunks", "Lit/unimi/dsi/fastutil/longs/LongSet;", "createBlockData", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "block", "Lorg/bukkit/block/Block;", "disableChunkUnload", "", "world", "Lorg/bukkit/World;", "centerChunk", "Ldev/imanity/antixray/api/chunk/DirectChunk;", "allChunks", "", "(Lorg/bukkit/World;Ldev/imanity/antixray/api/chunk/DirectChunk;[Ldev/imanity/antixray/api/chunk/DirectChunk;)V", "enableChunkUnload", "init", "legacy-base" })
@SourceDebugExtension({ "SMAP\nAbstractLegacyNativeProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractLegacyNativeProvider.kt\ndev/imanity/antixray/nms/legacy/AbstractLegacyNativeProvider\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,49:1\n13309#2,2:50\n13309#2,2:52\n*S KotlinDebug\n*F\n+ 1 AbstractLegacyNativeProvider.kt\ndev/imanity/antixray/nms/legacy/AbstractLegacyNativeProvider\n*L\n31#1:50,2\n37#1:52,2\n*E\n" })
public abstract class AbstractLegacyNativeProvider implements NativeProvider
{
    @NotNull
    private final LongSet cancelUnloadChunks;
    
    public AbstractLegacyNativeProvider() {
        this.cancelUnloadChunks = (LongSet)new LongOpenHashSet(1024);
    }
    
    @Override
    public void init() {
        Events.subscribe((Class)ChunkUnloadEvent.class).listen(new Consumer[] { AbstractLegacyNativeProvider::init$lambda$0 }).build((Plugin)BukkitPlugin.INSTANCE);
    }
    
    @Override
    public void disableChunkUnload(@NotNull final World world, @NotNull final DirectChunk directChunk, @NotNull final DirectChunk[] array) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        Intrinsics.checkNotNullParameter((Object)directChunk, "");
        Intrinsics.checkNotNullParameter((Object)array, "");
        for (int i = 0; i < array.length; ++i) {
            final DirectChunk directChunk2 = array[i];
            this.cancelUnloadChunks.add(LongHash.INSTANCE.toLong(directChunk2.getX(), directChunk2.getZ()));
        }
    }
    
    @Override
    public void enableChunkUnload(@NotNull final World world, @NotNull final DirectChunk directChunk, @NotNull final DirectChunk[] array) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        Intrinsics.checkNotNullParameter((Object)directChunk, "");
        Intrinsics.checkNotNullParameter((Object)array, "");
        for (int i = 0; i < array.length; ++i) {
            final DirectChunk directChunk2 = array[i];
            this.cancelUnloadChunks.remove(LongHash.INSTANCE.toLong(directChunk2.getX(), directChunk2.getZ()));
        }
    }
    
    @NotNull
    @Override
    public WrappedBlockState createBlockData(@NotNull final Block block) {
        Intrinsics.checkNotNullParameter((Object)block, "");
        final WrappedBlockState byGlobalId = WrappedBlockState.getByGlobalId(block.getType().getId() | block.getData() << 12);
        Intrinsics.checkNotNullExpressionValue((Object)byGlobalId, "");
        return byGlobalId;
    }
    
    @NotNull
    @Override
    public Set<Integer> getBlockDataIds(@NotNull final Object o) {
        return DefaultImpls.getBlockDataIds(this, o);
    }
    
    @Override
    public boolean isZoomSupported() {
        return DefaultImpls.isZoomSupported(this);
    }
    
    @Override
    public void resetEmptyTicks() {
        DefaultImpls.resetEmptyTicks(this);
    }
    
    @Override
    public void updateEntity(@NotNull final Entity entity, @NotNull final Collection<? extends Player> collection) {
        DefaultImpls.updateEntity(this, entity, collection);
    }
    
    @NotNull
    @Override
    public Vector zoom(@NotNull final Location location, final double n) {
        return DefaultImpls.zoom(this, location, n);
    }
    
    private static final void init$lambda$0(final AbstractLegacyNativeProvider abstractLegacyNativeProvider, final ChunkUnloadEvent chunkUnloadEvent) {
        Intrinsics.checkNotNullParameter((Object)abstractLegacyNativeProvider, "");
        final Chunk chunk = chunkUnloadEvent.getChunk();
        if (abstractLegacyNativeProvider.cancelUnloadChunks.contains(LongHash.INSTANCE.toLong(chunk.getX(), chunk.getZ()))) {
            chunkUnloadEvent.setCancelled(true);
        }
    }
}
