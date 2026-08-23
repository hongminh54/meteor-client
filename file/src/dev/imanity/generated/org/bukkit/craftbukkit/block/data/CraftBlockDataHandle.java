// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.org.bukkit.craftbukkit.block.data;

import org.bukkit.block.data.BlockData;
import dev.imanity.generated.Common;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("org.bukkit.craftbukkit.block.data.CraftBlockData")
public abstract class CraftBlockDataHandle extends Template.Handle
{
    public static final CraftBlockDataClass T;
    
    public static CraftBlockDataHandle createHandle(final Object o) {
        return CraftBlockDataHandle.T.createHandle(o);
    }
    
    public abstract Object getState();
    
    static {
        T = Template.Class.create(CraftBlockDataClass.class, Common.INSTANCE);
    }
    
    public static final class CraftBlockDataClass extends Template.Class<CraftBlockDataHandle>
    {
        @Template.Optional
        public final Template.StaticMethod.Converted<BlockData> fromData;
        @Template.Optional
        public final Template.StaticMethod.Converted<BlockData> createData;
        public final Template.Method<Object> getState;
        
        public CraftBlockDataClass() {
            this.fromData = new Template.StaticMethod.Converted<BlockData>();
            this.createData = new Template.StaticMethod.Converted<BlockData>();
            this.getState = new Template.Method<Object>();
        }
    }
}
