package com.thepepeyt.DenoriaBot.commands.economy;

import com.mewna.catnip.extension.AbstractExtension;
import com.mewna.catnip.shard.DiscordEvent;
import com.thepepeyt.DenoriaBot.Database;
import com.thepepeyt.DenoriaBot.Util;
import io.reactivex.rxjava3.core.Completable;

public class Working extends AbstractExtension {

    public Working(String name) {
        super(name);

    }

    @Override
    public Completable onLoaded() {
        observable(DiscordEvent.MESSAGE_CREATE)
                .filter(msg -> Database.playerExists(msg.author().id()))
                .subscribe(msg -> {
                    if(Util.getArgs(msg.content()).length > 2)
                    Database.updateColumn(msg.author().id(), "MONEY", Util.randomNumber(1, 2) + Database.getColumn(msg.author().id(), "MONEY"));
                });

    return super.onLoaded();
    }
}
