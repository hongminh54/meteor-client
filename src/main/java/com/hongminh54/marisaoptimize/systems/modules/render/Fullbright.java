/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.systems.modules.render;

import com.hongminh54.marisaoptimize.events.world.TickEvent;
import com.hongminh54.marisaoptimize.mixin.StatusEffectInstanceAccessor;
import com.hongminh54.marisaoptimize.settings.EnumSetting;
import com.hongminh54.marisaoptimize.settings.IntSetting;
import com.hongminh54.marisaoptimize.settings.Setting;
import com.hongminh54.marisaoptimize.settings.SettingGroup;
import com.hongminh54.marisaoptimize.systems.modules.Categories;
import com.hongminh54.marisaoptimize.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.LightLayer;

public class Fullbright extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    public final Setting<Mode> mode = sgGeneral.add(new EnumSetting.Builder<Mode>()
        .name("mode")
        .description("The mode to use for Fullbright.")
        .defaultValue(Mode.Gamma)
        .onChanged(mode -> {
            if (isActive()) {
                if (mode != Mode.Potion) disableNightVision();
                if (mc.levelExtractor != null) mc.levelExtractor.allChanged();
            }
        })
        .build()
    );

    public final Setting<LightLayer> lightType = sgGeneral.add(new EnumSetting.Builder<LightLayer>()
        .name("light-type")
        .description("Which type of light to use for Luminance mode.")
        .defaultValue(LightLayer.BLOCK)
        .visible(() -> mode.get() == Mode.Luminance)
        .onChanged(integer -> {
            if (mc.levelExtractor != null && isActive()) mc.levelExtractor.allChanged();
        })
        .build()
    );

    private final Setting<Integer> minimumLightLevel = sgGeneral.add(new IntSetting.Builder()
        .name("minimum-light-level")
        .description("Minimum light level when using Luminance mode.")
        .visible(() -> mode.get() == Mode.Luminance)
        .defaultValue(8)
        .range(0, 15)
        .sliderMax(15)
        .onChanged(integer -> {
            if (mc.levelExtractor != null && isActive()) mc.levelExtractor.allChanged();
        })
        .build()
    );

    public Fullbright() {
        super(Categories.Render, "fullbright", "Lights up your world!");
    }

    @Override
    public void onActivate() {
        if (mode.get() == Mode.Luminance) mc.levelExtractor.allChanged();
    }

    @Override
    public void onDeactivate() {
        if (mode.get() == Mode.Luminance) mc.levelExtractor.allChanged();
        else if (mode.get() == Mode.Potion) disableNightVision();
    }

    public int getLuminance(LightLayer type) {
        if (!isActive() || mode.get() != Mode.Luminance || type != lightType.get()) return 0;
        return minimumLightLevel.get();
    }

    public boolean getGamma() {
        return isActive() && mode.get() == Mode.Gamma;
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        if (mc.player == null || !mode.get().equals(Mode.Potion)) return;
        if (mc.player.hasEffect(MobEffects.NIGHT_VISION)) {
            MobEffectInstance instance = mc.player.getEffect(MobEffects.NIGHT_VISION);
            if (instance != null && instance.getDuration() < 420) ((StatusEffectInstanceAccessor) instance).meteor$setDuration(420);
        } else {
            mc.player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 420, 0));
        }
    }

    private void disableNightVision() {
        if (mc.player == null) return;
        if (mc.player.hasEffect(MobEffects.NIGHT_VISION)) {
            mc.player.removeEffect(MobEffects.NIGHT_VISION);
        }
    }

    public enum Mode {
        Gamma,
        Potion,
        Luminance
    }
}
