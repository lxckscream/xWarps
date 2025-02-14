package ru.screamoov.xwarps.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import ru.screamoov.xwarps.WarpInstance;

import java.util.HashMap;

import static ru.screamoov.xwarps.utils.Hex.color;

public class SelectWarpTypeGui implements Listener {
    public Inventory inventory;
    public HashMap<Integer, ItemStack> itemStackHashMap = new HashMap<>();
    public WarpInstance plugin;

    public SelectWarpTypeGui(WarpInstance plugin) {
        this.plugin = plugin;

        inventory = Bukkit.createInventory(null, plugin.getConfig().getInt("guis.select-warp-type.size"), color(plugin.getConfig().getString("guis.select-warp-type.title")));
        ItemStack playerWarps = new ItemStack(Material.valueOf(plugin.getConfig().getString("guis.select-warp-type.player-warps.icon").toUpperCase()));
        ItemMeta playerWarpsMeta = playerWarps.getItemMeta();
        playerWarpsMeta.setDisplayName(color(plugin.getConfig().getString("guis.select-warp-type.player-warps.display-name")));
        playerWarpsMeta.setLore(color(plugin.getConfig().getStringList("guis.select-warp-type.player-warps.lore")));
        playerWarps.setItemMeta(playerWarpsMeta);
        itemStackHashMap.put(plugin.getConfig().getInt("guis.select-warp-type.player-warps.slot"), playerWarps);

        itemStackHashMap.keySet().forEach(slot->{
            inventory.setItem(slot, itemStackHashMap.get(slot));
        });
    }
}
