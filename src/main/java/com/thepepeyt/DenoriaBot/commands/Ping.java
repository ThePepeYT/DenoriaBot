package com.thepepeyt.DenoriaBot.commands;

import com.mewna.catnip.entity.builder.EmbedBuilder;
import com.mewna.catnip.entity.interaction.Interaction;
import com.mewna.catnip.entity.interaction.InteractionResponseType;
import com.mewna.catnip.entity.interaction.command.ApplicationCommandInteraction;
import com.mewna.catnip.entity.message.Embed;
import com.mewna.catnip.entity.message.Message;
import com.mewna.catnip.entity.message.MessageOptions;
import com.mewna.catnip.extension.AbstractExtension;

 
import com.mewna.catnip.shard.DiscordEvent;

import com.thepepeyt.DenoriaBot.Util;
import io.reactivex.rxjava3.core.Completable;

import java.awt.*;
import java.util.Objects;


public class Ping extends AbstractExtension{


    public Ping(String name) {
        super(name);
        System.out.println("Ping registered");

    }


    @Override
    public Completable onLoaded() {


        observable(DiscordEvent.MESSAGE_CREATE)
                .filter(msg -> msg.content().equals("?ping"))
                .subscribe(msg -> {

                    long start = System.currentTimeMillis();
                    msg.reply("Sprawdzam ping daj chwilke", true)
                            .subscribe(ping -> {
                                long end = System.currentTimeMillis();
                                long ms = end - start;
                                Embed embed = Util.createEmbed("Denoria.pl | Ping", "Ping bota wynosi " + ms + "ms", "Denoria.PL", msg.timestamp());
                                ping.edit(embed);
                            });
                }, error -> error.printStackTrace());


        return super.onLoaded();
    }


}




