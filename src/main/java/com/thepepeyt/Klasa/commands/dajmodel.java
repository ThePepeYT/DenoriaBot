package com.thepepeyt.Klasa.commands;

import com.thepepeyt.Klasa.GUI;
import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.custom.Ewenstara;
import com.thepepeyt.Klasa.custom.Frostmourne;
import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.utils.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class dajmodel implements CommandExecutor {

    private Main plugin;
    Database db = new Database();
    GUI inv = new GUI();
    Chat cc = new Chat();


    public dajmodel(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("dajmodel").setExecutor(this);

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;
        if(p.hasPermission("denoria.dajmodel")){
            if(args.length == 1){

                if(args[0].equals("Ewenstara")){
                    Ewenstara ewen = new Ewenstara();
                    p.getInventory().addItem(ewen.mieczor());
                    String config = plugin.getConfig().getString("pomyslnie").replace("{wiadomosc}", "Pomyślnie dodano model do ekwipunku");
                    plugin.adventure().player(p).sendMessage(cc.fix(config));


                }
                else if(args[0].equals("Frostmourne")){
                    Frostmourne ewen = new Frostmourne();
                    p.getInventory().addItem(ewen.mieczor());
                    String config = plugin.getConfig().getString("pomyslnie").replace("{wiadomosc}", "Pomyślnie dodano model do ekwipunku");
                    plugin.adventure().player(p).sendMessage(cc.fix(config));

                }
            }
            else{

                String config = plugin.getConfig().getString("zle-uzycie").replace("{poprawne}", "/dajmodel <nazwa modelu>");
                plugin.adventure().player(p).sendMessage(cc.fix(config));


            }


        }
        else{
            String message = plugin.getConfig().getString("Brak-Permisji");
            plugin.adventure().player(p).sendMessage(cc.fix(message));

        }



        return true;
    }
}

