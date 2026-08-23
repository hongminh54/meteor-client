// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import dev.imanity.antixray.api.chunk.DirectChunk;
import java.util.concurrent.ThreadLocalRandom;
import dev.imanity.antixray.api.chunk.ChunkData;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class IIllIIIlllIlIIlIIlllIllIIlllIIlllIlIIIllIIIlllIIlIIllIllIllIIllIIIllIIlllIllIIIlIllIIlIIlllIIllIllIlIIllIllIIllIIIllIIlIIlllIIlIIIlIlllIIllIIllIIlllIIllIIlllIIIlllIlIIlllIIllIIllIIlIIlIllIIllIlllIIIllIIllIIllIlllIIIllIlllIIllIllIIIlIl implements IIllIIlllIllIlllIlllIIlllIlIIllIIIlIIIlIIIllIlIllIlIlllIIlIIllIIlIIllIIIlllIIlIlllIlIIllIIlIIllIIlllIIlllIIIllIIlllIIlIIlIIllIllIIIllIllIIlIIlIIllIIlIlllIllIlIIlIlllIlIllIIllIlIlllIIIllIlIIllIIlIlIIIlIIllIllIIlllIIIll
{
    @NotNull
    private final IIllIIlllIIIllIIIlllIlllIIIlIIlIIlIlllIlllIIllIIIllIIlIllIIIllIlllIIlIIIlIllIllIllIlllIllIlIIllIIIllIlIIIllIIllIIIllIlIIIllIllIIIlllIlllIlIIlllIIlIIIllIllIIlllIllIIllIIlllIlIlIIIlllIIlIIlllIIlllIIIllIlIllIIllIllIlllIIlIlIlIlll bZ;
    @NotNull
    private final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl ca;
    private final byte cb;
    private final int cc;
    private final int cd;
    private final int ce;
    private final int cf;
    
    public IIllIIIlllIlIIlIIlllIllIIlllIIlllIlIIIllIIIlllIIlIIllIllIllIIllIIIllIIlllIllIIIlIllIIlIIlllIIllIllIlIIllIllIIllIIIllIIlIIlllIIlIIIlIlllIIllIIllIIlllIIllIIlllIIIlllIlIIlllIIllIIllIIlIIlIllIIllIlllIIIllIIllIIllIlllIIIllIlllIIllIllIIIlIl(@NotNull final IIllIIlllIIIllIIIlllIlllIIIlIIlIIlIlllIlllIIllIIIllIIlIllIIIllIlllIIlIIIlIllIllIllIlllIllIlIIllIIIllIlIIIllIIllIIIllIlIIIllIllIIIlllIlllIlIIlllIIlIIIllIllIIlllIllIIllIIlllIlIlIIIlllIIlIIlllIIlllIIIllIlIllIIllIllIlllIIlIlIlIlll bz, @NotNull final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl ca, final byte cb, final int cc, final int cd, final int ce, final int cf) {
        Intrinsics.checkNotNullParameter((Object)bz, "");
        Intrinsics.checkNotNullParameter((Object)ca, "");
        this.bZ = bz;
        this.ca = ca;
        this.cb = cb;
        this.cc = cc;
        this.cd = cd;
        this.ce = ce;
        this.cf = cf;
    }
    
    @Override
    public void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final ChunkData chunkData, @NotNull final IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll illIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll) {
        Intrinsics.checkNotNullParameter((Object)chunkData, "");
        Intrinsics.checkNotNullParameter((Object)illIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll, "");
        for (int i = 0; i < this.cc; ++i) {
            final ThreadLocalRandom current = ThreadLocalRandom.current();
            final int n = illIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll.IIlIIllIIIlllIIlllIIIlIllIIIllIIllIIlllIIllIIlIIllIIllIllIIIllIIllIlIlIIllIIIlllIIIllIlIllIIIlllIIlIIlllIIlIlIllIlIIIllIIlllIllIIlllIIllIllIIllIlIIllIIIllIIlllIIlllIlIIIllIIIlIIllIIlIllIllIIIlIIIlIlIlIIlIIIlIllIIlllIIIlllIIlllIIIl() * 16 + current.nextInt(0, 16);
            final int nextInt = current.nextInt(this.ce, this.cf);
            final int n2 = illIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll.IlIIlIIIlIlIlllIIlIIlIllIllIIllIlllIlIlIIllIllIIlllIIIlllIIIllIIlllIIIllIIllIlIIIlIIllIIlllIIlllIIllIIIlllIIlllIlIIIlIIlllIIlllIIIllIIlllIIlllIllIIllIIlIIIlIIlIIIllIIlIIIlllIIlllIIIllIIllIllIIIlIIIllIllIIlIIIlllIIIllIIllIIIllIIlIlllIlllIl() * 16 + current.nextInt(0, 16);
            Intrinsics.checkNotNull((Object)current);
            this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(chunkData, illIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll, current, n, nextInt, n2);
        }
    }
    
    private final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final ChunkData chunkData, final IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll illIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll, final ThreadLocalRandom threadLocalRandom, final int n, final int n2, final int n3) {
        final double n4 = threadLocalRandom.nextFloat() * 3.141592653589793;
        final double n5 = (float)(n + 8) + Math.sin(n4) * (float)this.cd / 8.0f;
        final double n6 = (float)(n + 8) - Math.sin(n4) * (float)this.cd / 8.0f;
        final double n7 = (float)(n3 + 8) + Math.cos(n4) * (float)this.cd / 8.0f;
        final double n8 = (float)(n3 + 8) - Math.cos(n4) * (float)this.cd / 8.0f;
        final int n9 = n2 + threadLocalRandom.nextInt(3) - 2;
        final int n10 = n2 + threadLocalRandom.nextInt(3) - 2;
        for (int i = 0; i < this.cd; ++i) {
            final float n11 = i / (float)this.cd;
            final double n12 = n5 + (n6 - n5) * n11;
            final double n13 = n9 + (n10 - n9) * (double)n11;
            final double n14 = n7 + (n8 - n7) * n11;
            final double n15 = threadLocalRandom.nextDouble() * this.cd / 16.0;
            final double n16 = ((float)Math.sin(3.1415927f * n11) + 1.0f) * n15 + 1.0;
            final double n17 = ((float)Math.sin(3.1415927f * n11) + 1.0f) * n15 + 1.0;
            final int n18 = (int)Math.floor(n12 - n16 / 2.0);
            final int n19 = (int)Math.floor(n13 - n17 / 2.0);
            final int n20 = (int)Math.floor(n14 - n16 / 2.0);
            final int n21 = (int)Math.floor(n12 + n16 / 2.0);
            final int n22 = (int)Math.floor(n13 + n17 / 2.0);
            final int n23 = (int)Math.floor(n14 + n16 / 2.0);
            int n24 = n18;
            if (n24 <= n21) {
                while (true) {
                    final double n25 = (n24 + 0.5 - n12) / (n16 / 2.0);
                    if (n25 * n25 < 1.0) {
                        int n26 = n19;
                        if (n26 <= n22) {
                            while (true) {
                                final double n27 = (n26 + 0.5 - n13) / (n17 / 2.0);
                                if (n25 * n25 + n27 * n27 < 1.0) {
                                    int n28 = n20;
                                    if (n28 <= n23) {
                                        while (true) {
                                            final double n29 = (n28 + 0.5 - n14) / (n16 / 2.0);
                                            if (n25 * n25 + n27 * n27 + n29 * n29 < 1.0) {
                                                final IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll = this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(n24, n28, illIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll);
                                                if (this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(chunkData.getChunkRegion().getCurrent(), chunkData, n24, n26, n28)) {
                                                    iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(n24 & 0xF, n26, n28 & 0xF, this.cb);
                                                }
                                            }
                                            if (n28 == n23) {
                                                break;
                                            }
                                            ++n28;
                                        }
                                    }
                                }
                                if (n26 == n22) {
                                    break;
                                }
                                ++n26;
                            }
                        }
                    }
                    if (n24 == n21) {
                        break;
                    }
                    ++n24;
                }
            }
        }
    }
    
    private final boolean IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final DirectChunk directChunk, final ChunkData chunkData, final int n, final int n2, final int n3) {
        return this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(directChunk, chunkData, n, n2, n3) && this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(directChunk, chunkData, n + 1, n2, n3) && this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(directChunk, chunkData, n - 1, n2, n3) && this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(directChunk, chunkData, n, n2 + 1, n3) && this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(directChunk, chunkData, n, n2 - 1, n3) && this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(directChunk, chunkData, n, n2, n3 + 1) && this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(directChunk, chunkData, n, n2, n3 - 1);
    }
    
    private final boolean IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final DirectChunk directChunk, final ChunkData chunkData, final int n, final int n2, final int n3) {
        return this.ca.getGlobalIds().contains(this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(n, n3, directChunk, chunkData).getBlockIdAt(n & 0xF, n2, n3 & 0xF));
    }
    
    private final DirectChunk IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final int n, final int n2, final DirectChunk directChunk, final ChunkData chunkData) {
        final int n3 = n >> 4;
        final int n4 = n2 >> 4;
        return (directChunk.getX() == n3 && directChunk.getZ() == n4) ? directChunk : chunkData.getChunkAt(n3, n4);
    }
    
    private final IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final int n, final int n2, final IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll illIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll) {
        final int n3 = n >> 4;
        final int n4 = n2 >> 4;
        return (illIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll.IIlIIllIIIlllIIlllIIIlIllIIIllIIllIIlllIIllIIlIIllIIllIllIIIllIIllIlIlIIllIIIlllIIIllIlIllIIIlllIIlIIlllIIlIlIllIlIIIllIIlllIllIIlllIIllIllIIllIlIIllIIIllIIlllIIlllIlIIIllIIIlIIllIIlIllIllIIIlIIIlIlIlIIlIIIlIllIIlllIIIlllIIlllIIIl() == n3 && illIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll.IlIIlIIIlIlIlllIIlIIlIllIllIIllIlllIlIlIIllIllIIlllIIIlllIIIllIIlllIIIllIIllIlIIIlIIllIIlllIIlllIIllIIIlllIIlllIlIIIlIIlllIIlllIIIllIIlllIIlllIllIIllIIlIIIlIIlIIIllIIlIIIlllIIlllIIIllIIllIllIIIlIIIllIllIIlIIIlllIIIllIIllIIIllIIlIlllIlllIl() == n4) ? illIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll : this.bZ.IllIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll(n3, n4);
    }
}
