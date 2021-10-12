package com.thepepeyt.Klasa.custom;

import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.utils.Chat;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class sztylet implements Listener {



    private Main plugin;
    Chat cc = new Chat();


    public sztylet(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public Boolean istrue(Entity e, Player p){
        Boolean is = true;






        if(e == p){
            is = false;
        }
        if(e.getType().equals(EntityType.ARMOR_STAND)){
            is= false;
        }
        if(e.getType().equals(EntityType.DROPPED_ITEM)){
            is= false;
        }

        return is;
    }









    @EventHandler
    public void xD(final PlayerInteractEvent e) {
        final Player p = (Player) e.getPlayer();
        if(!e.hasItem()) return;
        ItemStack item = e.getItem();
        if (!item.hasItemMeta()) return;
            ItemMeta itemmeta = item.getItemMeta();
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (itemmeta.getDisplayName().equals(ChatColor.AQUA + "Sztylet")) {
                    if (percentageBar == 100.0) {
                        percentageBar = 0;

                        final Double dmg = Double.parseDouble(plugin.getConfig().getString("sztylet-dmg"));

                        final ArmorStand as = (ArmorStand) p.getWorld().spawnEntity(p.getLocation().add(0, 0.9, 0), EntityType.ARMOR_STAND);

                        as.setGravity(false);
                        as.setArms(true);
                        as.setVisible(false);
                        as.setMarker(true);

                        ItemStack sword = new ItemStack(Material.IRON_SWORD);
                        ItemMeta swordmeta = sword.getItemMeta();
                        swordmeta.setDisplayName(ChatColor.AQUA + "Sztylet");
                        sword.setItemMeta(swordmeta);
                        as.setItemInHand(sword);
                        as.setRightArmPose(new EulerAngle(Math.toRadians(90), Math.toRadians(0), Math.toRadians(0)));

                        p.getInventory().getItemInMainHand().setAmount(p.getItemInHand().getAmount() - 1);
                        Location loc = p.getLocation().add(p.getLocation().getDirection().multiply(10));
                        final Vector vector = loc.subtract(p.getLocation()).toVector();


                        new BukkitRunnable() {
                            int distance = 30;
                            int i = 1;

                            public void run() {

                                EulerAngle rotation = as.getRightArmPose();
                                EulerAngle nextrotation = rotation.add(20, 0, 0);
                                as.setRightArmPose(nextrotation);
                                as.teleport(as.getLocation().add(vector.normalize()));
                                for (Entity entity : as.getLocation().getChunk().getEntities()) {


                                    if (istrue(entity, p)) {


                                        System.out.println(entity.getType());
                                        System.out.println(entity.getLocation().distanceSquared(as.getLocation()));
                                        if (entity.getLocation().distanceSquared(as.getLocation()) <= 1) {
                                            if (entity instanceof LivingEntity) {

                                                LivingEntity en = (LivingEntity) entity;
                                                en.damage(dmg);
                                                as.remove();
                                                System.out.println("xD");
                                                cancel();

                                            } else if (e instanceof Player) {

                                                p.damage(dmg);
                                                as.remove();
                                                System.out.println("kekw");
                                                cancel();
                                            }
                                        }
                                    }

                                    if (i < distance) {
                                        i++;


                                    } else {
                                        as.remove();
                                        System.out.println("jd");
                                        cancel();
                                    }


                                }
                            }

                        }.runTaskTimer(plugin, 0L, 1L);
                    }
                }
            }
        }




    private Map<String, Integer> sneakTasks = new HashMap<String, Integer>();

    private double percentageBar = 0;

    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent event) {
        final Player player = event.getPlayer();
        if (event.isSneaking()) {
            if (player.getItemInHand() != null & player.getItemInHand().getItemMeta() != null) {
                if (player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Sztylet")) {

                    if (!this.sneakTasks.containsKey(event.getPlayer().getName())) {
                        final String playerName = player.getName();
                        this.sneakTasks.put(event.getPlayer().getName(), event.getPlayer().getServer().getScheduler().runTaskTimer(plugin, new Runnable() {

                            public void run() {
                                if (sneakTasks.containsKey(playerName)) {
                                    if (player != null) {
                                        if (player.isSneaking()) {
                                            if (percentageBar == 0 || percentageBar % 25 == 0) {


                                                String message = ChatColor.GRAY + "&7Naładowano sztylet w " + ChatColor.RED + percentageBar + ChatColor.GRAY + " &7procentach";
                                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));

                                                if (percentageBar >= 100D) {

                                                    int sneakTask = sneakTasks.remove(playerName);
                                                    Bukkit.getScheduler().cancelTask(sneakTask);
                                                    player.sendMessage("essa");
                                                    return;

                                                }
                                            }
                                            percentageBar += 12.5D;
                                        } else {
                                            int sneakTask = sneakTasks.remove(playerName);
                                            if (Bukkit.getScheduler().isQueued(sneakTask) || Bukkit.getScheduler().isCurrentlyRunning(sneakTask))
                                                Bukkit.getScheduler().cancelTask(sneakTask); // The player is not sneaking - Cancel the charge up.
                                        }
                                    } else {
                                        int sneakTask = sneakTasks.remove(playerName);
                                        if (Bukkit.getScheduler().isQueued(sneakTask) || Bukkit.getScheduler().isCurrentlyRunning(sneakTask))
                                            Bukkit.getScheduler().cancelTask(sneakTask);
                                    }
                                }
                            }
                        }, 0L, 7L).getTaskId());
                    }
                } else {
                    if (this.sneakTasks.containsKey(event.getPlayer().getName())) {
                        int sneakTask = this.sneakTasks.remove(event.getPlayer().getName());
                        if (event.getPlayer().getServer().getScheduler().isQueued(sneakTask) || event.getPlayer().getServer().getScheduler().isCurrentlyRunning(sneakTask))
                            event.getPlayer().getServer().getScheduler().cancelTask(sneakTask);
                    }
                }
            }
        }
    }



    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if (this.sneakTasks.containsKey(event.getPlayer().getName())) {
            int sneakTask = this.sneakTasks.remove(event.getPlayer().getName());
            if (event.getPlayer().getServer().getScheduler().isQueued(sneakTask) || event.getPlayer().getServer().getScheduler().isCurrentlyRunning(sneakTask)) event.getPlayer().getServer().getScheduler().cancelTask(sneakTask);
        }
    }

    @EventHandler
    public void onPlayerKicked(PlayerKickEvent event) {
        if (this.sneakTasks.containsKey(event.getPlayer().getName())) {
            int sneakTask = this.sneakTasks.remove(event.getPlayer().getName());
            if (event.getPlayer().getServer().getScheduler().isQueued(sneakTask) || event.getPlayer().getServer().getScheduler().isCurrentlyRunning(sneakTask)) event.getPlayer().getServer().getScheduler().cancelTask(sneakTask);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (this.sneakTasks.containsKey(event.getEntity().getName())) {
            int sneakTask = this.sneakTasks.remove(event.getEntity().getName());
            if (event.getEntity().getServer().getScheduler().isQueued(sneakTask) || event.getEntity().getServer().getScheduler().isCurrentlyRunning(sneakTask))
                event.getEntity().getServer().getScheduler().cancelTask(sneakTask);
        }
    }









    public void crafting() {
        ItemStack sword = new ItemStack(Material.IRON_SWORD, 4);
        ItemMeta swordmeta = sword.getItemMeta();
        swordmeta.setDisplayName(ChatColor.AQUA + "Sztylet");
        sword.setItemMeta(swordmeta);
        ShapedRecipe recipe = new ShapedRecipe(sword);

        recipe.shape("   ", "CAC", " B ");

        recipe.setIngredient('A', Material.IRON_INGOT);
        recipe.setIngredient('B', Material.GOLD_INGOT);
        recipe.setIngredient('C', Material.SPRUCE_WOOD);

        Bukkit.getServer().addRecipe(recipe);
    }

}




