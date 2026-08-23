// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist;

import java.util.Set;
import java.util.Hashtable;
import dev.imanity.antixray.libs.javassist.compiler.CompileError;
import dev.imanity.antixray.libs.javassist.bytecode.CodeIterator;
import dev.imanity.antixray.libs.javassist.bytecode.CodeAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.BadBytecode;
import dev.imanity.antixray.libs.javassist.compiler.Javac;
import dev.imanity.antixray.libs.javassist.expr.ExprEditor;
import dev.imanity.antixray.libs.javassist.bytecode.Bytecode;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;
import dev.imanity.antixray.libs.javassist.bytecode.ConstantAttribute;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import dev.imanity.antixray.libs.javassist.bytecode.FieldInfo;
import java.lang.ref.WeakReference;
import dev.imanity.antixray.libs.javassist.bytecode.EnclosingMethodAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.AnnotationImpl;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;
import dev.imanity.antixray.libs.javassist.bytecode.ParameterAnnotationsAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.annotation.Annotation;
import dev.imanity.antixray.libs.javassist.bytecode.AnnotationsAttribute;
import java.util.ArrayList;
import dev.imanity.antixray.libs.javassist.bytecode.InnerClassesAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.AccessFlag;
import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import dev.imanity.antixray.libs.javassist.bytecode.AttributeInfo;
import dev.imanity.antixray.libs.javassist.bytecode.SignatureAttribute;
import java.net.URL;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Map;
import dev.imanity.antixray.libs.javassist.compiler.AccessorMaker;
import java.lang.ref.Reference;
import dev.imanity.antixray.libs.javassist.bytecode.ClassFile;

class CtClassType extends CtClass
{
    ClassPool classPool;
    boolean wasChanged;
    private boolean wasFrozen;
    boolean wasPruned;
    boolean gcConstPool;
    ClassFile classfile;
    byte[] rawClassfile;
    private Reference<CtMember.Cache> memberCache;
    private AccessorMaker accessors;
    private FieldInitLink fieldInitializers;
    private Map<CtMethod, String> hiddenMethods;
    private int uniqueNumberSeed;
    private boolean doPruning;
    private int getCount;
    private static final int GET_THRESHOLD = 2;
    
    CtClassType(final String s, final ClassPool classPool) {
        super(s);
        this.doPruning = ClassPool.doPruning;
        this.classPool = classPool;
        final boolean b = false;
        this.gcConstPool = b;
        this.wasPruned = b;
        this.wasFrozen = b;
        this.wasChanged = b;
        this.classfile = null;
        this.rawClassfile = null;
        this.memberCache = null;
        this.accessors = null;
        this.fieldInitializers = null;
        this.hiddenMethods = null;
        this.uniqueNumberSeed = 0;
        this.getCount = 0;
    }
    
    CtClassType(final InputStream in, final ClassPool classPool) {
        this((String)null, classPool);
        this.classfile = new ClassFile(new DataInputStream(in));
        this.qualifiedName = this.classfile.getName();
    }
    
    CtClassType(final ClassFile classfile, final ClassPool classPool) {
        this((String)null, classPool);
        this.classfile = classfile;
        this.qualifiedName = this.classfile.getName();
    }
    
    @Override
    protected void extendToString(final StringBuilder sb) {
        if (this.wasChanged) {
            sb.append("changed ");
        }
        if (this.wasFrozen) {
            sb.append("frozen ");
        }
        if (this.wasPruned) {
            sb.append("pruned ");
        }
        sb.append(Modifier.toString(this.getModifiers()));
        sb.append(" class ");
        sb.append(this.getName());
        try {
            final CtClass superclass = this.getSuperclass();
            if (superclass != null && !superclass.getName().equals("java.lang.Object")) {
                sb.append(" extends ").append(superclass.getName());
            }
        }
        catch (final NotFoundException ex) {
            sb.append(" extends ??");
        }
        try {
            final CtClass[] interfaces = this.getInterfaces();
            if (interfaces.length > 0) {
                sb.append(" implements ");
            }
            for (int i = 0; i < interfaces.length; ++i) {
                sb.append(interfaces[i].getName());
                sb.append(", ");
            }
        }
        catch (final NotFoundException ex2) {
            sb.append(" extends ??");
        }
        final CtMember.Cache members = this.getMembers();
        this.exToString(sb, " fields=", members.fieldHead(), members.lastField());
        this.exToString(sb, " constructors=", members.consHead(), members.lastCons());
        this.exToString(sb, " methods=", members.methodHead(), members.lastMethod());
    }
    
    private void exToString(final StringBuilder sb, final String str, CtMember next, final CtMember ctMember) {
        sb.append(str);
        while (next != ctMember) {
            next = next.next();
            sb.append(next);
            sb.append(", ");
        }
    }
    
    @Override
    public AccessorMaker getAccessorMaker() {
        if (this.accessors == null) {
            this.accessors = new AccessorMaker(this);
        }
        return this.accessors;
    }
    
    @Override
    public ClassFile getClassFile2() {
        return this.getClassFile3(true);
    }
    
    public ClassFile getClassFile3(final boolean b) {
        final ClassFile classfile = this.classfile;
        if (classfile != null) {
            return classfile;
        }
        if (b) {
            this.classPool.compress();
        }
        final byte[] rawClassfile;
        synchronized (this) {
            final ClassFile classfile2 = this.classfile;
            if (classfile2 != null) {
                return classfile2;
            }
            rawClassfile = this.rawClassfile;
        }
        if (rawClassfile != null) {
            ClassFile classFile;
            try {
                classFile = new ClassFile(new DataInputStream(new ByteArrayInputStream(rawClassfile)));
            }
            catch (final IOException cause) {
                throw new RuntimeException(cause.toString(), cause);
            }
            this.getCount = 2;
            synchronized (this) {
                this.rawClassfile = null;
                return this.setClassFile(classFile);
            }
        }
        InputStream openClassfile = null;
        try {
            openClassfile = this.classPool.openClassfile(this.getName());
            if (openClassfile == null) {
                throw new NotFoundException(this.getName());
            }
            openClassfile = new BufferedInputStream(openClassfile);
            final ClassFile classFile2 = new ClassFile(new DataInputStream(openClassfile));
            if (!classFile2.getName().equals(this.qualifiedName)) {
                throw new RuntimeException("cannot find " + this.qualifiedName + ": " + classFile2.getName() + " found in " + this.qualifiedName.replace('.', '/') + ".class");
            }
            return this.setClassFile(classFile2);
        }
        catch (final NotFoundException cause2) {
            throw new RuntimeException(cause2.toString(), cause2);
        }
        catch (final IOException cause3) {
            throw new RuntimeException(cause3.toString(), cause3);
        }
        finally {
            if (openClassfile != null) {
                try {
                    openClassfile.close();
                }
                catch (final IOException ex) {}
            }
        }
    }
    
    @Override
    final void incGetCounter() {
        ++this.getCount;
    }
    
    @Override
    void compress() {
        if (this.getCount < 2) {
            if (!this.isModified() && ClassPool.releaseUnmodifiedClassFile) {
                this.removeClassFile();
            }
            else if (this.isFrozen() && !this.wasPruned) {
                this.saveClassFile();
            }
        }
        this.getCount = 0;
    }
    
    private synchronized void saveClassFile() {
        if (this.classfile == null || this.hasMemberCache() != null) {
            return;
        }
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(out);
        try {
            this.classfile.write(dataOutputStream);
            out.close();
            this.rawClassfile = out.toByteArray();
            this.classfile = null;
        }
        catch (final IOException ex) {}
    }
    
    private synchronized void removeClassFile() {
        if (this.classfile != null && !this.isModified() && this.hasMemberCache() == null) {
            this.classfile = null;
        }
    }
    
    private synchronized ClassFile setClassFile(final ClassFile classfile) {
        if (this.classfile == null) {
            this.classfile = classfile;
        }
        return this.classfile;
    }
    
    @Override
    public ClassPool getClassPool() {
        return this.classPool;
    }
    
    void setClassPool(final ClassPool classPool) {
        this.classPool = classPool;
    }
    
    @Override
    public URL getURL() {
        final URL find = this.classPool.find(this.getName());
        if (find == null) {
            throw new NotFoundException(this.getName());
        }
        return find;
    }
    
    @Override
    public boolean isModified() {
        return this.wasChanged;
    }
    
    @Override
    public boolean isFrozen() {
        return this.wasFrozen;
    }
    
    @Override
    public void freeze() {
        this.wasFrozen = true;
    }
    
    @Override
    void checkModify() {
        if (this.isFrozen()) {
            String s = this.getName() + " class is frozen";
            if (this.wasPruned) {
                s += " and pruned";
            }
            throw new RuntimeException(s);
        }
        this.wasChanged = true;
    }
    
    @Override
    public void defrost() {
        this.checkPruned("defrost");
        this.wasFrozen = false;
    }
    
    @Override
    public boolean subtypeOf(final CtClass ctClass) {
        final String name = ctClass.getName();
        if (this == ctClass || this.getName().equals(name)) {
            return true;
        }
        final ClassFile classFile2 = this.getClassFile2();
        final String superclass = classFile2.getSuperclass();
        if (superclass != null && superclass.equals(name)) {
            return true;
        }
        final String[] interfaces = classFile2.getInterfaces();
        final int length = interfaces.length;
        for (int i = 0; i < length; ++i) {
            if (interfaces[i].equals(name)) {
                return true;
            }
        }
        if (superclass != null && this.classPool.get(superclass).subtypeOf(ctClass)) {
            return true;
        }
        for (int j = 0; j < length; ++j) {
            if (this.classPool.get(interfaces[j]).subtypeOf(ctClass)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void setName(final String s) {
        final String name = this.getName();
        if (s.equals(name)) {
            return;
        }
        this.classPool.checkNotFrozen(s);
        final ClassFile classFile2 = this.getClassFile2();
        super.setName(s);
        classFile2.setName(s);
        this.nameReplaced();
        this.classPool.classNameChanged(name, this);
    }
    
    @Override
    public String getGenericSignature() {
        final SignatureAttribute signatureAttribute = (SignatureAttribute)this.getClassFile2().getAttribute("Signature");
        return (signatureAttribute == null) ? null : signatureAttribute.getSignature();
    }
    
    @Override
    public void setGenericSignature(final String s) {
        final ClassFile classFile = this.getClassFile();
        classFile.addAttribute(new SignatureAttribute(classFile.getConstPool(), s));
    }
    
    @Override
    public void replaceClassName(final ClassMap classMap) {
        final String name = this.getName();
        String name2 = classMap.get(Descriptor.toJvmName(name));
        if (name2 != null) {
            name2 = Descriptor.toJavaName(name2);
            this.classPool.checkNotFrozen(name2);
        }
        super.replaceClassName(classMap);
        this.getClassFile2().renameClass(classMap);
        this.nameReplaced();
        if (name2 != null) {
            super.setName(name2);
            this.classPool.classNameChanged(name, this);
        }
    }
    
    @Override
    public void replaceClassName(final String anObject, final String name) {
        if (this.getName().equals(anObject)) {
            this.setName(name);
        }
        else {
            super.replaceClassName(anObject, name);
            this.getClassFile2().renameClass(anObject, name);
            this.nameReplaced();
        }
    }
    
    @Override
    public boolean isInterface() {
        return Modifier.isInterface(this.getModifiers());
    }
    
    @Override
    public boolean isAnnotation() {
        return Modifier.isAnnotation(this.getModifiers());
    }
    
    @Override
    public boolean isEnum() {
        return Modifier.isEnum(this.getModifiers());
    }
    
    @Override
    public int getModifiers() {
        final ClassFile classFile2 = this.getClassFile2();
        int clear = AccessFlag.clear(classFile2.getAccessFlags(), 32);
        final int innerAccessFlags = classFile2.getInnerAccessFlags();
        if (innerAccessFlags != -1) {
            if ((innerAccessFlags & 0x8) != 0x0) {
                clear |= 0x8;
            }
            if ((innerAccessFlags & 0x1) != 0x0) {
                clear |= 0x1;
            }
            else {
                clear &= 0xFFFFFFFE;
                if ((innerAccessFlags & 0x4) != 0x0) {
                    clear |= 0x4;
                }
                else if ((innerAccessFlags & 0x2) != 0x0) {
                    clear |= 0x2;
                }
            }
        }
        return AccessFlag.toModifier(clear);
    }
    
    @Override
    public CtClass[] getNestedClasses() {
        final ClassFile classFile2 = this.getClassFile2();
        final InnerClassesAttribute innerClassesAttribute = (InnerClassesAttribute)classFile2.getAttribute("InnerClasses");
        if (innerClassesAttribute == null) {
            return new CtClass[0];
        }
        final String string = classFile2.getName() + "$";
        final int tableLength = innerClassesAttribute.tableLength();
        final ArrayList list = new ArrayList(tableLength);
        for (int i = 0; i < tableLength; ++i) {
            final String innerClass = innerClassesAttribute.innerClass(i);
            if (innerClass != null && innerClass.startsWith(string) && innerClass.lastIndexOf(36) < string.length()) {
                list.add((Object)this.classPool.get(innerClass));
            }
        }
        return (CtClass[])list.toArray((Object[])new CtClass[list.size()]);
    }
    
    @Override
    public void setModifiers(final int n) {
        this.checkModify();
        updateInnerEntry(n, this.getName(), this, true);
        this.getClassFile2().setAccessFlags(AccessFlag.of(n & 0xFFFFFFF7));
    }
    
    private static void updateInnerEntry(final int n, final String s, final CtClass ctClass, final boolean b) {
        final InnerClassesAttribute innerClassesAttribute = (InnerClassesAttribute)ctClass.getClassFile2().getAttribute("InnerClasses");
        if (innerClassesAttribute != null) {
            final int n2 = n & 0xFFFFFFF7;
            final int find = innerClassesAttribute.find(s);
            if (find >= 0) {
                final int n3 = innerClassesAttribute.accessFlags(find) & 0x8;
                if (n3 != 0 || !Modifier.isStatic(n)) {
                    ctClass.checkModify();
                    innerClassesAttribute.setAccessFlags(find, AccessFlag.of(n2) | n3);
                    final String outerClass = innerClassesAttribute.outerClass(find);
                    if (outerClass != null && b) {
                        try {
                            updateInnerEntry(n2, s, ctClass.getClassPool().get(outerClass), false);
                        }
                        catch (final NotFoundException ex) {
                            throw new RuntimeException("cannot find the declaring class: " + outerClass);
                        }
                    }
                    return;
                }
            }
        }
        if (Modifier.isStatic(n)) {
            throw new RuntimeException("cannot change " + Descriptor.toJavaName(s) + " into a static class");
        }
    }
    
    @Override
    public boolean hasAnnotation(final String s) {
        final ClassFile classFile2 = this.getClassFile2();
        return hasAnnotationType(s, this.getClassPool(), (AnnotationsAttribute)classFile2.getAttribute("RuntimeInvisibleAnnotations"), (AnnotationsAttribute)classFile2.getAttribute("RuntimeVisibleAnnotations"));
    }
    
    @Deprecated
    static boolean hasAnnotationType(final Class<?> clazz, final ClassPool classPool, final AnnotationsAttribute annotationsAttribute, final AnnotationsAttribute annotationsAttribute2) {
        return hasAnnotationType(clazz.getName(), classPool, annotationsAttribute, annotationsAttribute2);
    }
    
    static boolean hasAnnotationType(final String s, final ClassPool classPool, final AnnotationsAttribute annotationsAttribute, final AnnotationsAttribute annotationsAttribute2) {
        Annotation[] annotations;
        if (annotationsAttribute == null) {
            annotations = null;
        }
        else {
            annotations = annotationsAttribute.getAnnotations();
        }
        Annotation[] annotations2;
        if (annotationsAttribute2 == null) {
            annotations2 = null;
        }
        else {
            annotations2 = annotationsAttribute2.getAnnotations();
        }
        if (annotations != null) {
            for (int i = 0; i < annotations.length; ++i) {
                if (annotations[i].getTypeName().equals(s)) {
                    return true;
                }
            }
        }
        if (annotations2 != null) {
            for (int j = 0; j < annotations2.length; ++j) {
                if (annotations2[j].getTypeName().equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public Object getAnnotation(final Class<?> clazz) {
        final ClassFile classFile2 = this.getClassFile2();
        return getAnnotationType(clazz, this.getClassPool(), (AnnotationsAttribute)classFile2.getAttribute("RuntimeInvisibleAnnotations"), (AnnotationsAttribute)classFile2.getAttribute("RuntimeVisibleAnnotations"));
    }
    
    static Object getAnnotationType(final Class<?> clazz, final ClassPool classPool, final AnnotationsAttribute annotationsAttribute, final AnnotationsAttribute annotationsAttribute2) {
        Annotation[] annotations;
        if (annotationsAttribute == null) {
            annotations = null;
        }
        else {
            annotations = annotationsAttribute.getAnnotations();
        }
        Annotation[] annotations2;
        if (annotationsAttribute2 == null) {
            annotations2 = null;
        }
        else {
            annotations2 = annotationsAttribute2.getAnnotations();
        }
        final String name = clazz.getName();
        if (annotations != null) {
            for (int i = 0; i < annotations.length; ++i) {
                if (annotations[i].getTypeName().equals(name)) {
                    return toAnnoType(annotations[i], classPool);
                }
            }
        }
        if (annotations2 != null) {
            for (int j = 0; j < annotations2.length; ++j) {
                if (annotations2[j].getTypeName().equals(name)) {
                    return toAnnoType(annotations2[j], classPool);
                }
            }
        }
        return null;
    }
    
    @Override
    public Object[] getAnnotations() {
        return this.getAnnotations(false);
    }
    
    @Override
    public Object[] getAvailableAnnotations() {
        try {
            return this.getAnnotations(true);
        }
        catch (final ClassNotFoundException cause) {
            throw new RuntimeException("Unexpected exception ", cause);
        }
    }
    
    private Object[] getAnnotations(final boolean b) {
        final ClassFile classFile2 = this.getClassFile2();
        return toAnnotationType(b, this.getClassPool(), (AnnotationsAttribute)classFile2.getAttribute("RuntimeInvisibleAnnotations"), (AnnotationsAttribute)classFile2.getAttribute("RuntimeVisibleAnnotations"));
    }
    
    static Object[] toAnnotationType(final boolean b, final ClassPool classPool, final AnnotationsAttribute annotationsAttribute, final AnnotationsAttribute annotationsAttribute2) {
        Annotation[] annotations;
        int length;
        if (annotationsAttribute == null) {
            annotations = null;
            length = 0;
        }
        else {
            annotations = annotationsAttribute.getAnnotations();
            length = annotations.length;
        }
        Annotation[] annotations2;
        int length2;
        if (annotationsAttribute2 == null) {
            annotations2 = null;
            length2 = 0;
        }
        else {
            annotations2 = annotationsAttribute2.getAnnotations();
            length2 = annotations2.length;
        }
        if (!b) {
            final Object[] array = new Object[length + length2];
            for (int i = 0; i < length; ++i) {
                array[i] = toAnnoType(annotations[i], classPool);
            }
            for (int j = 0; j < length2; ++j) {
                array[j + length] = toAnnoType(annotations2[j], classPool);
            }
            return array;
        }
        final ArrayList list = new ArrayList();
        for (int k = 0; k < length; ++k) {
            try {
                list.add(toAnnoType(annotations[k], classPool));
            }
            catch (final ClassNotFoundException ex) {}
        }
        for (int l = 0; l < length2; ++l) {
            try {
                list.add(toAnnoType(annotations2[l], classPool));
            }
            catch (final ClassNotFoundException ex2) {}
        }
        return list.toArray();
    }
    
    static Object[][] toAnnotationType(final boolean b, final ClassPool classPool, final ParameterAnnotationsAttribute parameterAnnotationsAttribute, final ParameterAnnotationsAttribute parameterAnnotationsAttribute2, final MethodInfo methodInfo) {
        int n;
        if (parameterAnnotationsAttribute != null) {
            n = parameterAnnotationsAttribute.numParameters();
        }
        else if (parameterAnnotationsAttribute2 != null) {
            n = parameterAnnotationsAttribute2.numParameters();
        }
        else {
            n = Descriptor.numOfParameters(methodInfo.getDescriptor());
        }
        final Object[][] array = new Object[n][];
        for (int i = 0; i < n; ++i) {
            Annotation[] array2;
            int length;
            if (parameterAnnotationsAttribute == null) {
                array2 = null;
                length = 0;
            }
            else {
                array2 = parameterAnnotationsAttribute.getAnnotations()[i];
                length = array2.length;
            }
            Annotation[] array3;
            int length2;
            if (parameterAnnotationsAttribute2 == null) {
                array3 = null;
                length2 = 0;
            }
            else {
                array3 = parameterAnnotationsAttribute2.getAnnotations()[i];
                length2 = array3.length;
            }
            if (!b) {
                array[i] = new Object[length + length2];
                for (int j = 0; j < length; ++j) {
                    array[i][j] = toAnnoType(array2[j], classPool);
                }
                for (int k = 0; k < length2; ++k) {
                    array[i][k + length] = toAnnoType(array3[k], classPool);
                }
            }
            else {
                final ArrayList list = new ArrayList();
                for (int l = 0; l < length; ++l) {
                    try {
                        list.add(toAnnoType(array2[l], classPool));
                    }
                    catch (final ClassNotFoundException ex) {}
                }
                for (int n2 = 0; n2 < length2; ++n2) {
                    try {
                        list.add(toAnnoType(array3[n2], classPool));
                    }
                    catch (final ClassNotFoundException ex2) {}
                }
                array[i] = list.toArray();
            }
        }
        return array;
    }
    
    private static Object toAnnoType(final Annotation annotation, final ClassPool classPool) {
        try {
            return annotation.toAnnotationType(classPool.getClassLoader(), classPool);
        }
        catch (final ClassNotFoundException ex) {
            final ClassLoader classLoader = classPool.getClass().getClassLoader();
            try {
                return annotation.toAnnotationType(classLoader, classPool);
            }
            catch (final ClassNotFoundException ex2) {
                try {
                    final Class<?> class1 = classPool.get(annotation.getTypeName()).toClass();
                    return AnnotationImpl.make(class1.getClassLoader(), class1, classPool, annotation);
                }
                catch (final Throwable t) {
                    throw new ClassNotFoundException(annotation.getTypeName());
                }
            }
        }
    }
    
    @Override
    public boolean subclassOf(final CtClass ctClass) {
        if (ctClass == null) {
            return false;
        }
        final String name = ctClass.getName();
        CtClass superclass = this;
        try {
            while (superclass != null) {
                if (superclass.getName().equals(name)) {
                    return true;
                }
                superclass = superclass.getSuperclass();
            }
        }
        catch (final Exception ex) {}
        return false;
    }
    
    @Override
    public CtClass getSuperclass() {
        final String superclass = this.getClassFile2().getSuperclass();
        if (superclass == null) {
            return null;
        }
        return this.classPool.get(superclass);
    }
    
    @Override
    public void setSuperclass(final CtClass ctClass) {
        this.checkModify();
        if (this.isInterface()) {
            this.addInterface(ctClass);
        }
        else {
            this.getClassFile2().setSuperclass(ctClass.getName());
        }
    }
    
    @Override
    public CtClass[] getInterfaces() {
        final String[] interfaces = this.getClassFile2().getInterfaces();
        final int length = interfaces.length;
        final CtClass[] array = new CtClass[length];
        for (int i = 0; i < length; ++i) {
            array[i] = this.classPool.get(interfaces[i]);
        }
        return array;
    }
    
    @Override
    public void setInterfaces(final CtClass[] array) {
        this.checkModify();
        String[] interfaces;
        if (array == null) {
            interfaces = new String[0];
        }
        else {
            final int length = array.length;
            interfaces = new String[length];
            for (int i = 0; i < length; ++i) {
                interfaces[i] = array[i].getName();
            }
        }
        this.getClassFile2().setInterfaces(interfaces);
    }
    
    @Override
    public void addInterface(final CtClass ctClass) {
        this.checkModify();
        if (ctClass != null) {
            this.getClassFile2().addInterface(ctClass.getName());
        }
    }
    
    @Override
    public CtClass getDeclaringClass() {
        final ClassFile classFile2 = this.getClassFile2();
        final InnerClassesAttribute innerClassesAttribute = (InnerClassesAttribute)classFile2.getAttribute("InnerClasses");
        if (innerClassesAttribute == null) {
            return null;
        }
        final String name = this.getName();
        for (int tableLength = innerClassesAttribute.tableLength(), i = 0; i < tableLength; ++i) {
            if (name.equals(innerClassesAttribute.innerClass(i))) {
                final String outerClass = innerClassesAttribute.outerClass(i);
                if (outerClass != null) {
                    return this.classPool.get(outerClass);
                }
                final EnclosingMethodAttribute enclosingMethodAttribute = (EnclosingMethodAttribute)classFile2.getAttribute("EnclosingMethod");
                if (enclosingMethodAttribute != null) {
                    return this.classPool.get(enclosingMethodAttribute.className());
                }
            }
        }
        return null;
    }
    
    @Override
    public CtBehavior getEnclosingBehavior() {
        final EnclosingMethodAttribute enclosingMethodAttribute = (EnclosingMethodAttribute)this.getClassFile2().getAttribute("EnclosingMethod");
        if (enclosingMethodAttribute == null) {
            return null;
        }
        final CtClass value = this.classPool.get(enclosingMethodAttribute.className());
        final String methodName = enclosingMethodAttribute.methodName();
        if ("<init>".equals(methodName)) {
            return value.getConstructor(enclosingMethodAttribute.methodDescriptor());
        }
        if ("<clinit>".equals(methodName)) {
            return value.getClassInitializer();
        }
        return value.getMethod(methodName, enclosingMethodAttribute.methodDescriptor());
    }
    
    @Override
    public CtClass makeNestedClass(final String str, final boolean b) {
        if (!b) {
            throw new RuntimeException("sorry, only nested static class is supported");
        }
        this.checkModify();
        final CtClass nestedClass = this.classPool.makeNestedClass(this.getName() + "$" + str);
        final ClassFile classFile2 = this.getClassFile2();
        final ClassFile classFile3 = nestedClass.getClassFile2();
        InnerClassesAttribute innerClassesAttribute = (InnerClassesAttribute)classFile2.getAttribute("InnerClasses");
        if (innerClassesAttribute == null) {
            innerClassesAttribute = new InnerClassesAttribute(classFile2.getConstPool());
            classFile2.addAttribute(innerClassesAttribute);
        }
        innerClassesAttribute.append(nestedClass.getName(), this.getName(), str, (classFile3.getAccessFlags() & 0xFFFFFFDF) | 0x8);
        classFile3.addAttribute(innerClassesAttribute.copy(classFile3.getConstPool(), null));
        return nestedClass;
    }
    
    private void nameReplaced() {
        final CtMember.Cache hasMemberCache = this.hasMemberCache();
        if (hasMemberCache != null) {
            CtMember ctMember = hasMemberCache.methodHead();
            while (ctMember != hasMemberCache.lastMethod()) {
                ctMember = ctMember.next();
                ctMember.nameReplaced();
            }
        }
    }
    
    protected CtMember.Cache hasMemberCache() {
        if (this.memberCache != null) {
            return this.memberCache.get();
        }
        return null;
    }
    
    protected synchronized CtMember.Cache getMembers() {
        CtMember.Cache referent;
        if (this.memberCache == null || (referent = this.memberCache.get()) == null) {
            referent = new CtMember.Cache(this);
            this.makeFieldCache(referent);
            this.makeBehaviorCache(referent);
            this.memberCache = new WeakReference<CtMember.Cache>(referent);
        }
        return referent;
    }
    
    private void makeFieldCache(final CtMember.Cache cache) {
        final Iterator<FieldInfo> iterator = this.getClassFile3(false).getFields().iterator();
        while (iterator.hasNext()) {
            cache.addField(new CtField(iterator.next(), this));
        }
    }
    
    private void makeBehaviorCache(final CtMember.Cache cache) {
        for (final MethodInfo methodInfo : this.getClassFile3(false).getMethods()) {
            if (methodInfo.isMethod()) {
                cache.addMethod(new CtMethod(methodInfo, this));
            }
            else {
                cache.addConstructor(new CtConstructor(methodInfo, this));
            }
        }
    }
    
    @Override
    public CtField[] getFields() {
        final ArrayList list = new ArrayList();
        getFields(list, this);
        return (CtField[])list.toArray(new CtField[list.size()]);
    }
    
    private static void getFields(final List<CtMember> list, final CtClass ctClass) {
        if (ctClass == null) {
            return;
        }
        try {
            getFields(list, ctClass.getSuperclass());
        }
        catch (final NotFoundException ex) {}
        try {
            final CtClass[] interfaces = ctClass.getInterfaces();
            for (int length = interfaces.length, i = 0; i < length; ++i) {
                getFields(list, interfaces[i]);
            }
        }
        catch (final NotFoundException ex2) {}
        final CtMember.Cache members = ((CtClassType)ctClass).getMembers();
        CtMember ctMember = members.fieldHead();
        while (ctMember != members.lastField()) {
            ctMember = ctMember.next();
            if (!Modifier.isPrivate(ctMember.getModifiers())) {
                list.add(ctMember);
            }
        }
    }
    
    @Override
    public CtField getField(final String s, final String s2) {
        return this.checkGetField(this.getField2(s, s2), s, s2);
    }
    
    private CtField checkGetField(final CtField ctField, final String str, final String str2) {
        if (ctField == null) {
            String s = "field: " + str;
            if (str2 != null) {
                s = s + " type " + str2;
            }
            throw new NotFoundException(s + " in " + this.getName());
        }
        return ctField;
    }
    
    @Override
    CtField getField2(final String s, final String s2) {
        final CtField declaredField2 = this.getDeclaredField2(s, s2);
        if (declaredField2 != null) {
            return declaredField2;
        }
        try {
            final CtClass[] interfaces = this.getInterfaces();
            for (int length = interfaces.length, i = 0; i < length; ++i) {
                final CtField field2 = interfaces[i].getField2(s, s2);
                if (field2 != null) {
                    return field2;
                }
            }
            final CtClass superclass = this.getSuperclass();
            if (superclass != null) {
                return superclass.getField2(s, s2);
            }
        }
        catch (final NotFoundException ex) {}
        return null;
    }
    
    @Override
    public CtField[] getDeclaredFields() {
        final CtMember.Cache members = this.getMembers();
        CtMember ctMember = members.fieldHead();
        final CtMember lastField = members.lastField();
        final CtField[] array = new CtField[CtMember.Cache.count(ctMember, lastField)];
        for (int n = 0; ctMember != lastField; ctMember = ctMember.next(), array[n++] = (CtField)ctMember) {}
        return array;
    }
    
    @Override
    public CtField getDeclaredField(final String s) {
        return this.getDeclaredField(s, null);
    }
    
    @Override
    public CtField getDeclaredField(final String s, final String s2) {
        return this.checkGetField(this.getDeclaredField2(s, s2), s, s2);
    }
    
    private CtField getDeclaredField2(final String anObject, final String s) {
        final CtMember.Cache members = this.getMembers();
        CtMember ctMember = members.fieldHead();
        while (ctMember != members.lastField()) {
            ctMember = ctMember.next();
            if (ctMember.getName().equals(anObject) && (s == null || s.equals(ctMember.getSignature()))) {
                return (CtField)ctMember;
            }
        }
        return null;
    }
    
    @Override
    public CtBehavior[] getDeclaredBehaviors() {
        final CtMember.Cache members = this.getMembers();
        CtMember ctMember = members.consHead();
        final CtMember lastCons = members.lastCons();
        final int count = CtMember.Cache.count(ctMember, lastCons);
        CtMember ctMember2 = members.methodHead();
        final CtMember lastMethod = members.lastMethod();
        CtBehavior[] array;
        int n;
        for (array = new CtBehavior[count + CtMember.Cache.count(ctMember2, lastMethod)], n = 0; ctMember != lastCons; ctMember = ctMember.next(), array[n++] = (CtBehavior)ctMember) {}
        while (ctMember2 != lastMethod) {
            ctMember2 = ctMember2.next();
            array[n++] = (CtBehavior)ctMember2;
        }
        return array;
    }
    
    @Override
    public CtConstructor[] getConstructors() {
        final CtMember.Cache members = this.getMembers();
        final CtMember consHead = members.consHead();
        final CtMember lastCons = members.lastCons();
        int n = 0;
        CtMember next = consHead;
        while (next != lastCons) {
            next = next.next();
            if (isPubCons((CtConstructor)next)) {
                ++n;
            }
        }
        final CtConstructor[] array = new CtConstructor[n];
        int n2 = 0;
        CtMember next2 = consHead;
        while (next2 != lastCons) {
            next2 = next2.next();
            final CtConstructor ctConstructor = (CtConstructor)next2;
            if (isPubCons(ctConstructor)) {
                array[n2++] = ctConstructor;
            }
        }
        return array;
    }
    
    private static boolean isPubCons(final CtConstructor ctConstructor) {
        return !Modifier.isPrivate(ctConstructor.getModifiers()) && ctConstructor.isConstructor();
    }
    
    @Override
    public CtConstructor getConstructor(final String anObject) {
        final CtMember.Cache members = this.getMembers();
        CtMember ctMember = members.consHead();
        while (ctMember != members.lastCons()) {
            ctMember = ctMember.next();
            final CtConstructor ctConstructor = (CtConstructor)ctMember;
            if (ctConstructor.getMethodInfo2().getDescriptor().equals(anObject) && ctConstructor.isConstructor()) {
                return ctConstructor;
            }
        }
        return super.getConstructor(anObject);
    }
    
    @Override
    public CtConstructor[] getDeclaredConstructors() {
        final CtMember.Cache members = this.getMembers();
        final CtMember consHead = members.consHead();
        final CtMember lastCons = members.lastCons();
        int n = 0;
        CtMember next = consHead;
        while (next != lastCons) {
            next = next.next();
            if (((CtConstructor)next).isConstructor()) {
                ++n;
            }
        }
        final CtConstructor[] array = new CtConstructor[n];
        int n2 = 0;
        CtMember next2 = consHead;
        while (next2 != lastCons) {
            next2 = next2.next();
            final CtConstructor ctConstructor = (CtConstructor)next2;
            if (ctConstructor.isConstructor()) {
                array[n2++] = ctConstructor;
            }
        }
        return array;
    }
    
    @Override
    public CtConstructor getClassInitializer() {
        final CtMember.Cache members = this.getMembers();
        CtMember ctMember = members.consHead();
        while (ctMember != members.lastCons()) {
            ctMember = ctMember.next();
            final CtConstructor ctConstructor = (CtConstructor)ctMember;
            if (ctConstructor.isClassInitializer()) {
                return ctConstructor;
            }
        }
        return null;
    }
    
    @Override
    public CtMethod[] getMethods() {
        final HashMap hashMap = new HashMap();
        getMethods0(hashMap, this);
        return (CtMethod[])hashMap.values().toArray(new CtMethod[hashMap.size()]);
    }
    
    private static void getMethods0(final Map<String, CtMember> map, final CtClass ctClass) {
        try {
            final CtClass[] interfaces = ctClass.getInterfaces();
            for (int length = interfaces.length, i = 0; i < length; ++i) {
                getMethods0(map, interfaces[i]);
            }
        }
        catch (final NotFoundException ex) {}
        try {
            final CtClass superclass = ctClass.getSuperclass();
            if (superclass != null) {
                getMethods0(map, superclass);
            }
        }
        catch (final NotFoundException ex2) {}
        if (ctClass instanceof CtClassType) {
            final CtMember.Cache members = ((CtClassType)ctClass).getMembers();
            CtMember ctMember = members.methodHead();
            while (ctMember != members.lastMethod()) {
                ctMember = ctMember.next();
                if (!Modifier.isPrivate(ctMember.getModifiers())) {
                    map.put(((CtMethod)ctMember).getStringRep(), ctMember);
                }
            }
        }
    }
    
    @Override
    public CtMethod getMethod(final String str, final String s) {
        final CtMethod method0 = getMethod0(this, str, s);
        if (method0 != null) {
            return method0;
        }
        throw new NotFoundException(str + "(..) is not found in " + this.getName());
    }
    
    private static CtMethod getMethod0(final CtClass ctClass, final String anObject, final String anObject2) {
        if (ctClass instanceof CtClassType) {
            final CtMember.Cache members = ((CtClassType)ctClass).getMembers();
            CtMember ctMember = members.methodHead();
            while (ctMember != members.lastMethod()) {
                ctMember = ctMember.next();
                if (ctMember.getName().equals(anObject) && ((CtMethod)ctMember).getMethodInfo2().getDescriptor().equals(anObject2)) {
                    return (CtMethod)ctMember;
                }
            }
        }
        try {
            final CtClass superclass = ctClass.getSuperclass();
            if (superclass != null) {
                final CtMethod method0 = getMethod0(superclass, anObject, anObject2);
                if (method0 != null) {
                    return method0;
                }
            }
        }
        catch (final NotFoundException ex) {}
        try {
            final CtClass[] interfaces = ctClass.getInterfaces();
            for (int length = interfaces.length, i = 0; i < length; ++i) {
                final CtMethod method2 = getMethod0(interfaces[i], anObject, anObject2);
                if (method2 != null) {
                    return method2;
                }
            }
        }
        catch (final NotFoundException ex2) {}
        return null;
    }
    
    @Override
    public CtMethod[] getDeclaredMethods() {
        final CtMember.Cache members = this.getMembers();
        CtMember ctMember = members.methodHead();
        final CtMember lastMethod = members.lastMethod();
        final ArrayList list = new ArrayList();
        while (ctMember != lastMethod) {
            ctMember = ctMember.next();
            list.add(ctMember);
        }
        return (CtMethod[])list.toArray(new CtMethod[list.size()]);
    }
    
    @Override
    public CtMethod[] getDeclaredMethods(final String anObject) {
        final CtMember.Cache members = this.getMembers();
        CtMember ctMember = members.methodHead();
        final CtMember lastMethod = members.lastMethod();
        final ArrayList list = new ArrayList();
        while (ctMember != lastMethod) {
            ctMember = ctMember.next();
            if (ctMember.getName().equals(anObject)) {
                list.add(ctMember);
            }
        }
        return (CtMethod[])list.toArray(new CtMethod[list.size()]);
    }
    
    @Override
    public CtMethod getDeclaredMethod(final String s) {
        final CtMember.Cache members = this.getMembers();
        CtMember ctMember = members.methodHead();
        while (ctMember != members.lastMethod()) {
            ctMember = ctMember.next();
            if (ctMember.getName().equals(s)) {
                return (CtMethod)ctMember;
            }
        }
        throw new NotFoundException(s + "(..) is not found in " + this.getName());
    }
    
    @Override
    public CtMethod getDeclaredMethod(final String s, final CtClass[] array) {
        final String ofParameters = Descriptor.ofParameters(array);
        final CtMember.Cache members = this.getMembers();
        CtMember ctMember = members.methodHead();
        while (ctMember != members.lastMethod()) {
            ctMember = ctMember.next();
            if (ctMember.getName().equals(s) && ((CtMethod)ctMember).getMethodInfo2().getDescriptor().startsWith(ofParameters)) {
                return (CtMethod)ctMember;
            }
        }
        throw new NotFoundException(s + "(..) is not found in " + this.getName());
    }
    
    @Override
    public void addField(final CtField ctField, final String s) {
        this.addField(ctField, CtField.Initializer.byExpr(s));
    }
    
    @Override
    public void addField(final CtField ctField, CtField.Initializer init) {
        this.checkModify();
        if (ctField.getDeclaringClass() != this) {
            throw new CannotCompileException("cannot add");
        }
        if (init == null) {
            init = ctField.getInit();
        }
        if (init != null) {
            init.check(ctField.getSignature());
            final int modifiers = ctField.getModifiers();
            if (Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)) {
                try {
                    final ConstPool constPool = this.getClassFile2().getConstPool();
                    final int constantValue = init.getConstantValue(constPool, ctField.getType());
                    if (constantValue != 0) {
                        ctField.getFieldInfo2().addAttribute(new ConstantAttribute(constPool, constantValue));
                        init = null;
                    }
                }
                catch (final NotFoundException ex) {}
            }
        }
        this.getMembers().addField(ctField);
        this.getClassFile2().addField(ctField.getFieldInfo2());
        if (init != null) {
            final FieldInitLink fieldInitLink = new FieldInitLink(ctField, init);
            FieldInitLink fieldInitLink2 = this.fieldInitializers;
            if (fieldInitLink2 == null) {
                this.fieldInitializers = fieldInitLink;
            }
            else {
                while (fieldInitLink2.next != null) {
                    fieldInitLink2 = fieldInitLink2.next;
                }
                fieldInitLink2.next = fieldInitLink;
            }
        }
    }
    
    @Override
    public void removeField(final CtField ctField) {
        this.checkModify();
        if (this.getClassFile2().getFields().remove(ctField.getFieldInfo2())) {
            this.getMembers().remove(ctField);
            this.gcConstPool = true;
            return;
        }
        throw new NotFoundException(ctField.toString());
    }
    
    @Override
    public CtConstructor makeClassInitializer() {
        final CtConstructor classInitializer = this.getClassInitializer();
        if (classInitializer != null) {
            return classInitializer;
        }
        this.checkModify();
        final ClassFile classFile2 = this.getClassFile2();
        this.modifyClassConstructor(classFile2, new Bytecode(classFile2.getConstPool(), 0, 0), 0, 0);
        return this.getClassInitializer();
    }
    
    @Override
    public void addConstructor(final CtConstructor ctConstructor) {
        this.checkModify();
        if (ctConstructor.getDeclaringClass() != this) {
            throw new CannotCompileException("cannot add");
        }
        this.getMembers().addConstructor(ctConstructor);
        this.getClassFile2().addMethod(ctConstructor.getMethodInfo2());
    }
    
    @Override
    public void removeConstructor(final CtConstructor ctConstructor) {
        this.checkModify();
        if (this.getClassFile2().getMethods().remove(ctConstructor.getMethodInfo2())) {
            this.getMembers().remove(ctConstructor);
            this.gcConstPool = true;
            return;
        }
        throw new NotFoundException(ctConstructor.toString());
    }
    
    @Override
    public void addMethod(final CtMethod ctMethod) {
        this.checkModify();
        if (ctMethod.getDeclaringClass() != this) {
            throw new CannotCompileException("bad declaring class");
        }
        final int modifiers = ctMethod.getModifiers();
        if ((this.getModifiers() & 0x200) != 0x0) {
            if (Modifier.isProtected(modifiers) || Modifier.isPrivate(modifiers)) {
                throw new CannotCompileException("an interface method must be public: " + ctMethod.toString());
            }
            ctMethod.setModifiers(modifiers | 0x1);
        }
        this.getMembers().addMethod(ctMethod);
        this.getClassFile2().addMethod(ctMethod.getMethodInfo2());
        if ((modifiers & 0x400) != 0x0) {
            this.setModifiers(this.getModifiers() | 0x400);
        }
    }
    
    @Override
    public void removeMethod(final CtMethod ctMethod) {
        this.checkModify();
        if (this.getClassFile2().getMethods().remove(ctMethod.getMethodInfo2())) {
            this.getMembers().remove(ctMethod);
            this.gcConstPool = true;
            return;
        }
        throw new NotFoundException(ctMethod.toString());
    }
    
    @Override
    public byte[] getAttribute(final String s) {
        final AttributeInfo attribute = this.getClassFile2().getAttribute(s);
        if (attribute == null) {
            return null;
        }
        return attribute.get();
    }
    
    @Override
    public void setAttribute(final String s, final byte[] array) {
        this.checkModify();
        final ClassFile classFile2 = this.getClassFile2();
        classFile2.addAttribute(new AttributeInfo(classFile2.getConstPool(), s, array));
    }
    
    @Override
    public void instrument(final CodeConverter codeConverter) {
        this.checkModify();
        final ClassFile classFile2 = this.getClassFile2();
        final ConstPool constPool = classFile2.getConstPool();
        final List<MethodInfo> methods = classFile2.getMethods();
        final MethodInfo[] array = methods.toArray(new MethodInfo[methods.size()]);
        for (int length = array.length, i = 0; i < length; ++i) {
            codeConverter.doit(this, array[i], constPool);
        }
    }
    
    @Override
    public void instrument(final ExprEditor exprEditor) {
        this.checkModify();
        final List<MethodInfo> methods = this.getClassFile2().getMethods();
        final MethodInfo[] array = methods.toArray(new MethodInfo[methods.size()]);
        for (int length = array.length, i = 0; i < length; ++i) {
            exprEditor.doit(this, array[i]);
        }
    }
    
    @Override
    public void prune() {
        if (this.wasPruned) {
            return;
        }
        final boolean b = true;
        this.wasFrozen = b;
        this.wasPruned = b;
        this.getClassFile2().prune();
    }
    
    @Override
    public void rebuildClassFile() {
        this.gcConstPool = true;
    }
    
    @Override
    public void toBytecode(final DataOutputStream dataOutputStream) {
        try {
            if (this.isModified()) {
                this.checkPruned("toBytecode");
                final ClassFile classFile2 = this.getClassFile2();
                if (this.gcConstPool) {
                    classFile2.compact();
                    this.gcConstPool = false;
                }
                this.modifyClassConstructor(classFile2);
                this.modifyConstructors(classFile2);
                if (CtClassType.debugDump != null) {
                    this.dumpClassFile(classFile2);
                }
                classFile2.write(dataOutputStream);
                dataOutputStream.flush();
                this.fieldInitializers = null;
                if (this.doPruning) {
                    classFile2.prune();
                    this.wasPruned = true;
                }
            }
            else {
                this.classPool.writeClassfile(this.getName(), dataOutputStream);
            }
            this.getCount = 0;
            this.wasFrozen = true;
        }
        catch (final NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
        catch (final IOException ex2) {
            throw new CannotCompileException(ex2);
        }
    }
    
    private void dumpClassFile(final ClassFile classFile) {
        final DataOutputStream fileOutput = this.makeFileOutput(CtClassType.debugDump);
        try {
            classFile.write(fileOutput);
        }
        finally {
            fileOutput.close();
        }
    }
    
    private void checkPruned(final String str) {
        if (this.wasPruned) {
            throw new RuntimeException(str + "(): " + this.getName() + " was pruned.");
        }
    }
    
    @Override
    public boolean stopPruning(final boolean b) {
        final boolean b2 = !this.doPruning;
        this.doPruning = !b;
        return b2;
    }
    
    private void modifyClassConstructor(final ClassFile classFile) {
        if (this.fieldInitializers == null) {
            return;
        }
        final Bytecode bytecode = new Bytecode(classFile.getConstPool(), 0, 0);
        final Javac javac = new Javac(bytecode, this);
        int n = 0;
        boolean b = false;
        for (FieldInitLink fieldInitLink = this.fieldInitializers; fieldInitLink != null; fieldInitLink = fieldInitLink.next) {
            final CtField field = fieldInitLink.field;
            if (Modifier.isStatic(field.getModifiers())) {
                b = true;
                final int compileIfStatic = fieldInitLink.init.compileIfStatic(field.getType(), field.getName(), bytecode, javac);
                if (n < compileIfStatic) {
                    n = compileIfStatic;
                }
            }
        }
        if (b) {
            this.modifyClassConstructor(classFile, bytecode, n, 0);
        }
    }
    
    private void modifyClassConstructor(final ClassFile classFile, final Bytecode bytecode, final int n, final int n2) {
        MethodInfo staticInitializer = classFile.getStaticInitializer();
        if (staticInitializer == null) {
            bytecode.add(177);
            bytecode.setMaxStack(n);
            bytecode.setMaxLocals(n2);
            staticInitializer = new MethodInfo(classFile.getConstPool(), "<clinit>", "()V");
            staticInitializer.setAccessFlags(8);
            staticInitializer.setCodeAttribute(bytecode.toCodeAttribute());
            classFile.addMethod(staticInitializer);
            final CtMember.Cache hasMemberCache = this.hasMemberCache();
            if (hasMemberCache != null) {
                hasMemberCache.addConstructor(new CtConstructor(staticInitializer, this));
            }
        }
        else {
            final CodeAttribute codeAttribute = staticInitializer.getCodeAttribute();
            if (codeAttribute == null) {
                throw new CannotCompileException("empty <clinit>");
            }
            try {
                final CodeIterator iterator = codeAttribute.iterator();
                iterator.insert(bytecode.getExceptionTable(), iterator.insertEx(bytecode.get()));
                if (codeAttribute.getMaxStack() < n) {
                    codeAttribute.setMaxStack(n);
                }
                if (codeAttribute.getMaxLocals() < n2) {
                    codeAttribute.setMaxLocals(n2);
                }
            }
            catch (final BadBytecode badBytecode) {
                throw new CannotCompileException(badBytecode);
            }
        }
        try {
            staticInitializer.rebuildStackMapIf6(this.classPool, classFile);
        }
        catch (final BadBytecode badBytecode2) {
            throw new CannotCompileException(badBytecode2);
        }
    }
    
    private void modifyConstructors(final ClassFile classFile) {
        if (this.fieldInitializers == null) {
            return;
        }
        final ConstPool constPool = classFile.getConstPool();
        for (final MethodInfo methodInfo : classFile.getMethods()) {
            if (methodInfo.isConstructor()) {
                final CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
                if (codeAttribute == null) {
                    continue;
                }
                try {
                    final Bytecode bytecode = new Bytecode(constPool, 0, codeAttribute.getMaxLocals());
                    insertAuxInitializer(codeAttribute, bytecode, this.makeFieldInitializer(bytecode, Descriptor.getParameterTypes(methodInfo.getDescriptor(), this.classPool)));
                    methodInfo.rebuildStackMapIf6(this.classPool, classFile);
                }
                catch (final BadBytecode badBytecode) {
                    throw new CannotCompileException(badBytecode);
                }
            }
        }
    }
    
    private static void insertAuxInitializer(final CodeAttribute codeAttribute, final Bytecode bytecode, final int n) {
        final CodeIterator iterator = codeAttribute.iterator();
        if (iterator.skipSuperConstructor() < 0 && iterator.skipThisConstructor() >= 0) {
            return;
        }
        iterator.insert(bytecode.getExceptionTable(), iterator.insertEx(bytecode.get()));
        codeAttribute.setMaxStack(codeAttribute.getMaxStack() + n);
    }
    
    private int makeFieldInitializer(final Bytecode bytecode, final CtClass[] array) {
        int n = 0;
        final Javac javac = new Javac(bytecode, this);
        try {
            javac.recordParams(array, false);
        }
        catch (final CompileError compileError) {
            throw new CannotCompileException(compileError);
        }
        for (FieldInitLink fieldInitLink = this.fieldInitializers; fieldInitLink != null; fieldInitLink = fieldInitLink.next) {
            final CtField field = fieldInitLink.field;
            if (!Modifier.isStatic(field.getModifiers())) {
                final int compile = fieldInitLink.init.compile(field.getType(), field.getName(), bytecode, array, javac);
                if (n < compile) {
                    n = compile;
                }
            }
        }
        return n;
    }
    
    Map<CtMethod, String> getHiddenMethods() {
        if (this.hiddenMethods == null) {
            this.hiddenMethods = new Hashtable<CtMethod, String>();
        }
        return this.hiddenMethods;
    }
    
    int getUniqueNumber() {
        return this.uniqueNumberSeed++;
    }
    
    @Override
    public String makeUniqueName(final String str) {
        final HashMap hashMap = new HashMap();
        this.makeMemberList(hashMap);
        final Set keySet = hashMap.keySet();
        final String[] array = new String[keySet.size()];
        keySet.toArray(array);
        if (notFindInArray(str, array)) {
            return str;
        }
        int i = 100;
        while (i <= 999) {
            final String string = str + i++;
            if (notFindInArray(string, array)) {
                return string;
            }
        }
        throw new RuntimeException("too many unique name");
    }
    
    private static boolean notFindInArray(final String prefix, final String[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (array[i].startsWith(prefix)) {
                return false;
            }
        }
        return true;
    }
    
    private void makeMemberList(final Map<Object, CtClassType> map) {
        final int modifiers = this.getModifiers();
        Label_0081: {
            if (!Modifier.isAbstract(modifiers)) {
                if (!Modifier.isInterface(modifiers)) {
                    break Label_0081;
                }
            }
            try {
                for (final CtClass ctClass : this.getInterfaces()) {
                    if (ctClass != null && ctClass instanceof CtClassType) {
                        ((CtClassType)ctClass).makeMemberList(map);
                    }
                }
            }
            catch (final NotFoundException ex) {}
            try {
                final CtClass superclass = this.getSuperclass();
                if (superclass != null && superclass instanceof CtClassType) {
                    ((CtClassType)superclass).makeMemberList(map);
                }
            }
            catch (final NotFoundException ex2) {}
        }
        final Iterator<MethodInfo> iterator = this.getClassFile2().getMethods().iterator();
        while (iterator.hasNext()) {
            map.put(iterator.next().getName(), this);
        }
        final Iterator<FieldInfo> iterator2 = this.getClassFile2().getFields().iterator();
        while (iterator2.hasNext()) {
            map.put(iterator2.next().getName(), this);
        }
    }
}
