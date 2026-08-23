// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

final class Handler
{
    final Label startPc;
    final Label endPc;
    final Label handlerPc;
    final int catchType;
    final String catchTypeDescriptor;
    Handler nextHandler;
    
    Handler(final Label startPc, final Label endPc, final Label handlerPc, final int catchType, final String catchTypeDescriptor) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlerPc = handlerPc;
        this.catchType = catchType;
        this.catchTypeDescriptor = catchTypeDescriptor;
    }
    
    Handler(final Handler handler, final Label label, final Label label2) {
        this(label, label2, handler.handlerPc, handler.catchType, handler.catchTypeDescriptor);
        this.nextHandler = handler.nextHandler;
    }
    
    static Handler removeRange(final Handler handler, final Label label, final Label label2) {
        if (handler == null) {
            return null;
        }
        handler.nextHandler = removeRange(handler.nextHandler, label, label2);
        final int bytecodeOffset = handler.startPc.bytecodeOffset;
        final int bytecodeOffset2 = handler.endPc.bytecodeOffset;
        final int bytecodeOffset3 = label.bytecodeOffset;
        final int n = (label2 == null) ? Integer.MAX_VALUE : label2.bytecodeOffset;
        if (bytecodeOffset3 >= bytecodeOffset2 || n <= bytecodeOffset) {
            return handler;
        }
        if (bytecodeOffset3 <= bytecodeOffset) {
            if (n >= bytecodeOffset2) {
                return handler.nextHandler;
            }
            return new Handler(handler, label2, handler.endPc);
        }
        else {
            if (n >= bytecodeOffset2) {
                return new Handler(handler, handler.startPc, label);
            }
            handler.nextHandler = new Handler(handler, label2, handler.endPc);
            return new Handler(handler, handler.startPc, label);
        }
    }
    
    static int getExceptionTableLength(final Handler handler) {
        int n = 0;
        for (Handler nextHandler = handler; nextHandler != null; nextHandler = nextHandler.nextHandler) {
            ++n;
        }
        return n;
    }
    
    static int getExceptionTableSize(final Handler handler) {
        return 2 + 8 * getExceptionTableLength(handler);
    }
    
    static void putExceptionTable(final Handler handler, final ByteVector byteVector) {
        byteVector.putShort(getExceptionTableLength(handler));
        for (Handler nextHandler = handler; nextHandler != null; nextHandler = nextHandler.nextHandler) {
            byteVector.putShort(nextHandler.startPc.bytecodeOffset).putShort(nextHandler.endPc.bytecodeOffset).putShort(nextHandler.handlerPc.bytecodeOffset).putShort(nextHandler.catchType);
        }
    }
}
