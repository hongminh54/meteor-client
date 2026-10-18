/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.systems.accounts;

import com.hongminh54.marisaoptimize.systems.System;
import com.hongminh54.marisaoptimize.systems.Systems;
import com.hongminh54.marisaoptimize.systems.accounts.types.CrackedAccount;
import com.hongminh54.marisaoptimize.systems.accounts.types.MicrosoftAccount;
import com.hongminh54.marisaoptimize.systems.accounts.types.SessionAccount;
import com.hongminh54.marisaoptimize.systems.accounts.types.TheAlteningAccount;
import com.hongminh54.marisaoptimize.utils.misc.NbtException;
import com.hongminh54.marisaoptimize.utils.misc.NbtUtils;
import com.hongminh54.marisaoptimize.utils.network.MeteorExecutor;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Accounts extends System<Accounts> implements Iterable<Account<?>> {
    private List<Account<?>> accounts = new ArrayList<>();

    public Accounts() {
        super("accounts");
    }

    public static Accounts get() {
        return Systems.get(Accounts.class);
    }

    public void add(Account<?> account) {
        accounts.add(account);
        save();
    }

    public boolean exists(Account<?> account) {
        return accounts.contains(account);
    }

    public void remove(Account<?> account) {
        if (accounts.remove(account)) {
            save();
        }
    }

    public int size() {
        return accounts.size();
    }

    @Override
    public @NotNull Iterator<Account<?>> iterator() {
        return accounts.iterator();
    }

    @Override
    public CompoundTag toTag() {
        CompoundTag tag = new CompoundTag();

        tag.put("accounts", NbtUtils.listToTag(accounts));

        return tag;
    }

    @Override
    public Accounts fromTag(CompoundTag tag) {
        MeteorExecutor.execute(() -> accounts = NbtUtils.listFromTag(tag.getListOrEmpty("accounts"), tag1 -> {
            CompoundTag t = (CompoundTag) tag1;
            if (!t.contains("type")) return null;

            AccountType type = AccountType.valueOf(t.getStringOr("type", ""));

            try {
                return switch (type) {
                    case Cracked ->     new CrackedAccount(null).fromTag(t);
                    case Microsoft ->   new MicrosoftAccount(null).fromTag(t);
                    case TheAltening -> new TheAlteningAccount(null).fromTag(t);
                    case Session ->     new SessionAccount(null).fromTag(t);
                };
            } catch (NbtException e) {
                return null;
            }
        }));

        return this;
    }
}
