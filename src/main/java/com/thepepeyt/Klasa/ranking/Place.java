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
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPlaceEvent;

public class Place implements Listener {
    RankDatabase dbr = new RankDatabase();

    private Main plugin;

    public Place(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void place(BlockPlaceEvent e){
        Player p = (Player) e.getPlayer();

        int blocks = dbr.getVar(p.getUniqueId(), "POSTAWIONE");
        dbr.updateVar(p.getUniqueId(), "POSTAWIONE", blocks + 1);
    }
}
