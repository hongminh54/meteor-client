// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist;

import java.net.URL;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.ref.Reference;

public class LoaderClassPath implements ClassPath
{
    private Reference<ClassLoader> clref;
    
    public LoaderClassPath(final ClassLoader referent) {
        this.clref = new WeakReference<ClassLoader>(referent);
    }
    
    @Override
    public String toString() {
        return (this.clref.get() == null) ? "<null>" : this.clref.get().toString();
    }
    
    @Override
    public InputStream openClassfile(final String s) {
        final String string = s.replace('.', '/') + ".class";
        final ClassLoader classLoader = this.clref.get();
        if (classLoader == null) {
            return null;
        }
        return classLoader.getResourceAsStream(string);
    }
    
    @Override
    public URL find(final String s) {
        final String string = s.replace('.', '/') + ".class";
        final ClassLoader classLoader = this.clref.get();
        if (classLoader == null) {
            return null;
        }
        return classLoader.getResource(string);
    }
}
