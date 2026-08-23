// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import kotlin.jvm.internal.DefaultConstructorMarker;
import io.fairyproject.libs.packetevents.protocol.world.states.type.StateType;
import dev.imanity.antixray.nms.Native;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.collections.SetsKt;
import kotlin.collections.CollectionsKt;
import dev.imanity.antixray.internal.NewBlockMaterialData;
import dev.imanity.antixray.internal.OldBlockMaterialData;
import dev.imanity.antixray.internal.BukkitBlockMaterialData;
import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.bukkit.nms.BukkitNMSManager;
import org.bukkit.Material;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import io.fairyproject.libs.xseries.XMaterial;
import java.util.Set;
import dev.imanity.antixray.internal.BlockMaterialData;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import io.fairyproject.container.InjectableComponent;
import dev.imanity.antixray.api.data.IMaterialDataHandler;

@InjectableComponent
@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0007\b\u0007\u0018\u0000 (2\u00020\u0001:\u0001(B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0015H\u0016J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\nH\u0016J\u001c\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00190\u00062\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00150\"H\u0016J\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00190\t2\u0006\u0010\u001f\u001a\u00020\u0015H\u0016J\u001c\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00190\t2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00150\"H\u0016J\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00190\t2\u0006\u0010\u001f\u001a\u00020\nH\u0016J\u0010\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u001eH\u0016J\u0010\u0010$\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\nH\u0016J\u0010\u0010&\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u001eH\u0016J\u0010\u0010&\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\nH\u0016J\u0010\u0010'\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u001eH\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u000b\u001a\r\u0012\t\u0012\u00070\n¢\u0006\u0002\b\f0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)" }, d2 = { "Ldev/imanity/antixray/core/data/MaterialDataHandler;", "Ldev/imanity/antixray/api/data/IMaterialDataHandler;", "bukkitNMSManager", "Lio/fairyproject/bukkit/nms/BukkitNMSManager;", "(Lio/fairyproject/bukkit/nms/BukkitNMSManager;)V", "blockMaterialDataList", "", "Ldev/imanity/antixray/internal/BlockMaterialData;", "bukkitLavaMaterials", "", "Lorg/bukkit/Material;", "bukkitTransparentMaterials", "Lorg/jetbrains/annotations/Nullable;", "cullingTransparentCache", "", "globalIdTransparentMaterials", "Lit/unimi/dsi/fastutil/ints/IntOpenHashSet;", "lavaCache", "lavaGlobalIds", "transparentCache", "transparentMaterials", "Lio/fairyproject/libs/xseries/XMaterial;", "cacheCullingTransparency", "", "globalId", "", "transparent", "", "cullingTransparencyCached", "decode", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "material", "decodeToIds", "materials", "", "decodeToPossibleIds", "isLava", "wrappedBlockState", "isTransparent", "slowIsTransparent", "Companion", "plugin" })
@SourceDebugExtension({ "SMAP\nMaterialDataHandler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MaterialDataHandler.kt\ndev/imanity/antixray/core/data/MaterialDataHandler\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,170:1\n766#2:171\n857#2,2:172\n1360#2:174\n1446#2,5:175\n1549#2:180\n1620#2,3:181\n766#2:184\n857#2,2:185\n1549#2:187\n1620#2,3:188\n1549#2:191\n1620#2,3:192\n766#2:195\n857#2,2:196\n1549#2:198\n1620#2,3:199\n1360#2:202\n1446#2,5:203\n*S KotlinDebug\n*F\n+ 1 MaterialDataHandler.kt\ndev/imanity/antixray/core/data/MaterialDataHandler\n*L\n36#1:171\n36#1:172,2\n43#1:174\n43#1:175,5\n45#1:180\n45#1:181,3\n89#1:184\n89#1:185,2\n89#1:187\n89#1:188,3\n89#1:191\n89#1:192,3\n111#1:195\n111#1:196,2\n112#1:198\n112#1:199,3\n113#1:202\n113#1:203,5\n*E\n" })
public final class IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll implements IMaterialDataHandler
{
    @NotNull
    private static final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll IlIIIlllIIIlIIllIIllIIlIIlIIllIIIlIllIIlllIIIlllIIlIllIIlIIlIllIIlIIllIIllIllIIlIIIllIIllIlIIlllIIIllIlIIllIlllIIIlIIllIIlIllIlllIIllIIlIllIllIlIllIIIllIIllIIIlllIIIlllIlllIlIllIlIlllIIllIllIlllIlllIIlIlIIllIlIIIlIIIlll;
    @NotNull
    private final List<BlockMaterialData> IllIIIlllIIllIIllIIIllIlIIIlIIIllIIIllIIIlIIIlIIIlIllIllIIIllIIIlIIIlllIlIIllIIIlIIllIllIlllIIllIIIllIlIIllIIIllIlIIlIlllIIIllIllIllIllIIlIIlllIIIllIIlllIIllIlIIIlllIllIIlIIlIIlIllIIIllIIlllIIllIIIllIIIllIIlIIllIlIIlIIlIIllIIllIll;
    @NotNull
    private final Set<XMaterial> IIllIIlIIIlllIIIlIlIIllIIllIlIlllIlIIllIlllIIlIlllIIIlIIIlIIlIIllIlllIllIIIllIllIllIIlIIlIllIllIIIlllIIlllIIlllIIllIllIIlIIIlIIllIIlIIlllIIllIIllIllIIIlIllIIIllIlIllIIlllIIllIIllIIllIIIllIlIIIlllIIlIIllIlllIIllIIllIlllIIlIIll;
    @NotNull
    private final IntOpenHashSet IlIIlIIIllIlllIllIlIIIllIIIllIllIIlIlllIlllIIlIIlIIIlllIIIllIIlIlllIlllIIllIlllIIllIlIllIlIlIlllIllIllIIlIIllIIlIlllIIlIIIllIIIllIIIlIIllIIllIIIllIlllIllIIllIlllIIlllIIlIIIlIIlllIllIIllIIlIIlIIlIIIlIIIlllIIIllIIlllIIIllIllIIIlll;
    @NotNull
    private final Set<Material> IIlllIIIllIIIlIIlIIlIIllIIIllIIllIlllIlllIlllIIlllIIlllIIlllIlIIlIlIIIlIlIIIlIIIlIlllIIIlIIllIIIllIIlIIIllIllIIlllIIllIIlllIIllIIllIllIlllIIllIIllIllIIIlIIlllIIllIIllIIlIIllIlIIllIIllIlIIIllIIlllIIlIlIIllIIllIIlllIllIlIlIIlllIll;
    @NotNull
    private final IntOpenHashSet IllIlIIlllIIlIIlIIlIlIIIlllIIIllIlIIllIIlllIIIlIIllIIIllIIIllIIlIIlllIllIllIIllIlllIIIllIIllIIllIlllIlllIllIIlIIlIIllIIllIIIllIIIlIIIlIIllIIIllIIlIlllIllIIlllIIIllIIllIlIllIIIlllIllIIlIlIllIlIIIlIIIlIIlIIIlIIIlIllIIllIIIlllIl;
    @NotNull
    private final Set<Material> IllIIllIIllIIllIllIIlIllIIIlllIIIllIIIlllIllIlIIlllIllIllIIIlIIllIIlIIIllIIlIlIIlllIlIIlIIlllIlllIIIlIIllIIllIIlIIIlIllIllIIlllIIIlllIIllIIlIIIlllIIIlIllIllIIIllIIlIIllIIllIIlIIlllIIllIllIIllIlIIlllIllIIlllIIlIIIllIIllIIlIIIllIll;
    @NotNull
    private final byte[] IIllIllIIIllIIlllIllIIllIlllIIIllIllIIIlllIllIIllIllIlIIlllIIlIIllIllIIllIIIlIIllIIllIllIlllIIIlllIIllIlIIllIIIllIlIllIIIllIlIIIlIIIlIIlllIlIIllIllIIlllIIIlIIIllIIlIIIlllIIlIIllIIllIIIlIIlIlIIIllIllIIllIIlIllIlIlllIIllIIIlIl;
    @NotNull
    private final byte[] IIllIIllIllIIllIIIlllIIllIIllIIlIlllIlllIlllIIIllIIIllIIlIIlIIlllIIlIIIlIIIlllIllIIllIIllIIllIIlIIlllIlIIIllIIlIlIllIIlIIIllIllIllIIllIIIlIIIllIIllIIlIIIlllIIIlIlIIIlIllIIIlIIllIIlllIIIllIIIlllIIIllIlIIlllIlIIIlllIIlllIIllIIIllIIlllIIllIll;
    @NotNull
    private final byte[] IIIllIIllIIIlIllIIllIIIlIIllIlIIlIIlIIIlIIIllIlllIlIIllIIIllIIllIIIlllIIllIIIllIllIllIllIIllIIllIIlIIIlllIIIlllIllIlIIlllIIIllIIIllIIlIIlllIIlIllIllIlIIlllIIllIIlIIIllIIlIllIllIIIlIIlIIlllIlIllIlIllIIlllIIllIIlIIIlllIIlllIIIllIIl;
    private static final int IllIIlIIllIIlIlllIlIlIlllIIIlIIlllIIIlIllIIIlIllIIIllIIlIIIllIIIllIIIlllIlllIllIllIllIIIlIIIllIlIlllIIllIlllIIIllIlIIlIIlIlIlIIllIllIlllIIllIlIIlIIIlllIIllIIIlIIIllIIIlllIIIllIlllIIlIlIIlIIllIIIlllIllIlIIIlIlIIlIIIlllIIIlll = 65536;
    private static final int IIllIIIlllIIllIIlllIIIllIIIllIlllIlIllIllIIlIIllIIlIIllIIIlIlIIlllIIlllIIIllIIllIIllIlIIlIlIllIIlllIIIllIllIIllIlllIllIllIlllIIllIIIllIllIllIllIIIlllIIllIllIIlllIlIlIlllIIlIlIIllIlIIllIlllIIlIIllIIIlIIlIIllIlIIlIllIIIl = 0;
    private static final int IIlIllIIlIIlllIlIlllIIIllIlIIlIIllIIllIlIIIlIllIIlllIIllIlllIllIIllIlIIllIIIlllIllIllIlIlllIIIlllIIIlIIIlIllIlllIIlIIlIIllIIlIIIllIllIIlIIlllIllIIlIllIIlIIllIlIIIlllIIIlIIlIlIlllIIIllIIlIlIllIIIlIIllIlllIIIllIIllIll = 1;
    private static final int IIllIIlllIIllIIIlllIlIIlIlllIIllIIllIIlIIIlllIIlllIllIIlIIlIlIIlIIIllIIlllIIIllIIlllIIllIIlIlllIlllIIIlIllIlIIlIIIllIIIllIIllIllIIllIllIIIllIIllIIIlllIIlIIlllIlllIIlIIlIIlllIIllIllIIlllIllIIIlIllIIlllIIIllIIIlllIIlIIlIIIlIllIllIllIl = 2;
    
    public IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll(@NotNull final BukkitNMSManager bukkitNMSManager) {
        Intrinsics.checkNotNullParameter((Object)bukkitNMSManager, "");
        this.IllIIIlllIIllIIllIIIllIlIIIlIIIllIIIllIIIlIIIlIIIlIllIllIIIllIIIlIIIlllIlIIllIIIlIIllIllIlllIIllIIIllIlIIllIIIllIlIIlIlllIIIllIllIllIllIIlIIlllIIIllIIlllIIllIlIIIlllIllIIlIIlIIlIllIIIllIIlllIIllIIIllIIIllIIlIIllIlIIlIIlIIllIIllIll = CollectionsKt.listOf((Object[])new BlockMaterialData[] { new BukkitBlockMaterialData(), new OldBlockMaterialData(bukkitNMSManager), new NewBlockMaterialData(bukkitNMSManager) });
        final Iterable iterable = SetsKt.setOf((Object[])new XMaterial[] { XMaterial.SPAWNER, XMaterial.SHULKER_BOX, XMaterial.BARRIER, XMaterial.SLIME_BLOCK, XMaterial.HONEY_BLOCK, XMaterial.MANGROVE_ROOTS, XMaterial.LADDER, XMaterial.LAVA });
        final Collection collection = new ArrayList();
        for (final Object next : iterable) {
            if (((XMaterial)next).isSupported()) {
                collection.add(next);
            }
        }
        this.IIllIIlIIIlllIIIlIlIIllIIllIlIlllIlIIllIlllIIlIlllIIIlIIIlIIlIIllIlllIllIIIllIllIllIIlIIlIllIllIIIlllIIlllIIlllIIllIllIIlIIIlIIllIIlIIlllIIllIIllIllIIIlIllIIIllIlIllIIlllIIllIIllIIllIIIllIlIIIlllIIlIIllIlllIIllIIllIlllIIlIIll = CollectionsKt.toSet((Iterable)collection);
        final Iterable iterable2 = this.IIllIIlIIIlllIIIlIlIIllIIllIlIlllIlIIllIlllIIlIlllIIIlIIIlIIlIIllIlllIllIIIllIllIllIIlIIlIllIllIIIlllIIlllIIlllIIllIllIIlIIIlIIllIIlIIlllIIllIIllIllIIIlIllIIIllIlIllIIlllIIllIIllIIllIIIllIlIIIlllIIlIIllIlllIIllIIllIlllIIlIIll;
        final Collection collection2 = new ArrayList();
        final Iterator iterator2 = iterable2.iterator();
        while (iterator2.hasNext()) {
            CollectionsKt.addAll(collection2, (Iterable)this.decodeToPossibleIds((XMaterial)iterator2.next()));
        }
        this.IlIIlIIIllIlllIllIlIIIllIIIllIllIIlIlllIlllIIlIIlIIIlllIIIllIIlIlllIlllIIllIlllIIllIlIllIlIlIlllIllIllIIlIIllIIlIlllIIlIIIllIIIllIIIlIIllIIllIIIllIlllIllIIllIlllIIlllIIlIIIlIIlllIllIIllIIlIIlIIlIIIlIIIlllIIIllIIlllIIIllIllIIIlll = (IntOpenHashSet)CollectionsKt.toCollection((Iterable)collection2, (Collection)new IntOpenHashSet());
        final Iterable iterable3;
        final Collection collection3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3 = this.IIllIIlIIIlllIIIlIlIIllIIllIlIlllIlIIllIlllIIlIlllIIIlIIIlIIlIIllIlllIllIIIllIllIllIIlIIlIllIllIIIlllIIlllIIlllIIllIllIIlIIIlIIllIIlIIlllIIllIIllIllIIIlIllIIIllIlIllIIlllIIllIIllIIllIIIllIlIIIlllIIlIIllIlllIIllIIllIlllIIlIIll, 10));
        for (final Object next2 : iterable3) {
            final Collection collection4 = collection3;
            final XMaterial xMaterial = (XMaterial)next2;
            final Collection collection5 = collection4;
            final Material material = xMaterial.parseMaterial();
            Intrinsics.checkNotNull((Object)material);
            collection5.add(material);
        }
        this.IIlllIIIllIIIlIIlIIlIIllIIIllIIllIlllIlllIlllIIlllIIlllIIlllIlIIlIlIIIlIlIIIlIIIlIlllIIIlIIllIIIllIIlIIIllIllIIlllIIllIIlllIIllIIllIllIlllIIllIIllIllIIIlIIlllIIllIIllIIlIIllIlIIllIIllIlIIIllIIlllIIlIlIIllIIllIIlllIllIlIlIIlllIll = CollectionsKt.toSet((Iterable)collection3);
        this.IllIlIIlllIIlIIlIIlIlIIIlllIIIllIlIIllIIlllIIIlIIllIIIllIIIllIIlIIlllIllIllIIllIlllIIIllIIllIIllIlllIlllIllIIlIIlIIllIIllIIIllIIIlIIIlIIllIIIllIIlIlllIllIIlllIIIllIIllIlIllIIIlllIllIIlIlIllIlIIIlIIIlIIlIIIlIIIlIllIIllIIIlllIl = (IntOpenHashSet)CollectionsKt.toCollection((Iterable)this.decodeToPossibleIds(XMaterial.LAVA), (Collection)new IntOpenHashSet());
        Set illIIllIIllIIllIllIIlIllIIIlllIIIllIIIlllIllIlIIlllIllIllIIIlIIllIIlIIIllIIlIlIIlllIlIIlIIlllIlllIIIlIIllIIllIIlIIIlIllIllIIlllIIIlllIIllIIlIIIlllIIIlIllIllIIIllIIlIIllIIllIIlIIlllIIllIllIIllIlIIlllIllIIlllIIlIIIllIIllIIlIIIllIll;
        if (XMaterial.LAVA.isSupported()) {
            final Material material2 = XMaterial.LAVA.parseMaterial();
            Intrinsics.checkNotNull((Object)material2);
            illIIllIIllIIllIllIIlIllIIIlllIIIllIIIlllIllIlIIlllIllIllIIIlIIllIIlIIIllIIlIlIIlllIlIIlIIlllIlllIIIlIIllIIllIIlIIIlIllIllIIlllIIIlllIIllIIlIIIlllIIIlIllIllIIIllIIlIIllIIllIIlIIlllIIllIllIIllIlIIlllIllIIlllIIlIIIllIIllIIlIIIllIll = SetsKt.setOf((Object)material2);
        }
        else {
            illIIllIIllIIllIllIIlIllIIIlllIIIllIIIlllIllIlIIlllIllIllIIIlIIllIIlIIIllIIlIlIIlllIlIIlIIlllIlllIIIlIIllIIllIIlIIIlIllIllIIlllIIIlllIIllIIlIIIlllIIIlIllIllIIIllIIlIIllIIllIIlIIlllIIllIllIIllIlIIlllIllIIlllIIlIIIllIIllIIlIIIllIll = SetsKt.emptySet();
        }
        this.IllIIllIIllIIllIllIIlIllIIIlllIIIllIIIlllIllIlIIlllIllIllIIIlIIllIIlIIIllIIlIlIIlllIlIIlIIlllIlllIIIlIIllIIllIIlIIIlIllIllIIlllIIIlllIIllIIlIIIlllIIIlIllIllIIIllIIlIIllIIllIIlIIlllIIllIllIIllIlIIlllIllIIlllIIlIIIllIIllIIlIIIllIll = illIIllIIllIIllIllIIlIllIIIlllIIIllIIIlllIllIlIIlllIllIllIIIlIIllIIlIIIllIIlIlIIlllIlIIlIIlllIlllIIIlIIllIIllIIlIIIlIllIllIIlllIIIlllIIllIIlIIIlllIIIlIllIllIIIllIIlIIllIIllIIlIIlllIIllIllIIllIlIIlllIllIIlllIIlIIIllIIllIIlIIIllIll;
        this.IIllIllIIIllIIlllIllIIllIlllIIIllIllIIIlllIllIIllIllIlIIlllIIlIIllIllIIllIIIlIIllIIllIllIlllIIIlllIIllIlIIllIIIllIlIllIIIllIlIIIlIIIlIIlllIlIIllIllIIlllIIIlIIIllIIlIIIlllIIlIIllIIllIIIlIIlIlIIIllIllIIllIIlIllIlIlllIIllIIIlIl = new byte[65536];
        this.IIllIIllIllIIllIIIlllIIllIIllIIlIlllIlllIlllIIIllIIIllIIlIIlIIlllIIlIIIlIIIlllIllIIllIIllIIllIIlIIlllIlIIIllIIlIlIllIIlIIIllIllIllIIllIIIlIIIllIIllIIlIIIlllIIIlIlIIIlIllIIIlIIllIIlllIIIllIIIlllIIIllIlIIlllIlIIIlllIIlllIIllIIIllIIlllIIllIll = new byte[65536];
        this.IIIllIIllIIIlIllIIllIIIlIIllIlIIlIIlIIIlIIIllIlllIlIIllIIIllIIllIIIlllIIllIIIllIllIllIllIIllIIllIIlIIIlllIIIlllIllIlIIlllIIIllIIIllIIlIIlllIIlIllIllIlIIlllIIllIIlIIIllIIlIllIllIIIlIIlIIlllIlIllIlIllIIlllIIllIIlIIIlllIIlllIIIllIIl = new byte[65536];
    }
    
    @NotNull
    @Override
    public WrappedBlockState decode(@NotNull final XMaterial obj) {
        Intrinsics.checkNotNullParameter((Object)obj, "");
        for (final BlockMaterialData blockMaterialData : this.IllIIIlllIIllIIllIIIllIlIIIlIIIllIIIllIIIlIIIlIIIlIllIllIIIllIIIlIIIlllIlIIllIIIlIIllIllIlllIIllIIIllIlIIllIIIllIlIIlIlllIIIllIllIllIllIIlIIlllIIIllIIlllIIllIlIIIlllIllIIlIIlIIlIllIIIllIIlllIIllIIIllIIIllIIlIIllIlIIlIIlIIllIIllIll) {
            if (blockMaterialData.isCapable()) {
                final WrappedBlockState blockState = blockMaterialData.getBlockState(obj);
                Intrinsics.checkNotNullExpressionValue((Object)blockState, "");
                return blockState;
            }
        }
        throw new IllegalStateException("No block material data found for " + obj);
    }
    
    @NotNull
    @Override
    public WrappedBlockState decode(@NotNull final Material obj) {
        Intrinsics.checkNotNullParameter((Object)obj, "");
        for (final BlockMaterialData blockMaterialData : this.IllIIIlllIIllIIllIIIllIlIIIlIIIllIIIllIIIlIIIlIIIlIllIllIIIllIIIlIIIlllIlIIllIIIlIIllIllIlllIIllIIIllIlIIllIIIllIlIIlIlllIIIllIllIllIllIIlIIlllIIIllIIlllIIllIlIIIlllIllIIlIIlIIlIllIIIllIIlllIIllIIIllIIIllIIlIIllIlIIlIIlIIllIIllIll) {
            if (blockMaterialData.isCapable()) {
                final WrappedBlockState blockState = blockMaterialData.getBlockState(obj);
                Intrinsics.checkNotNullExpressionValue((Object)blockState, "");
                return blockState;
            }
        }
        throw new IllegalStateException("No block material data found for " + obj);
    }
    
    @NotNull
    @Override
    public List<Integer> decodeToIds(@NotNull final Collection<? extends XMaterial> collection) {
        Intrinsics.checkNotNullParameter((Object)collection, "");
        final Iterable iterable = collection;
        final Collection collection2 = new ArrayList();
        for (final Object next : iterable) {
            if (((XMaterial)next).isSupported()) {
                collection2.add(next);
            }
        }
        final Iterable iterable2;
        final Collection collection3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2 = collection2, 10));
        final Iterator iterator2 = iterable2.iterator();
        while (iterator2.hasNext()) {
            collection3.add(this.decode((XMaterial)iterator2.next()));
        }
        final Iterable iterable3;
        final Collection collection4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3 = collection3, 10));
        final Iterator iterator3 = iterable3.iterator();
        while (iterator3.hasNext()) {
            collection4.add(((WrappedBlockState)iterator3.next()).getGlobalId());
        }
        return (List<Integer>)collection4;
    }
    
    @NotNull
    @Override
    public Set<Integer> decodeToPossibleIds(@NotNull final Material material) {
        Intrinsics.checkNotNullParameter((Object)material, "");
        return Native.INSTANCE.getPossibleDefinition(material);
    }
    
    @NotNull
    @Override
    public Set<Integer> decodeToPossibleIds(@NotNull final XMaterial xMaterial) {
        Intrinsics.checkNotNullParameter((Object)xMaterial, "");
        Set<Integer> set;
        if (xMaterial.isSupported()) {
            final Material material = xMaterial.parseMaterial();
            Intrinsics.checkNotNull((Object)material);
            Material value = material;
            if (Intrinsics.areEqual((Object)value.name(), (Object)"NETHER_STALK")) {
                value = Material.valueOf("NETHER_WARTS");
            }
            set = Native.INSTANCE.getPossibleDefinition(value);
        }
        else {
            set = SetsKt.emptySet();
        }
        return set;
    }
    
    @NotNull
    @Override
    public Set<Integer> decodeToPossibleIds(@NotNull final Collection<? extends XMaterial> collection) {
        Intrinsics.checkNotNullParameter((Object)collection, "");
        final Iterable iterable = collection;
        final Collection collection2 = new ArrayList();
        for (final Object next : iterable) {
            if (((XMaterial)next).isSupported()) {
                collection2.add(next);
            }
        }
        final Iterable iterable2;
        final Collection collection3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2 = collection2, 10));
        for (final Object next2 : iterable2) {
            final Collection collection4 = collection3;
            final XMaterial xMaterial = (XMaterial)next2;
            final Collection collection5 = collection4;
            final Material material = xMaterial.parseMaterial();
            Intrinsics.checkNotNull((Object)material);
            collection5.add(material);
        }
        final Iterable iterable3 = collection3;
        final Collection collection6 = new ArrayList();
        for (final Material material2 : iterable3) {
            final Native instance = Native.INSTANCE;
            Intrinsics.checkNotNull((Object)material2);
            CollectionsKt.addAll(collection6, (Iterable)instance.getPossibleDefinition(material2));
        }
        return CollectionsKt.toSet((Iterable)collection6);
    }
    
    @Override
    public boolean isTransparent(@NotNull final WrappedBlockState wrappedBlockState) {
        Intrinsics.checkNotNullParameter((Object)wrappedBlockState, "");
        final int globalId = wrappedBlockState.getGlobalId();
        if (globalId < 0 || globalId >= 65536) {
            return this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(wrappedBlockState);
        }
        final byte b = this.IIllIllIIIllIIlllIllIIllIlllIIIllIllIIIlllIllIIllIllIlIIlllIIlIIllIllIIllIIIlIIllIIllIllIlllIIIlllIIllIlIIllIIIllIlIllIIIllIlIIIlIIIlIIlllIlIIllIllIIlllIIIlIIIllIIlIIIlllIIlIIllIIllIIIlIIlIlIIIllIllIIllIIlIllIlIlllIIllIIIlIl[globalId];
        if (b != 0) {
            return b == 2;
        }
        final boolean iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll = this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(wrappedBlockState);
        this.IIllIllIIIllIIlllIllIIllIlllIIIllIllIIIlllIllIIllIllIlIIlllIIlIIllIllIIllIIIlIIllIIllIllIlllIIIlllIIllIlIIllIIIllIlIllIIIllIlIIIlIIIlIIlllIlIIllIllIIlllIIIlIIIllIIlIIIlllIIlIIllIIllIIIlIIlIlIIIllIllIIllIIlIllIlIlllIIllIIIlIl[globalId] = (byte)(iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll ? 2 : 1);
        return iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;
    }
    
    private final boolean IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final WrappedBlockState wrappedBlockState) {
        final StateType type = wrappedBlockState.getType();
        return !type.isSolid() || type.exceedsCube() || this.IlIIlIIIllIlllIllIlIIIllIIIllIllIIlIlllIlllIIlIIlIIIlllIIIllIIlIlllIlllIIllIlllIIllIlIllIlIlIlllIllIllIIlIIllIIlIlllIIlIIIllIIIllIIIlIIllIIllIIIllIlllIllIIllIlllIIlllIIlIIIlIIlllIllIIllIIlIIlIIlIIIlIIIlllIIIllIIlllIIIllIllIIIlll.contains(wrappedBlockState.getGlobalId());
    }
    
    @Override
    public boolean isTransparent(@NotNull final Material material) {
        Intrinsics.checkNotNullParameter((Object)material, "");
        return !material.isOccluding() || this.IIlllIIIllIIIlIIlIIlIIllIIIllIIllIlllIlllIlllIIlllIIlllIIlllIlIIlIlIIIlIlIIIlIIIlIlllIIIlIIllIIIllIIlIIIllIllIIlllIIllIIlllIIllIIllIllIlllIIllIIllIllIIIlIIlllIIllIIllIIlIIllIlIIllIIllIlIIIllIIlllIIlIlIIllIIllIIlllIllIlIlIIlllIll.contains(material);
    }
    
    @Override
    public boolean isLava(@NotNull final WrappedBlockState wrappedBlockState) {
        Intrinsics.checkNotNullParameter((Object)wrappedBlockState, "");
        final int globalId = wrappedBlockState.getGlobalId();
        if (globalId < 0 || globalId >= 65536) {
            return this.IllIlIIlllIIlIIlIIlIlIIIlllIIIllIlIIllIIlllIIIlIIllIIIllIIIllIIlIIlllIllIllIIllIlllIIIllIIllIIllIlllIlllIllIIlIIlIIllIIllIIIllIIIlIIIlIIllIIIllIIlIlllIllIIlllIIIllIIllIlIllIIIlllIllIIlIlIllIlIIIlIIIlIIlIIIlIIIlIllIIllIIIlllIl.contains(globalId);
        }
        final byte b = this.IIllIIllIllIIllIIIlllIIllIIllIIlIlllIlllIlllIIIllIIIllIIlIIlIIlllIIlIIIlIIIlllIllIIllIIllIIllIIlIIlllIlIIIllIIlIlIllIIlIIIllIllIllIIllIIIlIIIllIIllIIlIIIlllIIIlIlIIIlIllIIIlIIllIIlllIIIllIIIlllIIIllIlIIlllIlIIIlllIIlllIIllIIIllIIlllIIllIll[globalId];
        if (b != 0) {
            return b == 2;
        }
        final boolean contains = this.IllIlIIlllIIlIIlIIlIlIIIlllIIIllIlIIllIIlllIIIlIIllIIIllIIIllIIlIIlllIllIllIIllIlllIIIllIIllIIllIlllIlllIllIIlIIlIIllIIllIIIllIIIlIIIlIIllIIIllIIlIlllIllIIlllIIIllIIllIlIllIIIlllIllIIlIlIllIlIIIlIIIlIIlIIIlIIIlIllIIllIIIlllIl.contains(globalId);
        this.IIllIIllIllIIllIIIlllIIllIIllIIlIlllIlllIlllIIIllIIIllIIlIIlIIlllIIlIIIlIIIlllIllIIllIIllIIllIIlIIlllIlIIIllIIlIlIllIIlIIIllIllIllIIllIIIlIIIllIIllIIlIIIlllIIIlIlIIIlIllIIIlIIllIIlllIIIllIIIlllIIIllIlIIlllIlIIIlllIIlllIIllIIIllIIlllIIllIll[globalId] = (byte)(contains ? 2 : 1);
        return contains;
    }
    
    @Override
    public boolean isLava(@NotNull final Material material) {
        Intrinsics.checkNotNullParameter((Object)material, "");
        return this.IllIIllIIllIIllIllIIlIllIIIlllIIIllIIIlllIllIlIIlllIllIllIIIlIIllIIlIIIllIIlIlIIlllIlIIlIIlllIlllIIIlIIllIIllIIlIIIlIllIllIIlllIIIlllIIllIIlIIIlllIIIlIllIllIIIllIIlIIllIIllIIlIIlllIIllIllIIllIlIIlllIllIIlllIIlIIIllIIllIIlIIIllIll.contains(material);
    }
    
    @Override
    public int cullingTransparencyCached(final int n) {
        if (n < 0 || n >= 65536) {
            return 0;
        }
        return this.IIIllIIllIIIlIllIIllIIIlIIllIlIIlIIlIIIlIIIllIlllIlIIllIIIllIIllIIIlllIIllIIIllIllIllIllIIllIIllIIlIIIlllIIIlllIllIlIIlllIIIllIIIllIIlIIlllIIlIllIllIlIIlllIIllIIlIIIllIIlIllIllIIIlIIlIIlllIlIllIlIllIIlllIIllIIlIIIlllIIlllIIIllIIl[n];
    }
    
    @Override
    public void cacheCullingTransparency(final int n, final boolean b) {
        if (n < 0 || n >= 65536) {
            return;
        }
        this.IIIllIIllIIIlIllIIllIIIlIIllIlIIlIIlIIIlIIIllIlllIlIIllIIIllIIllIIIlllIIllIIIllIllIllIllIIllIIllIIlIIIlllIIIlllIllIlIIlllIIIllIIIllIIlIIlllIIlIllIllIlIIlllIIllIIlIIIllIIlIllIllIIIlIIlIIlllIlIllIlIllIIlllIIllIIlIIIlllIIlllIIIllIIl[n] = (byte)(b ? 2 : 1);
    }
    
    static {
        IlIIIlllIIIlIIllIIllIIlIIlIIllIIIlIllIIlllIIIlllIIlIllIIlIIlIllIIlIIllIIllIllIIlIIIllIIllIlIIlllIIIllIlIIllIlllIIIlIIllIIlIllIlllIIllIIlIllIllIlIllIIIllIIllIIIlllIIIlllIlllIlIllIlIlllIIllIllIlllIlllIIlIlIIllIlIIIlIIIlll = new IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(null);
    }
    
    @Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\n\u0010\u0006R\u0014\u0010\r\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\f\u0010\u0006" }, d2 = { "Ldev/imanity/antixray/a/IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll$IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;", "", "<init>", "()V", "", "IIlIllIIlIIlllIlIlllIIIllIlIIlIIllIIllIlIIIlIllIIlllIIllIlllIllIIllIlIIllIIIlllIllIllIlIlllIIIlllIIIlIIIlIllIlllIIlIIlIIllIIlIIIllIllIIlIIlllIllIIlIllIIlIIllIlIIIlllIIIlIIlIlIlllIIIllIIlIlIllIIIlIIllIlllIIIllIIllIll", "I", "IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll", "IllIIlIIllIIlIlllIlIlIlllIIIlIIlllIIIlIllIIIlIllIIIllIIlIIIllIIIllIIIlllIlllIllIllIllIIIlIIIllIlIlllIIllIlllIIIllIlIIlIIlIlIlIIllIllIlllIIllIlIIlIIIlllIIllIIIlIIIllIIIlllIIIllIlllIIlIlIIlIIllIIIlllIllIlIIIlIlIIlIIIlllIIIlll", "IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll", "IIllIIlllIIllIIIlllIlIIlIlllIIllIIllIIlIIIlllIIlllIllIIlIIlIlIIlIIIllIIlllIIIllIIlllIIllIIlIlllIlllIIIlIllIlIIlIIIllIIIllIIllIllIIllIllIIIllIIllIIIlllIIlIIlllIlllIIlIIlIIlllIIllIllIIlllIllIIIlIllIIlllIIIllIIIlllIIlIIlIIIlIllIllIllIl", "IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll", "IIllIIIlllIIllIIlllIIIllIIIllIlllIlIllIllIIlIIllIIlIIllIIIlIlIIlllIIlllIIIllIIllIIllIlIIlIlIllIIlllIIIllIllIIllIlllIllIllIlllIIllIIIllIllIllIllIIIlllIIllIllIIlllIlIlIlllIIlIlIIllIlIIllIlllIIlIIllIIIlIIlIIllIlIIlIllIIIl", "IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll" })
    private static final class IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll
    {
    }
}
