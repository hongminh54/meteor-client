// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.v1_16_R3;

import java.util.Iterator;
import net.minecraft.server.v1_16_R3.StructurePiece;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import java.util.Collection;
import java.util.List;
import net.minecraft.server.v1_16_R3.StructureBoundingBox;
import dev.imanity.antixray.api.math.AABB;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import net.minecraft.server.v1_16_R3.StructureStart;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import dev.imanity.antixray.api.nms.StructureHandle;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011" }, d2 = { "Ldev/imanity/antixray/nms/v1_16_R3/StructureHandleImpl;", "Ldev/imanity/antixray/api/nms/StructureHandle;", "structureStart", "Lnet/minecraft/server/v1_16_R3/StructureStart;", "(Lnet/minecraft/server/v1_16_R3/StructureStart;)V", "boundingBox", "Ldev/imanity/antixray/api/math/AABB;", "getBoundingBox", "()Ldev/imanity/antixray/api/math/AABB;", "name", "", "getName", "()Ljava/lang/String;", "pieces", "", "getPieces", "()Ljava/util/List;", "v1_16_5" })
@SourceDebugExtension({ "SMAP\nStructureHandleImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StructureHandleImpl.kt\ndev/imanity/antixray/nms/v1_16_R3/StructureHandleImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,26:1\n1549#2:27\n1620#2,3:28\n*S KotlinDebug\n*F\n+ 1 StructureHandleImpl.kt\ndev/imanity/antixray/nms/v1_16_R3/StructureHandleImpl\n*L\n14#1:27\n14#1:28,3\n*E\n" })
public final class StructureHandleImpl implements StructureHandle
{
    @NotNull
    private final StructureStart<?> structureStart;
    
    public StructureHandleImpl(@NotNull final StructureStart<?> structureStart) {
        Intrinsics.checkNotNullParameter((Object)structureStart, "");
        this.structureStart = structureStart;
    }
    
    @NotNull
    @Override
    public String getName() {
        return this.structureStart.l().toString();
    }
    
    @NotNull
    @Override
    public AABB getBoundingBox() {
        final StructureBoundingBox c = this.structureStart.c();
        Intrinsics.checkNotNullExpressionValue((Object)c, "");
        return StructureHandleImplKt.access$toAABB(c);
    }
    
    @NotNull
    @Override
    public List<AABB> getPieces() {
        final List d = this.structureStart.d();
        Intrinsics.checkNotNullExpressionValue((Object)d, "");
        final Iterable iterable;
        final Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable = d, 10));
        for (final Object next : iterable) {
            final Collection collection2 = collection;
            final StructurePiece structurePiece = (StructurePiece)next;
            final Collection collection3 = collection2;
            final StructureBoundingBox g = structurePiece.g();
            Intrinsics.checkNotNullExpressionValue((Object)g, "");
            collection3.add(StructureHandleImplKt.access$toAABB(g));
        }
        return (List<AABB>)collection;
    }
}
