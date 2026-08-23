// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.asm;

import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;
import java.util.Comparator;
import java.util.TreeSet;
import java.lang.reflect.Method;
import java.io.InputStream;
import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.asm.Label;
import dev.imanity.antixray.libs.asm.Type;
import java.util.Collection;
import dev.imanity.antixray.libs.asm.MethodVisitor;
import dev.imanity.antixray.libs.asm.FieldVisitor;
import dev.imanity.antixray.libs.asm.Attribute;
import dev.imanity.antixray.libs.asm.AnnotationVisitor;
import dev.imanity.antixray.libs.asm.ClassVisitor;
import dev.imanity.antixray.libs.asm.ClassWriter;
import dev.imanity.antixray.libs.asm.ClassReader;
import java.util.Set;

public class ASMUtil
{
    private static final int ASM_VERSION = 589824;
    
    public static byte[] removeClassMethods(final byte[] array, final Set<String> set) {
        final ClassReader classReader = new ClassReader(array);
        final ClassWriter classWriter = new ClassWriter(classReader, 0);
        classReader.accept(new ClassVisitor(589824) {
            @Override
            public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
                return classWriter.visitAnnotation(s, b);
            }
            
            @Override
            public void visitAttribute(final Attribute attribute) {
                classWriter.visitAttribute(attribute);
            }
            
            @Override
            public void visitEnd() {
                classWriter.visitEnd();
            }
            
            @Override
            public FieldVisitor visitField(final int n, final String s, final String s2, final String s3, final Object o) {
                return classWriter.visitField(n, s, s2, s3, o);
            }
            
            @Override
            public void visitInnerClass(final String s, final String s2, final String s3, final int n) {
                classWriter.visitInnerClass(s, s2, s3, n);
            }
            
            @Override
            public MethodVisitor visitMethod(final int n, final String str, final String str2, final String s, final String[] array) {
                if (set.contains(str + str2)) {
                    return null;
                }
                return classWriter.visitMethod(n, str, str2, s, array);
            }
            
            @Override
            public void visitOuterClass(final String s, final String s2, final String s3) {
                classWriter.visitOuterClass(s, s2, s3);
            }
            
            @Override
            public void visitSource(final String s, final String s2) {
                classWriter.visitSource(s, s2);
            }
            
            @Override
            public void visit(final int n, final int n2, final String s, final String s2, final String s3, final String[] array) {
                classWriter.visit(n, n2, s, s2, s3, array);
            }
        }, 0);
        return classWriter.toByteArray();
    }
    
    public static final Collection<Class<?>> findUsedTypes(final Class<?> obj) {
        final FoundTypeSet set = new FoundTypeSet(obj.getClassLoader());
        try {
            final InputStream resourceAsStream = obj.getResourceAsStream(obj.getSimpleName() + ".class");
            if (resourceAsStream == null) {
                return set;
            }
            new ClassReader(resourceAsStream).accept(new ClassVisitor(589824) {
                @Override
                public void visit(final int n, final int n2, final String s, final String s2, final String s3, final String[] array) {
                    set.add(Type.getObjectType(s));
                    if (s3 != null) {
                        set.add(Type.getObjectType(s3));
                    }
                    if (array != null) {
                        for (int length = array.length, i = 0; i < length; ++i) {
                            set.add(Type.getObjectType(array[i]));
                        }
                    }
                }
                
                @Override
                public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
                    return null;
                }
                
                @Override
                public void visitAttribute(final Attribute attribute) {
                }
                
                @Override
                public void visitEnd() {
                }
                
                @Override
                public FieldVisitor visitField(final int n, final String s, final String s2, final String s3, final Object o) {
                    set.add(Type.getType(s2));
                    return null;
                }
                
                @Override
                public void visitInnerClass(final String s, final String s2, final String s3, final int n) {
                }
                
                @Override
                public MethodVisitor visitMethod(final int n, final String s, final String s2, final String s3, final String[] array) {
                    final Type[] argumentTypes = Type.getArgumentTypes(s2);
                    for (int length = argumentTypes.length, i = 0; i < length; ++i) {
                        set.add(argumentTypes[i]);
                    }
                    set.add(Type.getReturnType(s2));
                    return new MethodVisitor(589824) {
                        @Override
                        public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
                            return null;
                        }
                        
                        @Override
                        public AnnotationVisitor visitAnnotationDefault() {
                            return null;
                        }
                        
                        @Override
                        public void visitAttribute(final Attribute attribute) {
                        }
                        
                        @Override
                        public void visitCode() {
                        }
                        
                        @Override
                        public void visitEnd() {
                        }
                        
                        @Override
                        public void visitFieldInsn(final int n, final String s, final String s2, final String s3) {
                            set.add(Type.getObjectType(s));
                            set.add(Type.getType(s3));
                        }
                        
                        @Override
                        public void visitFrame(final int n, final int n2, final Object[] array, final int n3, final Object[] array2) {
                        }
                        
                        @Override
                        public void visitIincInsn(final int n, final int n2) {
                        }
                        
                        @Override
                        public void visitInsn(final int n) {
                        }
                        
                        @Override
                        public void visitIntInsn(final int n, final int n2) {
                        }
                        
                        @Override
                        public void visitJumpInsn(final int n, final Label label) {
                        }
                        
                        @Override
                        public void visitLabel(final Label label) {
                        }
                        
                        @Override
                        public void visitLdcInsn(final Object o) {
                            if (o instanceof Type) {
                                set.add((Type)o);
                            }
                        }
                        
                        @Override
                        public void visitLineNumber(final int n, final Label label) {
                        }
                        
                        @Override
                        public void visitLocalVariable(final String s, final String s2, final String s3, final Label label, final Label label2, final int n) {
                            set.add(Type.getType(s2));
                        }
                        
                        @Override
                        public void visitLookupSwitchInsn(final Label label, final int[] array, final Label[] array2) {
                        }
                        
                        @Override
                        public void visitMaxs(final int n, final int n2) {
                        }
                        
                        @Override
                        public void visitMethodInsn(final int n, final String s, final String s2, final String s3) {
                            set.add(Type.getObjectType(s));
                            final Type[] argumentTypes = Type.getArgumentTypes(s3);
                            for (int length = argumentTypes.length, i = 0; i < length; ++i) {
                                set.add(argumentTypes[i]);
                            }
                            set.add(Type.getReturnType(s3));
                        }
                        
                        @Override
                        public void visitMultiANewArrayInsn(final String s, final int n) {
                            set.add(Type.getType(s));
                        }
                        
                        @Override
                        public AnnotationVisitor visitParameterAnnotation(final int n, final String s, final boolean b) {
                            return null;
                        }
                        
                        @Override
                        public void visitTableSwitchInsn(final int n, final int n2, final Label label, final Label... array) {
                        }
                        
                        @Override
                        public void visitTryCatchBlock(final Label label, final Label label2, final Label label3, final String s) {
                        }
                        
                        @Override
                        public void visitTypeInsn(final int n, final String s) {
                            set.add(Type.getObjectType(s));
                        }
                        
                        @Override
                        public void visitVarInsn(final int n, final int n2) {
                        }
                    };
                }
                
                @Override
                public void visitOuterClass(final String s, final String s2, final String s3) {
                }
                
                @Override
                public void visitSource(final String s, final String s2) {
                }
            }, 0);
        }
        catch (final Throwable thrown) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Unhandled error finding used types of " + obj, thrown);
        }
        return set;
    }
    
    public static String findStringConstantReturnedByMethod(final Method method) {
        final FoundConstantInfo foundConstantInfo = new FoundConstantInfo();
        final String name = method.getName();
        final String methodDescriptor = MPLType.getMethodDescriptor(method);
        try {
            final Class<?> declaringClass = method.getDeclaringClass();
            final InputStream resourceAsStream = declaringClass.getResourceAsStream(declaringClass.getSimpleName() + ".class");
            if (resourceAsStream == null) {
                return null;
            }
            new ClassReader(resourceAsStream).accept(new ClassVisitor(589824) {
                @Override
                public MethodVisitor visitMethod(final int n, final String anObject, final String anObject2, final String s, final String[] array) {
                    if (!name.equals(anObject) || !methodDescriptor.equals(anObject2)) {
                        return null;
                    }
                    return new MethodVisitor(589824) {
                        @Override
                        public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
                            return null;
                        }
                        
                        @Override
                        public AnnotationVisitor visitAnnotationDefault() {
                            return null;
                        }
                        
                        @Override
                        public void visitAttribute(final Attribute attribute) {
                        }
                        
                        @Override
                        public void visitCode() {
                            foundConstantInfo.isValidSig = true;
                        }
                        
                        @Override
                        public void visitFieldInsn(final int n, final String s, final String s2, final String s3) {
                            foundConstantInfo.invalid();
                        }
                        
                        @Override
                        public void visitFrame(final int n, final int n2, final Object[] array, final int n3, final Object[] array2) {
                            foundConstantInfo.invalid();
                        }
                        
                        @Override
                        public void visitIincInsn(final int n, final int n2) {
                            foundConstantInfo.invalid();
                        }
                        
                        @Override
                        public void visitInsn(final int n) {
                            if (n != 176 || foundConstantInfo.result == null) {
                                foundConstantInfo.invalid();
                            }
                        }
                        
                        @Override
                        public void visitIntInsn(final int n, final int n2) {
                            foundConstantInfo.invalid();
                        }
                        
                        @Override
                        public void visitJumpInsn(final int n, final Label label) {
                            foundConstantInfo.invalid();
                        }
                        
                        @Override
                        public void visitLabel(final Label label) {
                        }
                        
                        @Override
                        public void visitLdcInsn(final Object o) {
                            if (foundConstantInfo.isValidSig && o instanceof String) {
                                foundConstantInfo.result = (String)o;
                            }
                            else {
                                foundConstantInfo.invalid();
                            }
                        }
                        
                        @Override
                        public void visitLineNumber(final int n, final Label label) {
                        }
                        
                        @Override
                        public void visitLocalVariable(final String s, final String s2, final String s3, final Label label, final Label label2, final int n) {
                        }
                        
                        @Override
                        public void visitLookupSwitchInsn(final Label label, final int[] array, final Label[] array2) {
                            foundConstantInfo.invalid();
                        }
                        
                        @Override
                        public void visitMaxs(final int n, final int n2) {
                        }
                        
                        @Override
                        public void visitMethodInsn(final int n, final String s, final String s2, final String s3) {
                            foundConstantInfo.invalid();
                        }
                        
                        @Override
                        public void visitMultiANewArrayInsn(final String s, final int n) {
                            foundConstantInfo.invalid();
                        }
                        
                        @Override
                        public AnnotationVisitor visitParameterAnnotation(final int n, final String s, final boolean b) {
                            return null;
                        }
                        
                        @Override
                        public void visitTableSwitchInsn(final int n, final int n2, final Label label, final Label... array) {
                            foundConstantInfo.invalid();
                        }
                        
                        @Override
                        public void visitTryCatchBlock(final Label label, final Label label2, final Label label3, final String s) {
                            foundConstantInfo.invalid();
                        }
                        
                        @Override
                        public void visitTypeInsn(final int n, final String s) {
                            foundConstantInfo.invalid();
                        }
                        
                        @Override
                        public void visitVarInsn(final int n, final int n2) {
                            foundConstantInfo.invalid();
                        }
                    };
                }
            }, 0);
        }
        catch (final Throwable t) {}
        return foundConstantInfo.isValidSig ? foundConstantInfo.result : null;
    }
    
    public static StackTraceElement findMethodDetails(final Method obj) {
        final MethodInfo methodInfo = new MethodInfo(obj);
        final Class<?> declaringClass = obj.getDeclaringClass();
        try {
            final InputStream resourceAsStream = declaringClass.getResourceAsStream(declaringClass.getSimpleName() + ".class");
            if (resourceAsStream == null) {
                return methodInfo.toTrace();
            }
            new ClassReader(resourceAsStream).accept(new ClassVisitor(589824) {
                @Override
                public void visit(final int n, final int n2, final String s, final String s2, final String s3, final String[] array) {
                }
                
                @Override
                public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
                    return null;
                }
                
                @Override
                public void visitAttribute(final Attribute attribute) {
                }
                
                @Override
                public void visitEnd() {
                }
                
                @Override
                public FieldVisitor visitField(final int n, final String s, final String s2, final String s3, final Object o) {
                    return null;
                }
                
                @Override
                public void visitInnerClass(final String s, final String s2, final String s3, final int n) {
                }
                
                @Override
                public MethodVisitor visitMethod(final int n, final String s, final String s2, final String s3, final String[] array) {
                    if (!s.equals(methodInfo.method.getName())) {
                        return null;
                    }
                    return new MethodVisitor(589824) {
                        @Override
                        public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
                            return null;
                        }
                        
                        @Override
                        public AnnotationVisitor visitAnnotationDefault() {
                            return null;
                        }
                        
                        @Override
                        public void visitAttribute(final Attribute attribute) {
                        }
                        
                        @Override
                        public void visitCode() {
                        }
                        
                        @Override
                        public void visitEnd() {
                        }
                        
                        @Override
                        public void visitFieldInsn(final int n, final String s, final String s2, final String s3) {
                        }
                        
                        @Override
                        public void visitFrame(final int n, final int n2, final Object[] array, final int n3, final Object[] array2) {
                        }
                        
                        @Override
                        public void visitIincInsn(final int n, final int n2) {
                        }
                        
                        @Override
                        public void visitInsn(final int n) {
                        }
                        
                        @Override
                        public void visitIntInsn(final int n, final int n2) {
                        }
                        
                        @Override
                        public void visitJumpInsn(final int n, final Label label) {
                        }
                        
                        @Override
                        public void visitLabel(final Label label) {
                        }
                        
                        @Override
                        public void visitLdcInsn(final Object o) {
                        }
                        
                        @Override
                        public void visitLineNumber(final int n, final Label label) {
                            if (methodInfo.lineNumber == -1) {
                                methodInfo.lineNumber = n - 1;
                            }
                        }
                        
                        @Override
                        public void visitLocalVariable(final String s, final String s2, final String s3, final Label label, final Label label2, final int n) {
                        }
                        
                        @Override
                        public void visitLookupSwitchInsn(final Label label, final int[] array, final Label[] array2) {
                        }
                        
                        @Override
                        public void visitMaxs(final int n, final int n2) {
                        }
                        
                        @Override
                        public void visitMethodInsn(final int n, final String s, final String s2, final String s3) {
                        }
                        
                        @Override
                        public void visitMultiANewArrayInsn(final String s, final int n) {
                        }
                        
                        @Override
                        public AnnotationVisitor visitParameterAnnotation(final int n, final String s, final boolean b) {
                            return null;
                        }
                        
                        @Override
                        public void visitTableSwitchInsn(final int n, final int n2, final Label label, final Label... array) {
                        }
                        
                        @Override
                        public void visitTryCatchBlock(final Label label, final Label label2, final Label label3, final String s) {
                        }
                        
                        @Override
                        public void visitTypeInsn(final int n, final String s) {
                        }
                        
                        @Override
                        public void visitVarInsn(final int n, final int n2) {
                        }
                    };
                }
                
                @Override
                public void visitOuterClass(final String s, final String s2, final String s3) {
                }
                
                @Override
                public void visitSource(final String source, final String s) {
                    methodInfo.source = source;
                }
            }, 0);
        }
        catch (final Throwable thrown) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Unhandled error finding method details of " + obj, thrown);
        }
        return methodInfo.toTrace();
    }
    
    private static class MethodInfo
    {
        public String source;
        public int lineNumber;
        public Method method;
        
        public MethodInfo(final Method method) {
            this.source = null;
            this.lineNumber = -1;
            this.method = method;
        }
        
        public StackTraceElement toTrace() {
            return new StackTraceElement(MPLType.getName(this.method.getDeclaringClass()), MPLType.getName(this.method), this.source, this.lineNumber);
        }
    }
    
    private static class FoundConstantInfo
    {
        public String result;
        public boolean isValidSig;
        
        private FoundConstantInfo() {
            this.result = null;
            this.isValidSig = false;
        }
        
        public void invalid() {
            if (this.isValidSig) {
                this.isValidSig = false;
            }
        }
    }
    
    private static class FoundTypeSet extends TreeSet<Class<?>>
    {
        private static final long serialVersionUID = 1L;
        private final ClassLoader loader;
        
        public FoundTypeSet(final ClassLoader loader) {
            super(new Comparator<Class<?>>() {
                @Override
                public int compare(final Class<?> clazz, final Class<?> clazz2) {
                    if (clazz.isPrimitive() == clazz2.isPrimitive()) {
                        return MPLType.getName(clazz).compareTo(MPLType.getName(clazz2));
                    }
                    if (clazz.isPrimitive()) {
                        return -1;
                    }
                    return 1;
                }
            });
            this.loader = loader;
        }
        
        public void add(final Type type) {
            String name;
            for (name = type.getClassName(); name.endsWith("[]"); name = name.substring(0, name.length() - 2)) {}
            try {
                final Class<?> unboxedType = BoxedType.getUnboxedType(name);
                if (unboxedType != null) {
                    this.add(unboxedType);
                }
                else {
                    this.add(Class.forName(name, false, this.loader));
                }
            }
            catch (final Throwable t) {}
        }
    }
}
