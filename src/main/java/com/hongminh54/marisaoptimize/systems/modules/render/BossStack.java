/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.systems.modules.render;

import com.hongminh54.marisaoptimize.events.render.RenderBossBarEvent;
import com.hongminh54.marisaoptimize.settings.BoolSetting;
import com.hongminh54.marisaoptimize.settings.DoubleSetting;
import com.hongminh54.marisaoptimize.settings.Setting;
import com.hongminh54.marisaoptimize.settings.SettingGroup;
import com.hongminh54.marisaoptimize.systems.modules.Categories;
import com.hongminh54.marisaoptimize.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.network.chat.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class BossStack extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    public final Setting<Boolean> stack = sgGeneral.add(new BoolSetting.Builder()
        .name("stack")
        .description("Stacks boss bars and adds a counter to the text.")
        .defaultValue(true)
        .build()
    );

    public final Setting<Boolean> hideName = sgGeneral.add(new BoolSetting.Builder()
        .name("hide-name")
        .description("Hides the names of boss bars.")
        .defaultValue(false)
        .build()
    );

    private final Setting<Double> spacing = sgGeneral.add(new DoubleSetting.Builder()
        .name("bar-spacing")
        .description("The spacing reduction between each boss bar.")
        .defaultValue(10)
        .min(0)
        .build()
    );

    public static final Map<LerpingBossEvent, Integer> barMap = new WeakHashMap<>();

    public BossStack() {
        super(Categories.Render, "boss-stack", "Stacks boss bars to make your HUD less cluttered.");
    }

    @EventHandler
    private void onFetchText(RenderBossBarEvent.BossText event) {
        if (hideName.get()) {
            event.name = Component.empty();
            return;
        } else if (barMap.isEmpty() || !stack.get()) return;
        LerpingBossEvent bar = event.bossBar;
        Integer integer = barMap.get(bar);
        barMap.remove(bar);
        if (integer != null && !hideName.get()) event.name = event.name.copy().append(" x" + integer);
    }

    @EventHandler
    private void onSpaceBars(RenderBossBarEvent.BossSpacing event) {
        event.spacing = spacing.get().intValue();
    }

    @EventHandler
    private void onGetBars(RenderBossBarEvent.BossIterator event) {
        if (stack.get()) {
            HashMap<String, LerpingBossEvent> chosenBarMap = new HashMap<>();
            event.iterator.forEachRemaining(bar -> {
                String name = bar.getName().getString();
                if (chosenBarMap.containsKey(name)) {
                    barMap.compute(chosenBarMap.get(name), (clientBossBar, integer) -> (integer == null) ? 2 : integer + 1);
                } else {
                    chosenBarMap.put(name, bar);
                }
            });
            event.iterator = chosenBarMap.values().iterator();
        }
    }
}
