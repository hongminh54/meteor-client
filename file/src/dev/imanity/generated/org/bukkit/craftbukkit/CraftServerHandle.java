// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.org.bukkit.craftbukkit;

import dev.imanity.generated.Common;
import org.bukkit.Bukkit;
import dev.imanity.generated.net.minecraft.server.MinecraftServerHandle;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("org.bukkit.craftbukkit.CraftServer")
public abstract class CraftServerHandle extends Template.Handle
{
    public static final CraftServerClass T;
    private static CraftServerHandle _instance;
    
    public static CraftServerHandle createHandle(final Object o) {
        return CraftServerHandle.T.createHandle(o);
    }
    
    public abstract MinecraftServerHandle getServer();
    
    public static CraftServerHandle instance() {
        if (CraftServerHandle._instance == null) {
            CraftServerHandle._instance = createHandle(Bukkit.getServer());
        }
        return CraftServerHandle._instance;
    }
    
    static {
        T = Template.Class.create(CraftServerClass.class, Common.INSTANCE);
        CraftServerHandle._instance = null;
    }
    
    public static final class CraftServerClass extends Template.Class<CraftServerHandle>
    {
        public final Template.Method.Converted<MinecraftServerHandle> getServer;
        
        public CraftServerClass() {
            this.getServer = new Template.Method.Converted<MinecraftServerHandle>();
        }
    }
}
