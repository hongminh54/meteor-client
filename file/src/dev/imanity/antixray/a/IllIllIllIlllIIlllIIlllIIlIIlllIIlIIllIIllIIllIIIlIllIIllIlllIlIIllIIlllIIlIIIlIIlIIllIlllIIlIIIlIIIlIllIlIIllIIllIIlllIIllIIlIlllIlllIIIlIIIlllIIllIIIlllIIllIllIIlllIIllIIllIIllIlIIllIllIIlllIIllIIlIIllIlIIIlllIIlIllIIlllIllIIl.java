// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import java.lang.reflect.Method;
import org.jetbrains.annotations.NotNull;
import org.bukkit.World;
import kotlin.jvm.internal.Intrinsics;
import java.lang.reflect.Field;

public final class IllIllIllIlllIIlllIIlllIIlIIlllIIlIIllIIllIIllIIIlIllIIllIlllIlIIllIIlllIIlIIIlIIlIIllIlllIIlIIIlIIIlIllIlIIllIIllIIlllIIllIIlIlllIlllIIIlIIIlllIIllIIIlllIIllIllIIlllIIllIIllIIllIlIIllIllIIlllIIllIIlIIllIlIIIlllIIlIllIIlllIllIIl implements IllIIlIIllIllIIlllIlllIlIIllIlIllIIllIlllIIllIIIlIIIlllIIllIIllIlIIIlIIllIIlIIIlIllIIllIIlIIllIIIlIIlllIIllIIIlIIlIlIIIlIllIIlIIIllIIlIlIIIlIIIllIIlllIIllIIlllIIlIIIllIIllIIlllIllIllIIlIIllIIlllIIIlllIllIllIIlIIlllIlllIIIlIIIll
{
    private Class<?> IllIllIllIlIIllIllIIllIIlllIIlIIlIIIlllIIlllIlllIllIllIIlIIllIIlllIIIllIIlIIlllIIIlIlllIIlIIlllIIlllIIllIIllIIIllIIIlIllIllIllIIlIllIllIllIIllIllIIlllIIllIIlIIllIIlIlIIIllIIlllIIllIIlllIIllIIlIllIlllIlIIIllIIIlIIIlIIlllIlIl;
    private Field field;
    
    @Override
    public boolean IIllIIlIIllIIlllIIIlllIlIllIIlIIlIllIIIllIIlIIlIIlIIllIllIIlIIllIIlllIIlIlllIlllIlllIIIlllIIllIIllIIllIlllIlllIIIlIllIIllIIIllIIlIlIIllIIlllIlllIIIllIIllIlllIlllIIllIIlllIIllIIlIIllIllIllIllIIIlIIIllIIIlIIIlIllIllIIIllIllIllIll() {
        boolean b;
        try {
            final Class<?> forName = Class.forName("org.spigotmc.SpigotWorldConfig");
            Intrinsics.checkNotNullExpressionValue((Object)forName, "");
            this.IllIllIllIlIIllIllIIllIIlllIIlIIlIIIlllIIlllIlllIllIllIIlIIllIIlllIIIllIIlIIlllIIIlIlllIIlIIlllIIlllIIllIIllIIIllIIIlIllIllIllIIlIllIllIllIIllIllIIlllIIllIIlIIllIIlIlIIIllIIlllIIllIIlllIIllIIlIllIlllIlIIIllIIIlIIIlIIlllIlIl = forName;
            Class<?> illIllIllIlIIllIllIIllIIlllIIlIIlIIIlllIIlllIlllIllIllIIlIIllIIlllIIIllIIlIIlllIIIlIlllIIlIIlllIIlllIIllIIllIIIllIIIlIllIllIllIIlIllIllIllIIllIllIIlllIIllIIlIIllIIlIlIIIllIIlllIIllIIlllIIllIIlIllIlllIlIIIllIIIlIIIlIIlllIlIl;
            if ((illIllIllIlIIllIllIIllIIlllIIlIIlIIIlllIIlllIlllIllIllIIlIIllIIlllIIIllIIlIIlllIIIlIlllIIlIIlllIIlllIIllIIllIIIllIIIlIllIllIllIIlIllIllIllIIllIllIIlllIIllIIlIIllIIlIlIIIllIIlllIIllIIlllIIllIIlIllIlllIlIIIllIIIlIIIlIIlllIlIl = this.IllIllIllIlIIllIllIIllIIlllIIlIIlIIIlllIIlllIlllIllIllIIlIIllIIlllIIIllIIlIIlllIIIlIlllIIlIIlllIIlllIIllIIllIIIllIIIlIllIllIllIIlIllIllIllIIllIllIIlllIIllIIlIIllIIlIlIIIllIIlllIIllIIlllIIllIIlIllIlllIlIIIllIIIlIIIlIIlllIlIl) == null) {
                Intrinsics.throwUninitializedPropertyAccessException("");
                illIllIllIlIIllIllIIllIIlllIIlIIlIIIlllIIlllIlllIllIllIIlIIllIIlllIIIllIIlIIlllIIIlIlllIIlIIlllIIlllIIllIIllIIIllIIIlIllIllIllIIlIllIllIllIIllIllIIlllIIllIIlIIllIIlIlIIIllIIlllIIllIIlllIIllIIlIllIlllIlIIIllIIIlIIIlIIlllIlIl = null;
            }
            final Field declaredField = illIllIllIlIIllIllIIllIIlllIIlIIlIIIlllIIlllIlllIllIllIIlIIllIIlllIIIllIIlIIlllIIIlIlllIIlIIlllIIlllIIllIIllIIIllIIIlIllIllIllIIlIllIllIllIIllIllIIlllIIllIIlIIllIIlIlIIIllIIlllIIllIIlllIIllIIlIllIlllIlIIIllIIIlIIIlIIlllIlIl.getDeclaredField("antiXray");
            Intrinsics.checkNotNullExpressionValue((Object)declaredField, "");
            this.field = declaredField;
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
        final Object iIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll = this.IIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll(world);
        final Object value = iIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll.getClass().getField("spigotConfig").get(iIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll);
        Field field;
        if ((field = this.field) == null) {
            Intrinsics.throwUninitializedPropertyAccessException("");
            field = null;
        }
        final Object value2 = field.get(value);
        Intrinsics.checkNotNull(value2);
        return (boolean)value2;
    }
    
    private final Object IIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll(final World obj) {
        final Method declaredMethod = obj.getClass().getDeclaredMethod("getHandle", (Class<?>[])new Class[0]);
        declaredMethod.setAccessible(true);
        final Object invoke = declaredMethod.invoke(obj, new Object[0]);
        Intrinsics.checkNotNullExpressionValue(invoke, "");
        return invoke;
    }
}
