// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.compiler;

import dev.imanity.antixray.libs.javassist.bytecode.FieldInfo;
import dev.imanity.antixray.libs.javassist.bytecode.ExceptionsAttribute;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;
import dev.imanity.antixray.libs.javassist.bytecode.ClassFile;
import dev.imanity.antixray.libs.javassist.NotFoundException;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import dev.imanity.antixray.libs.javassist.bytecode.Bytecode;
import dev.imanity.antixray.libs.javassist.bytecode.AttributeInfo;
import dev.imanity.antixray.libs.javassist.bytecode.SyntheticAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;
import java.util.HashMap;
import java.util.Map;
import dev.imanity.antixray.libs.javassist.CtClass;

public class AccessorMaker
{
    private CtClass clazz;
    private int uniqueNumber;
    private Map<String, Object> accessors;
    static final String lastParamType = "dev.imanity.antixray.libs.javassist.runtime.Inner";
    
    public AccessorMaker(final CtClass clazz) {
        this.clazz = clazz;
        this.uniqueNumber = 1;
        this.accessors = new HashMap<String, Object>();
    }
    
    public String getConstructor(final CtClass ctClass, final String str, final MethodInfo methodInfo) {
        final String string = "<init>:" + str;
        final String s = this.accessors.get(string);
        if (s != null) {
            return s;
        }
        final String appendParameter = Descriptor.appendParameter("dev.imanity.antixray.libs.javassist.runtime.Inner", str);
        final ClassFile classFile = this.clazz.getClassFile();
        try {
            final ConstPool constPool = classFile.getConstPool();
            final ClassPool classPool = this.clazz.getClassPool();
            final MethodInfo methodInfo2 = new MethodInfo(constPool, "<init>", appendParameter);
            methodInfo2.setAccessFlags(0);
            methodInfo2.addAttribute(new SyntheticAttribute(constPool));
            final ExceptionsAttribute exceptionsAttribute = methodInfo.getExceptionsAttribute();
            if (exceptionsAttribute != null) {
                methodInfo2.addAttribute(exceptionsAttribute.copy(constPool, null));
            }
            final CtClass[] parameterTypes = Descriptor.getParameterTypes(str, classPool);
            final Bytecode bytecode = new Bytecode(constPool);
            bytecode.addAload(0);
            int n = 1;
            for (int i = 0; i < parameterTypes.length; ++i) {
                n += bytecode.addLoad(n, parameterTypes[i]);
            }
            bytecode.setMaxLocals(n + 1);
            bytecode.addInvokespecial(this.clazz, "<init>", str);
            bytecode.addReturn(null);
            methodInfo2.setCodeAttribute(bytecode.toCodeAttribute());
            classFile.addMethod(methodInfo2);
        }
        catch (final CannotCompileException ex) {
            throw new CompileError(ex);
        }
        catch (final NotFoundException ex2) {
            throw new CompileError(ex2);
        }
        this.accessors.put(string, appendParameter);
        return appendParameter;
    }
    
    public String getMethodAccessor(final String str, final String str2, final String s, final MethodInfo methodInfo) {
        final String string = str + ":" + str2;
        final String s2 = this.accessors.get(string);
        if (s2 != null) {
            return s2;
        }
        final ClassFile classFile = this.clazz.getClassFile();
        final String accessorName = this.findAccessorName(classFile);
        try {
            final ConstPool constPool = classFile.getConstPool();
            final ClassPool classPool = this.clazz.getClassPool();
            final MethodInfo methodInfo2 = new MethodInfo(constPool, accessorName, s);
            methodInfo2.setAccessFlags(8);
            methodInfo2.addAttribute(new SyntheticAttribute(constPool));
            final ExceptionsAttribute exceptionsAttribute = methodInfo.getExceptionsAttribute();
            if (exceptionsAttribute != null) {
                methodInfo2.addAttribute(exceptionsAttribute.copy(constPool, null));
            }
            final CtClass[] parameterTypes = Descriptor.getParameterTypes(s, classPool);
            int maxLocals = 0;
            final Bytecode bytecode = new Bytecode(constPool);
            for (int i = 0; i < parameterTypes.length; ++i) {
                maxLocals += bytecode.addLoad(maxLocals, parameterTypes[i]);
            }
            bytecode.setMaxLocals(maxLocals);
            if (str2 == s) {
                bytecode.addInvokestatic(this.clazz, str, str2);
            }
            else {
                bytecode.addInvokevirtual(this.clazz, str, str2);
            }
            bytecode.addReturn(Descriptor.getReturnType(str2, classPool));
            methodInfo2.setCodeAttribute(bytecode.toCodeAttribute());
            classFile.addMethod(methodInfo2);
        }
        catch (final CannotCompileException ex) {
            throw new CompileError(ex);
        }
        catch (final NotFoundException ex2) {
            throw new CompileError(ex2);
        }
        this.accessors.put(string, accessorName);
        return accessorName;
    }
    
    public MethodInfo getFieldGetter(final FieldInfo fieldInfo, final boolean b) {
        final String name = fieldInfo.getName();
        final String string = name + ":getter";
        final Object value = this.accessors.get(string);
        if (value != null) {
            return (MethodInfo)value;
        }
        final ClassFile classFile = this.clazz.getClassFile();
        final String accessorName = this.findAccessorName(classFile);
        try {
            final ConstPool constPool = classFile.getConstPool();
            final ClassPool classPool = this.clazz.getClassPool();
            final String descriptor = fieldInfo.getDescriptor();
            String s;
            if (b) {
                s = "()" + descriptor;
            }
            else {
                s = "(" + Descriptor.of(this.clazz) + ")" + descriptor;
            }
            final MethodInfo methodInfo = new MethodInfo(constPool, accessorName, s);
            methodInfo.setAccessFlags(8);
            methodInfo.addAttribute(new SyntheticAttribute(constPool));
            final Bytecode bytecode = new Bytecode(constPool);
            if (b) {
                bytecode.addGetstatic(Bytecode.THIS, name, descriptor);
            }
            else {
                bytecode.addAload(0);
                bytecode.addGetfield(Bytecode.THIS, name, descriptor);
                bytecode.setMaxLocals(1);
            }
            bytecode.addReturn(Descriptor.toCtClass(descriptor, classPool));
            methodInfo.setCodeAttribute(bytecode.toCodeAttribute());
            classFile.addMethod(methodInfo);
            this.accessors.put(string, methodInfo);
            return methodInfo;
        }
        catch (final CannotCompileException ex) {
            throw new CompileError(ex);
        }
        catch (final NotFoundException ex2) {
            throw new CompileError(ex2);
        }
    }
    
    public MethodInfo getFieldSetter(final FieldInfo fieldInfo, final boolean b) {
        final String name = fieldInfo.getName();
        final String string = name + ":setter";
        final Object value = this.accessors.get(string);
        if (value != null) {
            return (MethodInfo)value;
        }
        final ClassFile classFile = this.clazz.getClassFile();
        final String accessorName = this.findAccessorName(classFile);
        try {
            final ConstPool constPool = classFile.getConstPool();
            final ClassPool classPool = this.clazz.getClassPool();
            final String descriptor = fieldInfo.getDescriptor();
            String s;
            if (b) {
                s = "(" + descriptor + ")V";
            }
            else {
                s = "(" + Descriptor.of(this.clazz) + descriptor + ")V";
            }
            final MethodInfo methodInfo = new MethodInfo(constPool, accessorName, s);
            methodInfo.setAccessFlags(8);
            methodInfo.addAttribute(new SyntheticAttribute(constPool));
            final Bytecode bytecode = new Bytecode(constPool);
            int addLoad;
            if (b) {
                addLoad = bytecode.addLoad(0, Descriptor.toCtClass(descriptor, classPool));
                bytecode.addPutstatic(Bytecode.THIS, name, descriptor);
            }
            else {
                bytecode.addAload(0);
                addLoad = bytecode.addLoad(1, Descriptor.toCtClass(descriptor, classPool)) + 1;
                bytecode.addPutfield(Bytecode.THIS, name, descriptor);
            }
            bytecode.addReturn(null);
            bytecode.setMaxLocals(addLoad);
            methodInfo.setCodeAttribute(bytecode.toCodeAttribute());
            classFile.addMethod(methodInfo);
            this.accessors.put(string, methodInfo);
            return methodInfo;
        }
        catch (final CannotCompileException ex) {
            throw new CompileError(ex);
        }
        catch (final NotFoundException ex2) {
            throw new CompileError(ex2);
        }
    }
    
    private String findAccessorName(final ClassFile classFile) {
        String string;
        do {
            string = "access$" + this.uniqueNumber++;
        } while (classFile.getMethod(string) != null);
        return string;
    }
}
