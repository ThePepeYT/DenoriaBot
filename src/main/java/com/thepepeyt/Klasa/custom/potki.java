package com.thepepeyt.Klasa.custom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

public class potki {

    public void eliksir() {
        ItemStack potion = new ItemStack(Material.POTION, 1);
        PotionMeta pm = (PotionMeta) potion.getItemMeta();
        pm.setDisplayName(ChatColor.LIGHT_PURPLE + "Eliksir Szatana");
        pm.setColor(Color.RED);
        potion.setItemMeta(pm);
        ShapedRecipe recipe = new ShapedRecipe(potion);

        recipe.shape(" B ", "BAB", " B ");

        recipe.setIngredient('A', Material.END_CRYSTAL);
        recipe.setIngredient('B', Material.NETHERITE_INGOT);

        Bukkit.getServer().addRecipe(recipe);
    }




    public void eliksirboga() {
        ItemStack potion = new ItemStack(Material.POTION, 1);
        PotionMeta pm = (PotionMeta) potion.getItemMeta();
        pm.setDisplayName(ChatColor.LIGHT_PURPLE + "Eliksir Boga");
        pm.setColor(Color.BLUE);
        potion.setItemMeta(pm);
        ShapedRecipe recipe = new ShapedRecipe(potion);

        recipe.shape(" A ", "ABA", " A ");

        recipe.setIngredient('A', Material.END_CRYSTAL);
        recipe.setIngredient('B', Material.NETHERITE_INGOT);

        Bukkit.getServer().addRecipe(recipe);
    }
}
