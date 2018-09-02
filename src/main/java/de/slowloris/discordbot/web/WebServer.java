/*
 *     Copyright (c) 2018 Slowloris.de
 *     Development: Weichtier
 *
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!
 */

package de.slowloris.discordbot.web;


import com.sun.net.httpserver.HttpServer;
import de.slowloris.discordbot.web.handler.RootHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebServer {
    public WebServer(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new RootHandler());
        server.setExecutor(null);
        server.start();
    }
}
