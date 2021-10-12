package com.thepepeyt.Klasa.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Lore {



    public List<String> setlore(String jakosc, String umiejetnosc){
        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.WHITE + "=-=--=-==-=-=-=-=-=-=--=-=-=");
        lore.add("");
        lore.add(ChatColor.WHITE + "|  "  + ChatColor.WHITE +"Jakość >> " + ChatColor.BLUE + jakosc);
        lore.add("");
        lore.add(ChatColor.WHITE + "|  "  + ChatColor.WHITE +"Umiejętność >> " + ChatColor.BLUE + umiejetnosc);
        lore.add("");
        lore.add(ChatColor.WHITE + "=-=--=-==-=-=-=-=-=-=--=-=-=");
    return lore;
    }
}
