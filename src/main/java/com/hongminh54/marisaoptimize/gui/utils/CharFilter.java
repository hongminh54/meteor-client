/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.gui.utils;

public interface CharFilter {
    boolean filter(String text, char c);

    default boolean filter(String text, int i) {
        return filter(text, (char) i);
    }
}
