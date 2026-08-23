// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.data;

import java.util.Set;
import java.util.List;
import java.util.Collection;
import org.bukkit.Material;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import io.fairyproject.libs.xseries.XMaterial;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\"\n\u0002\b\u0005\bf\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\rH&J\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u0011H&J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\u000b\u001a\u00020\fH&J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u0011H&J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\u000b\u001a\u00020\rH&J\u0010\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\nH&J\u0010\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\rH&J\u0010\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\nH&J\u0010\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\rH&¨\u0006\u0018" }, d2 = { "Ldev/imanity/antixray/api/data/IMaterialDataHandler;", "", "cacheCullingTransparency", "", "globalId", "", "transparent", "", "cullingTransparencyCached", "decode", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "material", "Lio/fairyproject/libs/xseries/XMaterial;", "Lorg/bukkit/Material;", "decodeToIds", "", "materials", "", "decodeToPossibleIds", "", "isLava", "wrappedBlockState", "isTransparent", "Companion", "api" })
public interface IMaterialDataHandler
{
    @NotNull
    public static final Companion Companion = IMaterialDataHandler.Companion.$$INSTANCE;
    public static final int CULLING_UNKNOWN = 0;
    public static final int CULLING_OPAQUE = 1;
    public static final int CULLING_TRANSPARENT = 2;
    
    @NotNull
    WrappedBlockState decode(@NotNull final XMaterial p0);
    
    @NotNull
    WrappedBlockState decode(@NotNull final Material p0);
    
    @NotNull
    List<Integer> decodeToIds(@NotNull final Collection<? extends XMaterial> p0);
    
    @NotNull
    Set<Integer> decodeToPossibleIds(@NotNull final Material p0);
    
    @NotNull
    Set<Integer> decodeToPossibleIds(@NotNull final XMaterial p0);
    
    @NotNull
    Set<Integer> decodeToPossibleIds(@NotNull final Collection<? extends XMaterial> p0);
    
    boolean isTransparent(@NotNull final WrappedBlockState p0);
    
    boolean isTransparent(@NotNull final Material p0);
    
    boolean isLava(@NotNull final WrappedBlockState p0);
    
    boolean isLava(@NotNull final Material p0);
    
    int cullingTransparencyCached(final int p0);
    
    void cacheCullingTransparency(final int p0, final boolean p1);
    
    @Metadata(mv = { 1, 9, 0 }, k = 3, xi = 48)
    public static final class DefaultImpls
    {
        public static int cullingTransparencyCached(@NotNull final IMaterialDataHandler materialDataHandler, final int n) {
            return 0;
        }
        
        public static void cacheCullingTransparency(@NotNull final IMaterialDataHandler materialDataHandler, final int n, final boolean b) {
        }
    }
    
    @Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\b\u0010\u0006" }, d2 = { "Ldev/imanity/antixray/api/data/IMaterialDataHandler$Companion;", "", "<init>", "()V", "", "CULLING_OPAQUE", "I", "CULLING_TRANSPARENT", "CULLING_UNKNOWN" })
    public static final class Companion
    {
        static final /* synthetic */ Companion $$INSTANCE;
        public static final int CULLING_UNKNOWN = 0;
        public static final int CULLING_OPAQUE = 1;
        public static final int CULLING_TRANSPARENT = 2;
        
        private Companion() {
        }
        
        static {
            $$INSTANCE = new Companion();
        }
    }
}
