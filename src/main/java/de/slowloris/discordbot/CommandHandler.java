/*
 * Copyright (c) 2018 Slowloris.de
 *
 * Development: Weichtier
 *
 * You're allowed to edit the Project.
 * Its not allowed to reupload this Project!
 */

package de.slowloris.discordbot;

import de.slowloris.discordbot.interfaces.Command;
import de.slowloris.discordbot.serverbot.utils.DiscordBot;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandHandler {


    public static final CommandParser parser = new CommandParser();

    //command list with executors
    public static HashMap<String, Command> commands = new HashMap<>();

    //Command String list for help command
    public static ArrayList<String> commands_list = new ArrayList<>();



    /*
    * Handle Command when Bot read some
    * in defined Bot Channel.
    * Triggered in "CommandListener" class
    * */

    public static void handleCommand(CommandParser.commandContainer cmd) {

        System.out.println("[INFO] " + cmd.event.getMember().getEffectiveName() + " executed command !" + cmd.invoke);

        if (commands.containsKey(cmd.invoke)) {

            boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

            if (!safe) {
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
                commands.get(cmd.invoke).executed(safe, cmd.event);
            } else {
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }

        }else {
            cmd.event.getChannel().sendMessage(DiscordBot.buildErrorMessage("Befehl nicht gefunden", cmd.event.getMember())).queue();
        }

    }

}
