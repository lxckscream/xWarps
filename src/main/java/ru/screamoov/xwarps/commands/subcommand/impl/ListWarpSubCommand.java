package ru.screamoov.xwarps.commands.subcommand.impl;

import org.bukkit.command.CommandSender;
import ru.screamoov.xwarps.WarpInstance;
import ru.screamoov.xwarps.commands.subcommand.ISubCommand;

public class ListWarpSubCommand implements ISubCommand {
    @Override
    public String name() {
        return "list";
    }

    @Override
    public String usage() {
        return "/warp list";
    }

    @Override
    public String permission() {
        return "xwarps.list";
    }

    @Override
    public void execute(String[] args, CommandSender sender, WarpInstance plugin) {

    }
}
