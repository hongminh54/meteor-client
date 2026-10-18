/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.gui.tabs.builtin;

import com.hongminh54.marisaoptimize.gui.GuiTheme;
import com.hongminh54.marisaoptimize.gui.GuiThemes;
import com.hongminh54.marisaoptimize.gui.tabs.Tab;
import com.hongminh54.marisaoptimize.gui.tabs.TabScreen;
import net.minecraft.client.gui.screens.Screen;

public class ModulesTab extends Tab {
    public ModulesTab() {
        super("Modules");
    }

    @Override
    public TabScreen createScreen(GuiTheme theme) {
        return theme.modulesScreen();
    }

    @Override
    public boolean isScreen(Screen screen) {
        return GuiThemes.get().isModulesScreen(screen);
    }
}
