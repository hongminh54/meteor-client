// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

public abstract class AnnotationVisitor
{
    protected final int api;
    protected AnnotationVisitor av;
    
    protected AnnotationVisitor(final int n) {
        this(n, null);
    }
    
    protected AnnotationVisitor(final int api, final AnnotationVisitor av) {
        if (api != 589824 && api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 17432576) {
            throw new IllegalArgumentException(stringConcat$0(api));
        }
        if (api == 17432576) {
            Constants.checkAsmExperimental(this);
        }
        this.api = api;
        this.av = av;
    }
    
    private static /* synthetic */ String stringConcat$0(final int i) {
        return "Unsupported api " + i;
    }
    
    public AnnotationVisitor getDelegate() {
        return this.av;
    }
    
    public void visit(final String s, final Object o) {
        if (this.av != null) {
            this.av.visit(s, o);
        }
    }
    
    public void visitEnum(final String s, final String s2, final String s3) {
        if (this.av != null) {
            this.av.visitEnum(s, s2, s3);
        }
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final String s2) {
        if (this.av != null) {
            return this.av.visitAnnotation(s, s2);
        }
        return null;
    }
    
    public AnnotationVisitor visitArray(final String s) {
        if (this.av != null) {
            return this.av.visitArray(s);
        }
        return null;
    }
    
    public void visitEnd() {
        if (this.av != null) {
            this.av.visitEnd();
        }
    }
}
