// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms;

import io.fairyproject.libs.packetevents.PacketEvents;
import java.util.Iterator;
import io.fairyproject.log.Log;
import java.util.NoSuchElementException;
import io.fairyproject.bukkit.reflection.resolver.MethodResolver;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.bukkit.reflection.resolver.FieldResolver;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import io.fairyproject.libs.packetevents.protocol.player.ClientVersion;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bR\u0018\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082.¢\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011" }, d2 = { "Ldev/imanity/antixray/nms/WrappedBlockStateCache;", "", "()V", "cache", "", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "[Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "clientVersion", "Lio/fairyproject/libs/packetevents/protocol/player/ClientVersion;", "kotlin.jvm.PlatformType", "useOptimizedLookup", "", "get", "globalId", "", "init", "", "base" })
@SourceDebugExtension({ "SMAP\nWrappedBlockStateCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WrappedBlockStateCache.kt\ndev/imanity/antixray/nms/WrappedBlockStateCache\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,51:1\n1940#2,14:52\n*S KotlinDebug\n*F\n+ 1 WrappedBlockStateCache.kt\ndev/imanity/antixray/nms/WrappedBlockStateCache\n*L\n28#1:52,14\n*E\n" })
public final class WrappedBlockStateCache
{
    @NotNull
    public static final WrappedBlockStateCache INSTANCE;
    private static final ClientVersion clientVersion;
    private static WrappedBlockState[] cache;
    private static boolean useOptimizedLookup;
    
    private WrappedBlockStateCache() {
    }
    
    public final void init(final boolean b) {
        if (b) {
            try {
                final Object value = new FieldResolver((Class)WrappedBlockState.class).resolve(new String[] { "BY_ID" }).get(null);
                Intrinsics.checkNotNull(value);
                final Map[] array = (Map[])value;
                final Object invoke = new MethodResolver((Class)WrappedBlockState.class).resolve(new String[] { "loadMappings" }).invoke(null, WrappedBlockStateCache.clientVersion);
                Intrinsics.checkNotNull(invoke);
                final Map map = array[(byte)invoke];
                final Iterator iterator = map.keySet().iterator();
                if (!iterator.hasNext()) {
                    throw new NoSuchElementException();
                }
                Object next = iterator.next();
                Object o;
                if (!iterator.hasNext()) {
                    o = next;
                }
                else {
                    int intValue = ((Number)next).intValue();
                    do {
                        final Object next2 = iterator.next();
                        final int intValue2 = ((Number)next2).intValue();
                        if (intValue < intValue2) {
                            next = next2;
                            intValue = intValue2;
                        }
                    } while (iterator.hasNext());
                    o = next;
                }
                final int intValue3 = ((Number)o).intValue();
                System.out.println((Object)("The highest mapping index is " + intValue3));
                int i = 0;
                final int n = intValue3 + 1;
                final WrappedBlockState[] cache = new WrappedBlockState[n];
                while (i < n) {
                    final int j = i;
                    cache[j] = (WrappedBlockState)map.get(j);
                    ++i;
                }
                WrappedBlockStateCache.cache = cache;
                WrappedBlockStateCache.useOptimizedLookup = true;
            }
            catch (final Throwable t) {
                Log.error("Failed to initialize WrappedBlockStateCache, Fallback to normal lookup", t, new Object[0]);
                int k = 0;
                final WrappedBlockState[] cache2 = new WrappedBlockState[0];
                while (k < 0) {
                    cache2[k] = null;
                    ++k;
                }
                WrappedBlockStateCache.cache = cache2;
            }
        }
    }
    
    @NotNull
    public final WrappedBlockState get(final int n) {
        if (!WrappedBlockStateCache.useOptimizedLookup) {
            final WrappedBlockState byGlobalId = WrappedBlockState.getByGlobalId(WrappedBlockStateCache.clientVersion, n, false);
            Intrinsics.checkNotNullExpressionValue((Object)byGlobalId, "");
            return byGlobalId;
        }
        if (n >= 0) {
            WrappedBlockState[] cache;
            if ((cache = WrappedBlockStateCache.cache) == null) {
                Intrinsics.throwUninitializedPropertyAccessException("");
                cache = null;
            }
            if (n < cache.length) {
                WrappedBlockState[] cache2;
                if ((cache2 = WrappedBlockStateCache.cache) == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("");
                    cache2 = null;
                }
                WrappedBlockState byGlobalId2;
                if ((byGlobalId2 = cache2[n]) == null) {
                    Intrinsics.checkNotNullExpressionValue((Object)(byGlobalId2 = WrappedBlockState.getByGlobalId(WrappedBlockStateCache.clientVersion, n, false)), "");
                }
                return byGlobalId2;
            }
        }
        final WrappedBlockState byGlobalId3 = WrappedBlockState.getByGlobalId(WrappedBlockStateCache.clientVersion, n, false);
        Intrinsics.checkNotNullExpressionValue((Object)byGlobalId3, "");
        return byGlobalId3;
    }
    
    static {
        INSTANCE = new WrappedBlockStateCache();
        clientVersion = PacketEvents.getAPI().getServerManager().getVersion().toClientVersion();
    }
}
