// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.context;

import dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.DeclarationParser;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.ParserStringBuffer;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ClassResolver;

public interface DeclarationParserContext
{
    ClassResolver getResolver();
    
    ParserStringBuffer getBuffer();
    
    void addWarning(final String p0);
    
    void addError(final String p0);
    
    default boolean runParsers(final DeclarationParser[] array) {
        final ParserStringBuffer buffer = this.getBuffer();
        if (buffer.isNull()) {
            return false;
        }
        for (final DeclarationParser declarationParser : array) {
            if (declarationParser.detect(buffer, this)) {
                declarationParser.parse(buffer, this);
                return true;
            }
        }
        return false;
    }
}
