// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.fast;

import java.util.IdentityHashMap;
import java.util.function.Function;
import java.util.HashMap;
import java.util.Iterator;
import dev.imanity.antixray.libs.asm.MethodVisitor;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import dev.imanity.antixray.libs.mountiplex.reflection.util.ExtendedClassWriter;
import java.util.List;
import java.lang.reflect.Modifier;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import java.lang.reflect.Field;
import java.util.ArrayList;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.util.stream.Stream;
import java.util.Map;

public abstract class ClassFieldCopier<T>
{
    private static final Map<Class<?>, ClassFieldCopier<?>> cache;
    private static final ClassFieldCopier<?> NOOP_COPIER;
    
    public final void copy(final T t, final T t2) {
        try {
            this.tryCopy(t, t2);
        }
        catch (final Throwable cause) {
            if (t == null) {
                throw new IllegalArgumentException("Object to copy fields from is null");
            }
            if (t2 == null) {
                throw new IllegalArgumentException("Object to copy fields to is null");
            }
            throw new UnsupportedOperationException("Failed to copy fields of " + t.getClass() + " to " + t2.getClass(), cause);
        }
    }
    
    protected abstract void tryCopy(final T p0, final T p1);
    
    protected Stream<ClassFieldCopier<?>> all() {
        return (Stream<ClassFieldCopier<?>>)MountiplexUtil.toStream(this);
    }
    
    protected final void copyAllWithReflection(final ReflectionCopier[] array, final Object o, final Object o2) {
        int length = array.length;
        while (--length >= 0) {
            array[length].copy(o, o2);
        }
    }
    
    public static synchronized <T> ClassFieldCopier<T> of(final Class<T> clazz) {
        ClassFieldCopier<?> generateOneAndCombine = ClassFieldCopier.cache.get(clazz);
        if (generateOneAndCombine == null) {
            generateOneAndCombine = generateOneAndCombine(clazz);
            ClassFieldCopier.cache.put(clazz, generateOneAndCombine);
        }
        return (ClassFieldCopier<T>)generateOneAndCombine;
    }
    
    private static ClassFieldCopier<?> generateOneAndCombine(final Class<?> clazz) {
        final ClassFieldCopier<?> generateOne = generateOne(clazz);
        ClassFieldCopier<?> classFieldCopier = ClassFieldCopier.NOOP_COPIER;
        final Class<?> superclass = clazz.getSuperclass();
        if (superclass != null && superclass != Object.class) {
            classFieldCopier = of(superclass);
        }
        if (classFieldCopier == ClassFieldCopier.NOOP_COPIER) {
            return generateOne;
        }
        if (generateOne == ClassFieldCopier.NOOP_COPIER) {
            return classFieldCopier;
        }
        return new MultiCopier(Stream.concat((Stream<?>)classFieldCopier.all(), (Stream<?>)generateOne.all()).toArray(ClassFieldCopier[]::new));
    }
    
    private static ClassFieldCopier<?> generateOne(final Class<?> clazz) {
        final Field[] declaredFields = clazz.getDeclaredFields();
        final ArrayList list = new ArrayList<ReflectionCopier>(declaredFields.length);
        final ArrayList list2 = new ArrayList<Field>(declaredFields.length);
        if (Resolver.isPublic(clazz)) {
            for (final Field e : declaredFields) {
                final int modifiers = e.getModifiers();
                if (!Modifier.isStatic(modifiers)) {
                    if (Modifier.isPublic(modifiers)) {
                        list2.add(e);
                        if (Modifier.isFinal(modifiers)) {
                            e.setAccessible(true);
                        }
                    }
                    else {
                        e.setAccessible(true);
                        list.add(ReflectionCopier.of(e));
                    }
                }
            }
        }
        else {
            for (final Field field : declaredFields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    list.add(ReflectionCopier.of(field));
                }
            }
        }
        if (list2.isEmpty()) {
            return list.isEmpty() ? ClassFieldCopier.NOOP_COPIER : new MultiFieldReflectionCopier((List<ReflectionCopier>)list);
        }
        final ExtendedClassWriter<Object> build = ExtendedClassWriter.builder((Class<? super Object>)ClassFieldCopier.class).setFlags(1).setAccess(16).build();
        final String internalName = MPLType.getInternalName(clazz);
        final String descriptor = MPLType.getDescriptor(clazz);
        if (!list.isEmpty()) {
            build.visitStaticField("copiers", ReflectionCopier[].class, list.toArray(new ReflectionCopier[list.size()]));
        }
        final MethodVisitor visitMethod = build.visitMethod(1, "<init>", "()V", null, null);
        visitMethod.visitCode();
        visitMethod.visitVarInsn(25, 0);
        visitMethod.visitMethodInsn(183, MPLType.getInternalName(ClassFieldCopier.class), "<init>", "()V", false);
        visitMethod.visitInsn(177);
        visitMethod.visitMaxs(1, 1);
        visitMethod.visitEnd();
        final MethodVisitor visitMethod2 = build.visitMethod(4, "tryCopy", "(" + descriptor + descriptor + ")V", null, new String[] { "java/lang/Throwable" });
        visitMethod2.visitCode();
        if (!list.isEmpty()) {
            final String descriptor2 = MPLType.getDescriptor(ReflectionCopier[].class);
            visitMethod2.visitVarInsn(25, 0);
            visitMethod2.visitFieldInsn(178, build.getInternalName(), "copiers", descriptor2);
            visitMethod2.visitVarInsn(25, 1);
            visitMethod2.visitVarInsn(25, 2);
            visitMethod2.visitMethodInsn(183, MPLType.getInternalName(ClassFieldCopier.class), "copyAllWithReflection", "(" + descriptor2 + "Ljava/lang/Object;Ljava/lang/Object;)V", false);
        }
        for (final Field field2 : list2) {
            final String name = MPLType.getName(field2);
            final String descriptor3 = MPLType.getDescriptor(field2.getType());
            if (Modifier.isFinal(field2.getModifiers())) {
                final String string = "field_" + name;
                build.visitStaticField(string, Field.class, field2);
                visitMethod2.visitFieldInsn(178, build.getInternalName(), string, "Ljava/lang/reflect/Field;");
                visitMethod2.visitVarInsn(25, 2);
                visitMethod2.visitVarInsn(25, 1);
                visitMethod2.visitFieldInsn(180, internalName, name, descriptor3);
                ReflectionCopier.callSetter(visitMethod2, field2.getType());
            }
            else {
                visitMethod2.visitVarInsn(25, 2);
                visitMethod2.visitVarInsn(25, 1);
                visitMethod2.visitFieldInsn(180, internalName, name, descriptor3);
                visitMethod2.visitFieldInsn(181, internalName, name, descriptor3);
            }
        }
        visitMethod2.visitInsn(177);
        visitMethod2.visitMaxs(0, 0);
        visitMethod2.visitEnd();
        final MethodVisitor visitMethod3 = build.visitMethod(4164, "tryCopy", "(Ljava/lang/Object;Ljava/lang/Object;)V", null, new String[] { "java/lang/Throwable" });
        visitMethod3.visitCode();
        visitMethod3.visitVarInsn(25, 0);
        visitMethod3.visitVarInsn(25, 1);
        visitMethod3.visitTypeInsn(192, internalName);
        visitMethod3.visitVarInsn(25, 2);
        visitMethod3.visitTypeInsn(192, internalName);
        visitMethod3.visitMethodInsn(182, build.getInternalName(), "tryCopy", "(" + descriptor + descriptor + ")V", false);
        visitMethod3.visitInsn(177);
        visitMethod3.visitMaxs(3, 3);
        visitMethod3.visitEnd();
        return build.generateInstance();
    }
    
    static {
        cache = new HashMap<Class<?>, ClassFieldCopier<?>>();
        NOOP_COPIER = new ClassFieldCopier<Object>() {
            @Override
            protected void tryCopy(final Object o, final Object o2) {
            }
            
            @Override
            protected Stream<ClassFieldCopier<?>> all() {
                return Stream.empty();
            }
        };
    }
    
    private static final class MultiCopier extends ClassFieldCopier<Object>
    {
        private final ClassFieldCopier<Object>[] copiers;
        
        public MultiCopier(final ClassFieldCopier<Object>[] copiers) {
            this.copiers = copiers;
        }
        
        @Override
        protected void tryCopy(final Object o, final Object o2) {
            final ClassFieldCopier<Object>[] copiers = this.copiers;
            for (int length = copiers.length, i = 0; i < length; ++i) {
                copiers[i].copy(o, o2);
            }
        }
        
        @Override
        protected Stream<ClassFieldCopier<?>> all() {
            return (Stream<ClassFieldCopier<?>>)Stream.of(this.copiers);
        }
    }
    
    private static final class MultiFieldReflectionCopier extends ClassFieldCopier<Object>
    {
        private final ReflectionCopier[] copiers;
        
        public MultiFieldReflectionCopier(final List<ReflectionCopier> list) {
            this.copiers = list.toArray(new ReflectionCopier[list.size()]);
        }
        
        @Override
        protected void tryCopy(final Object o, final Object o2) {
            this.copyAllWithReflection(this.copiers, o, o2);
        }
    }
    
    public abstract static class ReflectionCopier
    {
        private static Map<Class<?>, Function<Field, ReflectionCopier>> primitiveCopierLookup;
        private static Map<Class<?>, ReflectionSetterMethod> reflectionSetterMethods;
        private static final ReflectionSetterMethod defaultSetterMethod;
        private final Field field;
        
        private static void register(final Class<?> clazz, final String s, final Function<Field, ReflectionCopier> function) {
            ReflectionCopier.primitiveCopierLookup.put(clazz, function);
            ReflectionCopier.reflectionSetterMethods.put(clazz, new ReflectionSetterMethod(s, clazz));
        }
        
        public static ReflectionCopier of(final Field field) {
            final Class<?> type = field.getType();
            if (type.isPrimitive()) {
                return ReflectionCopier.primitiveCopierLookup.get(type).apply(field);
            }
            return new ReflectionCopier(field) {
                @Override
                protected void copyBase(final Field field, final Object obj, final Object obj2) {
                    field.set(obj2, field.get(obj));
                }
            };
        }
        
        protected static void callSetter(final MethodVisitor methodVisitor, final Class<?> key) {
            final ReflectionSetterMethod reflectionSetterMethod = ReflectionCopier.reflectionSetterMethods.getOrDefault(key, ReflectionCopier.defaultSetterMethod);
            methodVisitor.visitMethodInsn(182, "java/lang/reflect/Field", reflectionSetterMethod.name, reflectionSetterMethod.descriptor, false);
        }
        
        public ReflectionCopier(final Field field) {
            this.field = field;
        }
        
        public final void copy(final Object o, final Object o2) {
            this.copyBase(this.field, o, o2);
        }
        
        protected abstract void copyBase(final Field p0, final Object p1, final Object p2);
        
        static {
            ReflectionCopier.primitiveCopierLookup = new IdentityHashMap<Class<?>, Function<Field, ReflectionCopier>>();
            ReflectionCopier.reflectionSetterMethods = new IdentityHashMap<Class<?>, ReflectionSetterMethod>();
            defaultSetterMethod = new ReflectionSetterMethod("set", Object.class);
            register((Class<?>)Boolean.TYPE, "setBoolean", field -> new ReflectionCopier() {
                @Override
                protected void copyBase(final Field field, final Object obj, final Object obj2) {
                    field.setBoolean(obj2, field.getBoolean(obj));
                }
            });
            register((Class<?>)Byte.TYPE, "setByte", field3 -> new ReflectionCopier() {
                @Override
                protected void copyBase(final Field field, final Object obj, final Object obj2) {
                    field.setByte(obj2, field.getByte(obj));
                }
            });
            register((Class<?>)Character.TYPE, "setChar", field5 -> new ReflectionCopier() {
                @Override
                protected void copyBase(final Field field, final Object obj, final Object obj2) {
                    field.setChar(obj2, field.getChar(obj));
                }
            });
            register((Class<?>)Short.TYPE, "setShort", field7 -> new ReflectionCopier() {
                @Override
                protected void copyBase(final Field field, final Object obj, final Object obj2) {
                    field.setShort(obj2, field.getShort(obj));
                }
            });
            register((Class<?>)Integer.TYPE, "setInt", field9 -> new ReflectionCopier() {
                @Override
                protected void copyBase(final Field field, final Object obj, final Object obj2) {
                    field.setInt(obj2, field.getInt(obj));
                }
            });
            register((Class<?>)Long.TYPE, "setLong", field11 -> new ReflectionCopier() {
                @Override
                protected void copyBase(final Field field, final Object obj, final Object obj2) {
                    field.setLong(obj2, field.getLong(obj));
                }
            });
            register((Class<?>)Float.TYPE, "setFloat", field13 -> new ReflectionCopier() {
                @Override
                protected void copyBase(final Field field, final Object obj, final Object obj2) {
                    field.setFloat(obj2, field.getFloat(obj));
                }
            });
            register((Class<?>)Double.TYPE, "setDouble", field15 -> new ReflectionCopier() {
                @Override
                protected void copyBase(final Field field, final Object obj, final Object obj2) {
                    field.setDouble(obj2, field.getDouble(obj));
                }
            });
        }
        
        private static class ReflectionSetterMethod
        {
            public final String name;
            public final String descriptor;
            
            public ReflectionSetterMethod(final String name, final Class<?> clazz) {
                this.name = name;
                this.descriptor = "(Ljava/lang/Object;" + MPLType.getDescriptor(clazz) + ")V";
            }
        }
    }
}
