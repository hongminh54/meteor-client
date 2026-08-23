// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import org.bukkit.World;
import kotlin.Metadata;
import java.util.function.Supplier;
import kotlin.jvm.internal.DefaultConstructorMarker;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import org.bukkit.Location;
import org.bukkit.util.NumberConversions;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import io.fairyproject.libs.packetevents.protocol.world.chunk.BaseChunk;
import dev.imanity.antixray.api.nms.ChunkSectionHandle;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import dev.imanity.antixray.api.chunk.DirectChunk;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.internal.SourceDebugExtension;

@SourceDebugExtension({ "SMAP\nObfuscationWriter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ObfuscationWriter.kt\ndev/imanity/antixray/core/strategy/writer/ObfuscationWriter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,150:1\n1#2:151\n*E\n" })
public final class IlllIIIlIlIlllIIIlIIlIIlIIIlIIlIIlIIllIlllIlIIlllIIllIIlllIlllIlllIIlllIIllIlllIIllIlIllIIlIIIlIIIlllIIlllIIIllIIllIIlllIlllIllIIllIIIlllIIlIIllIllIIlIIllIIllIIlllIIIlllIIlIIllIIIlllIlIIllIllIIlllIIlIIlIllIIllIIlllIllIllIllIlIl
{
    @NotNull
    public static final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll cm;
    @NotNull
    private final Player cn;
    @NotNull
    private final DirectChunk co;
    @NotNull
    private final LongArrayList cq;
    @NotNull
    private final ChunkSectionHandle[] cr;
    public BaseChunk cs;
    @Nullable
    private BaseChunk ct;
    @Nullable
    private BaseChunk cu;
    private int cw;
    private final int cx;
    private final int cy;
    private final int cz;
    private final int cA;
    private final int cB;
    private final IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll cC;
    @NotNull
    private boolean[] cD;
    @NotNull
    private boolean[] cE;
    @NotNull
    private boolean[] cF;
    @NotNull
    private boolean[] cG;
    public static final int cH = 0;
    public static final int cI = 1;
    public static final int cJ = 2;
    public static final int cK = 3;
    private static final ThreadLocal<IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll> cL;
    
    public IlllIIIlIlIlllIIIlIIlIIlIIIlIIlIIlIIllIlllIlIIlllIIllIIlllIlllIlllIIlllIIllIlllIIllIlIllIIlIIIlIIIlllIIlllIIIllIIllIIlllIlllIllIIllIIIlllIIlIIllIllIIlIIllIIllIIlllIIIlllIIlIIllIIIlllIlIIllIllIIlllIIlIIlIllIIllIIlllIllIllIllIlIl(@NotNull final Player cn, @NotNull final DirectChunk co) {
        Intrinsics.checkNotNullParameter((Object)cn, "");
        Intrinsics.checkNotNullParameter((Object)co, "");
        this.cn = cn;
        this.co = co;
        this.cq = new LongArrayList();
        this.cr = new ChunkSectionHandle[4];
        this.cA = this.co.getX() << 4;
        this.cB = this.co.getZ() << 4;
        this.cC = IlllIIIlIlIlllIIIlIIlIIlIIIlIIlIIlIIllIlllIlIIlllIIllIIlllIlllIlllIIlllIIllIlllIIllIlIllIIlIIIlIIIlllIIlllIIIllIIllIIlllIlllIllIIllIIIlllIIlIIllIllIIlIIllIIllIIlllIIIlllIIlIIllIIIlllIlIIllIllIIlllIIlIIlIllIIllIIlllIllIllIllIlIl.cL.get();
        final boolean[] iIllIIllIIllIlIIIlIllIlIIIlIIlIIlIIlllIlIIlIIIllIIlllIIllIIlIlllIIllIIIlIIIlIIlllIIIlIIllIIlIIIlllIllIllIIlllIllIllIIllIIlIIlIlIIIlIIIllIIllIIllIIIlIIlIIlIlllIllIIlIIlIlllIIllIlIIllIIllIIIllIllIIllIIllIllIIlllIllIIllIl = this.cC.IIllIIllIIllIlIIIlIllIlIIIlIIlIIlIIlllIlIIlIIIllIIlllIIllIIlIlllIIllIIIlIIIlIIlllIIIlIIllIIlIIIlllIllIllIIlllIllIllIIllIIlIIlIlIIIlIIIllIIllIIllIIIlIIlIIlIlllIllIIlIIlIlllIIllIlIIllIIllIIIllIllIIllIIllIllIIlllIllIIllIl();
        ArraysKt.fill$default(iIllIIllIIllIlIIIlIllIlIIIlIIlIIlIIlllIlIIlIIIllIIlllIIllIIlIlllIIllIIIlIIIlIIlllIIIlIIllIIlIIIlllIllIllIIlllIllIllIIllIIlIIlIlIIIlIIIllIIllIIllIIIlIIlIIlIlllIllIIlIIlIlllIIllIlIIllIIllIIIllIllIIllIIllIllIIlllIllIIllIl, false, 0, 0, 6, (Object)null);
        this.cD = iIllIIllIIllIlIIIlIllIlIIIlIIlIIlIIlllIlIIlIIIllIIlllIIllIIlIlllIIllIIIlIIIlIIlllIIIlIIllIIlIIIlllIllIllIIlllIllIllIIllIIlIIlIlIIIlIIIllIIllIIllIIIlIIlIIlIlllIllIIlIIlIlllIIllIlIIllIIllIIIllIllIIllIIllIllIIlllIllIIllIl;
        final boolean[] iiIllIIlIIIlllIlIIIlllIlIlIIlllIIllIIlIllIlIIlIIlIlllIIIlIIlllIIlllIlIIlllIIlllIIlIIlllIIIllIIllIIIlIIllIIllIlllIIlllIIIllIIlIIlIIIlllIllIlllIlIIlllIIlIIlllIIllIIllIllIlllIIIlllIIlIIlllIIIllIIlIlIIIlllIIlllIllIIIllIlIllIIIllIIllIIIllIlll = this.cC.IIIllIIlIIIlllIlIIIlllIlIlIIlllIIllIIlIllIlIIlIIlIlllIIIlIIlllIIlllIlIIlllIIlllIIlIIlllIIIllIIllIIIlIIllIIllIlllIIlllIIIllIIlIIlIIIlllIllIlllIlIIlllIIlIIlllIIllIIllIllIlllIIIlllIIlIIlllIIIllIIlIlIIIlllIIlllIllIIIllIlIllIIIllIIllIIIllIlll();
        ArraysKt.fill$default(iiIllIIlIIIlllIlIIIlllIlIlIIlllIIllIIlIllIlIIlIIlIlllIIIlIIlllIIlllIlIIlllIIlllIIlIIlllIIIllIIllIIIlIIllIIllIlllIIlllIIIllIIlIIlIIIlllIllIlllIlIIlllIIlIIlllIIllIIllIllIlllIIIlllIIlIIlllIIIllIIlIlIIIlllIIlllIllIIIllIlIllIIIllIIllIIIllIlll, false, 0, 0, 6, (Object)null);
        this.cE = iiIllIIlIIIlllIlIIIlllIlIlIIlllIIllIIlIllIlIIlIIlIlllIIIlIIlllIIlllIlIIlllIIlllIIlIIlllIIIllIIllIIIlIIllIIllIlllIIlllIIIllIIlIIlIIIlllIllIlllIlIIlllIIlIIlllIIllIIllIllIlllIIIlllIIlIIlllIIIllIIlIlIIIlllIIlllIllIIIllIlIllIIIllIIllIIIllIlll;
        final boolean[] iIllIlIllIIIllIIlllIIIllIlIIllIIIlIIIllIlllIlIlllIllIIIllIllIllIIIlllIIllIIlIIIlllIIllIIIllIIllIllIIIlllIIlllIIllIIIlllIlIllIIIlIIlllIlIIIlllIIllIIllIIIlllIIllIIIllIIIllIIIllIlIlllIIllIIIllIIlllIIllIIlIIlIIllIllIIllIlllIllIlIllIIIlllIllIll = this.cC.IIllIlIllIIIllIIlllIIIllIlIIllIIIlIIIllIlllIlIlllIllIIIllIllIllIIIlllIIllIIlIIIlllIIllIIIllIIllIllIIIlllIIlllIIllIIIlllIlIllIIIlIIlllIlIIIlllIIllIIllIIIlllIIllIIIllIIIllIIIllIlIlllIIllIIIllIIlllIIllIIlIIlIIllIllIIllIlllIllIlIllIIIlllIllIll();
        ArraysKt.fill$default(iIllIlIllIIIllIIlllIIIllIlIIllIIIlIIIllIlllIlIlllIllIIIllIllIllIIIlllIIllIIlIIIlllIIllIIIllIIllIllIIIlllIIlllIIllIIIlllIlIllIIIlIIlllIlIIIlllIIllIIllIIIlllIIllIIIllIIIllIIIllIlIlllIIllIIIllIIlllIIllIIlIIlIIllIllIIllIlllIllIlIllIIIlllIllIll, false, 0, 0, 6, (Object)null);
        this.cF = iIllIlIllIIIllIIlllIIIllIlIIllIIIlIIIllIlllIlIlllIllIIIllIllIllIIIlllIIllIIlIIIlllIIllIIIllIIllIllIIIlllIIlllIIllIIIlllIlIllIIIlIIlllIlIIIlllIIllIIllIIIlllIIllIIIllIIIllIIIllIlIlllIIllIIIllIIlllIIllIIlIIlIIllIllIIllIlllIllIlIllIIIlllIllIll;
        final boolean[] iIlIIllIIllIIlllIlIIlllIlIllIllIlllIllIlIllIllIIllIllIIIllIIIlIIIllIIlllIIIlIIllIIIlllIllIIllIlIlllIllIlllIIllIIlIIllIllIIllIIIlIllIIllIIlIlllIIlllIIlllIIIllIIllIIlIIlIIlIlIIIlIIlllIIIlIIllIIIllIIlIllIlIllIIlllIlIIlIIll = this.cC.IIlIIllIIllIIlllIlIIlllIlIllIllIlllIllIlIllIllIIllIllIIIllIIIlIIIllIIlllIIIlIIllIIIlllIllIIllIlIlllIllIlllIIllIIlIIllIllIIllIIIlIllIIllIIlIlllIIlllIIlllIIIllIIllIIlIIlIIlIlIIIlIIlllIIIlIIllIIIllIIlIllIlIllIIlllIlIIlIIll();
        ArraysKt.fill$default(iIlIIllIIllIIlllIlIIlllIlIllIllIlllIllIlIllIllIIllIllIIIllIIIlIIIllIIlllIIIlIIllIIIlllIllIIllIlIlllIllIlllIIllIIlIIllIllIIllIIIlIllIIllIIlIlllIIlllIIlllIIIllIIllIIlIIlIIlIlIIIlIIlllIIIlIIllIIIllIIlIllIlIllIIlllIlIIlIIll, false, 0, 0, 6, (Object)null);
        this.cG = iIlIIllIIllIIlllIlIIlllIlIllIllIlllIllIlIllIllIIllIllIIIllIIIlIIIllIIlllIIIlIIllIIIlllIllIIllIlIlllIllIlllIIllIIlIIllIllIIllIIIlIllIIllIIlIlllIIlllIIlllIIIllIIllIIlIIlIIlIlIIIlIIlllIIIlIIllIIIllIIlIllIlIllIIlllIlIIlIIll;
        final Location iIllIIllIIllIllIIIllIIlllIIIllIllIIllIIIllIlllIlllIIllIIlIIIlIlIllIIlIIllIIlIIIlIllIlllIIllIllIIllIlIlIllIIllIIIllIIllIIIlllIlIIllIIlIllIIlIIlIIIlIIlIIlllIIlllIIllIIlIIllIIllIIlllIIlllIIlIlIIIlllIIIlllIIIlllIIlIIllIlIIlllIlllIll = this.cC.IIllIIllIIllIllIIIllIIlllIIIllIllIIllIIIllIlllIlllIIllIIlIIIlIlIllIIlIIllIIlIIIlIllIlllIIllIllIIllIlIlIllIIllIIIllIIllIIIlllIlIIllIIlIllIIlIIlIIIlIIlIIlllIIlllIIllIIlIIllIIllIIlllIIlllIIlIlIIIlllIIIlllIIIlllIIlIIllIlIIlllIlllIll();
        this.cn.getLocation(iIllIIllIIllIllIIIllIIlllIIIllIllIIllIIIllIlllIlllIIllIIlIIIlIlIllIIlIIllIIlIIIlIllIlllIIllIllIIllIlIlIllIIllIIIllIIllIIIlllIlIIllIIlIllIIlIIlIIIlIIlIIlllIIlllIIllIIlIIllIIllIIlllIIlllIIlIlIIIlllIIIlllIIIlllIIlIIllIlIIlllIlllIll);
        this.cx = iIllIIllIIllIllIIIllIIlllIIIllIllIIllIIIllIlllIlllIIllIIlIIIlIlIllIIlIIllIIlIIIlIllIlllIIllIllIIllIlIlIllIIllIIIllIIllIIIlllIlIIllIIlIllIIlIIlIIIlIIlIIlllIIlllIIllIIlIIllIIllIIlllIIlllIIlIlIIIlllIIIlllIIIlllIIlIIllIlIIlllIlllIll.getBlockX();
        this.cy = NumberConversions.floor(iIllIIllIIllIllIIIllIIlllIIIllIllIIllIIIllIlllIlllIIllIIlIIIlIlIllIIlIIllIIlIIIlIllIlllIIllIllIIllIlIlIllIIllIIIllIIllIIIlllIlIIllIIlIllIIlIIlIIIlIIlIIlllIIlllIIllIIlIIllIIllIIlllIIlllIIlIlIIIlllIIIlllIIIlllIIlIIllIlIIlllIlllIll.getY() + this.cn.getEyeHeight());
        this.cz = iIllIIllIIllIllIIIllIIlllIIIllIllIIllIIIllIlllIlllIIllIIlIIIlIlIllIIlIIllIIlIIIlIllIlllIIllIllIIllIlIlIllIIllIIIllIIllIIIlllIlIIllIIlIllIIlIIlIIIlIIlIIlllIIlllIIllIIlIIllIIllIIlllIIlllIIlIlIIIlllIIIlllIIIlllIIlIIllIlIIlllIlllIll.getBlockZ();
    }
    
    @NotNull
    public final Player IIlllIIlIlIllIlIllIIlllIllIIlllIlllIIlIlIIlIllIIlllIIlIIIllIlIIIlIIlllIIIllIIlIIllIIIllIIIllIIlIlllIlIIllIlIIllIIlllIIIllIIllIlIllIlIIllIlIIIlllIIllIIllIllIIlIllIllIIIlIlIIllIIIlllIllIIIlllIIIlllIIIllIIllIIIlIIlIlIIIllIIIlll() {
        return this.cn;
    }
    
    @NotNull
    public final DirectChunk IIIlllIIIllIIlIlIlIIIllIIlllIIIlllIIllIllIllIIlIIllIlIIIlllIIlIlllIIIlllIlIllIIlIIIlllIIlllIllIlllIIIlllIIIllIIIlllIIllIllIllIIlIlIIlllIIllIIllIIIlIlIlllIlllIllIIllIIIlllIlIIIlllIIllIlIIllIIIlIIIlIIlIllIllIIlIIlllIIllIllIIIllIIlIIl() {
        return this.co;
    }
    
    @NotNull
    public final LongArrayList IIIlIIllIIIlIIIlllIlIIIlIIllIlIIlIllIlllIlIIlIlllIIIlIlIlllIlIllIIIllIIlIllIIIllIIIllIIlIIllIllIIllIIIlllIllIIllIIIlIIIlllIIlIIllIIIlllIIllIlIIIllIlIIlllIllIIlIIIlIlllIIIllIIlllIIllIlllIlllIIIlIIlllIIllIIlIIllIllIIlIIlIllIIl() {
        return this.cq;
    }
    
    @NotNull
    public final ChunkSectionHandle[] IIlllIlllIlIllIIIlIlllIIIlIIIlllIIIlllIIIlIIlIIllIlIIIlIIIlIlIlllIllIlIIllIIIllIlllIllIIlIIllIIlllIlIIIllIIlIIllIIlIllIIIllIlIIlIIIlIIllIIIlllIIIllIIlIIllIIllIlIIllIlllIIlIIllIllIlllIIlIlIIIlIIlllIlllIlIllIIIllIllIIIlIIIl() {
        return this.cr;
    }
    
    @NotNull
    public final BaseChunk IlllIlIIllIIlIIlIIIlllIllIIllIIIllIllIIIllIIlllIlllIIIlIIIllIIIllIlllIllIlllIIllIlllIIlllIIIlIIllIIllIIlllIllIIllIlllIlllIIIlIIllIIIllIIllIlllIlllIIllIllIllIllIIIllIIlIIlllIlIIIlIIIllIIllIlIIIlIIlllIIlIllIIIllIIllIIllIlllIIllIlIlIIll() {
        final BaseChunk cs = this.cs;
        if (cs != null) {
            return cs;
        }
        Intrinsics.throwUninitializedPropertyAccessException("");
        return null;
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final BaseChunk cs) {
        Intrinsics.checkNotNullParameter((Object)cs, "");
        this.cs = cs;
    }
    
    @Nullable
    public final BaseChunk IIlIlIllIIllIIlllIIlIlllIIlllIIllIIlllIlIIllIIIllIIllIIllIIllIIIlIIlIIlIlllIlIIIllIIlllIIIllIllIllIlIIllIIIlIIIlllIIllIllIIllIIlIllIllIIllIIllIIIlIlllIlIIlIlllIllIIllIIIlIIIlIllIIlllIIlIIllIlIllIIlllIllIIIlllIlIlIlIIl() {
        return this.ct;
    }
    
    public final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@Nullable final BaseChunk ct) {
        this.ct = ct;
    }
    
    @Nullable
    public final BaseChunk IllIlllIIIllIllIlIlllIIIllIIllIllIlllIlllIIllIlllIIlIIIllIlllIIllIlIIllIIIlIIlIIlIIllIlllIllIIIllIIIllIlIIllIIlIllIllIlllIlIIlllIIIlIIlIIllIIIlllIIIllIIllIlIIllIlIIIlIIIllIIIlllIlllIllIlIIlIllIIlIIllIIllIIIlIIlIIllIllIIIlll() {
        return this.cu;
    }
    
    public final void IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(@Nullable final BaseChunk cu) {
        this.cu = cu;
    }
    
    public final int IIlIIllIIIlIIllIIIllIlllIlIllIlIIlIIlllIIlIllIllIIllIIllIlIIlllIIIlIllIllIIlllIIIllIIllIllIIllIIlIIlllIIlIlIIlllIlllIlllIlIIlIIlllIIlllIIlIIlIlIIlIIlIIIllIIIllIIllIIIllIIIlllIIllIlllIllIIIlllIIllIIllIIllIIllIIIllIIlllIIIllIlIIll() {
        return this.cw;
    }
    
    public final void IIIllIIlllIlIIIllIllIIIlllIIlllIIlllIlIlIIlIIIllIIllIlllIIlllIIlIlllIIlIIlIIllIIlIIIlllIllIIlllIIlIllIIIllIIlIIlIIlIIllIIlIIlIIIllIIllIIlIIlIIlllIIlllIIIllIlIIllIllIIlllIlIIIlllIIllIIlllIIllIlllIIllIIIllIIllIIllIIIlllIlIIlllIIllIIllIIlll(final int cw) {
        this.cw = cw;
    }
    
    public final int IIlIIlllIIlIlllIIllIIlIllIllIIlllIlIIIllIIIlIlIllIllIIIlIIlIIIlIlIIIllIllIlllIllIIIlIIIlIIlIIIlIIIlIIlIllIlllIIlIIlllIlIlIIIllIIIlIIIllIIllIIllIlIIlllIIlIlIIlIIIllIIIlIIllIllIllIIIlllIIIllIIIllIllIIlllIIlllIIlIIIlllIIIlIIIl() {
        return this.cx;
    }
    
    public final int IIlIIllIIIlllIlIIIllIIIlllIllIlllIlllIlllIIlllIlIIlIIlIIIlIIlllIIlllIIlIIlIllIIllIlllIlllIllIllIIlllIIlIIIlIIllIllIIlIIlllIIlIIlIIlllIIllIIllIIllIIlIIIlIIlIIlIIlIIlllIlIIlllIlllIIlIlllIIlllIIlIIIllIIlllIIlllIlIIlIIlllIIIllIllIll() {
        return this.cy;
    }
    
    public final int IlIIIlIIllIlllIIIllIlIIlIIlIllIIIlllIlIIIlllIlIIllIlllIlIlllIlllIIIllIIIllIIlIIlllIIlllIIlIlllIIlllIIIllIIllIIlIIlllIIllIlllIIlIIlllIIIllIIllIIIlIIllIIIlIllIIIllIIllIllIllIIIllIllIIIlIIlIlllIIlllIIlllIllIllIIlIIllIIllIlIllIIlIIIll() {
        return this.cz;
    }
    
    public final int IIIlIIlllIIllIIllIIIlIIIlIIllIIlllIIlllIIIlllIlIlIIlIIlIllIIllIIllIIlllIIIllIllIIlIllIIllIIllIlIIIlIIlllIlIIllIlIllIIllIlIlIIIlIllIlIllIIIlIlllIIlIIlIllIlIIIlIIlIllIIlllIIllIIlllIIlIIIllIIlllIIllIIlIIlllIIllIllIIllIIl() {
        return this.cA;
    }
    
    public final int IIlIIllIIIlIIlllIllIIIlIIIllIIIllIlIIlllIIllIIlIIlllIllIIllIIllIIlIlIIIlllIllIIllIllIIllIIlIIlIIIllIlIIIlllIlllIIlIIllIIIllIlllIIIlllIIlIIlIllIlllIIIllIIlllIIlllIIlllIIllIllIIllIIlllIIIlllIllIIIlIllIIlllIIIllIlIIIllIIlIIllIllIIIlllIllIIll() {
        return this.cB;
    }
    
    @NotNull
    public final boolean[] IIlllIlllIIIlIIIlIIIllIlIIllIIllIlllIIIllIIIllIIIlIllIIIllIIIlllIIllIIlllIIllIIIllIIllIIIllIIIllIIIlIIllIIlIIlIlIIlllIllIlllIIlIIIlIIlIIllIIllIIIllIIllIllIllIlIIIlIllIIlIllIllIlIIlllIIlllIIlllIIlIlIIIlIIlllIIlIIIllIlIIllIIllIIlllIIll() {
        return this.cD;
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final boolean[] cd) {
        Intrinsics.checkNotNullParameter((Object)cd, "");
        this.cD = cd;
    }
    
    @NotNull
    public final boolean[] IIIllIIIllIIlIIIlIllIIllIIlIIIllIIlIlIIlllIIllIlllIIIllIlIllIllIlllIllIIllIIllIllIlIllIllIllIIlIlIIIlllIIllIIIlllIIlIIllIIlIIlIIIlllIIlIlllIIlIIllIIIlllIIlIIlIlIllIIIllIIllIIlIllIIllIllIIlllIlllIIlllIIlllIIIllIlIIlIIllIIIlll() {
        return this.cE;
    }
    
    public final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final boolean[] ce) {
        Intrinsics.checkNotNullParameter((Object)ce, "");
        this.cE = ce;
    }
    
    @NotNull
    public final boolean[] IIlllIIlIIlIIIlIIlllIIllIlllIIlllIIlIIlIIIlllIIlllIllIIllIIIllIlIIIllIllIllIIIllIIlllIIlllIIlllIIllIIlllIIIlIIIlIIlIIIllIllIIIlIllIlllIllIlllIlllIIllIIIlIIllIIIllIIllIIlllIIlIIllIllIIIllIIIlllIIIllIIlllIllIIIllIIIllIIllIIllIIIlllIllIIlIIlIIIlllIIll() {
        return this.cF;
    }
    
    public final void IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(@NotNull final boolean[] cf) {
        Intrinsics.checkNotNullParameter((Object)cf, "");
        this.cF = cf;
    }
    
    @NotNull
    public final boolean[] IIllIllIIllIlllIIllIIlIIlIllIIlIllIIIlIIllIIlIlIllIlIIlIllIlllIlIIlllIllIIIllIIllIllIIIlIIllIIllIIlIIllIIlIIllIIIlllIIlIllIIlllIIlIIIllIIllIIIllIlIIllIIIllIIllIIIllIIllIIllIIIllIIIllIIlllIIIlllIIllIlllIIlllIIllIlIllIIlllIIlIIll() {
        return this.cG;
    }
    
    public final void IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(@NotNull final boolean[] cg) {
        Intrinsics.checkNotNullParameter((Object)cg, "");
        this.cG = cg;
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final IllIllIllIlIIllIllIIllIIlllIIlIIlIIIlllIIlllIlllIllIllIIlIIllIIlllIIIllIIlIIlllIIIlIlllIIlIIlllIIlllIIllIIllIIIllIIIlIllIllIllIIlIllIllIllIIllIllIIlllIIllIIlIIllIIlIlIIIllIIlllIIllIIlllIIllIIlIllIlllIlIIIllIIIlIIIlIIlllIlIl illIllIllIlIIllIllIIllIIlllIIlIIlIIIlllIIlllIlllIllIllIIlIIllIIlllIIIllIIlIIlllIIIlIlllIIlIIlllIIlllIIllIIllIIIllIIIlIllIllIllIIlIllIllIllIIllIllIIlllIIllIIlIIllIIlIlIIIllIIlllIIllIIlllIIllIIlIllIlllIlIIIllIIIlIIIlIIlllIlIl, @NotNull final WrappedBlockState wrappedBlockState, final int n, final int n2) {
        Intrinsics.checkNotNullParameter((Object)illIllIllIlIIllIllIIllIIlllIIlIIlIIIlllIIlllIlllIllIllIIlIIllIIlllIIIllIIlIIlllIIIlIlllIIlIIlllIIlllIIllIIllIIIllIIIlIllIllIllIIlIllIllIllIIllIllIIlllIIllIIlIIllIIlIlIIIllIIlllIIllIIlllIIllIIlIllIlllIlIIIllIIIlIIIlIIlllIlIl, "");
        Intrinsics.checkNotNullParameter((Object)wrappedBlockState, "");
        if (illIllIllIlIIllIllIIllIIlllIIlIIlIIIlllIIlllIlllIllIllIIlIIllIIlllIIIllIIlIIlllIIIlIlllIIlIIlllIIlllIIllIIllIIIllIIIlIllIllIllIIlIllIllIllIIllIllIIlllIIllIIlIIllIIlIlIIIllIIlllIIllIIlllIIllIIlIllIlllIlIIIllIIIlIIIlIIlllIlIl.IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(wrappedBlockState)) {
            this.cD[IIlllIIlIlllIIIlllIIIlIIIlllIlIIIllIIIllIIlIIIllIlIIllIIIllIIIllIIlIIllIIIlIlllIIllIIllIllIIllIIlllIlIIlIlllIllIllIllIIlllIIlllIIIllIlllIIlllIlIIlIIllIllIIllIIllIlIllIIlllIIIlllIIllIlIIIllIllIlIlIIllIllIlllIIIlllIIllIIlIIlllIIllIIl.IllIIllIlIlIllIIllIIIllIIIllIIIllIIIlIlIlIllIlIIllIIlllIlllIIlIIIllIIlIllIIlllIIllIllIIIlIIlIlIlllIIIlllIIlIIIllIIIllIIIllIlIIlllIIlIlIIIlllIIlIlIIIlllIIlllIIlIllIllIIlIIIllIIllIlllIIllIIllIllIIllIIllIIlIIllIllIIllIIlIIl(n, n2)] = true;
        }
        else {
            this.cD[IIlllIIlIlllIIIlllIIIlIIIlllIlIIIllIIIllIIlIIIllIlIIllIIIllIIIllIIlIIllIIIlIlllIIllIIllIllIIllIIlllIlIIlIlllIllIllIllIIlllIIlllIIIllIlllIIlllIlIIlIIllIllIIllIIllIlIllIIlllIIIlllIIllIlIIIllIllIlIlIIllIllIlllIIIlllIIllIIlIIlllIIllIIl.IllIIllIlIlIllIIllIIIllIIIllIIIllIIIlIlIlIllIlIIllIIlllIlllIIlIIIllIIlIllIIlllIIllIllIIIlIIlIlIlllIIIlllIIlIIIllIIIllIIIllIlIIlllIIlIlIIIlllIIlIlIIIlllIIlllIIlIllIllIIlIIIllIIllIlllIIllIIllIllIIllIIllIIlIIllIllIIllIIlIIl(n, n2)] = false;
            this.cF[IIlllIIlIlllIIIlllIIIlIIIlllIlIIIllIIIllIIlIIIllIlIIllIIIllIIIllIIlIIllIIIlIlllIIllIIllIllIIllIIlllIlIIlIlllIllIllIllIIlllIIlllIIIllIlllIIlllIlIIlIIllIllIIllIIllIlIllIIlllIIIlllIIllIlIIIllIllIlIlIIllIllIlllIIIlllIIllIIlIIlllIIllIIl.IllIIllIlIlIllIIllIIIllIIIllIIIllIIIlIlIlIllIlIIllIIlllIlllIIlIIIllIIlIllIIlllIIllIllIIIlIIlIlIlllIIIlllIIlIIIllIIIllIIIllIlIIlllIIlIlIIIlllIIlIlIIIlllIIlllIIlIllIllIIlIIIllIIllIlllIIllIIllIllIIllIIllIIlIIllIllIIllIIlIIl(n, n2)] = true;
        }
    }
    
    public final void IIlIIlIIIlIlllIllIIlIIIlIIlllIllIIlIlllIIllIIlIlIIlIIIllIlIIIllIIlIIllIIIllIIllIIIlllIIIlIlIIIllIIllIIIllIllIllIlIIllIIllIIIllIIlllIIIlllIIIlIlllIlIlllIIllIllIllIlllIIIlIIllIIIllIIlIIlIIlIIlIllIIllIIlIIlIllIIIllIIlllIlllIIlll() {
        final boolean[] ce = this.cE;
        this.cE = this.cF;
        this.cF = this.cG;
        this.cG = ce;
    }
    
    @NotNull
    public final WrappedBlockState IIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll(final int n, final int n2, final int n3) {
        WrappedBlockState wrappedBlockState;
        if (n2 == 15) {
            final BaseChunk ct = this.ct;
            Intrinsics.checkNotNull((Object)ct);
            wrappedBlockState = IIlllIIlIlllIIIlllIIIlIIIlllIlIIIllIIIllIIlIIIllIlIIllIIIllIIIllIIlIIllIIIlIlllIIllIIllIllIIllIIlllIlIIlIlllIllIllIllIIlllIIlllIIIllIlllIIlllIlIIlIIllIllIIllIIllIlIllIIlllIIIlllIIllIlIIIllIllIlIlIIllIllIlllIIIlllIIllIIlIIlllIIllIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(ct, n, 0, n3);
        }
        else {
            wrappedBlockState = IIlllIIlIlllIIIlllIIIlIIIlllIlIIIllIIIllIIlIIIllIlIIllIIIllIIIllIIlIIllIIIlIlllIIllIIllIllIIllIIlllIlIIlIlllIllIllIllIIlllIIlllIIIllIlllIIlllIlIIlIIllIllIIllIIllIlIllIIlllIIIlllIIllIlIIIllIllIlIlIIllIllIlllIIIlllIIllIIlIIlllIIllIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(this.IlllIlIIllIIlIIlIIIlllIllIIllIIIllIllIIIllIIlllIlllIIIlIIIllIIIllIlllIllIlllIIllIlllIIlllIIIlIIllIIllIIlllIllIIllIlllIlllIIIlIIllIIIllIIllIlllIlllIIllIllIllIllIIIllIIlIIlllIlIIIlIIIllIIllIlIIIlIIlllIIlIllIIIllIIllIIllIlllIIllIlIlIIll(), n, n2 + 1, n3);
        }
        return wrappedBlockState;
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final int n, final int n2, final int n3, @NotNull final WrappedBlockState wrappedBlockState) {
        Intrinsics.checkNotNullParameter((Object)wrappedBlockState, "");
        this.IlllIlIIllIIlIIlIIIlllIllIIllIIIllIllIIIllIIlllIlllIIIlIIIllIIIllIlllIllIlllIIllIlllIIlllIIIlIIllIIllIIlllIllIIllIlllIlllIIIlIIllIIIllIIllIlllIlllIIllIllIllIllIIIllIIlIIlllIlIIIlIIIllIIllIlIIIlIIlllIIlIllIIIllIIllIIllIlllIIllIlIlIIll().set(n, n2, n3, wrappedBlockState);
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final int n, final int n2, final int n3, final boolean b) {
        this.cq.add(IlllIIIlIlIlllIIIlIIlIIlIIIlIIlIIlIIllIlllIlIIlllIIllIIlllIlllIlllIIlllIIllIlllIIllIlIllIIlIIIlIIIlllIIlllIIIllIIllIIlllIlllIllIIllIIIlllIIlIIllIllIIlIIllIIllIIlllIIIlllIIlIIllIIIlllIlIIllIllIIlllIIlIIlIllIIllIIlllIllIllIllIlIl.cm.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(n, n2, n3, b));
    }
    
    private static final IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll IIlIIllIllIlllIIIlllIllIIlIIIllIIlIIllIIlIIllIIIlIlIIIlIIlllIIIlllIIIlIIlIIIlIIlllIIlllIlIIlllIIlIIIllIIllIllIllIllIllIIlIIIllIIIlIIllIIlllIlIlIlIIIllIIIlIIlIIlllIIlIIIlllIllIIlllIllIIlIIllIIIlIlIIllIIlllIIIlllIIlllIlllIlIllIlll() {
        return new IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll();
    }
    
    static {
        cm = new IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(null);
        cL = ThreadLocal.withInitial((Supplier<? extends IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll>)IlllIIIlIlIlllIIIlIIlIIlIIIlIIlIIlIIllIlllIlIIlllIIllIIlllIlllIlllIIlllIIllIlllIIllIlIllIIlIIIlIIIlllIIlllIIIllIIllIIlllIlllIllIIllIIIlllIIlIIllIllIIlIIllIIllIIlllIIIlllIIlIIllIIIlllIlIIllIllIIlllIIlIIlIllIIllIIlllIllIllIllIlIl::IIlIIllIllIlllIIIlllIllIIlIIIllIIlIIllIIlIIllIIIlIlIIIlIIlllIIIlllIIIlIIlIIIlIIlllIIlllIlIIlllIIlIIIllIIllIllIllIllIllIIlIIIllIIIlIIllIIlllIlIlIlIIIllIIIlIIlIIlllIIlIIIlllIllIIlllIllIIlIIllIIIlIlIIllIIlllIIIlllIIlllIlllIlIllIlll);
    }
    
    @Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\r\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\n¢\u0006\u0004\b\u0011\u0010\u0010J\u0015\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\n¢\u0006\u0004\b\u0012\u0010\u0010R\u0014\u0010\u0015\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u000b\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0016\u0010\u0014R,\u0010\u001b\u001a\u001a\u0012\b\u0012\u0006*\u00020\u00180\u0018*\f\u0012\b\u0012\u0006*\u00020\u00180\u00180\u00170\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001d\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001c\u0010\u0014R\u0014\u0010\u001f\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001e\u0010\u0014" }, d2 = { "Ldev/imanity/antixray/a/IlllIIIlIlIlllIIIlIIlIIlIIIlIIlIIlIIllIlllIlIIlllIIllIIlllIlllIlllIIlllIIllIlllIIllIlIllIIlIIIlIIIlllIIlllIIIllIIllIIlllIlllIllIIllIIIlllIIlIIllIllIIlIIllIIllIIlllIIIlllIIlIIllIIIlllIlIIllIllIIlllIIlIIlIllIIllIIlllIllIllIllIlIl$IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;", "", "<init>", "()V", "", "p0", "p1", "p2", "", "p3", "", "IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll", "(IIIZ)J", "IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl", "(J)Z", "IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll", "(J)I", "IllIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll", "IIllIllIlIlllIIlllIIlllIlIIIllIIlIIllIIllIIlIIIlllIIIlIIlllIIlllIIIlIlIIlIIllIlIIIllIIlIIllIIlIIlIIlllIIIllIlllIIlIIlllIllIllIIlllIlllIIlllIlIIIllIIllIlIlIIIllIlIIIlIIIllIIllIIlIlllIllIllIIIlIIIlIIllIIllIlIIIlIIIlIIllIIIllIIlll", "cI", "I", "IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll", "cJ", "Ljava/lang/ThreadLocal;", "Ldev/imanity/antixray/a/IlllIIIlIlIlllIIIlIIlIIlIIIlIIlIIlIIllIlllIlIIlllIIllIIlllIlllIlllIIlllIIllIlllIIllIlIllIIlIIIlIIIlllIIlllIIIllIIllIIlllIlllIllIIllIIIlllIIlIIllIllIIlIIllIIllIIlllIIIlllIIlIIllIIIlllIlIIllIllIIlllIIlIIlIllIIllIIlllIllIllIllIlIl$IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll;", "cL", "Ljava/lang/ThreadLocal;", "IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll", "cK", "IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll", "cH", "IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll" })
    public static final class IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll
    {
        private IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll() {
        }
        
        public final long IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final int n, final int n2, final int n3, final boolean b) {
            return (long)n2 << 9 | (b ? 256L : 0L) | (long)(n3 & 0xF) << 4 | (long)(n & 0xF);
        }
        
        public final int IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll(final long n) {
            return (int)(n & 0xFL);
        }
        
        public final int IIllIllIlIlllIIlllIIlllIlIIIllIIlIIllIIllIIlIIIlllIIIlIIlllIIlllIIIlIlIIlIIllIlIIIllIIlIIllIIlIIlIIlllIIIllIlllIIlIIlllIllIllIIlllIlllIIlllIlIIIllIIllIlIlIIIllIlIIIlIIIllIIllIIlIlllIllIllIIIlIIIlIIllIIllIlIIIlIIIlIIllIIIllIIlll(final long n) {
            return (int)(n >> 4 & 0xFL);
        }
        
        public final boolean IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(final long n) {
            return (n & 0x100L) != 0x0L;
        }
        
        public final int IllIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll(final long n) {
            return (int)(n >> 9);
        }
    }
    
    private static final class IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll
    {
        @NotNull
        private final boolean[] cM;
        @NotNull
        private final boolean[] cN;
        @NotNull
        private final boolean[] cO;
        @NotNull
        private final boolean[] cP;
        @NotNull
        private final Location cQ;
        
        public IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll() {
            this.cM = new boolean[256];
            this.cN = new boolean[256];
            this.cO = new boolean[256];
            this.cP = new boolean[256];
            this.cQ = new Location((World)null, 0.0, 0.0, 0.0);
        }
        
        @NotNull
        public final boolean[] IIllIIllIIllIlIIIlIllIlIIIlIIlIIlIIlllIlIIlIIIllIIlllIIllIIlIlllIIllIIIlIIIlIIlllIIIlIIllIIlIIIlllIllIllIIlllIllIllIIllIIlIIlIlIIIlIIIllIIllIIllIIIlIIlIIlIlllIllIIlIIlIlllIIllIlIIllIIllIIIllIllIIllIIllIllIIlllIllIIllIl() {
            return this.cM;
        }
        
        @NotNull
        public final boolean[] IIIllIIlIIIlllIlIIIlllIlIlIIlllIIllIIlIllIlIIlIIlIlllIIIlIIlllIIlllIlIIlllIIlllIIlIIlllIIIllIIllIIIlIIllIIllIlllIIlllIIIllIIlIIlIIIlllIllIlllIlIIlllIIlIIlllIIllIIllIllIlllIIIlllIIlIIlllIIIllIIlIlIIIlllIIlllIllIIIllIlIllIIIllIIllIIIllIlll() {
            return this.cN;
        }
        
        @NotNull
        public final boolean[] IIllIlIllIIIllIIlllIIIllIlIIllIIIlIIIllIlllIlIlllIllIIIllIllIllIIIlllIIllIIlIIIlllIIllIIIllIIllIllIIIlllIIlllIIllIIIlllIlIllIIIlIIlllIlIIIlllIIllIIllIIIlllIIllIIIllIIIllIIIllIlIlllIIllIIIllIIlllIIllIIlIIlIIllIllIIllIlllIllIlIllIIIlllIllIll() {
            return this.cO;
        }
        
        @NotNull
        public final boolean[] IIlIIllIIllIIlllIlIIlllIlIllIllIlllIllIlIllIllIIllIllIIIllIIIlIIIllIIlllIIIlIIllIIIlllIllIIllIlIlllIllIlllIIllIIlIIllIllIIllIIIlIllIIllIIlIlllIIlllIIlllIIIllIIllIIlIIlIIlIlIIIlIIlllIIIlIIllIIIllIIlIllIlIllIIlllIlIIlIIll() {
            return this.cP;
        }
        
        @NotNull
        public final Location IIllIIllIIllIllIIIllIIlllIIIllIllIIllIIIllIlllIlllIIllIIlIIIlIlIllIIlIIllIIlIIIlIllIlllIIllIllIIllIlIlIllIIllIIIllIIllIIIlllIlIIllIIlIllIIlIIlIIIlIIlIIlllIIlllIIllIIlIIllIIllIIlllIIlllIIlIlIIIlllIIIlllIIIlllIIlIIllIlIIlllIlllIll() {
            return this.cQ;
        }
    }
}
