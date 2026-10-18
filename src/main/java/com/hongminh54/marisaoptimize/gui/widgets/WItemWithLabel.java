/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.gui.widgets;

import com.hongminh54.marisaoptimize.gui.widgets.containers.WHorizontalList;
import com.hongminh54.marisaoptimize.utils.misc.Names;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;

import java.util.Iterator;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

public class WItemWithLabel extends WHorizontalList {
    private ItemStack itemStack;
    private String name;

    private WItem item;
    private WLabel label;

    public WItemWithLabel(ItemStack itemStack, String name) {
        this.itemStack = itemStack;
        this.name = name;
    }

    @Override
    public void init() {
        item = add(theme.item(itemStack)).widget();
        label = add(theme.label(name + getStringToAppend())).widget();
    }

    private String getStringToAppend() {
        String str = "";

        if (itemStack.getItem() == Items.POTION) {
            PotionContents contents = itemStack.get(DataComponents.POTION_CONTENTS);
            if (contents == null) return str;
            Iterator<MobEffectInstance> effects = contents.getAllEffects().iterator();
            if (!effects.hasNext()) return str;

            str += " ";

            MobEffectInstance effect = effects.next();
            if (effect.getAmplifier() > 0) str += "%d ".formatted(effect.getAmplifier() + 1);

            str += "(%s)".formatted(MobEffectUtil.formatDuration(effect, 1, mc.level != null ? mc.level.tickRateManager().tickrate() : 20.0F).getString());
        }

        return str;
    }

    public void set(ItemStack itemStack) {
        this.itemStack = itemStack;
        item.itemStack = itemStack;

        name = Names.get(itemStack);
        label.set(name + getStringToAppend());
    }

    public String getLabelText() {
        return label == null ? name : label.get();
    }
}
