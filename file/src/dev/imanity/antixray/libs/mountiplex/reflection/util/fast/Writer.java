// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.fast;

import java.lang.reflect.Field;

public interface Writer<T>
{
    void set(final Object p0, final T p1);
    
    void setDouble(final Object p0, final double p1);
    
    void setFloat(final Object p0, final float p1);
    
    void setByte(final Object p0, final byte p1);
    
    void setShort(final Object p0, final short p1);
    
    void setInteger(final Object p0, final int p1);
    
    void setLong(final Object p0, final long p1);
    
    void setCharacter(final Object p0, final char p1);
    
    void setBoolean(final Object p0, final boolean p1);
    
    Field getWriteField();
    
    void checkCanWrite();
}
