/*
 *     Copyright (c) 2018 Slowloris.de
 *     Development: Weichtier
 *
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!
 */

package de.slowloris.discordbot.interfaces;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface Command {

    /*
    *
    * This is the command interface which will be implemented in commands
    *
    * here will be set useful command methods
    * */

    boolean called(String[] args, MessageReceivedEvent event);
    void action(String[] args, MessageReceivedEvent event);
    void executed(boolean sucess, MessageReceivedEvent event);

}
