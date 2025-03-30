package io.github.vlouboos.serverbridge.bungee;

import io.github.vlouboos.serverbridge.ServerBridge;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.io.File;

public class BungeeMain extends Plugin {
    public static BungeeMain INSTANCE;

    @Override
    public void onLoad() {
        INSTANCE = this;
        ServerBridge.load();
        getLogger().info("API loaded");
    }

    @Override
    public void onEnable() {
        Configuration configuration = BungeeConfig.getConfig(new File(getDataFolder(), "config.yml"));
        assert configuration != null;
        String host = configuration.getString("host", "unknown");
        int port = configuration.getInt("port", 6379);
        getLogger().info("Creating connection to " + host + ":" + port);
        ServerBridge.connect(host, port);
        getLogger().info("Connected to redis server");
        ServerBridge.submitSubscription();
    }

    @Override
    public void onDisable() {
        ServerBridge.stop();
        getLogger().info("Stopped ServerBridge API");
    }
}
