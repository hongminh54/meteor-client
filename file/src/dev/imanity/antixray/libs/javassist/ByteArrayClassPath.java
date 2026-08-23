// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist;

import java.net.URLConnection;
import java.net.MalformedURLException;
import java.net.URLStreamHandler;
import java.net.URL;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ByteArrayClassPath implements ClassPath
{
    protected String classname;
    protected byte[] classfile;
    
    public ByteArrayClassPath(final String classname, final byte[] classfile) {
        this.classname = classname;
        this.classfile = classfile;
    }
    
    @Override
    public String toString() {
        return "byte[]:" + this.classname;
    }
    
    @Override
    public InputStream openClassfile(final String anObject) {
        if (this.classname.equals(anObject)) {
            return new ByteArrayInputStream(this.classfile);
        }
        return null;
    }
    
    @Override
    public URL find(final String anObject) {
        if (this.classname.equals(anObject)) {
            final String string = anObject.replace('.', '/') + ".class";
            try {
                return new URL(null, "file:/ByteArrayClassPath/" + string, new BytecodeURLStreamHandler());
            }
            catch (final MalformedURLException ex) {}
        }
        return null;
    }
    
    private class BytecodeURLStreamHandler extends URLStreamHandler
    {
        @Override
        protected URLConnection openConnection(final URL url) {
            return new BytecodeURLConnection(url);
        }
    }
    
    private class BytecodeURLConnection extends URLConnection
    {
        protected BytecodeURLConnection(final URL url) {
            super(url);
        }
        
        @Override
        public void connect() {
        }
        
        @Override
        public InputStream getInputStream() {
            return new ByteArrayInputStream(ByteArrayClassPath.this.classfile);
        }
        
        @Override
        public int getContentLength() {
            return ByteArrayClassPath.this.classfile.length;
        }
    }
}
