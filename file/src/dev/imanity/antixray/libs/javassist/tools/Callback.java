// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.tools;

import java.util.HashMap;
import dev.imanity.antixray.libs.javassist.CtBehavior;
import java.util.UUID;
import java.util.Map;

public abstract class Callback
{
    public static Map<String, Callback> callbacks;
    private final String sourceCode;
    
    public Callback(final String str) {
        final String string = UUID.randomUUID().toString();
        Callback.callbacks.put(string, this);
        this.sourceCode = "((javassist.tools.Callback) javassist.tools.Callback.callbacks.get(\"" + string + "\")).result(new Object[]{" + str + "});";
    }
    
    public abstract void result(final Object[] p0);
    
    @Override
    public String toString() {
        return this.sourceCode();
    }
    
    public String sourceCode() {
        return this.sourceCode;
    }
    
    public static void insertBefore(final CtBehavior ctBehavior, final Callback callback) {
        ctBehavior.insertBefore(callback.toString());
    }
    
    public static void insertAfter(final CtBehavior ctBehavior, final Callback callback) {
        ctBehavior.insertAfter(callback.toString(), false);
    }
    
    public static void insertAfter(final CtBehavior ctBehavior, final Callback callback, final boolean b) {
        ctBehavior.insertAfter(callback.toString(), b);
    }
    
    public static int insertAt(final CtBehavior ctBehavior, final Callback callback, final int n) {
        return ctBehavior.insertAt(n, callback.toString());
    }
    
    static {
        Callback.callbacks = new HashMap<String, Callback>();
    }
}
