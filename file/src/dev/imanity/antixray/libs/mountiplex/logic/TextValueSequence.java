// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.logic;

import java.util.Comparator;

public final class TextValueSequence implements Comparable<TextValueSequence>
{
    public static final Comparator<String> STRING_COMPARATOR;
    private final String fullText;
    private boolean number;
    private int value;
    private String text;
    private TextValueSequence next;
    
    public static TextValueSequence parse(final String s) {
        return new TextValueSequence(s);
    }
    
    public static int compareText(final String s, final String s2) {
        return parse(s).compareTo(parse(s2));
    }
    
    public static boolean evaluateText(final String s, final String s2, final String s3) {
        return evaluate(parse(s), s2, parse(s3));
    }
    
    public static boolean evaluate(final TextValueSequence textValueSequence, final String s, final TextValueSequence textValueSequence2) {
        final int length = s.length();
        if (length == 0 || length > 2) {
            return false;
        }
        final char char1 = s.charAt(0);
        final char c = (length == 2) ? s.charAt(1) : ' ';
        final int compareTo = textValueSequence.compareTo(textValueSequence2);
        if (char1 == '>') {
            if (c == '=') {
                return compareTo >= 0;
            }
            return compareTo > 0;
        }
        else if (char1 == '<') {
            if (c == '=') {
                return compareTo <= 0;
            }
            return compareTo < 0;
        }
        else {
            if (char1 == '=' && c == '=') {
                return compareTo == 0;
            }
            return char1 == '!' && c == '=' && compareTo != 0;
        }
    }
    
    private TextValueSequence(final String fullText) {
        this.fullText = fullText;
        this.number = false;
        this.value = 0;
        int beginIndex;
        for (beginIndex = 0; beginIndex < fullText.length() && Character.isDigit(fullText.charAt(beginIndex)); ++beginIndex) {
            this.number = true;
        }
        if (this.number) {
            this.text = fullText.substring(0, beginIndex);
            try {
                this.value = Integer.parseInt(this.text);
            }
            catch (final NumberFormatException ex) {
                this.number = false;
            }
        }
        if (!this.number) {
            while (beginIndex < fullText.length() && !Character.isDigit(fullText.charAt(beginIndex))) {
                ++beginIndex;
            }
            this.text = fullText.substring(0, beginIndex);
        }
        if (beginIndex < fullText.length()) {
            this.next = new TextValueSequence(fullText.substring(beginIndex));
        }
        else {
            this.next = null;
        }
    }
    
    @Override
    public int hashCode() {
        return this.fullText.hashCode();
    }
    
    @Override
    public String toString() {
        return this.fullText;
    }
    
    @Override
    public int compareTo(final TextValueSequence textValueSequence) {
        int n;
        if (this.number && textValueSequence.number) {
            n = Integer.compare(this.value, textValueSequence.value);
        }
        else {
            n = this.text.compareTo(textValueSequence.text);
        }
        if (n == 0) {
            if (this.next != null && textValueSequence.next != null) {
                return this.next.compareTo(textValueSequence.next);
            }
            if (this.next != null) {
                return 1;
            }
            if (textValueSequence.next != null) {
                return -1;
            }
        }
        return n;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof TextValueSequence && this.fullText.equals(((TextValueSequence)o).fullText);
    }
    
    static {
        STRING_COMPARATOR = ((s, anObject) -> {
            if (s.equals(anObject)) {
                return 0;
            }
            else if (evaluateText(s, ">", anObject)) {
                return 1;
            }
            else {
                return -1;
            }
        });
    }
}
