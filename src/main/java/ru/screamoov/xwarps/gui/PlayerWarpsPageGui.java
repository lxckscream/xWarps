package ru.screamoov.xwarps.gui;

import net.md_5.bungee.api.ChatColor;
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
import ru.screamoov.xwarps.utils.TimeFormatter;
import ru.screamoov.xwarps.warp.Warp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.screamoov.xwarps.utils.Hex.color;

public class PlayerWarpsPageGui implements Listener {

    private final WarpInstance plugin;
    private final int WARPS_PER_PAGE = 45;
    private final int NEXT_PAGE_SLOT = 53;
    private final int PREV_PAGE_SLOT = 45;

    public PlayerWarpsPageGui(WarpInstance plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void openWarpsInventory(Player player, int page) {
        List<Warp> warps = plugin.getWarpsManager().getPlayerWarps();
        int totalPages = (int) Math.ceil((double) warps.size() / WARPS_PER_PAGE);

        if (page < 0 || page >= totalPages) {
            player.sendMessage(ChatColor.RED + "Страница не существует!");
            return;
        }

        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.GRAY + "Варпы игроков - Страница " + (page + 1));

        int startIndex = page * WARPS_PER_PAGE;
        int endIndex = Math.min(startIndex + WARPS_PER_PAGE, warps.size());

        for (int i = startIndex; i < endIndex; i++) {
            inventory.addItem(createWarpItem(warps.get(i)));
        }

        if (page > 0) {
            inventory.setItem(PREV_PAGE_SLOT, createNavigationItem(Material.ARROW, ChatColor.YELLOW + "След. страница"));
        }

        if (page < totalPages - 1) {
            inventory.setItem(NEXT_PAGE_SLOT, createNavigationItem(Material.ARROW, ChatColor.YELLOW + "Пред. страница"));
        }

        player.openInventory(inventory);
    }

    private ItemStack createWarpItem(Warp warp) {
        ItemStack item = new ItemStack(Material.ENDER_PEARL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(color("&7Варп: &6&n" + warp.name));
        meta.setLore(color(
                Arrays.asList(
                        "&7",
                        "&7  Будет удалён через: &6&n" + TimeFormatter.format(plugin.getWarpsManager().toRemove.get(warp)),
                        "&7  Создатель: &6&n" + warp.creator,
                        "&7"
                )
        ));
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack createNavigationItem(Material material, String displayName) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().startsWith(ChatColor.GRAY + "Варпы игроков - Страница ")) {
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            int page = Integer.parseInt(event.getView().getTitle().replace(ChatColor.GREEN + "Варпы игроков - Страница ", "")) - 1;

            if (event.getSlot() == NEXT_PAGE_SLOT) {
                List<Warp> warps = plugin.getWarpsManager().getPlayerWarps();
                int totalPages = (int) Math.ceil((double) warps.size() / WARPS_PER_PAGE);

                if (page < totalPages - 1) {
                    openWarpsInventory(player, page + 1);
                }
            }
            else if (event.getSlot() == PREV_PAGE_SLOT) {
                if (page > 0) {
                    openWarpsInventory(player, page - 1);
                }
            }
            else if (event.getSlot() < WARPS_PER_PAGE) {
                List<Warp> warps = plugin.getWarpsManager().getPlayerWarps();
                int warpIndex = page * WARPS_PER_PAGE + event.getSlot();

                if (warpIndex >= 0 && warpIndex < warps.size()) {
                    Warp warp = warps.get(warpIndex);
                    player.teleport(warp.location);
                    player.sendMessage(ChatColor.GREEN + "Вы телепортированы на варп: " + warp.name);
                }
            }
        }
    }
}