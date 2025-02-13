package ru.screamoov.xwarps.warp;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

public class Warp {
    public String name;
    public String creator;
    public long toRemove;
    public WarpType type;
    public FileConfiguration config;

    public Warp(String name, String creator, long toRemove, WarpType type, FileConfiguration config) {
        this.name = name;
        this.creator = creator;
        this.toRemove = toRemove;
        this.type = type;
        this.config = config;
    }
}
