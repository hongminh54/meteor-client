// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import io.fairyproject.command.CommandContext;
import org.bukkit.command.CommandSender;
import io.fairyproject.util.CC;
import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.command.MessageType;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.bukkit.command.event.BukkitCommandContext;
import io.fairyproject.command.PresenceProvider;

public final class IlllIllIIllIllIIIlllIllIIIlllIIlIIllIIlIllIIlllIIllIIIlIlIlllIIlIlIllIIIllIlllIlllIllIIIllIIIlIIIlIIIlIIIlIIllIllIIIllIllIlIIIlllIIllIIllIIllIIlllIIIlIIIlIIIlIllIIIlllIIlllIlllIIlIIIlIIlllIlIIllIIllIIIllIIlllIlIIIlIllIIlllIIIlllIIllIIIlll implements PresenceProvider<BukkitCommandContext>
{
    @NotNull
    public Class<BukkitCommandContext> type() {
        return BukkitCommandContext.class;
    }
    
    public void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final BukkitCommandContext bukkitCommandContext, @NotNull final MessageType messageType, @NotNull final String... array) {
        Intrinsics.checkNotNullParameter((Object)bukkitCommandContext, "");
        Intrinsics.checkNotNullParameter((Object)messageType, "");
        Intrinsics.checkNotNullParameter((Object)array, "");
        final CommandSender sender = bukkitCommandContext.getSender();
        for (int i = 0; i < array.length; ++i) {
            final String str = array[i];
            switch (IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.IllIIIlIllIIIlllIIllIIllIIllIIllIIlIIlllIIIlIllIIlllIlllIIlIlIIIllIIlIllIIIlllIIIlIIlIllIIIllIIllIlllIIlllIIlIlllIIllIIlIIllIIlllIlllIIIlIIllIIIlIIIlllIlllIllIlllIllIlllIIIllIIIlllIllIIllIllIllIlllIIllIIlllIIlllIlIlIIlIIIlIIlIIlIl[messageType.ordinal()]) {
                case 1: {
                    sender.sendMessage(CC.translate("&8[&bAntiXray&8] &7" + str));
                    break;
                }
                case 2: {
                    sender.sendMessage(CC.translate("&8[&bAntiXray&8] &e" + str));
                    break;
                }
                case 3: {
                    sender.sendMessage(CC.translate("&8[&bAntiXray&8] &c" + str));
                    break;
                }
            }
        }
    }
}
