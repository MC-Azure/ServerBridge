package me.vlouboos.serverbridge.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import me.vlouboos.serverbridge.RedisChat;
import me.vlouboos.serverbridge.ServerBridge;
import org.slf4j.Logger;

@Plugin(id = "serverbridge", name = "ServerBridge", version = "1.1", authors = {"vlouboos"})
public class VelocityMain {
    private final RedisChat redisChat;
    private final Logger logger;

    @Inject
    public VelocityMain(ProxyServer proxyServer, Logger logger) {
        redisChat = new RedisChat();
        this.logger = logger;
        ServerBridge.loadApi(redisChat);
        logger.info("API loaded");
    }

    @Subscribe
    public void onProxyInitialization(final ProxyInitializeEvent event) {
        redisChat.connect();
        logger.info("Connected to redis server");
        redisChat.subscribe();
    }
}
