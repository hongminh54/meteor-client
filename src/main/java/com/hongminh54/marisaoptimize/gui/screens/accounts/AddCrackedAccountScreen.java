/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.gui.screens.accounts;

import com.hongminh54.marisaoptimize.gui.GuiTheme;
import com.hongminh54.marisaoptimize.gui.widgets.containers.WTable;
import com.hongminh54.marisaoptimize.gui.widgets.input.WTextBox;
import com.hongminh54.marisaoptimize.systems.accounts.Accounts;
import com.hongminh54.marisaoptimize.systems.accounts.types.CrackedAccount;

public class AddCrackedAccountScreen extends AddAccountScreen {
    public AddCrackedAccountScreen(GuiTheme theme, AccountsScreen parent) {
        super(theme, "Add Cracked Account", parent);
    }

    @Override
    public void initWidgets() {
        WTable t = add(theme.table()).widget();

        // Name
        t.add(theme.label("Name: "));
        WTextBox name = t.add(theme.textBox("", "seasnail8169", (text, c) ->
            /** @see net.minecraft.util.StringHelper#isValidPlayerName */
            c > 32 && c < 127
        )).minWidth(400).expandX().widget();
        name.setFocused(true);
        t.row();

        // Add
        add = t.add(theme.button("Add")).expandX().widget();
        add.action = () -> {
            String username = name.get().trim();
            if (username.length() > 16) return;

            CrackedAccount account = new CrackedAccount(username);
            if (!(Accounts.get().exists(account))) {
                AccountsScreen.addAccount(this, parent, account);
            }
        };

        enterAction = add.action;
    }
}
