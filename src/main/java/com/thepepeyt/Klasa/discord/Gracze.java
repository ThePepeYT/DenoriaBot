package com.thepepeyt.Klasa.discord;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

public class Gracze extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        try {
            if (event.getMessage().getContentRaw().equals("?gracze")) {
                final String[] gracze = {""};
                Bukkit.getOnlinePlayers().forEach(x -> gracze[0] += " " + x.getName());
                event.getTextChannel().sendMessage(gracze[0]).queue();


            }
        }
        catch (Exception e){}
    }
}
