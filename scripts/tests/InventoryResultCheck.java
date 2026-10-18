import com.hongminh54.marisaoptimize.utils.player.FindItemResult;
import net.minecraft.world.InteractionHand;

public class InventoryResultCheck {
    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
    public static void main(String[] args) throws Exception {
        check(!new FindItemResult(-1, 0).found(), "Missing item sentinel changed");
        for (int slot = 0; slot <= 40; slot++) {
            var result = new FindItemResult(slot, 3);
            check(result.found() && result.count() == 3, "Result payload changed");
            check(result.isHotbar() == (slot <= 8), "Hotbar range changed");
            check(result.isMain() == (slot >= 9 && slot <= 35), "Main range changed");
            check(result.isArmor() == (slot >= 36 && slot <= 39), "Armor range changed");
        }
        check(new FindItemResult(40, 1).getHand() == InteractionHand.OFF_HAND, "Offhand result changed");
        // Pure private formulas are exercised without constructing game menus or transforming mixins.
        var type = com.hongminh54.marisaoptimize.utils.player.SlotUtils.class;
        String[] names = {"craftingTable", "furnace", "generic3x3", "enchantmentTable", "brewingStand", "villager", "beacon", "anvil", "hopper", "cartographyTable", "grindstone", "loom", "stonecutter", "crafter", "smithingTable"};
        int[] offsets = {10, 3, 9, 2, 5, 3, 1, 3, 5, 3, 3, 4, 2, 9, 4};
        for (int n = 0; n < names.length; n++) {
            var method = type.getDeclaredMethod(names[n], int.class);
            method.setAccessible(true);
            for (int slot = 0; slot < 41; slot++) {
                int expected = slot <= 8 ? offsets[n] + 27 + slot : slot <= 35 ? offsets[n] + slot - 9 : -1;
                check((int) method.invoke(null, slot) == expected, names[n] + " slot mapping changed at " + slot);
            }
        }
        var chest = type.getDeclaredMethod("genericContainer", int.class, int.class);
        chest.setAccessible(true);
        for (int rows = 1; rows <= 6; rows++) {
            for (int slot = 0; slot < 41; slot++) {
                int expected = slot <= 8 ? rows * 9 + 27 + slot : slot <= 35 ? rows * 9 + slot - 9 : -1;
                check((int) chest.invoke(null, slot, rows) == expected, "Chest mapping changed");
            }
        }
        var survival = type.getDeclaredMethod("survivalInventory", int.class);
        survival.setAccessible(true);
        for (int slot = 0; slot <= 40; slot++) {
            int expected = slot <= 8 ? 36 + slot : slot <= 35 ? slot : slot <= 39 ? 5 + slot - 36 : 45;
            check((int) survival.invoke(null, slot) == expected, "Survival mapping changed");
        }
        System.out.println("Inventory checks passed: result ranges, offhand, survival, 15 workstation formulas and chest rows 1-6; real SlotUtils compiled, menu dispatch/mounts/mixin application/slot clicks not runtime-tested.");
    }
}
