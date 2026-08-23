// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

public final class ClassTooLargeException extends IndexOutOfBoundsException
{
    private static final long serialVersionUID = 160715609518896765L;
    private final String className;
    private final int constantPoolCount;
    
    public ClassTooLargeException(final String className, final int constantPoolCount) {
        super(stringConcat$0(className));
        this.className = className;
        this.constantPoolCount = constantPoolCount;
    }
    
    private static /* synthetic */ String stringConcat$0(final String str) {
        return "Class too large: " + str;
    }
    
    public String getClassName() {
        return this.className;
    }
    
    public int getConstantPoolCount() {
        return this.constantPoolCount;
    }
}
