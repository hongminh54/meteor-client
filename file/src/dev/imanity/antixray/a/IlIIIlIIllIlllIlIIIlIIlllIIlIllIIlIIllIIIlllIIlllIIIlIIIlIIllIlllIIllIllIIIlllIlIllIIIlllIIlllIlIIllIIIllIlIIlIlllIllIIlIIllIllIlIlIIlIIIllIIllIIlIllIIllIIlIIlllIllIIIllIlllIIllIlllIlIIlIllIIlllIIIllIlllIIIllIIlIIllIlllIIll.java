// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import java.util.Iterator;
import dev.imanity.antixray.api.block.tile.TileEntityTypes;
import io.fairyproject.log.Log;
import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import org.bukkit.Material;
import kotlin.collections.SetsKt;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.libs.xseries.XMaterial;
import java.util.Set;
import kotlin.jvm.internal.SourceDebugExtension;
import io.fairyproject.container.InjectableComponent;

@InjectableComponent
@SourceDebugExtension({ "SMAP\nBlockBlacklistService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BlockBlacklistService.kt\ndev/imanity/antixray/core/block/BlockBlacklistService\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,59:1\n1747#2,3:60\n1747#2,3:63\n*S KotlinDebug\n*F\n+ 1 BlockBlacklistService.kt\ndev/imanity/antixray/core/block/BlockBlacklistService\n*L\n22#1:60,3\n27#1:63,3\n*E\n" })
public final class IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll
{
    @NotNull
    private final Set<XMaterial> IIlIIIlIlIIlIIlllIIlIIlIllIIllIIIllIllIIlIllIlllIIIlllIlIIlIIIlIllIlIIIlIIIllIIIllIllIIIlIlIlIIIllIIIllIllIllIIlIllIIIllIIlIIIllIIIllIllIIIlIIlllIIlIIlllIllIllIIlIIlIIlIllIllIlllIIIllIIlIIIlllIllIIIlllIIlllIIIllIIIlllIIIllIll;
    
    public IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll() {
        this.IIlIIIlIlIIlIIlllIIlIIlIllIIllIIIllIllIIlIllIlllIIIlllIlIIlIIIlIllIlIIIlIIIllIIIllIllIIIlIlIlIIIllIIIllIllIllIIlIllIIIllIIlIIIllIIIllIllIIIlIIlllIIlIIlllIllIllIIlIIlIIlIllIllIlllIIIllIIlIIIlllIllIIIlllIIlllIIIllIIIlllIIIllIll = SetsKt.setOf((Object[])new XMaterial[] { XMaterial.NETHER_WART, XMaterial.NETHER_WART_BLOCK });
    }
    
    public final boolean IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Material material) {
        Intrinsics.checkNotNullParameter((Object)material, "");
        if (XMaterial.AIR.parseMaterial() == material || XMaterial.CAVE_AIR.parseMaterial() == material || XMaterial.VOID_AIR.parseMaterial() == material) {
            return false;
        }
        if (!material.isBlock()) {
            final Iterable iterable = this.IIlIIIlIlIIlIIlllIIlIIlIllIIllIIIllIllIIlIllIlllIIIlllIlIIlIIIlIllIlIIIlIIIllIIIllIllIIIlIlIlIIIllIIIllIllIllIIlIllIIIllIIlIIIllIIIllIllIIIlIIlllIIlIIlllIllIllIIlIIlIIlIllIllIlllIIIllIIlIIIlllIllIIIlllIIlllIIIllIIIlllIIIllIll;
            boolean b = false;
            Label_0137: {
                if (iterable instanceof Collection && ((Collection)iterable).isEmpty()) {
                    b = false;
                }
                else {
                    final Iterator iterator = iterable.iterator();
                    while (iterator.hasNext()) {
                        if (((XMaterial)iterator.next()).parseMaterial() == material) {
                            b = true;
                            break Label_0137;
                        }
                    }
                    b = false;
                }
            }
            if (!b) {
                Log.warn("Material " + material + " is not a block.", new Object[0]);
                return false;
            }
        }
        final Iterable iterable2 = TileEntityTypes.INSTANCE.getTileEntityMaterials();
        boolean b2 = false;
        Label_0267: {
            if (iterable2 instanceof Collection && ((Collection)iterable2).isEmpty()) {
                b2 = false;
            }
            else {
                final Iterator iterator2 = iterable2.iterator();
                while (iterator2.hasNext()) {
                    if (((XMaterial)iterator2.next()).parseMaterial() == material) {
                        b2 = true;
                        break Label_0267;
                    }
                }
                b2 = false;
            }
        }
        if (b2) {
            Log.warn("Material " + material + " is a tile entity. You need TileEntityCulling addon to use this material.", new Object[0]);
            return false;
        }
        return true;
    }
    
    public final boolean IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final XMaterial xMaterial, final boolean b) {
        Intrinsics.checkNotNullParameter((Object)xMaterial, "");
        if (!xMaterial.isSupported()) {
            return false;
        }
        if (!b && (xMaterial == XMaterial.AIR || xMaterial == XMaterial.CAVE_AIR || xMaterial == XMaterial.VOID_AIR)) {
            return false;
        }
        final Material material = xMaterial.parseMaterial();
        if (material == null) {
            return false;
        }
        if (!material.isBlock() && !this.IIlIIIlIlIIlIIlllIIlIIlIllIIllIIIllIllIIlIllIlllIIIlllIlIIlIIIlIllIlIIIlIIIllIIIllIllIIIlIlIlIIIllIIIllIllIllIIlIllIIIllIIlIIIllIIIllIllIIIlIIlllIIlIIlllIllIllIIlIIlIIlIllIllIlllIIIllIIlIIIlllIllIIIlllIIlllIIIllIIIlllIIIllIll.contains(xMaterial)) {
            Log.warn("Material " + xMaterial + " is not a block.", new Object[0]);
            return false;
        }
        if (TileEntityTypes.INSTANCE.getTileEntityMaterials().contains(xMaterial)) {
            Log.warn("Material " + xMaterial + " is a tile entity. You need TileEntityCulling addon to use this material.", new Object[0]);
            return false;
        }
        return true;
    }
}
