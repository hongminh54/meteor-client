// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import org.bukkit.event.inventory.ClickType;
import java.util.Iterator;
import java.util.List;
import io.fairyproject.util.CC;
import org.bukkit.entity.Entity;
import io.fairyproject.libs.xseries.XSound;
import io.fairyproject.mc.scheduler.MCSchedulers;
import org.bukkit.World;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import org.bukkit.Material;
import dev.imanity.antixray.AntiXrayPlugin;
import org.bukkit.inventory.ItemStack;
import kotlin.reflect.KMutableProperty0;
import dev.imanity.antixray.config.AntiXrayConfig;
import io.fairyproject.bukkit.gui.Gui;
import io.fairyproject.bukkit.util.items.ItemBuilder;
import io.fairyproject.libs.xseries.XMaterial;
import io.fairyproject.bukkit.gui.slot.GuiSlot;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.internal.SourceDebugExtension;

@SourceDebugExtension({ "SMAP\nAbstractOresGui.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractOresGui.kt\ndev/imanity/antixray/core/gui/ores/AbstractOresGui\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,185:1\n1855#2,2:186\n*S KotlinDebug\n*F\n+ 1 AbstractOresGui.kt\ndev/imanity/antixray/core/gui/ores/AbstractOresGui\n*L\n44#1:186,2\n*E\n" })
public abstract class IIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll
{
    @NotNull
    private final IllIIllIIIllIIllIllIIllIIIlIIllIllIIIlIIIlIllIIIlllIllIllIlIIllIIlllIlllIIlIIllIlIIlIIllIllIIIlIIIllIlllIIllIllIIllIIIllIIllIIIlIIIlIIIlllIIIlIIlIlllIIllIIllIIIlIIlIIlllIlIIllIllIIIllIIlIIllIIlllIIIllIIIllIIIlllIIllIIIllIIIlIIlllIIlIIll IIlllIIllIllIllIIIlIIIlllIlIIlIllIlIIIlllIlIllIIIllIIIlllIIllIllIIllIIlIIIllIIIlIIlIIIllIlIIlIIlIIIllIIllIlIIllIIlIllIlIllIllIIllIIIllIIlIlIIllIllIIIllIIIlllIIIllIIIlIIllIIllIlIIllIIlIIlIllIIllIIIllIllIlllIlllIIlllIIIlIl;
    @NotNull
    private final IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll IllIlllIlllIlIIllIllIllIIIllIlIIIllIIllIlIllIIIlIIIllIlllIllIllIlllIIIlIIlIIIllIIlIIIlIllIIlIIllIllIIlIIllIlIIlIIllIlllIIlIlIIllIIlllIIllIIlllIIllIllIlllIIlllIIlllIIIllIIllIIlIIlIlIIllIIIlIlIIllIlllIIlIIIllIIIlIllIll;
    @NotNull
    private final IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll IlllIIlIIIlIIIllIIlIIllIIIlllIIlllIlllIIllIIlllIIIllIllIIllIIlIIllIIlIIllIlIIlIIllIllIIIlIlllIlIIIlIIlIlIIllIIllIIllIIIlllIIIllIllIIlllIlllIIlIlIIIlllIllIIIlIIllIIlIllIlllIIIlIlllIlllIlIIlIIIllIIllIlllIIllIIllIlllIIllIIllIIlllIlll;
    
    public IIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll(@NotNull final IllIIllIIIllIIllIllIIllIIIlIIllIllIIIlIIIlIllIIIlllIllIllIlIIllIIlllIlllIIlIIllIlIIlIIllIllIIIlIIIllIlllIIllIllIIllIIIllIIllIIIlIIIlIIIlllIIIlIIlIlllIIllIIllIIIlIIlIIlllIlIIllIllIIIllIIlIIllIIlllIIIllIIIllIIIlllIIllIIIllIIIlIIlllIIlIIll iIlllIIllIllIllIIIlIIIlllIlIIlIllIlIIIlllIlIllIIIllIIIlllIIllIllIIllIIlIIIllIIIlIIlIIIllIlIIlIIlIIIllIIllIlIIllIIlIllIlIllIllIIllIIIllIIlIlIIllIllIIIllIIIlllIIIllIIIlIIllIIllIlIIllIIlIIlIllIIllIIIllIllIlllIlllIIlllIIIlIl, @NotNull final IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll illIlllIlllIlIIllIllIllIIIllIlIIIllIIllIlIllIIIlIIIllIlllIllIllIlllIIIlIIlIIIllIIlIIIlIllIIlIIllIllIIlIIllIlIIlIIllIlllIIlIlIIllIIlllIIllIIlllIIllIllIlllIIlllIIlllIIIllIIllIIlIIlIlIIllIIIlIlIIllIlllIIlIIIllIIIlIllIll, @NotNull final IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll illlIIlIIIlIIIllIIlIIllIIIlllIIlllIlllIIllIIlllIIIllIllIIllIIlIIllIIlIIllIlIIlIIllIllIIIlIlllIlIIIlIIlIlIIllIIllIIllIIIlllIIIllIllIIlllIlllIIlIlIIIlllIllIIIlIIllIIlIllIlllIIIlIlllIlllIlIIlIIIllIIllIlllIIllIIllIlllIIllIIllIIlllIlll) {
        Intrinsics.checkNotNullParameter((Object)iIlllIIllIllIllIIIlIIIlllIlIIlIllIlIIIlllIlIllIIIllIIIlllIIllIllIIllIIlIIIllIIIlIIlIIIllIlIIlIIlIIIllIIllIlIIllIIlIllIlIllIllIIllIIIllIIlIlIIllIllIIIllIIIlllIIIllIIIlIIllIIllIlIIllIIlIIlIllIIllIIIllIllIlllIlllIIlllIIIlIl, "");
        Intrinsics.checkNotNullParameter((Object)illIlllIlllIlIIllIllIllIIIllIlIIIllIIllIlIllIIIlIIIllIlllIllIllIlllIIIlIIlIIIllIIlIIIlIllIIlIIllIllIIlIIllIlIIlIIllIlllIIlIlIIllIIlllIIllIIlllIIllIllIlllIIlllIIlllIIIllIIllIIlIIlIlIIllIIIlIlIIllIlllIIlIIIllIIIlIllIll, "");
        Intrinsics.checkNotNullParameter((Object)illlIIlIIIlIIIllIIlIIllIIIlllIIlllIlllIIllIIlllIIIllIllIIllIIlIIllIIlIIllIlIIlIIllIllIIIlIlllIlIIIlIIlIlIIllIIllIIllIIIlllIIIllIllIIlllIlllIIlIlIIIlllIllIIIlIIllIIlIllIlllIIIlIlllIlllIlIIlIIIllIIllIlllIIllIIllIlllIIllIIllIIlllIlll, "");
        this.IIlllIIllIllIllIIIlIIIlllIlIIlIllIlIIIlllIlIllIIIllIIIlllIIllIllIIllIIlIIIllIIIlIIlIIIllIlIIlIIlIIIllIIllIlIIllIIlIllIlIllIllIIllIIIllIIlIlIIllIllIIIllIIIlllIIIllIIIlIIllIIllIlIIllIIlIIlIllIIllIIIllIllIlllIlllIIlllIIIlIl = iIlllIIllIllIllIIIlIIIlllIlIIlIllIlIIIlllIlIllIIIllIIIlllIIllIllIIllIIlIIIllIIIlIIlIIIllIlIIlIIlIIIllIIllIlIIllIIlIllIlIllIllIIllIIIllIIlIlIIllIllIIIllIIIlllIIIllIIIlIIllIIllIlIIllIIlIIlIllIIllIIIllIllIlllIlllIIlllIIIlIl;
        this.IllIlllIlllIlIIllIllIllIIIllIlIIIllIIllIlIllIIIlIIIllIlllIllIllIlllIIIlIIlIIIllIIlIIIlIllIIlIIllIllIIlIIllIlIIlIIllIlllIIlIlIIllIIlllIIllIIlllIIllIllIlllIIlllIIlllIIIllIIllIIlIIlIlIIllIIIlIlIIllIlllIIlIIIllIIIlIllIll = illIlllIlllIlIIllIllIllIIIllIlIIIllIIllIlIllIIIlIIIllIlllIllIllIlllIIIlIIlIIIllIIlIIIlIllIIlIIllIllIIlIIllIlIIlIIllIlllIIlIlIIllIIlllIIllIIlllIIllIllIlllIIlllIIlllIIIllIIllIIlIIlIlIIllIIIlIlIIllIlllIIlIIIllIIIlIllIll;
        this.IlllIIlIIIlIIIllIIlIIllIIIlllIIlllIlllIIllIIlllIIIllIllIIllIIlIIllIIlIIllIlIIlIIllIllIIIlIlllIlIIIlIIlIlIIllIIllIIllIIIlllIIIllIllIIlllIlllIIlIlIIIlllIllIIIlIIllIIlIllIlllIIIlIlllIlllIlIIlIIIllIIllIlllIIllIIllIlllIIllIIllIIlllIlll = illlIIlIIIlIIIllIIlIIllIIIlllIIlllIlllIIllIIlllIIIllIllIIllIIlIIllIIlIIllIlIIlIIllIllIIIlIlllIlIIIlIIlIlIIllIIllIIllIIIlllIIIllIllIIlllIlllIIlIlIIIlllIllIIIlIIllIIlIllIlllIIIlIlllIlllIlIIlIIIllIIllIlllIIllIIllIlllIIllIIllIIlllIlll;
    }
    
    @NotNull
    public final IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll IlIllIIIllIIIlIIIlIIlIIIlIIlllIIllIIllIlllIlIIlIllIIIlIIlIllIIllIllIIllIIllIIlllIllIllIIlllIIIlIIIllIIlIlIIIllIIIlIIlIllIIlIIllIIIllIIllIlIIlllIIlIIIlIllIIIlIIIllIIlllIIllIIlllIllIIIlllIIIlllIllIllIllIIlIIlllIIIlllIIlllIIIlllIllIIll() {
        return this.IllIlllIlllIlIIllIllIllIIIllIlIIIllIIllIlIllIIIlIIIllIlllIllIllIlllIIIlIIlIIIllIIlIIIlIllIIlIIllIllIIlIIllIlIIlIIllIlllIIlIlIIllIIlllIIllIIlllIIllIllIlllIIlllIIlllIIIllIIllIIlIIlIlIIllIIIlIlIIllIlllIIlIIIllIIIlIllIll;
    }
    
    @NotNull
    public final IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll IIllIIlIIlllIIIllIlllIIlllIIlIlIIlIlllIIlllIIIlIIlIIlIIlIIlllIIllIIlIIllIIlllIllIllIIlllIllIllIIlIIlllIlIIlIIllIIlIlIIlllIIllIIlllIIllIIllIIlIIlIIlIIlllIIIlllIIIllIIIllIlIlIlllIIlIIIllIIIlIllIlllIllIllIIIlllIlllIIllIIIlIIIlIIll() {
        return this.IlllIIlIIIlIIIllIIlIIllIIIlllIIlllIlllIIllIIlllIIIllIllIIllIIlIIllIIlIIllIlIIlIIllIllIIIlIlllIlIIIlIIlIlIIllIIllIIllIIIlllIIIllIllIIlllIlllIIlIlIIIlllIllIIIlIIllIIlIllIlllIIIlIlllIlllIlIIlIIIllIIllIlllIIllIIllIlllIIllIIllIIlllIlll;
    }
    
    @NotNull
    public final GuiSlot IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll() {
        final GuiSlot of = GuiSlot.of(ItemBuilder.of(XMaterial.EMERALD).name("&aRefresh").lore(new String[] { "&7This button will refresh the all player's view.", "&7In order to apply the changes you've made,", "&eWe do not recommend to use this during peak times.", "&eOr using too frequently.", " ", "&aClick to refresh player's view." }), IIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll::IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll);
        Intrinsics.checkNotNullExpressionValue((Object)of, "");
        return of;
    }
    
    @NotNull
    public final GuiSlot IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Gui gui, @NotNull final AntiXrayConfig antiXrayConfig, @NotNull final KMutableProperty0<Boolean> kMutableProperty0, @NotNull final ItemBuilder itemBuilder) {
        Intrinsics.checkNotNullParameter((Object)gui, "");
        Intrinsics.checkNotNullParameter((Object)antiXrayConfig, "");
        Intrinsics.checkNotNullParameter((Object)kMutableProperty0, "");
        Intrinsics.checkNotNullParameter((Object)itemBuilder, "");
        final GuiSlot of = GuiSlot.of(itemBuilder.lore(new String[] { "&7Current: &e" + (kMutableProperty0.get() ? "&a\u2714" : "&c\u2716"), "", "&aClick to toggle." }), IIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        Intrinsics.checkNotNullExpressionValue((Object)of, "");
        return of;
    }
    
    @NotNull
    public final GuiSlot IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final Gui gui, @NotNull final AntiXrayConfig antiXrayConfig, @NotNull final KMutableProperty0<Integer> kMutableProperty0, @NotNull final ItemBuilder itemBuilder) {
        Intrinsics.checkNotNullParameter((Object)gui, "");
        Intrinsics.checkNotNullParameter((Object)antiXrayConfig, "");
        Intrinsics.checkNotNullParameter((Object)kMutableProperty0, "");
        Intrinsics.checkNotNullParameter((Object)itemBuilder, "");
        final GuiSlot of = GuiSlot.of(itemBuilder.lore(new String[] { "&7Current: &e" + ((Number)kMutableProperty0.get()).intValue(), "", "&aLeft Click to increase by 1.", "&cRight Click to decrease by 1.", "&eHold Shift to increase/decrease by 10." }), IIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        Intrinsics.checkNotNullExpressionValue((Object)of, "");
        return of;
    }
    
    @NotNull
    public final GuiSlot IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(@NotNull final Gui gui, @NotNull final AntiXrayConfig antiXrayConfig, @NotNull final KMutableProperty0<Long> kMutableProperty0, @NotNull final ItemBuilder itemBuilder) {
        Intrinsics.checkNotNullParameter((Object)gui, "");
        Intrinsics.checkNotNullParameter((Object)antiXrayConfig, "");
        Intrinsics.checkNotNullParameter((Object)kMutableProperty0, "");
        Intrinsics.checkNotNullParameter((Object)itemBuilder, "");
        final GuiSlot of = GuiSlot.of(itemBuilder.lore(new String[] { "&7Current: &e" + ((Number)kMutableProperty0.get()).longValue(), "", "&aLeft Click to increase by 1.", "&cRight Click to decrease by 1.", "&eHold Shift to increase/decrease by 10." }), IIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll::IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll);
        Intrinsics.checkNotNullExpressionValue((Object)of, "");
        return of;
    }
    
    @NotNull
    public final GuiSlot IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(@NotNull final Gui gui, @NotNull final AntiXrayConfig antiXrayConfig, @NotNull final KMutableProperty0<Double> kMutableProperty0, @NotNull final ItemBuilder itemBuilder) {
        Intrinsics.checkNotNullParameter((Object)gui, "");
        Intrinsics.checkNotNullParameter((Object)antiXrayConfig, "");
        Intrinsics.checkNotNullParameter((Object)kMutableProperty0, "");
        Intrinsics.checkNotNullParameter((Object)itemBuilder, "");
        final GuiSlot of = GuiSlot.of(itemBuilder.lore(new String[] { "&7Current: &e" + ((Number)kMutableProperty0.get()).doubleValue(), "", "&aLeft Click to increase by 1.", "&cRight Click to decrease by 1.", "&eHold Shift to increase/decrease by 10." }), IIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll::IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll);
        Intrinsics.checkNotNullExpressionValue((Object)of, "");
        return of;
    }
    
    @Nullable
    public final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final ItemStack itemStack) {
        Intrinsics.checkNotNullParameter((Object)itemStack, "");
        if (!itemStack.getType().isBlock()) {
            return null;
        }
        try {
            final XMaterial matchXMaterial = XMaterial.matchXMaterial(itemStack);
            Intrinsics.checkNotNullExpressionValue((Object)matchXMaterial, "");
            final XMaterial xMaterial = matchXMaterial;
            if (!xMaterial.isSupported()) {
                return null;
            }
            if (!IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(this.IlllIIlIIIlIIIllIIlIIllIIIlllIIlllIlllIIllIIlllIIIllIllIIllIIlIIllIIlIIllIlIIlIIllIllIIIlIlllIlIIIlIIlIlIIllIIllIIllIIIlllIIIllIllIIlllIlllIIlIlIIIlllIllIIIlIIllIIlIllIlllIIIlIlllIlllIlIIlIIIllIIllIlllIIllIIllIlllIIllIIllIIlllIlll, xMaterial, false, 2, null)) {
                return null;
            }
            return new IIlIlllIIIllIIlllIIlIIllIIlIlIIlIIlIIlllIIlllIIIllIIlllIIllIIllIIlIIlIIlIIIlIIlllIIlllIlIIIlIllIIIlllIlIIIlIlIIIlllIIlIllIIllIIIlIIlllIIllIIIlIIllIIllIIlllIIllIIllIIllIIIllIIIllIIIlIIllIlllIlllIllIIlllIlIlllIIlIIlIlllIIlIIIllIIllIIl(xMaterial, this.IllIlllIlllIlIIllIllIllIIIllIlIIIllIIllIlIllIIIlIIIllIlllIllIllIlllIIIlIIlIIIllIIlIIIlIllIIlIIllIllIIlIIllIlIIlIIllIlllIIlIlIIllIIlllIIllIIlllIIllIllIlllIIlllIIlllIIIllIIllIIlIIlIlIIllIIIlIlIIllIlllIIlIIIllIIIlIllIll);
        }
        catch (final IllegalArgumentException ex) {
            if (AntiXrayPlugin.MODERN_BLOCK_DATA) {
                return IIllIllIIllIIlllIIlIIlIIIlIllIllIIlIIIlllIIllIIllIlllIIllIIlIIlllIlllIllIlllIIllIIlIlllIlllIlIlllIllIlllIIlIlllIIlIIlIIlllIIlllIlIlIIIllIlIllIllIIllIIIlllIIIllIllIIlIlIllIllIIIllIIIlIlIIlllIIIllIlIlIIlIlllIIlllIIllIIIl.IIllIllIIlllIIIlIIllIIllIIllIIlIllIIllIIllIIIllIIIllIIllIllIllIIIlllIIlIIIllIIllIIIllIIIllIIllIIIlIIIlIIlIllIIllIIllIlllIlllIllIIllIlllIIlllIIllIIllIIIlllIIlllIIIlllIIlllIIlIIIlIIllIlIIlIlIlIIlIllIlIIIlllIllIIlllIlllIlIIIllIIIlIIlIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(itemStack);
            }
            final Material type = itemStack.getType();
            Intrinsics.checkNotNullExpressionValue((Object)type, "");
            final Material material = type;
            if (!this.IlllIIlIIIlIIIllIIlIIllIIIlllIIlllIlllIIllIIlllIIIllIllIIllIIlIIllIIlIIllIlIIlIIllIllIIIlIlllIlIIIlIIlIlIIllIIllIIllIIIlllIIIllIllIIlllIlllIIlIlIIIlllIllIIIlIIllIIlIllIlllIIIlIlllIlllIlIIlIIIllIIllIlllIIllIIllIlllIIllIIllIIlllIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(material)) {
                return null;
            }
            return new IIlllIIIlIlllIIlIIllIllIlllIIllIlIllIlIIllIlllIIllIllIlllIlllIIllIIllIIIllIIIllIIIlIIllIllIIIllIIllIIlIlllIIlIIllIIIllIlIllIIIllIIIllIIllIlllIIIlIIllIIIlllIIllIIllIIIllIlIIIlllIIllIIllIIlllIIlllIlllIlIIllIlIIIllIIlllIllIllIIIllIIlllIlll(material, this.IllIlllIlllIlIIllIllIllIIIllIlIIIllIIllIlIllIIIlIIIllIlllIllIllIlllIIIlIIlIIIllIIlIIIlIllIIlIIllIllIIlIIllIlIIlIIllIlllIIlIlIIllIIlllIIllIIlllIIllIllIlllIIlllIIlllIIIllIIllIIlIIlIlIIllIIIlIlIIllIlllIIlIIIllIIIlIllIll);
        }
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final IIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll ilIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll, final Player player) {
        Intrinsics.checkNotNullParameter((Object)ilIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll, "");
        final IllIIllIIIllIIllIllIIllIIIlIIllIllIIIlIIIlIllIIIlllIllIllIlIIllIIlllIlllIIlIIllIlIIlIIllIllIIIlIIIllIlllIIllIllIIllIIIllIIllIIIlIIIlIIIlllIIIlIIlIlllIIllIIllIIIlIIlIIlllIlIIllIllIIIllIIlIIllIIlllIIIllIIIllIIIlllIIllIIIllIIIlIIlllIIlIIll iIlllIIllIllIllIIIlIIIlllIlIIlIllIlIIIlllIlIllIIIllIIIlllIIllIllIIllIIlIIIllIIIlIIlIIIllIlIIlIIlIIIllIIllIlIIllIIlIllIlIllIllIIllIIIllIIlIlIIllIllIIIllIIIlllIIIllIIIlIIllIIllIlIIllIIlIIlIllIIllIIIllIllIlllIlllIIlllIIIlIl = ilIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll.IIlllIIllIllIllIIIlIIIlllIlIIlIllIlIIIlllIlIllIIIllIIIlllIIllIllIIllIIlIIIllIIIlIIlIIIllIlIIlIIlIIIllIIllIlIIllIIlIllIlIllIllIIllIIIllIIlIlIIllIllIIIllIIIlllIIIllIIIlIIllIIllIlIIllIIlIIlIllIIllIIIllIllIlllIlllIIlllIIIlIl;
        Intrinsics.checkNotNull((Object)player);
        iIlllIIllIllIllIIIlIIIlllIlIIlIllIlIIIlllIlIllIIIllIIIlllIIllIllIIllIIlIIIllIIIlIIlIIIllIlIIlIIlIIIllIIllIlIIllIIlIllIlIllIllIIllIIIllIIlIlIIllIllIIIllIIIlllIIIllIIIlIIllIIllIlIIllIIlIIlIllIIllIIIllIllIlllIlllIIlllIIIlIl.get(player).reinitialize(20L);
        IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IllIIllIlIlIllIIllIIIllIIIllIIIllIIIlIlIlIllIlIIllIIlllIlllIIlIIIllIIlIllIIlllIIllIllIIIlIIlIlIlllIIIlllIIlIIIllIIIllIIIllIlIIlllIIlIlIIIlllIIlIlIIIlllIIlllIIlIllIllIIlIIIllIIllIlllIIllIIllIllIIllIIllIIlIIllIllIIllIIlIIl(player);
    }
    
    private static final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final IIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll ilIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll, final Player player) {
        Intrinsics.checkNotNullParameter((Object)ilIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll, "");
        final List worlds = Bukkit.getWorlds();
        Intrinsics.checkNotNullExpressionValue((Object)worlds, "");
        for (final World world : worlds) {
            Intrinsics.checkNotNull((Object)world);
            final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll illIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll = IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl.IllIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll(world);
            if (illIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll != null) {
                illIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll.IlIllIIlllIIlllIIlllIIIllIIIllIIlllIlIIllIllIIIllIlIIlllIlIIlIIllIllIlIIlIllIIIllIIllIIIllIllIlIIIlllIIIlIIlllIIlIIIlllIIIllIIIllIIlllIIllIIlIIIllIIllIIlllIIllIlIllIIllIIllIllIIIllIIllIIlIIIlIIlllIIIlIIlllIIlIIIllIlIlllIlIllIIlIIIll();
            }
        }
        for (final Player player2 : Bukkit.getOnlinePlayers()) {
            MCSchedulers.getEntityScheduler((Object)player2).schedule(IIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        }
        XSound.ENTITY_PLAYER_LEVELUP.play((Entity)player);
        player.sendMessage(CC.GREEN + "Updating all player's view... you may experience some lag.");
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final KMutableProperty0 kMutableProperty0, final AntiXrayConfig antiXrayConfig, final Gui gui, final Player player) {
        Intrinsics.checkNotNullParameter((Object)kMutableProperty0, "");
        Intrinsics.checkNotNullParameter((Object)antiXrayConfig, "");
        Intrinsics.checkNotNullParameter((Object)gui, "");
        kMutableProperty0.set((Object)!(boolean)kMutableProperty0.get());
        antiXrayConfig.save();
        gui.update(player);
        XSound.UI_BUTTON_CLICK.play((Entity)player);
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final KMutableProperty0 kMutableProperty0, final AntiXrayConfig antiXrayConfig, final Gui gui, final Player player, final ClickType clickType) {
        Intrinsics.checkNotNullParameter((Object)kMutableProperty0, "");
        Intrinsics.checkNotNullParameter((Object)antiXrayConfig, "");
        Intrinsics.checkNotNullParameter((Object)gui, "");
        final int intValue = ((Number)kMutableProperty0.get()).intValue();
        final int n = clickType.isShiftClick() ? 10 : 1;
        if (clickType.isLeftClick()) {
            kMutableProperty0.set((Object)(intValue + n));
        }
        else {
            kMutableProperty0.set((Object)(intValue - n));
        }
        antiXrayConfig.save();
        gui.update(player);
        XSound.UI_BUTTON_CLICK.play((Entity)player);
    }
    
    private static final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final KMutableProperty0 kMutableProperty0, final AntiXrayConfig antiXrayConfig, final Gui gui, final Player player, final ClickType clickType) {
        Intrinsics.checkNotNullParameter((Object)kMutableProperty0, "");
        Intrinsics.checkNotNullParameter((Object)antiXrayConfig, "");
        Intrinsics.checkNotNullParameter((Object)gui, "");
        final long longValue = ((Number)kMutableProperty0.get()).longValue();
        final int n = clickType.isShiftClick() ? 10 : 1;
        if (clickType.isLeftClick()) {
            kMutableProperty0.set((Object)(longValue + n));
        }
        else {
            kMutableProperty0.set((Object)(longValue - n));
        }
        antiXrayConfig.save();
        gui.update(player);
        XSound.UI_BUTTON_CLICK.play((Entity)player);
    }
    
    private static final void IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(final KMutableProperty0 kMutableProperty0, final AntiXrayConfig antiXrayConfig, final Gui gui, final Player player, final ClickType clickType) {
        Intrinsics.checkNotNullParameter((Object)kMutableProperty0, "");
        Intrinsics.checkNotNullParameter((Object)antiXrayConfig, "");
        Intrinsics.checkNotNullParameter((Object)gui, "");
        final double doubleValue = ((Number)kMutableProperty0.get()).doubleValue();
        final int n = clickType.isShiftClick() ? 10 : 1;
        if (clickType.isLeftClick()) {
            kMutableProperty0.set((Object)(doubleValue + n));
        }
        else {
            kMutableProperty0.set((Object)(doubleValue - n));
        }
        antiXrayConfig.save();
        gui.update(player);
        XSound.UI_BUTTON_CLICK.play((Entity)player);
    }
}
