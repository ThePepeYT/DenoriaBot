package com.thepepeyt.Klasa.commands;

import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.GUI;

public class ustawklase implements CommandExecutor, Listener {

    private Main plugin;
    Database db = new Database();
    GUI inv = new GUI();
    Chat cc = new Chat();


    public ustawklase(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        plugin.getCommand("ustawklase").setExecutor(this);

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(p.hasPermission("denoria.ustawklase")) {
            p.openInventory(inv.plecgui(p));
        }
        else{
            String message = plugin.getConfig().getString("Brak-Permisji");
            plugin.adventure().player(p).sendMessage(cc.fix(message));
        }

    return true;}



}
