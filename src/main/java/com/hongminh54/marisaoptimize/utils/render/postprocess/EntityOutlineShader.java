/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.utils.render.postprocess;

import com.hongminh54.marisaoptimize.renderer.MeshRenderer;
import com.hongminh54.marisaoptimize.renderer.MeteorRenderPipelines;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.ESP;
import net.minecraft.world.entity.Entity;

public class EntityOutlineShader extends EntityShader {
    private static ESP esp;

    public EntityOutlineShader() {
        super(MeteorRenderPipelines.POST_OUTLINE);
    }

    @Override
    protected boolean shouldDraw() {
        if (esp == null) esp = Modules.get().get(ESP.class);
        return esp.isShader();
    }

    @Override
    public boolean shouldDraw(Entity entity) {
        if (!shouldDraw()) return false;
        return !esp.shouldSkip(entity);
    }

    @Override
    protected void setupPass(MeshRenderer renderer) {
        renderer.uniform("OutlineData", OutlineUniforms.write(
            esp.outlineWidth.get(),
            esp.fillOpacity.get().floatValue(),
            esp.shapeMode.get().ordinal(),
            esp.glowMultiplier.get().floatValue()
        ));
    }
}
