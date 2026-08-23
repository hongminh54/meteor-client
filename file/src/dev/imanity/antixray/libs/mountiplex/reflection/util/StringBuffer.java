// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util;

public final class StringBuffer implements CharSequence
{
    private final char[] buffer;
    private final int buffer_start;
    private final int buffer_length;
    private int hash;
    public static final StringBuffer EMPTY;
    
    private StringBuffer() {
        this.hash = 0;
        this.buffer = new char[0];
        this.buffer_start = 0;
        this.buffer_length = 0;
    }
    
    public StringBuffer(final char[] buffer) {
        this.hash = 0;
        this.buffer = buffer;
        this.buffer_start = 0;
        this.buffer_length = buffer.length;
    }
    
    public StringBuffer(final char[] buffer, final int buffer_start, final int buffer_length) {
        this.hash = 0;
        this.buffer = buffer;
        this.buffer_start = buffer_start;
        this.buffer_length = buffer_length;
    }
    
    public StringBuffer(final StringBuffer stringBuffer, final int n, final int n2) {
        this.hash = 0;
        if (n < 0) {
            throw new IndexOutOfBoundsException("Index " + n + " is out of bounds");
        }
        if (n + n2 > stringBuffer.buffer_length) {
            throw new IndexOutOfBoundsException("Substring " + n + " length " + n2 + " is out of bounds");
        }
        this.buffer = stringBuffer.buffer;
        this.buffer_start = stringBuffer.buffer_start + n;
        this.buffer_length = n2;
    }
    
    public StringBuffer(final String s) {
        this.hash = 0;
        this.buffer = s.toCharArray();
        this.buffer_start = 0;
        this.buffer_length = this.buffer.length;
    }
    
    @Override
    public int length() {
        return this.buffer_length;
    }
    
    @Override
    public char charAt(final int i) {
        if (i < 0 || i >= this.buffer_length) {
            throw new IndexOutOfBoundsException("Index " + i + " out of range of [0 ... " + (this.buffer_length - 1) + "]");
        }
        return this.buffer[this.buffer_start + i];
    }
    
    public String substringToString(final int n, final int n2) {
        return String.valueOf(this.buffer, this.buffer_start + n, n2 - n);
    }
    
    public String substringToString(final int n) {
        return this.substringToString(n, this.buffer_length);
    }
    
    public StringBuffer substring(final int n, final int n2) {
        return new StringBuffer(this, n, n2 - n);
    }
    
    public StringBuffer substring(final int n) {
        return new StringBuffer(this, n, this.buffer_length - n);
    }
    
    public boolean substringEquals(final int n, final int n2, final String anObject) {
        return n >= 0 && n2 - n <= this.length() && this.substringToString(n, n2).equals(anObject);
    }
    
    public StringBuffer prepend(final String s) {
        final int length = s.length();
        if (length == 0) {
            return this;
        }
        if (this.buffer_length == 0) {
            return of(s);
        }
        final char[] dst = new char[this.buffer_length + length];
        s.getChars(0, s.length(), dst, 0);
        System.arraycopy(this.buffer, this.buffer_start, dst, length, this.buffer_length);
        return new StringBuffer(dst, 0, dst.length);
    }
    
    public StringBuffer append(final String s) {
        final int length = s.length();
        if (length == 0) {
            return this;
        }
        if (this.buffer_length == 0) {
            return of(s);
        }
        final char[] dst = new char[this.buffer_length + length];
        System.arraycopy(this.buffer, this.buffer_start, dst, 0, this.buffer_length);
        s.getChars(0, s.length(), dst, this.buffer_length);
        return new StringBuffer(dst, 0, dst.length);
    }
    
    public StringBuffer append(final StringBuffer stringBuffer) {
        return join(this, stringBuffer);
    }
    
    public StringBuffer prepend(final StringBuffer stringBuffer) {
        return join(stringBuffer, this);
    }
    
    @Override
    public StringBuffer subSequence(final int n, final int n2) {
        return this.subSequence(n, n2);
    }
    
    public int indexOf(final char c) {
        return this.indexOf(c, 0);
    }
    
    public int indexOf(final char c, final int n) {
        int n2 = this.buffer_start + n;
        for (int i = n; i < this.buffer_length; ++i) {
            if (this.buffer[n2++] == c) {
                return i;
            }
        }
        return -1;
    }
    
    public int indexOf(final String s) {
        return this.indexOf(s, 0);
    }
    
    public int indexOf(final String s, int n) {
        if (s.isEmpty()) {
            throw new IllegalArgumentException("Token string is empty");
        }
        for (int length = s.length(), i = this.buffer_start + n; i < this.buffer_start + this.buffer_length - length; ++i, ++n) {
            int n2 = i;
            int n3 = 0;
            while (this.buffer[n2++] == s.charAt(n3++)) {
                if (n3 == length) {
                    return n;
                }
            }
        }
        return -1;
    }
    
    public boolean startsWith(final String s) {
        final int length = s.length();
        if (length > this.buffer_length) {
            return false;
        }
        int buffer_start = this.buffer_start;
        for (int i = 0; i < length; ++i) {
            if (this.buffer[buffer_start++] != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean endsWith(final String s) {
        final int length = s.length();
        if (length > this.buffer_length) {
            return false;
        }
        int n = this.buffer_start + this.buffer_length;
        for (int i = length - 1; i >= 0; --i) {
            if (this.buffer[--n] != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean equals(final String s) {
        if (this.buffer_length != s.length()) {
            return false;
        }
        int buffer_start = this.buffer_start;
        for (int i = 0; i < this.buffer_length; ++i) {
            if (this.buffer[buffer_start++] != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean equals(final StringBuffer stringBuffer) {
        if (this.buffer_length != stringBuffer.buffer_length) {
            return false;
        }
        int buffer_start = this.buffer_start;
        int buffer_start2 = stringBuffer.buffer_start;
        for (int i = 0; i < this.buffer_length; ++i) {
            if (this.buffer[buffer_start++] != stringBuffer.buffer[buffer_start2++]) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.buffer, this.buffer_start, this.buffer_length);
    }
    
    @Override
    public int hashCode() {
        int hash = this.hash;
        if (hash == 0 && this.buffer_length > 0) {
            final char[] buffer = this.buffer;
            int buffer_start = this.buffer_start;
            for (int i = 0; i < this.buffer_length; ++i) {
                hash = 31 * hash + buffer[buffer_start++];
            }
            this.hash = hash;
        }
        return hash;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof StringBuffer && this.equals((StringBuffer)o));
    }
    
    public static StringBuffer of(final String s) {
        if (s.isEmpty()) {
            return StringBuffer.EMPTY;
        }
        return new StringBuffer(s);
    }
    
    public static StringBuffer of(final StringBuilder sb) {
        final int length = sb.length();
        if (length == 0) {
            return StringBuffer.EMPTY;
        }
        final char[] array = new char[length];
        sb.getChars();
        return new StringBuffer(array);
    }
    
    public static StringBuffer join(final StringBuffer stringBuffer, final StringBuffer stringBuffer2) {
        if (stringBuffer.buffer_length == 0) {
            return stringBuffer2;
        }
        if (stringBuffer2.buffer_length == 0) {
            return stringBuffer;
        }
        final char[] array = new char[stringBuffer.buffer_length + stringBuffer2.buffer_length];
        System.arraycopy(stringBuffer.buffer, stringBuffer.buffer_start, array, 0, stringBuffer.buffer_length);
        System.arraycopy(stringBuffer2.buffer, stringBuffer2.buffer_start, array, stringBuffer.buffer_length, stringBuffer2.buffer_length);
        return new StringBuffer(array);
    }
    
    static {
        EMPTY = new StringBuffer();
    }
}
