// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util;

import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;

public class InputTypeMap<T> extends TypeMap<T> implements Cloneable
{
    public InputTypeMap() {
    }
    
    protected InputTypeMap(final InputTypeMap<T> inputTypeMap) {
        super(inputTypeMap);
    }
    
    @Override
    protected boolean isParentTypeOf(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
        return typeDeclaration2.isInstanceOf(typeDeclaration);
    }
    
    public InputTypeMap<T> clone() {
        return new InputTypeMap<T>(this);
    }
}
