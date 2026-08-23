// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.fast;

public class InvalidArgumentCountException extends IllegalArgumentException
{
    private static final long serialVersionUID = 8575190678319065492L;
    
    public InvalidArgumentCountException(final String str, final int i, final int j) {
        super("Invalid amount of arguments for " + str + " (" + i + " given, " + j + " expected)");
    }
}
