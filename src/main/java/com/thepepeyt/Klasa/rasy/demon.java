package com.thepepeyt.Klasa.rasy;

import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.utils.Chat;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class demon implements Listener {

    private Main plugin;
    Database db = new Database();
    Chat cc = new Chat();

    public demon(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static List<String> playerList = new ArrayList<String>();


    private Boolean is_true(EntityDamageEvent.DamageCause e) {
        Boolean is = false;
        if (e.equals(EntityDamageEvent.DamageCause.LAVA)) {
            is = true;
        } else if (e.equals(EntityDamageEvent.DamageCause.FIRE_TICK)) {
            is = true;
        } else if (e.equals(EntityDamageEvent.DamageCause.POISON)) {
            is = true;
        } else if (e.equals(EntityDamageEvent.DamageCause.FIRE)) {
            is = true;
        }
        else if (e.equals(EntityDamageEvent.DamageCause.WITHER)){
            is = true;
        }
        return is;
    }


    @EventHandler
    public void lavadmg(EntityDamageEvent e) {
        if (is_true(e.getCause())) {
            if (e.getEntity() instanceof Player) {
                Player p = ((Player) e.getEntity()).getPlayer();
                String rasa = db.getColumn(p.getUniqueId(), "RASA");
                if (rasa.equals("demon")) {
                    e.setCancelled(true);
                }
            }
        }
    }
    Map<UUID, Long> map = new HashMap<>();


    @EventHandler
    public void shift(PlayerToggleSneakEvent e) {
        if (!e.isSneaking()) return;
        Player p = e.getPlayer();
        UUID id = e.getPlayer().getUniqueId();
        String rasa = db.getColumn(p.getUniqueId(), "RASA");
        if (rasa.equalsIgnoreCase("demon")) {
            if (map.containsKey(id)) {
                long time = System.currentTimeMillis();
                if (map.get(id) > time - 1000) {
                    if (playerList.contains(e.getPlayer().getName())) {
                        playerList.remove(e.getPlayer().getName());
                        String config = plugin.getConfig().getString("pomyslnie").replace("{wiadomosc}", "Pomyślnie wyłączono tryb chodzenia po lavie");
                        plugin.adventure().player(p).sendMessage(cc.fix(config));

                    } else {
                        playerList.add(e.getPlayer().getName());
                        String config = plugin.getConfig().getString("pomyslnie").replace("{wiadomosc}", "Pomyślnie włączono tryb chodzenia po lavie");
                        plugin.adventure().player(p).sendMessage(cc.fix(config));
                    }

                }

            }
            map.put(id, System.currentTimeMillis());
        }else{
            return;        }
    }


    @EventHandler
    public void lavawalking(PlayerMoveEvent event) {
        Player player = event.getPlayer();


        if (!playerList.contains(player.getName()))
            return;
        else {
            if (event.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) {
                return;
            } else {
                Location location = player.getLocation();
                location.setY(location.getY() - 1);
                final Block blok = location.getWorld().getBlockAt(location);
                if (blok.getType() == Material.LAVA) {
                    blok.setType(Material.OBSIDIAN);
                    Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                        @Override
                        public void run() {
                            blok.setType(Material.LAVA);
                        }
                    }, 80L);
                }
            }
        }
    }


}






