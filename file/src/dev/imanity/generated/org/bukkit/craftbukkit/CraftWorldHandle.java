// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.org.bukkit.craftbukkit;

import dev.imanity.generated.Common;
import dev.imanity.generated.net.minecraft.world.level.WorldHandle;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("org.bukkit.craftbukkit.CraftWorld")
public abstract class CraftWorldHandle extends Template.Handle
{
    public static final CraftWorldClass T;
    
    public static CraftWorldHandle createHandle(final Object o) {
        return CraftWorldHandle.T.createHandle(o);
    }
    
    public abstract WorldHandle getHandle();
    
    static {
        T = Template.Class.create(CraftWorldClass.class, Common.INSTANCE);
    }
    
    public static final class CraftWorldClass extends Template.Class<CraftWorldHandle>
    {
        public final Template.Method.Converted<WorldHandle> getHandle;
        
        public CraftWorldClass() {
            this.getHandle = new Template.Method.Converted<WorldHandle>();
        }
    }
}
