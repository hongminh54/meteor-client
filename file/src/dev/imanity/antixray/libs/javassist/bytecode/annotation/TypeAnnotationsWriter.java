// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode.annotation;

import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;
import java.io.OutputStream;

public class TypeAnnotationsWriter extends AnnotationsWriter
{
    public TypeAnnotationsWriter(final OutputStream outputStream, final ConstPool constPool) {
        super(outputStream, constPool);
    }
    
    @Override
    public void numAnnotations(final int n) {
        super.numAnnotations(n);
    }
    
    public void typeParameterTarget(final int n, final int n2) {
        this.output.write(n);
        this.output.write(n2);
    }
    
    public void supertypeTarget(final int n) {
        this.output.write(16);
        this.write16bit(n);
    }
    
    public void typeParameterBoundTarget(final int n, final int n2, final int n3) {
        this.output.write(n);
        this.output.write(n2);
        this.output.write(n3);
    }
    
    public void emptyTarget(final int n) {
        this.output.write(n);
    }
    
    public void formalParameterTarget(final int n) {
        this.output.write(22);
        this.output.write(n);
    }
    
    public void throwsTarget(final int n) {
        this.output.write(23);
        this.write16bit(n);
    }
    
    public void localVarTarget(final int n, final int n2) {
        this.output.write(n);
        this.write16bit(n2);
    }
    
    public void localVarTargetTable(final int n, final int n2, final int n3) {
        this.write16bit(n);
        this.write16bit(n2);
        this.write16bit(n3);
    }
    
    public void catchTarget(final int n) {
        this.output.write(66);
        this.write16bit(n);
    }
    
    public void offsetTarget(final int n, final int n2) {
        this.output.write(n);
        this.write16bit(n2);
    }
    
    public void typeArgumentTarget(final int n, final int n2, final int n3) {
        this.output.write(n);
        this.write16bit(n2);
        this.output.write(n3);
    }
    
    public void typePath(final int n) {
        this.output.write(n);
    }
    
    public void typePathPath(final int n, final int n2) {
        this.output.write(n);
        this.output.write(n2);
    }
}
