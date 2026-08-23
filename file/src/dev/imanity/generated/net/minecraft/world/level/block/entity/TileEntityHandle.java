// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.world.level.block.entity;

import dev.imanity.generated.Common;
import dev.imanity.generated.net.minecraft.core.Vec3iHandle;
import dev.imanity.generated.net.minecraft.network.protocol.PacketHandle;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.world.level.block.entity.TileEntity")
public abstract class TileEntityHandle extends Template.Handle
{
    public static final TileEntityClass T;
    
    public static TileEntityHandle createHandle(final Object o) {
        return TileEntityHandle.T.createHandle(o);
    }
    
    public abstract PacketHandle getUpdatePacket();
    
    public abstract Vec3iHandle getPosition_field();
    
    public abstract void setPosition_field(final Vec3iHandle p0);
    
    static {
        T = Template.Class.create(TileEntityClass.class, Common.INSTANCE);
    }
    
    public static final class TileEntityClass extends Template.Class<TileEntityHandle>
    {
        public final Template.Field.Converted<Vec3iHandle> position_field;
        public final Template.Method.Converted<PacketHandle> getUpdatePacket;
        
        public TileEntityClass() {
            this.position_field = new Template.Field.Converted<Vec3iHandle>();
            this.getUpdatePacket = new Template.Method.Converted<PacketHandle>();
        }
    }
}
