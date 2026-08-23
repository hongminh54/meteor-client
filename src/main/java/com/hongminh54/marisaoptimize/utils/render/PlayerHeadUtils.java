/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.utils.render;

import com.google.gson.Gson;
import com.hongminh54.marisaoptimize.MarisaOptimize;
import com.hongminh54.marisaoptimize.systems.accounts.TexturesJson;
import com.hongminh54.marisaoptimize.systems.accounts.UuidToProfileResponse;
import com.hongminh54.marisaoptimize.utils.PostInit;
import com.hongminh54.marisaoptimize.utils.network.Http;

import java.util.Base64;
import java.util.UUID;

public class PlayerHeadUtils {
    public static PlayerHeadTexture STEVE_HEAD;

    private PlayerHeadUtils() {
    }

    @PostInit
    public static void init() {
        STEVE_HEAD = new PlayerHeadTexture();
    }

    public static byte[] fetchHead(UUID id) {
        if (id == null) return null;

        String url = getSkinUrl(id);
        if (url == null) return null;

        try {
            return PlayerHeadTexture.downloadHead(url);
        } catch (java.io.IOException e) {
            MarisaOptimize.LOG.error("Could not fetch player head for {}.", id, e);
            return null;
        }
    }

    public static String getSkinUrl(UUID id) {
        UuidToProfileResponse res2 = Http.get("https://sessionserver.mojang.com/session/minecraft/profile/" + id)
            .exceptionHandler(e -> MarisaOptimize.LOG.error("Could not contact mojang session servers.", e))
            .sendJson(UuidToProfileResponse.class);
        if (res2 == null) return null;

        String base64Textures = res2.getPropertyValue("textures");
        if (base64Textures == null) return null;

        TexturesJson textures = new Gson().fromJson(new String(Base64.getDecoder().decode(base64Textures)), TexturesJson.class);
        if (textures.textures.SKIN == null) return null;

        return textures.textures.SKIN.url;
    }
}
