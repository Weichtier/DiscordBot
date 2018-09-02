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
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class SendmessageCommand implements Command {
    private static String[] roleswithperms = {"Admin", "Spezi", "Bot Operator"};
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if(PermissionsCore.check(event.getMember(), roleswithperms)){
            if(args.length >= 2){
                String id = args[0].substring(2, args[0].length() - 1);
                Member member = event.getGuild().getMemberById(id);

                String message = "";
                for (int i = 1; i < args.length;i++){
                    message += args[i] + " ";
                }

                MessageEmbed finalMessage = new EmbedBuilder().setColor(java.awt.Color.CYAN).setTitle("Private Nachricht").setDescription("Von: **" + event.getMember().getEffectiveName() + "**\n\n**" + message + "**").build();
                try {
                    member.getUser().openPrivateChannel().queue(privateChannel -> {
                        privateChannel.sendMessage(finalMessage).queue();
                        privateChannel.close().queue();
                    });
                }catch (UnsupportedOperationException e){
                    return;
                }
                event.getChannel().sendMessage("Nachricht gesendet.").queue();
            }else {
                event.getChannel().sendMessage(DiscordBot.buildErrorMessage("Syntax: !sendmessage @User <Message>", event.getMember())).queue();
            }
        }else {
            event.getChannel().sendMessage(DiscordBot.buildErrorMessage(Data.getNoPermsMessage(), event.getMember()));
        }
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }
}
