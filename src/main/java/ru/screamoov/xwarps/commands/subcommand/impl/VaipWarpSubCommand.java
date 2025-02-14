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
import java.util.ArrayList;
import java.util.List;

import static ru.screamoov.xwarps.utils.Hex.color;

public class VaipWarpSubCommand implements ISubCommand {
    @Override
    public String name() {
        return "vaip";
    }

    @Override
    public String usage() {
        return "&c/warp vaip <CUSTOM/SERVER/ALL>";
    }

    @Override
    public String permission() {
        return "xwarps.vaip";
    }

    @Override
    public void execute(String[] args, CommandSender sender, WarpInstance plugin) {
        if (sender instanceof Player) {
            if (isHasPermission(sender)) {
                if (args.length == 2) {
                    switch (args[1].toLowerCase()) {
                        case "custom":
                            List<Warp> customWarps = new ArrayList<>(plugin.getWarpsManager().warps); // Создаем копию списка
                            for (Warp warp1 : customWarps) {
                                if (warp1.type == WarpType.CUSTOM) {
                                    plugin.getWarpsManager().removeWarp(warp1); // Удаляем из оригинального списка
                                    sender.sendMessage(color("&c&lУДАЛЁН ВАРП &6&l" + warp1.name + "&c&l ОТ " + warp1.creator + " ЛОКАЦИЯ: " + warp1.location + " ВРЕМЯ ДО УДАЛЕНИЯ: " + warp1.toRemove));
                                }
                            }
                            sender.sendMessage(color("&aПроцесс завершён."));
                            break;

                        case "server":
                            List<Warp> serverWarps = new ArrayList<>(plugin.getWarpsManager().warps); // Создаем копию списка
                            for (Warp warp1 : serverWarps) {
                                if (warp1.type == WarpType.SERVER) {
                                    plugin.getWarpsManager().removeWarp(warp1); // Удаляем из оригинального списка
                                    sender.sendMessage(color("&c&lУДАЛЁН ВАРП &6&l" + warp1.name + "&c&l ОТ " + warp1.creator + " ЛОКАЦИЯ: " + warp1.location + " ВРЕМЯ ДО УДАЛЕНИЯ: " + warp1.toRemove));
                                }
                            }
                            sender.sendMessage(color("&aПроцесс завершён."));
                            break;

                        case "all":
                            List<Warp> allWarps = new ArrayList<>(plugin.getWarpsManager().warps); // Создаем копию списка
                            for (Warp warp : allWarps) {
                                plugin.getWarpsManager().removeWarp(warp); // Удаляем из оригинального списка
                                sender.sendMessage(color("&c&lУДАЛЁН ВАРП &6&l" + warp.name + "&c&l ОТ " + warp.creator + " ЛОКАЦИЯ: " + warp.location + " ВРЕМЯ ДО УДАЛЕНИЯ: " + warp.toRemove));
                            }
                            sender.sendMessage(color("&aПроцесс завершён."));
                            break;
                    }
                } else sender.sendMessage(color(usage()));
            } else sender.sendMessage(color(plugin.getConfig().getString("messages.no-perms")));
        } else sender.sendMessage(color(plugin.getConfig().getString("messages.no-player")));
    }
}
