/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.systems.modules.movement;

import com.hongminh54.marisaoptimize.events.meteor.KeyEvent;
import com.hongminh54.marisaoptimize.events.world.TickEvent;
import com.hongminh54.marisaoptimize.settings.BoolSetting;
import com.hongminh54.marisaoptimize.settings.Setting;
import com.hongminh54.marisaoptimize.settings.SettingGroup;
import com.hongminh54.marisaoptimize.systems.modules.Categories;
import com.hongminh54.marisaoptimize.systems.modules.Module;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.Freecam;
import com.hongminh54.marisaoptimize.utils.misc.input.KeyAction;
import meteordevelopment.orbit.EventHandler;

public class AirJump extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Boolean> maintainLevel = sgGeneral.add(new BoolSetting.Builder()
        .name("maintain-level")
        .description("Maintains your current Y level when holding the jump key.")
        .defaultValue(false)
        .build()
    );

    private int level;

    public AirJump() {
        super(Categories.Movement, "air-jump", "Lets you jump in the air.");
    }

    @Override
    public void onActivate() {
        level = mc.player.blockPosition().getY();
    }

    @EventHandler
    private void onKey(KeyEvent event) {
        if (Modules.get().isActive(Freecam.class) || mc.gui.screen() != null || mc.player.onGround()) return;

        if (event.action != KeyAction.Press) return;

        if (mc.options.keyJump.matches(event.input)) {
            level = mc.player.blockPosition().getY();
            mc.player.jumpFromGround();
        }
        else if (mc.options.keyShift.matches(event.input)) {
            level--;
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre event) {
        if (Modules.get().isActive(Freecam.class) || mc.player.onGround()) return;

        if (maintainLevel.get() && mc.player.blockPosition().getY() == level && mc.options.keyJump.isDown()) {
            mc.player.jumpFromGround();
        }
    }
}
