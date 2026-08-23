// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.tile;

import kotlin.jvm.JvmName;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0007@\u0007X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n" }, d2 = { "Ldev/imanity/antixray/api/tile/TileCullingAPI;", "", "<init>", "()V", "Ldev/imanity/antixray/api/tile/TileCullingGuiProvider;", "guiProvider", "Ldev/imanity/antixray/api/tile/TileCullingGuiProvider;", "getGuiProvider", "()Ldev/imanity/antixray/api/tile/TileCullingGuiProvider;", "setGuiProvider", "(Ldev/imanity/antixray/api/tile/TileCullingGuiProvider;)V" })
public final class TileCullingAPI
{
    @NotNull
    public static final TileCullingAPI INSTANCE;
    @Nullable
    private static TileCullingGuiProvider guiProvider;
    
    private TileCullingAPI() {
    }
    
    @Nullable
    @JvmName(name = "getGuiProvider")
    public final TileCullingGuiProvider getGuiProvider() {
        return TileCullingAPI.guiProvider;
    }
    
    @JvmName(name = "setGuiProvider")
    public final void setGuiProvider(@Nullable final TileCullingGuiProvider guiProvider) {
        TileCullingAPI.guiProvider = guiProvider;
    }
    
    static {
        INSTANCE = new TileCullingAPI();
    }
}
