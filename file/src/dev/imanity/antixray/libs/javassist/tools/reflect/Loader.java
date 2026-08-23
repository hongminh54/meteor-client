// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.tools.reflect;

import dev.imanity.antixray.libs.javassist.Translator;
import dev.imanity.antixray.libs.javassist.ClassPool;

public class Loader extends dev.imanity.antixray.libs.javassist.Loader
{
    protected Reflection reflection;
    
    public static void main(final String[] array) {
        new Loader().run(array);
    }
    
    public Loader() {
        this.delegateLoadingOf("dev.imanity.antixray.libs.javassist.tools.reflect.Loader");
        this.reflection = new Reflection();
        this.addTranslator(ClassPool.getDefault(), this.reflection);
    }
    
    public boolean makeReflective(final String s, final String s2, final String s3) {
        return this.reflection.makeReflective(s, s2, s3);
    }
}
