// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.world.level.block.state;

import dev.imanity.generated.Common;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.world.level.block.state.IBlockData")
public abstract class IBlockDataHandle extends Template.Handle
{
    public static final IBlockDataClass T;
    
    public static IBlockDataHandle createHandle(final Object o) {
        return IBlockDataHandle.T.createHandle(o);
    }
    
    public abstract Object getBlock();
    
    static {
        T = Template.Class.create(IBlockDataClass.class, Common.INSTANCE);
    }
    
    public static final class IBlockDataClass extends Template.Class<IBlockDataHandle>
    {
        public final Template.Method<Object> getBlock;
        
        public IBlockDataClass() {
            this.getBlock = new Template.Method<Object>();
        }
    }
}
