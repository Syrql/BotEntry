package fr.syrql.botentry.bots.factory;

import fr.syrql.botentry.commons.Bot;

import java.util.UUID;

public class BotFactory {

    public Bot create(String botName){
        return new Bot(botName, UUID.randomUUID());
    }

}
