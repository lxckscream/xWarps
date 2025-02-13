package ru.screamoov.xwarps.commands.subcommand;

import org.bukkit.command.CommandSender;

public interface ISubCommand {
    String name();
    default String desc() {return "Описание команды не установлено";}
    String usage();

    void execute(String[] args, CommandSender sender);
}
