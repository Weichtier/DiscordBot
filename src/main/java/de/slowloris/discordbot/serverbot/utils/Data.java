/*
 *     Copyright (c) 2018 Slowloris.de
 *     Development: Weichtier
 *
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!
 */

package de.slowloris.discordbot.serverbot.utils;

public class Data {
    private static String noPermsMessage = "Du hast keine Berechtigung für diesen Befehl!";
    private static ServerbotData serverbotData = new ServerbotData();


    public static String getNoPermsMessage() {
        return noPermsMessage;
    }

    public static ServerbotData getServerbotData(){
        return serverbotData.getInstance();
    }

}
