// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.server.level;

import dev.imanity.generated.Common;
import org.bukkit.plugin.Plugin;
import net.minecraft.server.level.ChunkMap;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;
import dev.imanity.generated.net.minecraft.world.level.WorldHandle;

@Template.InstanceType("net.minecraft.server.level.WorldServer")
public abstract class WorldServerHandle extends WorldHandle
{
    public static final WorldServerClass T;
    
    public static WorldServerHandle createHandle(final Object o) {
        return WorldServerHandle.T.createHandle(o);
    }
    
    public abstract ChunkMap getPlayerChunkMap();
    
    public abstract EntityTrackerHandle getEntityTrackerHandle();
    
    public abstract void setForceLoadedAsync(final int p0, final int p1, final Plugin p2, final boolean p3, final int p4);
    
    static {
        T = Template.Class.create(WorldServerClass.class, Common.INSTANCE);
    }
    
    public static final class WorldServerClass extends Template.Class<WorldServerHandle>
    {
        @Template.Optional
        public final Template.Field.Converted<ChunkProviderServerHandle> field_chunkProviderServer;
        public final Template.Method<ChunkMap> getPlayerChunkMap;
        public final Template.Method.Converted<EntityTrackerHandle> getEntityTrackerHandle;
        public final Template.Method<Void> setForceLoadedAsync;
        
        public WorldServerClass() {
            this.field_chunkProviderServer = new Template.Field.Converted<ChunkProviderServerHandle>();
            this.getPlayerChunkMap = new Template.Method<ChunkMap>();
            this.getEntityTrackerHandle = new Template.Method.Converted<EntityTrackerHandle>();
            this.setForceLoadedAsync = new Template.Method<Void>();
        }
    }
}
