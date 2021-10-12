package com.thepepeyt.Klasa.commands;

import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.databases.WLDatabase;
import com.thepepeyt.Klasa.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.GUI;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class wl implements CommandExecutor, Listener {

    private Main plugin;
    WLDatabase dbwl = new WLDatabase();
    GUI inv = new GUI();
    Chat cc = new Chat();



    public wl(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        plugin.getCommand("whitelista").setExecutor(this);

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(p.hasPermission("denoria.wl")) {
            p.openInventory(inv.whitelistgui(p));


        }
        else{
            String message = plugin.getConfig().getString("Brak-Permisji");
            plugin.adventure().player(p).sendMessage(cc.fix(message));
        }

        return true;
    }


    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        final ItemStack clickedItem = e.getCurrentItem();
        if (e.getInventory().getSize() == 9) {
            if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Wyłącz Whiteliste")) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getConfig().set("whitelist", false);

            } else if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Włącz Whiteliste")) {
                e.setCancelled(true);
                p.closeInventory();
                p.sendMessage(ChatColor.GREEN + "Pomyślnie ustawiono klase na " + ChatColor.AQUA + "Włącz Whiteliste");
                plugin.getConfig().set("whitelist", true);


            } else if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.RED + "Whitelista")) {
                e.setCancelled(true);

            }
        }
    }




}
