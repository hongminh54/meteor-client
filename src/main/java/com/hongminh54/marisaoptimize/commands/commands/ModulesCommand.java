/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.commands.commands;

import com.hongminh54.marisaoptimize.commands.Command;
import com.hongminh54.marisaoptimize.systems.modules.Module;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.utils.player.ChatUtils;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;

public class ModulesCommand extends Command {
    public ModulesCommand() {
        super("modules", "Displays a list of all modules.", "features");
    }

    @Override
    public void build(LiteralArgumentBuilder<ClientSuggestionProvider> builder) {
        builder.executes(context -> {
            ChatUtils.info("--- Modules ((highlight)%d(default)) ---", Modules.get().getCount());

            Modules.loopCategories().forEach(category -> {
                MutableComponent categoryMessage = Component.literal("");
                Modules.get().getGroup(category).forEach(module -> categoryMessage.append(getModuleText(module)));
                ChatUtils.sendMsg(category.name, categoryMessage);
            });

            return SINGLE_SUCCESS;
        });
    }

    private MutableComponent getModuleText(Module module) {
        // Hover tooltip
        MutableComponent tooltip = Component.literal("");

        tooltip.append(Component.literal(module.title).withStyle(ChatFormatting.BLUE, ChatFormatting.BOLD)).append("\n");
        tooltip.append(Component.literal(module.name).withStyle(ChatFormatting.GRAY)).append("\n\n");
        tooltip.append(Component.literal(module.description).withStyle(ChatFormatting.WHITE));

        MutableComponent finalModule = Component.literal(module.title);
        if (!module.isActive()) finalModule.withStyle(ChatFormatting.GRAY);
        if (!module.equals(Modules.get().getGroup(module.category).getLast())) finalModule.append(Component.literal(", ").withStyle(ChatFormatting.GRAY));
        finalModule.setStyle(finalModule.getStyle().withHoverEvent(new HoverEvent.ShowText(tooltip)));

        return finalModule;
    }

}
