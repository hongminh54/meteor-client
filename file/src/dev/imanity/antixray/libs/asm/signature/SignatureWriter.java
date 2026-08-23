// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm.signature;

public class SignatureWriter extends SignatureVisitor
{
    private final StringBuilder stringBuilder;
    private boolean hasFormals;
    private boolean hasParameters;
    private int argumentStack;
    
    public SignatureWriter() {
        this(new StringBuilder());
    }
    
    private SignatureWriter(final StringBuilder stringBuilder) {
        super(589824);
        this.argumentStack = 1;
        this.stringBuilder = stringBuilder;
    }
    
    @Override
    public void visitFormalTypeParameter(final String str) {
        if (!this.hasFormals) {
            this.hasFormals = true;
            this.stringBuilder.append('<');
        }
        this.stringBuilder.append(str);
        this.stringBuilder.append(':');
    }
    
    @Override
    public SignatureVisitor visitClassBound() {
        return this;
    }
    
    @Override
    public SignatureVisitor visitInterfaceBound() {
        this.stringBuilder.append(':');
        return this;
    }
    
    @Override
    public SignatureVisitor visitSuperclass() {
        this.endFormals();
        return this;
    }
    
    @Override
    public SignatureVisitor visitInterface() {
        return this;
    }
    
    @Override
    public SignatureVisitor visitParameterType() {
        this.endFormals();
        if (!this.hasParameters) {
            this.hasParameters = true;
            this.stringBuilder.append('(');
        }
        return this;
    }
    
    @Override
    public SignatureVisitor visitReturnType() {
        this.endFormals();
        if (!this.hasParameters) {
            this.stringBuilder.append('(');
        }
        this.stringBuilder.append(')');
        return this;
    }
    
    @Override
    public SignatureVisitor visitExceptionType() {
        this.stringBuilder.append('^');
        return this;
    }
    
    @Override
    public void visitBaseType(final char c) {
        this.stringBuilder.append(c);
    }
    
    @Override
    public void visitTypeVariable(final String str) {
        this.stringBuilder.append('T');
        this.stringBuilder.append(str);
        this.stringBuilder.append(';');
    }
    
    @Override
    public SignatureVisitor visitArrayType() {
        this.stringBuilder.append('[');
        return this;
    }
    
    @Override
    public void visitClassType(final String str) {
        this.stringBuilder.append('L');
        this.stringBuilder.append(str);
        this.argumentStack <<= 1;
    }
    
    @Override
    public void visitInnerClassType(final String str) {
        this.endArguments();
        this.stringBuilder.append('.');
        this.stringBuilder.append(str);
        this.argumentStack <<= 1;
    }
    
    @Override
    public void visitTypeArgument() {
        if ((this.argumentStack & 0x1) == 0x0) {
            this.argumentStack |= 0x1;
            this.stringBuilder.append('<');
        }
        this.stringBuilder.append('*');
    }
    
    @Override
    public SignatureVisitor visitTypeArgument(final char c) {
        if ((this.argumentStack & 0x1) == 0x0) {
            this.argumentStack |= 0x1;
            this.stringBuilder.append('<');
        }
        if (c != '=') {
            this.stringBuilder.append(c);
        }
        return ((this.argumentStack & Integer.MIN_VALUE) == 0x0) ? this : new SignatureWriter(this.stringBuilder);
    }
    
    @Override
    public void visitEnd() {
        this.endArguments();
        this.stringBuilder.append(';');
    }
    
    @Override
    public String toString() {
        return this.stringBuilder.toString();
    }
    
    private void endFormals() {
        if (this.hasFormals) {
            this.hasFormals = false;
            this.stringBuilder.append('>');
        }
    }
    
    private void endArguments() {
        if ((this.argumentStack & 0x1) == 0x1) {
            this.stringBuilder.append('>');
        }
        this.argumentStack >>>= 1;
    }
}
