package com.xdx505.playersonline.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class WebServerConfig {
    public static ForgeConfigSpec.ConfigValue<String> hostname;
    public static ForgeConfigSpec.ConfigValue<Integer> port;
    public static ForgeConfigSpec.ConfigValue<String> path;

    public static void init(ForgeConfigSpec.Builder server) {
        server.comment("WebServer Config");

        hostname = server
                .comment("hostname")
                .define("host", "127.0.0.1");

        port = server
                .comment("port")
                .define("port", 8082);

        path = server
                .comment("path (optional)")
                .define("path", "/path");
    }
}
