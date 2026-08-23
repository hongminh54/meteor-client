/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.gui.themes.meteor.widgets.pressable;

import com.hongminh54.marisaoptimize.gui.themes.meteor.MeteorWidget;
import com.hongminh54.marisaoptimize.gui.widgets.pressable.WFavorite;
import com.hongminh54.marisaoptimize.utils.render.color.Color;

public class WMeteorFavorite extends WFavorite implements MeteorWidget {
    public WMeteorFavorite(boolean checked) {
        super(checked);
    }

    @Override
    protected Color getColor() {
        return theme().favoriteColor.get();
    }
}
