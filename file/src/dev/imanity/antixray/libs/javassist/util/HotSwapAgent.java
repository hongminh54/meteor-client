// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.util;

import dev.imanity.antixray.libs.javassist.ClassPool;
import java.util.zip.ZipEntry;
import java.util.jar.JarEntry;
import java.io.OutputStream;
import java.util.jar.JarOutputStream;
import java.io.FileOutputStream;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.io.File;
import com.sun.tools.attach.VirtualMachine;
import java.lang.management.ManagementFactory;
import java.lang.instrument.UnmodifiableClassException;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import dev.imanity.antixray.libs.javassist.NotFoundException;
import java.lang.instrument.ClassDefinition;
import dev.imanity.antixray.libs.javassist.CtClass;
import java.lang.instrument.Instrumentation;

public class HotSwapAgent
{
    private static Instrumentation instrumentation;
    
    public Instrumentation instrumentation() {
        return HotSwapAgent.instrumentation;
    }
    
    public static void premain(final String s, final Instrumentation instrumentation) {
        agentmain(s, instrumentation);
    }
    
    public static void agentmain(final String s, final Instrumentation instrumentation) {
        if (!instrumentation.isRedefineClassesSupported()) {
            throw new RuntimeException("this JVM does not support redefinition of classes");
        }
        HotSwapAgent.instrumentation = instrumentation;
    }
    
    public static void redefine(final Class<?> clazz, final CtClass ctClass) {
        redefine(new Class[] { clazz }, new CtClass[] { ctClass });
    }
    
    public static void redefine(final Class<?>[] array, final CtClass[] array2) {
        startAgent();
        final ClassDefinition[] array3 = new ClassDefinition[array.length];
        for (int i = 0; i < array.length; ++i) {
            array3[i] = new ClassDefinition(array[i], array2[i].toBytecode());
        }
        try {
            HotSwapAgent.instrumentation.redefineClasses(array3);
        }
        catch (final ClassNotFoundException ex) {
            throw new NotFoundException(ex.getMessage(), ex);
        }
        catch (final UnmodifiableClassException ex2) {
            throw new CannotCompileException(ex2.getMessage(), ex2);
        }
    }
    
    private static void startAgent() {
        if (HotSwapAgent.instrumentation != null) {
            return;
        }
        try {
            final File jarFile = createJarFile();
            final String name = ManagementFactory.getRuntimeMXBean().getName();
            final VirtualMachine attach = VirtualMachine.attach(name.substring(0, name.indexOf(64)));
            attach.loadAgent(jarFile.getAbsolutePath(), null);
            attach.detach();
        }
        catch (final Exception ex) {
            throw new NotFoundException("hotswap agent", ex);
        }
        for (int i = 0; i < 10; ++i) {
            if (HotSwapAgent.instrumentation != null) {
                return;
            }
            try {
                Thread.sleep(1000L);
            }
            catch (final InterruptedException ex2) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        throw new NotFoundException("hotswap agent (timeout)");
    }
    
    public static File createAgentJarFile(final String pathname) {
        return createJarFile(new File(pathname));
    }
    
    private static File createJarFile() {
        final File tempFile = File.createTempFile("agent", ".jar");
        tempFile.deleteOnExit();
        return createJarFile(tempFile);
    }
    
    private static File createJarFile(final File file) {
        final Manifest man = new Manifest();
        final Attributes mainAttributes = man.getMainAttributes();
        mainAttributes.put(Attributes.Name.MANIFEST_VERSION, "1.0");
        mainAttributes.put(new Attributes.Name("Premain-Class"), HotSwapAgent.class.getName());
        mainAttributes.put(new Attributes.Name("Agent-Class"), HotSwapAgent.class.getName());
        mainAttributes.put(new Attributes.Name("Can-Retransform-Classes"), "true");
        mainAttributes.put(new Attributes.Name("Can-Redefine-Classes"), "true");
        JarOutputStream jarOutputStream = null;
        try {
            jarOutputStream = new JarOutputStream(new FileOutputStream(file), man);
            final String name = HotSwapAgent.class.getName();
            jarOutputStream.putNextEntry(new JarEntry(name.replace('.', '/') + ".class"));
            jarOutputStream.write(ClassPool.getDefault().get(name).toBytecode());
            jarOutputStream.closeEntry();
        }
        finally {
            if (jarOutputStream != null) {
                jarOutputStream.close();
            }
        }
        return file;
    }
    
    static {
        HotSwapAgent.instrumentation = null;
    }
}
