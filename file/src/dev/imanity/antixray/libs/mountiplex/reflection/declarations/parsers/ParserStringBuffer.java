// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers;

import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Declaration;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ClassResolver;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.reflection.util.StringBuffer;

public final class ParserStringBuffer
{
    public static final char[] WHITESPACE_CHARACTERS;
    public static final char[] INVALID_NAME_CHARACTERS;
    private StringBuffer _text;
    
    public ParserStringBuffer() {
        this._text = null;
    }
    
    public StringBuffer get() {
        return this._text;
    }
    
    public void set(final StringBuffer text) {
        this._text = text;
    }
    
    public boolean isNull() {
        return this._text == null;
    }
    
    @Override
    public String toString() {
        return this._text.toString();
    }
    
    public boolean startsWith(final String s) {
        return this._text.startsWith(s);
    }
    
    public void trimWhitespace(final int n) {
        if (this._text == null) {
            return;
        }
        for (int i = n; i < this._text.length(); ++i) {
            if (!MountiplexUtil.containsChar(this._text.charAt(i), ParserStringBuffer.WHITESPACE_CHARACTERS)) {
                this._text = this._text.substring(i);
                return;
            }
        }
        this._text = StringBuffer.EMPTY;
    }
    
    public String trimLine() {
        if (this.isNull()) {
            return "";
        }
        int n = -1;
        for (int i = 0; i < this._text.length(); ++i) {
            final char char1 = this._text.charAt(i);
            if (char1 == '\r' || char1 == '\n') {
                if (n == -1) {
                    n = i;
                }
            }
            else if (char1 != ' ' && n != -1) {
                final String substringToString = this._text.substringToString(0, n);
                this._text = this._text.substring(i);
                return substringToString;
            }
        }
        final String string = this._text.toString();
        this._text = StringBuffer.EMPTY;
        return string;
    }
    
    public void trimGenericTypes() {
        final StringBuffer value = this.get();
        if (value != null && value.length() > 0 && value.charAt(0) == '<') {
            boolean b = false;
            for (int i = 1; i < value.length(); ++i) {
                final char char1 = value.charAt(i);
                if (char1 == '>') {
                    b = true;
                }
                else if (b && !MountiplexUtil.containsChar(char1, ParserStringBuffer.INVALID_NAME_CHARACTERS)) {
                    this.set(value.substring(i));
                    break;
                }
            }
        }
    }
    
    public Declaration detectMemberDeclaration(final ClassResolver classResolver) {
        final Declaration declaration = Declaration.parseDeclaration(classResolver, this.get());
        if (declaration != null) {
            this.set(declaration.getPostfix());
            this.trimLine();
            return declaration;
        }
        return null;
    }
    
    static {
        WHITESPACE_CHARACTERS = new char[] { ' ', '\n', '\r' };
        INVALID_NAME_CHARACTERS = new char[] { ' ', '\n', '\r', '<', '>', ',', '(', ')', '{', '}', ';', '=' };
    }
}
