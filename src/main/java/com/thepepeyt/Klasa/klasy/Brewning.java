package com.thepepeyt.Klasa.klasy;

import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

public class Brewning implements Listener {
    Database db = new Database();

    private Main plugin;


    public Brewning(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void catchChestOpen(InventoryOpenEvent e) {
        Player p = (Player) e.getPlayer();

        if (e.getInventory().getType().equals(InventoryType.BREWING)) {
            String klasa = db.getColumn(p.getUniqueId(), "KLASA");
            if(!(klasa.equals("zaklinacz"))){
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Musisz mieć klase alchemika żeby korzystać z stołu alchemicznego");
            }


            //Do Stuff

        }
    }
}
