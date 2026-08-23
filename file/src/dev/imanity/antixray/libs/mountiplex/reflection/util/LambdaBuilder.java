// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import dev.imanity.antixray.libs.mountiplex.reflection.ClassInterceptor;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.Invoker;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.ConstantReturningInvoker;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;
import java.util.Map;

public class LambdaBuilder<T>
{
    private static final Map<Class<?>, LambdaBuilder<?>> cachedBuilders;
    private final Class<T> type;
    private final Method abstractMethod;
    
    private LambdaBuilder(final Class<T> obj) {
        this.type = obj;
        if (!obj.isInterface()) {
            try {
                if (Modifier.isPrivate(obj.getDeclaredConstructor((Class[])new Class[0]).getModifiers())) {
                    throw new IllegalArgumentException("Type " + obj + " has a private empty constructor");
                }
            }
            catch (final NoSuchMethodException ex) {
                throw new IllegalArgumentException("Type " + obj + " does not have an empty constructor");
            }
        }
        final List<? super Method> list = Stream.of(obj.getMethods()).filter(method -> Modifier.isAbstract(method.getModifiers())).collect((Collector<? super Method, ?, List<? super Method>>)Collectors.toList());
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Type " + obj + " does not have any abstract methods");
        }
        if (list.size() > 1) {
            throw new IllegalArgumentException("Type " + obj + " has too many abstract methods (" + list.size() + ")");
        }
        this.abstractMethod = (Method)list.get(0);
    }
    
    public T createConstant(final Object o) {
        return this.create(ConstantReturningInvoker.of(o));
    }
    
    public T create(final Invoker<?> invoker) {
        final ClassInterceptor classInterceptor = new ClassInterceptor() {
            @Override
            protected Invoker<?> getCallback(final Method method) {
                if (method.equals(LambdaBuilder.this.abstractMethod)) {
                    return invoker;
                }
                return null;
            }
        };
        classInterceptor.setUseGlobalCallbacks(false);
        return classInterceptor.createInstance(this.type);
    }
    
    public static <C, T extends C> LambdaBuilder<T> of(final Class<C> key) {
        return (LambdaBuilder)LambdaBuilder.cachedBuilders.computeIfAbsent(key, LambdaBuilder::new);
    }
    
    static {
        cachedBuilders = new ConcurrentHashMap<Class<?>, LambdaBuilder<?>>();
    }
}
