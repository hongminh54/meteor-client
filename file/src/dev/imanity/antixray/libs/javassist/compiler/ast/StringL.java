// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.compiler.ast;

public class StringL extends ASTree
{
    private static final long serialVersionUID = 1L;
    protected String text;
    
    public StringL(final String text) {
        this.text = text;
    }
    
    public String get() {
        return this.text;
    }
    
    @Override
    public String toString() {
        return "\"" + this.text + "\"";
    }
    
    @Override
    public void accept(final Visitor visitor) {
        visitor.atStringL(this);
    }
}
