package io.github.vlouboos.serverbridge;

public class ServerBridge {
    private static RedisChat redisChat;

    public static void connect(String host, int port) {
        redisChat.connect(host, port);
    }

    public static void submitSubscription() {
        redisChat.subscribe();
    }

    public static void load() {
        redisChat = new RedisChat();
        ServerBridgeAPI.load(new ApiProvider(redisChat));
    }

    public static void stop() {
        redisChat.unsubscribe();
    }
}
