// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.libs.xseries.XMaterial;
import org.jetbrains.annotations.NotNull;
import org.bukkit.Material;

public final class IIlllIIlIIIlIlIlIIllIlllIIIllIlIllIlIIIlIIllIlllIIllIlIIlIIIllIIIllIIllIIllIllIIlllIlIIlllIlllIIIlIlllIIIlIIIlIlIIllIlIIIlIIlIlIIIlllIllIIlllIIlllIIIlIIIlIIIlIllIlIIIlIIllIIlIllIIIllIIlIIIllIlIIlllIIIlllIlllIIlIlIllIIll
{
    @NotNull
    public static final XMaterial IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final Material material) {
        Intrinsics.checkNotNullParameter((Object)material, "");
        final XMaterial matchXMaterial = XMaterial.matchXMaterial(material);
        Intrinsics.checkNotNullExpressionValue((Object)matchXMaterial, "");
        return matchXMaterial;
    }
}
