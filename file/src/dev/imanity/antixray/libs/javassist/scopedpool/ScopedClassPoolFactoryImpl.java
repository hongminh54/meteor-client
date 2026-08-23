// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.scopedpool;

import dev.imanity.antixray.libs.javassist.ClassPool;

public class ScopedClassPoolFactoryImpl implements ScopedClassPoolFactory
{
    @Override
    public ScopedClassPool create(final ClassLoader classLoader, final ClassPool classPool, final ScopedClassPoolRepository scopedClassPoolRepository) {
        return new ScopedClassPool(classLoader, classPool, scopedClassPoolRepository, false);
    }
    
    @Override
    public ScopedClassPool create(final ClassPool classPool, final ScopedClassPoolRepository scopedClassPoolRepository) {
        return new ScopedClassPool(null, classPool, scopedClassPoolRepository, true);
    }
}
