/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.utils.tooltip;

import net.minecraft.client.gui.screens.inventory.tooltip.ClientTextTooltip;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.network.chat.Component;

public class TextTooltipComponent extends ClientTextTooltip implements MeteorTooltipData {
    public TextTooltipComponent(FormattedCharSequence text) {
        super(text);
    }

    public TextTooltipComponent(Component text) {
        this(text.getVisualOrderText());
    }

    @Override
    public ClientTooltipComponent getComponent() {
        return this;
    }
}
