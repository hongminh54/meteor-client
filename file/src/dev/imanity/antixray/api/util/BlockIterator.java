// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.util;

import java.util.NoSuchElementException;
import java.util.Iterator;

public final class BlockIterator implements Iterator<int[]>
{
    private int x;
    private int y;
    private int z;
    private int stepX;
    private int stepY;
    private int stepZ;
    private double tMax;
    private double tMaxX;
    private double tMaxY;
    private double tMaxZ;
    private double tDeltaX;
    private double tDeltaY;
    private double tDeltaZ;
    private int[] ref;
    private int[] refSwap;
    private int[] next;
    
    public BlockIterator(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        this.ref = new int[3];
        this.refSwap = new int[3];
        this.initialize(n, n2, n3, n4, n5, n6);
    }
    
    public BlockIterator(final int n, final int n2, final int n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9) {
        this.ref = new int[3];
        this.refSwap = new int[3];
        this.initialize(n, n2, n3, n4, n5, n6, n7, n8, n9);
    }
    
    public BlockIterator(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7) {
        this.ref = new int[3];
        this.refSwap = new int[3];
        this.initialize(n, n2, n3, n4, n5, n6, n7);
    }
    
    public BlockIterator(final int n, final int n2, final int n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9, final double n10) {
        this.ref = new int[3];
        this.refSwap = new int[3];
        this.initialize(n, n2, n3, n4, n5, n6, n7, n8, n9, n10);
    }
    
    public BlockIterator(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final boolean b) {
        this.ref = new int[3];
        this.refSwap = new int[3];
        if (b) {
            this.initializeNormalized(n, n2, n3, n4, n5, n6, n7);
        }
        else {
            this.initialize(n, n2, n3, n4, n5, n6, n7);
        }
    }
    
    public BlockIterator(final int n, final int n2, final int n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9, final double n10, final boolean b) {
        this.ref = new int[3];
        this.refSwap = new int[3];
        if (b) {
            this.initializeNormalized(n, n2, n3, n4, n5, n6, n7, n8, n9, n10);
        }
        else {
            this.initialize(n, n2, n3, n4, n5, n6, n7, n8, n9, n10);
        }
    }
    
    public BlockIterator initialize(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        return this.initialize(floor(n), floor(n2), floor(n3), n, n2, n3, n4, n5, n6);
    }
    
    public BlockIterator initialize(final int n, final int n2, final int n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9) {
        final double n10 = n7 - n4;
        final double n11 = n8 - n5;
        final double n12 = n9 - n6;
        final double sqrt = Math.sqrt(n10 * n10 + n11 * n11 + n12 * n12);
        final double n13 = (sqrt == 0.0) ? Double.NaN : sqrt;
        return this.initializeNormalized(n, n2, n3, n4, n5, n6, n10 / n13, n11 / n13, n12 / n13, sqrt);
    }
    
    public BlockIterator initialize(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7) {
        return this.initialize(floor(n), floor(n2), floor(n3), n, n2, n3, n4, n5, n6, n7);
    }
    
    public BlockIterator initialize(final int n, final int n2, final int n3, final double n4, final double n5, final double n6, double n7, double n8, double n9, final double n10) {
        final double signum = Math.signum(n10);
        n7 *= signum;
        n8 *= signum;
        n9 *= signum;
        double sqrt = Math.sqrt(n7 * n7 + n8 * n8 + n9 * n9);
        if (sqrt == 0.0) {
            sqrt = Double.NaN;
        }
        n7 /= sqrt;
        n8 /= sqrt;
        n9 /= sqrt;
        return this.initializeNormalized(n, n2, n3, n4, n5, n6, n7, n8, n9, Math.abs(n10));
    }
    
    public BlockIterator initializeNormalized(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double a) {
        return this.initializeNormalized(floor(n), floor(n2), floor(n3), n, n2, n3, n4, n5, n6, Math.abs(a));
    }
    
    public BlockIterator initializeNormalized(final int x, final int y, final int z, final double n, final double n2, final double n3, final double a, final double a2, final double a3, final double tMax) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.tMax = tMax;
        this.stepX = ((a < 0.0) ? -1 : 1);
        this.stepY = ((a2 < 0.0) ? -1 : 1);
        this.stepZ = ((a3 < 0.0) ? -1 : 1);
        this.tMaxX = ((a == 0.0) ? Double.POSITIVE_INFINITY : ((x + (this.stepX + 1) / 2 - n) / a));
        this.tMaxY = ((a2 == 0.0) ? Double.POSITIVE_INFINITY : ((y + (this.stepY + 1) / 2 - n2) / a2));
        this.tMaxZ = ((a3 == 0.0) ? Double.POSITIVE_INFINITY : ((z + (this.stepZ + 1) / 2 - n3) / a3));
        this.tDeltaX = 1.0 / Math.abs(a);
        this.tDeltaY = 1.0 / Math.abs(a2);
        this.tDeltaZ = 1.0 / Math.abs(a3);
        this.next = this.ref;
        this.ref[0] = x;
        this.ref[1] = y;
        this.ref[2] = z;
        return this;
    }
    
    public int[] calculateNext() {
        if (this.tMaxX < this.tMaxY) {
            if (this.tMaxZ < this.tMaxX) {
                if (this.tMaxZ <= this.tMax) {
                    this.z += this.stepZ;
                    this.ref[0] = this.x;
                    this.ref[1] = this.y;
                    this.ref[2] = this.z;
                    this.tMaxZ += this.tDeltaZ;
                }
                else {
                    this.next = null;
                }
            }
            else if (this.tMaxX <= this.tMax) {
                if (this.tMaxZ == this.tMaxX) {
                    this.z += this.stepZ;
                    this.tMaxZ += this.tDeltaZ;
                }
                this.x += this.stepX;
                this.ref[0] = this.x;
                this.ref[1] = this.y;
                this.ref[2] = this.z;
                this.tMaxX += this.tDeltaX;
            }
            else {
                this.next = null;
            }
        }
        else if (this.tMaxY < this.tMaxZ) {
            if (this.tMaxY <= this.tMax) {
                if (this.tMaxX == this.tMaxY) {
                    this.x += this.stepX;
                    this.tMaxX += this.tDeltaX;
                }
                this.y += this.stepY;
                this.ref[0] = this.x;
                this.ref[1] = this.y;
                this.ref[2] = this.z;
                this.tMaxY += this.tDeltaY;
            }
            else {
                this.next = null;
            }
        }
        else if (this.tMaxZ <= this.tMax) {
            if (this.tMaxX == this.tMaxZ) {
                this.x += this.stepX;
                this.tMaxX += this.tDeltaX;
            }
            if (this.tMaxY == this.tMaxZ) {
                this.y += this.stepY;
                this.tMaxY += this.tDeltaY;
            }
            this.z += this.stepZ;
            this.ref[0] = this.x;
            this.ref[1] = this.y;
            this.ref[2] = this.z;
            this.tMaxZ += this.tDeltaZ;
        }
        else {
            this.next = null;
        }
        return this.next;
    }
    
    @Override
    public boolean hasNext() {
        return this.next != null;
    }
    
    @Override
    public int[] next() {
        final int[] next = this.next;
        if (next == null) {
            throw new NoSuchElementException();
        }
        final int[] ref = this.ref;
        this.ref = this.refSwap;
        this.refSwap = ref;
        this.next = this.ref;
        this.calculateNext();
        return next;
    }
    
    private static int floor(final double n) {
        final int n2 = (int)n;
        return (n < n2) ? (n2 - 1) : n2;
    }
}
