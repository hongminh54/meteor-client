// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.DataOutputStream;
import java.util.Map;

abstract class ConstInfo
{
    int index;
    
    public ConstInfo(final int index) {
        this.index = index;
    }
    
    public abstract int getTag();
    
    public String getClassName(final ConstPool constPool) {
        return null;
    }
    
    public void renameClass(final ConstPool constPool, final String s, final String s2, final Map<ConstInfo, ConstInfo> map) {
    }
    
    public void renameClass(final ConstPool constPool, final Map<String, String> map, final Map<ConstInfo, ConstInfo> map2) {
    }
    
    public abstract int copy(final ConstPool p0, final ConstPool p1, final Map<String, String> p2);
    
    public abstract void write(final DataOutputStream p0);
    
    public abstract void print(final PrintWriter p0);
    
    @Override
    public String toString() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        this.print(new PrintWriter(out));
        return out.toString();
    }
}
