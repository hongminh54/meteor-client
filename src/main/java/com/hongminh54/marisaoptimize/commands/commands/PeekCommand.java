/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.commands.commands;

import com.hongminh54.marisaoptimize.commands.Command;
import com.hongminh54.marisaoptimize.utils.Utils;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

public class PeekCommand extends Command {
    private static final ItemStack[] ITEMS = new ItemStack[27];
    private static final SimpleCommandExceptionType CANT_PEEK = new SimpleCommandExceptionType(Component.literal("You must be holding a storage block or looking at an item frame."));

    public PeekCommand() {
        super("peek", "Lets you see what's inside storage block items.");
    }

    @Override
    public void build(LiteralArgumentBuilder<ClientSuggestionProvider> builder) {
        builder.executes(context -> {
            if (Utils.openContainer(mc.player.getMainHandItem(), ITEMS, true)) return SINGLE_SUCCESS;
            else if (Utils.openContainer(mc.player.getOffhandItem(), ITEMS, true)) return SINGLE_SUCCESS;
            else if (mc.crosshairPickEntity instanceof ItemFrame &&
                Utils.openContainer(((ItemFrame) mc.crosshairPickEntity).getItem(), ITEMS, true)
            ) return SINGLE_SUCCESS;
            else throw CANT_PEEK.create();
        });
    }
}
