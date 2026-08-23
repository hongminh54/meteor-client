// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers;

import dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.context.DeclarationParserContext;

abstract class MacroParser implements DeclarationParser
{
    private final String prefix;
    
    public MacroParser(final String str) {
        this.prefix = str + " ";
    }
    
    @Override
    public boolean detect(final ParserStringBuffer parserStringBuffer, final DeclarationParserContext declarationParserContext) {
        return parserStringBuffer.startsWith(this.prefix);
    }
    
    @Override
    public void parse(final ParserStringBuffer parserStringBuffer, final DeclarationParserContext declarationParserContext) {
        parserStringBuffer.trimWhitespace(this.prefix.length());
    }
}
