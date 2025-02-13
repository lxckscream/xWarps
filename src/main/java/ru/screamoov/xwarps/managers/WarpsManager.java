package ru.screamoov.xwarps.managers;

import ru.screamoov.xwarps.WarpInstance;
import ru.screamoov.xwarps.warp.Warp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class WarpsManager {
    public List<Warp> warps;
    public HashMap<Warp, Long> toRemove;
    private final WarpInstance plugin;

    public WarpsManager(WarpInstance plugin) {
        this.plugin = plugin;
        this.warps = new ArrayList<>();
        this.toRemove = new HashMap<>();
    }

    public void registerWarp(Warp warp) {
        this.warps.add(warp);
        this.toRemove.put(warp, warp.toRemove);
        plugin.getLogger().info("Warp " + warp.name + " registered!");
    }

    public WarpInstance getPlugin() {
        return plugin;
    }
}
