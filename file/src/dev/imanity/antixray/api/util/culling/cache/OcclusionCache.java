// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.util.culling.cache;

public interface OcclusionCache
{
    void resetCache();
    
    void setVisible(final int p0, final int p1, final int p2);
    
    void setHidden(final int p0, final int p1, final int p2);
    
    int getState(final int p0, final int p1, final int p2);
    
    void setLastHidden();
    
    void setLastVisible();
}
