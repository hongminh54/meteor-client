// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.modern.packet;

import java.util.function.Function;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import io.netty.buffer.ByteBuf;
import dev.imanity.generated.net.minecraft.network.codec.StreamCodecHandle;
import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tR$\u0010\r\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0004\u0012\u00020\f0\n8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e" }, d2 = { "Ldev/imanity/antixray/nms/modern/packet/PacketCodecEncoder;", "", "<init>", "()V", "p0", "Lio/netty/buffer/ByteBuf;", "p1", "", "encode", "(Ljava/lang/Object;Lio/netty/buffer/ByteBuf;)V", "Ljava/util/concurrent/ConcurrentHashMap;", "Ljava/lang/Class;", "Ldev/imanity/generated/net/minecraft/network/codec/StreamCodecHandle;", "streamCodecCache", "Ljava/util/concurrent/ConcurrentHashMap;" })
public final class PacketCodecEncoder
{
    @NotNull
    public static final PacketCodecEncoder INSTANCE;
    @NotNull
    private static final ConcurrentHashMap<Class<?>, StreamCodecHandle> streamCodecCache;
    
    private PacketCodecEncoder() {
    }
    
    public final void encode(@NotNull final Object o, @NotNull final ByteBuf byteBuf) {
        Intrinsics.checkNotNullParameter(o, "");
        Intrinsics.checkNotNullParameter((Object)byteBuf, "");
        final StreamCodecHandle computeIfAbsent = PacketCodecEncoder.streamCodecCache.computeIfAbsent(o.getClass(), PacketCodecEncoder::encode$lambda$0);
        Intrinsics.checkNotNullExpressionValue((Object)computeIfAbsent, "");
        computeIfAbsent.encode(byteBuf, o);
    }
    
    private static final StreamCodecHandle encode$lambda$0(final Function1 function1, final Object o) {
        Intrinsics.checkNotNullParameter((Object)function1, "");
        return (StreamCodecHandle)function1.invoke(o);
    }
    
    static {
        INSTANCE = new PacketCodecEncoder();
        streamCodecCache = new ConcurrentHashMap<Class<?>, StreamCodecHandle>();
    }
}
