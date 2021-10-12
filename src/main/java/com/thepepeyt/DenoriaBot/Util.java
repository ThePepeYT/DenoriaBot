package com.thepepeyt.DenoriaBot;

import com.mewna.catnip.Catnip;
import com.mewna.catnip.entity.builder.EmbedBuilder;
import com.mewna.catnip.entity.builder.component.ActionRowBuilder;
import com.mewna.catnip.entity.builder.component.ButtonBuilder;
import com.mewna.catnip.entity.guild.Member;
import com.mewna.catnip.entity.impl.message.component.ButtonImpl;
import com.mewna.catnip.entity.interaction.InteractionResponseType;
import com.mewna.catnip.entity.message.Embed;

import com.mewna.catnip.entity.message.Message;
import com.mewna.catnip.entity.message.component.ActionRow;
import com.mewna.catnip.entity.message.component.Button;
import com.mewna.catnip.entity.message.component.MessageComponent;


import java.awt.*;
import java.sql.Struct;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.temporal.TemporalAccessor;

public class Util {

    static String url = "https://images-ext-1.discordapp.net/external/ZoadJiMiYySqWzsJ_PNU8Rrrt2B3CwC25OS4vOCbPVs/https/cdn.discordapp.com/icons/824373754083672124/b3da5250abf4e399d1d939bb28d09277.png";

    public static String[] getArgs(String bruh){
        return bruh.split(" ");

    }

    public static Embed createEmbed(String author, String desc, String foot, OffsetDateTime e){

        return  new EmbedBuilder()
                .author(author)
                .description(desc.replace("{LINIA}", "\n"))
                .footer(foot, url)
                .color(Color.orange)
                .timestamp(e)
                .build();
    }

    public static ActionRow createButton(String label, boolean disabled, Button.ButtonStyle e, String id){
           MessageComponent button = new ButtonBuilder()
                    .label(label)
                    .disabled(disabled)
                    .style(e)
                    .customId(id)
                    .build();
            return new ActionRowBuilder()
                    .addComponent(button)
                    .build();
    }

    public static ActionRow createurlButton(String label, String id, String url){
        MessageComponent button = new ButtonBuilder()
                .label(label)
                .style(Button.ButtonStyle.LINK)
                .url(url)
                .build();
        return new ActionRowBuilder()
                .addComponent(button)
                .build();
    }





    public static Embed createBiggerEmbed(String author, String desc, OffsetDateTime e, String Image, String title){



        return  new EmbedBuilder()
                .author(author)
                .description(desc.replace("{LINIA}", "\n"))
                .footer("Denoria.pl", url)
                .image(Image)
                .title(title)
                .color(Color.orange)
                .timestamp(e)
                .build();
    }

    public static boolean isDatabase(Message message){
        boolean is = true;
        if(!Database.playerExists(message.author().id())){
            Embed embed = createEmbed("Denoria.pl | Ekonomia", "Musisz założyć konto bankowe użyj komendy ```?zalozkonto```", "denoria.pl", message.timestamp());
            message.respond(embed);
            is = false;
        }
        return is;
    }

    public static int randomNumber(int first, int second){
        return ((int) (Math.random() * (first - second))) + second;
    }
}
