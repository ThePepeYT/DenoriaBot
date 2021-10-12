package com.thepepeyt.DenoriaBot;

import com.mewna.catnip.Catnip;
import com.mewna.catnip.entity.user.Presence;
import com.mewna.catnip.shard.DiscordEvent;
import com.thepepeyt.DenoriaBot.commands.*;
import com.thepepeyt.DenoriaBot.commands.admin.Ban;
import com.thepepeyt.DenoriaBot.commands.economy.CreateAccount;
import com.thepepeyt.DenoriaBot.commands.economy.Money;
import com.thepepeyt.DenoriaBot.commands.economy.Working;
import com.thepepeyt.DenoriaBot.listeners.Join;
import com.thepepeyt.DenoriaBot.listeners.PomocBot;

import java.io.IOException;


public class Main{

    public static String token = "ODgyNjMzMTU5NjU1NzE4OTYy.YS-OLw.GyWSinHfn42I36IT1zM62vE0hog";
    final static Catnip catnip = Catnip.catnip(token);








    public static void main(String[] args) throws IOException {










        catnip.observable(DiscordEvent.READY)
                        .subscribe(ready -> {

                            Database.connect();


                            catnip.presence(Presence.of(Presence.OnlineStatus.ONLINE, Presence.Activity.of("Najlepszy geopolityk", Presence.ActivityType.WATCHING)));
                        });













        catnip.loadExtension(new github("github"));
        catnip.loadExtension(new Ping("Ping"));
        catnip.loadExtension(new RasaInfo("RasaInfo"));
        catnip.loadExtension(new PomocBot("PomocBot"));
        catnip.loadExtension(new ProfesjaInfo("ProfesjaInfo"));
        catnip.loadExtension(new Ban("ban"));
        catnip.loadExtension(new Join("join"));
        catnip.loadExtension(new EmbedCreator("embed"));
        catnip.loadExtension(new CreateAccount("createaccount"));
        catnip.loadExtension(new Money("money"));
        catnip.loadExtension(new Working("praca"));
        catnip.connect();

    }




}
