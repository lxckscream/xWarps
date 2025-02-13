package ru.screamoov.xwarps.commands.subcommand.impl;

import org.bukkit.command.CommandSender;
import ru.screamoov.xwarps.commands.subcommand.ISubCommand;

public class CreateWarpSubCommand implements ISubCommand {
    @Override
    public String name() {
        return "create";
    }

    @Override
    public String usage() {
        return "/warp create <name>";
    }

    @Override
    public void execute(String[] args, CommandSender sender) {

    }
}
