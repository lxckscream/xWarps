package ru.screamoov.xwarps.storage;

import ru.screamoov.xwarps.managers.WarpsManager;
import ru.screamoov.xwarps.warp.Warp;

import java.util.List;

public interface IStorage {
    void loadWarps(WarpsManager warpsManager);
    void saveWarps(List<Warp> warps, WarpsManager warpsManager);
}
