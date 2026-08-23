// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection;

import java.util.IdentityHashMap;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.InitInvoker;
import java.util.logging.Level;
import dev.imanity.antixray.libs.objenesis.ObjenesisHelper;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.ClassFieldCopier;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.util.Collection;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.ConstantReturningInvoker;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.util.Arrays;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.GeneratedHook;
import java.util.HashMap;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.Invoker;
import java.lang.reflect.Method;
import java.util.Map;

public abstract class ClassInterceptor
{
    private static final Object globalMethodDelegatesLock;
    private static Map<Class<?>, Map<Method, Invoker<?>>> globalMethodDelegatesMap;
    private static Map<ClassPair, EnhancedClass> enhancedTypes;
    private boolean useGlobalCallbacks;
    private final ClassLoader hookClassLoader;
    private final Map<Method, Invoker<?>> globalMethodDelegates;
    private final InstanceHolder lastHookedObject;
    private final ThreadLocal<StackInformation> stackInfo;
    
    public ClassInterceptor() {
        this.useGlobalCallbacks = true;
        this.lastHookedObject = new InstanceHolder();
        this.stackInfo = ThreadLocal.withInitial(() -> new StackInformation());
        this.hookClassLoader = this.getClass().getClassLoader();
        this.globalMethodDelegates = this.initMethodDelegatesMap();
    }
    
    public ClassInterceptor(final ClassLoader hookClassLoader) {
        this.useGlobalCallbacks = true;
        this.lastHookedObject = new InstanceHolder();
        this.stackInfo = ThreadLocal.withInitial(() -> new StackInformation());
        this.hookClassLoader = hookClassLoader;
        this.globalMethodDelegates = this.initMethodDelegatesMap();
    }
    
    private Map<Method, Invoker<?>> initMethodDelegatesMap() {
        synchronized (ClassInterceptor.globalMethodDelegatesLock) {
            return ClassInterceptor.globalMethodDelegatesMap.computeIfAbsent(this.getClass(), p0 -> new HashMap());
        }
    }
    
    protected abstract Invoker<?> getCallback(final Method p0);
    
    protected Invoker<?> getCallback(final Class<?> clazz, final Method method) {
        return this.getCallback(method);
    }
    
    protected void onClassGenerated(final Class<?> clazz) {
    }
    
    public ClassLoader getHookClassLoader() {
        return this.hookClassLoader;
    }
    
    public <T> T createInstance(final Class<T> clazz) {
        return createEnhancedClass(this, clazz, (T)null, null, null);
    }
    
    public <T> T constructInstance(final Class<T> clazz, final Class<?>[] array, final Object[] array2) {
        return createEnhancedClass(this, clazz, (T)null, array, array2);
    }
    
    public <T> T hook(final T t) {
        return createEnhancedClass(this, t.getClass(), t, null, null);
    }
    
    public void mock(final Object value) {
        this.lastHookedObject.value = value;
    }
    
    public static <T> T unhook(final T t) {
        if (t instanceof EnhancedObject) {
            final EnhancedObject enhancedObject = (EnhancedObject)t;
            final ClassInterceptor ci_getInterceptor = enhancedObject.CI_getInterceptor();
            final EnhancedClass ci_getEnhancedClass = enhancedObject.CI_getEnhancedClass();
            if (ci_getInterceptor.lastHookedObject.value == t) {
                ci_getInterceptor.lastHookedObject.value = null;
            }
            return ci_getEnhancedClass.createBase(t);
        }
        return t;
    }
    
    public static <T extends ClassInterceptor> T get(final Object o, final Class<T> clazz) {
        final ClassInterceptor value = get(o);
        return (T)(clazz.isInstance(value) ? value : null);
    }
    
    protected static ClassInterceptor get(final Object o) {
        return (o instanceof EnhancedObject) ? ((EnhancedObject)o).CI_getInterceptor() : null;
    }
    
    protected void storeCallbackForCurrentThread(final Method method, final Invoker<?> invoker) {
        this.stackInfo.get().storeCallback(method, invoker);
    }
    
    public final void setUseGlobalCallbacks(final boolean useGlobalCallbacks) {
        this.useGlobalCallbacks = useGlobalCallbacks;
    }
    
    protected final Object instance() {
        final Object currentInstance = this.stackInfo.get().currentInstance();
        if (currentInstance != null) {
            return currentInstance;
        }
        if (this.lastHookedObject.value == null) {
            throw new IllegalStateException("No object is handled right now");
        }
        return this.lastHookedObject.value;
    }
    
    protected final Class<?> instanceBaseType() {
        return findInstanceBaseType(this.instance());
    }
    
    public final Object invokeSuperMethod(final Method method, final Object o, final Object[] array) {
        return GeneratedHook.createSuperInvoker(o.getClass(), method).invokeVA(o, array);
    }
    
    public static Class<?> findInstanceBaseType(final Object o) {
        if (o instanceof EnhancedObject) {
            return ((EnhancedObject)o).CI_getBaseType();
        }
        if (o != null) {
            return o.getClass();
        }
        return null;
    }
    
    public static Class<?> findBaseType(Class<?> superclass) {
        if (superclass == null) {
            return null;
        }
        while (EnhancedObject.class.isAssignableFrom(superclass)) {
            superclass = superclass.getSuperclass();
        }
        return superclass;
    }
    
    private static synchronized <T> T createEnhancedClass(final ClassInterceptor currentInterceptor, final Class<?> clazz, final T t, final Class<?>[] array, final Object[] array2) {
        if (clazz == null) {
            throw new IllegalArgumentException("Input class type to be intercepted is null");
        }
        final ClassPair classPair = new ClassPair(currentInterceptor.getClass(), clazz);
        EnhancedClass enhancedClass = ClassInterceptor.enhancedTypes.get(classPair);
        if (enhancedClass == null) {
            final EnhancedClass enhancedClass2 = new EnhancedClass(clazz);
            final StackInformation stackInformation = currentInterceptor.stackInfo.get();
            enhancedClass2.setupEnhancedType(GeneratedHook.generate(currentInterceptor.getHookClassLoader(), clazz, (Collection<Class<?>>)Arrays.asList(EnhancedObject.class), method -> {
                MPLType.getName(method);
                if (method.getParameterCount() == 0) {
                    final String s;
                    if (s.equals("CI_getInterceptor")) {
                        return enhancedClass3.getInterceptorCallback;
                    }
                    else if (s.equals("CI_getBaseType")) {
                        return ConstantReturningInvoker.of(clazz2);
                    }
                    else if (s.equals("CI_getEnhancedClass")) {
                        return ConstantReturningInvoker.of(enhancedClass3);
                    }
                }
                classInterceptor.getCallback(clazz2, method);
                final Invoker invoker;
                if (invoker == null) {
                    return null;
                }
                else {
                    stackInformation2.storeCallback(method, invoker);
                    return new CallbackMethodInterceptor(method);
                }
            }));
            enhancedClass = enhancedClass2;
            ClassInterceptor.enhancedTypes.put(classPair, enhancedClass);
            currentInterceptor.onClassGenerated(enhancedClass.enhancedType);
        }
        enhancedClass.currentInterceptor = currentInterceptor;
        final T enhanced = enhancedClass.createEnhanced(t, array, array2);
        currentInterceptor.lastHookedObject.value = enhanced;
        ((EnhancedObject)enhanced).CI_getInterceptor();
        enhancedClass.currentInterceptor = null;
        return enhanced;
    }
    
    static {
        globalMethodDelegatesLock = new Object();
        ClassInterceptor.globalMethodDelegatesMap = new HashMap<Class<?>, Map<Method, Invoker<?>>>();
        ClassInterceptor.enhancedTypes = new HashMap<ClassPair, EnhancedClass>();
        MountiplexUtil.registerUnloader(new Runnable() {
            @Override
            public void run() {
                synchronized (ClassInterceptor.globalMethodDelegatesLock) {
                    ClassInterceptor.globalMethodDelegatesMap = (Map<Class<?>, Map<Method, Invoker<?>>>)new HashMap(0);
                    ClassInterceptor.enhancedTypes = (Map<ClassPair, EnhancedClass>)new HashMap(0);
                }
            }
        });
    }
    
    private static final class ClassPair
    {
        public final Class<?> hookClass;
        public final Class<?> instanceClass;
        private final int hashcode;
        
        public ClassPair(final Class<?> hookClass, final Class<?> instanceClass) {
            this.hookClass = hookClass;
            this.instanceClass = instanceClass;
            this.hashcode = (hookClass.hashCode() >> 1) + (instanceClass.hashCode() >> 1);
        }
        
        @Override
        public int hashCode() {
            return this.hashcode;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o instanceof ClassPair) {
                final ClassPair classPair = (ClassPair)o;
                return this.hookClass.equals(classPair.hookClass) && this.instanceClass.equals(classPair.instanceClass);
            }
            return false;
        }
    }
    
    public static final class EnhancedClass
    {
        public final Class<?> baseType;
        public final ObjectInstantiator<?> baseInstantiator;
        public final Invoker<?> getInterceptorCallback;
        private final ClassFieldCopier<Object> baseFieldCopier;
        public Class<?> enhancedType;
        public ObjectInstantiator<?> enhancedInstantiator;
        public ClassInterceptor currentInterceptor;
        
        public EnhancedClass(final Class<?> baseType) {
            this.baseType = baseType;
            if (baseType.isInterface()) {
                this.baseInstantiator = (() -> {
                    new UnsupportedOperationException("Base type " + clazz.getName() + " is an interface and cannot be instantiated");
                    throw;
                });
            }
            else {
                this.baseInstantiator = ObjenesisHelper.getInstantiatorOf(baseType);
                if (this.baseInstantiator == null) {
                    throw new RuntimeException("Base Class " + MPLType.getName(baseType) + " has no instantiator");
                }
            }
            this.baseFieldCopier = (ClassFieldCopier<Object>)ClassFieldCopier.of(baseType);
            this.getInterceptorCallback = GeneratedHook.createLocalField(() -> this.currentInterceptor);
        }
        
        public void setupEnhancedType(final Class<?> enhancedType) {
            this.enhancedType = enhancedType;
            this.enhancedInstantiator = ObjenesisHelper.getInstantiatorOf(enhancedType);
            if (this.enhancedInstantiator == null) {
                throw new RuntimeException("Enhanced Class " + MPLType.getName(enhancedType) + " has no instantiator");
            }
        }
        
        public <T> T createBase(final T t) {
            final Object instance = this.baseInstantiator.newInstance();
            if (instance == null) {
                throw new RuntimeException("Class " + MPLType.getName(this.baseType) + " could not be instantiated (newInstance failed)");
            }
            this.baseFieldCopier.copy(t, instance);
            return (T)instance;
        }
        
        public <T> T createEnhanced(final T t, final Class<?>[] parameterTypes, final Object[] initargs) {
            Object o = null;
            if (parameterTypes == null) {
                o = this.enhancedInstantiator.newInstance();
            }
            else {
                try {
                    o = this.enhancedType.getConstructor(parameterTypes).newInstance(initargs);
                }
                catch (final Throwable thrown) {
                    MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to construct " + MPLType.getName(this.enhancedType), thrown);
                }
            }
            if (o == null) {
                throw new RuntimeException("Class " + MPLType.getName(this.enhancedType) + " could not be instantiated (newInstance failed)");
            }
            if (t != null) {
                this.baseFieldCopier.copy(t, o);
            }
            return (T)o;
        }
    }
    
    private static class CallbackMethodInterceptor implements Invoker<Object>
    {
        private final Method method;
        
        public CallbackMethodInterceptor(final Method method) {
            this.method = method;
        }
        
        @Override
        public Object invokeVA(Object instance, final Object... array) {
            final ClassInterceptor ci_getInterceptor = ((EnhancedObject)instance).CI_getInterceptor();
            final StackInformation stackInformation = ci_getInterceptor.stackInfo.get();
            StackFrame next = stackInformation.frame.next;
            if (next == null) {
                final StackFrame frame = stackInformation.frame;
                final StackFrame next2 = new StackFrame(stackInformation.frame);
                frame.next = next2;
                next = next2;
            }
            stackInformation.frame = next;
            try {
                next.instance = instance;
                Invoker<?> invoker = stackInformation.getCallback(this.method);
                if (invoker == null) {
                    synchronized (ci_getInterceptor.globalMethodDelegates) {
                        invoker = ci_getInterceptor.globalMethodDelegates.get(this.method);
                    }
                    if (invoker == null) {
                        invoker = ci_getInterceptor.getCallback(ci_getInterceptor.instanceBaseType(), this.method);
                        if (invoker == null) {
                            invoker = GeneratedHook.createSuperInvoker(instance.getClass(), this.method);
                        }
                        if (ci_getInterceptor.useGlobalCallbacks) {
                            synchronized (ci_getInterceptor.globalMethodDelegates) {
                                ci_getInterceptor.globalMethodDelegates.put(this.method, invoker);
                            }
                        }
                    }
                    stackInformation.storeCallback(this.method, invoker);
                }
                if (invoker instanceof InterceptorCallback) {
                    invoker = ((InterceptorCallback)invoker).interceptorCallback;
                    instance = ci_getInterceptor;
                }
                return invoker.invokeVA(instance, array);
            }
            finally {
                next.instance = null;
                stackInformation.frame = next.prev;
            }
        }
    }
    
    public abstract static class InterceptorCallback implements Invoker<Object>
    {
        public Invoker<Object> interceptorCallback;
        
        public InterceptorCallback() {
            this.interceptorCallback = InitInvoker.unavailableMethod();
        }
        
        @Override
        public Object invokeVA(final Object o, final Object... array) {
            return this.interceptorCallback.invokeVA(((EnhancedObject)o).CI_getInterceptor(), array);
        }
    }
    
    private static final class StackInformation
    {
        private final Map<Method, Invoker<?>> methodDelegates_fast;
        private final Map<Method, Invoker<?>> methodDelegates;
        private Method last_method;
        private Invoker<?> last_method_callback;
        public StackFrame frame;
        
        private StackInformation() {
            this.methodDelegates_fast = new IdentityHashMap<Method, Invoker<?>>();
            this.methodDelegates = new HashMap<Method, Invoker<?>>();
            this.last_method = null;
            this.last_method_callback = null;
            this.frame = new StackFrame(null);
        }
        
        public Object currentInstance() {
            return this.frame.instance;
        }
        
        public Invoker<?> getCallback(final Method method) {
            if (method == this.last_method) {
                return this.last_method_callback;
            }
            final Invoker last_method_callback = this.methodDelegates_fast.get(method);
            if (last_method_callback != null) {
                this.last_method = method;
                return this.last_method_callback = last_method_callback;
            }
            final Invoker last_method_callback2 = this.methodDelegates.get(method);
            if (last_method_callback2 != null) {
                this.methodDelegates_fast.put(method, last_method_callback2);
                this.last_method = method;
                return this.last_method_callback = last_method_callback2;
            }
            return null;
        }
        
        public void storeCallback(final Method last_method, final Invoker<?> last_method_callback) {
            this.last_method = last_method;
            this.last_method_callback = last_method_callback;
            this.methodDelegates.put(last_method, last_method_callback);
        }
    }
    
    private static final class StackFrame
    {
        public Object instance;
        public final StackFrame prev;
        public StackFrame next;
        
        public StackFrame(final StackFrame prev) {
            this.instance = null;
            this.next = null;
            this.prev = prev;
        }
    }
    
    private static class InstanceHolder
    {
        public Object value;
        
        private InstanceHolder() {
            this.value = null;
        }
    }
    
    protected interface EnhancedObject
    {
        ClassInterceptor CI_getInterceptor();
        
        Class<?> CI_getBaseType();
        
        EnhancedClass CI_getEnhancedClass();
    }
}
