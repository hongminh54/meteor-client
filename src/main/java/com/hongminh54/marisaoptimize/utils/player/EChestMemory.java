/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.utils.player;

import com.hongminh54.marisaoptimize.MarisaOptimize;
import com.hongminh54.marisaoptimize.events.game.GameLeftEvent;
import com.hongminh54.marisaoptimize.events.game.OpenScreenEvent;
import com.hongminh54.marisaoptimize.events.world.BlockActivateEvent;
import com.hongminh54.marisaoptimize.utils.PreInit;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.world.level.block.EnderChestBlock;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.core.NonNullList;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

public class EChestMemory {
    public static final NonNullList<ItemStack> ITEMS = NonNullList.withSize(27, ItemStack.EMPTY);
    private static int echestOpenedState;
    private static boolean isKnown = false;

    private EChestMemory() {
    }

    @PreInit
    public static void init() {
        MarisaOptimize.EVENT_BUS.subscribe(EChestMemory.class);
    }

    @EventHandler
    private static void onBlockActivate(BlockActivateEvent event) {
        if (event.blockState.getBlock() instanceof EnderChestBlock && echestOpenedState == 0) echestOpenedState = 1;
    }

    @EventHandler
    private static void onOpenScreenEvent(OpenScreenEvent event) {
        if (echestOpenedState == 1 && event.screen instanceof ContainerScreen) {
            echestOpenedState = 2;
            return;
        }
        if (echestOpenedState == 0) return;

        if (!(mc.gui.screen() instanceof ContainerScreen screen)) return;
        ChestMenu container = screen.getMenu();
        if (container == null) return;
        Container inv = container.getContainer();

        for (int i = 0; i < 27; i++) {
            ITEMS.set(i, inv.getItem(i));
        }
        isKnown = true;

        echestOpenedState = 0;
    }

    @EventHandler
    private static void onLeaveEvent(GameLeftEvent event) {
        ITEMS.clear();
        isKnown = false;
    }

    public static boolean isKnown() {
        return isKnown;
    }
}
