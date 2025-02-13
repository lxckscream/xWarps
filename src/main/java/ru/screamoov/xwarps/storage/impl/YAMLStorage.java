package ru.screamoov.xwarps.storage.impl;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.screamoov.xwarps.WarpInstance;
import ru.screamoov.xwarps.managers.WarpsManager;
import ru.screamoov.xwarps.storage.IStorage;
import ru.screamoov.xwarps.warp.Warp;
import ru.screamoov.xwarps.warp.WarpType;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class YAMLStorage implements IStorage {
    @Override
    public void loadWarps(WarpsManager warpsManager) {
        File dir = new File("plugins/xWarps/warps/");
        if (!dir.exists()) dir.mkdirs();
        if (dir.listFiles()!=null){
            Arrays.asList(dir.listFiles()).forEach(file -> {
                if (file.getName().endsWith(".yml")) {
                    FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                    try {
                        warpsManager.registerWarp(
                                new Warp(
                                        config.getString("name"),
                                        config.getString("creator"),
                                        config.getLong("to-remove"),
                                        WarpType.valueOf(
                                                config.getString("type").toUpperCase()
                                        ),
                                        config
                                )
                        );
                    } catch (Exception e) {
                        warpsManager.getPlugin().getLogger().severe("ERROR WHILE LOADING WARP: " + file.getName().replaceFirst(".yml", ""));
                        Bukkit.getPluginManager().disablePlugin(warpsManager.getPlugin());
                    }
                }
            });
        }
    }

    @Override
    public void saveWarps(List<Warp> warps) {

    }
}
