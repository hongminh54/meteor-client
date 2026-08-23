// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.strategy;

import java.lang.reflect.Field;
import dev.imanity.antixray.libs.objenesis.ObjenesisException;

public final class PlatformDescription
{
    public static final String GNU = "GNU libgcj";
    public static final String HOTSPOT = "Java HotSpot";
    @Deprecated
    public static final String SUN = "Java HotSpot";
    public static final String OPENJDK = "OpenJDK";
    public static final String PERC = "PERC";
    public static final String DALVIK = "Dalvik";
    public static final String SPECIFICATION_VERSION;
    public static final String VM_VERSION;
    public static final String VM_INFO;
    public static final String VENDOR_VERSION;
    public static final String VENDOR;
    public static final String JVM_NAME;
    public static final int ANDROID_VERSION;
    public static final boolean IS_ANDROID_OPENJDK;
    @Deprecated
    public static final String GAE_VERSION;
    
    public static String describePlatform() {
        String s = "Java " + PlatformDescription.SPECIFICATION_VERSION + " (VM vendor name=\"" + PlatformDescription.VENDOR + "\", VM vendor version=" + PlatformDescription.VENDOR_VERSION + ", JVM name=\"" + PlatformDescription.JVM_NAME + "\", JVM version=" + PlatformDescription.VM_VERSION + ", JVM info=" + PlatformDescription.VM_INFO;
        if (PlatformDescription.ANDROID_VERSION != 0) {
            s = s + ", API level=" + PlatformDescription.ANDROID_VERSION;
        }
        return s + ")";
    }
    
    public static boolean isThisJVM(final String prefix) {
        return PlatformDescription.JVM_NAME.startsWith(prefix);
    }
    
    public static boolean isAndroidOpenJDK() {
        return PlatformDescription.IS_ANDROID_OPENJDK;
    }
    
    private static boolean getIsAndroidOpenJDK() {
        if (getAndroidVersion() == 0) {
            return false;
        }
        final String property = System.getProperty("java.boot.class.path");
        return property != null && property.toLowerCase().contains("core-oj.jar");
    }
    
    public static boolean isAfterJigsaw() {
        return PlatformDescription.SPECIFICATION_VERSION.indexOf(46) < 0;
    }
    
    public static boolean isAfterJava11() {
        return isAfterJigsaw() && Integer.parseInt(PlatformDescription.SPECIFICATION_VERSION) >= 11;
    }
    
    @Deprecated
    public static boolean isGoogleAppEngine() {
        return PlatformDescription.GAE_VERSION != null;
    }
    
    @Deprecated
    private static String getGaeRuntimeVersion() {
        return System.getProperty("com.google.appengine.runtime.version");
    }
    
    private static int getAndroidVersion() {
        if (!isThisJVM("Dalvik")) {
            return 0;
        }
        return getAndroidVersion0();
    }
    
    private static int getAndroidVersion0() {
        Class<?> forName;
        try {
            forName = Class.forName("android.os.Build$VERSION");
        }
        catch (final ClassNotFoundException ex) {
            throw new ObjenesisException(ex);
        }
        Field field;
        try {
            field = forName.getField("SDK_INT");
        }
        catch (final NoSuchFieldException ex2) {
            return getOldAndroidVersion(forName);
        }
        int intValue;
        try {
            intValue = (int)field.get(null);
        }
        catch (final IllegalAccessException cause) {
            throw new RuntimeException(cause);
        }
        return intValue;
    }
    
    private static int getOldAndroidVersion(final Class<?> clazz) {
        Field field;
        try {
            field = clazz.getField("SDK");
        }
        catch (final NoSuchFieldException ex) {
            throw new ObjenesisException(ex);
        }
        String s;
        try {
            s = (String)field.get(null);
        }
        catch (final IllegalAccessException cause) {
            throw new RuntimeException(cause);
        }
        return Integer.parseInt(s);
    }
    
    private PlatformDescription() {
    }
    
    static {
        SPECIFICATION_VERSION = System.getProperty("java.specification.version");
        VM_VERSION = System.getProperty("java.runtime.version");
        VM_INFO = System.getProperty("java.vm.info");
        VENDOR_VERSION = System.getProperty("java.vm.version");
        VENDOR = System.getProperty("java.vm.vendor");
        JVM_NAME = System.getProperty("java.vm.name");
        ANDROID_VERSION = getAndroidVersion();
        IS_ANDROID_OPENJDK = getIsAndroidOpenJDK();
        GAE_VERSION = getGaeRuntimeVersion();
    }
}
