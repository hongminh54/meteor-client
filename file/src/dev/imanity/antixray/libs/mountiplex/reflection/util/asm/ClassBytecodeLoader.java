// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.asm;

import dev.imanity.antixray.libs.mountiplex.reflection.util.GeneratorClassLoader;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import dev.imanity.antixray.libs.javassist.ClassClassPath;
import java.io.IOException;
import dev.imanity.antixray.libs.asm.MethodVisitor;
import dev.imanity.antixray.libs.asm.ClassVisitor;
import dev.imanity.antixray.libs.asm.ClassReader;
import java.util.function.Supplier;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import dev.imanity.antixray.libs.asm.ClassWriter;
import java.net.MalformedURLException;
import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URL;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import dev.imanity.antixray.libs.javassist.ClassPath;

public class ClassBytecodeLoader
{
    public static final ClassPath CLASSPATH;
    private static final ClassPath SYSTEM;
    
    public static InputStream getResourceAsStream(final Class<?> clazz2) {
        final InputStream cleanBytecode = cleanBytecode(() -> {
            new StringBuilder().append((char)47).append(MPLType.getInternalName(clazz2)).append(".class").toString();
            return ClassBytecodeLoader.class.getResourceAsStream(name);
        });
        if (cleanBytecode == null) {
            return new ByteArrayInputStream(generateMockByteCode(clazz2));
        }
        return cleanBytecode;
    }
    
    public static URL getResource(final Class<?> clazz) {
        final String string = '/' + MPLType.getInternalName(clazz) + ".class";
        final URL resource = ClassBytecodeLoader.class.getResource(string);
        return (resource != null) ? resource : generatedURL(clazz, string);
    }
    
    public static URL getResourceGenerated(final Class<?> clazz) {
        return generatedURL(clazz, '/' + MPLType.getInternalName(clazz) + ".class");
    }
    
    private static URL generatedURL(final Class<?> obj, final String file) {
        try {
            return new URL("bytecode", "mountiplex", 0, file, new URLStreamHandler() {
                @Override
                protected URLConnection openConnection(final URL url) {
                    return new URLConnection(url) {
                        @Override
                        public InputStream getInputStream() {
                            return new ByteArrayInputStream(generateMockByteCode(obj));
                        }
                        
                        @Override
                        public void connect() {
                        }
                    };
                }
            });
        }
        catch (final MalformedURLException thrown) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Unhandled error parsing generated url for " + obj, thrown);
            return null;
        }
    }
    
    private static byte[] generateMockByteCode(final Class<?> clazz) {
        final ClassWriter classWriter = new ClassWriter(0);
        Class<Object> superclass = clazz.getSuperclass();
        if (superclass == null) {
            superclass = Object.class;
        }
        if (clazz.isAssignableFrom(superclass)) {
            throw new IllegalStateException("Super class '" + MPLType.getName(superclass) + "' of '" + MPLType.getName(clazz) + "' implements itself!");
        }
        classWriter.visit(52, clazz.getModifiers(), MPLType.getInternalName(clazz), null, MPLType.getInternalName(superclass), MPLType.getInternalNames(clazz.getInterfaces()));
        for (final Field field : clazz.getDeclaredFields()) {
            classWriter.visitField(field.getModifiers(), MPLType.getName(field), MPLType.getDescriptor(field.getType()), null, null).visitEnd();
        }
        for (final Constructor constructor : clazz.getDeclaredConstructors()) {
            classWriter.visitMethod(constructor.getModifiers(), "<init>", MPLType.getConstructorDescriptor(constructor), null, MPLType.getInternalNames(constructor.getExceptionTypes())).visitEnd();
        }
        for (final Method method : clazz.getDeclaredMethods()) {
            classWriter.visitMethod(method.getModifiers() & 0xFFFFFFBF, MPLType.getName(method), MPLType.getMethodDescriptor(method), null, MPLType.getInternalNames(method.getExceptionTypes())).visitEnd();
        }
        classWriter.visitEnd();
        return classWriter.toByteArray();
    }
    
    private static InputStream cleanBytecode(final Supplier<InputStream> supplier) {
        try (final InputStream inputStream = supplier.get()) {
            if (inputStream == null) {
                final InputStream inputStream2 = null;
                if (inputStream != null) {
                    inputStream.close();
                }
                return inputStream2;
            }
            final ClassReader classReader = new ClassReader(inputStream);
            final ClassWriter classWriter = new ClassWriter(0);
            classReader.accept(new ClassVisitor(589824, classWriter) {
                @Override
                public MethodVisitor visitMethod(final int n, final String s, final String s2, final String s3, final String[] array) {
                    return super.visitMethod(n & 0xFFFFFFBF, s, s2, s3, array);
                }
            }, 0);
            return new ByteArrayInputStream(classWriter.toByteArray());
        }
        catch (final IOException | IllegalArgumentException ex) {
            return supplier.get();
        }
    }
    
    static {
        CLASSPATH = new CBLObjectClassPath();
        SYSTEM = new ClassClassPath(Object.class);
    }
    
    private static final class CBLObjectClassPath implements ClassPath
    {
        private final ClassLoader mountiplexClassLoader;
        private final ClassLoader fallbackClassLoader;
        private final boolean fallbackIsMountiplex;
        
        private CBLObjectClassPath() {
            this.mountiplexClassLoader = ClassBytecodeLoader.class.getClassLoader();
            this.fallbackClassLoader = Thread.currentThread().getContextClassLoader();
            this.fallbackIsMountiplex = (this.mountiplexClassLoader == this.fallbackClassLoader);
        }
        
        @Override
        public InputStream openClassfile(final String s) {
            final InputStream openClassfile;
            if (s.startsWith("java.") && (openClassfile = ClassBytecodeLoader.SYSTEM.openClassfile(s)) != null) {
                return openClassfile;
            }
            if (Resolver.canLoadClassPath(s)) {
                new StringBuilder().append(s.replace('.', '/')).append(".class").toString();
                final InputStream access$300;
                if ((access$300 = cleanBytecode(() -> this.fallbackClassLoader.getResourceAsStream(name))) != null) {
                    return access$300;
                }
                final InputStream access$301;
                if (!this.fallbackIsMountiplex && (access$301 = cleanBytecode(() -> this.mountiplexClassLoader.getResourceAsStream(name2))) != null) {
                    return access$301;
                }
                final Class<?> generatedClass = GeneratorClassLoader.findGeneratedClass(s);
                if (generatedClass != null) {
                    return new ByteArrayInputStream(generateMockByteCode(generatedClass));
                }
                final InputStream openClassfile2;
                if ((openClassfile2 = ClassBytecodeLoader.SYSTEM.openClassfile(s)) != null) {
                    return openClassfile2;
                }
            }
            Class<?> classByExactName;
            try {
                classByExactName = Resolver.getClassByExactName(s);
            }
            catch (final ClassNotFoundException ex) {
                return null;
            }
            return new ByteArrayInputStream(generateMockByteCode(classByExactName));
        }
        
        @Override
        public URL find(final String s) {
            final URL find;
            if (s.startsWith("java.") && (find = ClassBytecodeLoader.SYSTEM.find(s)) != null) {
                return find;
            }
            final String string = s.replace('.', '/') + ".class";
            if (Resolver.canLoadClassPath(s)) {
                final URL resource;
                if ((resource = this.fallbackClassLoader.getResource(string)) != null) {
                    return resource;
                }
                final URL resource2;
                if (!this.fallbackIsMountiplex && (resource2 = this.mountiplexClassLoader.getResource(string)) != null) {
                    return resource2;
                }
                final Class<?> generatedClass = GeneratorClassLoader.findGeneratedClass(s);
                if (generatedClass != null) {
                    return ClassBytecodeLoader.getResourceGenerated(generatedClass);
                }
                final URL find2;
                if ((find2 = ClassBytecodeLoader.SYSTEM.find(s)) != null) {
                    return find2;
                }
            }
            try {
                return generatedURL(Resolver.getClassByExactName(s), string);
            }
            catch (final Throwable t) {
                return null;
            }
        }
    }
}
