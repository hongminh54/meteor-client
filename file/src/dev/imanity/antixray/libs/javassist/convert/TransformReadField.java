// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.convert;

import dev.imanity.antixray.libs.javassist.bytecode.CodeIterator;
import dev.imanity.antixray.libs.javassist.NotFoundException;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.javassist.Modifier;
import dev.imanity.antixray.libs.javassist.CtField;
import dev.imanity.antixray.libs.javassist.CtClass;

public class TransformReadField extends Transformer
{
    protected String fieldname;
    protected CtClass fieldClass;
    protected boolean isPrivate;
    protected String methodClassname;
    protected String methodName;
    
    public TransformReadField(final Transformer transformer, final CtField ctField, final String methodClassname, final String methodName) {
        super(transformer);
        this.fieldClass = ctField.getDeclaringClass();
        this.fieldname = ctField.getName();
        this.methodClassname = methodClassname;
        this.methodName = methodName;
        this.isPrivate = Modifier.isPrivate(ctField.getModifiers());
    }
    
    static String isField(final ClassPool classPool, final ConstPool constPool, final CtClass ctClass, final String anObject, final boolean b, final int n) {
        if (!constPool.getFieldrefName(n).equals(anObject)) {
            return null;
        }
        try {
            final CtClass value = classPool.get(constPool.getFieldrefClassName(n));
            if (value == ctClass || (!b && isFieldInSuper(value, ctClass, anObject))) {
                return constPool.getFieldrefType(n);
            }
        }
        catch (final NotFoundException ex) {}
        return null;
    }
    
    static boolean isFieldInSuper(final CtClass ctClass, final CtClass ctClass2, final String s) {
        if (!ctClass.subclassOf(ctClass2)) {
            return false;
        }
        try {
            return ctClass.getField(s).getDeclaringClass() == ctClass2;
        }
        catch (final NotFoundException ex) {
            return false;
        }
    }
    
    @Override
    public int transform(final CtClass ctClass, int n, final CodeIterator codeIterator, final ConstPool constPool) {
        final int byte1 = codeIterator.byteAt(n);
        if (byte1 == 180 || byte1 == 178) {
            final String field = isField(ctClass.getClassPool(), constPool, this.fieldClass, this.fieldname, this.isPrivate, codeIterator.u16bitAt(n + 1));
            if (field != null) {
                if (byte1 == 178) {
                    codeIterator.move(n);
                    n = codeIterator.insertGap(1);
                    codeIterator.writeByte(1, n);
                    n = codeIterator.next();
                }
                final int addMethodrefInfo = constPool.addMethodrefInfo(constPool.addClassInfo(this.methodClassname), this.methodName, "(Ljava/lang/Object;)" + field);
                codeIterator.writeByte(184, n);
                codeIterator.write16bit(addMethodrefInfo, n + 1);
                return n;
            }
        }
        return n;
    }
}
