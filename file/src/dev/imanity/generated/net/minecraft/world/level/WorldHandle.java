// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.world.level;

import dev.imanity.generated.Common;
import dev.imanity.generated.net.minecraft.world.level.block.state.IBlockDataHandle;
import dev.imanity.generated.net.minecraft.core.BlockPosHandle;
import dev.imanity.generated.net.minecraft.world.level.chunk.ChunkHandle;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.world.level.World")
public abstract class WorldHandle extends Template.Handle
{
    public static final WorldClass T;
    
    public static WorldHandle createHandle(final Object o) {
        return WorldHandle.T.createHandle(o);
    }
    
    public abstract ChunkHandle getChunkAt(final int p0, final int p1);
    
    public abstract void sendBlockUpdated(final BlockPosHandle p0, final IBlockDataHandle p1, final IBlockDataHandle p2, final int p3);
    
    public abstract void flagDirty(final BlockPosHandle p0);
    
    public abstract IBlockDataHandle getBlockData(final BlockPosHandle p0);
    
    public abstract IBlockDataHandle getBlockDataAtCoord(final int p0, final int p1, final int p2);
    
    static {
        T = Template.Class.create(WorldClass.class, Common.INSTANCE);
    }
    
    public static final class WorldClass extends Template.Class<WorldHandle>
    {
        @Template.Optional
        public final Template.Method<Object> getChunkProvider;
        public final Template.Method.Converted<ChunkHandle> getChunkAt;
        public final Template.Method.Converted<Void> sendBlockUpdated;
        public final Template.Method.Converted<Void> flagDirty;
        public final Template.Method.Converted<IBlockDataHandle> getBlockData;
        public final Template.Method.Converted<IBlockDataHandle> getBlockDataAtCoord;
        
        public WorldClass() {
            this.getChunkProvider = new Template.Method<Object>();
            this.getChunkAt = new Template.Method.Converted<ChunkHandle>();
            this.sendBlockUpdated = new Template.Method.Converted<Void>();
            this.flagDirty = new Template.Method.Converted<Void>();
            this.getBlockData = new Template.Method.Converted<IBlockDataHandle>();
            this.getBlockDataAtCoord = new Template.Method.Converted<IBlockDataHandle>();
        }
    }
}
