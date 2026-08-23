/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import net.minecraft.client.render.block.entity.BlockEntityRenderManager;
import net.minecraft.client.texture.SpriteHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BlockEntityRenderManager.class)
public interface BlockEntityRenderManagerAccessor {
    @Accessor("spriteHolder")
    SpriteHolder getSpriteHolder();
}
