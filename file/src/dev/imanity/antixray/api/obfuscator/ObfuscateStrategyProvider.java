// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.obfuscator;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import org.bukkit.World;
import kotlin.jvm.JvmName;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H'¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00078'X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t" }, d2 = { "Ldev/imanity/antixray/api/obfuscator/ObfuscateStrategyProvider;", "", "Lorg/bukkit/World;", "p0", "Ldev/imanity/antixray/api/obfuscator/ObfuscateStrategy;", "create", "(Lorg/bukkit/World;)Ldev/imanity/antixray/api/obfuscator/ObfuscateStrategy;", "", "getOrder", "()I", "order" })
public interface ObfuscateStrategyProvider
{
    @JvmName(name = "getOrder")
    int getOrder();
    
    @Nullable
    ObfuscateStrategy create(@NotNull final World p0);
}
