package io.github.vlouboos.serverbridge.velocity;

import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class VelocityConfig {
    public static ConfigurationNode getConfig(Path file) {
        try {
            return getProvider(file).load();
        } catch (ConfigurateException e) {
            throw new RuntimeException(e);
        }
    }

    private static YamlConfigurationLoader getProvider(Path path) {
        return YamlConfigurationLoader.builder().path(path).build();
    }

    @SuppressWarnings("all")
    public static void saveDefaultConfig(Path path, String fileName) {
        File file = new File(path.toFile(), fileName);
        if (!file.exists()) {
            file.mkdirs();
            try (InputStream in = VelocityConfig.class.getResourceAsStream(fileName)) {
                assert in != null;
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
