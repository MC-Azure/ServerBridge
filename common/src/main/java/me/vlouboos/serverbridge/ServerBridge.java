package me.vlouboos.serverbridge;

public class ServerBridge {
    public static void loadApi(RedisChat redisChat) {
        new ServerBridgeAPI(redisChat);
    }
}
