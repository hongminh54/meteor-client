// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.core;

import dev.imanity.generated.Common;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.Optional
@Template.InstanceType("net.minecraft.core.IdMapper")
public abstract class IdMapperHandle extends Template.Handle
{
    public static final IdMapperClass T;
    
    public static IdMapperHandle createHandle(final Object o) {
        return IdMapperHandle.T.createHandle(o);
    }
    
    public abstract int getId(final Object p0);
    
    static {
        T = Template.Class.create(IdMapperClass.class, Common.INSTANCE);
    }
    
    public static final class IdMapperClass extends Template.Class<IdMapperHandle>
    {
        public final Template.Method<Integer> getId;
        
        public IdMapperClass() {
            this.getId = new Template.Method<Integer>();
        }
    }
}
