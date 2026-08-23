// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

public abstract class ModuleVisitor
{
    protected final int api;
    protected ModuleVisitor mv;
    
    protected ModuleVisitor(final int n) {
        this(n, null);
    }
    
    protected ModuleVisitor(final int api, final ModuleVisitor mv) {
        if (api != 589824 && api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 17432576) {
            throw new IllegalArgumentException(stringConcat$0(api));
        }
        if (api == 17432576) {
            Constants.checkAsmExperimental(this);
        }
        this.api = api;
        this.mv = mv;
    }
    
    private static /* synthetic */ String stringConcat$0(final int i) {
        return "Unsupported api " + i;
    }
    
    public ModuleVisitor getDelegate() {
        return this.mv;
    }
    
    public void visitMainClass(final String s) {
        if (this.mv != null) {
            this.mv.visitMainClass(s);
        }
    }
    
    public void visitPackage(final String s) {
        if (this.mv != null) {
            this.mv.visitPackage(s);
        }
    }
    
    public void visitRequire(final String s, final int n, final String s2) {
        if (this.mv != null) {
            this.mv.visitRequire(s, n, s2);
        }
    }
    
    public void visitExport(final String s, final int n, final String... array) {
        if (this.mv != null) {
            this.mv.visitExport(s, n, array);
        }
    }
    
    public void visitOpen(final String s, final int n, final String... array) {
        if (this.mv != null) {
            this.mv.visitOpen(s, n, array);
        }
    }
    
    public void visitUse(final String s) {
        if (this.mv != null) {
            this.mv.visitUse(s);
        }
    }
    
    public void visitProvide(final String s, final String... array) {
        if (this.mv != null) {
            this.mv.visitProvide(s, array);
        }
    }
    
    public void visitEnd() {
        if (this.mv != null) {
            this.mv.visitEnd();
        }
    }
}
