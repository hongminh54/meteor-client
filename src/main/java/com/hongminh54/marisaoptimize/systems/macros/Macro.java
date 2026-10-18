/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.systems.macros;

import com.hongminh54.marisaoptimize.gui.utils.StarscriptTextBoxRenderer;
import com.hongminh54.marisaoptimize.utils.misc.ISerializable;
import com.hongminh54.marisaoptimize.utils.misc.Keybind;
import com.hongminh54.marisaoptimize.utils.misc.MeteorStarscript;
import com.hongminh54.marisaoptimize.utils.player.ChatUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import org.meteordev.starscript.Script;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;
import com.hongminh54.marisaoptimize.settings.*;

public class Macro implements ISerializable<Macro> {
    public final Settings settings = new Settings();

    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    public Setting<String> name = sgGeneral.add(new StringSetting.Builder()
        .name("name")
        .description("The name of the macro.")
        .build()
    );

    public Setting<List<String>> messages = sgGeneral.add(new StringListSetting.Builder()
        .name("messages")
        .description("The messages for the macro to send.")
        .onChanged(v -> dirty = true)
        .renderer(StarscriptTextBoxRenderer.class)
        .build()
    );

    public Setting<Keybind> keybind = sgGeneral.add(new KeybindSetting.Builder()
        .name("keybind")
        .description("The bind to run the macro.")
        .build()
    );

    private final List<Script> scripts = new ArrayList<>(1);
    private boolean dirty;

    public Macro() {}
    public Macro(Tag tag) {
        fromTag((CompoundTag) tag);
    }

    public boolean onAction(boolean isKey, int value, int modifiers) {
        if (!keybind.get().matches(isKey, value, modifiers) || mc.gui.screen() != null) return false;
        return onAction();
    }

    public boolean onAction() {
        if (dirty) {
            scripts.clear();

            for (String message : messages.get()) {
                Script script = MeteorStarscript.compile(message);
                if (script != null) scripts.add(script);
            }

            dirty = false;
        }

        for (Script script : scripts) {
            String message = MeteorStarscript.run(script);

            if (message != null) {
                ChatUtils.sendPlayerMsg(message, false);
            }
        }

        return true;
    }

    @Override
    public CompoundTag toTag() {
        CompoundTag tag = new CompoundTag();

        tag.put("settings", settings.toTag());

        return tag;
    }

    @Override
    public Macro fromTag(CompoundTag tag) {
        if (tag.contains("settings")) {
            settings.fromTag(tag.getCompoundOrEmpty("settings"));
        }

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Macro macro = (Macro) o;
        return Objects.equals(macro.name.get(), this.name.get());
    }
}
