// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.asm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface MPLTypeHelper
{
    String getClassName(final Class<?> p0);
    
    String getMethodName(final Method p0);
    
    String getFieldName(final Field p0);
    
    Method getDeclaredMethod(final Class<?> p0, final String p1, final Class<?>... p2) throws NoSuchMethodException, SecurityException;
    
    Field getDeclaredField(final Class<?> p0, final String p1) throws NoSuchFieldException, SecurityException;
    
    Class<?> getClassByName(final String p0, final boolean p1, final ClassLoader p2) throws ClassNotFoundException;
}
