package com.hongminh54.marisaoptimize.utils.player;

/** Compile-only inventory action boundary. Never clicks slots or drops items. */
public class InvUtils {
    public static Action drop() { throw new UnsupportedOperationException("Inventory action compile stub"); }
    public static Action dropOne() { throw new UnsupportedOperationException("Inventory action compile stub"); }
    public static class Action {
        public void slot(int slot) {}
        public void slotOffhand() {}
        public void slotHotbar(int slot) {}
        public void slotMain(int slot) {}
        public void slotArmor(int slot) {}
    }
}
