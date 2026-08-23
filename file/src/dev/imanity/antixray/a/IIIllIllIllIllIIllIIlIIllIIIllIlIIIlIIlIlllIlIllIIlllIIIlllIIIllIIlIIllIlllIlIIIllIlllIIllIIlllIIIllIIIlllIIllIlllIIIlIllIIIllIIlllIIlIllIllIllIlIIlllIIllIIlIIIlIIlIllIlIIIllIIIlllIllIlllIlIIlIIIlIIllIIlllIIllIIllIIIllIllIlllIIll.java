// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import kotlin.Metadata;
import kotlin.jvm.internal.Lambda;
import dev.imanity.antixray.api.DirectionalVector;
import org.bukkit.util.Vector;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import dev.imanity.antixray.api.RaytraceDebugger;
import io.fairyproject.mc.util.BlockPosition;
import dev.imanity.antixray.api.Vision;
import dev.imanity.antixray.api.data.DataProvider;
import org.jetbrains.annotations.Nullable;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.Function;
import kotlin.jvm.internal.FunctionAdapter;
import dev.imanity.antixray.api.data.DirectDataProvider;
import org.bukkit.entity.Player;
import kotlin.jvm.internal.Intrinsics;
import dev.imanity.antixray.api.util.BlockIterator;
import org.jetbrains.annotations.NotNull;

public final class IIIllIllIllIllIIllIIlIIllIIIllIlIIIlIIlIlllIlIllIIlllIIIlllIIIllIIlIIllIlllIlIIIllIlllIIllIIlllIIIllIIIlllIIllIlllIIIlIllIIIllIIlllIIlIllIllIllIlIIlllIIllIIlIIIlIIlIllIlIIIllIIIlllIllIlllIlIIlIIIlIIllIIlllIIllIIllIIIllIllIlllIIll implements IIllIIIlllIIlllIIllIlIIIlllIIIllIlIIllIIlIllIIlllIIIlIIIlIIIlIIlIIlIIIllIlllIllIIIllIIllIlIlllIllIIllIIlIIlllIlllIIlllIIIlllIIllIlllIlIIllIllIlIIllIIIlIIIlIIIllIIlIIlIIlllIllIlllIlIIllIIIllIIlIIlllIIlIIllIlllIIIlIlIIllIIIlllIIIlIll
{
    @NotNull
    private final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll ag;
    @NotNull
    private final BlockIterator ah;
    private IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll ai;
    
    public IIIllIllIllIllIIllIIlIIllIIIllIlIIIlIIlIlllIlIllIIlllIIIlllIIIllIIlIIllIlllIlIIIllIlllIIllIIlllIIIllIIIlllIIllIlllIIIlIllIIIllIIlllIIlIllIllIllIlIIlllIIllIIlIIIlIIlIllIlIIIllIIIlllIllIlllIlIIlIIIlIIllIIlllIIllIIllIIIllIllIlllIIll(@NotNull final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll ag) {
        Intrinsics.checkNotNullParameter((Object)ag, "");
        this.ag = ag;
        this.ah = new BlockIterator(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }
    
    @Override
    public void cleanup() {
    }
    
    @Override
    public void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Player player, @NotNull final DirectDataProvider directDataProvider) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)directDataProvider, "");
        this.ai = new IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll(player, new IIIllIlllIIlllIIIllIIllIIllIlIllIIIllIIIllIIlIIIlIIlIlllIlIIllIIIlllIlIIIllIIIlIIllIIlIllIIIlllIIllIllIIlIIIlllIlllIIlIIlIIlIIIlIIlIlllIIllIllIIlIIllIIlIIllIlIllIIlIIIlIIllIIllIlIllIlIIlllIIIllIIIlllIIIlIlIIlIIIllIIIlllIlIIll(), directDataProvider, true);
    }
    
    @Override
    public boolean IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Vision vision, @NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)vision, "");
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        final Vector position = vision.getOrigin().getPosition();
        if (this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(vision.getPlayer(), position.getX(), position.getY(), position.getZ(), vision.getOrigin().getDirection(), blockPosition)) {
            if (RaytraceDebugger.INSTANCE.isActive()) {
                RaytraceDebugger.INSTANCE.debug(vision.getPlayer(), blockPosition.getX(), blockPosition.getY(), blockPosition.getZ(), (Function1<? super Player, Unit>)IIIllIllIllIllIIllIIlIIllIIIllIlIIIlIIlIlllIlIllIIlllIIIlllIIIllIIlIIllIlllIlIIIllIlllIIllIIlllIIIllIIIlllIIllIlllIIIlIllIIIllIIlllIIlIllIllIllIlIIlllIIllIIlIIIlIIlIllIlIIIllIIIlllIllIlllIlIIlIIIlIIllIIlllIIllIIllIIIllIllIlllIIll$1.ak);
            }
            return true;
        }
        final DirectionalVector thirdPersonFront = vision.getThirdPersonFront();
        if (thirdPersonFront != null) {
            final DirectionalVector directionalVector = thirdPersonFront;
            if (this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(vision.getPlayer(), directionalVector.getPosition().getX(), directionalVector.getPosition().getY(), directionalVector.getPosition().getZ(), directionalVector.getDirection(), blockPosition)) {
                if (RaytraceDebugger.INSTANCE.isActive()) {
                    RaytraceDebugger.INSTANCE.debug(vision.getPlayer(), blockPosition.getX(), blockPosition.getY(), blockPosition.getZ(), (Function1<? super Player, Unit>)IIIllIllIllIllIIllIIlIIllIIIllIlIIIlIIlIlllIlIllIIlllIIIlllIIIllIIlIIllIlllIlIIIllIlllIIllIIlllIIIllIIIlllIIllIlllIIIlIllIIIllIIlllIIlIllIllIllIlIIlllIIllIIlIIIlIIlIllIlIIIllIIIlllIllIlllIlIIlIIIlIIllIIlllIIllIIllIIIllIllIlllIIll$2.al);
                }
                return true;
            }
        }
        final DirectionalVector thirdPersonBack = vision.getThirdPersonBack();
        if (thirdPersonBack != null) {
            final DirectionalVector directionalVector2 = thirdPersonBack;
            if (this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(vision.getPlayer(), directionalVector2.getPosition().getX(), directionalVector2.getPosition().getY(), directionalVector2.getPosition().getZ(), directionalVector2.getDirection(), blockPosition)) {
                if (RaytraceDebugger.INSTANCE.isActive()) {
                    RaytraceDebugger.INSTANCE.debug(vision.getPlayer(), blockPosition.getX(), blockPosition.getY(), blockPosition.getZ(), (Function1<? super Player, Unit>)IIIllIllIllIllIIllIIlIIllIIIllIlIIIlIIlIlllIlIllIIlllIIIlllIIIllIIlIIllIlllIlIIIllIlllIIllIIlllIIIllIIIlllIIllIlllIIIlIllIIIllIIlllIIlIllIllIllIlIIlllIIllIIlIIIlIIlIllIlIIIllIIIlllIllIlllIlIIlIIIlIIllIIlllIIllIIllIIIllIllIlllIIll$3.am);
                }
                return true;
            }
        }
        return false;
    }
    
    private final boolean IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final Player player, final double n, final double n2, final double n3, final Vector vector, final BlockPosition blockPosition) {
        final int x = blockPosition.getX();
        final int y = blockPosition.getY();
        final int z = blockPosition.getZ();
        final double n4 = x + 0.5;
        final double n5 = y + 0.5;
        final double n6 = z + 0.5;
        final double n7 = n - n4;
        final double n8 = n2 - n5;
        final double n9 = n3 - n6;
        final double n10 = n7 * n7 + n8 * n8 + n9 * n9;
        final double x2 = vector.getX();
        final double y2 = vector.getY();
        final double z2 = vector.getZ();
        boolean b = false;
        if (n10 <= this.ag.IllIIlIIllIllIIlllIlllIlIIllIlIllIIllIlllIIllIIIlIIIlllIIllIIllIlIIIlIIllIIlIIIlIllIIllIIlIIllIIIlIIlllIIllIIIlIIlIlIIIlIllIIlIIIllIIlIlIIIlIIIllIIlllIIllIIlllIIlIIIllIIllIIlllIllIllIIlIIllIIlllIIIlllIllIllIIlIIlllIlllIIIlIIIll() * this.ag.IllIIlIIllIllIIlllIlllIlIIllIlIllIIllIlllIIllIIIlIIIlllIIllIIllIlIIIlIIllIIlIIIlIllIIllIIlIIllIIIlIIlllIIllIIIlIIlIlIIIlIllIIlIIIllIIlIlIIIlIIIllIIlllIIllIIlllIIlIIIllIIllIIlllIllIllIIlIIllIIlllIIIlllIllIllIIlIIlllIlllIIIlIIIll()) {
            IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll ai;
            if ((ai = this.ai) == null) {
                Intrinsics.throwUninitializedPropertyAccessException("");
                ai = null;
            }
            if (ai.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(x, y, z, n4, n5, n6, n7, n8, n9, n10, x2, y2, z2)) {
                b = true;
            }
        }
        else if (RaytraceDebugger.INSTANCE.isActive()) {
            RaytraceDebugger.INSTANCE.debug(player, x, y, z, (Function1<? super Player, Unit>)new Function1<Player, Unit>() {
                public final void IllIllIIIlIllIllIIllIIIlllIlIllIIlIIlIlIIllIllIIIlllIllIllIIIllIlIlIIIlIIIllIIllIIlIllIIlIIlllIIlIIlllIllIIIllIIllIIIlIllIlllIIllIIIllIlIlIIIlIlIIllIIllIIlllIIIlIIllIllIllIIIlllIIlllIllIIlIIlIIlIIllIIllIIlIIIlllIIlIIlll(@NotNull final Player player) {
                    Intrinsics.checkNotNullParameter((Object)player, "");
                    player.sendMessage("§c§l[AntiXray] §7Distance squared for " + x + ' ' + y + ' ' + z + " is greater than raytrace radius");
                }
            });
        }
        return b;
    }
}
