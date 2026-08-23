// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.convert;

import dev.imanity.antixray.libs.javassist.bytecode.CodeAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;
import dev.imanity.antixray.libs.javassist.bytecode.CodeIterator;
import dev.imanity.antixray.libs.javassist.CtClass;
import dev.imanity.antixray.libs.javassist.CtField;

public final class TransformWriteField extends TransformReadField
{
    public TransformWriteField(final Transformer transformer, final CtField ctField, final String s, final String s2) {
        super(transformer, ctField, s, s2);
    }
    
    @Override
    public int transform(final CtClass ctClass, int n, final CodeIterator codeIterator, final ConstPool constPool) {
        final int byte1 = codeIterator.byteAt(n);
        if (byte1 == 181 || byte1 == 179) {
            final String field = TransformReadField.isField(ctClass.getClassPool(), constPool, this.fieldClass, this.fieldname, this.isPrivate, codeIterator.u16bitAt(n + 1));
            if (field != null) {
                if (byte1 == 179) {
                    final CodeAttribute value = codeIterator.get();
                    codeIterator.move(n);
                    final char char1 = field.charAt(0);
                    if (char1 == 'J' || char1 == 'D') {
                        n = codeIterator.insertGap(3);
                        codeIterator.writeByte(1, n);
                        codeIterator.writeByte(91, n + 1);
                        codeIterator.writeByte(87, n + 2);
                        value.setMaxStack(value.getMaxStack() + 2);
                    }
                    else {
                        n = codeIterator.insertGap(2);
                        codeIterator.writeByte(1, n);
                        codeIterator.writeByte(95, n + 1);
                        value.setMaxStack(value.getMaxStack() + 1);
                    }
                    n = codeIterator.next();
                }
                final int addMethodrefInfo = constPool.addMethodrefInfo(constPool.addClassInfo(this.methodClassname), this.methodName, "(Ljava/lang/Object;" + field + ")V");
                codeIterator.writeByte(184, n);
                codeIterator.write16bit(addMethodrefInfo, n + 1);
            }
        }
        return n;
    }
}
