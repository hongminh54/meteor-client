// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode.analysis;

import dev.imanity.antixray.libs.javassist.NotFoundException;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.javassist.CtClass;

public class MultiArrayType extends Type
{
    private MultiType component;
    private int dims;
    
    public MultiArrayType(final MultiType component, final int dims) {
        super(null);
        this.component = component;
        this.dims = dims;
    }
    
    @Override
    public CtClass getCtClass() {
        final CtClass ctClass = this.component.getCtClass();
        if (ctClass == null) {
            return null;
        }
        ClassPool classPool = ctClass.getClassPool();
        if (classPool == null) {
            classPool = ClassPool.getDefault();
        }
        final String arrayName = this.arrayName(ctClass.getName(), this.dims);
        try {
            return classPool.get(arrayName);
        }
        catch (final NotFoundException cause) {
            throw new RuntimeException(cause);
        }
    }
    
    @Override
    boolean popChanged() {
        return this.component.popChanged();
    }
    
    @Override
    public int getDimensions() {
        return this.dims;
    }
    
    @Override
    public Type getComponent() {
        return (this.dims == 1) ? this.component : new MultiArrayType(this.component, this.dims - 1);
    }
    
    @Override
    public int getSize() {
        return 1;
    }
    
    @Override
    public boolean isArray() {
        return true;
    }
    
    @Override
    public boolean isAssignableFrom(final Type type) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public boolean isReference() {
        return true;
    }
    
    public boolean isAssignableTo(final Type type) {
        if (Type.eq(type.getCtClass(), Type.OBJECT.getCtClass())) {
            return true;
        }
        if (Type.eq(type.getCtClass(), Type.CLONEABLE.getCtClass())) {
            return true;
        }
        if (Type.eq(type.getCtClass(), Type.SERIALIZABLE.getCtClass())) {
            return true;
        }
        if (!type.isArray()) {
            return false;
        }
        final Type rootComponent = this.getRootComponent(type);
        final int dimensions = type.getDimensions();
        if (dimensions > this.dims) {
            return false;
        }
        if (dimensions < this.dims) {
            return Type.eq(rootComponent.getCtClass(), Type.OBJECT.getCtClass()) || Type.eq(rootComponent.getCtClass(), Type.CLONEABLE.getCtClass()) || Type.eq(rootComponent.getCtClass(), Type.SERIALIZABLE.getCtClass());
        }
        return this.component.isAssignableTo(rootComponent);
    }
    
    @Override
    public int hashCode() {
        return this.component.hashCode() + this.dims;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof MultiArrayType)) {
            return false;
        }
        final MultiArrayType multiArrayType = (MultiArrayType)o;
        return this.component.equals(multiArrayType.component) && this.dims == multiArrayType.dims;
    }
    
    @Override
    public String toString() {
        return this.arrayName(this.component.toString(), this.dims);
    }
}
