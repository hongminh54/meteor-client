// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.io.PrintWriter;
import java.io.DataOutputStream;
import java.util.Map;
import java.io.DataInputStream;

class ClassInfo extends ConstInfo
{
    static final int tag = 7;
    int name;
    
    public ClassInfo(final int name, final int n) {
        super(n);
        this.name = name;
    }
    
    public ClassInfo(final DataInputStream dataInputStream, final int n) {
        super(n);
        this.name = dataInputStream.readUnsignedShort();
    }
    
    @Override
    public int hashCode() {
        return this.name;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof ClassInfo && ((ClassInfo)o).name == this.name;
    }
    
    @Override
    public int getTag() {
        return 7;
    }
    
    @Override
    public String getClassName(final ConstPool constPool) {
        return constPool.getUtf8Info(this.name);
    }
    
    @Override
    public void renameClass(final ConstPool constPool, final String anObject, final String s, final Map<ConstInfo, ConstInfo> map) {
        final String utf8Info = constPool.getUtf8Info(this.name);
        String s2 = null;
        if (utf8Info.equals(anObject)) {
            s2 = s;
        }
        else if (utf8Info.charAt(0) == '[') {
            final String rename = Descriptor.rename(utf8Info, anObject, s);
            if (utf8Info != rename) {
                s2 = rename;
            }
        }
        if (s2 != null) {
            if (map == null) {
                this.name = constPool.addUtf8Info(s2);
            }
            else {
                map.remove(this);
                this.name = constPool.addUtf8Info(s2);
                map.put(this, this);
            }
        }
    }
    
    @Override
    public void renameClass(final ConstPool constPool, final Map<String, String> map, final Map<ConstInfo, ConstInfo> map2) {
        final String utf8Info = constPool.getUtf8Info(this.name);
        String s = null;
        if (utf8Info.charAt(0) == '[') {
            final String rename = Descriptor.rename(utf8Info, map);
            if (utf8Info != rename) {
                s = rename;
            }
        }
        else {
            final String s2 = map.get(utf8Info);
            if (s2 != null && !s2.equals(utf8Info)) {
                s = s2;
            }
        }
        if (s != null) {
            if (map2 == null) {
                this.name = constPool.addUtf8Info(s);
            }
            else {
                map2.remove(this);
                this.name = constPool.addUtf8Info(s);
                map2.put(this, this);
            }
        }
    }
    
    @Override
    public int copy(final ConstPool constPool, final ConstPool constPool2, final Map<String, String> map) {
        String utf8Info = constPool.getUtf8Info(this.name);
        if (map != null) {
            final String s = map.get(utf8Info);
            if (s != null) {
                utf8Info = s;
            }
        }
        return constPool2.addClassInfo(utf8Info);
    }
    
    @Override
    public void write(final DataOutputStream dataOutputStream) {
        dataOutputStream.writeByte(7);
        dataOutputStream.writeShort(this.name);
    }
    
    @Override
    public void print(final PrintWriter printWriter) {
        printWriter.print("Class #");
        printWriter.println(this.name);
    }
}
