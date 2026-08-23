// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import java.util.Locale;
import java.util.LinkedList;

public class SourcePreprocessor
{
    private final ClassResolver resolver;
    private StringBuilder result;
    private int disabledIfLevel;
    private boolean disabledIfExpression;
    private LinkedList<String> selectStack;
    private boolean isFirstSelectCase;
    
    public SourcePreprocessor(final ClassResolver resolver) {
        this.result = new StringBuilder();
        this.disabledIfLevel = 0;
        this.disabledIfExpression = false;
        this.selectStack = new LinkedList<String>();
        this.isFirstSelectCase = false;
        this.resolver = resolver;
    }
    
    public String preprocess(String string) {
        while (true) {
            final int lastIndex = string.lastIndexOf("/*");
            if (lastIndex == -1) {
                break;
            }
            final int index = string.indexOf("*/", lastIndex + 2);
            if (index == -1) {
                break;
            }
            string = string.substring(0, lastIndex) + string.substring(index + 2);
        }
        final String[] split = string.split("\\r?\\n");
        for (int length = split.length, i = 0; i < length; ++i) {
            this.preprocessLine(split[i]);
        }
        return this.result.toString();
    }
    
    public void preprocessLine(String str) {
        String s = str.trim();
        String s2 = s.toLowerCase(Locale.ENGLISH);
        int n = -1;
        if (s2.startsWith("#select ")) {
            final int index = str.indexOf(35);
            if (index != -1) {
                this.selectStack.offer(str.substring(index + 8));
                this.isFirstSelectCase = true;
            }
            return;
        }
        if (s2.startsWith("#endselect")) {
            this.selectStack.pollLast();
            if (this.isFirstSelectCase) {
                this.isFirstSelectCase = false;
                return;
            }
            final int index2 = str.indexOf(35);
            if (index2 == -1) {
                return;
            }
            str = str.substring(0, index2) + "#endif";
            s = str.trim();
            s2 = s.toLowerCase(Locale.ENGLISH);
        }
        else if (s2.startsWith("#case")) {
            final int index3 = str.indexOf(35);
            if (index3 == -1 || this.selectStack.isEmpty()) {
                return;
            }
            final String substring = str.substring(index3 + 5);
            String substring2 = null;
            String substring3 = substring;
            final int index4 = substring.indexOf(58);
            if (index4 != -1) {
                substring2 = substring.substring(index4 + 1);
                substring3 = substring.substring(0, index4);
                n = index3 + 5 + index4;
            }
            final boolean equalsIgnoreCase = substring3.trim().equalsIgnoreCase("else");
            final StringBuilder sb = new StringBuilder();
            sb.append(str.substring(0, index3));
            if (!this.isFirstSelectCase && equalsIgnoreCase) {
                sb.append("#else");
                if (substring2 != null) {
                    sb.append(':').append(substring2);
                }
            }
            else {
                sb.append(this.isFirstSelectCase ? "#if " : "#elseif ");
                sb.append(this.selectStack.peekLast()).append(substring);
            }
            str = sb.toString();
            s = str.trim();
            s2 = s.toLowerCase(Locale.ENGLISH);
            this.isFirstSelectCase = false;
        }
        if (s2.startsWith("#if") || s2.startsWith("#elseif") || s2.startsWith("#else")) {
            final int index5 = str.indexOf(58);
            if (index5 != -1) {
                this.preprocessLine(str.substring(0, index5));
                final StringBuilder sb2 = new StringBuilder();
                for (int n2 = (n != -1) ? n : index5, i = 0; i <= n2; ++i) {
                    sb2.append(' ');
                }
                sb2.append(str.substring(index5 + 1));
                this.preprocessLine(sb2.toString());
                return;
            }
        }
        if (this.disabledIfLevel > 1) {
            if (s2.startsWith("#if")) {
                ++this.disabledIfLevel;
            }
            else if (s2.startsWith("#endif")) {
                --this.disabledIfLevel;
            }
            return;
        }
        if (this.disabledIfLevel == 1) {
            if (s2.startsWith("#if")) {
                ++this.disabledIfLevel;
            }
            else if (s2.startsWith("#endif")) {
                --this.disabledIfLevel;
            }
            else if (s2.startsWith("#else")) {
                final int index6 = s.indexOf("if", 5);
                boolean evaluateExpression = true;
                if (index6 != -1) {
                    evaluateExpression = this.resolver.evaluateExpression(s.substring(index6 + 2).trim());
                }
                if (!this.disabledIfExpression && evaluateExpression) {
                    --this.disabledIfLevel;
                }
            }
            return;
        }
        this.disabledIfExpression = false;
        if (s2.startsWith("#if")) {
            if (!this.resolver.evaluateExpression(s.substring(3).trim())) {
                ++this.disabledIfLevel;
            }
            return;
        }
        if (s2.startsWith("#else")) {
            ++this.disabledIfLevel;
            this.disabledIfExpression = true;
            return;
        }
        if (s2.startsWith("#endif")) {
            return;
        }
        if (s2.startsWith("//")) {
            return;
        }
        this.result.append(str).append('\n');
        if (!s2.startsWith("#set ")) {
            return;
        }
        final String trim = s.substring(5).trim();
        final int index7 = trim.indexOf(32);
        if (index7 == -1) {
            return;
        }
        final String substring4 = trim.substring(0, index7);
        String s3;
        for (s3 = trim.substring(index7 + 1); s3.length() > 0 && s3.charAt(0) == ' '; s3 = s3.substring(1)) {}
        this.resolver.setVariable(substring4, s3);
    }
}
