// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.modern;

import dev.imanity.generated.org.bukkit.craftbukkit.util.CraftMagicNumbersHandle;
import dev.imanity.generated.net.minecraft.world.level.block.state.IBlockDataHandle;
import org.bukkit.Material;
import dev.imanity.generated.net.minecraft.world.level.block.BlockHandle;
import dev.imanity.antixray.nms.WrappedBlockStateCache;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import dev.imanity.antixray.api.nms.ChunkSectionHandle;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J \u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0016J \u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0016J \u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0016R\u0018\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\u0004\u0018\u00010\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0018" }, d2 = { "Ldev/imanity/antixray/nms/modern/ChunkSectionHandleImpl;", "Ldev/imanity/antixray/api/nms/ChunkSectionHandle;", "array", "", "Ldev/imanity/generated/net/minecraft/world/level/chunk/ChunkSectionHandle;", "index", "", "([Ldev/imanity/generated/net/minecraft/world/level/chunk/ChunkSectionHandle;I)V", "[Ldev/imanity/generated/net/minecraft/world/level/chunk/ChunkSectionHandle;", "empty", "", "getEmpty", "()Z", "instance", "getInstance", "()Ldev/imanity/generated/net/minecraft/world/level/chunk/ChunkSectionHandle;", "getBlockData", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "x", "y", "z", "getBlockId", "getBlockType", "Lorg/bukkit/Material;", "modern" })
public final class ChunkSectionHandleImpl implements ChunkSectionHandle
{
    @NotNull
    private final dev.imanity.generated.net.minecraft.world.level.chunk.ChunkSectionHandle[] array;
    private final int index;
    
    public ChunkSectionHandleImpl(@NotNull final dev.imanity.generated.net.minecraft.world.level.chunk.ChunkSectionHandle[] array, final int index) {
        Intrinsics.checkNotNullParameter((Object)array, "");
        this.array = array;
        this.index = index;
    }
    
    private final dev.imanity.generated.net.minecraft.world.level.chunk.ChunkSectionHandle getInstance() {
        return this.array[this.index];
    }
    
    @Override
    public boolean getEmpty() {
        final dev.imanity.generated.net.minecraft.world.level.chunk.ChunkSectionHandle instance = this.getInstance();
        return instance == null || instance.isEmpty();
    }
    
    @NotNull
    @Override
    public WrappedBlockState getBlockData(final int n, final int n2, final int n3) {
        final dev.imanity.generated.net.minecraft.world.level.chunk.ChunkSectionHandle instance = this.getInstance();
        final Object o = (instance != null) ? instance.getBlockData(n, n2, n3) : null;
        if (o == null) {
            final WrappedBlockState byGlobalId = WrappedBlockState.getByGlobalId(0);
            Intrinsics.checkNotNullExpressionValue((Object)byGlobalId, "");
            return byGlobalId;
        }
        return WrappedBlockStateCache.INSTANCE.get(BlockHandle.REGISTRY_ID.getId(o));
    }
    
    @NotNull
    @Override
    public Material getBlockType(final int n, final int n2, final int n3) {
        final dev.imanity.generated.net.minecraft.world.level.chunk.ChunkSectionHandle instance = this.getInstance();
        final Object o = (instance != null) ? instance.getBlockData(n, n2, n3) : null;
        if (o == null) {
            return Material.AIR;
        }
        final Material materialFromBlock = CraftMagicNumbersHandle.getMaterialFromBlock(IBlockDataHandle.T.getBlock.invoke(o));
        Intrinsics.checkNotNullExpressionValue((Object)materialFromBlock, "");
        return materialFromBlock;
    }
    
    @Override
    public int getBlockId(final int n, final int n2, final int n3) {
        final dev.imanity.generated.net.minecraft.world.level.chunk.ChunkSectionHandle instance = this.getInstance();
        final Object o = (instance != null) ? instance.getBlockData(n, n2, n3) : null;
        if (o == null) {
            return 0;
        }
        return BlockHandle.REGISTRY_ID.getId(o);
    }
}
