/*
 *     Copyright (c) 2018 Slowloris.de
 *     Development: Weichtier
 *
 *     Ändern für den privaten nutzen erlaubt. Reuploaded verboten!
 */

package de.slowloris.discordbot.web.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import de.slowloris.discordbot.Main;
import de.slowloris.discordbot.serverbot.core.Serverbot;

@SuppressWarnings("restriction")
public class RootHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Main.getTerminal().writer().println("New Request: " + exchange.getRemoteAddress().getAddress().getHostAddress() + ":" + exchange.getRemoteAddress().getPort() + " | " + exchange.getRequestURI().getPath());

        if(exchange.getRequestURI().getPath().equalsIgnoreCase("/")){
            Headers map = exchange.getResponseHeaders();
            String redirect = "/index.html";
            map.add("Location", redirect);
            exchange.sendResponseHeaders(301, -1);
        }

        String fileId = exchange.getRequestURI().getPath();
        File file = getFile(fileId);
        if (file == null) {
            String response = "File not found.";
            exchange.sendResponseHeaders(404, response.length());
            OutputStream output = exchange.getResponseBody();
            output.write(response.getBytes());
            output.flush();
            output.close();
        } else {
            exchange.sendResponseHeaders(200, 0);
            OutputStream output = exchange.getResponseBody();
            FileInputStream fs = new FileInputStream(file);
            final byte[] buffer = new byte[0x10000];
            int count;
            while ((count = fs.read(buffer)) >= 0) {
                output.write(buffer, 0, count);
            }
            output.flush();
            output.close();
            fs.close();
        }
    }

    private File getFile(String fileId) {
        File file;
        String filepath = "/public_html" + fileId;
        try {
            file = new File(getClass().getResource(filepath).toURI());
        } catch (Exception e) {
            System.out.println("File not found while Process webserver connection ( " + filepath + " )");
            return null;
        }
        return file;
    }
}
