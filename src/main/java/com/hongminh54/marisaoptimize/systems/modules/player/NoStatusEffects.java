/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.systems.modules.player;

import com.hongminh54.marisaoptimize.systems.modules.Categories;
import com.hongminh54.marisaoptimize.systems.modules.Module;
import net.minecraft.world.effect.MobEffect;

import java.util.List;

import static net.minecraft.world.effect.MobEffects.*;
import com.hongminh54.marisaoptimize.settings.*;

public class NoStatusEffects extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<List<MobEffect>> blockedEffects = sgGeneral.add(new StatusEffectListSetting.Builder()
        .name("blocked-effects")
        .description("Effects to block.")
        .defaultValue(
            LEVITATION.value(),
            JUMP_BOOST.value(),
            SLOW_FALLING.value(),
            DOLPHINS_GRACE.value()
        )
        .build()
    );

    public NoStatusEffects() {
        super(Categories.Player, "no-status-effects", "Blocks specified status effects.");
    }

    public boolean shouldBlock(MobEffect effect) {
        return isActive() && blockedEffects.get().contains(effect);
    }
}
