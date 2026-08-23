/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.ESP;
import net.minecraft.client.render.entity.ArmorStandEntityRenderer;
import net.minecraft.entity.EntityType;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ArmorStandEntityRenderer.class)
public class ArmorStandEntityRendererMixin {
    @Unique
    private static ESP esp;

    @ModifyExpressionValue(method = "getRenderLayer(Lnet/minecraft/client/render/entity/state/ArmorStandEntityRenderState;ZZZ)Lnet/minecraft/client/render/RenderLayer;", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/entity/state/ArmorStandEntityRenderState;marker:Z", opcode = Opcodes.GETFIELD))
    private boolean modifyMarkerValue(boolean original) {
        if (esp == null) esp = Modules.get().get(ESP.class);

        return original && !(esp.isActive() && !esp.shouldSkip(EntityType.ARMOR_STAND));
    }
}
