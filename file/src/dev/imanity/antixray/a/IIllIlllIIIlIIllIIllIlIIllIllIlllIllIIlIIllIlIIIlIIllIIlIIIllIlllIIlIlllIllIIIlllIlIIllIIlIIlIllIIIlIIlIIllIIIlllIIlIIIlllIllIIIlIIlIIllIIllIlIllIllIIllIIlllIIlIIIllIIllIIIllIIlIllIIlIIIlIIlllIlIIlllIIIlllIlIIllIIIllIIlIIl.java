// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import kotlin.jvm.JvmName;
import io.fairyproject.mc.version.MCVersion;
import io.fairyproject.mc.MCServer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import io.fairyproject.libs.packetevents.wrapper.play.server.WrapperPlayServerUpdateLight;
import io.fairyproject.libs.packetevents.PacketEvents;
import io.netty.channel.Channel;
import io.fairyproject.libs.packetevents.protocol.player.User;
import io.fairyproject.libs.packetevents.protocol.world.chunk.Column;
import io.fairyproject.libs.packetevents.wrapper.PacketWrapper;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import io.fairyproject.libs.packetevents.protocol.world.chunk.BaseChunk;
import kotlin.collections.ArraysKt;
import io.fairyproject.bukkit.util.CoordXZ;
import kotlin.jvm.internal.Lambda;
import dev.imanity.antixray.api.chunk.ChunkData;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.libs.packetevents.protocol.world.chunk.TileEntity;
import kotlin.Lazy;
import org.jetbrains.annotations.Nullable;
import io.fairyproject.libs.packetevents.protocol.world.chunk.LightData;
import io.fairyproject.libs.packetevents.wrapper.play.server.WrapperPlayServerChunkData;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import dev.imanity.antixray.api.chunk.TileEntityHolder;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 !2\u00020\u00012\u00020\u0002:\u0001!B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\f\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0014J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0014R\"\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\"" }, d2 = { "Ldev/imanity/antixray/core/protocol/SingleChunkPacket;", "Ldev/imanity/antixray/core/protocol/ChunkPacket;", "Ldev/imanity/antixray/api/chunk/TileEntityHolder;", "worldHeight", "", "packet", "Lio/fairyproject/libs/packetevents/wrapper/play/server/WrapperPlayServerChunkData;", "lightData", "Lio/fairyproject/libs/packetevents/protocol/world/chunk/LightData;", "(ILcom/github/retrooper/packetevents/wrapper/play/server/WrapperPlayServerChunkData;Lcom/github/retrooper/packetevents/protocol/world/chunk/LightData;)V", "array", "", "Lio/fairyproject/libs/packetevents/protocol/world/chunk/TileEntity;", "getArray", "()[Lcom/github/retrooper/packetevents/protocol/world/chunk/TileEntity;", "setArray", "([Lcom/github/retrooper/packetevents/protocol/world/chunk/TileEntity;)V", "[Lio/fairyproject/libs/packetevents/protocol/world/chunk/TileEntity;", "chunks", "", "Ldev/imanity/antixray/api/chunk/ChunkData;", "getChunks", "()Ljava/util/List;", "chunks$delegate", "Lkotlin/Lazy;", "getChunkPacket", "Lio/fairyproject/libs/packetevents/wrapper/PacketWrapper;", "preSend", "", "user", "Lio/fairyproject/libs/packetevents/protocol/player/User;", "channel", "Lio/netty/channel/Channel;", "Companion", "plugin" })
public final class IIllIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl extends IIllIIlIIlIlIIIlllIIlIIlIIlIllIIlIllIIlllIlllIlllIIllIlIIlllIlIlIIlllIIlIllIlllIIlllIlllIlIIIlllIlllIIIllIIllIlllIIllIIlllIlllIIlllIllIIlIlIllIlIlIlIlIIllIlIllIllIlllIIllIIIlllIlllIllIIllIlIllIlIIlIllIIllIlll implements TileEntityHolder
{
    @NotNull
    public static final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll aW;
    @NotNull
    private final WrapperPlayServerChunkData aX;
    @Nullable
    private final LightData lightData;
    @NotNull
    private final Lazy aY;
    @NotNull
    private TileEntity[] aZ;
    private static final boolean ba;
    
    public IIllIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl(final int n, @NotNull final WrapperPlayServerChunkData ax, @Nullable final LightData lightData) {
        Intrinsics.checkNotNullParameter((Object)ax, "");
        super(n);
        this.aX = ax;
        this.lightData = lightData;
        this.aY = LazyKt.lazy((Function0)new Function0<List<? extends ChunkData>>() {
            final /* synthetic */ IIllIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl bc;
            
            @NotNull
            public final List<ChunkData> IIllIIlllIIIllIlllIIllIIlllIIllIIllIIllIllIIllIlIIlIIllIlIllIIIlllIIIlllIlIIlIIlllIIIlIIlllIIllIIllIlIIIlIIlllIIlIIllIllIIlIlIlllIIlIIIllIIlIIIllIllIIIlIIIlllIIllIIIlIIlIIllIllIlIIlllIIllIIllIlIIllIllIIlllIIlIIllIlIIlIllIl() {
                final CoordXZ coordXZ = new CoordXZ(IIllIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(this.bc).getColumn().getX(), IIllIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(this.bc).getColumn().getZ());
                final BaseChunk[] chunks = IIllIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(this.bc).getColumn().getChunks();
                Intrinsics.checkNotNullExpressionValue((Object)chunks, "");
                return CollectionsKt.listOf((Object)new ChunkData(coordXZ, ArraysKt.asList((Object[])chunks), this.bc));
            }
        });
        final TileEntity[] tileEntities = this.aX.getColumn().getTileEntities();
        Intrinsics.checkNotNullExpressionValue((Object)tileEntities, "");
        this.aZ = tileEntities;
    }
    
    @NotNull
    @Override
    public List<ChunkData> IllIIlllIIIlIIllIIlIIllIIlIIlllIIIllIllIIlllIIlIIllIIllIIIllIlllIlIlIlllIlIIllIIIlllIllIlIIlllIIlllIIllIIIlIIIllIIlIIIlIllIIIllIIllIIllIIlIIIlIIlIIllIllIlIlIIlllIIlIIlIllIllIIllIIlIIIlIIlllIllIIIlllIlllIIlllIllIIlIIIlllIIllIl() {
        return (List)this.aY.getValue();
    }
    
    @NotNull
    @Override
    protected PacketWrapper<?> IIllIIIlllIIllIlllIllIlIllIlllIIIlIIllIIIlIIlIIIlIIlllIIllIlllIIlIIllIIlIIllIIlIllIllIIllIllIlIlIIlIIIllIIllIlIIlIlllIIlllIIIlllIIlIIIllIIIlIIlIIlIIllIIIlllIIIlIIIlllIIlIllIIlIIIllIllIIllIIllIlIlIIIlllIlllIIlIIlllIlIllIIl() {
        final Column column = this.aX.getColumn();
        Column iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;
        if (this.getArray() == column.getTileEntities()) {
            iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll = column;
        }
        else {
            final IlIllIIlIIlllIllIIllIIIlIIllIIlIlIIllIIlIIlIIIlIllIllIIIlIIIlllIIllIIllIllIIllIllIIlllIIlIIIlIIIllIIIllIlllIIllIIIlllIlIIIlIIlllIllIIlIlIllIIlllIIlIIllIIllIIIllIIlllIIIlIIlIlIIllIIIllIIlllIIllIIIlIlllIIllIllIIIlIllIIllIlIIlll aa = IlIllIIlIIlllIllIIllIIIlIIllIIlIlIIllIIlIIlIIIlIllIllIIIlIIIlllIIllIIllIllIIllIllIIlllIIlIIIlIIIllIIIllIlllIIllIIIlllIlIIIlIIlllIllIIlIlIllIIlllIIlIIllIIllIIIllIIlllIIIlIIlIlIIllIIIllIIlllIIllIIIlIlllIIllIllIIIlIllIIllIlIIlll.aA;
            Intrinsics.checkNotNull((Object)column);
            iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll = aa.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(column, this.getArray());
        }
        final WrapperPlayServerChunkData wrapperPlayServerChunkData = new WrapperPlayServerChunkData(iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        if (!IIllIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl.ba) {
            final WrapperPlayServerChunkData wrapperPlayServerChunkData2 = wrapperPlayServerChunkData;
            LightData lightData;
            if ((lightData = this.lightData) == null) {
                lightData = this.aX.getLightData();
            }
            wrapperPlayServerChunkData2.setLightData(lightData);
        }
        wrapperPlayServerChunkData.setIgnoreOldData(this.aX.isIgnoreOldData());
        return (PacketWrapper<?>)wrapperPlayServerChunkData;
    }
    
    @Override
    protected void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final User user, @NotNull final Channel channel) {
        Intrinsics.checkNotNullParameter((Object)user, "");
        Intrinsics.checkNotNullParameter((Object)channel, "");
        if (IIllIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl.ba && this.lightData != null) {
            PacketEvents.getAPI().getProtocolManager().writePacketSilently((Object)channel, (PacketWrapper)new WrapperPlayServerUpdateLight(this.aX.getColumn().getX(), this.aX.getColumn().getZ(), this.lightData));
        }
    }
    
    @NotNull
    @Override
    public TileEntity[] getArray() {
        return this.aZ;
    }
    
    @Override
    public void setArray(@NotNull final TileEntity[] az) {
        Intrinsics.checkNotNullParameter((Object)az, "");
        this.aZ = az;
    }
    
    public static final /* synthetic */ boolean IIllIIIlIlllIIIllIllIIIllIIlllIIllIIlllIllIllIlIIlIIIllIllIIllIIllIIllIIIllIlllIIllIllIllIIlllIllIIllIIlllIIlllIIlIIlIlIlIIllIIllIIIllIIlIIIlllIllIIIllIIIllIIIllIllIIlIIllIIlllIlllIIlIllIIIllIIIllIIllIIlIIllIlIllIllIIlllIIlllIllIIIll() {
        return IIllIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl.ba;
    }
    
    public static final /* synthetic */ WrapperPlayServerChunkData IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final IIllIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl illIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl) {
        return illIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl.aX;
    }
    
    static {
        aW = new IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(null);
        ba = MCServer.current().getVersion().isBetween(MCVersion.of(16, 0), MCVersion.of(17, 2));
    }
    
    @Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\t\u001a\u00020\u00048\u0007¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b" }, d2 = { "Ldev/imanity/antixray/a/IIllIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl$IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;", "", "<init>", "()V", "", "ba", "Z", "IllIIlllIllIIIllIIlIIlIIllIIlllIIllIIIlIlIllIllIIllIllIllIIlIIlllIIlIllIlllIIllIlllIlIlllIIlllIIlllIlllIIlIlllIllIllIIIlIlIIllIIllIIllIllIIlllIlIIlllIIlllIIlllIIllIIlllIIlIIlllIlIIllIllIlIllIIIlllIllIIIlllIIllIlllIIlllIlIIll", "()Z", "IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll" })
    public static final class IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll
    {
        private IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll() {
        }
        
        @JvmName(name = "IllIIlllIllIIIllIIlIIlIIllIIlllIIllIIIlIlIllIllIIllIllIllIIlIIlllIIlIllIlllIIllIlllIlIlllIIlllIIlllIlllIIlIlllIllIllIIIlIlIIllIIllIIllIllIIlllIlIIlllIIlllIIlllIIllIIlllIIlIIlllIlIIllIllIlIllIIIlllIllIIIlllIIllIlllIIlllIlIIll")
        public final boolean IllIIlllIllIIIllIIlIIlIIllIIlllIIllIIIlIlIllIllIIllIllIllIIlIIlllIIlIllIlllIIllIlllIlIlllIIlllIIlllIlllIIlIlllIllIllIIIlIlIIllIIllIIllIllIIlllIlIIlllIIlllIIlllIIllIIlllIIlIIlllIlIIllIllIlIllIIIlllIllIIIlllIIllIlllIIlllIlIIll() {
            return IIllIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl.IIllIIIlIlllIIIllIllIIIllIIlllIIllIIlllIllIllIlIIlIIIllIllIIllIIllIIllIIIllIlllIIllIllIllIIlllIllIIllIIlllIIlllIIlIIlIlIlIIllIIllIIIllIIlIIIlllIllIIIllIIIllIIIllIllIIlIIllIIlllIlllIIlIllIIIllIIIllIIllIIlIIllIlIllIllIIlllIIlllIllIIIll();
        }
    }
}
