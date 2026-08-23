// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms;

import java.util.Set;
import org.bukkit.Material;
import dev.imanity.antixray.api.chunk.DirectChunk;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import org.bukkit.block.Block;
import dev.imanity.antixray.api.math.AABB;
import dev.imanity.antixray.api.util.Position;
import java.util.Collection;
import org.bukkit.entity.Player;
import java.util.concurrent.CompletableFuture;
import dev.imanity.antixray.api.nms.ChunkHandle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0001J)\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0017¢\u0006\u0002\u0010\u0018J)\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0017¢\u0006\u0002\u0010\u0018J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u001bJ\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020 J\u001e\u0010!\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#J&\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0&2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#J\u000e\u0010'\u001a\u00020#2\u0006\u0010\u001c\u001a\u00020 J\u000e\u0010(\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020 J\u000e\u0010)\u001a\u00020#2\u0006\u0010\u000f\u001a\u00020\u0010J\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020#0+2\u0006\u0010,\u001a\u00020-J\u0006\u0010.\u001a\u00020/J\u001e\u00100\u001a\u00020\u00132\u0006\u00101\u001a\u0002022\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#J\u0006\u00103\u001a\u00020\u0013J&\u00104\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020#2\u0006\u00105\u001a\u00020#2\u0006\u0010$\u001a\u00020#J\u001c\u00106\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020 2\f\u00107\u001a\b\u0012\u0004\u0012\u00020208J\u0016\u00109\u001a\u00020:2\u0006\u0010\u001d\u001a\u00020;2\u0006\u0010<\u001a\u00020=R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006>" }, d2 = { "Ldev/imanity/antixray/nms/Native;", "", "()V", "nativeProvider", "Ldev/imanity/antixray/nms/NativeProvider;", "getNativeProvider", "()Ldev/imanity/antixray/nms/NativeProvider;", "setNativeProvider", "(Ldev/imanity/antixray/nms/NativeProvider;)V", "createBlockData", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "block", "Lorg/bukkit/block/Block;", "createChunkHandle", "Ldev/imanity/antixray/api/nms/ChunkHandle;", "world", "Lorg/bukkit/World;", "raw", "disableChunkUnload", "", "centerChunk", "Ldev/imanity/antixray/api/chunk/DirectChunk;", "allChunks", "", "(Lorg/bukkit/World;Ldev/imanity/antixray/api/chunk/DirectChunk;[Ldev/imanity/antixray/api/chunk/DirectChunk;)V", "enableChunkUnload", "fetchLocation", "Ldev/imanity/antixray/api/util/Position;", "entity", "location", "getBoundingBox", "Ldev/imanity/antixray/api/math/AABB;", "Lorg/bukkit/entity/Entity;", "getChunkAt", "x", "", "z", "getChunkAtAsync", "Ljava/util/concurrent/CompletableFuture;", "getEntityTrackingRange", "getLocatableObject", "getMaxEntityTrackingRange", "getPossibleDefinition", "", "material", "Lorg/bukkit/Material;", "isZoomSupported", "", "resendChunk", "player", "Lorg/bukkit/entity/Player;", "resetEmptyTicks", "updateBlock", "y", "updateEntity", "observer", "", "zoom", "Lorg/bukkit/util/Vector;", "Lorg/bukkit/Location;", "maxZoom", "", "base" })
public final class Native
{
    @NotNull
    public static final Native INSTANCE;
    @Nullable
    private static NativeProvider nativeProvider;
    
    private Native() {
    }
    
    @Nullable
    public final NativeProvider getNativeProvider() {
        return Native.nativeProvider;
    }
    
    public final void setNativeProvider(@Nullable final NativeProvider nativeProvider) {
        Native.nativeProvider = nativeProvider;
    }
    
    public final boolean isZoomSupported() {
        final NativeProvider nativeProvider = Native.nativeProvider;
        Intrinsics.checkNotNull((Object)nativeProvider);
        return nativeProvider.isZoomSupported();
    }
    
    @NotNull
    public final Vector zoom(@NotNull final Location location, final double n) {
        Intrinsics.checkNotNullParameter((Object)location, "");
        final NativeProvider nativeProvider = Native.nativeProvider;
        Intrinsics.checkNotNull((Object)nativeProvider);
        return nativeProvider.zoom(location, n);
    }
    
    public final int getEntityTrackingRange(@NotNull final Entity entity) {
        Intrinsics.checkNotNullParameter((Object)entity, "");
        final NativeProvider nativeProvider = Native.nativeProvider;
        Intrinsics.checkNotNull((Object)nativeProvider);
        return nativeProvider.getEntityTrackingRange(entity);
    }
    
    public final int getMaxEntityTrackingRange(@NotNull final World world) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        final NativeProvider nativeProvider = Native.nativeProvider;
        Intrinsics.checkNotNull((Object)nativeProvider);
        return nativeProvider.getMaxEntityTrackingRange(world);
    }
    
    @NotNull
    public final ChunkHandle createChunkHandle(@NotNull final World world, @NotNull final Object o) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        Intrinsics.checkNotNullParameter(o, "");
        final NativeProvider nativeProvider = Native.nativeProvider;
        Intrinsics.checkNotNull((Object)nativeProvider);
        return nativeProvider.createChunkHandle(world, o);
    }
    
    @NotNull
    public final ChunkHandle getChunkAt(@NotNull final World world, final int n, final int n2) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        final NativeProvider nativeProvider = Native.nativeProvider;
        Intrinsics.checkNotNull((Object)nativeProvider);
        return nativeProvider.getChunkAt(world, n, n2);
    }
    
    @NotNull
    public final CompletableFuture<ChunkHandle> getChunkAtAsync(@NotNull final World world, final int n, final int n2) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        final NativeProvider nativeProvider = Native.nativeProvider;
        Intrinsics.checkNotNull((Object)nativeProvider);
        return nativeProvider.getChunkAtAsync(world, n, n2);
    }
    
    public final void resendChunk(@NotNull final Player player, final int n, final int n2) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        final NativeProvider nativeProvider = Native.nativeProvider;
        Intrinsics.checkNotNull((Object)nativeProvider);
        nativeProvider.resendChunk(player, n, n2);
    }
    
    public final void updateBlock(@NotNull final World world, final int n, final int n2, final int n3) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        final NativeProvider nativeProvider = Native.nativeProvider;
        Intrinsics.checkNotNull((Object)nativeProvider);
        nativeProvider.updateBlock(world, n, n2, n3);
    }
    
    public final void updateEntity(@NotNull final Entity entity, @NotNull final Collection<? extends Player> collection) {
        Intrinsics.checkNotNullParameter((Object)entity, "");
        Intrinsics.checkNotNullParameter((Object)collection, "");
        final NativeProvider nativeProvider = Native.nativeProvider;
        Intrinsics.checkNotNull((Object)nativeProvider);
        nativeProvider.updateEntity(entity, collection);
    }
    
    @NotNull
    public final Object getLocatableObject(@NotNull final Entity entity) {
        Intrinsics.checkNotNullParameter((Object)entity, "");
        final NativeProvider nativeProvider = Native.nativeProvider;
        Intrinsics.checkNotNull((Object)nativeProvider);
        return nativeProvider.getLocatableObject(entity);
    }
    
    @NotNull
    public final Position fetchLocation(@NotNull final Object o, @NotNull final Position position) {
        Intrinsics.checkNotNullParameter(o, "");
        Intrinsics.checkNotNullParameter((Object)position, "");
        final NativeProvider nativeProvider = Native.nativeProvider;
        Intrinsics.checkNotNull((Object)nativeProvider);
        nativeProvider.fetchLocation(o, position);
        return position;
    }
    
    @NotNull
    public final AABB getBoundingBox(@NotNull final Entity entity) {
        Intrinsics.checkNotNullParameter((Object)entity, "");
        final NativeProvider nativeProvider = Native.nativeProvider;
        Intrinsics.checkNotNull((Object)nativeProvider);
        return nativeProvider.getBoundingBox(entity);
    }
    
    @NotNull
    public final WrappedBlockState createBlockData(@NotNull final Block block) {
        Intrinsics.checkNotNullParameter((Object)block, "");
        final NativeProvider nativeProvider = Native.nativeProvider;
        Intrinsics.checkNotNull((Object)nativeProvider);
        return nativeProvider.createBlockData(block);
    }
    
    public final void disableChunkUnload(@NotNull final World world, @NotNull final DirectChunk directChunk, @NotNull final DirectChunk[] array) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        Intrinsics.checkNotNullParameter((Object)directChunk, "");
        Intrinsics.checkNotNullParameter((Object)array, "");
        final NativeProvider nativeProvider = Native.nativeProvider;
        Intrinsics.checkNotNull((Object)nativeProvider);
        nativeProvider.disableChunkUnload(world, directChunk, array);
    }
    
    public final void enableChunkUnload(@NotNull final World world, @NotNull final DirectChunk directChunk, @NotNull final DirectChunk[] array) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        Intrinsics.checkNotNullParameter((Object)directChunk, "");
        Intrinsics.checkNotNullParameter((Object)array, "");
        final NativeProvider nativeProvider = Native.nativeProvider;
        Intrinsics.checkNotNull((Object)nativeProvider);
        nativeProvider.enableChunkUnload(world, directChunk, array);
    }
    
    @NotNull
    public final Set<Integer> getPossibleDefinition(@NotNull final Material material) {
        Intrinsics.checkNotNullParameter((Object)material, "");
        final NativeProvider nativeProvider = Native.nativeProvider;
        Intrinsics.checkNotNull((Object)nativeProvider);
        return nativeProvider.getPossibleDefinition(material);
    }
    
    public final void resetEmptyTicks() {
        final NativeProvider nativeProvider = Native.nativeProvider;
        Intrinsics.checkNotNull((Object)nativeProvider);
        nativeProvider.resetEmptyTicks();
    }
    
    static {
        INSTANCE = new Native();
    }
}
