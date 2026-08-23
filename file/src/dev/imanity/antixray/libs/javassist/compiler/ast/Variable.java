// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.compiler.ast;

public class Variable extends Symbol
{
    private static final long serialVersionUID = 1L;
    protected Declarator declarator;
    
    public Variable(final String s, final Declarator declarator) {
        super(s);
        this.declarator = declarator;
    }
    
    public Declarator getDeclarator() {
        return this.declarator;
    }
    
    @Override
    public String toString() {
        return this.identifier + ":" + this.declarator.getType();
    }
    
    @Override
    public void accept(final Visitor visitor) {
        visitor.atVariable(this);
    }
}
