// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.asm;

import dev.imanity.antixray.libs.asm.Type;
import dev.imanity.antixray.libs.asm.FieldVisitor;
import dev.imanity.antixray.libs.asm.AnnotationVisitor;
import dev.imanity.antixray.libs.asm.MethodVisitor;
import dev.imanity.antixray.libs.asm.ClassVisitor;
import dev.imanity.antixray.libs.asm.ClassWriter;
import dev.imanity.antixray.libs.asm.ClassReader;

public class AnnotationReplacer
{
    public static byte[] replace(final byte[] array, final Transformer transformer) {
        final ClassReader classReader = new ClassReader(array);
        final ClassWriter classWriter = new ClassWriter(0);
        classReader.accept(new Adapter(classWriter, transformer), 0);
        return classWriter.toByteArray();
    }
    
    private static class Adapter extends ClassVisitor
    {
        private final Transformer transformer;
        
        public Adapter(final ClassVisitor classVisitor, final Transformer transformer) {
            super(589824, classVisitor);
            this.transformer = transformer;
        }
        
        @Override
        public void visit(final int n, final int n2, final String s, final String s2, final String s3, final String[] array) {
            super.visit(n, n2, s, s2, s3, array);
        }
        
        @Override
        public MethodVisitor visitMethod(final int n, final String s, final String s2, final String s3, final String[] array) {
            return new MethodVisitor(this.api, super.visitMethod(n, s, s2, s3, array)) {
                @Override
                public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
                    return new AnnotationAdapter(super.visitAnnotation(s, b), s);
                }
            };
        }
        
        @Override
        public FieldVisitor visitField(final int n, final String s, final String s2, final String s3, final Object o) {
            return new FieldVisitor(this.api, super.visitField(n, s, s2, s3, o)) {
                @Override
                public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
                    return new AnnotationAdapter(super.visitAnnotation(s, b), s);
                }
            };
        }
        
        @Override
        public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
            return new AnnotationAdapter(super.visitAnnotation(s, b), s);
        }
        
        private class AnnotationAdapter extends AnnotationVisitor
        {
            private final String annotationName;
            
            public AnnotationAdapter(final AnnotationVisitor annotationVisitor, final String s) {
                super(Adapter.this.api, annotationVisitor);
                this.annotationName = Type.getType(s).getClassName();
            }
            
            @Override
            public void visit(final String s, final Object o) {
                if (o instanceof String) {
                    super.visit(s, Adapter.this.transformer.transform(this.annotationName, (String)o));
                }
                else {
                    super.visit(s, o);
                }
            }
            
            @Override
            public AnnotationVisitor visitArray(final String s) {
                return new AnnotationVisitor(Adapter.this.api, super.visitArray(s)) {
                    @Override
                    public AnnotationVisitor visitAnnotation(final String s, final String s2) {
                        return new AnnotationAdapter(super.visitAnnotation(s, s2), s2);
                    }
                };
            }
        }
    }
    
    public interface Transformer
    {
        String transform(final String p0, final String p1);
    }
}
