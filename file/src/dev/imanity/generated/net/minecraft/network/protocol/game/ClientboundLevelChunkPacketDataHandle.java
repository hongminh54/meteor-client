// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.network.protocol.game;

import dev.imanity.generated.Common;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.Optional
@Template.InstanceType("net.minecraft.network.protocol.game.ClientboundLevelChunkPacketData")
public abstract class ClientboundLevelChunkPacketDataHandle extends Template.Handle
{
    public static final ClientboundLevelChunkPacketDataClass T;
    
    public static ClientboundLevelChunkPacketDataHandle createHandle(final Object o) {
        return ClientboundLevelChunkPacketDataHandle.T.createHandle(o);
    }
    
    public static Object createNew(final Object o) {
        return ClientboundLevelChunkPacketDataHandle.T.createNew.invoke(o);
    }
    
    static {
        T = Template.Class.create(ClientboundLevelChunkPacketDataClass.class, Common.INSTANCE);
    }
    
    public static final class ClientboundLevelChunkPacketDataClass extends Template.Class<ClientboundLevelChunkPacketDataHandle>
    {
        public final Template.StaticMethod.Converted<Object> createNew;
        
        public ClientboundLevelChunkPacketDataClass() {
            this.createNew = new Template.StaticMethod.Converted<Object>();
        }
    }
}
