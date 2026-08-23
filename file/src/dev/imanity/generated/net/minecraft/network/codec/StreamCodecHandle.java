// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.network.codec;

import dev.imanity.generated.Common;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.network.codec.StreamCodec")
public abstract class StreamCodecHandle extends Template.Handle
{
    public static final StreamCodecClass T;
    
    public static StreamCodecHandle createHandle(final Object o) {
        return StreamCodecHandle.T.createHandle(o);
    }
    
    public abstract Object decode(final Object p0);
    
    public abstract void encode(final Object p0, final Object p1);
    
    static {
        T = Template.Class.create(StreamCodecClass.class, Common.INSTANCE);
    }
    
    public static final class StreamCodecClass extends Template.Class<StreamCodecHandle>
    {
        public final Template.Method<Object> decode;
        public final Template.Method<Void> encode;
        
        public StreamCodecClass() {
            this.decode = new Template.Method<Object>();
            this.encode = new Template.Method<Void>();
        }
    }
}
