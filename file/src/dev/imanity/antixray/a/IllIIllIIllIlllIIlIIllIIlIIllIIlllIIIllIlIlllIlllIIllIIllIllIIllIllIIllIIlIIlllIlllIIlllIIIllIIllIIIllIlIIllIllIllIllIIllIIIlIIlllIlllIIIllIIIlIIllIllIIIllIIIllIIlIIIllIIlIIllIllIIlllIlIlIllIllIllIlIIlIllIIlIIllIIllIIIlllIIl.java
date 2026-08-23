// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import io.fairyproject.mc.scheduler.MCSchedulers;
import kotlin.jvm.internal.DefaultConstructorMarker;
import java.util.Iterator;
import java.util.List;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import kotlin.Metadata;
import kotlin.jvm.internal.Lambda;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import dev.imanity.antixray.api.RaytraceDebugger;
import io.fairyproject.mc.util.BlockPosition;
import dev.imanity.antixray.api.block.RaytraceBlock;
import io.fairyproject.log.Log;
import kotlin.jvm.internal.Intrinsics;
import org.bukkit.entity.Player;
import java.util.concurrent.atomic.AtomicBoolean;
import dev.imanity.antixray.api.data.DataProvider;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.jetbrains.annotations.Nullable;
import dev.imanity.antixray.api.Vision;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.internal.SourceDebugExtension;

@SourceDebugExtension({ "SMAP\nRaytracePlayerAsyncContext.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RaytracePlayerAsyncContext.kt\ndev/imanity/antixray/core/player/RaytracePlayerAsyncContext\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 RangeUtil.kt\ndev/imanity/antixray/core/util/RangeUtil\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,265:1\n1#2:266\n13#3,8:267\n1855#4,2:275\n*S KotlinDebug\n*F\n+ 1 RaytracePlayerAsyncContext.kt\ndev/imanity/antixray/core/player/RaytracePlayerAsyncContext\n*L\n119#1:267,8\n255#1:275,2\n*E\n" })
public final class IllIIllIIllIlllIIlIIllIIlIIllIIlllIIIllIlIlllIlllIIllIIllIllIIllIllIIllIIlIIlllIlllIIlllIIIllIIllIIIllIlIIllIllIllIllIIllIIIlIIlllIlllIIIllIIIlIIllIllIIIllIIIllIIlIIIllIIlIIllIllIIlllIlIlIllIllIllIlIIlIllIIlIIllIIllIIIlllIIl
{
    @NotNull
    public static final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll H;
    @NotNull
    private final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl I;
    private int J;
    private int K;
    private int L;
    @Nullable
    private Vision IIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll;
    @NotNull
    private final ConcurrentLinkedQueue<Runnable> M;
    @NotNull
    private final LongOpenHashSet N;
    private IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll IIIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll;
    private DataProvider O;
    private IIllIIIlllIIlllIIllIlIIIlllIIIllIlIIllIIlIllIIlllIIIlIIIlIIIlIIlIIlIIIllIlllIllIIIllIIllIlIlllIllIIllIIlIIlllIlllIIlllIIIlllIIllIlllIlIIllIllIlIIllIIIlIIIlIIIllIIlIIlIIlllIllIlllIlIIllIIIllIIlIIlllIIlIIllIlllIIIlIlIIllIIIlllIIIlIll E;
    @NotNull
    private final AtomicBoolean P;
    @NotNull
    private final AtomicBoolean Q;
    @NotNull
    private final IIllIIlIIllIIIlllIllIIlIIllIIllIIIllIIllIIlIIllIIIlllIIllIIllIllIlIIIlllIIIllIIlllIIIlllIIlllIIllIlIIlIlllIlllIIllIIIlllIIllIIlIIIlIIlllIIIllIIIlllIlIIlIIlIllIIIllIllIIIlllIIIlIlllIllIIllIlIIIllIIIlllIIlIIIlIIlIIlIlIIlIllIIIlllIllIIIllIIll R;
    private boolean S;
    private static final long U = 300000L;
    private static final long V = 600000L;
    private static long IlIIllIllIIlllIIllIIlIIIlIIllIIlIIlllIIlIlIlIIllIIlIIllIIllIIllIllIIllIlllIIIllIIlIIllIllIllIlllIIIllIllIIlIlIlIIllIllIIIllIIlIlIlIIIlllIIlIIllIIlllIIllIllIIIllIlIIllIIlllIllIllIllIIIllIIlIIlIIIlIIIllIlIIIlllIlIIIl;
    
    public IllIIllIIllIlllIIlIIllIIlIIllIIlllIIIllIlIlllIlllIIllIIllIllIIllIllIIllIIlIIlllIlllIIlllIIIllIIllIIIllIlIIllIllIllIllIIllIIIlIIlllIlllIIIllIIIlIIllIllIIIllIIIllIIlIIIllIIlIIllIllIIlllIlIlIllIllIllIlIIlIllIIlIIllIIllIIIlllIIl(@NotNull final Player player, @NotNull final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl i) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)i, "");
        this.I = i;
        this.J = (int)Math.floor(player.getLocation().getX() / 16.0);
        this.K = (int)Math.floor(player.getLocation().getZ() / 16.0);
        this.M = new ConcurrentLinkedQueue<Runnable>();
        this.N = new LongOpenHashSet();
        this.P = new AtomicBoolean(false);
        this.Q = new AtomicBoolean(false);
        this.R = new IIllIIlIIllIIIlllIllIIlIIllIIllIIIllIIllIIlIIllIIIlllIIllIIllIllIlIIIlllIIIllIIlllIIIlllIIlllIIllIlIIlIlllIlllIIllIIIlllIIllIIlIIIlIIlllIIIllIIIlllIlIIlIIlIllIIIllIllIIIlllIIIlIlllIllIIllIlIIIllIIIlllIIlIIIlIIlIIlIlIIlIllIIIlllIllIIIllIIll(this);
    }
    
    @NotNull
    public final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl IIllIIIlllIIllIIlllIIIllIIIllIlllIlIllIllIIlIIllIIlIIllIIIlIlIIlllIIlllIIIllIIllIIllIlIIlIlIllIIlllIIIllIllIIllIlllIllIllIlllIIllIIIllIllIllIllIIIlllIIllIllIIlllIlIlIlllIIlIlIIllIlIIllIlllIIlIIllIIIlIIlIIllIlIIlIllIIIl() {
        return this.I;
    }
    
    @NotNull
    public final ConcurrentLinkedQueue<Runnable> IIlIllIIlIIlllIlIlllIIIllIlIIlIIllIIllIlIIIlIllIIlllIIllIlllIllIIllIlIIllIIIlllIllIllIlIlllIIIlllIIIlIIIlIllIlllIIlIIlIIllIIlIIIllIllIIlIIlllIllIIlIllIIlIIllIlIIIlllIIIlIIlIlIlllIIIllIIlIlIllIIIlIIllIlllIIIllIIllIll() {
        return this.M;
    }
    
    @NotNull
    public final LongOpenHashSet IIllIIlllIIllIIIlllIlIIlIlllIIllIIllIIlIIIlllIIlllIllIIlIIlIlIIlIIIllIIlllIIIllIIlllIIllIIlIlllIlllIIIlIllIlIIlIIIllIIIllIIllIllIIllIllIIIllIIllIIIlllIIlIIlllIlllIIlIIlIIlllIIllIllIIlllIllIIIlIllIIlllIIIllIIIlllIIlIIlIIIlIllIllIllIl() {
        return this.N;
    }
    
    public final void IllIIllIlllIlllIllIIlllIIllIIlIIlllIIlIIlIIIllIlllIllIIIllIllIIlllIIlIIlIIlIIlllIIIlIlIIlllIlllIllIIllIIlllIIIlllIIlllIIlIllIlllIlllIIllIIIllIIllIIlIIIllIIIlllIlllIIlIIlIlllIIIllIIIllIIIlllIlIIIlllIIllIIllIIlllIIIlIIlIllIIIlIlIIllIlIll() {
        while (true) {
            final Runnable poll = this.M.poll();
            final Runnable runnable = poll;
            if (poll == null) {
                break;
            }
            try {
                final Runnable runnable2 = runnable;
                Intrinsics.checkNotNull((Object)runnable2);
                runnable2.run();
            }
            catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll ilIIlllIIlIIllIlIllIIlIIlllIIIllIIIllIllIIlllIIllIIIllIIllIllIIlIIlIIlIllIlllIIllIlllIIllIIllIllIIlllIIIlllIIllIllIlIIlllIIlIIllIIIllIIlIIllIIIlllIlIIllIIllIIIlllIIlIIlIllIlllIlIIlIIllIIlllIIlIlIIlIlllIIllIIlllIIIllIllIIlllIIll = this.I.IlIIlllIIlIIllIlIllIIlIIlllIIIllIIIllIllIIlllIIllIIIllIIllIllIIlIIlIIlIllIlllIIllIlllIIllIIllIllIIlllIIIlllIIllIllIlIIlllIIlIIllIIIllIIlIIllIIIlllIlIIllIIllIIIlllIIlIIlIllIlllIlIIlIIllIIlllIIlIlIIlIlllIIllIIlllIIIllIllIIlllIIll();
        if (ilIIlllIIlIIllIlIllIIlIIlllIIIllIIIllIllIIlllIIllIIIllIIllIllIIlIIlIIlIllIlllIIllIlllIIllIIllIllIIlllIIIlllIIllIllIlIIlllIIlIIllIIIllIIlIIllIIIlllIlIIllIIllIIIlllIIlIIlIllIlllIlIIlIIllIIlllIIlIlIIlIlllIIllIIlllIIIllIllIIlllIIll == null) {
            final IllIIllIIllIlllIIlIIllIIlIIllIIlllIIIllIlIlllIlllIIllIIllIllIIllIllIIllIIlIIlllIlllIIlllIIIllIIllIIIllIlIIllIllIllIllIIllIIIlIIlllIlllIIIllIIIlIIllIllIIIllIIIllIIlIIIllIIlIIllIllIIlllIlIlIllIllIllIlIIlIllIIlIIllIIllIIIlllIIl illIIllIIllIlllIIlIIllIIlIIllIIlllIIIllIlIlllIlllIIllIIllIllIIllIllIIllIIlIIlllIlllIIlllIIIllIIllIIIllIlIIllIllIllIllIIllIIIlIIlllIlllIIIllIIIlIIllIllIIIllIIIllIIlIIIllIIlIIllIllIIlllIlIlIllIllIllIlIIlIllIIlIIllIIllIIIlllIIl = this;
            return;
        }
        this.IIIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll = ilIIlllIIlIIllIlIllIIlIIlllIIIllIIIllIllIIlllIIllIIIllIIllIllIIlIIlIIlIllIlllIIllIlllIIllIIllIllIIlllIIIlllIIllIllIlIIlllIIlIIllIIIllIIlIIllIIIlllIlIIllIIllIIIlllIIlIIlIllIlllIlIIlIIllIIlllIIlIlIIlIlllIIllIIlllIIIllIllIIlllIIll;
        this.O = this.I.IlIIIlllIIIlIIllIIllIIlIIlIIllIIIlIllIIlllIIIlllIIlIllIIlIIlIllIIlIIllIIllIllIIlIIIllIIllIlIIlllIIIllIlIIllIlllIIIlIIllIIlIllIlllIIllIIlIllIllIlIllIIIllIIllIIIlllIIIlllIlllIlIllIlIlllIIllIllIlllIlllIIlIlIIllIlIIIlIIIlll();
        this.E = this.I.IIllIIlIIIlllIIIlIlIIllIIllIlIlllIlIIllIlllIIlIlllIIIlIIIlIIlIIllIlllIllIIIllIllIllIIlIIlIllIllIIIlllIIlllIIlllIIllIllIIlIIIlIIllIIlIIlllIIllIIllIllIIIlIllIIIllIlIllIIlllIIllIIllIIllIIIllIlIIIlllIIlIIllIlllIIllIIllIlllIIlIIll();
        final int n = this.L++;
        IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll;
        if ((iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll = this.IIIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll) == null) {
            Intrinsics.throwUninitializedPropertyAccessException("");
            iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll = null;
        }
        if (n % iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll.IllIllIIIlllIllIIlllIlIllIIllIllIIlIIIlIIlllIIllIlllIIlIlIlIIlIIIllIIlIIIllIIIllIIlIIlIIIllIIlIllIIlllIlIIlllIllIIIllIIllIIlllIIIllIIllIIIllIIIllIIIlllIIlIlIIIlIlllIIIlIIllIlIIlllIlIIllIllIllIlllIIllIIIllIIIllIIlIIlIIIlIIllIll() != 0) {
            return;
        }
        if (this.IIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll != this.I.getVision()) {
            this.IIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll = this.I.getVision();
            this.S = false;
        }
        else {
            if (!this.P.compareAndSet(true, false)) {
                return;
            }
            this.S = true;
        }
        if (!this.Q.compareAndSet(false, true)) {
            return;
        }
        try {
            final IIllIIlIIllIIIlllIllIIlIIllIIllIIIllIIllIIlIIllIIIlllIIllIIllIllIlIIIlllIIIllIIlllIIIlllIIlllIIllIlIIlIlllIlllIIllIIIlllIIllIIlIIIlIIlllIIIllIIIlllIlIIlIIlIllIIIllIllIIIlllIIIlIlllIllIIllIlIIIllIIIlllIIlIIIlIIlIIlIlIIlIllIIIlllIllIIIllIIll r = this.R;
            final Vision iIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll = this.IIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll;
            Intrinsics.checkNotNull((Object)iIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll);
            r.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(iIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll);
            final Vision iIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll2 = this.IIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll;
            Intrinsics.checkNotNull((Object)iIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll2);
            final double x = iIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll2.getOrigin().getPosition().getX();
            final Vision iIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll3 = this.IIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll;
            Intrinsics.checkNotNull((Object)iIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll3);
            final double z = iIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll3.getOrigin().getPosition().getZ();
            final int n2 = (int)Math.floor(x) >> 4;
            final int n3 = (int)Math.floor(z) >> 4;
            final double n4 = x;
            IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll2;
            if ((iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll2 = this.IIIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll) == null) {
                Intrinsics.throwUninitializedPropertyAccessException("");
                iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll2 = null;
            }
            final int n5 = (int)Math.floor(n4 - iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll2.IllIIlIIllIllIIlllIlllIlIIllIlIllIIllIlllIIllIIIlIIIlllIIllIIllIlIIIlIIllIIlIIIlIllIIllIIlIIllIIIlIIlllIIllIIIlIIlIlIIIlIllIIlIIIllIIlIlIIIlIIIllIIlllIIllIIlllIIlIIIllIIllIIlllIllIllIIlIIllIIlllIIIlllIllIllIIlIIlllIlllIIIlIIIll()) >> 4;
            final double n6 = z;
            IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll3;
            if ((iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll3 = this.IIIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll) == null) {
                Intrinsics.throwUninitializedPropertyAccessException("");
                iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll3 = null;
            }
            final int n7 = (int)Math.floor(n6 - iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll3.IllIIlIIllIllIIlllIlllIlIIllIlIllIIllIlllIIllIIIlIIIlllIIllIIllIlIIIlIIllIIlIIIlIllIIllIIlIIllIIIlIIlllIIllIIIlIIlIlIIIlIllIIlIIIllIIlIlIIIlIIIllIIlllIIllIIlllIIlIIIllIIllIIlllIllIllIIlIIllIIlllIIIlllIllIllIIlIIlllIlllIIIlIIIll()) >> 4;
            final double n8 = x;
            IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll4;
            if ((iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll4 = this.IIIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll) == null) {
                Intrinsics.throwUninitializedPropertyAccessException("");
                iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll4 = null;
            }
            final int n9 = (int)Math.floor(n8 + iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll4.IllIIlIIllIllIIlllIlllIlIIllIlIllIIllIlllIIllIIIlIIIlllIIllIIllIlIIIlIIllIIlIIIlIllIIllIIlIIllIIIlIIlllIIllIIIlIIlIlIIIlIllIIlIIIllIIlIlIIIlIIIllIIlllIIllIIlllIIlIIIllIIllIIlllIllIllIIlIIllIIlllIIIlllIllIllIIlIIlllIlllIIIlIIIll()) >> 4;
            final double n10 = z;
            IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll5;
            if ((iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll5 = this.IIIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll) == null) {
                Intrinsics.throwUninitializedPropertyAccessException("");
                iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll5 = null;
            }
            final int n11 = (int)Math.floor(n10 + iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll5.IllIIlIIllIllIIlllIlllIlIIllIlIllIIllIlllIIllIIIlIIIlllIIllIIllIlIIIlIIllIIlIIIlIllIIllIIlIIllIIIlIIlllIIllIIIlIIlIlIIIlIllIIlIIIllIIlIlIIIlIIIllIIlllIIllIIlllIIlIIIllIIllIIlllIllIllIIlIIllIIlllIIIlllIllIllIIlIIlllIlllIIIlIIIll()) >> 4;
            int n12 = n5;
            if (n12 <= n9) {
                while (true) {
                    int n13 = n7;
                    if (n13 <= n11) {
                        while (true) {
                            final dev.imanity.antixray.a.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll illIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll = this.I.IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll(n12, n13);
                            if (illIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll != null) {
                                this.IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll(illIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll);
                            }
                            if (n13 == n11) {
                                break;
                            }
                            ++n13;
                        }
                    }
                    if (n12 == n9) {
                        break;
                    }
                    ++n12;
                }
            }
            this.IllIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll(n2, n3);
            this.I.IIllIIlIIIlllIIIlIlIIllIIllIlIlllIlIIllIlllIIlIlllIIIlIIIlIIlIIllIlllIllIIIllIllIllIIlIIlIllIllIIIlllIIlllIIlllIIllIllIIlIIIlIIllIIlIIlllIIllIIllIllIIIlIllIIIllIlIllIIlllIIllIIllIIllIIIllIlIIIlllIIlIIllIlllIIllIIllIlllIIlIIll().cleanup();
            DataProvider o;
            if ((o = this.O) == null) {
                Intrinsics.throwUninitializedPropertyAccessException("");
                o = null;
            }
            o.cleanup();
        }
        catch (final Throwable t) {
            Log.error("Error while raytracing", t, new Object[0]);
            t.printStackTrace();
        }
        finally {
            this.Q.set(false);
        }
    }
    
    private final void IllIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll(final int j, final int k) {
        if (j == this.J && k == this.K) {
            return;
        }
        IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll;
        if ((iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll = this.IIIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll) == null) {
            Intrinsics.throwUninitializedPropertyAccessException("");
            iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll = null;
        }
        final int n = (int)Math.floor(iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll.IllIIlIIllIllIIlllIlllIlIIllIlIllIIllIlllIIllIIIlIIIlllIIllIIllIlIIIlIIllIIlIIIlIllIIllIIlIIllIIIlIIlllIIllIIIlIIlIlIIIlIllIIlIIIllIIlIlIIIlIIIllIIlllIIllIIlllIIlIIIllIIllIIlllIllIllIIlIIllIIlllIIIlllIllIllIIlIIlllIlllIIIlIIIll()) >> 4;
        final IIIlllIlIIlllIIlllIlIlIlIlllIIIllIIIllIIlIlIIIllIlIlIIllIIllIIlllIIllIlllIIIllIIIlIIIlIIllIIllIIIlIllIIllIIIllIIlllIllIIIllIIllIIIllIlIIIllIIIlllIllIlIlIIIlllIIlllIIllIIlllIIllIlIIlllIIIlllIIllIIlIIllIlIllIIIllIlllIIlllIlIlIIllIlll instance = IIIlllIlIIlllIIlllIlIlIlIlllIIIllIIIllIIlIlIIIllIlIlIIllIIllIIlllIIllIlllIIIllIIIlIIIlIIllIIllIIIlIllIIllIIIllIIlllIllIIIllIIllIIIllIlIIIllIIIlllIllIlIlIIIlllIIlllIIllIIlllIIllIlIIlllIIIlllIIllIIlIIllIlIllIIIllIlllIIlllIlIlIIllIlll.INSTANCE;
        final int i = this.J;
        final int l = this.K;
        int n2 = i - n;
        final int n3 = i + n;
        if (n2 <= n3) {
            while (true) {
                int n4 = l - n;
                final int n5 = l + n;
                if (n4 <= n5) {
                    while (true) {
                        if (Math.abs(n2 - j) > n || Math.abs(n4 - k) > n) {
                            final dev.imanity.antixray.a.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll illIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll = this.I.IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll(n2, n4);
                            if (illIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll != null) {
                                this.IIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll(illIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll);
                            }
                        }
                        if (n4 == n5) {
                            break;
                        }
                        ++n4;
                    }
                }
                if (n2 == n3) {
                    break;
                }
                ++n2;
            }
        }
        this.J = j;
        this.K = k;
    }
    
    private final void IIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll(final dev.imanity.antixray.a.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll) {
        final RaytraceBlock[] iIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll = illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.IIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll();
        for (int i = 0; i < iIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll.length; ++i) {
            final RaytraceBlock raytraceBlock = iIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll[i];
            if (raytraceBlock.getVisible()) {
                raytraceBlock.setVisible(false);
                final BlockPosition blockPosition = raytraceBlock.getBlockPosition();
                this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(raytraceBlock, raytraceBlock.getChunk().getBlockDataAt(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ()), true);
            }
        }
    }
    
    private final void IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll(final dev.imanity.antixray.a.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll) {
        final RaytraceBlock[] iIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll = illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.IIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll();
        for (int i = 0; i < iIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll.length; ++i) {
            final RaytraceBlock raytraceBlock = iIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll[i];
            if (!this.S || !raytraceBlock.getInitialized()) {
                raytraceBlock.setInitialized(true);
                final boolean visible = raytraceBlock.getVisible();
                final BlockPosition blockPosition = raytraceBlock.getBlockPosition();
                final WrappedBlockState blockData = raytraceBlock.getChunk().getBlockDataAt(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
                if (RaytraceDebugger.INSTANCE.isActive()) {
                    RaytraceDebugger.INSTANCE.debug(this.I.getPlayer(), blockPosition.getX(), blockPosition.getY(), blockPosition.getZ(), (Function1<? super Player, Unit>)new Function1<Player, Unit>() {
                        final /* synthetic */ IllIIllIIllIlllIIlIIllIIlIIllIIlllIIIllIlIlllIlllIIllIIllIllIIllIllIIllIIlIIlllIlllIIlllIIIllIIllIIIllIlIIllIllIllIllIIllIIIlIIlllIlllIIIllIIIlIIllIllIIIllIIIllIIlIIIllIIlIIllIllIIlllIlIlIllIllIllIlIIlIllIIlIIllIIllIIIlllIIl W;
                        
                        public final void IllIlllIIllIIlIIlIlIIIllIIllIIlllIIIlIIlIIlllIIlIlIIIlIlIIIlIIllIlllIIIlllIIllIllIllIIlllIlIllIIlIlIIIlIlIlllIIIlIlllIIlIIllIIlIIllIllIIllIlllIIIllIllIlIIIllIIllIIllIIlIIIlIIlIlIIIllIIllIllIlllIIIllIIIllIIIlllIIlIIIlIlll(@NotNull final Player player) {
                            Intrinsics.checkNotNullParameter((Object)player, "");
                            this.W.IIllIIIlllIIllIIlllIIIllIIIllIlllIlIllIllIIlIIllIIlIIllIIIlIlIIlllIIlllIIIllIIllIIllIlIIlIlIllIIlllIIIllIllIIllIlllIllIllIlllIIllIIIllIllIllIllIIIlllIIllIllIIlllIlIlIlllIIlIlIIllIlIIllIlllIIlIIllIIIlIIlIIllIlIIlIllIIIl().getPlayer().sendMessage("§c§l[AntiXray] §7Block: §f" + blockPosition.getX() + ", " + blockPosition.getY() + ", " + blockPosition.getZ() + " §7State: §f" + blockData);
                        }
                    });
                }
                if (raytraceBlock.getPendingRemoval()) {
                    if (!visible) {
                        raytraceBlock.setVisible(true);
                        this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(raytraceBlock, blockPosition, blockData, false);
                    }
                    this.I.removeTraceBlock(raytraceBlock.getChunk(), blockPosition);
                    if (RaytraceDebugger.INSTANCE.isActive()) {
                        RaytraceDebugger.INSTANCE.debug(this.I.getPlayer(), blockPosition.getX(), blockPosition.getY(), blockPosition.getZ(), (Function1<? super Player, Unit>)new Function1<Player, Unit>() {
                            final /* synthetic */ IllIIllIIllIlllIIlIIllIIlIIllIIlllIIIllIlIlllIlllIIllIIllIllIIllIllIIllIIlIIlllIlllIIlllIIIllIIllIIIllIlIIllIllIllIllIIllIIIlIIlllIlllIIIllIIIlIIllIllIIIllIIIllIIlIIIllIIlIIllIllIIlllIlIlIllIllIllIlIIlIllIIlIIllIIllIIIlllIIl Z;
                            
                            public final void IIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll(@NotNull final Player player) {
                                Intrinsics.checkNotNullParameter((Object)player, "");
                                this.Z.IIllIIIlllIIllIIlllIIIllIIIllIlllIlIllIllIIlIIllIIlIIllIIIlIlIIlllIIlllIIIllIIllIIllIlIIlIlIllIIlllIIIllIllIIllIlllIllIllIlllIIllIIIllIllIllIllIIIlllIIllIllIIlllIlIlIlllIIlIlIIllIlIIllIlllIIlIIllIIIlIIlIIllIlIIlIllIIIl().getPlayer().sendMessage("§c§l[AntiXray] §7Removed: §f" + blockPosition.getX() + ", " + blockPosition.getY() + ", " + blockPosition.getZ());
                            }
                        });
                    }
                }
                else {
                    final boolean checkVisibility = raytraceBlock.checkVisibility(this.L, (Function1<? super RaytraceBlock, Boolean>)this.R);
                    if (visible != checkVisibility) {
                        IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll;
                        if ((iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll = this.IIIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll) == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("");
                            iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll = null;
                        }
                        if (iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll.IllIlllIIllIIlIIlIlIIIllIIllIIlllIIIlIIlIIlllIIlIlIIIlIlIIIlIIllIlllIIIlllIIllIllIllIIlllIlIllIIlIlIIIlIlIlllIIIlIlllIIlIIllIIlIIllIllIIllIlllIIIllIllIlIIIllIIllIIllIIlIIIlIIlIlIIIllIIllIllIlllIIIllIIIllIIIlllIIlIIIlIlll() && blockData.getGlobalId() != raytraceBlock.getOriginal().getGlobalId()) {
                            raytraceBlock.setPendingRemoval(true);
                        }
                    }
                    if (checkVisibility) {
                        if (RaytraceDebugger.INSTANCE.isActive()) {
                            RaytraceDebugger.INSTANCE.debug(this.I.getPlayer(), blockPosition.getX(), blockPosition.getY(), blockPosition.getZ(), (Function1<? super Player, Unit>)new Function1<Player, Unit>() {
                                final /* synthetic */ IllIIllIIllIlllIIlIIllIIlIIllIIlllIIIllIlIlllIlllIIllIIllIllIIllIllIIllIIlIIlllIlllIIlllIIIllIIllIIIllIlIIllIllIllIllIIllIIIlIIlllIlllIIIllIIIlIIllIllIIIllIIIllIIlIIIllIIlIIllIllIIlllIlIlIllIllIllIlIIlIllIIlIIllIIllIIIlllIIl ac;
                                
                                public final void IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll(@NotNull final Player player) {
                                    Intrinsics.checkNotNullParameter((Object)player, "");
                                    this.ac.IIllIIIlllIIllIIlllIIIllIIIllIlllIlIllIllIIlIIllIIlIIllIIIlIlIIlllIIlllIIIllIIllIIllIlIIlIlIllIIlllIIIllIllIIllIlllIllIllIlllIIllIIIllIllIllIllIIIlllIIllIllIIlllIlIlIlllIIlIlIIllIlIIllIlllIIlIIllIIIlIIlIIllIlIIlIllIIIl().getPlayer().sendMessage("§c§l[AntiXray] §aVisible: §f" + blockPosition.getX() + ", " + blockPosition.getY() + ", " + blockPosition.getZ());
                                }
                            });
                        }
                        this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(raytraceBlock, blockPosition, blockData, visible);
                    }
                    else {
                        if (RaytraceDebugger.INSTANCE.isActive()) {
                            RaytraceDebugger.INSTANCE.debug(this.I.getPlayer(), blockPosition.getX(), blockPosition.getY(), blockPosition.getZ(), (Function1<? super Player, Unit>)new Function1<Player, Unit>() {
                                final /* synthetic */ IllIIllIIllIlllIIlIIllIIlIIllIIlllIIIllIlIlllIlllIIllIIllIllIIllIllIIllIIlIIlllIlllIIlllIIIllIIllIIIllIlIIllIllIllIllIIllIIIlIIlllIlllIIIllIIIlIIllIllIIIllIIIllIIlIIIllIIlIIllIllIIlllIlIlIllIllIllIlIIlIllIIlIIllIIllIIIlllIIl ae;
                                
                                public final void IIlIIlIllIIllIIllIIIlIIIlllIIIlIIlllIIIlIIllIIlIIlIIllIIIllIlllIIIlllIIlIlIIIllIIlllIIlIlllIIllIIlIIllIIlIllIllIIlIIllIIllIIllIIlllIIlIIllIlIIllIlIIllIlllIllIlllIIIlIIllIIlllIlIllIIlIllIIlIIllIIIllIllIIlIIIlIllIllIlllIll(@NotNull final Player player) {
                                    Intrinsics.checkNotNullParameter((Object)player, "");
                                    this.ae.IIllIIIlllIIllIIlllIIIllIIIllIlllIlIllIllIIlIIllIIlIIllIIIlIlIIlllIIlllIIIllIIllIIllIlIIlIlIllIIlllIIIllIllIIllIlllIllIllIlllIIllIIIllIllIllIllIIIlllIIllIllIIlllIlIlIlllIIlIlIIllIlIIllIlllIIlIIllIIIlIIlIIllIlIIlIllIIIl().getPlayer().sendMessage("§c§l[AntiXray] §cNot visible: §f" + blockPosition.getX() + ", " + blockPosition.getY() + ", " + blockPosition.getZ());
                                }
                            });
                        }
                        this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(raytraceBlock, blockData, visible);
                    }
                }
            }
        }
    }
    
    private final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final RaytraceBlock raytraceBlock, final BlockPosition blockPosition, final WrappedBlockState wrappedBlockState, final boolean b) {
        raytraceBlock.setVisible(true);
        if (!b) {
            this.I.IllIIIlIllIIIlllIIllIIllIIllIIllIIlIIlllIIIlIllIIlllIlllIIlIlIIIllIIlIllIIIlllIIIlIIlIllIIIllIIllIlllIIlllIIlIlllIIllIIlIIllIIlllIlllIIIlIIllIIIlIIIlllIlllIllIlllIllIlllIIIllIIIlllIllIIllIllIllIlllIIllIIlllIIlllIlIlIIlIIIlIIlIIlIl().IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(new IIIlllIllIllIllIIllIllIIIllIIllIlIIIllIIlIllIllIIlIIIllIIIlIIlllIlIIIllIlIIlllIIlIIlllIlIIlIIIllIIllIIlIIlllIIllIllIllIIIllIllIlIlllIIlIllIlIllIIIlllIIlIIlllIIlIIllIIIlllIlllIIlIIllIIlllIllIllIIlIIllIIllIllIIIlIIlIIIllIIl(raytraceBlock, wrappedBlockState, true));
        }
        IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll;
        if ((iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll = this.IIIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll) == null) {
            Intrinsics.throwUninitializedPropertyAccessException("");
            iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll = null;
        }
        if (iiIlllIIlllIIllIIlllIIlllIIIllIIllIIlIIlIlIlIllIlIIllIlIlllIIIlIIIllIlIIIllIIIllIIIllIIllIIllIIlllIIlIIllIIllIlIIllIIllIIlIIIlllIIllIIllIIlIllIlIIlllIIIlIllIIlllIIIllIIllIllIlllIIllIlllIIlIIIlllIIIllIllIIlllIllIIIlllIllIIllIlllIIIlllIlll.IllIllIllIlllIIlllIIlllIIlIIlllIIlIIllIIllIIllIIIlIllIIllIlllIlIIllIIlllIIlIIIlIIlIIllIlllIIlIIIlIIIlIllIlIIllIIllIIlllIIllIIlIlllIlllIIIlIIIlllIIllIIIlllIIllIllIIlllIIllIIllIIllIlIIllIllIIlllIIllIIlIIllIlIIIlllIIlIllIIlllIllIIl()) {
            this.I.removeTraceBlock(raytraceBlock.getChunk(), blockPosition);
        }
    }
    
    private final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final RaytraceBlock raytraceBlock, final WrappedBlockState wrappedBlockState, final boolean b) {
        raytraceBlock.setVisible(false);
        if (b) {
            this.I.IllIIIlIllIIIlllIIllIIllIIllIIllIIlIIlllIIIlIllIIlllIlllIIlIlIIIllIIlIllIIIlllIIIlIIlIllIIIllIIllIlllIIlllIIlIlllIIllIIlIIllIIlllIlllIIIlIIllIIIlIIIlllIlllIllIlllIllIlllIIIllIIIlllIllIIllIllIllIlllIIllIIlllIIlllIlIlIIlIIIlIIlIIlIl().IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(new IIIlllIllIllIllIIllIllIIIllIIllIlIIIllIIlIllIllIIlIIIllIIIlIIlllIlIIIllIlIIlllIIlIIlllIlIIlIIIllIIllIIlIIlllIIllIllIllIIIllIllIlIlllIIlIllIlIllIIIlllIIlIIlllIIlIIllIIIlllIlllIIlIIllIIlllIllIllIIlIIllIIllIllIIIlIIlIIIllIIl(raytraceBlock, wrappedBlockState, false));
        }
    }
    
    public final boolean IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Vision vision, @NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)vision, "");
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        IIllIIIlllIIlllIIllIlIIIlllIIIllIlIIllIIlIllIIlllIIIlIIIlIIIlIIlIIlIIIllIlllIllIIIllIIllIlIlllIllIIllIIlIIlllIlllIIlllIIIlllIIllIlllIlIIllIllIlIIllIIIlIIIlIIIllIIlIIlIIlllIllIlllIlIIllIIIllIIlIIlllIIlIIllIlllIIIlIlIIllIIIlllIIIlIll e;
        if ((e = this.E) == null) {
            Intrinsics.throwUninitializedPropertyAccessException("");
            e = null;
        }
        return e.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(vision, blockPosition);
    }
    
    public final void IIlIIlIIllIlllIIlIlIllIIlIIllIIllIIIlIIlllIllIlIllIlIllIIllIIlIIllIIlIlIIIlllIIIlIIllIlIIllIIllIllIIllIllIlIlllIlIllIIIlllIIIlIIIlllIllIIIlIIIllIlllIIllIIlllIllIIlIIllIIlIIlIIIlIIllIIlIIllIIlIIlllIIlIIlllIIIllIIllIIl() {
        this.P.set(true);
    }
    
    public final void IIIlIIlIIlIIlIIlIlIIllIIlllIIIllIIIllIIllIIIllIIllIIlllIIIllIIllIIlIllIIlllIIlIIllIIIllIIIlllIlllIllIIlIllIIIlllIIIlIIllIllIIlIIlllIIllIIIllIIllIlIIllIllIIIlIIIllIIllIIllIIIlllIIIllIlIIIlIlIlIIIllIIlIIlllIIlllIIllIIllIllIIlllIIIllIIlllIIlll() {
        this.J = 0;
        this.K = 0;
        this.L = 0;
    }
    
    private static final void IllIIIlllIllIIIllIIIlIlIIIlllIlIIlllIIllIIlllIIlllIllIIllIIIlIIlllIIIlIIllIllIlllIIIllIIlIllIllIlIIllIlllIllIIllIIIlIllIIlIIllIIIlIllIIlIllIIIllIIlIlIIIllIIlIIlIIllIIIlIIlllIlllIIlIIllIIIlIllIIlIIlIIlIIlllIIIllIIllIIlIIIllIIl() {
        try {
            final long ilIIlllIIllIIllIIllIIIlllIIllIllIllIIlllIIIlllIIlIllIIlllIIlllIIlllIIlllIlIIIllIIlllIllIIIlllIIIllIllIIIllIlIlllIIlllIIlllIlIlllIIllIIIllIIIlllIlllIIlIllIIIllIIlIIllIIllIIllIIllIIlllIlIIIlIIllIIlIIlllIlllIIIllIIllIIllIIllIlIIllIIIlllIlllIIlIll = IIllIIllIIlIIllIIllIIlllIIlllIllIIIllIIIlllIIlIlIIlIIllIlIIllIllIIlIIllIllIlIIllIllIlllIIIlIIIllIIllIIlIllIIIllIlIIllIIllIIllIlIIlllIIlIIIlllIIIlllIIllIIlllIIIlllIIIllIIIllIIllIIllIIllIlllIlIIIlllIIlIIIlIlllIIllIIllIIllIIIlIIllIIlllIlll.cR.IlIIlllIIllIIllIIllIIIlllIIllIllIllIIlllIIIlllIIlIllIIlllIIlllIIlllIIlllIlIIIllIIlllIllIIIlllIIIllIllIIIllIlIlllIIlllIIlllIlIlllIIllIIIllIIIlllIlllIIlIllIIIllIIlIIllIIllIIllIIllIIlllIlIIIlIIllIIlIIlllIlllIIIllIIllIIllIIllIlIIllIIIlllIlllIIlIll();
            if (IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.IIllIllIIIllIIllIIIlIllIllIIlIllIlIlllIllIIIllIIIlllIlllIlIIIlIllIlIllIlIllIIIlIIllIllIlIllIlllIIIllIIllIlllIIllIIlIllIIIlllIIIllIIllIIllIIllIIIlllIIlIIIlllIIIlllIlllIllIllIIlIlIIIllIlIllIlIIllIllIlllIllIlllIIlIIlllIlll() && ilIIlllIIllIIllIIllIIIlllIIllIllIllIIlllIIIlllIIlIllIIlllIIlllIIlllIIlllIlIIIllIIlllIllIIIlllIIIllIllIIIllIlIlllIIlllIIlllIlIlllIIllIIIllIIIlllIlllIIlIllIIIllIIlIIllIIllIIllIIllIIlllIlIIIlIIllIIlIIlllIlllIIIllIIllIIllIIllIlIIllIIIlllIlllIIlIll - IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.IIlllIIIlIlllIIlIIllIllIlllIIllIlIllIlIIllIlllIIllIllIlllIlllIIllIIllIIIllIIIllIIIlIIllIllIIIllIIllIIlIlllIIlIIllIIIllIlIllIIIllIIIllIIllIlllIIIlIIllIIIlllIIllIIllIIIllIlIIIlllIIllIIllIIlllIIlllIlllIlIIllIlIIIllIIlllIllIllIIIllIIlllIlll() > 300000L && ilIIlllIIllIIllIIllIIIlllIIllIllIllIIlllIIIlllIIlIllIIlllIIlllIIlllIIlllIlIIIllIIlllIllIIIlllIIIllIllIIIllIlIlllIIlllIIlllIlIlllIIllIIIllIIIlllIlllIIlIllIIIllIIlIIllIIllIIllIIllIIlllIlIIIlIIllIIlIIlllIlllIIIllIIllIIllIIllIlIIllIIIlllIlllIIlIll - IllIIllIIllIlllIIlIIllIIlIIllIIlllIIIllIlIlllIlllIIllIIllIllIIllIllIIllIIlIIlllIlllIIlllIIIllIIllIIIllIlIIllIllIllIllIIllIIIlIIlllIlllIIIllIIIlIIllIllIIIllIIIllIIlIIIllIIlIIllIllIIlllIlIlIllIllIllIlIIlIllIIlIIllIIllIIIlllIIl.IlIIllIllIIlllIIllIIlIIIlIIllIIlIIlllIIlIlIlIIllIIlIIllIIllIIllIllIIllIlllIIIllIIlIIllIllIllIlllIIIllIllIIlIlIlIIllIllIIIllIIlIlIlIIIlllIIlIIllIIlllIIllIllIIIllIlIIllIIlllIllIllIllIIIllIIlIIlIIIlIIIllIlIIIlllIlIIIl >= 600000L) {
                final List<Player> illIllIlllIIlIIlllIllIIlIIlllIIlIIlllIIllIIlIlIIllIIIllIIllIIllIIIlIIlIlIllIIIllIlllIllIIIlIIIllIIIllIIlIIlIIlIIllIIllIllIIlIIIllIIlIllIIlIIlIIllIlIIIllIllIIIllIIllIIIllIllIIlllIIllIIllIllIIlllIIllIIlllIlIIlIIIllIIlIIlIIl = IllIllIIIlIIIlllIIllIIlIIlllIIIlIlIIllIIlIIIlIIllIIIllIIlIIIllIIlIlllIlllIllIIIlIIllIIIllIIlIllIIIllIIIllIlIIIlIIllIlIlIIlllIIIllIlIllIIlllIIIlIllIllIlIlIIIllIIllIIllIIlIllIIlIlIIlIIllIIllIlIlllIIIllIIlllIlIIlIIlIIIlll.INSTANCE.IllIllIlllIIlIIlllIllIIlIIlllIIlIIlllIIllIIlIlIIllIIIllIIllIIllIIIlIIlIlIllIIIllIlllIllIIIlIIIllIIIllIIlIIlIIlIIllIIllIllIIlIIIllIIlIllIIlIIlIIllIlIIIllIllIIIllIIllIIIllIllIIlllIIllIIllIllIIlllIIllIIlllIlIIlIIIllIIlIIlIIl();
                if (illIllIlllIIlIIlllIllIIlIIlllIIlIIlllIIllIIlIlIIllIIIllIIllIIllIIIlIIlIlIllIIIllIlllIllIIIlIIIllIIIllIIlIIlIIlIIllIIllIllIIlIIIllIIlIllIIlIIlIIllIlIIIllIllIIIllIIllIIIllIllIIlllIIllIIllIllIIlllIIllIIlllIlIIlIIIllIIlIIlIIl.size() > 2) {
                    final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll h = IllIIllIIllIlllIIlIIllIIlIIllIIlllIIIllIlIlllIlllIIllIIllIllIIllIllIIllIIlIIlllIlllIIlllIIIllIIllIIIllIlIIllIllIllIllIIllIIIlIIlllIlllIIIllIIIlIIllIllIIIllIIIllIIlIIIllIIlIIllIllIIlllIlIlIllIllIllIlIIlIllIIlIIllIIllIIIlllIIl.H;
                    IllIIllIIllIlllIIlIIllIIlIIllIIlllIIIllIlIlllIlllIIllIIllIllIIllIllIIllIIlIIlllIlllIIlllIIIllIIllIIIllIlIIllIllIllIllIIllIIIlIIlllIlllIIIllIIIlIIllIllIIIllIIIllIIlIIIllIIlIIllIllIIlllIlIlIllIllIllIlIIlIllIIlIIllIIllIIIlllIIl.IlIIllIllIIlllIIllIIlIIIlIIllIIlIIlllIIlIlIlIIllIIlIIllIIllIIllIllIIllIlllIIIllIIlIIllIllIllIlllIIIllIllIIlIlIlIIllIllIIIllIIlIlIlIIIlllIIlIIllIIlllIIllIllIIIllIlIIllIIlllIllIllIllIIIllIIlIIlIIIlIIIllIlIIIlllIlIIIl = ilIIlllIIllIIllIIllIIIlllIIllIllIllIIlllIIIlllIIlIllIIlllIIlllIIlllIIlllIlIIIllIIlllIllIIIlllIIIllIllIIIllIlIlllIIlllIIlllIlIlllIIllIIIllIIIlllIlllIIlIllIIIllIIlIIllIIllIIllIIllIIlllIlIIIlIIllIIlIIlllIlllIIIllIIllIIllIIllIlIIllIIIlllIlllIIlIll;
                    for (final Player player : illIllIlllIIlIIlllIllIIlIIlllIIlIIlllIIllIIlIlIIllIIIllIIllIIllIIIlIIlIlIllIIIllIlllIllIIIlIIIllIIIllIIlIIlIIlIIllIIllIllIIlIIIllIIlIllIIlIIlIIllIlIIIllIllIIIllIIllIIIllIllIIlllIIllIIllIllIIlllIIllIIlllIlIIlIIIllIIlIIlIIl) {
                        final IllIllIIIlIIIlllIIllIIlIIlllIIIlIlIIllIIlIIIlIIllIIIllIIlIIIllIIlIlllIlllIllIIIlIIllIIIllIIlIllIIIllIIIllIlIIIlIIllIlIlIIlllIIIllIlIllIIlllIIIlIllIllIlIlIIIllIIllIIllIIlIllIIlIlIIlIIllIIllIlIlllIIIllIIlllIlIIlIIlIIIlll instance = IllIllIIIlIIIlllIIllIIlIIlllIIIlIlIIllIIlIIIlIIllIIIllIIlIIIllIIlIlllIlllIllIIIlIIllIIIllIIlIllIIIllIIIllIlIIIlIIllIlIlIIlllIIIllIlIllIIlllIIIlIllIllIlIlIIIllIIllIIllIIlIllIIlIlIIlIIllIIllIlIlllIIIllIIlllIlIIlIIlIIIlll.INSTANCE;
                        final Player player2 = player;
                        final String string = new IIIlllIlllIIIlIIIlIIllIlllIIllIIllIIIllIIIlllIIIllIllIIlllIIIlllIlllIllIIIllIIllIllIlIllIIlllIIlIIlllIlIlllIIlIllIlIIIlIIllIllIlIlIIlIlllIIIllIlllIIlllIIIlIlIIIlIIllIIlllIIlllIlllIIllIlllIIlllIIlllIIllIIlIllIlIllIIlllIIIlIlllIIlIlll().toString();
                        Intrinsics.checkNotNullExpressionValue((Object)string, "");
                        instance.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(player2, string);
                    }
                }
            }
        }
        catch (final Throwable t) {}
    }
    
    static {
        H = new IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(null);
        MCSchedulers.getAsyncScheduler().scheduleAtFixedRate(IllIIllIIllIlllIIlIIllIIlIIllIIlllIIIllIlIlllIlllIIllIIllIllIIllIllIIllIIlIIlllIlllIIlllIIIllIIllIIIllIlIIllIllIllIllIIllIIIlIIlllIlllIIIllIIIlIIllIllIIIllIIIllIIlIIIllIIlIIllIllIIlllIlIlIllIllIllIlIIlIllIIlIIllIIllIIIlllIIl::IllIIIlllIllIIIllIIIlIlIIIlllIlIIlllIIllIIlllIIlllIllIIllIIIlIIlllIIIlIIllIllIlllIIIllIIlIllIllIlIIllIlllIllIIllIIIlIllIIlIIllIIIlIllIIlIllIIIllIIlIlIIIllIIlIIlIIllIIIlIIlllIlllIIlIIllIIIlIllIIlIIlIIlIIlllIIIllIIllIIlIIIllIIl, 18000L, 600L);
    }
    
    @Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0016\u0010\u000b\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010\u0006" }, d2 = { "Ldev/imanity/antixray/a/IllIIllIIllIlllIIlIIllIIlIIllIIlllIIIllIlIlllIlllIIllIIllIllIIllIllIIllIIlIIlllIlllIIlllIIIllIIllIIIllIlIIllIllIllIllIIllIIIlIIlllIlllIIIllIIIlIIllIllIIIllIIIllIIlIIIllIIlIIllIllIIlllIlIlIllIllIllIlIIlIllIIlIIllIIllIIIlllIIl$IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;", "", "<init>", "()V", "", "V", "J", "IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll", "U", "IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll", "IlIIllIllIIlllIIllIIlIIIlIIllIIlIIlllIIlIlIlIIllIIlIIllIIllIIllIllIIllIlllIIIllIIlIIllIllIllIlllIIIllIllIIlIlIlIIllIllIIIllIIlIlIlIIIlllIIlIIllIIlllIIllIllIIIllIlIIllIIlllIllIllIllIIIllIIlIIlIIIlIIIllIlIIIlllIlIIIl", "IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll" })
    public static final class IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll
    {
        private IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll() {
        }
    }
}
