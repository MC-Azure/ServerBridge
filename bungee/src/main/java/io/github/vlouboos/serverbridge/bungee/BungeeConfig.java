package io.github.vlouboos.serverbridge.bungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.logging.Level;

public class BungeeConfig {
    @SuppressWarnings("all")
    public static @Nullable Configuration getConfig(@NotNull File file) {
        if (!file.exists()) {
            InputStream in = BungeeMain.INSTANCE.getResourceAsStream(file.getName());
            if (in == null) {
                throw new IllegalArgumentException(ChatColor.DARK_RED + "The embedded resource " + ChatColor.YELLOW + "'" + file.getName() + "'" + ChatColor.DARK_RED + " cannot be found in " + ChatColor.YELLOW + BungeeMain.INSTANCE.getFile());
            } else {
                File outDir = BungeeMain.INSTANCE.getDataFolder();
                if (!outDir.exists()) {
                    outDir.mkdirs();
                }
                try {
                    file.createNewFile();
                    OutputStream out = Files.newOutputStream(file.toPath());
                    byte[] buf = new byte[1024];

                    int len;
                    while((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }

                    out.close();
                    in.close();
                } catch (IOException e) {
                    BungeeMain.INSTANCE.getLogger().log(Level.SEVERE, ChatColor.DARK_RED + "Could not save " + ChatColor.YELLOW + file.getName() + ChatColor.DARK_RED + " to " + ChatColor.YELLOW + file, e);
                }
            }
        }
        try {
            return getProvider().load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ConfigurationProvider getProvider() {
        return ConfigurationProvider.getProvider(YamlConfiguration.class);
    }
}
