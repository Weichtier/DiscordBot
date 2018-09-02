/*
 *     Copyright (c) 2018 Slowloris.de
 *     Development: Weichtier
 *
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!
 */

package de.slowloris.discordbot.serverbot.commands;

import de.slowloris.discordbot.interfaces.Command;
import de.slowloris.discordbot.serverbot.utils.DiscordBot;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ServerInfoCommand implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        Guild g = event.getGuild();
        int userstotal = g.getMembers().size();
        int useronline = DiscordBot.getOnlineUsers(event.getGuild());
        String usersinfo = useronline + " / " + userstotal;
        event.getChannel().sendMessage("```css\n" + g.getName() + " | " + usersinfo + " Online\n```\n<@" + event.getAuthor().getId() + ">").queue();
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }
}
