/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.gui.screens.settings.base;

import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import com.hongminh54.marisaoptimize.gui.GuiTheme;
import com.hongminh54.marisaoptimize.gui.widgets.containers.WHorizontalList;
import com.hongminh54.marisaoptimize.gui.widgets.containers.WTable;
import com.hongminh54.marisaoptimize.gui.widgets.input.WTextBox;
import com.hongminh54.marisaoptimize.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.IdentifierException;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public abstract class DynamicRegistryListSettingScreen<T> extends CollectionListSettingScreen<ResourceKey<T>> {
    protected final ResourceKey<Registry<T>> registryKey;

    public DynamicRegistryListSettingScreen(GuiTheme theme, String title, Setting<?> setting, Collection<ResourceKey<T>> collection, ResourceKey<Registry<T>> registryKey) {
        super(theme, title, setting, collection, createUniverse(collection, registryKey));

        this.registryKey = registryKey;
    }

    private static <T> Iterable<ResourceKey<T>> createUniverse(Collection<ResourceKey<T>> collection, ResourceKey<Registry<T>> registryKey) {
        Set<ResourceKey<T>> set = new ReferenceOpenHashSet<>(collection);

        Optional.ofNullable(Minecraft.getInstance().getConnection())
            .map(networkHandler -> (HolderLookup.Provider) networkHandler.registryAccess())
            .orElseGet(VanillaRegistries::createLookup)
            .lookup(registryKey)
            .ifPresent(registry -> registry.listElementIds().forEach(set::add));

        return set;
    }

    @Override
    protected void postWidgets(WTable left, WTable right) {
        if (!left.cells.isEmpty()) {
            left.add(theme.horizontalSeparator()).expandX();
            left.row();
        }

        WHorizontalList manualEntry = left.add(theme.horizontalList()).expandX().widget();
        WTextBox textBox = manualEntry.add(theme.textBox("minecraft:")).expandX().minWidth(120d).widget();
        manualEntry.add(theme.plus()).expandCellX().right().widget().action = () -> {
            String entry = textBox.get().trim();
            try {
                Identifier id = entry.contains(":") ? Identifier.parse(entry) : Identifier.withDefaultNamespace(entry);
                addValue(ResourceKey.create(registryKey, id));
            } catch (IdentifierException ignored) {}
        };
    }
}
