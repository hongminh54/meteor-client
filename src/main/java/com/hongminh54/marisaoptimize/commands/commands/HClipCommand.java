/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.commands.commands;

import com.hongminh54.marisaoptimize.commands.Command;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class HClipCommand extends Command {
    public HClipCommand() {
        super("hclip", "Lets you clip through blocks horizontally.");
    }

    @Override
    public void build(LiteralArgumentBuilder<ClientSuggestionProvider> builder) {
        builder.then(argument("blocks", DoubleArgumentType.doubleArg()).executes(context -> {
            double blocks = context.getArgument("blocks", Double.class);
            Vec3 forward = Vec3.directionFromRotation(0, mc.player.getYRot()).normalize();

            if (mc.player.isPassenger()) {
                Entity vehicle = mc.player.getVehicle();
                vehicle.setPos(vehicle.getX() + forward.x * blocks, vehicle.getY(), vehicle.getZ() + forward.z * blocks);
            }

            mc.player.setPos(mc.player.getX() + forward.x * blocks, mc.player.getY(), mc.player.getZ() + forward.z * blocks);

            return SINGLE_SUCCESS;
        }));
    }
}
