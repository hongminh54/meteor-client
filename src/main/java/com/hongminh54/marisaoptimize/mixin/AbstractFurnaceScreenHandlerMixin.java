/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.hongminh54.marisaoptimize.mixininterface.IAbstractFurnaceScreenHandler;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AbstractFurnaceMenu.class)
public abstract class AbstractFurnaceScreenHandlerMixin implements IAbstractFurnaceScreenHandler {
    @Shadow
    protected abstract boolean canSmelt(ItemStack itemStack);

    @Override
    public boolean meteor$isItemSmeltable(ItemStack itemStack) {
        return canSmelt(itemStack);
    }
}
