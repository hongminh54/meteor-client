// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.world.level.chunk;

import dev.imanity.generated.Common;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.world.level.chunk.ChunkSection")
public abstract class ChunkSectionHandle extends Template.Handle
{
    public static final ChunkSectionClass T;
    
    public static ChunkSectionHandle createHandle(final Object o) {
        return ChunkSectionHandle.T.createHandle(o);
    }
    
    public abstract boolean isEmpty();
    
    public abstract Object getBlockData(final int p0, final int p1, final int p2);
    
    static {
        T = Template.Class.create(ChunkSectionClass.class, Common.INSTANCE);
    }
    
    public static final class ChunkSectionClass extends Template.Class<ChunkSectionHandle>
    {
        public final Template.Method<Boolean> isEmpty;
        public final Template.Method<Object> getBlockData;
        
        public ChunkSectionClass() {
            this.isEmpty = new Template.Method<Boolean>();
            this.getBlockData = new Template.Method<Object>();
        }
    }
}
