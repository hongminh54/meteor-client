// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util;

import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.ReflectionAccessor;
import java.lang.reflect.Modifier;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.lang.reflect.Field;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.Writer;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.Reader;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.Copier;

public final class FastField<T> implements IgnoresRemapping, LazyInitializedObject, Copier, Reader<T>, Writer<T>
{
    private Reader<T> reader;
    private Writer<T> writer;
    private Copier copier;
    private Field field;
    private String missingInfo;
    
    public FastField() {
        this.missingInfo = "!!UNKNOWN!!";
        this.init(null);
    }
    
    public FastField(final Field field) {
        this.missingInfo = "!!UNKNOWN!!";
        this.init(field);
    }
    
    public final void init(final Field field) {
        this.field = field;
        final FastFieldInitProxy copier = new FastFieldInitProxy();
        this.reader = copier;
        this.writer = copier;
        this.copier = copier;
    }
    
    public final void initUnavailable(final String missingInfo) {
        this.init(null);
        this.missingInfo = missingInfo;
    }
    
    public final void checkInit() {
        if (this.field == null) {
            throw new UnsupportedOperationException("Field " + this.missingInfo + " is not available");
        }
    }
    
    public final boolean isAvailable() {
        return this.field != null;
    }
    
    public final Field getField() {
        return this.field;
    }
    
    public final Class<T> getType() {
        if (this.field == null) {
            return null;
        }
        return (Class<T>)this.field.getType();
    }
    
    public final String getName() {
        if (this.field == null) {
            return "null";
        }
        return MPLType.getName(this.field);
    }
    
    public final String getDescription() {
        if (this.field == null) {
            return this.missingInfo;
        }
        return this.field.toString();
    }
    
    public final boolean isStatic() {
        return this.field != null && Modifier.isStatic(this.field.getModifiers());
    }
    
    private void makeAccessible() {
        try {
            this.field.setAccessible(true);
        }
        catch (final Throwable t) {
            throw new RuntimeException("Failed to make field " + MPLType.getName(this.field) + " accessible");
        }
    }
    
    @Override
    public void forceInitialization() {
        this.reader.checkCanRead();
    }
    
    @Override
    public final void checkCanCopy() {
        this.checkCanWrite();
        this.checkCanRead();
    }
    
    @Override
    public final void checkCanWrite() {
        this.writer.checkCanWrite();
    }
    
    @Override
    public final void checkCanRead() {
        this.reader.checkCanRead();
    }
    
    @Override
    public T get(final Object o) {
        return this.reader.get(o);
    }
    
    @Override
    public double getDouble(final Object o) {
        return this.reader.getDouble(o);
    }
    
    @Override
    public float getFloat(final Object o) {
        return this.reader.getFloat(o);
    }
    
    @Override
    public byte getByte(final Object o) {
        return this.reader.getByte(o);
    }
    
    @Override
    public short getShort(final Object o) {
        return this.reader.getShort(o);
    }
    
    @Override
    public int getInteger(final Object o) {
        return this.reader.getInteger(o);
    }
    
    @Override
    public long getLong(final Object o) {
        return this.reader.getLong(o);
    }
    
    @Override
    public char getCharacter(final Object o) {
        return this.reader.getCharacter(o);
    }
    
    @Override
    public boolean getBoolean(final Object o) {
        return this.reader.getBoolean(o);
    }
    
    @Override
    public Field getReadField() {
        return this.reader.getReadField();
    }
    
    @Override
    public void set(final Object o, final T t) {
        this.writer.set(o, t);
    }
    
    @Override
    public void setDouble(final Object o, final double n) {
        this.writer.setDouble(o, n);
    }
    
    @Override
    public void setFloat(final Object o, final float n) {
        this.writer.setFloat(o, n);
    }
    
    @Override
    public void setByte(final Object o, final byte b) {
        this.writer.setByte(o, b);
    }
    
    @Override
    public void setShort(final Object o, final short n) {
        this.writer.setShort(o, n);
    }
    
    @Override
    public void setInteger(final Object o, final int n) {
        this.writer.setInteger(o, n);
    }
    
    @Override
    public void setLong(final Object o, final long n) {
        this.writer.setLong(o, n);
    }
    
    @Override
    public void setCharacter(final Object o, final char c) {
        this.writer.setCharacter(o, c);
    }
    
    @Override
    public void setBoolean(final Object o, final boolean b) {
        this.writer.setBoolean(o, b);
    }
    
    @Override
    public Field getWriteField() {
        return this.writer.getWriteField();
    }
    
    @Override
    public void copy(final Object o, final Object o2) {
        this.copier.copy(o, o2);
    }
    
    @Override
    public Field getCopyField() {
        return this.copier.getCopyField();
    }
    
    private final class FastFieldInitProxy implements Copier, Reader<T>, Writer<T>
    {
        private ReflectionAccessor<T> access() {
            if (FastField.this.writer instanceof ReflectionAccessor) {
                return (ReflectionAccessor)FastField.this.writer;
            }
            if (FastField.this.reader instanceof ReflectionAccessor) {
                return (ReflectionAccessor)FastField.this.reader;
            }
            if (FastField.this.copier instanceof ReflectionAccessor) {
                return (ReflectionAccessor)FastField.this.copier;
            }
            return ReflectionAccessor.create(FastField.this.field);
        }
        
        private Reader<T> read() {
            if (FastField.this.reader == this) {
                synchronized (FastField.this) {
                    if (FastField.this.reader == this) {
                        FastField.this.checkInit();
                        if (!Resolver.isPublic(FastField.this.field)) {
                            FastField.this.makeAccessible();
                        }
                        FastField.this.reader = this.access();
                    }
                }
            }
            return FastField.this.reader;
        }
        
        private Writer<T> write() {
            if (FastField.this.writer == this) {
                synchronized (FastField.this) {
                    if (FastField.this.writer == this) {
                        FastField.this.checkInit();
                        final int modifiers = FastField.this.field.getModifiers();
                        if (!Resolver.isPublic(FastField.this.field) || Modifier.isFinal(modifiers)) {
                            FastField.this.makeAccessible();
                        }
                        FastField.this.writer = this.access();
                    }
                }
            }
            return FastField.this.writer;
        }
        
        private Copier copy() {
            if (FastField.this.copier == this) {
                synchronized (FastField.this) {
                    if (FastField.this.copier == this) {
                        FastField.this.checkInit();
                        final int modifiers = FastField.this.field.getModifiers();
                        if (Modifier.isStatic(modifiers)) {
                            throw new RuntimeException("Static fields can not be copied");
                        }
                        if (!Resolver.isPublic(FastField.this.field) || Modifier.isFinal(modifiers)) {
                            FastField.this.makeAccessible();
                        }
                        FastField.this.copier = this.access();
                    }
                }
            }
            return FastField.this.copier;
        }
        
        @Override
        public final void checkCanCopy() {
            this.checkCanWrite();
            this.checkCanRead();
        }
        
        @Override
        public final void checkCanWrite() {
            this.write().checkCanWrite();
        }
        
        @Override
        public final void checkCanRead() {
            this.read().checkCanRead();
        }
        
        @Override
        public T get(final Object o) {
            return this.read().get(o);
        }
        
        @Override
        public double getDouble(final Object o) {
            return this.read().getDouble(o);
        }
        
        @Override
        public float getFloat(final Object o) {
            return this.read().getFloat(o);
        }
        
        @Override
        public byte getByte(final Object o) {
            return this.read().getByte(o);
        }
        
        @Override
        public short getShort(final Object o) {
            return this.read().getShort(o);
        }
        
        @Override
        public int getInteger(final Object o) {
            return this.read().getInteger(o);
        }
        
        @Override
        public long getLong(final Object o) {
            return this.read().getLong(o);
        }
        
        @Override
        public char getCharacter(final Object o) {
            return this.read().getCharacter(o);
        }
        
        @Override
        public boolean getBoolean(final Object o) {
            return this.read().getBoolean(o);
        }
        
        @Override
        public Field getReadField() {
            return this.read().getReadField();
        }
        
        @Override
        public void set(final Object o, final T t) {
            this.write().set(o, t);
        }
        
        @Override
        public void setDouble(final Object o, final double n) {
            this.write().setDouble(o, n);
        }
        
        @Override
        public void setFloat(final Object o, final float n) {
            this.write().setFloat(o, n);
        }
        
        @Override
        public void setByte(final Object o, final byte b) {
            this.write().setByte(o, b);
        }
        
        @Override
        public void setShort(final Object o, final short n) {
            this.write().setShort(o, n);
        }
        
        @Override
        public void setInteger(final Object o, final int n) {
            this.write().setInteger(o, n);
        }
        
        @Override
        public void setLong(final Object o, final long n) {
            this.write().setLong(o, n);
        }
        
        @Override
        public void setCharacter(final Object o, final char c) {
            this.write().setCharacter(o, c);
        }
        
        @Override
        public void setBoolean(final Object o, final boolean b) {
            this.write().setBoolean(o, b);
        }
        
        @Override
        public Field getWriteField() {
            return this.write().getWriteField();
        }
        
        @Override
        public void copy(final Object o, final Object o2) {
            this.copy().copy(o, o2);
        }
        
        @Override
        public Field getCopyField() {
            return this.copy().getCopyField();
        }
    }
}
