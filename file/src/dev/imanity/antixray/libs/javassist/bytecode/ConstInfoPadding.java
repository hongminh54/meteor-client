// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.io.PrintWriter;
import java.io.DataOutputStream;
import java.util.Map;

class ConstInfoPadding extends ConstInfo
{
    public ConstInfoPadding(final int n) {
        super(n);
    }
    
    @Override
    public int getTag() {
        return 0;
    }
    
    @Override
    public int copy(final ConstPool constPool, final ConstPool constPool2, final Map<String, String> map) {
        return constPool2.addConstInfoPadding();
    }
    
    @Override
    public void write(final DataOutputStream dataOutputStream) {
    }
    
    @Override
    public void print(final PrintWriter printWriter) {
        printWriter.println("padding");
    }
}
