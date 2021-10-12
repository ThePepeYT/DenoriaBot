package com.thepepeyt.Klasa.rasy;

import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class ork implements Listener {

    private Main plugin;
    Database db = new Database();

    public ork(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public HashMap<UUID, Long> cooldowns = new HashMap<UUID, Long>();
    int cooldownTime = 120;
    Chat cc = new Chat();


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();


        ItemStack item = p.getItemInHand();
        String rasa = db.getColumn(p.getUniqueId(), "RASA");
        if (rasa.equals("ork")) {

            PotionEffect reg = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2 * 20, 0, false);

            p.addPotionEffect(reg);


        }
    }



    @EventHandler
    public void dmg(EntityDamageEvent e) {
        if (e.getEntityType().equals(EntityType.PLAYER)) {

            if (e.getFinalDamage() == 15.0) {
                String rasa = db.getColumn(e.getEntity().getUniqueId(), "RASA");

                if (rasa.equalsIgnoreCase("ork")) {
                    if (cooldowns.containsKey(e.getEntity().getUniqueId())) {
                            long secondsLeft = ((cooldowns.get(e.getEntity().getUniqueId()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
                        } else {
                            cooldowns.put(e.getEntity().getUniqueId(), System.currentTimeMillis());
                            PotionEffect reg = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5 * 20, 1, false, false);
                            Player en = (Player) e;
                            en.addPotionEffect(reg);
                            String config = plugin.getConfig().getString("berserker");
                            plugin.adventure().player(en).sendMessage(cc.fix(config));

                        }
                    }
                }
            }
        }
    }




