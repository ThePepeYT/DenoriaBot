package com.thepepeyt.DenoriaBot.commands;

import com.mewna.catnip.entity.message.Embed;
import com.mewna.catnip.extension.AbstractExtension;
import com.mewna.catnip.shard.DiscordEvent;
import com.thepepeyt.DenoriaBot.Util;
import io.reactivex.rxjava3.core.Completable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProfesjaInfo extends AbstractExtension {

    public ProfesjaInfo(String name) {
        super(name);
        System.out.println("ProfesjaInfo registered");

    }
    public List<String> informacjerasa(String rasa) {
        List<String> list = new ArrayList<String>();
        if (rasa.toLowerCase(Locale.ROOT).equals("farmer")) {
            list.add("Umożliwia zbieranie plonów (zdobywanie itemków z rozwalania roślin) oraz hodowlę zwierząt (rozmnażanie)");
            list.add("farmer");
        } else if (rasa.toLowerCase(Locale.ROOT).equals("górnik")) {
            list.add("Umożliwia wykopywanie rud (zdobywanie itemków z rozwalania rud)");
            list.add("górnik");
        } else if (rasa.toLowerCase(Locale.ROOT).equals("kowal")) {
            list.add("Umożliwia przetapianie rud, a także korzystanie z kowadła");
            list.add("kowal");
        }
        else if (rasa.toLowerCase(Locale.ROOT).equals("zaklinacz")) {
            list.add("Umożliwia korzystanie ze stołu do zaklęć");
            list.add("zaklinacz");
        }
        else if (rasa.toLowerCase(Locale.ROOT).equals("alchemik")) {
            list.add("Umożliwia korzystanie ze statywu alchemicznego");
            list.add("alchemik");


        }
        else{
            list.add(null);
        }

        return list;

    }





    @Override
    public Completable onLoaded() {



        observable(DiscordEvent.MESSAGE_CREATE)
                .filter(msg -> Util.getArgs(msg.content().toLowerCase(Locale.ROOT))[0].equals("?profesja"))
                .subscribe(msg -> {
                    if(Util.getArgs(msg.content()).length == 2) {
                        List<String> info = informacjerasa(Util.getArgs(msg.content().toLowerCase(Locale.ROOT))[1]);
                        if (info.get(0) == null) {
                            Embed embed = Util.createEmbed("Denoria.pl | Profesje", "Musisz wpisać jedną z nazw tych profesji{LINIA}{LINIA}**Do wyboru masz:**{LINIA}Farmer, Górnik, Kowal, Zaklinacz, Alchemik", "Denoria.PL", msg.timestamp());
                            msg.reply(embed, true);

                        } else {
                            Embed embed = Util.createEmbed("Denoria.pl | Profesje", "Profesja:{LINIA}**" + info.get(1) + "**{LINIA}{LINIA}Informacje:{LINIA}**" + info.get(0) + "**", "Denoria.PL", msg.timestamp());
                            msg.reply(embed, true);

                        }
                    }else{
                        Embed embed = Util.createEmbed("Denoria.pl | Profesje", "Musisz wpisać jedną z nazw tych profesji{LINIA}{LINIA}**Do wyboru masz:**{LINIA}Farmer, Górnik, Kowal, Zaklinacz, Alchemik", "Denoria.PL", msg.timestamp());
                        msg.reply(embed, true);

                    }


                });



        return super.onLoaded();

    }
}
