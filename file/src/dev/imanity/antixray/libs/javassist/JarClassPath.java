// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist;

import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URL;
import java.io.InputStream;
import java.util.Iterator;
import java.io.IOException;
import java.io.File;
import java.util.jar.JarEntry;
import java.util.Collections;
import java.util.HashSet;
import java.util.jar.JarFile;
import java.util.Set;

final class JarClassPath implements ClassPath
{
    Set<String> jarfileEntries;
    String jarfileURL;
    
    JarClassPath(final String s) {
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(s);
            this.jarfileEntries = new HashSet<String>();
            for (final JarEntry jarEntry : Collections.list(jarFile.entries())) {
                if (jarEntry.getName().endsWith(".class")) {
                    this.jarfileEntries.add(jarEntry.getName());
                }
            }
            this.jarfileURL = new File(s).getCanonicalFile().toURI().toURL().toString();
            return;
        }
        catch (final IOException ex) {}
        finally {
            if (null != jarFile) {
                try {
                    jarFile.close();
                }
                catch (final IOException ex2) {}
            }
        }
        throw new NotFoundException(s);
    }
    
    @Override
    public InputStream openClassfile(final String str) {
        final URL find = this.find(str);
        if (null != find) {
            try {
                if (ClassPool.cacheOpenedJarFile) {
                    return find.openConnection().getInputStream();
                }
                final URLConnection openConnection = find.openConnection();
                openConnection.setUseCaches(false);
                return openConnection.getInputStream();
            }
            catch (final IOException ex) {
                throw new NotFoundException("broken jar file?: " + str);
            }
        }
        return null;
    }
    
    @Override
    public URL find(final String s) {
        final String string = s.replace('.', '/') + ".class";
        if (this.jarfileEntries.contains(string)) {
            try {
                return new URL(String.format("jar:%s!/%s", this.jarfileURL, string));
            }
            catch (final MalformedURLException ex) {}
        }
        return null;
    }
    
    @Override
    public String toString() {
        return (this.jarfileURL == null) ? "<null>" : this.jarfileURL;
    }
}
