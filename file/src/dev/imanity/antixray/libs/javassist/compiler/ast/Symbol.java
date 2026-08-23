// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.compiler.ast;

public class Symbol extends ASTree
{
    private static final long serialVersionUID = 1L;
    protected String identifier;
    
    public Symbol(final String identifier) {
        this.identifier = identifier;
    }
    
    public String get() {
        return this.identifier;
    }
    
    @Override
    public String toString() {
        return this.identifier;
    }
    
    @Override
    public void accept(final Visitor visitor) {
        visitor.atSymbol(this);
    }
}
