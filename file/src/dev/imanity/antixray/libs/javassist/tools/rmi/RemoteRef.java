// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.tools.rmi;

import java.io.Serializable;

public class RemoteRef implements Serializable
{
    private static final long serialVersionUID = 1L;
    public int oid;
    public String classname;
    
    public RemoteRef(final int oid) {
        this.oid = oid;
        this.classname = null;
    }
    
    public RemoteRef(final int oid, final String classname) {
        this.oid = oid;
        this.classname = classname;
    }
}
