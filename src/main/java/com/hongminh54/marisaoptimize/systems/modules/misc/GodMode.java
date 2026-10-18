/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.systems.modules.misc;

import com.hongminh54.marisaoptimize.events.packets.PacketEvent;
import com.hongminh54.marisaoptimize.settings.BoolSetting;
import com.hongminh54.marisaoptimize.settings.EnumSetting;
import com.hongminh54.marisaoptimize.settings.Setting;
import com.hongminh54.marisaoptimize.settings.SettingGroup;
import com.hongminh54.marisaoptimize.systems.modules.Categories;
import com.hongminh54.marisaoptimize.systems.modules.Module;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.utils.Utils;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.network.protocol.game.ClientboundDamageEventPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;

public class GodMode extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Mode> mode = sgGeneral.add(new EnumSetting.Builder<Mode>()
        .name("mode")
        .description("Abilities fully protects you in singleplayer/LAN worlds. Packet only hides hurt effects on multiplayer servers.")
        .defaultValue(Mode.Abilities)
        .onChanged(v -> {
            if (!isActive() || !Utils.canUpdate()) return;

            if (v == Mode.Abilities) mc.player.getAbilities().invulnerable = true;
            else if (!mc.player.getAbilities().instabuild) mc.player.getAbilities().invulnerable = false;
        })
        .build()
    );

    private final Setting<Boolean> hideHurtEffects = sgGeneral.add(new BoolSetting.Builder()
        .name("hide-hurt-effects")
        .description("Cancels incoming damage packets so you don't see hurt effects. The server still applies real damage.")
        .defaultValue(true)
        .visible(() -> mode.get() == Mode.Packet)
        .build()
    );

    public GodMode() {
        super(Categories.Misc, "god-mode", "Makes you invincible, like creative mode. Fully works in singleplayer and LAN worlds.");
    }

    @Override
    public void onActivate() {
        if (mode.get() == Mode.Abilities) {
            if (Utils.canUpdate()) mc.player.getAbilities().invulnerable = true;
            info("Fully invincible in singleplayer and LAN worlds. Remote servers still control your health.");
        }
        else {
            warning("Only hiding hurt effects client-side; the server still applies real damage to you.");
        }
    }

    @Override
    public void onDeactivate() {
        if (mode.get() == Mode.Abilities && Utils.canUpdate()) {
            Abilities abilities = mc.player.getAbilities();
            if (!abilities.instabuild) abilities.invulnerable = false;
        }
    }

    @EventHandler
    private void onReceivePacket(PacketEvent.Receive event) {
        if (mc.player == null) return;

        if (event.packet instanceof ClientboundDamageEventPacket packet) {
            if (mode.get() == Mode.Packet && hideHurtEffects.get() && packet.entityId() == mc.player.getId()) event.cancel();
        }
        else if (event.packet instanceof ClientboundPlayerAbilitiesPacket packet && mode.get() == Mode.Abilities) {
            // Cancel so vanilla cannot reset our invulnerable flag, then apply everything else.
            event.cancel();

            Abilities abilities = mc.player.getAbilities();
            abilities.flying = packet.isFlying();
            abilities.mayfly = packet.canFly();
            abilities.instabuild = packet.canInstabuild();
            abilities.setFlyingSpeed(packet.getFlyingSpeed());
            abilities.setWalkingSpeed(packet.getWalkingSpeed());

            abilities.invulnerable = true;
        }
    }

    /**
     * Called from LivingEntityMixin. Blocks damage server-side (integrated server)
     * when Abilities mode is active, making it work like creative invulnerability.
     */
    public static boolean shouldBlockDamage(Entity entity) {
        GodMode godMode = Modules.get().get(GodMode.class);
        if (!godMode.isActive() || godMode.mode.get() != Mode.Abilities) return false;
        if (!(entity instanceof LivingEntity)) return false;

        LocalPlayer player = godMode.mc.player;
        return player != null && entity.getUUID().equals(player.getUUID());
    }

    @Override
    public String getInfoString() {
        return mode.get().name();
    }

    public enum Mode {
        Abilities,
        Packet
    }
}
