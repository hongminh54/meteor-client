/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.world.AutoBrewer;
import net.minecraft.client.gui.screens.inventory.BrewingStandScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.BrewingStandMenu;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BrewingStandScreen.class)
public abstract class BrewingStandScreenMixin extends AbstractContainerScreen<BrewingStandMenu> {
    public BrewingStandScreenMixin(BrewingStandMenu container, Inventory playerInventory, Component name) {
        super(container, playerInventory, name);
    }

    @Override
    public void containerTick() {
        super.containerTick();

        if (Modules.get().isActive(AutoBrewer.class)) Modules.get().get(AutoBrewer.class).tick(menu);
    }

    @Override
    public void onClose() {
        if (Modules.get().isActive(AutoBrewer.class)) Modules.get().get(AutoBrewer.class).onBrewingStandClose();

        super.onClose();
    }
}
