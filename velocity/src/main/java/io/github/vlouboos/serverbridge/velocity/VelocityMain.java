package io.github.vlouboos.serverbridge.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import io.github.vlouboos.serverbridge.ServerBridge;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.spongepowered.configurate.ConfigurationNode;

import java.io.File;
import java.nio.file.Path;

public class VelocityMain {
    private final Path pluginDataDir;
    private final Logger logger;

    @Inject
    public VelocityMain(@NotNull Logger logger, @DataDirectory Path path) {
        this.logger = logger;
        pluginDataDir = path;
        ServerBridge.load();
        logger.info("API loaded");
    }

    @Subscribe
    public void onProxyInitialization(final ProxyInitializeEvent event) {
        VelocityConfig.saveDefaultConfig(pluginDataDir, "config.yml");
        ConfigurationNode config = VelocityConfig.getConfig(new File(pluginDataDir.toFile(), "config.yml").toPath());
        String host = config.node("host").getString("unknown");
        int port = config.node("port").getInt(6379);
        logger.info("Creating connection to {}:{}", host, port);
        ServerBridge.connect(host, port);
        logger.info("Connected to redis server");
        ServerBridge.submitSubscription();
    }

    @Subscribe
    public void onStop(final ProxyShutdownEvent event) {
        ServerBridge.stop();
        logger.info("Stopped ServerBridge API");
    }
}
