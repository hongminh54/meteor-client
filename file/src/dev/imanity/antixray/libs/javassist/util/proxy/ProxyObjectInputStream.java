// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.util.proxy;

import java.io.ObjectStreamClass;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class ProxyObjectInputStream extends ObjectInputStream
{
    private ClassLoader loader;
    
    public ProxyObjectInputStream(final InputStream in) {
        super(in);
        this.loader = Thread.currentThread().getContextClassLoader();
        if (this.loader == null) {
            this.loader = ClassLoader.getSystemClassLoader();
        }
    }
    
    public void setClassLoader(ClassLoader systemClassLoader) {
        if (systemClassLoader != null) {
            this.loader = systemClassLoader;
        }
        else {
            systemClassLoader = ClassLoader.getSystemClassLoader();
        }
    }
    
    @Override
    protected ObjectStreamClass readClassDescriptor() {
        if (this.readBoolean()) {
            final Class<?> loadClass = this.loader.loadClass((String)this.readObject());
            final int int1 = this.readInt();
            final Class[] interfaces = new Class[int1];
            for (int i = 0; i < int1; ++i) {
                interfaces[i] = this.loader.loadClass((String)this.readObject());
            }
            final byte[] b = new byte[this.readInt()];
            this.read(b);
            final ProxyFactory proxyFactory = new ProxyFactory();
            proxyFactory.setUseCache(true);
            proxyFactory.setUseWriteReplace(false);
            proxyFactory.setSuperclass(loadClass);
            proxyFactory.setInterfaces(interfaces);
            return ObjectStreamClass.lookup(proxyFactory.createClass(b));
        }
        return super.readClassDescriptor();
    }
}
