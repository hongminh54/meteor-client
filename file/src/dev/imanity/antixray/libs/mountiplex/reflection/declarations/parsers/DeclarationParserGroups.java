// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers;

import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.util.stream.Stream;
import java.util.Arrays;

public class DeclarationParserGroups
{
    public static final DeclarationParser[] BASE;
    public static final DeclarationParser[] SOURCE;
    public static final DeclarationParser[] CLASS;
    
    static {
        BASE = new DeclarationParser[] { DeclarationParserTypes.COMMENT, DeclarationParserTypes.BOOTSTRAP, DeclarationParserTypes.RESOLVER, DeclarationParserTypes.SET_VARIABLE, DeclarationParserTypes.REQUIREMENT, DeclarationParserTypes.REMAPPING, DeclarationParserTypes.ERROR, DeclarationParserTypes.WARNING };
        SOURCE = Stream.concat((Stream<?>)Arrays.stream(DeclarationParserGroups.BASE), (Stream<?>)Stream.of((T[])new DeclarationParser[] { DeclarationParserTypes.PACKAGE, DeclarationParserTypes.IMPORT, DeclarationParserTypes.INCLUDE, DeclarationParserTypes.SET_PATH })).toArray(DeclarationParser[]::new);
        CLASS = Stream.concat((Stream<?>)MountiplexUtil.toStream(DeclarationParserTypes.CODE_BLOCK), (Stream<?>)Arrays.stream(DeclarationParserGroups.BASE)).toArray(DeclarationParser[]::new);
    }
}
