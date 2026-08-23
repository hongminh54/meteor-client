// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.util.proxy;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.lang.invoke.MethodHandles;
import java.io.IOException;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import java.security.ProtectionDomain;
import dev.imanity.antixray.libs.javassist.bytecode.ClassFile;

public class FactoryHelper
{
    public static final Class<?>[] primitiveTypes;
    public static final String[] wrapperTypes;
    public static final String[] wrapperDesc;
    public static final String[] unwarpMethods;
    public static final String[] unwrapDesc;
    public static final int[] dataSize;
    
    public static final int typeIndex(final Class<?> clazz) {
        for (int i = 0; i < FactoryHelper.primitiveTypes.length; ++i) {
            if (FactoryHelper.primitiveTypes[i] == clazz) {
                return i;
            }
        }
        throw new RuntimeException("bad type:" + clazz.getName());
    }
    
    public static Class<?> toClass(final ClassFile classFile, final ClassLoader classLoader) {
        return toClass(classFile, null, classLoader, null);
    }
    
    public static Class<?> toClass(final ClassFile classFile, final ClassLoader classLoader, final ProtectionDomain protectionDomain) {
        return toClass(classFile, null, classLoader, protectionDomain);
    }
    
    public static Class<?> toClass(final ClassFile classFile, final Class<?> clazz, final ClassLoader classLoader, final ProtectionDomain protectionDomain) {
        try {
            final byte[] bytecode = toBytecode(classFile);
            if (ProxyFactory.onlyPublicMethods) {
                return DefineClassHelper.toPublicClass(classFile.getName(), bytecode);
            }
            return DefineClassHelper.toClass(classFile.getName(), clazz, classLoader, protectionDomain, bytecode);
        }
        catch (final IOException ex) {
            throw new CannotCompileException(ex);
        }
    }
    
    public static Class<?> toClass(final ClassFile classFile, final MethodHandles.Lookup lookup) {
        try {
            return DefineClassHelper.toClass(lookup, toBytecode(classFile));
        }
        catch (final IOException ex) {
            throw new CannotCompileException(ex);
        }
    }
    
    private static byte[] toBytecode(final ClassFile classFile) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(out);
        try {
            classFile.write(dataOutputStream);
        }
        finally {
            dataOutputStream.close();
        }
        return out.toByteArray();
    }
    
    public static void writeFile(final ClassFile classFile, final String s) {
        try {
            writeFile0(classFile, s);
        }
        catch (final IOException ex) {
            throw new CannotCompileException(ex);
        }
    }
    
    private static void writeFile0(final ClassFile classFile, final String str) {
        final String string = str + File.separatorChar + classFile.getName().replace('.', File.separatorChar) + ".class";
        final int lastIndex = string.lastIndexOf(File.separatorChar);
        if (lastIndex > 0) {
            final String substring = string.substring(0, lastIndex);
            if (!substring.equals(".")) {
                new File(substring).mkdirs();
            }
        }
        final DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(string)));
        try {
            classFile.write(dataOutputStream);
        }
        catch (final IOException ex) {
            throw ex;
        }
        finally {
            dataOutputStream.close();
        }
    }
    
    static {
        primitiveTypes = new Class[] { Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Void.TYPE };
        wrapperTypes = new String[] { "java.lang.Boolean", "java.lang.Byte", "java.lang.Character", "java.lang.Short", "java.lang.Integer", "java.lang.Long", "java.lang.Float", "java.lang.Double", "java.lang.Void" };
        wrapperDesc = new String[] { "(Z)V", "(B)V", "(C)V", "(S)V", "(I)V", "(J)V", "(F)V", "(D)V" };
        unwarpMethods = new String[] { "booleanValue", "byteValue", "charValue", "shortValue", "intValue", "longValue", "floatValue", "doubleValue" };
        unwrapDesc = new String[] { "()Z", "()B", "()C", "()S", "()I", "()J", "()F", "()D" };
        dataSize = new int[] { 1, 1, 1, 1, 1, 2, 1, 2 };
    }
}
