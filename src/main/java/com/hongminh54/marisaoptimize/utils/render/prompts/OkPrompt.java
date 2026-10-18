/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.utils.render.prompts;

import com.hongminh54.marisaoptimize.gui.GuiTheme;
import com.hongminh54.marisaoptimize.gui.GuiThemes;
import com.hongminh54.marisaoptimize.gui.widgets.pressable.WButton;
import net.minecraft.client.gui.screens.Screen;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

public class OkPrompt extends Prompt<OkPrompt> {
    private Runnable onOk = () -> {};

    private OkPrompt(GuiTheme theme, Screen parent) {
        super(theme, parent);
    }

    public static OkPrompt create() {
        return new OkPrompt(GuiThemes.get(), mc.gui.screen());
    }

    public static OkPrompt create(GuiTheme theme, Screen parent) {
        return new OkPrompt(theme, parent);
    }

    public OkPrompt onOk(Runnable action) {
        this.onOk = action;
        return this;
    }

    @Override
    protected void initialiseWidgets(PromptScreen screen) {
        WButton okButton = screen.list.add(theme.button("Ok")).expandX().widget();
        okButton.action = () -> {
            dontShowAgain(screen);
            onOk.run();
            screen.onClose();
        };
    }
}
