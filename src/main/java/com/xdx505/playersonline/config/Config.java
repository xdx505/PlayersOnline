package com.xdx505.playersonline.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.xdx505.playersonline.PlayersOnline;
import net.minecraftforge.common.ForgeConfigSpec;

import java.io.File;

public class Config {
    public static final ForgeConfigSpec serverConfig;

    static {
        ForgeConfigSpec.Builder serverBuilder = new ForgeConfigSpec.Builder();
        WebServerConfig.init(serverBuilder);
        serverConfig = serverBuilder.build();
    }
}
