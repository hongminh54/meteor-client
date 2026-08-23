// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import org.jetbrains.annotations.Nullable;
import java.util.Optional;
import dev.imanity.antixray.AntiXrayPlugin;
import org.bukkit.Material;
import io.fairyproject.libs.xseries.XMaterial;
import kotlin.text.StringsKt;
import dev.imanity.antixray.hook.nexo.NexoHook;
import dev.imanity.antixray.hook.oraxen.OraxenHook;
import dev.imanity.antixray.hook.itemsadder.ItemsAdderHook;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import io.fairyproject.container.InjectableComponent;
import io.fairyproject.ObjectSerializer;

@InjectableComponent
@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0019\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u0017¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\fH\u0017¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\fH\u0017¢\u0006\u0004\b\u000f\u0010\u000eJ\u0017\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\n\u001a\u00020\u00068\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0010\u001a\u00020\u00048\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015" }, d2 = { "Ldev/imanity/antixray/a/IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl;", "Lio/fairyproject/ObjectSerializer;", "Ldev/imanity/antixray/a/IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl;", "", "Ldev/imanity/antixray/a/IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll;", "p0", "Ldev/imanity/antixray/a/IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll;", "p1", "<init>", "(Ldev/imanity/antixray/a/IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll;Ldev/imanity/antixray/a/IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll;)V", "IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll", "(Ljava/lang/String;)Ldev/imanity/antixray/a/IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl;", "Ljava/lang/Class;", "inputClass", "()Ljava/lang/Class;", "outputClass", "IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll", "(Ldev/imanity/antixray/a/IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl;)Ljava/lang/String;", "IIIllIIllIIllIIlllIIlllIIllIIllIllIIIlIlIlIlIIllIIIlIIllIIlllIIIlIllIllIIlllIlllIIIllIlIIIllIIIlIlIIlIIlllIIlllIIlllIlllIIlllIIIlIlllIIlllIlIIlIIlllIIlllIIIllIlIIIllIllIIllIIlllIIllIIlIIIlIIlIllIllIIllIIIlllIIlllIIllIIIllIIIllIIIlIIIlIIIll", "Ldev/imanity/antixray/a/IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll;", "IIlllIllIllIIIlllIIlllIlllIIlllIlllIllIlIIIlIIIlIIIlIllIlllIllIlIIIlIlIIIlllIIllIIIlllIIlIIllIIIlIlIIIllIIllIlIIIlllIllIIIllIIllIIIlIIlIlllIIllIlIIIlIIllIIllIlllIIlIllIIllIIIlIIlllIIllIlllIIlIIllIlllIIllIIIlllIIllIllIIlllIIIlllIIIllIIll", "Ldev/imanity/antixray/a/IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll;" })
public final class IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl implements ObjectSerializer<IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl, String>
{
    @NotNull
    private final IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll IIlllIllIllIIIlllIIlllIlllIIlllIlllIllIlIIIlIIIlIIIlIllIlllIllIlIIIlIlIIIlllIIllIIIlllIIlIIllIIIlIlIIIllIIllIlIIIlllIllIIIllIIllIIIlIIlIlllIIllIlIIIlIIllIIllIlllIIlIllIIllIIIlIIlllIIllIlllIIlIIllIlllIIllIIIlllIIllIllIIlllIIIlllIIIllIIll;
    @NotNull
    private final IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll IIIllIIllIIllIIlllIIlllIIllIIllIllIIIlIlIlIlIIllIIIlIIllIIlllIIIlIllIllIIlllIlllIIIllIlIIIllIIIlIlIIlIIlllIIlllIIlllIlllIIlllIIIlIlllIIlllIlIIlIIlllIIlllIIIllIlIIIllIllIIllIIlllIIllIIlIIIlIIlIllIllIIllIIIlllIIlllIIllIIIllIIIllIIIlIIIlIIIll;
    
    public IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl(@NotNull final IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll iIlllIllIllIIIlllIIlllIlllIIlllIlllIllIlIIIlIIIlIIIlIllIlllIllIlIIIlIlIIIlllIIllIIIlllIIlIIllIIIlIlIIIllIIllIlIIIlllIllIIIllIIllIIIlIIlIlllIIllIlIIIlIIllIIllIlllIIlIllIIllIIIlIIlllIIllIlllIIlIIllIlllIIllIIIlllIIllIllIIlllIIIlllIIIllIIll, @NotNull final IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll iiIllIIllIIllIIlllIIlllIIllIIllIllIIIlIlIlIlIIllIIIlIIllIIlllIIIlIllIllIIlllIlllIIIllIlIIIllIIIlIlIIlIIlllIIlllIIlllIlllIIlllIIIlIlllIIlllIlIIlIIlllIIlllIIIllIlIIIllIllIIllIIlllIIllIIlIIIlIIlIllIllIIllIIIlllIIlllIIllIIIllIIIllIIIlIIIlIIIll) {
        Intrinsics.checkNotNullParameter((Object)iIlllIllIllIIIlllIIlllIlllIIlllIlllIllIlIIIlIIIlIIIlIllIlllIllIlIIIlIlIIIlllIIllIIIlllIIlIIllIIIlIlIIIllIIllIlIIIlllIllIIIllIIllIIIlIIlIlllIIllIlIIIlIIllIIllIlllIIlIllIIllIIIlIIlllIIllIlllIIlIIllIlllIIllIIIlllIIllIllIIlllIIIlllIIIllIIll, "");
        Intrinsics.checkNotNullParameter((Object)iiIllIIllIIllIIlllIIlllIIllIIllIllIIIlIlIlIlIIllIIIlIIllIIlllIIIlIllIllIIlllIlllIIIllIlIIIllIIIlIlIIlIIlllIIlllIIlllIlllIIlllIIIlIlllIIlllIlIIlIIlllIIlllIIIllIlIIIllIllIIllIIlllIIllIIlIIIlIIlIllIllIIllIIIlllIIlllIIllIIIllIIIllIIIlIIIlIIIll, "");
        this.IIlllIllIllIIIlllIIlllIlllIIlllIlllIllIlIIIlIIIlIIIlIllIlllIllIlIIIlIlIIIlllIIllIIIlllIIlIIllIIIlIlIIIllIIllIlIIIlllIllIIIllIIllIIIlIIlIlllIIllIlIIIlIIllIIllIlllIIlIllIIllIIIlIIlllIIllIlllIIlIIllIlllIIllIIIlllIIllIllIIlllIIIlllIIIllIIll = iIlllIllIllIIIlllIIlllIlllIIlllIlllIllIlIIIlIIIlIIIlIllIlllIllIlIIIlIlIIIlllIIllIIIlllIIlIIllIIIlIlIIIllIIllIlIIIlllIllIIIllIIllIIIlIIlIlllIIllIlIIIlIIllIIllIlllIIlIllIIllIIIlIIlllIIllIlllIIlIIllIlllIIllIIIlllIIllIllIIlllIIIlllIIIllIIll;
        this.IIIllIIllIIllIIlllIIlllIIllIIllIllIIIlIlIlIlIIllIIIlIIllIIlllIIIlIllIllIIlllIlllIIIllIlIIIllIIIlIlIIlIIlllIIlllIIlllIlllIIlllIIIlIlllIIlllIlIIlIIlllIIlllIIIllIlIIIllIllIIllIIlllIIllIIlIIIlIIlIllIllIIllIIIlllIIlllIIllIIIllIIIllIIIlIIIlIIIll = iiIllIIllIIllIIlllIIlllIIllIIllIllIIIlIlIlIlIIllIIIlIIllIIlllIIIlIllIllIIlllIlllIIIllIlIIIllIIIlIlIIlIIlllIIlllIIlllIlllIIlllIIIlIlllIIlllIlIIlIIlllIIlllIIIllIlIIIllIllIIllIIlllIIllIIlIIIlIIlIllIllIIllIIIlllIIlllIIllIIIllIIIllIIIlIIIlIIIll;
    }
    
    @NotNull
    public String IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl obj) {
        Intrinsics.checkNotNullParameter((Object)obj, "");
        if (obj instanceof IIlIlllIIIllIIlllIIlIIllIIlIlIIlIIlIIlllIIlllIIIllIIlllIIllIIllIIlIIlIIlIIIlIIlllIIlllIlIIIlIllIIIlllIlIIIlIlIIIlllIIlIllIIllIIIlIIlllIIllIIIlIIllIIllIIlllIIllIIllIIllIIIllIIIllIIIlIIllIlllIlllIllIIlllIlIlllIIlIIlIlllIIlIIIllIIllIIl) {
            return ((IIlIlllIIIllIIlllIIlIIllIIlIlIIlIIlIIlllIIlllIIIllIIlllIIllIIllIIlIIlIIlIIIlIIlllIIlllIlIIIlIllIIIlllIlIIIlIlIIIlllIIlIllIIllIIIlIIlllIIllIIIlIIllIIllIIlllIIllIIllIIllIIIllIIIllIIIlIIllIlllIlllIllIIlllIlIlllIIlIIlIlllIIlIIIllIIllIIl)obj).IllIIllIIllIlllIIlIIllIIlIIllIIlllIIIllIlIlllIlllIIllIIllIllIIllIllIIllIIlIIlllIlllIIlllIIIllIIllIIIllIlIIllIllIllIllIIllIIIlIIlllIlllIIIllIIIlIIllIllIIIllIIIllIIlIIIllIIlIIllIllIIlllIlIlIllIllIllIlIIlIllIIlIIllIIllIIIlllIIl().name();
        }
        if (obj instanceof IIlllIIIlIlllIIlIIllIllIlllIIllIlIllIlIIllIlllIIllIllIlllIlllIIllIIllIIIllIIIllIIIlIIllIllIIIllIIllIIlIlllIIlIIllIIIllIlIllIIIllIIIllIIllIlllIIIlIIllIIIlllIIllIIllIIIllIlIIIlllIIllIIllIIlllIIlllIlllIlIIllIlIIIllIIlllIllIllIIIllIIlllIlll) {
            return ((IIlllIIIlIlllIIlIIllIllIlllIIllIlIllIlIIllIlllIIllIllIlllIlllIIllIIllIIIllIIIllIIIlIIllIllIIIllIIllIIlIlllIIlIIllIIIllIlIllIIIllIIIllIIllIlllIIIlIIllIIIlllIIllIIllIIIllIlIIIlllIIllIIllIIlllIIlllIlllIlIIllIlIIIllIIlllIllIllIIIllIIlllIlll)obj).IIllIIlIIllIIIlllIllIIlIIllIIllIIIllIIllIIlIIllIIIlllIIllIIllIllIlIIIlllIIIllIIlllIIIlllIIlllIIllIlIIlIlllIlllIIllIIIlllIIllIIlIIIlIIlllIIIllIIIlllIlIIlIIlIllIIIllIllIIIlllIIIlIlllIllIIllIlIIIllIIIlllIIlIIIlIIlIIlIlIIlIllIIIlllIllIIIllIIll().name();
        }
        if (obj instanceof IIllIllIIllIIlllIIlIIlIIIlIllIllIIlIIIlllIIllIIllIlllIIllIIlIIlllIlllIllIlllIIllIIlIlllIlllIlIlllIllIlllIIlIlllIIlIIlIIlllIIlllIlIlIIIllIlIllIllIIllIIIlllIIIllIllIIlIlIllIllIIIllIIIlIlIIlllIIIllIlIlIIlIlllIIlllIIllIIIl) {
            final String asString = ((IIllIllIIllIIlllIIlIIlIIIlIllIllIIlIIIlllIIllIIllIlllIIllIIlIIlllIlllIllIlllIIllIIlIlllIlllIlIlllIllIlllIIlIlllIIlIIlIIlllIIlllIlIlIIIllIlIllIllIIllIIIlllIIIllIllIIlIlIllIllIIIllIIIlIlIIlllIIIllIlIlIIlIlllIIlllIIllIIIl)obj).IlllIIIlllIIllIIIlllIIllIllIIIllIIllIIlIIlllIIlllIIlllIIlllIIllIlIIlIIlIIIlllIIllIlllIIllIIllIIllIIlIlllIllIIIlIlllIlllIIlllIllIIllIIllIlIIlllIlIIllIlIIIllIIIlllIIllIlllIIllIlIIlIIlllIIIlIIllIIIlllIIlIlIIlllIIIlIIlllIllIlIllIIllIlllIIlll().getAsString(true);
            Intrinsics.checkNotNullExpressionValue((Object)asString, "");
            return asString;
        }
        if (obj instanceof IIllIllIIIllIIllIIIlIllIllIIlIllIlIlllIllIIIllIIIlllIlllIlIIIlIllIlIllIlIllIIIlIIllIllIlIllIlllIIIllIIllIlllIIllIIlIllIIIlllIIIllIIllIIllIIllIIIlllIIlIIIlllIIIlllIlllIllIllIIlIlIIIllIlIllIlIIllIllIlllIllIlllIIlIIlllIlll) {
            return ((IIllIllIIIllIIllIIIlIllIllIIlIllIlIlllIllIIIllIIIlllIlllIlIIIlIllIlIllIlIllIIIlIIllIllIlIllIlllIIIllIIllIlllIIllIIlIllIIIlllIIIllIIllIIllIIllIIIlllIIlIIIlllIIIlllIlllIllIllIIlIlIIIllIlIllIlIIllIllIlllIllIlllIIlIIlllIlll)obj).IllIIlIllIlIllIIIllIlIlllIllIllIlIllIIIllIIlllIIIllIllIIllIIllIIllIllIllIIlIIlIIlIIIlllIIIlllIIlIllIllIlllIIlIllIllIIIlIIIllIIlIlIIIlIIIlIIIlllIllIIlllIIIllIIlIllIlIIlIIIllIIIlllIIlIIIlllIIlIlIIIllIIIlIIlIIIlIIIllIllIl();
        }
        if (ItemsAdderHook.INSTANCE.isHooked()) {
            return "ia:" + ItemsAdderHook.INSTANCE.convertFromBlockMaterial(obj);
        }
        if (OraxenHook.INSTANCE.isHooked()) {
            return "oraxen:" + OraxenHook.INSTANCE.convertFromBlockMaterial(obj);
        }
        if (NexoHook.INSTANCE.isHooked()) {
            return "nexo:" + NexoHook.INSTANCE.convertFromBlockMaterial(obj);
        }
        throw new IllegalArgumentException("The " + obj + " is not an instance of BlockMaterial");
    }
    
    @Nullable
    public IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final String s) {
        Intrinsics.checkNotNullParameter((Object)s, "");
        if (StringsKt.startsWith$default(s, "ia:", false, 2, (Object)null)) {
            if (!ItemsAdderHook.INSTANCE.isHooked()) {
                throw new IllegalArgumentException("ItemsAdder is not hooked, attempting to register " + s);
            }
            final ItemsAdderHook instance = ItemsAdderHook.INSTANCE;
            final String substring = s.substring(3);
            Intrinsics.checkNotNullExpressionValue((Object)substring, "");
            return instance.convertToBlockMaterial(substring);
        }
        else if (StringsKt.startsWith$default(s, "oraxen:", false, 2, (Object)null)) {
            if (!OraxenHook.INSTANCE.isHooked()) {
                throw new IllegalArgumentException("Oraxen is not hooked, attempting to register " + s);
            }
            final OraxenHook instance2 = OraxenHook.INSTANCE;
            final String substring2 = s.substring(7);
            Intrinsics.checkNotNullExpressionValue((Object)substring2, "");
            return instance2.convertToBlockMaterial(substring2);
        }
        else {
            if (!StringsKt.startsWith$default(s, "nexo:", false, 2, (Object)null)) {
                final Optional matchXMaterial = XMaterial.matchXMaterial(s);
                if (matchXMaterial.isPresent()) {
                    final Object value = matchXMaterial.get();
                    Intrinsics.checkNotNullExpressionValue(value, "");
                    final XMaterial xMaterial = (XMaterial)value;
                    if (this.IIIllIIllIIllIIlllIIlllIIllIIllIllIIIlIlIlIlIIllIIIlIIllIIlllIIIlIllIllIIlllIlllIIIllIlIIIllIIIlIlIIlIIlllIIlllIIlllIlllIIlllIIIlIlllIIlllIlIIlIIlllIIlllIIIllIlIIIllIllIIllIIlllIIllIIlIIIlIIlIllIllIIllIIIlllIIlllIIllIIIllIIIllIIIlIIIlIIIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(xMaterial, true)) {
                        return new IIlIlllIIIllIIlllIIlIIllIIlIlIIlIIlIIlllIIlllIIIllIIlllIIllIIllIIlIIlIIlIIIlIIlllIIlllIlIIIlIllIIIlllIlIIIlIlIIIlllIIlIllIIllIIIlIIlllIIllIIIlIIllIIllIIlllIIllIIllIIllIIIllIIIllIIIlIIllIlllIlllIllIIlllIlIlllIIlIIlIlllIIlIIIllIIllIIl(xMaterial, this.IIlllIllIllIIIlllIIlllIlllIIlllIlllIllIlIIIlIIIlIIIlIllIlllIllIlIIIlIlIIIlllIIllIIIlllIIlIIllIIIlIlIIIllIIllIlIIIlllIllIIIllIIllIIIlIIlIlllIIllIlIIIlIIllIIllIlllIIlIllIIllIIIlIIlllIIllIlllIIlIIllIlllIIllIIIlllIIllIllIIlllIIIlllIIIllIIll);
                    }
                }
                try {
                    final Material value2 = Material.valueOf(s);
                    if (this.IIIllIIllIIllIIlllIIlllIIllIIllIllIIIlIlIlIlIIllIIIlIIllIIlllIIIlIllIllIIlllIlllIIIllIlIIIllIIIlIlIIlIIlllIIlllIIlllIlllIIlllIIIlIlllIIlllIlIIlIIlllIIlllIIIllIlIIIllIllIIllIIlllIIllIIlIIIlIIlIllIllIIllIIIlllIIlllIIllIIIllIIIllIIIlIIIlIIIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(value2)) {
                        return new IIlllIIIlIlllIIlIIllIllIlllIIllIlIllIlIIllIlllIIllIllIlllIlllIIllIIllIIIllIIIllIIIlIIllIllIIIllIIllIIlIlllIIlIIllIIIllIlIllIIIllIIIllIIllIlllIIIlIIllIIIlllIIllIIllIIIllIlIIIlllIIllIIllIIlllIIlllIlllIlIIllIlIIIllIIlllIllIllIIIllIIlllIlll(value2, this.IIlllIllIllIIIlllIIlllIlllIIlllIlllIllIlIIIlIIIlIIIlIllIlllIllIlIIIlIlIIIlllIIllIIIlllIIlIIllIIIlIlIIIllIIllIlIIIlllIllIIIllIIllIIIlIIlIlllIIllIlIIIlIIllIIllIlllIIlIllIIllIIIlIIlllIIllIlllIIlIIllIlllIIllIIIlllIIllIllIIlllIIIlllIIIllIIll);
                    }
                }
                catch (final IllegalArgumentException ex) {}
                if (AntiXrayPlugin.MODERN_BLOCK_DATA) {
                    try {
                        return IIllIllIIllIIlllIIlIIlIIIlIllIllIIlIIIlllIIllIIllIlllIIllIIlIIlllIlllIllIlllIIllIIlIlllIlllIlIlllIllIlllIIlIlllIIlIIlIIlllIIlllIlIlIIIllIlIllIllIIllIIIlllIIIllIllIIlIlIllIllIIIllIIIlIlIIlllIIIllIlIlIIlIlllIIlllIIllIIIl.IIllIllIIlllIIIlIIllIIllIIllIIlIllIIllIIllIIIllIIIllIIllIllIllIIIlllIIlIIIllIIllIIIllIIIllIIllIIIlIIIlIIlIllIIllIIllIlllIlllIllIIllIlllIIlllIIllIIllIIIlllIIlllIIIlllIIlllIIlIIIlIIllIlIIlIlIlIIlIllIlIIIlllIllIIlllIlllIlIIIllIIIlIIlIIl.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(s);
                    }
                    catch (final IllegalArgumentException ex2) {}
                }
                throw new IllegalArgumentException("The material " + s + " is not found");
            }
            if (!NexoHook.INSTANCE.isHooked()) {
                throw new IllegalArgumentException("Nexo is not hooked, attempting to register " + s);
            }
            final NexoHook instance3 = NexoHook.INSTANCE;
            final String substring3 = s.substring(5);
            Intrinsics.checkNotNullExpressionValue((Object)substring3, "");
            return instance3.convertToBlockMaterial(substring3);
        }
    }
    
    @NotNull
    public Class<IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl> inputClass() {
        return IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl.class;
    }
    
    @NotNull
    public Class<String> outputClass() {
        return String.class;
    }
}
