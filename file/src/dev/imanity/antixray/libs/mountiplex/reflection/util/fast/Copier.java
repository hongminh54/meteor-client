// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.fast;

import java.lang.reflect.Field;

public interface Copier
{
    void copy(final Object p0, final Object p1);
    
    Field getCopyField();
    
    void checkCanCopy();
}
