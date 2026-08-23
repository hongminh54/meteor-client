// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import io.fairyproject.library.LibraryHandler;
import io.fairyproject.library.relocate.Relocation;
import io.fairyproject.library.Library;
import io.fairyproject.log.Log;
import io.fairyproject.Fairy;

public class IIlIIIlIlIIlIIlllIIlIIlIllIIllIIIllIllIIlIllIlllIIIlllIlIIlIIIlIllIlIIIlIIIllIIIllIllIIIlIlIlIIIllIIIllIllIllIIlIllIIIllIIlIIIllIIIllIllIIIlIIlllIIlIIlllIllIllIIlIIlIIlIllIllIlllIIIllIIlIIIlllIllIIIlllIIlllIIIllIIIlllIIIllIll
{
    public void init() {
        final LibraryHandler libraryHandler = Fairy.getLibraryHandler();
        try {
            Class.forName("it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap");
            Class.forName("it.unimi.dsi.fastutil.ints.IntOpenHashSet");
        }
        catch (final ClassNotFoundException ex) {
            Log.info("Installing fastutil library, this may take a few minutes...", new Object[0]);
            libraryHandler.loadLibrary(Library.builder().groupId("it.unimi.dsi").artifactId("fastutil").version("8.5.12").checksum("tVQ+4I0GLVUc8KXJvA+3BYiwOCB5ApukiUH6nJvopdQ=").build(), true, new Relocation[0]);
        }
        try {
            Class.forName("kotlin.Lazy");
        }
        catch (final ClassNotFoundException ex2) {
            Log.info("Installing kotlin library, this may take a few minutes...", new Object[0]);
            libraryHandler.loadLibrary(Library.builder().groupId("org.jetbrains.kotlin").artifactId("kotlin-stdlib").version("1.9.23").checksum("iRDMI4gH2G71UMsfCxDdXtQLNaTsGlJSX3YK7ehOrTc=").build(), true, new Relocation[0]);
        }
    }
}
