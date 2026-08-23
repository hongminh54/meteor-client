// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.block.tile;

import org.jetbrains.annotations.Nullable;
import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.libs.xseries.XMaterial;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015" }, d2 = { "Ldev/imanity/antixray/api/block/tile/TileEntityType;", "", "name", "", "materials", "", "Lio/fairyproject/libs/xseries/XMaterial;", "(Ljava/lang/String;Ljava/util/Set;)V", "getMaterials", "()Ljava/util/Set;", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "api" })
public final class TileEntityType
{
    @NotNull
    private final String name;
    @NotNull
    private final Set<XMaterial> materials;
    
    public TileEntityType(@NotNull final String name, @NotNull final Set<? extends XMaterial> materials) {
        Intrinsics.checkNotNullParameter((Object)name, "");
        Intrinsics.checkNotNullParameter((Object)materials, "");
        this.name = name;
        this.materials = (Set<XMaterial>)materials;
    }
    
    @NotNull
    public final String getName() {
        return this.name;
    }
    
    @NotNull
    public final Set<XMaterial> getMaterials() {
        return this.materials;
    }
    
    @NotNull
    public final String component1() {
        return this.name;
    }
    
    @NotNull
    public final Set<XMaterial> component2() {
        return this.materials;
    }
    
    @NotNull
    public final TileEntityType copy(@NotNull final String s, @NotNull final Set<? extends XMaterial> set) {
        Intrinsics.checkNotNullParameter((Object)s, "");
        Intrinsics.checkNotNullParameter((Object)set, "");
        return new TileEntityType(s, set);
    }
    
    @NotNull
    @Override
    public String toString() {
        return "TileEntityType(name=" + this.name + ", materials=" + this.materials + ')';
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode() * 31 + this.materials.hashCode();
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TileEntityType)) {
            return false;
        }
        final TileEntityType tileEntityType = (TileEntityType)o;
        return Intrinsics.areEqual((Object)this.name, (Object)tileEntityType.name) && Intrinsics.areEqual((Object)this.materials, (Object)tileEntityType.materials);
    }
}
