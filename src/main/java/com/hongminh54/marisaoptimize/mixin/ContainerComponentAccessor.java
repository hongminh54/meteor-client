/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import java.util.List;
import java.util.Optional;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemContainerContents.class)
public interface ContainerComponentAccessor {
    @Accessor("items")
    List<Optional<ItemStackTemplate>> meteor$getItems();

    default List<ItemStack> meteor$getStacks() {
        return meteor$getItems().stream().map(item -> item.map(ItemStackTemplate::create).orElse(ItemStack.EMPTY)).toList();
    }
}
