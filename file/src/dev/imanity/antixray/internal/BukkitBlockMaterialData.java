// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.internal;

import org.bukkit.Bukkit;
import io.fairyproject.libs.packetevents.PacketEvents;
import org.bukkit.Material;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.libs.xseries.XMaterial;

public class BukkitBlockMaterialData implements BlockMaterialData
{
    @Override
    public boolean isCapable() {
        try {
            Class.forName("org.bukkit.block.data.BlockData").getDeclaredMethod("getAsString", (Class<?>[])new Class[0]);
            return true;
        }
        catch (final ClassNotFoundException | NoSuchMethodException ex) {
            return false;
        }
    }
    
    @Override
    public WrappedBlockState getBlockState(@NotNull final XMaterial xMaterial) {
        final Material material = xMaterial.parseMaterial();
        if (material == null) {
            return WrappedBlockState.getByGlobalId(0);
        }
        return this.getBlockState(material);
    }
    
    @Override
    public WrappedBlockState getBlockState(@NotNull final Material material) {
        return WrappedBlockState.getByString(PacketEvents.getAPI().getServerManager().getVersion().toClientVersion(), Bukkit.createBlockData(material).getAsString(), false);
    }
}
