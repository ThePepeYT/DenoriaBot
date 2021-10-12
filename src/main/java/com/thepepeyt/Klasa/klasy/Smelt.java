package com.thepepeyt.Klasa.klasy;

import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.Main;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Smelt implements Listener {

    private Main plugin;
    Database db = new Database();

    public Smelt(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    private Boolean is_of(Material item) {
        Boolean is = false;


        if (item.equals(Material.GOLD_ORE)) {
            is = true;
        } else if (item.equals(Material.IRON_ORE)) {
            is = true;
        } else if (item.equals(Material.ANCIENT_DEBRIS)) {
            is = true;
        }
        return is;
    }

    Map<Player, Player> calls=new HashMap<Player, Player>();





    @EventHandler
    public void SmeltAsNuggets(BlockCookEvent event) {
        ItemStack smeltedBlocks = event.getResult();
        if(is_of(event.getSource().getType())){
            if(event.getBlock().getType().equals(Material.FURNACE)){
                event.setCancelled(true);
            }

        }



    }






}


