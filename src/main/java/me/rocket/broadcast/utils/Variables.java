package me.rocket.broadcast.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Variables {

    private static HashMap<UUID, Location> lastLocation = new HashMap<UUID, Location>();
    private static HashMap<Integer, Location> home = new HashMap<>();
    private static HashMap<Player, Player> ignored = new HashMap<>();
    private static boolean isSelfServer = true;

    public static Location getLastLocation(Player player) {
        if (lastLocation.containsKey(player.getUniqueId())) {
            return lastLocation.get(player.getUniqueId());
        } else {
            return null;
        }
    }

    public static void setLastLocation(Player player, Location loc) {
        lastLocation.put(player.getUniqueId(), loc);
    }

    public static Location getHome(int i) {
        if (home.containsKey(i)) {
            return home.get(i);
        }

        return new Location(Bukkit.getServer().getWorld("pandasucks"), -132.222, 40, -211.576, -89, 0);
    }

    public static HashMap<Integer, Location> getHomes() {
        return home;
    }

    public static boolean isHomeExist(int i) {
        if (home.containsKey(i)) {
            return true;
        } else {
            return false;
        }
    }

    public static void setHome(double x, double y, double z) {
        home.put(home.size() + 1, new Location(Bukkit.getServer().getWorld("pandasucks"), x, y, z));
    }

    public static void setHome(Location loc) {
        home.put(home.size() + 1, loc);
    }

    public static void setHome(int i, Location loc) {
        home.put(i, loc);
    }

    public static boolean isIsSelfServer() {
        return isSelfServer;
    }
}
