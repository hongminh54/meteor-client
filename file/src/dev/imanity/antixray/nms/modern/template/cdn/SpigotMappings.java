// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.modern.template.cdn;

import com.google.common.collect.HashBiMap;
import io.fairyproject.log.Log;
import dev.imanity.antixray.nms.modern.template.TemplateResolver;
import java.util.regex.Matcher;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import com.google.common.collect.BiMap;
import java.util.Iterator;
import java.util.Collections;
import java.io.DataOutputStream;
import java.util.zip.DeflaterOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.io.DataInputStream;
import java.util.zip.InflaterInputStream;
import java.util.HashMap;
import java.io.InputStream;
import java.util.TreeMap;
import dev.imanity.antixray.libs.mountiplex.logic.TextValueSequence;
import java.util.SortedMap;

public class SpigotMappings
{
    public final SortedMap<String, ClassMappings> byVersion;
    
    public SpigotMappings() {
        this.byVersion = new TreeMap<String, ClassMappings>((s, anObject) -> {
            if (s.equals(anObject)) {
                return 0;
            }
            else if (TextValueSequence.evaluateText(s, ">", anObject)) {
                return 1;
            }
            else {
                return -1;
            }
        });
    }
    
    public ClassMappings get(final String s) {
        return this.byVersion.get(s);
    }
    
    public void read(final InputStream in) {
        final HashMap hashMap = new HashMap();
        try (final DataInputStream dataInputStream = new DataInputStream(new InflaterInputStream(in))) {
            int int1 = dataInputStream.readInt();
            if (int1 == 0) {
                dataInputStream.close();
                return;
            }
            String utf = dataInputStream.readUTF();
            while (int1-- > 0) {
                String s = utf;
                String utf2;
                while (!(utf2 = dataInputStream.readUTF()).isEmpty()) {
                    final int index = utf2.indexOf(61);
                    if (index == -1) {
                        s = utf2;
                        break;
                    }
                    if (index == utf2.length() - 1) {
                        hashMap.remove(utf2.substring(0, index));
                    }
                    else {
                        hashMap.put(utf2.substring(0, index), utf2.substring(index + 1));
                    }
                }
                this.byVersion.put(utf, new ClassMappings(hashMap));
                utf = s;
            }
        }
    }
    
    public void write(final OutputStream out) {
        try (final DataOutputStream dataOutputStream = new DataOutputStream(new DeflaterOutputStream(out))) {
            dataOutputStream.writeInt(this.byVersion.size());
            Map<Object, Object> emptyMap = Collections.emptyMap();
            for (final Map.Entry entry : this.byVersion.entrySet()) {
                dataOutputStream.writeUTF((String)entry.getKey());
                final BiMap access$000 = ((ClassMappings)entry.getValue()).spigotToMojang;
                for (final Map.Entry entry2 : emptyMap.entrySet()) {
                    if (!((Map)access$000).containsKey(entry2.getKey())) {
                        dataOutputStream.writeUTF((String)entry2.getKey() + "=");
                    }
                }
                for (final Map.Entry entry3 : ((Map<Object, String>)access$000).entrySet()) {
                    if (!emptyMap.getOrDefault(entry3.getKey(), "").equals(entry3.getValue())) {
                        dataOutputStream.writeUTF((String)entry3.getKey() + "=" + (String)entry3.getValue());
                    }
                }
                emptyMap = (Map<Object, Object>)access$000;
            }
            dataOutputStream.writeUTF("");
        }
    }
    
    public boolean assertMappings(final String... array) {
        boolean b = false;
        for (final String s : array) {
            if (!this.byVersion.containsKey(s)) {
                this.downloadMappings(null, s);
                b = true;
            }
        }
        return b;
    }
    
    public void downloadMappings(MojangMappings fromCacheOrDownload, final String s) {
        if (fromCacheOrDownload == null) {
            fromCacheOrDownload = MojangMappings.fromCacheOrDownload(s);
        }
        final SpigotVersionMeta spigotVersionMeta = MojangIO.downloadJson(SpigotVersionMeta.class, "https://hub.spigotmc.org/versions/" + s + ".json");
        if (spigotVersionMeta.refs == null || spigotVersionMeta.refs.BuildData == null) {
            throw new IOException("No BuildData ref in response to /versions");
        }
        final File tempFile = File.createTempFile("bukkit-" + s + "-cl", ".csrg");
        final MojangIO.VersionManifest.VersionAssets.Download download = new MojangIO.VersionManifest.VersionAssets.Download();
        download.sha1 = null;
        download.size = 0L;
        download.url = "https://hub.spigotmc.org/stash/projects/SPIGOT/repos/builddata/raw/mappings/bukkit-" + s + "-cl.csrg?at=" + spigotVersionMeta.refs.BuildData;
        MojangIO.downloadFile("Minecraft - Bukkit " + s + " class mappings", download, tempFile);
        final Pattern compile = Pattern.compile("([\\w\\._\\-/$]+)\\s+([\\w\\._\\-/$]+)");
        final HashMap hashMap = new HashMap();
        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(tempFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final Matcher matcher = compile.matcher(line);
                if (matcher.matches()) {
                    hashMap.put(matcher.group(1).replace('/', '.'), matcher.group(2).replace('/', '.'));
                }
            }
        }
        final HashMap<String, String> hashMap2 = new HashMap<String, String>();
        for (final MojangMappings.ClassMappings classMappings : fromCacheOrDownload.classes()) {
            String string = (String)hashMap.get(classMappings.name_obfuscated);
            if (string == null) {
                int n = classMappings.name_obfuscated.length();
                while ((n = classMappings.name_obfuscated.lastIndexOf(36, n - 1)) != -1) {
                    string = (String)hashMap.get(classMappings.name_obfuscated.substring(0, n));
                    if (string != null) {
                        string += classMappings.name_obfuscated.substring(n);
                        break;
                    }
                }
            }
            if (string != null) {
                hashMap2.put(string, classMappings.name);
            }
        }
        this.byVersion.put(s, new ClassMappings(hashMap2));
        tempFile.delete();
    }
    
    public static ClassMappings fromCacheOrDownload(final String s) {
        final SpigotMappings spigotMappings = new SpigotMappings();
        final String name = "/dev/imanity/mapping/class_mappings.dat";
        try (final InputStream resourceAsStream = TemplateResolver.class.getResourceAsStream(name)) {
            spigotMappings.read(resourceAsStream);
        }
        catch (final IOException ex) {
            Log.info("Failed to read class mappings (corrupted jar?)", (Throwable)ex, new Object[0]);
        }
        if (!spigotMappings.byVersion.containsKey(s)) {
            Log.warn("[Developer] Class mappings file has no mappings for this Minecraft version. Build problem?", new Object[0]);
            try {
                spigotMappings.downloadMappings(MojangMappings.fromCacheOrDownload(s), s);
            }
            catch (final IOException ex2) {
                throw new IllegalStateException("Failed to download Spigot-Mojang class name mappings");
            }
        }
        return spigotMappings.get(s);
    }
    
    private static class SpigotVersionMeta
    {
        public Refs refs;
        
        private static class Refs
        {
            public String BuildData;
        }
    }
    
    public static class ClassMappings
    {
        private final BiMap<String, String> mojangToSpigot;
        private final BiMap<String, String> spigotToMojang;
        
        public ClassMappings(final Map<String, String> map) {
            final HashBiMap create = HashBiMap.create(map.size());
            for (final Map.Entry entry : map.entrySet()) {
                create.forcePut((Object)entry.getKey(), (Object)entry.getValue());
            }
            this.spigotToMojang = (BiMap<String, String>)create;
            this.mojangToSpigot = (BiMap<String, String>)this.spigotToMojang.inverse();
        }
        
        public String toMojang(final String s) {
            return (String)this.spigotToMojang.getOrDefault((Object)s, (Object)s);
        }
        
        public String toSpigot(final String s) {
            return (String)this.mojangToSpigot.getOrDefault((Object)s, (Object)s);
        }
    }
}
