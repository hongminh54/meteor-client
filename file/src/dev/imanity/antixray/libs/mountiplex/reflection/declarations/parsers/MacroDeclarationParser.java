// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers;

import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ClassResolver;
import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.reflection.util.StringBuffer;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.context.DeclarationParserContext;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Declaration;

abstract class MacroDeclarationParser extends MacroParser
{
    private final String typeName;
    
    public MacroDeclarationParser(final String s, final String typeName) {
        super(s);
        this.typeName = typeName;
    }
    
    public abstract void runMacro(final Declaration p0, final DeclarationParserContext p1);
    
    @Override
    public void parse(final ParserStringBuffer parserStringBuffer, final DeclarationParserContext declarationParserContext) {
        super.parse(parserStringBuffer, declarationParserContext);
        final int index = parserStringBuffer.get().indexOf(' ');
        if (index == -1) {
            parserStringBuffer.set(StringBuffer.EMPTY);
            return;
        }
        final String substringToString = parserStringBuffer.get().substringToString(0, index);
        parserStringBuffer.trimWhitespace(index);
        final ClassResolver clone = declarationParserContext.getResolver().clone();
        clone.setDeclaredClassName(substringToString);
        final Declaration detectMemberDeclaration = parserStringBuffer.detectMemberDeclaration(clone);
        if (detectMemberDeclaration == null) {
            final String trimLine = parserStringBuffer.trimLine();
            if (declarationParserContext.getResolver().getLogErrors()) {
                MountiplexUtil.LOGGER.log(Level.SEVERE, "Declaration invalid for " + this.typeName + ": " + substringToString);
                MountiplexUtil.LOGGER.log(Level.SEVERE, "Declaration: " + trimLine);
            }
            return;
        }
        if (declarationParserContext.getResolver().isGenerating()) {
            return;
        }
        this.runMacro(detectMemberDeclaration, declarationParserContext);
    }
}
