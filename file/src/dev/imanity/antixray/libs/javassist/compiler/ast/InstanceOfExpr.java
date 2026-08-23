// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.compiler.ast;

public class InstanceOfExpr extends CastExpr
{
    private static final long serialVersionUID = 1L;
    
    public InstanceOfExpr(final ASTList list, final int n, final ASTree asTree) {
        super(list, n, asTree);
    }
    
    public InstanceOfExpr(final int n, final int n2, final ASTree asTree) {
        super(n, n2, asTree);
    }
    
    @Override
    public String getTag() {
        return "instanceof:" + this.castType + ":" + this.arrayDim;
    }
    
    @Override
    public void accept(final Visitor visitor) {
        visitor.atInstanceOfExpr(this);
    }
}
