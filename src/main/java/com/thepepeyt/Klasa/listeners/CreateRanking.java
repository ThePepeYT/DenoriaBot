package com.thepepeyt.Klasa.listeners;

import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.databases.RankDatabase;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;


public class CreateRanking implements Listener {

    private Main plugin;
    Database db = new Database();
    RankDatabase dbr = new RankDatabase();

    public CreateRanking(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerLoginEvent e) {
        Player player = e.getPlayer();
        if (dbr.playerExists(player.getUniqueId()) == false){
            dbr.createPlayer(player.getUniqueId());
        }


    }

}