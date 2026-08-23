// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.bootstrap.instance;

import io.fairyproject.plugin.Plugin;
import io.fairyproject.libs.gson.JsonObject;

public interface PluginInstance
{
    void init(final JsonObject p0);
    
    void onLoad();
    
    void onEnable();
    
    void onDisable();
    
    Plugin getPlugin();
}
