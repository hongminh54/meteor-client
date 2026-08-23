// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.config;

import java.util.Iterator;
import io.fairyproject.log.Log;
import io.fairyproject.container.PostInitialize;
import io.fairyproject.container.PreInitialize;
import io.fairyproject.container.collection.ContainerObjCollector;
import java.util.LinkedHashSet;
import kotlin.jvm.internal.Intrinsics;
import dev.imanity.antixray.api.config.AbstractConfigService;
import java.util.Set;
import dev.imanity.antixray.api.config.migration.MigrationRegistry;
import dev.imanity.antixray.api.tick.InitialTickProcessor;
import io.fairyproject.container.ContainerContext;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import io.fairyproject.container.InjectableComponent;

@InjectableComponent
@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\r\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0007¢\u0006\u0004\b\u000f\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\fH\u0007¢\u0006\u0004\b\u0010\u0010\u000eJ\u000f\u0010\u0011\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0011\u0010\u000eR\u0014\u0010\u0012\u001a\u00020\u00048\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00028\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00068\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\b8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001c\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001b0\u001a8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d" }, d2 = { "Ldev/imanity/antixray/config/AntiXrayConfigRegistry;", "", "Ldev/imanity/antixray/config/GlobalAntiXrayConfig;", "p0", "Lio/fairyproject/container/ContainerContext;", "p1", "Ldev/imanity/antixray/api/tick/InitialTickProcessor;", "p2", "Ldev/imanity/antixray/api/config/migration/MigrationRegistry;", "p3", "<init>", "(Ldev/imanity/antixray/config/GlobalAntiXrayConfig;Lio/fairyproject/container/ContainerContext;Ldev/imanity/antixray/api/tick/InitialTickProcessor;Ldev/imanity/antixray/api/config/migration/MigrationRegistry;)V", "", "load", "()V", "onPostInitialize", "onPreInitialize", "tryToMigrateConfig", "containerContext", "Lio/fairyproject/container/ContainerContext;", "globalAntiXrayConfig", "Ldev/imanity/antixray/config/GlobalAntiXrayConfig;", "initialTickProcessor", "Ldev/imanity/antixray/api/tick/InitialTickProcessor;", "migrationRegistry", "Ldev/imanity/antixray/api/config/migration/MigrationRegistry;", "", "Ldev/imanity/antixray/api/config/AbstractConfigService;", "services", "Ljava/util/Set;" })
@SourceDebugExtension({ "SMAP\nAntiXrayConfigRegistry.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AntiXrayConfigRegistry.kt\ndev/imanity/antixray/config/AntiXrayConfigRegistry\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,67:1\n1855#2,2:68\n1855#2,2:70\n*S KotlinDebug\n*F\n+ 1 AntiXrayConfigRegistry.kt\ndev/imanity/antixray/config/AntiXrayConfigRegistry\n*L\n64#1:68,2\n38#1:70,2\n*E\n" })
public final class AntiXrayConfigRegistry
{
    @NotNull
    private final GlobalAntiXrayConfig globalAntiXrayConfig;
    @NotNull
    private final ContainerContext containerContext;
    @NotNull
    private final InitialTickProcessor initialTickProcessor;
    @NotNull
    private final MigrationRegistry migrationRegistry;
    @NotNull
    private final Set<AbstractConfigService<?>> services;
    
    public AntiXrayConfigRegistry(@NotNull final GlobalAntiXrayConfig globalAntiXrayConfig, @NotNull final ContainerContext containerContext, @NotNull final InitialTickProcessor initialTickProcessor, @NotNull final MigrationRegistry migrationRegistry) {
        Intrinsics.checkNotNullParameter((Object)globalAntiXrayConfig, "");
        Intrinsics.checkNotNullParameter((Object)containerContext, "");
        Intrinsics.checkNotNullParameter((Object)initialTickProcessor, "");
        Intrinsics.checkNotNullParameter((Object)migrationRegistry, "");
        this.globalAntiXrayConfig = globalAntiXrayConfig;
        this.containerContext = containerContext;
        this.initialTickProcessor = initialTickProcessor;
        this.migrationRegistry = migrationRegistry;
        this.services = new LinkedHashSet<AbstractConfigService<?>>();
    }
    
    @PreInitialize
    public final void onPreInitialize() {
        this.containerContext.objectCollectorRegistry().add(ContainerObjCollector.create().withFilter(ContainerObjCollector.inherits((Class)AbstractConfigService.class)).withAddHandler(ContainerObjCollector.warpInstance((Class)AbstractConfigService.class, AntiXrayConfigRegistry::onPreInitialize$lambda$0)).withRemoveHandler(ContainerObjCollector.warpInstance((Class)AbstractConfigService.class, AntiXrayConfigRegistry::onPreInitialize$lambda$1)));
    }
    
    @PostInitialize
    public final void onPostInitialize() {
        this.initialTickProcessor.addTask(0, AntiXrayConfigRegistry::onPostInitialize$lambda$3);
    }
    
    private final void tryToMigrateConfig() {
        if (this.globalAntiXrayConfig.isNewFile()) {
            this.globalAntiXrayConfig.setConfigVersion(1);
            this.globalAntiXrayConfig.save();
            return;
        }
        try {
            final int configVersion = this.globalAntiXrayConfig.getConfigVersion();
            final int migrate = this.migrationRegistry.migrate(configVersion);
            if (migrate != configVersion) {
                this.globalAntiXrayConfig.setConfigVersion(migrate);
                this.globalAntiXrayConfig.save();
                Log.info("Migrated config version from v" + configVersion + " to v" + migrate + '.', new Object[0]);
            }
        }
        catch (final Throwable t) {
            t.printStackTrace();
        }
    }
    
    public final void load() {
        final Iterator iterator = this.services.iterator();
        while (iterator.hasNext()) {
            ((AbstractConfigService)iterator.next()).reload();
        }
    }
    
    private static final void onPreInitialize$lambda$0(final AntiXrayConfigRegistry antiXrayConfigRegistry, final AbstractConfigService abstractConfigService) {
        Intrinsics.checkNotNullParameter((Object)antiXrayConfigRegistry, "");
        final Set<AbstractConfigService<?>> services = antiXrayConfigRegistry.services;
        Intrinsics.checkNotNull((Object)abstractConfigService);
        services.add(abstractConfigService);
    }
    
    private static final void onPreInitialize$lambda$1(final AntiXrayConfigRegistry antiXrayConfigRegistry, final AbstractConfigService abstractConfigService) {
        Intrinsics.checkNotNullParameter((Object)antiXrayConfigRegistry, "");
        antiXrayConfigRegistry.services.remove(abstractConfigService);
    }
    
    private static final void onPostInitialize$lambda$3(final AntiXrayConfigRegistry antiXrayConfigRegistry) {
        Intrinsics.checkNotNullParameter((Object)antiXrayConfigRegistry, "");
        antiXrayConfigRegistry.tryToMigrateConfig();
        final Iterator iterator = antiXrayConfigRegistry.services.iterator();
        while (iterator.hasNext()) {
            ((AbstractConfigService)iterator.next()).load();
        }
    }
}
