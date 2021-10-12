package com.thepepeyt.Klasa.listeners;

import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class HealthBar implements Listener {

    private Main plugin;
    Chat cc = new Chat();

    public HealthBar(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }



    public static String buildHealthString(final LivingEntity p) {
        final StringBuilder sb = new StringBuilder("<gold><bold>").append(p.getName()).append(" ");
        final int hearts = (int) Math.ceil(p.getHealth() / 2);

        for(int i = 0; i < hearts; i++) {
            sb.append("<red>").append("♥"); // Represents remaining hearts, colored red.
        }
        for(int i = 10 - hearts; i > 0; i--) {
            sb.append("<black>").append("♥"); // Represents missing hearts, colored dark red.
        }

        // Same for absorption hearts.
        return sb.toString();
    }


    @EventHandler
    public void HealthBar(EntityDamageByEntityEvent e){
        Entity entity = e.getEntity();
        if(e.getDamager() instanceof Player){
            Player p = (Player) e.getDamager();
            LivingEntity en = (LivingEntity) entity;
            String bar = buildHealthString(en);

            plugin.adventure().player(p).sendActionBar(cc.fix(bar));

        }
    }
}
