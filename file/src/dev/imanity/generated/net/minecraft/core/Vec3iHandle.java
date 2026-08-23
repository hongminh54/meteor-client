// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.net.minecraft.core;

import dev.imanity.generated.Common;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("net.minecraft.core.Vec3i")
public abstract class Vec3iHandle extends Template.Handle
{
    public static final Vec3iClass T;
    
    public static Vec3iHandle createHandle(final Object o) {
        return Vec3iHandle.T.createHandle(o);
    }
    
    public abstract int getX();
    
    public abstract int getY();
    
    public abstract int getZ();
    
    public abstract boolean isPositionInBox(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    static {
        T = Template.Class.create(Vec3iClass.class, Common.INSTANCE);
    }
    
    public static final class Vec3iClass extends Template.Class<Vec3iHandle>
    {
        public final Template.Method<Integer> getX;
        public final Template.Method<Integer> getY;
        public final Template.Method<Integer> getZ;
        public final Template.Method<Boolean> isPositionInBox;
        
        public Vec3iClass() {
            this.getX = new Template.Method<Integer>();
            this.getY = new Template.Method<Integer>();
            this.getZ = new Template.Method<Integer>();
            this.isPositionInBox = new Template.Method<Boolean>();
        }
    }
}
