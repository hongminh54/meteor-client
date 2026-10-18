/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.commands.commands;

import com.hongminh54.marisaoptimize.MarisaOptimize;
import com.hongminh54.marisaoptimize.commands.Command;
import com.hongminh54.marisaoptimize.commands.arguments.PlayerArgumentType;
import com.hongminh54.marisaoptimize.events.meteor.KeyEvent;
import com.hongminh54.marisaoptimize.events.meteor.MouseClickEvent;
import com.hongminh54.marisaoptimize.utils.misc.input.Input;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import net.minecraft.network.chat.Component;

public class SpectateCommand extends Command {

    private final StaticListener shiftListener = new StaticListener();

    public SpectateCommand() {
        super("spectate", "Allows you to spectate nearby players");
    }

    @Override
    public void build(LiteralArgumentBuilder<ClientSuggestionProvider> builder) {
        builder.then(literal("reset").executes(context -> {
            mc.setCameraEntity(mc.player);
            return SINGLE_SUCCESS;
        }));

        builder.then(argument("player", PlayerArgumentType.create()).executes(context -> {
            mc.setCameraEntity(PlayerArgumentType.get(context));
            mc.gui.hud.setOverlayMessage(Component.literal("Sneak to un-spectate."), false);
            MarisaOptimize.EVENT_BUS.subscribe(shiftListener);
            return SINGLE_SUCCESS;
        }));
    }

    private static class StaticListener {
        @EventHandler
        private void onKey(KeyEvent event) {
            if (Input.isPressed(mc.options.keyShift)) {
                mc.setCameraEntity(mc.player);
                event.cancel();
                MarisaOptimize.EVENT_BUS.unsubscribe(this);
            }
        }

        @EventHandler
        private void onMouse(MouseClickEvent event) {
            if (Input.isPressed(mc.options.keyShift)) {
                mc.setCameraEntity(mc.player);
                event.cancel();
                MarisaOptimize.EVENT_BUS.unsubscribe(this);
            }
        }
    }
}
