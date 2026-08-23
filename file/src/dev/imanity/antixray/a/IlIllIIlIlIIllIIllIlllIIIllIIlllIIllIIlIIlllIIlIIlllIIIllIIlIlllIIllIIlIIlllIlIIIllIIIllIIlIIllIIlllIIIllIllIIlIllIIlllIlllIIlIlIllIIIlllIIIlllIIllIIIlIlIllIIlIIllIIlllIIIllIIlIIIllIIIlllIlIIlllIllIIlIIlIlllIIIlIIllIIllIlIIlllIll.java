// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.mc.util.BlockPosition;
import org.jetbrains.annotations.NotNull;
import org.bukkit.block.Block;

public final class IlIllIIlIlIIllIIllIlllIIIllIIlllIIllIIlIIlllIIlIIlllIIIllIIlIlllIIllIIlIIlllIlIIIllIIIllIIlIIllIIlllIIIllIllIIlIllIIlllIlllIIlIlIllIIIlllIIIlllIIllIIIlIlIllIIlIIllIIlllIIIllIIlIIIllIIIlllIlIIlllIllIIlIIlIlllIIIlIIllIIllIlIIlllIll
{
    @NotNull
    public static final BlockPosition IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final Block block) {
        Intrinsics.checkNotNullParameter((Object)block, "");
        return new BlockPosition(block.getX(), block.getY(), block.getZ());
    }
}
