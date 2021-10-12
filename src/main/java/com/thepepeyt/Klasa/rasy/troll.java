package com.thepepeyt.Klasa.rasy;

import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class troll implements Listener {

    private Main plugin;
    Database db = new Database();

    public troll(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void orkdmg(EntityDamageByEntityEvent e) {
        if (e.getDamager().getType().equals(EntityType.PLAYER)) {
            Player dm = (Player) e.getDamager();
            String rasa = db.getColumn(dm.getUniqueId(), "RASA");
            if (rasa.equals("troll")) {
                LivingEntity en = (LivingEntity) e.getEntity();
                PotionEffect reg = new PotionEffect(PotionEffectType.SLOW, 2*20, 0, false, false);
                PotionEffect nausea = new PotionEffect(PotionEffectType.CONFUSION, 2*20, 200, false, false);
                en.addPotionEffect(reg);
                en.addPotionEffect(nausea);
                if(dm.getInventory().getItemInHand().getType().equals(Material.IRON_AXE)){
                    PotionEffect haste = new PotionEffect(PotionEffectType.FAST_DIGGING, 4*20, 1, false, false);
                    en.addPotionEffect(haste);

                }




            }
        }


    }
}
