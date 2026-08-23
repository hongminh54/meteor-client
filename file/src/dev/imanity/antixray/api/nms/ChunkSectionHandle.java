// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.nms;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH&J \u0010\f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH&J \u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u000f" }, d2 = { "Ldev/imanity/antixray/api/nms/ChunkSectionHandle;", "", "empty", "", "getEmpty", "()Z", "getBlockData", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "x", "", "y", "z", "getBlockId", "getBlockType", "Lorg/bukkit/Material;", "api" })
public interface ChunkSectionHandle
{
    boolean getEmpty();
    
    @NotNull
    WrappedBlockState getBlockData(final int p0, final int p1, final int p2);
    
    @NotNull
    Material getBlockType(final int p0, final int p1, final int p2);
    
    int getBlockId(final int p0, final int p1, final int p2);
}
