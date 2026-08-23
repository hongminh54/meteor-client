// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.obfuscator;

import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.mc.util.BlockPosition;
import org.bukkit.block.Block;
import org.bukkit.Chunk;
import dev.imanity.antixray.api.chunk.ChunkData;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.bukkit.World;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J'\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\f\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0013\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J'\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u00192\u0006\u0010\u0007\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u0017\u0010\u001a" }, d2 = { "Ldev/imanity/antixray/api/obfuscator/ObfuscateStrategy;", "", "Lorg/bukkit/World;", "p0", "Lorg/bukkit/entity/Player;", "p1", "Ldev/imanity/antixray/api/chunk/ChunkData;", "p2", "", "obfuscate", "(Lorg/bukkit/World;Lorg/bukkit/entity/Player;Ldev/imanity/antixray/api/chunk/ChunkData;)V", "Lorg/bukkit/block/Block;", "onBlockPlace", "(Lorg/bukkit/block/Block;)V", "", "(Lorg/bukkit/block/Block;Z)V", "Lorg/bukkit/Chunk;", "onGlobalChunkLoaded", "(Lorg/bukkit/Chunk;)V", "onGlobalChunkUnloaded", "onReload", "()V", "Lio/fairyproject/mc/util/BlockPosition;", "shouldNotifyBlockUpdate", "(Lio/fairyproject/mc/util/BlockPosition;)Z", "", "(III)Z" })
public interface ObfuscateStrategy
{
    void obfuscate(@NotNull final World p0, @NotNull final Player p1, @NotNull final ChunkData p2);
    
    void onReload();
    
    void onGlobalChunkLoaded(@NotNull final Chunk p0);
    
    void onGlobalChunkUnloaded(@NotNull final Chunk p0);
    
    void onBlockPlace(@NotNull final Block p0);
    
    void onBlockPlace(@NotNull final Block p0, final boolean p1);
    
    boolean shouldNotifyBlockUpdate(@NotNull final BlockPosition p0);
    
    boolean shouldNotifyBlockUpdate(final int p0, final int p1, final int p2);
    
    @Metadata(mv = { 1, 9, 0 }, k = 3, xi = 48)
    public static final class DefaultImpls
    {
        public static void onReload(@NotNull final ObfuscateStrategy obfuscateStrategy) {
        }
        
        public static void onGlobalChunkLoaded(@NotNull final ObfuscateStrategy obfuscateStrategy, @NotNull final Chunk chunk) {
            Intrinsics.checkNotNullParameter((Object)chunk, "");
        }
        
        public static void onGlobalChunkUnloaded(@NotNull final ObfuscateStrategy obfuscateStrategy, @NotNull final Chunk chunk) {
            Intrinsics.checkNotNullParameter((Object)chunk, "");
        }
        
        public static void onBlockPlace(@NotNull final ObfuscateStrategy obfuscateStrategy, @NotNull final Block block) {
            Intrinsics.checkNotNullParameter((Object)block, "");
        }
        
        public static void onBlockPlace(@NotNull final ObfuscateStrategy obfuscateStrategy, @NotNull final Block block, final boolean b) {
            Intrinsics.checkNotNullParameter((Object)block, "");
            obfuscateStrategy.onBlockPlace(block);
        }
        
        public static boolean shouldNotifyBlockUpdate(@NotNull final ObfuscateStrategy obfuscateStrategy, @NotNull final BlockPosition blockPosition) {
            Intrinsics.checkNotNullParameter((Object)blockPosition, "");
            return false;
        }
        
        public static boolean shouldNotifyBlockUpdate(@NotNull final ObfuscateStrategy obfuscateStrategy, final int n, final int n2, final int n3) {
            return obfuscateStrategy.shouldNotifyBlockUpdate(new BlockPosition(n, n2, n3));
        }
    }
}
