// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers;

import dev.imanity.antixray.libs.mountiplex.reflection.util.StringBuffer;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.context.DeclarationParserContext;

abstract class MacroPathParser extends MacroParser
{
    public MacroPathParser(final String s) {
        super(s);
    }
    
    public abstract void runMacro(final String p0, final DeclarationParserContext p1);
    
    @Override
    public void parse(final ParserStringBuffer parserStringBuffer, final DeclarationParserContext declarationParserContext) {
        super.parse(parserStringBuffer, declarationParserContext);
        String s = null;
        final StringBuffer value = parserStringBuffer.get();
        for (int i = 0; i < value.length(); ++i) {
            if (MountiplexUtil.containsChar(value.charAt(i), ParserStringBuffer.INVALID_NAME_CHARACTERS)) {
                s = value.substringToString(0, i);
                break;
            }
        }
        if (s == null) {
            s = value.toString();
        }
        parserStringBuffer.trimLine();
        this.runMacro(s, declarationParserContext);
    }
}
