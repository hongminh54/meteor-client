// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import io.fairyproject.libs.packetevents.manager.server.ServerVersion;
import io.fairyproject.libs.packetevents.PacketEvents;
import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.libs.packetevents.protocol.world.chunk.TileEntity;
import io.fairyproject.libs.packetevents.protocol.world.chunk.Column;
import org.jetbrains.annotations.NotNull;

public final class IlIllIIlIIlllIllIIllIIIlIIllIIlIlIIllIIlIIlIIIlIllIllIIIlIIIlllIIllIIllIllIIllIllIIlllIIlIIIlIIIllIIIllIlllIIllIIIlllIlIIIlIIlllIllIIlIlIllIIlllIIlIIllIIllIIIllIIlllIIIlIIlIlIIllIIIllIIlllIIllIIIlIlllIIllIllIIIlIllIIllIlIIlll
{
    @NotNull
    public static final IlIllIIlIIlllIllIIllIIIlIIllIIlIlIIllIIlIIlIIIlIllIllIIIlIIIlllIIllIIllIllIIllIllIIlllIIlIIIlIIIllIIIllIlllIIllIIIlllIlIIIlIIlllIllIIlIlIllIIlllIIlIIllIIllIIIllIIlllIIIlIIlIlIIllIIIllIIlllIIllIIIlIlllIIllIllIIIlIllIIllIlIIlll aA;
    
    private IlIllIIlIIlllIllIIllIIIlIIllIIlIlIIllIIlIIlIIIlIllIllIIIlIIIlllIIllIIllIllIIllIllIIlllIIlIIIlIIIllIIIllIlllIIllIIIlllIlIIIlIIlllIllIIlIlIllIIlllIIlIIllIIllIIIllIIlllIIIlIIlIlIIllIIIllIIlllIIllIIIlIlllIIllIllIIIlIllIIllIlIIlll() {
    }
    
    @NotNull
    public final Column IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Column column, @NotNull final TileEntity[] array) {
        Intrinsics.checkNotNullParameter((Object)column, "");
        Intrinsics.checkNotNullParameter((Object)array, "");
        Column column2;
        if (column.hasBiomeData()) {
            final boolean olderThan = PacketEvents.getAPI().getServerManager().getVersion().isOlderThan(ServerVersion.V_1_13);
            column2 = (column.hasHeightMaps() ? (olderThan ? new Column(column.getX(), column.getZ(), column.isFullChunk(), column.getChunks(), array, column.getHeightMaps(), column.getBiomeDataBytes()) : new Column(column.getX(), column.getZ(), column.isFullChunk(), column.getChunks(), array, column.getHeightMaps(), column.getBiomeDataInts())) : (olderThan ? new Column(column.getX(), column.getZ(), column.isFullChunk(), column.getChunks(), array, column.getBiomeDataBytes()) : new Column(column.getX(), column.getZ(), column.isFullChunk(), column.getChunks(), array, column.getBiomeDataInts())));
        }
        else {
            column2 = (column.hasHeightMaps() ? new Column(column.getX(), column.getZ(), column.isFullChunk(), column.getChunks(), array, column.getHeightMaps()) : new Column(column.getX(), column.getZ(), column.isFullChunk(), column.getChunks(), array));
        }
        return column2;
    }
    
    static {
        aA = new IlIllIIlIIlllIllIIllIIIlIIllIIlIlIIllIIlIIlIIIlIllIllIIIlIIIlllIIllIIllIllIIllIllIIlllIIlIIIlIIIllIIIllIlllIIllIIIlllIlIIIlIIlllIllIIlIlIllIIlllIIlIIllIIllIIIllIIlllIIIlIIlIlIIllIIIllIIlllIIllIIIlIlllIIllIllIIIlIllIIllIlIIlll();
    }
}
