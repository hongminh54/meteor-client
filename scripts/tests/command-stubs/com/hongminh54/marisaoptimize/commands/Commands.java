package com.hongminh54.marisaoptimize.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import java.util.ArrayList;
import java.util.List;

/** Registration stand-in only; no module registration or game join is exercised. */
public class Commands {
    public static final List<Command> COMMANDS = new ArrayList<>();
    public static final CommandDispatcher<ClientSuggestionProvider> DISPATCHER = new CommandDispatcher<>();
    public static Command get(String name) {
        return COMMANDS.stream().filter(command -> command.getName().equals(name)).findFirst().orElse(null);
    }
}
