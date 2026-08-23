// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.internal.wrapper;

import io.fairyproject.libs.packetevents.protocol.world.chunk.reader.ChunkReader;
import io.fairyproject.libs.packetevents.protocol.world.chunk.NetworkChunkData;
import java.util.zip.Deflater;
import io.fairyproject.libs.packetevents.protocol.world.chunk.impl.v1_7.Chunk_v1_7;
import io.fairyproject.libs.packetevents.protocol.world.chunk.impl.v1_8.Chunk_v1_8;
import io.fairyproject.libs.packetevents.protocol.world.chunk.impl.v1_16.Chunk_v1_9;
import io.fairyproject.libs.packetevents.protocol.world.chunk.impl.v_1_18.Chunk_v1_18;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.Map;
import io.fairyproject.libs.packetevents.protocol.nbt.NBTCompound;
import java.util.BitSet;
import io.fairyproject.libs.packetevents.protocol.world.chunk.BaseChunk;
import io.fairyproject.libs.packetevents.protocol.world.chunk.TileEntity;
import io.fairyproject.libs.packetevents.netty.buffer.ByteBufHelper;
import io.fairyproject.libs.packetevents.netty.buffer.UnpooledByteBufAllocationHelper;
import io.fairyproject.libs.packetevents.protocol.world.chunk.HeightmapType;
import io.fairyproject.libs.packetevents.protocol.world.chunk.ChunkBitMask;
import io.fairyproject.libs.packetevents.manager.server.ServerVersion;
import io.fairyproject.libs.packetevents.protocol.packettype.PacketTypeCommon;
import io.fairyproject.libs.packetevents.protocol.packettype.PacketType;
import io.fairyproject.libs.packetevents.event.PacketSendEvent;
import io.fairyproject.libs.packetevents.protocol.world.chunk.LightData;
import io.fairyproject.libs.packetevents.protocol.world.chunk.Column;
import io.fairyproject.libs.packetevents.protocol.world.chunk.reader.impl.ChunkReader_v1_18;
import io.fairyproject.libs.packetevents.protocol.world.chunk.reader.impl.ChunkReader_v1_16;
import io.fairyproject.libs.packetevents.protocol.world.chunk.reader.impl.ChunkReader_v1_9;
import io.fairyproject.libs.packetevents.protocol.world.chunk.reader.impl.ChunkReader_v1_8;
import io.fairyproject.libs.packetevents.protocol.world.chunk.reader.impl.ChunkReader_v1_7;
import io.fairyproject.libs.packetevents.wrapper.PacketWrapper;

public class WrapperPlayServerChunkData extends PacketWrapper<WrapperPlayServerChunkData>
{
    private static ChunkReader_v1_7 chunkReader_v1_7;
    private static ChunkReader_v1_8 chunkReader_v1_8;
    private static ChunkReader_v1_9 chunkReader_v1_9;
    private static ChunkReader_v1_16 chunkReader_v1_16;
    private static ChunkReader_v1_18 chunkReader_v1_18;
    private Column column;
    private LightData lightData;
    private boolean ignoreOldData;
    
    public WrapperPlayServerChunkData(final PacketSendEvent packetSendEvent) {
        super(packetSendEvent);
    }
    
    public WrapperPlayServerChunkData(final Column column) {
        this(column, null, false);
    }
    
    public WrapperPlayServerChunkData(final Column column, final LightData lightData) {
        this(column, lightData, false);
    }
    
    public WrapperPlayServerChunkData(final Column column, final LightData lightData, final boolean ignoreOldData) {
        super((PacketTypeCommon)PacketType.Play.Server.CHUNK_DATA);
        this.column = column;
        this.lightData = lightData;
        this.ignoreOldData = ignoreOldData;
    }
    
    public void read() {
        final int int1 = this.readInt();
        final int int2 = this.readInt();
        final boolean b = !this.serverVersion.isOlderThan(ServerVersion.V_1_17) || this.readBoolean();
        if (this.serverVersion == ServerVersion.V_1_16 || this.serverVersion == ServerVersion.V_1_16_1) {
            this.ignoreOldData = this.readBoolean();
        }
        final BitSet set = this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_18) ? null : ChunkBitMask.readChunkMask((PacketWrapper)this);
        final boolean newerThanOrEquals = this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_14);
        NBTCompound nbt = null;
        Map map = null;
        if (newerThanOrEquals) {
            if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_21_5)) {
                map = this.readMap(HeightmapType::read, PacketWrapper::readLongArray);
            }
            else {
                nbt = this.readNBT();
            }
        }
        BitSet chunkMask = null;
        if (this.serverVersion.isOlderThanOrEquals(ServerVersion.V_1_7_10)) {
            chunkMask = ChunkBitMask.readChunkMask((PacketWrapper)this);
        }
        int n = 16;
        if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_17)) {
            n = this.user.getTotalWorldHeight() >> 4;
        }
        final boolean b2 = b && this.serverVersion.isOlderThan(ServerVersion.V_1_18);
        final boolean olderThan = this.serverVersion.isOlderThan(ServerVersion.V_1_13);
        int[] varIntArray = null;
        byte[] bytes = null;
        if (b2 && this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_16_2)) {
            varIntArray = this.readVarIntArray();
        }
        else if (b2 && this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_15)) {
            varIntArray = new int[1024];
            for (int i = 0; i < varIntArray.length; ++i) {
                varIntArray[i] = this.readInt();
            }
        }
        final boolean b3 = (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_16) || this.serverVersion.isOlderThan(ServerVersion.V_1_14)) && !this.serverVersion.isOlderThanOrEquals(ServerVersion.V_1_8_8);
        final boolean b4 = this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_16) || this.serverVersion.isOlderThanOrEquals(ServerVersion.V_1_8_8) || (this.user != null && this.user.getDimensionType().hasSkyLight() && this.serverVersion.isOlderThan(ServerVersion.V_1_14));
        final Object buffer = this.buffer;
        int n2;
        if (this.serverVersion.isOlderThanOrEquals(ServerVersion.V_1_7_10)) {
            final Object o = this.inflate(this.readByteArray(), set, b);
            this.buffer = UnpooledByteBufAllocationHelper.wrappedBuffer((byte[])o);
            n2 = ((BaseChunk[])o).length;
        }
        else {
            n2 = this.readVarInt();
        }
        Object o;
        try {
            final int j = ByteBufHelper.readerIndex(this.buffer) + n2;
            o = this.getChunkReader().read(this.user.getDimensionType(), set, chunkMask, b, b3, b4, n, n2, (PacketWrapper)this);
            if (b2 && this.serverVersion.isOlderThan(ServerVersion.V_1_15)) {
                if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_13)) {
                    varIntArray = new int[256];
                    for (int k = 0; k < varIntArray.length; ++k) {
                        varIntArray[k] = this.readInt();
                    }
                }
                else if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_9)) {
                    bytes = new byte[256];
                    for (int l = 0; l < bytes.length; ++l) {
                        bytes[l] = this.readByte();
                    }
                }
                else if (n2 == 0) {
                    bytes = new byte[0];
                }
                else {
                    bytes = this.readBytes(256);
                }
            }
            final int readerIndex = ByteBufHelper.readerIndex(this.buffer);
            if (j != readerIndex) {
                throw new RuntimeException("Error while decoding chunk at " + int1 + " " + int2 + "; expected reader index " + j + ", got " + readerIndex);
            }
        }
        finally {
            if (this.buffer != buffer) {
                ByteBufHelper.release(this.buffer);
                this.buffer = buffer;
            }
        }
        final TileEntity[] array = new TileEntity[this.serverVersion.isOlderThan(ServerVersion.V_1_9) ? 0 : this.readVarInt()];
        if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_18)) {
            for (int n3 = 0; n3 < array.length; ++n3) {
                array[n3] = new TileEntity(this.readByte(), this.readShort(), this.readVarInt(), this.readNBT());
            }
        }
        else {
            for (int n4 = 0; n4 < array.length; ++n4) {
                array[n4] = new TileEntity(this.readNBT());
            }
        }
        if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_18)) {
            this.lightData = LightData.read((PacketWrapper)this);
        }
        if (b2) {
            if (newerThanOrEquals) {
                if (olderThan) {
                    this.column = new Column(int1, int2, true, (BaseChunk[])o, array, nbt, bytes);
                }
                else {
                    this.column = new Column(int1, int2, true, (BaseChunk[])o, array, nbt, varIntArray);
                }
            }
            else if (olderThan) {
                this.column = new Column(int1, int2, true, (BaseChunk[])o, array, bytes);
            }
            else {
                this.column = new Column(int1, int2, true, (BaseChunk[])o, array, varIntArray);
            }
        }
        else if (newerThanOrEquals) {
            if (map != null) {
                this.column = new Column(int1, int2, b, (BaseChunk[])o, array, map);
            }
            else {
                this.column = new Column(int1, int2, b, (BaseChunk[])o, array, nbt);
            }
        }
        else {
            this.column = new Column(int1, int2, b, (BaseChunk[])o, array);
        }
    }
    
    private byte[] inflate(final byte[] input, final BitSet set, final boolean b) {
        int n = 0;
        for (int i = 0; i < 16; ++i) {
            n += (set.get(i) ? 1 : 0);
        }
        int n2 = 12288 * n;
        if (b) {
            n2 += 256;
        }
        final byte[] output = new byte[n2];
        final Inflater inflater = new Inflater();
        inflater.setInput(input, 0, input.length);
        try {
            inflater.inflate(output);
        }
        catch (final DataFormatException ex) {
            ex.printStackTrace();
        }
        finally {
            inflater.end();
        }
        return output;
    }
    
    public void write() {
        this.writeInt(this.column.getX());
        this.writeInt(this.column.getZ());
        final boolean newerThanOrEquals = this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_18);
        final boolean newerThanOrEquals2 = this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_17);
        final boolean newerThanOrEquals3 = this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_9);
        final boolean newerThanOrEquals4 = this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_8);
        if (!newerThanOrEquals2) {
            this.writeBoolean(this.column.isFullChunk());
        }
        boolean b = false;
        if (this.serverVersion == ServerVersion.V_1_16 || this.serverVersion == ServerVersion.V_1_16_1) {
            this.writeBoolean(this.ignoreOldData);
        }
        final BitSet set = new BitSet();
        final BaseChunk[] chunks = this.column.getChunks();
        if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_9)) {
            final Object buffer = this.buffer;
            final Object allocateNewBuffer = ByteBufHelper.allocateNewBuffer(this.buffer);
            this.buffer = allocateNewBuffer;
            for (int i = 0; i < chunks.length; ++i) {
                final BaseChunk baseChunk = chunks[i];
                if (newerThanOrEquals) {
                    Chunk_v1_18.write((PacketWrapper)this, (Chunk_v1_18)baseChunk);
                }
                else if (newerThanOrEquals3 && baseChunk != null) {
                    set.set(i);
                    Chunk_v1_9.write((PacketWrapper)this, (Chunk_v1_9)baseChunk);
                }
            }
            this.buffer = buffer;
            if (this.serverVersion.isOlderThan(ServerVersion.V_1_21_6) && this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_21_5)) {
                final int n = ByteBufHelper.writerIndex(allocateNewBuffer) + ChunkReader_v1_18.getMojangZeroByteSuffixLength(chunks);
                if (n > ByteBufHelper.capacity(allocateNewBuffer)) {
                    ByteBufHelper.capacity(allocateNewBuffer, n);
                }
                ByteBufHelper.writerIndex(allocateNewBuffer, n);
            }
            if (this.column.isFullChunk() && this.serverVersion.isOlderThan(ServerVersion.V_1_15)) {
                if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_13)) {
                    final int[] biomeDataInts = this.column.getBiomeDataInts();
                    for (int length = biomeDataInts.length, j = 0; j < length; ++j) {
                        ByteBufHelper.writeInt(allocateNewBuffer, biomeDataInts[j]);
                    }
                }
                else {
                    final byte[] biomeDataBytes = this.column.getBiomeDataBytes();
                    for (int length2 = biomeDataBytes.length, k = 0; k < length2; ++k) {
                        ByteBufHelper.writeByte(allocateNewBuffer, (int)biomeDataBytes[k]);
                    }
                }
                b = true;
            }
            if (!newerThanOrEquals) {
                ChunkBitMask.writeChunkMask((PacketWrapper)this, set);
            }
            if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_14)) {
                if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_21_5)) {
                    this.writeMap(this.column.getHeightmaps(), HeightmapType::write, PacketWrapper::writeLongArray);
                }
                else {
                    this.writeNBT(this.column.getHeightMaps());
                }
            }
            if (this.column.hasBiomeData() && this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_15) && !newerThanOrEquals) {
                final boolean olderThan = this.serverVersion.isOlderThan(ServerVersion.V_1_13);
                final int[] biomeDataInts2 = this.column.getBiomeDataInts();
                final byte[] biomeDataBytes2 = this.column.getBiomeDataBytes();
                if (olderThan) {
                    final byte[] array = biomeDataBytes2;
                    for (int length3 = array.length, l = 0; l < length3; ++l) {
                        this.writeByte((int)array[l]);
                    }
                }
                else if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_16_2)) {
                    this.writeVarIntArray(biomeDataInts2);
                }
                else {
                    final int[] array2 = biomeDataInts2;
                    for (int length4 = array2.length, n2 = 0; n2 < length4; ++n2) {
                        this.writeInt(array2[n2]);
                    }
                }
                b = true;
            }
            this.writeVarInt(ByteBufHelper.readableBytes(allocateNewBuffer));
            ByteBufHelper.writeBytes(this.buffer, allocateNewBuffer);
            ByteBufHelper.release(allocateNewBuffer);
            if (this.column.hasBiomeData() && !b) {
                final byte[] array3 = new byte[256];
                final int[] biomeDataInts3 = this.column.getBiomeDataInts();
                for (int n3 = 0; n3 < array3.length; ++n3) {
                    array3[n3] = (byte)biomeDataInts3[n3];
                }
                this.writeByteArray(array3);
            }
            if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_18)) {
                this.writeVarInt(this.column.getTileEntities().length);
                for (final TileEntity tileEntity : this.column.getTileEntities()) {
                    this.writeByte((int)tileEntity.getPackedByte());
                    this.writeShort((int)tileEntity.getYShort());
                    this.writeVarInt(tileEntity.getType());
                    this.writeNBT(tileEntity.getNBT());
                }
            }
            else if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_9)) {
                final TileEntity[] tileEntities2 = this.column.getTileEntities();
                this.writeVarInt(tileEntities2.length);
                final TileEntity[] array4 = tileEntities2;
                for (int length6 = array4.length, n5 = 0; n5 < length6; ++n5) {
                    this.writeNBT(array4[n5].getNBT());
                }
            }
            if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_18)) {
                LightData.write((PacketWrapper)this, this.lightData);
            }
            return;
        }
        if (newerThanOrEquals4) {
            final NetworkChunkData chunksToData = ChunkReader_v1_8.chunksToData((Chunk_v1_8[])chunks, this.column.getBiomeDataBytes());
            this.writeShort(chunksToData.getMask());
            this.writeByteArray(chunksToData.getData());
            return;
        }
        final NetworkChunkData chunksToData2 = ChunkReader_v1_7.chunksToData((Chunk_v1_7[])chunks, this.column.getBiomeDataBytes());
        final Deflater deflater = new Deflater(-1);
        final byte[] output = new byte[chunksToData2.getData().length];
        int deflate;
        try {
            deflater.setInput(chunksToData2.getData(), 0, chunksToData2.getData().length);
            deflater.finish();
            deflate = deflater.deflate(output);
        }
        finally {
            deflater.end();
        }
        this.writeShort(chunksToData2.getMask());
        this.writeShort(chunksToData2.getExtendedChunkMask());
        this.writeInt(deflate);
        ByteBufHelper.writeBytes(this.buffer, output, 0, deflate);
    }
    
    public void copy(final WrapperPlayServerChunkData wrapperPlayServerChunkData) {
        this.column = wrapperPlayServerChunkData.column;
        this.lightData = ((wrapperPlayServerChunkData.lightData != null) ? wrapperPlayServerChunkData.lightData.clone() : null);
        this.ignoreOldData = wrapperPlayServerChunkData.ignoreOldData;
    }
    
    public Column getColumn() {
        return this.column;
    }
    
    public void setColumn(final Column column) {
        this.column = column;
    }
    
    public LightData getLightData() {
        return this.lightData;
    }
    
    public void setLightData(final LightData lightData) {
        this.lightData = lightData;
    }
    
    public boolean isIgnoreOldData() {
        return this.ignoreOldData;
    }
    
    public void setIgnoreOldData(final boolean ignoreOldData) {
        this.ignoreOldData = ignoreOldData;
    }
    
    private ChunkReader getChunkReader() {
        if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_18)) {
            return (ChunkReader)WrapperPlayServerChunkData.chunkReader_v1_18;
        }
        if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_16)) {
            return (ChunkReader)WrapperPlayServerChunkData.chunkReader_v1_16;
        }
        if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_9)) {
            return (ChunkReader)WrapperPlayServerChunkData.chunkReader_v1_9;
        }
        if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_8)) {
            return (ChunkReader)WrapperPlayServerChunkData.chunkReader_v1_8;
        }
        return (ChunkReader)WrapperPlayServerChunkData.chunkReader_v1_7;
    }
    
    static {
        WrapperPlayServerChunkData.chunkReader_v1_7 = new ChunkReader_v1_7();
        WrapperPlayServerChunkData.chunkReader_v1_8 = new ChunkReader_v1_8();
        WrapperPlayServerChunkData.chunkReader_v1_9 = new ChunkReader_v1_9();
        WrapperPlayServerChunkData.chunkReader_v1_16 = new ChunkReader_v1_16();
        WrapperPlayServerChunkData.chunkReader_v1_18 = new ChunkReader_v1_18();
    }
}
