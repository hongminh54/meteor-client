// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.internal;

import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.libs.xseries.XMaterial;
import org.bukkit.Material;
import io.fairyproject.bukkit.reflection.resolver.MethodResolver;
import io.fairyproject.bukkit.nms.BukkitNMSManager;
import io.fairyproject.bukkit.reflection.wrapper.MethodWrapper;

public class NewBlockMaterialData implements BlockMaterialData
{
    private final Class<?> magicNumbersClass;
    private final MethodWrapper<?> fromLegacyDataMethod;
    private final MethodWrapper<?> getIdMethod;
    
    public NewBlockMaterialData(final BukkitNMSManager bukkitNMSManager) {
        Class resolve = null;
        MethodWrapper resolve2 = null;
        MethodWrapper resolve3 = null;
        try {
            if (bukkitNMSManager.isSupported()) {
                final Class resolve4 = bukkitNMSManager.getNmsClassResolver().resolve(new String[] { "world.level.block.state.BlockState", "IBlockData" });
                final Class resolve5 = bukkitNMSManager.getNmsClassResolver().resolve(new String[] { "world.level.block.Block", "Block" });
                resolve = bukkitNMSManager.getObcClassResolver().resolve(new String[] { "util.CraftMagicNumbers" });
                resolve2 = new MethodResolver(resolve).resolve(resolve4, 0, new Class[] { Material.class, Byte.TYPE });
                resolve3 = new MethodResolver(resolve5).resolve(resolve4, 0, new Class[] { resolve5 });
            }
        }
        catch (final ReflectiveOperationException ex) {}
        this.magicNumbersClass = resolve;
        this.fromLegacyDataMethod = (MethodWrapper<?>)resolve2;
        this.getIdMethod = (MethodWrapper<?>)resolve3;
    }
    
    @Override
    public WrappedBlockState getBlockState(@NotNull final XMaterial xMaterial) {
        return WrappedBlockState.getByGlobalId((int)this.getIdMethod.invoke((Object)null, new Object[] { this.fromLegacyDataMethod.invoke((Object)null, new Object[] { xMaterial.parseMaterial(), xMaterial.getData() }) }));
    }
    
    @Override
    public WrappedBlockState getBlockState(@NotNull final Material material) {
        return WrappedBlockState.getByGlobalId((int)this.getIdMethod.invoke((Object)null, new Object[] { this.fromLegacyDataMethod.invoke((Object)null, new Object[] { material, material.getData() }) }));
    }
    
    @Override
    public boolean isCapable() {
        return this.magicNumbersClass != null && this.fromLegacyDataMethod != null && this.getIdMethod != null;
    }
}
