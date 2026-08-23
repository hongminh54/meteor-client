// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.config;

import java.util.Collection;
import java.util.Iterator;
import io.fairyproject.log.Log;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.collections.MapsKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.config.yaml.YamlConfiguration;
import java.nio.file.Path;
import dev.imanity.antixray.a.IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import io.fairyproject.config.annotation.NestedComment;
import io.fairyproject.config.annotation.ElementType;
import io.fairyproject.libs.xseries.XMaterial;
import java.util.Map;
import java.util.List;
import io.fairyproject.config.annotation.Comment;
import org.jetbrains.annotations.NotNull;
import dev.imanity.antixray.a.IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import io.fairyproject.config.annotation.NestedConfig;
import dev.imanity.antixray.api.config.WorldFilteredConfig;

@NestedConfig({ WorldFilteredConfig.class })
@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u001a\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0014\u0010c\u001a\u00020L2\f\u0010d\u001a\b\u0012\u0004\u0012\u00020L0KJ\b\u0010e\u001a\u00020LH\u0014J\b\u0010f\u001a\u00020LH\u0014J\b\u0010g\u001a\u00020LH\u0002J\u0006\u0010h\u001a\u00020LR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001e\u0010\u0015\u001a\u00020\u00168\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001b\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\f\"\u0004\b\u001d\u0010\u000eR\u001e\u0010\u001e\u001a\u00020\u001f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001e\u0010$\u001a\u00020\u001f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010!\"\u0004\b&\u0010#R\u001e\u0010'\u001a\u00020\u001f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010!\"\u0004\b)\u0010#R\u001e\u0010*\u001a\u00020+8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001e\u00100\u001a\u00020\u001f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010!\"\u0004\b2\u0010#R$\u00103\u001a\b\u0012\u0004\u0012\u000205048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001e\u0010:\u001a\u00020\u001f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010!\"\u0004\b<\u0010#R\u0017\u0010=\u001a\b\u0012\u0004\u0012\u00020?0>¢\u0006\b\n\u0000\u001a\u0004\b@\u00107R\u001e\u0010A\u001a\u00020B8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u001e\u0010G\u001a\u00020\u001f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010!\"\u0004\bI\u0010#R\u001a\u0010J\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020L0K0>X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010M\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020O0N8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u001e\u0010T\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010\f\"\u0004\bV\u0010\u000eR\u0014\u0010W\u001a\u000205X\u0082\u000e¢\u0006\b\n\u0000\u0012\u0004\bX\u0010YR\u001e\u0010Z\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\f\"\u0004\b\\\u0010\u000eR\u001e\u0010]\u001a\u00020\u001f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010!\"\u0004\b_\u0010#R\u001e\u0010`\u001a\u00020\u001f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010!\"\u0004\bb\u0010#¨\u0006i" }, d2 = { "Ldev/imanity/antixray/config/AntiXrayConfig;", "Ldev/imanity/antixray/api/config/WorldFilteredConfig;", "path", "Ljava/nio/file/Path;", "properties", "Lio/fairyproject/config/yaml/YamlConfiguration$YamlProperties;", "blockMaterialSerializer", "Ldev/imanity/antixray/core/data/type/BlockMaterialSerializer;", "(Ljava/nio/file/Path;Lio/fairyproject/config/yaml/YamlConfiguration$YamlProperties;Ldev/imanity/antixray/core/data/type/BlockMaterialSerializer;)V", "aggressiveCheckBlock", "", "getAggressiveCheckBlock", "()Z", "setAggressiveCheckBlock", "(Z)V", "blockGrouping", "getBlockGrouping", "setBlockGrouping", "enabled", "getEnabled", "setEnabled", "fakeConfig", "Ldev/imanity/antixray/config/FakeConfig;", "getFakeConfig", "()Ldev/imanity/antixray/config/FakeConfig;", "setFakeConfig", "(Ldev/imanity/antixray/config/FakeConfig;)V", "hideUnderLava", "getHideUnderLava", "setHideUnderLava", "lavaRevealDistance", "", "getLavaRevealDistance", "()I", "setLavaRevealDistance", "(I)V", "maxBlockHeight", "getMaxBlockHeight", "setMaxBlockHeight", "maxChunkLoadPerTick", "getMaxChunkLoadPerTick", "setMaxChunkLoadPerTick", "maxRaytraceDistance", "", "getMaxRaytraceDistance", "()D", "setMaxRaytraceDistance", "(D)V", "minBlockHeight", "getMinBlockHeight", "setMinBlockHeight", "obfuscateBlocks", "", "", "getObfuscateBlocks", "()Ljava/util/List;", "setObfuscateBlocks", "(Ljava/util/List;)V", "obfuscateThreads", "getObfuscateThreads", "setObfuscateThreads", "obfuscatedBlockMaterials", "", "Ldev/imanity/antixray/core/data/type/BlockMaterial;", "getObfuscatedBlockMaterials", "raytraceDelay", "", "getRaytraceDelay", "()J", "setRaytraceDelay", "(J)V", "raytraceRate", "getRaytraceRate", "setRaytraceRate", "reloadCallbacks", "Lkotlin/Function0;", "", "replacementBlocks", "", "Lio/fairyproject/libs/xseries/XMaterial;", "getReplacementBlocks", "()Ljava/util/Map;", "setReplacementBlocks", "(Ljava/util/Map;)V", "revealStopRaytracing", "getRevealStopRaytracing", "setRevealStopRaytracing", "serializableId", "getSerializableId$annotations", "()V", "tracePlacedBlock", "getTracePlacedBlock", "setTracePlacedBlock", "trustedRange", "getTrustedRange", "setTrustedRange", "updateRadius", "getUpdateRadius", "setUpdateRadius", "addReloadCallback", "callback", "postLoad", "preSave", "processFakeBlocks", "resetMaterial", "plugin" })
@SourceDebugExtension({ "SMAP\nAntiXrayConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AntiXrayConfig.kt\ndev/imanity/antixray/config/AntiXrayConfig\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,237:1\n1855#2,2:238\n1603#2,9:240\n1855#2:249\n1856#2:251\n1612#2:252\n1855#2,2:253\n1#3:250\n*S KotlinDebug\n*F\n+ 1 AntiXrayConfig.kt\ndev/imanity/antixray/config/AntiXrayConfig\n*L\n140#1:238,2\n151#1:240,9\n151#1:249\n151#1:251\n151#1:252\n159#1:253,2\n151#1:250\n*E\n" })
public final class AntiXrayConfig extends WorldFilteredConfig
{
    @NotNull
    private final IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl blockMaterialSerializer;
    @Comment({ "Should anti-xray be enabled?" })
    private boolean enabled;
    @Comment({ "The minimum block height to execute anti xray." })
    private int minBlockHeight;
    @Comment({ "The maximum block height to execute anti xray." })
    private int maxBlockHeight;
    @Comment({ "Maximum chunk load per tick." })
    private int maxChunkLoadPerTick;
    @Comment({ "The maximum raytrace distance." })
    private double maxRaytraceDistance;
    @Comment({ "The block update radius when world interaction happens.", "The higher update radius, the more performance impact.", "Anything over 2 is not ideal." })
    private int updateRadius;
    @Comment({ "The delay to start raytracing after the player joins the server / teleport / world change." })
    private long raytraceDelay;
    @Comment({ "The trusted block range when player joins the server / teleport / world change.", "Trust range is basically any block within the range will not be checked for raytracing.", "This is useful to make sure whenever teleported, the nearby blocks will seems normal." })
    private int trustedRange;
    @Comment({ "Should ores covered by lava be hidden?", "When enabled, ore blocks whose only line-of-sight to the player passes through lava", "will be obfuscated, preventing xray/ESP detection of ores under lava." })
    private boolean hideUnderLava;
    @Comment({ "The distance at which lava-covered ores become revealed.", "Mirrors trustedRange but applies only to ores hidden by hide-under-lava.", "Set to 0 to always hide lava-covered ores regardless of distance." })
    private int lavaRevealDistance;
    @Comment({ "DEPRECATED: obfuscation now uses a single SHARED pool across all worlds.", "This per-world value is no longer used; configure 'obfuscateThreads' in config.yml instead", "(0 = auto-size from CPU cores)." })
    private int obfuscateThreads;
    @Comment({ "The raytrace ticking rate for each player. (in ticks, 1 ticks = 50ms)" })
    private int raytraceRate;
    @Comment({ "Should the newly placed block in loaded chunks be raytraced?", "The term 'loaded chunk' means the chunk that is loaded by the player's client.", "Not the server's chunk loading.", "This feature is useful to prevent the player from using x-ray by another player's block placement.", "However, this feature will be a bit more resource-intensive." })
    private boolean tracePlacedBlock;
    @Comment({ "Should the block group be enabled?", "The block group is a feature that will group the blocks that are connected to each other.", "And raytrace the group instead of the individual block." })
    private boolean blockGrouping;
    @Comment({ "Should stop raytracing the block when the player reveals it?" })
    private boolean revealStopRaytracing;
    @Comment({ "Should the block check be aggressive?", "If enabled, the block will be checked every time the block is attempting an update.", "This feature is useful" })
    private boolean aggressiveCheckBlock;
    @Comment({ "The blocks to be obfuscated.", "For regular minecraft blocks, you can use the XMaterial enum.", "Which is listed here: https://github.com/CryptoMorin/XSeries/blob/master/src/main/java/com/cryptomorin/xseries/XMaterial.java#L68", "If your server is modded and the material is not in the XMaterial, you can use the bukkit material name instead.", "For modern versions (1.13+), you can use the block registry name for more detailed specification & modded block info that isn't in XMaterial dictionary,", "Example: 'minecraft:stone_stairs[waterlogged=true]'.", "", "For ItemsAdder custom blocks, you can use the format 'ia:custom_block_registry_name'.", "For Oraxen custom blocks, you can use the format 'oraxen:custom_item_registry_name'.", "For Nexo custom blocks, you can use the format 'nexo:custom_item_registry_name'." })
    @NotNull
    private List<String> obfuscateBlocks;
    @Comment({ "The material to replace the obfuscated blocks." })
    @ElementType(XMaterial.class)
    @NotNull
    private Map<Integer, ? extends XMaterial> replacementBlocks;
    @Comment({ "The fake structures to be generated within the world,", "It contains several features such as fake ores,", "The goal is to mislead the cheaters." })
    @NestedComment
    @NotNull
    private FakeConfig fakeConfig;
    @NotNull
    private final transient List<Function0<Unit>> reloadCallbacks;
    @NotNull
    private final transient List<IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl> obfuscatedBlockMaterials;
    @NotNull
    private transient String serializableId;
    
    public AntiXrayConfig(@NotNull final Path path, @NotNull final YamlConfiguration.YamlProperties yamlProperties, @NotNull final IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl blockMaterialSerializer) {
        Intrinsics.checkNotNullParameter((Object)path, "");
        Intrinsics.checkNotNullParameter((Object)yamlProperties, "");
        Intrinsics.checkNotNullParameter((Object)blockMaterialSerializer, "");
        super(path, yamlProperties);
        this.blockMaterialSerializer = blockMaterialSerializer;
        this.minBlockHeight = -64;
        this.maxBlockHeight = 64;
        this.maxChunkLoadPerTick = 20;
        this.maxRaytraceDistance = 64.0;
        this.updateRadius = 2;
        this.raytraceDelay = 10L;
        this.trustedRange = 5;
        this.lavaRevealDistance = 3;
        this.obfuscateThreads = 2;
        this.raytraceRate = 1;
        this.tracePlacedBlock = true;
        this.obfuscateBlocks = CollectionsKt.emptyList();
        this.replacementBlocks = MapsKt.emptyMap();
        this.fakeConfig = new FakeConfig(null, 1, null);
        this.reloadCallbacks = new ArrayList<Function0<Unit>>();
        this.obfuscatedBlockMaterials = new ArrayList<IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl>();
        this.serializableId = "1265093829";
    }
    
    public final boolean getEnabled() {
        return this.enabled;
    }
    
    public final void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public final int getMinBlockHeight() {
        return this.minBlockHeight;
    }
    
    public final void setMinBlockHeight(final int minBlockHeight) {
        this.minBlockHeight = minBlockHeight;
    }
    
    public final int getMaxBlockHeight() {
        return this.maxBlockHeight;
    }
    
    public final void setMaxBlockHeight(final int maxBlockHeight) {
        this.maxBlockHeight = maxBlockHeight;
    }
    
    public final int getMaxChunkLoadPerTick() {
        return this.maxChunkLoadPerTick;
    }
    
    public final void setMaxChunkLoadPerTick(final int maxChunkLoadPerTick) {
        this.maxChunkLoadPerTick = maxChunkLoadPerTick;
    }
    
    public final double getMaxRaytraceDistance() {
        return this.maxRaytraceDistance;
    }
    
    public final void setMaxRaytraceDistance(final double maxRaytraceDistance) {
        this.maxRaytraceDistance = maxRaytraceDistance;
    }
    
    public final int getUpdateRadius() {
        return this.updateRadius;
    }
    
    public final void setUpdateRadius(final int updateRadius) {
        this.updateRadius = updateRadius;
    }
    
    public final long getRaytraceDelay() {
        return this.raytraceDelay;
    }
    
    public final void setRaytraceDelay(final long raytraceDelay) {
        this.raytraceDelay = raytraceDelay;
    }
    
    public final int getTrustedRange() {
        return this.trustedRange;
    }
    
    public final void setTrustedRange(final int trustedRange) {
        this.trustedRange = trustedRange;
    }
    
    public final boolean getHideUnderLava() {
        return this.hideUnderLava;
    }
    
    public final void setHideUnderLava(final boolean hideUnderLava) {
        this.hideUnderLava = hideUnderLava;
    }
    
    public final int getLavaRevealDistance() {
        return this.lavaRevealDistance;
    }
    
    public final void setLavaRevealDistance(final int lavaRevealDistance) {
        this.lavaRevealDistance = lavaRevealDistance;
    }
    
    public final int getObfuscateThreads() {
        return this.obfuscateThreads;
    }
    
    public final void setObfuscateThreads(final int obfuscateThreads) {
        this.obfuscateThreads = obfuscateThreads;
    }
    
    public final int getRaytraceRate() {
        return this.raytraceRate;
    }
    
    public final void setRaytraceRate(final int raytraceRate) {
        this.raytraceRate = raytraceRate;
    }
    
    public final boolean getTracePlacedBlock() {
        return this.tracePlacedBlock;
    }
    
    public final void setTracePlacedBlock(final boolean tracePlacedBlock) {
        this.tracePlacedBlock = tracePlacedBlock;
    }
    
    public final boolean getBlockGrouping() {
        return this.blockGrouping;
    }
    
    public final void setBlockGrouping(final boolean blockGrouping) {
        this.blockGrouping = blockGrouping;
    }
    
    public final boolean getRevealStopRaytracing() {
        return this.revealStopRaytracing;
    }
    
    public final void setRevealStopRaytracing(final boolean revealStopRaytracing) {
        this.revealStopRaytracing = revealStopRaytracing;
    }
    
    public final boolean getAggressiveCheckBlock() {
        return this.aggressiveCheckBlock;
    }
    
    public final void setAggressiveCheckBlock(final boolean aggressiveCheckBlock) {
        this.aggressiveCheckBlock = aggressiveCheckBlock;
    }
    
    @NotNull
    public final List<String> getObfuscateBlocks() {
        return this.obfuscateBlocks;
    }
    
    public final void setObfuscateBlocks(@NotNull final List<String> obfuscateBlocks) {
        Intrinsics.checkNotNullParameter((Object)obfuscateBlocks, "");
        this.obfuscateBlocks = obfuscateBlocks;
    }
    
    @NotNull
    public final Map<Integer, XMaterial> getReplacementBlocks() {
        return (Map<Integer, XMaterial>)this.replacementBlocks;
    }
    
    public final void setReplacementBlocks(@NotNull final Map<Integer, ? extends XMaterial> replacementBlocks) {
        Intrinsics.checkNotNullParameter((Object)replacementBlocks, "");
        this.replacementBlocks = replacementBlocks;
    }
    
    @NotNull
    public final FakeConfig getFakeConfig() {
        return this.fakeConfig;
    }
    
    public final void setFakeConfig(@NotNull final FakeConfig fakeConfig) {
        Intrinsics.checkNotNullParameter((Object)fakeConfig, "");
        this.fakeConfig = fakeConfig;
    }
    
    @NotNull
    public final List<IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl> getObfuscatedBlockMaterials() {
        return this.obfuscatedBlockMaterials;
    }
    
    public final void addReloadCallback(@NotNull final Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter((Object)function0, "");
        this.reloadCallbacks.add(function0);
    }
    
    protected void postLoad() {
        super.postLoad();
        this.resetMaterial();
        this.processFakeBlocks();
        if (this.updateRadius > 2) {
            Log.warn(this.getPath() + " updateRadius is currently greater than 2, it may cause performance issues.", new Object[0]);
        }
    }
    
    protected void preSave() {
        this.processFakeBlocks();
    }
    
    private final void processFakeBlocks() {
        for (final MinableFeature minableFeature : this.fakeConfig.getOres()) {
            if (minableFeature.getMaxHeight() <= minableFeature.getMinHeight()) {
                Log.error("Invalid min/max height for " + minableFeature.getBlock() + " in " + this.getPath() + ": min: " + minableFeature.getMinHeight() + " > max: " + minableFeature.getMaxHeight() + ", Max height should be greater than min height", new Object[0]);
            }
            minableFeature.init(this.blockMaterialSerializer);
        }
    }
    
    public final void resetMaterial() {
        this.obfuscatedBlockMaterials.clear();
        final List<IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl> obfuscatedBlockMaterials = this.obfuscatedBlockMaterials;
        final Iterable iterable = this.obfuscateBlocks;
        final List<IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl> list = obfuscatedBlockMaterials;
        final Iterable iterable2 = iterable;
        final Collection collection = new ArrayList<IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl>();
        for (final String str : iterable2) {
            IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;
            try {
                iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll = this.blockMaterialSerializer.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(str);
            }
            catch (final Exception ex) {
                Log.error("Invalid obfuscated blocks: " + str + " in " + this.getPath() + ": " + ex.getMessage(), new Object[0]);
                iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll = null;
            }
            final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl ilIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl = iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;
            if (ilIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl != null) {
                collection.add(ilIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl);
            }
        }
        list.addAll((List<? extends IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl>)collection);
        final Iterator iterator2 = this.reloadCallbacks.iterator();
        while (iterator2.hasNext()) {
            ((Function0)iterator2.next()).invoke();
        }
    }
}
