/*
 *     Copyright (c) 2018 Slowloris.de
 *     Development: Weichtier
 *
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!
 */

package de.slowloris.discordbot.serverbot.commands;

import de.slowloris.discordbot.serverbot.core.PermissionsCore;
import de.slowloris.discordbot.interfaces.Command;
import de.slowloris.discordbot.serverbot.utils.Data;
import de.slowloris.discordbot.serverbot.utils.DiscordBot;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class CreateinviteCommand implements Command {
    private static final String[] roleswithperms = {"Admin", "Spezi", "Bot Operator"};
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        if(PermissionsCore.check(event.getMember(), roleswithperms)){
            String link = event.getGuild().getDefaultChannel().createInvite().setTemporary(false).setMaxUses(10).complete().getURL();
            event.getChannel().sendMessage(new EmbedBuilder().setColor(Color.GREEN).setTitle("Invite Link").setDescription("Hey, " + event.getMember().getEffectiveName() + "! \nDer Link wurde dir per **Privater Nachricht** gesendet. \nEr kann von **5** Benutzern benutzt werden\n\n" + event.getMember().getAsMention()).build()).queue();
            event.getAuthor().openPrivateChannel().queue(privateChannel -> {
                privateChannel.sendMessage(new EmbedBuilder().setColor(Color.black).setTitle("Invite Link").setDescription("**Dein Invite Link: **" + link).build()).queue();
            });

        }else {
            event.getChannel().sendMessage(DiscordBot.buildErrorMessage(Data.getNoPermsMessage(), event.getMember())).queue();
        }
    }

}