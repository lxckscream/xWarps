package ru.screamoov.xwarps.commands.subcommand;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import ru.screamoov.xwarps.WarpInstance;

public interface ISubCommand {
    String name();
    default String desc() {return "Описание команды не установлено";}
    String usage();
    String permission();
    default boolean isHasPermission(CommandSender commandSender) {
        return commandSender.hasPermission(permission());
    }

    void execute(String[] args, CommandSender sender, WarpInstance plugin);
}
