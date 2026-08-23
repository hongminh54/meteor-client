// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.v1_16_R3;

import org.bukkit.craftbukkit.v1_16_R3.util.CraftMagicNumbers;
import org.bukkit.Material;
import net.minecraft.server.v1_16_R3.IBlockData;
import net.minecraft.server.v1_16_R3.Block;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import net.minecraft.server.v1_16_R3.ChunkSection;
import kotlin.Metadata;
import dev.imanity.antixray.api.nms.ChunkSectionHandle;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J \u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0016J \u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0016J \u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0016R\u0016\u0010\b\u001a\u0004\u0018\u00010\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0018\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018" }, d2 = { "Ldev/imanity/antixray/nms/v1_16_R3/ChunkSectionHandleImpl;", "Ldev/imanity/antixray/api/nms/ChunkSectionHandle;", "chunkSections", "", "Lnet/minecraft/server/v1_16_R3/ChunkSection;", "index", "", "([Lnet/minecraft/server/v1_16_R3/ChunkSection;I)V", "chunkSection", "getChunkSection", "()Lnet/minecraft/server/v1_16_R3/ChunkSection;", "[Lnet/minecraft/server/v1_16_R3/ChunkSection;", "empty", "", "getEmpty", "()Z", "getBlockData", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "x", "y", "z", "getBlockId", "getBlockType", "Lorg/bukkit/Material;", "v1_16_5" })
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
        return chunkSection == null || chunkSection.c();
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
        final WrappedBlockState byGlobalId2 = WrappedBlockState.getByGlobalId(Block.REGISTRY_ID.getId((Object)blockData));
        Intrinsics.checkNotNullExpressionValue((Object)byGlobalId2, "");
        return byGlobalId2;
    }
    
    @NotNull
    @Override
    public Material getBlockType(final int n, final int n2, final int n3) {
        final ChunkSection chunkSection = this.getChunkSection();
        Block block2 = null;
        Block block = null;
        Label_0026: {
            if (chunkSection != null) {
                final IBlockData type = chunkSection.getType(n, n2, n3);
                if (type != null) {
                    block = (block2 = type.getBlock());
                    break Label_0026;
                }
            }
            block = (block2 = null);
        }
        if (block2 == null) {
            return Material.AIR;
        }
        final Material material = CraftMagicNumbers.getMaterial(block);
        Intrinsics.checkNotNullExpressionValue((Object)material, "");
        return material;
    }
    
    @Override
    public int getBlockId(final int n, final int n2, final int n3) {
        final ChunkSection chunkSection = this.getChunkSection();
        final IBlockData blockData = (chunkSection != null) ? chunkSection.getType(n, n2, n3) : null;
        if (blockData == null) {
            return 0;
        }
        return Block.REGISTRY_ID.getId((Object)blockData);
    }
}
