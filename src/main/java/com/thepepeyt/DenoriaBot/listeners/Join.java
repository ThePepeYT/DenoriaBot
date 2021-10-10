package com.thepepeyt.DenoriaBot.listeners;

import com.mewna.catnip.entity.message.Embed;
import com.mewna.catnip.entity.message.MessageOptions;
import com.mewna.catnip.entity.message.component.ActionRow;
import com.mewna.catnip.entity.message.component.Button;
import com.mewna.catnip.extension.AbstractExtension;
import com.mewna.catnip.shard.DiscordEvent;
import com.thepepeyt.DenoriaBot.Util;
import io.reactivex.rxjava3.core.Completable;

public class Join extends AbstractExtension {


    public Join(String name) {
        super(name);
        System.out.println("Join registered");

    }

    @Override
    public Completable onLoaded() {


        observable(DiscordEvent.GUILD_MEMBER_ADD)
                .subscribe(join -> {
                    join.guild().subscribe(guild -> {
                        Embed embed = Util.createEmbed("Użytkownik dołączył", join.nick() + " dołączył, aktualnie na serwerze jest " + guild.members() + " użytkowników!", "Denoria.pl", join.creationTime());
                        ActionRow button = Util.createButton("Witaj\uD83D\uDD90", false, Button.ButtonStyle.PRIMARY, "dołączID");
                        catnip().rest().channel().createMessage("824373755341701147", new MessageOptions().embed(embed).addComponent(button));

                    });

                });



        return super.onLoaded();
    }


}


