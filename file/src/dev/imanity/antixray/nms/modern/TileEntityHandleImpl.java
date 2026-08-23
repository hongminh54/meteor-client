// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.modern;

import org.jetbrains.annotations.Nullable;
import dev.imanity.antixray.nms.modern.packet.PacketCodecEncoder;
import io.fairyproject.libs.packetevents.protocol.packettype.PacketType;
import dev.imanity.antixray.nms.modern.packet.PacketByteBufFactory;
import io.netty.buffer.ByteBuf;
import io.fairyproject.libs.packetevents.protocol.player.ClientVersion;
import dev.imanity.generated.net.minecraft.network.protocol.PacketHandle;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.Lazy;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import dev.imanity.antixray.api.nms.TileEntityHandle;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u0004\u0018\u00010\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000e¨\u0006\u001a" }, d2 = { "Ldev/imanity/antixray/nms/modern/TileEntityHandleImpl;", "Ldev/imanity/antixray/api/nms/TileEntityHandle;", "tileEntityHandle", "Ldev/imanity/generated/net/minecraft/world/level/block/entity/TileEntityHandle;", "(Ldev/imanity/generated/net/minecraft/world/level/block/entity/TileEntityHandle;)V", "updatePacket", "Ldev/imanity/generated/net/minecraft/network/protocol/PacketHandle;", "getUpdatePacket", "()Ldev/imanity/generated/net/minecraft/network/protocol/PacketHandle;", "updatePacket$delegate", "Lkotlin/Lazy;", "x", "", "getX", "()I", "y", "getY", "z", "getZ", "Lio/netty/buffer/ByteBuf;", "version", "Lio/fairyproject/libs/packetevents/protocol/player/ClientVersion;", "writeVarInt", "", "value", "byteBuf", "modern" })
public final class TileEntityHandleImpl implements TileEntityHandle
{
    @NotNull
    private final dev.imanity.generated.net.minecraft.world.level.block.entity.TileEntityHandle tileEntityHandle;
    @NotNull
    private final Lazy updatePacket$delegate;
    
    public TileEntityHandleImpl(@NotNull final dev.imanity.generated.net.minecraft.world.level.block.entity.TileEntityHandle tileEntityHandle) {
        Intrinsics.checkNotNullParameter((Object)tileEntityHandle, "");
        this.tileEntityHandle = tileEntityHandle;
        this.updatePacket$delegate = LazyKt.lazy((Function0)new TileEntityHandleImpl$updatePacket.TileEntityHandleImpl$updatePacket$2(this));
    }
    
    @Override
    public int getX() {
        return this.tileEntityHandle.getPosition_field().getX();
    }
    
    @Override
    public int getY() {
        return this.tileEntityHandle.getPosition_field().getY();
    }
    
    @Override
    public int getZ() {
        return this.tileEntityHandle.getPosition_field().getZ();
    }
    
    private final PacketHandle getUpdatePacket() {
        return (PacketHandle)this.updatePacket$delegate.getValue();
    }
    
    @Nullable
    @Override
    public ByteBuf getUpdatePacket(@NotNull final ClientVersion clientVersion) {
        Intrinsics.checkNotNullParameter((Object)clientVersion, "");
        final PacketHandle updatePacket = this.getUpdatePacket();
        ByteBuf byteBuf;
        if (updatePacket != null) {
            final PacketHandle packetHandle = updatePacket;
            final ByteBuf create = PacketByteBufFactory.INSTANCE.create();
            this.writeVarInt(PacketType.Play.Server.BLOCK_ENTITY_DATA.getId(clientVersion), create);
            if (PacketHandle.T.write.isAvailable()) {
                PacketHandle.T.write.invoke(packetHandle.getRaw(), create);
            }
            else {
                final PacketCodecEncoder instance = PacketCodecEncoder.INSTANCE;
                final Object raw = packetHandle.getRaw();
                Intrinsics.checkNotNullExpressionValue(raw, "");
                instance.encode(raw, create);
            }
            byteBuf = create;
        }
        else {
            byteBuf = null;
        }
        return byteBuf;
    }
    
    private final void writeVarInt(final int n, final ByteBuf byteBuf) {
        int n2;
        for (n2 = n; (n2 & 0xFFFFFF80) != 0x0; n2 >>>= 7) {
            byteBuf.writeByte((n2 & 0x7F) | 0x80);
        }
        byteBuf.writeByte(n2);
    }
}
