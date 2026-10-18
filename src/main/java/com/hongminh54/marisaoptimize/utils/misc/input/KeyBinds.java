/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.utils.misc.input;

import com.hongminh54.marisaoptimize.MarisaOptimize;
import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;

public class KeyBinds {
    private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(MarisaOptimize.identifier("marisa-optimize"));

    public static KeyMapping OPEN_GUI = new KeyMapping("key.marisa-optimize.open-gui", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, CATEGORY);
    public static KeyMapping OPEN_COMMANDS = new KeyMapping("key.marisa-optimize.open-commands", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_PERIOD, CATEGORY);

    private KeyBinds() {
    }

    public static KeyMapping[] apply(KeyMapping[] binds) {
        // Add key binding
        KeyMapping[] newBinds = new KeyMapping[binds.length + 2];

        System.arraycopy(binds, 0, newBinds, 0, binds.length);
        newBinds[binds.length] = OPEN_GUI;
        newBinds[binds.length + 1] = OPEN_COMMANDS;

        return newBinds;
    }
}
