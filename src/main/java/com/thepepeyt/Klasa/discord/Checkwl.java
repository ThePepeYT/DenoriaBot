package com.thepepeyt.Klasa.discord;


import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.databases.WLDatabase;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;


public class Checkwl extends ListenerAdapter {

    WLDatabase wldb = new WLDatabase();

    private JDA jda;



    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        if(args[0].equals("?sprawdzwl")){

            if(args[1].equals(null)){
                event.getTextChannel().sendMessage("Podaj nazwe użytkownika").queue();



            }

            else{
                if(wldb.isWhitelisted(args[1])){
                    event.getTextChannel().sendMessage("Użytkownik o nazwie " + args[1] + " jest na whiteliście").queue();
                }
                else{
                    event.getTextChannel().sendMessage("Użytkownik o nazwie " + args[1] + " nie jest na whiteliście").queue();
                }


            }

        }
    }




}
