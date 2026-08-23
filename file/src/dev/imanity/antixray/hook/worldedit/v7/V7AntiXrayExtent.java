// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.hook.worldedit.v7;

import com.sk89q.worldedit.world.registry.BlockMaterial;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockStateHolder;
import org.jetbrains.annotations.Nullable;
import com.sk89q.worldedit.math.BlockVector3;
import kotlin.jvm.internal.Intrinsics;
import com.sk89q.worldedit.extent.Extent;
import org.jetbrains.annotations.NotNull;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.Metadata;
import com.sk89q.worldedit.extent.AbstractDelegateExtent;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B3\u0012$\u0010\u0002\u001a \u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ1\u0010\n\u001a\u00020\u0005\"\u0010\b\u0000\u0010\u000b*\n\u0012\u0004\u0012\u0002H\u000b\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u0002H\u000bH\u0016¢\u0006\u0002\u0010\u0010R,\u0010\u0002\u001a \u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011" }, d2 = { "Ldev/imanity/antixray/hook/worldedit/v7/V7AntiXrayExtent;", "Lcom/sk89q/worldedit/extent/AbstractDelegateExtent;", "callable", "Lkotlin/Function4;", "", "", "", "extent", "Lcom/sk89q/worldedit/extent/Extent;", "(Lkotlin/jvm/functions/Function4;Lcom/sk89q/worldedit/extent/Extent;)V", "setBlock", "T", "Lcom/sk89q/worldedit/world/block/BlockStateHolder;", "location", "Lcom/sk89q/worldedit/math/BlockVector3;", "block", "(Lcom/sk89q/worldedit/math/BlockVector3;Lcom/sk89q/worldedit/world/block/BlockStateHolder;)Z", "worldedit7" })
public final class V7AntiXrayExtent extends AbstractDelegateExtent
{
    @NotNull
    private final Function4<Integer, Integer, Integer, Boolean, Unit> callable;
    
    public V7AntiXrayExtent(@NotNull final Function4<? super Integer, ? super Integer, ? super Integer, ? super Boolean, Unit> callable, @NotNull final Extent extent) {
        Intrinsics.checkNotNullParameter((Object)callable, "");
        Intrinsics.checkNotNullParameter((Object)extent, "");
        super(extent);
        this.callable = (Function4<Integer, Integer, Integer, Boolean, Unit>)callable;
    }
    
    public <T extends BlockStateHolder<T>> boolean setBlock(@Nullable final BlockVector3 blockVector3, final T t) {
        final Function4<Integer, Integer, Integer, Boolean, Unit> callable = this.callable;
        Intrinsics.checkNotNull((Object)blockVector3);
        final Integer value = blockVector3.getBlockX();
        final Integer value2 = blockVector3.getBlockY();
        final Integer value3 = blockVector3.getBlockZ();
        boolean air = false;
        Label_0060: {
            if (t != null) {
                final BlockType blockType = t.getBlockType();
                if (blockType != null) {
                    final BlockMaterial material = blockType.getMaterial();
                    if (material != null) {
                        air = material.isAir();
                        break Label_0060;
                    }
                }
            }
            air = false;
        }
        callable.invoke((Object)value, (Object)value2, (Object)value3, (Object)air);
        return super.setBlock(blockVector3, (BlockStateHolder)t);
    }
}
