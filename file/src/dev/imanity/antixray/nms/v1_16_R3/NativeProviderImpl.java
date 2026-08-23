// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.v1_16_R3;

import dev.imanity.antixray.api.util.Position;
import org.bukkit.craftbukkit.v1_16_R3.block.data.CraftBlockData;
import net.minecraft.server.v1_16_R3.IBlockData;
import org.bukkit.block.data.BlockData;
import com.google.common.collect.ImmutableList;
import net.minecraft.server.v1_16_R3.Block;
import org.bukkit.craftbukkit.v1_16_R3.util.CraftMagicNumbers;
import java.util.Set;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import java.util.Collection;
import org.bukkit.util.BoundingBox;
import dev.imanity.antixray.api.math.AABB;
import net.minecraft.server.v1_16_R3.PlayerChunkMap;
import dev.imanity.antixray.bootstrap.bukkit.BukkitPlugin;
import net.minecraft.server.v1_16_R3.ChunkCoordIntPair;
import net.minecraft.server.v1_16_R3.TicketType;
import net.minecraft.server.v1_16_R3.ChunkMapDistance;
import dev.imanity.antixray.api.chunk.DirectChunk;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import net.minecraft.server.v1_16_R3.BlockPosition;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.PacketPlayOutMapChunk;
import net.minecraft.server.v1_16_R3.Packet;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import net.minecraft.server.v1_16_R3.WorldServer;
import java.util.function.Function;
import kotlin.jvm.functions.Function1;
import java.util.concurrent.CompletableFuture;
import net.minecraft.server.v1_16_R3.Chunk;
import dev.imanity.antixray.api.nms.ChunkHandle;
import org.spigotmc.SpigotWorldConfig;
import kotlin.comparisons.ComparisonsKt;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.World;
import org.spigotmc.TrackingRange;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Entity;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.server.v1_16_R3.ChunkStatus;
import net.minecraft.server.v1_16_R3.ChunkProviderServer;
import java.lang.reflect.Method;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import dev.imanity.antixray.nms.modern.AbstractModernNativeProvider;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\t\u0010\nJ-\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\fH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J-\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\fH\u0016¢\u0006\u0004\b\u0011\u0010\u0010J\u001f\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0005\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0005\u001a\u00020\u0019H\u0017¢\u0006\u0004\b\u001b\u0010\u001cJ'\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u0016H\u0017¢\u0006\u0004\b\u001d\u0010\u001eJ/\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u001f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u0016H\u0017¢\u0006\u0004\b \u0010!J\u0017\u0010\"\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\"\u0010#J\u0017\u0010$\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0019H\u0017¢\u0006\u0004\b$\u0010%J\u0017\u0010&\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b&\u0010'J\u001d\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0005\u001a\u00020(H\u0017¢\u0006\u0004\b)\u0010*J'\u0010,\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020+2\u0006\u0010\u0007\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u0016H\u0016¢\u0006\u0004\b,\u0010-J7\u00100\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020+2\u0006\u0010\u0007\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u00162\u0006\u0010.\u001a\u00020\u00162\u0006\u0010/\u001a\u00020(H\u0016¢\u0006\u0004\b0\u00101J/\u00102\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u00162\u0006\u0010.\u001a\u00020\u0016H\u0016¢\u0006\u0004\b2\u00103J%\u00105\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00192\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020+04H\u0016¢\u0006\u0004\b5\u00106J\u000f\u00108\u001a\u000207H\u0016¢\u0006\u0004\b8\u00109R\u0016\u0010;\u001a\u00020:8\u0002@\u0002X\u0083.¢\u0006\u0006\n\u0004\b;\u0010<" }, d2 = { "Ldev/imanity/antixray/nms/v1_16_R3/NativeProviderImpl;", "Ldev/imanity/antixray/nms/modern/AbstractModernNativeProvider;", "<init>", "()V", "Lorg/bukkit/World;", "p0", "", "p1", "Ldev/imanity/antixray/api/nms/ChunkHandle;", "createChunkHandle", "(Lorg/bukkit/World;Ljava/lang/Object;)Ldev/imanity/antixray/api/nms/ChunkHandle;", "Ldev/imanity/antixray/api/chunk/DirectChunk;", "", "p2", "", "disableChunkUnload", "(Lorg/bukkit/World;Ldev/imanity/antixray/api/chunk/DirectChunk;[Ldev/imanity/antixray/api/chunk/DirectChunk;)V", "enableChunkUnload", "Ldev/imanity/antixray/api/util/Position;", "fetchLocation", "(Ljava/lang/Object;Ldev/imanity/antixray/api/util/Position;)V", "", "", "getBlockDataIds", "(Ljava/lang/Object;)Ljava/util/Set;", "Lorg/bukkit/entity/Entity;", "Ldev/imanity/antixray/api/math/AABB;", "getBoundingBox", "(Lorg/bukkit/entity/Entity;)Ldev/imanity/antixray/api/math/AABB;", "getChunkAt", "(Lorg/bukkit/World;II)Ldev/imanity/antixray/api/nms/ChunkHandle;", "Ljava/util/concurrent/CompletableFuture;", "getChunkAtAsync", "(Lorg/bukkit/World;II)Ljava/util/concurrent/CompletableFuture;", "getEntityTrackingRange", "(Lorg/bukkit/entity/Entity;)I", "getLocatableObject", "(Lorg/bukkit/entity/Entity;)Ljava/lang/Object;", "getMaxEntityTrackingRange", "(Lorg/bukkit/World;)I", "Lorg/bukkit/Material;", "getPossibleDefinition", "(Lorg/bukkit/Material;)Ljava/util/Set;", "Lorg/bukkit/entity/Player;", "resendChunk", "(Lorg/bukkit/entity/Player;II)V", "p3", "p4", "sendBlock", "(Lorg/bukkit/entity/Player;IIILorg/bukkit/Material;)V", "updateBlock", "(Lorg/bukkit/World;III)V", "", "updateEntity", "(Lorg/bukkit/entity/Entity;Ljava/util/Collection;)V", "", "validate", "()Z", "Ljava/lang/reflect/Method;", "getChunkFutureMainThread", "Ljava/lang/reflect/Method;" })
@SourceDebugExtension({ "SMAP\nNativeProviderImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NativeProviderImpl.kt\ndev/imanity/antixray/nms/v1_16_R3/NativeProviderImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,184:1\n1549#2:185\n1620#2,3:186\n1549#2:189\n1620#2,3:190\n1603#2,9:193\n1855#2:202\n1856#2:204\n1612#2:205\n1#3:203\n*S KotlinDebug\n*F\n+ 1 NativeProviderImpl.kt\ndev/imanity/antixray/nms/v1_16_R3/NativeProviderImpl\n*L\n142#1:185\n142#1:186,3\n152#1:189\n152#1:190,3\n158#1:193,9\n158#1:202\n158#1:204\n158#1:205\n158#1:203\n*E\n" })
public final class NativeProviderImpl extends AbstractModernNativeProvider
{
    private Method getChunkFutureMainThread;
    
    @Override
    public boolean validate() {
        boolean b;
        try {
            Class.forName("net.minecraft.server.v1_16_R3.Chunk");
            final Method declaredMethod = ChunkProviderServer.class.getDeclaredMethod("getChunkFutureMainThread", Integer.TYPE, Integer.TYPE, ChunkStatus.class, Boolean.TYPE);
            Intrinsics.checkNotNullExpressionValue((Object)declaredMethod, "");
            this.getChunkFutureMainThread = declaredMethod;
            Method getChunkFutureMainThread;
            if ((getChunkFutureMainThread = this.getChunkFutureMainThread) == null) {
                Intrinsics.throwUninitializedPropertyAccessException("");
                getChunkFutureMainThread = null;
            }
            getChunkFutureMainThread.setAccessible(true);
            b = true;
        }
        catch (final Throwable t) {
            b = false;
        }
        return b;
    }
    
    @Override
    public int getEntityTrackingRange(@NotNull final Entity entity) {
        Intrinsics.checkNotNullParameter((Object)entity, "");
        return TrackingRange.getEntityTrackingRange(((CraftEntity)entity).getHandle(), -1);
    }
    
    @Override
    public int getMaxEntityTrackingRange(@NotNull final World world) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        final SpigotWorldConfig spigotConfig = ((CraftWorld)world).getHandle().spigotConfig;
        return ComparisonsKt.maxOf(spigotConfig.miscTrackingRange, new int[] { spigotConfig.monsterTrackingRange, spigotConfig.animalTrackingRange, spigotConfig.playerTrackingRange, spigotConfig.otherTrackingRange });
    }
    
    @NotNull
    @Override
    public ChunkHandle createChunkHandle(@NotNull final World world, @NotNull final Object o) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        Intrinsics.checkNotNullParameter(o, "");
        return new ChunkHandleImpl((Chunk)o);
    }
    
    @NotNull
    @Override
    public ChunkHandle getChunkAt(@NotNull final World world, final int n, final int n2) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        final Chunk chunk = ((CraftWorld)world).getHandle().getChunkAt(n, n2);
        Intrinsics.checkNotNull((Object)chunk);
        return new ChunkHandleImpl(chunk);
    }
    
    @NotNull
    @Override
    public CompletableFuture<ChunkHandle> getChunkAtAsync(@NotNull final World world, final int i, final int j) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        final WorldServer handle = ((CraftWorld)world).getHandle();
        final Chunk chunkIfLoaded = handle.getChunkIfLoaded(i, j);
        if (chunkIfLoaded != null) {
            final CompletableFuture<ChunkHandleImpl> completedFuture = CompletableFuture.completedFuture(new ChunkHandleImpl(chunkIfLoaded));
            Intrinsics.checkNotNullExpressionValue((Object)completedFuture, "");
            return (CompletableFuture<ChunkHandle>)completedFuture;
        }
        Method getChunkFutureMainThread;
        if ((getChunkFutureMainThread = this.getChunkFutureMainThread) == null) {
            Intrinsics.throwUninitializedPropertyAccessException("");
            getChunkFutureMainThread = null;
        }
        final Object invoke = getChunkFutureMainThread.invoke(handle.getChunkProvider(), i, j, ChunkStatus.FULL, true);
        Intrinsics.checkNotNull(invoke);
        final CompletableFuture thenApply = ((CompletableFuture)invoke).thenApply((Function)NativeProviderImpl::getChunkAtAsync$lambda$0);
        Intrinsics.checkNotNullExpressionValue((Object)thenApply, "");
        return thenApply;
    }
    
    @Override
    public void resendChunk(@NotNull final Player player, final int n, final int n2) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        final EntityPlayer handle = ((CraftPlayer)player).getHandle();
        handle.playerConnection.sendPacket((Packet)new PacketPlayOutMapChunk(handle.world.getChunkAt(n, n2), 65535));
    }
    
    @Override
    public void updateBlock(@NotNull final World world, final int n, final int n2, final int n3) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        ((CraftWorld)world).getHandle().getChunkProvider().flagDirty(new BlockPosition(n, n2, n3));
    }
    
    @Override
    public void sendBlock(@NotNull final Player player, final int n, final int n2, final int n3, @NotNull final Material material) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)material, "");
        player.sendBlockChange(new Location(player.getWorld(), (double)n, (double)n2, (double)n3), Bukkit.createBlockData(material));
    }
    
    @Override
    public void disableChunkUnload(@NotNull final World world, @NotNull final DirectChunk directChunk, @NotNull final DirectChunk[] array) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        Intrinsics.checkNotNullParameter((Object)directChunk, "");
        Intrinsics.checkNotNullParameter((Object)array, "");
        final PlayerChunkMap.a chunkDistanceManager = ((CraftWorld)world).getHandle().getChunkProvider().playerChunkMap.chunkDistanceManager;
        Intrinsics.checkNotNullExpressionValue((Object)chunkDistanceManager, "");
        ((ChunkMapDistance)chunkDistanceManager).addTicketAtLevel(TicketType.PLUGIN_TICKET, new ChunkCoordIntPair(directChunk.getX(), directChunk.getZ()), 32, (Object)BukkitPlugin.INSTANCE);
    }
    
    @Override
    public void enableChunkUnload(@NotNull final World world, @NotNull final DirectChunk directChunk, @NotNull final DirectChunk[] array) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        Intrinsics.checkNotNullParameter((Object)directChunk, "");
        Intrinsics.checkNotNullParameter((Object)array, "");
        final PlayerChunkMap.a chunkDistanceManager = ((CraftWorld)world).getHandle().getChunkProvider().playerChunkMap.chunkDistanceManager;
        Intrinsics.checkNotNullExpressionValue((Object)chunkDistanceManager, "");
        ((ChunkMapDistance)chunkDistanceManager).removeTicketAtLevel(TicketType.PLUGIN_TICKET, new ChunkCoordIntPair(directChunk.getX(), directChunk.getZ()), 32, (Object)BukkitPlugin.INSTANCE);
    }
    
    @NotNull
    @Override
    public AABB getBoundingBox(@NotNull final Entity entity) {
        Intrinsics.checkNotNullParameter((Object)entity, "");
        final BoundingBox boundingBox = entity.getBoundingBox();
        Intrinsics.checkNotNullExpressionValue((Object)boundingBox, "");
        final BoundingBox boundingBox2 = boundingBox;
        return new AABB(boundingBox2.getMinX(), boundingBox2.getMinY(), boundingBox2.getMinZ(), boundingBox2.getMaxX(), boundingBox2.getMaxY(), boundingBox2.getMaxZ());
    }
    
    @Override
    public void updateEntity(@NotNull final Entity entity, @NotNull final Collection<? extends Player> collection) {
        Intrinsics.checkNotNullParameter((Object)entity, "");
        Intrinsics.checkNotNullParameter((Object)collection, "");
        final net.minecraft.server.v1_16_R3.Entity handle = ((CraftEntity)entity).getHandle();
        final net.minecraft.server.v1_16_R3.World world = handle.world;
        Intrinsics.checkNotNull((Object)world);
        final PlayerChunkMap.EntityTracker entityTracker = (PlayerChunkMap.EntityTracker)((WorldServer)world).getChunkProvider().playerChunkMap.trackedEntities.get(handle.getId());
        if (entityTracker != null) {
            final PlayerChunkMap.EntityTracker entityTracker2 = entityTracker;
            final Iterable iterable;
            final Collection collection2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable = collection, 10));
            for (final Object next : iterable) {
                final Collection collection3 = collection2;
                final Player player = (Player)next;
                final Collection collection4 = collection3;
                Intrinsics.checkNotNull((Object)player);
                collection4.add(((CraftPlayer)player).getHandle());
            }
            final List list = (List)collection2;
            entityTracker2.trackedPlayers.removeAll(CollectionsKt.toSet((Iterable)list));
            entityTracker2.track(list);
        }
    }
    
    @NotNull
    @Override
    public Set<Integer> getPossibleDefinition(@NotNull final Material material) {
        Intrinsics.checkNotNullParameter((Object)material, "");
        final ImmutableList a = CraftMagicNumbers.getBlock(material).getStates().a();
        Intrinsics.checkNotNullExpressionValue((Object)a, "");
        final Iterable iterable;
        final Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable = (Iterable)a, 10));
        final Iterator iterator = iterable.iterator();
        while (iterator.hasNext()) {
            collection.add(Block.REGISTRY_ID.getId((Object)iterator.next()));
        }
        return CollectionsKt.toSet((Iterable)collection);
    }
    
    @NotNull
    @Override
    public Set<Integer> getBlockDataIds(@NotNull final Object o) {
        Intrinsics.checkNotNullParameter(o, "");
        final BlockData blockData = (BlockData)o;
        final ImmutableList a = CraftMagicNumbers.getBlock(blockData.getMaterial()).getStates().a();
        Intrinsics.checkNotNullExpressionValue((Object)a, "");
        final Iterable iterable = (Iterable)a;
        final Collection collection = new ArrayList();
        for (final IBlockData blockData2 : iterable) {
            final Integer n = CraftBlockData.fromData(blockData2).matches(blockData) ? Integer.valueOf(Block.REGISTRY_ID.getId((Object)blockData2)) : null;
            if (n != null) {
                collection.add(n);
            }
        }
        return CollectionsKt.toSet((Iterable)collection);
    }
    
    @NotNull
    @Override
    public Object getLocatableObject(@NotNull final Entity entity) {
        Intrinsics.checkNotNullParameter((Object)entity, "");
        final net.minecraft.server.v1_16_R3.Entity handle = ((CraftEntity)entity).getHandle();
        Intrinsics.checkNotNullExpressionValue((Object)handle, "");
        return handle;
    }
    
    @Override
    public void fetchLocation(@NotNull final Object o, @NotNull final Position position) {
        Intrinsics.checkNotNullParameter(o, "");
        Intrinsics.checkNotNullParameter((Object)position, "");
        final net.minecraft.server.v1_16_R3.Entity entity = (net.minecraft.server.v1_16_R3.Entity)o;
        final double locX = entity.locX();
        final double locY = entity.locY();
        final double locZ = entity.locZ();
        position.getVec3d().x = locX;
        position.getVec3d().y = locY;
        position.getVec3d().z = locZ;
        position.setYaw(entity.yaw);
        position.setPitch(entity.pitch);
    }
    
    private static final ChunkHandle getChunkAtAsync$lambda$0(final Function1 function1, final Object o) {
        Intrinsics.checkNotNullParameter((Object)function1, "");
        return (ChunkHandle)function1.invoke(o);
    }
}
