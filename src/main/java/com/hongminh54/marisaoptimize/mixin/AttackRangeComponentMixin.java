/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.combat.Hitboxes;
import net.minecraft.world.item.component.AttackRange;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.ToDoubleFunction;

@Mixin(AttackRange.class)
public class AttackRangeComponentMixin {
    @ModifyExpressionValue(method = "isInRange(Lnet/minecraft/world/entity/LivingEntity;Ljava/util/function/ToDoubleFunction;D)Z", at = @At(value = "FIELD", target = "Lnet/minecraft/world/item/component/AttackRange;hitboxMargin:F", opcode = Opcodes.GETFIELD))
    private float modifyHitboxMargin(float original, LivingEntity entity, ToDoubleFunction<Vec3> squaredDistanceFunction, double extraHitboxMargin) {
        float v = (float) Modules.get().get(Hitboxes.class).getEntityValue(entity);
        return original + v;
    }
}
