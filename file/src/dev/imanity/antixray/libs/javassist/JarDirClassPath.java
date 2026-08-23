// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist;

import java.net.URL;
import java.io.InputStream;
import java.io.FilenameFilter;
import java.io.File;

final class JarDirClassPath implements ClassPath
{
    JarClassPath[] jars;
    
    JarDirClassPath(final String pathname) {
        final File[] listFiles = new File(pathname).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(final File file, String lowerCase) {
                lowerCase = lowerCase.toLowerCase();
                return lowerCase.endsWith(".jar") || lowerCase.endsWith(".zip");
            }
        });
        if (listFiles != null) {
            this.jars = new JarClassPath[listFiles.length];
            for (int i = 0; i < listFiles.length; ++i) {
                this.jars[i] = new JarClassPath(listFiles[i].getPath());
            }
        }
    }
    
    @Override
    public InputStream openClassfile(final String s) {
        if (this.jars != null) {
            for (int i = 0; i < this.jars.length; ++i) {
                final InputStream openClassfile = this.jars[i].openClassfile(s);
                if (openClassfile != null) {
                    return openClassfile;
                }
            }
        }
        return null;
    }
    
    @Override
    public URL find(final String s) {
        if (this.jars != null) {
            for (int i = 0; i < this.jars.length; ++i) {
                final URL find = this.jars[i].find(s);
                if (find != null) {
                    return find;
                }
            }
        }
        return null;
    }
}
