import com.hongminh54.marisaoptimize.systems.modules.Category;
import com.hongminh54.marisaoptimize.utils.misc.MyPotion;
import com.hongminh54.marisaoptimize.utils.render.DisplayItemUtils;
import net.minecraft.SharedConstants;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.Bootstrap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class DisplayItemCheck {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();
        if (Items.GOLDEN_SWORD.builtInRegistryHolder().areComponentsBound()) throw new AssertionError("Test requires unbound item components");
        for (var item : new net.minecraft.world.item.Item[] { Items.GOLDEN_SWORD, Items.ARMOR_STAND, Items.DIAMOND_BOOTS, Items.GLASS, Items.GRASS_BLOCK, Items.LAVA_BUCKET }) {
            ItemStack stack = DisplayItemUtils.toStack(item);
            if (stack.isEmpty() || stack.getItem() != item) throw new AssertionError("Display item identity");
            if (!item.builtInRegistryHolder().key().identifier().equals(stack.get(DataComponents.ITEM_MODEL))) throw new AssertionError("Display model");
            if (stack.get(DataComponents.ITEM_NAME) == null) throw new AssertionError("Display name");
            if (new Category("test", stack).icon != stack) throw new AssertionError("Category compatibility");
        }
        if (!DisplayItemUtils.toStack(Items.AIR).isEmpty() || !new Category("empty").icon.isEmpty()) throw new AssertionError("Empty icons");
        if (DisplayItemUtils.toStack(Items.DIAMOND, 7).getCount() != 7) throw new AssertionError("Display count");
        for (MyPotion potion : MyPotion.values()) {
            if (potion.potion.getItem() != Items.POTION) throw new AssertionError("Potion icon item: " + potion);
            var contents = potion.potion.get(DataComponents.POTION_CONTENTS);
            if (contents == null || contents.potion().isEmpty()) throw new AssertionError("Missing potion contents: " + potion);
            if (potion.ingredients.length == 0) throw new AssertionError("Missing brewing ingredients: " + potion);
        }
        if (!MyPotion.Strength.potion.get(DataComponents.POTION_CONTENTS).potion().orElseThrow().equals(net.minecraft.world.item.alchemy.Potions.STRENGTH)) throw new AssertionError("Strength icon contents");
        if (Items.GOLDEN_SWORD.builtInRegistryHolder().areComponentsBound()) throw new AssertionError("Display helper modified global registry");
        System.out.println("Display item checks passed with unbound registry components; no world or renderer tested.");
    }
}
