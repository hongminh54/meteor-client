// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist;

import dev.imanity.antixray.libs.javassist.util.proxy.DefinePackageHelper;
import java.lang.invoke.MethodHandles;
import java.io.IOException;
import dev.imanity.antixray.libs.javassist.util.proxy.DefineClassHelper;
import java.security.ProtectionDomain;
import java.io.File;
import dev.imanity.antixray.libs.javassist.bytecode.ClassFile;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.URL;
import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Hashtable;

public class ClassPool
{
    public boolean childFirstLookup;
    public static boolean doPruning;
    private int compressCount;
    private static final int COMPRESS_THRESHOLD = 100;
    public static boolean releaseUnmodifiedClassFile;
    public static boolean cacheOpenedJarFile;
    protected ClassPoolTail source;
    protected ClassPool parent;
    protected Hashtable classes;
    private Hashtable cflow;
    private static final int INIT_HASH_SIZE = 191;
    private ArrayList importedPackages;
    private static ClassPool defaultPool;
    
    public ClassPool() {
        this(null);
    }
    
    public ClassPool(final boolean b) {
        this(null);
        if (b) {
            this.appendSystemPath();
        }
    }
    
    public ClassPool(final ClassPool parent) {
        this.childFirstLookup = false;
        this.cflow = null;
        this.classes = new Hashtable(191);
        this.source = new ClassPoolTail();
        this.parent = parent;
        if (parent == null) {
            final CtClass[] primitiveTypes = CtClass.primitiveTypes;
            for (int i = 0; i < primitiveTypes.length; ++i) {
                this.classes.put(primitiveTypes[i].getName(), primitiveTypes[i]);
            }
        }
        this.cflow = null;
        this.compressCount = 0;
        this.clearImportedPackages();
    }
    
    public static synchronized ClassPool getDefault() {
        if (ClassPool.defaultPool == null) {
            (ClassPool.defaultPool = new ClassPool(null)).appendSystemPath();
        }
        return ClassPool.defaultPool;
    }
    
    protected CtClass getCached(final String key) {
        return this.classes.get(key);
    }
    
    protected void cacheCtClass(final String key, final CtClass value, final boolean b) {
        this.classes.put(key, value);
    }
    
    protected CtClass removeCached(final String key) {
        return this.classes.remove(key);
    }
    
    @Override
    public String toString() {
        return this.source.toString();
    }
    
    void compress() {
        if (this.compressCount++ > 100) {
            this.compressCount = 0;
            final Enumeration elements = this.classes.elements();
            while (elements.hasMoreElements()) {
                ((CtClass)elements.nextElement()).compress();
            }
        }
    }
    
    public void importPackage(final String e) {
        this.importedPackages.add(e);
    }
    
    public void clearImportedPackages() {
        (this.importedPackages = new ArrayList()).add("java.lang");
    }
    
    public Iterator<String> getImportedPackages() {
        return this.importedPackages.iterator();
    }
    
    public void recordInvalidClassName(final String s) {
    }
    
    void recordCflow(final String key, final String s, final String s2) {
        if (this.cflow == null) {
            this.cflow = new Hashtable();
        }
        this.cflow.put(key, new Object[] { s, s2 });
    }
    
    public Object[] lookupCflow(final String key) {
        if (this.cflow == null) {
            this.cflow = new Hashtable();
        }
        return this.cflow.get(key);
    }
    
    public CtClass getAndRename(final String s, final String name) {
        final CtClass get0 = this.get0(s, false);
        if (get0 == null) {
            throw new NotFoundException(s);
        }
        if (get0 instanceof CtClassType) {
            ((CtClassType)get0).setClassPool(this);
        }
        get0.setName(name);
        return get0;
    }
    
    synchronized void classNameChanged(final String s, final CtClass ctClass) {
        if (this.getCached(s) == ctClass) {
            this.removeCached(s);
        }
        final String name = ctClass.getName();
        this.checkNotFrozen(name);
        this.cacheCtClass(name, ctClass, false);
    }
    
    public CtClass get(final String s) {
        CtClass get0;
        if (s == null) {
            get0 = null;
        }
        else {
            get0 = this.get0(s, true);
        }
        if (get0 == null) {
            throw new NotFoundException(s);
        }
        get0.incGetCounter();
        return get0;
    }
    
    public CtClass getOrNull(final String s) {
        CtClass get0 = null;
        if (s == null) {
            get0 = null;
        }
        else {
            try {
                get0 = this.get0(s, true);
            }
            catch (final NotFoundException ex) {}
        }
        if (get0 != null) {
            get0.incGetCounter();
        }
        return get0;
    }
    
    public CtClass getCtClass(final String s) {
        if (s.charAt(0) == '[') {
            return Descriptor.toCtClass(s, this);
        }
        return this.get(s);
    }
    
    protected synchronized CtClass get0(final String s, final boolean b) {
        if (b) {
            final CtClass cached = this.getCached(s);
            if (cached != null) {
                return cached;
            }
        }
        if (!this.childFirstLookup && this.parent != null) {
            final CtClass get0 = this.parent.get0(s, b);
            if (get0 != null) {
                return get0;
            }
        }
        CtClass ctClass = this.createCtClass(s, b);
        if (ctClass != null) {
            if (b) {
                this.cacheCtClass(ctClass.getName(), ctClass, false);
            }
            return ctClass;
        }
        if (this.childFirstLookup && this.parent != null) {
            ctClass = this.parent.get0(s, b);
        }
        return ctClass;
    }
    
    protected CtClass createCtClass(String className, final boolean b) {
        if (className.charAt(0) == '[') {
            className = Descriptor.toClassName(className);
        }
        if (className.endsWith("[]")) {
            final String substring = className.substring(0, className.indexOf(91));
            if ((!b || this.getCached(substring) == null) && this.find(substring) == null) {
                return null;
            }
            return new CtArray(className, this);
        }
        else {
            if (this.find(className) == null) {
                return null;
            }
            return new CtClassType(className, this);
        }
    }
    
    public URL find(final String s) {
        return this.source.find(s);
    }
    
    void checkNotFrozen(final String s) {
        CtClass ctClass = this.getCached(s);
        if (ctClass == null) {
            if (!this.childFirstLookup && this.parent != null) {
                try {
                    ctClass = this.parent.get0(s, true);
                }
                catch (final NotFoundException ex) {}
                if (ctClass != null) {
                    throw new RuntimeException(s + " is in a parent ClassPool.  Use the parent.");
                }
            }
        }
        else if (ctClass.isFrozen()) {
            throw new RuntimeException(s + ": frozen class (cannot edit)");
        }
    }
    
    CtClass checkNotExists(final String s) {
        CtClass ctClass = this.getCached(s);
        if (ctClass == null && !this.childFirstLookup && this.parent != null) {
            try {
                ctClass = this.parent.get0(s, true);
            }
            catch (final NotFoundException ex) {}
        }
        return ctClass;
    }
    
    InputStream openClassfile(final String s) {
        return this.source.openClassfile(s);
    }
    
    void writeClassfile(final String s, final OutputStream outputStream) {
        this.source.writeClassfile(s, outputStream);
    }
    
    public CtClass[] get(final String[] array) {
        if (array == null) {
            return new CtClass[0];
        }
        final int length = array.length;
        final CtClass[] array2 = new CtClass[length];
        for (int i = 0; i < length; ++i) {
            array2[i] = this.get(array[i]);
        }
        return array2;
    }
    
    public CtMethod getMethod(final String s, final String s2) {
        return this.get(s).getDeclaredMethod(s2);
    }
    
    public CtClass makeClass(final InputStream inputStream) {
        return this.makeClass(inputStream, true);
    }
    
    public CtClass makeClass(final InputStream in, final boolean b) {
        this.compress();
        final CtClassType ctClassType = new CtClassType(new BufferedInputStream(in), this);
        ctClassType.checkModify();
        final String name = ctClassType.getName();
        if (b) {
            this.checkNotFrozen(name);
        }
        this.cacheCtClass(name, ctClassType, true);
        return ctClassType;
    }
    
    public CtClass makeClass(final ClassFile classFile) {
        return this.makeClass(classFile, true);
    }
    
    public CtClass makeClass(final ClassFile classFile, final boolean b) {
        this.compress();
        final CtClassType ctClassType = new CtClassType(classFile, this);
        ctClassType.checkModify();
        final String name = ctClassType.getName();
        if (b) {
            this.checkNotFrozen(name);
        }
        this.cacheCtClass(name, ctClassType, true);
        return ctClassType;
    }
    
    public CtClass makeClassIfNew(final InputStream in) {
        this.compress();
        final CtClassType ctClassType = new CtClassType(new BufferedInputStream(in), this);
        ctClassType.checkModify();
        final String name = ctClassType.getName();
        final CtClass checkNotExists = this.checkNotExists(name);
        if (checkNotExists != null) {
            return checkNotExists;
        }
        this.cacheCtClass(name, ctClassType, true);
        return ctClassType;
    }
    
    public CtClass makeClass(final String s) {
        return this.makeClass(s, null);
    }
    
    public synchronized CtClass makeClass(final String s, final CtClass ctClass) {
        this.checkNotFrozen(s);
        final CtNewClass ctNewClass = new CtNewClass(s, this, false, ctClass);
        this.cacheCtClass(s, ctNewClass, true);
        return ctNewClass;
    }
    
    synchronized CtClass makeNestedClass(final String s) {
        this.checkNotFrozen(s);
        final CtNewClass ctNewClass = new CtNewClass(s, this, false, null);
        this.cacheCtClass(s, ctNewClass, true);
        return ctNewClass;
    }
    
    public CtClass makeInterface(final String s) {
        return this.makeInterface(s, null);
    }
    
    public synchronized CtClass makeInterface(final String s, final CtClass ctClass) {
        this.checkNotFrozen(s);
        final CtNewClass ctNewClass = new CtNewClass(s, this, true, ctClass);
        this.cacheCtClass(s, ctNewClass, true);
        return ctNewClass;
    }
    
    public CtClass makeAnnotation(final String s) {
        try {
            final CtClass interface1 = this.makeInterface(s, this.get("java.lang.annotation.Annotation"));
            interface1.setModifiers(interface1.getModifiers() | 0x2000);
            return interface1;
        }
        catch (final NotFoundException cause) {
            throw new RuntimeException(cause.getMessage(), cause);
        }
    }
    
    public ClassPath appendSystemPath() {
        return this.source.appendSystemPath();
    }
    
    public ClassPath insertClassPath(final ClassPath classPath) {
        return this.source.insertClassPath(classPath);
    }
    
    public ClassPath appendClassPath(final ClassPath classPath) {
        return this.source.appendClassPath(classPath);
    }
    
    public ClassPath insertClassPath(final String s) {
        return this.source.insertClassPath(s);
    }
    
    public ClassPath appendClassPath(final String s) {
        return this.source.appendClassPath(s);
    }
    
    public void removeClassPath(final ClassPath classPath) {
        this.source.removeClassPath(classPath);
    }
    
    public void appendPathList(final String s) {
        final char pathSeparatorChar = File.pathSeparatorChar;
        int beginIndex = 0;
        while (true) {
            final int index = s.indexOf(pathSeparatorChar, beginIndex);
            if (index < 0) {
                break;
            }
            this.appendClassPath(s.substring(beginIndex, index));
            beginIndex = index + 1;
        }
        this.appendClassPath(s.substring(beginIndex));
    }
    
    public Class toClass(final CtClass ctClass) {
        return this.toClass(ctClass, this.getClassLoader());
    }
    
    public ClassLoader getClassLoader() {
        return getContextClassLoader();
    }
    
    static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
    
    public Class toClass(final CtClass ctClass, final ClassLoader classLoader) {
        return this.toClass(ctClass, null, classLoader, null);
    }
    
    public Class toClass(final CtClass ctClass, final ClassLoader classLoader, final ProtectionDomain protectionDomain) {
        return this.toClass(ctClass, null, classLoader, protectionDomain);
    }
    
    public Class<?> toClass(final CtClass ctClass, final Class<?> clazz) {
        try {
            return DefineClassHelper.toClass(clazz, ctClass.toBytecode());
        }
        catch (final IOException ex) {
            throw new CannotCompileException(ex);
        }
    }
    
    public Class<?> toClass(final CtClass ctClass, final MethodHandles.Lookup lookup) {
        try {
            return DefineClassHelper.toClass(lookup, ctClass.toBytecode());
        }
        catch (final IOException ex) {
            throw new CannotCompileException(ex);
        }
    }
    
    public Class toClass(final CtClass ctClass, final Class<?> clazz, final ClassLoader classLoader, final ProtectionDomain protectionDomain) {
        try {
            return DefineClassHelper.toClass(ctClass.getName(), clazz, classLoader, protectionDomain, ctClass.toBytecode());
        }
        catch (final IOException ex) {
            throw new CannotCompileException(ex);
        }
    }
    
    public void makePackage(final ClassLoader classLoader, final String s) {
        DefinePackageHelper.definePackage(s, classLoader);
    }
    
    static {
        ClassPool.doPruning = false;
        ClassPool.releaseUnmodifiedClassFile = true;
        ClassPool.cacheOpenedJarFile = true;
        ClassPool.defaultPool = null;
    }
}
