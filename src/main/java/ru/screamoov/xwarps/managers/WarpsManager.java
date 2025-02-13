package ru.screamoov.xwarps.managers;

import ru.screamoov.xwarps.WarpInstance;
import ru.screamoov.xwarps.warp.Warp;

import java.util.ArrayList;
import java.util.List;

public class WarpsManager {
    public List<Warp> warps;
    private final WarpInstance plugin;

    public WarpsManager(WarpInstance plugin) {
        this.plugin = plugin;
        this.warps = new ArrayList<>();
    }
}
