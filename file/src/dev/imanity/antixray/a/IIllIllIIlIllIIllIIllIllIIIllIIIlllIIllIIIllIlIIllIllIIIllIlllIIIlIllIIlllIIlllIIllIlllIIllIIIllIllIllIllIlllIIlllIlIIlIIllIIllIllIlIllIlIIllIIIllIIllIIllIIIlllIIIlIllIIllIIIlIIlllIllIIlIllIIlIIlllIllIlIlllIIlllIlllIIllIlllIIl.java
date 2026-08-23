// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import org.bukkit.event.inventory.ClickType;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import kotlin.reflect.KMutableProperty0;
import java.util.function.Consumer;
import dev.imanity.antixray.config.FakeConfig;
import io.fairyproject.mc.scheduler.MCSchedulers;
import java.util.List;
import kotlin.collections.CollectionsKt;
import java.util.Collection;
import io.fairyproject.util.CC;
import org.bukkit.entity.Entity;
import io.fairyproject.libs.xseries.XSound;
import org.bukkit.inventory.ItemStack;
import java.util.Iterator;
import dev.imanity.antixray.config.MinableFeature;
import io.fairyproject.bukkit.gui.slot.ItemSelectorGuiSlot;
import io.fairyproject.bukkit.util.items.ItemBuilder;
import io.fairyproject.libs.xseries.XMaterial;
import io.fairyproject.bukkit.gui.slot.GuiSlot;
import io.fairyproject.bukkit.gui.pane.NormalPane;
import io.fairyproject.bukkit.gui.pane.PaginatedPane;
import io.fairyproject.bukkit.gui.pane.mapping.PaneMapping;
import io.fairyproject.bukkit.gui.pane.Pane;
import io.fairyproject.libs.kyori.adventure.text.Component;
import io.fairyproject.bukkit.gui.Gui;
import dev.imanity.antixray.config.AntiXrayConfig;
import dev.imanity.antixray.api.config.ConfigContext;
import org.bukkit.entity.Player;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.bukkit.gui.GuiFactory;
import kotlin.jvm.internal.SourceDebugExtension;
import io.fairyproject.container.InjectableComponent;

@InjectableComponent
@SourceDebugExtension({ "SMAP\nFakeBlockGui.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FakeBlockGui.kt\ndev/imanity/antixray/core/gui/ores/FakeBlockGui\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,295:1\n1855#2,2:296\n1747#2,3:298\n1747#2,3:301\n*S KotlinDebug\n*F\n+ 1 FakeBlockGui.kt\ndev/imanity/antixray/core/gui/ores/FakeBlockGui\n*L\n41#1:296,2\n112#1:298,3\n152#1:301,3\n*E\n" })
public final class IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl extends IIlIllIIlllIIllIIIlllIIIllIIllIIIlIllIIIllIlIIIlIIIlIlllIIllIIIllIIlIlIIlIlllIIIlllIIlIlllIIIllIIlllIIllIllIIIlIIlIIIllIllIlllIIllIIlIIllIlIIIllIIlIIllIIlIIIlIIlllIIIlllIlIIlIIIlIlIIllIllIIllIIlIIllIIllIIIlIIllIIllIlllIIlIIllIIIlll
{
    @NotNull
    private final GuiFactory IIlllIIllIlIIlIIlIIllIllIllIIIllIllIIlllIIIllIllIIIlllIIlllIIlllIIIlIIllIIIlllIIllIlIIllIIlllIlllIIIlIIIllIlllIIlIIIllIIlIIllIIlIlIIlIIIlIIlIIIlIlllIIlllIIlIlllIIlIIlllIIIllIIllIIIlIIIlIIlllIIIllIIIlllIIllIIllIIIllIIlIIllIIllIIllIllIllIIIll;
    @NotNull
    private final IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl IllIlIllIIllIlllIlIlIlllIlIlIIlIlIIlllIllIIlllIIIllIlllIIIllIIIlIIllIIlIlIIlIlllIlIIIllIIlIIIllIIIllIlIlIlIIlllIIIlIlllIIlIlIIllIIlllIIllIllIllIIllIIllIllIlIlIllIIllIIIlllIllIIlllIIlIIlIIlIllIIllIIIllIIlIIll;
    
    public IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl(@NotNull final GuiFactory iIlllIIllIlIIlIIlIIllIllIllIIIllIllIIlllIIIllIllIIIlllIIlllIIlllIIIlIIllIIIlllIIllIlIIllIIlllIlllIIIlIIIllIlllIIlIIIllIIlIIllIIlIlIIlIIIlIIlIIIlIlllIIlllIIlIlllIIlIIlllIIIllIIllIIIlIIIlIIlllIIIllIIIlllIIllIIllIIIllIIlIIllIIllIIllIllIllIIIll, @NotNull final IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl illIlIllIIllIlllIlIlIlllIlIlIIlIlIIlllIllIIlllIIIllIlllIIIllIIIlIIllIIlIlIIlIlllIlIIIllIIlIIIllIIIllIlIlIlIIlllIIIlIlllIIlIlIIllIIlllIIllIllIllIIllIIllIllIlIlIllIIllIIIlllIllIIlllIIlIIlIIlIllIIllIIIllIIlIIll, @NotNull final IllIIllIIIllIIllIllIIllIIIlIIllIllIIIlIIIlIllIIIlllIllIllIlIIllIIlllIlllIIlIIllIlIIlIIllIllIIIlIIIllIlllIIllIllIIllIIIllIIllIIIlIIIlIIIlllIIIlIIlIlllIIllIIllIIIlIIlIIlllIlIIllIllIIIllIIlIIllIIlllIIIllIIIllIIIlllIIllIIIllIIIlIIlllIIlIIll illIIllIIIllIIllIllIIllIIIlIIllIllIIIlIIIlIllIIIlllIllIllIlIIllIIlllIlllIIlIIllIlIIlIIllIllIIIlIIIllIlllIIllIllIIllIIIllIIllIIIlIIIlIIIlllIIIlIIlIlllIIllIIllIIIlIIlIIlllIlIIllIllIIIllIIlIIllIIlllIIIllIIIllIIIlllIIllIIIllIIIlIIlllIIlIIll, @NotNull final IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll iiIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll, @NotNull final IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll ilIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll) {
        Intrinsics.checkNotNullParameter((Object)iIlllIIllIlIIlIIlIIllIllIllIIIllIllIIlllIIIllIllIIIlllIIlllIIlllIIIlIIllIIIlllIIllIlIIllIIlllIlllIIIlIIIllIlllIIlIIIllIIlIIllIIlIlIIlIIIlIIlIIIlIlllIIlllIIlIlllIIlIIlllIIIllIIllIIIlIIIlIIlllIIIllIIIlllIIllIIllIIIllIIlIIllIIllIIllIllIllIIIll, "");
        Intrinsics.checkNotNullParameter((Object)illIlIllIIllIlllIlIlIlllIlIlIIlIlIIlllIllIIlllIIIllIlllIIIllIIIlIIllIIlIlIIlIlllIlIIIllIIlIIIllIIIllIlIlIlIIlllIIIlIlllIIlIlIIllIIlllIIllIllIllIIllIIllIllIlIlIllIIllIIIlllIllIIlllIIlIIlIIlIllIIllIIIllIIlIIll, "");
        Intrinsics.checkNotNullParameter((Object)illIIllIIIllIIllIllIIllIIIlIIllIllIIIlIIIlIllIIIlllIllIllIlIIllIIlllIlllIIlIIllIlIIlIIllIllIIIlIIIllIlllIIllIllIIllIIIllIIllIIIlIIIlIIIlllIIIlIIlIlllIIllIIllIIIlIIlIIlllIlIIllIllIIIllIIlIIllIIlllIIIllIIIllIIIlllIIllIIIllIIIlIIlllIIlIIll, "");
        Intrinsics.checkNotNullParameter((Object)iiIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll, "");
        Intrinsics.checkNotNullParameter((Object)ilIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll, "");
        super(illIIllIIIllIIllIllIIllIIIlIIllIllIIIlIIIlIllIIIlllIllIllIlIIllIIlllIlllIIlIIllIlIIlIIllIllIIIlIIIllIlllIIllIllIIllIIIllIIllIIIlIIIlIIIlllIIIlIIlIlllIIllIIllIIIlIIlIIlllIlIIllIllIIIllIIlIIllIIlllIIIllIIIllIIIlllIIllIIIllIIIlIIlllIIlIIll, iiIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll, ilIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll);
        this.IIlllIIllIlIIlIIlIIllIllIllIIIllIllIIlllIIIllIllIIIlllIIlllIIlllIIIlIIllIIIlllIIllIlIIllIIlllIlllIIIlIIIllIlllIIlIIIllIIlIIllIIlIlIIlIIIlIIlIIIlIlllIIlllIIlIlllIIlIIlllIIIllIIllIIIlIIIlIIlllIIIllIIIlllIIllIIllIIIllIIlIIllIIllIIllIllIllIIIll = iIlllIIllIlIIlIIlIIllIllIllIIIllIllIIlllIIIllIllIIIlllIIlllIIlllIIIlIIllIIIlllIIllIlIIllIIlllIlllIIIlIIIllIlllIIlIIIllIIlIIllIIlIlIIlIIIlIIlIIIlIlllIIlllIIlIlllIIlIIlllIIIllIIllIIIlIIIlIIlllIIIllIIIlllIIllIIllIIIllIIlIIllIIllIIllIllIllIIIll;
        this.IllIlIllIIllIlllIlIlIlllIlIlIIlIlIIlllIllIIlllIIIllIlllIIIllIIIlIIllIIlIlIIlIlllIlIIIllIIlIIIllIIIllIlIlIlIIlllIIIlIlllIIlIlIIllIIlllIIllIllIllIIllIIllIllIlIlIllIIllIIIlllIllIIlllIIlIIlIIlIllIIllIIIllIIlIIll = illIlIllIIllIlllIlIlIlllIlIlIIlIlIIlllIllIIlllIIIllIlllIIIllIIIlIIllIIlIlIIlIlllIlIIIllIIlIIIllIIIllIlIlIlIIlllIIIlIlllIIlIlIIllIIlllIIllIllIllIIllIIllIllIlIlIllIIllIIIlllIllIIlllIIlIIlIIlIllIIllIIIllIIlIIll;
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Player player, @NotNull final ConfigContext<AntiXrayConfig> configContext, @NotNull final Gui gui) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)configContext, "");
        Intrinsics.checkNotNullParameter((Object)gui, "");
        final AntiXrayConfig antiXrayConfig = (AntiXrayConfig)configContext.getConfig();
        final Gui create = this.IIlllIIllIlIIlIIlIIllIllIllIIIllIllIIlllIIIllIllIIIlllIIlllIIlllIIIlIIllIIIlllIIllIlIIllIIlllIlllIIIlIIIllIlllIIlIIIllIIlIIllIIlIlIIlIIIlIIlIIIlIlllIIlllIIlIlllIIlIIlllIIIllIIllIIIlIIIlIIlllIIIllIIIlllIIllIIllIIIllIIlIIllIIllIIllIllIllIIIll.create((Component)Component.text("Fake Blocks " + configContext.getTitle()));
        final PaginatedPane paginated = Pane.paginated(1, 1, 7, 3);
        create.onDrawCallback(IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        create.addPane((Pane)paginated);
        final PaginatedPane paginatedPane = paginated;
        final NormalPane normal = Pane.normal(PaneMapping.outline(5));
        create.onDrawCallback(IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        create.addPane((Pane)normal);
        create.open(player);
    }
    
    private final GuiSlot IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final Player player, final Gui gui, final Pane pane, final AntiXrayConfig antiXrayConfig) {
        final ItemSelectorGuiSlot build = GuiSlot.itemSelector(pane, 4, 4).emptyItem(ItemBuilder.of(XMaterial.LIME_DYE).name("&aAdd Block").lore(new String[] { "&7Add a new block to the fake list.", " ", "&aDrag and drop a block to this slot." }).build()).canPlacePredicate(IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll).build();
        Intrinsics.checkNotNullExpressionValue((Object)build, "");
        return (GuiSlot)build;
    }
    
    private final GuiSlot IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final Gui gui, final AntiXrayConfig antiXrayConfig) {
        final GuiSlot of = GuiSlot.of(ItemBuilder.of(XMaterial.BARRIER).name("&bAdd Air").lore(new String[] { "&7Add a fake air block to the list.", "&7Fake air carves out illusory caves/holes", "&7to mislead cave-finder & ESP cheats.", " ", "&aClick to add a fake air block." }), IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        Intrinsics.checkNotNullExpressionValue((Object)of, "");
        return of;
    }
    
    private final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final Player player, final AntiXrayConfig antiXrayConfig, final MinableFeature minableFeature, final Gui gui) {
        final Gui create = this.IIlllIIllIlIIlIIlIIllIllIllIIIllIllIIlllIIIllIllIIIlllIIlllIIlllIIIlIIllIIIlllIIllIlIIllIIlllIlllIIIlIIIllIlllIIlIIIllIIlIIllIIlIlIIlIIIlIIlIIIlIlllIIlllIIlIlllIIlIIlllIIIllIIllIIIlIIIlIIlllIIIllIIIlllIIllIIllIIIllIIlIIllIIllIIllIllIllIIIll.create((Component)Component.text("Edit Minable Feature"));
        final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl blockMaterial = minableFeature.getBlockMaterial();
        final Gui gui2 = create;
        final NormalPane normal = Pane.normal(PaneMapping.outline(3));
        final Gui gui3 = gui2;
        normal.setSlot(0, 0, GuiSlot.of(ItemBuilder.of(XMaterial.OAK_DOOR).name("&eBack").lore(new String[] { "&7Click here to go back to the main menu." }), IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl::IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll));
        create.onDrawCallback(IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        normal.fillEmptySlots(GuiSlot.of(ItemBuilder.of(XMaterial.GRAY_STAINED_GLASS_PANE).name(" ")));
        gui3.addPane((Pane)normal);
        final Gui gui4 = create;
        final NormalPane normal2 = Pane.normal(1, 1, 7, 1);
        final Gui gui5 = gui4;
        create.onDrawCallback(IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        gui5.addPane((Pane)normal2);
        create.open(player);
    }
    
    private final GuiSlot IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final Gui gui, final AntiXrayConfig antiXrayConfig, final MinableFeature minableFeature) {
        final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl blockMaterial = minableFeature.getBlockMaterial();
        Intrinsics.checkNotNull((Object)blockMaterial);
        final GuiSlot of = GuiSlot.of(ItemBuilder.of(blockMaterial.getDisplayItem()).amount(minableFeature.getVeinCount()).lore(new String[] { "&7Vein Count: &b" + minableFeature.getVeinCount() + "/chunk", "&7Vein Size: &a" + minableFeature.getVeinSize() + "/vein", "&7Height Range: &ey>=" + minableFeature.getMinHeight() + " &7| &ey<=" + minableFeature.getMaxHeight(), " ", "&cClick to edit this block.", "&cShift + Click to remove this block from the list." }).build(), IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        Intrinsics.checkNotNullExpressionValue((Object)of, "");
        return of;
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final PaginatedPane paginatedPane, final AntiXrayConfig antiXrayConfig, final IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl, final Gui gui, final Player player) {
        Intrinsics.checkNotNullParameter((Object)antiXrayConfig, "");
        Intrinsics.checkNotNullParameter((Object)illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl, "");
        paginatedPane.clear();
        for (final MinableFeature minableFeature : antiXrayConfig.getFakeConfig().getOres()) {
            Intrinsics.checkNotNull((Object)gui);
            paginatedPane.addSlot(illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(gui, antiXrayConfig, minableFeature));
        }
    }
    
    private static final void IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(final Gui gui, final Player player) {
        Intrinsics.checkNotNullParameter((Object)gui, "");
        gui.open(player);
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final NormalPane normalPane, final PaginatedPane paginatedPane, final IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl, final Gui gui, final AntiXrayConfig antiXrayConfig, final Player player, final Gui gui2, final Player player2) {
        Intrinsics.checkNotNullParameter((Object)illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl, "");
        Intrinsics.checkNotNullParameter((Object)antiXrayConfig, "");
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)gui2, "");
        normalPane.setSlot(0, 0, GuiSlot.of(ItemBuilder.of(XMaterial.OAK_DOOR).name("&eBack").lore(new String[] { "&7Click here to go back to the main menu." }), IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl::IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll));
        normalPane.setSlot(4, 0, GuiSlot.of(ItemBuilder.of(XMaterial.OAK_SIGN).name("&eOres to fake").lore(new String[] { "&7Below are the ores that will be faked to players.", " ", "&7Page: &e" + (paginatedPane.getPage() + 1) + '/' + paginatedPane.getMaxPage() }).amount(paginatedPane.getPage() + 1)));
        normalPane.setSlot(8, 0, illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl.IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll());
        normalPane.setSlot(0, 4, GuiSlot.previousPage(paginatedPane));
        final int n = 2;
        final int n2 = 4;
        Intrinsics.checkNotNull((Object)gui);
        normalPane.setSlot(n, n2, illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(gui, antiXrayConfig));
        final int n3 = 4;
        final int n4 = 4;
        Intrinsics.checkNotNull((Object)normalPane);
        normalPane.setSlot(n3, n4, illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(player, gui, (Pane)normalPane, antiXrayConfig));
        normalPane.setSlot(8, 4, GuiSlot.nextPage(paginatedPane));
        normalPane.fillEmptySlots(GuiSlot.of(ItemBuilder.of(XMaterial.RED_STAINED_GLASS_PANE).name(" ")));
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl, final Player player, final AntiXrayConfig antiXrayConfig, final MinableFeature minableFeature, final Gui gui) {
        Intrinsics.checkNotNullParameter((Object)illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl, "");
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)antiXrayConfig, "");
        Intrinsics.checkNotNullParameter((Object)minableFeature, "");
        Intrinsics.checkNotNullParameter((Object)gui, "");
        illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(player, antiXrayConfig, minableFeature, gui);
    }
    
    private static final boolean IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final Player player, final IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl, final AntiXrayConfig antiXrayConfig, final Gui gui, final ItemStack itemStack) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl, "");
        Intrinsics.checkNotNullParameter((Object)antiXrayConfig, "");
        Intrinsics.checkNotNullParameter((Object)gui, "");
        if (!itemStack.getType().isBlock()) {
            XSound.ENTITY_ENDERMAN_TELEPORT.play((Entity)player);
            player.sendMessage(CC.RED + "This item is not a block.");
            return false;
        }
        Intrinsics.checkNotNull((Object)itemStack);
        final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl iIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll = illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(itemStack);
        if (iIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll == null) {
            player.sendMessage(CC.translate("&cThis item is not a valid to be block to hide."));
            XSound.ENTITY_ENDERMAN_HURT.play((Entity)player);
            return false;
        }
        final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl ilIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl = iIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll;
        final Iterable iterable = antiXrayConfig.getFakeConfig().getOres();
        boolean b = false;
        Label_0220: {
            if (iterable instanceof Collection && ((Collection)iterable).isEmpty()) {
                b = false;
            }
            else {
                final Iterator iterator = iterable.iterator();
                while (iterator.hasNext()) {
                    final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl blockMaterial = ((MinableFeature)iterator.next()).getBlockMaterial();
                    Intrinsics.checkNotNull((Object)blockMaterial);
                    if (Intrinsics.areEqual((Object)blockMaterial, (Object)ilIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl)) {
                        b = true;
                        break Label_0220;
                    }
                }
                b = false;
            }
        }
        if (b) {
            XSound.ENTITY_ENDERMAN_TELEPORT.play((Entity)player);
            player.sendMessage(CC.RED + "This block is already in the list.");
            return false;
        }
        final MinableFeature minableFeature = new MinableFeature(illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl.IllIlIllIIllIlllIlIlIlllIlIlIIlIlIIlllIllIIlllIIIllIlllIIIllIIIlIIllIIlIlIIlIlllIlIIIllIIlIIIllIIIllIlIlIlIIlllIIIlIlllIIlIlIIllIIlllIIllIllIllIIllIIllIllIlIlIllIIllIIIlllIllIIlllIIlIIlIIlIllIIllIIIllIIlIIll.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(ilIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl), illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl.IllIlIllIIllIlllIlIlIlllIlIlIIlIlIIlllIllIIlllIIIllIlllIIIllIIIlIIllIIlIlIIlIlllIlIIIllIIlIIIllIIIllIlIlIlIIlllIIIlIlllIIlIlIIllIIlllIIllIllIllIIllIIllIllIlIlIllIIllIIIlllIllIIlllIIlIIlIIlIllIIllIIIllIIlIIll.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl.IllIIllIlllIlllIllIIlllIIllIIlIIlllIIlIIlIIIllIlllIllIIIllIllIIlllIIlIIlIIlIIlllIIIlIlIIlllIlllIllIIllIIlllIIIlllIIlllIIlIllIlllIlllIIllIIIllIIllIIlIIIllIIIlllIlllIIlIIlIlllIIIllIIIllIIIlllIlIIIlllIIllIIllIIlllIIIlIIlIllIIIlIlIIllIlIll.IllIllIIllIIlllIlllIIIlIIllIlIIIlIlllIlIllIIIllIlIlIlllIllIlllIIllIIlIIllIIllIIlIIlIIlIIllIllIIllIIlIIllIIlllIIllIIIlIIlIllIIIllIllIlIIIllIIllIIlllIlllIIllIIIlIllIlIllIIlllIIIllIlllIIllIllIlIlllIIlllIllIIlIIIllIllIIIl()), 1, 1, 0, 256);
        final FakeConfig fakeConfig = antiXrayConfig.getFakeConfig();
        fakeConfig.setOres(CollectionsKt.plus((Collection)fakeConfig.getOres(), (Object)minableFeature));
        antiXrayConfig.save();
        MCSchedulers.getGlobalScheduler().schedule(IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll, 2L);
        return false;
    }
    
    private static final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl, final Player player, final AntiXrayConfig antiXrayConfig, final MinableFeature minableFeature, final Gui gui) {
        Intrinsics.checkNotNullParameter((Object)illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl, "");
        Intrinsics.checkNotNullParameter((Object)antiXrayConfig, "");
        Intrinsics.checkNotNullParameter((Object)minableFeature, "");
        Intrinsics.checkNotNullParameter((Object)gui, "");
        Intrinsics.checkNotNull((Object)player);
        illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(player, antiXrayConfig, minableFeature, gui);
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl, final AntiXrayConfig antiXrayConfig, final Gui gui, final Player player) {
        Intrinsics.checkNotNullParameter((Object)illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl, "");
        Intrinsics.checkNotNullParameter((Object)antiXrayConfig, "");
        Intrinsics.checkNotNullParameter((Object)gui, "");
        final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll = illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl.IllIlIllIIllIlllIlIlIlllIlIlIIlIlIIlllIllIIlllIIIllIlllIIIllIIIlIIllIIlIlIIlIlllIlIIIllIIlIIIllIIIllIlIlIlIIlllIIIlIlllIIlIlIIllIIlllIIllIllIllIIllIIllIllIlIlIllIIllIIIlllIllIIlllIIlIIlIIlIllIIllIIIllIIlIIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll("AIR");
        final Iterable iterable = antiXrayConfig.getFakeConfig().getOres();
        boolean b = false;
        Label_0126: {
            if (iterable instanceof Collection && ((Collection)iterable).isEmpty()) {
                b = false;
            }
            else {
                final Iterator iterator = iterable.iterator();
                while (iterator.hasNext()) {
                    if (Intrinsics.areEqual((Object)((MinableFeature)iterator.next()).getBlockMaterial(), (Object)iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll)) {
                        b = true;
                        break Label_0126;
                    }
                }
                b = false;
            }
        }
        if (b) {
            XSound.ENTITY_ENDERMAN_TELEPORT.play((Entity)player);
            player.sendMessage(CC.RED + "Air is already in the list.");
        }
        else {
            final MinableFeature minableFeature = new MinableFeature("AIR", illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl.IllIlIllIIllIlllIlIlIlllIlIlIIlIlIIlllIllIIlllIIIllIlllIIIllIIIlIIllIIlIlIIlIlllIlIIIllIIlIIIllIIIllIlIlIlIIlllIIIlIlllIIlIlIIllIIlllIIllIllIllIIllIIllIllIlIlIllIIllIIIlllIllIIlllIIlIIlIIlIllIIllIIIllIIlIIll.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl.IllIIllIlllIlllIllIIlllIIllIIlIIlllIIlIIlIIIllIlllIllIIIllIllIIlllIIlIIlIIlIIlllIIIlIlIIlllIlllIllIIllIIlllIIIlllIIlllIIlIllIlllIlllIIllIIIllIIllIIlIIIllIIIlllIlllIIlIIlIlllIIIllIIIllIIIlllIlIIIlllIIllIIllIIlllIIIlIIlIllIIIlIlIIllIlIll.IllIllIIllIIlllIlllIIIlIIllIlIIIlIlllIlIllIIIllIlIlIlllIllIlllIIllIIlIIllIIllIIlIIlIIlIIllIllIIllIIlIIllIIlllIIllIIIlIIlIllIIIllIllIlIIIllIIllIIlllIlllIIllIIIlIllIlIllIIlllIIIllIlllIIllIllIlIlllIIlllIllIIlIIIllIllIIIl()), 1, 1, 0, 256);
            final FakeConfig fakeConfig = antiXrayConfig.getFakeConfig();
            fakeConfig.setOres(CollectionsKt.plus((Collection)fakeConfig.getOres(), (Object)minableFeature));
            antiXrayConfig.save();
            MCSchedulers.getGlobalScheduler().schedule(IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl::IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll, 2L);
        }
    }
    
    private static final void IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(final Gui gui, final Player player) {
        Intrinsics.checkNotNullParameter((Object)gui, "");
        gui.open(player);
    }
    
    private static final void IIIlIIIllIIIllIlllIIllIlllIIlIIIllIIIlIIIllIIlllIIlllIIIlIlIllIlIIllIIlIllIIIlIlIIlllIIllIIIllIIllIIIlIIllIlIIllIIIllIlIIlllIIllIIIlIIlIIIllIlIIlllIIIllIllIlllIllIlIllIIIllIIlIllIIlIIlllIIllIlllIllIIlIllIIlIIllIlIllIIllIIl(final Player player) {
        player.closeInventory();
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final NormalPane normalPane, final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl ilIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl, final MinableFeature minableFeature, final Player player) {
        Intrinsics.checkNotNullParameter((Object)minableFeature, "");
        final int n = 4;
        final int n2 = 0;
        Intrinsics.checkNotNull((Object)ilIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl);
        normalPane.setSlot(n, n2, GuiSlot.of(ItemBuilder.of(ilIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl.getDisplayItem()).name("&e" + ilIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl.getName()).lore(new String[] { "&7Vein Count: &b" + minableFeature.getVeinCount() + "/chunk", "&7Vein Size: &a" + minableFeature.getVeinSize() + "/vein", "&7Height Range: &ey>=" + minableFeature.getMinHeight() + " &7| &ey<=" + minableFeature.getMaxHeight(), " ", "&cClick to edit this block." }), (Consumer)IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl::IIIlIIIllIIIllIlllIIllIlllIIlIIIllIIIlIIIllIIlllIIlllIIIlIlIllIlIIllIIlIllIIIlIlIIlllIIllIIIllIIllIIIlIIllIlIIllIIIllIlIIlllIIllIIIlIIlIIIllIlIIlllIIIllIllIlllIllIlIllIIIllIIlIllIIlIIlllIIllIlllIllIIlIllIIlIIllIlIllIIllIIl));
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final AntiXrayConfig antiXrayConfig, final MinableFeature minableFeature, final Gui gui, final Player player) {
        Intrinsics.checkNotNullParameter((Object)antiXrayConfig, "");
        Intrinsics.checkNotNullParameter((Object)minableFeature, "");
        Intrinsics.checkNotNullParameter((Object)gui, "");
        final FakeConfig fakeConfig = antiXrayConfig.getFakeConfig();
        fakeConfig.setOres(CollectionsKt.minus((Iterable)fakeConfig.getOres(), (Object)minableFeature));
        antiXrayConfig.save();
        gui.open(player);
        XSound.BLOCK_PISTON_EXTEND.play((Entity)player);
    }
    
    private static final boolean IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(final ItemStack itemStack) {
        return itemStack.getType().isBlock();
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final MinableFeature minableFeature, final IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl, final NormalPane normalPane, final AntiXrayConfig antiXrayConfig, final Gui gui, final Player player, final ItemStack itemStack) {
        Intrinsics.checkNotNullParameter((Object)minableFeature, "");
        Intrinsics.checkNotNullParameter((Object)illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl, "");
        Intrinsics.checkNotNullParameter((Object)antiXrayConfig, "");
        Intrinsics.checkNotNullParameter((Object)player, "");
        final IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl illIlIllIIllIlllIlIlIlllIlIlIIlIlIIlllIllIIlllIIIllIlllIIIllIIIlIIllIIlIlIIlIlllIlIIIllIIlIIIllIIIllIlIlIlIIlllIIIlIlllIIlIlIIllIIlllIIllIllIllIIllIIllIllIlIlIllIIllIIIlllIllIIlllIIlIIlIIlIllIIllIIIllIIlIIll = illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl.IllIlIllIIllIlllIlIlIlllIlIlIIlIlIIlllIllIIlllIIIllIlllIIIllIIIlIIllIIlIlIIlIlllIlIIIllIIlIIIllIIIllIlIlIlIIlllIIIlIlllIIlIlIIllIIlllIIllIllIllIIllIIllIllIlIlIllIIllIIIlllIllIIlllIIlIIlIIlIllIIllIIIllIIlIIll;
        Intrinsics.checkNotNull((Object)itemStack);
        final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl iIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll = illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(itemStack);
        if (iIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll == null) {
            player.sendMessage(CC.RED + "This item is not a block.");
            XSound.ENTITY_ENDERMAN_HURT.play((Entity)player);
            return;
        }
        minableFeature.setReplace(illIlIllIIllIlllIlIlIlllIlIlIIlIlIIlllIllIIlllIIIllIlllIIIllIIIlIIllIIlIlIIlIlllIlIIIllIIlIIIllIIIllIlIlIlIIlllIIIlIlllIIlIlIIllIIlllIIllIllIllIIllIIllIllIlIlIllIIllIIIlllIllIIlllIIlIIlIIlIllIIllIIIllIIlIIll.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(iIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll));
        antiXrayConfig.save();
        gui.update(player);
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final NormalPane normalPane, final IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl, final Gui gui, final AntiXrayConfig antiXrayConfig, final MinableFeature minableFeature, final Gui gui2, final Player player, final Player player2) {
        Intrinsics.checkNotNullParameter((Object)illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl, "");
        Intrinsics.checkNotNullParameter((Object)antiXrayConfig, "");
        Intrinsics.checkNotNullParameter((Object)minableFeature, "");
        Intrinsics.checkNotNullParameter((Object)gui2, "");
        Intrinsics.checkNotNullParameter((Object)player, "");
        normalPane.setSlot(0, 0, GuiSlot.of(ItemBuilder.of(XMaterial.REDSTONE_BLOCK).name("&cRemove").lore(new String[] { "&7Click here to remove this block from the list." }), IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll));
        final int n = 2;
        final int n2 = 0;
        Intrinsics.checkNotNull((Object)gui);
        final KMutableProperty0 kMutableProperty0 = (KMutableProperty0)new MutablePropertyReference0Impl(minableFeature);
        final ItemBuilder lore = ItemBuilder.of(XMaterial.REDSTONE).name("&bVein Count").lore(new String[] { "&7The amount of veins to generate per chunk." });
        Intrinsics.checkNotNullExpressionValue((Object)lore, "");
        normalPane.setSlot(n, n2, illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(gui, antiXrayConfig, (KMutableProperty0<Integer>)kMutableProperty0, lore));
        final int n3 = 3;
        final int n4 = 0;
        final KMutableProperty0 kMutableProperty2 = (KMutableProperty0)new MutablePropertyReference0Impl(minableFeature);
        final ItemBuilder lore2 = ItemBuilder.of(XMaterial.EMERALD).name("&aVein Size").lore(new String[] { "&7The amount of blocks to generate per vein." });
        Intrinsics.checkNotNullExpressionValue((Object)lore2, "");
        normalPane.setSlot(n3, n4, illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(gui, antiXrayConfig, (KMutableProperty0<Integer>)kMutableProperty2, lore2));
        final int n5 = 4;
        final int n6 = 0;
        final KMutableProperty0 kMutableProperty3 = (KMutableProperty0)new MutablePropertyReference0Impl(minableFeature);
        final ItemBuilder lore3 = ItemBuilder.of(XMaterial.GRASS_BLOCK).name("&eMax Height").lore(new String[] { "&7The maximum height to generate the vein." });
        Intrinsics.checkNotNullExpressionValue((Object)lore3, "");
        normalPane.setSlot(n5, n6, illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(gui, antiXrayConfig, (KMutableProperty0<Integer>)kMutableProperty3, lore3));
        final int n7 = 5;
        final int n8 = 0;
        final KMutableProperty0 kMutableProperty4 = (KMutableProperty0)new MutablePropertyReference0Impl(minableFeature);
        final ItemBuilder lore4 = ItemBuilder.of(XMaterial.DIRT).name("&eMin Height").lore(new String[] { "&7The minimum height to generate the vein." });
        Intrinsics.checkNotNullExpressionValue((Object)lore4, "");
        normalPane.setSlot(n7, n8, illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(gui, antiXrayConfig, (KMutableProperty0<Integer>)kMutableProperty4, lore4));
        final int n9 = 6;
        final int n10 = 0;
        final ItemSelectorGuiSlot.Builder itemSelector = GuiSlot.itemSelector((Pane)normalPane, 6, 0);
        final IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl replaceMaterial = minableFeature.getReplaceMaterial();
        Intrinsics.checkNotNull((Object)replaceMaterial);
        normalPane.setSlot(n9, n10, (GuiSlot)itemSelector.currentItem(replaceMaterial.getDisplayItem()).canPlacePredicate((Predicate)IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl::IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll).updateCallback(IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll).build());
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final IIllIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl, final AntiXrayConfig antiXrayConfig, final MinableFeature minableFeature, final Gui gui, final Player player, final ClickType clickType) {
        Intrinsics.checkNotNullParameter((Object)illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl, "");
        Intrinsics.checkNotNullParameter((Object)antiXrayConfig, "");
        Intrinsics.checkNotNullParameter((Object)minableFeature, "");
        Intrinsics.checkNotNullParameter((Object)gui, "");
        if (!clickType.isShiftClick()) {
            Intrinsics.checkNotNull((Object)player);
            illIllIIlIllIIllIIllIllIIIllIIIlllIIllIIIllIlIIllIllIIIllIlllIIIlIllIIlllIIlllIIllIlllIIllIIIllIllIllIllIlllIIlllIlIIlIIllIIllIllIlIllIlIIllIIIllIIllIIllIIIlllIIIlIllIIllIIIlIIlllIllIIlIllIIlIIlllIllIlIlllIIlllIlllIIllIlllIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(player, antiXrayConfig, minableFeature, gui);
            return;
        }
        final FakeConfig fakeConfig = antiXrayConfig.getFakeConfig();
        fakeConfig.setOres(CollectionsKt.minus((Iterable)fakeConfig.getOres(), (Object)minableFeature));
        antiXrayConfig.save();
        gui.update(player);
        XSound.BLOCK_PISTON_EXTEND.play((Entity)player);
    }
}
