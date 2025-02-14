package ru.screamoov.xwarps.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
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
    public ItemStack playerWarps;
    public ItemStack serverWarps;
    public ItemStack decoration;

    public SelectWarpTypeGui(WarpInstance plugin) {
        this.plugin = plugin;

        inventory = Bukkit.createInventory(null, plugin.getConfig().getInt("guis.select-warp-type.size"), color(plugin.getConfig().getString("guis.select-warp-type.title")));
        playerWarps = new ItemStack(Material.valueOf(plugin.getConfig().getString("guis.select-warp-type.player-warps.icon").toUpperCase()));
        ItemMeta playerWarpsMeta = playerWarps.getItemMeta();
        playerWarpsMeta.setDisplayName(color(plugin.getConfig().getString("guis.select-warp-type.player-warps.display-name")));
        playerWarpsMeta.setLore(color(plugin.getConfig().getStringList("guis.select-warp-type.player-warps.lore")));
        playerWarps.setItemMeta(playerWarpsMeta);
        itemStackHashMap.put(plugin.getConfig().getInt("guis.select-warp-type.player-warps.slot"), playerWarps);

        serverWarps = new ItemStack(Material.valueOf(plugin.getConfig().getString("guis.select-warp-type.server-warps.icon").toUpperCase()));
        ItemMeta serverWarpsMeta = serverWarps.getItemMeta();
        serverWarpsMeta.setDisplayName(color(plugin.getConfig().getString("guis.select-warp-type.server-warps.display-name")));
        serverWarpsMeta.setLore(color(plugin.getConfig().getStringList("guis.select-warp-type.server-warps.lore")));
        serverWarps.setItemMeta(serverWarpsMeta);
        itemStackHashMap.put(plugin.getConfig().getInt("guis.select-warp-type.server-warps.slot"), serverWarps);

        decoration = new ItemStack(Material.valueOf(plugin.getConfig().getString("guis.select-warp-type.decoration.icon").toUpperCase()));
        ItemMeta decorationMeta = decoration.getItemMeta();
        decorationMeta.setDisplayName(color(plugin.getConfig().getString("guis.select-warp-type.decoration.display-name")));
        decorationMeta.setLore(color(plugin.getConfig().getStringList("guis.select-warp-type.decoration.lore")));
        decoration.setItemMeta(decorationMeta);

        plugin.getConfig().getIntegerList("guis.select-warp-type.decoration.slots").forEach(slot->{
            itemStackHashMap.put(slot,decoration);
        });

        itemStackHashMap.keySet().forEach(slot->{
            inventory.setItem(slot, itemStackHashMap.get(slot));
        });

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null || event.getCurrentItem() == null) return;

        if (event.getClickedInventory().equals(inventory)){
            event.setCancelled(true);
            ItemStack clicked = event.getCurrentItem();
            if (clicked.isSimilar(playerWarps)) {
                plugin.getPlayerWarpsPageGui().openWarpsInventory((Player) event.getWhoClicked(), 0);
            }
        }
    }
}
