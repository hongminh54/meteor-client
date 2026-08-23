// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.generated.org.bukkit.craftbukkit.util;

import dev.imanity.generated.Common;
import net.minecraft.world.level.block.Block;
import org.bukkit.Material;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;

@Template.InstanceType("org.bukkit.craftbukkit.util.CraftMagicNumbers")
public abstract class CraftMagicNumbersHandle extends Template.Handle
{
    public static final CraftMagicNumbersClass T;
    
    public static CraftMagicNumbersHandle createHandle(final Object o) {
        return CraftMagicNumbersHandle.T.createHandle(o);
    }
    
    public static Material getMaterialFromBlock(final Object o) {
        return CraftMagicNumbersHandle.T.getMaterialFromBlock.invoke(o);
    }
    
    public static Material getMaterialFromItem(final Object o) {
        return CraftMagicNumbersHandle.T.getMaterialFromItem.invoke(o);
    }
    
    public static Block getBlockFromMaterial(final Material material) {
        return CraftMagicNumbersHandle.T.getBlockFromMaterial.invoker.invoke(null, material);
    }
    
    static {
        T = Template.Class.create(CraftMagicNumbersClass.class, Common.INSTANCE);
    }
    
    public static final class CraftMagicNumbersClass extends Template.Class<CraftMagicNumbersHandle>
    {
        public final Template.StaticMethod.Converted<Material> getMaterialFromBlock;
        public final Template.StaticMethod.Converted<Material> getMaterialFromItem;
        public final Template.StaticMethod<Block> getBlockFromMaterial;
        
        public CraftMagicNumbersClass() {
            this.getMaterialFromBlock = new Template.StaticMethod.Converted<Material>();
            this.getMaterialFromItem = new Template.StaticMethod.Converted<Material>();
            this.getBlockFromMaterial = new Template.StaticMethod<Block>();
        }
    }
}
