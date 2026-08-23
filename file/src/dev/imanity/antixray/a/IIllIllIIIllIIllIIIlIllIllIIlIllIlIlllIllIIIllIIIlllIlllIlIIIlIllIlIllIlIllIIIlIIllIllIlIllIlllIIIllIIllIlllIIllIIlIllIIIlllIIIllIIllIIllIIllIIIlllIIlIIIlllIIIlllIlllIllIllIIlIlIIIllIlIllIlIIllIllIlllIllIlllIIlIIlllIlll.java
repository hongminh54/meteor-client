// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import org.jetbrains.annotations.Nullable;
import org.bukkit.block.Block;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import kotlin.collections.SetsKt;
import java.util.Set;
import io.fairyproject.libs.xseries.XMaterial;
import kotlin.jvm.internal.Intrinsics;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class IIllIllIIIllIIllIIIlIllIllIIlIllIlIlllIllIIIllIIIlllIlllIlIIIlIllIlIllIlIllIIIlIIllIllIlIllIlllIIIllIIllIlllIIllIIlIllIIIlllIIIllIIllIIllIIllIIIlllIIlIIIlllIIIlllIlllIllIllIIlIlIIIllIlIllIlIIllIllIlllIllIlllIIlIIlllIlll implements IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl
{
    @NotNull
    private final String IIllIIIlllIIllIlllIllIlIllIlllIIIlIIllIIIlIIlIIIlIIlllIIllIlllIIlIIllIIlIIllIIlIllIllIIllIllIlIlIIlIIIllIIllIlIIlIlllIIlllIIIlllIIlIIIllIIIlIIlIIlIIllIIIlllIIIlIIIlllIIlIllIIlIIIllIllIIllIIllIlIlIIIlllIlllIIlIIlllIlIllIIl;
    @NotNull
    private final ItemStack IIIllIllIlllIIllIlIlIIllIIIllIIIllIIIlIIIlllIIlIIIlIlllIllIIIlllIIIllIllIIllIIIlllIIlllIlIllIIllIIIlIIlIIlIlIIIllIlllIIIlIIIlIIIlIIIllIIlllIlllIllIIlIIllIIIlllIlIIlIIIlIIllIllIlIIllIIIllIllIlIIlIIlllIIIllIIIlIIlIlIIlllIllIllIll;
    
    public IIllIllIIIllIIllIIIlIllIllIIlIllIlIlllIllIIIllIIIlllIlllIlIIIlIllIlIllIlIllIIIlIIllIllIlIllIlllIIIllIIllIlllIIllIIlIllIIIlllIIIllIIllIIllIIllIIIlllIIlIIIlllIIIlllIlllIllIllIIlIlIIIllIlIllIlIIllIllIlllIllIlllIIlIIlllIlll(@NotNull final String iIllIIIlllIIllIlllIllIlIllIlllIIIlIIllIIIlIIlIIIlIIlllIIllIlllIIlIIllIIlIIllIIlIllIllIIllIllIlIlIIlIIIllIIllIlIIlIlllIIlllIIIlllIIlIIIllIIIlIIlIIlIIllIIIlllIIIlIIIlllIIlIllIIlIIIllIllIIllIIllIlIlIIIlllIlllIIlIIlllIlIllIIl) {
        Intrinsics.checkNotNullParameter((Object)iIllIIIlllIIllIlllIllIlIllIlllIIIlIIllIIIlIIlIIIlIIlllIIllIlllIIlIIllIIlIIllIIlIllIllIIllIllIlIlIIlIIIllIIllIlIIlIlllIIlllIIIlllIIlIIIllIIIlIIlIIlIIllIIIlllIIIlIIIlllIIlIllIIlIIIllIllIIllIIllIlIlIIIlllIlllIIlIIlllIlIllIIl, "");
        this.IIllIIIlllIIllIlllIllIlIllIlllIIIlIIllIIIlIIlIIIlIIlllIIllIlllIIlIIllIIlIIllIIlIllIllIIllIllIlIlIIlIIIllIIllIlIIlIlllIIlllIIIlllIIlIIIllIIIlIIlIIlIIllIIIlllIIIlIIIlllIIlIllIIlIIIllIllIIllIIllIlIlIIIlllIlllIIlIIlllIlIllIIl = iIllIIIlllIIllIlllIllIlIllIlllIIIlIIllIIIlIIlIIIlIIlllIIllIlllIIlIIllIIlIIllIIlIllIllIIllIllIlIlIIlIIIllIIllIlIIlIlllIIlllIIIlllIIlIIIllIIIlIIlIIlIIllIIIlllIIIlIIIlllIIlIllIIlIIIllIllIIllIIllIlIlIIIlllIlllIIlIIlllIlIllIIl;
        final ItemStack item = XMaterial.STONE.parseItem();
        Intrinsics.checkNotNull((Object)item);
        this.IIIllIllIlllIIllIlIlIIllIIIllIIIllIIIlIIIlllIIlIIIlIlllIllIIIlllIIIllIllIIllIIIlllIIlllIlIllIIllIIIlIIlIIlIlIIIllIlllIIIlIIIlIIIlIIIllIIlllIlllIllIIlIIllIIIlllIlIIlIIIlIIllIllIlIIllIIIllIllIlIIlIIlllIIIllIIIlIIlIlIIlllIllIllIll = item;
    }
    
    @NotNull
    public final String IllIIlIllIlIllIIIllIlIlllIllIllIlIllIIIllIIlllIIIllIllIIllIIllIIllIllIllIIlIIlIIlIIIlllIIIlllIIlIllIllIlllIIlIllIllIIIlIIIllIIlIlIIIlIIIlIIIlllIllIIlllIIIllIIlIllIlIIlIIIllIIIlllIIlIIIlllIIlIlIIIllIIIlIIlIIIlIIIllIllIl() {
        return this.IIllIIIlllIIllIlllIllIlIllIlllIIIlIIllIIIlIIlIIIlIIlllIIllIlllIIlIIllIIlIIllIIlIllIllIIllIllIlIlIIlIIIllIIllIlIIlIlllIIlllIIIlllIIlIIIllIIIlIIlIIlIIllIIIlllIIIlIIIlllIIlIllIIlIIIllIllIIllIIllIlIlIIIlllIlllIIlIIlllIlIllIIl;
    }
    
    @NotNull
    @Override
    public String getName() {
        return "Empty " + this.IIllIIIlllIIllIlllIllIlIllIlllIIIlIIllIIIlIIlIIIlIIlllIIllIlllIIlIIllIIlIIllIIlIllIllIIllIllIlIlIIlIIIllIIllIlIIlIlllIIlllIIIlllIIlIIIllIIIlIIlIIlIIllIIIlllIIIlIIIlllIIlIllIIlIIIllIllIIllIIllIlIlIIIlllIlllIIlIIlllIlIllIIl;
    }
    
    @NotNull
    @Override
    public Set<Integer> getGlobalIds() {
        return SetsKt.emptySet();
    }
    
    @NotNull
    @Override
    public WrappedBlockState getBlockState() {
        final WrappedBlockState byGlobalId = WrappedBlockState.getByGlobalId(1);
        Intrinsics.checkNotNullExpressionValue((Object)byGlobalId, "");
        return byGlobalId;
    }
    
    @NotNull
    @Override
    public ItemStack getDisplayItem() {
        return this.IIIllIllIlllIIllIlIlIIllIIIllIIIllIIIlIIIlllIIlIIIlIlllIllIIIlllIIIllIllIIllIIIlllIIlllIlIllIIllIIIlIIlIIlIlIIIllIlllIIIlIIIlIIIlIIIllIIlllIlllIllIIlIIllIIIlllIlIIlIIIlIIllIllIlIIllIIIllIllIlIIlIIlllIIIllIIIlIIlIlIIlllIllIllIll;
    }
    
    @Override
    public boolean isSimilar(@NotNull final Block block) {
        Intrinsics.checkNotNullParameter((Object)block, "");
        return false;
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        return this == o || (o instanceof IIllIllIIIllIIllIIIlIllIllIIlIllIlIlllIllIIIllIIIlllIlllIlIIIlIllIlIllIlIllIIIlIIllIllIlIllIlllIIIllIIllIlllIIllIIlIllIIIlllIIIllIIllIIllIIllIIIlllIIlIIIlllIIIlllIlllIllIllIIlIlIIIllIlIllIlIIllIllIlllIllIlllIIlIIlllIlll && Intrinsics.areEqual((Object)this.IIllIIIlllIIllIlllIllIlIllIlllIIIlIIllIIIlIIlIIIlIIlllIIllIlllIIlIIllIIlIIllIIlIllIllIIllIllIlIlIIlIIIllIIllIlIIlIlllIIlllIIIlllIIlIIIllIIIlIIlIIlIIllIIIlllIIIlIIIlllIIlIllIIlIIIllIllIIllIIllIlIlIIIlllIlllIIlIIlllIlIllIIl, (Object)((IIllIllIIIllIIllIIIlIllIllIIlIllIlIlllIllIIIllIIIlllIlllIlIIIlIllIlIllIlIllIIIlIIllIllIlIllIlllIIIllIIllIlllIIllIIlIllIIIlllIIIllIIllIIllIIllIIIlllIIlIIIlllIIIlllIlllIllIllIIlIlIIIllIlIllIlIIllIllIlllIllIlllIIlIIlllIlll)o).IIllIIIlllIIllIlllIllIlIllIlllIIIlIIllIIIlIIlIIIlIIlllIIllIlllIIlIIllIIlIIllIIlIllIllIIllIllIlIlIIlIIIllIIllIlIIlIlllIIlllIIIlllIIlIIIllIIIlIIlIIlIIllIIIlllIIIlIIIlllIIlIllIIlIIIllIllIIllIIllIlIlIIIlllIlllIIlIIlllIlIllIIl));
    }
    
    @Override
    public int hashCode() {
        return this.IIllIIIlllIIllIlllIllIlIllIlllIIIlIIllIIIlIIlIIIlIIlllIIllIlllIIlIIllIIlIIllIIlIllIllIIllIllIlIlIIlIIIllIIllIlIIlIlllIIlllIIIlllIIlIIIllIIIlIIlIIlIIllIIIlllIIIlIIIlllIIlIllIIlIIIllIllIIllIIllIlIlIIIlllIlllIIlIIlllIlIllIIl.hashCode();
    }
    
    @Override
    public boolean isSupported() {
        return IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(this);
    }
}
