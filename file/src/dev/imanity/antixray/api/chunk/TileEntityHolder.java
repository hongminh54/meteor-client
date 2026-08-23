// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.chunk;

import org.jetbrains.annotations.NotNull;
import io.fairyproject.libs.packetevents.protocol.world.chunk.TileEntity;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001R\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t" }, d2 = { "Ldev/imanity/antixray/api/chunk/TileEntityHolder;", "", "array", "", "Lio/fairyproject/libs/packetevents/protocol/world/chunk/TileEntity;", "getArray", "()[Lcom/github/retrooper/packetevents/protocol/world/chunk/TileEntity;", "setArray", "([Lcom/github/retrooper/packetevents/protocol/world/chunk/TileEntity;)V", "api" })
public interface TileEntityHolder
{
    @NotNull
    TileEntity[] getArray();
    
    void setArray(@NotNull final TileEntity[] p0);
}
