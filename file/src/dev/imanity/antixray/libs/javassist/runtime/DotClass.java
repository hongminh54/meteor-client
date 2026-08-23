// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.runtime;

public class DotClass
{
    public static NoClassDefFoundError fail(final ClassNotFoundException ex) {
        return new NoClassDefFoundError(ex.getMessage());
    }
}
