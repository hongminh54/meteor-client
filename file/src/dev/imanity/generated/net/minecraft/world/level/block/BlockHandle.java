// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.world.level.block;

import dev.imanity.generated.Common;
import dev.imanity.generated.net.minecraft.world.level.block.state.BlockStateListHandle;
import dev.imanity.generated.net.minecraft.core.IdMapperHandle;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.world.level.block.Block")
public abstract class BlockHandle extends Template.Handle
{
    public static final BlockClass T;
    public static final IdMapperHandle REGISTRY_ID;
    
    public static BlockHandle createHandle(final Object o) {
        return BlockHandle.T.createHandle(o);
    }
    
    public static Iterable<?> getRegistry() {
        return BlockHandle.T.getRegistry.invoker.invoke(null);
    }
    
    public abstract BlockStateListHandle getStates();
    
    static {
        T = Template.Class.create(BlockClass.class, Common.INSTANCE);
        REGISTRY_ID = BlockHandle.T.REGISTRY_ID.getSafe();
    }
    
    public static final class BlockClass extends Template.Class<BlockHandle>
    {
        public final Template.StaticField.Converted<IdMapperHandle> REGISTRY_ID;
        public final Template.StaticMethod<Iterable<?>> getRegistry;
        public final Template.Method.Converted<BlockStateListHandle> getStates;
        
        public BlockClass() {
            this.REGISTRY_ID = new Template.StaticField.Converted<IdMapperHandle>();
            this.getRegistry = new Template.StaticMethod<Iterable<?>>();
            this.getStates = new Template.Method.Converted<BlockStateListHandle>();
        }
    }
}
