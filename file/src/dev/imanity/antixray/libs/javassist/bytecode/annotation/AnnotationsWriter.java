// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode.annotation;

import dev.imanity.antixray.libs.javassist.bytecode.ByteArray;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;
import java.io.OutputStream;

public class AnnotationsWriter
{
    protected OutputStream output;
    private ConstPool pool;
    
    public AnnotationsWriter(final OutputStream output, final ConstPool pool) {
        this.output = output;
        this.pool = pool;
    }
    
    public ConstPool getConstPool() {
        return this.pool;
    }
    
    public void close() {
        this.output.close();
    }
    
    public void numParameters(final int n) {
        this.output.write(n);
    }
    
    public void numAnnotations(final int n) {
        this.write16bit(n);
    }
    
    public void annotation(final String s, final int n) {
        this.annotation(this.pool.addUtf8Info(s), n);
    }
    
    public void annotation(final int n, final int n2) {
        this.write16bit(n);
        this.write16bit(n2);
    }
    
    public void memberValuePair(final String s) {
        this.memberValuePair(this.pool.addUtf8Info(s));
    }
    
    public void memberValuePair(final int n) {
        this.write16bit(n);
    }
    
    public void constValueIndex(final boolean b) {
        this.constValueIndex(90, this.pool.addIntegerInfo((int)(b ? 1 : 0)));
    }
    
    public void constValueIndex(final byte b) {
        this.constValueIndex(66, this.pool.addIntegerInfo(b));
    }
    
    public void constValueIndex(final char c) {
        this.constValueIndex(67, this.pool.addIntegerInfo(c));
    }
    
    public void constValueIndex(final short n) {
        this.constValueIndex(83, this.pool.addIntegerInfo(n));
    }
    
    public void constValueIndex(final int n) {
        this.constValueIndex(73, this.pool.addIntegerInfo(n));
    }
    
    public void constValueIndex(final long n) {
        this.constValueIndex(74, this.pool.addLongInfo(n));
    }
    
    public void constValueIndex(final float n) {
        this.constValueIndex(70, this.pool.addFloatInfo(n));
    }
    
    public void constValueIndex(final double n) {
        this.constValueIndex(68, this.pool.addDoubleInfo(n));
    }
    
    public void constValueIndex(final String s) {
        this.constValueIndex(115, this.pool.addUtf8Info(s));
    }
    
    public void constValueIndex(final int n, final int n2) {
        this.output.write(n);
        this.write16bit(n2);
    }
    
    public void enumConstValue(final String s, final String s2) {
        this.enumConstValue(this.pool.addUtf8Info(s), this.pool.addUtf8Info(s2));
    }
    
    public void enumConstValue(final int n, final int n2) {
        this.output.write(101);
        this.write16bit(n);
        this.write16bit(n2);
    }
    
    public void classInfoIndex(final String s) {
        this.classInfoIndex(this.pool.addUtf8Info(s));
    }
    
    public void classInfoIndex(final int n) {
        this.output.write(99);
        this.write16bit(n);
    }
    
    public void annotationValue() {
        this.output.write(64);
    }
    
    public void arrayValue(final int n) {
        this.output.write(91);
        this.write16bit(n);
    }
    
    protected void write16bit(final int n) {
        final byte[] b = new byte[2];
        ByteArray.write16bit(n, b, 0);
        this.output.write(b);
    }
}
