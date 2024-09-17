package me.vlouboos.serverbridge.bungee;

import me.vlouboos.serverbridge.RedisChat;
import me.vlouboos.serverbridge.ServerBridge;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeMain extends Plugin {
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
