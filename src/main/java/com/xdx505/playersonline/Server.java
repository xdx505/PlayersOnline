package com.xdx505.playersonline;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.xdx505.playersonline.config.WebServerConfig;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Server implements HttpHandler {
    private final Gson gson = new Gson();

    public void init() {
        if (ServerLifecycleHooks.getCurrentServer() == null) {
            return;
        }

        final String hostname = WebServerConfig.hostname.get();
        final int port = WebServerConfig.port.get();
        final String path = WebServerConfig.path.get();

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(hostname, port), 0);
            server.createContext(path, this);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        final MinecraftServer is = ServerLifecycleHooks.getCurrentServer();

        if (is == null) {
            return;
        }

        final List<ServerPlayerEntity> list = new ArrayList<>(is.getPlayerList().getPlayers());
        final List<String> playersList = new ArrayList<>();
        for (ServerPlayerEntity player : list) {
            playersList.add(player.getName().getString());
        }
        final byte[] answer = gson.toJson(playersList).getBytes(UTF_8);

        Headers headers = httpExchange.getRequestHeaders();
        httpExchange.sendResponseHeaders(200, answer.length);

        try (OutputStream os = httpExchange.getResponseBody()) {
            os.write(answer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}