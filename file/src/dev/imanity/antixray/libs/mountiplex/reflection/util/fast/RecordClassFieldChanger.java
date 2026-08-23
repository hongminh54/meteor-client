// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.fast;

import dev.imanity.antixray.libs.asm.MethodVisitor;
import java.util.Iterator;
import java.lang.reflect.Field;
import dev.imanity.antixray.libs.mountiplex.reflection.util.ExtendedClassWriter;
import dev.imanity.antixray.libs.asm.Type;
import java.lang.reflect.Modifier;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import dev.imanity.antixray.libs.mountiplex.reflection.util.IgnoresRemapping;

public abstract class RecordClassFieldChanger<T> implements IgnoresRemapping, GeneratedExactSignatureInvoker<T>
{
    public static <T> RecordClassFieldChanger<T> create(final Class<?> obj, final String s, final List<String> list) {
        List list2 = null;
        try {
            final Class<?> forName = Class.forName("java.lang.reflect.RecordComponent");
            final Object[] array = (Object[])obj.getMethod("getRecordComponents", (Class[])new Class[0]).invoke(obj, new Object[0]);
            if (array != null) {
                final Method method = forName.getMethod("getAccessor", (Class[])new Class[0]);
                final Method method2 = forName.getMethod("getType", (Class[])new Class[0]);
                list2 = new ArrayList(array.length);
                for (final Object o : array) {
                    list2.add(new RecordComponent(MPLType.getName((Method)method.invoke(o, new Object[0])), (Class<?>)method2.invoke(o, new Object[0])));
                }
            }
        }
        catch (final Throwable t) {}
        if (list2 == null) {
            list2 = new ArrayList();
            for (final Field field : obj.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    list2.add(new RecordComponent(field.getName(), field.getType()));
                }
            }
        }
        final ArrayList list3 = new ArrayList(list.size());
        int argPosition = 1;
        for (final String s2 : list) {
            boolean b = false;
            for (final RecordComponent recordComponent : list2) {
                if (recordComponent.name.equals(s2)) {
                    recordComponent.changed = true;
                    recordComponent.argPosition = argPosition;
                    argPosition += Type.getType(recordComponent.type).getSize();
                    b = true;
                    list3.add(recordComponent);
                    break;
                }
            }
            if (!b) {
                throw new IllegalArgumentException("Record class " + obj.getName() + " does not contain record with name '" + s2 + "'!");
            }
        }
        final ExtendedClassWriter<Object> build = ExtendedClassWriter.builder((Class<? super Object>)RecordClassFieldChanger.class).setFlags(1).setSingleton(true).build();
        final String internalName = MPLType.getInternalName(obj);
        final StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(MPLType.getDescriptor(obj));
        final Iterator iterator3 = list3.iterator();
        while (iterator3.hasNext()) {
            sb.append(MPLType.getDescriptor(((RecordComponent)iterator3.next()).type));
        }
        sb.append(')');
        sb.append(MPLType.getDescriptor(obj));
        final String string = sb.toString();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append('(');
        final Iterator iterator4 = list2.iterator();
        while (iterator4.hasNext()) {
            sb2.append(MPLType.getDescriptor(((RecordComponent)iterator4.next()).type));
        }
        sb2.append(")V");
        final MethodVisitor visitMethod = build.visitMethod(9, s, string, null, null);
        visitMethod.visitCode();
        visitMethod.visitTypeInsn(187, internalName);
        visitMethod.visitInsn(89);
        for (final RecordComponent recordComponent2 : list2) {
            if (recordComponent2.changed) {
                visitMethod.visitVarInsn(Type.getType(recordComponent2.type).getOpcode(21), recordComponent2.argPosition);
            }
            else {
                visitMethod.visitVarInsn(25, 0);
                visitMethod.visitMethodInsn(182, internalName, recordComponent2.name, "()" + MPLType.getDescriptor(recordComponent2.type), false);
            }
        }
        visitMethod.visitMethodInsn(183, internalName, "<init>", sb2.toString(), false);
        visitMethod.visitInsn(176);
        visitMethod.visitMaxs(0, 0);
        visitMethod.visitEnd();
        if (list3.size() <= 5) {
            final MethodVisitor visitMethod2 = build.visitMethod(1, "invoke", GeneratedInvoker.buildInvokeDescriptor(list3.size()), null, null);
            visitMethod2.visitCode();
            visitMethod2.visitVarInsn(25, 1);
            visitMethod2.visitTypeInsn(192, internalName);
            int n = 2;
            for (final RecordComponent recordComponent3 : list3) {
                visitMethod2.visitVarInsn(25, n++);
                ExtendedClassWriter.visitUnboxObjectVariable(visitMethod2, recordComponent3.type);
            }
            visitMethod2.visitMethodInsn(184, build.getInternalName(), s, string, false);
            visitMethod2.visitInsn(176);
            visitMethod2.visitMaxs(0, 0);
            visitMethod2.visitEnd();
        }
        final MethodVisitor visitMethod3 = build.visitMethod(129, "invokeVA", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", null, null);
        visitMethod3.visitCode();
        GeneratedInvoker.visitInvokeVAArgCountCheck(visitMethod3, list3.size());
        visitMethod3.visitVarInsn(25, 1);
        visitMethod3.visitTypeInsn(192, internalName);
        int n2 = 0;
        for (final RecordComponent recordComponent4 : list3) {
            visitMethod3.visitVarInsn(25, 2);
            ExtendedClassWriter.visitPushInt(visitMethod3, n2++);
            visitMethod3.visitInsn(50);
            ExtendedClassWriter.visitUnboxObjectVariable(visitMethod3, recordComponent4.type);
        }
        visitMethod3.visitMethodInsn(184, build.getInternalName(), s, string, false);
        visitMethod3.visitInsn(176);
        visitMethod3.visitMaxs(0, 0);
        visitMethod3.visitEnd();
        return build.generateInstance();
    }
    
    @Override
    public String getInvokerClassInternalName() {
        return MPLType.getInternalName(this.getClass());
    }
    
    @Override
    public String getInvokerClassTypeDescriptor() {
        return MPLType.getDescriptor(this.getClass());
    }
    
    private static final class RecordComponent
    {
        public final String name;
        public final Class<?> type;
        public boolean changed;
        public int argPosition;
        
        public RecordComponent(final String name, final Class<?> type) {
            this.changed = false;
            this.argPosition = -1;
            this.name = name;
            this.type = type;
        }
    }
}
