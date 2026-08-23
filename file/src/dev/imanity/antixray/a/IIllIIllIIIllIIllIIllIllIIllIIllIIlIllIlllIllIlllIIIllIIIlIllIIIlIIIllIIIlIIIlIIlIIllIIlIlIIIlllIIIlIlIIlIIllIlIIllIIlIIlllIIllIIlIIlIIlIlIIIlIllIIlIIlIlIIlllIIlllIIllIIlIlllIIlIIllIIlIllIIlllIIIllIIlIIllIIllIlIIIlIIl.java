// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import dev.imanity.antixray.config.AntiXrayConfig;
import dev.imanity.antixray.api.config.AbstractConfigService;
import dev.imanity.antixray.api.config.ConfigContext;
import java.util.function.Consumer;
import dev.imanity.antixray.api.AntiXrayAPI;
import dev.imanity.antixray.api.entity.EntityCullingGuiProvider;
import dev.imanity.antixray.api.entity.EntityCullingAPI;
import dev.imanity.antixray.api.tile.TileCullingGuiProvider;
import dev.imanity.antixray.api.tile.TileCullingAPI;
import io.fairyproject.bukkit.gui.pane.NormalPane;
import io.fairyproject.bukkit.gui.Gui;
import io.fairyproject.bukkit.gui.slot.GuiSlot;
import io.fairyproject.bukkit.util.items.ItemBuilder;
import io.fairyproject.libs.xseries.XMaterial;
import io.fairyproject.bukkit.gui.pane.Pane;
import io.fairyproject.bukkit.gui.pane.mapping.PaneMapping;
import io.fairyproject.libs.kyori.adventure.text.format.NamedTextColor;
import io.fairyproject.libs.kyori.adventure.text.format.TextColor;
import io.fairyproject.libs.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import kotlin.jvm.internal.Intrinsics;
import dev.imanity.antixray.config.AntiXrayConfigService;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.bukkit.gui.GuiFactory;
import io.fairyproject.container.InjectableComponent;

@InjectableComponent
public final class IIllIIllIIIllIIllIIllIllIIllIIllIIlIllIlllIllIlllIIIllIIIlIllIIIlIIIllIIIlIIIlIIlIIllIIlIlIIIlllIIIlIlIIlIIllIlIIllIIlIIlllIIllIIlIIlIIlIlIIIlIllIIlIIlIlIIlllIIlllIIllIIlIlllIIlIIllIIlIllIIlllIIIllIIlIIllIIllIlIIIlIIl
{
    @NotNull
    private final GuiFactory IIIllIllIIlIlllIlllIllIIIlllIllIIIllIIIlIIIllIIllIIIllIllIIllIlllIIIllIIIllIllIIIlIIIlllIIlllIIIlIIIlllIIIlIlIIlIIIlllIlllIlIlllIIlIIIllIIIlllIIIllIIlIlllIIllIIllIlIIlIIIlIIlllIIllIlllIIllIlllIIlIllIIIlIlIIIllIIlllIllIIlllIlllIIllIlIllIl;
    @NotNull
    private final IIIllIIllIIIllIIIlIlllIIIllIIllIllIIlllIlIIlllIlllIIllIllIIIlllIIllIIIlllIlIIIllIIllIIIlIIIllIIlIIllIIlllIllIllIIllIllIIllIllIIllIIIllIlllIllIllIIllIIllIlllIIlIIllIIllIlIIllIIIlllIIIllIllIllIllIllIIIlIIlIllIIIllIIlIIllIlIllIIlllIIll IIlIIIlIIlIllIllIIlIIllIIIlllIlllIIlIlllIIlIIllIIllIIlllIIllIlIIIlIIIllIIIllIllIllIlIlIIllIIlIIllIIlIlIllIIllIIlIIIlIIllIIIllIlIlllIIllIlllIIlIlIIIlIllIIllIlIIllIIlIlllIIllIIIlIIIllIIIlllIIIlllIIllIIllIlIIlIIlllIIIllIIlll;
    @NotNull
    private final AntiXrayConfigService IIlIIlllIlIIlllIIlIIIllIIlIIIllIIllIIIllIllIIIlllIlIIlIIIlIIlIIIllIIIlIIllIIIlllIIIlllIIlIIllIIIlllIllIIlllIIllIllIIIlllIIllIIllIIlIllIlIIIlIIIllIllIIIllIIlllIIlllIIllIIIlllIIIllIIIllIlllIIllIllIIllIIlIlIllIIlllIIllIlllIIllIlIIllIIllIllIIll;
    
    public IIllIIllIIIllIIllIIllIllIIllIIllIIlIllIlllIllIlllIIIllIIIlIllIIIlIIIllIIIlIIIlIIlIIllIIlIlIIIlllIIIlIlIIlIIllIlIIllIIlIIlllIIllIIlIIlIIlIlIIIlIllIIlIIlIlIIlllIIlllIIllIIlIlllIIlIIllIIlIllIIlllIIIllIIlIIllIIllIlIIIlIIl(@NotNull final GuiFactory iiIllIllIIlIlllIlllIllIIIlllIllIIIllIIIlIIIllIIllIIIllIllIIllIlllIIIllIIIllIllIIIlIIIlllIIlllIIIlIIIlllIIIlIlIIlIIIlllIlllIlIlllIIlIIIllIIIlllIIIllIIlIlllIIllIIllIlIIlIIIlIIlllIIllIlllIIllIlllIIlIllIIIlIlIIIllIIlllIllIIlllIlllIIllIlIllIl, @NotNull final IIIllIIllIIIllIIIlIlllIIIllIIllIllIIlllIlIIlllIlllIIllIllIIIlllIIllIIIlllIlIIIllIIllIIIlIIIllIIlIIllIIlllIllIllIIllIllIIllIllIIllIIIllIlllIllIllIIllIIllIlllIIlIIllIIllIlIIllIIIlllIIIllIllIllIllIllIIIlIIlIllIIIllIIlIIllIlIllIIlllIIll iIlIIIlIIlIllIllIIlIIllIIIlllIlllIIlIlllIIlIIllIIllIIlllIIllIlIIIlIIIllIIIllIllIllIlIlIIllIIlIIllIIlIlIllIIllIIlIIIlIIllIIIllIlIlllIIllIlllIIlIlIIIlIllIIllIlIIllIIlIlllIIllIIIlIIIllIIIlllIIIlllIIllIIllIlIIlIIlllIIIllIIlll, @NotNull final AntiXrayConfigService iIlIIlllIlIIlllIIlIIIllIIlIIIllIIllIIIllIllIIIlllIlIIlIIIlIIlIIIllIIIlIIllIIIlllIIIlllIIlIIllIIIlllIllIIlllIIllIllIIIlllIIllIIllIIlIllIlIIIlIIIllIllIIIllIIlllIIlllIIllIIIlllIIIllIIIllIlllIIllIllIIllIIlIlIllIIlllIIllIlllIIllIlIIllIIllIllIIll) {
        Intrinsics.checkNotNullParameter((Object)iiIllIllIIlIlllIlllIllIIIlllIllIIIllIIIlIIIllIIllIIIllIllIIllIlllIIIllIIIllIllIIIlIIIlllIIlllIIIlIIIlllIIIlIlIIlIIIlllIlllIlIlllIIlIIIllIIIlllIIIllIIlIlllIIllIIllIlIIlIIIlIIlllIIllIlllIIllIlllIIlIllIIIlIlIIIllIIlllIllIIlllIlllIIllIlIllIl, "");
        Intrinsics.checkNotNullParameter((Object)iIlIIIlIIlIllIllIIlIIllIIIlllIlllIIlIlllIIlIIllIIllIIlllIIllIlIIIlIIIllIIIllIllIllIlIlIIllIIlIIllIIlIlIllIIllIIlIIIlIIllIIIllIlIlllIIllIlllIIlIlIIIlIllIIllIlIIllIIlIlllIIllIIIlIIIllIIIlllIIIlllIIllIIllIlIIlIIlllIIIllIIlll, "");
        Intrinsics.checkNotNullParameter((Object)iIlIIlllIlIIlllIIlIIIllIIlIIIllIIllIIIllIllIIIlllIlIIlIIIlIIlIIIllIIIlIIllIIIlllIIIlllIIlIIllIIIlllIllIIlllIIllIllIIIlllIIllIIllIIlIllIlIIIlIIIllIllIIIllIIlllIIlllIIllIIIlllIIIllIIIllIlllIIllIllIIllIIlIlIllIIlllIIllIlllIIllIlIIllIIllIllIIll, "");
        this.IIIllIllIIlIlllIlllIllIIIlllIllIIIllIIIlIIIllIIllIIIllIllIIllIlllIIIllIIIllIllIIIlIIIlllIIlllIIIlIIIlllIIIlIlIIlIIIlllIlllIlIlllIIlIIIllIIIlllIIIllIIlIlllIIllIIllIlIIlIIIlIIlllIIllIlllIIllIlllIIlIllIIIlIlIIIllIIlllIllIIlllIlllIIllIlIllIl = iiIllIllIIlIlllIlllIllIIIlllIllIIIllIIIlIIIllIIllIIIllIllIIllIlllIIIllIIIllIllIIIlIIIlllIIlllIIIlIIIlllIIIlIlIIlIIIlllIlllIlIlllIIlIIIllIIIlllIIIllIIlIlllIIllIIllIlIIlIIIlIIlllIIllIlllIIllIlllIIlIllIIIlIlIIIllIIlllIllIIlllIlllIIllIlIllIl;
        this.IIlIIIlIIlIllIllIIlIIllIIIlllIlllIIlIlllIIlIIllIIllIIlllIIllIlIIIlIIIllIIIllIllIllIlIlIIllIIlIIllIIlIlIllIIllIIlIIIlIIllIIIllIlIlllIIllIlllIIlIlIIIlIllIIllIlIIllIIlIlllIIllIIIlIIIllIIIlllIIIlllIIllIIllIlIIlIIlllIIIllIIlll = iIlIIIlIIlIllIllIIlIIllIIIlllIlllIIlIlllIIlIIllIIllIIlllIIllIlIIIlIIIllIIIllIllIllIlIlIIllIIlIIllIIlIlIllIIllIIlIIIlIIllIIIllIlIlllIIllIlllIIlIlIIIlIllIIllIlIIllIIlIlllIIllIIIlIIIllIIIlllIIIlllIIllIIllIlIIlIIlllIIIllIIlll;
        this.IIlIIlllIlIIlllIIlIIIllIIlIIIllIIllIIIllIllIIIlllIlIIlIIIlIIlIIIllIIIlIIllIIIlllIIIlllIIlIIllIIIlllIllIIlllIIllIllIIIlllIIllIIllIIlIllIlIIIlIIIllIllIIIllIIlllIIlllIIllIIIlllIIIllIIIllIlllIIllIllIIllIIlIlIllIIlllIIllIlllIIllIlIIllIIllIllIIll = iIlIIlllIlIIlllIIlIIIllIIlIIIllIIllIIIllIllIIIlllIlIIlIIIlIIlIIIllIIIlIIllIIIlllIIIlllIIlIIllIIIlllIllIIlllIIllIllIIIlllIIllIIllIIlIllIlIIIlIIIllIllIIIllIIlllIIlllIIllIIIlllIIIllIIIllIlllIIllIllIIllIIlIlIllIIlllIIllIlllIIllIlIIllIIllIllIIll;
    }
    
    public final void IIlllIIllIlllIlllIIlllIIIlIllIIIlllIIlIIIlIlllIlllIIllIIIlIIllIIlIIlIllIIIllIIlllIIIllIIIllIIlllIIllIIllIIllIlllIlllIIIllIllIlIllIIlllIlllIIIlllIlllIlllIllIIllIIIllIIllIlllIIllIIllIIlllIIlIIlllIIIlIlIllIIlIIllIIIlIIlIIlIIIlllIIIllIIIllIIlIl(@NotNull final Player player) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        final Gui create = this.IIIllIllIIlIlllIlllIllIIIlllIllIIIllIIIlIIIllIIllIIIllIllIIllIlllIIIllIIIllIllIIIlIIIlllIIlllIIIlIIIlllIIIlIlIIlIIIlllIlllIlIlllIIlIIIllIIIlllIIIllIIlIlllIIllIIllIlIIlIIIlIIlllIIllIlllIIllIlllIIlIllIIIlIlIIIllIIlllIllIIlllIlllIIllIlIllIl.create((Component)Component.text("Raytrace Anti-Xray", (TextColor)NamedTextColor.AQUA));
        final NormalPane normal = Pane.normal(PaneMapping.outline(5));
        normal.fillEmptySlots(GuiSlot.of(ItemBuilder.of(XMaterial.GRAY_STAINED_GLASS_PANE).name(" ")));
        create.addPane((Pane)normal);
        final NormalPane normal2 = Pane.normal(1, 1, 7, 3);
        Intrinsics.checkNotNull((Object)normal2);
        this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(normal2);
        create.onDrawCallback(IIllIIllIIIllIIllIIllIllIIllIIllIIlIllIlllIllIlllIIIllIIIlIllIIIlIIIllIIIlIIIlIIlIIllIIlIlIIIlllIIIlIlIIlIIllIlIIllIIlIIlllIIllIIlIIlIIlIlIIIlIllIIlIIlIlIIlllIIlllIIllIIlIlllIIlIIllIIlIllIIlllIIIllIIlIIllIIllIlIIIlIIl::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        Intrinsics.checkNotNull((Object)create);
        this.IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(create, normal2);
        this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(create, normal2);
        this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(create, normal2);
        this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(normal2);
        create.addPane((Pane)normal2);
        create.open(player);
    }
    
    private final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final Gui gui, final NormalPane normalPane) {
        final TileCullingGuiProvider guiProvider = TileCullingAPI.INSTANCE.getGuiProvider();
        if (guiProvider != null) {
            normalPane.setSlot(5, 1, GuiSlot.of(ItemBuilder.of(XMaterial.CHEST).name("&aTile Culling").lore(new String[] { "&7This feature will hide tiles (chest, furnace, etc.)", "&7that are not visible to the player.", " ", "&aClick to configure it." }), IIllIIllIIIllIIllIIllIllIIllIIllIIlIllIlllIllIlllIIIllIIIlIllIIIlIIIllIIIlIIIlIIlIIllIIlIlIIIlllIIIlIlIIlIIllIlIIllIIlIIlllIIllIIlIIlIIlIlIIIlIllIIlIIlIlIIlllIIlllIIllIIlIlllIIlIIllIIlIllIIlllIIIllIIlIIllIIllIlIIIlIIl::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll));
        }
        else {
            normalPane.setSlot(5, 1, GuiSlot.of(ItemBuilder.of(XMaterial.RED_STAINED_GLASS_PANE).name("&cTile Culling").lore(new String[] { "&7This feature will hide tiles (chest, furnace, etc.)", "&7that are not visible to the player.", "&7Requires: &eTile Culling Addon", " ", "&cNot installed" })));
        }
    }
    
    private final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final Gui gui, final NormalPane normalPane) {
        final EntityCullingGuiProvider guiProvider = EntityCullingAPI.INSTANCE.getGuiProvider();
        if (guiProvider != null) {
            normalPane.setSlot(1, 1, GuiSlot.of(ItemBuilder.of(XMaterial.ZOMBIE_HEAD).name("&aEntity Culling").lore(new String[] { "&7This feature will hide entities that are", "&7not visible to the player.", " ", "&aClick to configure it." }), IIllIIllIIIllIIllIIllIllIIllIIllIIlIllIlllIllIlllIIIllIIIlIllIIIlIIIllIIIlIIIlIIlIIllIIlIlIIIlllIIIlIlIIlIIllIlIIllIIlIIlllIIllIIlIIlIIlIlIIIlIllIIlIIlIlIIlllIIlllIIllIIlIlllIIlIIllIIlIllIIlllIIIllIIlIIllIIllIlIIIlIIl::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll));
        }
        else {
            normalPane.setSlot(1, 1, GuiSlot.of(ItemBuilder.of(XMaterial.RED_STAINED_GLASS_PANE).name("&cEntity Culling").lore(new String[] { "&7This feature will hide entities that are", "&7not visible to the player.", "&7Requires: &eEntity Culling Addon", " ", "&cNot installed" })));
        }
    }
    
    private final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final Player player, final Gui gui, final NormalPane normalPane) {
        if (AntiXrayAPI.INSTANCE.isBypassed(player)) {
            normalPane.setSlot(4, 0, GuiSlot.of(ItemBuilder.of(XMaterial.ENDER_EYE).name("&aBypass").lore(new String[] { "&7Click here to stop bypassing the Anti-Xray", "&7for &eyourself only&7.", " ", "&aClick to stop bypassing." }), IIllIIllIIIllIIllIIllIllIIllIIllIIlIllIlllIllIlllIIIllIIIlIllIIIlIIIllIIIlIIIlIIlIIllIIlIlIIIlllIIIlIlIIlIIllIlIIllIIlIIlllIIllIIlIIlIIlIlIIIlIllIIlIIlIlIIlllIIlllIIllIIlIlllIIlIIllIIlIllIIlllIIIllIIlIIllIIllIlIIIlIIl::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll));
        }
        else {
            normalPane.setSlot(4, 0, GuiSlot.of(ItemBuilder.of(XMaterial.BARRIER).name("&cBypass").lore(new String[] { "&7Click here to bypass the Anti-Xray", "&7for &eyourself only&7.", " ", "&aClick to bypass." }), IIllIIllIIIllIIllIIllIllIIllIIllIIlIllIlllIllIlllIIIllIIIlIllIIIlIIIllIIIlIIIlIIlIIllIIlIlIIIlllIIIlIlIIlIIllIlIIllIIlIIlllIIllIIlIIlIIlIlIIIlIllIIlIIlIlIIlllIIlllIIllIIlIlllIIlIIllIIlIllIIlllIIIllIIlIIllIIllIlIIIlIIl::IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll));
        }
    }
    
    private final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final NormalPane normalPane) {
        normalPane.setSlot(2, 0, GuiSlot.of(ItemBuilder.of(XMaterial.REDSTONE).name("&cReload Configuration").lore(new String[] { "&7Click here to reload the configuration of", "&7Raytrace Anti-Xray.", " ", "&aClick to reload it." }), (Consumer)IIllIIllIIIllIIllIIllIllIIllIIllIIlIllIlllIllIlllIIIllIIIlIllIIIlIIIllIIIlIIIlIIlIIllIIlIlIIIlllIIIlIlIIlIIllIlIIllIIlIIlllIIllIIlIIlIIlIlIIIlIllIIlIIlIlIIlllIIlllIIllIIlIlllIIlIIllIIlIllIIlllIIIllIIlIIllIIllIlIIIlIIl::IllIllIllIlllIIlllIIlllIIlIIlllIIlIIllIIllIIllIIIlIllIIllIlllIlIIllIIlllIIlIIIlIIlIIllIlllIIlIIIlIIIlIllIlIIllIIllIIlllIIllIIlIlllIlllIIIlIIIlllIIllIIIlllIIllIllIIlllIIllIIllIIllIlIIllIllIIlllIIllIIlIIllIlIIIlllIIlIllIIlllIllIIl));
    }
    
    private final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final NormalPane normalPane) {
        normalPane.setSlot(3, 2, GuiSlot.of(ItemBuilder.of(XMaterial.BOOK).name("&eDocumentation").lore(new String[] { "&7Click here to view the documentation of", "&7Raytrace Anti-Xray.", " ", "&aClick to view it." }), (Consumer)IIllIIllIIIllIIllIIllIllIIllIIllIIlIllIlllIllIlllIIIllIIIlIllIIIlIIIllIIIlIIIlIIlIIllIIlIlIIIlllIIIlIlIIlIIllIlIIllIIlIIlllIIllIIlIIlIIlIlIIIlIllIIlIIlIlIIlllIIlllIIllIIlIlllIIlIIllIIlIllIIlllIIIllIIlIIllIIllIlIIIlIIl::IlIllIlllIIllIlllIIllIIlllIIIllIIllIIlIIIlllIllIIlIlIIllIIIllIlIlIIllIllIlllIlIIllIIIlllIIllIIIlIIIlIIlIIlIIIlllIllIlllIIIlIIllIIIlIllIIIllIIlIIlIIIlIlIlIIIlllIlllIIlIllIIlllIIllIllIIlllIIIllIlIIllIIllIIlIIlIIllIllIIIllIl));
    }
    
    private final void IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(final Gui gui, final NormalPane normalPane) {
        normalPane.setSlot(3, 1, GuiSlot.of(ItemBuilder.of(XMaterial.DIAMOND_ORE).name("&bAntiXray &7(Ores, Fake Blocks)").lore(new String[] { "&7The core of Raytrace Anti-Xray.", "&7This part of configuration mainly focuses on", "&7how the ray-traced ores and fake blocks are handled.", " ", "&aClick to configure it." }), IIllIIllIIIllIIllIIllIllIIllIIllIIlIllIlllIllIlllIIIllIIIlIllIIIlIIIllIIIlIIIlIIlIIllIIlIlIIIlllIIIlIlIIlIIllIlIIllIIlIIlllIIllIIlIIlIIlIlIIIlIllIIlIIlIlIIlllIIlllIIllIIlIlllIIlIIllIIlIllIIlllIIIllIIlIIllIIllIlIIIlIIl::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll));
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final IIllIIllIIIllIIllIIllIllIIllIIllIIlIllIlllIllIlllIIIllIIIlIllIIIlIIIllIIIlIIIlIIlIIllIIlIlIIIlllIIIlIlIIlIIllIlIIllIIlIIlllIIllIIlIIlIIlIlIIIlIllIIlIIlIlIIlllIIlllIIllIIlIlllIIlIIllIIlIllIIlllIIIllIIlIIllIIllIlIIIlIIl illIIllIIIllIIllIIllIllIIllIIllIIlIllIlllIllIlllIIIllIIIlIllIIIlIIIllIIIlIIIlIIlIIllIIlIlIIIlllIIIlIlIIlIIllIlIIllIIlIIlllIIllIIlIIlIIlIlIIIlIllIIlIIlIlIIlllIIlllIIllIIlIlllIIlIIllIIlIllIIlllIIIllIIlIIllIIllIlIIIlIIl, final Player player, final Gui gui, final NormalPane normalPane, final Player player2) {
        Intrinsics.checkNotNullParameter((Object)illIIllIIIllIIllIIllIllIIllIIllIIlIllIlllIllIlllIIIllIIIlIllIIIlIIIllIIIlIIIlIIlIIllIIlIlIIIlllIIIlIlIIlIIllIlIIllIIlIIlllIIllIIlIIlIIlIlIIIlIllIIlIIlIlIIlllIIlllIIllIIlIlllIIlIIllIIlIllIIlllIIIllIIlIIllIIllIlIIIlIIl, "");
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNull((Object)gui);
        Intrinsics.checkNotNull((Object)normalPane);
        illIIllIIIllIIllIIllIllIIllIIllIIlIllIlllIllIlllIIIllIIIlIllIIIlIIIllIIIlIIIlIIlIIllIIlIlIIIlllIIIlIlIIlIIllIlIIllIIlIIlllIIllIIlIIlIIlIlIIIlIllIIlIIlIlIIlllIIlllIIllIIlIlllIIlIIllIIlIllIIlllIIIllIIlIIllIIllIlIIIlIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(player, gui, normalPane);
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final TileCullingGuiProvider tileCullingGuiProvider, final Gui gui, final Player player) {
        Intrinsics.checkNotNullParameter((Object)gui, "");
        Intrinsics.checkNotNull((Object)player);
        tileCullingGuiProvider.open(player, gui);
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final EntityCullingGuiProvider entityCullingGuiProvider, final Gui gui, final Player player) {
        Intrinsics.checkNotNullParameter((Object)gui, "");
        Intrinsics.checkNotNull((Object)player);
        entityCullingGuiProvider.open(player, gui);
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final Gui gui, final Player player) {
        Intrinsics.checkNotNullParameter((Object)gui, "");
        player.performCommand("antixray toggle");
        gui.update(player);
    }
    
    private static final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final Gui gui, final Player player) {
        Intrinsics.checkNotNullParameter((Object)gui, "");
        player.performCommand("antixray toggle");
        gui.update(player);
    }
    
    private static final void IllIllIllIlllIIlllIIlllIIlIIlllIIlIIllIIllIIllIIIlIllIIllIlllIlIIllIIlllIIlIIIlIIlIIllIlllIIlIIIlIIIlIllIlIIllIIllIIlllIIllIIlIlllIlllIIIlIIIlllIIllIIIlllIIllIllIIlllIIllIIllIIllIlIIllIllIIlllIIllIIlIIllIlIIIlllIIlIllIIlllIllIIl(final Player player) {
        player.performCommand("antixray reload");
    }
    
    private static final void IlIllIlllIIllIlllIIllIIlllIIIllIIllIIlIIIlllIllIIlIlIIllIIIllIlIlIIllIllIlllIlIIllIIIlllIIllIIIlIIIlIIlIIlIIIlllIllIlllIIIlIIllIIIlIllIIIllIIlIIlIIIlIlIlIIIlllIlllIIlIllIIlllIIllIllIIlllIIIllIlIIllIIllIIlIIlIIllIllIIIllIl(final Player player) {
        player.closeInventory();
        player.performCommand("antixray docs");
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final IIllIIllIIIllIIllIIllIllIIllIIllIIlIllIlllIllIlllIIIllIIIlIllIIIlIIIllIIIlIIIlIIlIIllIIlIlIIIlllIIIlIlIIlIIllIlIIllIIlIIlllIIllIIlIIlIIlIlIIIlIllIIlIIlIlIIlllIIlllIIllIIlIlllIIlIIllIIlIllIIlllIIIllIIlIIllIIllIlIIIlIIl illIIllIIIllIIllIIllIllIIllIIllIIlIllIlllIllIlllIIIllIIIlIllIIIlIIIllIIIlIIIlIIlIIllIIlIlIIIlllIIIlIlIIlIIllIlIIllIIlIIlllIIllIIlIIlIIlIlIIIlIllIIlIIlIlIIlllIIlllIIllIIlIlllIIlIIllIIlIllIIlllIIIllIIlIIllIIllIlIIIlIIl, final Gui gui, final Player player) {
        Intrinsics.checkNotNullParameter((Object)illIIllIIIllIIllIIllIllIIllIIllIIlIllIlllIllIlllIIIllIIIlIllIIIlIIIllIIIlIIIlIIlIIllIIlIlIIIlllIIIlIlIIlIIllIlIIllIIlIIlllIIllIIlIIlIIlIlIIIlIllIIlIIlIlIIlllIIlllIIllIIlIlllIIlIIllIIlIllIIlllIIIllIIlIIllIIllIlIIIlIIl, "");
        Intrinsics.checkNotNullParameter((Object)gui, "");
        final IIIllIIllIIIllIIIlIlllIIIllIIllIllIIlllIlIIlllIlllIIllIllIIIlllIIllIIIlllIlIIIllIIllIIIlIIIllIIlIIllIIlllIllIllIIllIllIIllIllIIllIIIllIlllIllIllIIllIIllIlllIIlIIllIIllIlIIllIIIlllIIIllIllIllIllIllIIIlIIlIllIIIllIIlIIllIlIllIIlllIIll iIlIIIlIIlIllIllIIlIIllIIIlllIlllIIlIlllIIlIIllIIllIIlllIIllIlIIIlIIIllIIIllIllIllIlIlIIllIIlIIllIIlIlIllIIllIIlIIIlIIllIIIllIlIlllIIllIlllIIlIlIIIlIllIIllIlIIllIIlIlllIIllIIIlIIIllIIIlllIIIlllIIllIIllIlIIlIIlllIIIllIIlll = illIIllIIIllIIllIIllIllIIllIIllIIlIllIlllIllIlllIIIllIIIlIllIIIlIIIllIIIlIIIlIIlIIllIIlIlIIIlllIIIlIlIIlIIllIlIIllIIlIIlllIIllIIlIIlIIlIlIIIlIllIIlIIlIlIIlllIIlllIIllIIlIlllIIlIIllIIlIllIIlllIIIllIIlIIllIIllIlIIIlIIl.IIlIIIlIIlIllIllIIlIIllIIIlllIlllIIlIlllIIlIIllIIllIIlllIIllIlIIIlIIIllIIIllIllIllIlIlIIllIIlIIllIIlIlIllIIllIIlIIIlIIllIIIllIlIlllIIllIlllIIlIlIIIlIllIIllIlIIllIIlIlllIIllIIIlIIIllIIIlllIIIlllIIllIIllIlIIlIIlllIIIllIIlll;
        Intrinsics.checkNotNull((Object)player);
        iIlIIIlIIlIllIllIIlIIllIIIlllIlllIIlIlllIIlIIllIIllIIlllIIllIlIIIlIIIllIIIllIllIllIlIlIIllIIlIIllIIlIlIllIIllIIlIIIlIIllIIIllIlIlllIIllIlllIIlIlIIIlIllIIllIlIIllIIlIlllIIllIIIlIIIllIIIlllIIIlllIIllIIllIlIIlIIlllIIIllIIlll.IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(player, ConfigContext.Companion.from(player, (AbstractConfigService<AntiXrayConfig>)illIIllIIIllIIllIIllIllIIllIIllIIlIllIlllIllIlllIIIllIIIlIllIIIlIIIllIIIlIIIlIIlIIllIIlIlIIIlllIIIlIlIIlIIllIlIIllIIlIIlllIIllIIlIIlIIlIlIIIlIllIIlIIlIlIIlllIIlllIIllIIlIlllIIlIIllIIlIllIIlllIIIllIIlIIllIIllIlIIIlIIl.IIlIIlllIlIIlllIIlIIIllIIlIIIllIIllIIIllIllIIIlllIlIIlIIIlIIlIIIllIIIlIIllIIIlllIIIlllIIlIIllIIIlllIllIIlllIIllIllIIIlllIIllIIllIIlIllIlIIIlIIIllIllIIIllIIlllIIlllIIllIIIlllIIIllIIIllIlllIIllIllIIllIIlIlIllIIlllIIllIlllIIllIlIIllIIllIllIIll), gui);
    }
}
