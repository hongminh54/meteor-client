// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.modern;

import org.bukkit.util.Vector;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.Collection;
import org.bukkit.entity.Entity;
import java.util.Set;
import org.bukkit.block.data.BlockData;
import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import org.jetbrains.annotations.NotNull;
import org.bukkit.block.Block;
import kotlin.Metadata;
import dev.imanity.antixray.nms.NativeProvider;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007" }, d2 = { "Ldev/imanity/antixray/nms/modern/AbstractModernNativeProvider;", "Ldev/imanity/antixray/nms/NativeProvider;", "()V", "createBlockData", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "block", "Lorg/bukkit/block/Block;", "modern-base" })
public abstract class AbstractModernNativeProvider implements NativeProvider
{
    @NotNull
    @Override
    public WrappedBlockState createBlockData(@NotNull final Block block) {
        Intrinsics.checkNotNullParameter((Object)block, "");
        final BlockData blockData = block.getBlockData();
        Intrinsics.checkNotNullExpressionValue((Object)blockData, "");
        final WrappedBlockState byString = WrappedBlockState.getByString(blockData.getAsString());
        Intrinsics.checkNotNullExpressionValue((Object)byString, "");
        return byString;
    }
    
    @NotNull
    @Override
    public Set<Integer> getBlockDataIds(@NotNull final Object o) {
        return DefaultImpls.getBlockDataIds(this, o);
    }
    
    @Override
    public void init() {
        DefaultImpls.init(this);
    }
    
    @Override
    public boolean isZoomSupported() {
        return DefaultImpls.isZoomSupported(this);
    }
    
    @Override
    public void resetEmptyTicks() {
        DefaultImpls.resetEmptyTicks(this);
    }
    
    @Override
    public void updateEntity(@NotNull final Entity entity, @NotNull final Collection<? extends Player> collection) {
        DefaultImpls.updateEntity(this, entity, collection);
    }
    
    @NotNull
    @Override
    public Vector zoom(@NotNull final Location location, final double n) {
        return DefaultImpls.zoom(this, location, n);
    }
}
