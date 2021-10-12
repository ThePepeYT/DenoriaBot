package com.thepepeyt.Klasa.commands;

import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.GUI;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ustawrase implements CommandExecutor, Listener {

    private Main plugin;
    Database db = new Database();
    GUI inv = new GUI();
    Chat cc = new Chat();


    public ustawrase(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        plugin.getCommand("ustawrase").setExecutor(this);

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(p.hasPermission("denoria.ustawrase")) {
            p.openInventory(inv.rasagui(p));

        }
        else{
            String message = plugin.getConfig().getString("Brak-Permisji");
            plugin.adventure().player(p).sendMessage(cc.fix(message));
        }
        return true;}




    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        final ItemStack clickedItem = e.getCurrentItem();
        if (e.getInventory().getSize() == 9) {
            if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Człowiek")) {
                e.setCancelled(true);
                p.closeInventory();
                p.sendMessage(ChatColor.GREEN + "Pomyślnie ustawiono rase na " + ChatColor.AQUA + "człowiek");
                db.updateRasa(p.getUniqueId(), "czlowiek");
                p.setMaxHealth(30.0);


            } else if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Elf")) {
                e.setCancelled(true);
                p.closeInventory();
                p.sendMessage(ChatColor.GREEN + "Pomyślnie ustawiono rase na " + ChatColor.AQUA + "elf");
                db.updateRasa(p.getUniqueId(), "elf");
                p.setMaxHealth(20.0);


            } else if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Krasnolud")) {
                e.setCancelled(true);
                p.closeInventory();
                p.sendMessage(ChatColor.GREEN + "Pomyślnie ustawiono rase na " + ChatColor.AQUA + "krasnolud");
                db.updateRasa(p.getUniqueId(), "krasnolud");
                p.setMaxHealth(20.0);
            } else if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Demon")) {
                e.setCancelled(true);
                p.closeInventory();
                p.sendMessage(ChatColor.GREEN + "Pomyślnie ustawiono rase na " + ChatColor.AQUA + "demon");
                db.updateRasa(p.getUniqueId(), "demon");
                p.setMaxHealth(20.0);
            } else if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Ork")) {
                e.setCancelled(true);
                p.closeInventory();
                p.sendMessage(ChatColor.GREEN + "Pomyślnie ustawiono rase na " + ChatColor.AQUA + "ork");
                db.updateRasa(p.getUniqueId(), "ork");
                p.setMaxHealth(20.0);
            } else if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Troll")) {
                e.setCancelled(true);
                p.closeInventory();
                p.sendMessage(ChatColor.GREEN + "Pomyślnie ustawiono rase na " + ChatColor.AQUA + "troll");
                db.updateRasa(p.getUniqueId(), "troll");
                p.setMaxHealth(20.0);
            } else if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Goblin")) {
                e.setCancelled(true);
                p.closeInventory();
                p.sendMessage(ChatColor.GREEN + "Pomyślnie ustawiono rase na " + ChatColor.AQUA + "goblin");
                db.updateRasa(p.getUniqueId(), "goblin");
                p.setMaxHealth(20.0);
            } else if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.RED + "RASA")) {
                e.setCancelled(true);

            }
        }
    }


}
