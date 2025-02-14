package ru.screamoov.xwarps.warp;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;

public class Warp {
    public String name;
    public String creator;
    public long toRemove;
    public WarpType type;
    public Location location;
    public FileConfiguration config;

    public Warp(String name, String creator, long toRemove, WarpType type, Location location, FileConfiguration config, boolean createdNow) {
        this.name = name;
        this.creator = creator;
        this.toRemove = toRemove;
        this.type = type;
        this.location = location;
        this.config = config;

        if (createdNow) {
            this.config.set("name", name);
            this.config.set("creator", creator);
            this.config.set("to-remove", toRemove);
            this.config.set("type", type.name());
            this.config.set("location", location);
            try {
                this.config.save(new File("plugins/xWarps/warps/" + name + ".yml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
