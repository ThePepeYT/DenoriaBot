package com.thepepeyt.Klasa.custom;


import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.utils.Lore;
import com.thepepeyt.Klasa.utils.unbreak;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;

import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Ewenstara implements Listener {

    private Main plugin;

    Lore lore = new Lore();
    unbreak breakable = new unbreak();



    public ItemStack mieczor(){




        ItemStack miecz = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta mieczmeta = miecz.getItemMeta();

        mieczmeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "bron-1", 8.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));

        mieczmeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "xD", -2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));

        mieczmeta.setDisplayName(ChatColor.GOLD + "Miecz Ewenstara");
        mieczmeta.setLore(lore.setlore("Mityczna", "Dodaje pół złotego serca przy ataku"));
        mieczmeta.addEnchant(Enchantment.DURABILITY, 10, true);
        miecz.setItemMeta(mieczmeta);
        breakable.setUnbreakable(miecz);







    return miecz;
    }
}
