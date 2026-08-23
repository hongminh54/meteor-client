// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.tools.reflect;

import java.lang.reflect.InvocationTargetException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.io.Serializable;

public class Metaobject implements Serializable
{
    private static final long serialVersionUID = 1L;
    protected ClassMetaobject classmetaobject;
    protected Metalevel baseobject;
    protected Method[] methods;
    
    public Metaobject(final Object o, final Object[] array) {
        this.baseobject = (Metalevel)o;
        this.classmetaobject = this.baseobject._getClass();
        this.methods = this.classmetaobject.getReflectiveMethods();
    }
    
    protected Metaobject() {
        this.baseobject = null;
        this.classmetaobject = null;
        this.methods = null;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeObject(this.baseobject);
    }
    
    private void readObject(final ObjectInputStream objectInputStream) {
        this.baseobject = (Metalevel)objectInputStream.readObject();
        this.classmetaobject = this.baseobject._getClass();
        this.methods = this.classmetaobject.getReflectiveMethods();
    }
    
    public final ClassMetaobject getClassMetaobject() {
        return this.classmetaobject;
    }
    
    public final Object getObject() {
        return this.baseobject;
    }
    
    public final void setObject(final Object o) {
        this.baseobject = (Metalevel)o;
        this.classmetaobject = this.baseobject._getClass();
        this.methods = this.classmetaobject.getReflectiveMethods();
        this.baseobject._setMetaobject(this);
    }
    
    public final String getMethodName(final int n) {
        final String name = this.methods[n].getName();
        int beginIndex = 3;
        char char1;
        do {
            char1 = name.charAt(beginIndex++);
        } while (char1 >= '0' && '9' >= char1);
        return name.substring(beginIndex);
    }
    
    public final Class<?>[] getParameterTypes(final int n) {
        return this.methods[n].getParameterTypes();
    }
    
    public final Class<?> getReturnType(final int n) {
        return this.methods[n].getReturnType();
    }
    
    public Object trapFieldRead(final String name) {
        final Class<?> javaClass = this.getClassMetaobject().getJavaClass();
        try {
            return javaClass.getField(name).get(this.getObject());
        }
        catch (final NoSuchFieldException ex) {
            throw new RuntimeException(ex.toString());
        }
        catch (final IllegalAccessException ex2) {
            throw new RuntimeException(ex2.toString());
        }
    }
    
    public void trapFieldWrite(final String name, final Object value) {
        final Class<?> javaClass = this.getClassMetaobject().getJavaClass();
        try {
            javaClass.getField(name).set(this.getObject(), value);
        }
        catch (final NoSuchFieldException ex) {
            throw new RuntimeException(ex.toString());
        }
        catch (final IllegalAccessException ex2) {
            throw new RuntimeException(ex2.toString());
        }
    }
    
    public Object trapMethodcall(final int n, final Object[] args) {
        try {
            return this.methods[n].invoke(this.getObject(), args);
        }
        catch (final InvocationTargetException ex) {
            throw ex.getTargetException();
        }
        catch (final IllegalAccessException ex2) {
            throw new CannotInvokeException(ex2);
        }
    }
}
