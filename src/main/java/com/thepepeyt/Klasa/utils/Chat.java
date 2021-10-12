package com.thepepeyt.Klasa.utils;

import com.thepepeyt.Klasa.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.ChatColor;

public class Chat {

    private Main plugin;




    public static Component fix(String text) {




        return MiniMessage.markdown().parse(text.replace(">>", "»").replace("<<", "«").replace("{O}", "\u2022").replace("{NOWALINIA}", "\n"));



    }

    public static String fixchat(String text) {

        return ChatColor.translateAlternateColorCodes('&', text.replace(">>", "»").replace("<<", "«").replace("*", "¢").replace("{O}", "\u2022").replace("{NEWLINE}", "\n"));



    }

}
