// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

public abstract class RecordComponentVisitor
{
    protected final int api;
    protected RecordComponentVisitor delegate;
    
    protected RecordComponentVisitor(final int n) {
        this(n, null);
    }
    
    protected RecordComponentVisitor(final int api, final RecordComponentVisitor delegate) {
        if (api != 589824 && api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 17432576) {
            throw new IllegalArgumentException(stringConcat$0(api));
        }
        if (api == 17432576) {
            Constants.checkAsmExperimental(this);
        }
        this.api = api;
        this.delegate = delegate;
    }
    
    private static /* synthetic */ String stringConcat$0(final int i) {
        return "Unsupported api " + i;
    }
    
    public RecordComponentVisitor getDelegate() {
        return this.delegate;
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        if (this.delegate != null) {
            return this.delegate.visitAnnotation(s, b);
        }
        return null;
    }
    
    public AnnotationVisitor visitTypeAnnotation(final int n, final TypePath typePath, final String s, final boolean b) {
        if (this.delegate != null) {
            return this.delegate.visitTypeAnnotation(n, typePath, s, b);
        }
        return null;
    }
    
    public void visitAttribute(final Attribute attribute) {
        if (this.delegate != null) {
            this.delegate.visitAttribute(attribute);
        }
    }
    
    public void visitEnd() {
        if (this.delegate != null) {
            this.delegate.visitEnd();
        }
    }
}
