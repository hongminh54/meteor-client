// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.context;

import dev.imanity.antixray.libs.mountiplex.reflection.util.StringBuffer;
import java.io.File;

public interface SourceDeclarationParserContext extends DeclarationParserContext
{
    ClassLoader getClassLoader();
    
    File getCurrentDirectory();
    
    void includeSource(final StringBuffer p0);
    
    void setCurrentTemplateFile(final String p0);
    
    String getCurrentTemplateFile();
}
