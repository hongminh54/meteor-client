// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.chunk;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.libs.packetevents.protocol.world.chunk.TileEntity;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R0\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b" }, d2 = { "Ldev/imanity/antixray/api/chunk/EmptyTileEntityHolder;", "Ldev/imanity/antixray/api/chunk/TileEntityHolder;", "()V", "<anonymous parameter 0>", "", "Lio/fairyproject/libs/packetevents/protocol/world/chunk/TileEntity;", "array", "getArray", "()[Lcom/github/retrooper/packetevents/protocol/world/chunk/TileEntity;", "setArray", "([Lcom/github/retrooper/packetevents/protocol/world/chunk/TileEntity;)V", "api" })
@SourceDebugExtension({ "SMAP\nChunkData.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChunkData.kt\ndev/imanity/antixray/api/chunk/EmptyTileEntityHolder\n+ 2 ArrayIntrinsics.kt\nkotlin/ArrayIntrinsicsKt\n*L\n1#1,135:1\n26#2:136\n*S KotlinDebug\n*F\n+ 1 ChunkData.kt\ndev/imanity/antixray/api/chunk/EmptyTileEntityHolder\n*L\n133#1:136\n*E\n" })
public final class EmptyTileEntityHolder implements TileEntityHolder
{
    @NotNull
    @Override
    public TileEntity[] getArray() {
        return new TileEntity[0];
    }
    
    @Override
    public void setArray(@NotNull final TileEntity[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "");
    }
}
