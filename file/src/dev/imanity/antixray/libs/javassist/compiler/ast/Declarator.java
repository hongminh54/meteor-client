// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.compiler.ast;

import dev.imanity.antixray.libs.javassist.compiler.TokenId;

public class Declarator extends ASTList implements TokenId
{
    private static final long serialVersionUID = 1L;
    protected int varType;
    protected int arrayDim;
    protected int localVar;
    protected String qualifiedClass;
    
    public Declarator(final int varType, final int arrayDim) {
        super(null);
        this.varType = varType;
        this.arrayDim = arrayDim;
        this.localVar = -1;
        this.qualifiedClass = null;
    }
    
    public Declarator(final ASTList list, final int arrayDim) {
        super(null);
        this.varType = 307;
        this.arrayDim = arrayDim;
        this.localVar = -1;
        this.qualifiedClass = astToClassName(list, '/');
    }
    
    public Declarator(final int varType, final String qualifiedClass, final int arrayDim, final int localVar, final Symbol left) {
        super(null);
        this.varType = varType;
        this.arrayDim = arrayDim;
        this.localVar = localVar;
        this.qualifiedClass = qualifiedClass;
        this.setLeft(left);
        ASTList.append(this, null);
    }
    
    public Declarator make(final Symbol left, final int n, final ASTree asTree) {
        final Declarator declarator = new Declarator(this.varType, this.arrayDim + n);
        declarator.qualifiedClass = this.qualifiedClass;
        declarator.setLeft(left);
        ASTList.append(declarator, asTree);
        return declarator;
    }
    
    public int getType() {
        return this.varType;
    }
    
    public int getArrayDim() {
        return this.arrayDim;
    }
    
    public void addArrayDim(final int n) {
        this.arrayDim += n;
    }
    
    public String getClassName() {
        return this.qualifiedClass;
    }
    
    public void setClassName(final String qualifiedClass) {
        this.qualifiedClass = qualifiedClass;
    }
    
    public Symbol getVariable() {
        return (Symbol)this.getLeft();
    }
    
    public void setVariable(final Symbol left) {
        this.setLeft(left);
    }
    
    public ASTree getInitializer() {
        final ASTList tail = this.tail();
        if (tail != null) {
            return tail.head();
        }
        return null;
    }
    
    public void setLocalVar(final int localVar) {
        this.localVar = localVar;
    }
    
    public int getLocalVar() {
        return this.localVar;
    }
    
    public String getTag() {
        return "decl";
    }
    
    @Override
    public void accept(final Visitor visitor) {
        visitor.atDeclarator(this);
    }
    
    public static String astToClassName(final ASTList list, final char c) {
        if (list == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        astToClassName(sb, list, c);
        return sb.toString();
    }
    
    private static void astToClassName(final StringBuilder sb, ASTList tail, final char c) {
        while (true) {
            final ASTree head = tail.head();
            if (head instanceof Symbol) {
                sb.append(((Symbol)head).get());
            }
            else if (head instanceof ASTList) {
                astToClassName(sb, (ASTList)head, c);
            }
            tail = tail.tail();
            if (tail == null) {
                break;
            }
            sb.append(c);
        }
    }
}
