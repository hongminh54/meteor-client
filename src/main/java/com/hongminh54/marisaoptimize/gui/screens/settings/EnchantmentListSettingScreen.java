/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.gui.screens.settings;

import com.hongminh54.marisaoptimize.gui.GuiTheme;
import com.hongminh54.marisaoptimize.gui.screens.settings.base.DynamicRegistryListSettingScreen;
import com.hongminh54.marisaoptimize.gui.widgets.WWidget;
import com.hongminh54.marisaoptimize.settings.Setting;
import com.hongminh54.marisaoptimize.utils.misc.Names;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import java.util.Set;

public class EnchantmentListSettingScreen extends DynamicRegistryListSettingScreen<Enchantment> {
    public EnchantmentListSettingScreen(GuiTheme theme, Setting<Set<ResourceKey<Enchantment>>> setting) {
        super(theme, "Select Enchantments", setting, setting.get(), Registries.ENCHANTMENT);
    }

    @Override
    protected WWidget getValueWidget(ResourceKey<Enchantment> value) {
        return theme.label(Names.get(value));
    }

    @Override
    protected String[] getValueNames(ResourceKey<Enchantment> value) {
        return new String[]{
            Names.get(value),
            value.identifier().toString()
        };
    }
}
