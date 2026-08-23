// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import org.bukkit.Bukkit;
import org.bukkit.World;
import java.util.Locale;
import io.fairyproject.bukkit.gui.pane.PaginatedPane;
import dev.imanity.antixray.api.config.ConfigContextType;
import java.util.Iterator;
import io.fairyproject.bukkit.gui.pane.NormalPane;
import io.fairyproject.bukkit.gui.Gui;
import io.fairyproject.bukkit.gui.pane.mapping.PaneMapping;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import io.fairyproject.bukkit.gui.slot.GuiSlot;
import io.fairyproject.bukkit.util.items.ItemBuilder;
import io.fairyproject.libs.xseries.XMaterial;
import io.fairyproject.bukkit.gui.pane.Pane;
import io.fairyproject.libs.kyori.adventure.text.Component;
import dev.imanity.antixray.api.config.WorldFilteredConfig;
import kotlin.Unit;
import dev.imanity.antixray.api.config.ConfigContext;
import kotlin.jvm.functions.Function1;
import dev.imanity.antixray.api.config.AbstractConfigService;
import org.bukkit.entity.Player;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.bukkit.gui.GuiFactory;
import kotlin.jvm.internal.SourceDebugExtension;
import io.fairyproject.container.InjectableComponent;

@InjectableComponent
@SourceDebugExtension({ "SMAP\nSelectWorldGui.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SelectWorldGui.kt\ndev/imanity/antixray/core/gui/ores/SelectWorldGui\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,111:1\n1864#2,2:112\n1549#2:114\n1620#2,3:115\n1866#2:118\n1855#2,2:119\n*S KotlinDebug\n*F\n+ 1 SelectWorldGui.kt\ndev/imanity/antixray/core/gui/ores/SelectWorldGui\n*L\n34#1:112,2\n64#1:114\n64#1:115,3\n34#1:118\n89#1:119,2\n*E\n" })
public final class IlIIIlIllIIllIlIIlIIllIIlllIIIllIIllIIlIIlIIllIlIIllIlllIIllIllIIlIIlIIlIIlIIlllIlllIIllIIIlllIIIlIIlIIIllIllIIllIlIllIlllIIIlIllIIlIIIllIlllIlIIlIIIllIIlIIIllIIllIIllIIIllIIlIIllIIlIIllIIlllIIIllIIllIlIIlllIIlIIIlllIlIIIll
{
    @NotNull
    private final GuiFactory IIlIIllIIIlIIllIIIllIlllIlIllIlIIlIIlllIIlIllIllIIllIIllIlIIlllIIIlIllIllIIlllIIIllIIllIllIIllIIlIIlllIIlIlIIlllIlllIlllIlIIlIIlllIIlllIIlIIlIlIIlIIlIIIllIIIllIIllIIIllIIIlllIIllIlllIllIIIlllIIllIIllIIllIIllIIIllIIlllIIIllIlIIll;
    
    public IlIIIlIllIIllIlIIlIIllIIlllIIIllIIllIIlIIlIIllIlIIllIlllIIllIllIIlIIlIIlIIlIIlllIlllIIllIIIlllIIIlIIlIIIllIllIIllIlIllIlllIIIlIllIIlIIIllIlllIlIIlIIIllIIlIIIllIIllIIllIIIllIIlIIllIIlIIllIIlllIIIllIIllIlIIlllIIlIIIlllIlIIIll(@NotNull final GuiFactory iIlIIllIIIlIIllIIIllIlllIlIllIlIIlIIlllIIlIllIllIIllIIllIlIIlllIIIlIllIllIIlllIIIllIIllIllIIllIIlIIlllIIlIlIIlllIlllIlllIlIIlIIlllIIlllIIlIIlIlIIlIIlIIIllIIIllIIllIIIllIIIlllIIllIlllIllIIIlllIIllIIllIIllIIllIIIllIIlllIIIllIlIIll) {
        Intrinsics.checkNotNullParameter((Object)iIlIIllIIIlIIllIIIllIlllIlIllIlIIlIIlllIIlIllIllIIllIIllIlIIlllIIIlIllIllIIlllIIIllIIllIllIIllIIlIIlllIIlIlIIlllIlllIlllIlIIlIIlllIIlllIIlIIlIlIIlIIlIIIllIIIllIIllIIIllIIIlllIIllIlllIllIIIlllIIllIIllIIllIIllIIIllIIlllIIIllIlIIll, "");
        this.IIlIIllIIIlIIllIIIllIlllIlIllIlIIlIIlllIIlIllIllIIllIIllIlIIlllIIIlIllIllIIlllIIIllIIllIllIIllIIlIIlllIIlIlIIlllIlllIlllIlIIlIIlllIIlllIIlIIlIlIIlIIlIIIllIIIllIIllIIIllIIIlllIIllIlllIllIIIlllIIllIIllIIllIIllIIIllIIlllIIIllIlIIll = iIlIIllIIIlIIllIIIllIlllIlIllIlIIlIIlllIIlIllIllIIllIIllIlIIlllIIIlIllIllIIlllIIIllIIllIllIIllIIlIIlllIIlIlIIlllIlllIlllIlIIlIIlllIIlllIIlIIlIlIIlIIlIIIllIIIllIIllIIIllIIIlllIIllIlllIllIIIlllIIllIIllIIllIIllIIIllIIlllIIIllIlIIll;
    }
    
    public final <T extends WorldFilteredConfig> void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Player player, @NotNull final AbstractConfigService<T> abstractConfigService, @NotNull final Function1<? super ConfigContext<T>, Unit> function1) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)abstractConfigService, "");
        Intrinsics.checkNotNullParameter((Object)function1, "");
        final Gui create;
        final Gui gui = create = this.IIlIIllIIIlIIllIIIllIlllIlIllIlIIlIIlllIIlIllIllIIllIIllIlIIlllIIIlIllIllIIlllIIIllIIllIllIIllIIlIIlllIIlIlIIlllIlllIlllIlIIlIIlllIIlllIIlIIlIlIIlIIlIIIllIIIllIIllIIIllIIIlllIIllIlllIllIIIlllIIllIIllIIllIIllIIIllIIlllIIIllIlIIll.create((Component)Component.text("Select World Config"));
        final NormalPane normal = Pane.normal(2);
        final Gui gui2 = create;
        normal.setSlot(0, 0, GuiSlot.of(ItemBuilder.of(XMaterial.OAK_DOOR).name("&eBack").lore(new String[] { "&7Click here to go back to the main menu." }), IlIIIlIllIIllIlIIlIIllIIlllIIIllIIllIIlIIlIIllIlIIllIlllIIllIllIIlIIlIIlIIlIIlllIlllIIllIIIlllIIIlIIlIIIllIllIIllIlIllIlllIIIlIllIIlIIIllIlllIlIIlIIIllIIlIIIllIIllIIllIIIllIIlIIllIIlIIllIIlllIIIllIIllIlIIlllIIlIIIlllIlIIIll::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll));
        final Iterable iterable = abstractConfigService.getOrderedConfigs();
        int n = 0;
        for (final Object next : iterable) {
            final int n2 = n++;
            if (n2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            final int n3 = n2;
            final WorldFilteredConfig worldFilteredConfig = (T)next;
            final int n4 = n3;
            final ConfigContextType configContextType = abstractConfigService.getConfigContextType((T)worldFilteredConfig);
            final NormalPane normalPane = normal;
            final int n5 = n4 + 1;
            final int n6 = 1;
            final ItemBuilder of = ItemBuilder.of(switch (IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.IIlIIlllIIlIlllIIllIIlIllIllIIlllIlIIIllIIIlIlIllIllIIIlIIlIIIlIlIIIllIllIlllIllIIIlIIIlIIlIIIlIIIlIIlIllIlllIIlIIlllIlIlIIIllIIIlIIIllIIllIIllIlIIlllIIlIlIIlIIIllIIIlIIllIllIllIIIlllIIIllIIIllIllIIlllIIlllIIlIIIlllIIIlIIIl[configContextType.ordinal()]) {
                case 1 -> XMaterial.GRASS_BLOCK;
                case 2 -> XMaterial.NETHERRACK;
                case 3 -> XMaterial.END_STONE;
                case 4 -> XMaterial.GRASS_BLOCK;
                case 5 -> XMaterial.STONE;
                default -> throw new NoWhenBranchMatchedException();
            });
            final ItemBuilder name = of.name(switch (IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.IIlIIlllIIlIlllIIllIIlIllIllIIlllIlIIIllIIIlIlIllIllIIIlIIlIIIlIlIIIllIllIlllIllIIIlIIIlIIlIIIlIIIlIIlIllIlllIIlIIlllIlIlIIIllIIIlIIIllIIllIIllIlIIlllIIlIlIIlIIIllIIIlIIllIllIllIIIlllIIIllIIIllIllIIlllIIlllIIlIIIlllIIIlIIIl[configContextType.ordinal()]) {
                case 1 -> "&aOverworld";
                case 2 -> "&cNether";
                case 3 -> "&dEnd";
                case 4 -> "&bDefault";
                case 5 -> "&eCustom";
                default -> throw new NoWhenBranchMatchedException();
            });
            normalPane.setSlot(n5, n6, GuiSlot.of(name.lore((Iterable)switch (IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.IIlIIlllIIlIlllIIllIIlIllIllIIlllIlIIIllIIIlIlIllIllIIIlIIlIIIlIlIIIllIllIlllIllIIIlIIIlIIlIIIlIIIlIIlIllIlllIIlIIlllIlIlIIIllIIIlIIIllIIllIIllIlIIlllIIlIlIIlIIIllIIIlIIllIllIllIIIlllIIIllIIIllIllIIlllIIlllIIlIIIlllIIIlIIIl[configContextType.ordinal()]) {
                case 1 -> CollectionsKt.listOf((Object)"&7Click to edit the default overworld config.");
                case 2 -> CollectionsKt.listOf((Object)"&7Click to edit the default nether config.");
                case 3 -> CollectionsKt.listOf((Object)"&7Click to edit the default end config.");
                case 4 -> CollectionsKt.listOf((Object)"&7Click to edit the default config.");
                case 5 -> {
                    if (worldFilteredConfig.isSingleNameFilter()) {
                        yield CollectionsKt.listOf((Object)("&7Click to edit the config of the world \"" + worldFilteredConfig.getWorldFilters().get(0) + "\"."));
                    }
                    final List[] array2;
                    final List[] array = array2 = new List[] { CollectionsKt.listOf((Object)"&7Conditions:"), null, null };
                    final int n7 = 1;
                    final Iterable iterable2 = worldFilteredConfig.getWorldFilters();
                    final int n8 = n7;
                    final List[] array3 = array2;
                    final Iterable iterable3 = iterable2;
                    final Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                    final Iterator iterator2 = iterable3.iterator();
                    while (iterator2.hasNext()) {
                        collection.add("&7- &f" + (String)iterator2.next());
                    }
                    array3[n8] = (List)collection;
                    array[2] = CollectionsKt.listOf((Object[])new String[] { " ", "&7Click to edit the custom config." });
                    yield CollectionsKt.flatten((Iterable)CollectionsKt.listOf((Object[])array));
                }
                default -> throw new NoWhenBranchMatchedException();
            }), IlIIIlIllIIllIlIIlIIllIIlllIIIllIIllIIlIIlIIllIlIIllIlllIIllIllIIlIIlIIlIIlIIlllIlllIIllIIIlllIIIlIIlIIIllIllIIllIlIllIlllIIIlIllIIlIIIllIlllIlIIlIIIllIIlIIIllIIllIIllIIIllIIlIIllIIlIIllIIlllIIIllIIllIlIIlllIIlIIIlllIlIIIll::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll));
        }
        normal.fillEmptySlots(GuiSlot.of(ItemBuilder.of(XMaterial.GRAY_STAINED_GLASS_PANE).name(" ")));
        gui2.addPane((Pane)normal);
        final Gui gui3 = gui;
        final NormalPane normal2 = Pane.normal(PaneMapping.outline(0, 2, 9, 4));
        final Gui gui4 = gui3;
        normal2.fillEmptySlots(GuiSlot.of(ItemBuilder.of(XMaterial.GRAY_STAINED_GLASS_PANE).name(" ")));
        gui4.addPane((Pane)normal2);
        final Gui gui5 = gui;
        final PaginatedPane paginated = Pane.paginated(1, 3, 7, 2);
        final Gui gui6 = gui5;
        gui.onDrawCallback(IlIIIlIllIIllIlIIlIIllIIlllIIIllIIllIIlIIlIIllIlIIllIlllIIllIllIIlIIlIIlIIlIIlllIlllIIllIIIlllIIIlIIlIIIllIllIIllIlIllIlllIIIlIllIIlIIIllIlllIlIIlIIIllIIlIIIllIIllIIllIIIllIIlIIllIIlIIllIIlllIIIllIIllIlIIlllIIlIIIlllIlIIIll::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        gui6.addPane((Pane)paginated);
        gui.open(player);
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final Function1 function1, final Player player) {
        Intrinsics.checkNotNullParameter((Object)function1, "");
        function1.invoke((Object)null);
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final Function1 function1, final ConfigContextType configContextType, final WorldFilteredConfig worldFilteredConfig, final Player player) {
        Intrinsics.checkNotNullParameter((Object)function1, "");
        Intrinsics.checkNotNullParameter((Object)configContextType, "");
        Intrinsics.checkNotNullParameter((Object)worldFilteredConfig, "");
        final String lowerCase = worldFilteredConfig.getType().name().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue((Object)lowerCase, "");
        function1.invoke((Object)new ConfigContext(configContextType, lowerCase, worldFilteredConfig));
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final Function1 function1, final World world, final AbstractConfigService abstractConfigService, final Player player) {
        Intrinsics.checkNotNullParameter((Object)function1, "");
        Intrinsics.checkNotNullParameter((Object)abstractConfigService, "");
        final ConfigContextType custom = ConfigContextType.CUSTOM;
        final String name = world.getName();
        Intrinsics.checkNotNullExpressionValue((Object)name, "");
        Intrinsics.checkNotNull((Object)world);
        function1.invoke((Object)new ConfigContext(custom, name, abstractConfigService.getOrCreateCustomConfig(world)));
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final PaginatedPane paginatedPane, final Function1 function1, final AbstractConfigService abstractConfigService, final Player player) {
        Intrinsics.checkNotNullParameter((Object)function1, "");
        Intrinsics.checkNotNullParameter((Object)abstractConfigService, "");
        paginatedPane.clear();
        final List worlds = Bukkit.getWorlds();
        Intrinsics.checkNotNullExpressionValue((Object)worlds, "");
        for (final World world : worlds) {
            paginatedPane.addSlot(GuiSlot.of(ItemBuilder.of(XMaterial.PAPER).name("&a" + world.getName()).lore(new String[] { "&7Click to edit the config of this world." }), IlIIIlIllIIllIlIIlIIllIIlllIIIllIIllIIlIIlIIllIlIIllIlllIIllIllIIlIIlIIlIIlIIlllIlllIIllIIIlllIIIlIIlIIIllIllIIllIlIllIlllIIIlIllIIlIIIllIlllIlIIlIIIllIIlIIIllIIllIIllIIIllIIlIIllIIlIIllIIlllIIIllIIllIlIIlllIIlIIIlllIlIIIll::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll));
        }
    }
}
