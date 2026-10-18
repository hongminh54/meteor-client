package com.hongminh54.marisaoptimize.systems;

/** Global system discovery must not run during schema checks. */
public class Systems {
    public static <T> T get(Class<T> type) { throw new UnsupportedOperationException("No global systems in friend checks"); }
}
