// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import dev.imanity.antixray.config.AntiXrayConfig;
import dev.imanity.antixray.api.obfuscator.ObfuscateStrategy;
import org.bukkit.World;
import kotlin.jvm.internal.Intrinsics;
import dev.imanity.antixray.config.AntiXrayConfigService;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.container.InjectableComponent;
import dev.imanity.antixray.api.obfuscator.ObfuscateStrategyProvider;

@InjectableComponent
public final class IIIllIllIIllIlllIIlIIIllIllIIllIIlIIIllIIllIIlIlllIIlllIlllIIllIlllIIlllIIllIllIllIIlIIlllIIIlIlIllIllIIIlllIIIlIIIllIllIIlIIlllIIlIIllIIIllIIIlllIlllIlIIlIIIlIlIllIIlIIllIIIlIIIllIIIllIIIlllIIllIllIIIllIllIlllIllIIlIIllIIlIlllIIIl implements ObfuscateStrategyProvider
{
    @NotNull
    private final IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll ci;
    @NotNull
    private final IllIlllIIllIIlIIlIlIIIllIIllIIlllIIIlIIlIIlllIIlIlIIIlIlIIIlIIllIlllIIIlllIIllIllIllIIlllIlIllIIlIlIIIlIlIlllIIIlIlllIIlIIllIIlIIllIllIIllIlllIIIllIllIlIIIllIIllIIllIIlIIIlIIlIlIIIllIIllIllIlllIIIllIIIllIIIlllIIlIIIlIlll cj;
    @NotNull
    private final IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll ck;
    @NotNull
    private final AntiXrayConfigService cl;
    
    public IIIllIllIIllIlllIIlIIIllIllIIllIIlIIIllIIllIIlIlllIIlllIlllIIllIlllIIlllIIllIllIllIIlIIlllIIIlIlIllIllIIIlllIIIlIIIllIllIIlIIlllIIlIIllIIIllIIIlllIlllIlIIlIIIlIlIllIIlIIllIIIlIIIllIIIllIIIlllIIllIllIIIllIllIlllIllIIlIIllIIlIlllIIIl(@NotNull final IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll ci, @NotNull final IllIlllIIllIIlIIlIlIIIllIIllIIlllIIIlIIlIIlllIIlIlIIIlIlIIIlIIllIlllIIIlllIIllIllIllIIlllIlIllIIlIlIIIlIlIlllIIIlIlllIIlIIllIIlIIllIllIIllIlllIIIllIllIlIIIllIIllIIllIIlIIIlIIlIlIIIllIIllIllIlllIIIllIIIllIIIlllIIlIIIlIlll cj, @NotNull final IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll ck, @NotNull final AntiXrayConfigService cl) {
        Intrinsics.checkNotNullParameter((Object)ci, "");
        Intrinsics.checkNotNullParameter((Object)cj, "");
        Intrinsics.checkNotNullParameter((Object)ck, "");
        Intrinsics.checkNotNullParameter((Object)cl, "");
        this.ci = ci;
        this.cj = cj;
        this.ck = ck;
        this.cl = cl;
    }
    
    @Override
    public int getOrder() {
        return 0;
    }
    
    @NotNull
    @Override
    public ObfuscateStrategy create(@NotNull final World world) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        return new IllIllIllIlIIllIllIIllIIlllIIlIIlIIIlllIIlllIlllIllIllIIlIIllIIlllIIIllIIlIIlllIIIlIlllIIlIIlllIIlllIIllIIllIIIllIIIlIllIllIllIIlIllIllIllIIllIllIIlllIIllIIlIIllIIlIlIIIllIIlllIIllIIlllIIllIIlIllIlllIlIIIllIIIlIIIlIIlllIlIl(world, this.ci, this.cl.getConfig(world), this.cj.get(world), this.ck);
    }
}
