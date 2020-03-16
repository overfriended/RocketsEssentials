package me.rocket.broadcast.commands;

import me.rocket.broadcast.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandFly implements CommandExecutor {
    ArrayList<Player> flying = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.log("&cMust be a player!");
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("fly")) {
            if (sender.hasPermission("stuff.fly")) {
                if (flying.contains(player)) {
                    Utils.send(player, "&cFlight is now &7disabled&c!");
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    flying.remove(player);
                } else {
                    Utils.send(player, "&aFlight is now &7enabled&a!");
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    flying.add(player);
                }
            }
        }

        return false;
    }
}
