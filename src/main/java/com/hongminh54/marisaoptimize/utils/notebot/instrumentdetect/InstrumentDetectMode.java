/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.utils.notebot.instrumentdetect;

import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.client.Minecraft;

public enum InstrumentDetectMode {
    BlockState(((noteBlock, blockPos) -> noteBlock.getValue(NoteBlock.INSTRUMENT))),
    BelowBlock(((noteBlock, blockPos) -> Minecraft.getInstance().level.getBlockState(blockPos.below()).instrument()));

    private final InstrumentDetectFunction instrumentDetectFunction;

    InstrumentDetectMode(InstrumentDetectFunction instrumentDetectFunction) {
        this.instrumentDetectFunction = instrumentDetectFunction;
    }

    public InstrumentDetectFunction getInstrumentDetectFunction() {
        return instrumentDetectFunction;
    }
}
