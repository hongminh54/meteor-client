/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixininterface;

import net.minecraft.core.BlockPos;

public interface IBox {
    void meteor$expand(double v);

    void meteor$set(double x1, double y1, double z1, double x2, double y2, double z2);

    default void meteor$set(BlockPos pos) {
        meteor$set(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
    }
}
