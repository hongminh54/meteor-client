/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.systems.modules.combat;

import com.hongminh54.marisaoptimize.events.render.Render3DEvent;
import com.hongminh54.marisaoptimize.events.world.TickEvent;
import com.hongminh54.marisaoptimize.renderer.ShapeMode;
import com.hongminh54.marisaoptimize.systems.modules.Categories;
import com.hongminh54.marisaoptimize.systems.modules.Module;
import com.hongminh54.marisaoptimize.utils.entity.SortPriority;
import com.hongminh54.marisaoptimize.utils.entity.TargetUtils;
import com.hongminh54.marisaoptimize.utils.player.FindItemResult;
import com.hongminh54.marisaoptimize.utils.player.InvUtils;
import com.hongminh54.marisaoptimize.utils.player.PlayerUtils;
import com.hongminh54.marisaoptimize.utils.render.color.SettingColor;
import com.hongminh54.marisaoptimize.utils.world.BlockUtils;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.ClipContext;

import java.util.ArrayList;
import java.util.List;
import com.hongminh54.marisaoptimize.settings.*;

public class AutoWeb extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();
    private final SettingGroup sgRender = settings.createGroup("Render");

    private final Setting<Double> placeRange = sgGeneral.add(new DoubleSetting.Builder()
        .name("place-range")
        .description("The range at which webs can be placed.")
        .defaultValue(4)
        .min(0)
        .sliderMax(6)
        .build()
    );

    private final Setting<Double> placeWallsRange = sgGeneral.add(new DoubleSetting.Builder()
        .name("walls-range")
        .description("Range in which to place webs when behind blocks.")
        .defaultValue(4)
        .min(0)
        .sliderMax(6)
        .build()
    );

    private final Setting<SortPriority> priority = sgGeneral.add(new EnumSetting.Builder<SortPriority>()
        .name("target-priority")
        .description("How to filter targets within range.")
        .defaultValue(SortPriority.LowestDistance)
        .build()
    );

    private final Setting<Double> targetRange = sgGeneral.add(new DoubleSetting.Builder()
        .name("target-range")
        .description("The maximum distance to target players.")
        .defaultValue(10)
        .min(0)
        .sliderMax(30)
        .build()
    );

    private final Setting<Boolean> predictMovement = sgGeneral.add(new BoolSetting.Builder()
        .name("predict-movement")
        .description("Predict target movement to account for ping.")
        .defaultValue(true)
        .build()
    );

    private final Setting<Double> ticksToPredict = sgGeneral.add(new DoubleSetting.Builder()
        .name("ticks-to-predict")
        .description("How many ticks ahead we should predict for.")
        .defaultValue(10)
        .min(1)
        .sliderMax(30)
        .visible(predictMovement::get)
        .build()
    );

    private final Setting<Boolean> doubles = sgGeneral.add(new BoolSetting.Builder()
        .name("doubles")
        .description("Places webs in the target's upper hitbox as well as the lower hitbox.")
        .defaultValue(false)
        .build()
    );

    private final Setting<Boolean> rotate = sgGeneral.add(new BoolSetting.Builder()
        .name("rotate")
        .description("Rotates towards the webs when placing.")
        .defaultValue(true)
        .build()
    );

    // Render

    private final Setting<Boolean> render = sgRender.add(new BoolSetting.Builder()
        .name("render")
        .description("Renders an overlay where webs are placed.")
        .defaultValue(true)
        .build()
    );

    private final Setting<ShapeMode> shapeMode = sgRender.add(new EnumSetting.Builder<ShapeMode>()
        .name("shape-mode")
        .description("How the shapes are rendered.")
        .defaultValue(ShapeMode.Both)
        .visible(render::get)
        .build()
    );

    private final Setting<SettingColor> sideColor = sgRender.add(new ColorSetting.Builder()
        .name("side-color")
        .description("The side color of the placed web rendering.")
        .defaultValue(new SettingColor(239, 231, 244, 31))
        .visible(() -> render.get() && shapeMode.get().sides())
        .build()
    );

    private final Setting<SettingColor> lineColor = sgRender.add(new ColorSetting.Builder()
        .name("line-color")
        .description("The line color of the placed web rendering.")
        .defaultValue(new SettingColor(255, 255, 255))
        .visible(() -> render.get() && shapeMode.get().lines())
        .build()
    );

    private final List<BlockPos> placePositions = new ArrayList<>();
    private Player target = null;

    public AutoWeb() {
        super(Categories.Combat, "auto-web", "Automatically places webs on other players.");
    }

    @Override
    public void onActivate() {
        target = null;
    }

    @Override
    public void onDeactivate() {
        placePositions.clear();
    }

    @EventHandler
    private void onTick(TickEvent.Pre event) {
        placePositions.clear();

        if (TargetUtils.isBadTarget(target, targetRange.get())) {
            target = TargetUtils.getPlayerTarget(targetRange.get(), priority.get());
            if (TargetUtils.isBadTarget(target, targetRange.get())) return;
        }

        // Grab webs from hotbar
        FindItemResult webs = InvUtils.findInHotbar(Items.COBWEB);
        if (!webs.found()) return;

        Vec3 pos = target.position();

        // Prediction mode via target's movement delta
        if (predictMovement.get()) {
            double dx = target.getX() - target.xOld;
            double dy = target.getY() - target.yOld;
            double dz = target.getZ() - target.zOld;
            pos = pos.add(dx * ticksToPredict.get(), dy * ticksToPredict.get(), dz * ticksToPredict.get());
        }

        BlockPos blockPos = BlockPos.containing(pos);

        if (canPlaceWebAt(blockPos)) {
            BlockUtils.place(blockPos, webs, rotate.get(), 0, false);
            placePositions.add(blockPos);
        }

        if (doubles.get() && canPlaceWebAt(blockPos.above())) {
            BlockUtils.place(blockPos.above(), webs, rotate.get(), 0, false);
            placePositions.add(blockPos.above());
        }
    }

    private boolean canPlaceWebAt(BlockPos blockPos) {
        if (!mc.level.getBlockState(blockPos).canBeReplaced()) return false;

        // Check raycast and range
        return !isOutOfRange(blockPos);
    }

    private boolean isOutOfRange(BlockPos blockPos) {
        Vec3 pos = Vec3.atCenterOf(blockPos);
        if (!PlayerUtils.isWithin(pos, placeRange.get())) return true;

        ClipContext raycastContext = new ClipContext(mc.player.getEyePosition(), pos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, mc.player);
        BlockHitResult result = mc.level.clip(raycastContext);
        if (result == null || !result.getBlockPos().equals(blockPos))
            return !PlayerUtils.isWithin(pos, placeWallsRange.get());

        return false;
    }

    @EventHandler
    private void onRender(Render3DEvent event) {
        if (!render.get() || placePositions.isEmpty()) return;

        for (BlockPos placePosition : placePositions) {
            event.renderer.box(placePosition, sideColor.get(), lineColor.get(), shapeMode.get(), 0);
        }
    }
}
