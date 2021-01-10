package com.xdx505.playersonline.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.xdx505.playersonline.PlayersOnline;
import net.minecraftforge.common.ForgeConfigSpec;

import java.io.File;

public class Config {
    private static final ForgeConfigSpec.Builder serverBuilder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec serverConfig;

    static {
        WebServerConfig.init(serverBuilder);
        serverConfig = serverBuilder.build();
    }

    public static void loadConfig(ForgeConfigSpec config, String path) {
        PlayersOnline.LOGGER.info("Loading config:" + path);
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        PlayersOnline.LOGGER.info("Build config: " + path);
        file.load();
        PlayersOnline.LOGGER.info("Loaded config:" + path);
        config.setConfig(file);
    }
}
