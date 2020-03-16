package me.rocket.broadcast.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {
    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void send(String message) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            player.sendMessage(format(message));
        }
    }

    public static void send(Player player, String message) {
        player.sendMessage(format(message));
    }

    public static void log(String message) {
        System.out.println(format(message));
    }
}
