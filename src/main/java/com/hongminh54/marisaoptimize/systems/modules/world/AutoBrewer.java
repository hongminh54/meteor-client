/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.systems.modules.world;

import com.hongminh54.marisaoptimize.settings.PotionSetting;
import com.hongminh54.marisaoptimize.settings.Setting;
import com.hongminh54.marisaoptimize.settings.SettingGroup;
import com.hongminh54.marisaoptimize.systems.modules.Categories;
import com.hongminh54.marisaoptimize.systems.modules.Module;
import com.hongminh54.marisaoptimize.utils.misc.MyPotion;
import com.hongminh54.marisaoptimize.utils.player.InvUtils;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.inventory.BrewingStandMenu;

public class AutoBrewer extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<MyPotion> potion = sgGeneral.add(new PotionSetting.Builder()
        .name("potion")
        .description("The type of potion to brew.")
        .defaultValue(MyPotion.Strength)
        .build()
    );

    private int ingredientI;
    private boolean first;
    private int timer;

    public AutoBrewer() {
        super(Categories.World, "auto-brewer", "Automatically brews the specified potion.");
    }

    @Override
    public void onActivate() {
        first = false;
    }

    public void onBrewingStandClose() {
        first = false;
    }

    public void tick(BrewingStandMenu c) {
        timer++;

        // When the brewing stand is opened.
        if (!first) {
            first = true;

            ingredientI = -2;
            timer = 0;
        }

        // Wait for the brewing to complete.
        if (c.getBrewingTicks() != 0 || timer < 5) return;

        if (ingredientI == -2) {
            // Take the bottles.
            if (takePotions(c)) return;
            ingredientI++;
            timer = 0;
        } else if (ingredientI == -1) {
            // Insert water bottles into the brewing stand.
            if (insertWaterBottles(c)) return;
            ingredientI++;
            timer = 0;
        } else if (ingredientI < potion.get().ingredients.length) {
            // Check for fuel for the brew and add the ingredient.
            if (checkFuel(c)) return;
            if (insertIngredient(c, potion.get().ingredients[ingredientI])) return;
            ingredientI++;
            timer = 0;
        } else {
            // Reset the loop.
            ingredientI = -2;
            timer = 0;
        }
    }

    private boolean insertIngredient(BrewingStandMenu c, Item ingredient) {
        int slot = -1;

        for (int slotI = 5; slotI < c.slots.size(); slotI++) {
            if (c.slots.get(slotI).getItem().getItem() == ingredient) {
                slot = slotI;
                break;
            }
        }

        if (slot == -1) {
            error("You do not have any %s left in your inventory... disabling.", ingredient.components().get(DataComponents.ITEM_NAME).getString());
            toggle();
            return true;
        }

        moveOneItem(c, slot, 3);

        return false;
    }

    private boolean checkFuel(BrewingStandMenu c) {
        if (c.getFuel() == 0) {
            int slot = -1;

            for (int slotI = 5; slotI < c.slots.size(); slotI++) {
                if (c.slots.get(slotI).getItem().getItem() == Items.BLAZE_POWDER) {
                    slot = slotI;
                    break;
                }
            }

            if (slot == -1) {
                error("You do not have a sufficient amount of blaze powder to use as fuel for the brew... disabling.");
                toggle();
                return true;
            }

            moveOneItem(c, slot, 4);
        }

        return false;
    }

    private void moveOneItem(BrewingStandMenu c, int from, int to) {
        InvUtils.move().fromId(from).toId(to);
    }

    private boolean insertWaterBottles(BrewingStandMenu c) {
        for (int i = 0; i < 3; i++) {
            int slot = -1;

            for (int slotI = 5; slotI < c.slots.size(); slotI++) {
                if (c.slots.get(slotI).getItem().getItem() == Items.POTION) {
                    PotionContents contents = c.slots.get(slotI).getItem().getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
                    if (contents.potion().filter(Potions.WATER::equals).isPresent()) {
                        slot = slotI;
                        break;
                    }
                }
            }

            if (slot == -1) {
                error("You do not have a sufficient amount of water bottles to complete this brew... disabling.");
                toggle();
                return true;
            }

            InvUtils.move().fromId(slot).toId(i);
        }

        return false;
    }

    private boolean takePotions(BrewingStandMenu c) {
        for (int i = 0; i < 3; i++) {
            InvUtils.shiftClick().slotId(i);

            if (!c.slots.get(i).getItem().isEmpty()) {
                error("You do not have a sufficient amount of inventory space... disabling.");
                toggle();
                return true;
            }
        }

        return false;
    }
}
