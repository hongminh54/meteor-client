// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.config;

import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import kotlin.enums.EnumEntries;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b" }, d2 = { "Ldev/imanity/antixray/api/config/ConfigContextType;", "", "<init>", "(Ljava/lang/String;I)V", "DEFAULT", "OVERWORLD", "NETHER", "END", "CUSTOM" })
public enum ConfigContextType
{
    DEFAULT, 
    OVERWORLD, 
    NETHER, 
    END, 
    CUSTOM;
    
    private static final /* synthetic */ ConfigContextType[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;
    
    @NotNull
    public static EnumEntries<ConfigContextType> getEntries() {
        return (EnumEntries<ConfigContextType>)ConfigContextType.$ENTRIES;
    }
    
    static {
        $VALUES = $values();
        $ENTRIES = EnumEntriesKt.enumEntries((Enum[])ConfigContextType.$VALUES);
    }
}
