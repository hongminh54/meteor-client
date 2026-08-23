// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.compiler.ast;

public class FieldDecl extends ASTList
{
    private static final long serialVersionUID = 1L;
    
    public FieldDecl(final ASTree asTree, final ASTList list) {
        super(asTree, list);
    }
    
    public ASTList getModifiers() {
        return (ASTList)this.getLeft();
    }
    
    public Declarator getDeclarator() {
        return (Declarator)this.tail().head();
    }
    
    public ASTree getInit() {
        return this.sublist(2).head();
    }
    
    @Override
    public void accept(final Visitor visitor) {
        visitor.atFieldDecl(this);
    }
}
