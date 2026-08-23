// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.hook.slimefun;

import org.jetbrains.annotations.Nullable;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import java.util.function.Function;
import java.util.ArrayDeque;
import java.util.LinkedHashSet;
import org.bukkit.Material;
import dev.imanity.antixray.a.IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll;
import io.fairyproject.mc.scheduler.MCSchedulers;
import kotlin.Pair;
import kotlin.TuplesKt;
import java.util.LinkedHashMap;
import java.util.Map;
import io.fairyproject.mc.MCWorld;
import io.fairyproject.mc.util.BlockPosition;
import dev.imanity.antixray.a.IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Keyed;
import org.bukkit.Tag;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.jvm.functions.Function1;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import kotlin.collections.CollectionsKt;
import io.github.thebusybiscuit.slimefun4.api.events.AndroidMineEvent;
import io.github.thebusybiscuit.slimefun4.implementation.items.tools.LumberAxe;
import io.github.thebusybiscuit.slimefun4.implementation.items.tools.PickaxeOfVeinMining;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import java.util.List;
import org.bukkit.World;
import io.github.thebusybiscuit.slimefun4.implementation.items.tools.ExplosiveTool;
import org.bukkit.block.Block;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import kotlin.jvm.internal.Intrinsics;
import io.github.thebusybiscuit.slimefun4.api.events.ExplosiveToolBreakBlocksEvent;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import org.bukkit.event.Listener;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\b\u0006\b\u0007\u0018\u0000 (2\u00020\u0001:\u0002()B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J9\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\bH\u0002¢\u0006\u0004\b\f\u0010\rJ5\u0010\u0012\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u000e2\u0006\u0010\u0005\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00102\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\u0018H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u001bH\u0007¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u0014H\u0007¢\u0006\u0004\b\u001e\u0010\u0017J\u0017\u0010 \u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u001fH\u0007¢\u0006\u0004\b \u0010!J%\u0010$\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\"2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040#H\u0002¢\u0006\u0004\b$\u0010%J\u0017\u0010&\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u000fH\u0002¢\u0006\u0004\b&\u0010'" }, d2 = { "Ldev/imanity/antixray/hook/slimefun/SlimefunListener;", "Lorg/bukkit/event/Listener;", "<init>", "()V", "Lorg/bukkit/block/Block;", "p0", "", "p1", "Lkotlin/Function1;", "", "p2", "", "findConnectedBlocks", "(Lorg/bukkit/block/Block;ILkotlin/jvm/functions/Function1;)Ljava/util/List;", "T", "Lio/github/thebusybiscuit/slimefun4/api/items/SlimefunItem;", "", "Ljava/lang/Class;", "getItemSetting", "(Lio/github/thebusybiscuit/slimefun4/api/items/SlimefunItem;Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", "Lorg/bukkit/event/block/BlockBreakEvent;", "", "handleLumberAxe", "(Lorg/bukkit/event/block/BlockBreakEvent;)V", "Lio/github/thebusybiscuit/slimefun4/implementation/items/tools/PickaxeOfVeinMining;", "handleVeinMining", "(Lorg/bukkit/event/block/BlockBreakEvent;Lio/github/thebusybiscuit/slimefun4/implementation/items/tools/PickaxeOfVeinMining;)V", "Lio/github/thebusybiscuit/slimefun4/api/events/AndroidMineEvent;", "onAndroidMine", "(Lio/github/thebusybiscuit/slimefun4/api/events/AndroidMineEvent;)V", "onBlockBreak", "Lio/github/thebusybiscuit/slimefun4/api/events/ExplosiveToolBreakBlocksEvent;", "onExplosiveToolBreak", "(Lio/github/thebusybiscuit/slimefun4/api/events/ExplosiveToolBreakBlocksEvent;)V", "Lorg/bukkit/World;", "", "scheduleChangedBlocks", "(Lorg/bukkit/World;Ljava/lang/Iterable;)V", "usesExplosionEvent", "(Lio/github/thebusybiscuit/slimefun4/api/items/SlimefunItem;)Z", "Companion", "TrackedBlockChange" })
@SourceDebugExtension({ "SMAP\nSlimefunListener.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SlimefunListener.kt\ndev/imanity/antixray/hook/slimefun/SlimefunListener\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,179:1\n766#2:180\n857#2,2:181\n766#2:183\n857#2,2:184\n1549#2:186\n1620#2,3:187\n1477#2:190\n1502#2,3:191\n1505#2,3:201\n1855#2,2:206\n372#3,7:194\n215#4,2:204\n*S KotlinDebug\n*F\n+ 1 SlimefunListener.kt\ndev/imanity/antixray/hook/slimefun/SlimefunListener\n*L\n66#1:180\n66#1:181,2\n81#1:183\n81#1:184,2\n92#1:186\n92#1:187,3\n106#1:190\n106#1:191,3\n106#1:201,3\n108#1:206,2\n106#1:194,7\n106#1:204,2\n*E\n" })
public final class SlimefunListener implements Listener
{
    @NotNull
    public static final Companion Companion;
    private static final int LUMBER_AXE_MAX_BROKEN = 100;
    @NotNull
    private static final BlockFace[] VEIN_FACES;
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public final void onExplosiveToolBreak(@NotNull final ExplosiveToolBreakBlocksEvent explosiveToolBreakBlocksEvent) {
        Intrinsics.checkNotNullParameter((Object)explosiveToolBreakBlocksEvent, "");
        final ExplosiveTool explosiveTool = explosiveToolBreakBlocksEvent.getExplosiveTool();
        Intrinsics.checkNotNullExpressionValue((Object)explosiveTool, "");
        if (this.usesExplosionEvent((SlimefunItem)explosiveTool)) {
            return;
        }
        final World world = explosiveToolBreakBlocksEvent.getPlayer().getWorld();
        Intrinsics.checkNotNullExpressionValue((Object)world, "");
        final List additionalBlocks = explosiveToolBreakBlocksEvent.getAdditionalBlocks();
        Intrinsics.checkNotNullExpressionValue((Object)additionalBlocks, "");
        this.scheduleChangedBlocks(world, additionalBlocks);
    }
    
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public final void onBlockBreak(@NotNull final BlockBreakEvent blockBreakEvent) {
        Intrinsics.checkNotNullParameter((Object)blockBreakEvent, "");
        final SlimefunItem byItem = SlimefunItem.getByItem(blockBreakEvent.getPlayer().getInventory().getItemInMainHand());
        if (byItem == null) {
            return;
        }
        final SlimefunItem slimefunItem2;
        final SlimefunItem slimefunItem = slimefunItem2 = byItem;
        if (slimefunItem2 instanceof PickaxeOfVeinMining) {
            this.handleVeinMining(blockBreakEvent, (PickaxeOfVeinMining)slimefunItem);
        }
        else if (slimefunItem2 instanceof LumberAxe) {
            this.handleLumberAxe(blockBreakEvent);
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public final void onAndroidMine(@NotNull final AndroidMineEvent androidMineEvent) {
        Intrinsics.checkNotNullParameter((Object)androidMineEvent, "");
        final World world = androidMineEvent.getBlock().getWorld();
        Intrinsics.checkNotNullExpressionValue((Object)world, "");
        this.scheduleChangedBlocks(world, CollectionsKt.listOf((Object)androidMineEvent.getBlock()));
    }
    
    private final void handleVeinMining(final BlockBreakEvent blockBreakEvent, final PickaxeOfVeinMining pickaxeOfVeinMining) {
        if (!SlimefunTag.PICKAXE_OF_VEIN_MINING_BLOCKS.isTagged(blockBreakEvent.getBlock().getType())) {
            return;
        }
        final Integer n = this.getItemSetting((SlimefunItem)pickaxeOfVeinMining, "max-blocks", Integer.class);
        final int n2 = (n != null) ? n : Integer.MAX_VALUE;
        final Block block = blockBreakEvent.getBlock();
        Intrinsics.checkNotNullExpressionValue((Object)block, "");
        final List<Block> connectedBlocks = this.findConnectedBlocks(block, n2, (Function1<? super Block, Boolean>)SlimefunListener$handleVeinMining$connectedBlocks.SlimefunListener$handleVeinMining$connectedBlocks$1.INSTANCE);
        final World world = blockBreakEvent.getBlock().getWorld();
        Intrinsics.checkNotNullExpressionValue((Object)world, "");
        final Iterable iterable = connectedBlocks;
        final World world2 = world;
        final Iterable iterable2 = iterable;
        final Collection collection = new ArrayList<Block>();
        for (final Object next : iterable2) {
            if (!Intrinsics.areEqual((Object)next, (Object)blockBreakEvent.getBlock())) {
                collection.add(next);
            }
        }
        this.scheduleChangedBlocks(world2, (List<? extends Block>)collection);
    }
    
    private final void handleLumberAxe(final BlockBreakEvent blockBreakEvent) {
        if (blockBreakEvent.getPlayer().isSneaking() || !Tag.LOGS.isTagged((Keyed)blockBreakEvent.getBlock().getType())) {
            return;
        }
        final Block block = blockBreakEvent.getBlock();
        Intrinsics.checkNotNullExpressionValue((Object)block, "");
        final List<Block> connectedBlocks = this.findConnectedBlocks(block, 100, (Function1<? super Block, Boolean>)SlimefunListener$handleLumberAxe$connectedLogs.SlimefunListener$handleLumberAxe$connectedLogs$1.INSTANCE);
        final World world = blockBreakEvent.getBlock().getWorld();
        Intrinsics.checkNotNullExpressionValue((Object)world, "");
        final Iterable iterable = connectedBlocks;
        final World world2 = world;
        final Iterable iterable2 = iterable;
        final Collection collection = new ArrayList<Block>();
        for (final Object next : iterable2) {
            final Block block2 = (Block)next;
            if (!Intrinsics.areEqual((Object)block2, (Object)blockBreakEvent.getBlock()) && !BlockStorage.hasBlockInfo(block2)) {
                collection.add(next);
            }
        }
        this.scheduleChangedBlocks(world2, (List<? extends Block>)collection);
    }
    
    private final void scheduleChangedBlocks(final World world, final Iterable<? extends Block> iterable) {
        if (!IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl.IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(world)) {
            return;
        }
        final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll illIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll = IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl.IllIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll(world);
        if (illIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll == null) {
            return;
        }
        final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll = illIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll;
        final Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)iterable, 10));
        for (final Block next : iterable) {
            final Collection collection2 = collection;
            final Block block = next;
            final Collection collection3 = collection2;
            final BlockPosition blockPosition = new BlockPosition(block.getX(), block.getY(), block.getZ());
            final int n = block.getX() >> 4;
            final int n2 = block.getZ() >> 4;
            final Material type = block.getType();
            Intrinsics.checkNotNullExpressionValue((Object)type, "");
            collection3.add(new TrackedBlockChange(blockPosition, n, n2, type));
        }
        final List list = (List)collection;
        if (list.isEmpty()) {
            return;
        }
        final MCWorld from = MCWorld.from((Object)world);
        final Iterable iterable2 = list;
        final Map map = new LinkedHashMap();
        for (final Object next2 : iterable2) {
            final TrackedBlockChange trackedBlockChange = (TrackedBlockChange)next2;
            final Pair to = TuplesKt.to((Object)trackedBlockChange.getChunkX(), (Object)trackedBlockChange.getChunkZ());
            final Map map2 = map;
            final Object value = map2.get(to);
            List<TrackedBlockChange> list3;
            if (value == null) {
                final List list2 = new ArrayList();
                map2.put(to, list2);
                list3 = list2;
            }
            else {
                list3 = (List<TrackedBlockChange>)value;
            }
            list3.add((TrackedBlockChange)next2);
        }
        for (final Map.Entry entry : map.entrySet()) {
            final Pair pair = (Pair)entry.getKey();
            MCSchedulers.getChunkScheduler(from, ((Number)pair.getFirst()).intValue(), ((Number)pair.getSecond()).intValue()).schedule(SlimefunListener::scheduleChangedBlocks$lambda$6$lambda$5, 1L);
        }
    }
    
    private final List<Block> findConnectedBlocks(final Block e, final int n, final Function1<? super Block, Boolean> function1) {
        if (n <= 0 || !(boolean)function1.invoke((Object)e)) {
            return CollectionsKt.emptyList();
        }
        final LinkedHashSet set = new LinkedHashSet();
        final ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(e);
        while (!arrayDeque.isEmpty() && set.size() < n) {
            final Block e2 = arrayDeque.pop();
            if (!set.add(e2)) {
                continue;
            }
            int n2 = SlimefunListener.VEIN_FACES.length - 1;
            if (0 > n2) {
                continue;
            }
            do {
                final int n3 = n2;
                --n2;
                final Block relative = e2.getRelative(SlimefunListener.VEIN_FACES[n3]);
                Intrinsics.checkNotNullExpressionValue((Object)relative, "");
                final Block block = relative;
                if (!set.contains(block) && (boolean)function1.invoke((Object)block)) {
                    arrayDeque.push(block);
                }
            } while (0 <= n2);
        }
        return CollectionsKt.toList((Iterable)set);
    }
    
    private final boolean usesExplosionEvent(final SlimefunItem slimefunItem) {
        final Boolean b = this.getItemSetting(slimefunItem, "call-explosion-event", Boolean.class);
        return b != null && b;
    }
    
    private final <T> T getItemSetting(final SlimefunItem slimefunItem, final String s, final Class<T> clazz) {
        return slimefunItem.getItemSetting(s, (Class)clazz).map(SlimefunListener::getItemSetting$lambda$7).orElse(null);
    }
    
    private static final void scheduleChangedBlocks$lambda$6$lambda$5(final List list, final World world, final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll) {
        Intrinsics.checkNotNullParameter((Object)list, "");
        Intrinsics.checkNotNullParameter((Object)world, "");
        Intrinsics.checkNotNullParameter((Object)illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll, "");
        for (final TrackedBlockChange trackedBlockChange : list) {
            final Material type = world.getBlockAt(trackedBlockChange.getPosition().getX(), trackedBlockChange.getPosition().getY(), trackedBlockChange.getPosition().getZ()).getType();
            Intrinsics.checkNotNullExpressionValue((Object)type, "");
            if (type != trackedBlockChange.getOriginalType()) {
                illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll.IIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll(trackedBlockChange.getPosition());
            }
        }
    }
    
    private static final Object getItemSetting$lambda$7(final Function1 function1, final Object o) {
        Intrinsics.checkNotNullParameter((Object)function1, "");
        return function1.invoke(o);
    }
    
    static {
        Companion = new Companion(null);
        VEIN_FACES = new BlockFace[] { BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH_EAST, BlockFace.NORTH_WEST, BlockFace.SOUTH_EAST, BlockFace.SOUTH_WEST };
    }
    
    @Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\r\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c7\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0004H\u00c6\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0004H\u00c6\u0003¢\u0006\u0004\b\u000f\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u0007H\u00c7\u0003¢\u0006\u0004\b\u0010\u0010\u0011J8\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0007H\u00c7\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0015\u001a\u00020\u00142\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0004H\u00d6\u0001¢\u0006\u0004\b\u0017\u0010\u000eJ\u0010\u0010\u0019\u001a\u00020\u0018H\u00d7\u0001¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\u00020\u00048\u0007¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u000eR\u0017\u0010\u001e\u001a\u00020\u00048\u0007¢\u0006\f\n\u0004\b\u001e\u0010\u001c\u001a\u0004\b\u001f\u0010\u000eR\u001a\u0010 \u001a\u00020\u00078\u0007X\u0087\u0004¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010\u0011R\u001a\u0010#\u001a\u00020\u00028\u0007X\u0087\u0004¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010\f" }, d2 = { "Ldev/imanity/antixray/hook/slimefun/SlimefunListener$TrackedBlockChange;", "", "Lio/fairyproject/mc/util/BlockPosition;", "p0", "", "p1", "p2", "Lorg/bukkit/Material;", "p3", "<init>", "(Lio/fairyproject/mc/util/BlockPosition;IILorg/bukkit/Material;)V", "component1", "()Lio/fairyproject/mc/util/BlockPosition;", "component2", "()I", "component3", "component4", "()Lorg/bukkit/Material;", "copy", "(Lio/fairyproject/mc/util/BlockPosition;IILorg/bukkit/Material;)Ldev/imanity/antixray/hook/slimefun/SlimefunListener$TrackedBlockChange;", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "chunkX", "I", "getChunkX", "chunkZ", "getChunkZ", "originalType", "Lorg/bukkit/Material;", "getOriginalType", "position", "Lio/fairyproject/mc/util/BlockPosition;", "getPosition" })
    private static final class TrackedBlockChange
    {
        @NotNull
        private final BlockPosition position;
        private final int chunkX;
        private final int chunkZ;
        @NotNull
        private final Material originalType;
        
        public TrackedBlockChange(@NotNull final BlockPosition position, final int chunkX, final int chunkZ, @NotNull final Material originalType) {
            Intrinsics.checkNotNullParameter((Object)position, "");
            Intrinsics.checkNotNullParameter((Object)originalType, "");
            this.position = position;
            this.chunkX = chunkX;
            this.chunkZ = chunkZ;
            this.originalType = originalType;
        }
        
        @NotNull
        @JvmName(name = "getPosition")
        public final BlockPosition getPosition() {
            return this.position;
        }
        
        @JvmName(name = "getChunkX")
        public final int getChunkX() {
            return this.chunkX;
        }
        
        @JvmName(name = "getChunkZ")
        public final int getChunkZ() {
            return this.chunkZ;
        }
        
        @NotNull
        @JvmName(name = "getOriginalType")
        public final Material getOriginalType() {
            return this.originalType;
        }
        
        @NotNull
        public final BlockPosition component1() {
            return this.position;
        }
        
        public final int component2() {
            return this.chunkX;
        }
        
        public final int component3() {
            return this.chunkZ;
        }
        
        @NotNull
        public final Material component4() {
            return this.originalType;
        }
        
        @NotNull
        public final TrackedBlockChange copy(@NotNull final BlockPosition blockPosition, final int n, final int n2, @NotNull final Material material) {
            Intrinsics.checkNotNullParameter((Object)blockPosition, "");
            Intrinsics.checkNotNullParameter((Object)material, "");
            return new TrackedBlockChange(blockPosition, n, n2, material);
        }
        
        @NotNull
        @Override
        public String toString() {
            return "TrackedBlockChange(position=" + this.position + ", chunkX=" + this.chunkX + ", chunkZ=" + this.chunkZ + ", originalType=" + this.originalType + ')';
        }
        
        @Override
        public int hashCode() {
            return ((this.position.hashCode() * 31 + Integer.hashCode(this.chunkX)) * 31 + Integer.hashCode(this.chunkZ)) * 31 + this.originalType.hashCode();
        }
        
        @Override
        public boolean equals(@Nullable final Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof TrackedBlockChange)) {
                return false;
            }
            final TrackedBlockChange trackedBlockChange = (TrackedBlockChange)o;
            return Intrinsics.areEqual((Object)this.position, (Object)trackedBlockChange.position) && this.chunkX == trackedBlockChange.chunkX && this.chunkZ == trackedBlockChange.chunkZ && this.originalType == trackedBlockChange.originalType;
        }
    }
    
    @Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0083T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\t\u0010\n" }, d2 = { "Ldev/imanity/antixray/hook/slimefun/SlimefunListener$Companion;", "", "<init>", "()V", "", "LUMBER_AXE_MAX_BROKEN", "I", "", "Lorg/bukkit/block/BlockFace;", "VEIN_FACES", "[Lorg/bukkit/block/BlockFace;" })
    public static final class Companion
    {
        private Companion() {
        }
    }
}
