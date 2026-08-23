// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.compiler.ast;

public class BinExpr extends Expr
{
    private static final long serialVersionUID = 1L;
    
    private BinExpr(final int n, final ASTree asTree, final ASTList list) {
        super(n, asTree, list);
    }
    
    public static BinExpr makeBin(final int n, final ASTree asTree, final ASTree asTree2) {
        return new BinExpr(n, asTree, new ASTList(asTree2));
    }
    
    @Override
    public void accept(final Visitor visitor) {
        visitor.atBinExpr(this);
    }
}
