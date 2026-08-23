// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import kotlin.Unit;
import org.bukkit.Chunk;
import dev.imanity.antixray.api.util.LongHash;
import kotlin.jvm.internal.Intrinsics;
import java.util.function.Function;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import org.jetbrains.annotations.NotNull;
import org.bukkit.World;

public final class IllIIIlllIllIIIllIlllIlIIlllIIIllIIllIlIIlIIllIIlllIIlllIIllIIIllIIIlIIlIllIllIIllIIllIllIIIllIllIIllIIIlllIlllIIllIIIllIIIllIIIlIIllIIllIIIllIIlllIIIllIIlIIIlIlllIIlllIllIIllIIlllIllIlIlIllIIlllIIIlllIIIllIIllIIllIllIIIlllIIllIIllIlIlIIll implements IIllIIlllIIIllIIIlllIlllIIIlIIlIIlIlllIlllIIllIIIllIIlIllIIIllIlllIIlIIIlIllIllIllIlllIllIlIIllIIIllIlIIIllIIllIIIllIlIIIllIllIIIlllIlllIlIIlllIIlIIIllIllIIlllIllIIllIIlllIlIlIIIlllIIlIIlllIIlllIIIllIlIllIIllIllIlllIIlIlIlIlll
{
    @NotNull
    private final World bM;
    @NotNull
    private final Long2ObjectOpenHashMap<IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll> bN;
    @NotNull
    private final Function<Long, IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll> bO;
    
    public IllIIIlllIllIIIllIlllIlIIlllIIIllIIllIlIIlIIllIIlllIIlllIIllIIIllIIIlIIlIllIllIIllIIllIllIIIllIllIIllIIIlllIlllIIllIIIllIIIllIIIlIIllIIllIIIllIIlllIIIllIIlIIIlIlllIIlllIllIIllIIlllIllIlIlIllIIlllIIIlllIIIllIIllIIllIllIIIlllIIllIIllIlIlIIll(@NotNull final World bm) {
        Intrinsics.checkNotNullParameter((Object)bm, "");
        this.bM = bm;
        this.bN = (Long2ObjectOpenHashMap<IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll>)new Long2ObjectOpenHashMap(8192);
        this.bO = IllIIIlllIllIIIllIlllIlIIlllIIIllIIllIlIIlIIllIIlllIIlllIIllIIIllIIIlIIlIllIllIIllIIllIllIIIllIllIIllIIIlllIlllIIllIIIllIIIllIIIlIIllIIllIIIllIIlllIIIllIIlIIIlIlllIIlllIllIIllIIlllIllIlIlIllIIlllIIIlllIIIllIIllIIllIllIIIlllIIllIIllIlIlIIll::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;
    }
    
    @NotNull
    @Override
    public World getWorld() {
        return this.bM;
    }
    
    @NotNull
    @Override
    public IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll IllIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll(final int n, final int n2) {
        synchronized (this.bN) {
            final Object computeIfAbsent = this.bN.computeIfAbsent((Object)LongHash.INSTANCE.toLong(n, n2), (Function)this.bO);
            Intrinsics.checkNotNullExpressionValue(computeIfAbsent, "");
            return (IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll)computeIfAbsent;
        }
    }
    
    @Override
    public void IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll(@NotNull final Chunk chunk) {
        Intrinsics.checkNotNullParameter((Object)chunk, "");
    }
    
    @Override
    public IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll IIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll(@NotNull final Chunk p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc             ""
        //     3: invokestatic    kotlin/jvm/internal/Intrinsics.checkNotNullParameter:(Ljava/lang/Object;Ljava/lang/String;)V
        //     6: aload_0        
        //     7: getfield        dev/imanity/antixray/a/IllIIIlllIllIIIllIlllIlIIlllIIIllIIllIlIIlIIllIIlllIIlllIIllIIIllIIIlIIlIllIllIIllIIllIllIIIllIllIIllIIIlllIlllIIllIIIllIIIllIIIlIIllIIllIIIllIIlllIIIllIIlIIIlIlllIIlllIllIIllIIlllIllIlIlIllIIlllIIIlllIIIllIIllIIllIllIIIlllIIllIIllIlIlIIll.bN:Lit/unimi/dsi/fastutil/longs/Long2ObjectOpenHashMap;
        //    10: astore_2       
        //    11: aload_2        
        //    12: monitorenter   
        //    13: nop            
        //    14: iconst_0       
        //    15: istore_3       
        //    16: aload_0        
        //    17: getfield        dev/imanity/antixray/a/IllIIIlllIllIIIllIlllIlIIlllIIIllIIllIlIIlIIllIIlllIIlllIIllIIIllIIIlIIlIllIllIIllIIllIllIIIllIllIIllIIIlllIlllIIllIIIllIIIllIIIlIIllIIllIIIllIIlllIIIllIIlIIIlIlllIIlllIllIIllIIlllIllIlIlIllIIlllIIIlllIIIllIIllIIllIllIIIlllIIllIIllIlIlIIll.bN:Lit/unimi/dsi/fastutil/longs/Long2ObjectOpenHashMap;
        //    20: getstatic       dev/imanity/antixray/api/util/LongHash.INSTANCE:Ldev/imanity/antixray/api/util/LongHash;
        //    23: aload_1        
        //    24: invokeinterface org/bukkit/Chunk.getX:()I
        //    29: aload_1        
        //    30: invokeinterface org/bukkit/Chunk.getZ:()I
        //    35: invokevirtual   dev/imanity/antixray/api/util/LongHash.toLong:(II)J
        //    38: invokevirtual   it/unimi/dsi/fastutil/longs/Long2ObjectOpenHashMap.remove:(J)Ljava/lang/Object;
        //    41: checkcast       Ldev/imanity/antixray/a/IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll;
        //    44: astore_3       
        //    45: aload_2        
        //    46: monitorexit    
        //    47: aload_3        
        //    48: goto            56
        //    51: astore_3       
        //    52: aload_2        
        //    53: monitorexit    
        //    54: aload_3        
        //    55: athrow         
        //    56: areturn        
        //    StackMapTable: 00 02 FF 00 33 00 03 00 00 07 00 21 00 01 07 00 5B FF 00 04 00 00 00 01 07 00 59
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  13     45     51     56     Any
        //  51     52     51     56     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
        //     at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
        //     at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
        //     at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
        //     at java.base/java.util.Objects.checkIndex(Objects.java:385)
        //     at java.base/java.util.ArrayList.remove(ArrayList.java:551)
        //     at com.strobel.assembler.ir.StackMappingVisitor.pop(StackMappingVisitor.java:267)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:599)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2086)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:203)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:93)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:868)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:761)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:638)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:605)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:195)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:162)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:137)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:333)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:254)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:144)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void clear() {
        synchronized (this.bN) {
            this.bN.clear();
            final Unit instance = Unit.INSTANCE;
        }
    }
    
    private static final IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final IllIIIlllIllIIIllIlllIlIIlllIIIllIIllIlIIlIIllIIlllIIlllIIllIIIllIIIlIIlIllIllIIllIIllIllIIIllIllIIllIIIlllIlllIIllIIIllIIIllIIIlIIllIIllIIIllIIlllIIIllIIlIIIlIlllIIlllIllIIllIIlllIllIlIlIllIIlllIIIlllIIIllIIllIIllIllIIIlllIIllIIllIlIlIIll illIIIlllIllIIIllIlllIlIIlllIIIllIIllIlIIlIIllIIlllIIlllIIllIIIllIIIlIIlIllIllIIllIIllIllIIIllIllIIllIIIlllIlllIIllIIIllIIIllIIIlIIllIIllIIIllIIlllIIIllIIlIIIlIlllIIlllIllIIllIIlllIllIlIlIllIIlllIIIlllIIIllIIllIIllIllIIIlllIIllIIllIlIlIIll, final Long n) {
        Intrinsics.checkNotNullParameter((Object)illIIIlllIllIIIllIlllIlIIlllIIIllIIllIlIIlIIllIIlllIIlllIIllIIIllIIIlIIlIllIllIIllIIllIllIIIllIllIIllIIIlllIlllIIllIIIllIIIllIIIlIIllIIllIIIllIIlllIIIllIIlIIIlIlllIIlllIllIIllIIlllIllIlIlIllIIlllIIIlllIIIllIIllIIllIllIIIlllIIllIIllIlIlIIll, "");
        final int n2 = IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl.IllIIllIlIlIllIIllIIIllIIIllIIIllIIIlIlIlIllIlIIllIIlllIlllIIlIIIllIIlIllIIlllIIllIllIIIlIIlIlIlllIIIlllIIlIIIllIIIllIIIllIlIIlllIIlIlIIIlllIIlIlIIIlllIIlllIIlIllIllIIlIIIllIIllIlllIIllIIllIllIIllIIllIIlIIllIllIIllIIlIIl(illIIIlllIllIIIllIlllIlIIlllIIIllIIllIlIIlIIllIIlllIIlllIIllIIIllIIIlIIlIllIllIIllIIllIllIIIllIllIIllIIIlllIlllIIllIIIllIIIllIIIlIIllIIllIIIllIIlllIIIllIIlIIIlIlllIIlllIllIIllIIlllIllIlIlIllIIlllIIIlllIIIllIIllIIllIllIIIlllIIllIIllIlIlIIll.getWorld()) >> 4;
        final int n3 = illIIIlllIllIIIllIlllIlIIlllIIIllIIllIlIIlIIllIIlllIIlllIIllIIIllIIIlIIlIllIllIIllIIllIllIIIllIllIIllIIIlllIlllIIllIIIllIIIllIIIlIIllIIllIIIllIIlllIIIllIIlIIIlIlllIIlllIllIIllIIlllIllIlIlIllIIlllIIIlllIIIllIIllIIllIllIIIlllIIllIIllIlIlIIll.getWorld().getMaxHeight() >> 4;
        final LongHash instance = LongHash.INSTANCE;
        Intrinsics.checkNotNull((Object)n);
        return new IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll(n2, n3, instance.msw(n), LongHash.INSTANCE.lsw(n));
    }
}
