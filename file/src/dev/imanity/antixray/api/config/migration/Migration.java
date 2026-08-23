// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.config.migration;

import kotlin.jvm.JvmName;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004R\u0014\u0010\b\u001a\u00020\u00058'X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007" }, d2 = { "Ldev/imanity/antixray/api/config/migration/Migration;", "", "", "migrate", "()V", "", "getVersion", "()I", "version" })
public interface Migration
{
    @JvmName(name = "getVersion")
    int getVersion();
    
    void migrate();
}
