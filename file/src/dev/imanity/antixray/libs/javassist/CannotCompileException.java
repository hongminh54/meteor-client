// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist;

import dev.imanity.antixray.libs.javassist.compiler.CompileError;

public class CannotCompileException extends Exception
{
    private static final long serialVersionUID = 1L;
    private Throwable myCause;
    private String message;
    
    @Override
    public synchronized Throwable getCause() {
        return (this.myCause == this) ? null : this.myCause;
    }
    
    @Override
    public synchronized Throwable initCause(final Throwable myCause) {
        this.myCause = myCause;
        return this;
    }
    
    public String getReason() {
        if (this.message != null) {
            return this.message;
        }
        return this.toString();
    }
    
    public CannotCompileException(final String s) {
        super(s);
        this.message = s;
        this.initCause(null);
    }
    
    public CannotCompileException(final Throwable t) {
        super("by " + t.toString());
        this.message = null;
        this.initCause(t);
    }
    
    public CannotCompileException(final String s, final Throwable t) {
        this(s);
        this.initCause(t);
    }
    
    public CannotCompileException(final NotFoundException ex) {
        this("cannot find " + ex.getMessage(), ex);
    }
    
    public CannotCompileException(final CompileError compileError) {
        this("[source error] " + compileError.getMessage(), compileError);
    }
    
    public CannotCompileException(final ClassNotFoundException ex, final String str) {
        this("cannot find " + str, ex);
    }
    
    public CannotCompileException(final ClassFormatError classFormatError, final String str) {
        this("invalid class format: " + str, classFormatError);
    }
}
