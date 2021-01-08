package com.xdx505.playersonline;

import com.xdx505.playersonline.config.Config;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(value = "playersonline")
public class PlayersOnline {

    public static final Logger LOGGER = LogManager.getLogger();

    public PlayersOnline() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.server_config);
        Config.loadConfig(Config.server_config, "world\\serverconfig\\playersonline-server.toml");

        MinecraftForge.EVENT_BUS.register(this);
    }


    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        Server server = new Server();
        server.init();

        LOGGER.info("HELLO from server starting");
    }
}