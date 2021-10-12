package com.thepepeyt.Klasa.commands;

import com.thepepeyt.Klasa.GUI;
import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.databases.WLDatabase;
import com.thepepeyt.Klasa.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class usunwl implements CommandExecutor {



    private Main plugin;
    WLDatabase dbwl = new WLDatabase();

    Chat cc = new Chat();


    public usunwl(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("usunzwl").setExecutor(this);

    }





    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (p.hasPermission("denoria.usunwl")) {
            if (args.length != 1) {
                String config = plugin.getConfig().getString("zle-uzycie");
                String message = config.replace("{poprawne}", "/usunwl <gracz>");

                plugin.adventure().player(p).sendMessage(cc.fix(message));


            } else {
                dbwl.usunwhtelist(args[0]);
                String config = plugin.getConfig().getString("wl-usun");
                String message = config.replace("{player}", args[0]);
                plugin.adventure().player(p).sendMessage(cc.fix(message));



            }


        }else{
            String message = plugin.getConfig().getString("Brak-permisji");
            plugin.adventure().player(p).sendMessage(cc.fix(message));
        }




        return true;}
}
