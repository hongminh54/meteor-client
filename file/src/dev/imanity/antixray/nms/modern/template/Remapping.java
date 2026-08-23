// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.modern.template;

import java.util.Map;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.util.HashMap;
import dev.imanity.antixray.libs.mountiplex.logic.TextValueSequence;
import org.bukkit.Bukkit;
import io.fairyproject.bukkit.version.BukkitVersionDecoder;

public class Remapping
{
    private final MojangMappingResolver resolver;
    
    public Remapping(final MojangMappingResolver resolver) {
        this.resolver = resolver;
    }
    
    public boolean evaluateMCVersion(final String s, final String s2) {
        return TextValueSequence.evaluateText(BukkitVersionDecoder.create().decode(Bukkit.getServer()).getFormatted(), s, s2);
    }
    
    public void init() {
        final HashMap remappings = new HashMap();
        if (this.evaluateMCVersion(">=", "1.14")) {
            remappings.put("net.minecraft.server.level.EntityTracker", "net.minecraft.server.level.ChunkMap");
            remappings.put("net.minecraft.server.level.EntityTrackerEntry", "net.minecraft.server.level.ChunkMap$TrackedEntity");
        }
        remappings.put("net.minecraft.server.level.EntityTrackerEntryState", "net.minecraft.server.level.EntityTrackerEntry");
        remappings.put("net.minecraft.world.level.chunk.Chunk", "net.minecraft.world.level.chunk.LevelChunk");
        remappings.put("net.minecraft.world.level.chunk.ChunkSection", "net.minecraft.world.level.chunk.LevelChunkSection");
        remappings.put("net.minecraft.world.level.World", "net.minecraft.world.level.Level");
        remappings.put("net.minecraft.server.level.WorldServer", "net.minecraft.server.level.ServerLevel");
        remappings.put("net.minecraft.server.level.ChunkProviderServer", "net.minecraft.server.level.ServerChunkCache");
        remappings.put("net.minecraft.world.level.block.entity.TileEntity", "net.minecraft.world.level.block.entity.BlockEntity");
        remappings.put("net.minecraft.world.level.block.state.BlockStateList", "net.minecraft.world.level.block.state.StateDefinition");
        remappings.put("net.minecraft.world.level.block.state.IBlockData", "net.minecraft.world.level.block.state.BlockState");
        remappings.put("net.minecraft.world.level.block.state.IBlockDataHolder", "net.minecraft.world.level.block.state.StateHolder");
        if (this.evaluateMCVersion("<", "1.20.5")) {
            remappings.put("net.minecraft.world.level.chunk.status.ChunkStatus", "net.minecraft.world.level.chunk.ChunkStatus");
        }
        if (this.evaluateMCVersion("<", "1.18")) {
            remappings.put("net.minecraft.world.ticks.TickListServer", "net.minecraft.world.level.TickListServer");
            remappings.put("net.minecraft.world.ticks.TickList", "net.minecraft.world.level.TickList");
        }
        if (this.evaluateMCVersion("<", "1.19")) {
            remappings.put("net.minecraft.util.RandomSource", "java.util.Random");
        }
        if (this.evaluateMCVersion("<", "1.19.3")) {
            remappings.put("net.minecraft.core.registries.BuiltInRegistries", "net.minecraft.core.Registry");
        }
        if (this.evaluateMCVersion(">=", "1.14")) {
            final String s = "org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.";
            try {
                MPLType.getClassByName(s + "longs.LongSet");
                for (final String s2 : new String[] { "ints.Int2ObjectMap", "ints.Int2ObjectOpenHashMap", "ints.IntList", "ints.IntLists", "ints.IntArrayList", "longs.Long2ObjectMap", "longs.Long2ObjectOpenHashMap", "longs.Long2ObjectLinkedOpenHashMap", "longs.Long2IntOpenHashMap", "longs.LongIterator", "longs.LongLinkedOpenHashSet", "longs.LongOpenHashSet", "longs.LongSet", "longs.LongSortedSet", "longs.LongBidirectionalIterator", "objects.Object2IntMap", "objects.ObjectCollection" }) {
                    remappings.put("it.unimi.dsi.fastutil." + s2, s + s2);
                }
            }
            catch (final ClassNotFoundException ex) {}
        }
        boolean b;
        if (this.evaluateMCVersion("<", "1.15.2")) {
            b = true;
        }
        else if (this.evaluateMCVersion("==", "1.15.2")) {
            try {
                Class.forName(Bukkit.getServer().getClass().getPackage().getName() + ".legacy.CraftLegacy");
                b = false;
            }
            catch (final Throwable t) {
                b = true;
            }
        }
        else {
            b = false;
        }
        if (b) {
            remappings.put("org.bukkit.craftbukkit.legacy.CraftLegacy", "org.bukkit.craftbukkit.util.CraftLegacy");
        }
        final String s3 = "ca.spottedleaf.starlight.common.light.";
        final String[] array2 = { s3, "ca.spottedleaf.starlight.light.", "com.tuinity.tuinity.chunk.light." };
        final int length2 = array2.length;
        int j = 0;
        while (j < length2) {
            final String s4 = array2[j];
            boolean b2 = false;
            try {
                MPLType.getClassByName(s4 + "StarLightEngine");
                b2 = true;
            }
            catch (final ClassNotFoundException ex2) {}
            if (b2) {
                if (!s4.equals(s3)) {
                    for (final String s5 : new String[] { "SWMRNibbleArray", "StarLightInterface", "StarLightEngine", "SkyStarLightEngine", "BlockStarLightEngine" }) {
                        remappings.put(s3 + s5, s4 + s5);
                    }
                    break;
                }
                break;
            }
            else {
                ++j;
            }
        }
        final String s6 = "org.purpurmc.purpur.";
        final String[] array4 = { s6, "net.pl3x.purpur." };
        final int length4 = array4.length;
        int l = 0;
        while (l < length4) {
            final String s7 = array4[l];
            boolean b3 = false;
            try {
                MPLType.getClassByName(s7 + "PurpurConfig");
                b3 = true;
            }
            catch (final ClassNotFoundException ex3) {}
            if (b3) {
                if (!s7.equals(s6)) {
                    for (final String s8 : new String[] { "PurpurConfig", "PurpurWorldConfig" }) {
                        remappings.put(s6 + s8, s7 + s8);
                    }
                    break;
                }
                break;
            }
            else {
                ++l;
            }
        }
        if (!remappings.isEmpty()) {
            this.resolver.setRemappings(remappings);
        }
    }
}
