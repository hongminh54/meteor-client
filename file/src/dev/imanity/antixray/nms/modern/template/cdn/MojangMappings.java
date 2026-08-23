// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.modern.template.cdn;

import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.stream.Stream;
import java.util.regex.Pattern;
import java.util.concurrent.ConcurrentHashMap;
import com.google.common.collect.HashBiMap;
import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.BiMap;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import io.fairyproject.log.Log;
import java.io.File;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.function.Function;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MojangMappings
{
    private final Map<String, ClassMappings> classesByName;
    
    public MojangMappings() {
        this.classesByName = new HashMap<String, ClassMappings>();
    }
    
    public Collection<ClassMappings> classes() {
        return this.classesByName.values();
    }
    
    public ClassMappings forClassIfExists(final String s) {
        return this.classesByName.get(s);
    }
    
    public MojangMappings translateClassNames(final Function<String, String> function) {
        final MojangMappings mojangMappings = new MojangMappings();
        final ClassSignatureCache classSignatureCache = new ClassSignatureCache(function);
        for (final ClassMappings classMappings : this.classesByName.values()) {
            final ClassMappings classMappings2 = new ClassMappings(classMappings, function);
            mojangMappings.classesByName.put(classMappings2.name, classMappings2);
            classSignatureCache.put(classMappings.name, classMappings2);
        }
        for (final ClassMappings classMappings3 : mojangMappings.classesByName.values()) {
            final ListIterator<MethodSignature> listIterator = classMappings3.methods.listIterator();
            while (listIterator.hasNext()) {
                listIterator.set(listIterator.next().translate(classMappings3, classSignatureCache));
            }
        }
        return mojangMappings;
    }
    
    public static MojangMappings fromCacheOrDownload(final String s) {
        final File file = new File(MojangIO.getCacheFolder(), getMappingFile(s));
        if (file.exists()) {
            try {
                return readMappings(file);
            }
            catch (final Throwable t) {
                Log.info("Failed to parse cached server mappings, redownloading", t, new Object[0]);
            }
        }
        return download(s);
    }
    
    public static MojangMappings download(final String s) {
        final File cacheFolder = MojangIO.getCacheFolder();
        final File file = new File(cacheFolder, getMappingFile(s) + ".tmp");
        final File file2 = new File(cacheFolder, getMappingFile(s));
        try {
            return downloadMain(s, file, file2);
        }
        catch (final DownloadException ex) {
            Log.warn("Failed to download the server mappings. You can try manually downloading the file instead.", new Object[0]);
            Log.warn("Install the server mappings in the following location:", new Object[0]);
            Log.warn("> " + file2.getAbsolutePath(), new Object[0]);
            throw ex;
        }
    }
    
    private static MojangMappings downloadMain(final String s, final File obj, final File file) {
        MojangIO.VersionManifest versionManifest;
        try {
            versionManifest = MojangIO.downloadJson(MojangIO.VersionManifest.class, "https://launchermeta.mojang.com/mc/game/version_manifest.json");
        }
        catch (final IOException ex) {
            throw new DownloadException("Failed to download the game version manifest from Mojangs servers", ex);
        }
        final MojangIO.VersionManifest.Version version = versionManifest.findVersion(s);
        if (version == null) {
            throw new DownloadException("This Minecraft version is not available");
        }
        MojangIO.VersionManifest.VersionAssets versionAssets;
        try {
            versionAssets = MojangIO.downloadJson(MojangIO.VersionManifest.VersionAssets.class, version.url);
        }
        catch (final IOException ex2) {
            throw new DownloadException("Failed to download game version asset information", ex2);
        }
        final MojangIO.VersionManifest.VersionAssets.Download download = versionAssets.downloads.get("server_mappings");
        if (download == null) {
            throw new DownloadException("This Minecraft version has no downloadable server mappings");
        }
        try {
            MojangIO.downloadFile("Server Mappings", download, obj);
        }
        catch (final IOException ex3) {
            throw new DownloadException("Failed to download server mappings", ex3);
        }
        file.delete();
        if (!obj.renameTo(file)) {
            throw new DownloadException("Failed to move " + obj + " to " + file);
        }
        try {
            return readMappings(file);
        }
        catch (final Throwable t) {
            throw new DownloadException("Failed to parse server mappings", t);
        }
    }
    
    private static MojangMappings readMappings(final File file) {
        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            return new ProGuardParser().parse(bufferedReader);
        }
    }
    
    private static String getMappingFile(final String str) {
        return str + "_server_mappings.txt";
    }
    
    public static class ClassMappings extends ClassSignature
    {
        public final BiMap<String, String> fields_obfuscated_to_name;
        public final BiMap<String, String> fields_name_to_obfuscated;
        public final List<MethodSignature> methods;
        
        public ClassMappings(final ClassMappings classMappings, final Function<String, String> function) {
            super(function.apply(classMappings.name), classMappings.name_obfuscated);
            this.fields_obfuscated_to_name = classMappings.fields_obfuscated_to_name;
            this.fields_name_to_obfuscated = classMappings.fields_name_to_obfuscated;
            this.methods = new ArrayList<MethodSignature>(classMappings.methods);
        }
        
        public ClassMappings(final String s, final String s2) {
            super(s, s2);
            this.fields_obfuscated_to_name = (BiMap<String, String>)HashBiMap.create();
            this.fields_name_to_obfuscated = (BiMap<String, String>)this.fields_obfuscated_to_name.inverse();
            this.methods = new ArrayList<MethodSignature>();
        }
        
        public void addField(final String s, final String s2) {
            this.fields_obfuscated_to_name.put((Object)s, (Object)s2);
        }
        
        public void addMethod(final MethodSignature methodSignature) {
            this.methods.add(methodSignature);
        }
    }
    
    public static class ClassSignature
    {
        public final String name;
        public final String name_obfuscated;
        
        public ClassSignature(final String name, final String name_obfuscated) {
            this.name = name;
            this.name_obfuscated = name_obfuscated;
        }
        
        @Override
        public String toString() {
            if (this.name.equals(this.name_obfuscated)) {
                return this.name;
            }
            return this.name + ":" + this.name_obfuscated;
        }
    }
    
    public static class MethodSignature
    {
        public final ClassMappings declaring;
        public final String name;
        public final String name_obfuscated;
        public final ClassSignature returnType;
        public final List<ClassSignature> parameterTypes;
        
        public MethodSignature(final ClassMappings declaring, final String name, final String name_obfuscated, final ClassSignature returnType, final List<ClassSignature> parameterTypes) {
            this.declaring = declaring;
            this.name = name;
            this.name_obfuscated = name_obfuscated;
            this.returnType = returnType;
            this.parameterTypes = parameterTypes;
        }
        
        public MethodSignature translate(final ClassMappings classMappings, final ClassSignatureCache classSignatureCache) {
            final ArrayList list = new ArrayList(this.parameterTypes.size());
            final Iterator<ClassSignature> iterator = this.parameterTypes.iterator();
            while (iterator.hasNext()) {
                list.add(classSignatureCache.get(iterator.next().name));
            }
            return new MethodSignature(classMappings, this.name, this.name_obfuscated, classSignatureCache.get(this.returnType.name), list);
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("<").append(this.declaring.toString()).append("> ");
            sb.append(this.returnType).append(' ');
            sb.append(this.name).append(":").append(this.name_obfuscated);
            sb.append(" (");
            int n = 1;
            for (final ClassSignature classSignature : this.parameterTypes) {
                if (n != 0) {
                    n = 0;
                }
                else {
                    sb.append(", ");
                }
                sb.append(classSignature.toString());
            }
            sb.append(")");
            return sb.toString();
        }
    }
    
    public static class DownloadException extends RuntimeException
    {
        private static final long serialVersionUID = 6481322561950832096L;
        
        public DownloadException(final String message) {
            super(message);
        }
        
        public DownloadException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }
    
    private static class ClassSignatureCache
    {
        private final Map<String, ClassSignature> cache;
        private final Function<String, String> nameTranslator;
        
        public ClassSignatureCache() {
            this.cache = new ConcurrentHashMap<String, ClassSignature>();
            this.nameTranslator = Function.identity();
        }
        
        public ClassSignatureCache(final Function<String, String> nameTranslator) {
            this.cache = new ConcurrentHashMap<String, ClassSignature>();
            this.nameTranslator = nameTranslator;
        }
        
        public void put(final String s, final ClassSignature classSignature) {
            this.cache.put(s, classSignature);
        }
        
        public ClassSignature get(final String s) {
            ClassSignature compute = this.cache.get(s);
            if (compute == null) {
                compute = this.compute(s);
                this.cache.put(s, compute);
            }
            return compute;
        }
        
        private ClassSignature compute(final String s) {
            boolean b = false;
            String substring;
            String string;
            for (substring = s, string = ""; substring.endsWith("[]"); substring = substring.substring(0, substring.length() - 2), string += "[]", b = true) {}
            if (b) {
                final ClassSignature value = this.get(substring);
                return new ClassSignature(value.name + string, value.name_obfuscated + string);
            }
            final String s2 = this.nameTranslator.apply(substring);
            return new ClassSignature(s2, s2);
        }
    }
    
    private static class ProGuardParser
    {
        private static final Pattern FIELD_NAME_PATTERN;
        private static final Pattern METHOD_NAME_PATTERN;
        private final MojangMappings result;
        private final ClassSignatureCache classSigCache;
        
        private ProGuardParser() {
            this.result = new MojangMappings();
            this.classSigCache = new ClassSignatureCache();
        }
        
        public MojangMappings parse(final BufferedReader bufferedReader) {
            final AsyncLineReader asyncLineReader = new AsyncLineReader(bufferedReader);
            asyncLineReader.start();
            ClassMappings classMappings = null;
            final ArrayList list = new ArrayList();
            String line;
            while ((line = asyncLineReader.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }
                if (line.endsWith(":") && !line.startsWith(" ")) {
                    final int index = line.indexOf(" -> ");
                    if (index <= 0 || index >= line.length() - 4) {
                        classMappings = null;
                    }
                    else {
                        classMappings = new ClassMappings(line.substring(0, index), line.substring(index + 4, line.length() - 1));
                        this.classSigCache.put(classMappings.name, classMappings);
                        if (isValidClass(classMappings.name)) {
                            this.result.classesByName.put(classMappings.name, classMappings);
                        }
                    }
                }
                if (classMappings == null) {
                    continue;
                }
                if (!line.startsWith("    ")) {
                    continue;
                }
                final Matcher matcher = ProGuardParser.FIELD_NAME_PATTERN.matcher(line);
                if (matcher.matches()) {
                    this.processField(classMappings, matcher);
                }
                else {
                    final Matcher matcher2 = ProGuardParser.METHOD_NAME_PATTERN.matcher(line);
                    if (!matcher2.matches()) {
                        continue;
                    }
                    list.add(new PendingMethod(classMappings, matcher2));
                }
            }
            ((Stream)list.stream().parallel()).map(pendingMethod -> pendingMethod.makeSignature(this.classSigCache)).forEachOrdered(methodSignature -> methodSignature.declaring.addMethod(methodSignature));
            return this.result;
        }
        
        private static boolean isValidClass(final String s) {
            return !s.endsWith(".package-info");
        }
        
        private void processField(final ClassMappings classMappings, final Matcher matcher) {
            final String group = matcher.group(2);
            final String group2 = matcher.group(1);
            if (group2.startsWith("this$")) {
                return;
            }
            classMappings.addField(group, group2);
        }
        
        static {
            FIELD_NAME_PATTERN = Pattern.compile("\\s+[\\[\\]<>\\w\\._\\-$]+\\s([\\w_\\-$]+)\\s->\\s([\\w_\\-$]+)");
            METHOD_NAME_PATTERN = Pattern.compile("\\s+(\\d+:\\d+:)?([\\[\\]<>\\w\\._\\-$]+)\\s([\\w_\\-$]+)\\(([\\[\\]<>\\w\\._\\-,$]*)\\)\\s->\\s([\\w_\\-$]+)");
        }
        
        private static final class PendingMethod
        {
            public final ClassMappings classMappings;
            public final String obfuscatedName;
            public final String mojangName;
            public final String returnTypeName;
            public final String[] paramTypeNames;
            
            public PendingMethod(final ClassMappings classMappings, final Matcher matcher) {
                this.classMappings = classMappings;
                this.obfuscatedName = matcher.group(5);
                this.mojangName = matcher.group(3);
                this.returnTypeName = matcher.group(2);
                final String group = matcher.group(4);
                if (group.isEmpty()) {
                    this.paramTypeNames = new String[0];
                }
                else {
                    this.paramTypeNames = group.split(",", -1);
                }
            }
            
            public MethodSignature makeSignature(final ClassSignatureCache classSignatureCache) {
                final ClassSignature value = classSignatureCache.get(this.returnTypeName);
                final ArrayList list = new ArrayList(this.paramTypeNames.length);
                final String[] paramTypeNames = this.paramTypeNames;
                for (int length = paramTypeNames.length, i = 0; i < length; ++i) {
                    list.add(classSignatureCache.get(paramTypeNames[i]));
                }
                return new MethodSignature(this.classMappings, this.mojangName, this.obfuscatedName, value, list);
            }
        }
        
        private static final class AsyncLineReader
        {
            private static final String STOP_SIGNAL = "MAPPINGS_DONE_READING";
            private final BufferedReader reader;
            private CompletableFuture<Void> readerFuture;
            private final BlockingQueue<String> pending;
            
            public AsyncLineReader(final BufferedReader reader) {
                this.pending = new LinkedBlockingDeque<String>();
                this.reader = reader;
            }
            
            public String readLine() {
                try {
                    final String s = this.pending.poll(1000L, TimeUnit.MILLISECONDS);
                    if (s == "MAPPINGS_DONE_READING") {
                        try {
                            this.readerFuture.join();
                        }
                        catch (final CompletionException ex) {
                            if (ex.getCause() instanceof IOException) {
                                throw (IOException)ex.getCause();
                            }
                            throw ex;
                        }
                        return null;
                    }
                    return s;
                }
                catch (final InterruptedException ex2) {
                    throw new IllegalStateException("Read interrupted");
                }
            }
            
            public void start() {
                this.readerFuture = CompletableFuture.runAsync(() -> {
                    final BlockingQueue<String> pending = this.pending;
                    try {
                        while (true) {
                            this.reader.readLine();
                            final Object o;
                            if (o != null) {
                                final String s;
                                pending.offer(s, 1000L, TimeUnit.MILLISECONDS);
                            }
                            else {
                                break;
                            }
                        }
                    }
                    catch (final Throwable cause) {
                        pending.offer("MAPPINGS_DONE_READING");
                        throw new CompletionException(cause);
                    }
                    finally {
                        pending.offer("MAPPINGS_DONE_READING");
                    }
                });
            }
        }
    }
}
