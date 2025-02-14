package ru.screamoov.xwarps.commands.subcommand.impl;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import ru.screamoov.xwarps.WarpInstance;
import ru.screamoov.xwarps.commands.subcommand.ISubCommand;
import ru.screamoov.xwarps.managers.WarpsManager;
import ru.screamoov.xwarps.warp.Warp;
import ru.screamoov.xwarps.warp.WarpType;

import java.io.File;

import static ru.screamoov.xwarps.utils.Hex.color;

public class RemoveWarpSubCommand implements ISubCommand {
    @Override
    public String name() {
        return "remove";
    }

    @Override
    public String usage() {
        return "&c/warp remove <название>";
    }

    @Override
    public String permission() {
        return "xwarps.remove";
    }

    @Override
    public void execute(String[] args, CommandSender sender, WarpInstance plugin) {
        if (sender instanceof Player) {
            if (isHasPermission(sender)) {
                if (args.length == 2) {
                    String warpName = args[1];
                    WarpsManager warpsManager = plugin.getWarpsManager().getPlugin().getWarpsManager();
                    if (warpsManager.getWarp(warpName) != null) {
                        if (warpsManager.getWarp(warpName).creator.equals(sender.getName())) {
                            warpsManager.removeWarp(warpsManager.getWarp(warpName));
                            sender.sendMessage(color(plugin.getConfig().getString("messages.warp-removed")));
                        } else sender.sendMessage(color(plugin.getConfig().getString("messages.no-your-warp")));
                    } else sender.sendMessage(color(plugin.getConfig().getString("messages.already-created")));
                } else sender.sendMessage(color(usage()));
            } else sender.sendMessage(color(plugin.getConfig().getString("messages.no-perms")));
        } else sender.sendMessage(color(plugin.getConfig().getString("messages.no-player")));
    }
}
