package me.vlouboos.serverbridge.bukkit;

import me.vlouboos.serverbridge.RedisChat;
import me.vlouboos.serverbridge.ServerBridge;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitMain extends JavaPlugin {
    private RedisChat redisChat;

    @Override
    public void onLoad() {
        redisChat = new RedisChat();
        ServerBridge.loadApi(redisChat);
        getLogger().info("API loaded");
    }

    @Override
    public void onEnable() {
        redisChat.connect();
        getLogger().info("Connected to redis server");
        redisChat.subscribe();
    }
}
