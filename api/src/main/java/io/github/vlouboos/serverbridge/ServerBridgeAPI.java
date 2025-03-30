package io.github.vlouboos.serverbridge;

import org.jetbrains.annotations.ApiStatus;

public class ServerBridgeAPI {
    /**
     * The API of Server Bridge.
     */
    private static Api api;

    /**
     * Get the API of Server Bridge.
     *
     * @return The instance of API
     *
     * @see Api
     *
     * @throws PluginNotLoadedException Thrown when plugin is not loaded, or maybe some
     * handsome developers embedded this api into their packaging.
     */
    public static Api getApi() throws PluginNotLoadedException {
        if (api == null) {
            throw new PluginNotLoadedException();
        }
        return api;
    }

    /**
     * Well, hope that you can see the annotation here {@code @}{@link org.jetbrains.annotations.ApiStatus.Internal}.
     * Don't invoke me!
     *
     * @param api You don't need to know.
     */
    @ApiStatus.Internal
    protected static void load(Api api) {
        ServerBridgeAPI.api = api;
    }
}
