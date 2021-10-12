package com.thepepeyt.Klasa.rasy;

import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class krasnolud implements Listener {

    private Main plugin;
    Database db = new Database();

    public krasnolud(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    Chat cc = new Chat();



    public Boolean is_axe(ItemStack item){
        Boolean is  = false;
        if(item.equals(Material.IRON_AXE)){
            is = true;
        }
        else if (item.equals(Material.DIAMOND_AXE)){
            is = true;
        }
        else if(item.equals(Material.NETHERITE_AXE)){
            is = true;
        }
        else if(item.equals(Material.STONE_AXE)){
            is = true;
        }
        else if(item.equals(Material.WOODEN_AXE)){
            is = true;
        }
    return is;}



    public HashMap<UUID, Long> cooldowns = new HashMap<UUID, Long>();
    int cooldownTime = 120;




    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();


        ItemStack item = p.getItemInHand();
        String rasa = db.getColumn(p.getUniqueId(), "RASA");
        if (rasa.equals("krasnolud")) {

            PotionEffect reg = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2*20, 0, false, false);

            p.addPotionEffect(reg);


        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {

        if((e.getDamager() instanceof Player) & (e.getEntity() instanceof Player)){
            Player d = (Player) e.getDamager();
            Player p = (Player) e.getEntity();
            if(p.isBlocking()){
                if(is_axe(d.getItemInHand())){
                    String rasa = db.getColumn(p.getUniqueId(), "RASA");
                    if(rasa.equals("krasnolud")){
                        if (cooldowns.containsKey(p.getUniqueId())) {
                            long secondsLeft = ((cooldowns.get(p.getUniqueId()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
                        } else {
                            cooldowns.put(p.getUniqueId(), System.currentTimeMillis());
                            PotionEffect reg = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 5 * 20, 1, false, false);
                            p.addPotionEffect(reg);
                            String config = plugin.getConfig().getString("berserker");
                            plugin.adventure().player(p).sendMessage(cc.fix(config));

                        }

                    }
                }
            }
        }
    }

















}
