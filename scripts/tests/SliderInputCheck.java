import com.hongminh54.marisaoptimize.gui.widgets.input.WSlider;
import com.hongminh54.marisaoptimize.gui.widgets.input.WIntEdit;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.input.MouseButtonInfo;

public class SliderInputCheck {
    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    public static void main(String[] args) {
        WSlider slider = new WSlider(20, 0, 10) {};
        slider.width = 110;
        slider.height = 10;
        check(slider.get() == 10, "Constructor clamp failed");
        slider.set(-5);
        check(slider.get() == 0, "Setter clamp failed");
        int[] changes = {0}, releases = {0};
        slider.action = () -> changes[0]++;
        slider.actionOnRelease = () -> releases[0]++;
        slider.mouseOver = true;
        var click = new MouseButtonEvent(55, 5, new MouseButtonInfo(0, 0));
        check(!slider.onMouseClicked(click, true), "Double click should not start drag");
        check(slider.onMouseClicked(click, false) && slider.get() == 5, "Click position conversion failed");
        slider.onMouseMoved(75, 5, 55, 5);
        check(slider.get() == 7, "Drag movement failed");
        check(slider.onMouseReleased(click) && releases[0] == 1, "Release callback failed");
        check(!slider.onMouseReleased(click) && releases[0] == 1, "Duplicate release callback");
        slider.onMouseMoved(75, 5, 75, 5);
        check(slider.onMouseScrolled(1) && Math.abs(slider.get() - 7.05) < 1e-9, "Decimal scroll failed");
        slider.parent = new WIntEdit();
        slider.onMouseScrolled(1);
        check(Math.abs(slider.get() - 8.05) < 1e-9, "Integer-parent scroll increment failed");
        slider.onMouseMoved(200, 200, 75, 5);
        check(!slider.onMouseScrolled(1), "Scroll outside handle should propagate");
        check(changes[0] == 4, "Unexpected change callback count");
        System.out.println("Slider checks passed: clamp, click/drag/release, callbacks and scroll increments; layout/focus stubbed.");
    }
}
