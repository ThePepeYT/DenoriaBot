package com.thepepeyt.Klasa.listeners;

import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.databases.RankDatabase;
import com.thepepeyt.Klasa.utils.Chat;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.databases.RankDatabase;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;



public class Powitanie implements Listener {

    private Main plugin;
    Database db = new Database();
    RankDatabase dbr = new RankDatabase();
    Chat cc = new Chat();

    public Powitanie(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage("");
        Player player = e.getPlayer();



        if(db.playerExists(player.getUniqueId())) {
            String rasa = db.getColumn(player.getUniqueId(), "RASA");
            switch (rasa) {

                case "demon":
                    String message = plugin.getConfig().getString("powitanie-demon").replace("{rasa}", "demon");
                    String text = PlaceholderAPI.setPlaceholders(e.getPlayer(), message);
                    plugin.adventure().players().sendMessage(cc.fix(text));
                    break;
                case "czlowiek":
                    message = plugin.getConfig().getString("powitanie-człowiek").replace("{rasa}", "człowiek");
                    text = PlaceholderAPI.setPlaceholders(e.getPlayer(), message);
                    plugin.adventure().players().sendMessage(cc.fix(text));
                    break;
                case "elf":
                    message = plugin.getConfig().getString("powitanie-elf").replace("{rasa}", "elf");
                    text = PlaceholderAPI.setPlaceholders(e.getPlayer(), message);
                    plugin.adventure().players().sendMessage(cc.fix(text));
                    break;
                case "goblin":
                    message = plugin.getConfig().getString("powitanie-goblin").replace("{rasa}", "goblin");
                    text = PlaceholderAPI.setPlaceholders(e.getPlayer(), message);
                    plugin.adventure().players().sendMessage(cc.fix(text));
                    break;
                case "ork":
                    message = plugin.getConfig().getString("powitanie-ork").replace("{rasa}", "ork");
                    text = PlaceholderAPI.setPlaceholders(e.getPlayer(), message);
                    plugin.adventure().players().sendMessage(cc.fix(text));
                    break;
                case "troll":
                    message = plugin.getConfig().getString("powitanie-troll").replace("{rasa}", "troll");
                    text = PlaceholderAPI.setPlaceholders(e.getPlayer(), message);
                    plugin.adventure().players().sendMessage(cc.fix(text));
                    break;
                case "krasnolud":
                    message = plugin.getConfig().getString("powitanie-krasnolud").replace("{rasa}", "krasnolud");
                    text = PlaceholderAPI.setPlaceholders(e.getPlayer(), message);
                    plugin.adventure().players().sendMessage(cc.fix(text));
                    break;
                case "no":
                    message = plugin.getConfig().getString("powitanie-nic").replace("{rasa}", "krasnolud");
                    text = PlaceholderAPI.setPlaceholders(e.getPlayer(), message);
                    plugin.adventure().players().sendMessage(cc.fix(text));
                    break;
            }
        }
        else{

            String message = plugin.getConfig().getString("powitanie-nic").replace("{rasa}", "krasnolud");
            String text = PlaceholderAPI.setPlaceholders(e.getPlayer(), message);
            plugin.adventure().players().sendMessage(cc.fix(text));
            }



        }
    }

