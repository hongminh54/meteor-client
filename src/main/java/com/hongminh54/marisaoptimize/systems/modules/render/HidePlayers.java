/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.systems.modules.render;

import com.hongminh54.marisaoptimize.systems.modules.Categories;
import com.hongminh54.marisaoptimize.systems.modules.Module;
import com.hongminh54.marisaoptimize.utils.entity.fakeplayer.FakePlayerEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

public class HidePlayers extends Module {
    public HidePlayers() {
        super(Categories.Render, "hide-players", "Hides other players from rendering. (Client-side only)");
    }

    public boolean shouldHide(Entity entity) {
        if (!isActive()) return false;
        if (!(entity instanceof Player player)) return false;
        if (player == mc.player) return false;
        if (entity instanceof FakePlayerEntity) return false;

        return true;
    }
}
