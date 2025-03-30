package io.github.vlouboos.serverbridge.bukkit;

import io.github.vlouboos.serverbridge.ServerBridge;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitMain extends JavaPlugin {
    @Override
    public void onLoad() {
        ServerBridge.load();
        getLogger().info("API loaded");
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        String host = getConfig().getString("host", "localhost");
        int port = getConfig().getInt("port", 6379);
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
