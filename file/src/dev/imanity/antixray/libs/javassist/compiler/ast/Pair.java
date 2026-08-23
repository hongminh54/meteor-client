// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.compiler.ast;

public class Pair extends ASTree
{
    private static final long serialVersionUID = 1L;
    protected ASTree left;
    protected ASTree right;
    
    public Pair(final ASTree left, final ASTree right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public void accept(final Visitor visitor) {
        visitor.atPair(this);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("(<Pair> ");
        sb.append((this.left == null) ? "<null>" : this.left.toString());
        sb.append(" . ");
        sb.append((this.right == null) ? "<null>" : this.right.toString());
        sb.append(')');
        return sb.toString();
    }
    
    @Override
    public ASTree getLeft() {
        return this.left;
    }
    
    @Override
    public ASTree getRight() {
        return this.right;
    }
    
    @Override
    public void setLeft(final ASTree left) {
        this.left = left;
    }
    
    @Override
    public void setRight(final ASTree right) {
        this.right = right;
    }
}
