// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.server;

import dev.imanity.generated.Common;
import dev.imanity.generated.org.bukkit.craftbukkit.CraftServerHandle;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.server.MinecraftServer")
public abstract class MinecraftServerHandle extends Template.Handle
{
    public static final MinecraftServerClass T;
    private static MinecraftServerHandle _cached_instance;
    
    public static MinecraftServerHandle createHandle(final Object o) {
        return MinecraftServerHandle.T.createHandle(o);
    }
    
    public static MinecraftServerHandle instance() {
        if (MinecraftServerHandle._cached_instance == null) {
            MinecraftServerHandle._cached_instance = CraftServerHandle.instance().getServer();
        }
        return MinecraftServerHandle._cached_instance;
    }
    
    static {
        T = Template.Class.create(MinecraftServerClass.class, Common.INSTANCE);
        MinecraftServerHandle._cached_instance = null;
    }
    
    public static final class MinecraftServerClass extends Template.Class<MinecraftServerHandle>
    {
        @Template.Optional
        public final Template.Field.Integer emptyTicks;
        
        public MinecraftServerClass() {
            this.emptyTicks = new Template.Field.Integer();
        }
    }
}
