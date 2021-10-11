package com.thepepeyt.DenoriaBot.commands;

import com.mewna.catnip.entity.guild.Member;
import com.mewna.catnip.entity.message.Embed;
import com.mewna.catnip.entity.user.User;
import com.mewna.catnip.entity.util.Permission;
import com.mewna.catnip.extension.AbstractExtension;
import com.mewna.catnip.shard.DiscordEvent;
import com.thepepeyt.DenoriaBot.Util;
import io.reactivex.rxjava3.core.Completable;

public class Ban extends AbstractExtension {

    public Ban(String name) {
        super(name);
        System.out.println("Ban registered");

    }


    @Override
    public Completable onLoaded() {


        observable(DiscordEvent.MESSAGE_CREATE)
                .filter(msg -> Util.getArgs(msg.content())[0].equals("?ban"))
                .filter(msg -> msg.member().hasPermissions(Permission.BAN_MEMBERS))
                .subscribe(msg -> {
                    System.out.println(Util.getArgs(msg.content()).length);

                    if(Util.getArgs(msg.content()).length == 4){

                        String[] args = Util.getArgs(msg.content());





                        Embed ban = Util.createEmbed("Denoria.pl | Ban", "Użytkownik zbanowany{LINIA}Admin: **" + msg.author().asMention() + "**{LINIA}Użytkownik: **" + msg.mentionedMembers().get(0).asMention() + "**{LINIA}Powód: **" + args[3].replace("-", " ") + "**", "Denoria.PL", msg.timestamp());
                        msg.respond(ban);
                        msg.guild().subscribe(guild -> {

                            catnip().rest().channel().deleteMessage(msg.channelId(), msg.id());

                            guild.ban(msg.mentionedMembers().get(0),args[3].replace("-", " "), 10);

                        });






                    }
                    else {
                        Embed embed = Util.createEmbed("Denoria.pl | Ban", "Komendy użyj w ten sposób{LINIA}**?ban <użytkownik> <powód>**", "Denoria.PL", msg.timestamp());
                        msg.respond(embed);



                    }

                });




        return super.onLoaded();
    }
}
