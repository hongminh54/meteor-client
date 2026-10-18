/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.gui.screens.settings;

import com.hongminh54.marisaoptimize.gui.GuiTheme;
import com.hongminh54.marisaoptimize.gui.WindowScreen;
import com.hongminh54.marisaoptimize.gui.widgets.WItemWithLabel;
import com.hongminh54.marisaoptimize.gui.widgets.containers.WTable;
import com.hongminh54.marisaoptimize.gui.widgets.input.WTextBox;
import com.hongminh54.marisaoptimize.gui.widgets.pressable.WButton;
import com.hongminh54.marisaoptimize.settings.BlockSetting;
import com.hongminh54.marisaoptimize.utils.misc.Names;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.registries.BuiltInRegistries;
import org.apache.commons.lang3.Strings;

public class BlockSettingScreen extends WindowScreen {
    private final BlockSetting setting;

    private WTable table;

    private WTextBox filter;
    private String filterText = "";

    public BlockSettingScreen(GuiTheme theme, BlockSetting setting) {
        super(theme, "Select Block");

        this.setting = setting;
    }

    @Override
    public void initWidgets() {
        filter = add(theme.textBox("")).minWidth(400).expandX().widget();
        filter.setFocused(true);
        filter.action = () -> {
            filterText = filter.get().trim();

            table.clear();
            initTable();
        };

        table = add(theme.table()).expandX().widget();

        initTable();
    }

    private void initTable() {
        for (Block block : BuiltInRegistries.BLOCK) {
            if (setting.filter != null && !setting.filter.test(block)) continue;
            if (skipValue(block)) continue;

            WItemWithLabel item = theme.itemWithLabel(block.asItem().getDefaultInstance(), Names.get(block));
            if (!filterText.isEmpty() && !Strings.CI.contains(item.getLabelText(), filterText)) continue;
            table.add(item);

            WButton select = table.add(theme.button("Select")).expandCellX().right().widget();
            select.action = () -> {
                setting.set(block);
                onClose();
            };

            table.row();
        }
    }

    protected boolean skipValue(Block value) {
        return value == Blocks.AIR || BuiltInRegistries.BLOCK.getKey(value).getPath().endsWith("_wall_banner");
    }
}
