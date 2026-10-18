/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.systems.modules.movement.speed.modes;

import com.hongminh54.marisaoptimize.events.entity.player.PlayerMoveEvent;
import com.hongminh54.marisaoptimize.mixininterface.IVec3d;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.movement.Anchor;
import com.hongminh54.marisaoptimize.systems.modules.movement.speed.SpeedMode;
import com.hongminh54.marisaoptimize.systems.modules.movement.speed.SpeedModes;
import com.hongminh54.marisaoptimize.utils.player.PlayerUtils;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.Vec3;

public class Vanilla extends SpeedMode {
    public Vanilla() {
        super(SpeedModes.Vanilla);
    }

    @Override
    public void onMove(PlayerMoveEvent event) {
        Vec3 vel = PlayerUtils.getHorizontalVelocity(settings.vanillaSpeed.get());
        double velX = vel.x;
        double velZ = vel.z;

        if (mc.player.hasEffect(MobEffects.SPEED)) {
            double value = (mc.player.getEffect(MobEffects.SPEED).getAmplifier() + 1) * 0.205;
            velX += velX * value;
            velZ += velZ * value;
        }

        Anchor anchor = Modules.get().get(Anchor.class);
        if (anchor.isActive() && anchor.controlMovement) {
            velX = anchor.deltaX;
            velZ = anchor.deltaZ;
        }

        ((IVec3d) event.movement).meteor$set(velX, event.movement.y, velZ);
    }
}
