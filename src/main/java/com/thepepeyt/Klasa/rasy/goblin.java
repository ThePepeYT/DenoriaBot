package com.thepepeyt.Klasa.rasy;

import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;


public class goblin implements Listener {

    private Main plugin;
    Database db = new Database();

    public goblin(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public HashMap<UUID, Long> cooldowns = new HashMap<UUID, Long>();
    int cooldownTime = 120;


    @EventHandler
    public void orkdmg(EntityDamageByEntityEvent e) {
        if (e.getDamager().getType().equals(EntityType.PLAYER)) {
            Player dm = (Player) e.getDamager();
            String rasa = db.getColumn(dm.getUniqueId(), "RASA");
            if (rasa.equals("goblin")) {
                LivingEntity en = (LivingEntity) e.getEntity();
                PotionEffect reg = new PotionEffect(PotionEffectType.POISON, 3*20, 0, false, false);
                PotionEffect haste = new PotionEffect(PotionEffectType.FAST_DIGGING, 6*20, 1, false, false);
                en.addPotionEffect(reg);
                en.addPotionEffect(haste);




            }
        }


    }



}
