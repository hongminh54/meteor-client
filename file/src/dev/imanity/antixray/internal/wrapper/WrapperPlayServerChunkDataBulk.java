// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.internal.wrapper;

import java.util.zip.Deflater;
import io.fairyproject.libs.packetevents.protocol.world.chunk.impl.v1_7.Chunk_v1_7;
import io.fairyproject.libs.packetevents.protocol.world.chunk.impl.v1_8.Chunk_v1_8;
import java.util.Arrays;
import io.fairyproject.libs.packetevents.protocol.world.chunk.reader.impl.ChunkReader_v1_7;
import java.util.zip.DataFormatException;
import java.io.IOException;
import java.util.zip.Inflater;
import io.fairyproject.libs.packetevents.protocol.stream.NetStreamInput;
import java.util.BitSet;
import io.fairyproject.libs.packetevents.protocol.world.chunk.reader.impl.ChunkReader_v1_8;
import io.fairyproject.libs.packetevents.protocol.world.chunk.NetworkChunkData;
import io.fairyproject.libs.packetevents.manager.server.ServerVersion;
import io.fairyproject.libs.packetevents.protocol.packettype.PacketTypeCommon;
import io.fairyproject.libs.packetevents.protocol.packettype.PacketType;
import io.fairyproject.libs.packetevents.event.PacketSendEvent;
import io.fairyproject.libs.packetevents.protocol.world.chunk.BaseChunk;
import io.fairyproject.libs.packetevents.wrapper.PacketWrapper;

public class WrapperPlayServerChunkDataBulk extends PacketWrapper<WrapperPlayServerChunkDataBulk>
{
    private int[] x;
    private int[] z;
    private BaseChunk[][] chunks;
    private byte[][] biomeData;
    
    public WrapperPlayServerChunkDataBulk(final PacketSendEvent packetSendEvent) {
        super(packetSendEvent);
    }
    
    public WrapperPlayServerChunkDataBulk(final WrapperPlayServerChunkDataBulk wrapperPlayServerChunkDataBulk) {
        super((PacketTypeCommon)PacketType.Play.Server.MAP_CHUNK_BULK);
        this.copy(wrapperPlayServerChunkDataBulk);
    }
    
    public void read() {
        if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_8)) {
            this.read_1_8();
        }
        else {
            this.read_1_7();
        }
    }
    
    private void read_1_8() {
        final boolean boolean1 = this.readBoolean();
        final int varInt = this.readVarInt();
        this.x = new int[varInt];
        this.z = new int[varInt];
        this.chunks = new BaseChunk[varInt][];
        this.biomeData = new byte[varInt][];
        final NetworkChunkData[] array = new NetworkChunkData[varInt];
        for (int i = 0; i < varInt; ++i) {
            this.x[i] = this.readInt();
            this.z[i] = this.readInt();
            final int unsignedShort = this.readUnsignedShort();
            final int bitCount = Integer.bitCount(unsignedShort);
            array[i] = new NetworkChunkData(unsignedShort, true, boolean1, new byte[bitCount * 10240 + (boolean1 ? (bitCount * 2048) : 0)]);
        }
        for (int j = 0; j < varInt; ++j) {
            array[j].setData(this.readBytes(array[j].getData().length));
            this.chunks[j] = new ChunkReader_v1_8().read(this.user.getDimensionType(), BitSet.valueOf(new long[] { array[j].getMask() }), (BitSet)null, true, boolean1, false, 16, array[j].getData(), (NetStreamInput)null);
            this.biomeData[j] = this.readBytes(256);
        }
    }
    
    private void read_1_7() {
        final short short1 = this.readShort();
        final int int1 = this.readInt();
        final boolean boolean1 = this.readBoolean();
        final byte[] bytes = this.readBytes(int1);
        final byte[] output = new byte[196864 * short1];
        final Inflater inflater = new Inflater();
        inflater.setInput(bytes, 0, int1);
        try {
            inflater.inflate(output);
        }
        catch (final DataFormatException ex) {
            new IOException("Bad compressed data format").printStackTrace();
            return;
        }
        finally {
            inflater.end();
        }
        this.x = new int[short1];
        this.z = new int[short1];
        this.chunks = new BaseChunk[short1][];
        this.biomeData = new byte[short1][];
        int n = 0;
        for (short n2 = 0; n2 < short1; ++n2) {
            final int int2 = this.readInt();
            final int int3 = this.readInt();
            final BitSet value = BitSet.valueOf(new long[] { this.readUnsignedShort() });
            final BitSet value2 = BitSet.valueOf(new long[] { this.readUnsignedShort() });
            int n3 = 0;
            int n4 = 0;
            for (int i = 0; i < 16; ++i) {
                n3 += (value.get(i) ? 1 : 0);
                n4 += (value2.get(i) ? 1 : 0);
            }
            int n5 = 8192 * n3 + 256 + 2048 * n4;
            if (boolean1) {
                n5 += 2048 * n3;
            }
            final byte[] original = new byte[n5];
            System.arraycopy(output, n, original, 0, n5);
            final BaseChunk[] read = new ChunkReader_v1_7().read(this.user.getDimensionType(), value, value2, true, boolean1, false, 16, original, (NetStreamInput)null);
            final byte[] copyOfRange = Arrays.copyOfRange(original, original.length - 256, original.length);
            this.x[n2] = int2;
            this.z[n2] = int3;
            this.chunks[n2] = read;
            this.biomeData[n2] = copyOfRange;
            n += n5;
        }
    }
    
    public void write() {
        if (this.serverVersion.isNewerThanOrEquals(ServerVersion.V_1_8)) {
            this.write_1_8();
        }
        else {
            this.write_1_7();
        }
    }
    
    public void copy(final WrapperPlayServerChunkDataBulk wrapperPlayServerChunkDataBulk) {
        this.x = wrapperPlayServerChunkDataBulk.x;
        this.z = wrapperPlayServerChunkDataBulk.z;
        this.chunks = wrapperPlayServerChunkDataBulk.chunks;
        this.biomeData = wrapperPlayServerChunkDataBulk.biomeData;
    }
    
    private void write_1_8() {
        boolean b = false;
        final NetworkChunkData[] array = new NetworkChunkData[this.chunks.length];
        for (int i = 0; i < this.chunks.length; ++i) {
            array[i] = ChunkReader_v1_8.chunksToData((Chunk_v1_8[])this.chunks[i], this.biomeData[i]);
            if (array[i].hasSkyLight()) {
                b = true;
            }
        }
        this.writeBoolean(b);
        this.writeVarInt(this.chunks.length);
        for (int j = 0; j < this.x.length; ++j) {
            this.writeInt(this.x[j]);
            this.writeInt(this.z[j]);
            this.writeShort(array[j].getMask());
        }
        for (int k = 0; k < this.x.length; ++k) {
            this.writeBytes(array[k].getData());
        }
    }
    
    private void write_1_7() {
        final int[] array = new int[this.chunks.length];
        final int[] array2 = new int[this.chunks.length];
        int len = 0;
        byte[] input = new byte[0];
        boolean b = false;
        for (int i = 0; i < this.chunks.length; ++i) {
            final NetworkChunkData chunksToData = ChunkReader_v1_7.chunksToData((Chunk_v1_7[])this.chunks[i], this.biomeData[i]);
            if (input.length < len + chunksToData.getData().length) {
                final byte[] array3 = new byte[len + chunksToData.getData().length];
                System.arraycopy(input, 0, array3, 0, input.length);
                input = array3;
            }
            if (chunksToData.hasSkyLight()) {
                b = true;
            }
            System.arraycopy(chunksToData.getData(), 0, input, len, chunksToData.getData().length);
            len += chunksToData.getData().length;
            array[i] = chunksToData.getMask();
            array2[i] = chunksToData.getExtendedChunkMask();
        }
        final Deflater deflater = new Deflater(-1);
        final byte[] output = new byte[len];
        int deflate = len;
        try {
            deflater.setInput(input, 0, len);
            deflater.finish();
            deflate = deflater.deflate(output);
        }
        finally {
            deflater.end();
        }
        this.writeShort(this.chunks.length);
        this.writeInt(deflate);
        this.writeBoolean(b);
        for (int j = 0; j < deflate; ++j) {
            this.writeByte((int)output[j]);
        }
        for (int k = 0; k < this.chunks.length; ++k) {
            this.writeInt(this.x[k]);
            this.writeInt(this.z[k]);
            this.writeShort((int)(short)(array[k] & 0xFFFF));
            this.writeShort((int)(short)(array2[k] & 0xFFFF));
        }
    }
    
    public int[] getX() {
        return this.x;
    }
    
    public int[] getZ() {
        return this.z;
    }
    
    public BaseChunk[][] getChunks() {
        return this.chunks;
    }
    
    public byte[][] getBiomeData() {
        return this.biomeData;
    }
}
