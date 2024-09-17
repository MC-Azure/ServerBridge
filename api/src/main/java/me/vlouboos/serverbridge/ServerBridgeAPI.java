package me.vlouboos.serverbridge;

public class ServerBridgeAPI {
    private static RedisChat redisChat;

    public static void sendMessages(String channel, String... messages) throws PluginNotLoadedException {
        if (redisChat == null) {
            throw new PluginNotLoadedException();
        }
        redisChat.sendMessage(channel, messages);
    }

    public static void subscribeChannel(String channel) {
        redisChat.subscribe(channel);
    }

    public static void addListener(MessageListener listener) {
        redisChat.addListener(listener);
    }

    protected ServerBridgeAPI(RedisChat redisChat) {
        ServerBridgeAPI.redisChat = redisChat;
    }
}
