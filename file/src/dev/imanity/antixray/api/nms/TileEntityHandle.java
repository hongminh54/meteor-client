// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.nms;

import org.jetbrains.annotations.Nullable;
import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.libs.packetevents.protocol.player.ClientVersion;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005¨\u0006\u000e" }, d2 = { "Ldev/imanity/antixray/api/nms/TileEntityHandle;", "", "x", "", "getX", "()I", "y", "getY", "z", "getZ", "getUpdatePacket", "Lio/netty/buffer/ByteBuf;", "version", "Lio/fairyproject/libs/packetevents/protocol/player/ClientVersion;", "api" })
public interface TileEntityHandle
{
    int getX();
    
    int getY();
    
    int getZ();
    
    @Nullable
    ByteBuf getUpdatePacket(@NotNull final ClientVersion p0);
}
