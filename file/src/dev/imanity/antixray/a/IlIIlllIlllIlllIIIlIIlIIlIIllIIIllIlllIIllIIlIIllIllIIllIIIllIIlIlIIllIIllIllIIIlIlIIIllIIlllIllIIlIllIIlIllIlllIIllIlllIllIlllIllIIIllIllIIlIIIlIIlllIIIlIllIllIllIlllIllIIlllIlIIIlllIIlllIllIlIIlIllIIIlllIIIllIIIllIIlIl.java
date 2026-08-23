// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import io.fairyproject.container.Containers;
import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.libs.xseries.XMaterial;
import kotlin.Lazy;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001a" }, d2 = { "Ldev/imanity/antixray/core/data/type/BlockMaterial;", "", "blockState", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "getBlockState", "()Lcom/github/retrooper/packetevents/protocol/world/states/WrappedBlockState;", "displayItem", "Lorg/bukkit/inventory/ItemStack;", "getDisplayItem", "()Lorg/bukkit/inventory/ItemStack;", "globalIds", "", "", "getGlobalIds", "()Ljava/util/Set;", "isSupported", "", "()Z", "name", "", "getName", "()Ljava/lang/String;", "isSimilar", "block", "Lorg/bukkit/block/Block;", "Companion", "plugin" })
public interface IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl
{
    @NotNull
    public static final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll IllIIllIlllIlllIllIIlllIIllIIlIIlllIIlIIlIIIllIlllIllIIIllIllIIlllIIlIIlIIlIIlllIIIlIlIIlllIlllIllIIllIIlllIIIlllIIlllIIlIllIlllIlllIIllIIIllIIllIIlIIIllIIIlllIlllIIlIIlIlllIIIllIIIllIIIlllIlIIIlllIIllIIllIIlllIIIlIIlIllIIIlIlIIllIlIll = IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.IIlIIlIIllIlllIIlIlIllIIlIIllIIllIIIlIIlllIllIlIllIlIllIIllIIlIIllIIlIlIIIlllIIIlIIllIlIIllIIllIllIIllIllIlIlllIlIllIIIlllIIIlIIIlllIllIIIlIIIllIlllIIllIIlllIllIIlIIllIIlIIlIIIlIIllIIlIIllIIlIIlllIIlIIlllIIIllIIllIIl;
    
    @NotNull
    String getName();
    
    @NotNull
    Set<Integer> getGlobalIds();
    
    @NotNull
    WrappedBlockState getBlockState();
    
    @NotNull
    ItemStack getDisplayItem();
    
    boolean isSupported();
    
    boolean isSimilar(@NotNull final Block p0);
    
    public static final class IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll
    {
        public static boolean IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl ilIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl) {
            return true;
        }
    }
    
    public static final class IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll
    {
        static final /* synthetic */ IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll IIlIIlIIllIlllIIlIlIllIIlIIllIIllIIIlIIlllIllIlIllIlIllIIllIIlIIllIIlIlIIIlllIIIlIIllIlIIllIIllIllIIllIllIlIlllIlIllIIIlllIIIlIIIlllIllIIIlIIIllIlllIIllIIlllIllIIlIIllIIlIIlIIIlIIllIIlIIllIIlIIlllIIlIIlllIIIllIIllIIl;
        @NotNull
        private static final Lazy<IIlIlllIIIllIIlllIIlIIllIIlIlIIlIIlIIlllIIlllIIIllIIlllIIllIIllIIlIIlIIlIIIlIIlllIIlllIlIIIlIllIIIlllIlIIIlIlIIIlllIIlIllIIllIIIlIIlllIIllIIIlIIllIIllIIlllIIllIIllIIllIIIllIIIllIIIlIIllIlllIlllIllIIlllIlIlllIIlIIlIlllIIlIIIllIIllIIl> IIIlIIlIIlIIlIIlIlIIllIIlllIIIllIIIllIIllIIIllIIllIIlllIIIllIIllIIlIllIIlllIIlIIllIIIllIIIlllIlllIllIIlIllIIIlllIIIlIIllIllIIlIIlllIIllIIIllIIllIlIIllIllIIIlIIIllIIllIIllIIIlllIIIllIlIIIlIlIlIIIllIIlIIlllIIlllIIllIIllIllIIlllIIIllIIlllIIlll;
        
        private IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll() {
        }
        
        @NotNull
        public final IIlIlllIIIllIIlllIIlIIllIIlIlIIlIIlIIlllIIlllIIIllIIlllIIllIIllIIlIIlIIlIIIlIIlllIIlllIlIIIlIllIIIlllIlIIIlIlIIIlllIIlIllIIllIIIlIIlllIIllIIIlIIllIIllIIlllIIllIIllIIllIIIllIIIllIIIlIIllIlllIlllIllIIlllIlIlllIIlIIlIlllIIlIIIllIIllIIl IllIllIIllIIlllIlllIIIlIIllIlIIIlIlllIlIllIIIllIlIlIlllIllIlllIIllIIlIIllIIllIIlIIlIIlIIllIllIIllIIlIIllIIlllIIllIIIlIIlIllIIIllIllIlIIIllIIllIIlllIlllIIllIIIlIllIlIllIIlllIIIllIlllIIllIllIlIlllIIlllIllIIlIIIllIllIIIl() {
            return (IIlIlllIIIllIIlllIIlIIllIIlIlIIlIIlIIlllIIlllIIIllIIlllIIllIIllIIlIIlIIlIIIlIIlllIIlllIlIIIlIllIIIlllIlIIIlIlIIIlllIIlIllIIllIIIlIIlllIIllIIIlIIllIIllIIlllIIllIIllIIllIIIllIIIllIIIlIIllIlllIlllIllIIlllIlIlllIIlIIlIlllIIlIIIllIIllIIl)IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.IIIlIIlIIlIIlIIlIlIIllIIlllIIIllIIIllIIllIIIllIIllIIlllIIIllIIllIIlIllIIlllIIlIIllIIIllIIIlllIlllIllIIlIllIIIlllIIIlIIllIllIIlIIlllIIllIIIllIIllIlIIllIllIIIlIIIllIIllIIllIIIlllIIIllIlIIIlIlIlIIIllIIlIIlllIIlllIIllIIllIllIIlllIIIllIIlllIIlll.getValue();
        }
        
        @NotNull
        public final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final XMaterial xMaterial) {
            Intrinsics.checkNotNullParameter((Object)xMaterial, "");
            final Object value = Containers.get((Class)IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll.class);
            Intrinsics.checkNotNullExpressionValue(value, "");
            return new IIlIlllIIIllIIlllIIlIIllIIlIlIIlIIlIIlllIIlllIIIllIIlllIIllIIllIIlIIlIIlIIIlIIlllIIlllIlIIIlIllIIIlllIlIIIlIlIIIlllIIlIllIIllIIIlIIlllIIllIIIlIIllIIllIIlllIIllIIllIIllIIIllIIIllIIIlIIllIlllIlllIllIIlllIlIlllIIlIIlIlllIIlIIIllIIllIIl(xMaterial, (IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll)value);
        }
        
        static {
            IIlIIlIIllIlllIIlIlIllIIlIIllIIllIIIlIIlllIllIlIllIlIllIIllIIlIIllIIlIlIIIlllIIIlIIllIlIIllIIllIllIIllIllIlIlllIlIllIIIlllIIIlIIIlllIllIIIlIIIllIlllIIllIIlllIllIIlIIllIIlIIlIIIlIIllIIlIIllIIlIIlllIIlIIlllIIIllIIllIIl = new IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll();
            IIIlIIlIIlIIlIIlIlIIllIIlllIIIllIIIllIIllIIIllIIllIIlllIIIllIIllIIlIllIIlllIIlIIllIIIllIIIlllIlllIllIIlIllIIIlllIIIlIIllIllIIlIIlllIIllIIIllIIllIlIIllIllIIIlIIIllIIllIIllIIIlllIIIllIlIIIlIlIlIIIllIIlIIlllIIlllIIllIIllIllIIlllIIIllIIlllIIlll = LazyKt.lazy((Function0)IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl$IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll$1.IllIIIlllIllIIIllIIIlIlIIIlllIlIIlllIIllIIlllIIlllIllIIllIIIlIIlllIIIlIIllIllIlllIIIllIIlIllIllIlIIllIlllIllIIllIIIlIllIIlIIllIIIlIllIIlIllIIIllIIlIlIIIllIIlIIlIIllIIIlIIlllIlllIIlIIllIIIlIllIIlIIlIIlIIlllIIIllIIllIIlIIIllIIl);
        }
    }
}
