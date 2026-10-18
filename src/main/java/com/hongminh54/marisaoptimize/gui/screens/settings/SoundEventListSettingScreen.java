/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.gui.screens.settings;

import com.hongminh54.marisaoptimize.gui.GuiTheme;
import com.hongminh54.marisaoptimize.gui.screens.settings.base.CollectionListSettingScreen;
import com.hongminh54.marisaoptimize.gui.widgets.WWidget;
import com.hongminh54.marisaoptimize.settings.Setting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

import java.util.List;

public class SoundEventListSettingScreen extends CollectionListSettingScreen<SoundEvent> {
    public SoundEventListSettingScreen(GuiTheme theme, Setting<List<SoundEvent>> setting) {
        super(theme, "Select Sounds", setting, setting.get(), BuiltInRegistries.SOUND_EVENT);
    }

    @Override
    protected WWidget getValueWidget(SoundEvent value) {
        return theme.label(value.location().getPath());
    }

    @Override
    protected String[] getValueNames(SoundEvent value) {
        return new String[]{
            value.location().toString(),
            I18n.get("subtitles." + value.location().getPath())
        };
    }
}
