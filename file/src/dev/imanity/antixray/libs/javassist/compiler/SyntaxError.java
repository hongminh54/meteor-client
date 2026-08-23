// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.compiler;

public class SyntaxError extends CompileError
{
    private static final long serialVersionUID = 1L;
    
    public SyntaxError(final Lex lex) {
        super("syntax error near \"" + lex.getTextAround() + "\"", lex);
    }
}
