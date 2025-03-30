package io.github.vlouboos.serverbridge;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public class ApiProvider implements Api {
    private final RedisChat redisChat;

    /**
     * Send messages through a channel, with which registered {@link MessageListener}
     * can receive the messages.
     *
     * @param channel  The specific channel for sending messages, cannot be {@code null}.
     * @param messages The messages to be sent, cannot be {@code null}.
     */
    @Override
    public void sendMessages(@NotNull String channel, String @NotNull ... messages) {
        redisChat.sendMessage(channel, messages);
    }

    /**
     * Subscribe channels to receive messages via {@link MessageListener}.
     *
     * @param channels The channels to be subscribed, cannot be {@code null}
     * @see #registerListeners(MessageListener...)
     */
    @Override
    public void subscribeChannels(String @NotNull ... channels) {
        for (String channel : channels) {
            redisChat.subscribe(channel);
        }
    }

    /**
     * Register listeners to received messages via subscribed channels.
     *
     * @param listeners The listeners to be subscribed, cannot be {@code null}
     * @see #subscribeChannels(String...)
     */
    @Override
    public void registerListeners(MessageListener @NotNull ... listeners) {
        for (MessageListener listener : listeners) {
            redisChat.addListener(listener);
        }
    }
}
