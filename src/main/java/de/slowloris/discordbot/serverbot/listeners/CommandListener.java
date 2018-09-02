/*
 *     Copyright (c) 2018 Slowloris.de
 *     Development: Weichtier
 *
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!
 */

package de.slowloris.discordbot.serverbot.listeners;

import de.slowloris.discordbot.CommandHandler;
import de.slowloris.discordbot.serverbot.core.Serverbot;
import de.slowloris.discordbot.serverbot.utils.Data;
import de.slowloris.discordbot.serverbot.utils.DiscordBot;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {

        if(event.getMessage().getContentRaw().startsWith(Serverbot.getCommandPrefix()) && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()){
            CommandHandler.handleCommand(CommandHandler.parser.parse(event.getMessage().getContentRaw(), event));
        }

    }
}
