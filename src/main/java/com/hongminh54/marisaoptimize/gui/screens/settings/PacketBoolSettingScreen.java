/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.gui.screens.settings;

import com.hongminh54.marisaoptimize.gui.GuiTheme;
import com.hongminh54.marisaoptimize.gui.screens.settings.base.CollectionListSettingScreen;
import com.hongminh54.marisaoptimize.gui.widgets.WWidget;
import com.hongminh54.marisaoptimize.settings.PacketListSetting;
import com.hongminh54.marisaoptimize.settings.Setting;
import com.hongminh54.marisaoptimize.utils.network.PacketUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;

import java.util.Set;
import java.util.function.Predicate;

public class PacketBoolSettingScreen extends CollectionListSettingScreen<PacketType<? extends Packet<?>>> {
    public PacketBoolSettingScreen(GuiTheme theme, Setting<Set<PacketType<? extends Packet<?>>>> setting) {
        super(theme, "Select Packets", setting, setting.get(), PacketUtils.PACKETS);
    }

    @Override
    protected boolean includeValue(PacketType<? extends Packet<?>> value) {
        Predicate<PacketType<? extends Packet<?>>> filter = ((PacketListSetting) setting).filter;

        if (filter == null) return true;
        return filter.test(value);
    }

    @Override
    protected WWidget getValueWidget(PacketType<? extends Packet<?>> value) {
        return theme.label(PacketUtils.getName(value));
    }

    @Override
    protected String[] getValueNames(PacketType<? extends Packet<?>> value) {
        return new String[]{
            PacketUtils.getName(value),
            value.id().getPath()
        };
    }
}
