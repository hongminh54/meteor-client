// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import kotlin.jvm.internal.DefaultConstructorMarker;
import java.util.Collection;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import dev.imanity.antixray.api.block.RaytraceBlock;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import dev.imanity.antixray.api.block.RaytraceBlockGroup;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\n\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0016¢\u0006\u0004\b\n\u0010\u000bJ#\u0010\r\u001a\u00020\f2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\f0\u0006H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0001H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0007H\u0096\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0007H\u0096\u0002¢\u0006\u0004\b\u0013\u0010\u0012J\u0015\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014H\u0002¢\u0006\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00148\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00070\u001a8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010 \u001a\u00020\u00048WX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0016\u0010#\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010&\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010%" }, d2 = { "Ldev/imanity/antixray/a/IllIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll;", "Ldev/imanity/antixray/api/block/RaytraceBlockGroup;", "<init>", "()V", "", "p0", "Lkotlin/Function1;", "Ldev/imanity/antixray/api/block/RaytraceBlock;", "", "p1", "checkVisibility", "(ILkotlin/jvm/functions/Function1;)Z", "", "forEach", "(Lkotlin/jvm/functions/Function1;)V", "merge", "(Ldev/imanity/antixray/api/block/RaytraceBlockGroup;)V", "minusAssign", "(Ldev/imanity/antixray/api/block/RaytraceBlock;)V", "plusAssign", "", "IIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll", "()[Ldev/imanity/antixray/api/block/RaytraceBlock;", "IIlIlIlIlIllIIIllIIlllIlllIIIllIIIlIIIlllIIIlllIllIIllIIlIIIllIIlllIIIlIIllIIllIIlIIllIIlIIIlIIllIIllIIlIIlIIlIlIIIllIIIllIIlllIIIlIIIllIIllIIllIIIlllIlIIlIIIllIIllIIllIIIlllIIIlllIIIllIIllIIIllIIlllIIIlIIlIIlllIIlllIIlllIllIIllIIlllIIlIlIll", "[Ldev/imanity/antixray/api/block/RaytraceBlock;", "IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll", "Lit/unimi/dsi/fastutil/objects/ObjectOpenHashSet;", "IIllIIlIIllIIIlllIllIIlIIllIIllIIIllIIllIIlIIllIIIlllIIllIIllIllIlIIIlllIIIllIIlllIIIlllIIlllIIllIlIIlIlllIlllIIllIIIlllIIllIIlIIIlIIlllIIIllIIIlllIlIIlIIlIllIIIllIllIIIlllIIIlIlllIllIIllIlIIIllIIIlllIIlIIIlIIlIIlIlIIlIllIIIlllIllIIIllIIll", "Lit/unimi/dsi/fastutil/objects/ObjectOpenHashSet;", "IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll", "getSize", "()I", "IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll", "IllIIlIllIlIllIIIllIlIlllIllIllIlIllIIIllIIlllIIIllIllIIllIIllIIllIllIllIIlIIlIIlIIIlllIIIlllIIlIllIllIlllIIlIllIllIIIlIIIllIIlIlIIIlIIIlIIIlllIllIIlllIIIllIIlIllIlIIlIIIllIIIlllIIlIIIlllIIlIlIIIllIIIlIIlIIIlIIIllIllIl", "Z", "IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll", "IIIlllIllIllIllIIllIllIIIllIIllIlIIIllIIlIllIllIIlIIIllIIIlIIlllIlIIIllIlIIlllIIlIIlllIlIIlIIIllIIllIIlIIlllIIllIllIllIIIllIllIlIlllIIlIllIlIllIIIlllIIlIIlllIIlIIllIIIlllIlllIIlIIllIIlllIllIllIIlIIllIIllIllIIIlIIlIIIllIIl", "I", "IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll" })
@SourceDebugExtension({ "SMAP\nRaytraceBlockImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RaytraceBlockImpl.kt\ndev/imanity/antixray/core/block/RaytraceBlockGroupImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 ArrayIntrinsics.kt\nkotlin/ArrayIntrinsicsKt\n*L\n1#1,153:1\n1#2:154\n37#3,2:155\n26#4:157\n*S KotlinDebug\n*F\n+ 1 RaytraceBlockImpl.kt\ndev/imanity/antixray/core/block/RaytraceBlockGroupImpl\n*L\n144#1:155,2\n151#1:157\n*E\n" })
public final class IllIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll implements RaytraceBlockGroup
{
    @NotNull
    private static final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;
    @NotNull
    private final ObjectOpenHashSet<RaytraceBlock> IIllIIlIIllIIIlllIllIIlIIllIIllIIIllIIllIIlIIllIIIlllIIllIIllIllIlIIIlllIIIllIIlllIIIlllIIlllIIllIlIIlIlllIlllIIllIIIlllIIllIIlIIIlIIlllIIIllIIIlllIlIIlIIlIllIIIllIllIIIlllIIIlIlllIllIIllIlIIIllIIIlllIIlIIIlIIlIIlIlIIlIllIIIlllIllIIIllIIll;
    @Nullable
    private volatile RaytraceBlock[] IIlIlIlIlIllIIIllIIlllIlllIIIllIIIlIIIlllIIIlllIllIIllIIlIIIllIIlllIIIlIIllIIllIIlIIllIIlIIIlIIllIIllIIlIIlIIlIlIIIllIIIllIIlllIIIlIIIllIIllIIllIIIlllIlIIlIIIllIIllIIllIIIlllIIIlllIIIllIIllIIIllIIlllIIIlIIlIIlllIIlllIIlllIllIIllIIlllIIlIlIll;
    private int IIIlllIllIllIllIIllIllIIIllIIllIlIIIllIIlIllIllIIlIIIllIIIlIIlllIlIIIllIlIIlllIIlIIlllIlIIlIIIllIIllIIlIIlllIIllIllIllIIIllIllIlIlllIIlIllIlIllIIIlllIIlIIlllIIlIIllIIIlllIlllIIlIIllIIlllIllIllIIlIIllIIllIllIIIlIIlIIIllIIl;
    private boolean IllIIlIllIlIllIIIllIlIlllIllIllIlIllIIIllIIlllIIIllIllIIllIIllIIllIllIllIIlIIlIIlIIIlllIIIlllIIlIllIllIlllIIlIllIllIIIlIIIllIIlIlIIIlIIIlIIIlllIllIIlllIIIllIIlIllIlIIlIIIllIIIlllIIlIIIlllIIlIlIIIllIIIlIIlIIIlIIIllIllIl;
    @NotNull
    private static final RaytraceBlock[] IlllIIIlllIIllIIIlllIIllIllIIIllIIllIIlIIlllIIlllIIlllIIlllIIllIlIIlIIlIIIlllIIllIlllIIllIIllIIllIIlIlllIllIIIlIlllIlllIIlllIllIIllIIllIlIIlllIlIIllIlIIIllIIIlllIIllIlllIIllIlIIlIIlllIIIlIIllIIIlllIIlIlIIlllIIIlIIlllIllIlIllIIllIlllIIlll;
    
    public IllIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll() {
        this.IIllIIlIIllIIIlllIllIIlIIllIIllIIIllIIllIIlIIllIIIlllIIllIIllIllIlIIIlllIIIllIIlllIIIlllIIlllIIllIlIIlIlllIlllIIllIIIlllIIllIIlIIIlIIlllIIIllIIIlllIlIIlIIlIllIIIllIllIIIlllIIIlIlllIllIIllIlIIIllIIIlllIIlIIIlIIlIIlIlIIlIllIIIlllIllIIIllIIll = (ObjectOpenHashSet<RaytraceBlock>)new ObjectOpenHashSet();
    }
    
    @Override
    public void plusAssign(@NotNull final RaytraceBlock raytraceBlock) {
        Intrinsics.checkNotNullParameter((Object)raytraceBlock, "");
        synchronized (this) {
            if (this.IIllIIlIIllIIIlllIllIIlIIllIIllIIIllIIllIIlIIllIIIlllIIllIIllIllIlIIIlllIIIllIIlllIIIlllIIlllIIllIlIIlIlllIlllIIllIIIlllIIllIIlIIIlIIlllIIIllIIIlllIlIIlIIlIllIIIllIllIIIlllIIIlIlllIllIIllIlIIIllIIIlllIIlIIIlIIlIIlIlIIlIllIIIlllIllIIIllIIll.add((Object)raytraceBlock)) {
                this.IIlIlIlIlIllIIIllIIlllIlllIIIllIIIlIIIlllIIIlllIllIIllIIlIIIllIIlllIIIlIIllIIllIIlIIllIIlIIIlIIllIIllIIlIIlIIlIlIIIllIIIllIIlllIIIlIIIllIIllIIllIIIlllIlIIlIIIllIIllIIllIIIlllIIIlllIIIllIIllIIIllIIlllIIIlIIlIIlllIIlllIIlllIllIIllIIlllIIlIlIll = null;
            }
            final Unit instance = Unit.INSTANCE;
        }
        raytraceBlock.setGroup(this);
    }
    
    @Override
    public void minusAssign(@NotNull final RaytraceBlock raytraceBlock) {
        Intrinsics.checkNotNullParameter((Object)raytraceBlock, "");
        synchronized (this) {
            if (this.IIllIIlIIllIIIlllIllIIlIIllIIllIIIllIIllIIlIIllIIIlllIIllIIllIllIlIIIlllIIIllIIlllIIIlllIIlllIIllIlIIlIlllIlllIIllIIIlllIIllIIlIIIlIIlllIIIllIIIlllIlIIlIIlIllIIIllIllIIIlllIIIlIlllIllIIllIlIIIllIIIlllIIlIIIlIIlIIlIlIIlIllIIIlllIllIIIllIIll.remove((Object)raytraceBlock)) {
                this.IIlIlIlIlIllIIIllIIlllIlllIIIllIIIlIIIlllIIIlllIllIIllIIlIIIllIIlllIIIlIIllIIllIIlIIllIIlIIIlIIllIIllIIlIIlIIlIlIIIllIIIllIIlllIIIlIIIllIIllIIllIIIlllIlIIlIIIllIIllIIllIIIlllIIIlllIIIllIIllIIIllIIlllIIIlIIlIIlllIIlllIIlllIllIIllIIlllIIlIlIll = null;
            }
            final Unit instance = Unit.INSTANCE;
        }
        raytraceBlock.setGroup(null);
    }
    
    @Override
    public void forEach(@NotNull final Function1<? super RaytraceBlock, Unit> function1) {
        Intrinsics.checkNotNullParameter((Object)function1, "");
        final RaytraceBlock[] iIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll = this.IIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll();
        for (int i = 0; i < iIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll.length; ++i) {
            function1.invoke((Object)iIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll[i]);
        }
    }
    
    @JvmName(name = "getSize")
    @Override
    public int getSize() {
        return this.IIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll().length;
    }
    
    @Override
    public void merge(@NotNull final RaytraceBlockGroup raytraceBlockGroup) {
        Intrinsics.checkNotNullParameter((Object)raytraceBlockGroup, "");
        if (!(raytraceBlockGroup instanceof IllIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll)) {
            return;
        }
        final RaytraceBlock[] iIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll = ((IllIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll)raytraceBlockGroup).IIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll();
        synchronized (this) {
            for (int i = 0; i < iIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll.length; ++i) {
                this.IIllIIlIIllIIIlllIllIIlIIllIIllIIIllIIllIIlIIllIIIlllIIllIIllIllIlIIIlllIIIllIIlllIIIlllIIlllIIllIlIIlIlllIlllIIllIIIlllIIllIIlIIIlIIlllIIIllIIIlllIlIIlIIlIllIIIllIllIIIlllIIIlIlllIllIIllIlIIIllIIIlllIIlIIIlIIlIIlIlIIlIllIIIlllIllIIIllIIll.add((Object)iIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll[i]);
            }
            this.IIlIlIlIlIllIIIllIIlllIlllIIIllIIIlIIIlllIIIlllIllIIllIIlIIIllIIlllIIIlIIllIIllIIlIIllIIlIIIlIIllIIllIIlIIlIIlIlIIIllIIIllIIlllIIIlIIIllIIllIIllIIIlllIlIIlIIIllIIllIIllIIIlllIIIlllIIIllIIllIIIllIIlllIIIlIIlIIlllIIlllIIlllIllIIllIIlllIIlIlIll = null;
            final Unit instance = Unit.INSTANCE;
        }
        for (int j = 0; j < iIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll.length; ++j) {
            iIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll[j].setGroup(this);
        }
    }
    
    @Override
    public boolean checkVisibility(final int iiIlllIllIllIllIIllIllIIIllIIllIlIIIllIIlIllIllIIlIIIllIIIlIIlllIlIIIllIlIIlllIIlIIlllIlIIlIIIllIIllIIlIIlllIIllIllIllIIIllIllIlIlllIIlIllIlIllIIIlllIIlIIlllIIlIIllIIIlllIlllIIlIIllIIlllIllIllIIlIIllIIllIllIIIlIIlIIIllIIl, @NotNull final Function1<? super RaytraceBlock, Boolean> function1) {
        Intrinsics.checkNotNullParameter((Object)function1, "");
        if (iiIlllIllIllIllIIllIllIIIllIIllIlIIIllIIlIllIllIIlIIIllIIIlIIlllIlIIIllIlIIlllIIlIIlllIlIIlIIIllIIllIIlIIlllIIllIllIllIIIllIllIlIlllIIlIllIlIllIIIlllIIlIIlllIIlIIllIIIlllIlllIIlIIllIIlllIllIllIIlIIllIIllIllIIIlIIlIIIllIIl == this.IIIlllIllIllIllIIllIllIIIllIIllIlIIIllIIlIllIllIIlIIIllIIIlIIlllIlIIIllIlIIlllIIlIIlllIlIIlIIIllIIllIIlIIlllIIllIllIllIIIllIllIlIlllIIlIllIlIllIIIlllIIlIIlllIIlIIllIIIlllIlllIIlIIllIIlllIllIllIIlIIllIIllIllIIIlIIlIIIllIIl) {
            return this.IllIIlIllIlIllIIIllIlIlllIllIllIlIllIIIllIIlllIIIllIllIIllIIllIIllIllIllIIlIIlIIlIIIlllIIIlllIIlIllIllIlllIIlIllIllIIIlIIIllIIlIlIIIlIIIlIIIlllIllIIlllIIIllIIlIllIlIIlIIIllIIIlllIIlIIIlllIIlIlIIIllIIIlIIlIIIlIIIllIllIl;
        }
        boolean illIIlIllIlIllIIIllIlIlllIllIllIlIllIIIllIIlllIIIllIllIIllIIllIIllIllIllIIlIIlIIlIIIlllIIIlllIIlIllIllIlllIIlIllIllIIIlIIIllIIlIlIIIlIIIlIIIlllIllIIlllIIIllIIlIllIlIIlIIIllIIIlllIIlIIIlllIIlIlIIIllIIIlIIlIIIlIIIllIllIl = false;
        final RaytraceBlock[] iIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll = this.IIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll();
        for (int i = 0; i < iIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll.length; ++i) {
            if (function1.invoke((Object)iIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll[i])) {
                illIIlIllIlIllIIIllIlIlllIllIllIlIllIIIllIIlllIIIllIllIIllIIllIIllIllIllIIlIIlIIlIIIlllIIIlllIIlIllIllIlllIIlIllIllIIIlIIIllIIlIlIIIlIIIlIIIlllIllIIlllIIIllIIlIllIlIIlIIIllIIIlllIIlIIIlllIIlIlIIIllIIIlIIlIIIlIIIllIllIl = true;
                break;
            }
        }
        this.IIIlllIllIllIllIIllIllIIIllIIllIlIIIllIIlIllIllIIlIIIllIIIlIIlllIlIIIllIlIIlllIIlIIlllIlIIlIIIllIIllIIlIIlllIIllIllIllIIIllIllIlIlllIIlIllIlIllIIIlllIIlIIlllIIlIIllIIIlllIlllIIlIIllIIlllIllIllIIlIIllIIllIllIIIlIIlIIIllIIl = iiIlllIllIllIllIIllIllIIIllIIllIlIIIllIIlIllIllIIlIIIllIIIlIIlllIlIIIllIlIIlllIIlIIlllIlIIlIIIllIIllIIlIIlllIIllIllIllIIIllIllIlIlllIIlIllIlIllIIIlllIIlIIlllIIlIIllIIIlllIlllIIlIIllIIlllIllIllIIlIIllIIllIllIIIlIIlIIIllIIl;
        return this.IllIIlIllIlIllIIIllIlIlllIllIllIlIllIIIllIIlllIIIllIllIIllIIllIIllIllIllIIlIIlIIlIIIlllIIIlllIIlIllIllIlllIIlIllIllIIIlIIIllIIlIlIIIlIIIlIIIlllIllIIlllIIIllIIlIllIlIIlIIIllIIIlllIIlIIIlllIIlIlIIIllIIIlIIlIIIlIIIllIllIl = illIIlIllIlIllIIIllIlIlllIllIllIlIllIIIllIIlllIIIllIllIIllIIllIIllIllIllIIlIIlIIlIIIlllIIIlllIIlIllIllIlllIIlIllIllIIIlIIIllIIlIlIIIlIIIlIIIlllIllIIlllIIIllIIlIllIlIIlIIIllIIIlllIIlIIIlllIIlIlIIIllIIIlIIlIIIlIIIllIllIl;
    }
    
    private final RaytraceBlock[] IIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll() {
        final RaytraceBlock[] iIlIlIlIlIllIIIllIIlllIlllIIIllIIIlIIIlllIIIlllIllIIllIIlIIIllIIlllIIIlIIllIIllIIlIIllIIlIIIlIIllIIllIIlIIlIIlIlIIIllIIIllIIlllIIIlIIIllIIllIIllIIIlllIlIIlIIIllIIllIIllIIIlllIIIlllIIIllIIllIIIllIIlllIIIlIIlIIlllIIlllIIlllIllIIllIIlllIIlIlIll = this.IIlIlIlIlIllIIIllIIlllIlllIIIllIIIlIIIlllIIIlllIllIIllIIlIIIllIIlllIIIlIIllIIllIIlIIllIIlIIIlIIllIIllIIlIIlIIlIlIIIllIIIllIIlllIIIlIIIllIIllIIllIIIlllIlIIlIIIllIIllIIllIIIlllIIIlllIIIllIIllIIIllIIlllIIIlIIlIIlllIIlllIIlllIllIIllIIlllIIlIlIll;
        if (iIlIlIlIlIllIIIllIIlllIlllIIIllIIIlIIIlllIIIlllIllIIllIIlIIIllIIlllIIIlIIllIIllIIlIIllIIlIIIlIIllIIllIIlIIlIIlIlIIIllIIIllIIlllIIIlIIIllIIllIIllIIIlllIlIIlIIIllIIllIIllIIIlllIIIlllIIIllIIllIIIllIIlllIIIlIIlIIlllIIlllIIlllIllIIllIIlllIIlIlIll != null) {
            return iIlIlIlIlIllIIIllIIlllIlllIIIllIIIlIIIlllIIIlllIllIIllIIlIIIllIIlllIIIlIIllIIllIIlIIllIIlIIIlIIllIIllIIlIIlIIlIlIIIllIIIllIIlllIIIlIIIllIIllIIllIIIlllIlIIlIIIllIIllIIllIIIlllIIIlllIIIllIIllIIIllIIlllIIIlIIlIIlllIIlllIIlllIllIIllIIlllIIlIlIll;
        }
        synchronized (this) {
            final RaytraceBlock[] iIlIlIlIlIllIIIllIIlllIlllIIIllIIIlIIIlllIIIlllIllIIllIIlIIIllIIlllIIIlIIllIIllIIlIIllIIlIIIlIIllIIllIIlIIlIIlIlIIIllIIIllIIlllIIIlIIIllIIllIIllIIIlllIlIIlIIIllIIllIIllIIIlllIIIlllIIIllIIllIIIllIIlllIIIlIIlIIlllIIlllIIlllIllIIllIIlllIIlIlIll2 = this.IIlIlIlIlIllIIIllIIlllIlllIIIllIIIlIIIlllIIIlllIllIIllIIlIIIllIIlllIIIlIIllIIllIIlIIllIIlIIIlIIllIIllIIlIIlIIlIlIIIllIIIllIIlllIIIlIIIllIIllIIllIIIlllIlIIlIIIllIIllIIllIIIlllIIIlllIIIllIIllIIIllIIlllIIIlIIlIIlllIIlllIIlllIllIIllIIlllIIlIlIll;
            if (iIlIlIlIlIllIIIllIIlllIlllIIIllIIIlIIIlllIIIlllIllIIllIIlIIIllIIlllIIIlIIllIIllIIlIIllIIlIIIlIIllIIllIIlIIlIIlIlIIIllIIIllIIlllIIIlIIIllIIllIIllIIIlllIlIIlIIIllIIllIIllIIIlllIIIlllIIIllIIllIIIllIIlllIIIlIIlIIlllIIlllIIlllIllIIllIIlllIIlIlIll2 != null) {
                return iIlIlIlIlIllIIIllIIlllIlllIIIllIIIlIIIlllIIIlllIllIIllIIlIIIllIIlllIIIlIIllIIllIIlIIllIIlIIIlIIllIIllIIlIIlIIlIlIIIllIIIllIIlllIIIlIIIllIIllIIllIIIlllIlIIlIIIllIIllIIllIIIlllIIIlllIIIllIIllIIIllIIlllIIIlIIlIIlllIIlllIIlllIllIIllIIlllIIlIlIll2;
            }
            return this.IIlIlIlIlIllIIIllIIlllIlllIIIllIIIlIIIlllIIIlllIllIIllIIlIIIllIIlllIIIlIIllIIllIIlIIllIIlIIIlIIllIIllIIlIIlIIlIlIIIllIIIllIIlllIIIlIIIllIIllIIllIIIlllIlIIlIIIllIIllIIllIIIlllIIIlllIIIllIIllIIIllIIlllIIIlIIlIIlllIIlllIIlllIllIIllIIlllIIlIlIll = (this.IIllIIlIIllIIIlllIllIIlIIllIIllIIIllIIllIIlIIllIIIlllIIllIIllIllIlIIIlllIIIllIIlllIIIlllIIlllIIllIlIIlIlllIlllIIllIIIlllIIllIIlIIIlIIlllIIIllIIIlllIlIIlIIlIllIIIllIllIIIlllIIIlIlllIllIIllIlIIIllIIIlllIIlIIIlIIlIIlIlIIlIllIIIlllIllIIIllIIll.isEmpty() ? IllIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll.IlllIIIlllIIllIIIlllIIllIllIIIllIIllIIlIIlllIIlllIIlllIIlllIIllIlIIlIIlIIIlllIIllIlllIIllIIllIIllIIlIlllIllIIIlIlllIlllIIlllIllIIllIIllIlIIlllIlIIllIlIIIllIIIlllIIllIlllIIllIlIIlIIlllIIIlIIllIIIlllIIlIlIIlllIIIlIIlllIllIlIllIIllIlllIIlll : ((Collection)this.IIllIIlIIllIIIlllIllIIlIIllIIllIIIllIIllIIlIIllIIIlllIIllIIllIllIlIIIlllIIIllIIlllIIIlllIIlllIIllIlIIlIlllIlllIIllIIIlllIIllIIlIIIlIIlllIIIllIIIlllIlIIlIIlIllIIIllIllIIIlllIIIlIlllIllIIllIlIIIllIIIlllIIlIIIlIIlIIlIlIIlIllIIIlllIllIIIllIIll).toArray(new RaytraceBlock[0]));
        }
    }
    
    static {
        IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll = new IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(null);
        IlllIIIlllIIllIIIlllIIllIllIIIllIIllIIlIIlllIIlllIIlllIIlllIIllIlIIlIIlIIIlllIIllIlllIIllIIllIIllIIlIlllIllIIIlIlllIlllIIlllIllIIllIIllIlIIlllIlIIllIlIIIllIIIlllIIllIlllIIllIlIIlIIlllIIIlIIllIIIlllIIlIlIIlllIIIlIIlllIllIlIllIIllIlllIIlll = new RaytraceBlock[0];
    }
    
    @Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007" }, d2 = { "Ldev/imanity/antixray/a/IllIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll$IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;", "", "<init>", "()V", "", "Ldev/imanity/antixray/api/block/RaytraceBlock;", "IlllIIIlllIIllIIIlllIIllIllIIIllIIllIIlIIlllIIlllIIlllIIlllIIllIlIIlIIlIIIlllIIllIlllIIllIIllIIllIIlIlllIllIIIlIlllIlllIIlllIllIIllIIllIlIIlllIlIIllIlIIIllIIIlllIIllIlllIIllIlIIlIIlllIIIlIIllIIIlllIIlIlIIlllIIIlIIlllIllIlIllIIllIlllIIlll", "[Ldev/imanity/antixray/api/block/RaytraceBlock;", "IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll" })
    private static final class IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll
    {
    }
}
