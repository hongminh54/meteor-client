// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.v1_12_R1;

import org.bukkit.craftbukkit.v1_12_R1.util.CraftMagicNumbers;
import org.bukkit.Material;
import net.minecraft.server.v1_12_R1.IBlockData;
import net.minecraft.server.v1_12_R1.Block;
import dev.imanity.antixray.nms.WrappedBlockStateCache;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import net.minecraft.server.v1_12_R1.ChunkSection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import dev.imanity.antixray.api.nms.ChunkSectionHandle;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J \u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0016J \u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0016J \u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0016R\u0016\u0010\b\u001a\u0004\u0018\u00010\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0018\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018" }, d2 = { "Ldev/imanity/antixray/nms/v1_12_R1/ChunkSectionHandleImpl;", "Ldev/imanity/antixray/api/nms/ChunkSectionHandle;", "chunkSections", "", "Lnet/minecraft/server/v1_12_R1/ChunkSection;", "index", "", "([Lnet/minecraft/server/v1_12_R1/ChunkSection;I)V", "chunkSection", "getChunkSection", "()Lnet/minecraft/server/v1_12_R1/ChunkSection;", "[Lnet/minecraft/server/v1_12_R1/ChunkSection;", "empty", "", "getEmpty", "()Z", "getBlockData", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "x", "y", "z", "getBlockId", "getBlockType", "Lorg/bukkit/Material;", "v1_12_1" })
@SourceDebugExtension({ "SMAP\nChunkSectionHandleImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChunkSectionHandleImpl.kt\ndev/imanity/antixray/nms/v1_12_R1/ChunkSectionHandleImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,41:1\n1#2:42\n*E\n" })
public final class ChunkSectionHandleImpl implements ChunkSectionHandle
{
    @NotNull
    private final ChunkSection[] chunkSections;
    private final int index;
    
    public ChunkSectionHandleImpl(@NotNull final ChunkSection[] chunkSections, final int index) {
        Intrinsics.checkNotNullParameter((Object)chunkSections, "");
        this.chunkSections = chunkSections;
        this.index = index;
    }
    
    private final ChunkSection getChunkSection() {
        return this.chunkSections[this.index];
    }
    
    @Override
    public boolean getEmpty() {
        final ChunkSection chunkSection = this.getChunkSection();
        return chunkSection != null && chunkSection.a();
    }
    
    @NotNull
    @Override
    public WrappedBlockState getBlockData(final int n, final int n2, final int n3) {
        final ChunkSection chunkSection = this.getChunkSection();
        final IBlockData blockData = (chunkSection != null) ? chunkSection.getType(n, n2, n3) : null;
        if (blockData == null) {
            final WrappedBlockState byGlobalId = WrappedBlockState.getByGlobalId(0);
            Intrinsics.checkNotNullExpressionValue((Object)byGlobalId, "");
            return byGlobalId;
        }
        final IBlockData blockData2 = blockData;
        return WrappedBlockStateCache.INSTANCE.get(CombinedID.INSTANCE.getId(Block.getId(blockData2.getBlock()), blockData2.getBlock().toLegacyData(blockData2)));
    }
    
    @NotNull
    @Override
    public Material getBlockType(final int n, final int n2, final int n3) {
        final ChunkSection chunkSection = this.getChunkSection();
        Material air = (chunkSection != null) ? CraftMagicNumbers.getMaterial(chunkSection.getType(n, n2, n3).getBlock()) : null;
        if (air == null) {
            air = Material.AIR;
        }
        return air;
    }
    
    @Override
    public int getBlockId(final int n, final int n2, final int n3) {
        final ChunkSection chunkSection = this.getChunkSection();
        final IBlockData blockData = (chunkSection != null) ? chunkSection.getType(n, n2, n3) : null;
        if (blockData == null) {
            return 0;
        }
        final IBlockData blockData2 = blockData;
        return CombinedID.INSTANCE.getId(Block.getId(blockData2.getBlock()), blockData2.getBlock().toLegacyData(blockData2));
    }
}
