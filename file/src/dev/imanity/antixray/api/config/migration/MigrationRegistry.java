// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.config.migration;

import java.util.Iterator;
import java.util.function.Function;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import java.util.LinkedHashMap;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import io.fairyproject.container.InjectableComponent;

@InjectableComponent
@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010!\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR&\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\r0\f8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f" }, d2 = { "Ldev/imanity/antixray/api/config/migration/MigrationRegistry;", "", "<init>", "()V", "Ldev/imanity/antixray/api/config/migration/Migration;", "p0", "", "add", "(Ldev/imanity/antixray/api/config/migration/Migration;)V", "", "migrate", "(I)I", "", "", "migrations", "Ljava/util/Map;" })
@SourceDebugExtension({ "SMAP\nMigrationRegistry.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MigrationRegistry.kt\ndev/imanity/antixray/api/config/migration/MigrationRegistry\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,23:1\n1855#2,2:24\n*S KotlinDebug\n*F\n+ 1 MigrationRegistry.kt\ndev/imanity/antixray/api/config/migration/MigrationRegistry\n*L\n17#1:24,2\n*E\n" })
public final class MigrationRegistry
{
    @NotNull
    private final Map<Integer, List<Migration>> migrations;
    
    public MigrationRegistry() {
        this.migrations = new LinkedHashMap<Integer, List<Migration>>();
    }
    
    public final void add(@NotNull final Migration migration) {
        Intrinsics.checkNotNullParameter((Object)migration, "");
        this.migrations.computeIfAbsent(migration.getVersion(), MigrationRegistry::add$lambda$0).add(migration);
    }
    
    public final int migrate(final int i) {
        final List list = this.migrations.get(i);
        if (list != null) {
            final Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                ((Migration)iterator.next()).migrate();
            }
            return this.migrate(i + 1);
        }
        return i;
    }
    
    private static final List add$lambda$0(final Function1 function1, final Object o) {
        Intrinsics.checkNotNullParameter((Object)function1, "");
        return (List)function1.invoke(o);
    }
}
