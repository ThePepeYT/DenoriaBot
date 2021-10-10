package com.thepepeyt.DenoriaBot.commands;

import com.mewna.catnip.entity.message.Embed;
import com.mewna.catnip.extension.AbstractExtension;
import com.mewna.catnip.shard.DiscordEvent;
import com.thepepeyt.DenoriaBot.Util;
import io.reactivex.rxjava3.core.Completable;

public class github extends AbstractExtension {


    public github(String name) {
        super(name);
        System.out.println("Github registered");

    }


    @Override
    public Completable onLoaded() {


        observable(DiscordEvent.MESSAGE_CREATE)
                .filter(msg -> msg.content().equals("?github"))
                .subscribe(msg -> {
                    Embed embed = Util.createEmbed("Denoria.pl | Github", "Link do kodu naszego bota [Kliknij tu](https://github.com/ThePepeYT/DenoriaBot)", "Denoria.PL", msg.timestamp());
                    msg.respond(embed);
                    msg.delete();


                });




        return super.onLoaded();
    }


}
