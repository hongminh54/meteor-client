// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.server.network;

import dev.imanity.generated.Common;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.server.network.ServerGamePacketListenerImpl")
public abstract class ServerGamePacketListenerImplHandle extends Template.Handle
{
    public static final ServerGamePacketListenerImplClass T;
    
    public static ServerGamePacketListenerImplHandle createHandle(final Object o) {
        return ServerGamePacketListenerImplHandle.T.createHandle(o);
    }
    
    public abstract void sendPacket(final Object p0);
    
    static {
        T = Template.Class.create(ServerGamePacketListenerImplClass.class, Common.INSTANCE);
    }
    
    public static final class ServerGamePacketListenerImplClass extends Template.Class<ServerGamePacketListenerImplHandle>
    {
        public final Template.Method.Converted<Void> sendPacket;
        
        public ServerGamePacketListenerImplClass() {
            this.sendPacket = new Template.Method.Converted<Void>();
        }
    }
}
