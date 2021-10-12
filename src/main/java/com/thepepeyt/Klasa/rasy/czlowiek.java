package com.thepepeyt.Klasa.rasy;

import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class czlowiek implements Listener {

    private Main plugin;
    Database db = new Database();
    Chat cc = new Chat();

    public czlowiek(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public HashMap<UUID, Long> cooldowns = new HashMap<UUID, Long>();
    int cooldownTime = 120;




    @EventHandler
    public void dmg(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {

            Player p = (Player) e.getEntity();

            if (p.getHealth() < 15.0) {
                String rasa = db.getColumn(p.getUniqueId(), "RASA");

                if (rasa.equalsIgnoreCase("czlowiek")) {
                    if (cooldowns.containsKey(p.getUniqueId())) {
                            long secondsLeft = ((cooldowns.get(p.getUniqueId()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
                        } else {
                            cooldowns.put(p.getUniqueId(), System.currentTimeMillis());
                            PotionEffect reg = new PotionEffect(PotionEffectType.REGENERATION, 5 * 20, 2, false, false);
                            p.addPotionEffect(reg);
                            String config = plugin.getConfig().getString("berserker");
                            plugin.adventure().player(p).sendMessage(cc.fix(config));

                        }
                    }
                }
            }
        }
    }












