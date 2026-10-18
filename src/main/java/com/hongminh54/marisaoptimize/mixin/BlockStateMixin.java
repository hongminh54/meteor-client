/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.hongminh54.marisaoptimize.MarisaOptimize;
import com.hongminh54.marisaoptimize.events.world.BlockActivateEvent;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockState.class)
public abstract class BlockStateMixin extends BlockBehaviour.BlockStateBase {
    public BlockStateMixin(Block block, Property<?>[] propertyKeys, Comparable<?>[] propertyValues) {
        super(block, propertyKeys, propertyValues);
    }

    @Override
    public InteractionResult useWithoutItem(Level world, Player player, BlockHitResult hit) {
        MarisaOptimize.EVENT_BUS.post(BlockActivateEvent.get((BlockState) (Object) this));
        return super.useWithoutItem(world, player, hit);
    }
}
