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

public class elf implements Listener {

    private Main plugin;
    Database db = new Database();

    Chat cc = new Chat();
    public elf(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }








    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();


        ItemStack item = p.getItemInHand();
        String rasa = db.getColumn(p.getUniqueId(), "RASA");
        if (rasa.equals("elf")) {

            PotionEffect speedI = new PotionEffect(PotionEffectType.SPEED, 2*20, 0, false, false);
            p.addPotionEffect(speedI);

        }
    }





    @EventHandler
    public void lavadmg(EntityDamageEvent e){
        if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
            if(e.getEntity() instanceof Player){
                Player p = ((Player) e.getEntity()).getPlayer();
                String rasa = db.getColumn(p.getUniqueId(), "RASA");
                if(rasa.equals("elf")){
                    e.setCancelled(true);
                    LivingEntity en = (LivingEntity) p;
                    p.damage(e.getDamage() * 0.5);
                    PotionEffect reg = new PotionEffect(PotionEffectType.SPEED, 5*20, 1, false, false);
                    String config = plugin.getConfig().getString("berserker");
                    plugin.adventure().player(p).sendMessage(cc.fix(config));
                }
            }

        }



    }


    private Boolean is_true(EntityType damager) {
        Boolean is = false;

        if (damager.equals(EntityType.ARROW)) {
            is = true;

        }
        else if (damager.equals(EntityType.SPECTRAL_ARROW)) {

        }
            return is;
    }


    @EventHandler
    public void arrow(EntityDamageByEntityEvent e){
        EntityType damager = e.getDamager().getType();

        if(is_true(damager)){
            Arrow arrow = (Arrow) e.getDamager();
            Entity entity = (Entity) arrow.getShooter();
            if(entity instanceof Player){
                Player p = (Player) arrow.getShooter();
                String rasa = db.getColumn(p.getUniqueId(), "rasa");
                if(rasa.equals("elf")){
                    PotionEffect speedI = new PotionEffect(PotionEffectType.SPEED, 5*20, 1, false, false);
                    p.addPotionEffect(speedI);

                }


            }


        }
    }


}
