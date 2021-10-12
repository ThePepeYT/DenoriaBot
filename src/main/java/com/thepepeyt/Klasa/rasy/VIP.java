package com.thepepeyt.Klasa.rasy;

import com.thepepeyt.Klasa.GUI;
import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class VIP implements CommandExecutor, Listener {



    Database db = new Database();
    GUI inv = new GUI();
    private Main plugin;
    Chat cc = new Chat();


    public VIP(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("vip").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    Map<Player, Boolean> cobblestone = new HashMap<Player, Boolean>();
    Map<Player, Boolean> nightvision = new HashMap<Player, Boolean>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        Boolean xD = db.getVIP(p.getUniqueId());

        if (xD.equals(true)) {
            p.openInventory(inv.vipgui(p));

        } else {
            String message = plugin.getConfig().getString("Brak-Permisji");

            plugin.adventure().player(p).sendMessage(cc.fix(message));


        }


        return true;
    }


    @EventHandler
    public void brik(BlockBreakEvent e){
        if(e.getBlock().getType().equals(Material.STONE)){
            if(cobblestone.containsKey(e.getPlayer())){
                e.setDropItems(false);
            }
        }
    }





    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        final ItemStack clickedItem = e.getCurrentItem();
        if (e.getInventory().getSize() == 9) {
            if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Cobblestone")) {
                e.setCancelled(true);
                p.closeInventory();
                if (cobblestone.containsKey(p)) {
                    cobblestone.remove(p);
                } else {
                    cobblestone.put(p, true);
                }

            } else if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Nightvision")) {
                e.setCancelled(true);
                p.closeInventory();
                if (nightvision.containsKey(p)) {
                    nightvision.remove(p);
                    PotionEffect effect = new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 255, false, false);
                    p.addPotionEffect(effect);
                } else {
                    nightvision.put(p, true);
                    p.removePotionEffect(PotionEffectType.NIGHT_VISION);
                }


            } else if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Kolorowe pisanie")) {
                e.setCancelled(true);
                p.closeInventory();
                if (plugin.rainbow.containsKey(p)) {
                    nightvision.remove(p);
                } else {
                    plugin.rainbow.put(p, true);

                }


            } else if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.RED + "VIPEK")) {
                e.setCancelled(true);

            }
        }
    }






    }









