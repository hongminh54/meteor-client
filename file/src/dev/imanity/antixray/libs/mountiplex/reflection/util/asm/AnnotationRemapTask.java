// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.asm;

import java.nio.file.StandardOpenOption;
import java.nio.file.OpenOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.Files;
import java.io.File;
import java.util.Map;
import java.nio.file.Path;

public class AnnotationRemapTask
{
    public final Path inputFile;
    public final Path outputFile;
    public final Map<String, String> variables;
    
    public AnnotationRemapTask(final File file, final File file2, final Map<String, String> variables) {
        this.inputFile = file.toPath();
        this.outputFile = file2.toPath();
        this.variables = variables;
    }
    
    public void remap() {
        try {
            final byte[] replace = AnnotationReplacer.replace(Files.readAllBytes(this.inputFile), (p0, defaultValue) -> {
                if (defaultValue.length() > 2 && defaultValue.startsWith("%") && defaultValue.endsWith("%")) {
                    return (String)this.variables.getOrDefault(defaultValue.substring(1, defaultValue.length() - 1), defaultValue);
                }
                else {
                    return defaultValue;
                }
            });
            Files.createDirectories(this.outputFile.getParent(), (FileAttribute<?>[])new FileAttribute[0]);
            Files.write(this.outputFile, replace, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        }
        catch (final Throwable t) {
            t.printStackTrace();
        }
    }
}
