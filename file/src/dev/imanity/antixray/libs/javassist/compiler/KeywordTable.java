// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.compiler;

import java.util.HashMap;

public final class KeywordTable extends HashMap<String, Integer>
{
    private static final long serialVersionUID = 1L;
    
    public int lookup(final String s) {
        return this.containsKey(s) ? ((HashMap<K, Integer>)this).get(s) : -1;
    }
    
    public void append(final String key, final int i) {
        this.put(key, i);
    }
}
