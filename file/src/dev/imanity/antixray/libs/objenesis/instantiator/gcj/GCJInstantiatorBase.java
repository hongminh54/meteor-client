// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.gcj;

import java.io.IOException;
import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

public abstract class GCJInstantiatorBase<T> implements ObjectInstantiator<T>
{
    static Method newObjectMethod;
    static ObjectInputStream dummyStream;
    protected final Class<T> type;
    
    private static void initialize() {
        if (GCJInstantiatorBase.newObjectMethod == null) {
            try {
                (GCJInstantiatorBase.newObjectMethod = ObjectInputStream.class.getDeclaredMethod("newObject", Class.class, Class.class)).setAccessible(true);
                GCJInstantiatorBase.dummyStream = new DummyStream();
            }
            catch (final RuntimeException | NoSuchMethodException | IOException ex) {
                throw new ObjenesisException((Throwable)ex);
            }
        }
    }
    
    public GCJInstantiatorBase(final Class<T> type) {
        this.type = type;
        initialize();
    }
    
    @Override
    public abstract T newInstance();
    
    static {
        GCJInstantiatorBase.newObjectMethod = null;
    }
    
    private static class DummyStream extends ObjectInputStream
    {
        public DummyStream() {
        }
    }
}
