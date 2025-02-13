package ru.screamoov.xwarps.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import ru.screamoov.xwarps.commands.subcommand.ISubCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WarpCommandExecutor implements CommandExecutor {
    public List<ISubCommand> subCommands = new ArrayList<>();
    public Plugin plugin;

    public WarpCommandExecutor(Plugin plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(Bukkit.getPluginCommand("warp")).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        return true;
    }
}
