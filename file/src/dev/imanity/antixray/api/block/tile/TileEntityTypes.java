// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.block.tile;

import kotlin.enums.EnumEntriesKt;
import kotlin.enums.EnumEntries;
import java.util.LinkedHashMap;
import kotlin.ranges.RangesKt;
import kotlin.collections.MapsKt;
import kotlin.text.StringsKt;
import java.util.Iterator;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import java.util.Collection;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.libs.xseries.XMaterial;
import java.util.Set;
import java.util.Map;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J\u001e\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\n2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\u0004H\u0002J\u0010\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00042\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\u000e\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\nR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001b" }, d2 = { "Ldev/imanity/antixray/api/block/tile/TileEntityTypes;", "", "()V", "tileEntityMaterials", "", "Lio/fairyproject/libs/xseries/XMaterial;", "getTileEntityMaterials", "()Ljava/util/Set;", "tileEntityTypeByName", "", "", "Ldev/imanity/antixray/api/block/tile/TileEntityType;", "getTileEntityTypeByName", "()Ljava/util/Map;", "tileEntityTypes", "", "getTileEntityTypes", "()Ljava/util/List;", "materialsEndWith", "name", "suffix", "suffixes", "singleMaterial", "material", "singleMaterials", "materials", "valueOf", "api" })
@SourceDebugExtension({ "SMAP\nTileEntityTypes.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TileEntityTypes.kt\ndev/imanity/antixray/api/block/tile/TileEntityTypes\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,90:1\n1549#2:91\n1620#2,3:92\n766#2:95\n857#2,2:96\n766#2:98\n857#2:99\n1747#2,3:100\n858#2:103\n1194#2,2:104\n1222#2,4:106\n1360#2:110\n1446#2,5:111\n*S KotlinDebug\n*F\n+ 1 TileEntityTypes.kt\ndev/imanity/antixray/api/block/tile/TileEntityTypes\n*L\n79#1:91\n79#1:92,3\n83#1:95\n83#1:96,2\n87#1:98\n87#1:99\n87#1:100,3\n87#1:103\n67#1:104,2\n67#1:106,4\n68#1:110\n68#1:111,5\n*E\n" })
public final class TileEntityTypes
{
    @NotNull
    public static final TileEntityTypes INSTANCE;
    @NotNull
    private static final List<TileEntityType> tileEntityTypes;
    @NotNull
    private static final Map<String, TileEntityType> tileEntityTypeByName;
    @NotNull
    private static final Set<XMaterial> tileEntityMaterials;
    
    private TileEntityTypes() {
    }
    
    @NotNull
    public final List<TileEntityType> getTileEntityTypes() {
        return TileEntityTypes.tileEntityTypes;
    }
    
    @NotNull
    public final Map<String, TileEntityType> getTileEntityTypeByName() {
        return TileEntityTypes.tileEntityTypeByName;
    }
    
    @NotNull
    public final Set<XMaterial> getTileEntityMaterials() {
        return TileEntityTypes.tileEntityMaterials;
    }
    
    @NotNull
    public final TileEntityType valueOf(@NotNull final String str) {
        Intrinsics.checkNotNullParameter((Object)str, "");
        final TileEntityType tileEntityType = TileEntityTypes.tileEntityTypeByName.get(str);
        if (tileEntityType == null) {
            throw new IllegalStateException(("No such TileEntityType: " + str).toString());
        }
        return tileEntityType;
    }
    
    private final TileEntityType singleMaterial(final XMaterial of) {
        return new TileEntityType(of.name(), SetsKt.setOf((Object)of));
    }
    
    private final Set<TileEntityType> singleMaterials(final Set<? extends XMaterial> set) {
        final Iterable iterable;
        final Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable = set, 10));
        final Iterator iterator = iterable.iterator();
        while (iterator.hasNext()) {
            collection.add(TileEntityTypes.INSTANCE.singleMaterial((XMaterial)iterator.next()));
        }
        return CollectionsKt.toSet((Iterable)collection);
    }
    
    private final TileEntityType materialsEndWith(final String s, final String s2) {
        final Iterable iterable = (Iterable)EntriesMappings.entries$0;
        final Collection collection = new ArrayList();
        for (final Object next : iterable) {
            if (StringsKt.endsWith$default(((XMaterial)next).name(), s2, false, 2, (Object)null)) {
                collection.add(next);
            }
        }
        return new TileEntityType(s, CollectionsKt.toSet((Iterable)collection));
    }
    
    private final TileEntityType materialsEndWith(final String s, final Set<String> set) {
        final Iterable iterable = (Iterable)EntriesMappings.entries$0;
        final Collection collection = new ArrayList();
        for (final Object next : iterable) {
            final XMaterial xMaterial = (XMaterial)next;
            final Iterable iterable2 = set;
            boolean b = false;
            Label_0162: {
                if (iterable2 instanceof Collection && ((Collection)iterable2).isEmpty()) {
                    b = false;
                }
                else {
                    final Iterator iterator2 = iterable2.iterator();
                    while (iterator2.hasNext()) {
                        if (StringsKt.endsWith$default(xMaterial.name(), (String)iterator2.next(), false, 2, (Object)null)) {
                            b = true;
                            break Label_0162;
                        }
                    }
                    b = false;
                }
            }
            if (b) {
                collection.add(next);
            }
        }
        return new TileEntityType(s, CollectionsKt.toSet((Iterable)collection));
    }
    
    static {
        INSTANCE = new TileEntityTypes();
        tileEntityTypes = CollectionsKt.flatten((Iterable)SetsKt.setOf((Object[])new Set[] { TileEntityTypes.INSTANCE.singleMaterials(SetsKt.setOf((Object[])new XMaterial[] { XMaterial.BEACON, XMaterial.BREWING_STAND, XMaterial.CHEST, XMaterial.COMMAND_BLOCK, XMaterial.STRUCTURE_BLOCK, XMaterial.COMPARATOR, XMaterial.CONDUIT, XMaterial.DAYLIGHT_DETECTOR, XMaterial.DISPENSER, XMaterial.DROPPER, XMaterial.ENCHANTING_TABLE, XMaterial.END_PORTAL_FRAME, XMaterial.FURNACE, XMaterial.LECTERN, XMaterial.HOPPER, XMaterial.JUKEBOX, XMaterial.NOTE_BLOCK, XMaterial.REPEATER, XMaterial.ENDER_CHEST, XMaterial.TRAPPED_CHEST, XMaterial.BARREL, XMaterial.BELL, XMaterial.JIGSAW, XMaterial.BEEHIVE, XMaterial.SCULK_SENSOR, XMaterial.CALIBRATED_SCULK_SENSOR, XMaterial.SCULK_CATALYST, XMaterial.SCULK_SHRIEKER, XMaterial.CHISELED_BOOKSHELF, XMaterial.DECORATED_POT, XMaterial.SMOKER, XMaterial.BLAST_FURNACE, XMaterial.CRAFTER, XMaterial.BEACON, XMaterial.BREWING_STAND, XMaterial.SPAWNER, XMaterial.TRIAL_SPAWNER, XMaterial.VAULT, XMaterial.PISTON, XMaterial.STICKY_PISTON })), SetsKt.setOf((Object[])new TileEntityType[] { TileEntityTypes.INSTANCE.materialsEndWith("SHULKER_BOX", "SHULKER_BOX"), TileEntityTypes.INSTANCE.materialsEndWith("SIGN", "SIGN"), TileEntityTypes.INSTANCE.materialsEndWith("BANNER", "BANNER"), TileEntityTypes.INSTANCE.materialsEndWith("HEAD", SetsKt.setOf((Object[])new String[] { "SKULL", "HEAD" })), TileEntityTypes.INSTANCE.materialsEndWith("BED", "BED"), TileEntityTypes.INSTANCE.materialsEndWith("COPPER_CHEST", "COPPER_CHEST") }) }));
        final TileEntityTypes instance = TileEntityTypes.INSTANCE;
        final Iterable iterable = TileEntityTypes.tileEntityTypes;
        final int coerceAtLeast = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable, 10)), 16);
        final Iterable iterable2 = iterable;
        final Map tileEntityTypeByName2 = new LinkedHashMap(coerceAtLeast);
        for (final Object next : iterable2) {
            tileEntityTypeByName2.put(((TileEntityType)next).getName(), next);
        }
        tileEntityTypeByName = tileEntityTypeByName2;
        final TileEntityTypes instance2 = TileEntityTypes.INSTANCE;
        final Iterable iterable3 = TileEntityTypes.tileEntityTypes;
        final Collection collection = new ArrayList();
        final Iterator iterator2 = iterable3.iterator();
        while (iterator2.hasNext()) {
            CollectionsKt.addAll(collection, (Iterable)((TileEntityType)iterator2.next()).getMaterials());
        }
        tileEntityMaterials = CollectionsKt.toSet((Iterable)collection);
    }
}
