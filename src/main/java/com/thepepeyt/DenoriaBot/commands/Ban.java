package com.thepepeyt.DenoriaBot.commands;

import com.mewna.catnip.entity.guild.Member;
import com.mewna.catnip.entity.message.Embed;
import com.mewna.catnip.entity.user.User;
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
                .filter(msg -> msg.content().equals("?ban"))
                .subscribe(msg -> {
                    if(Util.getArgs(msg.content()).length < 3){
                        Embed embed = Util.createEmbed("Denoria.pl | Ban", "Komendy użyj w ten sposób{LINIA}**?ban <użytkownik> <powód>**", "Denoria.PL", msg.timestamp());
                        msg.respond(embed);

                    }
                    else {
                        String[] args = Util.getArgs(msg.content());
                        StringBuilder reason = new StringBuilder();
                        for(int i=0; i<args.length; i++){
                            if(i == 0 && i==1){
                                continue;
                            }
                            else{
                                reason.append(args[i]);
                            }
                        }
                        String Admin = msg.author().username();
                        Member user = msg.mentionedMembers().get(0);
                        Embed ban = Util.createEmbed("Denoria.pl | Ban", "Użytkownik odbanowany{LINIA}Admin: **" + Admin + "**{LINIA}Użytkownik: **" + user.nick() + "**{LINIA}Powód: **" + reason + "**", "Denoria.PL", msg.timestamp());
                        msg.respond(ban);
                    }

                });




        return super.onLoaded();
    }
}
