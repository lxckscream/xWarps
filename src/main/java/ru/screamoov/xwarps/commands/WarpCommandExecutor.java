package ru.screamoov.xwarps.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import ru.screamoov.xwarps.WarpInstance;
import ru.screamoov.xwarps.commands.subcommand.ISubCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WarpCommandExecutor implements CommandExecutor {
    public List<ISubCommand> subCommands = new ArrayList<>();
    public WarpInstance plugin;

    public WarpCommandExecutor(WarpInstance plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(Bukkit.getPluginCommand("warp")).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        subCommands.forEach(subCommand->{
            if (strings[0].equals(subCommand.name())) {
                subCommand.execute(strings, commandSender, this.plugin);
            }
        });
        return true;
    }
}
