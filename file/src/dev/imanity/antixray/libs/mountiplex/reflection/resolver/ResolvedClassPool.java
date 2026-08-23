// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.resolver;

import java.util.ArrayList;
import java.util.Hashtable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.javassist.compiler.MemberResolver;
import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import java.net.URL;
import dev.imanity.antixray.libs.javassist.NotFoundException;
import java.io.IOException;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import dev.imanity.antixray.libs.mountiplex.reflection.util.GeneratorClassLoader;
import java.security.ProtectionDomain;
import dev.imanity.antixray.libs.javassist.CtClass;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.ClassBytecodeLoader;
import java.lang.ref.Reference;
import java.util.Map;
import java.util.List;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Remapping;
import java.io.Closeable;
import dev.imanity.antixray.libs.javassist.ClassPool;

public final class ResolvedClassPool extends ClassPool implements Closeable
{
    private static final Remapping.Lookup NO_REMAPPINGS;
    private static final List<ResolvedClassPool> CACHED_CLASS_POOLS;
    private static final Map<ClassPool, Reference<Map<String, String>>> globalInvalidNamesMap;
    private boolean ignoreRemapper;
    private Remapping.Lookup remappings;
    
    public static ResolvedClassPool create() {
        synchronized (ResolvedClassPool.CACHED_CLASS_POOLS) {
            if (!ResolvedClassPool.CACHED_CLASS_POOLS.isEmpty()) {
                return ResolvedClassPool.CACHED_CLASS_POOLS.remove(ResolvedClassPool.CACHED_CLASS_POOLS.size() - 1);
            }
        }
        return new ResolvedClassPool();
    }
    
    @Override
    public void close() {
        this.clearImportedPackages();
        this.remappings = ResolvedClassPool.NO_REMAPPINGS;
        resetInvalidNames(this);
        synchronized (ResolvedClassPool.CACHED_CLASS_POOLS) {
            ResolvedClassPool.CACHED_CLASS_POOLS.add(this);
        }
    }
    
    private ResolvedClassPool() {
        this.ignoreRemapper = false;
        this.remappings = ResolvedClassPool.NO_REMAPPINGS;
        this.appendClassPath(ClassBytecodeLoader.CLASSPATH);
    }
    
    public Remapping.Lookup getRemappings() {
        return this.remappings;
    }
    
    public void setRemappings(final Remapping.Lookup remappings) {
        this.remappings = remappings;
    }
    
    @Override
    public Class<?> toClass(final CtClass ctClass, final Class<?> clazz, final ClassLoader classLoader, final ProtectionDomain protectionDomain) {
        if (classLoader instanceof GeneratorClassLoader && clazz == null) {
            final GeneratorClassLoader generatorClassLoader = (GeneratorClassLoader)classLoader;
            try {
                return generatorClassLoader.createClassFromBytecode(ctClass.getName(), ctClass.toBytecode(), protectionDomain);
            }
            catch (final IOException ex) {
                throw new CannotCompileException(ex);
            }
        }
        return super.toClass(ctClass, clazz, classLoader, protectionDomain);
    }
    
    public CtClass getWithoutResolving(final String s) {
        final boolean ignoreRemapper = this.ignoreRemapper;
        try {
            this.ignoreRemapper = true;
            return super.get(s);
        }
        finally {
            this.ignoreRemapper = ignoreRemapper;
        }
    }
    
    public IgnoreToken ignoreResolver() {
        final boolean ignoreRemapper = this.ignoreRemapper;
        this.ignoreRemapper = true;
        return new IgnoreToken(ignoreRemapper);
    }
    
    @Override
    public CtClass get(final String s) {
        if (this.ignoreRemapper) {
            return super.get(s);
        }
        if (s == null) {
            return null;
        }
        final String resolveClassPath = this.resolveClassPath(s);
        if (resolveClassPath == null) {
            throw new NotFoundException(s);
        }
        final CtClass cached = this.getCached(resolveClassPath);
        if (cached != null) {
            return cached;
        }
        try {
            this.ignoreRemapper = true;
            final CtClass get0 = super.get0(resolveClassPath, false);
            if (get0 == null) {
                throw new NotFoundException(s);
            }
            Resolver.getPackageNameCache().addPackageOfClassName(s);
            return get0;
        }
        finally {
            this.ignoreRemapper = false;
        }
    }
    
    @Override
    public URL find(final String s) {
        if (this.ignoreRemapper) {
            return super.find(s);
        }
        if (s == null) {
            return null;
        }
        final String resolveClassPath = this.resolveClassPath(s);
        if (resolveClassPath == null) {
            return null;
        }
        final URL find = super.find(resolveClassPath);
        if (find == null) {
            return null;
        }
        Resolver.getPackageNameCache().addPackage(s);
        return find;
    }
    
    @Override
    protected CtClass createCtClass(final String s, final boolean b) {
        if (this.ignoreRemapper) {
            return super.createCtClass(s, b);
        }
        String s2 = s;
        if (s2.charAt(0) == '[') {
            s2 = Descriptor.toClassName(s);
        }
        int n;
        for (n = 0; s2.endsWith("[]"); s2 = s2.substring(0, s2.length() - 2), ++n) {}
        String str = this.resolveClassPath(s2);
        if (str == null) {
            return null;
        }
        while (n-- > 0) {
            str += "[]";
        }
        try {
            this.ignoreRemapper = true;
            final CtClass ctClass = super.createCtClass(str, b);
            if (ctClass != null) {
                Resolver.getPackageNameCache().addPackageOfClassName(s);
            }
            return ctClass;
        }
        finally {
            this.ignoreRemapper = false;
        }
    }
    
    private final String resolveClassPath(final String s) {
        if (s.startsWith("MPL_NOREMAP$")) {
            final String substring = s.substring("MPL_NOREMAP$".length());
            if (!Resolver.getPackageNameCache().canExist(substring)) {
                return null;
            }
            return substring;
        }
        else {
            if (!Resolver.getPackageNameCache().canExist(s)) {
                return null;
            }
            return Resolver.resolveClassPath(s);
        }
    }
    
    private static Map<ClassPool, Reference<Map<String, String>>> findInvalidNamesMap() {
        try {
            final Field declaredField = MemberResolver.class.getDeclaredField("invalidNamesMap");
            declaredField.setAccessible(true);
            final Object value = declaredField.get(null);
            declaredField.setAccessible(false);
            return (Map<ClassPool, Reference<Map<String, String>>>)value;
        }
        catch (final Throwable t) {
            throw MountiplexUtil.uncheckedRethrow(t);
        }
    }
    
    private static void resetInvalidNames(final ClassPool key) {
        synchronized (MemberResolver.class) {
            ResolvedClassPool.globalInvalidNamesMap.computeIfPresent(key, (p0, reference) -> {
                final Map map = reference.get();
                if (map == null || map.isEmpty()) {
                    return reference;
                }
                else {
                    new WeakReference(new Hashtable());
                    return;
                }
            });
        }
    }
    
    static {
        NO_REMAPPINGS = Remapping.createLookup();
        CACHED_CLASS_POOLS = new ArrayList<ResolvedClassPool>();
        globalInvalidNamesMap = findInvalidNamesMap();
    }
    
    public final class IgnoreToken implements AutoCloseable
    {
        private final boolean ignoreResolver;
        
        private IgnoreToken(final boolean ignoreResolver) {
            this.ignoreResolver = ignoreResolver;
        }
        
        @Override
        public void close() {
            ResolvedClassPool.this.ignoreRemapper = this.ignoreResolver;
        }
    }
}
