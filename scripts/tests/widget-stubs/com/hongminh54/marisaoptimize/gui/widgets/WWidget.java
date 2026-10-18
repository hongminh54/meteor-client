package com.hongminh54.marisaoptimize.gui.widgets;

import net.minecraft.client.input.MouseButtonEvent;

/** Layout/focus stand-in for isolated slider input checks; no game or GPU. */
public class WWidget {
    public double x, y, width, height;
    public boolean mouseOver;
    public WWidget parent;
    protected final Theme theme = new Theme();
    protected void onCalculateSize() {}
    public boolean onMouseClicked(MouseButtonEvent click, boolean doubled) { return false; }
    public void onMouseMoved(double x, double y, double oldX, double oldY) {}
    public boolean onMouseReleased(MouseButtonEvent click) { return false; }
    public boolean onMouseScrolled(double amount) { return false; }
    public void setFocused(boolean focused) {}
    public static class Theme {
        public double textHeight() { return 10; }
    }
}
