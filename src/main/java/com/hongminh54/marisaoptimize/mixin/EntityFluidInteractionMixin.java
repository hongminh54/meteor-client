/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.movement.Velocity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityFluidInteraction;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

@Mixin(EntityFluidInteraction.class)
public abstract class EntityFluidInteractionMixin {
    @ModifyExpressionValue(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;getFlow(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/phys/Vec3;"))
    private Vec3 modifyFluidFlow(Vec3 flow, Entity entity, boolean ignoreCurrent) {
        if (entity != mc.player) return flow;

        Velocity velocity = Modules.get().get(Velocity.class);
        if (velocity.isActive() && velocity.liquids.get()) {
            double horizontal = velocity.getHorizontal(velocity.liquidsHorizontal);
            flow = flow.multiply(horizontal, velocity.getVertical(velocity.liquidsVertical), horizontal);
        }
        return flow;
    }
}
