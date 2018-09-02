/*
 * Copyright (c) 2018 Slowloris.de
 *
 * Development: Weichtier
 *
 * You're allowed to edit the Project.
 * Its not allowed to reupload this Project!
 */

package de.slowloris.discordbot.serverbot.utils;

public class DataMethods {
    private static String token;
    private static String botchannel;
    private static String broadcastchannel;
    private static String uptimeRobotAPIKey;
    private static String game;


    public String getToken() {
        return token;
    }

    public void setToken(String s) {
        token = s;
    }



    public String getBotchannel() {
        return botchannel;
    }

    public void setBotchannel(String s) {
        botchannel = s;
    }


    public String getUptimeRobotAPIKey() {
        return uptimeRobotAPIKey;
    }

    public void setUptimeRobotAPIKey(String s) {
        uptimeRobotAPIKey = s;
    }


    public String getBroadcastchannel() {
        return broadcastchannel;
    }

    public void setBroadcastchannel(String s) {
        broadcastchannel = s;
    }


    public String getGame() {
        return game;
    }

    public void setGame(String s) {
        game = s;
    }
}
