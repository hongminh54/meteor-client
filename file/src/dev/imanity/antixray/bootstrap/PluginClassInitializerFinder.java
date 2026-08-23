// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.bootstrap;

import io.fairyproject.plugin.initializer.DefaultPluginClassInitializer;
import io.fairyproject.plugin.initializer.PluginClassInitializer;

public final class PluginClassInitializerFinder
{
    public static PluginClassInitializer find() {
        try {
            return (PluginClassInitializer)Class.forName("io.fairyproject.devtools.reload.ReloadablePluginClassInitializer").getDeclaredConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
        }
        catch (final ReflectiveOperationException ex) {
            return (PluginClassInitializer)new DefaultPluginClassInitializer();
        }
    }
    
    private PluginClassInitializerFinder() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
