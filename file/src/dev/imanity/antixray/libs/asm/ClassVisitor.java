// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

public abstract class ClassVisitor
{
    protected final int api;
    protected ClassVisitor cv;
    
    protected ClassVisitor(final int n) {
        this(n, null);
    }
    
    protected ClassVisitor(final int api, final ClassVisitor cv) {
        if (api != 589824 && api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 17432576) {
            throw new IllegalArgumentException(stringConcat$0(api));
        }
        if (api == 17432576) {
            Constants.checkAsmExperimental(this);
        }
        this.api = api;
        this.cv = cv;
    }
    
    private static /* synthetic */ String stringConcat$0(final int i) {
        return "Unsupported api " + i;
    }
    
    public ClassVisitor getDelegate() {
        return this.cv;
    }
    
    public void visit(final int n, final int n2, final String s, final String s2, final String s3, final String[] array) {
        if (this.api < 524288 && (n2 & 0x10000) != 0x0) {
            throw new UnsupportedOperationException("Records requires ASM8");
        }
        if (this.cv != null) {
            this.cv.visit(n, n2, s, s2, s3, array);
        }
    }
    
    public void visitSource(final String s, final String s2) {
        if (this.cv != null) {
            this.cv.visitSource(s, s2);
        }
    }
    
    public ModuleVisitor visitModule(final String s, final int n, final String s2) {
        if (this.api < 393216) {
            throw new UnsupportedOperationException("Module requires ASM6");
        }
        if (this.cv != null) {
            return this.cv.visitModule(s, n, s2);
        }
        return null;
    }
    
    public void visitNestHost(final String s) {
        if (this.api < 458752) {
            throw new UnsupportedOperationException("NestHost requires ASM7");
        }
        if (this.cv != null) {
            this.cv.visitNestHost(s);
        }
    }
    
    public void visitOuterClass(final String s, final String s2, final String s3) {
        if (this.cv != null) {
            this.cv.visitOuterClass(s, s2, s3);
        }
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        if (this.cv != null) {
            return this.cv.visitAnnotation(s, b);
        }
        return null;
    }
    
    public AnnotationVisitor visitTypeAnnotation(final int n, final TypePath typePath, final String s, final boolean b) {
        if (this.api < 327680) {
            throw new UnsupportedOperationException("TypeAnnotation requires ASM5");
        }
        if (this.cv != null) {
            return this.cv.visitTypeAnnotation(n, typePath, s, b);
        }
        return null;
    }
    
    public void visitAttribute(final Attribute attribute) {
        if (this.cv != null) {
            this.cv.visitAttribute(attribute);
        }
    }
    
    public void visitNestMember(final String s) {
        if (this.api < 458752) {
            throw new UnsupportedOperationException("NestMember requires ASM7");
        }
        if (this.cv != null) {
            this.cv.visitNestMember(s);
        }
    }
    
    public void visitPermittedSubclass(final String s) {
        if (this.api < 589824) {
            throw new UnsupportedOperationException("PermittedSubclasses requires ASM9");
        }
        if (this.cv != null) {
            this.cv.visitPermittedSubclass(s);
        }
    }
    
    public void visitInnerClass(final String s, final String s2, final String s3, final int n) {
        if (this.cv != null) {
            this.cv.visitInnerClass(s, s2, s3, n);
        }
    }
    
    public RecordComponentVisitor visitRecordComponent(final String s, final String s2, final String s3) {
        if (this.api < 524288) {
            throw new UnsupportedOperationException("Record requires ASM8");
        }
        if (this.cv != null) {
            return this.cv.visitRecordComponent(s, s2, s3);
        }
        return null;
    }
    
    public FieldVisitor visitField(final int n, final String s, final String s2, final String s3, final Object o) {
        if (this.cv != null) {
            return this.cv.visitField(n, s, s2, s3, o);
        }
        return null;
    }
    
    public MethodVisitor visitMethod(final int n, final String s, final String s2, final String s3, final String[] array) {
        if (this.cv != null) {
            return this.cv.visitMethod(n, s, s2, s3, array);
        }
        return null;
    }
    
    public void visitEnd() {
        if (this.cv != null) {
            this.cv.visitEnd();
        }
    }
}
