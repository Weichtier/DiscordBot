/*
 *     Copyright (c) 2018 Slowloris.de
 *     Development: Weichtier
 *
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!
 */

package de.slowloris.discordbot.serverbot.commands;

import de.slowloris.discordbot.CommandHandler;
import de.slowloris.discordbot.interfaces.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class HelpCommand implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {

        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        // Execute Command
        String commands = "";
        for (String s : CommandHandler.commands_list){
            commands += "!" + s + "\n";
        }
        event.getChannel().sendMessage("Hilfe:\n\n" + commands + "\n<@" + event.getAuthor().getId() + ">").queue();
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
    }
}
