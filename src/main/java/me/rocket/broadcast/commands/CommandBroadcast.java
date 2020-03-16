package me.rocket.broadcast.commands;

import me.rocket.broadcast.utils.Config;
import me.rocket.broadcast.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandBroadcast implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Config config = new Config();

        if (!(sender instanceof Player)) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                str.append(args[i] + " ");
            }

            String s = str.toString();

            Bukkit.getServer().broadcastMessage(Utils.format(config.getBroadcastPrefix() + s));
            Utils.log("&aSent broadcast successfully!");

            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 100, 1);
            }
            return false;
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("broadcast")) {
            if (sender.hasPermission("rocket.broadcast")) {
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    str.append(args[i] + " ");
                }
                String s = str.toString();

                Bukkit.getServer().broadcastMessage(Utils.format(config.getBroadcastPrefix() + s));
                Utils.send(player, "&aSent broadcast successfully!");
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100, 1);
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
