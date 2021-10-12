package com.thepepeyt.Klasa.klasy;

import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityBreedEvent;

public class Breed implements Listener {

    private Main plugin;
    Database db = new Database();

    public Breed(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public boolean isblock(Material block){
        Boolean is = false;
        if(block.equals(Material.CARROTS)){
            is = true;
        }
        else if(block.equals(Material.BEETROOTS)){
            is = true;
        }
        else if(block.equals(Material.WHEAT)){
            is = true;
        }
        else if(block.equals(Material.POTATOES)){
            is = true;
        }
        else if(block.equals(Material.SWEET_BERRY_BUSH)){
            is = true;
        }
    return is;
    }

    @EventHandler
    public void breed(EntityBreedEvent e){
        Player p = (Player) e.getBreeder();
        String klasa = db.getColumn(p.getUniqueId(), "KLASA");
        if(!(klasa.equals("farmer"))){
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void breakblock(BlockBreakEvent e){
        Player p = (Player) e.getPlayer();
        if(isblock(e.getBlock().getType())){
            String klasa = db.getColumn(p.getUniqueId(), "KLASA");
            if(!(klasa.equals("farmer"))){
                e.setDropItems(false);
            }
        }

    }
}
