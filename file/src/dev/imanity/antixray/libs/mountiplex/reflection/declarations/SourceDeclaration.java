// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import java.util.Collection;
import java.util.Arrays;
import java.util.LinkedList;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.context.SourceDeclarationParserContext;
import java.util.Map;
import java.util.Iterator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.List;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.DeclarationParserGroups;
import dev.imanity.antixray.libs.mountiplex.reflection.util.StringBuffer;
import java.io.File;

public class SourceDeclaration extends Declaration
{
    public final ClassDeclaration[] classes;
    
    private SourceDeclaration(final ClassResolver classResolver, final ClassLoader classLoader, final File file, final StringBuffer stringBuffer) {
        super(classResolver, preprocess(stringBuffer));
        final ParserContext parserContext = new ParserContext(classLoader, file);
        this.getParserPostfix().trimWhitespace(0);
        StringBuffer postfix;
        while ((postfix = this.getPostfix()) != null && postfix.length() > 0) {
            if (parserContext.runParsers(DeclarationParserGroups.SOURCE)) {
                continue;
            }
            final ClassDeclaration nextClass = this.nextClass();
            if (!nextClass.isValid()) {
                MountiplexUtil.LOGGER.warning("Invalid class declaration parsed:\n" + nextClass);
                MountiplexUtil.LOGGER.warning("Source: " + (Object)nextClass._initialDeclaration);
                this.setInvalid();
                this.classes = new ClassDeclaration[0];
                return;
            }
            parserContext.classes.add(nextClass);
        }
        this.classes = parserContext.getClasses();
    }
    
    public static StringBuffer preprocess(final StringBuffer stringBuffer) {
        return StringBuffer.of(preprocess(stringBuffer.toString()));
    }
    
    public static String preprocess(final String s) {
        return preprocess(s, new ClassResolver());
    }
    
    public static String preprocess(final String s, final ClassResolver classResolver) {
        return new SourcePreprocessor(classResolver).preprocess(s);
    }
    
    public static String trimIndentation(final String s) {
        final String[] split = s.split("\\r?\\n", -1);
        int beginIndex = 20;
        for (final String s2 : split) {
            int j = 0;
            while (j < s2.length()) {
                if (s2.charAt(j) != ' ') {
                    if (j < beginIndex) {
                        beginIndex = j;
                        break;
                    }
                    break;
                }
                else {
                    ++j;
                }
            }
        }
        final StringBuilder sb = new StringBuilder(s.length());
        for (String substring : split) {
            if (substring.length() >= beginIndex) {
                substring = substring.substring(beginIndex);
            }
            sb.append(substring).append('\n');
        }
        return sb.toString();
    }
    
    @Override
    public boolean isResolved() {
        return false;
    }
    
    @Override
    public boolean match(final Declaration declaration) {
        return false;
    }
    
    @Override
    public String toString(final boolean b) {
        final String package1 = this.getResolver().getPackage();
        String str = "";
        if (package1.length() > 0) {
            str = str + "package " + package1 + ";\n\n";
        }
        final Iterator<String> iterator = this.getResolver().getAllImports().collect((Collector<? super String, ?, List<? super String>>)Collectors.toList()).iterator();
        while (iterator.hasNext()) {
            str = str + "import " + iterator.next() + ";\n";
        }
        String str2 = str + "\n";
        final ClassDeclaration[] classes = this.classes;
        for (int length = classes.length, i = 0; i < length; ++i) {
            str2 = str2 + classes[i].toString(b) + "\n\n";
        }
        return str2;
    }
    
    @Override
    protected void debugString(final StringBuilder sb, final String s) {
    }
    
    @Override
    public double similarity(final Declaration declaration) {
        return 0.0;
    }
    
    public static SourceDeclaration parse(final ClassResolver classResolver, final String s) {
        return new SourceDeclaration(classResolver, null, null, StringBuffer.of(s));
    }
    
    public static SourceDeclaration parse(final String s) {
        return new SourceDeclaration(new ClassResolver(), null, null, StringBuffer.of(s));
    }
    
    private static String saveVars(final Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        for (final Map.Entry entry : map.entrySet()) {
            sb.append("#set ").append((String)entry.getKey()).append(' ').append((String)entry.getValue()).append('\n');
        }
        return sb.toString();
    }
    
    public static SourceDeclaration parseFromResources(final ClassLoader classLoader, final String str, final Map<String, String> map) {
        return new SourceDeclaration(new ClassResolver(), classLoader, null, StringBuffer.of(saveVars(map) + "\n#include " + str));
    }
    
    public static SourceDeclaration loadFromDisk(final File file, final String str, final Map<String, String> map, final boolean generating) {
        final ClassResolver classResolver = new ClassResolver();
        classResolver.setGenerating(generating);
        return new SourceDeclaration(classResolver, null, file, StringBuffer.of(saveVars(map) + "\n#include " + str));
    }
    
    public static SourceDeclaration loadFromDisk(final File file, final String str, final Map<String, String> map) {
        return new SourceDeclaration(new ClassResolver(), null, file, StringBuffer.of(saveVars(map) + "\n#include " + str));
    }
    
    public static SourceDeclaration parseFromResources(final ClassLoader classLoader, final String str) {
        return new SourceDeclaration(new ClassResolver(), classLoader, null, StringBuffer.of("#include " + str));
    }
    
    public static SourceDeclaration loadFromDisk(final File file, final String str) {
        return new SourceDeclaration(new ClassResolver(), null, file, StringBuffer.of("#include " + str));
    }
    
    private class ParserContext extends BaseDeclarationParserContext implements SourceDeclarationParserContext
    {
        private ClassLoader classLoader;
        private final File currentDirectory;
        private String templateFile;
        private LinkedList<ClassDeclaration> classes;
        
        public ParserContext(final ClassLoader classLoader, final File currentDirectory) {
            this.templateFile = "";
            this.classes = new LinkedList<ClassDeclaration>();
            this.classLoader = classLoader;
            this.currentDirectory = currentDirectory;
        }
        
        public ClassDeclaration[] getClasses() {
            return this.classes.toArray(new ClassDeclaration[this.classes.size()]);
        }
        
        @Override
        public ClassLoader getClassLoader() {
            if (this.classLoader == null) {
                this.classLoader = SourceDeclaration.class.getClassLoader();
            }
            return this.classLoader;
        }
        
        @Override
        public File getCurrentDirectory() {
            return this.currentDirectory;
        }
        
        @Override
        public void includeSource(final StringBuffer stringBuffer) {
            final SourceDeclaration sourceDeclaration = new SourceDeclaration(this.getResolver().clone(), this.classLoader, this.currentDirectory, stringBuffer, null);
            this.getResolver().includeSourceDetails(sourceDeclaration.getResolver());
            this.classes.addAll(Arrays.asList(sourceDeclaration.classes));
        }
        
        @Override
        public void setCurrentTemplateFile(final String templateFile) {
            this.templateFile = templateFile;
        }
        
        @Override
        public String getCurrentTemplateFile() {
            return this.templateFile;
        }
    }
}
