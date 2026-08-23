// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.resolver;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.GenericSignatureFormatError;
import java.lang.reflect.Type;
import dev.imanity.antixray.libs.mountiplex.reflection.util.StringBuffer;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.FieldDeclaration;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ClassResolver;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.MethodDeclaration;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ClassDeclaration;
import java.lang.reflect.Array;
import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.util.function.Function;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Field;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.HashMap;

public class Resolver
{
    private static Resolver resolver;
    private ClassDeclarationResolver classDeclarationResolverChain;
    private ClassPathResolver classPathResolverChain;
    private CompiledFieldNameResolver compiledFieldNameResolverChain;
    private CompiledMethodNameResolver compiledMethodNameResolverChain;
    private FieldNameResolver fieldNameResolverChain;
    private FieldAliasResolver fieldAliasResolverChain;
    private MethodNameResolver methodNameResolverChain;
    private MethodAliasResolver methodAliasResolverChain;
    private boolean enableClassLoaderRemapping;
    private final HashMap<String, ClassMeta> classCache;
    private final Map<Class<?>, ClassMeta> classTypeCache;
    private final PackageNameCache packageNameCache;
    
    private Resolver() {
        this.classDeclarationResolverChain = NoOpResolver.INSTANCE;
        this.classPathResolverChain = NoOpResolver.INSTANCE;
        this.compiledFieldNameResolverChain = NoOpResolver.INSTANCE;
        this.compiledMethodNameResolverChain = NoOpResolver.INSTANCE;
        this.fieldNameResolverChain = NoOpResolver.INSTANCE;
        this.fieldAliasResolverChain = NoOpResolver.INSTANCE;
        this.methodNameResolverChain = NoOpResolver.INSTANCE;
        this.methodAliasResolverChain = NoOpResolver.INSTANCE;
        this.enableClassLoaderRemapping = false;
        this.classCache = new HashMap<String, ClassMeta>();
        this.classTypeCache = new ConcurrentHashMap<Class<?>, ClassMeta>();
        this.packageNameCache = new PackageNameCache();
    }
    
    private void initResolver() {
        BoxedType.getBoxedTypes().forEach(clazz -> this.classTypeCache.put(clazz, new ClassMeta(clazz)));
        BoxedType.getUnboxedTypes().forEach(clazz2 -> this.classTypeCache.put(clazz2, new ClassMeta(clazz2)));
    }
    
    public static PackageNameCache getPackageNameCache() {
        return Resolver.resolver.packageNameCache;
    }
    
    public static boolean isClassLoaderRemappingEnabled() {
        return Resolver.resolver.enableClassLoaderRemapping;
    }
    
    public static void setClassLoaderRemappingEnabled(final boolean enableClassLoaderRemapping) {
        MPLType.setRemappingEnabled(Resolver.resolver.enableClassLoaderRemapping = enableClassLoaderRemapping);
    }
    
    public static boolean isPublic(final Field field) {
        return Modifier.isPublic(field.getModifiers()) && isPublic(field.getDeclaringClass());
    }
    
    public static boolean isPublic(final Method method) {
        return Modifier.isPublic(method.getModifiers()) && isPublic(method.getDeclaringClass());
    }
    
    public static boolean isPublic(final Constructor<?> constructor) {
        return Modifier.isPublic(constructor.getModifiers()) && isPublic(constructor.getDeclaringClass());
    }
    
    public static boolean isPublic(final Class<?> clazz) {
        return getMeta(clazz).isPublic;
    }
    
    public static ClassMeta getMeta(final Class<?> clazz) {
        try {
            return Resolver.resolver.classTypeCache.computeIfAbsent(clazz, ClassMeta::new);
        }
        catch (final IllegalStateException thrown) {
            MountiplexUtil.LOGGER.log(Level.WARNING, "Recursive getMeta called while initializing " + clazz, thrown);
            final ClassMeta classMeta = new ClassMeta(clazz);
            Resolver.resolver.classTypeCache.put(clazz, classMeta);
            return classMeta;
        }
    }
    
    public static Class<?> getClassByExactName(final String s) {
        final HashMap<String, ClassMeta> classCache = Resolver.resolver.classCache;
        synchronized (classCache) {
            final ClassMeta classMeta = classCache.get(s);
            if (classMeta != null && classMeta.type != null && MPLType.getName(classMeta.type).equals(s)) {
                return classMeta.type;
            }
        }
        return MPLType.getClassByName(s);
    }
    
    public static Class<?> loadClass(final String s, final boolean b) {
        return loadClass(s, b, Resolver.class.getClassLoader());
    }
    
    public static Class<?> loadClass(final String key, final boolean loaded, final ClassLoader classLoader) {
        final HashMap<String, ClassMeta> classCache = Resolver.resolver.classCache;
        synchronized (classCache) {
            ClassMeta meta = classCache.get(key);
            if (meta == null) {
                final Class<?> loadClassImpl = loadClassImpl(key, loaded, classLoader);
                if (loadClassImpl == null) {
                    classCache.put(key, ClassMeta.MISSING);
                    return null;
                }
                meta = getMeta(loadClassImpl);
                meta.loaded = loaded;
                classCache.put(key, meta);
            }
            if (!meta.loaded && loaded) {
                if (meta.type != null) {
                    initializeClass(meta.type);
                }
                meta.loaded = true;
            }
            return meta.type;
        }
    }
    
    private static Class<?> loadClassImpl(final String s, final boolean b, final ClassLoader classLoader) {
        final Class<?> unboxedType = BoxedType.getUnboxedType(s);
        if (unboxedType != null) {
            return unboxedType;
        }
        if (s.endsWith("[]")) {
            final Class<?> loadClass = loadClass(s.substring(0, s.length() - 2), b);
            if (loadClass == null) {
                return null;
            }
            return Array.newInstance(loadClass, 0).getClass();
        }
        else {
            if (!Resolver.resolver.packageNameCache.canExist(s)) {
                return null;
            }
            final String resolveClassPath = resolveClassPath(s);
            try {
                final Class<?> classByName = MPLType.getClassByName(resolveClassPath, b, classLoader);
                Resolver.resolver.packageNameCache.addPackageOfClassName(s);
                return classByName;
            }
            catch (final ExceptionInInitializerError exceptionInInitializerError) {
                MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to initialize class '" + resolveClassPath + "':", exceptionInInitializerError.getCause());
                return null;
            }
            catch (final ClassNotFoundException ex) {
                final int lastIndex = s.lastIndexOf(46);
                if (lastIndex != -1 && Character.isUpperCase(s.charAt(s.lastIndexOf(46, lastIndex - 1) + 1))) {
                    final String substring = s.substring(0, lastIndex);
                    if (loadClass(substring, false) != null) {
                        return loadClass(substring + "$" + s.substring(lastIndex + 1), b);
                    }
                }
                return null;
            }
        }
    }
    
    public static void initializeClass(final Class<?> obj) {
        final String name = MPLType.getName(obj);
        try {
            MPLType.getClassByName(name, true, obj.getClassLoader());
        }
        catch (final ExceptionInInitializerError exceptionInInitializerError) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to initialize class '" + name + "':", exceptionInInitializerError.getCause());
        }
        catch (final ClassNotFoundException ex) {}
        catch (final Throwable thrown) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Unhandled error initializing class " + obj, thrown);
        }
    }
    
    public static void registerClassDeclarationResolver(final ClassDeclarationResolver classDeclarationResolver) {
        Resolver.resolver.classDeclarationResolverChain = ChainResolver.chain(Resolver.resolver.classDeclarationResolverChain, classDeclarationResolver);
    }
    
    public static void registerClassResolver(final ClassPathResolver classPathResolver) {
        Resolver.resolver.classPathResolverChain = ChainResolver.chain(Resolver.resolver.classPathResolverChain, classPathResolver);
        Resolver.resolver.classCache.clear();
        Resolver.resolver.packageNameCache.reset();
    }
    
    public static void registerCompiledFieldResolver(final CompiledFieldNameResolver compiledFieldNameResolver) {
        Resolver.resolver.compiledFieldNameResolverChain = ChainResolver.chain(Resolver.resolver.compiledFieldNameResolverChain, compiledFieldNameResolver);
    }
    
    public static void registerCompiledMethodResolver(final CompiledMethodNameResolver compiledMethodNameResolver) {
        Resolver.resolver.compiledMethodNameResolverChain = ChainResolver.chain(Resolver.resolver.compiledMethodNameResolverChain, compiledMethodNameResolver);
    }
    
    public static void registerFieldResolver(final FieldNameResolver fieldNameResolver) {
        Resolver.resolver.fieldNameResolverChain = ChainResolver.chain(Resolver.resolver.fieldNameResolverChain, fieldNameResolver);
    }
    
    public static void registerFieldAliasResolver(final FieldAliasResolver fieldAliasResolver) {
        Resolver.resolver.fieldAliasResolverChain = ChainResolver.chain(Resolver.resolver.fieldAliasResolverChain, fieldAliasResolver);
    }
    
    public static void registerMethodResolver(final MethodNameResolver methodNameResolver) {
        Resolver.resolver.methodNameResolverChain = ChainResolver.chain(Resolver.resolver.methodNameResolverChain, methodNameResolver);
    }
    
    public static void registerMethodAliasResolver(final MethodAliasResolver methodAliasResolver) {
        Resolver.resolver.methodAliasResolverChain = ChainResolver.chain(Resolver.resolver.methodAliasResolverChain, methodAliasResolver);
    }
    
    public static ClassDeclaration resolveClassDeclaration(final String s) {
        final Class<?> loadClass = loadClass(s, false);
        return (loadClass == null) ? null : resolveClassDeclaration(s, loadClass);
    }
    
    public static ClassDeclaration resolveClassDeclaration(final Class<?> clazz) {
        return resolveClassDeclaration(MPLType.getName(clazz), clazz);
    }
    
    public static ClassDeclaration resolveClassDeclaration(final String s, final Class<?> clazz) {
        return Resolver.resolver.classDeclarationResolverChain.resolveClassDeclaration(s, clazz);
    }
    
    public static Map<String, String> resolveClassVariables(final String s, final Class<?> clazz) {
        final HashMap hashMap = new HashMap();
        Resolver.resolver.classDeclarationResolverChain.resolveClassVariables(s, clazz, hashMap);
        return hashMap;
    }
    
    public static String resolveClassPath(final String s) {
        return Resolver.resolver.classPathResolverChain.resolveClassPath(s);
    }
    
    public static boolean canLoadClassPath(final String s) {
        return Resolver.resolver.classPathResolverChain.canLoadClassPath(s);
    }
    
    public static String resolveFieldName(final Class<?> clazz, final String s) {
        return Resolver.resolver.fieldNameResolverChain.resolveFieldName(clazz, s);
    }
    
    public static String resolveFieldAlias(final Field field, final String s) {
        return Resolver.resolver.fieldAliasResolverChain.resolveFieldAlias(field, s);
    }
    
    public static String resolveMethodName(final Class<?> clazz, final String s, final Class<?>[] array) {
        return Resolver.resolver.methodNameResolverChain.resolveMethodName(clazz, s, array);
    }
    
    public static String resolveMethodAlias(final Method method, final String s) {
        return Resolver.resolver.methodAliasResolverChain.resolveMethodAlias(method, s);
    }
    
    public static String resolveCompiledFieldName(final Class<?> clazz, final String s) {
        return Resolver.resolver.compiledFieldNameResolverChain.resolveCompiledFieldName(clazz, s);
    }
    
    public static String resolveCompiledMethodName(final Class<?> clazz, final String s, final Class<?>[] array) {
        return Resolver.resolver.compiledMethodNameResolverChain.resolveCompiledMethodName(clazz, s, array);
    }
    
    public static MethodDeclaration resolveMethodAlias(final TypeDeclaration typeDeclaration, final Method method) {
        final ClassResolver classResolver = new ClassResolver();
        classResolver.setDeclaredClass(method.getDeclaringClass());
        final MethodDeclaration methodDeclaration = new MethodDeclaration(classResolver, method);
        final String resolveMethodAliasInType = resolveMethodAliasInType(typeDeclaration, method);
        if (resolveMethodAliasInType != null) {
            return methodDeclaration.setAlias(resolveMethodAliasInType);
        }
        final TypeDeclaration[] superTypes = typeDeclaration.getSuperTypes();
        for (int length = superTypes.length, i = 0; i < length; ++i) {
            final String resolveMethodAliasInType2 = resolveMethodAliasInType(superTypes[i], method);
            if (resolveMethodAliasInType2 != null) {
                return methodDeclaration.setAlias(resolveMethodAliasInType2);
            }
        }
        return methodDeclaration;
    }
    
    private static String resolveMethodAliasInType(final TypeDeclaration typeDeclaration, final Method method) {
        final ClassDeclaration resolveClassDeclaration = resolveClassDeclaration(typeDeclaration.type);
        return (resolveClassDeclaration == null) ? null : resolveClassDeclaration.resolveMethodAlias(method);
    }
    
    public static FieldDeclaration findField(final Class<?> declaredClass, final String s) {
        final ClassResolver classResolver = new ClassResolver();
        classResolver.setDeclaredClass(declaredClass);
        classResolver.setLogErrors(true);
        return new FieldDeclaration(classResolver, StringBuffer.of(s)).discover();
    }
    
    public static MethodDeclaration findMethod(final Class<?> declaredClass, final String s) {
        final ClassResolver classResolver = new ClassResolver();
        classResolver.setDeclaredClass(declaredClass);
        classResolver.setLogErrors(true);
        return new MethodDeclaration(classResolver, s).discover();
    }
    
    public static MethodDeclaration findMethod(final MethodDeclaration methodDeclaration) {
        return methodDeclaration.discover();
    }
    
    public static Method resolveAndGetDeclaredMethod(final Class<?> clazz, final String s, final Class<?>... array) {
        return MPLType.getDeclaredMethod(clazz, resolveMethodName(clazz, s, array), array);
    }
    
    public static Field resolveAndGetDeclaredField(final Class<?> clazz, final String s) {
        return MPLType.getDeclaredField(clazz, resolveFieldName(clazz, s));
    }
    
    static {
        (Resolver.resolver = new Resolver()).initResolver();
        MountiplexUtil.registerUnloader(new Runnable() {
            @Override
            public void run() {
                Resolver.resolver = new Resolver(null);
                Resolver.resolver.initResolver();
            }
        });
    }
    
    public static final class ClassMeta
    {
        public static final ClassMeta MISSING;
        public final Class<?> type;
        protected boolean loaded;
        public final TypeDeclaration typeDec;
        public final TypeDeclaration[] interfaces;
        public final TypeDeclaration superType;
        public final boolean isPublic;
        
        public ClassMeta(final Class<?> clazz) {
            this(clazz, false);
        }
        
        public ClassMeta(final Class<?> type, final boolean loaded) {
            this.type = type;
            this.loaded = loaded;
            this.isPublic = isPublicClass(type);
            if (type != null) {
                this.typeDec = new TypeDeclaration(ClassResolver.DEFAULT, type);
                this.superType = findSuperType(this.typeDec);
                this.interfaces = findInterfaces(this.typeDec);
            }
            else {
                this.typeDec = TypeDeclaration.INVALID;
                this.superType = TypeDeclaration.OBJECT;
                this.interfaces = new TypeDeclaration[0];
            }
        }
        
        private static boolean isPublicClass(final Class<?> clazz) {
            if (clazz == null) {
                return true;
            }
            if (!Modifier.isPublic(clazz.getModifiers())) {
                return false;
            }
            try {
                return isPublicClass(clazz.getDeclaringClass());
            }
            catch (final Throwable thrown) {
                MountiplexUtil.LOGGER.log(Level.WARNING, "Failed to detect whether " + MPLType.getName(clazz) + " is public. Assuming it is not public.", thrown);
                return false;
            }
        }
        
        private static TypeDeclaration fixResolveGenericTypes(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
            return typeDeclaration2;
        }
        
        private static TypeDeclaration findSuperType(final TypeDeclaration typeDeclaration) {
            try {
                final Type genericSuperclass = typeDeclaration.type.getGenericSuperclass();
                return (genericSuperclass == null) ? null : toTypeDec(genericSuperclass);
            }
            catch (final GenericSignatureFormatError | TypeNotPresentException | MalformedParameterizedTypeException ex) {
                final Class<?> superclass = typeDeclaration.type.getSuperclass();
                return (superclass == null) ? null : fixResolveGenericTypes(typeDeclaration, toTypeDec(superclass));
            }
        }
        
        private static TypeDeclaration[] findInterfaces(final TypeDeclaration typeDeclaration) {
            try {
                final Type[] genericInterfaces = typeDeclaration.type.getGenericInterfaces();
                final TypeDeclaration[] array = new TypeDeclaration[genericInterfaces.length];
                for (int i = 0; i < array.length; ++i) {
                    array[i] = toTypeDec(genericInterfaces[i]);
                }
                return array;
            }
            catch (final GenericSignatureFormatError | TypeNotPresentException | MalformedParameterizedTypeException ex) {
                final Class<?>[] interfaces = typeDeclaration.type.getInterfaces();
                final TypeDeclaration[] array2 = new TypeDeclaration[interfaces.length];
                for (int j = 0; j < array2.length; ++j) {
                    array2[j] = fixResolveGenericTypes(typeDeclaration, toTypeDec(interfaces[j]));
                }
                return array2;
            }
        }
        
        private static TypeDeclaration toTypeDec(final Type type) {
            final ClassMeta classMeta;
            if (type instanceof Class && (classMeta = Resolver.resolver.classTypeCache.get(type)) != null) {
                return classMeta.typeDec;
            }
            return new TypeDeclaration(ClassResolver.DEFAULT, type);
        }
        
        static {
            MISSING = new ClassMeta(null, true);
        }
    }
}
