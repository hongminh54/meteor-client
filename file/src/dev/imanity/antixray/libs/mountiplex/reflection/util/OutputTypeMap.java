// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util;

import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;

public class OutputTypeMap<T> extends TypeMap<T> implements Cloneable
{
    public OutputTypeMap() {
    }
    
    protected OutputTypeMap(final OutputTypeMap<T> outputTypeMap) {
        super(outputTypeMap);
    }
    
    @Override
    protected boolean isParentTypeOf(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
        return typeDeclaration.isInstanceOf(typeDeclaration2);
    }
    
    public OutputTypeMap<T> clone() {
        return new OutputTypeMap<T>(this);
    }
}
