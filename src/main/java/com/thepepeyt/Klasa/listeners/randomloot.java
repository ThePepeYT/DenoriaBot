package com.thepepeyt.Klasa.listeners;

import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.utils.Chat;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.loot.LootTable;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;


public class randomloot implements Listener {

    private Main plugin;
    Chat cc = new Chat();




    public randomloot(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }



    @EventHandler
    public void move(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        Random random = new Random();
        int minx = Integer.parseInt(plugin.getConfig().getString("Min-X"));
        int maxx = Integer.parseInt(plugin.getConfig().getString("Max-X"));

        int minz = Integer.parseInt(plugin.getConfig().getString("Min-Z"));
        int maxz = Integer.parseInt(plugin.getConfig().getString("Max-Z"));

        int x = random.nextInt(maxx - (minx) + 1) + (minx);
        int z = random.nextInt(maxz - (minz) + 1) + (minz);
        int y = p.getWorld().getHighestBlockAt(x, z).getY();
        Location loc = new Location(p.getWorld(), x,y,z);





        if (random.nextInt(100) == 1) {
            EnderCrystal as = (EnderCrystal) loc.getWorld().spawnEntity(loc, EntityType.ENDER_CRYSTAL);
            as.setCustomName(ChatColor.LIGHT_PURPLE + "Meteoryt");
            as.setCustomNameVisible(true);

            String message = plugin.getConfig().getString("meteoryt").replace("{kordy}", x + " " + y + " " + z);
            plugin.adventure().server(plugin.getServer().getName()).sendMessage(cc.fix(message));
        }
    }


    @EventHandler
    public void move(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        Random random = new Random();
        int minx = Integer.parseInt(plugin.getConfig().getString("Min-X"));
        int maxx = Integer.parseInt(plugin.getConfig().getString("Max-X"));

        int minz = Integer.parseInt(plugin.getConfig().getString("Min-Z"));
        int maxz = Integer.parseInt(plugin.getConfig().getString("Max-Z"));

        int x = random.nextInt(maxx - (minx) + 1) + (minx);
        int z = random.nextInt(maxz - (minz) + 1) + (minz);
        int y = p.getWorld().getHighestBlockAt(x, z).getY();
        Location loc = new Location(p.getWorld(), x,y,z);

        if (random.nextInt(100) == 1) {
            EnderCrystal as = (EnderCrystal) loc.getWorld().spawnEntity(loc, EntityType.ENDER_CRYSTAL);
            as.setCustomName(ChatColor.LIGHT_PURPLE + "Meteoryt");
            as.setCustomNameVisible(true);

            String config = plugin.getConfig().getString("meteoryt").replace("{kordy}", x + " " + y + " " + z);
            plugin.adventure().server(plugin.getServer().getName()).sendMessage(cc.fix(config));

        }
    }



    @EventHandler
    public void damage(EntityDamageEvent event){
        Entity entity = event.getEntity();

        if(entity.getType().equals(EntityType.ENDER_CRYSTAL)){
            LivingEntity en = (LivingEntity) event.getEntity();
            if(en.getCustomName().equals(ChatColor.LIGHT_PURPLE + "Meteoryt")){
                event.setCancelled(true);
                dajnagrode(en.getLocation());
                en.remove();

            }
        }
    }



    public void dajnagrode(Location loc){

        Random rand = new Random();
        ItemStack diamonds = new ItemStack(Material.DIAMOND, rand.nextInt(15));
        ItemStack gold = new ItemStack(Material.GOLD_INGOT, rand.nextInt(25));
        ItemStack iron = new ItemStack(Material.GOLD_INGOT, rand.nextInt(64));
        ItemStack potion = new ItemStack(Material.POTION, 1);



        if(rand.nextInt(2) == 1) {
            PotionMeta pm = (PotionMeta) potion.getItemMeta();
            pm.setDisplayName(ChatColor.LIGHT_PURPLE + "Eliksir Szatana");
            pm.setColor(Color.RED);
            potion.setItemMeta(pm);
            loc.getWorld().dropItem(loc, diamonds);
            loc.getWorld().dropItem(loc, potion);
            loc.getWorld().dropItem(loc, iron);
            loc.getWorld().dropItem(loc, gold);
        }
        else{
            PotionMeta pm = (PotionMeta) potion.getItemMeta();
            pm.setDisplayName(ChatColor.LIGHT_PURPLE + "Eliksir Boga");
            pm.setColor(Color.BLUE);
            potion.setItemMeta(pm);
            loc.getWorld().dropItem(loc, diamonds);
            loc.getWorld().dropItem(loc, potion);
            loc.getWorld().dropItem(loc, iron);
            loc.getWorld().dropItem(loc, gold);
        }









        }
    }














