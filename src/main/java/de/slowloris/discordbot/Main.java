/*
 *     Copyright (c) 2018 Slowloris.de
 *     Development: Weichtier
 *
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!
 */

package de.slowloris.discordbot;

import de.slowloris.discordbot.serverbot.core.Serverbot;
import de.slowloris.discordbot.serverbot.utils.Data;
import de.slowloris.discordbot.web.WebServer;
import de.slowloris.discordbot.web.WebsocketServer;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

public class Main {
    private static Terminal terminal;
    private static LineReader reader;
    private static WebServer webServer;
    private static WebsocketServer websocket;
    private static String version = "v1.2.9";

    public static void main(String[] args) throws IOException {

        terminal = TerminalBuilder.builder().build();
        reader = LineReaderBuilder.builder().terminal(terminal).build();


        Properties prop = new Properties();
        File file = new File("config.properties");


        /*
         * Create Config if not exists and exit the Program
         * */
        if(!file.exists()){
            OutputStream outputStream = new FileOutputStream("config.properties");
            prop.setProperty("token", "YOUR_TOKEN");
            prop.setProperty("token_music", "YOUR_MUSIC_TOKEN");
            prop.setProperty("botchannel_name", "bot");
            prop.setProperty("broadcastchannel_name", "broadcast");
            prop.setProperty("uptimerobot_apikey", "YOUR_API_KEY");
            prop.setProperty("game_name", "be a Bot | " + version);
            prop.store(outputStream, null);
            System.out.println("Generated Your Config, please set your Token in 'config.properties' file");
            System.exit(0);
        }

        terminal.writer().println("[INFO] Loading Config Files...");

        /*
         * Set variables for later using
         * */

        InputStream inputStream = new FileInputStream("config.properties");
        prop.load(inputStream);
        Data.getServerbotData().setToken(prop.getProperty("token"));
        Data.getServerbotData().setBotchannel(prop.getProperty("botchannel_name"));
        Data.getServerbotData().setUptimeRobotAPIKey(prop.getProperty("uptimerobot_apikey"));
        Data.getServerbotData().setBroadcastchannel(prop.getProperty("broadcastchannel_name"));
        Data.getServerbotData().setGame(prop.getProperty("game_name"));


        terminal.writer().println("Loaded Config Files");

        terminal.writer().println("Starting Webserver...");
        int webport = 8080;
        webServer = new WebServer(webport);
        terminal.writer().println("Started Webserver on Port " + webport);
        terminal.writer().println("Starting Websocket...");
        int socketport = 8083;
        websocket = new WebsocketServer(socketport);
        websocket.start();
        terminal.writer().println("Started Websocket on Port " + socketport);
        terminal.writer().println("Starting Bots...");
        Serverbot serverbot = new Serverbot();
        serverbot.setToken(Data.getServerbotData().getToken());
        serverbot.start();
        terminal.writer().println("Started!");

        while (true){
            String line = reader.readLine("bot> ");
            if(line.equalsIgnoreCase("stop")){
                terminal.writer().println("#bye");
                System.exit(0);
            }
        }

    }

    public static Terminal getTerminal() {
        return terminal;
    }
}
