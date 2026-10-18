/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.systems.modules.movement;

import com.hongminh54.marisaoptimize.events.packets.PacketEvent;
import com.hongminh54.marisaoptimize.events.world.TickEvent;
import com.hongminh54.marisaoptimize.mixin.PlayerMoveC2SPacketAccessor;
import com.hongminh54.marisaoptimize.mixininterface.IPlayerMoveC2SPacket;
import com.hongminh54.marisaoptimize.mixininterface.IVec3d;
import com.hongminh54.marisaoptimize.pathing.PathManagers;
import com.hongminh54.marisaoptimize.settings.BoolSetting;
import com.hongminh54.marisaoptimize.settings.EnumSetting;
import com.hongminh54.marisaoptimize.settings.Setting;
import com.hongminh54.marisaoptimize.settings.SettingGroup;
import com.hongminh54.marisaoptimize.systems.modules.Categories;
import com.hongminh54.marisaoptimize.systems.modules.Module;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.utils.Utils;
import com.hongminh54.marisaoptimize.utils.entity.EntityUtils;
import com.hongminh54.marisaoptimize.utils.player.FindItemResult;
import com.hongminh54.marisaoptimize.utils.player.InvUtils;
import com.hongminh54.marisaoptimize.utils.player.PlayerUtils;
import com.hongminh54.marisaoptimize.utils.player.Rotations;
import com.hongminh54.marisaoptimize.utils.world.BlockUtils;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MaceItem;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.attribute.EnvironmentAttributes;

import java.util.function.Predicate;

public class NoFall extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Mode> mode = sgGeneral.add(new EnumSetting.Builder<Mode>()
        .name("mode")
        .description("The way you are saved from fall damage.")
        .defaultValue(Mode.Packet)
        .build()
    );

    private final Setting<PlacedItem> placedItem = sgGeneral.add(new EnumSetting.Builder<PlacedItem>()
        .name("placed-item")
        .description("Which block to place.")
        .defaultValue(PlacedItem.Bucket)
        .visible(() -> mode.get() == Mode.Place)
        .build()
    );

    private final Setting<PlaceMode> airPlaceMode = sgGeneral.add(new EnumSetting.Builder<PlaceMode>()
        .name("air-place-mode")
        .description("Whether place mode places before you die or before you take damage.")
        .defaultValue(PlaceMode.BeforeDeath)
        .visible(() -> mode.get() == Mode.AirPlace)
        .build()
    );

    private final Setting<Boolean> anchor = sgGeneral.add(new BoolSetting.Builder()
        .name("anchor")
        .description("Centers the player and reduces movement when using bucket or air place mode.")
        .defaultValue(true)
        .visible(() -> mode.get() != Mode.Packet)
        .build()
    );

    private final Setting<Boolean> antiBounce = sgGeneral.add(new BoolSetting.Builder()
        .name("anti-bounce")
        .description("Disables bouncing on slime-block and bed upon landing.")
        .defaultValue(true)
        .build()
    );

    private final Setting<Boolean> pauseOnMace = sgGeneral.add(new BoolSetting.Builder()
        .name("pause-on-mace")
        .description("Pauses NoFall when using a mace.")
        .defaultValue(true)
        .build()
    );

    private boolean placedWater;
    private BlockPos targetPos;
    private int timer;
    private boolean prePathManagerNoFall;

    public NoFall() {
        super(Categories.Movement, "no-fall", "Attempts to prevent you from taking fall damage.");
    }

    @Override
    public void onActivate() {
        prePathManagerNoFall = PathManagers.get().getSettings().getNoFall().get();
        if (mode.get() == Mode.Packet) PathManagers.get().getSettings().getNoFall().set(true);

        placedWater = false;
    }

    @Override
    public void onDeactivate() {
        PathManagers.get().getSettings().getNoFall().set(prePathManagerNoFall);
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send event) {
        if (mc.player == null) return;
        if (pauseOnMace.get() && mc.player.getMainHandItem().getItem() instanceof MaceItem) return;
        if (mc.player.getAbilities().instabuild
            || !(event.packet instanceof ServerboundMovePlayerPacket)
            || mode.get() != Mode.Packet
            || ((IPlayerMoveC2SPacket) event.packet).meteor$getTag() == 1337) return;


        if (!Modules.get().isActive(Flight.class)) {
            if (mc.player.isFallFlying()) return;
            if (mc.player.getDeltaMovement().y > -0.5) return;
            ((PlayerMoveC2SPacketAccessor) event.packet).meteor$setOnGround(true);
        } else {
            ((PlayerMoveC2SPacketAccessor) event.packet).meteor$setOnGround(true);
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre event) {
        if (!Utils.canUpdate()) return;

        if (timer > 20) {
            placedWater = false;
            timer = 0;
        }

        if (mc.player.getAbilities().instabuild) return;
        if (pauseOnMace.get() && mc.player.getMainHandItem().getItem() instanceof MaceItem) return;

        // Airplace mode
        if (mode.get() == Mode.AirPlace) {
            // Test if fall damage setting is valid
            if (!airPlaceMode.get().test((float) mc.player.fallDistance)) return;

            // Center and place block
            if (anchor.get()) PlayerUtils.centerPlayer();

            Rotations.rotate(mc.player.getYRot(), 90, Integer.MAX_VALUE, () -> {
                double preY = mc.player.getDeltaMovement().y;
                ((IVec3d) mc.player.getDeltaMovement()).meteor$setY(0);

                BlockUtils.place(mc.player.blockPosition().below(), InvUtils.findInHotbar(itemStack -> itemStack.getItem() instanceof BlockItem), false, 0, true);

                ((IVec3d) mc.player.getDeltaMovement()).meteor$setY(preY);
            });
        }

        // Bucket mode
        else if (mode.get() == Mode.Place) {
            PlacedItem placedItem1 = mc.level.environmentAttributes().getDimensionValue(EnvironmentAttributes.WATER_EVAPORATES) && placedItem.get() == PlacedItem.Bucket ? PlacedItem.PowderSnow : placedItem.get();
            if (mc.player.fallDistance > 3 && !EntityUtils.isAboveWater(mc.player)) {
                Item item = placedItem1.item;

                // Place
                FindItemResult findItemResult = InvUtils.findInHotbar(item);
                if (!findItemResult.found()) return;

                // Center player
                if (anchor.get()) PlayerUtils.centerPlayer();

                // Check if there is a block within 5 blocks
                BlockHitResult result = mc.level.clip(new ClipContext(mc.player.position(), mc.player.position().subtract(0, 5, 0), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, mc.player));

                // Place
                if (result != null && result.getType() == HitResult.Type.BLOCK) {
                    targetPos = result.getBlockPos().above();
                    if (placedItem1 == PlacedItem.Bucket)
                        useItem(findItemResult, true, targetPos, true);
                    else {
                        useItem(findItemResult, placedItem1 == PlacedItem.PowderSnow, targetPos, false);
                    }
                }
            }

            // Remove placed
            if (placedWater) {
                timer++;
                if (mc.player.getInBlockState().getBlock() == placedItem1.block) {
                    useItem(InvUtils.findInHotbar(Items.BUCKET), false, targetPos, true);
                } else if (mc.level.getBlockState(mc.player.blockPosition().below()).getBlock() == Blocks.POWDER_SNOW && mc.player.fallDistance==0 && placedItem1.block==Blocks.POWDER_SNOW){ //check if the powder snow block is still there and the player is on the ground
                    useItem(InvUtils.findInHotbar(Items.BUCKET), false, targetPos.below(), true);
                }
            }
        }
    }

    public boolean cancelBounce() {
        return isActive() && antiBounce.get();
    }

    private void useItem(FindItemResult item, boolean placedWater, BlockPos blockPos, boolean interactItem) {
        if (!item.found()) return;

        if (interactItem) {
            Rotations.rotate(Rotations.getYaw(blockPos), Rotations.getPitch(blockPos), 10, true, () -> {
                if (item.isOffhand()) {
                    mc.gameMode.useItem(mc.player, InteractionHand.OFF_HAND);
                } else {
                    InvUtils.swap(item.slot(), true);
                    mc.gameMode.useItem(mc.player, InteractionHand.MAIN_HAND);
                    InvUtils.swapBack();
                }
            });
        } else {
            BlockUtils.place(blockPos, item, true, 10, true);
        }

        this.placedWater = placedWater;
    }

    @Override
    public String getInfoString() {
        return mode.get().toString();
    }

    public enum Mode {
        Packet,
        AirPlace,
        Place
    }

    public enum PlacedItem {
        Bucket(Items.WATER_BUCKET, Blocks.WATER),
        PowderSnow(Items.POWDER_SNOW_BUCKET, Blocks.POWDER_SNOW),
        HayBale(Items.HAY_BLOCK, Blocks.HAY_BLOCK),
        Cobweb(Items.COBWEB, Blocks.COBWEB),
        SlimeBlock(Items.SLIME_BLOCK, Blocks.SLIME_BLOCK);

        private final Item item;
        private final Block block;

        PlacedItem(Item item, Block block) {
            this.item = item;
            this.block = block;
        }
    }

    public enum PlaceMode {
        BeforeDamage(height -> height > 2),
        BeforeDeath(height -> height > Math.max(PlayerUtils.getTotalHealth(), 2));

        private final Predicate<Float> fallHeight;

        PlaceMode(Predicate<Float> fallHeight) {
            this.fallHeight = fallHeight;
        }

        public boolean test(float fallheight) {
            return fallHeight.test(fallheight);
        }
    }
}
