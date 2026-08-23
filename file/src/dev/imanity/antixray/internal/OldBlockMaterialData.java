// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.internal;

import org.bukkit.Material;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.libs.xseries.XMaterial;
import java.lang.reflect.Method;
import io.fairyproject.bukkit.reflection.resolver.FieldResolver;
import io.fairyproject.bukkit.nms.BukkitNMSManager;
import io.fairyproject.bukkit.reflection.wrapper.MethodWrapper;

public class OldBlockMaterialData implements BlockMaterialData
{
    private final MethodWrapper<?> blockGetByIdMethod;
    private final MethodWrapper<?> fromLegacyDataMethod;
    private final MethodWrapper<?> fromIdMethod;
    private final Object blockRegistry;
    
    public OldBlockMaterialData(final BukkitNMSManager bukkitNMSManager) {
        MethodWrapper blockGetByIdMethod = null;
        MethodWrapper fromLegacyDataMethod = null;
        MethodWrapper fromIdMethod = null;
        Object value = null;
        try {
            if (bukkitNMSManager.isSupported()) {
                final Class resolve = bukkitNMSManager.getNmsClassResolver().resolve(new String[] { "Block" });
                Class clazz;
                try {
                    clazz = bukkitNMSManager.getNmsClassResolver().resolve(new String[] { "RegistryBlockID" });
                }
                catch (final ClassNotFoundException ex) {
                    clazz = bukkitNMSManager.getNmsClassResolver().resolve(new String[] { "RegistryID" });
                }
                blockGetByIdMethod = new MethodWrapper(resolve.getMethod("getById", Integer.TYPE));
                fromLegacyDataMethod = new MethodWrapper(resolve.getMethod("fromLegacyData", Integer.TYPE));
                value = new FieldResolver(resolve).resolve(clazz, 0).get((Object)0);
                for (final Method method : clazz.getDeclaredMethods()) {
                    if (method.getReturnType() == Integer.TYPE && method.getParameterCount() == 1) {
                        fromIdMethod = new MethodWrapper(method);
                        break;
                    }
                }
                if (fromIdMethod == null) {
                    throw new IllegalArgumentException("Cannot find method 'fromId' in " + clazz.getName());
                }
            }
        }
        catch (final Exception ex2) {}
        this.blockGetByIdMethod = (MethodWrapper<?>)blockGetByIdMethod;
        this.fromLegacyDataMethod = (MethodWrapper<?>)fromLegacyDataMethod;
        this.fromIdMethod = (MethodWrapper<?>)fromIdMethod;
        this.blockRegistry = value;
    }
    
    @Override
    public boolean isCapable() {
        return this.blockGetByIdMethod != null && this.fromLegacyDataMethod != null && this.fromIdMethod != null && this.blockRegistry != null;
    }
    
    @Override
    public WrappedBlockState getBlockState(@NotNull final XMaterial xMaterial) {
        return WrappedBlockState.getByGlobalId((int)this.fromIdMethod.invoke(this.blockRegistry, new Object[] { this.fromLegacyDataMethod.invoke(this.blockGetByIdMethod.invoke((Object)null, new Object[] { xMaterial.getId() }), new Object[] { xMaterial.getData() }) }));
    }
    
    @Override
    public WrappedBlockState getBlockState(@NotNull final Material material) {
        return WrappedBlockState.getByGlobalId((int)this.fromIdMethod.invoke(this.blockRegistry, new Object[] { this.fromLegacyDataMethod.invoke(this.blockGetByIdMethod.invoke((Object)null, new Object[] { material.getId() }), new Object[] { material.getData() }) }));
    }
}
