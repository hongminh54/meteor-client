// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.nms;

import java.util.List;
import dev.imanity.antixray.api.math.AABB;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028'X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0014\u0010\t\u001a\u00020\u00068'X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\n8'X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f" }, d2 = { "Ldev/imanity/antixray/api/nms/StructureHandle;", "", "Ldev/imanity/antixray/api/math/AABB;", "getBoundingBox", "()Ldev/imanity/antixray/api/math/AABB;", "boundingBox", "", "getName", "()Ljava/lang/String;", "name", "", "getPieces", "()Ljava/util/List;", "pieces" })
public interface StructureHandle
{
    @NotNull
    @JvmName(name = "getName")
    String getName();
    
    @NotNull
    @JvmName(name = "getBoundingBox")
    AABB getBoundingBox();
    
    @NotNull
    @JvmName(name = "getPieces")
    List<AABB> getPieces();
}
