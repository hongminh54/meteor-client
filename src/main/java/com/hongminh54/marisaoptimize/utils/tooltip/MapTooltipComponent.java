/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.utils.tooltip;

import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.BetterTooltips;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.state.MapRenderState;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.resources.Identifier;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

public class MapTooltipComponent implements ClientTooltipComponent, MeteorTooltipData {
    private static final Identifier TEXTURE_MAP_BACKGROUND = Identifier.parse("textures/map/map_background.png");
    private final int mapId;
    private final MapRenderState mapRenderState = new MapRenderState();

    public MapTooltipComponent(int mapId) {
        this.mapId = mapId;
    }

    @Override
    public int getHeight(Font textRenderer) {
        double scale = Modules.get().get(BetterTooltips.class).mapsScale.get();
        return (int) ((128 + 16) * scale) + 2;
    }

    @Override
    public int getWidth(Font textRenderer) {
        double scale = Modules.get().get(BetterTooltips.class).mapsScale.get();
        return (int) ((128 + 16) * scale);
    }

    @Override
    public ClientTooltipComponent getComponent() {
        return this;
    }

    @Override
    public void extractImage(Font textRenderer, int x, int y, int width, int height, GuiGraphicsExtractor context) {
        var scale = Modules.get().get(BetterTooltips.class).mapsScale.get().floatValue();

        // Background
        int size = (int) ((128 + 16) * scale);
        context.blit(RenderPipelines.GUI_TEXTURED, TEXTURE_MAP_BACKGROUND, x, y, 0,0, size, size, size, size);

        // Contents
        MapItemSavedData mapState = MapItem.getSavedData(new MapId(mapId), mc.level);
        if (mapState == null) return;

        context.pose().pushMatrix();
        context.pose().translate(x, y);
        context.pose().scale(scale, scale);
        context.pose().translate(8, 8);

        mc.getMapRenderer().extractRenderState(new MapId(mapId), mapState, mapRenderState);
        context.map(mapRenderState);

        context.pose().popMatrix();
    }
}
