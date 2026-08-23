/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixininterface;

import com.mojang.authlib.GameProfile;

public interface IChatHudLine {
    String meteor$getText();

    int meteor$getId();

    void meteor$setId(int id);

    GameProfile meteor$getSender();

    void meteor$setSender(GameProfile profile);
}
