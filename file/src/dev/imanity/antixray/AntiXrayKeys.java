// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.JvmName;
import dev.imanity.antixray.a.IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll;
import dev.imanity.antixray.a.IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll;
import dev.imanity.antixray.a.IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl;
import io.fairyproject.data.MetaKey;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR2\u0010\u000b\u001a\u001a\u0012\b\u0012\u0006*\u00020\n0\n*\f\u0012\b\u0012\u0006*\u00020\n0\n0\u00040\u00048\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u000b\u0010\u0007\u001a\u0004\b\f\u0010\tR \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u00048\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u0007\u001a\u0004\b\u000f\u0010\tR \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u00048\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u0007\u001a\u0004\b\u0012\u0010\t" }, d2 = { "Ldev/imanity/antixray/AntiXrayKeys;", "", "<init>", "()V", "Lio/fairyproject/data/MetaKey;", "", "antiXrayBypass", "Lio/fairyproject/data/MetaKey;", "getAntiXrayBypass", "()Lio/fairyproject/data/MetaKey;", "Ldev/imanity/antixray/a/IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll;", "packetQueueKey", "getPacketQueueKey", "Ldev/imanity/antixray/a/IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl;", "raytrace", "getRaytrace", "Ldev/imanity/antixray/a/IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll;", "raytraceWorldKey", "getRaytraceWorldKey" })
public final class AntiXrayKeys
{
    @NotNull
    public static final AntiXrayKeys INSTANCE;
    @NotNull
    private static final MetaKey<Boolean> antiXrayBypass;
    @NotNull
    private static final MetaKey<IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl> raytrace;
    private static final MetaKey<IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll> packetQueueKey;
    @NotNull
    private static final MetaKey<IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll> raytraceWorldKey;
    
    private AntiXrayKeys() {
    }
    
    @NotNull
    @JvmName(name = "getAntiXrayBypass")
    public final MetaKey<Boolean> getAntiXrayBypass() {
        return AntiXrayKeys.antiXrayBypass;
    }
    
    @NotNull
    @JvmName(name = "getRaytrace")
    public final MetaKey<IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl> getRaytrace() {
        return AntiXrayKeys.raytrace;
    }
    
    @JvmName(name = "getPacketQueueKey")
    public final MetaKey<IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll> getPacketQueueKey() {
        return AntiXrayKeys.packetQueueKey;
    }
    
    @NotNull
    @JvmName(name = "getRaytraceWorldKey")
    public final MetaKey<IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll> getRaytraceWorldKey() {
        return AntiXrayKeys.raytraceWorldKey;
    }
    
    static {
        INSTANCE = new AntiXrayKeys();
        final MetaKey boolean1 = MetaKey.createBoolean("anti-xray:bypass");
        Intrinsics.checkNotNullExpressionValue((Object)boolean1, "");
        antiXrayBypass = boolean1;
        final MetaKey create = MetaKey.create("raytrace:player", (Class)IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl.class);
        Intrinsics.checkNotNullExpressionValue((Object)create, "");
        raytrace = create;
        packetQueueKey = MetaKey.create("antixray:packet-queue", (Class)IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll.class);
        final MetaKey create2 = MetaKey.create("anti-xray:world", (Class)IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll.class);
        Intrinsics.checkNotNullExpressionValue((Object)create2, "");
        raytraceWorldKey = create2;
    }
}
