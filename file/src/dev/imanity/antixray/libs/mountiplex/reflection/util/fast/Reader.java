// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.fast;

import java.lang.reflect.Field;

public interface Reader<T>
{
    T get(final Object p0);
    
    double getDouble(final Object p0);
    
    float getFloat(final Object p0);
    
    byte getByte(final Object p0);
    
    short getShort(final Object p0);
    
    int getInteger(final Object p0);
    
    long getLong(final Object p0);
    
    char getCharacter(final Object p0);
    
    boolean getBoolean(final Object p0);
    
    Field getReadField();
    
    void checkCanRead();
}
