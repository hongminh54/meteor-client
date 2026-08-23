// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.hook.itemsadder;

import org.jetbrains.annotations.Nullable;
import org.bukkit.block.Block;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import java.util.Set;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.bukkit.inventory.ItemStack;
import kotlin.Lazy;
import org.jetbrains.annotations.NotNull;
import dev.lone.itemsadder.api.CustomBlock;
import kotlin.Metadata;
import dev.imanity.antixray.a.IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0096\u0002J\b\u0010\u001f\u001a\u00020\u0013H\u0016J\u0010\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R!\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\n\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006#" }, d2 = { "Ldev/imanity/antixray/hook/itemsadder/ItemsAdderBlockMaterial;", "Ldev/imanity/antixray/core/data/type/BlockMaterial;", "customBlock", "Ldev/lone/itemsadder/api/CustomBlock;", "(Ldev/lone/itemsadder/api/CustomBlock;)V", "blockState", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "getBlockState", "()Lcom/github/retrooper/packetevents/protocol/world/states/WrappedBlockState;", "blockState$delegate", "Lkotlin/Lazy;", "getCustomBlock", "()Ldev/lone/itemsadder/api/CustomBlock;", "displayItem", "Lorg/bukkit/inventory/ItemStack;", "getDisplayItem", "()Lorg/bukkit/inventory/ItemStack;", "globalIds", "", "", "getGlobalIds", "()Ljava/util/Set;", "globalIds$delegate", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "isSimilar", "block", "Lorg/bukkit/block/Block;", "plugin" })
public final class ItemsAdderBlockMaterial implements IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl
{
    @NotNull
    private final CustomBlock customBlock;
    @NotNull
    private final Lazy globalIds$delegate;
    @NotNull
    private final Lazy blockState$delegate;
    @NotNull
    private final ItemStack displayItem;
    
    public ItemsAdderBlockMaterial(@NotNull final CustomBlock customBlock) {
        Intrinsics.checkNotNullParameter((Object)customBlock, "");
        this.customBlock = customBlock;
        this.globalIds$delegate = LazyKt.lazy((Function0)new ItemsAdderBlockMaterial$globalIds.ItemsAdderBlockMaterial$globalIds$2(this));
        this.blockState$delegate = LazyKt.lazy((Function0)new ItemsAdderBlockMaterial$blockState.ItemsAdderBlockMaterial$blockState$2(this));
        final ItemStack itemStack = this.customBlock.getItemStack();
        Intrinsics.checkNotNullExpressionValue((Object)itemStack, "");
        this.displayItem = itemStack;
    }
    
    @NotNull
    public final CustomBlock getCustomBlock() {
        return this.customBlock;
    }
    
    @NotNull
    @Override
    public String getName() {
        return "ItemsAdder " + this.customBlock.getDisplayName();
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
        return this.displayItem;
    }
    
    @Override
    public boolean isSimilar(@NotNull final Block block) {
        Intrinsics.checkNotNullParameter((Object)block, "");
        return Intrinsics.areEqual((Object)CustomBlock.byAlreadyPlaced(block), (Object)this.customBlock);
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        return this == o || (o instanceof ItemsAdderBlockMaterial && Intrinsics.areEqual((Object)this.customBlock, (Object)((ItemsAdderBlockMaterial)o).customBlock));
    }
    
    @Override
    public int hashCode() {
        return this.customBlock.hashCode();
    }
    
    @Override
    public boolean isSupported() {
        return IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(this);
    }
}
