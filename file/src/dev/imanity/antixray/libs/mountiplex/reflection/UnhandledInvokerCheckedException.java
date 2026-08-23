// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection;

public class UnhandledInvokerCheckedException extends RuntimeException
{
    public UnhandledInvokerCheckedException(final Throwable cause) {
        super("An error occurred in the invoked method", cause);
    }
}
