// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import org.bukkit.block.Block;
import io.fairyproject.mc.util.BlockPosition;
import java.util.Iterator;
import dev.imanity.antixray.api.chunk.DirectChunk;
import io.fairyproject.libs.packetevents.protocol.world.chunk.BaseChunk;
import kotlin.Metadata;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.functions.Function4;
import kotlin.Unit;
import dev.imanity.antixray.api.chunk.ChunkData;
import org.bukkit.entity.Player;
import org.bukkit.World;
import org.bukkit.Chunk;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import it.unimi.dsi.fastutil.bytes.Byte2IntMap;
import kotlin.jvm.internal.SourceDebugExtension;
import dev.imanity.antixray.api.obfuscator.ObfuscateStrategy;

@SourceDebugExtension({ "SMAP\nFakeObfuscateStrategy.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FakeObfuscateStrategy.kt\ndev/imanity/antixray/core/strategy/fake/FakeObfuscateStrategy\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,73:1\n1855#2,2:74\n*S KotlinDebug\n*F\n+ 1 FakeObfuscateStrategy.kt\ndev/imanity/antixray/core/strategy/fake/FakeObfuscateStrategy\n*L\n39#1:74,2\n*E\n" })
public final class IIIlIlIllIllIIllIllIIlIlllIIIllIIIlIlllIIlIIllIIlIIIllIIlllIIIlIllIIllIIIllIlIlllIIlIIllIIllIIllIIIllIIllIIIllIIllIllIIIlllIIIlIlllIIIlllIlIlllIIIllIIllIlIlllIIlllIllIIlIIlllIIlIIllIlIllIllIlllIIIlIIlIIllIIlIIllIIIlIIllIIlIl implements ObfuscateStrategy
{
    @NotNull
    private final Byte2IntMap bS;
    @NotNull
    private final IIllIIlllIIIllIIIlllIlllIIIlIIlIIlIlllIlllIIllIIIllIIlIllIIIllIlllIIlIIIlIllIllIllIlllIllIlIIllIIIllIlIIIllIIllIIIllIlIIIllIllIIIlllIlllIlIIlllIIlIIIllIllIIlllIllIIllIIlllIlIlIIIlllIIlIIlllIIlllIIIllIlIllIIllIllIlllIIlIlIlIlll bT;
    private final byte bU;
    @NotNull
    private final List<IIllIIlllIllIlllIlllIIlllIlIIllIIIlIIIlIIIllIlIllIlIlllIIlIIllIIlIIllIIIlllIIlIlllIlIIllIIlIIllIIlllIIlllIIIllIIlllIIlIIlIIllIllIIIllIllIIlIIlIIllIIlIlllIllIlIIlIlllIlIllIIllIlIlllIIIllIlIIllIIlIlIIIlIIllIllIIlllIIIll> bV;
    
    public IIIlIlIllIllIIllIllIIlIlllIIIllIIIlIlllIIlIIllIIlIIIllIIlllIIIlIllIIllIIIllIlIlllIIlIIllIIllIIllIIIllIIllIIIllIIllIllIIIlllIIIlIlllIIIlllIlIlllIIIllIIllIlIlllIIlllIllIIlIIlllIIlIIllIlIllIllIlllIIIlIIlIIllIIlIIllIIIlIIllIIlIl(@NotNull final Byte2IntMap bs, @NotNull final IIllIIlllIIIllIIIlllIlllIIIlIIlIIlIlllIlllIIllIIIllIIlIllIIIllIlllIIlIIIlIllIllIllIlllIllIlIIllIIIllIlIIIllIIllIIIllIlIIIllIllIIIlllIlllIlIIlllIIlIIIllIllIIlllIllIIllIIlllIlIlIIIlllIIlIIlllIIlllIIIllIlIllIIllIllIlllIIlIlIlIlll bt) {
        Intrinsics.checkNotNullParameter((Object)bs, "");
        Intrinsics.checkNotNullParameter((Object)bt, "");
        this.bS = bs;
        this.bT = bt;
        this.bU = -1;
        this.bV = new ArrayList<IIllIIlllIllIlllIlllIIlllIlIIllIIIlIIIlIIIllIlIllIlIlllIIlIIllIIlIIllIIIlllIIlIlllIlIIllIIlIIllIIlllIIlllIIIllIIlllIIlIIlIIllIllIIIllIllIIlIIlIIllIIlIlllIllIlIIlIlllIlIllIIllIlIlllIIIllIlIIllIIlIlIIIlIIllIllIIlllIIIll>();
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final IIllIIlllIllIlllIlllIIlllIlIIllIIIlIIIlIIIllIlIllIlIlllIIlIIllIIlIIllIIIlllIIlIlllIlIIllIIlIIllIIlllIIlllIIIllIIlllIIlIIlIIllIllIIIllIllIIlIIlIIllIIlIlllIllIlIIlIlllIlIllIIllIlIlllIIIllIlIIllIIlIlIIIlIIllIllIIlllIIIll illIIlllIllIlllIlllIIlllIlIIllIIIlIIIlIIIllIlIllIlIlllIIlIIllIIlIIllIIIlllIIlIlllIlIIllIIlIIllIIlllIIlllIIIllIIlllIIlIIlIIllIllIIIllIllIIlIIlIIllIIlIlllIllIlIIlIlllIlIllIIllIlIlllIIIllIlIIllIIlIlIIIlIIllIllIIlllIIIll) {
        Intrinsics.checkNotNullParameter((Object)illIIlllIllIlllIlllIIlllIlIIllIIIlIIIlIIIllIlIllIlIlllIIlIIllIIlIIllIIIlllIIlIlllIlIIllIIlIIllIIlllIIlllIIIllIIlllIIlIIlIIllIllIIIllIllIIlIIlIIllIIlIlllIllIlIIlIlllIlIllIIllIlIlllIIIllIlIIllIIlIlIIIlIIllIllIIlllIIIll, "");
        this.bV.add(illIIlllIllIlllIlllIIlllIlIIllIIIlIIIlIIIllIlIllIlIlllIIlIIllIIlIIllIIIlllIIlIlllIlIIllIIlIIllIIlllIIlllIIIllIIlllIIlIIlIIllIllIIIllIllIIlIIlIIllIIlIlllIllIlIIlIlllIlIllIIllIlIlllIIIllIlIIllIIlIlIIIlIIllIllIIlllIIIll);
    }
    
    @Override
    public void onGlobalChunkLoaded(@NotNull final Chunk chunk) {
        Intrinsics.checkNotNullParameter((Object)chunk, "");
        this.bT.IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll(chunk);
    }
    
    @Override
    public void onGlobalChunkUnloaded(@NotNull final Chunk chunk) {
        Intrinsics.checkNotNullParameter((Object)chunk, "");
        this.bT.IIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll(chunk);
    }
    
    @Override
    public void onReload() {
        this.bT.clear();
    }
    
    @Override
    public void obfuscate(@NotNull final World world, @NotNull final Player player, @NotNull final ChunkData chunkData) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)chunkData, "");
        final DirectChunk current = chunkData.getChunkRegion().getCurrent();
        final IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll illIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll = this.bT.IllIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll(current.getX(), current.getZ());
        synchronized (illIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll) {
            if (!illIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll.IlIIlllIIlllIIllIIIlllIllIIlllIIlllIIlIIIllIllIIllIlIllIlIIlIlIlIIlIIlllIlIlllIIlllIIllIllIIIlIIIlIIllIIllIllIIIlllIIlllIIlIllIllIllIllIIIlllIllIIlIIllIIlllIllIlIIlIIllIllIIlIIllIlIllIlIlIIlllIIIllIlllIIlIllIIIlllIIll()) {
                final Iterator iterator = this.bV.iterator();
                while (iterator.hasNext()) {
                    ((IIllIIlllIllIlllIlllIIlllIlIIllIIIlIIIlIIIllIlIllIlIlllIIlIIllIIlIIllIIIlllIIlIlllIlIIllIIlIIllIIlllIIlllIIIllIIlllIIlIIlIIllIllIIIllIllIIlIIlIIllIIlIlllIllIlIIlIlllIlIllIIllIlIlllIIIllIlIIllIIlIlIIIlIIllIllIIlllIIIll)iterator.next()).IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(chunkData, illIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll);
                }
                illIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll.IIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll(true);
            }
            final Unit instance = Unit.INSTANCE;
        }
        illIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll((Function4<? super Integer, ? super Integer, ? super Integer, ? super Byte, Unit>)new Function4<Integer, Integer, Integer, Byte, Unit>() {
            final /* synthetic */ IIIlIlIllIllIIllIllIIlIlllIIIllIIIlIlllIIlIIllIIlIIIllIIlllIIIlIllIIllIIIllIlIlllIIlIIllIIllIIllIIIllIIllIIIllIIllIllIIIlllIIIlIlllIIIlllIlIlllIIIllIIllIlIlllIIlllIllIIlIIlllIIlIIllIlIllIllIlllIIIlIIlIIllIIlIIllIIIlIIllIIlIl bW;
            
            public final void IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(final int n, final int n2, final int n3, final byte b) {
                if (b == IIIlIlIllIllIIllIllIIlIlllIIIllIIIlIlllIIlIIllIIlIIIllIIlllIIIlIllIIllIIIllIlIlllIIlIIllIIllIIllIIIllIIllIIIllIIllIllIIIlllIIIlIlllIIIlllIlIlllIIIllIIllIlIlllIIlllIllIIlIIlllIIlIIllIlIllIllIlllIIIlIIlIIllIIlIIllIIIlIIllIIlIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(this.bW)) {
                    return;
                }
                final BaseChunk baseChunk = chunkData.getSections().get((n2 >> 4) - illIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll.IIllIIIllIIlIIllIIlllIlllIIIllIIIllIIllIIIlIllIllIlIIllIIIlIIIlIIIlIlIIlIllIIIlIllIIllIIlIIlIllIlIllIIlIlllIIllIlllIIIllIIllIIllIIllIIllIIllIIllIIIlIllIlIIIllIIIllIIIllIllIlllIllIlIIIllIIIlIIlIIIlIlIllIIIlllIIlIIllIlllIIll());
                if (baseChunk == null) {
                    return;
                }
                baseChunk.set(n, n2 & 0xF, n3, IIIlIlIllIllIIllIllIIlIlllIIIllIIIlIlllIIlIIllIIlIIIllIIlllIIIlIllIIllIIIllIlIlllIIlIIllIIllIIllIIIllIIllIIIllIIllIllIIIlllIIIlIlllIIIlllIlIlllIIIllIIllIlIlllIIlllIllIIlIIlllIIlIIllIlIllIllIlllIIIlIIlIIllIIlIIllIIIlIIllIIlIl.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(this.bW).get(b));
            }
        });
    }
    
    @Override
    public boolean shouldNotifyBlockUpdate(@NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        return this.shouldNotifyBlockUpdate(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
    }
    
    @Override
    public boolean shouldNotifyBlockUpdate(final int n, final int n2, final int n3) {
        final IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll illIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll = this.bT.IllIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll(n >> 4, n3 >> 4);
        final int n4 = n & 0xF;
        final int n5 = n3 & 0xF;
        if (illIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll.IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(n4, n2, n5) < 0) {
            return false;
        }
        illIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll.IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll(n4, n2, n5);
        return true;
    }
    
    @Override
    public void onBlockPlace(@NotNull final Block block) {
        DefaultImpls.onBlockPlace(this, block);
    }
    
    @Override
    public void onBlockPlace(@NotNull final Block block, final boolean b) {
        DefaultImpls.onBlockPlace(this, block, b);
    }
    
    public static final /* synthetic */ byte IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final IIIlIlIllIllIIllIllIIlIlllIIIllIIIlIlllIIlIIllIIlIIIllIIlllIIIlIllIIllIIIllIlIlllIIlIIllIIllIIllIIIllIIllIIIllIIllIllIIIlllIIIlIlllIIIlllIlIlllIIIllIIllIlIlllIIlllIllIIlIIlllIIlIIllIlIllIllIlllIIIlIIlIIllIIlIIllIIIlIIllIIlIl iiIlIlIllIllIIllIllIIlIlllIIIllIIIlIlllIIlIIllIIlIIIllIIlllIIIlIllIIllIIIllIlIlllIIlIIllIIllIIllIIIllIIllIIIllIIllIllIIIlllIIIlIlllIIIlllIlIlllIIIllIIllIlIlllIIlllIllIIlIIlllIIlIIllIlIllIllIlllIIIlIIlIIllIIlIIllIIIlIIllIIlIl) {
        return iiIlIlIllIllIIllIllIIlIlllIIIllIIIlIlllIIlIIllIIlIIIllIIlllIIIlIllIIllIIIllIlIlllIIlIIllIIllIIllIIIllIIllIIIllIIllIllIIIlllIIIlIlllIIIlllIlIlllIIIllIIllIlIlllIIlllIllIIlIIlllIIlIIllIlIllIllIlllIIIlIIlIIllIIlIIllIIIlIIllIIlIl.bU;
    }
    
    public static final /* synthetic */ Byte2IntMap IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final IIIlIlIllIllIIllIllIIlIlllIIIllIIIlIlllIIlIIllIIlIIIllIIlllIIIlIllIIllIIIllIlIlllIIlIIllIIllIIllIIIllIIllIIIllIIllIllIIIlllIIIlIlllIIIlllIlIlllIIIllIIllIlIlllIIlllIllIIlIIlllIIlIIllIlIllIllIlllIIIlIIlIIllIIlIIllIIIlIIllIIlIl iiIlIlIllIllIIllIllIIlIlllIIIllIIIlIlllIIlIIllIIlIIIllIIlllIIIlIllIIllIIIllIlIlllIIlIIllIIllIIllIIIllIIllIIIllIIllIllIIIlllIIIlIlllIIIlllIlIlllIIIllIIllIlIlllIIlllIllIIlIIlllIIlIIllIlIllIllIlllIIIlIIlIIllIIlIIllIIIlIIllIIlIl) {
        return iiIlIlIllIllIIllIllIIlIlllIIIllIIIlIlllIIlIIllIIlIIIllIIlllIIIlIllIIllIIIllIlIlllIIlIIllIIllIIllIIIllIIllIIIllIIllIllIIIlllIIIlIlllIIIlllIlIlllIIIllIIllIlIlllIIlllIllIIlIIlllIIlIIllIlIllIllIlllIIIlIIlIIllIIlIIllIIIlIIllIIlIl.bS;
    }
}
