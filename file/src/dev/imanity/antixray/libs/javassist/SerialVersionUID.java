// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist;

import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import dev.imanity.antixray.libs.javassist.bytecode.ClassFile;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Comparator;
import java.util.Arrays;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;

public class SerialVersionUID
{
    public static void setSerialVersionUID(final CtClass ctClass) {
        try {
            ctClass.getDeclaredField("serialVersionUID");
        }
        catch (final NotFoundException ex) {
            if (!isSerializable(ctClass)) {
                return;
            }
            final CtField ctField = new CtField(CtClass.longType, "serialVersionUID", ctClass);
            ctField.setModifiers(26);
            ctClass.addField(ctField, calculateDefault(ctClass) + "L");
        }
    }
    
    private static boolean isSerializable(final CtClass ctClass) {
        return ctClass.subtypeOf(ctClass.getClassPool().get("java.io.Serializable"));
    }
    
    public static long calculateDefault(final CtClass ctClass) {
        try {
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            final DataOutputStream dataOutputStream = new DataOutputStream(out);
            final ClassFile classFile = ctClass.getClassFile();
            dataOutputStream.writeUTF(javaName(ctClass));
            final CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
            int modifiers = ctClass.getModifiers();
            if ((modifiers & 0x200) != 0x0) {
                if (declaredMethods.length > 0) {
                    modifiers |= 0x400;
                }
                else {
                    modifiers &= 0xFFFFFBFF;
                }
            }
            dataOutputStream.writeInt(modifiers);
            final String[] interfaces = classFile.getInterfaces();
            for (int i = 0; i < interfaces.length; ++i) {
                interfaces[i] = javaName(interfaces[i]);
            }
            Arrays.sort(interfaces);
            for (int j = 0; j < interfaces.length; ++j) {
                dataOutputStream.writeUTF(interfaces[j]);
            }
            final CtField[] declaredFields = ctClass.getDeclaredFields();
            Arrays.sort(declaredFields, new Comparator<CtField>() {
                @Override
                public int compare(final CtField ctField, final CtField ctField2) {
                    return ctField.getName().compareTo(ctField2.getName());
                }
            });
            for (int k = 0; k < declaredFields.length; ++k) {
                final CtField ctField = declaredFields[k];
                final int modifiers2 = ctField.getModifiers();
                if ((modifiers2 & 0x2) == 0x0 || (modifiers2 & 0x88) == 0x0) {
                    dataOutputStream.writeUTF(ctField.getName());
                    dataOutputStream.writeInt(modifiers2);
                    dataOutputStream.writeUTF(ctField.getFieldInfo2().getDescriptor());
                }
            }
            if (classFile.getStaticInitializer() != null) {
                dataOutputStream.writeUTF("<clinit>");
                dataOutputStream.writeInt(8);
                dataOutputStream.writeUTF("()V");
            }
            final CtConstructor[] declaredConstructors = ctClass.getDeclaredConstructors();
            Arrays.sort(declaredConstructors, new Comparator<CtConstructor>() {
                @Override
                public int compare(final CtConstructor ctConstructor, final CtConstructor ctConstructor2) {
                    return ctConstructor.getMethodInfo2().getDescriptor().compareTo(ctConstructor2.getMethodInfo2().getDescriptor());
                }
            });
            for (int l = 0; l < declaredConstructors.length; ++l) {
                final CtConstructor ctConstructor = declaredConstructors[l];
                final int modifiers3 = ctConstructor.getModifiers();
                if ((modifiers3 & 0x2) == 0x0) {
                    dataOutputStream.writeUTF("<init>");
                    dataOutputStream.writeInt(modifiers3);
                    dataOutputStream.writeUTF(ctConstructor.getMethodInfo2().getDescriptor().replace('/', '.'));
                }
            }
            Arrays.sort(declaredMethods, new Comparator<CtMethod>() {
                @Override
                public int compare(final CtMethod ctMethod, final CtMethod ctMethod2) {
                    int n = ctMethod.getName().compareTo(ctMethod2.getName());
                    if (n == 0) {
                        n = ctMethod.getMethodInfo2().getDescriptor().compareTo(ctMethod2.getMethodInfo2().getDescriptor());
                    }
                    return n;
                }
            });
            for (int n = 0; n < declaredMethods.length; ++n) {
                final CtMethod ctMethod = declaredMethods[n];
                final int v = ctMethod.getModifiers() & 0xD3F;
                if ((v & 0x2) == 0x0) {
                    dataOutputStream.writeUTF(ctMethod.getName());
                    dataOutputStream.writeInt(v);
                    dataOutputStream.writeUTF(ctMethod.getMethodInfo2().getDescriptor().replace('/', '.'));
                }
            }
            dataOutputStream.flush();
            final byte[] digest = MessageDigest.getInstance("SHA").digest(out.toByteArray());
            long n2 = 0L;
            for (int n3 = Math.min(digest.length, 8) - 1; n3 >= 0; --n3) {
                n2 = (n2 << 8 | (long)(digest[n3] & 0xFF));
            }
            return n2;
        }
        catch (final IOException ex) {
            throw new CannotCompileException(ex);
        }
        catch (final NoSuchAlgorithmException ex2) {
            throw new CannotCompileException(ex2);
        }
    }
    
    private static String javaName(final CtClass ctClass) {
        return Descriptor.toJavaName(Descriptor.toJvmName(ctClass));
    }
    
    private static String javaName(final String s) {
        return Descriptor.toJavaName(Descriptor.toJvmName(s));
    }
}
