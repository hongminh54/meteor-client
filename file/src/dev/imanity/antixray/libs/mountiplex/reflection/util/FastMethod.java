// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util;

import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ClassResolver;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.InitInvoker;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.MethodDeclaration;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.Invoker;

public class FastMethod<T> implements IgnoresRemapping, LazyInitializedObject, Invoker<T>
{
    private MethodDeclaration method;
    private Invoker<T> invoker;
    
    public FastMethod() {
        this.method = null;
        this.invoker = (Invoker<T>)InitInvoker.unavailableMethod();
    }
    
    public FastMethod(final Method method) {
        this.init(method);
    }
    
    public FastMethod(final Constructor<?> constructor) {
        this.init(constructor);
    }
    
    public FastMethod(final MethodDeclaration methodDeclaration) {
        this.init(methodDeclaration);
    }
    
    public FastMethod(final DelayedInitializer<T> delayedInitializer) {
        this.init(delayedInitializer);
    }
    
    public final void init(final Method method) {
        if (method == null) {
            this.method = null;
            this.invoker = (Invoker<T>)InitInvoker.unavailableMethod();
        }
        else {
            this.init(new MethodDeclaration(ClassResolver.DEFAULT, method));
        }
    }
    
    public final void init(final Constructor<?> constructor) {
        if (constructor == null) {
            this.method = null;
            this.invoker = (Invoker<T>)InitInvoker.unavailableMethod();
        }
        else {
            this.init(new MethodDeclaration(ClassResolver.DEFAULT, constructor));
        }
    }
    
    public final void init(final MethodDeclaration method) {
        if (method == null) {
            this.method = null;
            this.invoker = (Invoker<T>)InitInvoker.unavailableMethod();
        }
        else if (!method.isRecordFieldChanger && method.body == null && method.method == null && method.constructor == null) {
            this.method = null;
            this.invoker = (Invoker<T>)InitInvoker.unavailable("method", method.toString());
        }
        else {
            this.method = method;
            this.invoker = (Invoker<T>)InitInvoker.forMethod(this, "invoker", method);
        }
    }
    
    public final void init(final DelayedInitializer<T> delayedInitializer) {
        this.init(null, new DelayedInitializationInvoker<T>(this, delayedInitializer));
    }
    
    public final void init(final MethodDeclaration method, final Invoker<T> invoker) {
        this.method = method;
        this.invoker = invoker;
    }
    
    public final void initUnavailable(final String s) {
        this.method = null;
        this.invoker = (Invoker<T>)InitInvoker.unavailable("method", s);
    }
    
    public final void checkInit() {
        if (this.invoker instanceof InitInvoker.UnavailableInvoker) {
            this.invoker.initializeInvoker();
        }
    }
    
    public final boolean isAvailable() {
        if (this.method != null) {
            return true;
        }
        if (this.invoker instanceof DelayedInitializationInvoker) {
            this.invoker.initializeInvoker();
            return this.method != null;
        }
        return false;
    }
    
    public final Method getMethod() {
        return this.isAvailable() ? this.method.method : null;
    }
    
    public final boolean isMethod(final Method obj) {
        return this.isAvailable() && this.method.method != null && this.method.method.equals(obj);
    }
    
    public final String getName() {
        if (this.isAvailable()) {
            return this.method.name.value();
        }
        return "null";
    }
    
    public final Invoker<T> getInvoker() {
        return this.invoker;
    }
    
    @Override
    public void forceInitialization() {
        this.invoker.forceInitialization();
    }
    
    @Override
    public T invokeVA(final Object o, final Object... array) {
        return this.invoker.invokeVA(o, array);
    }
    
    @Override
    public T invoke(final Object o) {
        return this.invoker.invoke(o);
    }
    
    @Override
    public T invoke(final Object o, final Object o2) {
        return this.invoker.invoke(o, o2);
    }
    
    @Override
    public T invoke(final Object o, final Object o2, final Object o3) {
        return this.invoker.invoke(o, o2, o3);
    }
    
    @Override
    public T invoke(final Object o, final Object o2, final Object o3, final Object o4) {
        return this.invoker.invoke(o, o2, o3, o4);
    }
    
    @Override
    public T invoke(final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
        return this.invoker.invoke(o, o2, o3, o4, o5);
    }
    
    @Override
    public T invoke(final Object o, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6) {
        return this.invoker.invoke(o, o2, o3, o4, o5, o6);
    }
    
    private static class DelayedInitializationInvoker<T> implements Invoker<T>
    {
        private final FastMethod<T> method;
        private final DelayedInitializer<T> initializer;
        
        public DelayedInitializationInvoker(final FastMethod<T> method, final DelayedInitializer<T> initializer) {
            this.method = method;
            this.initializer = initializer;
        }
        
        @Override
        public Invoker<T> initializeInvoker() {
            try {
                this.initializer.initialize(this.method);
                if (((FastMethod<Object>)this.method).invoker == this) {
                    this.method.initUnavailable("(callback-initialized) which did not initialize");
                }
                else {
                    this.method.forceInitialization();
                }
            }
            catch (final Throwable thrown) {
                MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to callback-initialize method", thrown);
                this.method.initUnavailable("(callback-initialized) with init error");
            }
            return (Invoker<T>)((FastMethod<Object>)this.method).invoker;
        }
        
        @Override
        public T invokeVA(final Object o, final Object... array) {
            return this.initializeInvoker().invokeVA(o, array);
        }
    }
    
    @FunctionalInterface
    public interface DelayedInitializer<T>
    {
        void initialize(final FastMethod<T> p0) throws Throwable;
    }
}
