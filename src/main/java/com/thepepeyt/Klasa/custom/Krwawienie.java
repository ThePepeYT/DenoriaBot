package com.thepepeyt.Klasa.custom;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;


public class Krwawienie extends Enchantment implements Listener {

    public Krwawienie(NamespacedKey id) {
        super(id);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();

            ItemStack mainhand = player.getInventory().getItemInMainHand();

            if (mainhand.containsEnchantment(this)) {
                player.getWorld().createExplosion(event.getEntity().getLocation(), 1, false);
            }
        }
    }




    @Override
    public boolean canEnchantItem(ItemStack arg0) {
        return arg0.getType().equals(Material.DIAMOND_SWORD) | arg0.getType().equals(Material.NETHERITE_SWORD) | arg0.getType().equals(Material.IRON_SWORD);
    }

    @Override
    public boolean conflictsWith(Enchantment arg0) {
        return false;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.WEAPON;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public String getName() {
        return "Krwawienie";
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }
}



