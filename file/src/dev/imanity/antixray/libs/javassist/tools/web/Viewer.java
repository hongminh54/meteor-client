// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.tools.web;

import java.io.InputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.net.URL;
import java.lang.reflect.InvocationTargetException;

public class Viewer extends ClassLoader
{
    private String server;
    private int port;
    
    public static void main(final String[] array) {
        if (array.length >= 3) {
            final Viewer viewer = new Viewer(array[0], Integer.parseInt(array[1]));
            final String[] array2 = new String[array.length - 3];
            System.arraycopy(array, 3, array2, 0, array.length - 3);
            viewer.run(array[2], array2);
        }
        else {
            System.err.println("Usage: java javassist.tools.web.Viewer <host> <port> class [args ...]");
        }
    }
    
    public Viewer(final String server, final int port) {
        this.server = server;
        this.port = port;
    }
    
    public String getServer() {
        return this.server;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public void run(final String name, final String[] array) {
        final Class<?> loadClass = this.loadClass(name);
        try {
            loadClass.getDeclaredMethod("main", String[].class).invoke(null, array);
        }
        catch (final InvocationTargetException ex) {
            throw ex.getTargetException();
        }
    }
    
    @Override
    protected synchronized Class<?> loadClass(final String s, final boolean b) {
        Class<?> c = this.findLoadedClass(s);
        if (c == null) {
            c = this.findClass(s);
        }
        if (c == null) {
            throw new ClassNotFoundException(s);
        }
        if (b) {
            this.resolveClass(c);
        }
        return c;
    }
    
    @Override
    protected Class<?> findClass(final String s) {
        Class<?> clazz = null;
        if (s.startsWith("java.") || s.startsWith("javax.") || s.equals("dev.imanity.antixray.libs.javassist.tools.web.Viewer")) {
            clazz = this.findSystemClass(s);
        }
        if (clazz == null) {
            try {
                final byte[] fetchClass = this.fetchClass(s);
                if (fetchClass != null) {
                    clazz = this.defineClass(s, fetchClass, 0, fetchClass.length);
                }
            }
            catch (final Exception ex) {}
        }
        return clazz;
    }
    
    protected byte[] fetchClass(final String str) {
        final URLConnection openConnection = new URL("http", this.server, this.port, "/" + str.replace('.', '/') + ".class").openConnection();
        openConnection.connect();
        final int contentLength = openConnection.getContentLength();
        final InputStream inputStream = openConnection.getInputStream();
        byte[] stream;
        if (contentLength <= 0) {
            stream = this.readStream(inputStream);
        }
        else {
            stream = new byte[contentLength];
            int i = 0;
            do {
                final int read = inputStream.read(stream, i, contentLength - i);
                if (read < 0) {
                    inputStream.close();
                    throw new IOException("the stream was closed: " + str);
                }
                i += read;
            } while (i < contentLength);
        }
        inputStream.close();
        return stream;
    }
    
    private byte[] readStream(final InputStream inputStream) {
        byte[] b = new byte[4096];
        int off = 0;
        int i = 0;
        do {
            off += i;
            if (b.length - off <= 0) {
                final byte[] array = new byte[b.length * 2];
                System.arraycopy(b, 0, array, 0, off);
                b = array;
            }
            i = inputStream.read(b, off, b.length - off);
        } while (i >= 0);
        final byte[] array2 = new byte[off];
        System.arraycopy(b, 0, array2, 0, off);
        return array2;
    }
}
