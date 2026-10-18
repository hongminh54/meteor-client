/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.hongminh54.marisaoptimize.MarisaOptimize;
import net.minecraft.server.packs.DownloadQueue;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.nio.file.Path;
import java.util.UUID;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

/**
 * Taken from <a href="https://github.com/CCBlueX/LiquidBounce">LiquidBounce</a>
 * <p>
 * Copyright (c) 2021 - 2025 CCBlueX
 *
 * @author Izuna
 * @see <a href="https://github.com/CCBlueX/LiquidBounce/blob/nextgen/src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/util/MixinDownloader.java">MixinDownloader.java</a>
 */
@Mixin(DownloadQueue.class)
public class DownloaderMixin {
    @Shadow
    @Final
    private Path cacheDir;

    @ModifyExpressionValue(method = "lambda$runDownload$0", at = @At(value = "INVOKE", target = "Ljava/nio/file/Path;resolve(Ljava/lang/String;)Ljava/nio/file/Path;"))
    private Path hookResolve(Path original, @Local(argsOnly = true, name = "id") UUID id) {
        UUID accountId = mc.getUser().getProfileId();
        if (accountId == null) {
            MarisaOptimize.LOG.warn("Failed to change resource pack download directory because the account id is null.");
            return original;
        }

        return cacheDir.resolve(accountId.toString()).resolve(id.toString());
    }
}
