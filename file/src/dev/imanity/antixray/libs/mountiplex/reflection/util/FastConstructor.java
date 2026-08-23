// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util;

import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.ReflectionConstructor;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ParameterDeclaration;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ClassResolver;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ConstructorDeclaration;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.Constructor;

public class FastConstructor<T> implements IgnoresRemapping, LazyInitializedObject, Constructor<T>
{
    private Constructor<T> constructor;
    private ConstructorDeclaration constructorDec;
    private String missingInfo;
    
    public FastConstructor() {
        this.missingInfo = "!!UNKNOWN!!";
        this.constructorDec = null;
        this.constructor = new FastConstructorInitProxy();
    }
    
    public FastConstructor(final java.lang.reflect.Constructor<?> constructor) {
        this.missingInfo = "!!UNKNOWN!!";
        this.constructorDec = new ConstructorDeclaration(ClassResolver.DEFAULT, constructor);
        this.constructor = new FastConstructorInitProxy();
    }
    
    public final void init(final java.lang.reflect.Constructor<?> constructor) {
        this.constructorDec = new ConstructorDeclaration(ClassResolver.DEFAULT, constructor);
        this.constructor = new FastConstructorInitProxy();
    }
    
    public final void init(final ConstructorDeclaration constructorDec) {
        if (constructorDec != null && constructorDec.constructor == null) {
            this.constructorDec = null;
            this.constructor = new FastConstructorInitProxy();
        }
        else {
            this.constructorDec = constructorDec;
            this.constructor = new FastConstructorInitProxy();
        }
    }
    
    public final void initUnavailable(final String missingInfo) {
        this.constructorDec = null;
        this.constructor = new FastConstructorInitProxy();
        this.missingInfo = missingInfo;
    }
    
    public final void checkInit() {
        if (this.constructorDec == null) {
            throw new UnsupportedOperationException("Constructor " + this.missingInfo + " is not available");
        }
    }
    
    public final boolean isAvailable() {
        return this.constructorDec != null;
    }
    
    public final java.lang.reflect.Constructor<?> getConstructor() {
        return (this.constructorDec == null) ? null : this.constructorDec.constructor;
    }
    
    public final boolean isConstructor(final java.lang.reflect.Constructor<?> obj) {
        return this.constructorDec != null && this.constructorDec.constructor != null && this.constructorDec.constructor.equals(obj);
    }
    
    public final String getName() {
        if (this.constructorDec == null) {
            return "null";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("constr");
        final ParameterDeclaration[] parameters = this.constructorDec.parameters.parameters;
        for (int length = parameters.length, i = 0; i < length; ++i) {
            sb.append(parameters[i].name.real());
        }
        return sb.toString();
    }
    
    @Override
    public void forceInitialization() {
        if (this.constructor instanceof FastConstructorInitProxy) {
            ((FastConstructorInitProxy)this.constructor).init();
        }
    }
    
    @Override
    public T newInstanceVA(final Object... array) {
        return this.constructor.newInstanceVA(array);
    }
    
    @Override
    public T newInstance() {
        return this.constructor.newInstance();
    }
    
    @Override
    public T newInstance(final Object o) {
        return this.constructor.newInstance(o);
    }
    
    @Override
    public T newInstance(final Object o, final Object o2) {
        return this.constructor.newInstance(o, o2);
    }
    
    @Override
    public T newInstance(final Object o, final Object o2, final Object o3) {
        return this.constructor.newInstance(o, o2, o3);
    }
    
    @Override
    public T newInstance(final Object o, final Object o2, final Object o3, final Object o4) {
        return this.constructor.newInstance(o, o2, o3, o4);
    }
    
    @Override
    public T newInstance(final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
        return this.constructor.newInstance(o, o2, o3, o4, o5);
    }
    
    private final class FastConstructorInitProxy implements Constructor<T>
    {
        private final Constructor<T> init() {
            if (FastConstructor.this.constructor == this) {
                synchronized (FastConstructor.this) {
                    if (FastConstructor.this.constructor == this) {
                        FastConstructor.this.checkInit();
                        FastConstructor.this.constructorDec.constructor.setAccessible(true);
                        FastConstructor.this.constructor = (Constructor<T>)ReflectionConstructor.create(FastConstructor.this.constructorDec.constructor);
                    }
                }
            }
            return FastConstructor.this.constructor;
        }
        
        @Override
        public T newInstanceVA(final Object... array) {
            return this.init().newInstanceVA(array);
        }
        
        @Override
        public T newInstance() {
            return this.init().newInstance();
        }
        
        @Override
        public T newInstance(final Object o) {
            return this.init().newInstance(o);
        }
        
        @Override
        public T newInstance(final Object o, final Object o2) {
            return this.init().newInstance(o, o2);
        }
        
        @Override
        public T newInstance(final Object o, final Object o2, final Object o3) {
            return this.init().newInstance(o, o2, o3);
        }
        
        @Override
        public T newInstance(final Object o, final Object o2, final Object o3, final Object o4) {
            return this.init().newInstance(o, o2, o3, o4);
        }
        
        @Override
        public T newInstance(final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
            return this.init().newInstance(o, o2, o3, o4, o5);
        }
    }
}
