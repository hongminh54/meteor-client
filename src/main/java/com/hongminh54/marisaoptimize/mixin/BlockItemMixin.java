/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.hongminh54.marisaoptimize.MarisaOptimize;
import com.hongminh54.marisaoptimize.events.entity.player.PlaceBlockEvent;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.world.NoGhostBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public abstract class BlockItemMixin {
    @Shadow
    protected abstract BlockState getPlacementState(BlockPlaceContext context);

    @Inject(method = "placeBlock(Lnet/minecraft/world/item/context/BlockPlaceContext;Lnet/minecraft/world/level/block/state/BlockState;)Z", at = @At("HEAD"), cancellable = true)
    private void onPlace(BlockPlaceContext context, BlockState state, CallbackInfoReturnable<Boolean> info) {
        if (!context.getLevel().isClientSide()) return;

        if (MarisaOptimize.EVENT_BUS.post(PlaceBlockEvent.get(context.getClickedPos(), state.getBlock())).isCancelled()) {
            info.setReturnValue(true);
        }
    }

    @ModifyVariable(
        method = "place(Lnet/minecraft/world/item/context/BlockPlaceContext;)Lnet/minecraft/world/InteractionResult;",
        name = "placedState",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/state/BlockState;is(Ljava/lang/Object;)Z"
        )
    )
    private BlockState modifyState(BlockState state, BlockPlaceContext context) {
        var noGhostBlocks = Modules.get().get(NoGhostBlocks.class);

        if (noGhostBlocks.isActive() && noGhostBlocks.placing.get()) {
            return getPlacementState(context);
        }

        return state;
    }
}
