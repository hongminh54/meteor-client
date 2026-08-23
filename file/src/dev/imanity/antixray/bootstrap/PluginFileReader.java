// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.bootstrap;

import java.net.URLConnection;
import java.net.URL;
import java.io.Reader;
import java.io.InputStreamReader;
import io.fairyproject.libs.gson.Gson;
import io.fairyproject.libs.gson.JsonObject;

public class PluginFileReader
{
    private static final String FAIRY_JSON_PATH = "fairy.json";
    
    public JsonObject read(final Class<?> clazz) {
        final URL resource = clazz.getClassLoader().getResource("fairy.json");
        if (resource == null) {
            return null;
        }
        try {
            final URLConnection openConnection = resource.openConnection();
            openConnection.setUseCaches(false);
            return (JsonObject)new Gson().fromJson((Reader)new InputStreamReader(openConnection.getInputStream()), (Class)JsonObject.class);
        }
        catch (final Throwable cause) {
            throw new IllegalArgumentException("Unable to load fairy.json", cause);
        }
    }
}
