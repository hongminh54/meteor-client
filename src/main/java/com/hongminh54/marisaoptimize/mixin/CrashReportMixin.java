/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.hongminh54.marisaoptimize.MarisaOptimize;
import com.hongminh54.marisaoptimize.systems.hud.Hud;
import com.hongminh54.marisaoptimize.systems.hud.HudElement;
import com.hongminh54.marisaoptimize.systems.hud.elements.TextHud;
import com.hongminh54.marisaoptimize.systems.modules.Category;
import com.hongminh54.marisaoptimize.systems.modules.Module;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import net.minecraft.CrashReport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(CrashReport.class)
public abstract class CrashReportMixin {
    @Inject(method = "getDetails(Ljava/lang/StringBuilder;)V", at = @At("TAIL"))
    private void onAddDetails(StringBuilder sb, CallbackInfo info) {
        try {
            meteor$appendDetails(sb);
        } catch (Throwable failure) {
            // Reporting must never hide the original crash if client initialization failed.
            sb.append("\nMarisa Optimize diagnostic details unavailable: ").append(failure.getClass().getName()).append('\n');
        }
    }

    @org.spongepowered.asm.mixin.Unique
    private void meteor$appendDetails(StringBuilder sb) {
        sb.append("\n\n-- Marisa Optimize --\n\n");
        sb.append("Version: ").append(MarisaOptimize.VERSION).append("\n");
        if (!MarisaOptimize.BUILD_NUMBER.isEmpty()) {
            sb.append("Build: ").append(MarisaOptimize.BUILD_NUMBER).append("\n");
        }

        if (Modules.get() != null) {
            boolean modulesActive = false;
            for (Category category : Modules.loopCategories()) {
                List<Module> modules = Modules.get().getGroup(category);
                boolean categoryActive = false;

                for (Module module : modules) {
                    if (module == null || !module.isActive()) continue;

                    if (!modulesActive) {
                        modulesActive = true;
                        sb.append("\n[[ Active Modules ]]\n");
                    }

                    if (!categoryActive) {
                        categoryActive = true;
                        sb.append("\n[")
                          .append(category)
                          .append("]:\n");
                    }

                    sb.append(module.name).append("\n");
                }

            }

        }

        if (Hud.get() != null && Hud.get().active) {
            boolean hudActive = false;
            for (HudElement element : Hud.get()) {
                if (element == null || !element.isActive()) continue;

                if (!hudActive) {
                    hudActive = true;
                    sb.append("\n[[ Active Hud Elements ]]\n");
                }

                if (!(element instanceof TextHud textHud)) sb.append(element.info.name).append("\n");
                else {
                    sb.append("Text\n{")
                      .append(textHud.text.get())
                      .append("}\n");
                    if (textHud.shown.get() != TextHud.Shown.Always) {
                        sb.append("(")
                          .append(textHud.shown.get())
                          .append(textHud.condition.get())
                          .append(")\n");
                    }
                }
            }
        }
    }
}
