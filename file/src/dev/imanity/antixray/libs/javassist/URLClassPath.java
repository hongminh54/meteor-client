// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;
import java.io.InputStream;

public class URLClassPath implements ClassPath
{
    protected String hostname;
    protected int port;
    protected String directory;
    protected String packageName;
    
    public URLClassPath(final String hostname, final int port, final String directory, final String packageName) {
        this.hostname = hostname;
        this.port = port;
        this.directory = directory;
        this.packageName = packageName;
    }
    
    @Override
    public String toString() {
        return this.hostname + ":" + this.port + this.directory;
    }
    
    @Override
    public InputStream openClassfile(final String s) {
        try {
            final URLConnection openClassfile0 = this.openClassfile0(s);
            if (openClassfile0 != null) {
                return openClassfile0.getInputStream();
            }
        }
        catch (final IOException ex) {}
        return null;
    }
    
    private URLConnection openClassfile0(final String s) {
        if (this.packageName == null || s.startsWith(this.packageName)) {
            return fetchClass0(this.hostname, this.port, this.directory + s.replace('.', '/') + ".class");
        }
        return null;
    }
    
    @Override
    public URL find(final String s) {
        try {
            final URLConnection openClassfile0 = this.openClassfile0(s);
            final InputStream inputStream = openClassfile0.getInputStream();
            if (inputStream != null) {
                inputStream.close();
                return openClassfile0.getURL();
            }
        }
        catch (final IOException ex) {}
        return null;
    }
    
    public static byte[] fetchClass(final String s, final int n, final String str, final String str2) {
        final URLConnection fetchClass0 = fetchClass0(s, n, str + str2.replace('.', '/') + ".class");
        final int contentLength = fetchClass0.getContentLength();
        final InputStream inputStream = fetchClass0.getInputStream();
        byte[] stream;
        try {
            if (contentLength <= 0) {
                stream = ClassPoolTail.readStream(inputStream);
            }
            else {
                stream = new byte[contentLength];
                int i = 0;
                do {
                    final int read = inputStream.read(stream, i, contentLength - i);
                    if (read < 0) {
                        throw new IOException("the stream was closed: " + str2);
                    }
                    i += read;
                } while (i < contentLength);
            }
        }
        finally {
            inputStream.close();
        }
        return stream;
    }
    
    private static URLConnection fetchClass0(final String host, final int port, final String file) {
        URL url;
        try {
            url = new URL("http", host, port, file);
        }
        catch (final MalformedURLException ex) {
            throw new IOException("invalid URL?");
        }
        final URLConnection openConnection = url.openConnection();
        openConnection.connect();
        return openConnection;
    }
}
