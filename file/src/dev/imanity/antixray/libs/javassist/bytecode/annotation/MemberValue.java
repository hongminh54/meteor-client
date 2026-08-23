// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode.annotation;

import java.util.Map;
import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;

public abstract class MemberValue
{
    ConstPool cp;
    char tag;
    
    MemberValue(final char tag, final ConstPool cp) {
        this.cp = cp;
        this.tag = tag;
    }
    
    abstract Object getValue(final ClassLoader p0, final ClassPool p1, final Method p2);
    
    abstract Class<?> getType(final ClassLoader p0);
    
    static Class<?> loadClass(final ClassLoader loader, final String s) {
        try {
            return Class.forName(convertFromArray(s), true, loader);
        }
        catch (final LinkageError linkageError) {
            throw new NoSuchClassError(s, linkageError);
        }
    }
    
    private static String convertFromArray(final String s) {
        int i = s.indexOf("[]");
        if (i != -1) {
            final StringBuilder sb = new StringBuilder(Descriptor.of(s.substring(0, i)));
            while (i != -1) {
                sb.insert(0, '[');
                i = s.indexOf("[]", i + 1);
            }
            return sb.toString().replace('/', '.');
        }
        return s;
    }
    
    public void renameClass(final String s, final String s2) {
    }
    
    public void renameClass(final Map<String, String> map) {
    }
    
    public abstract void accept(final MemberValueVisitor p0);
    
    public abstract void write(final AnnotationsWriter p0);
}
