package com.thepepeyt.Klasa.custom;

import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.utils.Lore;
import com.thepepeyt.Klasa.utils.unbreak;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Frostmourne {

    private Main plugin;

    Lore lore = new Lore();
    unbreak breakable = new unbreak();



    public ItemStack mieczor(){




        ItemStack miecz = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta mieczmeta = miecz.getItemMeta();

        mieczmeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "bron-1", 10.0, AttributeModifier.Operation.ADD_NUMBER));

        mieczmeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "bron-1", 1.0, AttributeModifier.Operation.ADD_NUMBER));

        mieczmeta.setDisplayName(ChatColor.GOLD + "Miecz Frostmourne");
        mieczmeta.setLore(lore.setlore("Mityczna", "Przy ataku zamraża przeciwnika na 0.3 sekundy"));



        mieczmeta.addEnchant(Enchantment.DURABILITY, 10, true);

        miecz.setItemMeta(mieczmeta);
        breakable.setUnbreakable(miecz);







        return miecz;
    }
}
