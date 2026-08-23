// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.extension;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.bukkit.util.CoordXZ;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 2, xi = 48, d1 = { "\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\f\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\u0004\u0010\u0003\u001a$\u0010\u0007\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0087\u0002¢\u0006\u0004\b\u0007\u0010\b\u001a\u001c\u0010\t\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0000H\u0087\u0002¢\u0006\u0004\b\t\u0010\n\u001a\u0013\u0010\u000b\u001a\u00020\u0000*\u00020\u0000H\u0007¢\u0006\u0004\b\u000b\u0010\f\u001a\u0013\u0010\r\u001a\u00020\u0000*\u00020\u0000H\u0007¢\u0006\u0004\b\r\u0010\f" }, d2 = { "Lio/fairyproject/bukkit/util/CoordXZ;", "", "component1", "(Lio/fairyproject/bukkit/util/CoordXZ;)I", "component2", "p0", "p1", "get", "(Lio/fairyproject/bukkit/util/CoordXZ;II)Lio/fairyproject/bukkit/util/CoordXZ;", "minus", "(Lio/fairyproject/bukkit/util/CoordXZ;Lio/fairyproject/bukkit/util/CoordXZ;)Lio/fairyproject/bukkit/util/CoordXZ;", "shiftAsBlockCoords", "(Lio/fairyproject/bukkit/util/CoordXZ;)Lio/fairyproject/bukkit/util/CoordXZ;", "shiftAsChunkCoords" })
public final class CoordXZExtensionKt
{
    @NotNull
    public static final CoordXZ minus(@NotNull final CoordXZ coordXZ, @NotNull final CoordXZ coordXZ2) {
        Intrinsics.checkNotNullParameter((Object)coordXZ, "");
        Intrinsics.checkNotNullParameter((Object)coordXZ2, "");
        return new CoordXZ(coordXZ.x - coordXZ2.x, coordXZ.z - coordXZ2.z);
    }
    
    @NotNull
    public static final CoordXZ get(@NotNull final CoordXZ coordXZ, final int n, final int n2) {
        Intrinsics.checkNotNullParameter((Object)coordXZ, "");
        return new CoordXZ(coordXZ.x + n, coordXZ.z + n2);
    }
    
    @NotNull
    public static final CoordXZ shiftAsBlockCoords(@NotNull final CoordXZ coordXZ) {
        Intrinsics.checkNotNullParameter((Object)coordXZ, "");
        return new CoordXZ(coordXZ.x << 4, coordXZ.z << 4);
    }
    
    @NotNull
    public static final CoordXZ shiftAsChunkCoords(@NotNull final CoordXZ coordXZ) {
        Intrinsics.checkNotNullParameter((Object)coordXZ, "");
        return new CoordXZ(coordXZ.x >> 4, coordXZ.z >> 4);
    }
    
    public static final int component1(@NotNull final CoordXZ coordXZ) {
        Intrinsics.checkNotNullParameter((Object)coordXZ, "");
        return coordXZ.x;
    }
    
    public static final int component2(@NotNull final CoordXZ coordXZ) {
        Intrinsics.checkNotNullParameter((Object)coordXZ, "");
        return coordXZ.z;
    }
}
