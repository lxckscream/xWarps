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

public class CreateServerWarpSubCommand implements ISubCommand {
    @Override
    public String name() {
        return "createserver";
    }

    @Override
    public String usage() {
        return "&c/warp createserver <название>";
    }

    @Override
    public String permission() {
        return "xwarps.create_server";
    }

    @Override
    public void execute(String[] args, CommandSender sender, WarpInstance plugin) {
        if (sender instanceof Player) {
            if (isHasPermission(sender)) {
                if (args.length == 2) {
                    String warpName = args[1];
                    WarpsManager warpsManager = plugin.getWarpsManager().getPlugin().getWarpsManager();
                    if (warpsManager.getWarp(warpName) == null) {
                        File file = new File("plugins/xWarps/warps/" + warpName + ".yml");
                        try {file.createNewFile();}catch (Exception e) {e.printStackTrace();}

                        warpsManager.registerWarp(
                                new Warp(
                                        warpName,
                                        sender.getName(),
                                        plugin.getConfig().getLong("settings.to-remove"),
                                        WarpType.SERVER,
                                        ((Player) sender).getLocation(),
                                        YamlConfiguration.loadConfiguration(file),
                                        true
                                )
                        );
                        sender.sendMessage(color(plugin.getConfig().getString("messages.warp-created")
                                .replaceAll("%warp%", warpName)
                        ));
                    } else sender.sendMessage(color(plugin.getConfig().getString("messages.already-created")));
                } else sender.sendMessage(color(usage()));
            } else sender.sendMessage(color(plugin.getConfig().getString("messages.no-perms")));
        } else sender.sendMessage(color(plugin.getConfig().getString("messages.no-player")));
    }
}
