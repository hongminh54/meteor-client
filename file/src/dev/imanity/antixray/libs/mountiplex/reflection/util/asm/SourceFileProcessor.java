// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.asm;

import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.Set;

public class SourceFileProcessor
{
    private static final Set<String> IGNORED_NAMES;
    
    public Map<String, String> process(final File file) {
        final HashMap hashMap = new HashMap();
        final String s = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        char char1 = ' ';
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int b = 0;
        int min = Integer.MAX_VALUE;
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            final char c = char1;
            char1 = s.charAt(i);
            if (char1 != '\r') {
                if (n2 == 0) {
                    if (char1 == '\n') {
                        b = 0;
                    }
                    else if (char1 == ' ') {
                        ++b;
                    }
                    else if (char1 == '\t') {
                        b += 4;
                    }
                    else if (n != 0 && c != '\\' && char1 == '\"') {
                        n = 0;
                    }
                    else if (n == 0 && char1 == '\"') {
                        n = 1;
                    }
                    else if (n == 0 && c == '/' && char1 == '*') {
                        n2 = 1;
                        n3 = 0;
                        b += 2;
                        min = Integer.MAX_VALUE;
                    }
                }
                else {
                    if (n3 == 0) {
                        if (char1 == '\n') {
                            sb.append('\n');
                            b = 0;
                            continue;
                        }
                        if (char1 == ' ' || char1 == '*') {
                            ++b;
                            continue;
                        }
                        if (char1 == '\t') {
                            b += 4;
                            continue;
                        }
                        n3 = 1;
                        this.appendSpaces(sb, b);
                    }
                    else if (char1 == '\n') {
                        n3 = 0;
                        b = 0;
                        sb.append('\n');
                        continue;
                    }
                    if (c == '*' && char1 == '/') {
                        n2 = 0;
                        n3 = 0;
                        this.cleanupSpaces(sb, min);
                        this.processBlockComment(sb, hashMap);
                        sb.setLength();
                    }
                    else {
                        min = Math.min(min, b);
                        sb.append(char1);
                    }
                }
            }
        }
        return hashMap;
    }
    
    private void appendSpaces(final StringBuilder sb, int n) {
        while (--n >= 0) {
            sb.append(' ');
        }
    }
    
    private void cleanupPreceedingSpaces(final StringBuilder sb) {
        int i = 0;
        while (i < sb.length()) {
            final char char1 = sb.charAt();
            if (char1 == '\n') {
                sb.replace(0, i + 1, "");
                i = 0;
            }
            else {
                if (char1 != ' ') {
                    break;
                }
                ++i;
            }
        }
    }
    
    private void cleanupSpaces(final StringBuilder sb, final int n) {
        this.cleanupPreceedingSpaces(sb);
        for (int i = sb.length() - 1; i >= 0; --i) {
            final char char1 = sb.charAt();
            if (char1 != ' ' && char1 != '\n') {
                sb.replace(i + 1, sb.length(), "");
                break;
            }
        }
        int n2 = n;
        int n3 = 0;
        for (int j = 0; j < sb.length(); ++j) {
            final char char2 = sb.charAt();
            if (char2 == ' ' && n2 > 0) {
                --n2;
                ++n3;
            }
            else if (n3 > 0) {
                sb.replace(j - n3, j, "");
                n3 = 0;
            }
            if (char2 == '\n') {
                n2 = n;
            }
        }
    }
    
    public void processBlockComment(final StringBuilder sb, final Map<String, String> map) {
        if (sb.length() < 2 || sb.charAt() != '<') {
            return;
        }
        final int index = sb.indexOf(">", 1);
        if (index == -1) {
            return;
        }
        final int index2 = sb.indexOf("\n", 1);
        if (index2 == -1 || index2 < index) {
            return;
        }
        final String trim = sb.substring().trim();
        if (SourceFileProcessor.IGNORED_NAMES.contains(trim)) {
            return;
        }
        sb.replace(0, index + 1, "");
        this.cleanupPreceedingSpaces(sb);
        map.put(trim, sb.toString());
    }
    
    static {
        IGNORED_NAMES = new HashSet<String>(Arrays.asList("u", "i", "b", "p", "pre", "code"));
    }
}
