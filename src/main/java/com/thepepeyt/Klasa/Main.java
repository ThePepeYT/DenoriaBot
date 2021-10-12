package com.thepepeyt.Klasa;

import com.thepepeyt.Klasa.Sidetab.scoreboard;
import com.thepepeyt.Klasa.custom.Krwawienie;
import com.thepepeyt.Klasa.custom.potki;
import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.databases.RankDatabase;
import com.thepepeyt.Klasa.databases.WLDatabase;
import com.thepepeyt.Klasa.discord.Bot;
import com.thepepeyt.Klasa.klasy.*;
import com.thepepeyt.Klasa.listeners.*;
import com.thepepeyt.Klasa.commands.*;
import com.thepepeyt.Klasa.whitelist.*;
import com.thepepeyt.Klasa.custom.sztylet;

import com.thepepeyt.Klasa.rasy.*;

import fr.mrmicky.fastboard.FastBoard;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.minecraft.server.v1_16_R3.ItemFireworks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;


public class Main extends JavaPlugin {

    Database db = new Database();
    RankDatabase dbr = new RankDatabase();
    WLDatabase dbwl = new WLDatabase();




    private BukkitAudiences adventure;

    public BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }



    public boolean iseffect(PotionEffectType potion){
        Boolean is = false;
        if(potion.equals(PotionEffectType.WEAKNESS)){
            is = true;
        }
        else if(potion.equals(PotionEffectType.BLINDNESS)){
            is = true;
        }
        else if(potion.equals(PotionEffectType.CONFUSION)){
            is = true;
        }
        else if(potion.equals(PotionEffectType.SLOW)){
            is = true;
        }
    return is;}


    public Map<Player, Boolean> rainbow = new HashMap<Player, Boolean>();


    public BukkitAudiences getaud(){
        return this.adventure;
    }

    public Krwawienie ench = new Krwawienie(new NamespacedKey( this, "KRWAWIENIE"));




    public void onEnable() {
        this.adventure = BukkitAudiences.create(this);


        saveDefaultConfig();



        db.connect();
        dbr.connect();
        dbwl.connect();
        new HealthBar(this);
        new VIP(this);
        new Powitanie(this);
        new CreateRanking(this);
        new scoreboard(this);
        new dajvip(this);
        new dajmodel(this);
        new Drink(this);
        new randomloot(this);
        new potki().eliksir();
        new potki().eliksirboga();
        new ChatFormat(this);
        new sztylet(this);
        new sztylet(this).crafting();
        new czolgaj(this);
        new usunwl(this);
        new wl(this);
        new dodajwl(this);
        new Check(this);
        new dodajwl(this);
        new wl(this);
        new ustawrase(this);
        new czlowiek(this);
        new demon(this);
        new elf(this);
        new goblin(this);
        new krasnolud(this);
        new ork(this);
        new troll(this);
        new CreateTable(this);
        new Anvil(this);
        new Breed(this);
        new Brewning(this);
        new Enchant(this);
        new OnMine(this);
        new Smelt(this);
        new ustawklase(this);
        new Inv(this);
        new Bot(this);
        new me(this);
        scoreboard sc = new scoreboard(this);



        Bukkit.getServer().getScheduler().runTaskTimer(this, () -> {
            for(Player p : Bukkit.getOnlinePlayers()){
                if(db.getColumn(p.getUniqueId(), "RASA").equals("demon")){
                    for(PotionEffect potion : p.getActivePotionEffects()){
                        if(iseffect(potion.getType())){
                            p.getActivePotionEffects().remove(potion);
                        }
                    }
                }
            }
            for (FastBoard board : sc.boards.values()) {
                sc.updateBoard(board, board.getPlayer());
            }
        }, 0, 3 * 20);
    }










    //DISCONECCTIN DATABASE

    @Override
    public void onDisable() {
        db.disconnect();
        dbwl.disconnect();
        dbr.disconnect();

        super.onDisable();
        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }
}

