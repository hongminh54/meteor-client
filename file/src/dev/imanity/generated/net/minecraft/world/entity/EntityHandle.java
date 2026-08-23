// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.world.entity;

import dev.imanity.generated.net.minecraft.server.level.EntityTrackerEntryHandle;
import dev.imanity.generated.Common;
import org.bukkit.util.Vector;
import dev.imanity.generated.net.minecraft.world.level.WorldHandle;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.world.entity.Entity")
public abstract class EntityHandle extends Template.Handle
{
    public static final EntityClass T;
    
    public static EntityHandle createHandle(final Object o) {
        return EntityHandle.T.createHandle(o);
    }
    
    public abstract WorldHandle getWorld();
    
    public abstract double getLocX();
    
    public abstract double getLocY();
    
    public abstract double getLocZ();
    
    public abstract void setLoc(final double p0, final double p1, final double p2);
    
    public abstract void setLocX(final double p0);
    
    public abstract void setLocY(final double p0);
    
    public abstract void setLocZ(final double p0);
    
    public abstract Vector getLoc();
    
    public abstract int getIdField();
    
    public abstract void setIdField(final int p0);
    
    public abstract float getYaw();
    
    public abstract void setYaw(final float p0);
    
    public abstract float getPitch();
    
    public abstract void setPitch(final float p0);
    
    static {
        T = Template.Class.create(EntityClass.class, Common.INSTANCE);
    }
    
    public static final class EntityClass extends Template.Class<EntityHandle>
    {
        @Template.Optional
        public final Template.Field.Converted<EntityTrackerEntryHandle> tracker;
        public final Template.Field.Integer idField;
        public final Template.Field.Float yaw;
        public final Template.Field.Float pitch;
        @Template.Optional
        public final Template.Method.Converted<EntityTrackerEntryHandle> moonrise$getTrackedEntity;
        public final Template.Method.Converted<WorldHandle> getWorld;
        public final Template.Method<Double> getLocX;
        public final Template.Method<Double> getLocY;
        public final Template.Method<Double> getLocZ;
        public final Template.Method<Void> setLoc;
        public final Template.Method<Void> setLocX;
        public final Template.Method<Void> setLocY;
        public final Template.Method<Void> setLocZ;
        public final Template.Method<Vector> getLoc;
        
        public EntityClass() {
            this.tracker = new Template.Field.Converted<EntityTrackerEntryHandle>();
            this.idField = new Template.Field.Integer();
            this.yaw = new Template.Field.Float();
            this.pitch = new Template.Field.Float();
            this.moonrise$getTrackedEntity = new Template.Method.Converted<EntityTrackerEntryHandle>();
            this.getWorld = new Template.Method.Converted<WorldHandle>();
            this.getLocX = new Template.Method<Double>();
            this.getLocY = new Template.Method<Double>();
            this.getLocZ = new Template.Method<Double>();
            this.setLoc = new Template.Method<Void>();
            this.setLocX = new Template.Method<Void>();
            this.setLocY = new Template.Method<Void>();
            this.setLocZ = new Template.Method<Void>();
            this.getLoc = new Template.Method<Vector>();
        }
    }
}
