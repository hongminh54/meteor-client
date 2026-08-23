// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.server.level;

import dev.imanity.generated.Common;
import dev.imanity.generated.net.minecraft.server.network.ServerGamePacketListenerImplHandle;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.server.level.ServerPlayer")
public abstract class ServerPlayerHandle extends Template.Handle
{
    public static final ServerPlayerClass T;
    
    public static ServerPlayerHandle createHandle(final Object o) {
        return ServerPlayerHandle.T.createHandle(o);
    }
    
    public abstract ServerGamePacketListenerImplHandle getPlayerConnection();
    
    public abstract void setPlayerConnection(final ServerGamePacketListenerImplHandle p0);
    
    static {
        T = Template.Class.create(ServerPlayerClass.class, Common.INSTANCE);
    }
    
    public static final class ServerPlayerClass extends Template.Class<ServerPlayerHandle>
    {
        public final Template.Field.Converted<ServerGamePacketListenerImplHandle> playerConnection;
        
        public ServerPlayerClass() {
            this.playerConnection = new Template.Field.Converted<ServerGamePacketListenerImplHandle>();
        }
    }
}
