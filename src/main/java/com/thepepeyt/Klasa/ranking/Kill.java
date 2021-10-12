package com.thepepeyt.Klasa.ranking;


import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.databases.RankDatabase;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.util.UUID;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Kill implements Listener {
    RankDatabase dbr = new RankDatabase();

    private Main plugin;

    public Kill(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void kill(PlayerDeathEvent e) {
        Player p = (Player) e.getEntity().getPlayer();
        int death = dbr.getVar(p.getUniqueId(), "DEATHS");
        dbr.updateVar(p.getUniqueId(), "DEATHS", death + 1);
        Player k = p.getKiller();
        int kills = dbr.getVar(k.getUniqueId(), "KILLS");
        dbr.updateVar(k.getUniqueId(), "KILLS", kills + 1);








    }
}
