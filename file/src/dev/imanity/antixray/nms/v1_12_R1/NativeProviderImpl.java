// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.v1_12_R1;

import dev.imanity.antixray.api.util.Position;
import com.google.common.collect.ImmutableList;
import net.minecraft.server.v1_12_R1.Block;
import net.minecraft.server.v1_12_R1.IBlockData;
import org.bukkit.craftbukkit.v1_12_R1.util.CraftMagicNumbers;
import java.util.Set;
import java.util.Iterator;
import net.minecraft.server.v1_12_R1.EntityTrackerEntry;
import java.util.List;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import net.minecraft.server.v1_12_R1.WorldServer;
import java.util.Collection;
import net.minecraft.server.v1_12_R1.AxisAlignedBB;
import dev.imanity.antixray.api.math.AABB;
import org.bukkit.Location;
import org.bukkit.Material;
import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.PacketPlayOutMapChunk;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import java.util.concurrent.CompletableFuture;
import net.minecraft.server.v1_12_R1.Chunk;
import dev.imanity.antixray.api.nms.ChunkHandle;
import org.spigotmc.SpigotWorldConfig;
import kotlin.comparisons.ComparisonsKt;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.World;
import org.spigotmc.TrackingRange;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Entity;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import dev.imanity.antixray.nms.legacy.AbstractLegacyNativeProvider;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u000fH\u0017¢\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0017¢\u0006\u0004\b\u0015\u0010\u0016J/\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00172\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0017¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u000fH\u0017¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u001d\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00130!2\u0006\u0010\u0005\u001a\u00020 H\u0017¢\u0006\u0004\b\"\u0010#J'\u0010%\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020$2\u0006\u0010\u0007\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b%\u0010&J7\u0010)\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020$2\u0006\u0010\u0007\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010'\u001a\u00020\u00132\u0006\u0010(\u001a\u00020 H\u0016¢\u0006\u0004\b)\u0010*J/\u0010+\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010'\u001a\u00020\u0013H\u0016¢\u0006\u0004\b+\u0010,J%\u0010.\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u000f2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020$0-H\u0016¢\u0006\u0004\b.\u0010/J\u000f\u00101\u001a\u000200H\u0016¢\u0006\u0004\b1\u00102" }, d2 = { "Ldev/imanity/antixray/nms/v1_12_R1/NativeProviderImpl;", "Ldev/imanity/antixray/nms/legacy/AbstractLegacyNativeProvider;", "<init>", "()V", "Lorg/bukkit/World;", "p0", "", "p1", "Ldev/imanity/antixray/api/nms/ChunkHandle;", "createChunkHandle", "(Lorg/bukkit/World;Ljava/lang/Object;)Ldev/imanity/antixray/api/nms/ChunkHandle;", "Ldev/imanity/antixray/api/util/Position;", "", "fetchLocation", "(Ljava/lang/Object;Ldev/imanity/antixray/api/util/Position;)V", "Lorg/bukkit/entity/Entity;", "Ldev/imanity/antixray/api/math/AABB;", "getBoundingBox", "(Lorg/bukkit/entity/Entity;)Ldev/imanity/antixray/api/math/AABB;", "", "p2", "getChunkAt", "(Lorg/bukkit/World;II)Ldev/imanity/antixray/api/nms/ChunkHandle;", "Ljava/util/concurrent/CompletableFuture;", "getChunkAtAsync", "(Lorg/bukkit/World;II)Ljava/util/concurrent/CompletableFuture;", "getEntityTrackingRange", "(Lorg/bukkit/entity/Entity;)I", "getLocatableObject", "(Lorg/bukkit/entity/Entity;)Ljava/lang/Object;", "getMaxEntityTrackingRange", "(Lorg/bukkit/World;)I", "Lorg/bukkit/Material;", "", "getPossibleDefinition", "(Lorg/bukkit/Material;)Ljava/util/Set;", "Lorg/bukkit/entity/Player;", "resendChunk", "(Lorg/bukkit/entity/Player;II)V", "p3", "p4", "sendBlock", "(Lorg/bukkit/entity/Player;IIILorg/bukkit/Material;)V", "updateBlock", "(Lorg/bukkit/World;III)V", "", "updateEntity", "(Lorg/bukkit/entity/Entity;Ljava/util/Collection;)V", "", "validate", "()Z" })
@SourceDebugExtension({ "SMAP\nNativeProviderImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NativeProviderImpl.kt\ndev/imanity/antixray/nms/v1_12_R1/NativeProviderImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,129:1\n1549#2:130\n1620#2,3:131\n1549#2:134\n1620#2,3:135\n*S KotlinDebug\n*F\n+ 1 NativeProviderImpl.kt\ndev/imanity/antixray/nms/v1_12_R1/NativeProviderImpl\n*L\n92#1:130\n92#1:131,3\n107#1:134\n107#1:135,3\n*E\n" })
public final class NativeProviderImpl extends AbstractLegacyNativeProvider
{
    @Override
    public boolean validate() {
        boolean b;
        try {
            Class.forName("net.minecraft.server.v1_12_R1.Chunk");
            b = true;
        }
        catch (final ClassNotFoundException ex) {
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
    public CompletableFuture<ChunkHandle> getChunkAtAsync(@NotNull final World world, final int n, final int n2) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        final CraftWorld craftWorld = (CraftWorld)world;
        final CompletableFuture completableFuture = new CompletableFuture();
        craftWorld.getHandle().getChunkProviderServer().getChunkAt(n, n2, NativeProviderImpl::getChunkAtAsync$lambda$0);
        return completableFuture;
    }
    
    @Override
    public void resendChunk(@NotNull final Player player, final int n, final int n2) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        final EntityPlayer handle = ((CraftPlayer)player).getHandle();
        final Chunk chunkIfLoaded = handle.world.getChunkIfLoaded(n, n2);
        if (chunkIfLoaded == null) {
            return;
        }
        handle.playerConnection.sendPacket((Packet)new PacketPlayOutMapChunk(chunkIfLoaded, 65535));
    }
    
    @Override
    public void updateBlock(@NotNull final World world, final int n, final int n2, final int n3) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        ((CraftWorld)world).getHandle().getPlayerChunkMap().flagDirty(new BlockPosition(n, n2, n3));
    }
    
    @Override
    public void sendBlock(@NotNull final Player player, final int n, final int n2, final int n3, @NotNull final Material material) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)material, "");
        player.sendBlockChange(new Location(player.getWorld(), (double)n, (double)n2, (double)n3), material, (byte)0);
    }
    
    @NotNull
    @Override
    public AABB getBoundingBox(@NotNull final Entity entity) {
        Intrinsics.checkNotNullParameter((Object)entity, "");
        final AxisAlignedBB boundingBox = ((CraftEntity)entity).getHandle().getBoundingBox();
        return new AABB(boundingBox.a, boundingBox.b, boundingBox.c, boundingBox.d, boundingBox.e, boundingBox.f);
    }
    
    @Override
    public void updateEntity(@NotNull final Entity entity, @NotNull final Collection<? extends Player> collection) {
        Intrinsics.checkNotNullParameter((Object)entity, "");
        Intrinsics.checkNotNullParameter((Object)collection, "");
        final net.minecraft.server.v1_12_R1.World world = ((CraftEntity)entity).getHandle().world;
        Intrinsics.checkNotNull((Object)world);
        final WorldServer worldServer = (WorldServer)world;
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
        final EntityTrackerEntry entityTrackerEntry = (EntityTrackerEntry)worldServer.tracker.trackedEntities.get(((CraftEntity)entity).getEntityId());
        if (entityTrackerEntry != null) {
            final EntityTrackerEntry entityTrackerEntry2 = entityTrackerEntry;
            entityTrackerEntry2.trackedPlayers.removeAll(CollectionsKt.toHashSet((Iterable)list));
            entityTrackerEntry2.scanPlayers(list);
        }
    }
    
    @NotNull
    @Override
    public Set<Integer> getPossibleDefinition(@NotNull final Material material) {
        Intrinsics.checkNotNullParameter((Object)material, "");
        final ImmutableList a = CraftMagicNumbers.getBlock(material).s().a();
        Intrinsics.checkNotNull((Object)a);
        final Iterable iterable;
        final Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable = (Iterable)a, 10));
        for (final Object next : iterable) {
            final Collection collection2 = collection;
            final IBlockData blockData = (IBlockData)next;
            collection2.add(CombinedID.INSTANCE.getId(Block.getId(blockData.getBlock()), blockData.getBlock().toLegacyData(blockData)));
        }
        return CollectionsKt.toSet((Iterable)collection);
    }
    
    @NotNull
    @Override
    public Object getLocatableObject(@NotNull final Entity entity) {
        Intrinsics.checkNotNullParameter((Object)entity, "");
        final net.minecraft.server.v1_12_R1.Entity handle = ((CraftEntity)entity).getHandle();
        Intrinsics.checkNotNullExpressionValue((Object)handle, "");
        return handle;
    }
    
    @Override
    public void fetchLocation(@NotNull final Object o, @NotNull final Position position) {
        Intrinsics.checkNotNullParameter(o, "");
        Intrinsics.checkNotNullParameter((Object)position, "");
        final net.minecraft.server.v1_12_R1.Entity entity = (net.minecraft.server.v1_12_R1.Entity)o;
        final double locX = entity.locX;
        final double locY = entity.locY;
        final double locZ = entity.locZ;
        position.getVec3d().x = locX;
        position.getVec3d().y = locY;
        position.getVec3d().z = locZ;
        position.setYaw(entity.yaw);
        position.setPitch(entity.pitch);
    }
    
    private static final void getChunkAtAsync$lambda$0(final CompletableFuture completableFuture, final CraftWorld craftWorld, final int n, final int n2) {
        Intrinsics.checkNotNullParameter((Object)completableFuture, "");
        Intrinsics.checkNotNullParameter((Object)craftWorld, "");
        final Chunk chunk = craftWorld.getHandle().getChunkProviderServer().getChunkAt(n, n2);
        Intrinsics.checkNotNullExpressionValue((Object)chunk, "");
        completableFuture.complete(new ChunkHandleImpl(chunk));
    }
}
