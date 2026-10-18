/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.misc.InventoryTweaks;
import net.minecraft.client.gui.BundleMouseActions;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BundleMouseActions.class)
public class BundleTooltipSubmenuHandlerMixin {
    @ModifyExpressionValue(method = "toggleSelectedBundleItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BundleItem;getNumberOfItemsToShow(Lnet/minecraft/world/item/ItemStack;)I"))
    private int uncapBundleScrolling1(int original, ItemStack item, int slotId, int selectedItemIndex) {
        if (Modules.get().get(InventoryTweaks.class).uncapBundleScrolling()) return item.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).size();
        return original;
    }

    @ModifyExpressionValue(method = "onMouseScrolled", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BundleItem;getNumberOfItemsToShow(Lnet/minecraft/world/item/ItemStack;)I"))
    private int uncapBundleScrolling2(int original, double horizontal, double vertical, int slotId, ItemStack item) {
        if (Modules.get().get(InventoryTweaks.class).uncapBundleScrolling()) return item.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).size();
        return original;
    }
}
