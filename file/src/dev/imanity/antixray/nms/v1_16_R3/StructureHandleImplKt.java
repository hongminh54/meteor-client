// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.v1_16_R3;

import dev.imanity.antixray.api.math.AABB;
import net.minecraft.server.v1_16_R3.StructureBoundingBox;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 2, xi = 48, d1 = { "\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003" }, d2 = { "toAABB", "Ldev/imanity/antixray/api/math/AABB;", "Lnet/minecraft/server/v1_16_R3/StructureBoundingBox;", "v1_16_5" })
public final class StructureHandleImplKt
{
    private static final AABB toAABB(final StructureBoundingBox structureBoundingBox) {
        return new AABB(structureBoundingBox.a, structureBoundingBox.b, structureBoundingBox.c, structureBoundingBox.d, structureBoundingBox.e, structureBoundingBox.f);
    }
}
