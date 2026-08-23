// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import dev.imanity.antixray.api.util.BlockIterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import dev.imanity.antixray.api.RaytraceDebugger;
import dev.imanity.antixray.api.data.DataProvider;
import org.bukkit.entity.Player;

public final class IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll
{
    private static final IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll dh;
    private static final IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll di;
    private static final IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll dj;
    private static final IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll dk;
    private static final IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll dl;
    private static final IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll dm;
    private static final IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] dn;
    private static final IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] do;
    private static final IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] dp;
    private static final IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] dq;
    private static final IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] dr;
    private static final IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] ds;
    private static final IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] dt;
    private static final IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] du;
    private static final IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] dv;
    private static final IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] dw;
    private static final IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] dx;
    private static final IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] dy;
    private final Player dz;
    private final IIIllIlllIIlllIIIllIIllIIllIlIllIIIllIIIllIIlIIIlIIlIlllIlIIllIIIlllIlIIIllIIIlIIllIIlIllIIIlllIIllIllIIlIIIlllIlllIIlIIlIIlIIIlIIlIlllIIllIllIIlIIllIIlIIllIlIllIIlIIIlIIllIIllIlIllIlIIlllIIIllIIIlllIIIlIlIIlIIIllIIIlllIlIIll dA;
    private final DataProvider dB;
    private final boolean dC;
    private int dD;
    private int dE;
    private int dF;
    
    public IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll(final Player dz, final IIIllIlllIIlllIIIllIIllIIllIlIllIIIllIIIllIIlIIIlIIlIlllIlIIllIIIlllIlIIIllIIIlIIllIIlIllIIIlllIIllIllIIlIIIlllIlllIIlIIlIIlIIIlIIlIlllIIllIllIIlIIllIIlIIllIlIllIIlIIIlIIllIIllIlIllIlIIlllIIIllIIIlllIIIlIlIIlIIIllIIIlllIlIIll da, final DataProvider db, final boolean dc) {
        this.dz = dz;
        this.dA = da;
        this.dB = db;
        this.dC = dc;
    }
    
    private boolean isOpaqueFullCube(final int n, final int n2, final int n3) {
        if (!this.dB.prepareChunk(n >> 4, n3 >> 4)) {
            if (RaytraceDebugger.INSTANCE.isActive()) {
                RaytraceDebugger.INSTANCE.debug(this.dz, this.dD, this.dE, this.dF, (Function1<? super Player, Unit>)(player -> {
                    player.sendMessage("Chunk not loaded, assume it's opaque " + n + " " + n2 + " " + n3);
                    return null;
                }));
            }
            return true;
        }
        final boolean opaqueFullCube = this.dB.isOpaqueFullCube(n, n2, n3);
        if (opaqueFullCube && RaytraceDebugger.INSTANCE.isActive()) {
            RaytraceDebugger.INSTANCE.debug(this.dz, this.dD, this.dE, this.dF, (Function1<? super Player, Unit>)(player -> {
                player.sendMessage("Block is opaque " + n + " " + n2 + " " + n3);
                return null;
            }));
        }
        return opaqueFullCube;
    }
    
    public boolean IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final int n, final int n2, final int n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9) {
        final double n10 = n + 0.5;
        final double n11 = n2 + 0.5;
        final double n12 = n3 + 0.5;
        final double n13 = n4 - n10;
        final double n14 = n5 - n11;
        final double n15 = n6 - n12;
        return this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(n, n2, n3, n10, n11, n12, n13, n14, n15, n13 * n13 + n14 * n14 + n15 * n15, n7, n8, n9);
    }
    
    public boolean IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final int dd, final int de, final int df, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double a, final double n7, final double n8, final double n9) {
        if (this.dC && (n4 - n7) * n7 + (n5 - n8) * n8 + (n6 - n9) * n9 > 0.0) {
            return false;
        }
        final double sqrt = Math.sqrt(a);
        final double n10 = (sqrt == 0.0) ? Double.NaN : sqrt;
        final BlockIterator iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll = this.dA.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(dd, de, df, n, n2, n3, n4 / n10, n5 / n10, n6 / n10, sqrt);
        this.dD = dd;
        this.dE = de;
        this.dF = df;
        int[] calculateNext;
        while ((calculateNext = iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.calculateNext()) != null) {
            final int n11 = calculateNext[0];
            final int n12 = calculateNext[1];
            final int n13 = calculateNext[2];
            if (this.isOpaqueFullCube(n11, n12, n13) && this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(dd, de, df, calculateNext, n11, n12, n13, n4, n5, n6)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final int n, final int n2, final int n3, final int[] array, int n4, int n5, int n6, final double a, final double a2, final double a3) {
        final double abs = Math.abs(a);
        final double abs2 = Math.abs(a2);
        final double abs3 = Math.abs(a3);
        final double n7 = n4 - n;
        final double n8 = n5 - n2;
        final double n9 = n6 - n3;
        IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] array2;
        IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll;
        IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll2;
        if (abs > abs2) {
            if (abs3 > abs) {
                final double iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll = IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(a3, n9);
                final double n10 = IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll, n7) - a;
                final double n11 = IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll, n8) - a2;
                if (n10 > 0.0) {
                    if (n11 > 0.0) {
                        array2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dy;
                    }
                    else {
                        array2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dx;
                    }
                }
                else if (n11 > 0.0) {
                    array2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dw;
                }
                else {
                    array2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dv;
                }
                if (a3 > 0.0) {
                    ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dm;
                    ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dl;
                }
                else {
                    ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dl;
                    ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dm;
                }
            }
            else {
                final double iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll2 = IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(a, n7);
                final double n12 = IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll2, n8) - a2;
                final double n13 = IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll2, n9) - a3;
                if (n12 > 0.0) {
                    if (n13 > 0.0) {
                        array2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dq;
                    }
                    else {
                        array2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dp;
                    }
                }
                else if (n13 > 0.0) {
                    array2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.do;
                }
                else {
                    array2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dn;
                }
                if (a > 0.0) {
                    ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.di;
                    ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dh;
                }
                else {
                    ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dh;
                    ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.di;
                }
            }
        }
        else if (abs2 > abs3) {
            final double iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll3 = IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(a2, n8);
            final double n14 = IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll3, n9) - a3;
            final double n15 = IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll3, n7) - a;
            if (n14 > 0.0) {
                if (n15 > 0.0) {
                    array2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.du;
                }
                else {
                    array2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dt;
                }
            }
            else if (n15 > 0.0) {
                array2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.ds;
            }
            else {
                array2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dr;
            }
            if (a2 > 0.0) {
                ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dk;
                ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dj;
            }
            else {
                ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dj;
                ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dk;
            }
        }
        else {
            final double iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll4 = IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(a3, n9);
            final double n16 = IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll4, n7) - a;
            final double n17 = IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll4, n8) - a2;
            if (n16 > 0.0) {
                if (n17 > 0.0) {
                    array2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dy;
                }
                else {
                    array2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dx;
                }
            }
            else if (n17 > 0.0) {
                array2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dw;
            }
            else {
                array2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dv;
            }
            if (a3 > 0.0) {
                ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dm;
                ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dl;
            }
            else {
                ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dl;
                ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll2 = IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dm;
            }
        }
        for (int i = 0; i < array2.length; ++i) {
            array2[i].accept(array);
            if (!this.isOpaqueFullCube(array[0], array[1], array[2])) {
                ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll.accept(array);
                n4 = array[0];
                n5 = array[1];
                n6 = array[2];
                if ((n4 == n && n5 == n2 && n6 == n3) || !this.isOpaqueFullCube(n4, n5, n6)) {
                    return false;
                }
                ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll2.accept(array);
            }
        }
        return true;
    }
    
    private static double IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final double n, final double n2) {
        return ((n2 == 0.0 && !Double.isNaN(n)) ? Math.copySign(1.0, n) : n) / n2;
    }
    
    private static double IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final double d, final double n) {
        return ((n == 0.0) ? Math.signum(d) : d) * n;
    }
    
    static {
        dh = (array -> {
            final int n;
            ++array[n];
            return;
        });
        di = (array2 -> {
            final int n2;
            --array2[n2];
            return;
        });
        dj = (array3 -> {
            final int n3;
            ++array3[n3];
            return;
        });
        dk = (array4 -> {
            final int n4;
            --array4[n4];
            return;
        });
        dl = (array5 -> {
            final int n5;
            ++array5[n5];
            return;
        });
        dm = (array6 -> {
            final int n6;
            --array6[n6];
            return;
        });
        dn = new IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] { IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dj, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dl, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dk };
        do = new IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] { IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dj, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dm, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dk };
        dp = new IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] { IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dk, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dl, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dj };
        dq = new IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] { IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dk, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dm, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dj };
        dr = new IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] { IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dl, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dh, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dm };
        ds = new IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] { IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dl, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.di, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dm };
        dt = new IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] { IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dm, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dh, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dl };
        du = new IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] { IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dm, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.di, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dl };
        dv = new IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] { IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dj, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dh, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dk };
        dw = new IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] { IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dk, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dh, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dj };
        dx = new IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] { IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dj, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.di, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dk };
        dy = new IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll[] { IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dk, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.di, IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll.dj };
    }
}
