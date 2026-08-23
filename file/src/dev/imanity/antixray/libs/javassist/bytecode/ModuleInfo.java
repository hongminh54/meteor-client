// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.io.PrintWriter;
import java.io.DataOutputStream;
import java.util.Map;
import java.io.DataInputStream;

class ModuleInfo extends ConstInfo
{
    static final int tag = 19;
    int name;
    
    public ModuleInfo(final int name, final int n) {
        super(n);
        this.name = name;
    }
    
    public ModuleInfo(final DataInputStream dataInputStream, final int n) {
        super(n);
        this.name = dataInputStream.readUnsignedShort();
    }
    
    @Override
    public int hashCode() {
        return this.name;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof ModuleInfo && ((ModuleInfo)o).name == this.name;
    }
    
    @Override
    public int getTag() {
        return 19;
    }
    
    public String getModuleName(final ConstPool constPool) {
        return constPool.getUtf8Info(this.name);
    }
    
    @Override
    public int copy(final ConstPool constPool, final ConstPool constPool2, final Map<String, String> map) {
        return constPool2.addModuleInfo(constPool2.addUtf8Info(constPool.getUtf8Info(this.name)));
    }
    
    @Override
    public void write(final DataOutputStream dataOutputStream) {
        dataOutputStream.writeByte(19);
        dataOutputStream.writeShort(this.name);
    }
    
    @Override
    public void print(final PrintWriter printWriter) {
        printWriter.print("Module #");
        printWriter.println(this.name);
    }
}
