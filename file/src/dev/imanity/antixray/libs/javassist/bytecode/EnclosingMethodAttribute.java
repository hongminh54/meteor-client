// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.util.Map;
import java.io.DataInputStream;

public class EnclosingMethodAttribute extends AttributeInfo
{
    public static final String tag = "EnclosingMethod";
    
    EnclosingMethodAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) {
        super(constPool, n, dataInputStream);
    }
    
    public EnclosingMethodAttribute(final ConstPool constPool, final String s, final String s2, final String s3) {
        super(constPool, "EnclosingMethod");
        final int addClassInfo = constPool.addClassInfo(s);
        final int addNameAndTypeInfo = constPool.addNameAndTypeInfo(s2, s3);
        this.set(new byte[] { (byte)(addClassInfo >>> 8), (byte)addClassInfo, (byte)(addNameAndTypeInfo >>> 8), (byte)addNameAndTypeInfo });
    }
    
    public EnclosingMethodAttribute(final ConstPool constPool, final String s) {
        super(constPool, "EnclosingMethod");
        final int addClassInfo = constPool.addClassInfo(s);
        final int n = 0;
        this.set(new byte[] { (byte)(addClassInfo >>> 8), (byte)addClassInfo, (byte)(n >>> 8), (byte)n });
    }
    
    public int classIndex() {
        return ByteArray.readU16bit(this.get(), 0);
    }
    
    public int methodIndex() {
        return ByteArray.readU16bit(this.get(), 2);
    }
    
    public String className() {
        return this.getConstPool().getClassInfo(this.classIndex());
    }
    
    public String methodName() {
        final ConstPool constPool = this.getConstPool();
        final int methodIndex = this.methodIndex();
        if (methodIndex == 0) {
            return "<clinit>";
        }
        return constPool.getUtf8Info(constPool.getNameAndTypeName(methodIndex));
    }
    
    public String methodDescriptor() {
        final ConstPool constPool = this.getConstPool();
        return constPool.getUtf8Info(constPool.getNameAndTypeDescriptor(this.methodIndex()));
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map<String, String> map) {
        if (this.methodIndex() == 0) {
            return new EnclosingMethodAttribute(constPool, this.className());
        }
        return new EnclosingMethodAttribute(constPool, this.className(), this.methodName(), this.methodDescriptor());
    }
}
