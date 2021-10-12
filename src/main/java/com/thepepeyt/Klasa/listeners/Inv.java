package com.thepepeyt.Klasa.listeners;

import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.utils.Chat;
import com.thepepeyt.Klasa.utils.NMSUTILS;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class Inv implements Listener {



    private Main plugin;
    Database db = new Database();
    Chat cc = new Chat();

    public Inv(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }



    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        final ItemStack clickedItem = e.getCurrentItem();
        if (e.getInventory().getSize() == 9) {
            if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Górnik")) {
                e.setCancelled(true);
                p.closeInventory();

                String message = plugin.getConfig().getString("ustaw-klasa").replace("{profesja}", "górnik");

                plugin.adventure().player(p).sendMessage(cc.fix(message));

                db.updateKlasa(p.getUniqueId(), "górnik");

                NMSUTILS.totemanimation(p);


            } else if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Farmer")) {
                e.setCancelled(true);
                p.closeInventory();

                String message = plugin.getConfig().getString("ustaw-klasa").replace("{profesja}", "farmer");
                plugin.adventure().player(p).sendMessage(cc.fix(message));


                db.updateKlasa(p.getUniqueId(), "farmer");

                NMSUTILS.totemanimation(p);


            } else if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Kowal")) {
                e.setCancelled(true);
                p.closeInventory();

                String message = plugin.getConfig().getString("ustaw-klasa").replace("{profesja}", "kowal");
                plugin.adventure().player(p).sendMessage(cc.fix(message));

                db.updateKlasa(p.getUniqueId(), "kowal");

                NMSUTILS.totemanimation(p);
            } else if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Zaklinacz")) {
                e.setCancelled(true);
                p.closeInventory();

                String message = plugin.getConfig().getString("ustaw-klasa").replace("{profesja}", "zaklinacz");
                plugin.adventure().player(p).sendMessage(cc.fix(message));

                db.updateKlasa(p.getUniqueId(), "zaklinacz");

                NMSUTILS.totemanimation(p);
            } else if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Alchemik")) {
                e.setCancelled(true);
                p.closeInventory();

                String message = plugin.getConfig().getString("ustaw-klasa").replace("{profesja}", "alchemik");

                plugin.adventure().player(p).sendMessage(cc.fix(message));
                db.updateKlasa(p.getUniqueId(), "alchemik");

                NMSUTILS.totemanimation(p);
            } else if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.RED + "KLASA")) {
                e.setCancelled(true);

            }
        }
    }
}
