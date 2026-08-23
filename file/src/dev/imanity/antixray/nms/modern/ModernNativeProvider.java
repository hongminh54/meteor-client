// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.modern;

import kotlin.jvm.JvmName;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import io.fairyproject.log.Log;
import dev.imanity.antixray.nms.modern.template.RunnableConsumer;
import dev.imanity.generated.org.bukkit.craftbukkit.CraftChunkHandle;
import dev.imanity.antixray.api.util.Position;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;
import dev.imanity.generated.net.minecraft.server.MinecraftServerHandle;
import dev.imanity.generated.org.bukkit.craftbukkit.block.data.CraftBlockDataHandle;
import org.bukkit.block.data.BlockData;
import com.google.common.collect.ImmutableList;
import dev.imanity.generated.net.minecraft.core.IdMapperHandle;
import java.util.List;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import dev.imanity.generated.net.minecraft.world.level.block.BlockHandle;
import dev.imanity.generated.org.bukkit.craftbukkit.util.CraftMagicNumbersHandle;
import java.util.Set;
import java.util.Iterator;
import dev.imanity.generated.net.minecraft.server.level.EntityTrackerEntryHandle;
import dev.imanity.generated.net.minecraft.server.level.EntityTrackerHandle;
import dev.imanity.generated.net.minecraft.world.entity.EntityHandle;
import java.util.Collection;
import org.bukkit.util.BoundingBox;
import dev.imanity.antixray.api.math.AABB;
import dev.imanity.antixray.bootstrap.bukkit.BukkitPlugin;
import dev.imanity.antixray.api.chunk.DirectChunk;
import kotlin.NotImplementedError;
import org.bukkit.Material;
import dev.imanity.generated.net.minecraft.core.BlockPosHandle;
import dev.imanity.generated.net.minecraft.server.network.ServerGamePacketListenerImplHandle;
import dev.imanity.generated.net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacketHandle;
import dev.imanity.generated.net.minecraft.server.level.ServerPlayerHandle;
import dev.imanity.generated.org.bukkit.craftbukkit.entity.CraftEntityHandle;
import org.bukkit.entity.Player;
import dev.imanity.generated.net.minecraft.server.level.ChunkProviderServerHandle;
import dev.imanity.generated.net.minecraft.server.level.WorldServerHandle;
import dev.imanity.generated.net.minecraft.world.level.WorldHandle;
import io.fairyproject.mc.scheduler.MCScheduler;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import kotlin.jvm.functions.Function1;
import java.util.concurrent.Executor;
import io.fairyproject.mc.scheduler.MCSchedulers;
import io.fairyproject.mc.MCWorld;
import dev.imanity.antixray.nms.Folia;
import java.util.concurrent.CompletableFuture;
import dev.imanity.generated.org.bukkit.craftbukkit.CraftWorldHandle;
import org.bukkit.World;
import kotlin.jvm.internal.Intrinsics;
import org.bukkit.entity.Entity;
import dev.imanity.generated.net.minecraft.world.level.chunk.ChunkHandle;
import dev.imanity.antixray.nms.modern.template.TemplateResolver;
import java.lang.reflect.Method;
import kotlin.Lazy;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u0000 ?2\u00020\u0001:\u0001?B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004H\u0017¢\u0006\u0004\b\f\u0010\rJ-\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J-\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000fH\u0016¢\u0006\u0004\b\u0014\u0010\u0013J\u001f\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u0005\u001a\u00020\u0004H\u0017¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0005\u001a\u00020\u001cH\u0017¢\u0006\u0004\b\u001e\u0010\u001fJ'\u0010 \u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u0019H\u0017¢\u0006\u0004\b \u0010!J/\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\"2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u0019H\u0017¢\u0006\u0004\b#\u0010$J/\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\"2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u0019H\u0002¢\u0006\u0004\b%\u0010$J\u0017\u0010&\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u001cH\u0016¢\u0006\u0004\b&\u0010'J\u0017\u0010(\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u001cH\u0017¢\u0006\u0004\b(\u0010)J\u0017\u0010*\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\tH\u0016¢\u0006\u0004\b*\u0010+J\u001d\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u0005\u001a\u00020,H\u0017¢\u0006\u0004\b-\u0010.J'\u00100\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020/2\u0006\u0010\n\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u0019H\u0016¢\u0006\u0004\b0\u00101J\u000f\u00102\u001a\u00020\u0011H\u0016¢\u0006\u0004\b2\u0010\u0003J7\u00105\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020/2\u0006\u0010\n\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u00192\u0006\u00103\u001a\u00020\u00192\u0006\u00104\u001a\u00020,H\u0016¢\u0006\u0004\b5\u00106J/\u00107\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u00192\u0006\u00103\u001a\u00020\u0019H\u0016¢\u0006\u0004\b7\u00108J%\u0010:\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u001c2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020/09H\u0016¢\u0006\u0004\b:\u0010;J\u000f\u0010=\u001a\u00020<H\u0016¢\u0006\u0004\b=\u0010>" }, d2 = { "Ldev/imanity/antixray/nms/modern/ModernNativeProvider;", "Ldev/imanity/antixray/nms/modern/AbstractModernNativeProvider;", "<init>", "()V", "", "p0", "Lorg/bukkit/block/data/BlockData;", "craftBlockDataFromState", "(Ljava/lang/Object;)Lorg/bukkit/block/data/BlockData;", "Lorg/bukkit/World;", "p1", "Ldev/imanity/antixray/api/nms/ChunkHandle;", "createChunkHandle", "(Lorg/bukkit/World;Ljava/lang/Object;)Ldev/imanity/antixray/api/nms/ChunkHandle;", "Ldev/imanity/antixray/api/chunk/DirectChunk;", "", "p2", "", "disableChunkUnload", "(Lorg/bukkit/World;Ldev/imanity/antixray/api/chunk/DirectChunk;[Ldev/imanity/antixray/api/chunk/DirectChunk;)V", "enableChunkUnload", "Ldev/imanity/antixray/api/util/Position;", "fetchLocation", "(Ljava/lang/Object;Ldev/imanity/antixray/api/util/Position;)V", "", "", "getBlockDataIds", "(Ljava/lang/Object;)Ljava/util/Set;", "Lorg/bukkit/entity/Entity;", "Ldev/imanity/antixray/api/math/AABB;", "getBoundingBox", "(Lorg/bukkit/entity/Entity;)Ldev/imanity/antixray/api/math/AABB;", "getChunkAt", "(Lorg/bukkit/World;II)Ldev/imanity/antixray/api/nms/ChunkHandle;", "Ljava/util/concurrent/CompletableFuture;", "getChunkAtAsync", "(Lorg/bukkit/World;II)Ljava/util/concurrent/CompletableFuture;", "getChunkAtAsyncInternal", "getEntityTrackingRange", "(Lorg/bukkit/entity/Entity;)I", "getLocatableObject", "(Lorg/bukkit/entity/Entity;)Ljava/lang/Object;", "getMaxEntityTrackingRange", "(Lorg/bukkit/World;)I", "Lorg/bukkit/Material;", "getPossibleDefinition", "(Lorg/bukkit/Material;)Ljava/util/Set;", "Lorg/bukkit/entity/Player;", "resendChunk", "(Lorg/bukkit/entity/Player;II)V", "resetEmptyTicks", "p3", "p4", "sendBlock", "(Lorg/bukkit/entity/Player;IIILorg/bukkit/Material;)V", "updateBlock", "(Lorg/bukkit/World;III)V", "", "updateEntity", "(Lorg/bukkit/entity/Entity;Ljava/util/Collection;)V", "", "validate", "()Z", "Companion" })
@SourceDebugExtension({ "SMAP\nModernNativeProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ModernNativeProvider.kt\ndev/imanity/antixray/nms/modern/ModernNativeProvider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,275:1\n1855#2,2:276\n1549#2:278\n1620#2,3:279\n1603#2,9:282\n1855#2:291\n1856#2:293\n1612#2:294\n1#3:292\n1#3:295\n*S KotlinDebug\n*F\n+ 1 ModernNativeProvider.kt\ndev/imanity/antixray/nms/modern/ModernNativeProvider\n*L\n216#1:276,2\n227#1:278\n227#1:279,3\n244#1:282,9\n244#1:291\n244#1:293\n244#1:294\n244#1:292\n*E\n" })
public final class ModernNativeProvider extends AbstractModernNativeProvider
{
    @NotNull
    public static final Companion Companion;
    @NotNull
    private static final Lazy<Method> getChunkAtAsyncMethod$delegate;
    
    @Override
    public boolean validate() {
        try {
            TemplateResolver.init();
        }
        catch (final Throwable t) {
            return false;
        }
        return ChunkHandle.T.isAvailable();
    }
    
    @Override
    public int getEntityTrackingRange(@NotNull final Entity entity) {
        Intrinsics.checkNotNullParameter((Object)entity, "");
        return entity.getWorld().getViewDistance() * 16;
    }
    
    @Override
    public int getMaxEntityTrackingRange(@NotNull final World world) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        return world.getViewDistance() * 16;
    }
    
    @NotNull
    @Override
    public dev.imanity.antixray.api.nms.ChunkHandle createChunkHandle(@NotNull final World world, @NotNull final Object o) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        Intrinsics.checkNotNullParameter(o, "");
        final ChunkHandle handle = ChunkHandle.createHandle(o);
        Intrinsics.checkNotNullExpressionValue((Object)handle, "");
        return new ChunkHandleImpl(world, handle);
    }
    
    @NotNull
    @Override
    public dev.imanity.antixray.api.nms.ChunkHandle getChunkAt(@NotNull final World world, final int n, final int n2) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        final ChunkHandle chunk = CraftWorldHandle.createHandle(world).getHandle().getChunkAt(n, n2);
        Intrinsics.checkNotNull((Object)chunk);
        return new ChunkHandleImpl(world, chunk);
    }
    
    @NotNull
    @Override
    public CompletableFuture<dev.imanity.antixray.api.nms.ChunkHandle> getChunkAtAsync(@NotNull final World obj, final int i, final int j) {
        Intrinsics.checkNotNullParameter((Object)obj, "");
        final Method access$getGetChunkAtAsyncMethod = ModernNativeProvider.Companion.getGetChunkAtAsyncMethod();
        if (Folia.INSTANCE.getIS_FOLIA() && access$getGetChunkAtAsyncMethod != null) {
            final CompletableFuture completableFuture = new CompletableFuture();
            access$getGetChunkAtAsyncMethod.invoke(obj, i, j, true, ModernNativeProvider::getChunkAtAsync$lambda$1);
            return completableFuture;
        }
        final MCScheduler chunkScheduler = MCSchedulers.getChunkScheduler(MCWorld.from((Object)obj), i, j);
        if (chunkScheduler.isCurrentThread()) {
            return this.getChunkAtAsyncInternal(obj, i, j);
        }
        final CompletableFuture<Object> thenCompose = (CompletableFuture<Object>)CompletableFuture.supplyAsync(ModernNativeProvider::getChunkAtAsync$lambda$2, (Executor)chunkScheduler).thenCompose((Function<? super CompletableFuture, ? extends CompletionStage<dev.imanity.antixray.api.nms.ChunkHandle>>)ModernNativeProvider::getChunkAtAsync$lambda$3);
        Intrinsics.checkNotNullExpressionValue((Object)thenCompose, "");
        return (CompletableFuture<dev.imanity.antixray.api.nms.ChunkHandle>)thenCompose;
    }
    
    private final CompletableFuture<dev.imanity.antixray.api.nms.ChunkHandle> getChunkAtAsyncInternal(final World world, final int n, final int n2) {
        final WorldHandle worldHandle = CraftWorldHandle.T.getHandle.invoke(world);
        final CompletableFuture completableFuture = new CompletableFuture();
        final ChunkProviderServerHandle chunkProviderServerHandle = WorldServerHandle.T.field_chunkProviderServer.isAvailable() ? WorldServerHandle.T.field_chunkProviderServer.get(worldHandle.getRaw()) : ChunkProviderServerHandle.createHandle(WorldHandle.T.getChunkProvider.invoke(worldHandle.getRaw()));
        try {
            chunkProviderServerHandle.getChunkAtAsync(n, n2, ModernNativeProvider::getChunkAtAsyncInternal$lambda$4);
        }
        catch (final Throwable ex) {
            completableFuture.completeExceptionally(ex);
        }
        return completableFuture;
    }
    
    @Override
    public void resendChunk(@NotNull final Player player, final int n, final int n2) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        final ServerGamePacketListenerImplHandle playerConnection = ServerPlayerHandle.createHandle(CraftEntityHandle.T.getHandle.invoke(player)).getPlayerConnection();
        final WorldHandle handle = CraftWorldHandle.createHandle(player.getWorld()).getHandle();
        playerConnection.sendPacket(ClientboundLevelChunkWithLightPacketHandle.createNew(handle.getChunkAt(n, n2).getRaw(), handle.getRaw()));
    }
    
    @Override
    public void updateBlock(@NotNull final World world, final int n, final int n2, final int n3) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        CraftWorldHandle.createHandle(world).getHandle().flagDirty(BlockPosHandle.createNew(n, n2, n3));
    }
    
    @Override
    public void sendBlock(@NotNull final Player player, final int n, final int n2, final int n3, @NotNull final Material material) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)material, "");
        throw new NotImplementedError("An operation is not implemented: " + "Not yet implemented");
    }
    
    @Override
    public void disableChunkUnload(@NotNull final World world, @NotNull final DirectChunk directChunk, @NotNull final DirectChunk[] array) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        Intrinsics.checkNotNullParameter((Object)directChunk, "");
        Intrinsics.checkNotNullParameter((Object)array, "");
        WorldServerHandle.T.setForceLoadedAsync.invoke(CraftWorldHandle.T.getHandle.invoke(world).getRaw(), directChunk.getX(), directChunk.getZ(), BukkitPlugin.INSTANCE, true, 1);
    }
    
    @Override
    public void enableChunkUnload(@NotNull final World world, @NotNull final DirectChunk directChunk, @NotNull final DirectChunk[] array) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        Intrinsics.checkNotNullParameter((Object)directChunk, "");
        Intrinsics.checkNotNullParameter((Object)array, "");
        WorldServerHandle.T.setForceLoadedAsync.invoke(CraftWorldHandle.T.getHandle.invoke(world).getRaw(), directChunk.getX(), directChunk.getZ(), BukkitPlugin.INSTANCE, false, 1);
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
        final EntityHandle entityHandle = CraftEntityHandle.T.entityHandle.get(entity);
        final EntityTrackerHandle entityTrackerHandle = WorldServerHandle.T.getEntityTrackerHandle.invoke(entityHandle.getWorld().getRaw());
        EntityTrackerEntryHandle entityTrackerEntryHandle;
        if (EntityHandle.T.tracker.isAvailable()) {
            entityTrackerEntryHandle = EntityHandle.T.tracker.get(entityHandle.getRaw());
        }
        else {
            final EntityTrackerEntryHandle entityTrackerEntryHandle2 = EntityHandle.T.moonrise$getTrackedEntity.isAvailable() ? EntityHandle.T.moonrise$getTrackedEntity.invoke(entityHandle.getRaw()) : entityTrackerHandle.getEntry(entityHandle.getIdField());
            if (entityTrackerEntryHandle2 == null) {
                return;
            }
            entityTrackerEntryHandle = entityTrackerEntryHandle2;
        }
        final EntityTrackerEntryHandle entityTrackerEntryHandle3 = entityTrackerEntryHandle;
        final Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            final ServerPlayerHandle handle = ServerPlayerHandle.createHandle(CraftEntityHandle.T.getHandle.invoke(iterator.next()));
            entityTrackerEntryHandle3.removeViewer(handle);
            entityTrackerEntryHandle3.updatePlayer(handle);
        }
    }
    
    @NotNull
    @Override
    public Set<Integer> getPossibleDefinition(@NotNull final Material material) {
        Intrinsics.checkNotNullParameter((Object)material, "");
        final BlockHandle handle = BlockHandle.createHandle(CraftMagicNumbersHandle.getBlockFromMaterial(material));
        final IdMapperHandle registry_ID = BlockHandle.REGISTRY_ID;
        final ImmutableList<Object> possibleDefinitions = handle.getStates().getPossibleDefinitions();
        Intrinsics.checkNotNullExpressionValue((Object)possibleDefinitions, "");
        final Iterable iterable;
        final Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable = (Iterable)possibleDefinitions, 10));
        final Iterator iterator = iterable.iterator();
        while (iterator.hasNext()) {
            collection.add(registry_ID.getId(iterator.next()));
        }
        return CollectionsKt.toSet((Iterable)collection);
    }
    
    private final BlockData craftBlockDataFromState(final Object o) {
        BlockData blockData;
        if (CraftBlockDataHandle.T.createData.isAvailable()) {
            final BlockData invoke = CraftBlockDataHandle.T.createData.invoke(o);
            Intrinsics.checkNotNullExpressionValue((Object)invoke, "");
            blockData = invoke;
        }
        else {
            if (!CraftBlockDataHandle.T.fromData.isAvailable()) {
                throw new IllegalStateException("CraftBlockData has neither createData nor fromData".toString());
            }
            final BlockData invoke2 = CraftBlockDataHandle.T.fromData.invoke(o);
            Intrinsics.checkNotNullExpressionValue((Object)invoke2, "");
            blockData = invoke2;
        }
        return blockData;
    }
    
    @NotNull
    @Override
    public Set<Integer> getBlockDataIds(@NotNull final Object o) {
        Intrinsics.checkNotNullParameter(o, "");
        final BlockData blockData = (BlockData)o;
        final BlockHandle handle = BlockHandle.createHandle(CraftMagicNumbersHandle.getBlockFromMaterial(blockData.getMaterial()));
        final IdMapperHandle registry_ID = BlockHandle.REGISTRY_ID;
        final ImmutableList<Object> possibleDefinitions = handle.getStates().getPossibleDefinitions();
        Intrinsics.checkNotNullExpressionValue((Object)possibleDefinitions, "");
        final Iterable iterable = (Iterable)possibleDefinitions;
        final Collection collection = new ArrayList();
        for (final Object next : iterable) {
            Intrinsics.checkNotNull(next);
            final Integer n = this.craftBlockDataFromState(next).matches(blockData) ? Integer.valueOf(registry_ID.getId(next)) : null;
            if (n != null) {
                collection.add(n);
            }
        }
        return CollectionsKt.toSet((Iterable)collection);
    }
    
    @Override
    public void resetEmptyTicks() {
        final Template.Field.Integer emptyTicks = MinecraftServerHandle.T.emptyTicks;
        if (!emptyTicks.isAvailable()) {
            return;
        }
        emptyTicks.setInteger(MinecraftServerHandle.instance().getRaw(), 0);
    }
    
    @NotNull
    @Override
    public Object getLocatableObject(@NotNull final Entity entity) {
        Intrinsics.checkNotNullParameter((Object)entity, "");
        final EntityHandle value = CraftEntityHandle.T.entityHandle.get(entity);
        Intrinsics.checkNotNullExpressionValue((Object)value, "");
        return value;
    }
    
    @Override
    public void fetchLocation(@NotNull final Object o, @NotNull final Position position) {
        Intrinsics.checkNotNullParameter(o, "");
        Intrinsics.checkNotNullParameter((Object)position, "");
        final EntityHandle entityHandle = (EntityHandle)o;
        position.getVec3d().x = entityHandle.getLocX();
        position.getVec3d().y = entityHandle.getLocY();
        position.getVec3d().z = entityHandle.getLocZ();
        position.setYaw(entityHandle.getYaw());
        position.setPitch(entityHandle.getPitch());
    }
    
    private static final void getChunkAtAsync$lambda$1(final CompletableFuture completableFuture, final ModernNativeProvider modernNativeProvider, final World world, final Object o) {
        Intrinsics.checkNotNullParameter((Object)completableFuture, "");
        Intrinsics.checkNotNullParameter((Object)modernNativeProvider, "");
        Intrinsics.checkNotNullParameter((Object)world, "");
        try {
            CompletableFuture completableFuture2 = completableFuture;
            dev.imanity.antixray.api.nms.ChunkHandle chunkHandle;
            if (o != null) {
                final Object handle = CraftChunkHandle.createHandle(o).getHandle();
                Intrinsics.checkNotNullExpressionValue(handle, "");
                chunkHandle = modernNativeProvider.createChunkHandle(world, handle);
                completableFuture2 = completableFuture;
            }
            else {
                chunkHandle = null;
            }
            completableFuture2.complete(chunkHandle);
        }
        catch (final Throwable ex) {
            completableFuture.completeExceptionally(ex);
        }
    }
    
    private static final CompletableFuture getChunkAtAsync$lambda$2(final ModernNativeProvider modernNativeProvider, final World world, final int n, final int n2) {
        Intrinsics.checkNotNullParameter((Object)modernNativeProvider, "");
        Intrinsics.checkNotNullParameter((Object)world, "");
        return modernNativeProvider.getChunkAtAsyncInternal(world, n, n2);
    }
    
    private static final CompletionStage getChunkAtAsync$lambda$3(final Function1 function1, final Object o) {
        Intrinsics.checkNotNullParameter((Object)function1, "");
        return (CompletionStage)function1.invoke(o);
    }
    
    private static final void getChunkAtAsyncInternal$lambda$4(final CompletableFuture completableFuture, final World world, final int i, final int j, final Object o) {
        Intrinsics.checkNotNullParameter((Object)completableFuture, "");
        Intrinsics.checkNotNullParameter((Object)world, "");
        try {
            final Object unpack = RunnableConsumer.unpack(o);
            if (unpack == null) {
                completableFuture.complete(null);
            }
            else {
                final ChunkHandle handle = ChunkHandle.createHandle(unpack);
                Intrinsics.checkNotNullExpressionValue((Object)handle, "");
                completableFuture.complete(new ChunkHandleImpl(world, handle));
            }
        }
        catch (final Throwable t) {
            Log.error("Failed to unpack chunk at " + i + ", " + j, t, new Object[0]);
            completableFuture.complete(null);
        }
    }
    
    public static final /* synthetic */ Lazy access$getGetChunkAtAsyncMethod$delegate$cp() {
        return ModernNativeProvider.getChunkAtAsyncMethod$delegate;
    }
    
    static {
        Companion = new Companion(null);
        getChunkAtAsyncMethod$delegate = LazyKt.lazy((Function0)ModernNativeProvider$Companion$getChunkAtAsyncMethod.ModernNativeProvider$Companion$getChunkAtAsyncMethod$2.INSTANCE);
    }
    
    @Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\t\u001a\u0004\u0018\u00010\u00048CX\u0083\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b" }, d2 = { "Ldev/imanity/antixray/nms/modern/ModernNativeProvider$Companion;", "", "<init>", "()V", "Ljava/lang/reflect/Method;", "getChunkAtAsyncMethod$delegate", "Lkotlin/Lazy;", "getGetChunkAtAsyncMethod", "()Ljava/lang/reflect/Method;", "getChunkAtAsyncMethod" })
    public static final class Companion
    {
        private Companion() {
        }
        
        @JvmName(name = "getGetChunkAtAsyncMethod")
        private final Method getGetChunkAtAsyncMethod() {
            return (Method)ModernNativeProvider.access$getGetChunkAtAsyncMethod$delegate$cp().getValue();
        }
    }
}
