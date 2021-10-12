package com.thepepeyt.DenoriaBot.listeners;

import com.mewna.catnip.entity.builder.component.ButtonBuilder;
import com.mewna.catnip.entity.message.Embed;
import com.mewna.catnip.entity.message.Message;
import com.mewna.catnip.entity.message.MessageOptions;
import com.mewna.catnip.entity.message.MessageType;
import com.mewna.catnip.entity.message.component.ActionRow;
import com.mewna.catnip.entity.message.component.Button;
import com.mewna.catnip.entity.message.component.MessageComponent;
import com.mewna.catnip.entity.util.Permission;
import com.mewna.catnip.extension.AbstractExtension;
import com.mewna.catnip.shard.DiscordEvent;
import com.thepepeyt.DenoriaBot.Util;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

import java.util.concurrent.TimeUnit;

public class PomocBot extends AbstractExtension {

    public PomocBot(String name) {
        super(name);
        System.out.println("PomocBot registered");

    }

    @Override
    public Completable onLoaded() {


        observable(DiscordEvent.MESSAGE_CREATE)
                .filter(msg -> msg.content().toLowerCase().indexOf(" ip") != -1 || msg.content().toLowerCase().indexOf(" wersja") != -1)
                .subscribe(msg -> {

                    Embed embed = Util.createEmbed("Denoria.pl | Pomoc", msg.author().username() + " nasze IP to **mc.denoria.pl**{LINIA}Gramy na wersji **1.16.5**", "Denoria.pl", msg.timestamp());

                    ActionRow button = Util.createButton("\uD83D\uDC95", true, Button.ButtonStyle.PRIMARY, "pingid");



                    msg.respond(new MessageOptions().embed(embed).addComponent(button)).delaySubscription(12, TimeUnit.SECONDS).subscribe(x -> {
                        catnip().rest().channel().deleteMessage(x.channelId(), x.id()).subscribe();
                    });
                });

        observable(DiscordEvent.MESSAGE_CREATE)
                .filter(msg -> msg.content().toLowerCase().indexOf("kto pytał") != -1)
                .subscribe(msg -> {

                    Embed embed = Util.createEmbed("Denoria.pl | Pomoc", msg.author().username() + " nikt nie musiał zjebie", "Denoria.pl", msg.timestamp());

                    ActionRow button = Util.createButton("\uD83D\uDC95", true, Button.ButtonStyle.PRIMARY, "pingid");



                    msg.respond(new MessageOptions().embed(embed).addComponent(button)).delaySubscription(12, TimeUnit.SECONDS).subscribe(x -> {
                        catnip().rest().channel().deleteMessage(x.channelId(), x.id()).subscribe();
                    });
                });


        observable(DiscordEvent.MESSAGE_CREATE)
                .filter(msg -> msg.content().toLowerCase().indexOf("discord.gg/") != -1)
                .filter(msg -> !msg.member().hasPermissions(Permission.MANAGE_MESSAGES))
                .subscribe(msg -> {

                    msg.delete().subscribe();

                    Embed embed = Util.createEmbed("Denoria.pl | AntyLink", msg.author().username() + " nie ładnie wysyłać tak linków do innych discordów", "Denoria.pl", msg.timestamp());

                    ActionRow button = Util.createButton("WRRR", true, Button.ButtonStyle.DANGER, "pingid");



                    msg.respond(new MessageOptions().embed(embed).addComponent(button)).delaySubscription(12, TimeUnit.SECONDS).subscribe(x -> {
                        catnip().rest().channel().deleteMessage(x.channelId(), x.id()).subscribe();
                    });
                });
    return super.onLoaded();
    }



}
