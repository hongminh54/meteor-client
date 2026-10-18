/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.NoRender;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.feature.ItemFeatureRenderer;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemFeatureRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyExpressionValue(method = "prepareFoilSubmit", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/feature/ItemFeatureRenderer$Submit;foilType()Lnet/minecraft/client/renderer/item/ItemStackRenderState$FoilType;"))
    private ItemStackRenderState.FoilType modifyEnchant(ItemStackRenderState.FoilType glint) {
        if (Modules.get().get(NoRender.class).noEnchantGlint()) return ItemStackRenderState.FoilType.NONE;
        return glint;
    }
}
