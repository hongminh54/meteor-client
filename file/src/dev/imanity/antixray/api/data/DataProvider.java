// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import dev.imanity.antixray.api.util.Vec3d;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J3\u0010\t\u001a\u00020\b2\u0010\u0010\u0004\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ'\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¢\u0006\u0004\b\u0010\u0010\u0011" }, d2 = { "Ldev/imanity/antixray/api/data/DataProvider;", "", "", "Ldev/imanity/antixray/api/util/Vec3d;", "p0", "", "p1", "p2", "", "checkingPosition", "([Ldev/imanity/antixray/api/util/Vec3d;ILdev/imanity/antixray/api/util/Vec3d;)V", "cleanup", "()V", "", "isOpaqueFullCube", "(III)Z", "prepareChunk", "(II)Z" })
public interface DataProvider
{
    boolean prepareChunk(final int p0, final int p1);
    
    boolean isOpaqueFullCube(final int p0, final int p1, final int p2);
    
    void cleanup();
    
    void checkingPosition(@Nullable final Vec3d[] p0, final int p1, @Nullable final Vec3d p2);
    
    @Metadata(mv = { 1, 9, 0 }, k = 3, xi = 48)
    public static final class DefaultImpls
    {
        public static void cleanup(@NotNull final DataProvider dataProvider) {
        }
        
        public static void checkingPosition(@NotNull final DataProvider dataProvider, @Nullable final Vec3d[] array, final int n, @Nullable final Vec3d vec3d) {
        }
    }
}
