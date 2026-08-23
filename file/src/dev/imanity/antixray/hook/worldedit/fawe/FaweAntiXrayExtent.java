// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.hook.worldedit.fawe;

import java.util.Set;
import java.util.Iterator;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.regions.Region;
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
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import com.sk89q.worldedit.extent.AbstractDelegateExtent;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\u0018\u00002\u00020\u0001B3\u0012$\u0010\u0002\u001a \u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ1\u0010\n\u001a\u00020\u0005\"\u0010\b\u0000\u0010\u000b*\n\u0012\u0004\u0012\u0002H\u000b\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u0002H\u000bH\u0016¢\u0006\u0002\u0010\u0010JC\u0010\n\u001a\u00020\u0005\"\u0012\b\u0000\u0010\u000b*\f\u0012\u0006\u0012\u0004\u0018\u0001H\u000b\u0018\u00010\f2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u0001H\u000bH\u0016¢\u0006\u0002\u0010\u0014J5\u0010\u0015\u001a\u00020\u0004\"\u0012\b\u0000\u0010\u0016*\f\u0012\u0006\u0012\u0004\u0018\u0001H\u0016\u0018\u00010\f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u000f\u001a\u0004\u0018\u0001H\u0016H\u0016¢\u0006\u0002\u0010\u0019J\u001c\u0010\u0015\u001a\u00020\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J$\u0010\u0015\u001a\u00020\u00042\u0010\u0010\u001c\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\u001d2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016R,\u0010\u0002\u001a \u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e" }, d2 = { "Ldev/imanity/antixray/hook/worldedit/fawe/FaweAntiXrayExtent;", "Lcom/sk89q/worldedit/extent/AbstractDelegateExtent;", "callable", "Lkotlin/Function4;", "", "", "", "extent", "Lcom/sk89q/worldedit/extent/Extent;", "(Lkotlin/jvm/functions/Function4;Lcom/sk89q/worldedit/extent/Extent;)V", "setBlock", "T", "Lcom/sk89q/worldedit/world/block/BlockStateHolder;", "location", "Lcom/sk89q/worldedit/math/BlockVector3;", "block", "(Lcom/sk89q/worldedit/math/BlockVector3;Lcom/sk89q/worldedit/world/block/BlockStateHolder;)Z", "x", "y", "z", "(IIILcom/sk89q/worldedit/world/block/BlockStateHolder;)Z", "setBlocks", "B", "region", "Lcom/sk89q/worldedit/regions/Region;", "(Lcom/sk89q/worldedit/regions/Region;Lcom/sk89q/worldedit/world/block/BlockStateHolder;)I", "pattern", "Lcom/sk89q/worldedit/function/pattern/Pattern;", "vset", "", "fawe" })
@SourceDebugExtension({ "SMAP\nFaweAntiXrayExtent.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FaweAntiXrayExtent.kt\ndev/imanity/antixray/hook/worldedit/fawe/FaweAntiXrayExtent\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,48:1\n1855#2,2:49\n1855#2,2:51\n1855#2,2:53\n*S KotlinDebug\n*F\n+ 1 FaweAntiXrayExtent.kt\ndev/imanity/antixray/hook/worldedit/fawe/FaweAntiXrayExtent\n*L\n27#1:49,2\n34#1:51,2\n41#1:53,2\n*E\n" })
public final class FaweAntiXrayExtent extends AbstractDelegateExtent
{
    @NotNull
    private final Function4<Integer, Integer, Integer, Boolean, Unit> callable;
    
    public FaweAntiXrayExtent(@NotNull final Function4<? super Integer, ? super Integer, ? super Integer, ? super Boolean, Unit> callable, @NotNull final Extent extent) {
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
    
    public <T extends BlockStateHolder<T>> boolean setBlock(final int i, final int j, final int k, @Nullable final T t) {
        final Function4<Integer, Integer, Integer, Boolean, Unit> callable = this.callable;
        final Integer value = i;
        final Integer value2 = j;
        final Integer value3 = k;
        boolean air = false;
        Label_0048: {
            if (t != null) {
                final BlockType blockType = t.getBlockType();
                if (blockType != null) {
                    final BlockMaterial material = blockType.getMaterial();
                    if (material != null) {
                        air = material.isAir();
                        break Label_0048;
                    }
                }
            }
            air = false;
        }
        callable.invoke((Object)value, (Object)value2, (Object)value3, (Object)air);
        return super.setBlock(i, j, k, (BlockStateHolder)t);
    }
    
    public int setBlocks(@Nullable final Region region, @Nullable final Pattern pattern) {
        if (region != null) {
            for (final BlockVector3 blockVector3 : (Iterable)region) {
                final Function4<Integer, Integer, Integer, Boolean, Unit> callable = this.callable;
                final Integer value = blockVector3.getBlockX();
                final Integer value2 = blockVector3.getBlockY();
                final Integer value3 = blockVector3.getBlockZ();
                Intrinsics.checkNotNull((Object)pattern);
                callable.invoke((Object)value, (Object)value2, (Object)value3, (Object)pattern.applyBlock(blockVector3).getBlockType().getMaterial().isAir());
            }
        }
        return super.setBlocks(region, pattern);
    }
    
    public <B extends BlockStateHolder<B>> int setBlocks(@Nullable final Region region, @Nullable final B b) {
        if (region != null) {
            for (final BlockVector3 blockVector3 : (Iterable)region) {
                final Function4<Integer, Integer, Integer, Boolean, Unit> callable = this.callable;
                final Integer value = blockVector3.getBlockX();
                final Integer value2 = blockVector3.getBlockY();
                final Integer value3 = blockVector3.getBlockZ();
                boolean air = false;
                Label_0108: {
                    if (b != null) {
                        final BlockType blockType = b.getBlockType();
                        if (blockType != null) {
                            final BlockMaterial material = blockType.getMaterial();
                            if (material != null) {
                                air = material.isAir();
                                break Label_0108;
                            }
                        }
                    }
                    air = false;
                }
                callable.invoke((Object)value, (Object)value2, (Object)value3, (Object)air);
            }
        }
        return super.setBlocks(region, (BlockStateHolder)b);
    }
    
    public int setBlocks(@Nullable final Set<? extends BlockVector3> set, @Nullable final Pattern pattern) {
        if (set != null) {
            for (final BlockVector3 blockVector3 : set) {
                final Function4<Integer, Integer, Integer, Boolean, Unit> callable = this.callable;
                final BlockVector3 blockVector4 = blockVector3;
                Intrinsics.checkNotNull((Object)blockVector4);
                final Integer value = blockVector4.getBlockX();
                final Integer value2 = blockVector3.getBlockY();
                final Integer value3 = blockVector3.getBlockZ();
                Intrinsics.checkNotNull((Object)pattern);
                callable.invoke((Object)value, (Object)value2, (Object)value3, (Object)pattern.applyBlock(blockVector3).getBlockType().getMaterial().isAir());
            }
        }
        return super.setBlocks((Set)set, pattern);
    }
}
