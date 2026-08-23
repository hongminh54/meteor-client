/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.addons;

import com.hongminh54.marisaoptimize.MarisaOptimize;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.Person;

import java.util.ArrayList;
import java.util.List;

public class AddonManager {
    public static final List<MeteorAddon> ADDONS = new ArrayList<>();

    public static void init() {
        // Meteor pseudo addon
        {
            MarisaOptimize.ADDON = new MeteorAddon() {
                @Override
                public void onInitialize() {}

                @Override
                public String getPackage() {
                    return "com.hongminh54.marisaoptimize";
                }

                @Override
                public String getWebsite() {
                    return "https://github.com/TheBlueSkyARL/marisa-optimize";
                }

                @Override
                public GithubRepo getRepo() {
                    return new GithubRepo("TheBlueSkyARL", "marisa-optimize");
                }

                @Override
                public String getCommit() {
                    String commit = MarisaOptimize.MOD_META.getCustomValue(MarisaOptimize.MOD_ID + ":commit").getAsString();
                    return commit.isEmpty() ? null : commit;
                }
            };

            ModMetadata metadata = FabricLoader.getInstance().getModContainer(MarisaOptimize.MOD_ID).get().getMetadata();

            MarisaOptimize.ADDON.name = metadata.getName();
            MarisaOptimize.ADDON.authors = new String[metadata.getAuthors().size()];
            if (metadata.containsCustomValue(MarisaOptimize.MOD_ID + ":color")) {
                MarisaOptimize.ADDON.color.parse(metadata.getCustomValue(MarisaOptimize.MOD_ID + ":color").getAsString());
            }

            int i = 0;
            for (Person author : metadata.getAuthors()) {
                MarisaOptimize.ADDON.authors[i++] = author.getName();
            }

            ADDONS.add(MarisaOptimize.ADDON);
        }

        // Addons
        for (EntrypointContainer<MeteorAddon> entrypoint : FabricLoader.getInstance().getEntrypointContainers("meteor", MeteorAddon.class)) {
            ModMetadata metadata = entrypoint.getProvider().getMetadata();
            MeteorAddon addon;
            try {
                addon = entrypoint.getEntrypoint();
            } catch (Throwable throwable) {
                throw new RuntimeException("Exception during addon init \"%s\".".formatted(metadata.getName()), throwable);
            }

            addon.name = metadata.getName();

            if (metadata.getAuthors().isEmpty()) throw new RuntimeException("Addon \"%s\" requires at least 1 author to be defined in it's fabric.mod.json. See https://fabricmc.net/wiki/documentation:fabric_mod_json_spec".formatted(addon.name));
            addon.authors = new String[metadata.getAuthors().size()];

            if (metadata.containsCustomValue(MarisaOptimize.MOD_ID + ":color")) {
                addon.color.parse(metadata.getCustomValue(MarisaOptimize.MOD_ID + ":color").getAsString());
            }

            int i = 0;
            for (Person author : metadata.getAuthors()) {
                addon.authors[i++] = author.getName();
            }

            ADDONS.add(addon);
        }
    }
}
