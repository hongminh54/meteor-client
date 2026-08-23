// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.modern.template;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import io.fairyproject.mc.version.MCVersion;
import org.bukkit.Bukkit;
import io.fairyproject.bukkit.version.BukkitVersionDecoder;
import java.util.Collections;
import java.util.Map;
import dev.imanity.antixray.nms.modern.template.cdn.SpigotMappings;
import dev.imanity.antixray.nms.modern.template.cdn.MojangSpigotRemapper;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.MethodNameResolver;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.MethodAliasResolver;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.FieldNameResolver;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.FieldAliasResolver;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.ClassPathResolver;

public class MojangMappingResolver implements ClassPathResolver, FieldAliasResolver, FieldNameResolver, MethodAliasResolver, MethodNameResolver
{
    private static final String PACKAGE_CB_ROOT = "org.bukkit.craftbukkit";
    private static final String PACKAGE_NMS_ROOT = "net.minecraft.server";
    private static final String CB_ROOT = "org.bukkit.craftbukkit.";
    private static final String NM_ROOT = "net.minecraft.";
    private boolean IS_MOJANGMAP_SERVER;
    public String packageVersion;
    public String nmsRootVersioned;
    public String craftBukkitRootVersioned;
    public String craftBukkitRootLibs;
    private boolean hasMojangFieldMappings;
    private boolean hasMojangMethodMappings;
    private MojangSpigotRemapper mojangToSpigotRemapper;
    private SpigotMappings.ClassMappings mojangToSpigotClassRemapper;
    private boolean isInitializingMojangSpigotRemapper;
    private Map<String, String> remappings;
    
    public MojangMappingResolver() {
        this.IS_MOJANGMAP_SERVER = false;
        this.hasMojangFieldMappings = false;
        this.hasMojangMethodMappings = false;
        this.mojangToSpigotClassRemapper = null;
        this.isInitializingMojangSpigotRemapper = false;
        this.remappings = Collections.emptyMap();
    }
    
    public void setRemappings(final Map<String, String> remappings) {
        this.remappings = remappings;
    }
    
    public void init() {
        final MCVersion decode = BukkitVersionDecoder.create().decode(Bukkit.getServer());
        final boolean higherOrEqual = decode.isHigherOrEqual(MCVersion.of(21, 4));
        if (higherOrEqual) {
            this.IS_MOJANGMAP_SERVER = true;
        }
        else {
            this.isInitializingMojangSpigotRemapper = true;
            try {
                Class<?> resolveFindClassEarly = null;
                Class<?> resolveFindClassEarly2 = null;
                try {
                    resolveFindClassEarly = this.resolveFindClassEarly("net.minecraft.server.level.ServerPlayer");
                }
                catch (final Throwable t) {}
                try {
                    resolveFindClassEarly2 = this.resolveFindClassEarly("net.minecraft.server.level.EntityPlayer");
                }
                catch (final Throwable t2) {}
                if (resolveFindClassEarly != null && resolveFindClassEarly2 == null) {
                    boolean b = false;
                    try {
                        MPLType.getDeclaredMethod(resolveFindClassEarly, "getCamera", (Class<?>[])new Class[0]);
                        b = true;
                    }
                    catch (final Throwable t3) {}
                    if (b) {
                        this.IS_MOJANGMAP_SERVER = true;
                    }
                }
            }
            finally {
                this.isInitializingMojangSpigotRemapper = false;
            }
        }
        new Remapping(this).init();
        if (higherOrEqual || this.IS_MOJANGMAP_SERVER) {
            this.mojangToSpigotRemapper = null;
            this.mojangToSpigotClassRemapper = null;
            this.hasMojangFieldMappings = false;
            this.hasMojangMethodMappings = false;
        }
        else {
            this.mojangToSpigotClassRemapper = SpigotMappings.fromCacheOrDownload(decode.getFormatted());
            this.hasMojangFieldMappings = decode.isHigherOrEqual(MCVersion.of(17));
            this.hasMojangMethodMappings = decode.isHigherOrEqual(MCVersion.of(18));
            try {
                this.isInitializingMojangSpigotRemapper = true;
                this.mojangToSpigotRemapper = MojangSpigotRemapper.load(decode.getFormatted(), this::resolveClassPathEarly);
            }
            finally {
                this.isInitializingMojangSpigotRemapper = false;
            }
        }
        this.craftBukkitRootVersioned = Bukkit.getServer().getClass().getPackage().getName();
        this.craftBukkitRootLibs = "org.bukkit.craftbukkit.libs";
        if (this.craftBukkitRootVersioned.startsWith("org.bukkit.craftbukkit.")) {
            this.packageVersion = this.craftBukkitRootVersioned.substring("org.bukkit.craftbukkit.".length());
            this.nmsRootVersioned = "net.minecraft.server." + this.packageVersion;
        }
        else {
            this.packageVersion = "";
            this.nmsRootVersioned = "net.minecraft.server";
        }
        Resolver.registerClassResolver(this);
        Resolver.registerFieldResolver(this);
        Resolver.registerMethodResolver(this);
        Resolver.registerFieldAliasResolver(this);
        Resolver.registerMethodAliasResolver(this);
    }
    
    private Class<?> resolveFindClassEarly(final String s) {
        return Resolver.getClassByExactName(this.resolveClassPathEarly(s));
    }
    
    protected String resolveClassPathEarly(final String s) {
        return this.resolveClassPath(s);
    }
    
    @Override
    public String resolveClassPath(String s) {
        if (this.isInitializingMojangSpigotRemapper && this.mojangToSpigotClassRemapper != null) {
            return this.mojangToSpigotClassRemapper.toSpigot(s);
        }
        s = this.remappings.getOrDefault(s, s);
        if (s.startsWith("org.bukkit.craftbukkit.") && !s.startsWith(this.craftBukkitRootVersioned) && !s.startsWith(this.craftBukkitRootLibs)) {
            s = this.craftBukkitRootVersioned + s.substring("org.bukkit.craftbukkit".length());
        }
        if (this.mojangToSpigotClassRemapper != null) {
            s = this.mojangToSpigotClassRemapper.toSpigot(s);
        }
        return s;
    }
    
    @Override
    public String resolveFieldAlias(final Field field, final String s) {
        if (!this.hasMojangFieldMappings) {
            return null;
        }
        return this.mojangToSpigotRemapper.remapFieldNameReverse(field.getDeclaringClass(), s, null);
    }
    
    @Override
    public String resolveFieldName(final Class<?> clazz, final String s) {
        if (!this.hasMojangFieldMappings) {
            return s;
        }
        return this.mojangToSpigotRemapper.remapFieldName(clazz, s, s);
    }
    
    @Override
    public String resolveMethodAlias(final Method method, final String s) {
        if (!this.hasMojangMethodMappings) {
            return null;
        }
        return this.mojangToSpigotRemapper.remapMethodNameReverse(method.getDeclaringClass(), s, method.getParameterTypes(), null);
    }
    
    @Override
    public String resolveMethodName(final Class<?> clazz, final String s, final Class<?>[] array) {
        if (!this.hasMojangMethodMappings) {
            return s;
        }
        return this.mojangToSpigotRemapper.remapMethodName(clazz, s, array, s);
    }
}
