package com.thepepeyt.Klasa.whitelist;

import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.databases.RankDatabase;
import com.thepepeyt.Klasa.databases.WLDatabase;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;


public class Check implements Listener {

    private Main plugin;

    WLDatabase dbwl = new WLDatabase();



    public Check(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }




    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player p = event.getPlayer();
        Boolean whitelisted = dbwl.isWhitelisted(p.getName());
        if (plugin.getConfig().getBoolean("whitelist")) {
            if(whitelisted.equals(false)) {
                event.setKickMessage("Nie jesteś na whiteliście");
                event.setResult(PlayerLoginEvent.Result.KICK_WHITELIST);
            }
        }

    }

}