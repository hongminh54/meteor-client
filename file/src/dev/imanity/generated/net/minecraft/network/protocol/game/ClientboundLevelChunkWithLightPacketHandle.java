// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.network.protocol.game;

import dev.imanity.generated.Common;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket")
public abstract class ClientboundLevelChunkWithLightPacketHandle extends Template.Handle
{
    public static final ClientboundLevelChunkWithLightPacketClass T;
    
    public static ClientboundLevelChunkWithLightPacketHandle createHandle(final Object o) {
        return ClientboundLevelChunkWithLightPacketHandle.T.createHandle(o);
    }
    
    public static Object createNew(final Object o, final Object o2) {
        return ClientboundLevelChunkWithLightPacketHandle.T.createNew.invoke(o, o2);
    }
    
    static {
        T = Template.Class.create(ClientboundLevelChunkWithLightPacketClass.class, Common.INSTANCE);
    }
    
    public static final class ClientboundLevelChunkWithLightPacketClass extends Template.Class<ClientboundLevelChunkWithLightPacketHandle>
    {
        public final Template.StaticMethod.Converted<Object> createNew;
        
        public ClientboundLevelChunkWithLightPacketClass() {
            this.createNew = new Template.StaticMethod.Converted<Object>();
        }
    }
}
