// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.tools.reflect;

import java.util.Iterator;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;
import dev.imanity.antixray.libs.javassist.bytecode.ClassFile;
import dev.imanity.antixray.libs.javassist.Modifier;
import dev.imanity.antixray.libs.javassist.CtNewMethod;
import dev.imanity.antixray.libs.javassist.CtField;
import dev.imanity.antixray.libs.javassist.bytecode.BadBytecode;
import dev.imanity.antixray.libs.javassist.NotFoundException;
import dev.imanity.antixray.libs.javassist.CodeConverter;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.javassist.CtClass;
import dev.imanity.antixray.libs.javassist.CtMethod;
import dev.imanity.antixray.libs.javassist.Translator;

public class Reflection implements Translator
{
    static final String classobjectField = "_classobject";
    static final String classobjectAccessor = "_getClass";
    static final String metaobjectField = "_metaobject";
    static final String metaobjectGetter = "_getMetaobject";
    static final String metaobjectSetter = "_setMetaobject";
    static final String readPrefix = "_r_";
    static final String writePrefix = "_w_";
    static final String metaobjectClassName = "dev.imanity.antixray.libs.javassist.tools.reflect.Metaobject";
    static final String classMetaobjectClassName = "dev.imanity.antixray.libs.javassist.tools.reflect.ClassMetaobject";
    protected CtMethod trapMethod;
    protected CtMethod trapStaticMethod;
    protected CtMethod trapRead;
    protected CtMethod trapWrite;
    protected CtClass[] readParam;
    protected ClassPool classPool;
    protected CodeConverter converter;
    
    private boolean isExcluded(final String s) {
        return s.startsWith("_m_") || s.equals("_getClass") || s.equals("_setMetaobject") || s.equals("_getMetaobject") || s.startsWith("_r_") || s.startsWith("_w_");
    }
    
    public Reflection() {
        this.classPool = null;
        this.converter = new CodeConverter();
    }
    
    @Override
    public void start(final ClassPool classPool) {
        this.classPool = classPool;
        try {
            final CtClass value = this.classPool.get("dev.imanity.antixray.libs.javassist.tools.reflect.Sample");
            this.rebuildClassFile(value.getClassFile());
            this.trapMethod = value.getDeclaredMethod("trap");
            this.trapStaticMethod = value.getDeclaredMethod("trapStatic");
            this.trapRead = value.getDeclaredMethod("trapRead");
            this.trapWrite = value.getDeclaredMethod("trapWrite");
            this.readParam = new CtClass[] { this.classPool.get("java.lang.Object") };
        }
        catch (final NotFoundException ex) {
            throw new RuntimeException("dev.imanity.antixray.libs.javassist.tools.reflect.Sample is not found or broken.");
        }
        catch (final BadBytecode badBytecode) {
            throw new RuntimeException("dev.imanity.antixray.libs.javassist.tools.reflect.Sample is not found or broken.");
        }
    }
    
    @Override
    public void onLoad(final ClassPool classPool, final String s) {
        classPool.get(s).instrument(this.converter);
    }
    
    public boolean makeReflective(final String s, final String s2, final String s3) {
        return this.makeReflective(this.classPool.get(s), this.classPool.get(s2), this.classPool.get(s3));
    }
    
    public boolean makeReflective(final Class<?> clazz, final Class<?> clazz2, final Class<?> clazz3) {
        return this.makeReflective(clazz.getName(), clazz2.getName(), clazz3.getName());
    }
    
    public boolean makeReflective(final CtClass ctClass, final CtClass ctClass2, final CtClass ctClass3) {
        if (ctClass.isInterface()) {
            throw new CannotReflectException("Cannot reflect an interface: " + ctClass.getName());
        }
        if (ctClass.subclassOf(this.classPool.get("dev.imanity.antixray.libs.javassist.tools.reflect.ClassMetaobject"))) {
            throw new CannotReflectException("Cannot reflect a subclass of ClassMetaobject: " + ctClass.getName());
        }
        if (ctClass.subclassOf(this.classPool.get("dev.imanity.antixray.libs.javassist.tools.reflect.Metaobject"))) {
            throw new CannotReflectException("Cannot reflect a subclass of Metaobject: " + ctClass.getName());
        }
        this.registerReflectiveClass(ctClass);
        return this.modifyClassfile(ctClass, ctClass2, ctClass3);
    }
    
    private void registerReflectiveClass(final CtClass ctClass) {
        final CtField[] declaredFields = ctClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; ++i) {
            final CtField ctField = declaredFields[i];
            final int modifiers = ctField.getModifiers();
            if ((modifiers & 0x1) != 0x0 && (modifiers & 0x10) == 0x0) {
                final String name = ctField.getName();
                this.converter.replaceFieldRead(ctField, ctClass, "_r_" + name);
                this.converter.replaceFieldWrite(ctField, ctClass, "_w_" + name);
            }
        }
    }
    
    private boolean modifyClassfile(final CtClass ctClass, final CtClass ctClass2, final CtClass ctClass3) {
        if (ctClass.getAttribute("Reflective") != null) {
            return false;
        }
        ctClass.setAttribute("Reflective", new byte[0]);
        final CtClass value = this.classPool.get("dev.imanity.antixray.libs.javassist.tools.reflect.Metalevel");
        final boolean b = !ctClass.subtypeOf(value);
        if (b) {
            ctClass.addInterface(value);
        }
        this.processMethods(ctClass, b);
        this.processFields(ctClass);
        if (b) {
            final CtField ctField = new CtField(this.classPool.get("dev.imanity.antixray.libs.javassist.tools.reflect.Metaobject"), "_metaobject", ctClass);
            ctField.setModifiers(4);
            ctClass.addField(ctField, CtField.Initializer.byNewWithParams(ctClass2));
            ctClass.addMethod(CtNewMethod.getter("_getMetaobject", ctField));
            ctClass.addMethod(CtNewMethod.setter("_setMetaobject", ctField));
        }
        final CtField ctField2 = new CtField(this.classPool.get("dev.imanity.antixray.libs.javassist.tools.reflect.ClassMetaobject"), "_classobject", ctClass);
        ctField2.setModifiers(10);
        ctClass.addField(ctField2, CtField.Initializer.byNew(ctClass3, new String[] { ctClass.getName() }));
        ctClass.addMethod(CtNewMethod.getter("_getClass", ctField2));
        return true;
    }
    
    private void processMethods(final CtClass ctClass, final boolean b) {
        final CtMethod[] methods = ctClass.getMethods();
        for (int i = 0; i < methods.length; ++i) {
            final CtMethod ctMethod = methods[i];
            final int modifiers = ctMethod.getModifiers();
            if (Modifier.isPublic(modifiers) && !Modifier.isAbstract(modifiers)) {
                this.processMethods0(modifiers, ctClass, ctMethod, i, b);
            }
        }
    }
    
    private void processMethods0(int modifiers, final CtClass ctClass, final CtMethod ctMethod, final int i, final boolean b) {
        final String name = ctMethod.getName();
        if (this.isExcluded(name)) {
            return;
        }
        CtMethod delegator;
        if (ctMethod.getDeclaringClass() == ctClass) {
            if (Modifier.isNative(modifiers)) {
                return;
            }
            delegator = ctMethod;
            if (Modifier.isFinal(modifiers)) {
                modifiers &= 0xFFFFFFEF;
                delegator.setModifiers(modifiers);
            }
        }
        else {
            if (Modifier.isFinal(modifiers)) {
                return;
            }
            modifiers &= 0xFFFFFEFF;
            delegator = CtNewMethod.delegator(this.findOriginal(ctMethod, b), ctClass);
            delegator.setModifiers(modifiers);
            ctClass.addMethod(delegator);
        }
        delegator.setName("_m_" + i + "_" + name);
        CtMethod ctMethod2;
        if (Modifier.isStatic(modifiers)) {
            ctMethod2 = this.trapStaticMethod;
        }
        else {
            ctMethod2 = this.trapMethod;
        }
        final CtMethod wrapped = CtNewMethod.wrapped(ctMethod.getReturnType(), name, ctMethod.getParameterTypes(), ctMethod.getExceptionTypes(), ctMethod2, CtMethod.ConstParameter.integer(i), ctClass);
        wrapped.setModifiers(modifiers);
        ctClass.addMethod(wrapped);
    }
    
    private CtMethod findOriginal(final CtMethod ctMethod, final boolean b) {
        if (b) {
            return ctMethod;
        }
        final String name = ctMethod.getName();
        final CtMethod[] declaredMethods = ctMethod.getDeclaringClass().getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; ++i) {
            final String name2 = declaredMethods[i].getName();
            if (name2.endsWith(name) && name2.startsWith("_m_") && declaredMethods[i].getSignature().equals(ctMethod.getSignature())) {
                return declaredMethods[i];
            }
        }
        return ctMethod;
    }
    
    private void processFields(final CtClass ctClass) {
        final CtField[] declaredFields = ctClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; ++i) {
            final CtField ctField = declaredFields[i];
            final int modifiers = ctField.getModifiers();
            if ((modifiers & 0x1) != 0x0 && (modifiers & 0x10) == 0x0) {
                final int n = modifiers | 0x8;
                final String name = ctField.getName();
                final CtClass type = ctField.getType();
                final CtMethod wrapped = CtNewMethod.wrapped(type, "_r_" + name, this.readParam, null, this.trapRead, CtMethod.ConstParameter.string(name), ctClass);
                wrapped.setModifiers(n);
                ctClass.addMethod(wrapped);
                final CtMethod wrapped2 = CtNewMethod.wrapped(CtClass.voidType, "_w_" + name, new CtClass[] { this.classPool.get("java.lang.Object"), type }, null, this.trapWrite, CtMethod.ConstParameter.string(name), ctClass);
                wrapped2.setModifiers(n);
                ctClass.addMethod(wrapped2);
            }
        }
    }
    
    public void rebuildClassFile(final ClassFile classFile) {
        if (ClassFile.MAJOR_VERSION < 50) {
            return;
        }
        final Iterator<MethodInfo> iterator = classFile.getMethods().iterator();
        while (iterator.hasNext()) {
            iterator.next().rebuildStackMap(this.classPool);
        }
    }
}
