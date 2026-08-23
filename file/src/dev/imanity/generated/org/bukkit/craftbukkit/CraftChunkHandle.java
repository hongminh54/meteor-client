// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.org.bukkit.craftbukkit;

import dev.imanity.generated.Common;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("org.bukkit.craftbukkit.CraftChunk")
public abstract class CraftChunkHandle extends Template.Handle
{
    public static final CraftChunkClass T;
    
    public static CraftChunkHandle createHandle(final Object o) {
        return CraftChunkHandle.T.createHandle(o);
    }
    
    public abstract Object getHandle();
    
    static {
        T = Template.Class.create(CraftChunkClass.class, Common.INSTANCE);
    }
    
    public static final class CraftChunkClass extends Template.Class<CraftChunkHandle>
    {
        public final Template.Method<Object> getHandle;
        
        public CraftChunkClass() {
            this.getHandle = new Template.Method<Object>();
        }
    }
}
