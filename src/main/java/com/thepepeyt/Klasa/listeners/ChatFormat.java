package com.thepepeyt.Klasa.listeners;

import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.rasy.VIP;
import com.thepepeyt.Klasa.utils.Chat;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormat implements Listener {

    private Main plugin;
    Database db = new Database();
    Chat cc = new Chat();


    public ChatFormat(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }



    @EventHandler(priority = EventPriority.HIGHEST)
    public void chat(AsyncPlayerChatEvent event){
        event.setCancelled(true);
        Player p = event.getPlayer();
        if(plugin.rainbow.containsKey(p)) {
            String ez = plugin.getConfig().getString("vip-format").replace("{player}", event.getPlayer().getName());
            String message = "<rainbow>" + event.getMessage() + "</rainbow>";
            plugin.adventure().players().sendMessage(cc.fix(ez.replace("{wiadomosc}", message)));
        }
        else{
                String format = plugin.getConfig().getString("chat-format").replace("{player}", p.getName());
                String message = format.replace("{wiadomosc}", event.getMessage());
                String text = PlaceholderAPI.setPlaceholders(event.getPlayer(), message);

                plugin.adventure().players().sendMessage(cc.fix(text));
            }

    }
}

