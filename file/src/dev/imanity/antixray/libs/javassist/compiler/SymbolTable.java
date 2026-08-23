// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.compiler;

import dev.imanity.antixray.libs.javassist.compiler.ast.Declarator;
import java.util.HashMap;

public final class SymbolTable extends HashMap<String, Declarator>
{
    private static final long serialVersionUID = 1L;
    private SymbolTable parent;
    
    public SymbolTable() {
        this(null);
    }
    
    public SymbolTable(final SymbolTable parent) {
        this.parent = parent;
    }
    
    public SymbolTable getParent() {
        return this.parent;
    }
    
    public Declarator lookup(final String key) {
        final Declarator declarator = ((HashMap<K, Declarator>)this).get(key);
        if (declarator == null && this.parent != null) {
            return this.parent.lookup(key);
        }
        return declarator;
    }
    
    public void append(final String key, final Declarator value) {
        this.put(key, value);
    }
}
