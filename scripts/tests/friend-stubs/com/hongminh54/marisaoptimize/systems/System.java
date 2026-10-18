package com.hongminh54.marisaoptimize.systems;

/** Disk persistence is excluded from isolated friend schema checks. */
public abstract class System<T> implements com.hongminh54.marisaoptimize.utils.misc.ISerializable<T> {
    protected System(String name) {}
    public void save() {}
}
