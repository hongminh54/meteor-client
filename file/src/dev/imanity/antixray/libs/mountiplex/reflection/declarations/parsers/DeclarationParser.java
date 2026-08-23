// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers;

import dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.context.DeclarationParserContext;

public interface DeclarationParser
{
    boolean detect(final ParserStringBuffer p0, final DeclarationParserContext p1);
    
    void parse(final ParserStringBuffer p0, final DeclarationParserContext p1);
}
