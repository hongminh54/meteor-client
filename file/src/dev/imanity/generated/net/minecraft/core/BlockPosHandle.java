// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.core;

import dev.imanity.generated.Common;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.core.BlockPos")
public abstract class BlockPosHandle extends Vec3iHandle
{
    public static final BlockPosClass T;
    
    public static BlockPosHandle createHandle(final Object o) {
        return BlockPosHandle.T.createHandle(o);
    }
    
    public static final BlockPosHandle createNew(final int i, final int j, final int k) {
        return BlockPosHandle.T.constr_x_y_z.newInstance(i, j, k);
    }
    
    static {
        T = Template.Class.create(BlockPosClass.class, Common.INSTANCE);
    }
    
    public static final class BlockPosClass extends Template.Class<BlockPosHandle>
    {
        public final Template.Constructor.Converted<BlockPosHandle> constr_x_y_z;
        
        public BlockPosClass() {
            this.constr_x_y_z = new Template.Constructor.Converted<BlockPosHandle>();
        }
    }
}
