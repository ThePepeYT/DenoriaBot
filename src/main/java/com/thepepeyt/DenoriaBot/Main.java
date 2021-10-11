package com.thepepeyt.DenoriaBot;

import com.mewna.catnip.Catnip;
import com.mewna.catnip.CatnipOptions;
import com.mewna.catnip.entity.builder.EmbedBuilder;
import com.mewna.catnip.entity.builder.command.CommandOptionBuilder;
import com.mewna.catnip.entity.interaction.InteractionResponseType;
import com.mewna.catnip.entity.interaction.command.ApplicationCommandInteraction;
import com.mewna.catnip.entity.interaction.command.ApplicationCommandOptionType;
import com.mewna.catnip.entity.message.MessageOptions;
import com.mewna.catnip.entity.user.Presence;
import com.mewna.catnip.shard.DiscordEvent;
import com.thepepeyt.DenoriaBot.commands.*;
import com.thepepeyt.DenoriaBot.listeners.Join;
import com.thepepeyt.DenoriaBot.listeners.PomocBot;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class Main{

    public static String token = "";
    final static Catnip catnip = Catnip.catnip(token);




    public static void main(String[] args) {


        catnip.observable(DiscordEvent.READY)
                        .subscribe(ready -> {
                            catnip.presence(Presence.of(Presence.OnlineStatus.ONLINE, Presence.Activity.of("Najlepszy geopolityk", Presence.ActivityType.WATCHING)));
                        });











        catnip.loadExtension(new github("github"));
        catnip.loadExtension(new Ping("Ping"));
        catnip.loadExtension(new RasaInfo("RasaInfo"));
        catnip.loadExtension(new PomocBot("PomocBot"));
        catnip.loadExtension(new ProfesjaInfo("ProfesjaInfo"));
        catnip.loadExtension(new Ban("ban"));
        catnip.loadExtension(new Join("join"));
        catnip.loadExtension(new EmbedCreator("embed"));
        catnip.connect();

    }



}
