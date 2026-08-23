// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.network;

import dev.imanity.generated.Common;
import io.netty.buffer.ByteBuf;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.network.FriendlyByteBuf")
public abstract class FriendlyByteBufHandle extends Template.Handle
{
    public static final FriendlyByteBufClass T;
    
    public static FriendlyByteBufHandle createHandle(final Object o) {
        return FriendlyByteBufHandle.T.createHandle(o);
    }
    
    public static ByteBuf createPacketByteBuf() {
        return FriendlyByteBufHandle.T.createPacketByteBuf.invoke();
    }
    
    static {
        T = Template.Class.create(FriendlyByteBufClass.class, Common.INSTANCE);
    }
    
    public static final class FriendlyByteBufClass extends Template.Class<FriendlyByteBufHandle>
    {
        public final Template.StaticMethod.Converted<ByteBuf> createPacketByteBuf;
        
        public FriendlyByteBufClass() {
            this.createPacketByteBuf = new Template.StaticMethod.Converted<ByteBuf>();
        }
    }
}
