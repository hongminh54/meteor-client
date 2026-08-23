// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.bootstrap.bukkit.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.bukkit.Bukkit;
import java.io.File;

public final class BootstrapUtil
{
    public static File getPluginDirectory() {
        Class<?> clazz;
        try {
            clazz = Class.forName("net.minecraft.server.MinecraftServer");
        }
        catch (final ClassNotFoundException ex) {
            final String name = Bukkit.getServer().getClass().getPackage().getName();
            final String substring = name.substring(name.lastIndexOf(46) + 1);
            try {
                clazz = Class.forName("net.minecraft.server." + substring + ".MinecraftServer");
            }
            catch (final ClassNotFoundException ex2) {
                throw new IllegalStateException("Couldn't find MinecraftServer class!");
            }
        }
        Class<?> clazz2;
        try {
            clazz2 = Class.forName("org.bukkit.craftbukkit.libs.joptsimple.OptionSet");
        }
        catch (final ClassNotFoundException ex3) {
            try {
                clazz2 = Class.forName("joptsimple.OptionSet");
            }
            catch (final ClassNotFoundException ex4) {
                throw new IllegalStateException("Couldn't find OptionSet class!");
            }
        }
        final Method method = clazz2.getMethod("valueOf", String.class);
        Field field = null;
        Field field2 = null;
        for (final Field field3 : clazz.getDeclaredFields()) {
            if (field3.getType() == clazz2) {
                field = field3;
            }
            else if (field3.getType() == clazz && Modifier.isStatic(field3.getModifiers())) {
                field2 = field3;
            }
        }
        if (field == null) {
            throw new IllegalStateException("Couldn't found OptionSet field!");
        }
        if (field2 == null) {
            throw new IllegalStateException("Couldn't found MinecraftServer field!");
        }
        field.setAccessible(true);
        field2.setAccessible(true);
        return (File)method.invoke(field.get(field2.get(null)), "plugins");
    }
    
    private BootstrapUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
