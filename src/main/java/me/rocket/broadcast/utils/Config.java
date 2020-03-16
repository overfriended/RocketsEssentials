package me.rocket.broadcast.utils;

import me.rocket.broadcast.Broadcast;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    Broadcast instance = Broadcast.getInstance();
    FileConfiguration config = instance.getConfig();

    public String getBroadcastPrefix() {
        return config.getString("prefix");
    }
}
