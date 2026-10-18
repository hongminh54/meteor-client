/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.systems.modules.combat;

import com.hongminh54.marisaoptimize.events.world.TickEvent;
import com.hongminh54.marisaoptimize.settings.BoolSetting;
import com.hongminh54.marisaoptimize.settings.Setting;
import com.hongminh54.marisaoptimize.settings.SettingGroup;
import com.hongminh54.marisaoptimize.systems.modules.Categories;
import com.hongminh54.marisaoptimize.systems.modules.Module;
import com.hongminh54.marisaoptimize.utils.player.InvUtils;
import com.hongminh54.marisaoptimize.utils.world.BlockUtils;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;

public class AntiAnchor extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Boolean> rotate = sgGeneral.add(new BoolSetting.Builder()
        .name("rotate")
        .description("Makes you rotate when placing.")
        .defaultValue(true)
        .build()
    );

    private final Setting<Boolean> swing = sgGeneral.add(new BoolSetting.Builder()
        .name("swing")
        .description("Swings your hand when placing.")
        .defaultValue(true)
        .build()
    );

    public AntiAnchor() {
        super(Categories.Combat, "anti-anchor", "Automatically prevents Anchor Aura by placing a slab on your head.");
    }

    @EventHandler
    private void onTick(TickEvent.Pre event) {
        if (mc.level.getBlockState(mc.player.blockPosition().above(2)).getBlock() == Blocks.RESPAWN_ANCHOR
            && mc.level.getBlockState(mc.player.blockPosition().above()).getBlock() == Blocks.AIR) {

            BlockUtils.place(
                mc.player.blockPosition().offset(0, 1, 0),
                InvUtils.findInHotbar(itemStack -> Block.byItem(itemStack.getItem()) instanceof SlabBlock),
                rotate.get(),
                15,
                swing.get(),
                false,
                true
            );
        }
    }
}
