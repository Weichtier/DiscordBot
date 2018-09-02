/*
 *     Copyright (c) 2018 Slowloris.de
 *     Development: Weichtier
 *
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!
 */

package de.slowloris.discordbot.serverbot.commands;

import com.madyoda.uptimerobot.UptimeRobot;
import com.madyoda.uptimerobot.exceptions.ApiException;
import com.madyoda.uptimerobot.methods.GetMonitorsRequest;
import com.madyoda.uptimerobot.objects.Monitors;
import com.mashape.unirest.http.exceptions.UnirestException;
import de.slowloris.discordbot.interfaces.Command;
import de.slowloris.discordbot.serverbot.utils.Data;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class StatusCommand implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        UptimeRobot robot = new UptimeRobot(Data.getServerbotData().getUptimeRobotAPIKey());


        Monitors monitors = null;
        try {
            monitors = GetMonitorsRequest.builder().setUptimeRobot(robot).get();
        } catch (ApiException | UnirestException e) {
            e.printStackTrace();
        }

        event.getChannel().sendMessage("** ### STATUS ### **").queue();


        EmbedBuilder embed = new EmbedBuilder();
        assert monitors != null;
        for(Monitors.Monitor monitor : monitors.getMonitors()){

            String status;
            Color color;
            if(monitor.getStatus() == 0){
                status = "Paused";
                color = Color.BLACK;
            }else if(monitor.getStatus() == 1){
                status = "Not Checked Yet";
                color = Color.lightGray;
            }else if(monitor.getStatus() == 2){
                status = "Up";
                color = Color.GREEN;
            }else if(monitor.getStatus() == 8){
                status = "Seems Down";
                color = Color.ORANGE;
            }else if(monitor.getStatus() == 9){
                status = "Down";
                color = Color.RED;
            }else {
                status = "Unknown";
                color = Color.GRAY;
            }
            event.getChannel().sendMessage(embed.setColor(color).setTitle(monitor.getFriendlyName()).setDescription(status).build()).queue();
        }
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }
}
