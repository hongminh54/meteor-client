// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm.signature;

public abstract class SignatureVisitor
{
    public static final char EXTENDS = '+';
    public static final char SUPER = '-';
    public static final char INSTANCEOF = '=';
    protected final int api;
    
    protected SignatureVisitor(final int api) {
        if (api != 589824 && api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 17432576) {
            throw new IllegalArgumentException(stringConcat$0(api));
        }
        this.api = api;
    }
    
    private static /* synthetic */ String stringConcat$0(final int i) {
        return "Unsupported api " + i;
    }
    
    public void visitFormalTypeParameter(final String s) {
    }
    
    public SignatureVisitor visitClassBound() {
        return this;
    }
    
    public SignatureVisitor visitInterfaceBound() {
        return this;
    }
    
    public SignatureVisitor visitSuperclass() {
        return this;
    }
    
    public SignatureVisitor visitInterface() {
        return this;
    }
    
    public SignatureVisitor visitParameterType() {
        return this;
    }
    
    public SignatureVisitor visitReturnType() {
        return this;
    }
    
    public SignatureVisitor visitExceptionType() {
        return this;
    }
    
    public void visitBaseType(final char c) {
    }
    
    public void visitTypeVariable(final String s) {
    }
    
    public SignatureVisitor visitArrayType() {
        return this;
    }
    
    public void visitClassType(final String s) {
    }
    
    public void visitInnerClassType(final String s) {
    }
    
    public void visitTypeArgument() {
    }
    
    public SignatureVisitor visitTypeArgument(final char c) {
        return this;
    }
    
    public void visitEnd() {
    }
}
