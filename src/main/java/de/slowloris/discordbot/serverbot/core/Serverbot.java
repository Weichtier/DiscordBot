/*
 *     Copyright (c) 2018 Slowloris.de
 *     Development: Weichtier
 *
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!
 */

package de.slowloris.discordbot.serverbot.core;

import de.slowloris.discordbot.serverbot.commands.*;
import de.slowloris.discordbot.serverbot.listeners.CommandListener;
import de.slowloris.discordbot.serverbot.listeners.ReadyListener;
import de.slowloris.discordbot.serverbot.utils.Data;
import de.slowloris.discordbot.serverbot.utils.DiscordBot;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;

public class Serverbot extends DiscordBot{
    private static String version = "v1.2.9";
    private String token;
    private JDABuilder builder;
    private static JDA jda;

    public Serverbot() {

        /*
        * Run DiscordBot and set it up!
        * */

        builder = new JDABuilder(AccountType.BOT);
        builder.setToken(token);
        builder.setAutoReconnect(true);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.addEventListener(new ReadyListener());
        builder.addEventListener(new CommandListener());
        registerCommand("help", new HelpCommand());
        registerCommand("createinvite", new CreateinviteCommand());
        registerCommand("serverinfo", new ServerInfoCommand());
        registerCommand("status", new StatusCommand());
        registerCommand("sendmessage", new SendmessageCommand());
        registerCommand("music", new MusicCommand());
        builder.setGame(new Game(Data.getServerbotData().getGame()) {
            @Override
            public String getName(){
                return Data.getServerbotData().getGame();
            }

            @Override
            public String getUrl(){
                return "https://slowloris.de/discordbot";
            }

            @Override
            public GameType getType(){
                return GameType.DEFAULT;
            }
        });

    }
    public void start(){
        try {
            jda = builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
            System.exit(0);
            return;
        }
    }
    public void stop(){
        jda.shutdown();
    }
    public void setToken(String token){
        this.token = token;
    }
    public static String getCommandPrefix(){
        return getCommandPrefix();
    }
}