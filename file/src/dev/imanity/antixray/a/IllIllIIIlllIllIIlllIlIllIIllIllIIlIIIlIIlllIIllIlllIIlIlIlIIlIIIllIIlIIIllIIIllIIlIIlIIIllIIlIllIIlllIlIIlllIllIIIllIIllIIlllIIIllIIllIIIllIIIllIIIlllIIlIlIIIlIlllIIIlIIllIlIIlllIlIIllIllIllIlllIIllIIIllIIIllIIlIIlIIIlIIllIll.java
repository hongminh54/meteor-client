// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import java.lang.reflect.Method;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;
import org.bukkit.World;
import kotlin.jvm.internal.Intrinsics;

public final class IllIllIIIlllIllIIlllIlIllIIllIllIIlIIIlIIlllIIllIlllIIlIlIlIIlIIIllIIlIIIllIIIllIIlIIlIIIllIIlIllIIlllIlIIlllIllIIIllIIllIIlllIIIllIIllIIIllIIIllIIIlllIIlIlIIIlIlllIIIlIIllIlIIlllIlIIllIllIllIlllIIllIIIllIIIllIIlIIlIIIlIIllIll implements IllIIlIIllIllIIlllIlllIlIIllIlIllIIllIlllIIllIIIlIIIlllIIllIIllIlIIIlIIllIIlIIIlIllIIllIIlIIllIIIlIIlllIIllIIIlIIlIlIIIlIllIIlIIIllIIlIlIIIlIIIllIIlllIIllIIlllIIlIIIllIIllIIlllIllIllIIlIIllIIlllIIIlllIllIllIIlIIlllIlllIIIlIIIll
{
    private Class<?> IlIIlllIllIIIllIIllIIIlllIIIlIIIlllIIlIIlllIIIlIllIIllIlIIlIIlIlIIlllIIlIIllIlIlllIIIlIIIlIIIlIIlIlllIIlIIIllIIIllIIIlIlIlllIIlIIlllIIlllIIllIlIllIlllIIIlllIlIlIIllIllIlllIIlIIlIIIllIlllIllIlllIIIlIllIllIllIIllIlllIIlIIIll;
    private Object IIllIIlllIIllIlllIIlIlllIIIllIIlllIlIlIIIllIIlllIIIlllIIIlIIllIIIlIIlIIllIlllIIlllIIlllIllIIllIIlllIlIIIllIIlIIlllIIlIIIlIIlllIIllIIlllIIIlIllIlllIIllIIlIIlllIIIllIIlllIIlllIIllIIlIIllIIlllIIlllIIllIllIllIIIllIIIlllIllIIllIIlllIIlIllIlIIllIIl;
    
    @Override
    public boolean IIllIIlIIllIIlllIIIlllIlIllIIlIIlIllIIIllIIlIIlIIlIIllIllIIlIIllIIlllIIlIlllIlllIlllIIIlllIIllIIllIIllIlllIlllIIIlIllIIllIIIllIIlIlIIllIIlllIlllIIIllIIllIlllIlllIIllIIlllIIllIIlIIllIllIllIllIIIlIIIllIIIlIIIlIllIllIIIllIllIllIll() {
        boolean b;
        try {
            final Class<?> forName = Class.forName("com.destroystokyo.paper.antixray.ChunkPacketBlockController");
            Intrinsics.checkNotNullExpressionValue((Object)forName, "");
            this.IlIIlllIllIIIllIIllIIIlllIIIlIIIlllIIlIIlllIIIlIllIIllIlIIlIIlIlIIlllIIlIIllIlIlllIIIlIIIlIIIlIIlIlllIIlIIIllIIIllIIIlIlIlllIIlIIlllIIlllIIllIlIllIlllIIIlllIlIlIIllIllIlllIIlIIlIIIllIlllIllIlllIIIlIllIllIllIIllIlllIIlIIIll = forName;
            Class<?> ilIIlllIllIIIllIIllIIIlllIIIlIIIlllIIlIIlllIIIlIllIIllIlIIlIIlIlIIlllIIlIIllIlIlllIIIlIIIlIIIlIIlIlllIIlIIIllIIIllIIIlIlIlllIIlIIlllIIlllIIllIlIllIlllIIIlllIlIlIIllIllIlllIIlIIlIIIllIlllIllIlllIIIlIllIllIllIIllIlllIIlIIIll;
            if ((ilIIlllIllIIIllIIllIIIlllIIIlIIIlllIIlIIlllIIIlIllIIllIlIIlIIlIlIIlllIIlIIllIlIlllIIIlIIIlIIIlIIlIlllIIlIIIllIIIllIIIlIlIlllIIlIIlllIIlllIIllIlIllIlllIIIlllIlIlIIllIllIlllIIlIIlIIIllIlllIllIlllIIIlIllIllIllIIllIlllIIlIIIll = this.IlIIlllIllIIIllIIllIIIlllIIIlIIIlllIIlIIlllIIIlIllIIllIlIIlIIlIlIIlllIIlIIllIlIlllIIIlIIIlIIIlIIlIlllIIlIIIllIIIllIIIlIlIlllIIlIIlllIIlllIIllIlIllIlllIIIlllIlIlIIllIllIlllIIlIIlIIIllIlllIllIlllIIIlIllIllIllIIllIlllIIlIIIll) == null) {
                Intrinsics.throwUninitializedPropertyAccessException("");
                ilIIlllIllIIIllIIllIIIlllIIIlIIIlllIIlIIlllIIIlIllIIllIlIIlIIlIlIIlllIIlIIllIlIlllIIIlIIIlIIIlIIlIlllIIlIIIllIIIllIIIlIlIlllIIlIIlllIIlllIIllIlIllIlllIIIlllIlIlIIllIllIlllIIlIIlIIIllIlllIllIlllIIIlIllIllIllIIllIlllIIlIIIll = null;
            }
            final Object value = ilIIlllIllIIIllIIllIIIlllIIIlIIIlllIIlIIlllIIIlIllIIllIlIIlIIlIlIIlllIIlIIllIlIlllIIIlIIIlIIIlIIlIlllIIlIIIllIIIllIIIlIlIlllIIlIIlllIIlllIIllIlIllIlllIIIlllIlIlIIllIllIlllIIlIIlIIIllIlllIllIlllIIIlIllIllIllIIllIlllIIlIIIll.getDeclaredField("NO_OPERATION_INSTANCE").get(null);
            Intrinsics.checkNotNullExpressionValue(value, "");
            this.IIllIIlllIIllIlllIIlIlllIIIllIIlllIlIlIIIllIIlllIIIlllIIIlIIllIIIlIIlIIllIlllIIlllIIlllIllIIllIIlllIlIIIllIIlIIlllIIlIIIlIIlllIIllIIlllIIIlIllIlllIIllIIlIIlllIIIllIIlllIIlllIIllIIlIIllIIlllIIlllIIllIllIllIIIllIIIlllIllIIllIIlllIIlIllIlIIllIIl = value;
            b = true;
        }
        catch (final Exception ex) {
            b = false;
        }
        return b;
    }
    
    @Override
    public boolean IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final World world) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        final Object iIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll = this.IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(world);
        final Object value = iIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll.getClass().getField("chunkPacketBlockController").get(iIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll);
        Object o;
        if ((o = this.IIllIIlllIIllIlllIIlIlllIIIllIIlllIlIlIIIllIIlllIIIlllIIIlIIllIIIlIIlIIllIlllIIlllIIlllIllIIllIIlllIlIIIllIIlIIlllIIlIIIlIIlllIIllIIlllIIIlIllIlllIIllIIlIIlllIIIllIIlllIIlllIIllIIlIIllIIlllIIlllIIllIllIllIIIllIIIlllIllIIllIIlllIIlIllIlIIllIIl) == null) {
            Intrinsics.throwUninitializedPropertyAccessException("");
            o = Unit.INSTANCE;
        }
        return !Intrinsics.areEqual(value, o);
    }
    
    private final Object IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(final World obj) {
        final Method declaredMethod = obj.getClass().getDeclaredMethod("getHandle", (Class<?>[])new Class[0]);
        declaredMethod.setAccessible(true);
        final Object invoke = declaredMethod.invoke(obj, new Object[0]);
        Intrinsics.checkNotNullExpressionValue(invoke, "");
        return invoke;
    }
}
