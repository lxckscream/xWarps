package ru.screamoov.xwarps.managers;

import org.bukkit.scheduler.BukkitRunnable;
import ru.screamoov.xwarps.WarpInstance;
import ru.screamoov.xwarps.warp.Warp;
import ru.screamoov.xwarps.warp.WarpType;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WarpsManager {
    public List<Warp> warps;
    public HashMap<Warp, Long> toRemove;
    private final WarpInstance plugin;

    public WarpsManager(WarpInstance plugin) {
        this.plugin = plugin;
        this.warps = new ArrayList<>();
        this.toRemove = new HashMap<>();

        new BukkitRunnable(){
            @Override
            public void run() {
                toRemove.keySet().forEach(warp->{
                    if (warp.type!= WarpType.SERVER) { // Игнорируем варпы, поставленные администрацией
                        long toRemove = WarpsManager.this.toRemove.get(warp);
                        if (toRemove >= 1) {
                            WarpsManager.this.toRemove.put(warp, toRemove - 1);
                        } else {
                            removeWarp(warp);
                        }
                    }
                });
            }
        }.runTaskTimerAsynchronously(plugin, 20, 20);
    }

    public void registerWarp(Warp warp) {
        this.warps.add(warp);
        this.toRemove.put(warp, warp.toRemove);
        plugin.getLogger().info("Warp " + warp.name + " registered!");
    }

    public WarpInstance getPlugin() {
        return plugin;
    }

    public void removePlayerWarps() {
        warps.forEach(warp -> {
            if (warp.type == WarpType.CUSTOM){
                removeWarp(warp);
            }
        });
    }

    public Warp getWarp(String name) {
        for (Warp warp:warps) {
            if (warp.name.equals(name))return warp;
        }
        return null;
    }

    public void removeWarp(Warp warp) {
        warps.remove(warp);
        toRemove.remove(warp);
        new File("plugins/xWarps/warps/" + warp.name +".yml").delete();
    }
}
