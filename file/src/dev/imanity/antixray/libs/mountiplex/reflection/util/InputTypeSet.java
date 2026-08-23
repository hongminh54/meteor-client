// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util;

import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;

public class InputTypeSet
{
    private final InputTypeMap<Object> map;
    
    public InputTypeSet() {
        this.map = new InputTypeMap<Object>();
    }
    
    public void add(final TypeDeclaration typeDeclaration) {
        this.map.put(typeDeclaration, new Object());
    }
    
    public boolean contains(final TypeDeclaration typeDeclaration) {
        return this.map.containsKey(typeDeclaration);
    }
    
    public void clear() {
        this.map.clear();
    }
}
