/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.settings;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import com.hongminh54.marisaoptimize.utils.network.PacketUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PacketListSetting extends Setting<Set<PacketType<? extends Packet<?>>>> {
    public final Predicate<PacketType<? extends Packet<?>>> filter;
    private static List<String> suggestions;

    public PacketListSetting(String name, String description, Set<PacketType<? extends Packet<?>>> defaultValue, Consumer<Set<PacketType<? extends Packet<?>>>> onChanged, Consumer<Setting<Set<PacketType<? extends Packet<?>>>>> onModuleActivated, Predicate<PacketType<? extends Packet<?>>> filter, IVisible visible) {
        super(name, description, defaultValue, onChanged, onModuleActivated, visible);

        this.filter = filter;
    }

    @Override
    public void resetImpl() {
        value = new ObjectOpenHashSet<>(defaultValue);
    }

    @Override
    protected Set<PacketType<? extends Packet<?>>> parseImpl(String str) {
        String[] values = str.split(",");
        Set<PacketType<? extends Packet<?>>> packets = new ObjectOpenHashSet<>(values.length);

        try {
            for (String value : values) {
                PacketType<? extends Packet<?>> packet = PacketUtils.getPacket(value.trim());
                if (packet != null && (filter == null || filter.test(packet))) packets.add(packet);
            }
        } catch (Exception ignored) {}

        return packets;
    }

    @Override
    protected boolean isValueValid(Set<PacketType<? extends Packet<?>>> value) {
        return true;
    }

    @Override
    public List<String> getSuggestions() {
        if (suggestions == null) {
            suggestions = new ArrayList<>(PacketUtils.getC2SPackets().size() + PacketUtils.getS2CPackets().size());

            for (PacketType<? extends Packet<?>> packet : PacketUtils.getC2SPackets()) {
                suggestions.add(PacketUtils.getName(packet));
            }

            for (PacketType<? extends Packet<?>> packet : PacketUtils.getS2CPackets()) {
                suggestions.add(PacketUtils.getName(packet));
            }
        }

        return suggestions;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag valueTag = new ListTag();
        for (PacketType<? extends Packet<?>> packet : get()) {
            valueTag.add(StringTag.valueOf(PacketUtils.getName(packet)));
        }
        tag.put("value", valueTag);

        return tag;
    }

    @Override
    public Set<PacketType<? extends Packet<?>>> load(CompoundTag tag) {
        get().clear();

        Tag valueTag = tag.get("value");
        if (valueTag instanceof ListTag) {
            for (Tag t : (ListTag) valueTag) {
                PacketType<? extends Packet<?>> packet = PacketUtils.getPacket(t.asString().orElse(""));
                if (packet != null && (filter == null || filter.test(packet))) get().add(packet);
            }
        }

        return get();
    }

    public static class Builder extends SettingBuilder<Builder, Set<PacketType<? extends Packet<?>>>, PacketListSetting> {
        private Predicate<PacketType<? extends Packet<?>>> filter;

        public Builder() {
            super(new ObjectOpenHashSet<>(0));
        }

        public Builder filter(Predicate<PacketType<? extends Packet<?>>> filter) {
            this.filter = filter;
            return this;
        }

        @Override
        public PacketListSetting build() {
            return new PacketListSetting(name, description, defaultValue, onChanged, onModuleActivated, filter, visible);
        }
    }
}
