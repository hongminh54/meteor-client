// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

final class CurrentFrame extends Frame
{
    CurrentFrame(final Label label) {
        super(label);
    }
    
    @Override
    void execute(final int n, final int n2, final Symbol symbol, final SymbolTable symbolTable) {
        super.execute(n, n2, symbol, symbolTable);
        final Frame frame = new Frame(null);
        this.merge(symbolTable, frame, 0);
        this.copyFrom(frame);
    }
}
