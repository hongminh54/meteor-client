// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.config.migration;

import java.util.Iterator;
import java.nio.file.DirectoryStream;
import kotlin.jvm.functions.Function1;
import java.util.Comparator;
import java.nio.file.FileVisitOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.OpenOption;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import java.nio.file.CopyOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import dev.imanity.antixray.api.config.AbstractConfigService;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\r\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\r\u0010\fJ\u001f\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000e\u0010\fJ\u001f\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000f\u0010\fR\u0018\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u00028\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0015\u001a\u00020\u00128WX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014" }, d2 = { "Ldev/imanity/antixray/api/config/migration/V0Migration;", "Ldev/imanity/antixray/api/config/migration/Migration;", "Ldev/imanity/antixray/api/config/AbstractConfigService;", "p0", "<init>", "(Ldev/imanity/antixray/api/config/AbstractConfigService;)V", "", "migrate", "()V", "Ljava/nio/file/Path;", "p1", "migrateCustom", "(Ljava/nio/file/Path;Ljava/nio/file/Path;)V", "migrateEnd", "migrateNether", "migrateOverworld", "configService", "Ldev/imanity/antixray/api/config/AbstractConfigService;", "", "getVersion", "()I", "version" })
@SourceDebugExtension({ "SMAP\nV0Migration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 V0Migration.kt\ndev/imanity/antixray/api/config/migration/V0Migration\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,145:1\n1855#2,2:146\n*S KotlinDebug\n*F\n+ 1 V0Migration.kt\ndev/imanity/antixray/api/config/migration/V0Migration\n*L\n32#1:146,2\n*E\n" })
public final class V0Migration implements Migration
{
    @NotNull
    private final AbstractConfigService<?> configService;
    
    public V0Migration(@NotNull final AbstractConfigService<?> configService) {
        Intrinsics.checkNotNullParameter((Object)configService, "");
        this.configService = configService;
    }
    
    @JvmName(name = "getVersion")
    @Override
    public int getVersion() {
        return 0;
    }
    
    @Override
    public void migrate() {
        final Path directory = this.configService.getDirectory();
        if (!Files.exists(directory, new LinkOption[0])) {
            return;
        }
        final Path configDirectory = this.configService.getConfigDirectory();
        if (!Files.exists(configDirectory, new LinkOption[0])) {
            Files.createDirectories(configDirectory, (FileAttribute<?>[])new FileAttribute[0]);
        }
        Intrinsics.checkNotNull((Object)directory);
        final Path path = directory;
        Intrinsics.checkNotNull((Object)configDirectory);
        this.migrateOverworld(path, configDirectory);
        this.migrateNether(directory, configDirectory);
        this.migrateEnd(directory, configDirectory);
        this.migrateCustom(directory, configDirectory);
    }
    
    private final void migrateCustom(final Path path, final Path path2) {
        final Path resolve = path.resolve("custom");
        if (Files.exists(resolve, new LinkOption[0])) {
            final DirectoryStream<Path> directoryStream = Files.newDirectoryStream(resolve);
            Intrinsics.checkNotNullExpressionValue((Object)directoryStream, "");
            for (final Path source : directoryStream) {
                final Path resolve2 = path2.resolve(source.getFileName().toString());
                Files.move(source, resolve2, new CopyOption[0]);
                final Path path3 = resolve2;
                final byte[] bytes = StringsKt.trimIndent("\n                            # The world filters to be used by the anti-xray.\n                            # When any of the world filters match, the configuration will be used.\n                            # Check documentation for syntaxes: https://docs.imanity.dev/antixray/configurations#world-filter\n                            worldFilters:\n                            - " + StringsKt.replace$default(source.getFileName().toString(), ".yml", "", false, 4, (Object)null) + "\n                            # The type of the config.\n                            # Config system will first search for the config with CUSTOM type.\n                            # If not found, it will search for the config with DEFAULT type.\n                            type: CUSTOM \n                        ").getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue((Object)bytes, "");
                Files.write(path3, bytes, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            }
            Files.walk(resolve, new FileVisitOption[0]).sorted(Comparator.reverseOrder()).forEach(V0Migration::migrateCustom$lambda$1);
        }
    }
    
    private final void migrateEnd(final Path path, final Path path2) {
        final Path resolve = path.resolve("default-end.yml");
        if (Files.exists(resolve, new LinkOption[0])) {
            final Path resolve2 = path2.resolve("default-the-end.yml");
            Files.move(resolve, resolve2, new CopyOption[0]);
            final Path path3 = resolve2;
            final byte[] bytes = "# The world filters to be used by the anti-xray.\n# When any of the world filters match, the configuration will be used.\n# Check documentation for syntaxes: https://docs.imanity.dev/antixray/configurations#world-filter\n worldFilters:\n - environment:the_end\n # The type of the config.\n # Config system will first search for the config with CUSTOM type.\n # If not found, it will search for the config with DEFAULT type.\n type: DEFAULT ".getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue((Object)bytes, "");
            Files.write(path3, bytes, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        }
    }
    
    private final void migrateNether(final Path path, final Path path2) {
        final Path resolve = path.resolve("default-nether.yml");
        if (Files.exists(resolve, new LinkOption[0])) {
            final Path resolve2 = path2.resolve("default-nether.yml");
            Files.move(resolve, resolve2, new CopyOption[0]);
            final Path path3 = resolve2;
            final byte[] bytes = "# The world filters to be used by the anti-xray.\n# When any of the world filters match, the configuration will be used.\n# Check documentation for syntaxes: https://docs.imanity.dev/antixray/configurations#world-filter\nworldFilters:\n- environment:nether\n# The type of the config.\n# Config system will first search for the config with CUSTOM type.\n# If not found, it will search for the config with DEFAULT type.\ntype: DEFAULT ".getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue((Object)bytes, "");
            Files.write(path3, bytes, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        }
    }
    
    private final void migrateOverworld(final Path path, final Path path2) {
        final Path resolve = path.resolve("default-overworld.yml");
        if (Files.exists(resolve, new LinkOption[0])) {
            final Path resolve2 = path2.resolve("default-overworld.yml");
            Files.move(resolve, resolve2, new CopyOption[0]);
            final Path path3 = resolve2;
            final byte[] bytes = "# The world filters to be used by the anti-xray.\n# When any of the world filters match, the configuration will be used.\n# Check documentation for syntaxes: https://docs.imanity.dev/antixray/configurations#world-filter\nworldFilters:\n- environment:normal\n# The type of the config.\n# Config system will first search for the config with CUSTOM type.\n# If not found, it will search for the config with DEFAULT type.\ntype: DEFAULT ".getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue((Object)bytes, "");
            Files.write(path3, bytes, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        }
    }
    
    private static final void migrateCustom$lambda$1(final Function1 function1, final Object o) {
        Intrinsics.checkNotNullParameter((Object)function1, "");
        function1.invoke(o);
    }
}
