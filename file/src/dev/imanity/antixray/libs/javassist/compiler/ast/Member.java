// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.compiler.ast;

import dev.imanity.antixray.libs.javassist.CtField;

public class Member extends Symbol
{
    private static final long serialVersionUID = 1L;
    private CtField field;
    
    public Member(final String s) {
        super(s);
        this.field = null;
    }
    
    public void setField(final CtField field) {
        this.field = field;
    }
    
    public CtField getField() {
        return this.field;
    }
    
    @Override
    public void accept(final Visitor visitor) {
        visitor.atMember(this);
    }
}
