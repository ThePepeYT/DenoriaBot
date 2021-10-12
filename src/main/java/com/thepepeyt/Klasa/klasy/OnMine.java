package com.thepepeyt.Klasa.klasy;

import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnMine implements Listener {



    private Main plugin;

    Database db = new Database();

    public OnMine(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    public Boolean is_true(Material block){
        Boolean is = false;
        Material diamond = Material.DIAMOND_ORE;
        Material gold = Material.GOLD_ORE;
        Material coal = Material.COAL_ORE;
        Material iron = Material.IRON_ORE;
        Material lapis = Material.LAPIS_ORE;
        Material debirs = Material.ANCIENT_DEBRIS;

        if(block.equals(diamond)){
            is = true;
        }
        else if(block.equals(gold)){
            is = true;
        }
        else if(block.equals(coal)){
            is = true;
        }
        else if(block.equals(iron)){
            is = true;
        }
        else if(block.equals(lapis)){
            is = true;
        }
        else if(block.equals(debirs)){
            is = true;
        }

    return is;
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Player p = (Player) event.getPlayer();
        Material blockType = event.getBlock().getType();
        Material diamond = Material.DIAMOND_ORE;
        Material gold = Material.GOLD_ORE;
        Material coal = Material.COAL_ORE;
        Material iron = Material.IRON_ORE;
        Material lapis = Material.LAPIS_ORE;
        Material debirs = Material.ANCIENT_DEBRIS;
        String klasa = db.getColumn(p.getUniqueId(), "KLASA");

        if (is_true(blockType)) {
            if(!(klasa.equals("górnik"))){
                event.setDropItems(false);
                event.setExpToDrop(0);

            }

        }
    }
}
