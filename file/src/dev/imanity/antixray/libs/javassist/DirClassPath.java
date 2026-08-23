// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;

final class DirClassPath implements ClassPath
{
    String directory;
    
    DirClassPath(final String directory) {
        this.directory = directory;
    }
    
    @Override
    public InputStream openClassfile(final String s) {
        try {
            final char separatorChar = File.separatorChar;
            return new FileInputStream(this.directory + separatorChar + s.replace('.', separatorChar) + ".class");
        }
        catch (final FileNotFoundException ex) {}
        catch (final SecurityException ex2) {}
        return null;
    }
    
    @Override
    public URL find(final String s) {
        final char separatorChar = File.separatorChar;
        final File file = new File(this.directory + separatorChar + s.replace('.', separatorChar) + ".class");
        if (file.exists()) {
            try {
                return file.getCanonicalFile().toURI().toURL();
            }
            catch (final MalformedURLException ex) {}
            catch (final IOException ex2) {}
        }
        return null;
    }
    
    @Override
    public String toString() {
        return this.directory;
    }
}
