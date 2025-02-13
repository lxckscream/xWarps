package ru.screamoov.xwarps.commands.subcommand.impl;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ru.screamoov.xwarps.WarpInstance;
import ru.screamoov.xwarps.commands.subcommand.ISubCommand;
import ru.screamoov.xwarps.managers.WarpsManager;
import ru.screamoov.xwarps.warp.Warp;

import static ru.screamoov.xwarps.utils.Hex.color;

public class CreateWarpSubCommand implements ISubCommand {
    @Override
    public String name() {
        return "create";
    }

    @Override
    public String usage() {
        return "&cИспользование: /warp create <name>";
    }

    @Override
    public String permission() {
        return "xwarps.create";
    }

    @Override
    public void execute(String[] args, CommandSender sender, WarpInstance plugin) {
        if (sender instanceof Player) {
            if (isHasPermission(sender)) {
                if (args.length == 2) {
                    String warpName = args[1];
                    WarpsManager warpsManager = plugin.getWarpsManager().getPlugin().getWarpsManager();
                    if (warpsManager.getWarp(warpName) == null) {
                        warpsManager.registerWarp(
                                new Warp(
                                        warpName,

                                )
                        );
                    } else color(plugin.getConfig().getString("messages.already-created"));
                } else color(usage());
            } else color(plugin.getConfig().getString("messages.no-perms"));
        } else color(plugin.getConfig().getString("messages.no-player"));
    }
}
