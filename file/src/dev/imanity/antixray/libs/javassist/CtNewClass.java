// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist;

import java.io.DataOutputStream;
import dev.imanity.antixray.libs.javassist.bytecode.ClassFile;

class CtNewClass extends CtClassType
{
    protected boolean hasConstructor;
    
    CtNewClass(final String s, final ClassPool classPool, final boolean hasConstructor, final CtClass ctClass) {
        super(s, classPool);
        this.wasChanged = true;
        String name;
        if (hasConstructor || ctClass == null) {
            name = null;
        }
        else {
            name = ctClass.getName();
        }
        this.classfile = new ClassFile(hasConstructor, s, name);
        if (hasConstructor && ctClass != null) {
            this.classfile.setInterfaces(new String[] { ctClass.getName() });
        }
        this.setModifiers(Modifier.setPublic(this.getModifiers()));
        this.hasConstructor = hasConstructor;
    }
    
    @Override
    protected void extendToString(final StringBuilder sb) {
        if (this.hasConstructor) {
            sb.append("hasConstructor ");
        }
        super.extendToString(sb);
    }
    
    @Override
    public void addConstructor(final CtConstructor ctConstructor) {
        this.hasConstructor = true;
        super.addConstructor(ctConstructor);
    }
    
    @Override
    public void toBytecode(final DataOutputStream dataOutputStream) {
        if (!this.hasConstructor) {
            try {
                this.inheritAllConstructors();
                this.hasConstructor = true;
            }
            catch (final NotFoundException ex) {
                throw new CannotCompileException(ex);
            }
        }
        super.toBytecode(dataOutputStream);
    }
    
    public void inheritAllConstructors() {
        final CtClass superclass = this.getSuperclass();
        final CtConstructor[] declaredConstructors = superclass.getDeclaredConstructors();
        int n = 0;
        for (int i = 0; i < declaredConstructors.length; ++i) {
            final CtConstructor ctConstructor = declaredConstructors[i];
            final int modifiers = ctConstructor.getModifiers();
            if (this.isInheritable(modifiers, superclass)) {
                final CtConstructor make = CtNewConstructor.make(ctConstructor.getParameterTypes(), ctConstructor.getExceptionTypes(), this);
                make.setModifiers(modifiers & 0x7);
                this.addConstructor(make);
                ++n;
            }
        }
        if (n < 1) {
            throw new CannotCompileException("no inheritable constructor in " + superclass.getName());
        }
    }
    
    private boolean isInheritable(final int n, final CtClass ctClass) {
        if (Modifier.isPrivate(n)) {
            return false;
        }
        if (!Modifier.isPackage(n)) {
            return true;
        }
        final String packageName = this.getPackageName();
        final String packageName2 = ctClass.getPackageName();
        if (packageName == null) {
            return packageName2 == null;
        }
        return packageName.equals(packageName2);
    }
}
