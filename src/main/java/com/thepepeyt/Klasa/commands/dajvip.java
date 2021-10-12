package com.thepepeyt.Klasa.commands;

import com.thepepeyt.Klasa.GUI;
import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.databases.WLDatabase;
import com.thepepeyt.Klasa.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class dajvip implements CommandExecutor, Listener {

    private Main plugin;
    Database db = new Database();
    GUI inv = new GUI();
    Chat cc = new Chat();



    public dajvip(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("dajvip").setExecutor(this);

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(p.hasPermission("denoria.dajvip")) {
            Player t = Bukkit.getPlayer(args[0]);

            Boolean wybor = null;

            if(args[1].equals("tak")){
                wybor = true;
            }
            else if(args[1].equals("nie")){
                wybor = false;
            }


            db.updateVIP(p.getUniqueId(), wybor);
            String message = plugin.getConfig().getString("pomyslnie").replace("{wiadomosc}", "Pomyślnie dodano vipa");
            plugin.adventure().player(p).sendMessage(cc.fix(message));



        }
        else{
            String message = plugin.getConfig().getString("Brak-Permisji");
            plugin.adventure().player(p).sendMessage(cc.fix(message));
        }


        return true;}
    }
