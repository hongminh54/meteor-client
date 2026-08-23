// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist;

public class NotFoundException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    public NotFoundException(final String message) {
        super(message);
    }
    
    public NotFoundException(final String str, final Exception ex) {
        super(str + " because of " + ex.toString());
    }
}
