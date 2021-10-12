package com.thepepeyt.Klasa;

import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.databases.RankDatabase;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class GUI {


    Database db = new Database();
    RankDatabase dbr = new RankDatabase();








    //CHOOSING PLEC GUI

    public Inventory plecgui(Player p){
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.LIGHT_PURPLE + "Wybierz Profesje");

        ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS);
        ItemMeta glassmeta = glass.getItemMeta();
        glassmeta.setDisplayName(ChatColor.RED + "Profesje");
        glass.setItemMeta(glassmeta);

        for(int i=0; i<9;i++){
            inv.setItem(i, glass);

        }


        ItemStack farmer = new ItemStack(Material.WHEAT);
        ItemMeta farmermeta = farmer.getItemMeta();
        farmermeta.setDisplayName(ChatColor.GREEN + "Farmer");
        farmer.setItemMeta(farmermeta);
        inv.setItem(2, farmer);

        ItemStack gornik = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta gornikmeta = gornik.getItemMeta();
        gornikmeta.setDisplayName(ChatColor.GREEN + "Górnik");
        gornik.setItemMeta(gornikmeta);
        inv.setItem(3, gornik);

        ItemStack kowal = new ItemStack(Material.ANVIL);
        ItemMeta kowalmeta = kowal.getItemMeta();
        kowalmeta.setDisplayName(ChatColor.GREEN + "Kowal");
        kowal.setItemMeta(kowalmeta);
        inv.setItem(4, kowal);

        ItemStack zaklinacz = new ItemStack(Material.ENCHANTING_TABLE);
        ItemMeta zaklinaczmeta = zaklinacz.getItemMeta();
        zaklinaczmeta.setDisplayName(ChatColor.GREEN + "Zaklinacz");
        zaklinacz.setItemMeta(zaklinaczmeta);
        inv.setItem(5, zaklinacz);



        ItemStack alchemik = new ItemStack(Material.BREWING_STAND);
        ItemMeta alchemikmeta = alchemik.getItemMeta();
        alchemikmeta.setDisplayName(ChatColor.GREEN + "Alchemik");
        alchemik.setItemMeta(alchemikmeta);
        inv.setItem(6, alchemik);

        return inv;





    }




    public Inventory rasagui(Player p){
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.LIGHT_PURPLE + "Wybierz rase");

        ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS);
        ItemMeta glassmeta = glass.getItemMeta();
        glassmeta.setDisplayName(ChatColor.RED + "RASY");
        glass.setItemMeta(glassmeta);

        for(int i=0; i<9;i++){
            inv.setItem(i, glass);

        }


        ItemStack human = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta humanmeta = human.getItemMeta();
        humanmeta.setDisplayName(ChatColor.GREEN + "Człowiek");
        human.setItemMeta(humanmeta);
        inv.setItem(2, human);

        ItemStack gornik = new ItemStack(Material.SPECTRAL_ARROW);
        ItemMeta gornikmeta = gornik.getItemMeta();
        gornikmeta.setDisplayName(ChatColor.GREEN + "Elf");
        gornik.setItemMeta(gornikmeta);
        inv.setItem(3, gornik);

        ItemStack kowal = new ItemStack(Material.IRON_AXE);
        ItemMeta kowalmeta = kowal.getItemMeta();
        kowalmeta.setDisplayName(ChatColor.GREEN + "Krasnolud");
        kowal.setItemMeta(kowalmeta);
        inv.setItem(4, kowal);

        ItemStack zaklinacz = new ItemStack(Material.NETHER_STAR);
        ItemMeta zaklinaczmeta = zaklinacz.getItemMeta();
        zaklinaczmeta.setDisplayName(ChatColor.GREEN + "Demon");
        zaklinacz.setItemMeta(zaklinaczmeta);
        inv.setItem(5, zaklinacz);



        ItemStack alchemik = new ItemStack(Material.ZOMBIE_HEAD);
        ItemMeta alchemikmeta = alchemik.getItemMeta();
        alchemikmeta.setDisplayName(ChatColor.GREEN + "Goblin");
        alchemik.setItemMeta(alchemikmeta);
        inv.setItem(6, alchemik);


        ItemStack Troll = new ItemStack(Material.STICK);
        ItemMeta trollmeta = Troll.getItemMeta();
        trollmeta.setDisplayName(ChatColor.GREEN + "Troll");
        Troll.setItemMeta(trollmeta);
        inv.setItem(7, Troll);
        return inv;





    }









    public Inventory openinventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 36, ChatColor.RED + "Ranking");

        ItemStack diamonds = new ItemStack(Material.DIAMOND);
        ItemMeta dmeta = diamonds.getItemMeta();
        dmeta.setDisplayName(ChatColor.GREEN + "Ilosc zabitych mobów");
        int damout = dbr.getVar(p.getUniqueId(), "MOBY");
        dmeta.setLore(Arrays.asList(ChatColor.AQUA + "" + damout + " mobów"));
        diamonds.setItemMeta(dmeta);
        inv.setItem(10, diamonds);
        ////BLOCKS

        ItemStack bloki = new ItemStack(Material.STONE);
        ItemMeta bmeta = bloki.getItemMeta();
        bmeta.setDisplayName(ChatColor.GREEN + "Ilosc wykopanych blokow");
        int bamout = dbr.getVar(p.getUniqueId(), "WYKOPANE");
        bmeta.setLore(Arrays.asList(ChatColor.AQUA + "" + bamout + " blokow"));
        bloki.setItemMeta(bmeta);
        inv.setItem(16, bloki);

        /////OBSIDIAN

        ItemStack obs = new ItemStack(Material.OBSIDIAN);
        ItemMeta ometa = obs.getItemMeta();
        ometa.setDisplayName(ChatColor.GREEN + "Ilosc wykopanego obsydianu");
        int oamout = dbr.getVar(p.getUniqueId(), "OBSIDIAN");
        ometa.setLore(Arrays.asList(ChatColor.AQUA + "" + oamout + " obsa"));
        obs.setItemMeta(ometa);
        inv.setItem(20, obs);

        //////IRON_SWORD
        ItemStack kills = new ItemStack(Material.IRON_SWORD);
        ItemMeta kmeta = kills.getItemMeta();
        kmeta.setDisplayName(ChatColor.GREEN + "Ilosc zabitych graczy");
        int kamout = dbr.getVar(p.getUniqueId(), "KILLS");
        kmeta.setLore(Arrays.asList(ChatColor.AQUA + "" + kamout + " zabitych"));
        kills.setItemMeta(kmeta);
        inv.setItem(13, kills);


        ////DEATHS


        ItemStack deaths = new ItemStack(Material.WITHER_SKELETON_SKULL);
        ItemMeta demeta = deaths.getItemMeta();
        demeta.setDisplayName(ChatColor.GREEN + "Ilosc smierci");
        int deamout = dbr.getVar(p.getUniqueId(), "DEATHS");
        demeta.setLore(Arrays.asList(ChatColor.AQUA + "" + deamout + " smierci"));
        deaths.setItemMeta(demeta);
        inv.setItem(24, deaths);

        ////PLACED


        ItemStack placed = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta pmeta = placed.getItemMeta();
        pmeta.setDisplayName(ChatColor.GREEN + "Ilosc postawionych blokow");
        int pamout = dbr.getVar(p.getUniqueId(), "POSTAWIONE");
        pmeta.setLore(Arrays.asList(ChatColor.AQUA + "" + pamout + " blokow"));
        placed.setItemMeta(pmeta);
        inv.setItem(31, placed);


        ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS);
        ItemMeta gmeta = glass.getItemMeta();
        gmeta.setDisplayName(ChatColor.RED + "RANKING");
        glass.setItemMeta(gmeta);
        int[] liczby = {10, 16, 20, 13, 24, 31};

        for(int i=0;i<inv.getSize();i++){
            if(i != 10 && i!= 16 && i!=20 && i!=13 && i!=24 && i!=31){
                inv.setItem(i, glass);
            }

        }








        return inv;

    }


    public Inventory whitelistgui(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.RED + "Whitelist");

        ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS);
        ItemMeta gmeta = glass.getItemMeta();
        gmeta.setDisplayName(ChatColor.RED + "Whitelista");
        glass.setItemMeta(gmeta);

        for(int i=0;i<inv.getSize();i++){
                inv.setItem(i, glass);
            }



        ItemStack off = new ItemStack(Material.RED_WOOL);
        ItemMeta offmeta = off.getItemMeta();
        offmeta.setDisplayName(ChatColor.GREEN + "Wyłącz Whiteliste");
        off.setItemMeta(offmeta);
        inv.setItem(5, off);
        ////BLOCKS

        ItemStack on = new ItemStack(Material.GREEN_WOOL);
        ItemMeta onmeta = on.getItemMeta();
        onmeta.setDisplayName(ChatColor.GREEN + "Włącz Whiteliste");
        on.setItemMeta(onmeta);
        inv.setItem(3, on);













        return inv;

    }


    //PROFILE INFO GUI



    public Inventory vipgui(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.RED + "VIP");

        ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS);
        ItemMeta gmeta = glass.getItemMeta();
        gmeta.setDisplayName(ChatColor.RED + "VIPEK");
        glass.setItemMeta(gmeta);

        for(int i=0;i<inv.getSize();i++){
            inv.setItem(i, glass);
        }



        ItemStack off = new ItemStack(Material.STONE);
        ItemMeta offmeta = off.getItemMeta();
        offmeta.setDisplayName(ChatColor.GREEN + "Cobblestone");
        off.setItemMeta(offmeta);
        inv.setItem(5, off);
        ////BLOCKS

        ItemStack on = new ItemStack(Material.POTION);
        ItemMeta onmeta = on.getItemMeta();
        onmeta.setDisplayName(ChatColor.GREEN + "NightVision");
        on.setItemMeta(onmeta);
        inv.setItem(3, on);


        ItemStack rainbow = new ItemStack(Material.PURPLE_WOOL);
        ItemMeta rainmeta = rainbow.getItemMeta();
        rainmeta.setDisplayName(ChatColor.GREEN + "Kolorowe pisanie");
        rainbow.setItemMeta(rainmeta);
        inv.setItem(4, rainbow);











        return inv;

    }



}
