// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.tools.rmi;

public class RemoteException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    
    public RemoteException(final String message) {
        super(message);
    }
    
    public RemoteException(final Exception ex) {
        super("by " + ex.toString());
    }
}
