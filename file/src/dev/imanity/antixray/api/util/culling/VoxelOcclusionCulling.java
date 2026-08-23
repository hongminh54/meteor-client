// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.util.culling;

import java.util.Arrays;
import dev.imanity.antixray.api.util.MathUtilities;
import dev.imanity.antixray.api.util.Vec3d;
import java.util.BitSet;
import dev.imanity.antixray.api.util.culling.cache.OcclusionCache;
import dev.imanity.antixray.api.data.DataProvider;

public class VoxelOcclusionCulling
{
    private static final int ON_MIN_X = 1;
    private static final int ON_MAX_X = 2;
    private static final int ON_MIN_Y = 4;
    private static final int ON_MAX_Y = 8;
    private static final int ON_MIN_Z = 16;
    private static final int ON_MAX_Z = 32;
    private final int reach;
    private final double aabbExpansion;
    private final DataProvider provider;
    private OcclusionCache cache;
    private final BitSet skipList;
    private final Vec3d[] targetPoints;
    private final Vec3d targetPos;
    private final Vec3d rayDirScratch;
    private final Vec3d rInvScratch;
    private final int[] cameraPos;
    private final boolean[] dotselectors;
    private boolean allowRayChecks;
    private final int[] lastHitBlock;
    private boolean allowWallClipping;
    private boolean canWallClip;
    
    public VoxelOcclusionCulling(final int reach, final DataProvider provider, final double aabbExpansion) {
        this.skipList = new BitSet();
        this.targetPoints = new Vec3d[15];
        this.targetPos = new Vec3d(0.0, 0.0, 0.0);
        this.rayDirScratch = new Vec3d(0.0, 0.0, 0.0);
        this.rInvScratch = new Vec3d(0.0, 0.0, 0.0);
        this.cameraPos = new int[3];
        this.dotselectors = new boolean[14];
        this.allowRayChecks = false;
        this.lastHitBlock = new int[3];
        this.allowWallClipping = false;
        this.canWallClip = false;
        this.reach = reach;
        this.provider = provider;
        this.aabbExpansion = aabbExpansion;
        for (int i = 0; i < this.targetPoints.length; ++i) {
            this.targetPoints[i] = new Vec3d(0.0, 0.0, 0.0);
        }
    }
    
    public void setCanWallClip(final boolean canWallClip) {
        this.canWallClip = canWallClip;
    }
    
    public boolean isAABBVisible(final Vec3d vec3d, final Vec3d vec3d2, final Vec3d vec3d3, final OcclusionCache cache) {
        this.cache = cache;
        try {
            final int floor = MathUtilities.floor(vec3d2.x + this.aabbExpansion);
            final int floor2 = MathUtilities.floor(vec3d2.y + this.aabbExpansion);
            final int floor3 = MathUtilities.floor(vec3d2.z + this.aabbExpansion);
            final int floor4 = MathUtilities.floor(vec3d.x - this.aabbExpansion);
            final int floor5 = MathUtilities.floor(vec3d.y - this.aabbExpansion);
            final int floor6 = MathUtilities.floor(vec3d.z - this.aabbExpansion);
            this.cameraPos[0] = MathUtilities.floor(vec3d3.x);
            this.cameraPos[1] = MathUtilities.floor(vec3d3.y);
            this.cameraPos[2] = MathUtilities.floor(vec3d3.z);
            final Relative from = Relative.from(floor4, floor, this.cameraPos[0]);
            final Relative from2 = Relative.from(floor5, floor2, this.cameraPos[1]);
            final Relative from3 = Relative.from(floor6, floor3, this.cameraPos[2]);
            if (from == Relative.INSIDE && from2 == Relative.INSIDE && from3 == Relative.INSIDE) {
                return true;
            }
            this.skipList.clear();
            int bitIndex = 0;
            for (int i = floor4; i <= floor; ++i) {
                for (int j = floor5; j <= floor2; ++j) {
                    for (int k = floor6; k <= floor3; ++k) {
                        final int cacheValue = this.getCacheValue(i, j, k);
                        if (cacheValue == 1) {
                            return true;
                        }
                        if (cacheValue != 0) {
                            this.skipList.set(bitIndex);
                        }
                        ++bitIndex;
                    }
                }
            }
            this.allowRayChecks = false;
            int bitIndex2 = 0;
            for (int l = floor4; l <= floor; ++l) {
                final boolean b = false;
                final byte b2 = (byte)((byte)((false | l == floor4) ? 1 : 0) | ((l == floor) ? 2 : 0));
                final byte b3 = (byte)((byte)((b | (l == floor4 && from == Relative.POSITIVE)) ? 1 : 0) | ((l == floor && from == Relative.NEGATIVE) ? 2 : 0));
                for (int n = floor5; n <= floor2; ++n) {
                    final byte b4 = b2;
                    final byte b5 = b3;
                    final byte b6 = (byte)((byte)(b4 | ((n == floor5) ? 4 : 0)) | ((n == floor2) ? 8 : 0));
                    final byte b7 = (byte)((byte)(b5 | ((n == floor5 && from2 == Relative.POSITIVE) ? 4 : 0)) | ((n == floor2 && from2 == Relative.NEGATIVE) ? 8 : 0));
                    for (int n2 = floor6; n2 <= floor3; ++n2) {
                        final byte b8 = b6;
                        final byte b9 = b7;
                        final byte b10 = (byte)((byte)(b8 | ((n2 == floor6) ? 16 : 0)) | ((n2 == floor3) ? 32 : 0));
                        final byte b11 = (byte)((byte)(b9 | ((n2 == floor6 && from3 == Relative.POSITIVE) ? 16 : 0)) | ((n2 == floor3 && from3 == Relative.NEGATIVE) ? 32 : 0));
                        if (this.skipList.get(bitIndex2)) {
                            ++bitIndex2;
                        }
                        else {
                            if (b11 != 0) {
                                this.targetPos.set(l, n, n2);
                                if (this.isVoxelVisible(vec3d3, this.targetPos, b10, b11)) {
                                    return true;
                                }
                            }
                            ++bitIndex2;
                        }
                    }
                }
            }
            return false;
        }
        catch (final Throwable t) {
            t.printStackTrace();
        }
        finally {
            this.cache = null;
        }
        return true;
    }
    
    private boolean isVoxelVisible(final Vec3d vec3d, final Vec3d vec3d2, final byte b, final byte b2) {
        int n = 0;
        Arrays.fill(this.dotselectors, false);
        if ((b2 & 0x1) == 0x1) {
            this.dotselectors[0] = true;
            if ((b & 0xFFFFFFFE) != 0x0) {
                this.dotselectors[1] = true;
                this.dotselectors[4] = true;
                this.dotselectors[5] = true;
            }
            this.dotselectors[8] = true;
        }
        if ((b2 & 0x4) == 0x4) {
            this.dotselectors[0] = true;
            if ((b & 0xFFFFFFFB) != 0x0) {
                this.dotselectors[3] = true;
                this.dotselectors[4] = true;
                this.dotselectors[7] = true;
            }
            this.dotselectors[9] = true;
        }
        if ((b2 & 0x10) == 0x10) {
            this.dotselectors[0] = true;
            if ((b & 0xFFFFFFEF) != 0x0) {
                this.dotselectors[1] = true;
                this.dotselectors[4] = true;
                this.dotselectors[5] = true;
            }
            this.dotselectors[10] = true;
        }
        if ((b2 & 0x2) == 0x2) {
            this.dotselectors[4] = true;
            if ((b & 0xFFFFFFFD) != 0x0) {
                this.dotselectors[5] = true;
                this.dotselectors[6] = true;
                this.dotselectors[7] = true;
            }
            this.dotselectors[11] = true;
        }
        if ((b2 & 0x8) == 0x8) {
            this.dotselectors[1] = true;
            if ((b & 0xFFFFFFF7) != 0x0) {
                this.dotselectors[2] = true;
                this.dotselectors[5] = true;
                this.dotselectors[6] = true;
            }
            this.dotselectors[12] = true;
        }
        if ((b2 & 0x20) == 0x20) {
            this.dotselectors[2] = true;
            if ((b & 0xFFFFFFDF) != 0x0) {
                this.dotselectors[3] = true;
                this.dotselectors[6] = true;
                this.dotselectors[7] = true;
            }
            this.dotselectors[13] = true;
        }
        if (this.dotselectors[0]) {
            this.targetPoints[n++].setAdd(vec3d2, 0.05, 0.05, 0.05);
        }
        if (this.dotselectors[1]) {
            this.targetPoints[n++].setAdd(vec3d2, 0.05, 0.95, 0.05);
        }
        if (this.dotselectors[2]) {
            this.targetPoints[n++].setAdd(vec3d2, 0.05, 0.95, 0.95);
        }
        if (this.dotselectors[3]) {
            this.targetPoints[n++].setAdd(vec3d2, 0.05, 0.05, 0.95);
        }
        if (this.dotselectors[4]) {
            this.targetPoints[n++].setAdd(vec3d2, 0.95, 0.05, 0.05);
        }
        if (this.dotselectors[5]) {
            this.targetPoints[n++].setAdd(vec3d2, 0.95, 0.95, 0.05);
        }
        if (this.dotselectors[6]) {
            this.targetPoints[n++].setAdd(vec3d2, 0.95, 0.95, 0.95);
        }
        if (this.dotselectors[7]) {
            this.targetPoints[n++].setAdd(vec3d2, 0.95, 0.05, 0.95);
        }
        if (this.dotselectors[8]) {
            this.targetPoints[n++].setAdd(vec3d2, 0.05, 0.5, 0.5);
        }
        if (this.dotselectors[9]) {
            this.targetPoints[n++].setAdd(vec3d2, 0.5, 0.05, 0.5);
        }
        if (this.dotselectors[10]) {
            this.targetPoints[n++].setAdd(vec3d2, 0.5, 0.5, 0.05);
        }
        if (this.dotselectors[11]) {
            this.targetPoints[n++].setAdd(vec3d2, 0.95, 0.5, 0.5);
        }
        if (this.dotselectors[12]) {
            this.targetPoints[n++].setAdd(vec3d2, 0.5, 0.95, 0.5);
        }
        if (this.dotselectors[13]) {
            this.targetPoints[n++].setAdd(vec3d2, 0.5, 0.5, 0.95);
        }
        return this.isVisible(vec3d, this.targetPoints, n);
    }
    
    private boolean rayIntersection(final int[] array, final Vec3d vec3d, final Vec3d vec3d2) {
        this.rInvScratch.set(1.0, 1.0, 1.0);
        final Vec3d div = this.rInvScratch.div(vec3d2);
        final double n = (array[0] - vec3d.x) * div.x;
        final double n2 = (array[0] + 1 - vec3d.x) * div.x;
        final double n3 = (array[1] - vec3d.y) * div.y;
        final double n4 = (array[1] + 1 - vec3d.y) * div.y;
        final double n5 = (array[2] - vec3d.z) * div.z;
        final double n6 = (array[2] + 1 - vec3d.z) * div.z;
        final double max = Math.max(Math.max(Math.min(n, n2), Math.min(n3, n4)), Math.min(n5, n6));
        final double min = Math.min(Math.min(Math.max(n, n2), Math.max(n3, n4)), Math.max(n5, n6));
        return min <= 0.0 && max <= min;
    }
    
    private boolean isVisible(final Vec3d vec3d, final Vec3d[] array, final int n) {
        final int n2 = this.cameraPos[0];
        final int n3 = this.cameraPos[1];
        final int n4 = this.cameraPos[2];
        for (final Vec3d vec3d2 : array) {
            final double a = vec3d.x - vec3d2.x;
            final double a2 = vec3d.y - vec3d2.y;
            final double a3 = vec3d.z - vec3d2.z;
            Label_0530: {
                if (this.allowRayChecks) {
                    this.rayDirScratch.set(a, a2, a3);
                    if (this.rayIntersection(this.lastHitBlock, vec3d, this.rayDirScratch.normalize())) {
                        break Label_0530;
                    }
                }
                final double abs = Math.abs(a);
                final double abs2 = Math.abs(a2);
                final double abs3 = Math.abs(a3);
                final double n5 = 1.0 / abs;
                final double n6 = 1.0 / abs2;
                final double n7 = 1.0 / abs3;
                int n8 = 1;
                int n9;
                double n10;
                if (abs == 0.0) {
                    n9 = 0;
                    n10 = n5;
                }
                else if (vec3d2.x > vec3d.x) {
                    n9 = 1;
                    n8 += MathUtilities.floor(vec3d2.x) - n2;
                    n10 = (float)((n2 + 1 - vec3d.x) * n5);
                }
                else {
                    n9 = -1;
                    n8 += n2 - MathUtilities.floor(vec3d2.x);
                    n10 = (float)((vec3d.x - n2) * n5);
                }
                int n11;
                double n12;
                if (abs2 == 0.0) {
                    n11 = 0;
                    n12 = n6;
                }
                else if (vec3d2.y > vec3d.y) {
                    n11 = 1;
                    n8 += MathUtilities.floor(vec3d2.y) - n3;
                    n12 = (float)((n3 + 1 - vec3d.y) * n6);
                }
                else {
                    n11 = -1;
                    n8 += n3 - MathUtilities.floor(vec3d2.y);
                    n12 = (float)((vec3d.y - n3) * n6);
                }
                int n13;
                double n14;
                if (abs3 == 0.0) {
                    n13 = 0;
                    n14 = n7;
                }
                else if (vec3d2.z > vec3d.z) {
                    n13 = 1;
                    n8 += MathUtilities.floor(vec3d2.z) - n4;
                    n14 = (float)((n4 + 1 - vec3d.z) * n7);
                }
                else {
                    n13 = -1;
                    n8 += n4 - MathUtilities.floor(vec3d2.z);
                    n14 = (float)((vec3d.z - n4) * n7);
                }
                final boolean stepRay = this.stepRay(vec3d, n2, n3, n4, n5, n6, n7, n8, n9, n11, n13, n12, n10, n14);
                this.provider.cleanup();
                if (stepRay) {
                    this.cacheResult(array[0], true);
                    return true;
                }
                this.allowRayChecks = true;
            }
        }
        this.cacheResult(array[0], false);
        return false;
    }
    
    private boolean stepRay(final Vec3d vec3d, int n, int n2, int n3, final double n4, final double n5, final double n6, int i, final int n7, final int n8, final int n9, double n10, double n11, double n12) {
        this.allowWallClipping = this.canWallClip;
        while (i > 1) {
            final int cacheValue = this.getCacheValue(n, n2, n3);
            if (cacheValue == 2 && !this.allowWallClipping) {
                this.lastHitBlock[0] = n;
                this.lastHitBlock[1] = n2;
                this.lastHitBlock[2] = n3;
                return false;
            }
            if (cacheValue == 0) {
                if (!this.provider.prepareChunk(n >> 4, n3 >> 4)) {
                    return false;
                }
                if (this.provider.isOpaqueFullCube(n, n2, n3)) {
                    if (!this.allowWallClipping) {
                        this.cache.setLastHidden();
                        this.lastHitBlock[0] = n;
                        this.lastHitBlock[1] = n2;
                        this.lastHitBlock[2] = n3;
                        return false;
                    }
                }
                else {
                    this.allowWallClipping = false;
                    this.cache.setLastVisible();
                }
            }
            if (cacheValue == 1) {
                this.allowWallClipping = false;
            }
            if (n10 < n11 && n10 < n12) {
                n2 += n8;
                n10 += n5;
            }
            else if (n11 < n10 && n11 < n12) {
                n += n7;
                n11 += n4;
            }
            else {
                n3 += n9;
                n12 += n6;
            }
            --i;
        }
        return true;
    }
    
    private int getCacheValue(int a, int a2, int a3) {
        a -= this.cameraPos[0];
        a2 -= this.cameraPos[1];
        a3 -= this.cameraPos[2];
        if (Math.abs(a) > this.reach - 2 || Math.abs(a2) > this.reach - 2 || Math.abs(a3) > this.reach - 2) {
            return -1;
        }
        return this.cache.getState(a + this.reach, a2 + this.reach, a3 + this.reach);
    }
    
    private void cacheResult(final int n, final int n2, final int n3, final boolean b) {
        final int n4 = n - this.cameraPos[0] + this.reach;
        final int n5 = n2 - this.cameraPos[1] + this.reach;
        final int n6 = n3 - this.cameraPos[2] + this.reach;
        if (b) {
            this.cache.setVisible(n4, n5, n6);
        }
        else {
            this.cache.setHidden(n4, n5, n6);
        }
    }
    
    private void cacheResult(final Vec3d vec3d, final boolean b) {
        final int n = MathUtilities.floor(vec3d.x) - this.cameraPos[0] + this.reach;
        final int n2 = MathUtilities.floor(vec3d.y) - this.cameraPos[1] + this.reach;
        final int n3 = MathUtilities.floor(vec3d.z) - this.cameraPos[2] + this.reach;
        if (b) {
            this.cache.setVisible(n, n2, n3);
        }
        else {
            this.cache.setHidden(n, n2, n3);
        }
    }
    
    public void resetCache() {
        this.cache.resetCache();
    }
    
    private enum Relative
    {
        INSIDE, 
        POSITIVE, 
        NEGATIVE;
        
        public static Relative from(final int n, final int n2, final int n3) {
            if (n2 > n3 && n > n3) {
                return Relative.POSITIVE;
            }
            if (n < n3 && n2 < n3) {
                return Relative.NEGATIVE;
            }
            return Relative.INSIDE;
        }
    }
}
