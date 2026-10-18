/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.utils.misc;

import net.minecraft.nbt.CompoundTag;

public interface ISerializable<T> {
    CompoundTag toTag();

    T fromTag(CompoundTag tag);
}
