// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.org.bukkit.craftbukkit.entity;

import dev.imanity.generated.Common;
import org.bukkit.entity.Entity;
import dev.imanity.generated.net.minecraft.world.entity.EntityHandle;
import org.bukkit.Server;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("org.bukkit.craftbukkit.entity.CraftEntity")
public abstract class CraftEntityHandle extends Template.Handle
{
    public static final CraftEntityClass T;
    
    public static CraftEntityHandle createHandle(final Object o) {
        return CraftEntityHandle.T.createHandle(o);
    }
    
    public static Entity createCraftEntity(final Server server, final EntityHandle entityHandle) {
        return CraftEntityHandle.T.createCraftEntity.invoke(server, entityHandle);
    }
    
    public abstract void setHandle(final EntityHandle p0);
    
    public abstract Object getHandle();
    
    public abstract EntityHandle getEntityHandle();
    
    public abstract void setEntityHandle(final EntityHandle p0);
    
    static {
        T = Template.Class.create(CraftEntityClass.class, Common.INSTANCE);
    }
    
    public static final class CraftEntityClass extends Template.Class<CraftEntityHandle>
    {
        public final Template.Field.Converted<EntityHandle> entityHandle;
        public final Template.StaticMethod.Converted<Entity> createCraftEntity;
        public final Template.Method.Converted<Void> setHandle;
        public final Template.Method<Object> getHandle;
        
        public CraftEntityClass() {
            this.entityHandle = new Template.Field.Converted<EntityHandle>();
            this.createCraftEntity = new Template.StaticMethod.Converted<Entity>();
            this.setHandle = new Template.Method.Converted<Void>();
            this.getHandle = new Template.Method<Object>();
        }
    }
}
