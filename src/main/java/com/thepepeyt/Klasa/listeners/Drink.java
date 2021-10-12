package com.thepepeyt.Klasa.listeners;

import com.thepepeyt.Klasa.GUI;
import com.thepepeyt.Klasa.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Drink implements Listener {


    GUI inv = new GUI();
    private Main plugin;


    public Drink(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void drink(PlayerItemConsumeEvent e){
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        ItemMeta itemmeta = item.getItemMeta();
        if(itemmeta.getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Eliksir Boga")){
            p.openInventory(inv.plecgui(p));

        }
        else if(itemmeta.getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Eliksir Szatana")){
            p.openInventory(inv.rasagui(p));
        }
    }
}
