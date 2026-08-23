// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist;

import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import java.util.HashMap;

public class ClassMap extends HashMap<String, String>
{
    private static final long serialVersionUID = 1L;
    private ClassMap parent;
    
    public ClassMap() {
        this.parent = null;
    }
    
    ClassMap(final ClassMap parent) {
        this.parent = parent;
    }
    
    public void put(final CtClass ctClass, final CtClass ctClass2) {
        this.put(ctClass.getName(), ctClass2.getName());
    }
    
    @Override
    public String put(final String s, final String s2) {
        if (s == s2) {
            return s;
        }
        final String jvmName = toJvmName(s);
        final String value = this.get(jvmName);
        if (value == null || !value.equals(jvmName)) {
            return super.put(jvmName, toJvmName(s2));
        }
        return value;
    }
    
    public void putIfNone(final String s, final String s2) {
        if (s == s2) {
            return;
        }
        final String jvmName = toJvmName(s);
        if (this.get(jvmName) == null) {
            super.put(jvmName, toJvmName(s2));
        }
    }
    
    protected final String put0(final String key, final String value) {
        return super.put(key, value);
    }
    
    @Override
    public String get(final Object key) {
        final String s = super.get(key);
        if (s == null && this.parent != null) {
            return this.parent.get(key);
        }
        return s;
    }
    
    public void fix(final CtClass ctClass) {
        this.fix(ctClass.getName());
    }
    
    public void fix(final String s) {
        final String jvmName = toJvmName(s);
        super.put(jvmName, jvmName);
    }
    
    public static String toJvmName(final String s) {
        return Descriptor.toJvmName(s);
    }
    
    public static String toJavaName(final String s) {
        return Descriptor.toJavaName(s);
    }
}
