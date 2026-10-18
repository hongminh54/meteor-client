/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.systems.accounts.types;

import com.mojang.authlib.Environment;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import de.florianreuth.waybackauthlib.InvalidCredentialsException;
import de.florianreuth.waybackauthlib.WaybackAuthLib;
import com.hongminh54.marisaoptimize.MarisaOptimize;
import com.hongminh54.marisaoptimize.systems.accounts.Account;
import com.hongminh54.marisaoptimize.systems.accounts.AccountType;
import com.hongminh54.marisaoptimize.systems.accounts.TokenAccount;
import com.hongminh54.marisaoptimize.utils.misc.NbtException;
import net.minecraft.client.User;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

public class TheAlteningAccount extends Account<TheAlteningAccount> implements TokenAccount {
    private static final Environment ENVIRONMENT = new Environment("http://sessionserver.thealtening.com", "http://authserver.thealtening.com", "https://api.mojang.com", "The Altening");
    private static final YggdrasilAuthenticationService SERVICE = new YggdrasilAuthenticationService(mc.getProxy(), ENVIRONMENT);
    private String token;
    private @Nullable WaybackAuthLib auth;

    public TheAlteningAccount(String token) {
        super(AccountType.TheAltening, token);
        this.token = token;
    }

    @Override
    public boolean fetchInfo() {
        auth = getAuth();

        try {
            auth.logIn();

            cache.username = auth.getCurrentProfile().name();
            cache.uuid = auth.getCurrentProfile().id().toString();
            cache.loadHead();

            return true;
        } catch (InvalidCredentialsException e) {
            MarisaOptimize.LOG.error("Invalid TheAltening credentials.");
            return false;
        } catch (Exception e) {
            MarisaOptimize.LOG.error("Failed to fetch info for TheAltening account!");
            return false;
        }
    }

    @Override
    public boolean login() {
        if (auth == null) return false;
        applyLoginEnvironment(SERVICE);

        try {
            setSession(new User(auth.getCurrentProfile().name(), auth.getCurrentProfile().id(), auth.getAccessToken(), Optional.empty(), Optional.empty()));
            return true;
        } catch (Exception e) {
            MarisaOptimize.LOG.error("Failed to login with TheAltening.");
            return false;
        }
    }

    private WaybackAuthLib getAuth() {
        WaybackAuthLib auth = new WaybackAuthLib(ENVIRONMENT.servicesHost());

        auth.setUsername(name);
        auth.setPassword("Meteor on Crack!");

        return auth;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public CompoundTag toTag() {
        CompoundTag tag = new CompoundTag();

        tag.putString("type", type.name());
        tag.putString("name", name);
        tag.putString("token", token);
        tag.put("cache", cache.toTag());

        return tag;
    }

    @Override
    public TheAlteningAccount fromTag(CompoundTag tag) {
        if (tag.getString("name").isEmpty() || tag.getCompound("cache").isEmpty() || tag.getString("token").isEmpty()) throw new NbtException();

        name = tag.getString("name").get();
        token = tag.getString("token").get();
        cache.fromTag(tag.getCompound("cache").get());

        return this;
    }
}
