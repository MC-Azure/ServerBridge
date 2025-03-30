package io.github.vlouboos.serverbridge;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.OverrideOnly
public interface MessageListener {
    /**
     * Receive messages via specific channel
     *
     * @param channel The specific channel
     * @param messages - The messages that be received
     *
     * @see Api#sendMessages(String, String...)
     */
    void onMessage(@NotNull String channel, String @NotNull ... messages);
}
