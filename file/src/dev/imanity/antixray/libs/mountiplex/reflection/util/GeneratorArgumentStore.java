// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util;

import java.util.concurrent.ConcurrentHashMap;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import dev.imanity.antixray.libs.javassist.CtField;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;

public class GeneratorArgumentStore
{
    private static final Map<Integer, Object> _constructionArgs;
    private static final AtomicInteger _constructionArgCtr;
    
    public static CtField.Initializer initializeField(final Object o) {
        if (o == null) {
            return CtField.Initializer.byExpr("null");
        }
        return initializeField(o, o.getClass());
    }
    
    public static CtField.Initializer initializeField(final Object o, final Class<?> clazz) {
        if (o == null) {
            return CtField.Initializer.byExpr("null");
        }
        return CtField.Initializer.byExpr("(" + MPLType.getName(clazz) + ") " + GeneratorArgumentStore.class.getName() + ".fetch(" + store(o) + ");");
    }
    
    public static Object fetch(final int i) {
        return GeneratorArgumentStore._constructionArgs.remove(i);
    }
    
    public static int store(final Object o) {
        final int incrementAndGet = GeneratorArgumentStore._constructionArgCtr.incrementAndGet();
        GeneratorArgumentStore._constructionArgs.put(incrementAndGet, o);
        return incrementAndGet;
    }
    
    static {
        _constructionArgs = new ConcurrentHashMap<Integer, Object>();
        _constructionArgCtr = new AtomicInteger(0);
    }
}
