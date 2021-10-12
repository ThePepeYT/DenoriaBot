package com.thepepeyt.DenoriaBot.commands;

import com.mewna.catnip.entity.message.Embed;
import com.mewna.catnip.entity.util.Permission;
import com.mewna.catnip.extension.AbstractExtension;
import com.mewna.catnip.shard.DiscordEvent;
import com.thepepeyt.DenoriaBot.Util;
import io.reactivex.rxjava3.core.Completable;

public class EmbedCreator extends AbstractExtension {

    public EmbedCreator(String name) {
        super(name);
        System.out.println("Embed registered");

    }


    @Override
    public Completable onLoaded() {


        observable(DiscordEvent.MESSAGE_CREATE)
                .filter(msg -> Util.getArgs(msg.content())[0].equals("?embedcreator"))
                .filter(msg -> msg.member().hasPermissions(Permission.MANAGE_MESSAGES))
                .subscribe(msg -> {
                    msg.delete().subscribe();

                    String[] args = Util.getArgs(msg.content());
                    System.out.println(args);

                    if (args.length != 2) {
                        Embed embed = Util.createEmbed("Denoria.pl | EmbedCreator", "Komendy użyj w ten sposób{LINIA}**?embedcreator  tytuł|tekst|zdjęcie|autor**", "Denoria.PL", msg.timestamp());
                        msg.respond(embed);

                    }else{

                        String[] embedargs = args[1].replace("::", " ").split("\\|");

                        Embed embed = Util.createBiggerEmbed(embedargs[3], embedargs[1], msg.timestamp(), embedargs[2], embedargs[0]);
                        msg.respond(embed);

                    }
                });


        return super.onLoaded();
    }
}
