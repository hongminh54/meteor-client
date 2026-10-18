/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.systems.modules.movement;

import com.hongminh54.marisaoptimize.systems.modules.Categories;
import com.hongminh54.marisaoptimize.systems.modules.Module;
import net.minecraft.world.level.block.Block;

import java.util.List;
import com.hongminh54.marisaoptimize.settings.*;

public class Slippy extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    public final Setting<Double> friction = sgGeneral.add(new DoubleSetting.Builder()
        .name("friction")
        .description("The base friction level.")
        .range(0.01, 1.10)
        .sliderRange(0.01, 1.10)
        .defaultValue(1)
        .build()
    );

    public final Setting<ListMode> listMode = sgGeneral.add(new EnumSetting.Builder<ListMode>()
        .name("list-mode")
        .description("The mode to select blocks.")
        .defaultValue(ListMode.Blacklist)
        .build()
    );

    public final Setting<List<Block>> ignoredBlocks = sgGeneral.add(new BlockListSetting.Builder()
        .name("ignored-blocks")
        .description("Decide which blocks not to slip on")
        .visible(() -> listMode.get() == ListMode.Blacklist)
        .build()
    );

    public final Setting<List<Block>> allowedBlocks = sgGeneral.add(new BlockListSetting.Builder()
        .name("allowed-blocks")
        .description("Decide which blocks to slip on")
        .visible(() -> listMode.get() == ListMode.Whitelist)
        .build()
    );

    public Slippy() {
        super(Categories.Movement, "slippy", "Changes the base friction level of blocks.");
    }

    public enum ListMode {
        Whitelist,
        Blacklist
    }
}
