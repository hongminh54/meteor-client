// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import java.io.OutputStream;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.TypeAnnotationsWriter;
import java.util.HashMap;
import java.util.Map;
import java.io.DataInputStream;

public class TypeAnnotationsAttribute extends AttributeInfo
{
    public static final String visibleTag = "RuntimeVisibleTypeAnnotations";
    public static final String invisibleTag = "RuntimeInvisibleTypeAnnotations";
    
    public TypeAnnotationsAttribute(final ConstPool constPool, final String s, final byte[] array) {
        super(constPool, s, array);
    }
    
    TypeAnnotationsAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) {
        super(constPool, n, dataInputStream);
    }
    
    public int numAnnotations() {
        return ByteArray.readU16bit(this.info, 0);
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map<String, String> map) {
        final Copier copier = new Copier(this.info, this.constPool, constPool, map);
        try {
            copier.annotationArray();
            return new TypeAnnotationsAttribute(constPool, this.getName(), copier.close());
        }
        catch (final Exception cause) {
            throw new RuntimeException(cause);
        }
    }
    
    @Override
    void renameClass(final String s, final String s2) {
        final HashMap hashMap = new HashMap();
        hashMap.put(s, s2);
        this.renameClass(hashMap);
    }
    
    @Override
    void renameClass(final Map<String, String> map) {
        final Renamer renamer = new Renamer(this.info, this.getConstPool(), map);
        try {
            renamer.annotationArray();
        }
        catch (final Exception cause) {
            throw new RuntimeException(cause);
        }
    }
    
    @Override
    void getRefClasses(final Map<String, String> map) {
        this.renameClass(map);
    }
    
    static class TAWalker extends AnnotationsAttribute.Walker
    {
        SubWalker subWalker;
        
        TAWalker(final byte[] array) {
            super(array);
            this.subWalker = new SubWalker(array);
        }
        
        @Override
        int annotationArray(int n, final int n2) {
            for (int i = 0; i < n2; ++i) {
                n = this.subWalker.targetInfo(n + 1, this.info[n] & 0xFF);
                n = this.subWalker.typePath(n);
                n = this.annotation(n);
            }
            return n;
        }
    }
    
    static class SubWalker
    {
        byte[] info;
        
        SubWalker(final byte[] info) {
            this.info = info;
        }
        
        final int targetInfo(final int n, final int i) {
            switch (i) {
                case 0:
                case 1: {
                    this.typeParameterTarget(n, i, this.info[n] & 0xFF);
                    return n + 1;
                }
                case 16: {
                    this.supertypeTarget(n, ByteArray.readU16bit(this.info, n));
                    return n + 2;
                }
                case 17:
                case 18: {
                    this.typeParameterBoundTarget(n, i, this.info[n] & 0xFF, this.info[n + 1] & 0xFF);
                    return n + 2;
                }
                case 19:
                case 20:
                case 21: {
                    this.emptyTarget(n, i);
                    return n;
                }
                case 22: {
                    this.formalParameterTarget(n, this.info[n] & 0xFF);
                    return n + 1;
                }
                case 23: {
                    this.throwsTarget(n, ByteArray.readU16bit(this.info, n));
                    return n + 2;
                }
                case 64:
                case 65: {
                    return this.localvarTarget(n + 2, i, ByteArray.readU16bit(this.info, n));
                }
                case 66: {
                    this.catchTarget(n, ByteArray.readU16bit(this.info, n));
                    return n + 2;
                }
                case 67:
                case 68:
                case 69:
                case 70: {
                    this.offsetTarget(n, i, ByteArray.readU16bit(this.info, n));
                    return n + 2;
                }
                case 71:
                case 72:
                case 73:
                case 74:
                case 75: {
                    this.typeArgumentTarget(n, i, ByteArray.readU16bit(this.info, n), this.info[n + 2] & 0xFF);
                    return n + 3;
                }
                default: {
                    throw new RuntimeException("invalid target type: " + i);
                }
            }
        }
        
        void typeParameterTarget(final int n, final int n2, final int n3) {
        }
        
        void supertypeTarget(final int n, final int n2) {
        }
        
        void typeParameterBoundTarget(final int n, final int n2, final int n3, final int n4) {
        }
        
        void emptyTarget(final int n, final int n2) {
        }
        
        void formalParameterTarget(final int n, final int n2) {
        }
        
        void throwsTarget(final int n, final int n2) {
        }
        
        int localvarTarget(int n, final int n2, final int n3) {
            for (int i = 0; i < n3; ++i) {
                this.localvarTarget(n, n2, ByteArray.readU16bit(this.info, n), ByteArray.readU16bit(this.info, n + 2), ByteArray.readU16bit(this.info, n + 4));
                n += 6;
            }
            return n;
        }
        
        void localvarTarget(final int n, final int n2, final int n3, final int n4, final int n5) {
        }
        
        void catchTarget(final int n, final int n2) {
        }
        
        void offsetTarget(final int n, final int n2, final int n3) {
        }
        
        void typeArgumentTarget(final int n, final int n2, final int n3, final int n4) {
        }
        
        final int typePath(int n) {
            return this.typePath(n, this.info[n++] & 0xFF);
        }
        
        int typePath(int n, final int n2) {
            for (int i = 0; i < n2; ++i) {
                this.typePath(n, this.info[n] & 0xFF, this.info[n + 1] & 0xFF);
                n += 2;
            }
            return n;
        }
        
        void typePath(final int n, final int n2, final int n3) {
        }
    }
    
    static class Renamer extends AnnotationsAttribute.Renamer
    {
        SubWalker sub;
        
        Renamer(final byte[] array, final ConstPool constPool, final Map<String, String> map) {
            super(array, constPool, map);
            this.sub = new SubWalker(array);
        }
        
        @Override
        int annotationArray(int n, final int n2) {
            for (int i = 0; i < n2; ++i) {
                n = this.sub.targetInfo(n + 1, this.info[n] & 0xFF);
                n = this.sub.typePath(n);
                n = this.annotation(n);
            }
            return n;
        }
    }
    
    static class Copier extends AnnotationsAttribute.Copier
    {
        SubCopier sub;
        
        Copier(final byte[] array, final ConstPool constPool, final ConstPool constPool2, final Map<String, String> map) {
            super(array, constPool, constPool2, map, false);
            final TypeAnnotationsWriter writer = new TypeAnnotationsWriter(this.output, constPool2);
            this.writer = writer;
            this.sub = new SubCopier(array, constPool, constPool2, map, writer);
        }
        
        @Override
        int annotationArray(int n, final int n2) {
            this.writer.numAnnotations(n2);
            for (int i = 0; i < n2; ++i) {
                n = this.sub.targetInfo(n + 1, this.info[n] & 0xFF);
                n = this.sub.typePath(n);
                n = this.annotation(n);
            }
            return n;
        }
    }
    
    static class SubCopier extends SubWalker
    {
        ConstPool srcPool;
        ConstPool destPool;
        Map<String, String> classnames;
        TypeAnnotationsWriter writer;
        
        SubCopier(final byte[] array, final ConstPool srcPool, final ConstPool destPool, final Map<String, String> classnames, final TypeAnnotationsWriter writer) {
            super(array);
            this.srcPool = srcPool;
            this.destPool = destPool;
            this.classnames = classnames;
            this.writer = writer;
        }
        
        @Override
        void typeParameterTarget(final int n, final int n2, final int n3) {
            this.writer.typeParameterTarget(n2, n3);
        }
        
        @Override
        void supertypeTarget(final int n, final int n2) {
            this.writer.supertypeTarget(n2);
        }
        
        @Override
        void typeParameterBoundTarget(final int n, final int n2, final int n3, final int n4) {
            this.writer.typeParameterBoundTarget(n2, n3, n4);
        }
        
        @Override
        void emptyTarget(final int n, final int n2) {
            this.writer.emptyTarget(n2);
        }
        
        @Override
        void formalParameterTarget(final int n, final int n2) {
            this.writer.formalParameterTarget(n2);
        }
        
        @Override
        void throwsTarget(final int n, final int n2) {
            this.writer.throwsTarget(n2);
        }
        
        @Override
        int localvarTarget(final int n, final int n2, final int n3) {
            this.writer.localVarTarget(n2, n3);
            return super.localvarTarget(n, n2, n3);
        }
        
        @Override
        void localvarTarget(final int n, final int n2, final int n3, final int n4, final int n5) {
            this.writer.localVarTargetTable(n3, n4, n5);
        }
        
        @Override
        void catchTarget(final int n, final int n2) {
            this.writer.catchTarget(n2);
        }
        
        @Override
        void offsetTarget(final int n, final int n2, final int n3) {
            this.writer.offsetTarget(n2, n3);
        }
        
        @Override
        void typeArgumentTarget(final int n, final int n2, final int n3, final int n4) {
            this.writer.typeArgumentTarget(n2, n3, n4);
        }
        
        @Override
        int typePath(final int n, final int n2) {
            this.writer.typePath(n2);
            return super.typePath(n, n2);
        }
        
        @Override
        void typePath(final int n, final int n2, final int n3) {
            this.writer.typePathPath(n2, n3);
        }
    }
}
