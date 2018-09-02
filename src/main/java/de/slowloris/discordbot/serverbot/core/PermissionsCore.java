/*
 *     Copyright (c) 2018 Slowloris.de
 *     Development: Weichtier
 *
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!
 */

package de.slowloris.discordbot.serverbot.core;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;

import java.util.Arrays;


public class PermissionsCore {

    public static boolean check(Member member, String[] roleneeded){

        for (Role role : member.getRoles()){
            if(Arrays.stream(roleneeded).parallel().anyMatch(role.getName()::equalsIgnoreCase)) {
                return true;
            }
        }
        return false;
    }

}
