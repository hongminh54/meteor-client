// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.config;

import dev.imanity.antixray.api.config.WorldFilteredConfig;
import dev.imanity.antixray.a.IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl;
import kotlin.Pair;
import dev.imanity.antixray.api.config.ConfigType;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.TuplesKt;
import java.util.ArrayList;
import io.fairyproject.libs.xseries.XMaterial;
import java.nio.file.Path;
import java.util.Collection;
import io.fairyproject.config.yaml.YamlConfiguration;
import kotlin.collections.CollectionsKt;
import java.util.List;
import io.fairyproject.plugin.Plugin;
import kotlin.jvm.internal.Intrinsics;
import dev.imanity.antixray.api.config.migration.MigrationRegistry;
import dev.imanity.antixray.AntiXrayPlugin;
import dev.imanity.antixray.a.IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl;
import org.jetbrains.annotations.NotNull;
import dev.imanity.antixray.a.IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import io.fairyproject.container.InjectableComponent;
import dev.imanity.antixray.api.config.AbstractConfigService;

@InjectableComponent
@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0006\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u000eH\u0017¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0011H\u0017¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0014\u0010\u0010J\u001f\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0015\u0010\u0010J\u001f\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0016\u0010\u0010J\u0015\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00110\u0017H\u0017¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\t8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00078\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d" }, d2 = { "Ldev/imanity/antixray/config/AntiXrayConfigService;", "Ldev/imanity/antixray/api/config/AbstractConfigService;", "Ldev/imanity/antixray/config/AntiXrayConfig;", "Ldev/imanity/antixray/AntiXrayPlugin;", "p0", "Ldev/imanity/antixray/api/config/migration/MigrationRegistry;", "p1", "Ldev/imanity/antixray/a/IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll;", "p2", "Ldev/imanity/antixray/a/IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl;", "p3", "<init>", "(Ldev/imanity/antixray/AntiXrayPlugin;Ldev/imanity/antixray/api/config/migration/MigrationRegistry;Ldev/imanity/antixray/a/IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll;Ldev/imanity/antixray/a/IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl;)V", "Ljava/nio/file/Path;", "Lio/fairyproject/config/yaml/YamlConfiguration$YamlProperties;", "createCustomConfig", "(Ljava/nio/file/Path;Lio/fairyproject/config/yaml/YamlConfiguration$YamlProperties;)Ldev/imanity/antixray/config/AntiXrayConfig;", "", "createDefaultConfig", "(Ljava/lang/String;)Ldev/imanity/antixray/config/AntiXrayConfig;", "createEndConfig", "createNetherConfig", "createOverworldConfig", "", "getDefaultNames", "()Ljava/util/List;", "blockMaterialSerializer", "Ldev/imanity/antixray/a/IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl;", "materialDataHandler", "Ldev/imanity/antixray/a/IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll;" })
@SourceDebugExtension({ "SMAP\nAntiXrayConfigService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AntiXrayConfigService.kt\ndev/imanity/antixray/config/AntiXrayConfigService\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,166:1\n1#2:167\n766#3:168\n857#3,2:169\n1549#3:171\n1620#3,3:172\n766#3:182\n857#3,2:183\n1549#3:185\n1620#3,3:186\n766#3:196\n857#3,2:197\n526#4:175\n511#4,6:176\n526#4:189\n511#4,6:190\n*S KotlinDebug\n*F\n+ 1 AntiXrayConfigService.kt\ndev/imanity/antixray/config/AntiXrayConfigService\n*L\n103#1:168\n103#1:169,2\n103#1:171\n103#1:172,3\n135#1:182\n135#1:183,2\n135#1:185\n135#1:186,3\n158#1:196\n158#1:197,2\n106#1:175\n106#1:176,6\n139#1:189\n139#1:190,6\n*E\n" })
public final class AntiXrayConfigService extends AbstractConfigService<AntiXrayConfig>
{
    @NotNull
    private final IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll materialDataHandler;
    @NotNull
    private final IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl blockMaterialSerializer;
    
    public AntiXrayConfigService(@NotNull final AntiXrayPlugin antiXrayPlugin, @NotNull final MigrationRegistry migrationRegistry, @NotNull final IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll materialDataHandler, @NotNull final IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl blockMaterialSerializer) {
        Intrinsics.checkNotNullParameter((Object)antiXrayPlugin, "");
        Intrinsics.checkNotNullParameter((Object)migrationRegistry, "");
        Intrinsics.checkNotNullParameter((Object)materialDataHandler, "");
        Intrinsics.checkNotNullParameter((Object)blockMaterialSerializer, "");
        super(antiXrayPlugin, migrationRegistry);
        this.materialDataHandler = materialDataHandler;
        this.blockMaterialSerializer = blockMaterialSerializer;
    }
    
    @NotNull
    @Override
    public List<String> getDefaultNames() {
        return CollectionsKt.listOf((Object[])new String[] { "default-overworld", "default-nether", "default-the-end" });
    }
    
    @NotNull
    @Override
    public AntiXrayConfig createDefaultConfig(@NotNull final String str) {
        Intrinsics.checkNotNullParameter((Object)str, "");
        switch (str) {
            case "default-the-end": {
                final Path resolve = this.getConfigDirectory().resolve("default-the-end.yml");
                Intrinsics.checkNotNullExpressionValue((Object)resolve, "");
                final YamlConfiguration.YamlProperties build = YamlConfiguration.YamlProperties.builder().setPrependedComments(CollectionsKt.listOf((Object[])new String[] { "This is the default the end configuration for AntiXray.", "You can configure the settings here.", " " })).build();
                Intrinsics.checkNotNullExpressionValue((Object)build, "");
                final AntiXrayConfig endConfig = this.createEndConfig(resolve, build);
                endConfig.getWorldFilters().add("environment:the_end");
                return endConfig;
            }
            case "default-overworld": {
                final Path resolve2 = this.getConfigDirectory().resolve("default-overworld.yml");
                Intrinsics.checkNotNullExpressionValue((Object)resolve2, "");
                final YamlConfiguration.YamlProperties build2 = YamlConfiguration.YamlProperties.builder().setPrependedComments(CollectionsKt.listOf((Object[])new String[] { "This is the default overworld configuration for AntiXray.", "You can configure the settings here.", " " })).build();
                Intrinsics.checkNotNullExpressionValue((Object)build2, "");
                final AntiXrayConfig overworldConfig = this.createOverworldConfig(resolve2, build2);
                CollectionsKt.addAll((Collection)overworldConfig.getWorldFilters(), (Iterable)CollectionsKt.listOf((Object[])new String[] { "environment:normal", "environment:custom" }));
                return overworldConfig;
            }
            case "default-nether": {
                final Path resolve3 = this.getConfigDirectory().resolve("default-nether.yml");
                Intrinsics.checkNotNullExpressionValue((Object)resolve3, "");
                final YamlConfiguration.YamlProperties build3 = YamlConfiguration.YamlProperties.builder().setPrependedComments(CollectionsKt.listOf((Object[])new String[] { "This is the default nether configuration for AntiXray.", "You can configure the settings here.", " " })).build();
                Intrinsics.checkNotNullExpressionValue((Object)build3, "");
                final AntiXrayConfig netherConfig = this.createNetherConfig(resolve3, build3);
                netherConfig.getWorldFilters().add("environment:nether");
                return netherConfig;
            }
            default:
                break;
        }
        final Path resolve4 = this.getConfigDirectory().resolve(str + ".yml");
        Intrinsics.checkNotNullExpressionValue((Object)resolve4, "");
        final YamlConfiguration.YamlProperties build4 = YamlConfiguration.YamlProperties.builder().setPrependedComments(CollectionsKt.listOf((Object[])new String[] { "This is the default configuration for AntiXray.", "You can configure the settings here.", " " })).build();
        Intrinsics.checkNotNullExpressionValue((Object)build4, "");
        final AntiXrayConfig overworldConfig2 = this.createOverworldConfig(resolve4, build4);
        overworldConfig2.getWorldFilters().add("environment:normal");
        return overworldConfig2;
    }
    
    private final AntiXrayConfig createEndConfig(final Path path, final YamlConfiguration.YamlProperties yamlProperties) {
        final AntiXrayConfig antiXrayConfig = new AntiXrayConfig(path, yamlProperties, this.blockMaterialSerializer);
        antiXrayConfig.setEnabled(false);
        return antiXrayConfig;
    }
    
    private final AntiXrayConfig createNetherConfig(final Path path, final YamlConfiguration.YamlProperties yamlProperties) {
        final AntiXrayConfig antiXrayConfig = new AntiXrayConfig(path, yamlProperties, this.blockMaterialSerializer);
        antiXrayConfig.setMinBlockHeight(-64);
        antiXrayConfig.setMaxBlockHeight(128);
        antiXrayConfig.setEnabled(true);
        final AntiXrayConfig antiXrayConfig2 = antiXrayConfig;
        final Iterable iterable = CollectionsKt.listOf((Object[])new XMaterial[] { XMaterial.CRAFTING_TABLE, XMaterial.ANCIENT_DEBRIS, XMaterial.NETHER_GOLD_ORE, XMaterial.NETHER_QUARTZ_ORE });
        final AntiXrayConfig antiXrayConfig3 = antiXrayConfig2;
        final Iterable iterable2 = iterable;
        final Collection collection = new ArrayList();
        for (final Object next : iterable2) {
            if (((XMaterial)next).isSupported()) {
                collection.add(next);
            }
        }
        final List list = (List)collection;
        final AntiXrayConfig antiXrayConfig4 = antiXrayConfig3;
        final Iterable iterable3 = list;
        final AntiXrayConfig antiXrayConfig5 = antiXrayConfig4;
        final Iterable iterable4 = iterable3;
        final Collection collection2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
        final Iterator iterator2 = iterable4.iterator();
        while (iterator2.hasNext()) {
            collection2.add(((XMaterial)iterator2.next()).name());
        }
        antiXrayConfig5.setObfuscateBlocks((List<String>)collection2);
        final AntiXrayConfig antiXrayConfig6 = antiXrayConfig;
        final Map map = MapsKt.mapOf(TuplesKt.to((Object)0, (Object)XMaterial.NETHERRACK));
        final AntiXrayConfig antiXrayConfig7 = antiXrayConfig6;
        final Map map2 = map;
        final Map replacementBlocks = new LinkedHashMap();
        for (final Map.Entry entry : map2.entrySet()) {
            if (((XMaterial)entry.getValue()).isSupported()) {
                replacementBlocks.put(entry.getKey(), entry.getValue());
            }
        }
        antiXrayConfig7.setReplacementBlocks(replacementBlocks);
        return antiXrayConfig;
    }
    
    @NotNull
    @Override
    public AntiXrayConfig createCustomConfig(@NotNull final Path path, @NotNull final YamlConfiguration.YamlProperties yamlProperties) {
        Intrinsics.checkNotNullParameter((Object)path, "");
        Intrinsics.checkNotNullParameter((Object)yamlProperties, "");
        final AntiXrayConfig overworldConfig = this.createOverworldConfig(path, yamlProperties);
        overworldConfig.setType(ConfigType.CUSTOM);
        return overworldConfig;
    }
    
    private final AntiXrayConfig createOverworldConfig(final Path path, final YamlConfiguration.YamlProperties yamlProperties) {
        final AntiXrayConfig antiXrayConfig = new AntiXrayConfig(path, yamlProperties, this.blockMaterialSerializer);
        antiXrayConfig.setEnabled(true);
        final AntiXrayConfig antiXrayConfig2 = antiXrayConfig;
        final Iterable iterable = CollectionsKt.listOf((Object[])new XMaterial[] { XMaterial.CRAFTING_TABLE, XMaterial.DIAMOND_ORE, XMaterial.EMERALD_ORE, XMaterial.GOLD_ORE, XMaterial.IRON_ORE, XMaterial.LAPIS_ORE, XMaterial.REDSTONE_ORE, XMaterial.COAL_ORE, XMaterial.COPPER_ORE, XMaterial.DEEPSLATE_COAL_ORE, XMaterial.DEEPSLATE_COPPER_ORE, XMaterial.DEEPSLATE_DIAMOND_ORE, XMaterial.DEEPSLATE_EMERALD_ORE, XMaterial.DEEPSLATE_GOLD_ORE, XMaterial.DEEPSLATE_IRON_ORE, XMaterial.DEEPSLATE_LAPIS_ORE, XMaterial.DEEPSLATE_REDSTONE_ORE });
        final AntiXrayConfig antiXrayConfig3 = antiXrayConfig2;
        final Iterable iterable2 = iterable;
        final Collection collection = new ArrayList();
        for (final Object next : iterable2) {
            if (((XMaterial)next).isSupported()) {
                collection.add(next);
            }
        }
        final List list = (List)collection;
        final AntiXrayConfig antiXrayConfig4 = antiXrayConfig3;
        final Iterable iterable3 = list;
        final AntiXrayConfig antiXrayConfig5 = antiXrayConfig4;
        final Iterable iterable4 = iterable3;
        final Collection collection2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
        final Iterator iterator2 = iterable4.iterator();
        while (iterator2.hasNext()) {
            collection2.add(((XMaterial)iterator2.next()).name());
        }
        antiXrayConfig5.setObfuscateBlocks((List<String>)collection2);
        final AntiXrayConfig antiXrayConfig6 = antiXrayConfig;
        final Map map = MapsKt.mapOf(new Pair[] { TuplesKt.to((Object)0, (Object)XMaterial.STONE), TuplesKt.to((Object)(-64), (Object)XMaterial.DEEPSLATE) });
        final AntiXrayConfig antiXrayConfig7 = antiXrayConfig6;
        final Map map2 = map;
        final Map replacementBlocks = new LinkedHashMap();
        for (final Map.Entry entry : map2.entrySet()) {
            if (((XMaterial)entry.getValue()).isSupported()) {
                replacementBlocks.put(entry.getKey(), entry.getValue());
            }
        }
        antiXrayConfig7.setReplacementBlocks(replacementBlocks);
        final FakeConfig fakeConfig = antiXrayConfig.getFakeConfig();
        final Iterable iterable5 = CollectionsKt.listOf((Object[])new MinableFeature[] { new MinableFeature(XMaterial.COAL_ORE, XMaterial.STONE, 10, 16, 0, 128), new MinableFeature(XMaterial.IRON_ORE, XMaterial.STONE, 10, 8, 0, 64), new MinableFeature(XMaterial.GOLD_ORE, XMaterial.STONE, 2, 8, 0, 32), new MinableFeature(XMaterial.REDSTONE_ORE, XMaterial.STONE, 8, 7, 0, 16), new MinableFeature(XMaterial.LAPIS_ORE, XMaterial.STONE, 1, 6, 0, 32), new MinableFeature(XMaterial.DIAMOND_ORE, XMaterial.STONE, 1, 7, 0, 16), new MinableFeature(XMaterial.EMERALD_ORE, XMaterial.STONE, 1, 1, 0, 32), new MinableFeature(XMaterial.COPPER_ORE, XMaterial.STONE, 20, 8, 0, 64), new MinableFeature(XMaterial.DEEPSLATE_COAL_ORE, XMaterial.DEEPSLATE, 5, 16, -64, 0), new MinableFeature(XMaterial.DEEPSLATE_IRON_ORE, XMaterial.DEEPSLATE, 5, 8, -64, 0), new MinableFeature(XMaterial.DEEPSLATE_GOLD_ORE, XMaterial.DEEPSLATE, 2, 8, -64, 0), new MinableFeature(XMaterial.DEEPSLATE_REDSTONE_ORE, XMaterial.DEEPSLATE, 4, 7, -64, 0), new MinableFeature(XMaterial.DEEPSLATE_LAPIS_ORE, XMaterial.DEEPSLATE, 1, 6, -64, 0), new MinableFeature(XMaterial.DEEPSLATE_DIAMOND_ORE, XMaterial.DEEPSLATE, 1, 7, -64, 0), new MinableFeature(XMaterial.DEEPSLATE_EMERALD_ORE, XMaterial.DEEPSLATE, 1, 1, -64, 0), new MinableFeature(XMaterial.DEEPSLATE_COPPER_ORE, XMaterial.DEEPSLATE, 20, 8, -64, 0) });
        final FakeConfig fakeConfig2 = fakeConfig;
        final Iterable iterable6 = iterable5;
        final Collection collection3 = new ArrayList();
        for (final Object next2 : iterable6) {
            final MinableFeature minableFeature = (MinableFeature)next2;
            minableFeature.init(this.blockMaterialSerializer);
            final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl blockMaterial = minableFeature.getBlockMaterial();
            boolean b = false;
            Label_1049: {
                if (blockMaterial != null && blockMaterial.isSupported()) {
                    final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl replaceMaterial = minableFeature.getReplaceMaterial();
                    if (replaceMaterial != null && replaceMaterial.isSupported()) {
                        b = true;
                        break Label_1049;
                    }
                }
                b = false;
            }
            if (b) {
                collection3.add(next2);
            }
        }
        fakeConfig2.setOres((List<MinableFeature>)collection3);
        return antiXrayConfig;
    }
}
