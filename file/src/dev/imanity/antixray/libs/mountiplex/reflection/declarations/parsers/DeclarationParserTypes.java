// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers;

import dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.context.ClassDeclarationParserContext;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.context.SourceDeclarationParserContext;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Remapping;
import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Requirement;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.FieldDeclaration;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.MethodDeclaration;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Declaration;
import dev.imanity.antixray.libs.mountiplex.reflection.util.StringBuffer;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.context.DeclarationParserContext;

public final class DeclarationParserTypes
{
    public static final DeclarationParser COMMENT;
    public static final DeclarationParser BOOTSTRAP;
    public static final DeclarationParser RESOLVER;
    public static final DeclarationParser REQUIREMENT;
    public static final DeclarationParser REMAPPING;
    public static final DeclarationParser WARNING;
    public static final DeclarationParser ERROR;
    public static final DeclarationParser PACKAGE;
    public static final DeclarationParser IMPORT;
    public static final DeclarationParser INCLUDE;
    public static final DeclarationParser SET_PATH;
    public static final DeclarationParser SET_VARIABLE;
    public static final DeclarationParser CODE_BLOCK;
    
    static {
        COMMENT = new DeclarationParser() {
            @Override
            public boolean detect(final ParserStringBuffer parserStringBuffer, final DeclarationParserContext declarationParserContext) {
                return parserStringBuffer.startsWith("//");
            }
            
            @Override
            public void parse(final ParserStringBuffer parserStringBuffer, final DeclarationParserContext declarationParserContext) {
                parserStringBuffer.trimLine();
            }
        };
        BOOTSTRAP = new MacroParser() {
            @Override
            public void parse(final ParserStringBuffer parserStringBuffer, final DeclarationParserContext declarationParserContext) {
                super.parse(parserStringBuffer, declarationParserContext);
                final StringBuffer value = parserStringBuffer.get();
                int index;
                if (value.startsWith("{")) {
                    index = -1;
                    int n = 0;
                    for (int i = 1; i < value.length(); ++i) {
                        final char char1 = value.charAt(i);
                        if (char1 == '{') {
                            ++n;
                        }
                        else if (char1 == '}' && n-- <= 0) {
                            index = i + 1;
                            break;
                        }
                    }
                }
                else {
                    index = value.indexOf('\n');
                }
                if (index == -1) {
                    parserStringBuffer.set(StringBuffer.EMPTY);
                }
                else {
                    declarationParserContext.getResolver().addBootstrap(value.substringToString(0, index));
                    parserStringBuffer.trimWhitespace(index);
                }
            }
        };
        RESOLVER = new MacroParser() {
            @Override
            public void parse(final ParserStringBuffer parserStringBuffer, final DeclarationParserContext declarationParserContext) {
                super.parse(parserStringBuffer, declarationParserContext);
                declarationParserContext.getResolver().setClassDeclarationResolverName(parserStringBuffer.trimLine());
            }
        };
        REQUIREMENT = new MacroDeclarationParser("requirement") {
            @Override
            public void runMacro(final Declaration declaration, final DeclarationParserContext declarationParserContext) {
                String s = "unknown";
                if (declaration instanceof MethodDeclaration) {
                    s = ((MethodDeclaration)declaration).name.real();
                }
                else if (declaration instanceof FieldDeclaration) {
                    s = ((FieldDeclaration)declaration).name.real();
                }
                declarationParserContext.getResolver().storeRequirement(new Requirement(s, declaration));
            }
        };
        REMAPPING = new MacroDeclarationParser("remapping") {
            @Override
            public void runMacro(final Declaration declaration, final DeclarationParserContext declarationParserContext) {
                final Declaration discover = declaration.discover();
                if (discover == null) {
                    if (declarationParserContext.getResolver().getLogErrors()) {
                        MountiplexUtil.LOGGER.log(Level.WARNING, "Remapping declaration not found!");
                        declaration.discoverAlternatives();
                    }
                    return;
                }
                if (discover instanceof MethodDeclaration) {
                    final MethodDeclaration methodDeclaration = (MethodDeclaration)discover;
                    if (methodDeclaration.body != null && methodDeclaration.method == null) {
                        MountiplexUtil.LOGGER.log(Level.WARNING, "Method bodies for remapped methods are not supported");
                        MountiplexUtil.LOGGER.log(Level.WARNING, "Method: " + discover.toString());
                        return;
                    }
                    declarationParserContext.getResolver().storeRemapping(new Remapping.MethodRemapping(methodDeclaration));
                }
                else if (discover instanceof FieldDeclaration) {
                    declarationParserContext.getResolver().storeRemapping(new Remapping.FieldRemapping((FieldDeclaration)discover));
                }
            }
        };
        WARNING = new MacroParser() {
            @Override
            public void parse(final ParserStringBuffer parserStringBuffer, final DeclarationParserContext declarationParserContext) {
                super.parse(parserStringBuffer, declarationParserContext);
                declarationParserContext.addWarning(parserStringBuffer.trimLine());
            }
        };
        ERROR = new MacroParser() {
            @Override
            public void parse(final ParserStringBuffer parserStringBuffer, final DeclarationParserContext declarationParserContext) {
                super.parse(parserStringBuffer, declarationParserContext);
                declarationParserContext.addError(parserStringBuffer.trimLine());
            }
        };
        PACKAGE = new MacroPathParser() {
            @Override
            public void runMacro(final String package1, final DeclarationParserContext declarationParserContext) {
                declarationParserContext.getResolver().setPackage(package1);
            }
        };
        IMPORT = new MacroPathParser() {
            @Override
            public void runMacro(final String s, final DeclarationParserContext declarationParserContext) {
                declarationParserContext.getResolver().addImport(s);
            }
        };
        INCLUDE = new MacroPathParser() {
            private InputStream openResource(final SourceDeclarationParserContext sourceDeclarationParserContext, final String name) {
                if (sourceDeclarationParserContext.getCurrentDirectory() == null) {
                    return sourceDeclarationParserContext.getClassLoader().getResourceAsStream(name);
                }
                try {
                    return new FileInputStream(sourceDeclarationParserContext.getCurrentDirectory().getAbsolutePath() + File.separator + name.replace("/", File.separator));
                }
                catch (final FileNotFoundException ex) {
                    return null;
                }
            }
            
            @Override
            public void runMacro(String str, final DeclarationParserContext declarationParserContext) {
                final SourceDeclarationParserContext sourceDeclarationParserContext = (SourceDeclarationParserContext)declarationParserContext;
                final String currentTemplateFile = sourceDeclarationParserContext.getCurrentTemplateFile();
                if (str.startsWith(".") || str.startsWith("/")) {
                    final int lastIndex = currentTemplateFile.lastIndexOf(47);
                    if (lastIndex != -1) {
                        str = currentTemplateFile.substring(0, lastIndex) + "/" + str;
                    }
                    else {
                        str = currentTemplateFile + "/" + str;
                    }
                    int index;
                    while ((index = str.indexOf("/../")) != -1) {
                        final int lastIndex2 = str.lastIndexOf(47, index - 1);
                        if (lastIndex2 == -1) {
                            str = str.substring(index + 4);
                        }
                        else {
                            str = str.substring(0, lastIndex2) + "/" + str.substring(index + 4);
                        }
                    }
                    str = str.replace("/./", "/").replace("//", "/");
                }
                String string;
                try (final InputStream openResource = this.openResource(sourceDeclarationParserContext, str)) {
                    if (openResource == null) {
                        MountiplexUtil.LOGGER.warning("Could not resolve include while parsing template: " + str);
                        MountiplexUtil.LOGGER.warning("Template file: " + currentTemplateFile);
                        if (openResource != null) {
                            openResource.close();
                        }
                        return;
                    }
                    try (final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                        final byte[] array = new byte[1024];
                        int read;
                        while ((read = openResource.read(array)) != -1) {
                            byteArrayOutputStream.write(array, 0, read);
                        }
                        string = byteArrayOutputStream.toString("UTF-8");
                    }
                }
                catch (final Throwable thrown) {
                    MountiplexUtil.LOGGER.log(Level.WARNING, "Failed to load template " + str, thrown);
                    return;
                }
                final StringBuilder sb = new StringBuilder();
                sb.append(sourceDeclarationParserContext.getResolver().saveDeclaration()).append("\n");
                sb.append("#setpath ").append(str).append("\n");
                sb.append(string);
                sourceDeclarationParserContext.includeSource(StringBuffer.of(sb));
            }
        };
        SET_PATH = new MacroPathParser() {
            @Override
            public void runMacro(final String currentTemplateFile, final DeclarationParserContext declarationParserContext) {
                ((SourceDeclarationParserContext)declarationParserContext).setCurrentTemplateFile(currentTemplateFile);
            }
        };
        SET_VARIABLE = new MacroParser() {
            @Override
            public void parse(final ParserStringBuffer parserStringBuffer, final DeclarationParserContext declarationParserContext) {
                super.parse(parserStringBuffer, declarationParserContext);
                final int index = parserStringBuffer.get().indexOf(' ');
                if (index == -1) {
                    parserStringBuffer.set(StringBuffer.EMPTY);
                    return;
                }
                final String substringToString = parserStringBuffer.get().substringToString(0, index);
                String substringToString2 = "";
                parserStringBuffer.trimWhitespace(index + 1);
                final StringBuffer value = parserStringBuffer.get();
                for (int i = 0; i < value.length(); ++i) {
                    if (MountiplexUtil.containsChar(value.charAt(i), ParserStringBuffer.INVALID_NAME_CHARACTERS)) {
                        substringToString2 = value.substringToString(0, i);
                        break;
                    }
                }
                parserStringBuffer.trimLine();
                declarationParserContext.getResolver().setVariable(substringToString, substringToString2);
            }
        };
        CODE_BLOCK = new DeclarationParser() {
            @Override
            public boolean detect(final ParserStringBuffer parserStringBuffer, final DeclarationParserContext declarationParserContext) {
                return parserStringBuffer.startsWith("<code>");
            }
            
            @Override
            public void parse(final ParserStringBuffer parserStringBuffer, final DeclarationParserContext declarationParserContext) {
                final ClassDeclarationParserContext classDeclarationParserContext = (ClassDeclarationParserContext)declarationParserContext;
                final int index = parserStringBuffer.get().indexOf("</code>");
                if (index == -1) {
                    parserStringBuffer.trimWhitespace(6);
                }
                else {
                    String s = parserStringBuffer.get().substringToString(7, index);
                    int fromIndex = 0;
                    int index2;
                    while ((index2 = s.indexOf("import ", fromIndex)) != -1) {
                        final int index3 = s.indexOf(59, index2 + 7);
                        if (index3 == 1) {
                            break;
                        }
                        final int index4 = s.indexOf(10, index2 + 7);
                        if (index4 != -1 && index4 < index3) {
                            fromIndex = index4;
                        }
                        else {
                            int endIndex = index2;
                            boolean b = true;
                            for (int i = index2 - 1; i >= 0; --i) {
                                final char char1 = s.charAt(i);
                                if (char1 == '\n') {
                                    break;
                                }
                                if (char1 != ' ') {
                                    b = false;
                                    break;
                                }
                                endIndex = i;
                            }
                            if (!b) {
                                fromIndex = index3 + 1;
                            }
                            else {
                                classDeclarationParserContext.addCodeImport(s.substring(index2 + 7, index3).trim());
                                int j = index3 + 1;
                                while (j < s.length()) {
                                    final char char2 = s.charAt(j);
                                    if (char2 == ' ') {
                                        ++j;
                                    }
                                    else {
                                        if (char2 == '\n') {
                                            ++j;
                                            break;
                                        }
                                        break;
                                    }
                                }
                                s = s.substring(0, endIndex) + s.substring(j);
                                fromIndex = endIndex;
                            }
                        }
                    }
                    classDeclarationParserContext.appendCode(s);
                    parserStringBuffer.set(parserStringBuffer.get().substring(index + 7));
                    parserStringBuffer.trimLine();
                }
            }
        };
    }
}
