/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.settings;

import com.hongminh54.marisaoptimize.utils.misc.IChangeable;
import com.hongminh54.marisaoptimize.utils.misc.ICopyable;
import com.hongminh54.marisaoptimize.utils.misc.IGetter;
import com.hongminh54.marisaoptimize.utils.misc.ISerializable;
import net.minecraft.world.level.block.Block;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class BlockDataSetting<T extends ICopyable<T> & ISerializable<T> & IChangeable & IBlockData<T>> extends Setting<Map<Block, T>> {
    public final IGetter<T> defaultData;

    public BlockDataSetting(String name, String description, Map<Block, T> defaultValue, Consumer<Map<Block, T>> onChanged, Consumer<Setting<Map<Block, T>>> onModuleActivated, IGetter<T> defaultData, IVisible visible) {
        super(name, description, defaultValue, onChanged, onModuleActivated, visible);

        this.defaultData = defaultData;
    }

    @Override
    public void resetImpl() {
        value = new HashMap<>(defaultValue);
    }

    @Override
    protected Map<Block, T> parseImpl(String str) {
        return new HashMap<>(0);
    }

    @Override
    protected boolean isValueValid(Map<Block, T> value) {
        return true;
    }

    @Override
    protected CompoundTag save(CompoundTag tag) {
        CompoundTag valueTag = new CompoundTag();
        for (Block block : get().keySet()) {
            valueTag.put(BuiltInRegistries.BLOCK.getKey(block).toString(), get().get(block).toTag());
        }
        tag.put("value", valueTag);

        return tag;
    }

    @Override
    protected Map<Block, T> load(CompoundTag tag) {
        get().clear();

        CompoundTag valueTag = tag.getCompoundOrEmpty("value");
        for (String key : valueTag.keySet()) {
            get().put(BuiltInRegistries.BLOCK.getValue(Identifier.parse(key)), defaultData.get().copy().fromTag(valueTag.getCompoundOrEmpty(key)));
        }

        return get();
    }

    public static class Builder<T extends ICopyable<T> & ISerializable<T> & IChangeable & IBlockData<T>> extends SettingBuilder<Builder<T>, Map<Block, T>, BlockDataSetting<T>> {
        private IGetter<T> defaultData;

        public Builder() {
            super(new HashMap<>(0));
        }

        public Builder<T> defaultData(IGetter<T> defaultData) {
            this.defaultData = defaultData;
            return this;
        }

        @Override
        public BlockDataSetting<T> build() {
            return new BlockDataSetting<>(name, description, defaultValue, onChanged, onModuleActivated, defaultData, visible);
        }
    }
}
