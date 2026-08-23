// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.scopedpool;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import dev.imanity.antixray.libs.javassist.ClassPath;
import dev.imanity.antixray.libs.javassist.LoaderClassPath;
import java.util.Collections;
import java.util.WeakHashMap;
import dev.imanity.antixray.libs.javassist.ClassPool;
import java.util.Map;

public class ScopedClassPoolRepositoryImpl implements ScopedClassPoolRepository
{
    private static final ScopedClassPoolRepositoryImpl instance;
    private boolean prune;
    boolean pruneWhenCached;
    protected Map<ClassLoader, ScopedClassPool> registeredCLs;
    protected ClassPool classpool;
    protected ScopedClassPoolFactory factory;
    
    public static ScopedClassPoolRepository getInstance() {
        return ScopedClassPoolRepositoryImpl.instance;
    }
    
    private ScopedClassPoolRepositoryImpl() {
        this.prune = true;
        this.registeredCLs = Collections.synchronizedMap(new WeakHashMap<ClassLoader, ScopedClassPool>());
        this.factory = new ScopedClassPoolFactoryImpl();
        (this.classpool = ClassPool.getDefault()).insertClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader()));
    }
    
    @Override
    public boolean isPrune() {
        return this.prune;
    }
    
    @Override
    public void setPrune(final boolean prune) {
        this.prune = prune;
    }
    
    @Override
    public ScopedClassPool createScopedClassPool(final ClassLoader classLoader, final ClassPool classPool) {
        return this.factory.create(classLoader, classPool, this);
    }
    
    @Override
    public ClassPool findClassPool(final ClassLoader classLoader) {
        if (classLoader == null) {
            return this.registerClassLoader(ClassLoader.getSystemClassLoader());
        }
        return this.registerClassLoader(classLoader);
    }
    
    @Override
    public ClassPool registerClassLoader(final ClassLoader classLoader) {
        synchronized (this.registeredCLs) {
            if (this.registeredCLs.containsKey(classLoader)) {
                return this.registeredCLs.get(classLoader);
            }
            final ScopedClassPool scopedClassPool = this.createScopedClassPool(classLoader, this.classpool);
            this.registeredCLs.put(classLoader, scopedClassPool);
            return scopedClassPool;
        }
    }
    
    @Override
    public Map<ClassLoader, ScopedClassPool> getRegisteredCLs() {
        this.clearUnregisteredClassLoaders();
        return this.registeredCLs;
    }
    
    @Override
    public void clearUnregisteredClassLoaders() {
        List<ClassLoader> list = null;
        synchronized (this.registeredCLs) {
            for (final Map.Entry entry : this.registeredCLs.entrySet()) {
                if (((ScopedClassPool)entry.getValue()).isUnloadedClassLoader()) {
                    final ClassLoader classLoader = ((ScopedClassPool)entry.getValue()).getClassLoader();
                    if (classLoader != null) {
                        if (list == null) {
                            list = new ArrayList<ClassLoader>();
                        }
                        list.add(classLoader);
                    }
                    this.registeredCLs.remove(entry.getKey());
                }
            }
            if (list != null) {
                final Iterator<ClassLoader> iterator2 = list.iterator();
                while (iterator2.hasNext()) {
                    this.unregisterClassLoader(iterator2.next());
                }
            }
        }
    }
    
    @Override
    public void unregisterClassLoader(final ClassLoader classLoader) {
        synchronized (this.registeredCLs) {
            try (final ScopedClassPool scopedClassPool = this.registeredCLs.remove(classLoader)) {}
        }
    }
    
    public void insertDelegate(final ScopedClassPoolRepository scopedClassPoolRepository) {
    }
    
    @Override
    public void setClassPoolFactory(final ScopedClassPoolFactory factory) {
        this.factory = factory;
    }
    
    @Override
    public ScopedClassPoolFactory getClassPoolFactory() {
        return this.factory;
    }
    
    static {
        instance = new ScopedClassPoolRepositoryImpl();
    }
}
