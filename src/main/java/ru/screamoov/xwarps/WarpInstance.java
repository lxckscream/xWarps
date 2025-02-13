package ru.screamoov.xwarps;

import org.bukkit.plugin.java.JavaPlugin;
import ru.screamoov.xwarps.managers.WarpsManager;
import ru.screamoov.xwarps.storage.IStorage;
import ru.screamoov.xwarps.storage.impl.YAMLStorage;

public final class WarpInstance extends JavaPlugin {
    private IStorage warpStorage;
    private WarpsManager warpsManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.warpsManager = new WarpsManager(this);

        String storage = getConfig().getString("storage.type", "YAML");
        switch (storage.toLowerCase()) {
            case "yaml":
                this.warpStorage = new YAMLStorage();
                break;
//            case "sqlite":
//                this.warpStorage = new YAMLStorage();
//                break;
            default:
                this.warpStorage = new YAMLStorage();
                break;
        }

        this.warpStorage.loadWarps(this.warpsManager);
    }

    public IStorage getWarpStorage() {
        return warpStorage;
    }

    public WarpsManager getWarpsManager() {
        return warpsManager;
    }

    @Override
    public void onDisable() {
        warpStorage.saveWarps(warpsManager.warps);
    }
}
