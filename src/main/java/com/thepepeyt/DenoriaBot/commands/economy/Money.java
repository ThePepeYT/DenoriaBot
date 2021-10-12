package com.thepepeyt.DenoriaBot.commands.economy;

import com.mewna.catnip.entity.message.Embed;
import com.mewna.catnip.entity.message.MessageOptions;
import com.mewna.catnip.entity.message.component.ActionRow;
import com.mewna.catnip.entity.message.component.Button;
import com.mewna.catnip.entity.user.User;
import com.mewna.catnip.extension.AbstractExtension;
import com.mewna.catnip.shard.DiscordEvent;
import com.thepepeyt.DenoriaBot.Database;
import com.thepepeyt.DenoriaBot.Util;
import io.reactivex.rxjava3.core.Completable;

public class Money extends AbstractExtension {

    public Money(String name) {
        super(name);

    }

    @Override
    public Completable onLoaded() {


        observable(DiscordEvent.MESSAGE_CREATE)
                .filter(msg -> Util.getArgs(msg.content())[0].equals("?kontobankowe"))
                .filter(msg -> Util.isDatabase(msg))
                .subscribe(msg ->{

                    var author = msg.author();;


                    if(!msg.mentionedUsers().isEmpty()) {
                        author = msg.mentionedUsers().get(0);
                    }


                    String money = String.valueOf(Database.getColumn(author.id(), "MONEY"));
                    String bank = String.valueOf(Database.getColumn(author.id(), "BANK"));
                    Embed embed = Util.createEmbed(
                            "Denoria.pl | Ekonomia",
                            "**Portfel:**{LINIA}" + money + "{LINIA}**Bank:**{LINIA}" + bank,
                            "denoria.pl",
                            msg.timestamp()
                            );
                    msg.respond(embed);
                });
        return super.onLoaded();
    }
}
