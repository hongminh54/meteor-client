/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.systems.modules.misc;

//Created by squidoodly

import meteordevelopment.discordipc.DiscordIPC;
import meteordevelopment.discordipc.RichPresence;
import com.hongminh54.marisaoptimize.MarisaOptimize;
import com.hongminh54.marisaoptimize.events.game.OpenScreenEvent;
import com.hongminh54.marisaoptimize.events.world.TickEvent;
import com.hongminh54.marisaoptimize.gui.GuiTheme;
import com.hongminh54.marisaoptimize.gui.WidgetScreen;
import com.hongminh54.marisaoptimize.gui.utils.StarscriptTextBoxRenderer;
import com.hongminh54.marisaoptimize.gui.widgets.WWidget;
import com.hongminh54.marisaoptimize.gui.widgets.pressable.WButton;
import com.hongminh54.marisaoptimize.systems.modules.Categories;
import com.hongminh54.marisaoptimize.systems.modules.Module;
import com.hongminh54.marisaoptimize.utils.Utils;
import com.hongminh54.marisaoptimize.utils.misc.MeteorStarscript;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.gui.screens.*;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.options.*;
import net.minecraft.client.gui.screens.options.controls.ControlsScreen;
import net.minecraft.client.gui.screens.packs.PackSelectionScreen;
import net.minecraft.client.gui.screens.worldselection.*;
import net.minecraft.realms.RealmsScreen;
import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.ObjectObjectMutablePair;
import net.minecraft.util.Util;
import org.meteordev.starscript.Script;

import java.util.ArrayList;
import java.util.List;
import com.hongminh54.marisaoptimize.settings.*;

public class DiscordPresence extends Module {
    public enum SelectMode {
        Random,
        Sequential
    }

    private final SettingGroup sgLine1 = settings.createGroup("Line 1");
    private final SettingGroup sgLine2 = settings.createGroup("Line 2");

    // Line 1

    private final Setting<List<String>> line1Strings = sgLine1.add(new StringListSetting.Builder()
        .name("line-1-messages")
        .description("Messages used for the first line.")
        .defaultValue("{player}", "{server}")
        .onChanged(strings -> recompileLine1())
        .renderer(StarscriptTextBoxRenderer.class)
        .build()
    );

    private final Setting<Integer> line1UpdateDelay = sgLine1.add(new IntSetting.Builder()
        .name("line-1-update-delay")
        .description("How fast to update the first line in ticks.")
        .defaultValue(200)
        .min(10)
        .sliderRange(10, 200)
        .build()
    );

    private final Setting<SelectMode> line1SelectMode = sgLine1.add(new EnumSetting.Builder<SelectMode>()
        .name("line-1-select-mode")
        .description("How to select messages for the first line.")
        .defaultValue(SelectMode.Sequential)
        .build()
    );

    // Line 2

    private final Setting<List<String>> line2Strings = sgLine2.add(new StringListSetting.Builder()
        .name("line-2-messages")
        .description("Messages used for the second line.")
        .defaultValue("Meteor on Crack!", "{round(server.tps, 1)} TPS", "Playing on {server.difficulty} difficulty.", "{server.player_count} Players online")
        .onChanged(strings -> recompileLine2())
        .renderer(StarscriptTextBoxRenderer.class)
        .build()
    );

    private final Setting<Integer> line2UpdateDelay = sgLine2.add(new IntSetting.Builder()
        .name("line-2-update-delay")
        .description("How fast to update the second line in ticks.")
        .defaultValue(60)
        .min(10)
        .sliderRange(10, 200)
        .build()
    );

    private final Setting<SelectMode> line2SelectMode = sgLine2.add(new EnumSetting.Builder<SelectMode>()
        .name("line-2-select-mode")
        .description("How to select messages for the second line.")
        .defaultValue(SelectMode.Sequential)
        .build()
    );

    private static final RichPresence rpc = new RichPresence();
    private SmallImage currentSmallImage;
    private int ticks;
    private boolean forceUpdate, lastWasInMainMenu;

    private final List<Script> line1Scripts = new ArrayList<>();
    private int line1Ticks, line1I;

    private final List<Script> line2Scripts = new ArrayList<>();
    private int line2Ticks, line2I;

    public static final List<Pair<String, String>> customStates = new ArrayList<>();

    static {
        registerCustomState("com.terraformersmc.modmenu.gui", "Browsing mods");
        registerCustomState("me.jellysquid.mods.sodium.client", "Changing options");
    }

    public DiscordPresence() {
        super(Categories.Misc, "discord-presence", "Displays Meteor as your presence on Discord.");

        runInMainMenu = true;
    }

    /** Registers a custom state to be used when the current screen is a class in the specified package. */
    public static void registerCustomState(String packageName, String state) {
        for (var pair : customStates) {
            if (pair.left().equals(packageName)) {
                pair.right(state);
                return;
            }
        }

        customStates.add(new ObjectObjectMutablePair<>(packageName, state));
    }

    /** The package name must match exactly to the one provided through {@link #registerCustomState(String, String)}. */
    public static void unregisterCustomState(String packageName) {
        customStates.removeIf(pair -> pair.left().equals(packageName));
    }

    @Override
    public void onActivate() {
        DiscordIPC.start(835240968533049424L, null);

        rpc.setStart(System.currentTimeMillis() / 1000L);

        String largeText = "%s %s".formatted(MarisaOptimize.NAME, MarisaOptimize.VERSION);
        if (!MarisaOptimize.BUILD_NUMBER.isEmpty()) largeText += " Build: " + MarisaOptimize.BUILD_NUMBER;
        rpc.setLargeImage("meteor_client", largeText);

        currentSmallImage = SmallImage.Snail;

        recompileLine1();
        recompileLine2();

        ticks = 0;
        line1Ticks = 0;
        line2Ticks = 0;
        lastWasInMainMenu = false;

        line1I = 0;
        line2I = 0;
    }

    @Override
    public void onDeactivate() {
        DiscordIPC.stop();
    }

    private void recompile(List<String> messages, List<Script> scripts) {
        scripts.clear();

        for (String message : messages) {
            Script script = MeteorStarscript.compile(message);
            if (script != null) scripts.add(script);
        }

        forceUpdate = true;
    }

    private void recompileLine1() {
        recompile(line1Strings.get(), line1Scripts);
    }

    private void recompileLine2() {
        recompile(line2Strings.get(), line2Scripts);
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        boolean update = false;

        // Image
        if (ticks >= 200 || forceUpdate) {
            currentSmallImage = currentSmallImage.next();
            currentSmallImage.apply();
            update = true;

            ticks = 0;
        }
        else ticks++;

        if (Utils.canUpdate()) {
            // Line 1
            if (line1Ticks >= line1UpdateDelay.get() || forceUpdate) {
                if (!line1Scripts.isEmpty()) {
                    int i = Utils.random(0, line1Scripts.size());
                    if (line1SelectMode.get() == SelectMode.Sequential) {
                        if (line1I >= line1Scripts.size()) line1I = 0;
                        i = line1I++;
                    }

                    String message = MeteorStarscript.run(line1Scripts.get(i));
                    if (message != null) rpc.setDetails(message);
                }
                update = true;

                line1Ticks = 0;
            } else line1Ticks++;

            // Line 2
            if (line2Ticks >= line2UpdateDelay.get() || forceUpdate) {
                if (!line2Scripts.isEmpty()) {
                    int i = Utils.random(0, line2Scripts.size());
                    if (line2SelectMode.get() == SelectMode.Sequential) {
                        if (line2I >= line2Scripts.size()) line2I = 0;
                        i = line2I++;
                    }

                    String message = MeteorStarscript.run(line2Scripts.get(i));
                    if (message != null) rpc.setState(message);
                }
                update = true;

                line2Ticks = 0;
            } else line2Ticks++;
        }
        else {
            if (!lastWasInMainMenu) {
                rpc.setDetails(MarisaOptimize.NAME + " " + (MarisaOptimize.BUILD_NUMBER.isEmpty() ? MarisaOptimize.VERSION : MarisaOptimize.VERSION + " " + MarisaOptimize.BUILD_NUMBER));

                if (mc.gui.screen() instanceof TitleScreen) rpc.setState("Looking at title screen");
                else if (mc.gui.screen() instanceof SelectWorldScreen) rpc.setState("Selecting world");
                else if (mc.gui.screen() instanceof CreateWorldScreen || mc.gui.screen() instanceof AbstractGameRulesScreen) rpc.setState("Creating world");
                else if (mc.gui.screen() instanceof EditWorldScreen) rpc.setState("Editing world");
                else if (mc.gui.screen() instanceof LevelLoadingScreen) rpc.setState("Loading world");
                else if (mc.gui.screen() instanceof JoinMultiplayerScreen) rpc.setState("Selecting server");
                else if (mc.gui.screen() instanceof ManageServerScreen) rpc.setState("Adding server");
                else if (mc.gui.screen() instanceof ConnectScreen || mc.gui.screen() instanceof DirectJoinServerScreen) rpc.setState("Connecting to server");
                else if (mc.gui.screen() instanceof WidgetScreen) rpc.setState("Browsing Meteor's GUI");
                else if (mc.gui.screen() instanceof OptionsScreen || mc.gui.screen() instanceof SkinCustomizationScreen || mc.gui.screen() instanceof SoundOptionsScreen || mc.gui.screen() instanceof VideoSettingsScreen || mc.gui.screen() instanceof ControlsScreen || mc.gui.screen() instanceof LanguageSelectScreen || mc.gui.screen() instanceof ChatOptionsScreen || mc.gui.screen() instanceof PackSelectionScreen || mc.gui.screen() instanceof AccessibilityOptionsScreen) rpc.setState("Changing options");
                else if (mc.gui.screen() instanceof WinScreen) rpc.setState("Reading credits");
                else if (mc.gui.screen() instanceof RealmsScreen) rpc.setState("Browsing Realms");
                else {
                    boolean setState = false;
                    if (mc.gui.screen() != null) {
                        String className = mc.gui.screen().getClass().getName();
                        for (var pair : customStates) {
                            if (className.startsWith(pair.left())) {
                                rpc.setState(pair.right());
                                setState = true;
                                break;
                            }
                        }
                    }
                    if (!setState) rpc.setState("In main menu");
                }

                update = true;
            }
        }

        // Update
        if (update) DiscordIPC.setActivity(rpc);
        forceUpdate = false;
        lastWasInMainMenu = !Utils.canUpdate();
    }

    @EventHandler
    private void onOpenScreen(OpenScreenEvent event) {
        if (!Utils.canUpdate()) lastWasInMainMenu = false;
    }

    @Override
    public WWidget getWidget(GuiTheme theme) {
        WButton help = theme.button("Open documentation.");
        help.action = () -> Util.getPlatform().openUri("https://github.com/TheBlueSkyARL/marisa-optimize/wiki/Starscript");

        return help;
    }

    private enum SmallImage {
        MineGame("minegame", "MineGame159"),
        Snail("seasnail", "seasnail8169");

        private final String key, text;

        SmallImage(String key, String text) {
            this.key = key;
            this.text = text;
        }

        void apply() {
            rpc.setSmallImage(key, text);
        }

        SmallImage next() {
            if (this == MineGame) return Snail;
            return MineGame;
        }
    }
}
