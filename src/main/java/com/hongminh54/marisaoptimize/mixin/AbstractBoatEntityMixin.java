/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.movement.EntityControl;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractBoat.class)
public abstract class AbstractBoatEntityMixin {
    @ModifyExpressionValue(method = "controlBoat", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/vehicle/boat/AbstractBoat;inputLeft:Z", opcode = Opcodes.GETFIELD))
    private boolean modifyPressingLeft(boolean original) {
        if (Modules.get().isActive(EntityControl.class) && Modules.get().get(EntityControl.class).lockYaw.get()) return false;
        return original;
    }

    @ModifyExpressionValue(method = "controlBoat", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/vehicle/boat/AbstractBoat;inputRight:Z", opcode = Opcodes.GETFIELD))
    private boolean modifyPressingRight(boolean original) {
        if (Modules.get().isActive(EntityControl.class) && Modules.get().get(EntityControl.class).lockYaw.get()) return false;
        return original;
    }
}
