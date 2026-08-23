// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.world.ChunkLoadEvent;
import kotlin.Unit;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import io.fairyproject.container.PreDestroy;
import io.fairyproject.data.MetaStorage;
import java.util.Iterator;
import io.fairyproject.log.Log;
import io.fairyproject.data.MetaKey;
import dev.imanity.antixray.AntiXrayKeys;
import io.fairyproject.mc.data.MCMetadata;
import org.bukkit.World;
import io.fairyproject.container.PostInitialize;
import kotlin.jvm.internal.Intrinsics;
import java.util.concurrent.locks.ReentrantLock;
import org.bukkit.Chunk;
import java.util.concurrent.ConcurrentHashMap;
import dev.imanity.antixray.api.tick.InitialTickProcessor;
import org.jetbrains.annotations.NotNull;
import org.bukkit.Server;
import kotlin.jvm.internal.SourceDebugExtension;
import io.fairyproject.bukkit.listener.RegisterAsListener;
import io.fairyproject.container.InjectableComponent;
import org.bukkit.event.Listener;

@InjectableComponent
@RegisterAsListener
@SourceDebugExtension({ "SMAP\nWorldLoadListener.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorldLoadListener.kt\ndev/imanity/antixray/core/listener/WorldLoadListener\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,152:1\n215#2,2:153\n*S KotlinDebug\n*F\n+ 1 WorldLoadListener.kt\ndev/imanity/antixray/core/listener/WorldLoadListener\n*L\n74#1:153,2\n*E\n" })
public final class IIlllIIIlIIllIIIlllIIIllIllIIIlllIIlIIllIIllIIlllIlIIllIIlIIllIIlIIllIIIllIIlllIIlllIIlIllIlIIIlllIIlIIlllIIllIIlIIllIIlllIIIllIIlIllIIllIIlIIllIllIIllIIllIIIllIllIIIllIIlllIIlIIlllIIIllIllIllIIIllIIllIIlIlllIIIllIlllIIllIIlIlllIllIIIlllIIll implements Listener
{
    @NotNull
    private final Server IlllIlIIllIIllIIIlIIllIllIIlIIlIlllIIllIIlIlllIIIllIIlIIllIIlIIIllIlllIlIIllIIIlIIIlIIIllIIllIIlIlIIIlIIIlIlllIIllIlllIIlIIlIllIIllIIlllIlllIIlIIlIIIlIllIIllIIIllIIllIIIllIIlllIIllIIllIIIlllIlllIIllIlIIlIllIlIlllIIllIIllIIll;
    @NotNull
    private final IllIllIlIIIllIIlIIllIIlllIIlllIIllIlllIIlIIllIIlllIlIllIIIllIllIIlllIlllIIlIIlIllIIllIlllIlllIIlIIlIIlIIlIIlllIllIIlIllIIIlllIIllIIIlIIIlllIlIIIlIlIlIIllIIIlIIllIlIIlIlIllIIlllIIllIIIlllIIllIIlIllIllIIllIllIIlllIIlIll IIllIllIIIlllIllIlllIIlIIIlllIIlIIIlllIllIIIllIIlllIIlIIllIIlIIlIIlllIlllIIlllIlIIlIIllIIllIIlIIllIllIlllIIIllIIIllIIllIllIIllIlIIllIIlIIlllIIIlIIlllIlIIIlllIlIllIllIIllIllIIIllIIlIIllIIlllIllIIllIIlllIIllIIlllIllIIlllIIllIIlIIIllIIIl;
    @NotNull
    private final IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll IIllIIIlIIllIlIllIIIlllIIllIllIllIIlIIIllIIlIIlIIlIIlIllIlllIlllIIlIIlIIlIIllIIIllIlIIlllIIlllIIIlIlllIIlIIlIIIllIIIlIIllIIIlIIlIIIllIlIIllIIllIIllIIlIIlIIllIIIlllIIIlIIIllIllIIlIllIIlIIIllIIIllIIllIIlllIIllIIllIlllIllIIlIIl;
    @NotNull
    private final InitialTickProcessor IlIIlIIlIlIlllIIllIlllIllIIllIIlIIlllIIlIlllIIlllIlllIIlIlllIlIIIlIIllIIlIIlllIIIlIIIllIllIIIlllIIIlIIlIIIlllIIIlIIIllIIlllIIlIllIIlIIllIllIIlIIIlIIllIIlllIIllIlllIIIllIIlllIlIIIllIIllIIlllIlIIllIIIlIIIllIlIlllIIllIIllIIIllIIlIll;
    @NotNull
    private final ConcurrentHashMap<IIIlllIlIIIlllIIllIIllIlllIIIllIIllIIIlllIllIlllIIllIIllIllIlIIlIIllIllIlIllIIlllIIlllIIlllIIllIllIlIllIIllIIlIIIlIIlIlIIIllIllIlllIllIIlIIllIlIllIIllIlllIllIIlllIIlIIlllIlllIIIlIIlIlllIIIlllIIlllIIllIIIlIlllIIlIIllIIllIIIllIIIlll, Chunk> IllIllIIlIlIIlIllIIlIIIlllIIllIllIIlllIllIIlllIIllIIlIllIIllIIllIlllIIlIIlIllIIllIllIIllIlIlIIlIIllIIlIIlIlIIIlllIIlIllIIlllIlIlllIIllIIllIllIIllIIIllIIllIlIIlIllIIIlIllIllIIlIIlIlIIlIlIIllIIIlIllIIIlllIIlll;
    @NotNull
    private final ReentrantLock IIllIIIllIlIIIlllIIlllIllIIlllIIllIlllIllIIllIlIIllIIllIlIIllIllIIllIlllIllIIlllIlllIIllIlIIlllIIllIlIIlIIlllIIlIIIllIIlllIlIllIIlllIIllIIlIlIIlIIllIllIIllIIllIIIlIIIlllIIIllIIIlllIIIllIlllIIllIIlllIlIIllIlIIIllIIllIIIlIllIIllIlll;
    private boolean initialized;
    
    public IIlllIIIlIIllIIIlllIIIllIllIIIlllIIlIIllIIllIIlllIlIIllIIlIIllIIlIIllIIIllIIlllIIlllIIlIllIlIIIlllIIlIIlllIIllIIlIIllIIlllIIIllIIlIllIIllIIlIIllIllIIllIIllIIIllIllIIIllIIlllIIlIIlllIIIllIllIllIIIllIIllIIlIlllIIIllIlllIIllIIlIlllIllIIIlllIIll(@NotNull final Server illlIlIIllIIllIIIlIIllIllIIlIIlIlllIIllIIlIlllIIIllIIlIIllIIlIIIllIlllIlIIllIIIlIIIlIIIllIIllIIlIlIIIlIIIlIlllIIllIlllIIlIIlIllIIllIIlllIlllIIlIIlIIIlIllIIllIIIllIIllIIIllIIlllIIllIIllIIIlllIlllIIllIlIIlIllIlIlllIIllIIllIIll, @NotNull final IllIllIlIIIllIIlIIllIIlllIIlllIIllIlllIIlIIllIIlllIlIllIIIllIllIIlllIlllIIlIIlIllIIllIlllIlllIIlIIlIIlIIlIIlllIllIIlIllIIIlllIIllIIIlIIIlllIlIIIlIlIlIIllIIIlIIllIlIIlIlIllIIlllIIllIIIlllIIllIIlIllIllIIllIllIIlllIIlIll iIllIllIIIlllIllIlllIIlIIIlllIIlIIIlllIllIIIllIIlllIIlIIllIIlIIlIIlllIlllIIlllIlIIlIIllIIllIIlIIllIllIlllIIIllIIIllIIllIllIIllIlIIllIIlIIlllIIIlIIlllIlIIIlllIlIllIllIIllIllIIIllIIlIIllIIlllIllIIllIIlllIIllIIlllIllIIlllIIllIIlIIIllIIIl, @NotNull final IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll iIllIIIlIIllIlIllIIIlllIIllIllIllIIlIIIllIIlIIlIIlIIlIllIlllIlllIIlIIlIIlIIllIIIllIlIIlllIIlllIIIlIlllIIlIIlIIIllIIIlIIllIIIlIIlIIIllIlIIllIIllIIllIIlIIlIIllIIIlllIIIlIIIllIllIIlIllIIlIIIllIIIllIIllIIlllIIllIIllIlllIllIIlIIl, @NotNull final InitialTickProcessor ilIIlIIlIlIlllIIllIlllIllIIllIIlIIlllIIlIlllIIlllIlllIIlIlllIlIIIlIIllIIlIIlllIIIlIIIllIllIIIlllIIIlIIlIIIlllIIIlIIIllIIlllIIlIllIIlIIllIllIIlIIIlIIllIIlllIIllIlllIIIllIIlllIlIIIllIIllIIlllIlIIllIIIlIIIllIlIlllIIllIIllIIIllIIlIll) {
        Intrinsics.checkNotNullParameter((Object)illlIlIIllIIllIIIlIIllIllIIlIIlIlllIIllIIlIlllIIIllIIlIIllIIlIIIllIlllIlIIllIIIlIIIlIIIllIIllIIlIlIIIlIIIlIlllIIllIlllIIlIIlIllIIllIIlllIlllIIlIIlIIIlIllIIllIIIllIIllIIIllIIlllIIllIIllIIIlllIlllIIllIlIIlIllIlIlllIIllIIllIIll, "");
        Intrinsics.checkNotNullParameter((Object)iIllIllIIIlllIllIlllIIlIIIlllIIlIIIlllIllIIIllIIlllIIlIIllIIlIIlIIlllIlllIIlllIlIIlIIllIIllIIlIIllIllIlllIIIllIIIllIIllIllIIllIlIIllIIlIIlllIIIlIIlllIlIIIlllIlIllIllIIllIllIIIllIIlIIllIIlllIllIIllIIlllIIllIIlllIllIIlllIIllIIlIIIllIIIl, "");
        Intrinsics.checkNotNullParameter((Object)iIllIIIlIIllIlIllIIIlllIIllIllIllIIlIIIllIIlIIlIIlIIlIllIlllIlllIIlIIlIIlIIllIIIllIlIIlllIIlllIIIlIlllIIlIIlIIIllIIIlIIllIIIlIIlIIIllIlIIllIIllIIllIIlIIlIIllIIIlllIIIlIIIllIllIIlIllIIlIIIllIIIllIIllIIlllIIllIIllIlllIllIIlIIl, "");
        Intrinsics.checkNotNullParameter((Object)ilIIlIIlIlIlllIIllIlllIllIIllIIlIIlllIIlIlllIIlllIlllIIlIlllIlIIIlIIllIIlIIlllIIIlIIIllIllIIIlllIIIlIIlIIIlllIIIlIIIllIIlllIIlIllIIlIIllIllIIlIIIlIIllIIlllIIllIlllIIIllIIlllIlIIIllIIllIIlllIlIIllIIIlIIIllIlIlllIIllIIllIIIllIIlIll, "");
        this.IlllIlIIllIIllIIIlIIllIllIIlIIlIlllIIllIIlIlllIIIllIIlIIllIIlIIIllIlllIlIIllIIIlIIIlIIIllIIllIIlIlIIIlIIIlIlllIIllIlllIIlIIlIllIIllIIlllIlllIIlIIlIIIlIllIIllIIIllIIllIIIllIIlllIIllIIllIIIlllIlllIIllIlIIlIllIlIlllIIllIIllIIll = illlIlIIllIIllIIIlIIllIllIIlIIlIlllIIllIIlIlllIIIllIIlIIllIIlIIIllIlllIlIIllIIIlIIIlIIIllIIllIIlIlIIIlIIIlIlllIIllIlllIIlIIlIllIIllIIlllIlllIIlIIlIIIlIllIIllIIIllIIllIIIllIIlllIIllIIllIIIlllIlllIIllIlIIlIllIlIlllIIllIIllIIll;
        this.IIllIllIIIlllIllIlllIIlIIIlllIIlIIIlllIllIIIllIIlllIIlIIllIIlIIlIIlllIlllIIlllIlIIlIIllIIllIIlIIllIllIlllIIIllIIIllIIllIllIIllIlIIllIIlIIlllIIIlIIlllIlIIIlllIlIllIllIIllIllIIIllIIlIIllIIlllIllIIllIIlllIIllIIlllIllIIlllIIllIIlIIIllIIIl = iIllIllIIIlllIllIlllIIlIIIlllIIlIIIlllIllIIIllIIlllIIlIIllIIlIIlIIlllIlllIIlllIlIIlIIllIIllIIlIIllIllIlllIIIllIIIllIIllIllIIllIlIIllIIlIIlllIIIlIIlllIlIIIlllIlIllIllIIllIllIIIllIIlIIllIIlllIllIIllIIlllIIllIIlllIllIIlllIIllIIlIIIllIIIl;
        this.IIllIIIlIIllIlIllIIIlllIIllIllIllIIlIIIllIIlIIlIIlIIlIllIlllIlllIIlIIlIIlIIllIIIllIlIIlllIIlllIIIlIlllIIlIIlIIIllIIIlIIllIIIlIIlIIIllIlIIllIIllIIllIIlIIlIIllIIIlllIIIlIIIllIllIIlIllIIlIIIllIIIllIIllIIlllIIllIIllIlllIllIIlIIl = iIllIIIlIIllIlIllIIIlllIIllIllIllIIlIIIllIIlIIlIIlIIlIllIlllIlllIIlIIlIIlIIllIIIllIlIIlllIIlllIIIlIlllIIlIIlIIIllIIIlIIllIIIlIIlIIIllIlIIllIIllIIllIIlIIlIIllIIIlllIIIlIIIllIllIIlIllIIlIIIllIIIllIIllIIlllIIllIIllIlllIllIIlIIl;
        this.IlIIlIIlIlIlllIIllIlllIllIIllIIlIIlllIIlIlllIIlllIlllIIlIlllIlIIIlIIllIIlIIlllIIIlIIIllIllIIIlllIIIlIIlIIIlllIIIlIIIllIIlllIIlIllIIlIIllIllIIlIIIlIIllIIlllIIllIlllIIIllIIlllIlIIIllIIllIIlllIlIIllIIIlIIIllIlIlllIIllIIllIIIllIIlIll = ilIIlIIlIlIlllIIllIlllIllIIllIIlIIlllIIlIlllIIlllIlllIIlIlllIlIIIlIIllIIlIIlllIIIlIIIllIllIIIlllIIIlIIlIIIlllIIIlIIIllIIlllIIlIllIIlIIllIllIIlIIIlIIllIIlllIIllIlllIIIllIIlllIlIIIllIIllIIlllIlIIllIIIlIIIllIlIlllIIllIIllIIIllIIlIll;
        this.IllIllIIlIlIIlIllIIlIIIlllIIllIllIIlllIllIIlllIIllIIlIllIIllIIllIlllIIlIIlIllIIllIllIIllIlIlIIlIIllIIlIIlIlIIIlllIIlIllIIlllIlIlllIIllIIllIllIIllIIIllIIllIlIIlIllIIIlIllIllIIlIIlIlIIlIlIIllIIIlIllIIIlllIIlll = new ConcurrentHashMap<IIIlllIlIIIlllIIllIIllIlllIIIllIIllIIIlllIllIlllIIllIIllIllIlIIlIIllIllIlIllIIlllIIlllIIlllIIllIllIlIllIIllIIlIIIlIIlIlIIIllIllIlllIllIIlIIllIlIllIIllIlllIllIIlllIIlIIlllIlllIIIlIIlIlllIIIlllIIlllIIllIIIlIlllIIlIIllIIllIIIllIIIlll, Chunk>();
        this.IIllIIIllIlIIIlllIIlllIllIIlllIIllIlllIllIIllIlIIllIIllIlIIllIllIIllIlllIllIIlllIlllIIllIlIIlllIIllIlIIlIIlllIIlIIIllIIlllIlIllIIlllIIllIIlIlIIlIIllIllIIllIIllIIIlIIIlllIIIllIIIlllIIIllIlllIIllIIlllIlIIllIlIIIllIIllIIIlIllIIllIlll = new ReentrantLock();
    }
    
    @PostInitialize
    public final void IIllIlllIIIlllIlllIlIIlIIllIIllIIlIIIlIIlIIIlllIllIIIlIIllIIlllIIllIIllIIlIllIIIllIIlllIlllIlIIIlllIIIllIIlllIllIllIIIllIlllIlIIllIllIIIlllIIllIIlIlIlIlIIlllIIllIIlllIIlIIllIIllIIIllIIllIIIllIIlIlllIIllIIllIIIlIIlIIlIllIlIIlIIIlll() {
        this.IlIIlIIlIlIlllIIllIlllIllIIllIIlIIlllIIlIlllIIlllIlllIIlIlllIlIIIlIIllIIlIIlllIIIlIIIllIllIIIlllIIIlIIlIIIlllIIIlIIIllIIlllIIlIllIIlIIllIllIIlIIIlIIllIIlllIIllIlllIIIllIIlllIlIIIllIIllIIlllIlIIllIIIlIIIllIlIlllIIllIIllIIIllIIlIll.addTask(5, IIlllIIIlIIllIIIlllIIIllIllIIIlllIIlIIllIIllIIlllIlIIllIIlIIllIIlIIllIIIllIIlllIIlllIIlIllIlIIIlllIIlIIlllIIllIIlIIllIIlllIIIllIIlIllIIllIIlIIllIllIIllIIllIIIllIllIIIllIIlllIIlIIlllIIIllIllIllIIIllIIllIIlIlllIIIllIlllIIllIIlIlllIllIIIlllIIll::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
    }
    
    @PreDestroy
    public final void IIllIIIllIlIIllIlllIllIIIlllIlIIlllIIlIIIlllIIlllIIIlIIIlIllIIllIllIIIllIIlIIIlllIIlllIIlIlIIllIIllIIllIllIIllIlIIIlIllIIllIIllIIllIIlIlllIllIllIllIIIllIlIIllIIllIIIlllIIlllIIIlIlIIllIIlIIllIlIIlIllIllIIIllIIlIIlIIllIIlllIl() {
        for (final World world : this.IlllIlIIllIIllIIIlIIllIllIIlIIlIlllIIllIIlIlllIIIllIIlIIllIIlIIIllIlllIlIIllIIIlIIIlIIIllIIllIIlIlIIIlIIIlIlllIIllIlllIIlIIlIllIIllIIlllIlllIIlIIlIIIlIllIIllIIIllIIllIIIllIIlllIIllIIllIIIlllIlllIIllIlIIlIllIlIlllIIllIIllIIll.getWorlds()) {
            try {
                final MetaStorage provide = MCMetadata.provide((Object)world);
                Intrinsics.checkNotNullExpressionValue((Object)provide, "");
                final MetaStorage metaStorage = provide;
                metaStorage.ifPresent((MetaKey)AntiXrayKeys.INSTANCE.getRaytraceWorldKey(), IIlllIIIlIIllIIIlllIIIllIllIIIlllIIlIIllIIllIIlllIlIIllIIlIIllIIlIIllIIIllIIlllIIlllIIlIllIlIIIlllIIlIIlllIIllIIlIIllIIlllIIIllIIlIllIIllIIlIIllIllIIllIIllIIIllIllIIIllIIlllIIlIIlllIIIllIllIllIIIllIIllIIlIlllIIIllIlllIIllIIlIlllIllIIIlllIIll::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
            }
            catch (final Throwable t) {
                Log.error("Failed to shutdown world " + world.getName() + '!', t, new Object[0]);
            }
        }
    }
    
    private final void initialize() {
        final Lock lock = this.IIllIIIllIlIIIlllIIlllIllIIlllIIllIlllIllIIllIlIIllIIllIlIIllIllIIllIlllIllIIlllIlllIIllIlIIlllIIllIlIIlIIlllIIlIIIllIIlllIlIllIIlllIIllIIlIlIIlIIllIllIIllIIllIIIlIIIlllIIIllIIIlllIIIllIlllIIllIIlllIlIIllIlIIIllIIllIIIlIllIIllIlll;
        lock.lock();
        try {
            this.initialized = true;
            for (final World world : this.IlllIlIIllIIllIIIlIIllIllIIlIIlIlllIIllIIlIlllIIIllIIlIIllIIlIIIllIlllIlIIllIIIlIIIlIIIllIIllIIlIlIIIlIIIlIlllIIllIlllIIlIIlIllIIllIIlllIlllIIlIIlIIIlIllIIllIIIllIIllIIIllIIlllIIllIIllIIIlllIlllIIllIlIIlIllIlIlllIIllIIllIIll.getWorlds()) {
                try {
                    Intrinsics.checkNotNull((Object)world);
                    this.IllIIlIIllIllIIlllIlllIlIIllIlIllIIllIlllIIllIIIlIIIlllIIllIIllIlIIIlIIllIIlIIIlIllIIllIIlIIllIIIlIIlllIIllIIIlIIlIlIIIlIllIIlIIIllIIlIlIIIlIIIllIIlllIIllIIlllIIlIIIllIIllIIlllIllIllIIlIIllIIlllIIIlllIllIllIIlIIlllIlllIIIlIIIll(world);
                }
                catch (final Throwable t) {
                    Log.error("Failed to initialize world " + world.getName() + '!', t, new Object[0]);
                    t.printStackTrace();
                }
            }
            for (final Map.Entry entry : this.IllIllIIlIlIIlIllIIlIIIlllIIllIllIIlllIllIIlllIIllIIlIllIIllIIllIlllIIlIIlIllIIllIllIIllIlIlIIlIIllIIlIIlIlIIIlllIIlIllIIlllIlIlllIIllIIllIllIIllIIIllIIllIlIIlIllIIIlIllIllIIlIIlIlIIlIlIIllIIIlIllIIIlllIIlll.entrySet()) {
                final IIIlllIlIIIlllIIllIIllIlllIIIllIIllIIIlllIllIlllIIllIIllIllIlIIlIIllIllIlIllIIlllIIlllIIlllIIllIllIlIllIIllIIlIIIlIIlIlIIIllIllIlllIllIIlIIllIlIllIIllIlllIllIIlllIIlIIlllIlllIIIlIIlIlllIIIlllIIlllIIllIIIlIlllIIlIIllIIllIIIllIIIlll iiIlllIlIIIlllIIllIIllIlllIIIllIIllIIIlllIllIlllIIllIIllIllIlIIlIIllIllIlIllIIlllIIlllIIlllIIllIllIlIllIIllIIlIIIlIIlIlIIIllIllIlllIllIIlIIllIlIllIIllIlllIllIIlllIIlIIlllIlllIIIlIIlIlllIIIlllIIlllIIllIIIlIlllIIlIIllIIllIIIllIIIlll = (IIIlllIlIIIlllIIllIIllIlllIIIllIIllIIIlllIllIlllIIllIIllIllIlIIlIIllIllIlIllIIlllIIlllIIlllIIllIllIlIllIIllIIlIIIlIIlIlIIIllIllIlllIllIIlIIllIlIllIIllIlllIllIIlllIIlIIlllIlllIIIlIIlIlllIIIlllIIlllIIllIIIlIlllIIlIIllIIllIIIllIIIlll)entry.getKey();
                final Chunk chunk = (Chunk)entry.getValue();
                if (chunk != null) {
                    this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(iiIlllIlIIIlllIIllIIllIlllIIIllIIllIIIlllIllIlllIIllIIllIllIlIIlIIllIllIlIllIIlllIIlllIIlllIIllIllIlIllIIllIIlIIIlIIlIlIIIllIllIlllIllIIlIIllIlIllIIllIlllIllIIlllIIlIIlllIlllIIIlIIlIlllIIIlllIIlllIIllIIIlIlllIIlIIllIIllIIIllIIIlll.IIllIIIlllIIIlIIlllIllIIllIIlllIllIlllIIIlIIlllIIllIlllIlIllIllIIIllIIllIlIlIIllIIllIlllIIIlllIlllIIllIIlllIIllIllIIlIllIlllIIlllIIIllIIIllIIIlllIIlIIlllIIIlllIIlllIIlllIIlllIIlIllIIIlllIIllIIllIlIIllIIIlIIllIIIlIIlllIIllIllIIlllIIlIllIIllIl(), chunk);
                }
            }
            this.IllIllIIlIlIIlIllIIlIIIlllIIllIllIIlllIllIIlllIIllIIlIllIIllIIllIlllIIlIIlIllIIllIllIIllIlIlIIlIIllIIlIIlIlIIIlllIIlIllIIlllIlIlllIIllIIllIllIIllIIIllIIllIlIIlIllIIIlIllIllIIlIIlIlIIlIlIIllIIIlIllIIIlllIIlll.clear();
            final Unit instance = Unit.INSTANCE;
        }
        finally {
            lock.unlock();
        }
    }
    
    private final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final World world, final Chunk chunk) {
        final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll = (IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll)MCMetadata.provide((Object)world).getOrNull((MetaKey)AntiXrayKeys.INSTANCE.getRaytraceWorldKey());
        if (illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll != null) {
            illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(chunk);
        }
    }
    
    private final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final World world, final Chunk chunk) {
        final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll = (IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll)MCMetadata.provide((Object)world).getOrNull((MetaKey)AntiXrayKeys.INSTANCE.getRaytraceWorldKey());
        if (illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll != null) {
            illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(chunk);
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final ChunkLoadEvent chunkLoadEvent) {
        Intrinsics.checkNotNullParameter((Object)chunkLoadEvent, "");
        final Chunk chunk = chunkLoadEvent.getChunk();
        Intrinsics.checkNotNullExpressionValue((Object)chunk, "");
        final Chunk chunk2 = chunk;
        final World world = chunk2.getWorld();
        Intrinsics.checkNotNullExpressionValue((Object)world, "");
        final World world2 = world;
        final Lock lock = this.IIllIIIllIlIIIlllIIlllIllIIlllIIllIlllIllIIllIlIIllIIllIlIIllIllIIllIlllIllIIlllIlllIIllIlIIlllIIllIlIIlIIlllIIlIIIllIIlllIlIllIIlllIIllIIlIlIIlIIllIllIIllIIllIIIlIIIlllIIIllIIIlllIIIllIlllIIllIIlllIlIIllIlIIIllIIllIIIlIllIIllIlll;
        lock.lock();
        try {
            if (this.initialized) {
                this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(world2, chunk2);
            }
            else {
                this.IllIllIIlIlIIlIllIIlIIIlllIIllIllIIlllIllIIlllIIllIIlIllIIllIIllIlllIIlIIlIllIIllIllIIllIlIlIIlIIllIIlIIlIlIIIlllIIlIllIIlllIlIlllIIllIIllIllIIllIIIllIIllIlIIlIllIIIlIllIllIIlIIlIlIIlIlIIllIIIlIllIIIlllIIlll.put(new IIIlllIlIIIlllIIllIIllIlllIIIllIIllIIIlllIllIlllIIllIIllIllIlIIlIIllIllIlIllIIlllIIlllIIlllIIllIllIlIllIIllIIlIIIlIIlIlIIIllIllIlllIllIIlIIllIlIllIIllIlllIllIIlllIIlIIlllIlllIIIlIIlIlllIIIlllIIlllIIllIIIlIlllIIlIIllIIllIIIllIIIlll(world2, chunk2.getX(), chunk2.getZ()), chunk2);
            }
            final Unit instance = Unit.INSTANCE;
        }
        finally {
            lock.unlock();
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final ChunkUnloadEvent chunkUnloadEvent) {
        Intrinsics.checkNotNullParameter((Object)chunkUnloadEvent, "");
        final Chunk chunk = chunkUnloadEvent.getChunk();
        Intrinsics.checkNotNullExpressionValue((Object)chunk, "");
        final Chunk chunk2 = chunk;
        final World world = chunk2.getWorld();
        Intrinsics.checkNotNullExpressionValue((Object)world, "");
        final World world2 = world;
        final Lock lock = this.IIllIIIllIlIIIlllIIlllIllIIlllIIllIlllIllIIllIlIIllIIllIlIIllIllIIllIlllIllIIlllIlllIIllIlIIlllIIllIlIIlIIlllIIlIIIllIIlllIlIllIIlllIIllIIlIlIIlIIllIllIIllIIllIIIlIIIlllIIIllIIIlllIIIllIlllIIllIIlllIlIIllIlIIIllIIllIIIlIllIIllIlll;
        lock.lock();
        try {
            if (this.initialized) {
                this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(world2, chunk2);
                final Unit instance = Unit.INSTANCE;
            }
            else {
                this.IllIllIIlIlIIlIllIIlIIIlllIIllIllIIlllIllIIlllIIllIIlIllIIllIIllIlllIIlIIlIllIIllIllIIllIlIlIIlIIllIIlIIlIlIIIlllIIlIllIIlllIlIlllIIllIIllIllIIllIIIllIIllIlIIlIllIIIlIllIllIIlIIlIlIIlIlIIllIIIlIllIIIlllIIlll.remove(new IIIlllIlIIIlllIIllIIllIlllIIIllIIllIIIlllIllIlllIIllIIllIllIlIIlIIllIllIlIllIIlllIIlllIIlllIIllIllIlIllIIllIIlIIIlIIlIlIIIllIllIlllIllIIlIIllIlIllIIllIlllIllIIlllIIlIIlllIlllIIIlIIlIlllIIIlllIIlllIIllIIIlIlllIIlIIllIIllIIIllIIIlll(world2, chunk2.getX(), chunk2.getZ()));
            }
        }
        finally {
            lock.unlock();
        }
    }
    
    @EventHandler
    public final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final WorldLoadEvent worldLoadEvent) {
        Intrinsics.checkNotNullParameter((Object)worldLoadEvent, "");
        if (!this.initialized) {
            return;
        }
        final World world = worldLoadEvent.getWorld();
        Intrinsics.checkNotNullExpressionValue((Object)world, "");
        this.IllIIlIIllIllIIlllIlllIlIIllIlIllIIllIlllIIllIIIlIIIlllIIllIIllIlIIIlIIllIIlIIIlIllIIllIIlIIllIIIlIIlllIIllIIIlIIlIlIIIlIllIIlIIIllIIlIlIIIlIIIllIIlllIIllIIlllIIlIIIllIIllIIlllIllIllIIlIIllIIlllIIIlllIllIllIIlIIlllIlllIIIlIIIll(world);
    }
    
    @EventHandler
    public final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final WorldUnloadEvent worldUnloadEvent) {
        Intrinsics.checkNotNullParameter((Object)worldUnloadEvent, "");
        if (!this.initialized) {
            return;
        }
        final World world = worldUnloadEvent.getWorld();
        Intrinsics.checkNotNullExpressionValue((Object)world, "");
        MCMetadata.provide((Object)world).remove((MetaKey)AntiXrayKeys.INSTANCE.getRaytraceWorldKey());
    }
    
    private final void IllIIlIIllIllIIlllIlllIlIIllIlIllIIllIlllIIllIIIlIIIlllIIllIIllIlIIIlIIllIIlIIIlIllIIllIIlIIllIIIlIIlllIIllIIIlIIlIlIIIlIllIIlIIIllIIlIlIIIlIIIllIIlllIIllIIlllIIlIIIllIIllIIlllIllIllIIlIIllIIlllIIIlllIllIllIIlIIlllIlllIIIlIIIll(final World world) {
        if (this.IIllIllIIIlllIllIlllIIlIIIlllIIlIIIlllIllIIIllIIlllIIlIIllIIlIIlIIlllIlllIIlllIlIIlIIllIIllIIlIIllIllIlllIIIllIIIllIIllIllIIllIlIIllIIlIIlllIIIlIIlllIlIIIlllIlIllIllIIllIllIIIllIIlIIllIIlllIllIIllIIlllIIllIIlllIllIIlllIIllIIlIIIllIIIl.IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(world)) {
            Log.warn("World " + world.getName() + " seems to have Spigot or Paper anti-xray enabled, consider disabling it for better performance.", new Object[0]);
        }
        MCMetadata.provide((Object)world).put((MetaKey)AntiXrayKeys.INSTANCE.getRaytraceWorldKey(), (Object)this.IIllIIIlIIllIlIllIIIlllIIllIllIllIIlIIIllIIlIIlIIlIIlIllIlllIlllIIlIIlIIlIIllIIIllIlIIlllIIlllIIIlIlllIIlIIlIIIllIIIlIIllIIIlIIlIIIllIlIIllIIllIIllIIlIIlIIllIIIlllIIIlIIIllIllIIlIllIIlIIIllIIIllIIllIIlllIIllIIllIlllIllIIlIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(world));
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final IIlllIIIlIIllIIIlllIIIllIllIIIlllIIlIIllIIllIIlllIlIIllIIlIIllIIlIIllIIIllIIlllIIlllIIlIllIlIIIlllIIlIIlllIIllIIlIIllIIlllIIIllIIlIllIIllIIlIIllIllIIllIIllIIIllIllIIIllIIlllIIlIIlllIIIllIllIllIIIllIIllIIlIlllIIIllIlllIIllIIlIlllIllIIIlllIIll illlIIIlIIllIIIlllIIIllIllIIIlllIIlIIllIIllIIlllIlIIllIIlIIllIIlIIllIIIllIIlllIIlllIIlIllIlIIIlllIIlIIlllIIllIIlIIllIIlllIIIllIIlIllIIllIIlIIllIllIIllIIllIIIllIllIIIllIIlllIIlIIlllIIIllIllIllIIIllIIllIIlIlllIIIllIlllIIllIIlIlllIllIIIlllIIll) {
        Intrinsics.checkNotNullParameter((Object)illlIIIlIIllIIIlllIIIllIllIIIlllIIlIIllIIllIIlllIlIIllIIlIIllIIlIIllIIIllIIlllIIlllIIlIllIlIIIlllIIlIIlllIIllIIlIIllIIlllIIIllIIlIllIIllIIlIIllIllIIllIIllIIIllIllIIIllIIlllIIlIIlllIIIllIllIllIIIllIIllIIlIlllIIIllIlllIIllIIlIlllIllIIIlllIIll, "");
        illlIIIlIIllIIIlllIIIllIllIIIlllIIlIIllIIllIIlllIlIIllIIlIIllIIlIIllIIIllIIlllIIlllIIlIllIlIIIlllIIlIIlllIIllIIlIIllIIlllIIIllIIlIllIIllIIlIIllIllIIllIIllIIIllIllIIIllIIlllIIlIIlllIIIllIllIllIIIllIIllIIlIlllIIIllIlllIIllIIlIlllIllIIIlllIIll.initialize();
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final MetaStorage metaStorage, final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll) {
        Intrinsics.checkNotNullParameter((Object)metaStorage, "");
        metaStorage.remove((MetaKey)AntiXrayKeys.INSTANCE.getRaytraceWorldKey());
    }
}
