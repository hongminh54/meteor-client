// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms;

import kotlin.jvm.internal.Intrinsics;
import dev.imanity.antixray.api.util.Position;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import java.util.Set;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import org.bukkit.block.Block;
import java.util.Collection;
import dev.imanity.antixray.api.math.AABB;
import dev.imanity.antixray.api.chunk.DirectChunk;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import java.util.concurrent.CompletableFuture;
import dev.imanity.antixray.api.nms.ChunkHandle;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Entity;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0001H&J+\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010H&¢\u0006\u0002\u0010\u0011J+\u0010\u0012\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010H&¢\u0006\u0002\u0010\u0011J\u0018\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0016H&J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u0001H\u0016J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0014\u001a\u00020\u001dH&J \u0010\u001e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u0019H&J(\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\"2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u0019H&J\u0010\u0010#\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u001dH&J\u0010\u0010$\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u001dH&J\u0010\u0010%\u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\tH&J\u0016\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010'\u001a\u00020(H&J\b\u0010)\u001a\u00020\fH\u0016J\b\u0010*\u001a\u00020+H\u0016J \u0010,\u001a\u00020\f2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u0019H&J\b\u0010/\u001a\u00020\fH\u0016J0\u00100\u001a\u00020\f2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001f\u001a\u00020\u00192\u0006\u00101\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u00192\u0006\u0010'\u001a\u00020(H&J(\u00102\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\u00192\u0006\u00101\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u0019H&J\u001e\u00103\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u001d2\f\u00104\u001a\b\u0012\u0004\u0012\u00020.05H\u0016J\b\u00106\u001a\u00020+H&J\u0018\u00107\u001a\u0002082\u0006\u0010\u0015\u001a\u0002092\u0006\u0010:\u001a\u00020;H\u0016¨\u0006<" }, d2 = { "Ldev/imanity/antixray/nms/NativeProvider;", "", "createBlockData", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "block", "Lorg/bukkit/block/Block;", "createChunkHandle", "Ldev/imanity/antixray/api/nms/ChunkHandle;", "world", "Lorg/bukkit/World;", "raw", "disableChunkUnload", "", "centerChunk", "Ldev/imanity/antixray/api/chunk/DirectChunk;", "allChunks", "", "(Lorg/bukkit/World;Ldev/imanity/antixray/api/chunk/DirectChunk;[Ldev/imanity/antixray/api/chunk/DirectChunk;)V", "enableChunkUnload", "fetchLocation", "entity", "location", "Ldev/imanity/antixray/api/util/Position;", "getBlockDataIds", "", "", "obj", "getBoundingBox", "Ldev/imanity/antixray/api/math/AABB;", "Lorg/bukkit/entity/Entity;", "getChunkAt", "x", "z", "getChunkAtAsync", "Ljava/util/concurrent/CompletableFuture;", "getEntityTrackingRange", "getLocatableObject", "getMaxEntityTrackingRange", "getPossibleDefinition", "material", "Lorg/bukkit/Material;", "init", "isZoomSupported", "", "resendChunk", "player", "Lorg/bukkit/entity/Player;", "resetEmptyTicks", "sendBlock", "y", "updateBlock", "updateEntity", "viewers", "", "validate", "zoom", "Lorg/bukkit/util/Vector;", "Lorg/bukkit/Location;", "maxZoom", "", "base" })
public interface NativeProvider
{
    boolean validate();
    
    void init();
    
    int getEntityTrackingRange(@NotNull final Entity p0);
    
    int getMaxEntityTrackingRange(@NotNull final World p0);
    
    @NotNull
    ChunkHandle createChunkHandle(@NotNull final World p0, @NotNull final Object p1);
    
    @NotNull
    ChunkHandle getChunkAt(@NotNull final World p0, final int p1, final int p2);
    
    @NotNull
    CompletableFuture<ChunkHandle> getChunkAtAsync(@NotNull final World p0, final int p1, final int p2);
    
    void resendChunk(@NotNull final Player p0, final int p1, final int p2);
    
    void updateBlock(@NotNull final World p0, final int p1, final int p2, final int p3);
    
    void sendBlock(@NotNull final Player p0, final int p1, final int p2, final int p3, @NotNull final Material p4);
    
    void disableChunkUnload(@NotNull final World p0, @NotNull final DirectChunk p1, @NotNull final DirectChunk[] p2);
    
    void enableChunkUnload(@NotNull final World p0, @NotNull final DirectChunk p1, @NotNull final DirectChunk[] p2);
    
    @NotNull
    AABB getBoundingBox(@NotNull final Entity p0);
    
    void updateEntity(@NotNull final Entity p0, @NotNull final Collection<? extends Player> p1);
    
    @NotNull
    WrappedBlockState createBlockData(@NotNull final Block p0);
    
    @NotNull
    Set<Integer> getPossibleDefinition(@NotNull final Material p0);
    
    @NotNull
    Set<Integer> getBlockDataIds(@NotNull final Object p0);
    
    boolean isZoomSupported();
    
    @NotNull
    Vector zoom(@NotNull final Location p0, final double p1);
    
    void resetEmptyTicks();
    
    @NotNull
    Object getLocatableObject(@NotNull final Entity p0);
    
    void fetchLocation(@NotNull final Object p0, @NotNull final Position p1);
    
    @Metadata(mv = { 1, 9, 0 }, k = 3, xi = 48)
    public static final class DefaultImpls
    {
        public static void init(@NotNull final NativeProvider nativeProvider) {
        }
        
        public static void updateEntity(@NotNull final NativeProvider nativeProvider, @NotNull final Entity entity, @NotNull final Collection<? extends Player> collection) {
            Intrinsics.checkNotNullParameter((Object)entity, "");
            Intrinsics.checkNotNullParameter((Object)collection, "");
        }
        
        @NotNull
        public static Set<Integer> getBlockDataIds(@NotNull final NativeProvider nativeProvider, @NotNull final Object o) {
            Intrinsics.checkNotNullParameter(o, "");
            throw new UnsupportedOperationException();
        }
        
        public static boolean isZoomSupported(@NotNull final NativeProvider nativeProvider) {
            return false;
        }
        
        @NotNull
        public static Vector zoom(@NotNull final NativeProvider nativeProvider, @NotNull final Location location, final double n) {
            Intrinsics.checkNotNullParameter((Object)location, "");
            throw new UnsupportedOperationException();
        }
        
        public static void resetEmptyTicks(@NotNull final NativeProvider nativeProvider) {
        }
    }
}
