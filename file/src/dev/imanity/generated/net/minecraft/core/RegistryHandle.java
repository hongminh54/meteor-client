// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.core;

import dev.imanity.generated.Common;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.Optional
@Template.InstanceType("net.minecraft.core.Registry")
public abstract class RegistryHandle extends Template.Handle
{
    public static final RegistryClass T;
    
    public static RegistryHandle createHandle(final Object o) {
        return RegistryHandle.T.createHandle(o);
    }
    
    public static Object getWindowTypeByName(final String s) {
        return RegistryHandle.T.getWindowTypeByName.invoker.invoke(null, s);
    }
    
    public static int getWindowTypeId(final Object o) {
        return RegistryHandle.T.getWindowTypeId.invoker.invoke(null, o);
    }
    
    static {
        T = Template.Class.create(RegistryClass.class, Common.INSTANCE);
    }
    
    public static final class RegistryClass extends Template.Class<RegistryHandle>
    {
        public final Template.StaticMethod<Object> getWindowTypeByName;
        public final Template.StaticMethod<Integer> getWindowTypeId;
        
        public RegistryClass() {
            this.getWindowTypeByName = new Template.StaticMethod<Object>();
            this.getWindowTypeId = new Template.StaticMethod<Integer>();
        }
    }
}
