// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis;

public class ObjenesisException extends RuntimeException
{
    private static final long serialVersionUID = -2677230016262426968L;
    
    public ObjenesisException(final String message) {
        super(message);
    }
    
    public ObjenesisException(final Throwable cause) {
        super(cause);
    }
    
    public ObjenesisException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
