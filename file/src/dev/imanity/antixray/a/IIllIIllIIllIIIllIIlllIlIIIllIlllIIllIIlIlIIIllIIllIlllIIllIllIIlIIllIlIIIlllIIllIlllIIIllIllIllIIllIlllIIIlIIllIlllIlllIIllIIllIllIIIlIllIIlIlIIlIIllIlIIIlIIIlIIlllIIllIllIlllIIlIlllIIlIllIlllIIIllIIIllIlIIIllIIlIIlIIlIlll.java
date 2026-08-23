// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import it.unimi.dsi.fastutil.bytes.Byte2IntMap;
import java.util.Map;
import dev.imanity.antixray.config.MinableFeature;
import kotlin.collections.CollectionsKt;
import it.unimi.dsi.fastutil.bytes.Byte2IntOpenHashMap;
import dev.imanity.antixray.config.AntiXrayConfig;
import dev.imanity.antixray.api.obfuscator.ObfuscateStrategy;
import org.bukkit.World;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import dev.imanity.antixray.config.AntiXrayConfigService;
import kotlin.jvm.internal.SourceDebugExtension;
import io.fairyproject.container.InjectableComponent;
import dev.imanity.antixray.api.obfuscator.ObfuscateStrategyProvider;

@InjectableComponent
@SourceDebugExtension({ "SMAP\nFakeObfuscateStrategyProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FakeObfuscateStrategyProvider.kt\ndev/imanity/antixray/core/strategy/provider/FakeObfuscateStrategyProvider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,59:1\n1864#2,3:60\n1559#2:63\n1590#2,4:64\n1855#2,2:68\n*S KotlinDebug\n*F\n+ 1 FakeObfuscateStrategyProvider.kt\ndev/imanity/antixray/core/strategy/provider/FakeObfuscateStrategyProvider\n*L\n32#1:60,3\n40#1:63\n40#1:64,4\n55#1:68,2\n*E\n" })
public final class IIllIIllIIllIIIllIIlllIlIIIllIlllIIllIIlIlIIIllIIllIlllIIllIllIIlIIllIlIIIlllIIllIlllIIIllIllIllIIllIlllIIIlIIllIlllIlllIIllIIllIllIIIlIllIIlIlIIlIIllIlIIIlIIIlIIlllIIllIllIlllIIlIlllIIlIllIlllIIIllIIIllIlIIIllIIlIIlIIlIlll implements ObfuscateStrategyProvider
{
    @NotNull
    private final AntiXrayConfigService cg;
    @NotNull
    private final IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll ch;
    
    public IIllIIllIIllIIIllIIlllIlIIIllIlllIIllIIlIlIIIllIIllIlllIIllIllIIlIIllIlIIIlllIIllIlllIIIllIllIllIIllIlllIIIlIIllIlllIlllIIllIIllIllIIIlIllIIlIlIIlIIllIlIIIlIIIlIIlllIIllIllIlllIIlIlllIIlIllIlllIIIllIIIllIlIIIllIIlIIlIIlIlll(@NotNull final AntiXrayConfigService cg, @NotNull final IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll ch) {
        Intrinsics.checkNotNullParameter((Object)cg, "");
        Intrinsics.checkNotNullParameter((Object)ch, "");
        this.cg = cg;
        this.ch = ch;
    }
    
    @Override
    public int getOrder() {
        return 1;
    }
    
    @NotNull
    @Override
    public ObfuscateStrategy create(@NotNull final World world) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        final AntiXrayConfig antiXrayConfig = this.cg.getConfig(world);
        final String property = System.getProperty("java.specification.version");
        Intrinsics.checkNotNull((Object)property);
        final IIllIIlllIIIllIIIlllIlllIIIlIIlIIlIlllIlllIIllIIIllIIlIllIIIllIlllIIlIIIlIllIllIllIlllIllIlIIllIIIllIlIIIllIIllIIIllIlIIIllIllIIIlllIlllIlIIlllIIlIIIllIllIIlllIllIIllIIlllIlIlIIIlllIIlIIlllIIlllIIIllIlIllIIllIllIlllIIlIlIlIlll illIIlllIIIllIIIlllIlllIIIlIIlIIlIlllIlllIIllIIIllIIlIllIIIllIlllIIlIIIlIllIllIllIlllIllIlIIllIIIllIlIIIllIIllIIIllIlIIIllIllIIIlllIlllIlIIlllIIlIIIllIllIIlllIllIIllIIlllIlIlIIIlllIIlIIlllIIlllIIIllIlIllIIllIllIlllIIlIlIlIlll = (Double.parseDouble(property) >= 17.0) ? new IlllIllIIlIIlllIllIIlllIIIlllIIlllIIIllIlllIlIIIlIIllIlIIIllIIllIIlllIllIIlIIIlIIlllIIlllIllIIlllIllIIllIllIIlllIIIlIIllIIlIlIIllIIIlllIIllIIllIIllIllIIIllIIlIIIlllIIIllIIllIlIllIIlllIIlllIIIllIllIIlIlIIllIllIllIIIlIllIIlIIIlIllIIlll(world) : ((IIllIIlllIIIllIIIlllIlllIIIlIIlIIlIlllIlllIIllIIIllIIlIllIIIllIlllIIlIIIlIllIllIllIlllIllIlIIllIIIllIlIIIllIIllIIIllIlIIIllIllIIIlllIlllIlIIlllIIlIIIllIllIIlllIllIIllIIlllIlIlIIIlllIIlIIlllIIlllIIIllIlIllIIllIllIlllIIlIlIlIlll)new IllIIIlllIllIIIllIlllIlIIlllIIIllIIllIlIIlIIllIIlllIIlllIIllIIIllIIIlIIlIllIllIIllIIllIllIIIllIllIIllIIIlllIlllIIllIIIllIIIllIIIlIIllIIllIIIllIIlllIIIllIIlIIIlIlllIIlllIllIIllIIlllIllIlIlIllIIlllIIIlllIIIllIIllIIllIllIIIlllIIllIIllIlIlIIll(world));
        final Byte2IntOpenHashMap byte2IntOpenHashMap = new Byte2IntOpenHashMap();
        final Iterable iterable = antiXrayConfig.getFakeConfig().getOres();
        int n = 0;
        for (final Object next : iterable) {
            final int n2 = n++;
            if (n2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            final int n3 = n2;
            final MinableFeature minableFeature = (MinableFeature)next;
            final int n4 = n3;
            final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl blockMaterial = minableFeature.getBlockMaterial();
            Intrinsics.checkNotNull((Object)blockMaterial);
            ((Map<Byte, Integer>)byte2IntOpenHashMap).put((byte)n4, blockMaterial.getBlockState().getGlobalId());
        }
        final IIIlIlIllIllIIllIllIIlIlllIIIllIIIlIlllIIlIIllIIlIIIllIIlllIIIlIllIIllIIIllIlIlllIIlIIllIIllIIllIIIllIIllIIIllIIllIllIIIlllIIIlIlllIIIlllIlIlllIIIllIIllIlIlllIIlllIllIIlIIlllIIlIIllIlIllIllIlllIIIlIIlIIllIIlIIllIIIlIIllIIlIl iiIlIlIllIllIIllIllIIlIlllIIIllIIIlIlllIIlIIllIIlIIIllIIlllIIIlIllIIllIIIllIlIlllIIlIIllIIllIIllIIIllIIllIIIllIIllIllIIIlllIIIlIlllIIIlllIlIlllIIIllIIllIlIlllIIlllIllIIlIIlllIIlIIllIlIllIllIlllIIIlIIlIIllIIlIIllIIIlIIllIIlIl = new IIIlIlIllIllIIllIllIIlIlllIIIllIIIlIlllIIlIIllIIlIIIllIIlllIIIlIllIIllIIIllIlIlllIIlIIllIIllIIllIIIllIIllIIIllIIllIllIIIlllIIIlIlllIIIlllIlIlllIIIllIIllIlIlllIIlllIllIIlIIlllIIlIIllIlIllIllIlllIIIlIIlIIllIIlIIllIIIlIIllIIlIl((Byte2IntMap)byte2IntOpenHashMap, illIIlllIIIllIIIlllIlllIIIlIIlIIlIlllIlllIIllIIIllIIlIllIIIllIlllIIlIIIlIllIllIllIlllIllIlIIllIIIllIlIIIllIIllIIIllIlIIIllIllIIIlllIlllIlIIlllIIlIIIllIllIIlllIllIIllIIlllIlIlIIIlllIIlIIlllIIlllIIIllIlIllIIllIllIlllIIlIlIlIlll);
        final Iterable iterable2;
        final Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2 = antiXrayConfig.getFakeConfig().getOres(), 10));
        int n5 = 0;
        for (final Object next2 : iterable2) {
            final Collection collection2 = collection;
            final int n6 = n5++;
            if (n6 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            final int n7 = n6;
            final MinableFeature minableFeature2 = (MinableFeature)next2;
            final int n8 = n7;
            final Collection collection3 = collection2;
            Object o;
            if (minableFeature2.getMinHeight() >= minableFeature2.getMaxHeight()) {
                o = null;
            }
            else {
                final IIllIIlllIIIllIIIlllIlllIIIlIIlIIlIlllIlllIIllIIIllIIlIllIIIllIlllIIlIIIlIllIllIllIlllIllIlIIllIIIllIlIIIllIIllIIIllIlIIIllIllIIIlllIlllIlIIlllIIlIIIllIllIIlllIllIIllIIlllIlIlIIIlllIIlIIlllIIlllIIIllIlIllIIllIllIlllIIlIlIlIlll illIIlllIIIllIIIlllIlllIIIlIIlIIlIlllIlllIIllIIIllIIlIllIIIllIlllIIlIIIlIllIllIllIlllIllIlIIllIIIllIlIIIllIIllIIIllIlIIIllIllIIIlllIlllIlIIlllIIlIIIllIllIIlllIllIIllIIlllIlIlIIIlllIIlIIlllIIlllIIIllIlIllIIllIllIlllIIlIlIlIlll2;
                final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl replaceMaterial;
                o = new IIllIIIlllIlIIlIIlllIllIIlllIIlllIlIIIllIIIlllIIlIIllIllIllIIllIIIllIIlllIllIIIlIllIIlIIlllIIllIllIlIIllIllIIllIIIllIIlIIlllIIlIIIlIlllIIllIIllIIlllIIllIIlllIIIlllIlIIlllIIllIIllIIlIIlIllIIllIlllIIIllIIllIIllIlllIIIllIlllIIllIllIIIlIl(illIIlllIIIllIIIlllIlllIIIlIIlIIlIlllIlllIIllIIIllIIlIllIIIllIlllIIlIIIlIllIllIllIlllIllIlIIllIIIllIlIIIllIIllIIIllIlIIIllIllIIIlllIlllIlIIlllIIlIIIllIllIIlllIllIIllIIlllIlIlIIIlllIIlIIlllIIlllIIIllIlIllIIllIllIlllIIlIlIlIlll2, replaceMaterial, (byte)n8, minableFeature2.getVeinCount(), minableFeature2.getVeinSize(), minableFeature2.getMinHeight(), minableFeature2.getMaxHeight());
                illIIlllIIIllIIIlllIlllIIIlIIlIIlIlllIlllIIllIIIllIIlIllIIIllIlllIIlIIIlIllIllIllIlllIllIlIIllIIIllIlIIIllIIllIIIllIlIIIllIllIIIlllIlllIlIIlllIIlIIIllIllIIlllIllIIllIIlllIlIlIIIlllIIlIIlllIIlllIIIllIlIllIIllIllIlllIIlIlIlIlll2 = illIIlllIIIllIIIlllIlllIIIlIIlIIlIlllIlllIIllIIIllIIlIllIIIllIlllIIlIIIlIllIllIllIlllIllIlIIllIIIllIlIIIllIIllIIIllIlIIIllIllIIIlllIlllIlIIlllIIlIIIllIllIIlllIllIIllIIlllIlIlIIIlllIIlIIlllIIlllIIIllIlIllIIllIllIlllIIlIlIlIlll;
                replaceMaterial = minableFeature2.getReplaceMaterial();
                Intrinsics.checkNotNull((Object)replaceMaterial);
            }
            collection3.add(o);
        }
        final Iterator iterator3 = CollectionsKt.filterNotNull((Iterable)collection).iterator();
        while (iterator3.hasNext()) {
            iiIlIlIllIllIIllIllIIlIlllIIIllIIIlIlllIIlIIllIIlIIIllIIlllIIIlIllIIllIIIllIlIlllIIlIIllIIllIIllIIIllIIllIIIllIIllIllIIIlllIIIlIlllIIIlllIlIlllIIIllIIllIlIlllIIlllIllIIlIIlllIIlIIllIlIllIllIlllIIIlIIlIIllIIlIIllIIIlIIllIIlIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll((IIllIIlllIllIlllIlllIIlllIlIIllIIIlIIIlIIIllIlIllIlIlllIIlIIllIIlIIllIIIlllIIlIlllIlIIllIIlIIllIIlllIIlllIIIllIIlllIIlIIlIIllIllIIIllIllIIlIIlIIllIIlIlllIllIlIIlIlllIlIllIIllIlIlllIIIllIlIIllIIlIlIIIlIIllIllIIlllIIIll)iterator3.next());
        }
        return iiIlIlIllIllIIllIllIIlIlllIIIllIIIlIlllIIlIIllIIlIIIllIIlllIIIlIllIIllIIIllIlIlllIIlIIllIIllIIllIIIllIIllIIIllIIllIllIIIlllIIIlIlllIIIlllIlIlllIIIllIIllIlIlllIIlllIllIIlIIlllIIlIIllIlIllIllIlllIIIlIIlIIllIIlIIllIIIlIIllIIlIl;
    }
}
