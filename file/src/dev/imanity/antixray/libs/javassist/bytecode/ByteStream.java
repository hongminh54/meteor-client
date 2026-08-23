// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.io.OutputStream;

final class ByteStream extends OutputStream
{
    private byte[] buf;
    private int count;
    
    public ByteStream() {
        this(32);
    }
    
    public ByteStream(final int n) {
        this.buf = new byte[n];
        this.count = 0;
    }
    
    public int getPos() {
        return this.count;
    }
    
    public int size() {
        return this.count;
    }
    
    public void writeBlank(final int n) {
        this.enlarge(n);
        this.count += n;
    }
    
    @Override
    public void write(final byte[] array) {
        this.write(array, 0, array.length);
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) {
        this.enlarge(n2);
        System.arraycopy(array, n, this.buf, this.count, n2);
        this.count += n2;
    }
    
    @Override
    public void write(final int n) {
        this.enlarge(1);
        final int count = this.count;
        this.buf[count] = (byte)n;
        this.count = count + 1;
    }
    
    public void writeShort(final int n) {
        this.enlarge(2);
        final int count = this.count;
        this.buf[count] = (byte)(n >>> 8);
        this.buf[count + 1] = (byte)n;
        this.count = count + 2;
    }
    
    public void writeInt(final int n) {
        this.enlarge(4);
        final int count = this.count;
        this.buf[count] = (byte)(n >>> 24);
        this.buf[count + 1] = (byte)(n >>> 16);
        this.buf[count + 2] = (byte)(n >>> 8);
        this.buf[count + 3] = (byte)n;
        this.count = count + 4;
    }
    
    public void writeLong(final long n) {
        this.enlarge(8);
        final int count = this.count;
        this.buf[count] = (byte)(n >>> 56);
        this.buf[count + 1] = (byte)(n >>> 48);
        this.buf[count + 2] = (byte)(n >>> 40);
        this.buf[count + 3] = (byte)(n >>> 32);
        this.buf[count + 4] = (byte)(n >>> 24);
        this.buf[count + 5] = (byte)(n >>> 16);
        this.buf[count + 6] = (byte)(n >>> 8);
        this.buf[count + 7] = (byte)n;
        this.count = count + 8;
    }
    
    public void writeFloat(final float value) {
        this.writeInt(Float.floatToIntBits(value));
    }
    
    public void writeDouble(final double value) {
        this.writeLong(Double.doubleToLongBits(value));
    }
    
    public void writeUTF(final String s) {
        final int length = s.length();
        int count = this.count;
        this.enlarge(length + 2);
        final byte[] buf = this.buf;
        buf[count++] = (byte)(length >>> 8);
        buf[count++] = (byte)length;
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if ('\u0001' > char1 || char1 > '\u007f') {
                this.writeUTF2(s, length, i);
                return;
            }
            buf[count++] = (byte)char1;
        }
        this.count = count;
    }
    
    private void writeUTF2(final String s, final int i, final int n) {
        int j = i;
        for (int k = n; k < i; ++k) {
            final char char1 = s.charAt(k);
            if (char1 > '\u07ff') {
                j += 2;
            }
            else if (char1 == '\0' || char1 > '\u007f') {
                ++j;
            }
        }
        if (j > 65535) {
            throw new RuntimeException("encoded string too long: " + i + j + " bytes");
        }
        this.enlarge(j + 2);
        final int count = this.count;
        final byte[] buf = this.buf;
        buf[count] = (byte)(j >>> 8);
        buf[count + 1] = (byte)j;
        int count2 = count + (2 + n);
        for (int l = n; l < i; ++l) {
            final char char2 = s.charAt(l);
            if ('\u0001' <= char2 && char2 <= '\u007f') {
                buf[count2++] = (byte)char2;
            }
            else if (char2 > '\u07ff') {
                buf[count2] = (byte)(0xE0 | (char2 >> 12 & 0xF));
                buf[count2 + 1] = (byte)(0x80 | (char2 >> 6 & 0x3F));
                buf[count2 + 2] = (byte)(0x80 | (char2 & '?'));
                count2 += 3;
            }
            else {
                buf[count2] = (byte)(0xC0 | (char2 >> 6 & 0x1F));
                buf[count2 + 1] = (byte)(0x80 | (char2 & '?'));
                count2 += 2;
            }
        }
        this.count = count2;
    }
    
    public void write(final int n, final int n2) {
        this.buf[n] = (byte)n2;
    }
    
    public void writeShort(final int n, final int n2) {
        this.buf[n] = (byte)(n2 >>> 8);
        this.buf[n + 1] = (byte)n2;
    }
    
    public void writeInt(final int n, final int n2) {
        this.buf[n] = (byte)(n2 >>> 24);
        this.buf[n + 1] = (byte)(n2 >>> 16);
        this.buf[n + 2] = (byte)(n2 >>> 8);
        this.buf[n + 3] = (byte)n2;
    }
    
    public byte[] toByteArray() {
        final byte[] array = new byte[this.count];
        System.arraycopy(this.buf, 0, array, 0, this.count);
        return array;
    }
    
    public void writeTo(final OutputStream outputStream) {
        outputStream.write(this.buf, 0, this.count);
    }
    
    public void enlarge(final int n) {
        final int n2 = this.count + n;
        if (n2 > this.buf.length) {
            final int n3 = this.buf.length << 1;
            final byte[] buf = new byte[(n3 > n2) ? n3 : n2];
            System.arraycopy(this.buf, 0, buf, 0, this.count);
            this.buf = buf;
        }
    }
}
