// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.world.level.block.state;

import dev.imanity.generated.Common;
import com.google.common.collect.ImmutableList;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.world.level.block.state.BlockStateList")
public abstract class BlockStateListHandle extends Template.Handle
{
    public static final BlockStateListClass T;
    
    public static BlockStateListHandle createHandle(final Object o) {
        return BlockStateListHandle.T.createHandle(o);
    }
    
    public abstract ImmutableList<Object> getPossibleDefinitions();
    
    static {
        T = Template.Class.create(BlockStateListClass.class, Common.INSTANCE);
    }
    
    public static final class BlockStateListClass extends Template.Class<BlockStateListHandle>
    {
        public final Template.Method<ImmutableList<Object>> getPossibleDefinitions;
        
        public BlockStateListClass() {
            this.getPossibleDefinitions = new Template.Method<ImmutableList<Object>>();
        }
    }
}
