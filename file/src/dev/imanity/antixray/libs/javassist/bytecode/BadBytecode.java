// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

public class BadBytecode extends Exception
{
    private static final long serialVersionUID = 1L;
    
    public BadBytecode(final int i) {
        super("bytecode " + i);
    }
    
    public BadBytecode(final String message) {
        super(message);
    }
    
    public BadBytecode(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public BadBytecode(final MethodInfo methodInfo, final Throwable cause) {
        super(methodInfo.toString() + " in " + methodInfo.getConstPool().getClassName() + ": " + cause.getMessage(), cause);
    }
}
