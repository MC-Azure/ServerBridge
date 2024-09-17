package me.vlouboos.serverbridge;

public interface MessageListener {
    void onMessage(String channel, String... message);
}
