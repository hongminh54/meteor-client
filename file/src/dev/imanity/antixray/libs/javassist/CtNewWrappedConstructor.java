// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist;

import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import dev.imanity.antixray.libs.javassist.bytecode.Bytecode;
import dev.imanity.antixray.libs.javassist.bytecode.ClassFile;

class CtNewWrappedConstructor extends CtNewWrappedMethod
{
    private static final int PASS_NONE = 0;
    private static final int PASS_PARAMS = 2;
    
    public static CtConstructor wrapped(final CtClass[] array, final CtClass[] exceptionTypes, final int n, final CtMethod ctMethod, final CtMethod.ConstParameter constParameter, final CtClass ctClass) {
        try {
            final CtConstructor ctConstructor = new CtConstructor(array, ctClass);
            ctConstructor.setExceptionTypes(exceptionTypes);
            ctConstructor.getMethodInfo2().setCodeAttribute(makeBody(ctClass, ctClass.getClassFile2(), n, ctMethod, array, constParameter).toCodeAttribute());
            return ctConstructor;
        }
        catch (final NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
    }
    
    protected static Bytecode makeBody(final CtClass ctClass, final ClassFile classFile, final int n, final CtMethod ctMethod, final CtClass[] array, final CtMethod.ConstParameter constParameter) {
        final int superclassId = classFile.getSuperclassId();
        final Bytecode bytecode = new Bytecode(classFile.getConstPool(), 0, 0);
        bytecode.setMaxLocals(false, array, 0);
        bytecode.addAload(0);
        int compileParameterList;
        if (n == 0) {
            compileParameterList = 1;
            bytecode.addInvokespecial(superclassId, "<init>", "()V");
        }
        else if (n == 2) {
            compileParameterList = bytecode.addLoadParameters(array, 1) + 1;
            bytecode.addInvokespecial(superclassId, "<init>", Descriptor.ofConstructor(array));
        }
        else {
            compileParameterList = CtNewWrappedMethod.compileParameterList(bytecode, array, 1);
            int n2;
            String s;
            if (constParameter == null) {
                n2 = 2;
                s = CtMethod.ConstParameter.defaultConstDescriptor();
            }
            else {
                n2 = constParameter.compile(bytecode) + 2;
                s = constParameter.constDescriptor();
            }
            if (compileParameterList < n2) {
                compileParameterList = n2;
            }
            bytecode.addInvokespecial(superclassId, "<init>", s);
        }
        if (ctMethod == null) {
            bytecode.add(177);
        }
        else {
            final int body0 = CtNewWrappedMethod.makeBody0(ctClass, classFile, ctMethod, false, array, CtClass.voidType, constParameter, bytecode);
            if (compileParameterList < body0) {
                compileParameterList = body0;
            }
        }
        bytecode.setMaxStack(compileParameterList);
        return bytecode;
    }
}
