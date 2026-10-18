/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.systems.modules;

import com.hongminh54.marisaoptimize.addons.AddonManager;
import com.hongminh54.marisaoptimize.addons.MeteorAddon;
import net.minecraft.world.item.Items;

public class Categories {
    public static final Category Combat = new Category("Combat", Items.GOLDEN_SWORD.getDefaultInstance());
    public static final Category Player = new Category("Player", Items.ARMOR_STAND.getDefaultInstance());
    public static final Category Movement = new Category("Movement", Items.DIAMOND_BOOTS.getDefaultInstance());
    public static final Category Render = new Category("Render", Items.GLASS.getDefaultInstance());
    public static final Category World = new Category("World", Items.GRASS_BLOCK.getDefaultInstance());
    public static final Category Misc = new Category("Misc", Items.LAVA_BUCKET.getDefaultInstance());

    public static boolean REGISTERING;

    public static void init() {
        REGISTERING = true;

        // Meteor
        Modules.registerCategory(Combat);
        Modules.registerCategory(Player);
        Modules.registerCategory(Movement);
        Modules.registerCategory(Render);
        Modules.registerCategory(World);
        Modules.registerCategory(Misc);

        // Addons
        AddonManager.ADDONS.forEach(MeteorAddon::onRegisterCategories);

        REGISTERING = false;
    }
}
