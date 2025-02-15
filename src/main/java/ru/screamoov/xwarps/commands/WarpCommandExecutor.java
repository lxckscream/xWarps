package ru.screamoov.xwarps.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ru.screamoov.xwarps.WarpInstance;
import ru.screamoov.xwarps.commands.subcommand.ISubCommand;
import ru.screamoov.xwarps.commands.subcommand.impl.*;
import ru.screamoov.xwarps.warp.Warp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.screamoov.xwarps.utils.Hex.color;

public class WarpCommandExecutor implements CommandExecutor {
    public List<ISubCommand> subCommands = new ArrayList<>();
    public WarpInstance plugin;

    public WarpCommandExecutor(WarpInstance plugin) {
        this.plugin = plugin;
        subCommands.addAll(
                Arrays.asList(
                        new CreateWarpSubCommand(),
                        new ListWarpSubCommand(),
                        new CreateServerWarpSubCommand(),
                        new RemoveWarpSubCommand(),
                        new VaipWarpSubCommand()
                )
        );
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage(color("&6&lxWarps &8| &7lxckScreamoov"));
            commandSender.sendMessage(color("&c/warp <название>&8 - &7Телепортироваться на варп"));
            subCommands.forEach(scommand->{
                if (scommand.isHasPermission(commandSender)) commandSender.sendMessage(color(scommand.usage() + "&8 - &7" + scommand.desc()));
            });
            return true;
        }

        for (ISubCommand subCommand : subCommands) {
            if (strings[0].equals(subCommand.name())) {
                subCommand.execute(strings, commandSender, this.plugin);
                return true;
            }
        }

        if (strings.length == 1) {
            String name = strings[0];
            for (Warp warp : plugin.getWarpsManager().warps) {
                if (warp.name.equals(name)) {
                    if (commandSender instanceof Player) {
                        warp.teleport((Player) commandSender);
                        commandSender.sendMessage(color(plugin.getConfig().getString("messages.teleported")));
                    }
                    else commandSender.sendMessage(color(plugin.getConfig().getString("messages.no-player")));
                    return true;
                }
            }
            commandSender.sendMessage(color(plugin.getConfig().getString("messages.warp-not-found")));
        }
        return true;
    }
}
