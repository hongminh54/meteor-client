// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import io.fairyproject.libs.gson.GsonBuilder;
import io.fairyproject.libs.gson.JsonObject;
import io.fairyproject.libs.gson.Gson;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.internal.SourceDebugExtension;

@SourceDebugExtension({ "SMAP\nHttpUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpUtil.kt\ndev/imanity/antixray/core/util/HttpUtil\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,51:1\n1#2:52\n*E\n" })
public final class IIllIIllIIlIIllIIllIIlllIIlllIllIIIllIIIlllIIlIlIIlIIllIlIIllIllIIlIIllIllIlIIllIllIlllIIIlIIIllIIllIIlIllIIIllIlIIllIIllIIllIlIIlllIIlIIIlllIIIlllIIllIIlllIIIlllIIIllIIIllIIllIIllIIllIlllIlIIIlllIIlIIIlIlllIIllIIllIIllIIIlIIllIIlllIlll
{
    @NotNull
    public static final IIllIIllIIlIIllIIllIIlllIIlllIllIIIllIIIlllIIlIlIIlIIllIlIIllIllIIlIIllIllIlIIllIllIlllIIIlIIIllIIllIIlIllIIIllIlIIllIIllIIllIlIIlllIIlIIIlllIIIlllIIllIIlllIIIlllIIIllIIIllIIllIIllIIllIlllIlIIIlllIIlIIIlIlllIIllIIllIIllIIIlIIllIIlllIlll cR;
    private static final Gson cS;
    
    private IIllIIllIIlIIllIIllIIlllIIlllIllIIIllIIIlllIIlIlIIlIIllIlIIllIllIIlIIllIllIlIIllIllIlllIIIlIIIllIIllIIlIllIIIllIlIIllIIllIIllIlIIlllIIlIIIlllIIIlllIIllIIlllIIIlllIIIllIIIllIIllIIllIIllIlllIlIIIlllIIlIIIlIlllIIllIIllIIllIIIlIIllIIlllIlll() {
    }
    
    public final long IlIIlllIIllIIllIIllIIIlllIIllIllIllIIlllIIIlllIIlIllIIlllIIlllIIlllIIlllIlIIIllIIlllIllIIIlllIIIllIllIIIllIlIlllIIlllIIlllIlIlllIIllIIIllIIIlllIlllIIlIllIIIllIIlIIllIIllIIllIIllIIlllIlIIIlIIllIIlIIlllIlllIIIllIIllIIllIIllIlIIllIIIlllIlllIIlIll() {
        return System.currentTimeMillis();
    }
    
    public final boolean IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final String p0, @NotNull final JsonObject p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc             ""
        //     3: invokestatic    kotlin/jvm/internal/Intrinsics.checkNotNullParameter:(Ljava/lang/Object;Ljava/lang/String;)V
        //     6: aload_2        
        //     7: ldc             ""
        //     9: invokestatic    kotlin/jvm/internal/Intrinsics.checkNotNullParameter:(Ljava/lang/Object;Ljava/lang/String;)V
        //    12: new             Ljava/net/URL;
        //    15: dup            
        //    16: aload_1        
        //    17: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    20: astore_3       
        //    21: iconst_1       
        //    22: invokestatic    java/net/HttpURLConnection.setFollowRedirects:(Z)V
        //    25: aload_3        
        //    26: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    29: dup            
        //    30: invokestatic    kotlin/jvm/internal/Intrinsics.checkNotNull:(Ljava/lang/Object;)V
        //    33: checkcast       Ljava/net/HttpURLConnection;
        //    36: astore          4
        //    38: aload_2        
        //    39: invokevirtual   io/fairyproject/libs/gson/JsonObject.toString:()Ljava/lang/String;
        //    42: dup            
        //    43: ldc             ""
        //    45: invokestatic    kotlin/jvm/internal/Intrinsics.checkNotNullExpressionValue:(Ljava/lang/Object;Ljava/lang/String;)V
        //    48: astore          5
        //    50: aload           4
        //    52: ldc             "POST"
        //    54: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
        //    57: aload           4
        //    59: ldc             "Content-Length"
        //    61: aload           5
        //    63: invokevirtual   java/lang/String.length:()I
        //    66: invokestatic    java/lang/String.valueOf:(I)Ljava/lang/String;
        //    69: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //    72: aload           4
        //    74: ldc             "Content-Type"
        //    76: ldc             "application/json"
        //    78: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //    81: aload           4
        //    83: ldc             "User-Agent"
        //    85: ldc             "Application"
        //    87: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //    90: aload           4
        //    92: sipush          5000
        //    95: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
        //    98: aload           4
        //   100: sipush          5000
        //   103: invokevirtual   java/net/HttpURLConnection.setReadTimeout:(I)V
        //   106: aload           4
        //   108: iconst_1       
        //   109: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //   112: aload           4
        //   114: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   117: checkcast       Ljava/io/Closeable;
        //   120: astore          6
        //   122: aconst_null    
        //   123: astore          7
        //   125: nop            
        //   126: aload           6
        //   128: checkcast       Ljava/io/OutputStream;
        //   131: astore          8
        //   133: iconst_0       
        //   134: istore          9
        //   136: aload           8
        //   138: aload           5
        //   140: getstatic       kotlin/text/Charsets.UTF_8:Ljava/nio/charset/Charset;
        //   143: swap           
        //   144: swap           
        //   145: invokevirtual   java/lang/String.getBytes:(Ljava/nio/charset/Charset;)[B
        //   148: dup            
        //   149: ldc             ""
        //   151: invokestatic    kotlin/jvm/internal/Intrinsics.checkNotNullExpressionValue:(Ljava/lang/Object;Ljava/lang/String;)V
        //   154: invokevirtual   java/io/OutputStream.write:([B)V
        //   157: nop            
        //   158: getstatic       kotlin/Unit.INSTANCE:Lkotlin/Unit;
        //   161: astore          8
        //   163: aload           6
        //   165: aload           7
        //   167: invokestatic    kotlin/io/CloseableKt.closeFinally:(Ljava/io/Closeable;Ljava/lang/Throwable;)V
        //   170: goto            194
        //   173: astore          8
        //   175: aload           8
        //   177: astore          7
        //   179: aload           8
        //   181: athrow         
        //   182: astore          8
        //   184: aload           6
        //   186: aload           7
        //   188: invokestatic    kotlin/io/CloseableKt.closeFinally:(Ljava/io/Closeable;Ljava/lang/Throwable;)V
        //   191: aload           8
        //   193: athrow         
        //   194: aload           4
        //   196: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   199: sipush          500
        //   202: if_icmplt       207
        //   205: iconst_1       
        //   206: ireturn        
        //   207: aload           4
        //   209: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   212: sipush          200
        //   215: if_icmpne       346
        //   218: aload           4
        //   220: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   223: dup            
        //   224: ldc             ""
        //   226: invokestatic    kotlin/jvm/internal/Intrinsics.checkNotNullExpressionValue:(Ljava/lang/Object;Ljava/lang/String;)V
        //   229: astore          7
        //   231: getstatic       kotlin/text/Charsets.UTF_8:Ljava/nio/charset/Charset;
        //   234: astore          8
        //   236: new             Ljava/io/InputStreamReader;
        //   239: dup            
        //   240: aload           7
        //   242: aload           8
        //   244: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V
        //   247: checkcast       Ljava/io/Reader;
        //   250: astore          9
        //   252: sipush          8192
        //   255: istore          10
        //   257: aload           9
        //   259: instanceof      Ljava/io/BufferedReader;
        //   262: ifeq            273
        //   265: aload           9
        //   267: checkcast       Ljava/io/BufferedReader;
        //   270: goto            284
        //   273: new             Ljava/io/BufferedReader;
        //   276: dup            
        //   277: aload           9
        //   279: iload           10
        //   281: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;I)V
        //   284: checkcast       Ljava/io/Closeable;
        //   287: astore          7
        //   289: aconst_null    
        //   290: astore          8
        //   292: nop            
        //   293: aload           7
        //   295: checkcast       Ljava/io/BufferedReader;
        //   298: astore          9
        //   300: iconst_0       
        //   301: istore          10
        //   303: aload           9
        //   305: checkcast       Ljava/io/Reader;
        //   308: invokestatic    kotlin/io/TextStreamsKt.readText:(Ljava/io/Reader;)Ljava/lang/String;
        //   311: astore          9
        //   313: aload           7
        //   315: aload           8
        //   317: invokestatic    kotlin/io/CloseableKt.closeFinally:(Ljava/io/Closeable;Ljava/lang/Throwable;)V
        //   320: aload           9
        //   322: goto            474
        //   325: astore          9
        //   327: aload           9
        //   329: astore          8
        //   331: aload           9
        //   333: athrow         
        //   334: astore          9
        //   336: aload           7
        //   338: aload           8
        //   340: invokestatic    kotlin/io/CloseableKt.closeFinally:(Ljava/io/Closeable;Ljava/lang/Throwable;)V
        //   343: aload           9
        //   345: athrow         
        //   346: aload           4
        //   348: invokevirtual   java/net/HttpURLConnection.getErrorStream:()Ljava/io/InputStream;
        //   351: dup            
        //   352: ldc             ""
        //   354: invokestatic    kotlin/jvm/internal/Intrinsics.checkNotNullExpressionValue:(Ljava/lang/Object;Ljava/lang/String;)V
        //   357: astore          7
        //   359: getstatic       kotlin/text/Charsets.UTF_8:Ljava/nio/charset/Charset;
        //   362: astore          8
        //   364: new             Ljava/io/InputStreamReader;
        //   367: dup            
        //   368: aload           7
        //   370: aload           8
        //   372: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V
        //   375: checkcast       Ljava/io/Reader;
        //   378: astore          9
        //   380: sipush          8192
        //   383: istore          10
        //   385: aload           9
        //   387: instanceof      Ljava/io/BufferedReader;
        //   390: ifeq            401
        //   393: aload           9
        //   395: checkcast       Ljava/io/BufferedReader;
        //   398: goto            412
        //   401: new             Ljava/io/BufferedReader;
        //   404: dup            
        //   405: aload           9
        //   407: iload           10
        //   409: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;I)V
        //   412: checkcast       Ljava/io/Closeable;
        //   415: astore          7
        //   417: aconst_null    
        //   418: astore          8
        //   420: nop            
        //   421: aload           7
        //   423: checkcast       Ljava/io/BufferedReader;
        //   426: astore          9
        //   428: iconst_0       
        //   429: istore          10
        //   431: aload           9
        //   433: checkcast       Ljava/io/Reader;
        //   436: invokestatic    kotlin/io/TextStreamsKt.readText:(Ljava/io/Reader;)Ljava/lang/String;
        //   439: astore          9
        //   441: aload           7
        //   443: aload           8
        //   445: invokestatic    kotlin/io/CloseableKt.closeFinally:(Ljava/io/Closeable;Ljava/lang/Throwable;)V
        //   448: aload           9
        //   450: goto            474
        //   453: astore          9
        //   455: aload           9
        //   457: astore          8
        //   459: aload           9
        //   461: athrow         
        //   462: astore          9
        //   464: aload           7
        //   466: aload           8
        //   468: invokestatic    kotlin/io/CloseableKt.closeFinally:(Ljava/io/Closeable;Ljava/lang/Throwable;)V
        //   471: aload           9
        //   473: athrow         
        //   474: astore          6
        //   476: getstatic       dev/imanity/antixray/a/IIllIIllIIlIIllIIllIIlllIIlllIllIIIllIIIlllIIlIlIIlIIllIlIIllIllIIlIIllIllIlIIllIllIlllIIIlIIIllIIllIIlIllIIIllIlIIllIIllIIllIlIIlllIIlIIIlllIIIlllIIllIIlllIIIlllIIIllIIIllIIllIIllIIllIlllIlIIIlllIIlIIIlIlllIIllIIllIIllIIIlIIllIIlllIlll.cS:Lio/fairyproject/libs/gson/Gson;
        //   479: aload           6
        //   481: ldc             Lio/fairyproject/libs/gson/JsonObject;.class
        //   483: invokevirtual   io/fairyproject/libs/gson/Gson.fromJson:(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
        //   486: checkcast       Lio/fairyproject/libs/gson/JsonObject;
        //   489: astore          7
        //   491: aload           7
        //   493: new             Ldev/imanity/antixray/a/IlllIlllIIlllIIIllIIlllIllIIlIIlIIlIllIlllIllIlIIIlllIIIlIllIIllIIIllIllIIlIIIlIIlllIllIlIIllIIllIIlllIIIllIIllIIIlllIIIllIIllIIIlllIllIIIlllIIllIIIlIIllIlIllIIllIIlllIIllIIIllIIIlIIllIIlllIIIlIlllIIIllIIllIIIllIIllIIlIllIIlIlIIlIllIIlll;
        //   496: dup            
        //   497: invokespecial   dev/imanity/antixray/a/IlllIlllIIlllIIIllIIlllIllIIlIIlIIlIllIlllIllIlIIIlllIIIlIllIIllIIIllIllIIlIIIlIIlllIllIlIIllIIllIIlllIIIllIIllIIIlllIIIllIIllIIIlllIllIIIlllIIllIIIlIIllIlIllIIllIIlllIIllIIIllIIIlIIllIIlllIIIlIlllIIIllIIllIIIllIIllIIlIllIIlIlIIlIllIIlll.<init>:()V
        //   500: invokevirtual   dev/imanity/antixray/a/IlllIlllIIlllIIIllIIlllIllIIlIIlIIlIllIlllIllIlIIIlllIIIlIllIIllIIIllIllIIlIIIlIIlllIllIlIIllIIllIIlllIIIllIIllIIIlllIIIllIIllIIIlllIllIIIlllIIllIIIlIIllIlIllIIllIIlllIIllIIIllIIIlIIllIIlllIIIlIlllIIIllIIllIIIllIIllIIlIllIIlIlIIlIllIIlll.toString:()Ljava/lang/String;
        //   503: invokevirtual   io/fairyproject/libs/gson/JsonObject.get:(Ljava/lang/String;)Lio/fairyproject/libs/gson/JsonElement;
        //   506: invokevirtual   io/fairyproject/libs/gson/JsonElement.getAsBoolean:()Z
        //   509: ifne            516
        //   512: iconst_1       
        //   513: goto            517
        //   516: iconst_0       
        //   517: ireturn        
        //    StackMapTable: 00 10 FF 00 AD 00 08 00 00 00 00 00 00 07 00 6D 05 00 01 07 00 1C FF 00 08 00 08 00 00 00 00 00 00 07 00 6D 07 00 1C 00 01 07 00 1C FF 00 0B 00 05 00 00 00 00 07 00 2B 00 00 0C FF 00 41 00 0B 00 00 00 00 00 00 00 00 00 07 00 97 01 00 00 FF 00 0A 00 00 00 01 07 00 99 FF 00 28 00 09 00 00 00 00 00 00 00 07 00 6D 05 00 01 07 00 1C FF 00 08 00 09 00 00 00 00 00 00 00 07 00 6D 07 00 1C 00 01 07 00 1C FF 00 0B 00 05 00 00 00 00 07 00 2B 00 00 FF 00 36 00 0B 00 00 00 00 00 00 00 00 00 07 00 97 01 00 00 FF 00 0A 00 00 00 01 07 00 99 FF 00 28 00 09 00 00 00 00 00 00 00 07 00 6D 05 00 01 07 00 1C FF 00 08 00 09 00 00 00 00 00 00 00 07 00 6D 07 00 1C 00 01 07 00 1C FF 00 0B 00 00 00 01 07 00 49 29 40 01
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  125    163    173    182    Ljava/lang/Throwable;
        //  125    163    182    194    Any
        //  173    182    182    194    Any
        //  182    184    182    194    Any
        //  292    313    325    334    Ljava/lang/Throwable;
        //  292    313    334    346    Any
        //  325    334    334    346    Any
        //  334    336    334    346    Any
        //  420    441    453    462    Ljava/lang/Throwable;
        //  420    441    462    474    Any
        //  453    462    462    474    Any
        //  462    464    462    474    Any
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
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visitVariable(StackMappingVisitor.java:474)
        //     at com.strobel.assembler.ir.Instruction.accept(Instruction.java:553)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:403)
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
    
    static {
        cR = new IIllIIllIIlIIllIIllIIlllIIlllIllIIIllIIIlllIIlIlIIlIIllIlIIllIllIIlIIllIllIlIIllIllIlllIIIlIIIllIIllIIlIllIIIllIlIIllIIllIIllIlIIlllIIlIIIlllIIIlllIIllIIlllIIIlllIIIllIIIllIIllIIllIIllIlllIlIIIlllIIlIIIlIlllIIllIIllIIllIIIlIIllIIlllIlll();
        cS = new GsonBuilder().setLenient().create();
    }
}
