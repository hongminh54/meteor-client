// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.v1_8_R3;

import com.google.common.collect.ImmutableList;
import dev.imanity.antixray.nms.legacy.CombinedID;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.BlockStateList;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftMagicNumbers;
import java.util.Set;
import net.minecraft.server.v1_8_R3.EntityTrackerEntry;
import java.util.List;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import java.util.Collection;
import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import dev.imanity.antixray.api.math.AABB;
import org.bukkit.Material;
import net.minecraft.server.v1_8_R3.BlockPosition;
import java.util.Iterator;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.TileEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutMapChunk;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import java.util.concurrent.CompletableFuture;
import net.minecraft.server.v1_8_R3.Chunk;
import dev.imanity.antixray.api.nms.ChunkHandle;
import org.spigotmc.SpigotWorldConfig;
import kotlin.comparisons.ComparisonsKt;
import org.spigotmc.TrackingRange;
import net.minecraft.server.v1_8_R3.MovingObjectPosition;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import net.minecraft.server.v1_8_R3.Vec3D;
import dev.imanity.antixray.api.util.Position;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.bukkit.Location;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.server.v1_8_R3.Block;
import java.lang.reflect.Field;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import dev.imanity.antixray.nms.legacy.AbstractLegacyNativeProvider;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u000fH\u0017¢\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0017¢\u0006\u0004\b\u0015\u0010\u0016J/\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00172\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0017¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u000fH\u0017¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010\"\u001a\u00020!2\u0006\u0010\u0005\u001a\u00020 2\u0006\u0010\u0007\u001a\u00020!H\u0002¢\u0006\u0004\b\"\u0010#J\u001d\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00130%2\u0006\u0010\u0005\u001a\u00020$H\u0017¢\u0006\u0004\b&\u0010'J\u000f\u0010)\u001a\u00020(H\u0016¢\u0006\u0004\b)\u0010*J'\u0010,\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020+2\u0006\u0010\u0007\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b,\u0010-J7\u00100\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020+2\u0006\u0010\u0007\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00132\u0006\u0010/\u001a\u00020$H\u0016¢\u0006\u0004\b0\u00101J/\u00102\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u0013H\u0016¢\u0006\u0004\b2\u00103J%\u00105\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u000f2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020+04H\u0016¢\u0006\u0004\b5\u00106J\u000f\u00107\u001a\u00020(H\u0016¢\u0006\u0004\b7\u0010*J\u001f\u00109\u001a\u0002082\u0006\u0010\u0005\u001a\u00020 2\u0006\u0010\u0007\u001a\u00020!H\u0017¢\u0006\u0004\b9\u0010:R\u0016\u0010<\u001a\u00020;8\u0002@\u0002X\u0083.¢\u0006\u0006\n\u0004\b<\u0010=" }, d2 = { "Ldev/imanity/antixray/nms/v1_8_R3/NativeProviderImpl;", "Ldev/imanity/antixray/nms/legacy/AbstractLegacyNativeProvider;", "<init>", "()V", "Lorg/bukkit/World;", "p0", "", "p1", "Ldev/imanity/antixray/api/nms/ChunkHandle;", "createChunkHandle", "(Lorg/bukkit/World;Ljava/lang/Object;)Ldev/imanity/antixray/api/nms/ChunkHandle;", "Ldev/imanity/antixray/api/util/Position;", "", "fetchLocation", "(Ljava/lang/Object;Ldev/imanity/antixray/api/util/Position;)V", "Lorg/bukkit/entity/Entity;", "Ldev/imanity/antixray/api/math/AABB;", "getBoundingBox", "(Lorg/bukkit/entity/Entity;)Ldev/imanity/antixray/api/math/AABB;", "", "p2", "getChunkAt", "(Lorg/bukkit/World;II)Ldev/imanity/antixray/api/nms/ChunkHandle;", "Ljava/util/concurrent/CompletableFuture;", "getChunkAtAsync", "(Lorg/bukkit/World;II)Ljava/util/concurrent/CompletableFuture;", "getEntityTrackingRange", "(Lorg/bukkit/entity/Entity;)I", "getLocatableObject", "(Lorg/bukkit/entity/Entity;)Ljava/lang/Object;", "getMaxEntityTrackingRange", "(Lorg/bukkit/World;)I", "Lorg/bukkit/Location;", "", "getMaxZoom", "(Lorg/bukkit/Location;D)D", "Lorg/bukkit/Material;", "", "getPossibleDefinition", "(Lorg/bukkit/Material;)Ljava/util/Set;", "", "isZoomSupported", "()Z", "Lorg/bukkit/entity/Player;", "resendChunk", "(Lorg/bukkit/entity/Player;II)V", "p3", "p4", "sendBlock", "(Lorg/bukkit/entity/Player;IIILorg/bukkit/Material;)V", "updateBlock", "(Lorg/bukkit/World;III)V", "", "updateEntity", "(Lorg/bukkit/entity/Entity;Ljava/util/Collection;)V", "validate", "Lorg/bukkit/util/Vector;", "zoom", "(Lorg/bukkit/Location;D)Lorg/bukkit/util/Vector;", "Ljava/lang/reflect/Field;", "blockStateListField", "Ljava/lang/reflect/Field;" })
@SourceDebugExtension({ "SMAP\nNativeProviderImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NativeProviderImpl.kt\ndev/imanity/antixray/nms/v1_8_R3/NativeProviderImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,197:1\n1549#2:198\n1620#2,3:199\n1549#2:202\n1620#2,3:203\n*S KotlinDebug\n*F\n+ 1 NativeProviderImpl.kt\ndev/imanity/antixray/nms/v1_8_R3/NativeProviderImpl\n*L\n174#1:198\n174#1:199,3\n190#1:202\n190#1:203,3\n*E\n" })
public final class NativeProviderImpl extends AbstractLegacyNativeProvider
{
    private Field blockStateListField;
    
    @Override
    public boolean validate() {
        boolean b;
        try {
            Class.forName("net.minecraft.server.v1_8_R3.Chunk");
            final Field declaredField = Block.class.getDeclaredField("blockStateList");
            Intrinsics.checkNotNullExpressionValue((Object)declaredField, "");
            this.blockStateListField = declaredField;
            Field blockStateListField;
            if ((blockStateListField = this.blockStateListField) == null) {
                Intrinsics.throwUninitializedPropertyAccessException("");
                blockStateListField = null;
            }
            blockStateListField.setAccessible(true);
            b = true;
        }
        catch (final ClassNotFoundException ex) {
            b = false;
        }
        return b;
    }
    
    @Override
    public boolean isZoomSupported() {
        return true;
    }
    
    @NotNull
    @Override
    public Vector zoom(@NotNull final Location location, final double n) {
        Intrinsics.checkNotNullParameter((Object)location, "");
        final Vector subtract = location.toVector().subtract(location.getDirection().clone().multiply(this.getMaxZoom(location, n)));
        Intrinsics.checkNotNullExpressionValue((Object)subtract, "");
        return subtract;
    }
    
    @NotNull
    @Override
    public Object getLocatableObject(@NotNull final Entity entity) {
        Intrinsics.checkNotNullParameter((Object)entity, "");
        final net.minecraft.server.v1_8_R3.Entity handle = ((CraftEntity)entity).getHandle();
        Intrinsics.checkNotNullExpressionValue((Object)handle, "");
        return handle;
    }
    
    @Override
    public void fetchLocation(@NotNull final Object o, @NotNull final Position position) {
        Intrinsics.checkNotNullParameter(o, "");
        Intrinsics.checkNotNullParameter((Object)position, "");
        final net.minecraft.server.v1_8_R3.Entity entity = (net.minecraft.server.v1_8_R3.Entity)o;
        final double locX = entity.locX;
        final double locY = entity.locY;
        final double locZ = entity.locZ;
        position.getVec3d().x = locX;
        position.getVec3d().y = locY;
        position.getVec3d().z = locZ;
        position.setYaw(entity.yaw);
        position.setPitch(entity.pitch);
    }
    
    private final double getMaxZoom(final Location location, final double n) {
        double n2 = n;
        final Vector vector = location.toVector();
        final Vec3D vec3D = new Vec3D(vector.getX(), vector.getY(), vector.getZ());
        final double a = vec3D.a;
        final double b = vec3D.b;
        final double c = vec3D.c;
        final Vector direction = location.getDirection();
        final double x = direction.getX();
        final double y = direction.getY();
        final double z = direction.getZ();
        final World world = location.getWorld();
        Intrinsics.checkNotNull((Object)world);
        final WorldServer handle = ((CraftWorld)world).getHandle();
        Intrinsics.checkNotNullExpressionValue((Object)handle, "");
        final WorldServer worldServer = handle;
        for (int i = 0; i < 8; ++i) {
            final float n3 = (float)((i & 0x1) * 2 - 1);
            final float n4 = (float)((i >> 1 & 0x1) * 2 - 1);
            final float n5 = (float)((i >> 2 & 0x1) * 2 - 1);
            final float n6 = n3 * 0.1f;
            final float n7 = n4 * 0.1f;
            final float n8 = n5 * 0.1f;
            final Vec3D add = vec3D.add((double)n6, (double)n7, (double)n8);
            Intrinsics.checkNotNullExpressionValue((Object)add, "");
            final MovingObjectPosition rayTrace = worldServer.rayTrace(add, new Vec3D(a - x * n2 + n6, b - y * n2 + n7, c - z * n2 + n8));
            if (rayTrace != null) {
                final double sqrt = Math.sqrt(rayTrace.pos.distanceSquared(vec3D));
                if (sqrt < n2) {
                    n2 = sqrt;
                }
            }
        }
        return n2;
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
        Intrinsics.checkNotNullExpressionValue((Object)chunk, "");
        return new ChunkHandleImpl(chunk);
    }
    
    @NotNull
    @Override
    public CompletableFuture<ChunkHandle> getChunkAtAsync(@NotNull final World world, final int n, final int n2) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        final CraftWorld craftWorld = (CraftWorld)world;
        final CompletableFuture completableFuture = new CompletableFuture();
        craftWorld.getHandle().chunkProviderServer.getChunkAt(n, n2, NativeProviderImpl::getChunkAtAsync$lambda$1);
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
        final Chunk chunk = chunkIfLoaded;
        handle.playerConnection.sendPacket((Packet)new PacketPlayOutMapChunk(chunk, false, 65535));
        final Iterator iterator = chunk.tileEntities.values().iterator();
        while (iterator.hasNext()) {
            final Packet updatePacket = ((TileEntity)iterator.next()).getUpdatePacket();
            if (updatePacket != null) {
                handle.playerConnection.sendPacket(updatePacket);
            }
        }
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
        final net.minecraft.server.v1_8_R3.World world = ((CraftEntity)entity).getHandle().world;
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
        final Block block = CraftMagicNumbers.getBlock(material);
        Field blockStateListField;
        if ((blockStateListField = this.blockStateListField) == null) {
            Intrinsics.throwUninitializedPropertyAccessException("");
            blockStateListField = null;
        }
        final Object value = blockStateListField.get(block);
        Intrinsics.checkNotNull(value);
        final ImmutableList a = ((BlockStateList)value).a();
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
    
    private static final void getChunkAtAsync$lambda$1(final CompletableFuture completableFuture, final CraftWorld craftWorld, final int n, final int n2) {
        Intrinsics.checkNotNullParameter((Object)completableFuture, "");
        Intrinsics.checkNotNullParameter((Object)craftWorld, "");
        final Chunk chunk = craftWorld.getHandle().chunkProviderServer.getChunkAt(n, n2);
        Intrinsics.checkNotNullExpressionValue((Object)chunk, "");
        completableFuture.complete(new ChunkHandleImpl(chunk));
    }
}
