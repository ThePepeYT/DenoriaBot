package com.thepepeyt.Klasa.ranking;

import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.databases.RankDatabase;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.util.UUID;
import org.bukkit.event.block.BlockBreakEvent;

public class Mine implements Listener {
    RankDatabase dbr = new RankDatabase();

    private Main plugin;

    public Mine(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void mine(BlockBreakEvent e){
        Player p = (Player) e.getPlayer();

        int blocks = dbr.getVar(p.getUniqueId(), "WYKOPANE");
        dbr.updateVar(p.getUniqueId(), "WYKOPANE", blocks + 1);
        if(e.getBlock().getType() == Material.OBSIDIAN) {
            int obs = dbr.getVar(p.getUniqueId(), "OBSIDIAN");
            dbr.updateVar(p.getUniqueId(), "OBSIDIAN", obs + 1);
        }

    }
}
