// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.hook.oraxen;

import org.jetbrains.annotations.Nullable;
import io.th0rgal.oraxen.api.OraxenBlocks;
import org.bukkit.block.Block;
import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.inventory.ItemStack;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import java.util.Set;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.Lazy;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import dev.imanity.antixray.a.IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0096\u0002J\b\u0010\u001d\u001a\u00020\u0011H\u0016J\u0010\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020 H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR!\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017¨\u0006!" }, d2 = { "Ldev/imanity/antixray/hook/oraxen/OraxenBlockMaterial;", "Ldev/imanity/antixray/core/data/type/BlockMaterial;", "oraxenId", "", "(Ljava/lang/String;)V", "blockState", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "getBlockState", "()Lcom/github/retrooper/packetevents/protocol/world/states/WrappedBlockState;", "blockState$delegate", "Lkotlin/Lazy;", "displayItem", "Lorg/bukkit/inventory/ItemStack;", "getDisplayItem", "()Lorg/bukkit/inventory/ItemStack;", "globalIds", "", "", "getGlobalIds", "()Ljava/util/Set;", "globalIds$delegate", "name", "getName", "()Ljava/lang/String;", "getOraxenId", "equals", "", "other", "", "hashCode", "isSimilar", "block", "Lorg/bukkit/block/Block;", "plugin" })
public final class OraxenBlockMaterial implements IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl
{
    @NotNull
    private final String oraxenId;
    @NotNull
    private final Lazy globalIds$delegate;
    @NotNull
    private final Lazy blockState$delegate;
    
    public OraxenBlockMaterial(@NotNull final String oraxenId) {
        Intrinsics.checkNotNullParameter((Object)oraxenId, "");
        this.oraxenId = oraxenId;
        this.globalIds$delegate = LazyKt.lazy((Function0)new OraxenBlockMaterial$globalIds.OraxenBlockMaterial$globalIds$2(this));
        this.blockState$delegate = LazyKt.lazy((Function0)new OraxenBlockMaterial$blockState.OraxenBlockMaterial$blockState$2(this));
    }
    
    @NotNull
    public final String getOraxenId() {
        return this.oraxenId;
    }
    
    @NotNull
    @Override
    public String getName() {
        return "Oraxen " + this.oraxenId;
    }
    
    @NotNull
    @Override
    public Set<Integer> getGlobalIds() {
        return (Set)this.globalIds$delegate.getValue();
    }
    
    @NotNull
    @Override
    public WrappedBlockState getBlockState() {
        final Object value = this.blockState$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "");
        return (WrappedBlockState)value;
    }
    
    @NotNull
    @Override
    public ItemStack getDisplayItem() {
        final ItemStack build = OraxenItems.getItemById(this.oraxenId).build();
        Intrinsics.checkNotNullExpressionValue((Object)build, "");
        return build;
    }
    
    @Override
    public boolean isSimilar(@NotNull final Block block) {
        Intrinsics.checkNotNullParameter((Object)block, "");
        return Intrinsics.areEqual((Object)OraxenBlocks.getOraxenBlock(block.getLocation()).getItemID(), (Object)this.oraxenId);
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        return this == o || (o instanceof OraxenBlockMaterial && Intrinsics.areEqual((Object)this.oraxenId, (Object)((OraxenBlockMaterial)o).oraxenId));
    }
    
    @Override
    public int hashCode() {
        return this.oraxenId.hashCode();
    }
    
    @Override
    public boolean isSupported() {
        return IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(this);
    }
}
