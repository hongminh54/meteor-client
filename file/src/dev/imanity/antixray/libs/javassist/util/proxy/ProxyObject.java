// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.util.proxy;

public interface ProxyObject extends Proxy
{
    void setHandler(final MethodHandler p0);
    
    MethodHandler getHandler();
}
