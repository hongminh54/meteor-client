// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.resolver;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class PackageNameCache
{
    private final Set<String> _defaultPackages;
    private final Set<String> _packages;
    
    public PackageNameCache() {
        this._defaultPackages = new HashSet<String>();
        this._packages = new HashSet<String>();
    }
    
    public PackageNameCache addDefaultPackage(final String s) {
        synchronized (this._packages) {
            addPackageToSet(this._defaultPackages, s);
            addPackageToSet(this._packages, s);
        }
        return this;
    }
    
    public void reset() {
        synchronized (this._packages) {
            this._packages.clear();
            this._packages.addAll(this._defaultPackages);
        }
    }
    
    public boolean isPackage(final String s) {
        synchronized (this._packages) {
            return this._packages.contains(s);
        }
    }
    
    public void addPackage(final String s) {
        synchronized (this._packages) {
            addPackageToSet(this._packages, s);
        }
    }
    
    public void addPackageOfClassName(final String s) {
        final int lastIndex = s.lastIndexOf(46);
        if (lastIndex != -1) {
            this.addPackage(s.substring(0, lastIndex));
        }
    }
    
    public boolean canExist(final String s) {
        String substring = s;
        final int index = s.indexOf(36);
        if (index != -1) {
            substring = s.substring(0, index);
        }
        synchronized (this._packages) {
            return !this._packages.contains(substring);
        }
    }
    
    private static void addPackageToSet(final Set<String> set, String substring) {
        int lastIndex;
        while (set.add(substring) && (lastIndex = substring.lastIndexOf(46)) != -1) {
            substring = substring.substring(0, lastIndex);
        }
    }
}
