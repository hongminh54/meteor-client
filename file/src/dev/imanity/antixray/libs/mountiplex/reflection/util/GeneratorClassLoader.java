// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util;

import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.GeneratedConstructor;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.GeneratedCodeInvoker;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.GeneratedInvoker;
import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLGeneratorClassLoaderBuilder;
import java.util.HashMap;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import java.security.ProtectionDomain;
import java.net.URL;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.util.Iterator;
import java.lang.reflect.Constructor;
import java.util.WeakHashMap;
import java.util.Map;

public abstract class GeneratorClassLoader extends ClassLoader
{
    private static final Map<String, Class<?>> staticClasses;
    private static WeakHashMap<ClassLoader, GeneratorClassLoader> loaders;
    private static final Constructor<? extends GeneratorClassLoader> implementationFactory;
    private static final GeneratorNotSupportedException implementationTypeFailure;
    
    private static void registerStaticClass(final Class<?> clazz) {
        GeneratorClassLoader.staticClasses.put(clazz.getName(), clazz);
    }
    
    public static GeneratorClassLoader get(final ClassLoader key) {
        final GeneratorClassLoader generatorClassLoader;
        if ((generatorClassLoader = GeneratorClassLoader.loaders.get(key)) != null) {
            return generatorClassLoader;
        }
        synchronized (GeneratorClassLoader.class) {
            final GeneratorClassLoader generatorClassLoader2;
            if ((generatorClassLoader2 = GeneratorClassLoader.loaders.get(key)) != null) {
                return generatorClassLoader2;
            }
            final GeneratorClassLoader create = create(key);
            final WeakHashMap loaders = new WeakHashMap((Map<? extends K, ? extends V>)GeneratorClassLoader.loaders);
            loaders.put(key, create);
            GeneratorClassLoader.loaders = loaders;
            return create;
        }
    }
    
    private static void remove(final ClassLoader classLoader) {
        synchronized (GeneratorClassLoader.class) {
            if (GeneratorClassLoader.loaders.containsKey(classLoader)) {
                final WeakHashMap loaders = new WeakHashMap((Map<? extends K, ? extends V>)GeneratorClassLoader.loaders);
                loaders.remove(classLoader);
                GeneratorClassLoader.loaders = loaders;
            }
        }
    }
    
    public static Class<?> findGeneratedClass(final String name) {
        final Iterator<GeneratorClassLoader> iterator = GeneratorClassLoader.loaders.values().iterator();
        while (iterator.hasNext()) {
            final Class<?> loadedClass = iterator.next().findLoadedClass(name);
            if (loadedClass != null) {
                return loadedClass;
            }
        }
        return null;
    }
    
    private static GeneratorClassLoader create(final ClassLoader classLoader) {
        if (classLoader instanceof GeneratorClassLoader) {
            return (GeneratorClassLoader)classLoader;
        }
        if (GeneratorClassLoader.implementationFactory != null) {
            try {
                return (GeneratorClassLoader)GeneratorClassLoader.implementationFactory.newInstance(classLoader);
            }
            catch (final Throwable t) {
                throw MountiplexUtil.uncheckedRethrow(t);
            }
        }
        throw GeneratorClassLoader.implementationTypeFailure;
    }
    
    protected GeneratorClassLoader(final ClassLoader parent) {
        super(parent);
    }
    
    private Class<?> superFindClass(final String name) {
        final Class<?> loadedClass = this.findLoadedClass(name);
        return (loadedClass != null) ? loadedClass : tryFindClass(this.getParent(), name);
    }
    
    private static Class<?> tryFindClass(final ClassLoader classLoader, final String s) {
        try {
            return MPLType.getClassByName(s, false, classLoader);
        }
        catch (final MPLType.LoaderClosedException ex) {
            throw ex;
        }
        catch (final ClassNotFoundException ex2) {
            return null;
        }
    }
    
    public Class<?> loadClass(final String s, final boolean resolve) {
        final Class clazz = GeneratorClassLoader.staticClasses.get(s);
        if (clazz != null) {
            return clazz;
        }
        try {
            return super.loadClass(s, resolve);
        }
        catch (final IllegalStateException ex) {
            if ("zip file closed".equals(ex.getMessage())) {
                throw new MPLType.LoaderClosedException();
            }
            throw new ClassNotFoundException("Failed to load class " + s, ex);
        }
    }
    
    public Class<?> findClass(final String s) {
        final Class<?> load;
        if ((load = ExtendedClassWriter.Deferred.load(s)) != null) {
            return load;
        }
        final GeneratorClassLoader generatorClassLoader = GeneratorClassLoader.loaders.get(GeneratorClassLoader.class.getClassLoader());
        if (generatorClassLoader != this) {
            try {
                if (generatorClassLoader != null) {
                    final Class<?> superFindClass;
                    if ((superFindClass = generatorClassLoader.superFindClass(s)) != null) {
                        return superFindClass;
                    }
                }
                else {
                    final Class<?> tryFindClass;
                    if ((tryFindClass = tryFindClass(GeneratorClassLoader.class.getClassLoader(), s)) != null) {
                        return tryFindClass;
                    }
                }
            }
            catch (final MPLType.LoaderClosedException ex) {
                remove(ExtendedClassWriter.class.getClassLoader());
            }
        }
        for (final GeneratorClassLoader generatorClassLoader2 : GeneratorClassLoader.loaders.values()) {
            if (generatorClassLoader2 != generatorClassLoader) {
                if (generatorClassLoader2 == this) {
                    continue;
                }
                try {
                    final Class<?> superFindClass2;
                    if ((superFindClass2 = generatorClassLoader2.superFindClass(s)) != null) {
                        return superFindClass2;
                    }
                    continue;
                }
                catch (final MPLType.LoaderClosedException ex2) {
                    remove(generatorClassLoader2.getParent());
                }
            }
        }
        throw new ClassNotFoundException(s);
    }
    
    public Package definePackage(final String name, final String specTitle, final String specVersion, final String specVendor, final String implTitle, final String implVersion, final String implVendor, final URL sealBase) {
        return super.definePackage(name, specTitle, specVersion, specVendor, implTitle, implVersion, implVendor, sealBase);
    }
    
    @Deprecated
    public Package getPackage(final String name) {
        return super.getPackage(name);
    }
    
    public Class<?> createClassFromBytecode(final String s, final byte[] array, final ProtectionDomain protectionDomain) {
        return this.createClassFromBytecode(s, array, protectionDomain, true);
    }
    
    public Class<?> createClassFromBytecode(final String name, final byte[] b, final ProtectionDomain protectionDomain, final boolean b2) {
        if (b2 && Resolver.isClassLoaderRemappingEnabled()) {
            return super.defineClass(name, b, 0, b.length, protectionDomain);
        }
        return this.defineClassFromBytecode(name, b, protectionDomain);
    }
    
    protected abstract Class<?> defineClassFromBytecode(final String p0, final byte[] p1, final ProtectionDomain p2);
    
    static {
        staticClasses = new HashMap<String, Class<?>>();
        GeneratorClassLoader.loaders = new WeakHashMap<ClassLoader, GeneratorClassLoader>();
        MountiplexUtil.registerUnloader(new Runnable() {
            @Override
            public void run() {
                GeneratorClassLoader.loaders = (WeakHashMap<ClassLoader, GeneratorClassLoader>)new WeakHashMap(0);
            }
        });
        Constructor<? extends GeneratorClassLoader> constructor = null;
        GeneratorNotSupportedException implementationTypeFailure2 = null;
        try {
            constructor = MPLGeneratorClassLoaderBuilder.buildImplementation().getConstructor(ClassLoader.class);
        }
        catch (final Throwable thrown) {
            implementationTypeFailure2 = new GeneratorNotSupportedException(thrown);
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to initialize generator class builder", thrown);
        }
        implementationFactory = constructor;
        implementationTypeFailure = implementationTypeFailure2;
        registerStaticClass(GeneratedInvoker.class);
        registerStaticClass(GeneratedCodeInvoker.class);
        registerStaticClass(GeneratedConstructor.class);
    }
    
    public static class GeneratorNotSupportedException extends RuntimeException
    {
        private static final long serialVersionUID = -362584700480972819L;
        
        public GeneratorNotSupportedException(final Throwable cause) {
            super("Generating classes is not supported on this JDK", cause);
        }
    }
}
