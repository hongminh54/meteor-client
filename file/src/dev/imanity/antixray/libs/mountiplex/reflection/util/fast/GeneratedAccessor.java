// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.fast;

import sun.misc.Unsafe;
import dev.imanity.antixray.libs.asm.Type;
import dev.imanity.antixray.libs.asm.MethodVisitor;
import java.io.Serializable;
import dev.imanity.antixray.libs.asm.Label;
import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;
import dev.imanity.antixray.libs.mountiplex.reflection.util.ExtendedClassWriter;
import java.lang.reflect.Modifier;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.lang.reflect.Field;

public class GeneratedAccessor<T> extends ReflectionAccessor<T>
{
    protected GeneratedAccessor(final Field field) {
        super(field);
    }
    
    public static <T> GeneratedAccessor<T> create(final Field field) {
        final String internalName = MPLType.getInternalName(GeneratedAccessor.class);
        final int modifiers = field.getModifiers();
        final boolean b = Modifier.isFinal(modifiers) && Modifier.isStatic(modifiers);
        final Serializable s = b ? GeneratedStaticFinalAccessor.class : GeneratedAccessor.class;
        final ExtendedClassWriter<Object> build = ExtendedClassWriter.builder((Class<? super Object>)s).setFlags(1).setAccess(16).build();
        final MethodVisitor visitMethod = build.visitMethod(1, "<init>", "(Ljava/lang/reflect/Field;)V", null, null);
        visitMethod.visitCode();
        visitMethod.visitVarInsn(25, 0);
        visitMethod.visitVarInsn(25, 1);
        visitMethod.visitMethodInsn(183, MPLType.getInternalName((Class<?>)s), "<init>", "(" + MPLType.getDescriptor(Field.class) + ")V", false);
        visitMethod.visitInsn(177);
        visitMethod.visitMaxs(2, 2);
        visitMethod.visitEnd();
        final String internalName2 = MPLType.getInternalName(field.getDeclaringClass());
        final Type type = MPLType.getType(field.getType());
        final String descriptor = type.getDescriptor();
        final String name = MPLType.getName(field);
        String simpleName = null;
        if (field.getType().isPrimitive()) {
            final Class<?> boxedType = BoxedType.getBoxedType(field.getType());
            if (boxedType != null) {
                simpleName = boxedType.getSimpleName();
            }
        }
        if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers)) {
            if (simpleName == null) {
                final String internalName3 = MPLType.getInternalName(field.getType());
                final MethodVisitor visitMethod2 = build.visitMethod(1, "get", "(Ljava/lang/Object;)Ljava/lang/Object;", null, null);
                visitMethod2.visitCode();
                visitMethod2.visitVarInsn(25, 1);
                visitMethod2.visitTypeInsn(192, internalName2);
                visitMethod2.visitFieldInsn(180, internalName2, name, descriptor);
                visitMethod2.visitInsn(176);
                visitMethod2.visitMaxs(0, 0);
                visitMethod2.visitEnd();
                if (!Modifier.isFinal(modifiers)) {
                    final MethodVisitor visitMethod3 = build.visitMethod(1, "set", "(Ljava/lang/Object;Ljava/lang/Object;)V", null, null);
                    visitMethod3.visitCode();
                    visitMethod3.visitVarInsn(25, 1);
                    visitMethod3.visitTypeInsn(192, internalName2);
                    visitMethod3.visitVarInsn(25, 2);
                    visitMethod3.visitTypeInsn(192, internalName3);
                    visitMethod3.visitFieldInsn(181, internalName2, name, descriptor);
                    visitMethod3.visitInsn(177);
                    visitMethod3.visitMaxs(0, 0);
                    visitMethod3.visitEnd();
                }
            }
            else {
                final MethodVisitor visitMethod4 = build.visitMethod(1, "get" + simpleName, "(Ljava/lang/Object;)" + descriptor, null, null);
                visitMethod4.visitCode();
                visitMethod4.visitVarInsn(25, 1);
                visitMethod4.visitTypeInsn(192, internalName2);
                visitMethod4.visitFieldInsn(180, internalName2, name, descriptor);
                visitMethod4.visitInsn(type.getOpcode(172));
                visitMethod4.visitMaxs(0, 0);
                visitMethod4.visitEnd();
                if (!Modifier.isFinal(modifiers)) {
                    final MethodVisitor visitMethod5 = build.visitMethod(1, "set" + simpleName, "(Ljava/lang/Object;" + descriptor + ")V", null, null);
                    visitMethod5.visitCode();
                    visitMethod5.visitVarInsn(25, 1);
                    visitMethod5.visitTypeInsn(192, internalName2);
                    visitMethod5.visitVarInsn(type.getOpcode(21), 2);
                    visitMethod5.visitFieldInsn(181, internalName2, name, descriptor);
                    visitMethod5.visitInsn(177);
                    visitMethod5.visitMaxs(0, 0);
                    visitMethod5.visitEnd();
                }
            }
        }
        if (b && simpleName != null) {
            String string;
            if (field.getType() == Integer.TYPE) {
                string = "putInt";
            }
            else if (field.getType() == Character.TYPE) {
                string = "putChar";
            }
            else {
                string = "put" + simpleName;
            }
            final MethodVisitor visitMethod6 = build.visitMethod(1, "set" + simpleName, "(Ljava/lang/Object;" + descriptor + ")V", null, null);
            visitMethod6.visitCode();
            visitMethod6.visitVarInsn(25, 0);
            visitMethod6.visitFieldInsn(180, build.getInternalName(), "class_init", "Z");
            final Label label = new Label();
            visitMethod6.visitJumpInsn(154, label);
            visitMethod6.visitVarInsn(25, 0);
            visitMethod6.visitMethodInsn(182, build.getInternalName(), "initDeclaringClass", "()V", false);
            visitMethod6.visitLabel(label);
            visitMethod6.visitFrame(3, 0, null, 0, null);
            visitMethod6.visitVarInsn(25, 0);
            visitMethod6.visitFieldInsn(180, build.getInternalName(), "unsafe", "Lsun/misc/Unsafe;");
            visitMethod6.visitVarInsn(25, 0);
            visitMethod6.visitFieldInsn(180, build.getInternalName(), "base", "Ljava/lang/Object;");
            visitMethod6.visitVarInsn(25, 0);
            visitMethod6.visitFieldInsn(180, build.getInternalName(), "offset", "J");
            visitMethod6.visitVarInsn(MPLType.getOpcode(field.getType(), 21), 2);
            visitMethod6.visitMethodInsn(182, "sun/misc/Unsafe", string, "(Ljava/lang/Object;J" + descriptor + ")V", false);
            visitMethod6.visitInsn(177);
            visitMethod6.visitMaxs(0, 0);
            visitMethod6.visitEnd();
            final String internalName4 = MPLType.getInternalName(BoxedType.getBoxedType(field.getType()));
            final MethodVisitor visitMethod7 = build.visitMethod(1, "set", "(Ljava/lang/Object;Ljava/lang/Object;)V", null, null);
            visitMethod7.visitCode();
            visitMethod7.visitVarInsn(25, 2);
            visitMethod7.visitTypeInsn(193, internalName4);
            final Label label2 = new Label();
            visitMethod7.visitJumpInsn(153, label2);
            visitMethod7.visitVarInsn(25, 0);
            visitMethod7.visitVarInsn(25, 1);
            visitMethod7.visitVarInsn(25, 2);
            visitMethod7.visitTypeInsn(192, internalName4);
            visitMethod7.visitMethodInsn(182, internalName4, field.getType().getSimpleName() + "Value", "()" + descriptor, false);
            visitMethod7.visitMethodInsn(182, build.getInternalName(), "set" + simpleName, "(Ljava/lang/Object;" + descriptor + ")V", false);
            final Label label3 = new Label();
            visitMethod7.visitJumpInsn(167, label3);
            visitMethod7.visitLabel(label2);
            visitMethod7.visitFrame(3, 0, null, 0, null);
            visitMethod7.visitVarInsn(25, 0);
            visitMethod7.visitVarInsn(25, 2);
            visitMethod7.visitMethodInsn(182, build.getInternalName(), "checkValueType", "(Ljava/lang/Object;)V", false);
            visitMethod7.visitLabel(label3);
            visitMethod7.visitFrame(3, 0, null, 0, null);
            visitMethod7.visitInsn(177);
            visitMethod7.visitMaxs(0, 0);
            visitMethod7.visitEnd();
        }
        if (Modifier.isStatic(modifiers)) {
            final MethodVisitor visitMethod8 = build.visitMethod(1, "copy", "(Ljava/lang/Object;Ljava/lang/Object;)V", null, null);
            visitMethod8.visitCode();
            visitMethod8.visitTypeInsn(187, "java/lang/UnsupportedOperationException");
            visitMethod8.visitInsn(89);
            visitMethod8.visitLdcInsn("Static fields can not be copied");
            visitMethod8.visitMethodInsn(183, "java/lang/UnsupportedOperationException", "<init>", "(Ljava/lang/String;)V", false);
            visitMethod8.visitInsn(191);
            visitMethod8.visitMaxs(0, 0);
            visitMethod8.visitEnd();
        }
        else if (Modifier.isPublic(modifiers) && !Modifier.isFinal(modifiers)) {
            final MethodVisitor visitMethod9 = build.visitMethod(1, "copy", "(Ljava/lang/Object;Ljava/lang/Object;)V", null, null);
            visitMethod9.visitCode();
            visitMethod9.visitVarInsn(25, 2);
            visitMethod9.visitTypeInsn(192, internalName2);
            visitMethod9.visitVarInsn(25, 1);
            visitMethod9.visitTypeInsn(192, internalName2);
            visitMethod9.visitFieldInsn(180, internalName2, name, descriptor);
            visitMethod9.visitFieldInsn(181, internalName2, name, descriptor);
            visitMethod9.visitInsn(177);
            visitMethod9.visitMaxs(0, 0);
            visitMethod9.visitEnd();
        }
        else if (Modifier.isPublic(modifiers) && Modifier.isFinal(modifiers)) {
            final String s2 = (simpleName == null) ? "set" : ("set" + simpleName);
            final MethodVisitor visitMethod10 = build.visitMethod(1, "copy", "(Ljava/lang/Object;Ljava/lang/Object;)V", null, null);
            visitMethod10.visitCode();
            visitMethod10.visitVarInsn(25, 0);
            visitMethod10.visitVarInsn(25, 2);
            visitMethod10.visitVarInsn(25, 1);
            visitMethod10.visitTypeInsn(192, internalName2);
            visitMethod10.visitFieldInsn(180, internalName2, name, descriptor);
            visitMethod10.visitMethodInsn(182, internalName, s2, "(Ljava/lang/Object;" + descriptor + ")V", false);
            visitMethod10.visitInsn(177);
            visitMethod10.visitMaxs(0, 0);
            visitMethod10.visitEnd();
        }
        else if (simpleName != null) {
            final MethodVisitor visitMethod11 = build.visitMethod(1, "copy", "(Ljava/lang/Object;Ljava/lang/Object;)V", null, null);
            visitMethod11.visitCode();
            visitMethod11.visitVarInsn(25, 0);
            visitMethod11.visitVarInsn(25, 2);
            visitMethod11.visitVarInsn(25, 0);
            visitMethod11.visitVarInsn(25, 1);
            visitMethod11.visitMethodInsn(182, internalName, "get" + simpleName, "(Ljava/lang/Object;)" + descriptor, false);
            visitMethod11.visitMethodInsn(182, internalName, "set" + simpleName, "(Ljava/lang/Object;" + descriptor + ")V", false);
            visitMethod11.visitInsn(177);
            visitMethod11.visitMaxs(0, 0);
            visitMethod11.visitEnd();
        }
        return (GeneratedAccessor<T>)build.generateInstance(new Class[] { Field.class }, new Object[] { field });
    }
    
    public static class GeneratedStaticFinalAccessor<T> extends GeneratedAccessor<T>
    {
        protected final Class<?> type;
        protected final Unsafe unsafe;
        protected final Object base;
        protected final long offset;
        protected boolean class_init;
        
        protected GeneratedStaticFinalAccessor(final Field field) {
            super(field);
            Unsafe unsafe;
            try {
                final Field declaredField = Class.forName("sun.misc.Unsafe").getDeclaredField("theUnsafe");
                declaredField.setAccessible(true);
                unsafe = (Unsafe)declaredField.get(null);
            }
            catch (final Throwable cause) {
                throw new UnsupportedOperationException("Failed to access sun.misc.Unsafe", cause);
            }
            this.type = field.getType();
            this.unsafe = unsafe;
            this.base = unsafe.staticFieldBase(field);
            this.offset = unsafe.staticFieldOffset(field);
            this.class_init = false;
        }
        
        protected final synchronized void initDeclaringClass() {
            if (!this.class_init) {
                try {
                    Class.forName(this.getWriteField().getDeclaringClass().getName());
                }
                catch (final ClassNotFoundException cause) {
                    throw new IllegalStateException("Field declaring class failed to initialize", cause);
                }
                this.class_init = true;
            }
        }
        
        protected void checkValueType(final Object o) {
            if (this.type.isPrimitive()) {
                if (o == null) {
                    throw new IllegalArgumentException("Field of primitive type " + this.type.getName() + " cannot be assigned null");
                }
                final Class<?> unboxedType = BoxedType.getUnboxedType(o.getClass());
                if (unboxedType != null && unboxedType != this.type) {
                    throw new IllegalArgumentException("Field of primitive type " + this.type.getName() + " cannot be assigned a value of type " + unboxedType);
                }
                throw new IllegalArgumentException("Field of primitive type " + this.type.getName() + " cannot be assigned a value of type " + o.getClass().getName());
            }
            else if (o != null && !this.type.isAssignableFrom(o.getClass())) {
                throw new IllegalArgumentException("Field of type " + this.type.getName() + " cannot be assigned a value of type " + o.getClass().getName());
            }
        }
        
        @Override
        public void setDouble(final Object o, final double d) {
            this.checkValueType(d);
        }
        
        @Override
        public void setFloat(final Object o, final float f) {
            this.checkValueType(f);
        }
        
        @Override
        public void setByte(final Object o, final byte b) {
            this.checkValueType(b);
        }
        
        @Override
        public void setShort(final Object o, final short s) {
            this.checkValueType(s);
        }
        
        @Override
        public void setInteger(final Object o, final int i) {
            this.checkValueType(i);
        }
        
        @Override
        public void setLong(final Object o, final long l) {
            this.checkValueType(l);
        }
        
        @Override
        public void setCharacter(final Object o, final char c) {
            this.checkValueType(c);
        }
        
        @Override
        public void setBoolean(final Object o, final boolean b) {
            this.checkValueType(b);
        }
        
        @Override
        public void set(final Object o, final Object x) {
            if (!this.class_init) {
                this.initDeclaringClass();
            }
            if (x == null || this.type.isInstance(x)) {
                this.unsafe.putObject(this.base, this.offset, x);
            }
            else {
                this.checkValueType(x);
            }
        }
        
        public static void setUninitializedField(final Field field, final Object x) {
            final GeneratedStaticFinalAccessor generatedStaticFinalAccessor = new GeneratedStaticFinalAccessor(field);
            if (x == null || generatedStaticFinalAccessor.type.isInstance(x)) {
                generatedStaticFinalAccessor.unsafe.putObject(generatedStaticFinalAccessor.base, generatedStaticFinalAccessor.offset, x);
            }
            else {
                generatedStaticFinalAccessor.checkValueType(x);
            }
        }
    }
}
