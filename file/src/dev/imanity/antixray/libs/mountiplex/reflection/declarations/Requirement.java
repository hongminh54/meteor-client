// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import java.util.HashMap;
import java.util.Collections;
import java.util.Map;

public class Requirement
{
    public final String name;
    public final Declaration declaration;
    private Map<String, Object> metadata;
    
    public Requirement(final String name, final Declaration declaration) {
        this.name = name;
        this.declaration = declaration;
        this.metadata = Collections.emptyMap();
    }
    
    public boolean hasProperty(final String s) {
        return this.metadata.containsKey(s);
    }
    
    public <T> T property(final String s) {
        return (T)this.metadata.get(s);
    }
    
    public void setProperty(final String s) {
        this.setProperty(s, Boolean.TRUE);
    }
    
    public void setProperty(final String key, final Object value) {
        if (this.metadata.isEmpty()) {
            this.metadata = Collections.singletonMap(key, value);
        }
        else {
            if (this.metadata.size() == 1) {
                this.metadata = new HashMap<String, Object>(this.metadata);
            }
            this.metadata.put(key, value);
        }
    }
}
