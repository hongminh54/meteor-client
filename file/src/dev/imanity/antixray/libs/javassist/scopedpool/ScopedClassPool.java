// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.scopedpool;

import java.security.ProtectionDomain;
import dev.imanity.antixray.libs.javassist.NotFoundException;
import java.util.Iterator;
import dev.imanity.antixray.libs.javassist.ClassPath;
import java.lang.ref.WeakReference;
import dev.imanity.antixray.libs.javassist.CtClass;
import java.util.Map;
import dev.imanity.antixray.libs.javassist.LoaderClassPath;
import java.lang.ref.Reference;
import dev.imanity.antixray.libs.javassist.ClassPool;

public class ScopedClassPool extends ClassPool
{
    protected ScopedClassPoolRepository repository;
    protected Reference<ClassLoader> classLoader;
    protected LoaderClassPath classPath;
    protected Map<String, CtClass> softcache;
    boolean isBootstrapCl;
    
    protected ScopedClassPool(final ClassLoader classLoader, final ClassPool classPool, final ScopedClassPoolRepository scopedClassPoolRepository) {
        this(classLoader, classPool, scopedClassPoolRepository, false);
    }
    
    protected ScopedClassPool(final ClassLoader referent, final ClassPool classPool, final ScopedClassPoolRepository repository, final boolean b) {
        super(classPool);
        this.softcache = new SoftValueHashMap<String, CtClass>();
        this.isBootstrapCl = true;
        this.repository = repository;
        this.classLoader = new WeakReference<ClassLoader>(referent);
        if (referent != null) {
            this.insertClassPath(this.classPath = new LoaderClassPath(referent));
        }
        this.childFirstLookup = true;
        if (!b && referent == null) {
            this.isBootstrapCl = true;
        }
    }
    
    @Override
    public ClassLoader getClassLoader() {
        final ClassLoader classLoader0 = this.getClassLoader0();
        if (classLoader0 == null && !this.isBootstrapCl) {
            throw new IllegalStateException("ClassLoader has been garbage collected");
        }
        return classLoader0;
    }
    
    protected ClassLoader getClassLoader0() {
        return this.classLoader.get();
    }
    
    public void close() {
        this.removeClassPath(this.classPath);
        this.classes.clear();
        this.softcache.clear();
    }
    
    public synchronized void flushClass(final String key) {
        this.classes.remove(key);
        this.softcache.remove(key);
    }
    
    public synchronized void soften(final CtClass ctClass) {
        if (this.repository.isPrune()) {
            ctClass.prune();
        }
        this.classes.remove(ctClass.getName());
        this.softcache.put(ctClass.getName(), ctClass);
    }
    
    public boolean isUnloadedClassLoader() {
        return false;
    }
    
    @Override
    protected CtClass getCached(final String s) {
        CtClass ctClass = this.getCachedLocally(s);
        if (ctClass == null) {
            int n = 0;
            final ClassLoader classLoader0 = this.getClassLoader0();
            if (classLoader0 != null) {
                final int lastIndex = s.lastIndexOf(36);
                String name;
                if (lastIndex < 0) {
                    name = s.replaceAll("[\\.]", "/") + ".class";
                }
                else {
                    name = s.substring(0, lastIndex).replaceAll("[\\.]", "/") + s.substring(lastIndex) + ".class";
                }
                n = ((classLoader0.getResource(name) != null) ? 1 : 0);
            }
            if (n == 0) {
                final Map<ClassLoader, ScopedClassPool> registeredCLs = this.repository.getRegisteredCLs();
                synchronized (registeredCLs) {
                    for (final ScopedClassPool scopedClassPool : registeredCLs.values()) {
                        if (scopedClassPool.isUnloadedClassLoader()) {
                            this.repository.unregisterClassLoader(scopedClassPool.getClassLoader());
                        }
                        else {
                            ctClass = scopedClassPool.getCachedLocally(s);
                            if (ctClass != null) {
                                return ctClass;
                            }
                            continue;
                        }
                    }
                }
            }
        }
        return ctClass;
    }
    
    @Override
    protected void cacheCtClass(final String s, final CtClass ctClass, final boolean b) {
        if (b) {
            super.cacheCtClass(s, ctClass, b);
        }
        else {
            if (this.repository.isPrune()) {
                ctClass.prune();
            }
            this.softcache.put(s, ctClass);
        }
    }
    
    public void lockInCache(final CtClass ctClass) {
        super.cacheCtClass(ctClass.getName(), ctClass, false);
    }
    
    protected CtClass getCachedLocally(final String key) {
        final CtClass ctClass = this.classes.get(key);
        if (ctClass != null) {
            return ctClass;
        }
        synchronized (this.softcache) {
            return this.softcache.get(key);
        }
    }
    
    public synchronized CtClass getLocally(final String key) {
        this.softcache.remove(key);
        CtClass ctClass = this.classes.get(key);
        if (ctClass == null) {
            ctClass = this.createCtClass(key, true);
            if (ctClass == null) {
                throw new NotFoundException(key);
            }
            super.cacheCtClass(key, ctClass, false);
        }
        return ctClass;
    }
    
    @Override
    public Class<?> toClass(final CtClass ctClass, final ClassLoader classLoader, final ProtectionDomain protectionDomain) {
        this.lockInCache(ctClass);
        return super.toClass(ctClass, this.getClassLoader0(), protectionDomain);
    }
    
    static {
        ClassPool.doPruning = false;
        ClassPool.releaseUnmodifiedClassFile = false;
    }
}
