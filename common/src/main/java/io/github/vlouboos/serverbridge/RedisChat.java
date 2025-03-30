package io.github.vlouboos.serverbridge;

import org.jetbrains.annotations.NotNull;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.*;

public class RedisChat extends JedisPubSub {
    private final ArrayList<MessageListener> listeners;
    private final HashSet<String> channels = new HashSet<>();
    private Jedis receiver, sender;
    private Thread thread;

    public RedisChat() {
        listeners = new ArrayList<>();
    }

    public void connect(@NotNull String host, int port) {
        receiver = new Jedis(host, port);
        sender = new Jedis(host, port);
    }

    public void sendMessage(@NotNull String channel, String @NotNull ... messages) {
        if (messages.length == 0) return;
        StringBuilder result = new StringBuilder().append(messages.length).append("-");
        for (String message : messages) {
            result.append(message.length()).append("[").append(message).append("]");
        }
        sender.publish(channel, result.toString());
    }

    @Override
    public void onUnsubscribe(@NotNull String channel, int subscribedChannels) {
        thread.interrupt();
    }

    public void subscribe(@NotNull String channel) {
        if (channels.contains(channel)) {
            return;
        }
        channels.add(channel);
    }

    public void subscribe() {
        if (channels.isEmpty()) {
            System.out.println("[ServerBridge] No channel was subscribed!");
        } else {
            StringBuilder sb = new StringBuilder().append("[ServerBridge] Subscribed channel(s):");
            for (String channel : channels) {
                sb.append(channel).append(", ");
            }
            System.out.println(sb.substring(0, sb.length() - 2));
            thread = new Thread(() -> receiver.subscribe(this, channels.toArray(new String[0])));
            thread.start();
        }
    }

    @Override
    public void onMessage(@NotNull String channel, @NotNull String message) {
        String[] messages = new String[Integer.parseInt(message.split("-")[0])];
        int current = 0;
        int size = messages.length;
        message = message.substring(String.valueOf(size).length() + 1);
        while (current < size) {
            Integer length = Integer.parseInt(message.split("\\[")[0]);
            message = message.substring(String.valueOf(length).length() + 1);
            messages[current] = message.substring(0, length);
            message = message.substring(length + 1);
            current++;
        }
        listeners.forEach(listener -> listener.onMessage(channel, messages));
    }


    public void addListener(@NotNull MessageListener listener) {
        listeners.add(listener);
    }
}