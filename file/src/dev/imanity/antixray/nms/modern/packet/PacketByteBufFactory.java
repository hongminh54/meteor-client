// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.modern.packet;

import kotlin.jvm.internal.Intrinsics;
import dev.imanity.generated.net.minecraft.network.FriendlyByteBufHandle;
import io.netty.buffer.ByteBuf;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u000b\u001a\u00020\u00008\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n" }, d2 = { "Ldev/imanity/antixray/nms/modern/packet/PacketByteBufFactory;", "", "<init>", "()V", "Lio/netty/buffer/ByteBuf;", "create", "()Lio/netty/buffer/ByteBuf;", "INSTANCE$1", "Ldev/imanity/antixray/nms/modern/packet/PacketByteBufFactory;", "getINSTANCE", "()Ldev/imanity/antixray/nms/modern/packet/PacketByteBufFactory;", "INSTANCE" })
public final class PacketByteBufFactory
{
    @NotNull
    public static final PacketByteBufFactory INSTANCE;
    @NotNull
    private static final PacketByteBufFactory INSTANCE$1;
    
    private PacketByteBufFactory() {
    }
    
    @NotNull
    @JvmName(name = "getINSTANCE")
    public final PacketByteBufFactory getINSTANCE() {
        return PacketByteBufFactory.INSTANCE$1;
    }
    
    @NotNull
    public final ByteBuf create() {
        final ByteBuf packetByteBuf = FriendlyByteBufHandle.createPacketByteBuf();
        Intrinsics.checkNotNullExpressionValue((Object)packetByteBuf, "");
        return packetByteBuf;
    }
    
    static {
        INSTANCE = new PacketByteBufFactory();
        INSTANCE$1 = PacketByteBufFactory.INSTANCE;
    }
}
