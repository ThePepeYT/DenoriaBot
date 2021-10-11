package com.thepepeyt.DenoriaBot.listeners;

import com.mewna.catnip.entity.builder.component.ButtonBuilder;
import com.mewna.catnip.entity.message.Embed;
import com.mewna.catnip.entity.message.Message;
import com.mewna.catnip.entity.message.MessageOptions;
import com.mewna.catnip.entity.message.MessageType;
import com.mewna.catnip.entity.message.component.ActionRow;
import com.mewna.catnip.entity.message.component.Button;
import com.mewna.catnip.entity.message.component.MessageComponent;
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
                .filter(msg -> msg.content().toLowerCase().indexOf(" ip") != -1)
                .subscribe(msg -> {

                    Embed embed = Util.createEmbed("Denoria.pl | Pomoc", msg.author().username() + " nasze IP to **mc.denoria.pl**", "Denoria.pl", msg.timestamp());

                    ActionRow button = Util.createButton("\uD83D\uDC95", true, Button.ButtonStyle.PRIMARY, "pingid");



                    msg.respond(new MessageOptions().embed(embed).addComponent(button)).delaySubscription(5, TimeUnit.SECONDS).subscribe(x -> {
                        catnip().rest().channel().deleteMessage(x.channelId(), x.id()).subscribe();
                    });
                });
    return super.onLoaded();
    }
}
