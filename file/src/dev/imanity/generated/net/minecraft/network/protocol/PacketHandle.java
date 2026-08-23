// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.network.protocol;

import dev.imanity.generated.Common;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.network.protocol.Packet")
public abstract class PacketHandle extends Template.Handle
{
    public static final PacketClass T;
    
    public static PacketHandle createHandle(final Object o) {
        return PacketHandle.T.createHandle(o);
    }
    
    static {
        T = Template.Class.create(PacketClass.class, Common.INSTANCE);
    }
    
    public static final class PacketClass extends Template.Class<PacketHandle>
    {
        @Template.Optional
        public final Template.Method<Void> write;
        
        public PacketClass() {
            this.write = new Template.Method<Void>();
        }
    }
}
