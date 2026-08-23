// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.internal;

import org.bukkit.Material;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.libs.xseries.XMaterial;

public interface BlockMaterialData
{
    boolean isCapable();
    
    WrappedBlockState getBlockState(@NotNull final XMaterial p0);
    
    WrappedBlockState getBlockState(@NotNull final Material p0);
}
