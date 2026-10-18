/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.BetterTooltips;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;
import java.util.List;
import java.util.Optional;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemContainerContents.class)
public abstract class ContainerComponentMixin {
    @Shadow
    @Final
    private List<Optional<ItemStackTemplate>> items;

    @Inject(method = "addToTooltip", at = @At("HEAD"), cancellable = true)
    private void onAppendTooltip(Item.TooltipContext context, Consumer<Component> textConsumer, TooltipFlag type, DataComponentGetter components, CallbackInfo ci) {
        if (Modules.get() == null) return;

        BetterTooltips tooltips = Modules.get().get(BetterTooltips.class);
        if (tooltips.isActive()) {
            if (tooltips.previewShulkers()) ci.cancel();
            else if (tooltips.shulkerCompactTooltip()) {
                ci.cancel();
                tooltips.applyCompactShulkerTooltip(items.stream().map(item -> item.map(ItemStackTemplate::create).orElse(ItemStack.EMPTY)).toList(), textConsumer);
            }
        }
    }
}
