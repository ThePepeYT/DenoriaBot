package com.thepepeyt.Klasa.commands;

import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.databases.WLDatabase;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import com.thepepeyt.Klasa.utils.Chat;

import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.GUI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dodajwl implements CommandExecutor, Listener {

    private Main plugin;
    WLDatabase dbwl = new WLDatabase();
    Chat cc = new Chat();


    public dodajwl(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        plugin.getCommand("dodajwl").setExecutor(this);

    }



    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (p.hasPermission("denoria.dodajwl")) {
            if (args.length != 1) {
                String config = plugin.getConfig().getString("zle-uzycie");
                String message = config.replace("{poprawne}", "/dodajwl <gracz>");
                plugin.adventure().player(p).sendMessage(cc.fix(message));


            } else {
                dbwl.dodajwhitelist(args[0]);
                String config = plugin.getConfig().getString("wl-dodaj");
                String message = config.replace("{player}", args[0]);
                plugin.adventure().player(p).sendMessage(cc.fix(message));



            }



        }
        else{
            String message = plugin.getConfig().getString("Brak-permisji");
            plugin.adventure().player(p).sendMessage(cc.fix(message));
        }



    return true;}
}
