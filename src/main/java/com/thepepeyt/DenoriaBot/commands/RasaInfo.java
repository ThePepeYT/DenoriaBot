package com.thepepeyt.DenoriaBot.commands;

import com.mewna.catnip.entity.message.Embed;
import com.mewna.catnip.extension.AbstractExtension;
import com.mewna.catnip.shard.DiscordEvent;
import com.thepepeyt.DenoriaBot.Util;
import io.reactivex.rxjava3.core.Completable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class RasaInfo extends AbstractExtension {


    public RasaInfo(String name) {
        super(name);
        System.out.println("RasaInfo Registered");

    }


    public List<String> informacjerasa(String rasa) {
        List<String> list = new ArrayList<String>();
        if (rasa.toLowerCase(Locale.ROOT).equals("człowiek")) {
            list.add("Posiada 5 serc więcej. Gdy zostanie mu połowa życia otrzymuje regenerację 3 na 5 sekund [Cooldown: 2 minuty]");
            list.add("człowiek");
        } else if (rasa.toLowerCase(Locale.ROOT).equals("elf")) {
            list.add("Posiada szybkość 1 oraz zmniejszony dmg od upadku, ale szybkość upadku pozostanie taka sama. Gdy zada obrażenia z łuku dostaje speed 2 na 5 sekund");
            list.add("elf");
        } else if (rasa.toLowerCase(Locale.ROOT).equals("krasnolud")) {
            list.add("Posiada wytrzymałość 1 oraz możliwość czołgania. Gdy zablokuje cios tarczą otrzymuje wytrzymałość 2 na 5 sekund [Cooldown: 2 minuty]");
            list.add("krasnolud");
        }
        else if (rasa.toLowerCase(Locale.ROOT).equals("demon")) {
            list.add("Posiada odporność na ogień, spowolnienie, mdłości, oślepienie, słabość, zatrucie, obumarcie. Jego atak nakłada obumarcie 1 na 5 sekund [Cooldown: 15 sekund]. Może chodzić po lawie. Wciskając szybko dwa razy shift może włączać lub wyłączać tą funkcję.");
            list.add("demon");
        }
        else if (rasa.toLowerCase(Locale.ROOT).equals("ork")) {
            list.add("Posiada siłe 1. Gdy zostanie mu połowa życia otrzymuje siłę 2 na 5 sekund [Cooldown: 2 minuty]");
            list.add("ork");


        }
        else if (rasa.toLowerCase(Locale.ROOT).equals("goblin")) {
            list.add("Ataki nakładają truciznę 1 na 2 sek, a także dają goblinowi haste 1 na 5 sekund oraz możliwość czołganie");
            list.add("goblin");

        }
        else if (rasa.toLowerCase(Locale.ROOT).equals("troll")) {
            list.add("Ataki nakładają slowness i nausea na 1 sek, a także dają trollowi haste 2 na 5 sekund gdy ten atakuje za pomocą siekiery");
            list.add("troll");


        }
        else{
            list.add(null);
        }

    return list;

    }




    @Override
    public Completable onLoaded() {



        observable(DiscordEvent.MESSAGE_CREATE)
                .filter(msg -> Util.getArgs(msg.content().toLowerCase(Locale.ROOT))[0].equals("?rasa"))
                .subscribe(msg -> {
                    if(Util.getArgs(msg.content()).length == 2) {
                        List<String> info = informacjerasa(Util.getArgs(msg.content().toLowerCase(Locale.ROOT))[1]);
                        if (info.get(0) == null) {
                            Embed embed = Util.createEmbed("Denoria.pl | Rasy", "Musisz wpisać jedną z nazw tych ras{LINIA}{LINIA}**Do wyboru masz:**{LINIA}człowiek, elf, krasnolud, demon, troll, goblin, ork", "Denoria.PL", msg.timestamp());
                            msg.reply(embed, true);

                        } else {
                            Embed embed = Util.createEmbed("Denoria.pl | Rasy", "Rasa:{LINIA}**" + info.get(1) + "**{LINIA}{LINIA}Informacje:{LINIA}**" + info.get(0) + "**", "Denoria.PL", msg.timestamp());
                            msg.reply(embed, true);

                        }
                    }else{
                        Embed embed = Util.createEmbed("Denoria.pl | Rasy", "Musisz wpisać jedną z nazw tych ras{LINIA}{LINIA}**Do wyboru masz:**{LINIA}człowiek, elf, krasnolud, demon, troll, goblin, ork", "Denoria.PL", msg.timestamp());
                        msg.reply(embed, true);

                    }


                });



        return super.onLoaded();

    }

    }
