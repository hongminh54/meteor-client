// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist;

class FieldInitLink
{
    FieldInitLink next;
    CtField field;
    CtField.Initializer init;
    
    FieldInitLink(final CtField field, final CtField.Initializer init) {
        this.next = null;
        this.field = field;
        this.init = init;
    }
}
