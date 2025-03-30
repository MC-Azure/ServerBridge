package io.github.vlouboos.serverbridge;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Before using this API, you're required to add ServerBridge Plugin as a dependent plugin.
 */
@ApiStatus.NonExtendable
public interface Api {
    /**
     * Send messages through a channel, with which registered {@link MessageListener}
     * can receive the messages.
     *
     * @param channel
     * The specific channel for sending messages, cannot be {@code null}.
     * @param messages
     * The messages to be sent, cannot be {@code null}.
     */
    void sendMessages(@NotNull String channel, String @NotNull ... messages);

    /**
     * Subscribe channels to receive messages via {@link MessageListener}.
     *
     * @param channels
     * The channels to be subscribed, cannot be {@code null}
     *
     * @see #registerListeners(MessageListener...)
     */
    void subscribeChannels(String @NotNull ... channels);

    /**
     * Register listeners to received messages via subscribed channels.
     *
     * @param listeners
     * The listeners to be subscribed, cannot be {@code null}
     *
     * @see #subscribeChannels(String...)
     */
    void registerListeners(MessageListener @NotNull ... listeners);
}
