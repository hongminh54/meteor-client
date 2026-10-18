package com.hongminh54.marisaoptimize.utils.network;

/** Intentionally suppress profile/head refresh so schema checks never access the network. */
public class MeteorExecutor {
    public static void execute(Runnable task) {}
}
