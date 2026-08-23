// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.modern.template;

import java.util.Iterator;
import java.util.Collection;
import java.util.Collections;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.SourceDeclaration;
import org.bukkit.Bukkit;
import io.fairyproject.bukkit.version.BukkitVersionDecoder;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import dev.imanity.generated.Common;
import java.util.zip.ZipEntry;
import java.net.URL;
import java.util.function.Predicate;
import java.util.Objects;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import io.fairyproject.log.Log;
import java.io.File;
import java.net.URLClassLoader;
import java.util.HashMap;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ClassDeclaration;
import java.util.List;
import java.util.Map;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.ClassDeclarationResolver;

public class TemplateResolver implements ClassDeclarationResolver
{
    public static final TemplateResolver INSTANCE;
    private final Map<Class<?>, List<ClassDeclaration>> classes;
    private final Map<String, String> variables;
    
    public TemplateResolver() {
        this.classes = new HashMap<Class<?>, List<ClassDeclaration>>();
        this.variables = new HashMap<String, String>();
    }
    
    public static void initializeTemplateClasses() {
        final URLClassLoader urlClassLoader = (URLClassLoader)TemplateResolver.class.getClassLoader();
        File file = null;
        final URL[] urLs = urlClassLoader.getURLs();
        for (int length = urLs.length, i = 0; i < length; ++i) {
            file = new File(urLs[i].getFile());
            if (!file.exists()) {
                file = null;
            }
        }
        if (file == null) {
            Log.warn("Failed to figure out the jar file of RaytraceAntiXray. No template classes pre-loaded.", new Object[0]);
            return;
        }
        final ArrayList list = new ArrayList();
        try (final ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file))) {
            for (ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
                if (!zipEntry.isDirectory()) {
                    String s = zipEntry.getName();
                    if (s.startsWith("/")) {
                        s = s.substring(1);
                    }
                    if (s.startsWith("dev/imanity/generated")) {
                        if (s.endsWith(".class")) {
                            if (!s.equals("com/bergerkiller/generated/net/minecraft/network/protocol/game/PacketPlayOutCustomPayloadHandle.class")) {
                                list.add(s.substring(0, s.length() - 6).replace('/', '.'));
                            }
                        }
                    }
                }
            }
        }
        catch (final IOException ex) {
            Log.warn("Failed to pre-load template classes: listing failed", (Throwable)ex, new Object[0]);
            return;
        }
        list.parallelStream().map(s2 -> {
            try {
                Class.forName(s2, (boolean)(1 != 0), TemplateResolver.class.getClassLoader());
                final Class clazz;
                if (Template.Handle.class.isAssignableFrom(clazz)) {
                    return (Object)clazz.getDeclaredField("T").get(null);
                }
            }
            catch (final Throwable t2) {
                Log.warn("Failed to load class " + s2, t2, new Object[0]);
                return null;
            }
            return null;
        }).filter(Objects::nonNull).forEach(class1 -> {
            try {
                class1.forceInitialization();
                Log.info("Force-initialized " + class1.getHandleType(), new Object[0]);
            }
            catch (final Throwable t4) {
                Log.warn("Failed to initialize " + class1.getHandleType(), t4, new Object[0]);
            }
        });
    }
    
    public static void init() {
        Common.INSTANCE = TemplateResolver.INSTANCE;
        initializeTemplateClasses();
        new MojangMappingResolver().init();
        TemplateResolver.INSTANCE.load();
        Resolver.registerClassDeclarationResolver(TemplateResolver.INSTANCE);
    }
    
    private void load() {
        final String s = "dev/imanity/templates/init.txt";
        this.variables.put("version", BukkitVersionDecoder.create().decode(Bukkit.getServer()).getFormatted());
        final ClassDeclaration[] classes = SourceDeclaration.parseFromResources(TemplateResolver.class.getClassLoader(), s, this.variables).classes;
        for (int length = classes.length, i = 0; i < length; ++i) {
            this.register(classes[i]);
        }
    }
    
    private void register(final ClassDeclaration classDeclaration) {
        final List c = this.classes.get(classDeclaration.type.type);
        if (c == null) {
            this.classes.put(classDeclaration.type.type, Collections.singletonList(classDeclaration));
        }
        else {
            final ArrayList list = new ArrayList<ClassDeclaration>(c);
            list.add(classDeclaration);
            list.trimToSize();
            this.classes.put(classDeclaration.type.type, (ArrayList<ClassDeclaration>)list);
        }
        final ClassDeclaration[] subclasses = classDeclaration.subclasses;
        for (int length = subclasses.length, i = 0; i < length; ++i) {
            this.register(subclasses[i]);
        }
    }
    
    @Override
    public ClassDeclaration resolveClassDeclaration(final String s, final Class<?> clazz) {
        final List list = this.classes.get(clazz);
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (list.size() == 1) {
            return (ClassDeclaration)list.get(0);
        }
        for (final ClassDeclaration classDeclaration : list) {
            if (s.endsWith(classDeclaration.type.typeName)) {
                return classDeclaration;
            }
        }
        return (ClassDeclaration)list.get(0);
    }
    
    @Override
    public void resolveClassVariables(final String s, final Class<?> clazz, final Map<String, String> map) {
        map.putAll(this.variables);
    }
    
    static {
        INSTANCE = new TemplateResolver();
    }
}
