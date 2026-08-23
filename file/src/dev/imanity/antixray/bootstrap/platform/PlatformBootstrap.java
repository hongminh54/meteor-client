// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.bootstrap.platform;

import io.fairyproject.plugin.Plugin;

public interface PlatformBootstrap
{
    boolean preload();
    
    void load(final Plugin p0);
    
    void enable();
    
    void disable();
}
