// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.server.level;

import dev.imanity.generated.Common;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.server.level.EntityTracker")
public abstract class EntityTrackerHandle extends Template.Handle
{
    public static final EntityTrackerClass T;
    
    public static EntityTrackerHandle createHandle(final Object o) {
        return EntityTrackerHandle.T.createHandle(o);
    }
    
    public abstract EntityTrackerEntryHandle getEntry(final int p0);
    
    static {
        T = Template.Class.create(EntityTrackerClass.class, Common.INSTANCE);
    }
    
    public static final class EntityTrackerClass extends Template.Class<EntityTrackerHandle>
    {
        public final Template.Method.Converted<EntityTrackerEntryHandle> getEntry;
        
        public EntityTrackerClass() {
            this.getEntry = new Template.Method.Converted<EntityTrackerEntryHandle>();
        }
    }
}
