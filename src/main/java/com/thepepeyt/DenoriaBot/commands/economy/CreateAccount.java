package com.thepepeyt.DenoriaBot.commands.economy;

import com.mewna.catnip.entity.message.Embed;
import com.mewna.catnip.entity.message.MessageOptions;
import com.mewna.catnip.entity.message.component.ActionRow;
import com.mewna.catnip.entity.message.component.Button;
import com.mewna.catnip.extension.AbstractExtension;
import com.mewna.catnip.shard.DiscordEvent;
import com.thepepeyt.DenoriaBot.Database;
import com.thepepeyt.DenoriaBot.Util;
import io.reactivex.rxjava3.core.Completable;

public class CreateAccount extends AbstractExtension {


    public CreateAccount(String name) {
        super(name);

    }

    @Override
    public Completable onLoaded() {


        observable(DiscordEvent.MESSAGE_CREATE)
                .filter(msg -> Util.getArgs(msg.content())[0].equals("?zalozkonto"))
                .subscribe(msg -> {
                    Database.createPlayer(msg.author().id());
                    if(!Database.playerExists(msg.author().id())) {
                        Embed embed = Util.createEmbed("Denoria.pl | Ekonomia", "Twoje konto zostało założone", "denoria.pl", msg.timestamp());
                        ActionRow button = Util.createButton("Brawo\uD83D\uDD90", false, Button.ButtonStyle.PRIMARY, "dołączID");
                        msg.reply(new MessageOptions().embed(embed).addComponent(button), true);
                    }
                    else{
                        Embed embed = Util.createEmbed("Denoria.pl | Ekonomia", "Masz już jedno kontobankowe dzbanie", "denoria.pl", msg.timestamp());
                        msg.reply(embed, true);
                        }


                    });
    return super.onLoaded();
    }
}
