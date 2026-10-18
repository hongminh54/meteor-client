/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.Fullbright;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.world.level.LightLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LightCoordsUtil.BrightnessGetter.class)
public interface BrightnessGetterMixin {

    @ModifyVariable(method = "lambda$static$0", at = @At(value = "STORE"), name = "sky")
    private static int getLightmapCoordinatesModifySkyLight(int sky) {
        return Math.max(Modules.get().get(Fullbright.class).getLuminance(LightLayer.SKY), sky);
    }

    @ModifyVariable(method = "lambda$static$0", at = @At(value = "STORE"), name = "block")
    private static int getLightmapCoordinatesModifyBlockLight(int sky) {
        return Math.max(Modules.get().get(Fullbright.class).getLuminance(LightLayer.BLOCK), sky);
    }
}
