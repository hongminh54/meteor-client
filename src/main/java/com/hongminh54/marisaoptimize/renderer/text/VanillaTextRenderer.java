/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.renderer.text;

import com.hongminh54.marisaoptimize.utils.render.color.Color;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.joml.Matrix3x2fStack;

import java.util.Objects;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

public class VanillaTextRenderer implements TextRenderer {
    public static final VanillaTextRenderer INSTANCE = new VanillaTextRenderer();

    public double scale = 2;
    public boolean scaleIndividually;

    private GuiGraphicsExtractor graphics;
    private boolean building;
    private double alpha = 1;

    private VanillaTextRenderer() {}

    @Override
    public void setAlpha(double a) {
        alpha = a;
    }

    @Override
    public double getWidth(String text, int length, boolean shadow) {
        if (text.isEmpty()) return 0;
        if (length != text.length()) text = text.substring(0, length);
        return (mc.font.width(text) + (shadow ? 1 : 0)) * scale;
    }

    @Override
    public double getHeight(boolean shadow) {
        return (mc.font.lineHeight + (shadow ? 1 : 0)) * scale;
    }

    @Override
    public void begin(GuiGraphicsExtractor graphics, double scale, boolean scaleOnly, boolean big) {
        if (building) throw new IllegalStateException("VanillaTextRenderer.begin() called twice");
        this.graphics = Objects.requireNonNull(graphics, "Vanilla text requires GUI graphics");
        this.scale = scale * 2;
        building = true;
    }

    @Override
    public double render(String text, double x, double y, Color color, boolean shadow) {
        if (!building) throw new IllegalStateException("VanillaTextRenderer.render() called without begin()");

        x += 0.5 * scale;
        y += 0.5 * scale;
        int previousAlpha = color.a;
        Matrix3x2fStack matrices = graphics.pose();
        matrices.pushMatrix();
        try {
            color.a = (int) (previousAlpha * alpha);
            matrices.scale((float) scale, (float) scale);
            graphics.text(mc.font, text, (int) (x / scale), (int) (y / scale), color.getPacked(), shadow);
            return (x / scale + mc.font.width(text) - 1) * scale;
        } finally {
            matrices.popMatrix();
            color.a = previousAlpha;
        }
    }

    @Override
    public boolean isBuilding() {
        return building;
    }

    @Override
    public void end() {
        if (!building) throw new IllegalStateException("VanillaTextRenderer.end() called without begin()");
        graphics = null;
        scale = 2;
        building = false;
    }
}
