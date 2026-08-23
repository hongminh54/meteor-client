// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.modern.template.cdn;

import java.util.Map;
import java.util.Iterator;
import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.io.FileInputStream;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import io.fairyproject.libs.gson.GsonBuilder;
import java.net.URL;
import io.fairyproject.log.Log;
import dev.imanity.antixray.bootstrap.bukkit.BukkitPlugin;
import java.io.File;

public class MojangIO
{
    public static File getCacheFolder() {
        final File file = new File(BukkitPlugin.INSTANCE.getDataFolder(), "minecraft");
        file.mkdirs();
        return file;
    }
    
    public static <T> T downloadJson(final Class<T> clazz, final String s) {
        Log.warn("> Fetching JSON from Mojang servers: " + s, new Object[0]);
        final InputStream openStream = new URL(s).openStream();
        try {
            return (T)new GsonBuilder().create().fromJson((Reader)new InputStreamReader(openStream, "UTF-8"), (Class)clazz);
        }
        finally {
            openStream.close();
        }
    }
    
    public static void downloadFile(final String str, final VersionManifest.VersionAssets.Download download, final File file) {
        long n = System.currentTimeMillis();
        int n2 = 0;
        boolean b = false;
        Log.warn("> Fetching " + str + " from Mojang servers: " + download.url, new Object[0]);
        try (final FileOutputStream fileOutputStream = new FileOutputStream(file);
             final InputStream openStream = new URL(download.url).openStream()) {
            int n3 = 0;
            long n4 = 0L;
            final byte[] array = new byte[4096];
            int read;
            while ((read = openStream.read(array)) != -1) {
                fileOutputStream.write(array, 0, read);
                n4 += read;
                if (download.size == 0L) {
                    continue;
                }
                final int i = (int)(100L * n4 / download.size);
                if (i >= n3 + 10) {
                    n3 = i;
                    n2 = 1;
                }
                if (n2 == 0 || System.currentTimeMillis() - n <= 1000L) {
                    continue;
                }
                n = System.currentTimeMillis();
                n2 = 0;
                b = true;
                Log.info("> Downloading " + str + ": " + i + ".", new Object[0]);
            }
        }
        if (n2 != 0 && b) {
            Log.info("> Downloading " + str + ": 100.", new Object[0]);
        }
        if (verifyFile(str, download, file)) {
            Log.warn("> " + str + " verified, download completed!", new Object[0]);
        }
        else {
            Log.warn("> " + str + " download completed!", new Object[0]);
        }
    }
    
    private static boolean verifyFile(final String str, final VersionManifest.VersionAssets.Download download, final File file) {
        if (download.sha1 == null) {
            Log.warn("> Download metadata has no checksum, no file verification will be performed", new Object[0]);
            return false;
        }
        BigInteger x;
        try {
            x = new BigInteger(download.sha1, 16);
        }
        catch (final NumberFormatException ex) {
            Log.warn("> Download metadata has corrupted checksum, no file verification will be performed", new Object[0]);
            return false;
        }
        MessageDigest instance;
        try {
            instance = MessageDigest.getInstance("SHA-1");
        }
        catch (final NoSuchAlgorithmException ex2) {
            Log.warn("> SHA-1 not supported by the JVM, no file verification will be performed", new Object[0]);
            return false;
        }
        Log.warn("> Verifying " + str + "...", new Object[0]);
        try (final FileInputStream fileInputStream = new FileInputStream(file)) {
            final byte[] array = new byte[4096];
            int read;
            while ((read = fileInputStream.read(array)) != -1) {
                instance.update(array, 0, read);
            }
        }
        if (new BigInteger(1, instance.digest()).equals(x)) {
            return true;
        }
        throw new IOException("Checksum of downloaded file does not match");
    }
    
    public static class VersionManifest
    {
        public static final String URL = "https://launchermeta.mojang.com/mc/game/version_manifest.json";
        public List<Version> versions;
        
        public VersionManifest() {
            this.versions = Collections.emptyList();
        }
        
        public Version findVersion(final String anObject) {
            for (final Version version : this.versions) {
                if (version.id != null && version.id.equals(anObject)) {
                    return version;
                }
            }
            return null;
        }
        
        public static class Version
        {
            public String id;
            public String url;
        }
        
        public static class VersionAssets
        {
            public Map<String, Download> downloads;
            
            public VersionAssets() {
                this.downloads = Collections.emptyMap();
            }
            
            public static class Download
            {
                public String sha1;
                public long size;
                public String url;
            }
        }
    }
}
