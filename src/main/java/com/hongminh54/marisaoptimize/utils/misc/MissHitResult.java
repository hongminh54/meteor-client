/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.utils.misc;

import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class MissHitResult extends HitResult {
    public static final MissHitResult INSTANCE = new MissHitResult();

    private MissHitResult() {
        super(Vec3.ZERO);
    }

    @Override
    public Type getType() {
        return Type.MISS;
    }
}
