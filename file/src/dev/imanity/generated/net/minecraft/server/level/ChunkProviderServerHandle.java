// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.server.level;

import dev.imanity.generated.Common;
import java.util.function.Consumer;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.server.level.ChunkProviderServer")
public abstract class ChunkProviderServerHandle extends Template.Handle
{
    public static final ChunkProviderServerClass T;
    
    public static ChunkProviderServerHandle createHandle(final Object o) {
        return ChunkProviderServerHandle.T.createHandle(o);
    }
    
    public static Object unpackGetChunkAsyncResult(final Object o) {
        return ChunkProviderServerHandle.T.unpackGetChunkAsyncResult.invoke(o);
    }
    
    public abstract void getChunkAtAsync(final int p0, final int p1, final Consumer<?> p2);
    
    static {
        T = Template.Class.create(ChunkProviderServerClass.class, Common.INSTANCE);
    }
    
    public static final class ChunkProviderServerClass extends Template.Class<ChunkProviderServerHandle>
    {
        public final Template.StaticMethod.Converted<Object> unpackGetChunkAsyncResult;
        public final Template.Method<Void> getChunkAtAsync;
        
        public ChunkProviderServerClass() {
            this.unpackGetChunkAsyncResult = new Template.StaticMethod.Converted<Object>();
            this.getChunkAtAsync = new Template.Method<Void>();
        }
    }
}
