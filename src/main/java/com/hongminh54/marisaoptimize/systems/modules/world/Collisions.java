/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.systems.modules.world;

import com.hongminh54.marisaoptimize.events.entity.player.PlayerMoveEvent;
import com.hongminh54.marisaoptimize.events.packets.PacketEvent;
import com.hongminh54.marisaoptimize.events.world.CollisionShapeEvent;
import com.hongminh54.marisaoptimize.mixininterface.IVec3d;
import com.hongminh54.marisaoptimize.settings.BlockListSetting;
import com.hongminh54.marisaoptimize.settings.BoolSetting;
import com.hongminh54.marisaoptimize.settings.Setting;
import com.hongminh54.marisaoptimize.settings.SettingGroup;
import com.hongminh54.marisaoptimize.systems.modules.Categories;
import com.hongminh54.marisaoptimize.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.world.level.block.*;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.game.ServerboundMoveVehiclePacket;
import net.minecraft.world.phys.shapes.Shapes;

import java.util.List;

public class Collisions extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    public final Setting<List<Block>> blocks = sgGeneral.add(new BlockListSetting.Builder()
        .name("blocks")
        .description("What blocks should be added collision box.")
        .filter(this::blockFilter)
        .build()
    );

    private final Setting<Boolean> magma = sgGeneral.add(new BoolSetting.Builder()
        .name("magma")
        .description("Prevents you from walking over magma blocks.")
        .defaultValue(false)
        .build()
    );

    private final Setting<Boolean> unloadedChunks = sgGeneral.add(new BoolSetting.Builder()
        .name("unloaded-chunks")
        .description("Stops you from going into unloaded chunks.")
        .defaultValue(false)
        .build()
    );

    private final Setting<Boolean> ignoreBorder = sgGeneral.add(new BoolSetting.Builder()
        .name("ignore-border")
        .description("Removes world border collision.")
        .defaultValue(false)
        .build()
    );

    public Collisions() {
        super(Categories.World, "collisions", "Adds collision boxes to certain blocks/areas.");
    }

    @EventHandler
    private void onCollisionShape(CollisionShapeEvent event) {
        if (mc.level == null || mc.player == null) return;
        if (!event.state.getFluidState().isEmpty()) return;
        if (blocks.get().contains(event.state.getBlock())) {
            event.shape = Shapes.block();
        } else if (magma.get() && !mc.player.isShiftKeyDown()
            && event.state.isAir()
            && mc.level.getBlockState(event.pos.below()).getBlock() == Blocks.MAGMA_BLOCK) {
            event.shape = Shapes.block();
        }
    }

    @EventHandler
    private void onPlayerMove(PlayerMoveEvent event) {
        int x = (int) (mc.player.getX() + event.movement.x) >> 4;
        int z = (int) (mc.player.getZ() + event.movement.z) >> 4;
        if (unloadedChunks.get() && !mc.level.getChunkSource().hasChunk(x, z)) {
            ((IVec3d) event.movement).meteor$set(0, event.movement.y, 0);
        }
    }

    @EventHandler
    private void onPacketSend(PacketEvent.Send event) {
        if (!unloadedChunks.get()) return;
        if (event.packet instanceof ServerboundMoveVehiclePacket packet) {
            if (!mc.level.getChunkSource().hasChunk((int) packet.position().x >> 4, (int) packet.position().z >> 4)) {
                mc.player.getVehicle().setPos(mc.player.getVehicle().xOld, mc.player.getVehicle().yOld, mc.player.getVehicle().zOld);
                event.cancel();
            }
        } else if (event.packet instanceof ServerboundMovePlayerPacket packet) {
            if (!mc.level.getChunkSource().hasChunk((int) packet.getX(mc.player.getX()) >> 4, (int) packet.getZ(mc.player.getZ()) >> 4)) {
                event.cancel();
            }
        }
    }

    private boolean blockFilter(Block block) {
        return (block instanceof BaseFireBlock
            || block instanceof BasePressurePlateBlock
            || block instanceof TripWireBlock
            || block instanceof TripWireHookBlock
            || block instanceof WebBlock
            || block instanceof CampfireBlock
            || block instanceof SweetBerryBushBlock
            || block instanceof CactusBlock
            || block instanceof BaseRailBlock
            || block instanceof TrapDoorBlock
            || block instanceof PowderSnowBlock
            || block instanceof AbstractCauldronBlock
            || block instanceof HoneyBlock
        );
    }

    public boolean ignoreBorder() {
        return isActive() && ignoreBorder.get();
    }
}
