package me.rocket.broadcast.events;

import me.rocket.broadcast.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class Events implements Listener {

    ArrayList<Player> sleeping = new ArrayList<>();
    private static HashMap<String, Character> colors = new HashMap<>();

    public static void setPlayerColor(Player player, char color) {
        colors.put(player.getName(), color);
    }

    public static void setPlayerColor(String player, char color) {
        colors.put(player, color);
    }

    public char getPlayerColor(Player player) {
        if (colors.containsKey(player.getName())) {
            return colors.get(player.getName());
        }

        return 'e';
    }

    @EventHandler
    public void onSleep(PlayerBedEnterEvent e) {
        if(e.getBedEnterResult() != PlayerBedEnterEvent.BedEnterResult.OK) {
            return;
        }
        sleeping.add(e.getPlayer());
        Utils.send("&7" + e.getPlayer().getName() + "&a has gone to sleep. You should too. &7(&a" + sleeping.size() + "&7/&a" + Bukkit.getOnlinePlayers().size() + "&7)");
    }

    @EventHandler
    public void onStopSleep(PlayerBedLeaveEvent e) {
        sleeping.remove(e.getPlayer());
        Utils.send("&7" + e.getPlayer().getName() + "&a has awaken.");
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (player.hasPermission("stuff.chat.color")) {
            e.setMessage(Utils.format(e.getMessage()));
        }

        if (player.getName().equalsIgnoreCase("supremegodofgods")) {
            e.setFormat(Utils.format("&" + getPlayerColor(player) + "[GOD] " + player.getName() + "&7: " + e.getMessage()));
        } else {
            e.setFormat(Utils.format("&" + getPlayerColor(player) + player.getName() + "&7: " + e.getMessage()));
        }
    }
}
