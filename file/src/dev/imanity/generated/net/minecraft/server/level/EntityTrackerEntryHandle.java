// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.server.level;

import dev.imanity.generated.Common;
import net.minecraft.server.network.ServerPlayerConnection;
import java.util.Collection;
import org.bukkit.entity.Player;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.server.level.EntityTrackerEntry")
public abstract class EntityTrackerEntryHandle extends Template.Handle
{
    public static final EntityTrackerEntryClass T;
    
    public static EntityTrackerEntryHandle createHandle(final Object o) {
        return EntityTrackerEntryHandle.T.createHandle(o);
    }
    
    public static Player convertRawViewer(final Object o) {
        return EntityTrackerEntryHandle.T.convertRawViewer.invoker.invoke(null, o);
    }
    
    public abstract Collection<ServerPlayerConnection> getRawViewers();
    
    public abstract void clearViewers();
    
    public abstract boolean addViewerToSet(final ServerPlayerConnection p0);
    
    public abstract boolean removeViewerFromSet(final ServerPlayerConnection p0);
    
    public abstract void removeViewer(final ServerPlayerHandle p0);
    
    public abstract void updatePlayer(final ServerPlayerHandle p0);
    
    static {
        T = Template.Class.create(EntityTrackerEntryClass.class, Common.INSTANCE);
    }
    
    public static final class EntityTrackerEntryClass extends Template.Class<EntityTrackerEntryHandle>
    {
        public final Template.StaticMethod<Player> convertRawViewer;
        public final Template.Method<Collection<ServerPlayerConnection>> getRawViewers;
        public final Template.Method<Void> clearViewers;
        public final Template.Method<Boolean> addViewerToSet;
        public final Template.Method<Boolean> removeViewerFromSet;
        public final Template.Method.Converted<Void> removeViewer;
        public final Template.Method.Converted<Void> updatePlayer;
        
        public EntityTrackerEntryClass() {
            this.convertRawViewer = new Template.StaticMethod<Player>();
            this.getRawViewers = new Template.Method<Collection<ServerPlayerConnection>>();
            this.clearViewers = new Template.Method<Void>();
            this.addViewerToSet = new Template.Method<Boolean>();
            this.removeViewerFromSet = new Template.Method<Boolean>();
            this.removeViewer = new Template.Method.Converted<Void>();
            this.updatePlayer = new Template.Method.Converted<Void>();
        }
    }
}
