// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.tools.reflect;

public class CannotCreateException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    public CannotCreateException(final String message) {
        super(message);
    }
    
    public CannotCreateException(final Exception ex) {
        super("by " + ex.toString());
    }
}
