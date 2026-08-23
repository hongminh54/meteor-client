// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.config;

import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import kotlin.enums.EnumEntries;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005" }, d2 = { "Ldev/imanity/antixray/api/config/ConfigType;", "", "<init>", "(Ljava/lang/String;I)V", "DEFAULT", "CUSTOM" })
public enum ConfigType
{
    DEFAULT, 
    CUSTOM;
    
    private static final /* synthetic */ ConfigType[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;
    
    @NotNull
    public static EnumEntries<ConfigType> getEntries() {
        return (EnumEntries<ConfigType>)ConfigType.$ENTRIES;
    }
    
    static {
        $VALUES = $values();
        $ENTRIES = EnumEntriesKt.enumEntries((Enum[])ConfigType.$VALUES);
    }
}
