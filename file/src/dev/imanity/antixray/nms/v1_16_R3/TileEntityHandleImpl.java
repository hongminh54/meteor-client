// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.v1_16_R3;

import org.jetbrains.annotations.Nullable;
import net.minecraft.server.v1_16_R3.PacketPlayOutTileEntityData;
import io.fairyproject.libs.packetevents.protocol.packettype.PacketType;
import net.minecraft.server.v1_16_R3.PacketDataSerializer;
import io.netty.buffer.Unpooled;
import io.netty.buffer.ByteBuf;
import io.fairyproject.libs.packetevents.protocol.player.ClientVersion;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import net.minecraft.server.v1_16_R3.TileEntity;
import kotlin.Metadata;
import dev.imanity.antixray.api.nms.TileEntityHandle;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0014\u0010\u000b\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\b¨\u0006\u0011" }, d2 = { "Ldev/imanity/antixray/nms/v1_16_R3/TileEntityHandleImpl;", "Ldev/imanity/antixray/api/nms/TileEntityHandle;", "blockEntity", "Lnet/minecraft/server/v1_16_R3/TileEntity;", "(Lnet/minecraft/server/v1_16_R3/TileEntity;)V", "x", "", "getX", "()I", "y", "getY", "z", "getZ", "getUpdatePacket", "Lio/netty/buffer/ByteBuf;", "version", "Lio/fairyproject/libs/packetevents/protocol/player/ClientVersion;", "v1_16_5" })
public final class TileEntityHandleImpl implements TileEntityHandle
{
    @NotNull
    private final TileEntity blockEntity;
    
    public TileEntityHandleImpl(@NotNull final TileEntity blockEntity) {
        Intrinsics.checkNotNullParameter((Object)blockEntity, "");
        this.blockEntity = blockEntity;
    }
    
    @Override
    public int getX() {
        return this.blockEntity.getPosition().getX();
    }
    
    @Override
    public int getY() {
        return this.blockEntity.getPosition().getY();
    }
    
    @Override
    public int getZ() {
        return this.blockEntity.getPosition().getZ();
    }
    
    @Nullable
    @Override
    public ByteBuf getUpdatePacket(@NotNull final ClientVersion clientVersion) {
        Intrinsics.checkNotNullParameter((Object)clientVersion, "");
        final PacketPlayOutTileEntityData updatePacket = this.blockEntity.getUpdatePacket();
        PacketDataSerializer packetDataSerializer2;
        if (updatePacket != null) {
            final PacketPlayOutTileEntityData packetPlayOutTileEntityData = updatePacket;
            final PacketDataSerializer packetDataSerializer = new PacketDataSerializer(Unpooled.buffer());
            packetDataSerializer.d(PacketType.Play.Server.BLOCK_ENTITY_DATA.getId(clientVersion));
            packetPlayOutTileEntityData.b(packetDataSerializer);
            packetDataSerializer2 = packetDataSerializer;
        }
        else {
            packetDataSerializer2 = null;
        }
        return (ByteBuf)packetDataSerializer2;
    }
}
