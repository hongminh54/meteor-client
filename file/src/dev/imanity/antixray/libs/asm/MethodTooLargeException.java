// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

public final class MethodTooLargeException extends IndexOutOfBoundsException
{
    private static final long serialVersionUID = 6807380416709738314L;
    private final String className;
    private final String methodName;
    private final String descriptor;
    private final int codeSize;
    
    public MethodTooLargeException(final String className, final String methodName, final String descriptor, final int codeSize) {
        super(stringConcat$0(className, methodName, descriptor));
        this.className = className;
        this.methodName = methodName;
        this.descriptor = descriptor;
        this.codeSize = codeSize;
    }
    
    private static /* synthetic */ String stringConcat$0(final String str, final String str2, final String str3) {
        return "Method too large: " + str + "." + str2 + " " + str3;
    }
    
    public String getClassName() {
        return this.className;
    }
    
    public String getMethodName() {
        return this.methodName;
    }
    
    public String getDescriptor() {
        return this.descriptor;
    }
    
    public int getCodeSize() {
        return this.codeSize;
    }
}
