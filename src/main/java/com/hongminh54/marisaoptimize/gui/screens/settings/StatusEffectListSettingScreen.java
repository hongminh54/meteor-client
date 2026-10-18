/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.gui.screens.settings;

import com.hongminh54.marisaoptimize.gui.GuiTheme;
import com.hongminh54.marisaoptimize.gui.screens.settings.base.CollectionListSettingScreen;
import com.hongminh54.marisaoptimize.gui.widgets.WWidget;
import com.hongminh54.marisaoptimize.settings.Setting;
import com.hongminh54.marisaoptimize.utils.misc.Names;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.List;
import java.util.Optional;

public class StatusEffectListSettingScreen extends CollectionListSettingScreen<MobEffect> {
    public StatusEffectListSettingScreen(GuiTheme theme, Setting<List<MobEffect>> setting) {
        super(theme, "Select Effects", setting, setting.get(), BuiltInRegistries.MOB_EFFECT);
    }

    @Override
    protected WWidget getValueWidget(MobEffect value) {
        return theme.itemWithLabel(getPotionStack(value), Names.get(value));
    }

    @Override
    protected String[] getValueNames(MobEffect value) {
        return new String[]{
            Names.get(value),
            BuiltInRegistries.MOB_EFFECT.getKey(value).toString()
        };
    }

    private ItemStack getPotionStack(MobEffect effect) {
        ItemStack potion = com.hongminh54.marisaoptimize.utils.render.DisplayItemUtils.toStack(Items.POTION);

        potion.set(
            DataComponents.POTION_CONTENTS,
            new PotionContents(
                Optional.empty(),
                Optional.of(effect.getColor()),
                java.util.List.of(),
                Optional.empty()
            )
        );

        return potion;
    }
}
