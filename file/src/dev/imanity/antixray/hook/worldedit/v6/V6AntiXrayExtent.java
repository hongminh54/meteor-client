// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.hook.worldedit.v6;

import com.sk89q.worldedit.blocks.BaseBlock;
import org.jetbrains.annotations.Nullable;
import com.sk89q.worldedit.Vector;
import kotlin.jvm.internal.Intrinsics;
import com.sk89q.worldedit.extent.Extent;
import org.jetbrains.annotations.NotNull;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.Metadata;
import com.sk89q.worldedit.extent.AbstractDelegateExtent;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B3\u0012$\u0010\u0002\u001a \u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u001c\u0010\n\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R,\u0010\u0002\u001a \u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f" }, d2 = { "Ldev/imanity/antixray/hook/worldedit/v6/V6AntiXrayExtent;", "Lcom/sk89q/worldedit/extent/AbstractDelegateExtent;", "callable", "Lkotlin/Function4;", "", "", "", "extent", "Lcom/sk89q/worldedit/extent/Extent;", "(Lkotlin/jvm/functions/Function4;Lcom/sk89q/worldedit/extent/Extent;)V", "setBlock", "location", "Lcom/sk89q/worldedit/Vector;", "block", "Lcom/sk89q/worldedit/blocks/BaseBlock;", "worldedit6" })
public final class V6AntiXrayExtent extends AbstractDelegateExtent
{
    @NotNull
    private final Function4<Integer, Integer, Integer, Boolean, Unit> callable;
    
    public V6AntiXrayExtent(@NotNull final Function4<? super Integer, ? super Integer, ? super Integer, ? super Boolean, Unit> callable, @NotNull final Extent extent) {
        Intrinsics.checkNotNullParameter((Object)callable, "");
        Intrinsics.checkNotNullParameter((Object)extent, "");
        super(extent);
        this.callable = (Function4<Integer, Integer, Integer, Boolean, Unit>)callable;
    }
    
    public boolean setBlock(@Nullable final Vector vector, @Nullable final BaseBlock baseBlock) {
        final Function4<Integer, Integer, Integer, Boolean, Unit> callable = this.callable;
        Intrinsics.checkNotNull((Object)vector);
        callable.invoke((Object)vector.getBlockX(), (Object)vector.getBlockY(), (Object)vector.getBlockZ(), (Object)(baseBlock != null && baseBlock.isAir()));
        return super.setBlock(vector, baseBlock);
    }
}
