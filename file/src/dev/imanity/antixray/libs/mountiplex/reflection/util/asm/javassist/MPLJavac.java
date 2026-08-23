// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.asm.javassist;

import dev.imanity.antixray.libs.javassist.CtConstructor;
import dev.imanity.antixray.libs.javassist.CtField;
import dev.imanity.antixray.libs.javassist.ClassMap;
import dev.imanity.antixray.libs.javassist.CtMethod;
import dev.imanity.antixray.libs.javassist.ClassPath;
import dev.imanity.antixray.libs.javassist.ByteArrayClassPath;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.javassist.compiler.SymbolTable;
import dev.imanity.antixray.libs.javassist.bytecode.Bytecode;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.ResolvedClassPool;
import dev.imanity.antixray.libs.javassist.CtClass;
import java.lang.reflect.Field;
import dev.imanity.antixray.libs.javassist.compiler.Javac;
import dev.imanity.antixray.libs.mountiplex.reflection.util.NullInstantiator;

public class MPLJavac
{
    private static final NullInstantiator<Javac> javac_instantiator;
    private static final Field javac_gen;
    private static final Field javac_stable;
    private static final Field javac_bytecode;
    
    public static Javac create(final CtClass ctClass) {
        final ClassPool classPool = ctClass.getClassPool();
        if (!(classPool instanceof ResolvedClassPool)) {
            throw new IllegalArgumentException("Class " + ctClass.getName() + " does not use a resolved class pool");
        }
        try {
            final Javac obj = MPLJavac.javac_instantiator.create();
            final Bytecode value = new Bytecode(ctClass.getClassFile2().getConstPool(), 0, 0);
            MPLJavac.javac_gen.set(obj, new MPLJvstCodeGen(value, ctClass, (ResolvedClassPool)classPool));
            MPLJavac.javac_stable.set(obj, new SymbolTable());
            MPLJavac.javac_bytecode.set(obj, value);
            return obj;
        }
        catch (final Throwable t) {
            throw MountiplexUtil.uncheckedRethrow(t);
        }
    }
    
    public static CtClass makeNewClass(final ClassPool classPool, final byte[] buf) {
        try {
            return classPool.makeClass(new ByteArrayInputStream(buf));
        }
        catch (final IOException cause) {
            throw new IllegalStateException("Unexpected IO Exception", cause);
        }
    }
    
    public static CtClass makeNewClassWithDefaultConstructor(final ClassPool classPool, final String s, final byte[] array) {
        final ByteArrayClassPath byteArrayClassPath = new ByteArrayClassPath(s, array);
        try {
            classPool.insertClassPath(byteArrayClassPath);
            return asNewClass(classPool.get(s));
        }
        finally {
            classPool.removeClassPath(byteArrayClassPath);
        }
    }
    
    public static CtClass asNewClass(final CtClass ctClass) {
        final CtClass class1 = ctClass.getClassPool().makeClass(ctClass.getName(), ctClass.getSuperclass());
        final CtClass[] interfaces = ctClass.getInterfaces();
        for (int length = interfaces.length, i = 0; i < length; ++i) {
            class1.addInterface(interfaces[i]);
        }
        final CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
        for (int length2 = declaredMethods.length, j = 0; j < length2; ++j) {
            class1.addMethod(new CtMethod(declaredMethods[j], class1, null));
        }
        final CtField[] declaredFields = ctClass.getDeclaredFields();
        for (int length3 = declaredFields.length, k = 0; k < length3; ++k) {
            class1.addField(new CtField(declaredFields[k], class1));
        }
        final CtConstructor[] declaredConstructors = ctClass.getDeclaredConstructors();
        for (int length4 = declaredConstructors.length, l = 0; l < length4; ++l) {
            class1.addConstructor(new CtConstructor(declaredConstructors[l], class1, null));
        }
        final CtConstructor classInitializer = ctClass.getClassInitializer();
        if (classInitializer != null) {
            class1.makeClassInitializer().setBody(classInitializer, null);
        }
        return class1;
    }
    
    static {
        javac_instantiator = NullInstantiator.of((Class<? extends Javac>)Javac.class);
        try {
            (javac_gen = Javac.class.getDeclaredField("gen")).setAccessible(true);
            (javac_stable = Javac.class.getDeclaredField("stable")).setAccessible(true);
            (javac_bytecode = Javac.class.getDeclaredField("bytecode")).setAccessible(true);
        }
        catch (final Throwable t) {
            throw MountiplexUtil.uncheckedRethrow(t);
        }
    }
}
