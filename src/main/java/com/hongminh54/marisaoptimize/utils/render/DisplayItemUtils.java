/*
 * This file is part of the Marisa Optimize distribution.
 * Copyright (c) Meteor Development.
 */
package com.hongminh54.marisaoptimize.utils.render;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

/** Display-only stacks, safe before a world/server binds item components. Never use for gameplay. */
public final class DisplayItemUtils {
    private DisplayItemUtils() {}

    public static ItemStack toStack(Block block) {
        return toStack(block.asItem());
    }

    public static ItemStack toStack(Item item) {
        return toStack(item, 1);
    }

    @SuppressWarnings("deprecation")
    public static ItemStack toStack(Item item, int count) {
        if (item == Items.AIR) return ItemStack.EMPTY;
        DataComponentMap components = DataComponentMap.builder()
            .addAll(DataComponents.COMMON_ITEM_COMPONENTS)
            .set(DataComponents.ITEM_MODEL, item.builtInRegistryHolder().key().identifier())
            .set(DataComponents.ITEM_NAME, Component.translatable(item.getDescriptionId()))
            .build();
        return new ItemStack(Holder.direct(item, components), count);
    }
}
