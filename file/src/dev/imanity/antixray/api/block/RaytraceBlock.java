// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.block;

import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.Nullable;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import io.fairyproject.mc.util.BlockPosition;
import org.jetbrains.annotations.NotNull;
import dev.imanity.antixray.api.chunk.DirectChunk;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J$\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020'2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00110)H&J\b\u0010*\u001a\u00020\u0000H&J\u0010\u0010+\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\u0000H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\u00020\u0011X¦\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0016\u001a\u00020\u0011X¦\u000e¢\u0006\f\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\u0012\u0010\u0019\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001d\u001a\u00020\u0011X¦\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u0013\"\u0004\b\u001f\u0010\u0015R\u0012\u0010 \u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u001cR\u0018\u0010\"\u001a\u00020\u0011X¦\u000e¢\u0006\f\u001a\u0004\b#\u0010\u0013\"\u0004\b$\u0010\u0015¨\u0006-" }, d2 = { "Ldev/imanity/antixray/api/block/RaytraceBlock;", "", "blockPosition", "Lio/fairyproject/mc/util/BlockPosition;", "getBlockPosition", "()Lio/fairyproject/mc/util/BlockPosition;", "chunk", "Ldev/imanity/antixray/api/chunk/DirectChunk;", "getChunk", "()Ldev/imanity/antixray/api/chunk/DirectChunk;", "group", "Ldev/imanity/antixray/api/block/RaytraceBlockGroup;", "getGroup", "()Ldev/imanity/antixray/api/block/RaytraceBlockGroup;", "setGroup", "(Ldev/imanity/antixray/api/block/RaytraceBlockGroup;)V", "initialized", "", "getInitialized", "()Z", "setInitialized", "(Z)V", "lavaCovered", "getLavaCovered", "setLavaCovered", "original", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "getOriginal", "()Lcom/github/retrooper/packetevents/protocol/world/states/WrappedBlockState;", "pendingRemoval", "getPendingRemoval", "setPendingRemoval", "replacement", "getReplacement", "visible", "getVisible", "setVisible", "checkVisibility", "currentTicks", "", "predicate", "Lkotlin/Function1;", "copy", "isNearby", "other", "api" })
public interface RaytraceBlock
{
    @NotNull
    DirectChunk getChunk();
    
    @NotNull
    BlockPosition getBlockPosition();
    
    @NotNull
    WrappedBlockState getOriginal();
    
    @NotNull
    WrappedBlockState getReplacement();
    
    boolean getInitialized();
    
    void setInitialized(final boolean p0);
    
    boolean getVisible();
    
    void setVisible(final boolean p0);
    
    boolean getPendingRemoval();
    
    void setPendingRemoval(final boolean p0);
    
    @Nullable
    RaytraceBlockGroup getGroup();
    
    void setGroup(@Nullable final RaytraceBlockGroup p0);
    
    boolean getLavaCovered();
    
    void setLavaCovered(final boolean p0);
    
    @NotNull
    RaytraceBlock copy();
    
    boolean isNearby(@NotNull final RaytraceBlock p0);
    
    boolean checkVisibility(final int p0, @NotNull final Function1<? super RaytraceBlock, Boolean> p1);
}
