// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.bootstrap.platform;

import dev.imanity.antixray.bootstrap.type.PlatformType;
import org.jetbrains.annotations.Nullable;
import io.fairyproject.plugin.PluginManager;
import io.fairyproject.plugin.Plugin;
import io.fairyproject.FairyPlatform;

public abstract class AbstractPlatformBootstrap implements PlatformBootstrap
{
    private FairyPlatform fairyPlatform;
    
    @Override
    public final boolean preload() {
        if (FairyPlatform.class.getClassLoader() != this.getClass().getClassLoader()) {
            return true;
        }
        if (FairyPlatform.INSTANCE != null) {
            return true;
        }
        try {
            (this.fairyPlatform = this.createPlatform()).preload();
        }
        catch (final Throwable t) {
            this.onFailure(t);
            return false;
        }
        return true;
    }
    
    @Override
    public final void load(final Plugin plugin) {
        if (this.fairyPlatform == null) {
            return;
        }
        try {
            this.fairyPlatform.load(plugin);
        }
        catch (final Throwable t) {
            this.onFailure(t);
        }
    }
    
    @Override
    public final void enable() {
        if (this.fairyPlatform == null) {
            return;
        }
        this.fairyPlatform.enable();
    }
    
    @umwLDqRKqHZhDbQEHQ4srBR2MiuH5Km8euY(m = "wGU4kFV3QUTaZTQGVWdf1UYSFjUhhmVaVFaWFGSWd0VEhmRNJWUHlWZDpFVYBjYHlWZDFVU")
    @Override
    public final void disable() {
        if (this.fairyPlatform == null) {
            return;
        }
        this.fairyPlatform.disable();
        PluginManager.INSTANCE.unload();
    }
    
    protected abstract void onFailure(@Nullable final Throwable p0);
    
    protected abstract PlatformType getPlatformType();
    
    protected abstract FairyPlatform createPlatform();
}
