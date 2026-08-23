// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.chunk;

import org.jetbrains.annotations.NotNull;
import org.bukkit.World;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H'¢\u0006\u0004\b\u0005\u0010\u0006" }, d2 = { "Ldev/imanity/antixray/api/chunk/IDirectChunkManager;", "", "Lorg/bukkit/World;", "p0", "Ldev/imanity/antixray/api/chunk/IDirectChunkCache;", "get", "(Lorg/bukkit/World;)Ldev/imanity/antixray/api/chunk/IDirectChunkCache;" })
public interface IDirectChunkManager
{
    @NotNull
    IDirectChunkCache get(@NotNull final World p0);
}
