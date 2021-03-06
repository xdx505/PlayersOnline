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
    private static final Server server = new Server();

    public PlayersOnline() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.serverConfig);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        server.init();

        LOGGER.info("HELLO from server starting");
    }
}