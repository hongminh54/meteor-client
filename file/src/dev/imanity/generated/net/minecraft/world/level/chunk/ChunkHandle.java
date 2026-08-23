// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.world.level.chunk;

import dev.imanity.generated.Common;
import dev.imanity.generated.net.minecraft.core.BlockPosHandle;
import java.util.Collection;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.world.level.chunk.Chunk")
public abstract class ChunkHandle extends Template.Handle
{
    public static final ChunkClass T;
    
    public static ChunkHandle createHandle(final Object o) {
        return ChunkHandle.T.createHandle(o);
    }
    
    public abstract ChunkSectionHandle[] getSections();
    
    public abstract Collection<?> getRawTileEntities();
    
    public abstract Object getTileEntity(final BlockPosHandle p0);
    
    static {
        T = Template.Class.create(ChunkClass.class, Common.INSTANCE);
    }
    
    public static final class ChunkClass extends Template.Class<ChunkHandle>
    {
        public final Template.Method.Converted<ChunkSectionHandle[]> getSections;
        public final Template.Method<Collection<?>> getRawTileEntities;
        public final Template.Method.Converted<Object> getTileEntity;
        
        public ChunkClass() {
            this.getSections = new Template.Method.Converted<ChunkSectionHandle[]>();
            this.getRawTileEntities = new Template.Method<Collection<?>>();
            this.getTileEntity = new Template.Method.Converted<Object>();
        }
    }
}
