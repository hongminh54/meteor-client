/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.systems.accounts;

import com.mojang.authlib.minecraft.UserApiService;
import com.mojang.authlib.yggdrasil.FriendsService;
import com.mojang.authlib.yggdrasil.ServicesKeyType;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.hongminh54.marisaoptimize.mixin.FileCacheAccessor;
import com.hongminh54.marisaoptimize.mixin.MinecraftClientAccessor;
import com.hongminh54.marisaoptimize.mixin.PlayerSkinProviderAccessor;
import com.hongminh54.marisaoptimize.utils.misc.ISerializable;
import com.hongminh54.marisaoptimize.utils.misc.NbtException;
import net.minecraft.client.gui.screens.social.PlayerSocialManager;
import net.minecraft.client.gui.screens.social.RemoteFriendListUpdateHandler;
import net.minecraft.client.multiplayer.ProfileKeyPairManager;
import net.minecraft.client.User;
import net.minecraft.client.multiplayer.chat.report.ReportingContext;
import net.minecraft.client.multiplayer.chat.report.ReportEnvironment;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.client.renderer.texture.SkinTextureDownloader;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.SignatureValidator;
import net.minecraft.server.Services;
import net.minecraft.util.Util;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

public abstract class Account<T extends Account<?>> implements ISerializable<T> {
    protected AccountType type;
    protected String name;

    protected final AccountCache cache;

    protected Account(AccountType type, String name) {
        this.type = type;
        this.name = name;
        this.cache = new AccountCache();
    }

    public abstract boolean fetchInfo();

    public boolean login() {
        YggdrasilAuthenticationService authenticationService = new YggdrasilAuthenticationService(mc.getProxy());
        applyLoginEnvironment(authenticationService);

        return true;
    }

    public String getUsername() {
        if (cache.username.isEmpty()) return name;
        return cache.username;
    }

    public AccountType getType() {
        return type;
    }

    public AccountCache getCache() {
        return cache;
    }

    public static void setSession(User session) {
        MinecraftClientAccessor mca = (MinecraftClientAccessor) mc;
        mca.meteor$setSession(session);

        YggdrasilAuthenticationService yggdrasilAuthenticationService = new YggdrasilAuthenticationService(mc.getProxy());

        UserApiService apiService = yggdrasilAuthenticationService.createUserApiService(session.getAccessToken());
        FriendsService friendsService = yggdrasilAuthenticationService.createFriendsService(session.getAccessToken());
        RemoteFriendListUpdateHandler remoteFriendListUpdateHandler = new RemoteFriendListUpdateHandler(friendsService, mc);
        mca.meteor$setUserApiService(apiService);
        mca.meteor$setSocialInteractionsManager(new PlayerSocialManager(mc, apiService, friendsService, remoteFriendListUpdateHandler));
        mca.meteor$setProfileKeys(ProfileKeyPairManager.create(apiService, session, mc.gameDirectory.toPath()));
        mca.meteor$setAbuseReportContext(ReportingContext.create(ReportEnvironment.local(), apiService));
        mca.meteor$setGameProfileFuture(CompletableFuture.supplyAsync(() -> mc.services().sessionService().fetchProfile(session.getProfileId(), true), Util.ioPool()));
    }

    public static void applyLoginEnvironment(YggdrasilAuthenticationService authService) {
        MinecraftClientAccessor mca = (MinecraftClientAccessor) mc;
        SignatureValidator.from(authService.getServicesKeySet(), ServicesKeyType.PROFILE_KEY);
        SkinManager.TextureCache skinCache = ((PlayerSkinProviderAccessor) mc.getSkinManager()).meteor$getSkinCache();
        Path skinCachePath = ((FileCacheAccessor) skinCache).meteor$getDirectory();
        mca.meteor$setApiServices(Services.create(authService, mc.gameDirectory));
        mca.meteor$setSkinProvider(new SkinManager(skinCachePath, mc.services(), new SkinTextureDownloader(mc.getProxy(), mc.getTextureManager(), mc), mc));
    }

    @Override
    public CompoundTag toTag() {
        CompoundTag tag = new CompoundTag();

        tag.putString("type", type.name());
        tag.putString("name", name);
        tag.put("cache", cache.toTag());

        return tag;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T fromTag(CompoundTag tag) {
        if (tag.getString("name").isEmpty() || tag.getCompound("cache").isEmpty()) throw new NbtException();

        name = tag.getString("name").get();
        cache.fromTag(tag.getCompound("cache").get());

        return (T) this;
    }
}
