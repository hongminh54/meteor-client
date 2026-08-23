// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.tools.rmi;

public class ObjectNotFoundException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    public ObjectNotFoundException(final String str) {
        super(str + " is not exported");
    }
    
    public ObjectNotFoundException(final String str, final Exception ex) {
        super(str + " because of " + ex.toString());
    }
}
