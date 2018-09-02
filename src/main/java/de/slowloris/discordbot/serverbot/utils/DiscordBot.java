/*
 *     Copyright (c) 2018 Slowloris.de
 *     Development: Weichtier
 *
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!
 */

package de.slowloris.discordbot.serverbot.utils;

import de.slowloris.discordbot.CommandHandler;
import de.slowloris.discordbot.serverbot.core.Serverbot;
import de.slowloris.discordbot.interfaces.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.Role;

import java.awt.*;
import java.time.LocalDateTime;

public class DiscordBot {
    private static boolean inChatlog = true;
    private static String commandPrefix = "!";
    private JDA jda;

    public DiscordBot(){
    }

    protected static void registerCommand(String name, Command command){
        CommandHandler.commands.put(name, command);
        CommandHandler.commands_list.add(name);
    }


    public static int getOnlineUsers(Guild g){
        int online = 0;
        for (Member m : g.getMembers()){
            if(isOnline(m)){
                online++;
            }
        }
        return online;
    }
    private int getOnlineInGroup(Guild g, Role role){
        int online = 0;
        for (Member m : role.getGuild().getMembers()){
            if(m.getRoles().contains(role) && isOnline(m)){
                online++;
            }
        }
        return online;
    }
    private static boolean isOnline(Member m){
        return m.getOnlineStatus().equals(OnlineStatus.ONLINE) || m.getOnlineStatus().equals(OnlineStatus.DO_NOT_DISTURB) || m.getOnlineStatus().equals(OnlineStatus.IDLE) || m.getOnlineStatus().equals(OnlineStatus.INVISIBLE);
    }

    private static void sendBroadcast(Guild g, String s) {

        LocalDateTime now = LocalDateTime.now();
        String date = now.getDayOfMonth() + "." + now.getMonthValue() + "." + now.getYear();
        String hour;
        if(now.getHour() < 10){
            hour = "0" + now.getHour();
        }else {
            hour = String.valueOf(now.getHour());
        }
        String minute;
        if(now.getMinute() < 10){
            minute = "0" + now.getMinute();
        }else {
            minute = String.valueOf(now.getMinute());
        }
        String time = hour + ":" + minute;

        String[] input = s.split(" ");
        String msg = "";
        for (String anInput : input) {
            msg += anInput + " ";
        }

        String out = "** ### BROADCAST ### **\n ```" + msg + "```\n " + date + " - " + time + "\n@everyone";
        g.getTextChannelsByName(Data.getServerbotData().getBroadcastchannel(), true).get(0).sendMessage(out).queue();
    }

    public void sendBroadcast(String s){
        for (Guild g : this.getInstance().getGuilds()){
            DiscordBot.sendBroadcast(g, s);
        }
    }

    private void setInChatlog(boolean inChatlog) {
        DiscordBot.inChatlog = inChatlog;
    }

    private JDA getInstance(){
        return jda;
    }

    private String getCommandPrefix() {
        return commandPrefix;
    }
    public static MessageEmbed buildErrorMessage(String s, Member m){
        return new EmbedBuilder().setColor(Color.RED).setTitle("Fehler").setDescription("\n" + s + "\n\n " + m.getAsMention()).build();
    }
}
