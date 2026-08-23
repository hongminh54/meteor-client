// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.config;

import dev.imanity.antixray.a.IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl;
import io.fairyproject.libs.xseries.XMaterial;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import dev.imanity.antixray.a.IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import io.fairyproject.config.annotation.ConfigurationElement;

@ConfigurationElement
@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B7\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0002\u0010\nB\u0007\b\u0016¢\u0006\u0002\u0010\u000bB5\u0012\u0006\u0010\u0002\u001a\u00020\f\u0012\u0006\u0010\u0004\u001a\u00020\f\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0002\u0010\rJ\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010*\u001a\u00020\u0006H\u0016J\u000e\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.R\u001a\u0010\u0002\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\t\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001bR\u001a\u0010\u0004\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000f\"\u0004\b\u001f\u0010\u0011R\u001c\u0010 \u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0015\"\u0004\b\"\u0010\u0017R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0019\"\u0004\b$\u0010\u001bR\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0019\"\u0004\b&\u0010\u001b¨\u0006/" }, d2 = { "Ldev/imanity/antixray/config/MinableFeature;", "", "block", "Lio/fairyproject/libs/xseries/XMaterial;", "replace", "veinCount", "", "veinSize", "minHeight", "maxHeight", "(Lcom/cryptomorin/xseries/XMaterial;Lcom/cryptomorin/xseries/XMaterial;IIII)V", "()V", "", "(Ljava/lang/String;Ljava/lang/String;IIII)V", "getBlock", "()Ljava/lang/String;", "setBlock", "(Ljava/lang/String;)V", "blockMaterial", "Ldev/imanity/antixray/core/data/type/BlockMaterial;", "getBlockMaterial", "()Ldev/imanity/antixray/core/data/type/BlockMaterial;", "setBlockMaterial", "(Ldev/imanity/antixray/core/data/type/BlockMaterial;)V", "getMaxHeight", "()I", "setMaxHeight", "(I)V", "getMinHeight", "setMinHeight", "getReplace", "setReplace", "replaceMaterial", "getReplaceMaterial", "setReplaceMaterial", "getVeinCount", "setVeinCount", "getVeinSize", "setVeinSize", "equals", "", "other", "hashCode", "init", "", "blockMaterialSerializer", "Ldev/imanity/antixray/core/data/type/BlockMaterialSerializer;", "plugin" })
public final class MinableFeature
{
    @NotNull
    private String block;
    @NotNull
    private String replace;
    @Nullable
    private transient IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl blockMaterial;
    @Nullable
    private transient IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl replaceMaterial;
    private int veinCount;
    private int veinSize;
    private int minHeight;
    private int maxHeight;
    
    public MinableFeature(@NotNull final String block, @NotNull final String replace, final int veinCount, final int veinSize, final int minHeight, final int maxHeight) {
        Intrinsics.checkNotNullParameter((Object)block, "");
        Intrinsics.checkNotNullParameter((Object)replace, "");
        this.block = block;
        this.replace = replace;
        this.veinCount = veinCount;
        this.veinSize = veinSize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }
    
    public MinableFeature(@NotNull final XMaterial xMaterial, @NotNull final XMaterial xMaterial2, final int n, final int n2, final int n3, final int n4) {
        Intrinsics.checkNotNullParameter((Object)xMaterial, "");
        Intrinsics.checkNotNullParameter((Object)xMaterial2, "");
        this(xMaterial.name(), xMaterial2.name(), n, n2, n3, n4);
    }
    
    public MinableFeature() {
        this(XMaterial.STONE, XMaterial.STONE, 0, 0, 0, 0);
    }
    
    public final void init(@NotNull final IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl illIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl) {
        Intrinsics.checkNotNullParameter((Object)illIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl, "");
        try {
            this.blockMaterial = illIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(this.block);
        }
        catch (final Exception ex) {}
        try {
            this.replaceMaterial = illIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(this.replace);
        }
        catch (final Exception ex2) {}
    }
    
    @NotNull
    public final String getBlock() {
        return this.block;
    }
    
    public final void setBlock(@NotNull final String block) {
        Intrinsics.checkNotNullParameter((Object)block, "");
        this.block = block;
    }
    
    @NotNull
    public final String getReplace() {
        return this.replace;
    }
    
    public final void setReplace(@NotNull final String replace) {
        Intrinsics.checkNotNullParameter((Object)replace, "");
        this.replace = replace;
    }
    
    @Nullable
    public final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl getBlockMaterial() {
        return this.blockMaterial;
    }
    
    public final void setBlockMaterial(@Nullable final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl blockMaterial) {
        this.blockMaterial = blockMaterial;
    }
    
    @Nullable
    public final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl getReplaceMaterial() {
        return this.replaceMaterial;
    }
    
    public final void setReplaceMaterial(@Nullable final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl replaceMaterial) {
        this.replaceMaterial = replaceMaterial;
    }
    
    public final int getVeinCount() {
        return this.veinCount;
    }
    
    public final void setVeinCount(final int veinCount) {
        this.veinCount = veinCount;
    }
    
    public final int getVeinSize() {
        return this.veinSize;
    }
    
    public final void setVeinSize(final int veinSize) {
        this.veinSize = veinSize;
    }
    
    public final int getMinHeight() {
        return this.minHeight;
    }
    
    public final void setMinHeight(final int minHeight) {
        this.minHeight = minHeight;
    }
    
    public final int getMaxHeight() {
        return this.maxHeight;
    }
    
    public final void setMaxHeight(final int maxHeight) {
        this.maxHeight = maxHeight;
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) {
            return true;
        }
        if (!Intrinsics.areEqual((Object)this.getClass(), (Object)((o != null) ? o.getClass() : null))) {
            return false;
        }
        Intrinsics.checkNotNull(o);
        final MinableFeature minableFeature = (MinableFeature)o;
        return Intrinsics.areEqual((Object)this.block, (Object)((MinableFeature)o).block) && Intrinsics.areEqual((Object)this.replace, (Object)((MinableFeature)o).replace) && this.veinCount == ((MinableFeature)o).veinCount && this.veinSize == ((MinableFeature)o).veinSize && this.minHeight == ((MinableFeature)o).minHeight && this.maxHeight == ((MinableFeature)o).maxHeight;
    }
    
    @Override
    public int hashCode() {
        return 31 * (31 * (31 * (31 * (31 * this.block.hashCode() + this.replace.hashCode()) + this.veinCount) + this.veinSize) + this.minHeight) + this.maxHeight;
    }
}
